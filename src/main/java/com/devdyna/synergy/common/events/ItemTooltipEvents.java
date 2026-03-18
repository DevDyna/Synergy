package com.devdyna.synergy.common.events;

import static com.devdyna.synergy.Main.ID;

import java.util.List;

import com.devdyna.synergy.Main;
import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.init.types.zBlockTag;
import com.devdyna.synergy.init.types.zItemTag;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;

public class ItemTooltipEvents {

    private static final int OVER_THE_REGISTRY_ID = 1;

    @SubscribeEvent
    public static void main(ItemTooltipEvent event) {

        var item = event.getItemStack();
        var tooltip = event.getToolTip();

        itemTooltipEnvironmentModifier(item, tooltip);
        itemTooltipLaserColorApplicator(item, tooltip);
        itemTooltipNoGrowingItems(item, tooltip);
        itemTooltipPlaceable(item, tooltip);
        itemTooltipChopperUpgrades(item, tooltip);
        itemTooltipWatcherTweak(item, tooltip);
        itemTooltipPatina(item, tooltip);

    }

    private static void itemTooltipWatcherTweak(ItemStack i, List<Component> t) {
        if (i.is(zItemTag.ENTITY_WATCHER_TWEAK)) {
            t.add(OVER_THE_REGISTRY_ID, Component.translatable(ID + "." + zStatic.Blocks.entity_watcher + ".tweak"));
        }
    }

    private static void itemTooltipChopperUpgrades(ItemStack i, List<Component> t) {
        if (i.is(zItemTag.CHOPPER_AREA_INCREASE)) {
            t.add(OVER_THE_REGISTRY_ID, Component.translatable(ID + "." + zStatic.Blocks.chopper + ".aoe"));
        }
        if (i.is(zItemTag.CHOPPER_ENERGY_UPGRADE)) {
            t.add(OVER_THE_REGISTRY_ID, Component.translatable(ID + "." + zStatic.Blocks.chopper + ".energy"));
        }
    }

    public static void itemTooltipPlaceable(ItemStack i, List<Component> t) {

        if (i.is(zItemTag.PLACEABLE)) {
            t.add(OVER_THE_REGISTRY_ID, Component.translatable(Main.ID + ".placed"));
        }
    }

    public static void itemTooltipLaserColorApplicator(ItemStack i, List<Component> t) {

        if (i.is(zItemTag.DYE_RESET)) {
            t.add(OVER_THE_REGISTRY_ID, Component.translatable(Main.ID + ".laser_use.reset"));
        }
        if (i.is(zItemTag.DYE_MAX)) {
            t.add(OVER_THE_REGISTRY_ID, Component.translatable(Main.ID + ".laser_use.max"));
        }
        if (i.is(zItemTag.DYE_RED)) {
            t.add(OVER_THE_REGISTRY_ID, Component.translatable(Main.ID + ".laser_use.red"));
        }
        if (i.is(zItemTag.DYE_GREEN)) {
            t.add(OVER_THE_REGISTRY_ID, Component.translatable(Main.ID + ".laser_use.green"));
        }
        if (i.is(zItemTag.DYE_BLUE)) {
            t.add(OVER_THE_REGISTRY_ID, Component.translatable(Main.ID + ".laser_use.blue"));
        }
    }

    public static void itemTooltipNoGrowingItems(ItemStack i, List<Component> t) {

        if (i.is(zItemTag.REMOVE_ENTITY_GROWING)) {
            t.add(OVER_THE_REGISTRY_ID, Component.translatable(Main.ID + ".remove_entity_growing"));
        }
        if (i.is(zItemTag.ADD_ENTITY_GROWING)) {
            t.add(OVER_THE_REGISTRY_ID, Component.translatable(Main.ID + ".add_entity_growing"));
        }

    }

    public static void itemTooltipEnvironmentModifier(ItemStack i, List<Component> t) {

        if (blockCheck(i, zBlockTag.DRYING_RACK_HEATER)) {
            t.add(OVER_THE_REGISTRY_ID, Component.translatable(Main.ID + ".boost.drying_rack"));
        }
        if (blockCheck(i, zBlockTag.EVAPORATION_BASIC_HEATER)) {
            t.add(OVER_THE_REGISTRY_ID, Component.translatable(Main.ID + ".boost.evaporation_basin"));
        }

    }

    //TODO replace with JEI
    public static void itemTooltipPatina(ItemStack i, List<Component> t) {

        if (i.is(zItemTag.PATINA)) {
            t.add(OVER_THE_REGISTRY_ID, Component.translatable(Main.ID + ".patina"));
        }
        

    }


    private static boolean blockCheck(ItemStack i, TagKey<Block> tag) {
        return i.getItem() instanceof BlockItem bi && bi.getBlock().defaultBlockState().is(tag);
    }

}
