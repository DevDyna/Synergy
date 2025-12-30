package com.devdyna.synergy.init.builder.laser.machine_gun;

import javax.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

import com.devdyna.synergy.init.builder.laser.sensor.LaserSensorBE;
import com.devdyna.synergy.init.types.zBlockEntities;
import com.devdyna.synergy.init.types.zBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class LaserMachineBE extends AbstractLaserMachine {

    public LaserMachineBE(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }

    public LaserMachineBE(BlockPos p, BlockState s) {
        this(zBlockEntities.LASER_MACHINE.get(), p, s);
    }

    @Override
    public boolean enableWhen(Level level, BlockPos pos) {
        return level.hasNeighborSignal(getBlockPos());
    }

    @Override
    public int getMaxLaserLenght() {
        return 8;
    }

    @Override
    public boolean rotateWhen(Level level, BlockState state, Direction dir, BlockPos pos,
            boolean isStart) {
        return state.is(zBlocks.LASER_MIRROR);
    }

    @Override
    protected boolean breakWhen(Level level, BlockState state, Direction dir, BlockPos pos,
            boolean isStart, @Nullable BlockEntity be) {
        return isEntity(level, state, pos) || isOpaqueBlock(level, state, pos)
                || level.getBlockState(pos.relative(dir.getOpposite())).is(zBlocks.LASER_ROTOR);
    }

    @Override
    protected boolean extendWhen(Level level, BlockState state, Direction dir, BlockPos pos,
            boolean isStart, @Nullable BlockEntity be) {
        return state.is(zBlocks.LASER_LENS);
    }

    @Override
    protected boolean sensorActive(Level level, BlockState partialState, Direction currentDir, BlockPos currentPos,
            boolean isStart, LaserSensorBE partialBE) {
        return partialBE.canSensorHandle(currentDir, partialState);
    }

    @Override
    protected boolean renderParticlesWhen(Level level, BlockState partialState, Direction currentDir,
            BlockPos currentPos, boolean isStart, @Nullable BlockEntity partialBE, int lenght) {
        return !isStart;
    }

    @Override
    protected boolean canExplodeDestination() {
        return true;
    }

    @Override
    protected boolean explodeWhen(Level level, BlockState partialState, Direction currentDir, BlockPos currentPos,
            boolean isStart, @NotNull AbstractLaserMachine partialBE) {
        return !isStart;
    }

    @Override
    protected float getParticlesScale(Level level, BlockState partialState, Direction currentDir, BlockPos currentPos,
            boolean isStart, @Nullable BlockEntity partialBE, int lenght) {
        return 0.45F - (0.005F * lenght);
    }

}
