package com.devdyna.synergy.api.beLogic;

import java.util.List;
import java.util.Map;

import com.devdyna.synergy.api.utils.x;

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
    default boolean exportFluids(FluidStack fluid, List<Direction> blacklistedDirs, Level level, BlockPos pos,
            Map<Direction, BlockCapabilityCache<IFluidHandler, Direction>> cache) {

        var totalDir = Direction.values().length;
        for (Direction dir : Direction.values()) {
            if (blacklistedDirs.contains(dir)) {
                totalDir--;
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
                totalDir--;
                continue;
            }

            if (cap.getFluidInTank(0).getAmount() == cap.getTankCapacity(0)) {
                totalDir--;
                continue;
            }

            if (!fluid.isEmpty()) {
                cap.fill(x.fluid(fluid.getFluid(), Math.min(fluid.getAmount(), cap.getTankCapacity(0))),
                        FluidAction.EXECUTE);
            }

            break;

        }
        return totalDir <= 0;
    }

}
