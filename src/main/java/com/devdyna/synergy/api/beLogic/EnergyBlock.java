package com.devdyna.synergy.api.beLogic;

import net.minecraft.world.inventory.ContainerData;
import net.neoforged.neoforge.transfer.energy.EnergyHandler;
import net.neoforged.neoforge.transfer.transaction.Transaction;

public interface EnergyBlock {

    /**
     * Something like "new SimpleContainerData(getMaxFE())"
     */
    ContainerData getContainerData();

    /**
     * Something like "getData(zHandlers.ENERGY_STORAGE)"
     */
    EnergyHandler getCapEnergy();

    int MaxFE();

    /**
     * Block -> Energy
     */
    default boolean extractFE(int value) {
        try (var tx = Transaction.openRoot()) {

            if (getCapEnergy().extract(value, tx) != value)
                return false;

            tx.commit();
            return true;
        }
    }

    default int getStoredFE() {
        return getCapEnergy().getAmountAsInt();
    }

    default int getMaxFE() {
        return getCapEnergy().getCapacityAsInt();
    }

    /**
     * Block <- Energy
     */
    default boolean increaseFE(int value) {
        
        try (var tx = Transaction.openRoot()) {

            if (getCapEnergy().insert(value, tx) != value)
                return false;

            tx.commit();
            return true;
        }


    }

    /**
     * FE not empty / full
     * REQUIRE SERVER ONLY
     */
    default boolean canExtract() {
        return hasEnergy(0);
    }

    /**
     * FE not empty / full
     * REQUIRE SERVER ONLY
     */
    default boolean hasEnergy(int v, boolean equal) {
        return (equal ? getStoredFE() >= v : getStoredFE() > v);
    }

    /**
     * FE not empty / full
     * REQUIRE SERVER ONLY
     */
    default boolean hasEnergy(int v) {
        return getStoredFE() > v;
    }

    /**
     * FE not full / empty
     */
    default boolean canReceive() {
        return getStoredFE() < getMaxFE();
    }

}