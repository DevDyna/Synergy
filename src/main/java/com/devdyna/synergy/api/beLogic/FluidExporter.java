package com.devdyna.synergy.api.beLogic;

import java.util.List;
import java.util.Map;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.capabilities.BlockCapabilityCache;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.fluids.capability.IFluidHandler.FluidAction;

public interface FluidExporter {

    /**
     * export fluids to the nearest storage
     */
    default void exportFluids(FluidStack fluid, List<Direction> blacklistedDirs, Level level, BlockPos pos,
            Map<Direction, BlockCapabilityCache<IFluidHandler, Direction>> cache) {

        boolean success;

        for (Direction dir : Direction.values()) {
            if (blacklistedDirs.contains(dir)) {
                continue;
            }
            var cachedData = cache.get(dir);
            if (cachedData == null)
                cachedData = BlockCapabilityCache.create(
                        Capabilities.FluidHandler.BLOCK,
                        (ServerLevel) level,
                        pos.relative(dir),
                        dir.getOpposite());
            cache.put(dir, cachedData);

            IFluidHandler cap = cachedData.getCapability();

            if (cap == null || !(cap instanceof IFluidHandler)) {
                continue;
            } else {
                success = false;
                for (int i = 0; i < cap.getTanks(); i++) {
                    if (FluidStack.isSameFluidSameComponents(cap.getFluidInTank(i), fluid)
                            && cap.isFluidValid(i, fluid)) {
                        cap.fill(fluid, FluidAction.EXECUTE);
                        success = true;
                        break;
                    }
                }

                if (success)
                    break;
            }

        }

    }
}
