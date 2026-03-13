package com.devdyna.synergy.init.builder.plants.cultivated;

import java.util.List;
import java.util.Random;

import com.devdyna.synergy.api.plants.builder.BaseCropBlock;
import com.devdyna.synergy.init.types.zItems;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.storage.loot.LootParams.Builder;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

@SuppressWarnings("null")
public class PottedAzalea extends BaseCropBlock {

    protected static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[] {
            Block.box(5.0, 0.0, 5.0, 11.0, 12.0, 11.0),
            Block.box(5.0, 0.0, 5.0, 11.0, 12.0, 11.0),
            Block.box(5.0, 0.0, 5.0, 11.0, 13.0, 11.0),
            Block.box(4.5, 0.0, 4.5, 11.5, 15.0, 11.5),
            Block.box(4.0, 0.0, 4.0, 12.0, 16.0, 12.0),
            Block.box(4.0, 0.0, 4.0, 12.0, 16.0, 12.0),
            Block.box(4.0, 0.0, 4.0, 12.0, 16.0, 12.0),
            Block.box(4.0, 0.0, 4.0, 12.0, 16.0, 12.0),

    };

    public PottedAzalea() {
        super(Properties.of().mapColor(MapColor.COLOR_GREEN));
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE_BY_AGE[state.getValue(AGE)];
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return zItems.AZALEA_SEEDS.get();
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        return true;
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return false;
    }

    @Override
    protected List<ItemStack> getDrops(BlockState arg0, Builder arg1) {
        return List.of(
                new ItemStack(zItems.AZALEA_SEEDS, new Random().nextInt(2)),
                new ItemStack(zItems.SMALL_AZALEA_LEAF, new Random().nextInt(3) + 1),
                new ItemStack(zItems.SMALL_AZALEA_ROOTS, new Random().nextInt(1) + 1));
    }
}
