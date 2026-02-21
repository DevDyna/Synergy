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

    private float prevLidProgress;
    private float lidProgress;
    private boolean startSound = false;
    private float temp;

    public boolean defineOpen() {
        return level.getNearestPlayer(getBlockPos().getX() + 0.5, getBlockPos().getY() + 0.5,
                getBlockPos().getZ() + 0.5, 4.5f, false) == null;
    }

    public void soundOpening() {
        level.playSound(null, getBlockPos(), SoundEvents.ENDER_CHEST_OPEN,
                SoundSource.BLOCKS, 1f, 1.1f);
    }

    public void soundClosing() {
        level.playSound(null, getBlockPos(), SoundEvents.ENDER_CHEST_CLOSE,
                SoundSource.BLOCKS, 1f, 1.1f);
    }

    @Override
    public void tickBoth() {
        super.tickBoth();

        if (level == null)
            return;

        prevLidProgress = lidProgress;

        if (!defineOpen() && lidProgress < 1.0f)
            lidProgress += 0.1f;
        else if (defineOpen() && lidProgress > 0.0f)
            lidProgress -= 0.1f;

        lidProgress = Mth.clamp(lidProgress, 0.0f, 1.0f);

        if (!level.isClientSide) {
            float current = lidProgress;

            if (current != temp && startSound) {
                if (current > temp)
                    soundOpening();
                else
                    soundClosing();

                startSound = false;
            }

            if (current <= 0.05f || current >= 0.95f) {
                startSound = true;
            }

            temp = current;
        }

    }

    public float getLidProgress(float partialTick) {
        return Mth.lerp(partialTick, prevLidProgress, lidProgress);
    }

    public float getAnimationProgress() {
        return lidProgress;
    }

}
