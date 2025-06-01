package com.devdyna.synergy.init.builder._core;

import java.util.List;

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

    VoxelShape Y_NODE_PART = Block.box(1, 0, 1, 15, 3, 15); // u-d
    VoxelShape Z_NODE_PART = Block.box(1, 1, 0, 15, 15, 3); // n-s
    VoxelShape X_NODE_PART = Block.box(0, 1, 1, 3, 15, 15); // e-w

    default VoxelShape getNodeBaseShape(BlockState s) {
        VoxelShape model = getPipeBaseShape(s);
        
        model = switch (s.getValue(FACING)) {
            case Direction.DOWN -> Shapes.or(model, Y_NODE_PART.move(0, (10 / 16), 0));
            case Direction.UP -> Shapes.or(model, Y_NODE_PART);
            case Direction.NORTH -> Shapes.or(model, Z_NODE_PART.move(0, 0, (10 / 16)));
            case Direction.SOUTH -> Shapes.or(model, Z_NODE_PART);
            case Direction.WEST -> Shapes.or(model, X_NODE_PART.move((10 / 16), 0, 0));
            case Direction.EAST -> Shapes.or(model, X_NODE_PART);
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
        nm.rotationX(180).addModel().condition(NodeBlock.FACING, Direction.SOUTH);
        nm.rotationY(270).addModel().condition(NodeBlock.FACING, Direction.WEST);
        nm.rotationX(270).addModel().condition(NodeBlock.FACING, Direction.UP);
        nm.rotationX(90).addModel().condition(NodeBlock.FACING, Direction.DOWN);

    }

}
