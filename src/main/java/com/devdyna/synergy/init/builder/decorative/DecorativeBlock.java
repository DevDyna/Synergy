package com.devdyna.synergy.init.builder.decorative;

import java.util.List;

import com.devdyna.synergy.Main;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item.TooltipContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.Block;

@SuppressWarnings("null")
public class DecorativeBlock extends Block {

    public DecorativeBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack i, TooltipContext c, List<Component> t,
            TooltipFlag f) {
        t.add(Component.translatable(Main.ID + ".safe_building"));
    }

}
