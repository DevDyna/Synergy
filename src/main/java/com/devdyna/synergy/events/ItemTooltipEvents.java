package com.devdyna.synergy.events;

import com.devdyna.synergy.Main;
import com.devdyna.synergy.init.types.zItemTag;

import net.minecraft.network.chat.Component;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;

public class ItemTooltipEvents {
    
    @SubscribeEvent
    public static void itemTooltipPlaceable(ItemTooltipEvent event) {

        var item = event.getItemStack();
        var tooltip = event.getToolTip();

        if (item.is(zItemTag.PLACEABLE)) {
            tooltip.add(Component.translatable(Main.ID + ".placed"));
        }
    }

    @SubscribeEvent
    public static void itemTooltipLaserColorApplicator(ItemTooltipEvent event) {

        var item = event.getItemStack();
        var tooltip = event.getToolTip();

        if (item.is(zItemTag.DYE_RESET)) {
            tooltip.add(Component.translatable(Main.ID + ".laser_use.reset"));
        }
        if (item.is(zItemTag.DYE_RED)) {
            tooltip.add(Component.translatable(Main.ID + ".laser_use.red"));
        }
        if (item.is(zItemTag.DYE_GREEN)) {
            tooltip.add(Component.translatable(Main.ID + ".laser_use.green"));
        }
        if (item.is(zItemTag.DYE_BLUE)) {
            tooltip.add(Component.translatable(Main.ID + ".laser_use.blue"));
        }
    }
}
