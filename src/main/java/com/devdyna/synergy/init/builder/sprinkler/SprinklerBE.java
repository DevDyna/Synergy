package com.devdyna.synergy.init.builder.sprinkler;

import com.devdyna.synergy.api.capabilities.EnergyBlock;
import com.devdyna.synergy.api.coreBE.BaseBE;
import com.devdyna.synergy.init.types.zBlockEntities;
import com.devdyna.synergy.init.types.zHandlers;
import com.devdyna.synergy.utils.LevelUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.energy.EnergyStorage;

@SuppressWarnings("null")
public class SprinklerBE extends BaseBE implements EnergyBlock {

    private static int radius = 5;

    public SprinklerBE(BlockPos pos, BlockState state) {
        super(zBlockEntities.SPRINKLER.get(), pos, state);
    }

    @Override
    public void tickServer() {

        var x = getBlockPos().getX();
        var y = getBlockPos().getY();
        var z = getBlockPos().getZ();

        if (canExtract())
            BlockPos.randomBetweenClosed(level.random, 3,
                    x - radius, y, z - radius,
                    x + radius, y + 2, z + radius)
                    .forEach(pos -> {
                        BlockState state = level.getBlockState(pos);
                        if (state.isRandomlyTicking()) {
                            LevelUtil.addParticle((ServerLevel) level, pos, ParticleTypes.HAPPY_VILLAGER, true);
                            state.randomTick((ServerLevel) level, pos, level.random);
                            extractFE(25, false);
                        }
                    });
    }

    @Override
    public ContainerData getContainerData() {
        return new SimpleContainerData(getMaxFE());
    }

    @Override
    public EnergyStorage getCapEnergy() {
        return getData(zHandlers.ENERGY_STORAGE);
    }

}
