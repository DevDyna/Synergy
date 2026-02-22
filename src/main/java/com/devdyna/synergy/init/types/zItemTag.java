package com.devdyna.synergy.init.types;

import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.init.Material;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;

public class zItemTag {
        public static void register(IEventBus bus) {

        }

        public static final TagKey<Item> COOLERS = Material
                        .tagItem("cooler");
        public static final TagKey<Item> MODERATORS = Material
                        .tagItem("moderator");

        public static final TagKey<Item> CAN_SUSTAIN_RICE = Material.tagItem("can_sustain/" + zStatic.Plants.RICE);
        public static final TagKey<Item> CAN_SUSTAIN_COTTON = Material
                        .tagItem("can_sustain/" + zStatic.Plants.COTTON);
        public static final TagKey<Item> CAN_SUSTAIN_CAVE_WHEAT = Material
                        .tagItem("can_sustain/" + zStatic.Plants.CAVE_WHEAT);
        public static final TagKey<Item> CAN_SUSTAIN_BLUE_CUP = Material
                        .tagItem("can_sustain/" + zStatic.Plants.BLUE_CUP_MUSHROOM.replace("_mushroom", ""));
        public static final TagKey<Item> CAN_SUSTAIN_VIOLET_WEBCAP = Material
                        .tagItem("can_sustain/" + zStatic.Plants.VIOLET_WEBCAP_MUSHROOM.replace("_mushroom", ""));

        public static final TagKey<Item> INFESTED_BLOCKS = Material
                        .tagItem("infested_blocks", "c");

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
        public static final TagKey<Item> SAWDUST2 = Material.tagItem("dusts/saw", "c");

        public static final TagKey<Item> MOB_DROP = Material.tagItem("mob_drops");

        public static final TagKey<Item> INGOT_STEEL = Material.tagItem("ingots/steel", "c");
        public static final TagKey<Item> INGOT_ADVANCEDALLOY = Material.tagItem("ingots/advanced_alloy", "c");
        public static final TagKey<Item> INGOT_WROUGHT_IRON = Material.tagItem("ingots/wrought_iron", "c");

        public static final TagKey<Item> NUGGET_STEEL = Material.tagItem("nuggets/steel", "c");
        public static final TagKey<Item> NUGGET_ADVANCEDALLOY = Material.tagItem("nuggets/advanced_alloy", "c");
        public static final TagKey<Item> NUGGET_COPPER = Material.tagItem("nuggets/copper", "c");
        public static final TagKey<Item> NUGGET_WROUGHT_IRON = Material.tagItem("nuggets/wrought_iron", "c");

        public static final TagKey<Item> URN_MIXTURES = Material.tagItem("urn_mixtures");

        public static final TagKey<Item> FOILS = Material.tagItem("foils", "c");
        public static final TagKey<Item> FOIL_COPPER = Material.tagItem("foils/copper", "c");
        public static final TagKey<Item> FOIL_GOLD = Material.tagItem("foils/gold", "c");
        public static final TagKey<Item> FOIL_SILVER = Material.tagItem("foils/silver", "c");
        public static final TagKey<Item> FOIL_IRON = Material.tagItem("foils/iron", "c");

        public static final TagKey<Item> PLATES = Material.tagItem("plates", "c");

        public static final TagKey<Item> PLATE_COPPER = Material.tagItem("plates/copper", "c");
        public static final TagKey<Item> PLATE_SILVER = Material.tagItem("plates/silver", "c");
        public static final TagKey<Item> PLATE_GOLD = Material.tagItem("plates/gold", "c");
        public static final TagKey<Item> PLATE_IRON = Material.tagItem("plates/iron", "c");
        public static final TagKey<Item> PLATE_STEEL = Material.tagItem("plates/steel", "c");
        public static final TagKey<Item> PLATE_COAL = Material.tagItem("plates/coal", "c");
        public static final TagKey<Item> PLATE_ADVANCED_ALLOY = Material.tagItem("plates/advanced_alloy", "c");
        public static final TagKey<Item> PLATE_AQUAMARINE = Material.tagItem("plates/aquamarine", "c");
        public static final TagKey<Item> PLATE_WROUGHT_IRON = Material.tagItem("plates/wrought_iron", "c");

        public static final TagKey<Item> RICE_PLANT = Material.tagItem("plant/rice");
        public static final TagKey<Item> COTTON_PLANT = Material.tagItem("plant/cotton");
        public static final TagKey<Item> CAVE_WHEAT_PLANT = Material.tagItem("plant/cave_wheat");

        public static final TagKey<Item> GEMS_AQUAMARINE = Material.tagItem("gems/aquamarine", "c");
        public static final TagKey<Item> GEMS_SILICON = Material.tagItem("gems/silicon", "c");

