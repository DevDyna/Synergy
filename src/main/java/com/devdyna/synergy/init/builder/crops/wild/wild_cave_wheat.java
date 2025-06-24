package com.devdyna.synergy.init.builder.crops.wild;

import java.util.List;

import com.devdyna.synergy.Main;
import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.api.plants.builder.BaseWildCropBlock;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Item.TooltipContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;

@SuppressWarnings("null")
public class wild_cave_wheat extends BaseWildCropBlock {

    public wild_cave_wheat() {
        super(Properties.of());
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        return level.getBlockState(pos.below()).is(BlockTags.BASE_STONE_OVERWORLD)
                || level.getBlockState(pos.below()).is(BlockTags.LUSH_GROUND_REPLACEABLE);
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return (level.getBlockState(pos.below()).is(BlockTags.BASE_STONE_OVERWORLD)
                || level.getBlockState(pos.below()).is(BlockTags.LUSH_GROUND_REPLACEABLE)) ? true
                        : super.mayPlaceOn(state, level, pos);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents,
            TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable(Main.ID + "." + zStatic.Wild.WILD + ".tip"));
    }

}
