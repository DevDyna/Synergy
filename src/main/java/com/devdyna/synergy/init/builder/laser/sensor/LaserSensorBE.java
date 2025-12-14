package com.devdyna.synergy.init.builder.laser.sensor;

import java.util.List;

import com.devdyna.synergy.api.basebe.be.TickingBE;
import com.devdyna.synergy.api.utils.LevelUtil;
import com.devdyna.synergy.init.types.zBlockEntities;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

@SuppressWarnings("null")
public class LaserSensorBE extends TickingBE {

    public LaserSensorBE(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }

    public LaserSensorBE(BlockPos p, BlockState s) {
        this(zBlockEntities.LASER_SENSOR.get(), p, s);
    }

    public int MAX_TIMER_COOLDOWN = 5;

    private int tick = 0;

    @Override
    public void tickServer() {

        level.setBlockAndUpdate(getBlockPos(), getBlockState().setValue(BlockStateProperties.ENABLED, tick > 0));

        if (tick > 0)
            tick--;

    }

    @Override
    public void tickBoth() {
        if (tick > 0)
            if (LevelUtil.chance(25, level))
                LevelUtil.addDustParticle(255, 0, 0, (ServerLevel) level, getBlockPos().getX(),
                        getBlockPos().getY() - 0.5, getBlockPos().getZ(), true, 1);
    }

    public boolean canSensorHandle(Direction dir, BlockState state) {
        var inverted = state.getValue(BlockStateProperties.INVERTED);
        var dirs = inverted ? List.of(Direction.EAST, Direction.WEST) : List.of(Direction.NORTH, Direction.SOUTH);
        return dirs.contains(dir);
    }

    public void setActive() {
        tick = MAX_TIMER_COOLDOWN;
    }

}
