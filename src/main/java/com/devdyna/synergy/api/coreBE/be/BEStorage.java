package com.devdyna.synergy.api.coreBE.be;

import com.devdyna.synergy.api.beLogic.ItemStorageBlock;
import com.devdyna.synergy.init.types.zHandlers;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.Containers;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;

@SuppressWarnings("null")
public abstract class BEStorage extends BEMenu implements ItemStorageBlock {

    public BEStorage(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
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

    @Override
    public ItemStackHandler getStorage() {
        return getData(zHandlers.ITEM_STORAGE);
    }

    @Override
    public int MachineSlots() {
        return 1;
    }
}
