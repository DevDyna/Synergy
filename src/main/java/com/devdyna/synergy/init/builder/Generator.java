package com.devdyna.synergy.init.builder;

import com.devdyna.synergy.init.types.zBlockTag;
import com.devdyna.synergy.utils.LevelUtil;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

@SuppressWarnings("null")
public class Generator extends Block {

    private TagKey<Block> block_result;
    private TagKey<Item> item_result;
    private int radius;

    public Generator(int radius, TagKey<Block> block_result, TagKey<Item> item_result) {
        super(BlockBehaviour.Properties.of().randomTicks().sound(SoundType.ANCIENT_DEBRIS)
                .destroyTime(Block.INDESTRUCTIBLE));
        this.radius = radius <= 1 ? 1 : radius;
        this.block_result = block_result;
        this.item_result = item_result;
    }

    protected boolean isRandomlyTicking(BlockState state) {
        return true;
    }

    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource randomSource) {

        if (level.getBlockState(pos.above()).is(zBlockTag.EXTRACTORS)) {
            for (int i = 0; i < ((radius * 2 + 1) ^ 2) - 1; i++)
                LevelUtil.popItemFromPos(level, pos.above(),
                        new ItemStack(LevelUtil.ItemByTag(item_result,
                                randomSource.nextInt(LevelUtil.getSizeItemTag(item_result)))));
        } else
            BlockPos.randomBetweenClosed(randomSource, ((radius * 2 + 1) ^ 2) - 1,
                    pos.getX() - radius, pos.getY() + 1, pos.getZ() - radius,
                    pos.getX() + radius, pos.getY() + 1, pos.getZ() + radius)
                    .forEach(ps -> {
                        if (ps != pos.above() && level.getBlockState(ps).isEmpty()
                                && !level.getBlockState(ps.below()).isEmpty()) {
                            level.setBlockAndUpdate(ps, LevelUtil.BlockByTag(block_result,
                                    randomSource.nextInt(LevelUtil.getSizeBlockTag(block_result))).defaultBlockState());
                        }

                    });

    }

}
