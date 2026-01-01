package com.devdyna.synergy.api.beLogic;

import java.util.Map;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.capabilities.BlockCapabilityCache;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.transfer.energy.EnergyHandler;
import net.neoforged.neoforge.transfer.transaction.Transaction;

public interface EnergyProvider extends EnergyBlock {

    default void providePowerAdjacent(Level level, BlockPos pos,
            Map<Direction, BlockCapabilityCache<EnergyHandler, Direction>> cache, int feRate) {

        var be = level.getBlockEntity(pos);

        if (be == null)
            return;

        if (((EnergyBlock) be).canExtract())
            for (Direction dir : Direction.values()) {
                var cachedData = cache.get(dir);
                if (cachedData == null)
                    cachedData = BlockCapabilityCache.create(
                            Capabilities.Energy.BLOCK,
                            (ServerLevel) level,
                            pos.relative(dir),
                            dir.getOpposite());
                cache.put(dir, cachedData);

                EnergyHandler cap = cachedData.getCapability();
                if (cap == null || level.getBlockState(pos.relative(dir)).is(level.getBlockState(pos).getBlock()))
                    continue;

                try (var tx = Transaction.openRoot()) {
                    int simOn = cap.insert(feRate * 10, tx);
                    if (simOn <= 0)
                        continue;
                    ((EnergyBlock) be).extractFE(simOn);
                    cap.insert(simOn, tx);
                    tx.commit();

                }

            }
    }

    /**
     * Fe rate when ready to produce
     * <br/>
     * <br/>
     * Set to -1 to ignore
     */
    int getFERate();
}
