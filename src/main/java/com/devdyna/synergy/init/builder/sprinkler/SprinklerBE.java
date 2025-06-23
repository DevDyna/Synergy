package com.devdyna.synergy.init.builder.sprinkler;

import com.devdyna.synergy.init.builder._core.BaseBE;
import com.devdyna.synergy.init.types.zBlockEntities;
import com.devdyna.synergy.utils.LevelUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.state.BlockState;

@SuppressWarnings("null")
public class SprinklerBE extends BaseBE {

    private static int radius = 5;

    public SprinklerBE(BlockPos pos, BlockState state) {
        super(zBlockEntities.SPRINKLER.get(), pos, state);
    }

    @Override
    public void tickBoth() {

        var x = getBlockPos().getX();
        var y = getBlockPos().getY();
        var z = getBlockPos().getZ();

        BlockPos.randomBetweenClosed(level.random, 3,
                x - radius, y, z - radius,
                x + radius, y + 2, z + radius)
                .forEach(pos -> {
                    BlockState state = level.getBlockState(pos);
                    if (state.isRandomlyTicking()) {
                        if (LevelUtil.chance(10, level))
                            level.addParticle(ParticleTypes.HAPPY_VILLAGER,
                                    pos.getX() + (0.1 * LevelUtil.getRandomValue(4, level)),
                                    pos.getY() + (0.1 * LevelUtil.getRandomValue(4, level)),
                                    pos.getZ() + (0.1 * LevelUtil.getRandomValue(4, level)),
                                    // TODO tweak particles
                                    5, 5, 5);
                        if (!level.isClientSide)
                            state.randomTick((ServerLevel) level, pos, level.random);
                    }
                });
    }

}
