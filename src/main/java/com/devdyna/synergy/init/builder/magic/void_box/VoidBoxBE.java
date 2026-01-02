package com.devdyna.synergy.init.builder.magic.void_box;

import com.devdyna.synergy.api.basebe.be.TickingBE;
import com.devdyna.synergy.api.beLogic.NoGuiStorage;
import com.devdyna.synergy.api.utils.LevelUtil;
import com.devdyna.synergy.init.types.zBlockEntities;
import com.devdyna.synergy.init.types.zHandlers;
import com.devdyna.synergy.init.types.zItemTag;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.neoforged.neoforge.capabilities.BlockCapabilityCache;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.transfer.ResourceHandler;
import net.neoforged.neoforge.transfer.item.ItemResource;
import net.neoforged.neoforge.transfer.item.ItemStacksResourceHandler;
import net.neoforged.neoforge.transfer.transaction.Transaction;

@SuppressWarnings("null")
public class VoidBoxBE extends TickingBE implements NoGuiStorage {
    private BlockCapabilityCache<ResourceHandler<ItemResource>, Direction> cache;

    private float prevLidProgress;
    private float lidProgress;
    private boolean startSound = false;
    private float temp;

    public VoidBoxBE(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }

    public VoidBoxBE(BlockPos pos, BlockState blockState) {
        this(zBlockEntities.VOID_BOX.get(), pos, blockState);
    }

    @Override
    public ItemStacksResourceHandler getStorage() {
        return getData(zHandlers.ITEM_STORAGE);
    }

    @Override
    public int MachineSlots() {
        return 1;
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        if (this.level instanceof ServerLevel serverLevel) {
            this.cache = BlockCapabilityCache.create(
                    Capabilities.Item.BLOCK,
                    serverLevel,
                    getBlockPos(),
                    null);
        }
    }

    @Override
    public void tickServer() {

        if (level == null)
            return;

        boolean powered = !level.hasNeighborSignal(getBlockPos());

        if (powered != getBlockState().getValue(BlockStateProperties.ENABLED)) {
            level.setBlockAndUpdate(
                    getBlockPos(),
                    getBlockState().setValue(BlockStateProperties.ENABLED, powered));
        }

        if (!getBlockState().getValue(BlockStateProperties.ENABLED) || cache == null)
            return;

        var slot = this.cache.getCapability();

        if (slot == null)
            return;

        var item = slot.getResource(0);

        if (item.isEmpty())
            return;

        if (item.is(zItemTag.VOID_BOX_DENY))
            return;

        try (var tx = Transaction.openRoot()) {
            slot.extract(item, item.toStack().getCount(), tx);
            tx.commit();
        }

    }

    @Override
    public void tickBoth() {

        var pos = getBlockPos();
        if (level == null)
            return;

        var player = level.getNearestPlayer(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 4.5f, false);

        if (lidProgress > 0.01f && level.random.nextFloat() < 0.001f) {
            if (LevelUtil.chance(50, level))
                level.playSound(player, pos, SoundEvents.ALLAY_DEATH, SoundSource.BLOCKS, 0.5f, 0.25f);
            else
                level.playSound(player, pos, SoundEvents.SCULK_SHRIEKER_SHRIEK, SoundSource.BLOCKS, 0.3f, 2f);
        }

        prevLidProgress = lidProgress;
        float increment = 0.1f;
        if (player != null && lidProgress < 1.0f) {
            lidProgress += increment;
        } else if (player == null && lidProgress > 0.0f) {
            lidProgress -= increment;
        }
        lidProgress = Mth.clamp(lidProgress, 0.0f, 1.0f);

        if (!level.isClientSide()) {
            float current = lidProgress;

            if (current != temp && startSound) {
                if (current > temp) {
                    // opening
                    level.playSound(null, pos, SoundEvents.ENDER_CHEST_OPEN,
                            SoundSource.BLOCKS, 1f, 1.1f);
                } else {
                    // closing
                    level.playSound(null, pos, SoundEvents.ENDER_CHEST_CLOSE,
                            SoundSource.BLOCKS, 1f, 1.1f);
                }
                startSound = false;
            }

            // re-arm only at the ends
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
