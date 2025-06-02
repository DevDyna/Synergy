package com.devdyna.synergy.init.builder.pipeBlocks;

import javax.annotation.Nullable;

import com.devdyna.synergy.init.Material;
import com.devdyna.synergy.init.builder._core.pipeType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

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
        return pipeType.updatePipeOnPlace(defaultBlockState(),c);
    }

    @Override
    protected VoxelShape getShape(BlockState s, BlockGetter l, BlockPos p, CollisionContext c) {
        return pipeType.getPipeBaseShape(s);
    }

    @Override
    public void destroy(LevelAccessor l, BlockPos p, BlockState s) {
        pipeType.onDestroyPipe(s, (Level) l, p);
    }

}
