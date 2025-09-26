package com.devdyna.synergy.init.types;

import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.init.Material;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;

public class zItemTag {
        public static void register(IEventBus bus) {

        }

        public static final TagKey<Item> STONE_SLABS = Material.tagItem("stone_slabs");

        public static final TagKey<Item> AZALEA_BUSHES = Material.tagItem("azalea");

        public static final TagKey<Item> CROP_BLUE_CUP = Material.tagItem("crops/" + zStatic.Plants.TYPE_BLUE_CUP, "c");
        public static final TagKey<Item> CROP_VIOLET_WEBCAP = Material.tagItem(
                        "crops/" + zStatic.Plants.TYPE_VIOLET_WEBCAP,
                        "c");
        public static final TagKey<Item> CROP_RICE = Material.tagItem("crops/" + zStatic.Plants.RICE, "c");
        public static final TagKey<Item> CROP_COTTON = Material.tagItem("crops/" + zStatic.Plants.COTTON, "c");
        public static final TagKey<Item> CROP_CAVE_WHEAT = Material.tagItem("crops/" + zStatic.Plants.CAVE_WHEAT, "c");
        public static final TagKey<Item> CROP_AZALEA = Material.tagItem("crops/potted_" + zStatic.Blocks.azalea, "c");

        public static final TagKey<Item> TOOLS_INTERACTIVE = Material.tagItem("interactive_tools");

        public static final TagKey<Item> AMERICIUM = Material.tagItem("pellets/" + zStatic.ResourceMaterial.americium,
                        "c");
        public static final TagKey<Item> BERKELIUM = Material.tagItem("pellets/" + zStatic.ResourceMaterial.berkelium,
                        "c");
        public static final TagKey<Item> CALIFORNIUM = Material.tagItem(
                        "pellets/" + zStatic.ResourceMaterial.californium,
                        "c");
        public static final TagKey<Item> CURIUM = Material.tagItem("pellets/" + zStatic.ResourceMaterial.curium, "c");
        public static final TagKey<Item> NEPTUNIUM = Material.tagItem("pellets/" + zStatic.ResourceMaterial.neptunium,
                        "c");
        public static final TagKey<Item> PLUTONIUM = Material.tagItem("pellets/" + zStatic.ResourceMaterial.plutonium,
                        "c");
        public static final TagKey<Item> THORIUM = Material.tagItem("pellets/" + zStatic.ResourceMaterial.thorium, "c");
        public static final TagKey<Item> URANIUM = Material.tagItem("pellets/" + zStatic.ResourceMaterial.uranium, "c");

        public static final TagKey<Item> COAL_LIKE = Material.tagItem("coal_like", "c");

        public static final TagKey<Item> SAWDUST = Material.tagItem("dusts/wood", "c");
        public static final TagKey<Item> DUST_GOLD = Material.tagItem("dusts/gold", "c");
        public static final TagKey<Item> DUST_IRON = Material.tagItem("dusts/iron", "c");
        public static final TagKey<Item> DUST_EMERALD = Material.tagItem("dusts/emerald", "c");
        public static final TagKey<Item> DUST_QUARTZ = Material.tagItem("dusts/quartz", "c");
        public static final TagKey<Item> DUST_DIAMOND = Material.tagItem("dusts/diamond", "c");
        public static final TagKey<Item> DUST_AMETHYST = Material.tagItem("dusts/amethyst", "c");
        public static final TagKey<Item> DUST_ANCIENT_DEBRIS = Material.tagItem("dusts/ancient_debris", "c");
        public static final TagKey<Item> DUST_COPPER = Material.tagItem("dusts/copper", "c");
        public static final TagKey<Item> DUST_COAL = Material.tagItem("dusts/coal", "c");
        public static final TagKey<Item> DUST_LAPIS = Material.tagItem("dusts/lapis", "c");

        public static final TagKey<Item> MOB_DROP = Material.tagItem("mob_drops");

        public static final TagKey<Item> INGOT_STEEL = Material.tagItem("ingots/steel", "c");
        public static final TagKey<Item> INGOT_ADVANCEDALLOY = Material.tagItem("ingots/advanced_alloy", "c");

        public static final TagKey<Item> NUGGET_STEEL = Material.tagItem("nuggets/steel", "c");
        public static final TagKey<Item> NUGGET_ADVANCEDALLOY = Material.tagItem("nuggets/advanced_alloy", "c");

        public static final TagKey<Item> RESISTOR_SHELL = Material.tagItem("resistor_shell");
        public static final TagKey<Item> CHIP_CORE = Material.tagItem("chip_core");

        public static final TagKey<Item> CAPACITOR_ACTIVATOR = Material.tagItem("capacitor_activator");

        public static final TagKey<Item> FOILS = Material.tagItem("foils", "c");
        public static final TagKey<Item> FOIL_COPPER = Material.tagItem("foils/copper", "c");
        public static final TagKey<Item> FOIL_GOLD = Material.tagItem("foils/gold", "c");
        public static final TagKey<Item> PLATES = Material.tagItem("plates", "c");

        public static final TagKey<Item> PLATE_COPPER = Material.tagItem("plates/copper", "c");
        public static final TagKey<Item> PLATE_GOLD = Material.tagItem("plates/gold", "c");
        public static final TagKey<Item> PLATE_IRON = Material.tagItem("plates/iron", "c");
        public static final TagKey<Item> PLATE_STEEL = Material.tagItem("plates/steel", "c");
        public static final TagKey<Item> PLATE_COAL = Material.tagItem("plates/coal", "c");




}
