package com.devdyna.synergy.init.builder.pipeBlocks.nodes.blocks;

import javax.annotation.Nullable;

import com.devdyna.synergy.init.Material;
import com.devdyna.synergy.init.builder._core.BaseBlockBE;
import com.devdyna.synergy.init.builder._core.pipes.nodeType;
import com.devdyna.synergy.init.builder.pipeBlocks.nodes.blockentities.ItemTransferBE;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

@SuppressWarnings("null")
public class ItemTranfer extends BaseBlockBE implements nodeType {

    public ItemTranfer() {
        super(Material.bProp);
    }

    @Override
    protected void createBlockStateDefinition(Builder<Block, BlockState> b) {
        nodeType.NodeStateDefinition(b);
    }

    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext c) {
        return nodeType.updateNodeOnPlace(defaultBlockState(), c.getClickedPos(),c.getLevel(),c.getClickedFace());
    }

    @Override
    protected VoxelShape getShape(BlockState s, BlockGetter l, BlockPos p, CollisionContext c) {
        return nodeType.getNodeBaseShape(s);
    }

    @Override
    public void destroy(LevelAccessor l, BlockPos p, BlockState s) {
        nodeType.onDestroyNode(s, (Level) l, p);
    }

    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block neighborBlock,
            BlockPos neighborPos, boolean movedByPiston) {
        nodeType.updateNodeOnPlace(state, pos, level, state.getValue(nodeType.FACING).getOpposite());
        super.neighborChanged(state, level, pos, neighborBlock, neighborPos, movedByPiston);
    }

    @Override
    @Nullable
    public BlockEntity newBlockEntity(BlockPos p, BlockState s) {
        return new ItemTransferBE(p, s);
    }

}
