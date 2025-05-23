package com.devdyna.synergy.init.builder.pipe;

import java.util.List;
import javax.annotation.Nullable;

import com.devdyna.synergy.init.Material;
import com.devdyna.synergy.init.types.zBlocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.PipeBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

@SuppressWarnings("null")
public class pipeBlock extends Block {

    public pipeBlock() {
        super(Material.bProp);
    }

    VoxelShape BASE = Block.box(6, 6, 6, 10, 10, 10);
    VoxelShape X_PART = Block.box(0, 6, 6, 6, 10, 10);//e-w
    VoxelShape Y_PART = Block.box(6, 10, 6, 10, 16, 10);//u-d
    VoxelShape Z_PART = Block.box(6, 6, 0, 10, 10, 6);//n-s

    @Override
    protected void createBlockStateDefinition(Builder<Block, BlockState> b) {
        b.add(PipeBlock.NORTH, PipeBlock.SOUTH, PipeBlock.EAST, PipeBlock.WEST, PipeBlock.UP, PipeBlock.DOWN);
    }

    public static List<BooleanProperty> prop = List.of(
            PipeBlock.UP,
            PipeBlock.DOWN,
            PipeBlock.NORTH,
            PipeBlock.SOUTH,
            PipeBlock.WEST,
            PipeBlock.EAST);

    public static List<Direction> Odir = List.of(
            Direction.DOWN,
            Direction.UP,
            Direction.SOUTH,
            Direction.NORTH,
            Direction.EAST,
            Direction.WEST);

    public static List<Direction> dir = List.of(
            Direction.UP,
            Direction.DOWN,
            Direction.NORTH,
            Direction.SOUTH,
            Direction.WEST,
            Direction.EAST);

    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext c) {
        BlockState state = defaultBlockState();
        for (Direction face : dir) {
            var offset = c.getLevel().getBlockState(c.getClickedPos().relative(face));
            if (!offset.is(zBlocks.PIPE.get())) {
                state = state.setValue(prop.get(dir.indexOf(face)), false);
            } else {
                // default is true
                c.getLevel().setBlockAndUpdate(c.getClickedPos().relative(face),
                        offset.setValue(prop.get(Odir.indexOf(face)),
                                true));
            }
        }
        return state;
    }

    @Override
    protected VoxelShape getShape(BlockState s, BlockGetter l, BlockPos p, CollisionContext c) {
        VoxelShape model = BASE;
        if(s.getValue(PipeBlock.DOWN)) model = Shapes.or(model, Y_PART.move(0, -0.625, 0));
        if(s.getValue(PipeBlock.UP)) model = Shapes.or(model, Y_PART);
        if(s.getValue(PipeBlock.SOUTH)) model = Shapes.or(model, Z_PART.move(0, 0, 0.625));
        if(s.getValue(PipeBlock.NORTH)) model = Shapes.or(model, Z_PART);
        if(s.getValue(PipeBlock.EAST)) model = Shapes.or(model, X_PART.move(0.625, 0, 0));
        if(s.getValue(PipeBlock.WEST)) model = Shapes.or(model, X_PART);
        
        return model.optimize();
    }

}