        public static final TagKey<Item> METAL_NUGGETS = Material.tagItem("metal_nuggets");

        public static final TagKey<Item> MUSHROOM_RESULT = Material.tagItem("mushroom/result");
        public static final TagKey<Item> MUSHROOM_SEED = Material.tagItem("mushroom/seed");
        public static final TagKey<Item> PLANT_SEED = Material.tagItem("crop/seed");

        public static final TagKey<Item> SILICON = Material.tagItem("silicon", "c");

        public static final TagKey<Item> PLACEABLE = Material.tagItem("placeable");

        public static final TagKey<Item> DUST_NICKEL = Material.tagItem("dusts/nickel", "c");
        public static final TagKey<Item> DUST_SILVER = Material.tagItem("dusts/silver", "c");
        public static final TagKey<Item> DUST_IRIDIUM = Material.tagItem("dusts/iridium", "c");
        public static final TagKey<Item> DUST_PLATINUM = Material.tagItem("dusts/platinum", "c");
        public static final TagKey<Item> DUST_OSMIUM = Material.tagItem("dusts/osmium", "c");
        public static final TagKey<Item> DUST_TIN = Material.tagItem("dusts/tin", "c");
        public static final TagKey<Item> DUST_LEAD = Material.tagItem("dusts/lead", "c");
        public static final TagKey<Item> DUST_URANIUM = Material.tagItem("dusts/uranium", "c");
        public static final TagKey<Item> DUST_ALUMINUM = Material.tagItem("dusts/aluminum", "c");
        public static final TagKey<Item> DUST_SULFUR = Material.tagItem("dusts/sulfur", "c");
        public static final TagKey<Item> DUST_ZINC = Material.tagItem("dusts/zinc", "c");
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

        public static final TagKey<Item> INGOT_NICKEL = Material.tagItem("ingots/nickel", "c");
        public static final TagKey<Item> INGOT_SILVER = Material.tagItem("ingots/silver", "c");
        public static final TagKey<Item> INGOT_IRIDIUM = Material.tagItem("ingots/iridium", "c");
        public static final TagKey<Item> INGOT_PLATINUM = Material.tagItem("ingots/platinum", "c");
        public static final TagKey<Item> INGOT_OSMIUM = Material.tagItem("ingots/osmium", "c");
        public static final TagKey<Item> INGOT_TIN = Material.tagItem("ingots/tin", "c");
        public static final TagKey<Item> INGOT_LEAD = Material.tagItem("ingots/lead", "c");
        public static final TagKey<Item> INGOT_URANIUM = Material.tagItem("ingots/uranium", "c");
        public static final TagKey<Item> INGOT_ALUMINUM = Material.tagItem("ingots/aluminum", "c");
        public static final TagKey<Item> INGOT_ZINC = Material.tagItem("ingots/zinc", "c");

        public static final TagKey<Item> PIPE = Material.tagItem("pipe");
        public static final TagKey<Item> NODES = Material.tagItem("nodes");

        public static final TagKey<Item> NODES_PROVIDER = Material.tagItem("nodes/provider");

        public static final TagKey<Item> NODES_TRANSFER = Material.tagItem("nodes/transfer");

        public static final TagKey<Item> NODES_RETRIEVAL = Material.tagItem("nodes/retrieval");

        public static final TagKey<Item> SUPPLEMENTARIES_BRICKS = Material.tagItem("throwable_bricks",
                        "supplementaries");

        public static final TagKey<Item> GEARS = Material.tagItem("gears", "c");
        public static final TagKey<Item> GEAR_WOODEN = Material.tagItem("gears/wooden", "c");
        public static final TagKey<Item> GEAR_IRON = Material.tagItem("gears/iron", "c");
        public static final TagKey<Item> GEAR_GOLD = Material.tagItem("gears/gold", "c");
        public static final TagKey<Item> GEAR_COPPER = Material.tagItem("gears/copper", "c");
        public static final TagKey<Item> GEAR_TIN = Material.tagItem("gears/tin", "c");
        public static final TagKey<Item> GEAR_LEAD = Material.tagItem("gears/lead", "c");
        public static final TagKey<Item> GEAR_STEEL = Material.tagItem("gears/steel", "c");
        public static final TagKey<Item> GEAR_NICKEL = Material.tagItem("gears/nickel", "c");

        public static final TagKey<Item> COLOR_APPLICABLE = Material.tagItem("color_applicable");

