package com.devdyna.synergy.api.machine;

import java.util.List;

import com.devdyna.synergy.api.basebe.be.BEMenu;
import com.devdyna.synergy.api.beLogic.EnergyBlock;
import com.devdyna.synergy.api.beLogic.MachineItemAutomation;

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
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemStackHandler;

@SuppressWarnings("null")
public abstract class BaseMachineBE extends BEMenu implements MachineItemAutomation, EnergyBlock {

    public static final int DEFAULT_FE_COST = 10;
    public static final int DEFAULT_TICK_DURATION = 60;

    protected int progress = 0;
    protected int maxProgress;
    protected int energy = 0;
    protected int maxEnergy = 0;

    public static final int PROGRESS_INDEX = 0;
    public static final int MAX_PROGRESS_INDEX = 1;
    public static final int ENERGY_INDEX = 2;
    public static final int MAX_ENERGY_INDEX = 3;

    public static final int INPUT_SLOT = 0;
    public static final int OUTPUT_SLOT = 1;

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

    // @Override
    // public int getMachineSlots() {
    // return 2;
    // }

    @Override
    public List<Integer> getInputSlotIndex() {
        return List.of(INPUT_SLOT);
    }

    @Override
    public List<Integer> getOutputSlotIndex() {
        return List.of(OUTPUT_SLOT);
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

    public IItemHandler getAutomationHandler() {
        return new IItemHandler() {

            @Override
            public int getSlots() {
                return storage.getSlots();
            }

            @Override
            public ItemStack getStackInSlot(int slot) {
                return storage.getStackInSlot(slot);
            }

            @Override
            public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
                if (getInputSlotIndex().contains(slot)
                        && (this instanceof UpgradeSlots upgrades ? !upgrades.getUpgradeIndexs().contains(slot) : true))
                    return storage.insertItem(slot, stack, simulate);
                return stack;
            }

            @Override
            public ItemStack extractItem(int slot, int amount, boolean simulate) {
                if (getOutputSlotIndex().contains(slot))
                    return storage.extractItem(slot, amount, simulate);
                return ItemStack.EMPTY;
            }

            @Override
            public int getSlotLimit(int slot) {
                return storage.getSlotLimit(slot);
            }

            @Override
            public boolean isItemValid(int slot, ItemStack stack) {
                return getInputSlotIndex().contains(slot);
            }
        };
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
        return 10_000;// TODO config
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
            return super.extractItem(slot, amount, simulate);
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
        if (level == null || level.isClientSide())
            return;
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

    public ItemStack getInput() {
        return getStorage().getStackInSlot(INPUT_SLOT);
    }

    public ItemStack getOutput() {
        return getStorage().getStackInSlot(OUTPUT_SLOT);
    }

    /**
     * Return <code>true</code> when success
     */
    public boolean checkSlot(ItemStack slot, ItemStack recipeSlot) {
        if (!slot.isEmpty()) {
            // same item
            if (ItemStack.isSameItemSameComponents(slot, recipeSlot)) {
                // count valid
                if (slot.getMaxStackSize() < slot.getCount() + recipeSlot.getCount()) {
                    return false;
                }

            } else {
                return false;
            }
        }
        return true;
    }

    // /**
    // * Return <code>true</code> when success
    // */
    // public boolean checkOptionalSlot(ItemStack slot, Ingredient recipeSlot) {
    // if (!slot.isEmpty()) {
    // // not same item or no items
    // if (recipeSlot.hasNoItems() || !recipeSlot.test(slot)) {
    // return false;
    // }
    // }
    // return true;
    // }

    /**
     * Return <code>true</code> when success
     */
    public boolean checkAndConsumeFE(int min) {
        if (energyStorage.getEnergyStored() >= min && !progress_cancel) {
            energyStorage.extractEnergy(min, false);
            return true;
        } else
            return false;
    }

    public void updateOutputSlot(ItemStack stack, ItemStack slotStack, int slotIndex) {
        if (stack.isEmpty())
            storage.setStackInSlot(slotIndex, slotStack);
        else if (ItemStack.isSameItemSameComponents(stack, slotStack))
            stack.grow(slotStack.getCount());
    }
}
