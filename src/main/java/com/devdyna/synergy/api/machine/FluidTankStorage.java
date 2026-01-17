package com.devdyna.synergy.api.machine;

import com.devdyna.synergy.api.beLogic.SimpleFluidStorage;

public interface FluidTankStorage extends SimpleFluidStorage {
   
    public enum FluidTankType {
        INPUT(),
        OUTPUT();
    }

    abstract FluidTankType getTankIOType();

}
