package com.devdyna.synergy.init.builder.pipeBlocks.nodes;

import javax.annotation.Nullable;

import com.devdyna.synergy.init.Material;
import com.devdyna.synergy.init.builder._core.BaseBlockBE;
import com.devdyna.synergy.init.builder._core.nodeType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

@SuppressWarnings("null")
public class NodeBlock extends BaseBlockBE implements nodeType {

    public NodeBlock() {
        super(Material.bProp);
    }

    @Override
    protected void createBlockStateDefinition(Builder<Block, BlockState> b) {
        NodeStateDefinition(b);
    }

    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext c) {
        return nodeType.updateNodeOnPlace(defaultBlockState(), c.getLevel(), c.getClickedPos(), c.getClickedFace());
    }

    @Override
    protected VoxelShape getShape(BlockState s, BlockGetter l, BlockPos p, CollisionContext c) {
        return getNodeBaseShape(s);
    }

    @Override
    @Nullable
    public BlockEntity newBlockEntity(BlockPos p, BlockState s) {
        return new NodeBE(p, s);
    }

}
