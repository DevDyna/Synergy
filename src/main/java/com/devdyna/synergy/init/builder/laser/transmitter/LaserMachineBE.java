package com.devdyna.synergy.init.builder.laser.transmitter;

import java.util.*;

import com.devdyna.synergy.api.beLogic.EnergyBlock;
import com.devdyna.synergy.api.coreBE.be.TickingBE;
import com.devdyna.synergy.init.types.zBlockEntities;
import com.devdyna.synergy.init.types.zBlocks;
import com.devdyna.synergy.init.types.zHandlers;
import com.devdyna.synergy.utils.ColorUtil;
import com.devdyna.synergy.utils.LevelUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.level.Level.ExplosionInteraction;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.energy.EnergyStorage;

@SuppressWarnings("null")
public class LaserMachineBE extends TickingBE implements EnergyBlock {
    List<Integer> rgbColor;

    public boolean fused;

    public LaserMachineBE(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);

        var random = new Random();

        fused = false;

        var color = ColorUtil.colorfulColorList.get(random.nextInt(ColorUtil.colorfulColorList.size()));

        rgbColor = List.of(color.getRed(), color.getGreen(), color.getBlue());
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
        // var lenght = 100;
        // var intensify = 100;

        if (state.getValue(BlockStateProperties.ENABLED) && canExtract()) {
            extractFE(10, false);

            var currentPos = pos;

            var i = 0;

            boolean isStart = true;
            var currentDir = facing;

            while (i != 4) {

                if (!isStart)
                    LevelUtil.addDustParticleLine(rgbColor.get(0), rgbColor.get(1), rgbColor.get(2),
                            (ServerLevel) level, currentPos, currentDir);

                var offset = level.getBlockState(currentPos);

                if (offset.is(zBlocks.LASER_LENS)) {
                    // lenght++;
                    // intensify--;
                    i = 0;
                }

                if (offset.is(zBlocks.LASER_MIRROR)) {
                    currentDir = switch (offset.getValue(BlockStateProperties.INVERTED) ? currentDir
                            : currentDir.getOpposite()) {
                        case Direction.NORTH -> Direction.EAST;
                        case Direction.SOUTH -> Direction.WEST;
                        case Direction.EAST -> Direction.NORTH;
                        case Direction.WEST -> Direction.SOUTH;
                        default -> null;
                    };
                    i = 0;
                }

                if (offset.is(zBlocks.LASER_MACHINE) && !isStart) {

                    if (level.getBlockEntity(currentPos) instanceof LaserMachineBE laser)
                        laser.setFused();

                    level.explode(null, currentPos.getX() + 0.5, currentPos.getY() + 0.5, currentPos.getZ() + 0.5, 1,
                            ExplosionInteraction.BLOCK);
                    break;
                }

                currentPos = currentPos.relative(currentDir);

                if (isStart)// prevent to select the initial laser machine
                    isStart = false;

                if (offset.isCollisionShapeFullBlock(level, pos)) {
                    break;
                }

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

}
