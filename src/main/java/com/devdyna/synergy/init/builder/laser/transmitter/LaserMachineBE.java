package com.devdyna.synergy.init.builder.laser.transmitter;

import java.util.*;

import com.devdyna.synergy.api.beLogic.EnergyBlock;
import com.devdyna.synergy.api.coreBE.be.TickingBE;
import com.devdyna.synergy.init.builder.laser.sensor.LaserSensorBE;
import com.devdyna.synergy.init.types.zBlockEntities;
import com.devdyna.synergy.init.types.zBlocks;
import com.devdyna.synergy.init.types.zHandlers;
import com.devdyna.synergy.utils.LevelUtil;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.level.Level.ExplosionInteraction;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.AABB;
import net.neoforged.neoforge.energy.EnergyStorage;

@SuppressWarnings("null")
public class LaserMachineBE extends TickingBE implements EnergyBlock {

    public boolean fused;

    public int red = 255;
    public int green = 0;
    public int blue = 0;

    protected final int MAX_LASER_LENGHT = 8;
    protected final int RESET = 0;

    public LaserMachineBE(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
        fused = false;
    }

    public LaserMachineBE(BlockPos p, BlockState s) {
        this(zBlockEntities.LASER_MACHINE.get(), p, s);
    }

    @Override
    public void tickServer() {
        if (!fused)
            level.setBlockAndUpdate(getBlockPos(),
                    getBlockState()
                            .setValue(BlockStateProperties.ENABLED, level.hasNeighborSignal(getBlockPos())));
    }

    @Override
    public void tickBoth() {
        var state = getBlockState();
        var pos = getBlockPos();
        var facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);

        if (state.getValue(BlockStateProperties.ENABLED) && canExtract()) {
            extractFE(10, false);

            var currentPos = pos;

            var i = RESET;

            boolean isStart = true;
            var currentDir = facing;

            while (i < MAX_LASER_LENGHT) {

                var partialState = level.getBlockState(currentPos);

                // if collide with a opaque block or a entity break
                if (!level.getEntities(null, new AABB(currentPos)).isEmpty()
                        || partialState.isCollisionShapeFullBlock(level, pos)) {
                    break;
                }

                if (partialState.is(zBlocks.LASER_LENS)) {
                    i = RESET;
                }

                if (partialState.is(zBlocks.LASER_SENSOR)) {
                    if (canSensorHandle(currentDir, partialState)) {
                        var be = level.getBlockEntity(currentPos);
                        if (be != null && be instanceof LaserSensorBE ls) {
                            ls.setActive();
                        }
                    } else
                        break;
                }

                if (partialState.is(zBlocks.LASER_MIRROR)) {
                    var newDir = getMirrorDir(currentDir, partialState);

                    LevelUtil.addDustParticleDiagonalLine(red, green, blue, (ServerLevel) level, currentPos,
                            currentDir, newDir,
                            0.35F);

                    currentDir = newDir;
                    i = RESET;
                } else if (!isStart)

                    LevelUtil.addDustParticleLine(red, green, blue,
                            (ServerLevel) level, currentPos, currentDir, 0.35F);// TODO change scale

                if (partialState.is(zBlocks.LASER_MACHINE) && !isStart) {

                    if (level.getBlockEntity(currentPos) instanceof LaserMachineBE laser)
                        laser.setFused();

                    level.explode(null, currentPos.getX() + 0.5, currentPos.getY() + 0.5, currentPos.getZ() + 0.5, 1,
                            ExplosionInteraction.BLOCK);
                    break;
                }

                currentPos = currentPos.relative(currentDir);

                if (isStart)// prevent to select the initial laser machine
                    isStart = false;

                i++;

            }

        }

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
        return 1000;
    }

    public void setFused() {
        fused = true;
    }

    public Direction getMirrorDir(Direction dir, BlockState state) {
        return switch (state.getValue(BlockStateProperties.INVERTED) ? dir
                : dir.getOpposite()) {
            case Direction.NORTH -> Direction.EAST;
            case Direction.SOUTH -> Direction.WEST;
            case Direction.EAST -> Direction.NORTH;
            case Direction.WEST -> Direction.SOUTH;
            default -> null;
        };
    }

    public boolean canSensorHandle(Direction dir, BlockState state) {
        var inverted = state.getValue(BlockStateProperties.INVERTED);
        var dirs = inverted ? List.of(Direction.EAST, Direction.WEST) : List.of(Direction.NORTH, Direction.SOUTH);
        return dirs.contains(dir);
    }

    @Override
    protected void saveAdditional(CompoundTag tag, Provider registries) {

        tag.putInt("red", red);
        tag.putInt("green", green);
        tag.putInt("blue", blue);
        super.saveAdditional(tag, registries);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, Provider registries) {

        if (tag.contains("red"))
            red = tag.getInt("red");
        if (tag.contains("green"))
            green = tag.getInt("green");
        if (tag.contains("blue"))
            blue = tag.getInt("blue");
        super.loadAdditional(tag, registries);
    }

    public void setRed(int red) {
        this.red = red;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }

}
