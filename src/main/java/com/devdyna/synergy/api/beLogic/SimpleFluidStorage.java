package com.devdyna.synergy.api.beLogic;

import com.devdyna.synergy.api.FluidStorageTank;
import com.devdyna.synergy.api.utils.x;

import net.minecraft.world.item.ItemStack;

public interface SimpleFluidStorage {

    FluidStorageTank getFluidStorage();

    int getFluidCapacity();

    default ItemStack getAsBucket() {
        return x.item(getFluidStorage().getFluid().getFluid().getBucket());
    }

}
