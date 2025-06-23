package com.devdyna.synergy.init.builder._core.crops;

import com.devdyna.synergy.utils.LevelUtil;
import com.devdyna.synergy.utils.LogUtil;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.common.CommonHooks;

@SuppressWarnings("null")
public class BaseCropMushroom extends BaseShortCropBlock {

    protected BaseCropMushroom(Properties properties) {
        super(properties.sound(SoundType.GRASS));
        this.registerDefaultState(
                stateDefinition.any()
                        .setValue(getAgeProperty(), 0)
                        .setValue(FERTILE, true));

    }

    public static final BooleanProperty FERTILE = BooleanProperty.create("fertile");

    private static int posFail = 0;

    VoxelShape[] SHAPE_BY_AGE = { Block.box(5.0, 0.0, 5.0, 11.0, 5.0, 11.0), Block.box(5.0, 0.0, 5.0, 11.0, 7.0, 11.0),
            Block.box(5.0, 0.0, 5.0, 11.0, 9.0, 11.0), Block.box(5.0, 0.0, 5.0, 11.0, 11.0, 11.0),
            Block.box(4.0, 0.0, 4.0, 12.0, 13.0, 12.0), Block.box(3.0, 0.0, 3.0, 13.0, 14.0, 13.0) };

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return this.SHAPE_BY_AGE[getAge(state)];
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE, FERTILE);
    }

    @Override
    protected boolean isRandomlyTicking(BlockState state) {
        return state.getValue(FERTILE) && !isMaxAge(state);
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return state.isSolidRender(level, pos);
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        return level.getBlockState(pos.below()).is(BlockTags.MUSHROOM_GROW_BLOCK) ? true
                : (level.getBlockState(pos.below()).canSustainPlant(level, pos.below(), Direction.UP, state).isDefault()
                        ? level.getRawBrightness(pos, 0) < maxBrightnessSustainable()
                                && mayPlaceOn(level.getBlockState(pos.below()), level, pos.below())
                        : level.getBlockState(pos.below()).canSustainPlant(level, pos.below(), Direction.UP, state)
                                .isTrue());
    }

    public void spreadSpores(BlockPos pos, Level level, BlockState state) {
        var valid = getSpreadPos(pos, level);
        if (valid != null)
            level.setBlockAndUpdate(valid, state.setValue(AGE, 0));
    }

    public BlockPos getSpreadPos(BlockPos pos, Level level) {
        // TODO mycelium and other condition placement
        if (pos == null)
            return null;
        var spots = BlockPos.randomBetweenClosed(level.random, 1,
                pos.getX() - 3, pos.getY() - 3, pos.getZ() - 3,
                pos.getX() + 3, pos.getY() + 3, pos.getZ() + 3);

        if (spots != null)
            for (BlockPos offpos : spots) {
                if (level.getBlockState(offpos).is(this))
                    posFail++;

                if (level.getBlockState(offpos).is(BlockTags.AIR)
                        && this.canSurvive(level.getBlockState(offpos), level, pos)
                        && this.mayPlaceOn(level.getBlockState(offpos.below()), level, pos.below())
                        && level.getRawBrightness(offpos, 0) < maxBrightnessSustainable())
                    return offpos;
            }
        return null;
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {

        if (!isMaxAge(state))
            if (CommonHooks.canCropGrow(level, pos, state, random.nextInt(13) == 0)) {
                level.setBlock(pos, getStateForAge(getAge(state) + 1), 2);
                CommonHooks.fireCropGrowPost(level, pos, state);
            }

        if (posFail >= 9) {
            state.setValue(FERTILE, false);
            return;
        }

        if (getAge(state) >= 3 && state.getValue(FERTILE)) {
            if (LevelUtil.chance(25, level))
                spreadSpores(pos, level, state);
        }

    }

    public int maxBrightnessSustainable() {
        return 13;
    }

}
