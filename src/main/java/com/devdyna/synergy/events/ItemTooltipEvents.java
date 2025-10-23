package com.devdyna.synergy.events;

import com.devdyna.synergy.Main;
import com.devdyna.synergy.init.types.zItemTag;

import net.minecraft.network.chat.Component;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;

public class ItemTooltipEvents {
    @SubscribeEvent
    public static void itemTooltipEvent(ItemTooltipEvent event) {

        var item = event.getItemStack();
        var tooltip = event.getToolTip();

        if (item.is(zItemTag.PLACEABLE)) {
            tooltip.add(Component.translatable(Main.ID + ".placed"));
        }
    }
}
