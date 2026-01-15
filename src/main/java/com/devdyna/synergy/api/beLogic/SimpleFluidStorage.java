package com.devdyna.synergy.api.beLogic;

import net.neoforged.neoforge.fluids.capability.templates.FluidTank;

public interface SimpleFluidStorage {

    FluidTank getFluidStorage();

    int getFluidCapacity();

}