        public static final TagKey<Item> DYE_RESET = Material.tagItem("laser_beam/black");
        public static final TagKey<Item> DYE_MAX = Material.tagItem("laser_beam/white");
        public static final TagKey<Item> DYE_RED = Material.tagItem("laser_beam/red");
        public static final TagKey<Item> DYE_GREEN = Material.tagItem("laser_beam/green");
        public static final TagKey<Item> DYE_BLUE = Material.tagItem("laser_beam/blue");

        public static final TagKey<Item> REMOVE_ENTITY_GROWING = Material.tagItem("remove_entity_growing");
        public static final TagKey<Item> ADD_ENTITY_GROWING = Material.tagItem("add_entity_growing");

        public static final TagKey<Item> REPEATERS = Material.tagItem("repeater", "c");

        public static final TagKey<Item> COILS = Material.tagItem("coils", "c");
        public static final TagKey<Item> COIL_COPPER = Material.tagItem("coils/copper", "c");
        public static final TagKey<Item> COIL_GOLD = Material.tagItem("coils/gold", "c");
        public static final TagKey<Item> COIL_IRON = Material.tagItem("coils/iron", "c");
        public static final TagKey<Item> COIL_SILVER = Material.tagItem("coils/silver", "c");

        public static final TagKey<Item> MACHINES = Material
                        .tagItem("industrial_machine");

        public static final TagKey<Item> UPGRADES = Material
                        .tagItem("upgrades");

        public static final TagKey<Item> UPGRADE_ENERGY = Material
                        .tagItem("upgrades/energy");

        public static final TagKey<Item> UPGRADE_SPEED = Material
                        .tagItem("upgrades/speed");

        public static final TagKey<Item> UPGRADE_LUCK = Material
                        .tagItem("upgrades/luck");

        public static final TagKey<Item> UPGRADE_FLUID = Material
                        .tagItem("upgrades/fluid");

        public static final TagKey<Item> VOID_BOX_DENY = Material
                        .tagItem("void_box_deny");

        public static final TagKey<Item> MOLDS = Material
                        .tagItem("molds");

        public static final TagKey<Item> ELECTRON_TUBES = Material.tagItem("electron_tubes", "c");

        public static final TagKey<Item> IRON_ELECTRON_TUBE = Material.tagItem("electron_tubes/iron", "c");
        public static final TagKey<Item> GOLD_ELECTRON_TUBE = Material.tagItem("electron_tubes/gold", "c");
        public static final TagKey<Item> COPPER_ELECTRON_TUBE = Material.tagItem("electron_tubes/copper", "c");
        public static final TagKey<Item> ALUMINUM_ELECTRON_TUBE = Material.tagItem("electron_tubes/aluminum", "c");
        public static final TagKey<Item> IRIDIUM_ELECTRON_TUBE = Material.tagItem("electron_tubes/iridium", "c");
        public static final TagKey<Item> LEAD_ELECTRON_TUBE = Material.tagItem("electron_tubes/lead", "c");
        public static final TagKey<Item> NICKEL_ELECTRON_TUBE = Material.tagItem("electron_tubes/nickel", "c");
        public static final TagKey<Item> OSMIUM_ELECTRON_TUBE = Material.tagItem("electron_tubes/osmium", "c");
        public static final TagKey<Item> PLATINUM_ELECTRON_TUBE = Material.tagItem("electron_tubes/platinum", "c");
        public static final TagKey<Item> SILVER_ELECTRON_TUBE = Material.tagItem("electron_tubes/silver", "c");
        public static final TagKey<Item> STEEL_ELECTRON_TUBE = Material.tagItem("electron_tubes/steel", "c");
        public static final TagKey<Item> TIN_ELECTRON_TUBE = Material.tagItem("electron_tubes/tin", "c");
        public static final TagKey<Item> URANIUM_ELECTRON_TUBE = Material.tagItem("electron_tubes/uranium", "c");

        public static final TagKey<Item> BLOCK_STEEL = Material
                        .tagItem("storage_blocks/steel", "c");

        public static final TagKey<Item> BLOCK_ADVANCED_ALLOY = Material
                        .tagItem("storage_blocks/advanced_alloy", "c");

        public static final TagKey<Item> BLOCK_WROUGHT_IRON = Material
                        .tagItem("storage_blocks/wrought_iron", "c");

        public static final TagKey<Item> CAST_IRON_BLOCKS = Material.tagItem("cast_iron_blocks");

        public static final TagKey<Item> MIXTURE_ALTERNATIVE = Material.tagItem("mixture_alternative");
        
        public static final TagKey<Item> RESISTOR_INGREDIENT = Material.tagItem("resistor_ingredient");

        public static final TagKey<Item> CHOPPER_AREA_INCREASE = Material.tagItem("chopper/area_increaser");

        public static final TagKey<Item> CHOPPER_ENERGY_UPGRADE = Material.tagItem("chopper/energy_upgrade");

}
