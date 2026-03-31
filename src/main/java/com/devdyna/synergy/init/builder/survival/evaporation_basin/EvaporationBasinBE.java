package com.devdyna.synergy.init.builder.survival.evaporation_basin;

import java.util.Optional;

import javax.annotation.Nullable;

import com.devdyna.synergy.api.FluidStorageTank;
import com.devdyna.synergy.api.basebe.be.TickingBE;
import com.devdyna.synergy.api.beLogic.EnvironmentModifier;
import com.devdyna.synergy.api.beLogic.ItemStorageBlock;
import com.devdyna.synergy.api.beLogic.NoGuiStorage;
import com.devdyna.synergy.api.beLogic.SimpleFluidStorage;
import com.devdyna.synergy.api.beLogic.TimeredRecipe;
import com.devdyna.synergy.api.recipes.inputs.FluidInput;
import com.devdyna.synergy.api.utils.LevelUtil;
import com.devdyna.synergy.api.utils.Ticker;
import com.devdyna.synergy.init.builder.survival.evaporation_basin.recipe.EvaporationBasinRecipe;
import com.devdyna.synergy.init.types.zBlockEntities;
import com.devdyna.synergy.init.types.zBlockTag;
import com.devdyna.synergy.init.types.zHandlers;
import com.devdyna.synergy.init.types.zRecipeTypes;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Containers;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.capabilities.BlockCapabilityCache;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.fluids.capability.IFluidHandler.FluidAction;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemStackHandler;

@SuppressWarnings("null")
public class EvaporationBasinBE extends TickingBE
        implements NoGuiStorage, ItemStorageBlock, SimpleFluidStorage, TimeredRecipe, EnvironmentModifier {

    private BlockCapabilityCache<IItemHandler, Direction> cache;

    public EvaporationBasinBE(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }

    public EvaporationBasinBE(BlockPos pos, BlockState blockState) {
        this(zBlockEntities.EVAPORATION_BASIN.get(), pos, blockState);
    }

    @Override
    public ItemStackHandler getStorage() {
        return getData(zHandlers.ITEM_STORAGE);
    }

    public void drops() {
        SimpleContainer inv = new SimpleContainer(getStorage().getSlots());
        inv.setItem(0, getStorage().getStackInSlot(0));
        Containers.dropContents(this.level, this.worldPosition, inv);
    }

    @Override
    public int MachineSlots() {
        return 1;
    }

    @Override
    public void onLoad() {
        super.onLoad();
        if (this.level instanceof ServerLevel serverLevel) {
            this.cache = BlockCapabilityCache.create(
                    Capabilities.ItemHandler.BLOCK,
                    serverLevel,
                    getBlockPos(),
                    null);
        }
    }

    public ItemStack insertItem(ItemStack stack) {
        return stack;
    }

    public ItemStack extractItem() {
        update();
        ItemStack extracted = getStorage().extractItem(0, getStorage().getStackInSlot(0).getCount(), false);
        if (!extracted.isEmpty())
            return extracted;
        return ItemStack.EMPTY;
    }

    private Ticker ticker = null;

    @Override
    public void tickBoth() {

        if (level == null) {
            fail();
            return;
        }

        if (cache == null) {
            fail();
            return;
        }

        if (getFluidStorage().getFluid().isEmpty()) {
            fail();
            return;
        }

        update();

        Optional<RecipeHolder<EvaporationBasinRecipe>> r = level.getRecipeManager()
                .getRecipeFor(zRecipeTypes.EVAPORATING_BASIN.getType(),
                        new FluidInput(getFluidStorage().getFluid()), level);

        if (r.isEmpty()) {
            fail();
            return;
        }

        var recipe = r.get().value();

        if (getStorage().getStackInSlot(0).getMaxStackSize() < recipe.getOutput().getCount()
                + getStorage().getStackInSlot(0).getCount()) {
            fail();
            return;
        }

        if (LevelUtil.chance(5, level))
            LevelUtil.addParticle(ParticleTypes.CLOUD, level, getBlockPos(), true);

        if (ticker == null)
            ticker = Ticker.of(calcTicks(recipe.getTicks()));

        if (ticker.commit()) {
            getFluidStorage().drain(recipe.getFluid().amount(), FluidAction.EXECUTE);
            getStorage().insertItem(0, recipe.getOutput().copy(), false);
            ticker = null;
        }

        update();

    }

    public void fail() {
        ticker = null;
    }

    private int calcTicks(int base) {
        return (int) Math.max(1,
                base / getTickerSpeed());
    }

    @Override
    public boolean extractOnly() {
        return true;
    }

    @Override
    public FluidStorageTank getFluidStorage() {
        return getData(zHandlers.FLUID_TANK);
    }

    @Override
    public int getFluidCapacity() {
        return 16_000;
    }

    protected void update() {
        setChanged();
        level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
    }

    @Override
    public void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        tag.put("tank", getFluidStorage().serializeNBT(registries));
        tag.put("inventory", getStorage().serializeNBT(registries));
        super.saveAdditional(tag, registries);
    }

    @Override
    public void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        getFluidStorage().deserializeNBT(registries, tag.getCompound("tank"));
        getStorage().deserializeNBT(registries, tag.getCompound("inventory"));
        super.loadAdditional(tag, registries);
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider pRegistries) {
        return saveWithoutMetadata(pRegistries);
    }

    @Override
    public Ticker getTicker() {
        return ticker;
    }

    @Override
    public float getTickerSpeed() {
        return 1.0f * getSpeedModifier();
    }

    @Override
    public float getSpeedModifier() {
        return (level.getBlockState(getBlockPos().below()).is(zBlockTag.EVAPORATION_BASIC_HEATER) ? 2 : 1);
    }

    @Override
    public boolean isRequired() {
        return false;
    }

}
