package com.devdyna.synergy.api.basebe.be;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

@SuppressWarnings("null")
public abstract class AnimatedChestBE extends TickingBE {

    public AnimatedChestBE(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }

    private float lidSnapshot;
    private float lidProgress;
    private boolean lastOpenState = false;

    /**
     * Can be used to customize opening animation event
     */
    public boolean defineOpen() {
        return level.getNearestPlayer(
                getBlockPos().getX() + 0.5,
                getBlockPos().getY() + 0.5,
                getBlockPos().getZ() + 0.5,
                4.5f,
                false) == null;
    }

    /**
     * Define sound opening
     */
    public void soundOpening() {
        level.playSound(
                null,
                getBlockPos(),
                SoundEvents.ENDER_CHEST_OPEN,
                SoundSource.BLOCKS,
                1f,
                1.1f);
    }

    /**
     * Define sound closing
     */
    public void soundClosing() {
        level.playSound(
                null,
                getBlockPos(),
                SoundEvents.ENDER_CHEST_CLOSE,
                SoundSource.BLOCKS,
                1f,
                1.1f);
    }

    @Override
    public void tickBoth() {
        super.tickBoth();

        var isOpening = !defineOpen();
        lidSnapshot = lidProgress;

        if (isOpening && lidProgress < 1.0f)
            lidProgress += 0.1f;
        else if (!isOpening && lidProgress > 0.0f)
            lidProgress -= 0.1f;

        lidProgress = Mth.clamp(lidProgress, 0.0f, 1.0f);

        if (!level.isClientSide) {

            if (isOpening != lastOpenState) {

                if (isOpening)
                    soundOpening();
                else
                    soundClosing();

                lastOpenState = isOpening;
            }

        }
    }

    public float getLidProgress(float partialTick) {
        return Mth.lerp(partialTick, lidSnapshot, lidProgress);
    }

    public float getAnimationProgress() {
        return lidProgress;
    }
}