package com.devdyna.synergy.api.node_pipe.builder;

import java.util.List;

import javax.annotation.Nullable;

import com.devdyna.synergy.Main;
import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.init.builder.pipe_blocks.pipeBlock;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Item.TooltipContext;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

@SuppressWarnings("null")
public abstract class NodeBaseBlock extends pipeBlock implements EntityBlock {

    public static final DirectionProperty FACING = BlockStateProperties.FACING;

    public static final VoxelShape DOWN_NODE_SHAPE = Shapes.box(0.0625, 0, 0.0625, 0.9375, 0.1875, 0.9375);
    public static final VoxelShape UP_NODE_SHAPE = Shapes.box(0.0625, 0.8125, 0.0625, 0.9375, 1, 0.9375);
    public static final VoxelShape NORTH_NODE_SHAPE = Shapes.box(0.0625, 0.0625, 0, 0.9375, 0.9375, 0.1875);
    public static final VoxelShape SOUTH_NODE_SHAPE = Shapes.box(0.0625, 0.0625, 0.8125, 0.9375, 0.9375, 1);
    public static final VoxelShape WEST_NODE_SHAPE = Shapes.box(0, 0.0625, 0.0625, 0.1875, 0.9375, 0.9375);
    public static final VoxelShape EAST_NODE_SHAPE = Shapes.box(0.8125, 0.0625, 0.0625, 1, 0.9375, 0.9375);

    public NodeBaseBlock() {
        super();
    }

    @Override
    protected void createBlockStateDefinition(Builder<Block, BlockState> b) {
        b.add(FACING);
        super.createBlockStateDefinition(b);
    }

    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext c) {
       return defaultBlockState()
                .setValue(BlockStateProperties.FACING, c.getClickedFace().getOpposite())
                .setValue(BlockStateProperties.NORTH, false)
                .setValue(BlockStateProperties.SOUTH, false)
                .setValue(BlockStateProperties.EAST, false)
                .setValue(BlockStateProperties.WEST, false)
                .setValue(BlockStateProperties.UP, false)
                .setValue(BlockStateProperties.DOWN, false);
    }

    @Override
    protected VoxelShape getShape(BlockState s, BlockGetter l, BlockPos p, CollisionContext c) {
        VoxelShape model = super.getShape(s, l, p, c);

        model = switch (s.getValue(FACING)) {
            case Direction.DOWN -> Shapes.or(model, DOWN_NODE_SHAPE);
            case Direction.UP -> Shapes.or(model, UP_NODE_SHAPE);
            case Direction.NORTH -> Shapes.or(model, NORTH_NODE_SHAPE);
            case Direction.SOUTH -> Shapes.or(model, SOUTH_NODE_SHAPE);
            case Direction.WEST -> Shapes.or(model, WEST_NODE_SHAPE);
            case Direction.EAST -> Shapes.or(model, EAST_NODE_SHAPE);
        };

        return model.optimize();
    }

 

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

    @Override
    public void appendHoverText(ItemStack i, TooltipContext c, List<Component> t,
            TooltipFlag f) {
        t.add(Component.translatable(Main.ID + "." + zStatic.Blocks.pipe + ".extend"));
    }
}
