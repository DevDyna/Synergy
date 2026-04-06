package com.devdyna.synergy.init.builder.survival.heater.bricked;

import javax.annotation.Nullable;

import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.api.blockfactories.heater.SolidFuelHeaterBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

@SuppressWarnings("null")
public class BrickedHeaterBlock extends SolidFuelHeaterBlock {

    public BrickedHeaterBlock(Properties properties) {
        super(properties);
    }

    public BrickedHeaterBlock() {
        this(Properties.of()
                .sound(SoundType.TUFF_BRICKS)
                .mapColor(MapColor.TERRACOTTA_BROWN)
                .instrument(NoteBlockInstrument.BASS)
                .strength(1F, 2.25F)
                .noOcclusion());
    }

    public VoxelShape north() {
        VoxelShape shape = Shapes.empty();
        shape = Shapes.join(shape, Shapes.box(0.0625, 0, 0.0625, 0.9375, 0.75, 0.9375), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.0625, 0, 0.9375, 0.9375, 0.75, 1), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.9375, 0, 0.25, 1, 0.3125, 1), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.9375, 0.3125, 0.5, 1, 0.5625, 1), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.9375, 0.5625, 0.75, 1, 0.75, 1), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0, 0, 0.25, 0.0625, 0.3125, 1), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0, 0.3125, 0.5, 0.0625, 0.5625, 1), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0, 0.5625, 0.75, 0.0625, 0.75, 1), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0, 0.75, 0, 1, 1, 1), BooleanOp.OR);
        return shape;
    }

    public VoxelShape east() {
        VoxelShape shape = Shapes.empty();
        shape = Shapes.join(shape, Shapes.box(0.0625, 0, 0.0625, 0.9375, 0.75, 0.9375), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0, 0, 0.0625, 0.0625, 0.75, 0.9375), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0, 0, 0.9375, 0.75, 0.3125, 1), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0, 0.3125, 0.9375, 0.5, 0.5625, 1), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0, 0.5625, 0.9375, 0.25, 0.75, 1), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0, 0, 0, 0.75, 0.3125, 0.0625), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0, 0.3125, 0, 0.5, 0.5625, 0.0625), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0, 0.5625, 0, 0.25, 0.75, 0.0625), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0, 0.75, 0, 1, 1, 1), BooleanOp.OR);

        return shape;
    }

    public VoxelShape south() {
        VoxelShape shape = Shapes.empty();
        shape = Shapes.join(shape, Shapes.box(0.0625, 0, 0.0625, 0.9375, 0.75, 0.9375), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.0625, 0, 0, 0.9375, 0.75, 0.0625), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0, 0, 0, 0.0625, 0.3125, 0.75), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0, 0.3125, 0, 0.0625, 0.5625, 0.5), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0, 0.5625, 0, 0.0625, 0.75, 0.25), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.9375, 0, 0, 1, 0.3125, 0.75), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.9375, 0.3125, 0, 1, 0.5625, 0.5), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.9375, 0.5625, 0, 1, 0.75, 0.25), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0, 0.75, 0, 1, 1, 1), BooleanOp.OR);

        return shape;
    }

    public VoxelShape west() {
        VoxelShape shape = Shapes.empty();
        shape = Shapes.join(shape, Shapes.box(0.0625, 0, 0.0625, 0.9375, 0.75, 0.9375), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.9375, 0, 0.0625, 1, 0.75, 0.9375), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.25, 0, 0, 1, 0.3125, 0.0625), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.5, 0.3125, 0, 1, 0.5625, 0.0625), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.75, 0.5625, 0, 1, 0.75, 0.0625), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.25, 0, 0.9375, 1, 0.3125, 1), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.5, 0.3125, 0.9375, 1, 0.5625, 1), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.75, 0.5625, 0.9375, 1, 0.75, 1), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0, 0.75, 0, 1, 1, 1), BooleanOp.OR);

        return shape;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return shape(state).optimize();
    }

    public VoxelShape shape(BlockState s) {
        return switch (s.getValue(BlockStateProperties.HORIZONTAL_FACING)) {
            case NORTH -> north();
            case SOUTH -> south();
            case EAST -> east();
            case WEST -> west();
            default -> Shapes.empty();
        };
    }

    @Override
    @Nullable
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new BrickedHeaterBE(pos, state);
    }

    public String key() {
        return zStatic.Blocks.bricked_heater;
    }

}
