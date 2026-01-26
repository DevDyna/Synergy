package com.devdyna.synergy.api;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.common.util.INBTSerializable;
import net.neoforged.neoforge.fluids.capability.templates.FluidTank;

@SuppressWarnings("null")
public class FluidStorageTank extends FluidTank implements INBTSerializable<CompoundTag> {

    private final BlockEntity be;

    public FluidStorageTank(BlockEntity be, int capacity) {
        super(capacity);
        this.be = be;
    }

    @Override
    protected void onContentsChanged() {
        super.onContentsChanged();

        if (be == null || be.getLevel() == null)
            return;

        be.setChanged();

        if (!be.getLevel().isClientSide) {
            be.getLevel().sendBlockUpdated(
                    be.getBlockPos(),
                    be.getBlockState(),
                    be.getBlockState(),
                    3);
        }
    }

    @Override
    public CompoundTag serializeNBT(HolderLookup.Provider provider) {
        return super.writeToNBT(provider, new CompoundTag());
    }

    @Override
    public void deserializeNBT(HolderLookup.Provider provider,
            CompoundTag tag) {
        fluid = super.readFromNBT(provider, tag).getFluid();
    }

    public float getPercentuage() {
        return (float) getFluidAmount() / getCapacity();
    }

}
