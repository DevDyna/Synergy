package com.devdyna.synergy.init.builder.automation.sprinkler;

import com.devdyna.synergy.api.basebe.be.TickingBE;
import com.devdyna.synergy.api.beLogic.EnergyBlock;
import com.devdyna.synergy.api.beLogic.SimpleAOE;
import com.devdyna.synergy.api.utils.LevelUtil;
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
public class SprinklerBE extends TickingBE implements EnergyBlock, SimpleAOE {

    public SprinklerBE(BlockPos pos, BlockState state) {
        super(zBlockEntities.SPRINKLER.get(), pos, state);
        if (radius == 0)
            this.radius = 4;
    }

    @Override
    public void tickServer() {

        var x = getBlockPos().getX();
        var y = getBlockPos().getY();
        var z = getBlockPos().getZ();

        level.setBlockAndUpdate(getBlockPos(), getBlockState().setValue(BlockStateProperties.ENABLED,
                canExtract() && !level.hasNeighborSignal(getBlockPos())));

        if (getBlockState().getValue(BlockStateProperties.ENABLED)) {

            BlockPos.randomBetweenClosed(level.random, 1,
                    x - radius, y, z - radius,
                    x + radius, y + 2, z + radius)
                    .forEach(pos -> {
                        BlockState state = level.getBlockState(pos);
                        if (state.isRandomlyTicking() && LevelUtil.chance(75, level)) {
                            if (LevelUtil.chance(25, level))
                                LevelUtil.addParticle(ParticleTypes.HAPPY_VILLAGER, (ServerLevel) level, pos, true);

                            if (LevelUtil.chance(25, level))
                                level.playSound(null, getBlockPos(), SoundEvents.BONE_MEAL_USE, SoundSource.BLOCKS,
                                        0.75F,
                                        1F);

                            if (state.getBlock() instanceof CropBlock cropBlock) {
                                if (!cropBlock.isMaxAge(state))
                                    cropBlock.performBonemeal((ServerLevel) level, level.random, pos, state);
                            } else
                                state.randomTick((ServerLevel) level, pos, level.random);
                            extractFE(25, false);
                        }
                    });
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
        return 10000;
    }

    @Override
    public void onLoad() {
        super.onLoad();
        if (level.isClientSide())
            rebuildArea();
    }


    @Override
    public int radius() {
        return radius;
    }

}
