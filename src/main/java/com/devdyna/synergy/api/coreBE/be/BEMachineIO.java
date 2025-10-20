package com.devdyna.synergy.api.coreBE.be;

import com.devdyna.synergy.api.beLogic.ItemStorageBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.Containers;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

@SuppressWarnings("null")
public abstract class BEMachineIO extends BEMenu implements ItemStorageBlock{

    public BEMachineIO(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }

    public void drops() {
        SimpleContainer inv = new SimpleContainer(getStorage().getSlots());
        for (int i = 0; i < getStorage().getSlots(); i++)
            inv.setItem(i, getStorage().getStackInSlot(i));
        Containers.dropContents(this.level, this.worldPosition, inv);

    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        tag.put("inventory", getStorage().serializeNBT(registries));
        super.saveAdditional(tag, registries);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        getStorage().deserializeNBT(registries, tag.getCompound("inventory"));
        super.loadAdditional(tag, registries);
    }

    // public abstract List<Integer> getInputSlotIndex();

    // public abstract List<Integer> getOutputSlotIndex();

    // @Override
    // public int getContainerSize() {
    // return MachineSlots();
    // }

    // @Override
    // public ItemStack getItem(int slot) {
    // return getStorage().getStackInSlot(slot);
    // }

    // @Override
    // public boolean isEmpty() {

    // for (Integer i : getInputSlotIndex())
    // if (!getStorage().getStackInSlot(i).isEmpty())
    // return false;

    // return true;
    // }

    // @Override
    // public ItemStack removeItem(int slot, int amount) {
    // return getStorage().extractItem(slot, amount, false);
    // }

    // @Override
    // public ItemStack removeItemNoUpdate(int slot) {
    // return getStorage().extractItem(slot,
    // getStorage().getStackInSlot(slot).getCount(), false);
    // }

    // @Override
    // public void setItem(int slot, ItemStack item) {
    // getStorage().setStackInSlot(slot, item);
    // }

    // @Override
    // public boolean stillValid(Player player) {
    // return Container.stillValidBlockEntity(this, player);
    // }

    // @Override
    // public void clearContent() {
    // for (int i = 0; i < getStorage().getSlots(); i++)
    // getStorage().getStackInSlot(i).setCount(0);
    // }



}
