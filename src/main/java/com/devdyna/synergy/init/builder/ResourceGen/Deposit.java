package com.devdyna.synergy.init.builder.ResourceGen;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class Deposit extends Block {

    public static String STONE = "stone";
    public static String IRON = "iron";
    public static String COPPER = "copper";
    public static String GOLD = "gold";
    public static String SAND = "sand";
    public static String URANIUM = "uranium";
    public static String PLATINUM = "platinum";


    public Deposit() {
        super(BlockBehaviour.Properties.of().sound(SoundType.ROOTED_DIRT));
    }

    @SuppressWarnings("null")
    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return Block.box(4.0, 0.0, 4.0, 9.0, 4.0, 9.0);
    }

}
