package com.devdyna.synergy.api.resource_gen;

import java.util.HashMap;
import java.util.Map;

import com.devdyna.synergy.api.basebe.be.TickingBE;
import com.devdyna.synergy.api.beLogic.FluidExporter;
import com.devdyna.synergy.api.beLogic.SimpleFluidStorage;
import com.devdyna.synergy.api.utils.Ticker;
import com.devdyna.synergy.api.utils.x;
import com.devdyna.synergy.init.types.zHandlers;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.capabilities.BlockCapabilityCache;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.fluids.capability.IFluidHandler.FluidAction;
import net.neoforged.neoforge.fluids.capability.templates.FluidTank;

@SuppressWarnings("null")
public abstract class BaseWaterRGBE extends TickingBE implements SimpleFluidStorage, FluidExporter {

    public static final Map<Direction, BlockCapabilityCache<IFluidHandler, Direction>> cache = new HashMap<>();

    protected Ticker ticker;

    public BaseWaterRGBE(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }

    @Override
    public void tickServer() {
        if (ticker.commit()) {
            if (!export())
                increaseStored();
        }
    }

    @Override
    public FluidTank getFluidStorage() {
        return getData(zHandlers.FLUID_TANK);
    }

    public FluidStack getFluidOutput() {
        return x.fluid(Fluids.WATER, getFluidAmount());
    }

    public abstract int getFluidAmount();

    public boolean export() {
        var output = getFluidOutput();

        for (Direction dir : Direction.values()) {

            var cap = Capabilities.FluidHandler.BLOCK.getCapability(level, getBlockPos().relative(dir),
                    level.getBlockState(getBlockPos().relative(dir)), level.getBlockEntity(getBlockPos().relative(dir)),
                    dir);

            if (cap == null)
                continue;

            if (level.getBlockState(getBlockPos().relative(dir)).getBlock() instanceof BaseResourceGenBlock)
                continue;

            if (!getFluidStorage().getFluidInTank(0).isEmpty())
                output = getFluidStorage().drain(getFluidStorage().getFluidAmount(), FluidAction.EXECUTE);

            for (int i = 0; i < cap.getTanks(); i++) {
                if (cap.getFluidInTank(i).isEmpty() || cap.isFluidValid(i, output)) {
                    cap.fill(output, FluidAction.EXECUTE);
                    return true;
                }
            }

        }

        return false;
    }

    public void increaseStored() {
        if (getFluidStorage().isEmpty() || getFluidStorage().getFluidAmount() < getFluidStorage().getFluidAmount())
            getFluidStorage().fill(getFluidOutput().copy(), FluidAction.EXECUTE);
    }

}
