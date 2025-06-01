package com.devdyna.synergy.init.builder._core;

import com.devdyna.synergy.init.builder.pipeBlocks.nodes.NodeBlock;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.client.model.generators.MultiPartBlockStateBuilder;

public interface nodeType extends pipeType {

    static DirectionProperty FACING = DirectionProperty.create("facing");

    default void NodeStateDefinition(Builder<Block, BlockState> b) {
        PipeStateDefinition(b);
        b.add(FACING);
    }

    class VoxelShapes {
        public static VoxelShape DOWN = Shapes.box(0.0625, 0, 0.0625, 0.9375, 0.1875, 0.9375);
        public static VoxelShape UP = Shapes.box(0.0625, 0.8125, 0.0625, 0.9375, 1, 0.9375);
        public static VoxelShape NORTH = Shapes.box(0.0625, 0.0625, 0, 0.9375, 0.9375, 0.1875);
        public static VoxelShape SOUTH = Shapes.box(0.0625, 0.0625, 0.8125, 0.9375, 0.9375, 1);
        public static VoxelShape WEST = Shapes.box(0, 0.0625, 0.0625, 0.1875, 0.9375, 0.9375);
        public static VoxelShape EAST = Shapes.box(0.8125, 0.0625, 0.0625, 1, 0.9375, 0.9375);
    }

    default VoxelShape getNodeBaseShape(BlockState s) {
        VoxelShape model = getPipeBaseShape(s);

        model = switch (s.getValue(FACING)) {
            case Direction.DOWN -> Shapes.or(model, VoxelShapes.DOWN);
            case Direction.UP -> Shapes.or(model, VoxelShapes.UP);
            case Direction.NORTH -> Shapes.or(model, VoxelShapes.NORTH);
            case Direction.SOUTH -> Shapes.or(model, VoxelShapes.SOUTH);
            case Direction.WEST -> Shapes.or(model, VoxelShapes.WEST);
            case Direction.EAST -> Shapes.or(model, VoxelShapes.EAST);
        };

        return model.optimize();
    }

    static BlockState updateNodeOnPlace(BlockState s, Level level, BlockPos pos, Direction direction) {
        var state = pipeType.updatePipeOnPlace(s, level, pos);
        state = state.setValue(FACING, direction.getOpposite());
        return state;
    }

    static void getNodeMultiPart(Block b, MultiPartBlockStateBuilder model, ModelFile node, ModelFile pipe) {
        var pm = model.part().modelFile(pipe);
        var nm = model.part().modelFile(node);

        pm.addModel().condition(NORTH, true);
        pm.rotationY(90).addModel().condition(EAST, true);
        pm.rotationX(180).addModel().condition(SOUTH, true);
        pm.rotationY(270).addModel().condition(WEST, true);
        pm.rotationX(270).addModel().condition(UP, true);
        pm.rotationX(90).addModel().condition(DOWN, true);
        nm.addModel().condition(NodeBlock.FACING, Direction.NORTH);
        nm.rotationY(90).addModel().condition(NodeBlock.FACING, Direction.EAST);
        nm.rotationY(180).addModel().condition(NodeBlock.FACING, Direction.SOUTH);
        nm.rotationY(270).addModel().condition(NodeBlock.FACING, Direction.WEST);
        nm.rotationX(270).addModel().condition(NodeBlock.FACING, Direction.UP);
        nm.rotationX(90).addModel().condition(NodeBlock.FACING, Direction.DOWN);

    }

}
