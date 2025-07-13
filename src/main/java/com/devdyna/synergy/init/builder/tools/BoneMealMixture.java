package com.devdyna.synergy.init.builder.tools;

import java.util.List;

import com.devdyna.synergy.Main;
import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.utils.LevelUtil;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.BoneMealItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.CactusBlock;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.NetherWartBlock;
import net.minecraft.world.level.block.StemBlock;
import net.minecraft.world.level.block.SugarCaneBlock;
import net.minecraft.world.level.block.VineBlock;
import net.neoforged.neoforge.common.CommonHooks;

public class BoneMealMixture extends BoneMealItem {

    public BoneMealMixture() {
        super(new Properties());
    }

    @Override
    public InteractionResult useOn(UseOnContext c) {
        var level = c.getLevel();
        var pos = c.getClickedPos();
        var state = level.getBlockState(pos);
        var random = level.random;
        var item = c.getItemInHand();
        var block = state.getBlock();
        var x = pos.getX();
        var y = pos.getY();
        var z = pos.getZ();

        if (block instanceof NetherWartBlock
                || ((block instanceof SugarCaneBlock || block instanceof CactusBlock)
                        && level.getBlockState(pos.above()).isAir()
                        && !level.getBlockState(pos.below().below()).is(block))
                || block instanceof VineBlock || (block instanceof StemBlock && state.getValue(StemBlock.AGE) == 7))

            if (state.isRandomlyTicking()) {
                item.shrink(1);

                if (!level.isClientSide) {
                    LevelUtil.addParticle((ServerLevel) level, pos, ParticleTypes.HAPPY_VILLAGER, true,
                            LevelUtil.getRandomValue(7, level) + 5);

                    for (int i = 0; i < 10; i++)
                        if (state.isRandomlyTicking())//this should prevent over-ticking
                            state.randomTick((ServerLevel) level, pos, random);

                }

                CommonHooks.fireCropGrowPost(level, pos, state);
                return InteractionResult.SUCCESS;
            }

        if (block instanceof FlowerBlock) {
            if (!level.isClientSide) {
                LevelUtil.addParticle((ServerLevel) level, pos, ParticleTypes.HAPPY_VILLAGER, true,
                        LevelUtil.getRandomValue(7, level) + 3);
                BlockPos.randomBetweenClosed(random, LevelUtil.getRandomValue(12, level),
                        x - GRASS_SPREAD_WIDTH, y, z - GRASS_SPREAD_WIDTH,
                        x + GRASS_SPREAD_WIDTH, y + GRASS_SPREAD_HEIGHT, z + GRASS_SPREAD_WIDTH).forEach(ps -> {
                            if ((level.getBlockState(ps.below()).is(BlockTags.DIRT)
                                    || level.getBlockState(ps.below()).getBlock() instanceof FarmBlock)
                                    && level.getBlockState(ps).canBeReplaced()) {
                                level.setBlockAndUpdate(ps, state);
                            }
                        });
            }
            return InteractionResult.SUCCESS;
        }

        return super.useOn(c);

    }

    @Override
    public void appendHoverText(ItemStack i, TooltipContext c, List<Component> t,
            TooltipFlag f) {
        t.add(Component.translatable(Main.ID + "." + zStatic.Items.bone_meal_mixture));
    }

}
