package com.devdyna.synergy.api.blockfactories.heater;

import java.util.List;
import javax.annotation.Nullable;

import com.devdyna.synergy.api.basebe.block.TickingBlock;
import com.devdyna.synergy.init.types.zItemTag;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item.TooltipContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.BlockHitResult;
import static com.devdyna.synergy.Main.ID;

@SuppressWarnings("null")
public abstract class SolidFuelHeaterBlock extends TickingBlock {

    public SolidFuelHeaterBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    protected void createBlockStateDefinition(Builder<Block, BlockState> b) {
        b.add(
                BlockStateProperties.HORIZONTAL_FACING,
                BlockStateProperties.ENABLED,
                BlockStateProperties.OPEN);
    }

    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext c) {
        return defaultBlockState()
                .setValue(BlockStateProperties.OPEN, false)
                .setValue(BlockStateProperties.ENABLED, false)
                .setValue(BlockStateProperties.HORIZONTAL_FACING, c.getHorizontalDirection().getOpposite());
    }

    @Override
    protected void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean movedByPiston) {
        if (state.getBlock() != newState.getBlock())
            if (level.getBlockEntity(pos) instanceof SolidFuelHeaterBE be) {
                be.drops();
                level.updateNeighbourForOutputSignal(pos, this);
            }
        super.onRemove(state, level, pos, newState, movedByPiston);
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos,
            Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (hitResult.getDirection().equals(state.getValue(BlockStateProperties.HORIZONTAL_FACING)))
            if (level.getBlockEntity(pos) instanceof SolidFuelHeaterBE be) {

                if (player.isCrouching()) {
                    level.setBlockAndUpdate(pos,
                            state.setValue(BlockStateProperties.OPEN, !state.getValue(BlockStateProperties.OPEN)));
                    return ItemInteractionResult.SUCCESS;
                }

                var fuel = be.getStorage().getStackInSlot(0);

                if (stack.is(zItemTag.CAN_LIT_SOLID_IGNITER)) {

                    if (fuel.isEmpty())
                        return ItemInteractionResult.FAIL;

                    if (!AbstractFurnaceBlockEntity.isFuel(fuel))
                        return ItemInteractionResult.FAIL;

                    if (!state.getValue(BlockStateProperties.OPEN))
                        return ItemInteractionResult.FAIL;

                    if (state.getValue(BlockStateProperties.ENABLED))
                        return ItemInteractionResult.FAIL;

                    if (!player.isCreative())
                        if (stack.isDamageableItem())
                            stack.hurtAndBreak(1, player, Player.getSlotForHand(hand));
                        else
                            stack.shrink(1);

                    be.updateState(true);

                    level.playSound(player, pos, SoundEvents.FIRECHARGE_USE, SoundSource.BLOCKS, 0.5f, 0.5f);

                    return ItemInteractionResult.SUCCESS;

                }

                if (state.getValue(BlockStateProperties.OPEN))
                    return be.itemUseOn(player, level, pos, hand);
            }

        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        var dir = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
        if (state.getValue(BlockStateProperties.ENABLED)) {
            double xp = pos.getX() + 0.5;
            double yp = pos.getY();
            double zp = pos.getZ() + 0.5;

            if (random.nextDouble() < 0.1)
                level.playLocalSound(xp, yp, zp, SoundEvents.BLASTFURNACE_FIRE_CRACKLE, SoundSource.BLOCKS, 1.0F, 1.0F,
                        false);

            double r = random.nextDouble() * 0.6 - 0.3;

            level.addParticle(ParticleTypes.SMOKE,
                    xp + (dir.getAxis() == Direction.Axis.X ? dir.getStepX() * 0.52 : r),
                    yp + (random.nextDouble() * 9.0 / 16.0),
                    zp + (dir.getAxis() == Direction.Axis.Z ? dir.getStepZ() * 0.52 : r),
                    0.0, 0.0, 0.0);

        }
    }

    public abstract String key();

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context,
            List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.translatable(ID + "." + key()));
    }

}
