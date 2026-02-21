package com.devdyna.synergy.init.builder.magic.void_box;

import com.devdyna.synergy.api.basebe.be.AnimatedChestBE;
import com.devdyna.synergy.api.beLogic.ItemStorageBlock;
import com.devdyna.synergy.api.beLogic.NoGuiStorage;
import com.devdyna.synergy.init.types.zBlockEntities;
import com.devdyna.synergy.init.types.zHandlers;
import com.devdyna.synergy.init.types.zItemTag;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Containers;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.capabilities.BlockCapabilityCache;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemStackHandler;

@SuppressWarnings("null")
public class VoidBoxBE extends AnimatedChestBE implements NoGuiStorage, ItemStorageBlock {
    private BlockCapabilityCache<IItemHandler, Direction> cache;

    public VoidBoxBE(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }

    public VoidBoxBE(BlockPos pos, BlockState blockState) {
        this(zBlockEntities.VOID_BOX.get(), pos, blockState);
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
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider pRegistries) {
        super.saveAdditional(tag, pRegistries);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider pRegistries) {
        super.loadAdditional(tag, pRegistries);
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
        return getStorage().insertItem(0, stack, false);
    }

    public ItemStack extractItem() {
        ItemStack extracted = getStorage().extractItem(0, getStorage().getStackInSlot(0).getCount(), false);
        if (!extracted.isEmpty())
            return extracted;
        return ItemStack.EMPTY;
    }

    @Override
    public void tickServer() {

        if (level == null)
            return;

        boolean powered = !level.hasNeighborSignal(getBlockPos());

        if (powered != getBlockState().getValue(BlockStateProperties.ENABLED)) {
            level.setBlockAndUpdate(
                    getBlockPos(),
                    getBlockState().setValue(BlockStateProperties.ENABLED, powered));
        }

        if (!getBlockState().getValue(BlockStateProperties.ENABLED) || cache == null)
            return;

        var slot = this.cache.getCapability();

        if (slot == null)
            return;

        var item = slot.getStackInSlot(0);

        if (item.isEmpty())
            return;

        if (item.is(zItemTag.VOID_BOX_DENY))
            return;

        slot.extractItem(0, item.getCount(), false);
    }

}
