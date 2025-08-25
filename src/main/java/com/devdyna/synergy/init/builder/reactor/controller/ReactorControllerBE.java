package com.devdyna.synergy.init.builder.reactor.controller;

import java.util.*;
import com.devdyna.synergy.api.beLogic.EnergyProvider;
import com.devdyna.synergy.api.beLogic.AreaOfEffect;
import com.devdyna.synergy.api.coreBE.BaseBE;
import com.devdyna.synergy.init.types.zBlockEntities;
import com.devdyna.synergy.init.types.zHandlers;
import com.devdyna.synergy.utils.ColorUtil;
import com.devdyna.synergy.utils.LevelUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.capabilities.BlockCapabilityCache;
import net.neoforged.neoforge.energy.EnergyStorage;
import net.neoforged.neoforge.energy.IEnergyStorage;

@SuppressWarnings("null")
public class ReactorControllerBE extends BaseBE implements EnergyProvider, AreaOfEffect {

    private final Map<Direction, BlockCapabilityCache<IEnergyStorage, Direction>> cache = new HashMap<>();

    public ReactorControllerBE(BlockPos pos, BlockState state) {
        super(zBlockEntities.REACTOR_CONTROLLER.get(), pos, state);
        var random = new Random();

        var color = ColorUtil.colorfulColorList.get(random.nextInt(ColorUtil.colorfulColorList.size()));

        rgbColor = List.of(color.getRed(), color.getGreen(), color.getBlue());
    }

    int i = 0;
    List<BlockPos> area = null;
    List<Integer> rgbColor;

    int delay = 1;

    @Override
    public void tickServer() {

        level.setBlockAndUpdate(getBlockPos(),
                getBlockState().setValue(BlockStateProperties.ENABLED,
                        area != null && level.hasNeighborSignal(getBlockPos())));

        if (area == null) {
            area = getAreaSelection(level, getBlockState().getValue(BlockStateProperties.HORIZONTAL_FACING),
                    getBlockPos());
        }

        if (getBlockState().getValue(BlockStateProperties.ENABLED)) {
            checkBlocks(level);
            // TODO GEN
        }

        if (canExtract()) {
            providePowerAdjacent(level, getBlockPos(), cache, Math.min(getStoredFE(), 256));
        }

    }

    private void checkBlocks(Level level) {
        int size = area.size();

        if (i < size) {

            LevelUtil.addDustParticle(rgbColor.get(0), rgbColor.get(1), rgbColor.get(2),
                    (ServerLevel) level, area.get(i), false, 4);

            level.playSound(null, getBlockPos(),
                    SoundEvents.CAMPFIRE_CRACKLE,
                    SoundSource.BLOCKS);
            i++;
        }

        if (i >= size) {
            i = 0;
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
    public int radius() {
        return 4;
    }

    @Override
    public int height() {
        return 4;
    }

}
