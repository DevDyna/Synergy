package com.devdyna.synergy.init.builder.laser.transmitter;

import java.util.*;

import com.devdyna.synergy.api.beLogic.EnergyBlock;
import com.devdyna.synergy.api.coreBE.be.TickingBE;
import com.devdyna.synergy.init.types.zBlockEntities;
import com.devdyna.synergy.init.types.zHandlers;
import com.devdyna.synergy.utils.ColorUtil;
import com.devdyna.synergy.utils.LevelUtil;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.energy.EnergyStorage;

@SuppressWarnings("null")
public class LaserMachineBE extends TickingBE implements EnergyBlock {
    List<Integer> rgbColor;

    public LaserMachineBE(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);

        var random = new Random();

        var color = ColorUtil.colorfulColorList.get(random.nextInt(ColorUtil.colorfulColorList.size()));

        rgbColor = List.of(color.getRed(), color.getGreen(), color.getBlue());
    }

    public LaserMachineBE(BlockPos p, BlockState s) {
        this(zBlockEntities.LASER_MACHINE.get(), p, s);
    }

    @Override
    public void tickServer() {
        level.setBlockAndUpdate(getBlockPos(),
                getBlockState()
                        .setValue(BlockStateProperties.ENABLED, level.hasNeighborSignal(getBlockPos())));

    }

    @Override
    public void tickBoth() {
        var state = getBlockState();
        var pos = getBlockPos();
        var facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
        var lenght = 100;
        var intensify = 100;

        if (state.getValue(BlockStateProperties.ENABLED) && canExtract()) {
            extractFE(10, false);

            var currentPos = pos;

            var i = 0;

            while (i != 4 && lenght != 0 && intensify != 0) {
                LevelUtil.addDustParticleLine(rgbColor.get(0), rgbColor.get(1), rgbColor.get(2),
                        (ServerLevel) level, currentPos, facing);

                currentPos = currentPos.relative(facing);

                if (level.getBlockState(currentPos).is(Blocks.GLASS)) {
                    lenght++;
                    intensify--;
                    i = 0;
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

}
