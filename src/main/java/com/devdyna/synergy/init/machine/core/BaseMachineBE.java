package com.devdyna.synergy.init.machine.core;

import java.util.List;

import com.devdyna.synergy.api.beLogic.EnergyBlock;
import com.devdyna.synergy.api.beLogic.MachineIO;
import com.devdyna.synergy.api.coreBE.be.BEMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.Containers;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.energy.EnergyStorage;
import net.neoforged.neoforge.items.ItemStackHandler;

@SuppressWarnings("null")
public abstract class BaseMachineBE extends BEMenu implements MachineIO, EnergyBlock {

    protected int progress = 0;
    protected int maxProgress;
    protected int energy = 0;
    protected int maxEnergy = 0;

    protected boolean progress_cancel;

    protected MachineItemHandler storage;
    /**
     * Server side data sent to client side render
     */
    public ContainerData networkData;

    protected EnergyStorage energyStorage;

    public BaseMachineBE(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }

    public void dropItems() {
        SimpleContainer inv = new SimpleContainer(getStorage().getSlots());
        for (int i = 0; i < getStorage().getSlots(); i++)
            inv.setItem(i, getStorage().getStackInSlot(i));
        Containers.dropContents(this.level, this.worldPosition, inv);
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
        tag.putInt("energy", energyStorage.getEnergyStored());
        super.saveAdditional(tag, registries);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        getStorage().deserializeNBT(registries, tag.getCompound("inventory"));
        if (tag.contains("progress"))
            progress = tag.getInt("progress");

        if (tag.contains("energy"))
            energyStorage.receiveEnergy(Math.min(tag.getInt("energy"), energyStorage.getMaxEnergyStored()), false);

        super.loadAdditional(tag, registries);
    }

    @Override
    public EnergyStorage getCapEnergy() {
        return energyStorage;
    }

    @Override
    public int MaxFE() {
        return 10_000;
    }

    @Override
    public ContainerData getContainerData() {
        return networkData;
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

    public boolean isCrafting() {
        return progress > 0 && !progress_cancel;
    }

    public int getProgress() {
        return progress;
    }

    public int getMaxProgress() {
        return maxProgress;
    }
}
