package com.devdyna.synergy.init.builder.pipe_blocks;

import javax.annotation.Nullable;

import com.devdyna.synergy.api.pipe.pipeProperties;
import com.devdyna.synergy.api.pipe.pipeType;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

@SuppressWarnings("null")
public class pipeBlock extends Block implements pipeType {

    public pipeBlock() {
        super(BlockBehaviour.Properties.of().destroyTime(0.125f).forceSolidOn()
                .sound(SoundType.WOOL).mapColor(MapColor.TERRACOTTA_GRAY));
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
    protected void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston) {
        // TODO check if work
        if (!level.isClientSide()) {
            BlockState newState = state;
            for (Direction face : DIRECTIONS) {
                BlockPos neighborPos = pos.relative(face);
                BlockState neighborState = level.getBlockState(neighborPos);

                if (neighborState.is(this)) {
                    newState = newState.setValue(PROPRTIES.get(DIRECTIONS.indexOf(face)), pipeProperties.TRUE);

                    level.setBlock(neighborPos,
                            neighborState.setValue(PROPRTIES.get(DIRECTIONS.indexOf(face.getOpposite())),
                                    pipeProperties.TRUE),
                            Block.UPDATE_ALL);
                }
            }
            level.setBlock(pos, newState, Block.UPDATE_ALL);
        }
    }

    @Override
    public void destroy(LevelAccessor l, BlockPos p, BlockState s) {
        pipeType.onDestroyPipe(s, (Level) l, p);
    }

@Override
public void onNeighborChange(BlockState state, LevelReader levelrReader, BlockPos pos, BlockPos neighbor) {
    if(levelrReader instanceof Level level)
    level.setBlockAndUpdate(pos, pipeType.updatePipeOnPlace(state, level, pos));
}

   

    // @Override
    // public void appendHoverText(ItemStack stack, TooltipContext context,
    // List<Component> tooltipComponents,
    // TooltipFlag tooltipFlag) {
    // tooltipComponents.add(Component.translatable(Main.ID + "." +
    // zStatic.Blocks.pipe + ".desc"));
    // tooltipComponents.add(Component.translatable(Main.ID + ".safe_building"));
    // }

}
