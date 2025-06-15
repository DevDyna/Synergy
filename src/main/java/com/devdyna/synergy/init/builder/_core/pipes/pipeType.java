package com.devdyna.synergy.init.builder._core.pipes;

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
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.client.model.generators.MultiPartBlockStateBuilder;

@SuppressWarnings("null")
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
        if (s.getValue(DOWN) == pipeProperties.TRUE || s.getValue(DOWN) == pipeProperties.OUTPUT)
            model = Shapes.or(model, VoxelShapes.DOWN);
        if (s.getValue(UP) == pipeProperties.TRUE || s.getValue(UP) == pipeProperties.OUTPUT)
            model = Shapes.or(model, VoxelShapes.UP);
        if (s.getValue(SOUTH) == pipeProperties.TRUE || s.getValue(SOUTH) == pipeProperties.OUTPUT)
            model = Shapes.or(model, VoxelShapes.SOUTH);
        if (s.getValue(NORTH) == pipeProperties.TRUE || s.getValue(NORTH) == pipeProperties.OUTPUT)
            model = Shapes.or(model, VoxelShapes.NORTH);
        if (s.getValue(EAST) == pipeProperties.TRUE || s.getValue(EAST) == pipeProperties.OUTPUT)
            model = Shapes.or(model, VoxelShapes.EAST);
        if (s.getValue(WEST) == pipeProperties.TRUE || s.getValue(WEST) == pipeProperties.OUTPUT)
            model = Shapes.or(model, VoxelShapes.WEST);
        return model.optimize();
    }

    // TODO need to define differents model parts
    static pipeProperties[] allTrue = { pipeProperties.TRUE, pipeProperties.NODE, pipeProperties.OUTPUT };

    static void getPipeMultiPart(Block b, MultiPartBlockStateBuilder model, ModelFile core, ModelFile pipe) {
        model.part().modelFile(core).addModel();
        model.part().modelFile(pipe).addModel().condition(NORTH, allTrue);
        model.part().modelFile(pipe).rotationY(90).addModel().condition(EAST, allTrue);
        model.part().modelFile(pipe).rotationX(180).addModel().condition(SOUTH, allTrue);
        model.part().modelFile(pipe).rotationY(270).addModel().condition(WEST, allTrue);
        model.part().modelFile(pipe).rotationX(270).addModel().condition(UP, allTrue);
        model.part().modelFile(pipe).rotationX(90).addModel().condition(DOWN, allTrue);
    }

    @Deprecated
    static BlockState updatePipeOnPlace(BlockState state, BlockPlaceContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        for (Direction face : DIRECTIONS) {

            var offset = level.getBlockState(pos.relative(face));
            if (offset.is(zBlockTag.PIPE_CONNECTORS)) {
                // TIP. default is true

                // connect to another pipe connector
                if (offset.getValue(PROPRTIES.get(DIRECTIONS.indexOf(face.getOpposite()))) != pipeProperties.NODE)
                    level.setBlockAndUpdate(pos.relative(face),
                            offset = offset.setValue(PROPRTIES.get(DIRECTIONS.indexOf(face.getOpposite())),
                                    pipeProperties.TRUE));
            } else {

                // connect to BE itemhandler
                if (level.getBlockEntity(pos.relative(face)) != null
                        && Capabilities.ItemHandler.BLOCK.getCapability(level, pos.relative(face),
                                level.getBlockState(pos.relative(face)),
                                level.getBlockEntity(pos.relative(face)), face.getOpposite()) != null) {
                    state = state.setValue(PROPRTIES.get(DIRECTIONS.indexOf(face)), pipeProperties.OUTPUT);
                } else {
                    // remove connection

                    state = state.setValue(PROPRTIES.get(DIRECTIONS.indexOf(face)), pipeProperties.FALSE);
                }
            }
        }
        return state;
    }


        static BlockState updatePipeOnPlace(BlockState state, Level level,BlockPos pos) {
        for (Direction face : DIRECTIONS) {

            var offset = level.getBlockState(pos.relative(face));
            if (offset.is(zBlockTag.PIPE_CONNECTORS)) {
                // TIP. default is true

                // connect to another pipe connector
                if (offset.getValue(PROPRTIES.get(DIRECTIONS.indexOf(face.getOpposite()))) != pipeProperties.NODE)
                    level.setBlockAndUpdate(pos.relative(face),
                            offset = offset.setValue(PROPRTIES.get(DIRECTIONS.indexOf(face.getOpposite())),
                                    pipeProperties.TRUE));
            } else {

                // connect to BE itemhandler
                if (level.getBlockEntity(pos.relative(face)) != null
                        && Capabilities.ItemHandler.BLOCK.getCapability(level, pos.relative(face),
                                level.getBlockState(pos.relative(face)),
                                level.getBlockEntity(pos.relative(face)), face.getOpposite()) != null) {
                    state = state.setValue(PROPRTIES.get(DIRECTIONS.indexOf(face)), pipeProperties.OUTPUT);
                } else {
                    // remove connection

                    state = state.setValue(PROPRTIES.get(DIRECTIONS.indexOf(face)), pipeProperties.FALSE);
                }
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

    static Direction P2D(EnumProperty<pipeProperties> e) {
        return DIRECTIONS.get(PROPRTIES.indexOf(e));
    }

    static EnumProperty<pipeProperties> D2P(Direction d) {
        return PROPRTIES.get(DIRECTIONS.indexOf(d));
    }

}
