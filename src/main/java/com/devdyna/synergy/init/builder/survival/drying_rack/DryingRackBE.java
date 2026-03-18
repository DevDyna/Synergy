package com.devdyna.synergy.init.builder.survival.drying_rack;

import java.util.List;
import java.util.Optional;

import javax.annotation.Nullable;

import com.devdyna.synergy.Common;
import com.devdyna.synergy.api.basebe.be.TickingBE;
import com.devdyna.synergy.api.beLogic.EnvironmentModifier;
import com.devdyna.synergy.api.beLogic.ItemStorageBlock;
import com.devdyna.synergy.api.beLogic.NoGuiStorage;
import com.devdyna.synergy.api.beLogic.TimeredRecipe;
import com.devdyna.synergy.api.utils.LevelUtil;
import com.devdyna.synergy.api.utils.Ticker;
import com.devdyna.synergy.api.utils.x;
import com.devdyna.synergy.common.recipes.input.MonoItemInput;
import com.devdyna.synergy.init.builder.survival.drying_rack.recipe.DryingRackRecipe;
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
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemStackHandler;

@SuppressWarnings("null")
public class DryingRackBE extends TickingBE
        implements NoGuiStorage, ItemStorageBlock, TimeredRecipe, EnvironmentModifier {

    private BlockCapabilityCache<IItemHandler, Direction> cache;

    public DryingRackBE(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }

    public DryingRackBE(BlockPos pos, BlockState blockState) {
        this(zBlockEntities.DRYING_RACK.get(), pos, blockState);
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
    public void tickBoth() {

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

        Optional<RecipeHolder<DryingRackRecipe>> r = level.getRecipeManager()
                .getRecipeFor(zRecipeTypes.DRYING_RACK.getType(),
                        new MonoItemInput(item), level);

        if (r.isEmpty()) {
            fail();
            return;
        }

        var recipe = r.get().value();

        if (LevelUtil.chance(5, level))
            LevelUtil.addParticle(ParticleTypes.CLOUD, level, getBlockPos().below(), true);

        if (ticker == null)
            ticker = Ticker.of(calcTicks(recipe.getTicks()));

        if (ticker.commit()) {
            getStorage().extractItem(0, item.getCount(), false);
            getStorage().insertItem(0,
                    x.item(recipe.getOutput().copy().getItem(), recipe.getOutput().copy().getCount() * item.getCount()),
                    false);
            ticker = null;
        }

        update();

    }

    public void fail() {
        ticker = null;
    }

    private int calcTicks(int base) {
        return (int) Math.max(1, base / getTickerSpeed());
    }

    protected void update() {
        setChanged();
        level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
    }

    @Override
    public void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        tag.put("inventory", getStorage().serializeNBT(registries));
        super.saveAdditional(tag, registries);
    }

    @Override
    public void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
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
        return (float) (1.0f
                / (Common.DISABLE_DRYING_RACK_STACK_NERFER.get() ? 1.0f
                        : getStorage().getStackInSlot(0).getCount()
                                / Common.DRYING_RACK_STACK_NERFER_RATE.get())
                * getSpeedModifier());
    }

    @Override
    public float getSpeedModifier() {
        var range = List.of(
                getBlockPos().below(),
                getBlockPos().below().below(),
                getBlockPos().below().below().below(),
                getBlockPos().below().below().below().below(),
                getBlockPos().below().below().below().below().below());

        var result = range
                .stream()
                .map(level::getBlockState)
                .anyMatch(s -> s.is(zBlockTag.DRYING_RACK_HEATER));
        return (result ? 2 : 1);
    }

}
