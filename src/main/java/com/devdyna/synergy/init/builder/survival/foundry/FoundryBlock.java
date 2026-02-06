package com.devdyna.synergy.init.builder.survival.foundry;

import java.util.List;
import javax.annotation.Nullable;

import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.api.basebe.block.TickingBlock;
import com.devdyna.synergy.api.beLogic.BucketInteraction;
import com.devdyna.synergy.api.beLogic.FluidClearableTank;
import com.devdyna.synergy.api.beLogic.FluidTooltipWhenEmpty;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
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
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.neoforge.fluids.capability.templates.FluidTank;

import static com.devdyna.synergy.Main.ID;

@SuppressWarnings("null")
public class FoundryBlock extends TickingBlock
        implements BucketInteraction, FluidClearableTank, FluidTooltipWhenEmpty {

    public FoundryBlock(Properties properties) {
        super(properties);
    }

    public FoundryBlock() {
        this(Properties.of().sound(SoundType.METAL).mapColor(MapColor.METAL).instrument(NoteBlockInstrument.BASS).strength(1F, 2.25F)
                .noOcclusion());
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    protected void createBlockStateDefinition(Builder<Block, BlockState> b) {
        b.add(BlockStateProperties.HORIZONTAL_FACING, BlockStateProperties.ENABLED);
    }

    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext c) {
        return defaultBlockState()
                .setValue(BlockStateProperties.ENABLED, false)
                .setValue(BlockStateProperties.HORIZONTAL_FACING, c.getHorizontalDirection().getOpposite());
    }

    @Override
    @Nullable
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new FoundryBE(pos, state);
    }

    @Override
    protected void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean movedByPiston) {
        if (state.getBlock() != newState.getBlock())
            if (level.getBlockEntity(pos) instanceof FoundryBE be) {
                be.drops();
                level.updateNeighbourForOutputSignal(pos, this);
            }
        super.onRemove(state, level, pos, newState, movedByPiston);
    }

    @Override
    public boolean showWhen(BlockEntity be) {
        return (be instanceof FoundryBE tank) ? tank.getStorage().getStackInSlot(0).isEmpty() : true;
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos,
            Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (level.getBlockEntity(pos) instanceof FoundryBE)
            return bucketAction(stack, state, level, pos, player, hand, hitResult);
        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    @Override
    public ItemInteractionResult executeWhenEmpty(ItemStack stack, BlockState state, Level level, BlockPos pos,
            Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (player.isCrouching())
            return useItemToClear(state, level, pos, player, hitResult);
        else
            return sendFluidTooltip(stack, state, level, pos, player, hand, hitResult);
    }

    @Override
    public ItemInteractionResult onTooltipFail(ItemStack stack, BlockState state, Level level, BlockPos pos,
            Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (level.getBlockEntity(pos) instanceof FoundryBE be)
            return be.itemUseOn(player, level, pos, hand);
        return ItemInteractionResult.FAIL;
    }

    @Override
    public ItemInteractionResult executeWhenNotBucket(ItemStack stack, BlockState state, Level level, BlockPos pos,
            Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (level.getBlockEntity(pos) instanceof FoundryBE be)
            return be.itemUseOn(player, level, pos, hand);
        return ItemInteractionResult.FAIL;
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        if (state.getValue(BlockStateProperties.ENABLED)) {
            Direction dir = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
            Axis axis = dir.getAxis();

            double x = pos.getX() + 0.5;
            double y = pos.getY() + random.nextDouble() * 6.0 / 16.0;
            double z = pos.getZ() + 0.5;

            double spread = random.nextDouble() * 0.6 - 0.3;
            double offX = axis == Direction.Axis.X ? dir.getStepX() * 0.52 : spread;
            double offZ = axis == Direction.Axis.Z ? dir.getStepZ() * 0.52 : spread;

            level.addParticle(ParticleTypes.SMOKE, x + offX, y, z + offZ, 0, 0, 0);

        }
    }

    @Override
    public FluidTank getFluidTank(BlockEntity be, BlockState state, Level level, BlockPos pos, Player player,
            InteractionHand hand, BlockHitResult hitResult) {
        return (be instanceof FoundryBE tank) ? tank.getFluidStorage() : null;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context,
            List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.translatable(ID + "." + zStatic.Blocks.foundry));
    }

}
