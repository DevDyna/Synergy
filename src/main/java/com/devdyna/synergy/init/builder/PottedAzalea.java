package com.devdyna.synergy.init.builder;

import java.util.List;

import com.devdyna.synergy.init.builder._core.crops.BaseCropBlock;
import com.devdyna.synergy.init.types.zItems;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
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
        super(Properties.of());
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE_BY_AGE[state.getValue(AGE)];
    }

    @Override
    public List<ItemStack> getItemResult(Level level, BlockState state, BlockPos pos, Player player, ItemStack tool) {

        // TODO convert removing pot
        return List.of(
                new ItemStack(zItems.AZALEA_SEEDS, level.random.nextInt(2)),
                new ItemStack(zItems.SMALL_AZALEA_LEAF, level.random.nextInt(3)+1),
                new ItemStack(zItems.SMALL_AZALEA_ROOTS, level.random.nextInt(1)+1));
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

}
