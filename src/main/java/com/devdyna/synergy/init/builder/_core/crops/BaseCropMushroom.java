package com.devdyna.synergy.init.builder._core.crops;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.common.CommonHooks;

@SuppressWarnings("null")
public class BaseCropMushroom extends BaseShortCropBlock {

    protected BaseCropMushroom(Properties properties) {
        super(properties);
    }

    VoxelShape[] SHAPE_BY_AGE = { Block.box(5.0, 0.0, 5.0, 11.0, 2.0, 11.0), Block.box(5.0, 0.0, 5.0, 11.0, 4.0, 11.0),
            Block.box(5.0, 0.0, 5.0, 11.0, 6.0, 11.0), Block.box(5.0, 0.0, 5.0, 11.0, 8.0, 11.0),
            Block.box(5.0, 0.0, 5.0, 11.0, 10.0, 11.0), Block.box(5.0, 0.0, 5.0, 11.0, 12.0, 11.0) };

    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return this.SHAPE_BY_AGE[getAge(state)];
    }

    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (!level.isAreaLoaded(pos, 1))
            return;

        if (getAge(state) == getMaxAge()) {
            for (int i = 0; i < Direction.values().length; i++) {
                var offset = pos.relative(Direction.values()[i]);

                if (Direction.values()[i] == Direction.UP)
                    offset = offset.relative(Direction.DOWN)
                            .relative(Direction.values()[random.nextInt(Direction.values().length)]);

                if (Direction.values()[i] == Direction.DOWN)
                    offset = offset.relative(Direction.UP)
                            .relative(Direction.values()[random.nextInt(Direction.values().length)]);

                if (level.getBlockState(offset).is(BlockTags.AIR)) {
                    level.setBlockAndUpdate(offset, state.setValue(AGE, 0));
                }
            }
        } else {

            if (getAge(state) < getMaxAge()) {
                if (CommonHooks.canCropGrow(level, pos, state, random.nextInt(13) == 0)) {
                    level.setBlock(pos, getStateForAge(getAge(state) + 1), 2);
                    CommonHooks.fireCropGrowPost(level, pos, state);
                }
            }

        }

    }

    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return state.isSolidRender(level, pos);
    }

    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        return !level.getBlockState(pos.below()).isAir() && mayPlaceOn(state, level, pos);
    }

}
