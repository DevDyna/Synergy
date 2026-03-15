package com.devdyna.synergy.api.node_pipe;

import net.neoforged.neoforge.energy.IEnergyStorage;

public interface EnergyNodeType {
        default void moveEnergy(IEnergyStorage input, IEnergyStorage output, int maxEnergy) {

        if (input == null || output == null)
            return;

        int remaining = maxEnergy;

        // Try to extract energy from input
        int extracted = input.extractEnergy(remaining, false);
        if (extracted <= 0)
            return;

        // Try to insert into output
        int accepted = output.receiveEnergy(extracted, false);
        int leftover = extracted - accepted;

        // If some energy couldn't be accepted, put it back into input
        if (leftover > 0) {
            input.receiveEnergy(leftover, false);
        }
    }

}
