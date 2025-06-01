package com.devdyna.synergy.init.builder._core;

import java.util.Arrays;
import java.util.List;

import com.devdyna.synergy.init.types.zBlockTag;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.client.model.generators.MultiPartBlockStateBuilder;

public interface pipeType {

    static BooleanProperty NORTH = BlockStateProperties.NORTH;
    static BooleanProperty SOUTH = BlockStateProperties.SOUTH;
    static BooleanProperty EAST = BlockStateProperties.EAST;
    static BooleanProperty WEST = BlockStateProperties.WEST;
    static BooleanProperty UP = BlockStateProperties.UP;
    static BooleanProperty DOWN = BlockStateProperties.DOWN;

    VoxelShape BASE = Block.box(6, 6, 6, 10, 10, 10);
    VoxelShape X_PART = Block.box(0, 6, 6, 6, 10, 10);// e-w
    VoxelShape Y_PART = Block.box(6, 10, 6, 10, 16, 10);// u-d
    VoxelShape Z_PART = Block.box(6, 6, 0, 10, 10, 6);// n-s

     class VoxelShapes {
       public static VoxelShape DOWN = Y_PART.move(0, -0.625, 0);
       public static VoxelShape UP = Y_PART;
       public static VoxelShape NORTH = Z_PART;
       public static VoxelShape SOUTH = Z_PART.move(0, 0, 0.625);
       public static VoxelShape WEST = X_PART;
       public static VoxelShape EAST = X_PART.move(0.625, 0, 0);
    }

    public static List<BooleanProperty> PROPRTIES = List.of(DOWN, UP, NORTH, SOUTH, WEST, EAST);
    public static List<Integer> X_ROT = List.of(-90, 90, 0, 0, 0, 0);
    public static List<Integer> Y_ROT = List.of(0, 0, 0, 180, -90, 90);

    public static List<Direction> DIRECTIONS = Arrays.stream(Direction.values()).toList();

    default void PipeStateDefinition(Builder<Block, BlockState> b) {
        PROPRTIES.forEach(e -> b.add(e));
    }

    default VoxelShape getPipeBaseShape(BlockState s) {
        VoxelShape model = BASE;
        if (s.getValue(DOWN))
            model = Shapes.or(model, VoxelShapes.DOWN);
        if (s.getValue(UP))
            model = Shapes.or(model, VoxelShapes.UP);
        if (s.getValue(SOUTH))
            model = Shapes.or(model, VoxelShapes.SOUTH);
        if (s.getValue(NORTH))
            model = Shapes.or(model, VoxelShapes.NORTH);
        if (s.getValue(EAST))
            model = Shapes.or(model, VoxelShapes.EAST);
        if (s.getValue(WEST))
            model = Shapes.or(model, VoxelShapes.WEST);
        return model.optimize();
    }

    static void getPipeMultiPart(Block b, MultiPartBlockStateBuilder model, ModelFile core, ModelFile pipe) {
        model.part().modelFile(core).addModel();
        model.part().modelFile(pipe).addModel().condition(NORTH, true);
        model.part().modelFile(pipe).rotationY(90).addModel().condition(EAST, true);
        model.part().modelFile(pipe).rotationX(180).addModel().condition(SOUTH, true);
        model.part().modelFile(pipe).rotationY(270).addModel().condition(WEST, true);
        model.part().modelFile(pipe).rotationX(270).addModel().condition(UP, true);
        model.part().modelFile(pipe).rotationX(90).addModel().condition(DOWN, true);
    }

    static BlockState updatePipeOnPlace(BlockState state, Level level, BlockPos pos) {
        for (Direction face : DIRECTIONS) {
            var offset = level.getBlockState(pos.relative(face));
            if (!offset.is(zBlockTag.PIPE_CONNECTORS)) {
                state = state.setValue(PROPRTIES.get(DIRECTIONS.indexOf(face)), false);
            } else {
                // default is true
                level.setBlockAndUpdate(pos.relative(face),
                        offset.setValue(PROPRTIES.get(DIRECTIONS.indexOf(face.getOpposite())),
                                true));
            }
        }
        return state;
    }

}
