package com.devdyna.synergy.api.beLogic;

import java.util.Map;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.capabilities.BlockCapabilityCache;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.energy.IEnergyStorage;

public interface EnergyProvider extends EnergyBlock {

    default void providePowerAdjacent(Level level, BlockPos pos,
            Map<Direction, BlockCapabilityCache<IEnergyStorage, Direction>> cache, int feRate) {

        var be = level.getBlockEntity(pos);

        if (be == null)
            return;

        if (((EnergyBlock) be).canExtract())
            for (Direction dir : Direction.values()) {
                var cachedData = cache.get(dir);
                if (cachedData == null)
                    cachedData = BlockCapabilityCache.create(
                            Capabilities.EnergyStorage.BLOCK,
                            (ServerLevel) level,
                            pos.relative(dir),
                            dir.getOpposite());
                cache.put(dir, cachedData);

                IEnergyStorage cap = cachedData.getCapability();
                if (cap == null || level.getBlockState(pos.relative(dir)).is(level.getBlockState(pos).getBlock()))
                    continue;
                int simOn = cap.receiveEnergy(feRate * 10, true);
                if (simOn <= 0)
                    continue;
                cap.receiveEnergy(((EnergyBlock) be).extractFE(simOn, false), false);
            }
    }


    /**
     * Fe rate when ready to produce
     * <br/><br/>
     * Set to -1 to ignore
     */
    int getFERate();
}
