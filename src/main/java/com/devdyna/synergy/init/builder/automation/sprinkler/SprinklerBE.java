package com.devdyna.synergy.init.builder.automation.sprinkler;

import java.util.List;

import com.devdyna.synergy.api.basebe.be.AreaBE;
import com.devdyna.synergy.api.beLogic.EnergyBlock;
import com.devdyna.synergy.api.utils.LevelUtil;
import com.devdyna.synergy.config.Common;
import com.devdyna.synergy.init.types.zBlockEntities;
import com.devdyna.synergy.init.types.zHandlers;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.energy.EnergyStorage;

@SuppressWarnings("null")
public class SprinklerBE extends AreaBE implements EnergyBlock {

    public SprinklerBE(BlockPos pos, BlockState state) {
        super(zBlockEntities.SPRINKLER.get(), pos, state);
    }

    @Override
    public void tickServer() {

        level.setBlockAndUpdate(getBlockPos(), getBlockState().setValue(BlockStateProperties.ENABLED,
                canExtract() && !level.hasNeighborSignal(getBlockPos())));

        if (getBlockState().getValue(BlockStateProperties.ENABLED)) {

            if (area == null)
                area = getArea();

            var selectedPos = getRandomPos(area);

            BlockState state = level.getBlockState(selectedPos);
            if (state.isRandomlyTicking() && LevelUtil.chance(75, level)) {
                if (LevelUtil.chance(25, level))
                    LevelUtil.addParticle(ParticleTypes.HAPPY_VILLAGER, (ServerLevel) level, selectedPos, true);

                if (LevelUtil.chance(25, level))
                    level.playSound(null, getBlockPos(), SoundEvents.BONE_MEAL_USE, SoundSource.BLOCKS,
                            0.75F,
                            1F);

                if (state.getBlock() instanceof CropBlock cropBlock) {
                    if (!cropBlock.isMaxAge(state))
                        cropBlock.performBonemeal((ServerLevel) level, level.random, selectedPos, state);
                } else
                    state.randomTick((ServerLevel) level, selectedPos, level.random);
                extractFE(Common.SPRINKLER_FE_COST.get(), false);
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
        return Common.SPRINKLER_MAX_FE.get();
    }

    @Override
    public AreaType getAreaType() {
        return AreaType.MIDDLE;
    }

    @Override
    public int getHeight() {
        return 5;
    }

    @Override
    public int getWidth() {
        return 9;
    }

    @Override
    public List<BlockPos> getArea() {
        return getCentredPosArea();
    }

}
