package com.devdyna.synergy.init.builder.survival.heater.metallic;

import javax.annotation.Nullable;

import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.api.blockfactories.heater.SolidFuelHeaterBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

@SuppressWarnings("null")
public class MetallicHeaterBlock extends SolidFuelHeaterBlock {

    public MetallicHeaterBlock(Properties properties) {
        super(properties);
    }

    public MetallicHeaterBlock() {
        this(Properties.of()
                .sound(SoundType.METAL)
                .mapColor(MapColor.METAL)
                .instrument(NoteBlockInstrument.BASS)
                .strength(1.5F, 2.75F)
                .noOcclusion());
    }

    public VoxelShape shape() {
        VoxelShape shape = Shapes.empty();
        shape = Shapes.join(shape, Shapes.box(0, 0.75, 0, 1, 1, 1), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0, 0, 0, 0.125, 0.75, 0.125), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0, 0, 0.875, 0.125, 0.75, 1), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.875, 0, 0, 1, 0.75, 0.125), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.875, 0, 0.875, 1, 0.75, 1), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.0625, 0, 0.0625, 0.9375, 0.75, 0.9375), BooleanOp.OR);

        return shape;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return shape().optimize();
    }

    @Override
    @Nullable
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new MetallicHeaterBE(pos, state);
    }

    public String key() {
        return zStatic.Blocks.metallic_heater;
    }

}
