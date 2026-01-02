package com.devdyna.synergy.api.node.builder;

import javax.annotation.Nullable;

import com.devdyna.synergy.api.BlockAbilities.tooltips.multi_simple.NodeType;
import com.devdyna.synergy.api.node.nodeType;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

@SuppressWarnings("null")
public abstract class NodeBaseBlock extends Block implements nodeType, EntityBlock, NodeType {

    public NodeBaseBlock() {
        super(BlockBehaviour.Properties.of().destroyTime(0.5f).forceSolidOn()
                .sound(SoundType.WOOL).mapColor(MapColor.TERRACOTTA_GRAY));
    }

    @Override
    protected void createBlockStateDefinition(Builder<Block, BlockState> b) {
        nodeType.NodeStateDefinition(b);
    }

    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext c) {
        return nodeType.updateNodeOnPlace(defaultBlockState(), c.getLevel(), c.getClickedPos(), c.getClickedFace());
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
    public void onNeighborChange(BlockState state, LevelReader levelReader, BlockPos pos, BlockPos neighbor) {
        super.onNeighborChange(state, levelReader, pos, neighbor);
        if (levelReader instanceof Level level)
            level.setBlockAndUpdate(pos,
                    nodeType.updateNodeOnPlace(state, level, pos, state.getValue(nodeType.FACING).getOpposite()));
    }

    // @Override
    // public void appendHoverText(ItemStack i, TooltipContext c, List<Component> t,
    // TooltipFlag f) {
    // t.add(Component.translatable(Main.ID + "." + zStatic.Blocks.pipe +
    // ".extend"));
    // }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level l, BlockState s,
            BlockEntityType<T> ty) {
        return (lvl, pos, b, t) -> {
            if (t instanceof NodeBaseBE be) {
                be.tickBoth();
                if (l.isClientSide())
                    be.tickClient();
                else
                    be.tickServer();
            }
        };
    }

}
