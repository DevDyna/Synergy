package com.devdyna.synergy.api.beLogic;

import net.neoforged.neoforge.fluids.capability.IFluidHandler;

public interface RestrictedFluidHandler extends SimpleFluidStorage{

    abstract IFluidHandler getFluidStorageRestricted();

}
