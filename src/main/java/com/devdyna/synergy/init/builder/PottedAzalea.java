package com.devdyna.synergy.init.builder;

import com.devdyna.synergy.utils.LevelUtil;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

@SuppressWarnings("null")
public class PottedAzalea extends Block {

    public static final IntegerProperty AGE = BlockStateProperties.AGE_7;

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

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE_BY_AGE[state.getValue(AGE)];
    }

    public PottedAzalea() {
        super(BlockBehaviour.Properties.of().randomTicks().sound(SoundType.FLOWERING_AZALEA)
                .instabreak().noOcclusion()
                .pushReaction(PushReaction.DESTROY));
        this.registerDefaultState(stateDefinition.any()
                .setValue(AGE, 0));
    }

    public BlockState getStateForAge(int age) {
        return this.defaultBlockState().setValue(AGE, Integer.valueOf(age));
    }

    protected boolean isRandomlyTicking(BlockState state) {
        return state.getValue(AGE) < 7;
    }

    protected void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        int stage = state.getValue(AGE);
        if (stage < 7) {
            world.setBlock(pos, getStateForAge(stage + 1), 2);
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(AGE);
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack i, BlockState b, Level l, BlockPos p,
            Player e, InteractionHand h, BlockHitResult r) {
        if (!l.isClientSide)
            if (b.getValue(AGE) >= 6) {
                l.setBlockAndUpdate(p, b.setValue(AGE, LevelUtil.getRandomValue(4, l)));
                var items = LevelUtil.getItemStackFromLootTable(l, b);
                items.forEach(s -> {
                    if (!s.is(Blocks.FLOWER_POT.asItem()))
                        LevelUtil.popItemFromPos(l, p.above(), s);
                });
                return ItemInteractionResult.SUCCESS;
            }

        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

}
