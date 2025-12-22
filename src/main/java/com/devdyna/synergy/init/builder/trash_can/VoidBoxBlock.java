package com.devdyna.synergy.init.builder.trash_can;

import javax.annotation.Nullable;

import com.devdyna.synergy.api.basebe.block.TickingBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

@SuppressWarnings("null")
public class VoidBoxBlock extends TickingBlock {

    public VoidBoxBlock(Properties properties) {
        super(properties
                .strength(0.4f)
                .destroyTime(0.4f)
                .sound(SoundType.SCULK)
                .mapColor(MapColor.DEEPSLATE));
    }

    public VoidBoxBlock() {
        this(Properties.of());
    }

    @Override
    @Nullable
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new VoidBoxBE(pos, state);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> b) {
        b.add(BlockStateProperties.ENABLED, BlockStateProperties.HORIZONTAL_FACING);
    }

    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext c) {
        return defaultBlockState()
                .setValue(BlockStateProperties.ENABLED, true)
                .setValue(BlockStateProperties.HORIZONTAL_FACING, c.getHorizontalDirection().getOpposite());
    }

    @Override
    protected VoxelShape getShape(BlockState s, BlockGetter l, BlockPos p, CollisionContext c) {
        return Block.box(4, 0, 4, 12, 8, 12);
    }

    @Override
    protected void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean movedByPiston) {

        if (state.getBlock() != newState.getBlock())
            if (level.getBlockEntity(pos) instanceof VoidBoxBE be) {

                be.drops();

                level.updateNeighbourForOutputSignal(pos, this);
            }

        super.onRemove(state, level, pos, newState, movedByPiston);
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos,
            Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (level.getBlockEntity(pos) instanceof VoidBoxBE be)
            return be.itemUseOn(player, level, pos, hand);
        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

}
