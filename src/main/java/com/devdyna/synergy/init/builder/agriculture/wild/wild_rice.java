package com.devdyna.synergy.init.builder.agriculture.wild;

import java.util.List;

import com.devdyna.synergy.Main;
import com.devdyna.synergy.api.plants.builder.BaseWildCropBlock;
import com.devdyna.synergy.init.types.zBlockTag;

import net.minecraft.network.chat.Component;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item.TooltipContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.MapColor;

@SuppressWarnings("null")
public class wild_rice extends BaseWildCropBlock {

    public wild_rice() {
        super(Properties.of().mapColor(MapColor.COLOR_LIGHT_GREEN));
    }

        @Override
    public TagKey<Block> getSpawnFilter() {
        return zBlockTag.CAN_SUSTAIN_RICE;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents,
            TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable(Main.ID +".disabled"));
    }

}
