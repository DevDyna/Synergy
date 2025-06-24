package com.devdyna.synergy.init.builder.tools;

import java.util.List;

import com.devdyna.synergy.Main;
import com.devdyna.synergy.zStatic;

import net.minecraft.network.chat.Component;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.Block;

@SuppressWarnings("null")
public class Croock extends DiggerItem {

    public Croock(TagKey<Block> tag, Tier tier) {
        super(tier, tag,
                new Item.Properties()
                        .attributes(HoeItem.createAttributes(tier, -3.0F, 0.0F)));
    }

    // TODO breaking multiplier event like
    // https://github.com/DevDyna/Ex-Alchemia/blob/main/src/main/java/com/devdyna/alchemia/events/block/broken.java#L21

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents,
            TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable(Main.ID + "." + zStatic.Items.wooden_crook));
    }
}
