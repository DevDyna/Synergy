package com.devdyna.synergy.init.builder._core;

import java.util.Arrays;
import java.util.List;
import com.devdyna.synergy.init.types.zBlockTag;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.client.model.generators.MultiPartBlockStateBuilder;

public interface pipeType {

    static EnumProperty<pipeProperties> NORTH = EnumProperty.create("north", pipeProperties.class);
    static EnumProperty<pipeProperties> SOUTH = EnumProperty.create("south", pipeProperties.class);
    static EnumProperty<pipeProperties> EAST = EnumProperty.create("east", pipeProperties.class);
    static EnumProperty<pipeProperties> WEST = EnumProperty.create("west", pipeProperties.class);
    static EnumProperty<pipeProperties> UP = EnumProperty.create("up", pipeProperties.class);
    static EnumProperty<pipeProperties> DOWN = EnumProperty.create("down", pipeProperties.class);

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

    public static List<EnumProperty<pipeProperties>> PROPRTIES = List.of(DOWN, UP, NORTH, SOUTH, WEST, EAST);
    public static List<Integer> X_ROT = List.of(-90, 90, 0, 0, 0, 0);
    public static List<Integer> Y_ROT = List.of(0, 0, 0, 180, -90, 90);

    public static List<Direction> DIRECTIONS = Arrays.stream(Direction.values()).toList();

    static void PipeStateDefinition(Builder<Block, BlockState> b) {
        PROPRTIES.forEach(e -> b.add(e));
    }

    static VoxelShape getPipeBaseShape(BlockState s) {
        VoxelShape model = BASE;
        if (s.getValue(DOWN) == pipeProperties.TRUE)
            model = Shapes.or(model, VoxelShapes.DOWN);
        if (s.getValue(UP) == pipeProperties.TRUE)
            model = Shapes.or(model, VoxelShapes.UP);
        if (s.getValue(SOUTH) == pipeProperties.TRUE)
            model = Shapes.or(model, VoxelShapes.SOUTH);
        if (s.getValue(NORTH) == pipeProperties.TRUE)
            model = Shapes.or(model, VoxelShapes.NORTH);
        if (s.getValue(EAST) == pipeProperties.TRUE)
            model = Shapes.or(model, VoxelShapes.EAST);
        if (s.getValue(WEST) == pipeProperties.TRUE)
            model = Shapes.or(model, VoxelShapes.WEST);
        return model.optimize();
    }

    static void getPipeMultiPart(Block b, MultiPartBlockStateBuilder model, ModelFile core, ModelFile pipe) {
        model.part().modelFile(core).addModel();
        model.part().modelFile(pipe).addModel().condition(NORTH, pipeProperties.TRUE, pipeProperties.NODE);
        model.part().modelFile(pipe).rotationY(90).addModel().condition(EAST, pipeProperties.TRUE, pipeProperties.NODE);
        model.part().modelFile(pipe).rotationX(180).addModel().condition(SOUTH, pipeProperties.TRUE,
                pipeProperties.NODE);
        model.part().modelFile(pipe).rotationY(270).addModel().condition(WEST, pipeProperties.TRUE,
                pipeProperties.NODE);
        model.part().modelFile(pipe).rotationX(270).addModel().condition(UP, pipeProperties.TRUE, pipeProperties.NODE);
        model.part().modelFile(pipe).rotationX(90).addModel().condition(DOWN, pipeProperties.TRUE, pipeProperties.NODE);
    }

    static BlockState updatePipeOnPlace(BlockState state, BlockPlaceContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        for (Direction face : DIRECTIONS) {
            var offset = level.getBlockState(pos.relative(face));
            if (offset.is(zBlockTag.PIPE_CONNECTORS)) {
                // default is true
                if (offset.getValue(PROPRTIES.get(DIRECTIONS.indexOf(face.getOpposite()))) != pipeProperties.NODE)
                    level.setBlockAndUpdate(pos.relative(face),
                            offset.setValue(PROPRTIES.get(DIRECTIONS.indexOf(face.getOpposite())),
                                    pipeProperties.TRUE));
            } else {
                if (state.getValue(PROPRTIES.get(DIRECTIONS.indexOf(face))) != pipeProperties.NODE)
                    state = state.setValue(PROPRTIES.get(DIRECTIONS.indexOf(face)), pipeProperties.FALSE);
            }
        }
        return state;
    }

    static void onDestroyPipe(BlockState state, Level level, BlockPos pos) {
        for (Direction face : DIRECTIONS) {
            var offset = level.getBlockState(pos.relative(face));
            if (offset.is(zBlockTag.PIPE_CONNECTORS)
                    && offset.getValue(PROPRTIES.get(DIRECTIONS.indexOf(face.getOpposite()))) != pipeProperties.NODE) {
                level.setBlock(pos.relative(face),
                        offset.setValue(PROPRTIES.get(DIRECTIONS.indexOf(face.getOpposite())),
                                pipeProperties.FALSE),
                        Block.UPDATE_ALL);
            }
        }
    }

}
