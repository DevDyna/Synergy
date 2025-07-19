package com.devdyna.synergy.api.beLogic;

import net.minecraft.world.inventory.ContainerData;
import net.neoforged.neoforge.energy.EnergyStorage;

public interface EnergyBlock {

    /**
     * Something like "new SimpleContainerData(getMaxFE())"
     */
    ContainerData getContainerData();

    /**
     * Something like "getData(zHandlers.ENERGY_STORAGE)"
     */
    EnergyStorage getCapEnergy();

    default int MaxFE() {
        return 10000;
    }

    /**
     * Block -> Energy
     */
    default int extractFE(int value, boolean simOn) {
        return getCapEnergy().extractEnergy(value, simOn);
    }

    default int getStoredFE() {
        return getCapEnergy().getEnergyStored();
    }

    default int getMaxFE() {
        return getCapEnergy().getMaxEnergyStored();
    }

    /**
     * Block <- Energy
     */
    default int increaseFE(int value, boolean simOn) {
        return getCapEnergy().receiveEnergy(value, simOn);
    }

    /**
     * FE not empty / full
     * REQUIRE SERVER ONLY
     */
    default boolean canExtract() {
        return getCapEnergy().getEnergyStored() > 0;
    }

    /**
     * FE not full / empty
     */
    default boolean canReceive() {
        return getCapEnergy().canReceive();
    }

}