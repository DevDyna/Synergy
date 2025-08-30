package com.devdyna.synergy.init.builder;

import java.util.List;

import com.devdyna.synergy.Main;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

@SuppressWarnings("null")
public class ItemComponents extends Item {

    public ItemComponents(Properties properties) {
        super(properties);
    }

    public ItemComponents() {
        super(new Item.Properties());
    }

    @Override
    public void appendHoverText(ItemStack i, TooltipContext c, List<Component> t,
            TooltipFlag f) {
        t.add(Component.translatable(Main.ID + ".crafting_ingredient"));
    }

}
