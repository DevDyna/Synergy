package com.devdyna.synergy.init.builder.pipeBlocks;

import javax.annotation.Nullable;

import com.devdyna.synergy.init.Material;
import com.devdyna.synergy.init.builder._core.pipes.pipeType;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.common.Tags;

@SuppressWarnings("null")
public class pipeBlock extends Block implements pipeType {

    public pipeBlock() {
        super(Material.bProp);
    }

    @Override
    protected void createBlockStateDefinition(Builder<Block, BlockState> b) {
        pipeType.PipeStateDefinition(b);
    }

    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext c) {
        return pipeType.updatePipeOnPlace(defaultBlockState(), c.getLevel(), c.getClickedPos());
    }

    @Override
    protected VoxelShape getShape(BlockState s, BlockGetter l, BlockPos p, CollisionContext c) {
        return pipeType.getPipeBaseShape(s);
    }

    @Override
    public void destroy(LevelAccessor l, BlockPos p, BlockState s) {
        pipeType.onDestroyPipe(s, (Level) l, p);
    }

    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block neighborBlock,
            BlockPos neighborPos, boolean movedByPiston) {
        pipeType.updatePipeOnPlace(state, level, pos);
        super.neighborChanged(state, level, pos, neighborBlock, neighborPos, movedByPiston);
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos,
            Player player, InteractionHand hand, BlockHitResult hitResult) {

        if (stack.is(Tags.Items.TOOLS_WRENCH)) {
           state = pipeType.updatePipeOnPlace(state, level, pos);
            return ItemInteractionResult.SUCCESS;
        }

        return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
    }

}
