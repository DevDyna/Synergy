package com.devdyna.synergy.common.events;

import static com.devdyna.synergy.Main.ID;

import java.util.*;

import com.devdyna.synergy.Main;
import com.devdyna.synergy.api.BlockAbilities.tooltips.base.ComplexTooltips;
import com.devdyna.synergy.api.BlockAbilities.tooltips.base.MultiSimpleTips;
import com.devdyna.synergy.api.BlockAbilities.tooltips.base.SimpleToolTip;
import com.devdyna.synergy.init.types.zItemTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;

public class ItemTooltipEvents {

    private static final int BELOW_THE_REGISTRY_ID = 1;

    @SubscribeEvent
    public static void tooltipEvent(ItemTooltipEvent e) {
        var item = e.getItemStack();
        var tooltip = e.getToolTip();
        var flags = e.getFlags();

        isBlock(item, tooltip, flags);
        itemPlaceable(item, tooltip);
        laserColorApplicator(item, tooltip);
        noGrowingItems(item, tooltip);
        upgrades(item, tooltip);

    }

    public static void isBlock(ItemStack item, List<Component> tooltip, TooltipFlag flags) {
        if (item.getItem() instanceof BlockItem blockItem) {
            simpleToolTip(blockItem, tooltip);
            advancedToolTip(blockItem, tooltip, flags);
        }
    }

    public static void itemPlaceable(ItemStack item, List<Component> tooltip) {

        if (item.is(zItemTag.PLACEABLE)) {
            tooltip.add(BELOW_THE_REGISTRY_ID, Component.translatable(Main.ID + ".placed"));
        }
    }

    public static void laserColorApplicator(ItemStack item, List<Component> tooltip) {

        if (item.is(zItemTag.DYE_RESET)) {
            tooltip.add(BELOW_THE_REGISTRY_ID, Component.translatable(Main.ID + ".laser_use.reset"));
        }
        if (item.is(zItemTag.DYE_MAX)) {
            tooltip.add(BELOW_THE_REGISTRY_ID, Component.translatable(Main.ID + ".laser_use.max"));
        }
        if (item.is(zItemTag.DYE_RED)) {
            tooltip.add(BELOW_THE_REGISTRY_ID, Component.translatable(Main.ID + ".laser_use.red"));
        }
        if (item.is(zItemTag.DYE_GREEN)) {
            tooltip.add(BELOW_THE_REGISTRY_ID, Component.translatable(Main.ID + ".laser_use.green"));
        }
        if (item.is(zItemTag.DYE_BLUE)) {
            tooltip.add(BELOW_THE_REGISTRY_ID, Component.translatable(Main.ID + ".laser_use.blue"));
        }
    }

    public static void noGrowingItems(ItemStack item, List<Component> tooltip) {

        if (item.is(zItemTag.REMOVE_ENTITY_GROWING)) {
            tooltip.add(BELOW_THE_REGISTRY_ID, Component.translatable(Main.ID + ".remove_entity_growing"));
        }
        if (item.is(zItemTag.ADD_ENTITY_GROWING)) {
            tooltip.add(BELOW_THE_REGISTRY_ID, Component.translatable(Main.ID + ".add_entity_growing"));
        }

    }

    /**
     * need to move on a dedicated interface
     */
    @Deprecated
    public static void upgrades(ItemStack item, List<Component> tooltip) {

        if (item.is(zItemTag.UPGRADE_ENERGY)) {
            tooltip.add(BELOW_THE_REGISTRY_ID, Component.translatable(Main.ID + ".upgrade.energy"));
        }
        if (item.is(zItemTag.UPGRADE_SPEED)) {
            tooltip.add(BELOW_THE_REGISTRY_ID, Component.translatable(Main.ID + ".upgrade.speed.energy"));
            tooltip.add(BELOW_THE_REGISTRY_ID, Component.translatable(Main.ID + ".upgrade.speed.speed"));
        }

    }

    public static void simpleToolTip(BlockItem blockItem, List<Component> tooltip) {
        if (blockItem.getBlock() instanceof SimpleToolTip sm) {
            tooltip.add(BELOW_THE_REGISTRY_ID, Component.translatable(ID + "." + sm.key()));
        }
    }

    public static void multiSimpleToolTip(BlockItem blockItem, List<Component> tooltip) {
        if (blockItem.getBlock() instanceof MultiSimpleTips msm) {
            for (String s : msm.keys()) {
                tooltip.add(BELOW_THE_REGISTRY_ID, Component.translatable(ID + "." + s));
            }
        }
    }

    public static void advancedToolTip(BlockItem blockItem, List<Component> tooltip, TooltipFlag flags) {
        if (blockItem.getBlock() instanceof ComplexTooltips adv) {
            adv.renderTip(tooltip, flags);
        }
    }

}
