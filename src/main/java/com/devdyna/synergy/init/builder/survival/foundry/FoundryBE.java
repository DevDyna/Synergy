package com.devdyna.synergy.init.builder.survival.foundry;

import java.util.Optional;

import javax.annotation.Nullable;

import com.devdyna.synergy.api.FluidStorageTank;
import com.devdyna.synergy.api.basebe.be.TickingBE;
import com.devdyna.synergy.api.beLogic.EnvironmentModifier;
import com.devdyna.synergy.api.beLogic.ItemStorageBlock;
import com.devdyna.synergy.api.beLogic.NoGuiStorage;
import com.devdyna.synergy.api.beLogic.SimpleFluidStorage;
import com.devdyna.synergy.api.beLogic.TimeredRecipe;
import com.devdyna.synergy.api.blockfactories.machine.BaseMachineBlock;
import com.devdyna.synergy.api.utils.Ticker;
import com.devdyna.synergy.common.recipes.input.FluidInput;
import com.devdyna.synergy.common.recipes.input.MonoItemInput;
import com.devdyna.synergy.common.recipes.type.FoundryFuelEfficiencyRecipe;
import com.devdyna.synergy.config.Common;
import com.devdyna.synergy.init.builder.automation.tank.FluidTankBE;
import com.devdyna.synergy.init.builder.survival.foundry.recipe.FoundryRecipe;
import com.devdyna.synergy.init.types.zBlockEntities;
import com.devdyna.synergy.init.types.zHandlers;
import com.devdyna.synergy.init.types.zRecipeTypes;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
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
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.capabilities.BlockCapabilityCache;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.fluids.capability.IFluidHandler.FluidAction;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemStackHandler;

@SuppressWarnings("null")
public class FoundryBE extends TickingBE
        implements NoGuiStorage, ItemStorageBlock, SimpleFluidStorage, TimeredRecipe, EnvironmentModifier {

    private BlockCapabilityCache<IItemHandler, Direction> cache;

    public static final int FLUID_BURN_RATE = 25;

    public FoundryBE(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }

    public FoundryBE(BlockPos pos, BlockState blockState) {
        this(zBlockEntities.FOUNDRY.get(), pos, blockState);
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
        update();
        return getStorage().insertItem(0, stack, false);
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
    public void tickServer() {

        if (level == null) {
            fail();
            return;
        }

        if (cache == null) {
            fail();
            return;
        }

        var slot = this.cache.getCapability();

        if (slot == null) {
            fail();
            return;
        }

        var item = slot.getStackInSlot(0);

        update();

        if (item.isEmpty()) {
            fail();
            return;
        }

        if (getFuelTankStorage() == null) {
            fail();
            return;
        }

        if (getFuelTankStorage().isEmpty()) {
            fail();
            return;
        }

        Optional<RecipeHolder<FoundryFuelEfficiencyRecipe>> f = level.getRecipeManager()
                .getRecipeFor(zRecipeTypes.FOUNDRY_FUELS.getType(),
                        new FluidInput(getFuelTankStorage().getFluid()), level);

        if (f.isEmpty()) {
            fail();
            return;
        }

        var fuel = f.get().value();

        if (getFuelTankStorage().getFluidAmount() < (FLUID_BURN_RATE * fuel.getUsageModifier())) {
            fail();
            return;
        }

        Optional<RecipeHolder<FoundryRecipe>> r = level.getRecipeManager()
                .getRecipeFor(zRecipeTypes.FOUNDRY.getType(),
                        new MonoItemInput(item), level);

        if (r.isEmpty()) {
            fail();
            return;
        }

        var recipe = r.get().value();

        if (getFluidStorage().fill(recipe.getFluid().copy(), FluidAction.SIMULATE) == 0) {
            fail();
            return;
        }

        updateState(true);

        if (ticker == null)
            ticker = Ticker.of(calcTicks(recipe.getTicks()));

        if (ticker.commit()) {
            getFluidStorage().fill(recipe.getFluid().copy(), FluidAction.EXECUTE);
            getStorage().extractItem(0, 1, false);
            getFuelTankStorage().drain((int) (FLUID_BURN_RATE * fuel.getUsageModifier()), FluidAction.EXECUTE);
        }

        update();

    }

    private int calcTicks(int base) {
        return (int) Math.max(1, base / getTickerSpeed());
    }

    public void fail() {
        update();
        ticker = null;
        if (getBlockState().getValue(BaseMachineBlock.ENABLED))
            updateState(false);
    }

    public void updateState(boolean v) {
        level.setBlockAndUpdate(getBlockPos(), getBlockState().setValue(BlockStateProperties.ENABLED, v));

    }

    public FluidStorageTank getFuelTankStorage() {
        return (level.getBlockEntity(getBlockPos().below()) instanceof FluidTankBE tank) ? tank.getFluidStorage()
                : null;
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
        return (float) Math.max(1f,
                getSpeedModifier() *
                        (Common.DISABLE_FOUNDRY_SPEED_BOOSTER.get() ? 1.0f
                                : ((getFluidStorage().getPercentuage() + Common.FOUNDRY_SPEED_BOOSTER_MULTIPLIER.get())
                                        / Common.FOUNDRY_SPEED_BOOSTER_MULTIPLIER.get())));
    }

    @Override
    public float getSpeedModifier() {

        if (getFuelTankStorage() == null)
            return 0.0f;

        Optional<RecipeHolder<FoundryFuelEfficiencyRecipe>> f = level.getRecipeManager()
                .getRecipeFor(zRecipeTypes.FOUNDRY_FUELS.getType(),
                        new FluidInput(getFuelTankStorage().getFluid()), level);

        return (f.isEmpty() ? 1.0f : f.get().value().getSpeedModifier());
    }

}
