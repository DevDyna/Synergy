package com.devdyna.synergy.init.builder.laser.sensor;

import java.util.*;

import com.devdyna.synergy.api.beLogic.EnergyProvider;
import com.devdyna.synergy.api.coreBE.be.TickingBE;
import com.devdyna.synergy.init.types.zBlockEntities;
import com.devdyna.synergy.init.types.zHandlers;
import com.devdyna.synergy.utils.LevelUtil;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.capabilities.BlockCapabilityCache;
import net.neoforged.neoforge.energy.EnergyStorage;
import net.neoforged.neoforge.energy.IEnergyStorage;

@SuppressWarnings("null")
public class LaserSensorBE extends TickingBE implements EnergyProvider {

    private final Map<Direction, BlockCapabilityCache<IEnergyStorage, Direction>> cache = new HashMap<>();

    public LaserSensorBE(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }

    public LaserSensorBE(BlockPos p, BlockState s) {
        this(zBlockEntities.LASER_SENSOR.get(), p, s);
    }

    public int MAX_TIMER_COOLDOWN = 20;

    private int tick = 0;

    @Override
    public void tickServer() {

        level.setBlockAndUpdate(getBlockPos(), getBlockState().setValue(BlockStateProperties.ENABLED, tick > 0));

        if (tick > 0) {
            if (tick % 4 == 0)
                increaseFE(1, false);
            tick--;
        }

        providePowerAdjacent(level, getBlockPos(), cache, getStoredFE());
    }

    @Override
    public void tickBoth() {
        if (tick > 0)
            if (LevelUtil.chance(25, level))
                LevelUtil.addDustParticle(255, 0, 0, (ServerLevel) level, getBlockPos().getX(),
                        getBlockPos().getY() - 0.5, getBlockPos().getZ(), true, 1);
    }

    public void setActive() {
        tick = MAX_TIMER_COOLDOWN;
    }

    @Override
    public ContainerData getContainerData() {
        return new SimpleContainerData(getMaxFE());
    }

    @Override
    public EnergyStorage getCapEnergy() {
        return getData(zHandlers.ENERGY_STORAGE);
    }

    @Override
    public int MaxFE() {
        return 1_000;
    }

}
