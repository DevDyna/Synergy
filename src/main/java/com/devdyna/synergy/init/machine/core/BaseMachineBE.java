package com.devdyna.synergy.init.machine.core;

import java.util.List;

import com.devdyna.synergy.api.beLogic.MachineIO;
import com.devdyna.synergy.api.coreBE.be.BEMenu;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;

@SuppressWarnings("null")
public abstract class BaseMachineBE extends BEMenu implements MachineIO {

    protected int progress = 0;
    protected int maxProgress;
    protected MachineItemHandler storage;
    /**
     * Server side data sended to client side render
     */
    protected ContainerData networkData;

    public BaseMachineBE(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }

    @Override
    public int getMachineSlots() {
        return 2;
    }

    @Override
    public List<Integer> getInputSlotIndex() {
        return List.of(0);
    }

    @Override
    public List<Integer> getOutputSlotIndex() {
        return List.of(1);
    }

    @Override
    public int getSlotLimit(int slotindex) {
        return getStorage().getSlotLimit(slotindex);
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        return getStorage().getStackInSlot(index);
    }

    @Override
    public boolean isItemValid(int slot, ItemStack itemStack) {
        return getInputSlotIndex().contains(slot);
    }

    @Override
    public ItemStackHandler getStorage() {
        return storage;
    }

    @Override
    public void setStackInSlot(int slot, ItemStack itemStack) {
        getStorage().setStackInSlot(slot, itemStack);
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        tag.put("inventory", getStorage().serializeNBT(registries));
        tag.putInt("progress", progress);
        super.saveAdditional(tag, registries);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        getStorage().deserializeNBT(registries, tag.getCompound("inventory"));
        if (tag.contains("progress"))
            progress = tag.getInt("progress");
        super.loadAdditional(tag, registries);
    }

    protected class MachineItemHandler extends ItemStackHandler {

        public MachineItemHandler(int machineSlots) {
            super(machineSlots);
        }

        @Override
        public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
            if (getInputSlotIndex().contains(slot))
                return super.insertItem(slot, stack, simulate);
            return stack;
        }

        @Override
        public ItemStack extractItem(int slot, int amount, boolean simulate) {
            if (getOutputSlotIndex().contains(slot))
                return super.extractItem(slot, amount, simulate);
            return ItemStack.EMPTY;
        }

        @Override
        public CompoundTag serializeNBT(Provider provider) {
            return super.serializeNBT(provider);
        }

        @Override
        public void deserializeNBT(HolderLookup.Provider provider, CompoundTag nbt) {
            super.deserializeNBT(provider, nbt);
        }

    }

    public void tickBoth() {

    }

    public void tickClient() {

    }

    public void tickServer() {

    }

}
