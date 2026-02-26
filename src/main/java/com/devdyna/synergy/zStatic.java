package com.devdyna.synergy;

import java.util.List;

import com.devdyna.synergy.init.types.zBlocks;
import com.devdyna.synergy.init.types.zItems;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredHolder;

public class zStatic {

    public class CreativeTab {
        public static String TYPE = "creative_tab";
        public static String resources = "resources";
        public static String machines = "machines";
        public static String laser = "laser";
        public static String nuclear = "nuclear";
        public static String tools = "tools";
        public static String agriculture = "agriculture";
        public static String decorative = "decorative";
        public static String automation = "automation";
        public static String logistics = "logistics";
        public static String survival = "survival";
        public static String magic = "magic";
        public static String redstone = "redstone";

    }

    public class MobDrop {
        public static String creeper_gall = "creeper_gall";
        public static String enderman_heart = "enderman_heart";
        public static String ghast_bladder = "ghast_bladder";
        public static String guardian_scale = "guardian_scale";
        // public static String red_heart = "red_heart";
        public static String silverfish_dust = "silverfish_dust";
        public static String slime_bolus = "slime_bolus";
        public static String venom_sac = "venom_sac";
        public static String witherflesh = "witherflesh";
        public static String zombie_liver = "zombie_liver";
    }

    public class ResourceMaterial {
        public static String cast_iron = "cast_iron";
        public static String calcite = "calcite";
        public static String wrought_iron = "wrought_iron";
        public static String iron = "iron";
        public static String copper = "copper";
        public static String gold = "gold";
        public static String aquamarine = "aquamarine";
        public static String silicon = "silicon";
        public static String advanced_alloy = "advanced_alloy";
        public static String steel = "steel";
        public static String carbon = "carbon";
        public static String netherrack = "netherrack";
        public static String stone = "stone";
        public static String plastic = "plastic";
        public static String americium = "americium";
        public static String berkelium = "berkelium";
        public static String californium = "californium";
        public static String curium = "curium";
        public static String neptunium = "neptunium";
        public static String plutonium = "plutonium";
        public static String thorium = "thorium";
        public static String uranium = "uranium";
        public static String amethyst = "amethyst";
        public static String diamond = "diamond";
        public static String emerald = "emerald";
        public static String ancient_debris = "ancient_debris";
        public static String energized_redstone = "energized_redstone";
        public static String lapis = "lapis";
        public static String sawdust = "sawdust";
        public static String quartz = "quartz";
        public static String mixed = "mixed";
        public static String nickel = "nickel";
        public static String silver = "silver";
        public static String tin = "tin";
        public static String aluminum = "aluminum";
        public static String iridium = "iridium";
        public static String platinum = "platinum";
        public static String osmium = "osmium";
        public static String lead = "lead";
        public static String sulfur = "sulfur";
        public static String zinc = "zinc";
        public static String wooden = "wooden";
        public static String salt = "salt";

        public class deposits {
            public static String bauxite = "bauxite";
            public static String cylindrite = "cylindrite";
            public static String auricupride = "auricupride";
            public static String pentlandite = "pentlandite";
            public static String galena = "galena";
            public static String osmiridium = "osmiridium";
            public static String argentite = "argentite";
            public static String cassiterite = "cassiterite";
            public static String malachite = "malachite";
            public static String uraninite = "uraninite";
            public static String kaolin = "kaolin";
            public static String xenothite = "xenothite";
            public static String quartzite = "quartzite";
            public static String chalcopyrite = "chalcopyrite";
            public static String pyrolite = "pyrolite";
            public static String lignite = "lignite";
            public static String hematite = "hematite";
        }

    }

    public class ResourceType {
        public static String foil = "_foil";
        public static String coil = "_coil";
        public static String gem = "_gem";
        public static String gear = "_gear";
        public static String ingot = "_ingot";
        public static String nugget = "_nugget";
        public static String block = "_block";
        public static String tiles = "_tiles";
        public static String bricks = "_bricks";
        public static String plate = "_plate";
        public static String raw = "raw_";
        public static String shard = "_shard";
        public static String dust = "_dust";
        public static String pellet = "_pellet";
        public static String mold = "_mold";
        public static String electron_tube = "_electron_tube";
    }

    public class MachineUpgrades {
        public static String TYPE = "upgrade";
        public static String speed = "speed";
        public static String energy = "energy";
        public static String luck = "luck";
        public static String fluid = "fluid";

        public class TemplateUpgrades {

            public static ItemStack SPEED = zItems.UPGRADE_SPEED.get().set(20, 125, 0, 0);
            public static ItemStack ENERGY = zItems.UPGRADE_ENERGY.get().set(0, -50, 0, 0);
            public static ItemStack LUCK = zItems.UPGRADE_LUCK.get().set(0, 150, 25, 0);
            public static ItemStack FLUID = zItems.UPGRADE_FLUID.get().set(0, 150, 0, -20);

            public static List<ItemStack> ALL = List.of(SPEED, ENERGY, LUCK, FLUID);

        }

    }

    public class Items {

        public class Azalea {
            public static String seeds = Blocks.azalea + "_seeds";
            public static String leaf = "small_" + Blocks.azalea + "_leaf";
            public static String roots = "small_" + Blocks.azalea + "_roots";
        }

        public static String wooden_crook = "wooden_crook";
        public static String configurator = "configurator";
        public static String smasher = "smasher";
        public static String soldering_gun = "soldering_gun";
        public static String bone_meal_mixture = "bone_meal_mixture";
        public static String glowstone_mixture = "glowstone_mixture";
        public static String amethyst_mixture = "amethyst_mixture";

        public static String cake_stick = "cake_stick";

        public static String chisel = "chisel";

        public class Batteries {
            public static String TYPE_BATTERY = "_battery";
            public static String red = "red" + TYPE_BATTERY;
            public static String green = "green" + TYPE_BATTERY;
            public static String blue = "blue" + TYPE_BATTERY;
        }

        public class CraftingComponents {
            public static String chip = "chip";
            public static String condenser = "condenser";
            public static String resistor = "resistor";
            public static String light_bulb = "light_bulb";
            public static String magnetic_stone_circuit = "magnetic_stone_circuit";
            public static String metal_bolts = "metal_bolts";
            public static String nether_circuit = "nether_circuit";
            public static String resistive_stone_circuit = "resistive_stone_circuit";
            public static String stone_circuit = "stone_circuit";
            public static String superconductor = "superconductor";
            public static String wired_nether_plate = "wired_nether_plate";
            public static String wired_stone_plate = "wired_stone_plate";
        }

        public class SteelItems {
            public static String steel = "infernal_steel";
            public static String ingot = steel + "_ingot";
            public static String block = steel + "_block";
            public static String nugget = steel + "_nugget";
        }

    }

    public class DecorativeBlocks {
        public static String waxed_planks = "waxed_planks";
        public static String adobe = "adobe";
        public static String smooth_adobe = "smooth_adobe";

        public static String blast_bricks = "blast_bricks";

        public class MachineFrame {
            public static String basic = "basic_machine_frame";
            public static String advanced = "advanced_machine_frame";
        }

        public static class FireClay {

            public static final String TYPE = "fireclay";
            public static final String mossy = "mossy_" + TYPE;
            public static final String cracked = "cracked_" + TYPE;

        }

    }

    public class DryableBricks {
        public static String BALL = "_ball";
        public static String BRICK = "_brick";

        public static final String TYPE = "dryable_brick";

        public class brick {
            public static String clay = "clay" + BRICK;
            public static String packed_mud = "packed_mud" + BRICK;
            public static String blast = "blast" + BRICK;
            public static String fireclay = "fireclay" + BRICK;
        }

        public class ball {
            public static String clay = "clay" + BALL;
            public static String mud = "mud" + BALL;
            public static String packed_mud = "packed_mud" + BALL;
            public static String adobe = "adobe" + BALL;
            public static String clay_mixture = "clay_mixture" + BALL;
            public static String fireclay = "fireclay" + BALL;
        }

    }

    public class Blocks {
        public static String azalea = "azalea";
        public static String sprinkler = "sprinkler";
        public static String harvester = "harvester";
        public static String chopper = "tree_chopper";
        public static String solar_panel = "solar_panel";
        public static String healer = "healer";

        public static String logic_box = "logic_box";
        public static String void_box = "void_box";
        public static String foundry = "foundry";
        public static String faucet = "faucet";

        public static String urn = "urn";
        public static String quern = "quern";

        public static String evaporation_basin = "evaporation_basin";
        public static String casting_table = "casting_table";
        public static String drying_rack = "drying_rack";
        public static String crushing_tub = "crushing_tub";

        // public static String fan = "fan";
        // public static String wind = "wind";

        public static String pipe = "pipe";

        public static String pulse_repeater = "pulse_repeater";
        public static String recursive_repeater = "recursive_repeater";
        public static String inverted_repeater = "inverted_repeater";
    }

    public class FluidTanks {
        public static final String AFFIX = "_tank";
        public static String normal = "fluid" + AFFIX;
        public static String fuel = "fuel" + AFFIX;
    }

    public class Tiers {
        public static String SIMPLE = "simple_";
        public static String ADVANCED = "advanced_";
        public static String ELITE = "elite_";

        public static List<String> ALL = List.of(SIMPLE, ADVANCED, ELITE);
    }

    public class ResourceGenerators {

        public class Water {
            public static final String TYPE = "water_collector";
            public static String simple = Tiers.SIMPLE + TYPE;
            public static String advanced = Tiers.ADVANCED + TYPE;
            public static String elite = Tiers.ELITE + TYPE;
        }

        public class CobbleStone {
            public static final String TYPE = "cobblestone_generator";
            public static String simple = Tiers.SIMPLE + TYPE;
            public static String advanced = Tiers.ADVANCED + TYPE;
            public static String elite = Tiers.ELITE + TYPE;
        }
    }

    public class Machines {
        public final static String TYPE = "machines";
        public static String macerator = "macerator";
        public static String alloy_smelter = "alloy_smelter";
        public static String compressor = "compressor";
        public static String electric_furnace = "electric_furnace";
        public static String extractor = "extractor";
        public static String casting_factory = "casting_factory";
        public static String melter = "electric_melter";
        public static String rock_crusher = "rock_crusher";
    }

    public class Chests {

        public static String TINY_GENERIC = "tiny_chest";

        public static String WOODEN = "tiny_wooden_chest";
        public static String STONE = "tiny_stone_chest";
        public static String ORNATE = "tiny_ornated_chest";

    }

    public class Lazers {
        public static String machine_gun = "laser_machine_gun";
        public static String mirror = "laser_mirror";
        public static String lens = "laser_lens";
        public static String sensor = "laser_sensor";
        public static String rotor = "laser_rotor";
    }

    public class ReactorStuff {
        public static String controller = "quantum_reactor_controller";
        public static String cooler = "cooler";
        public static String fuel_cell = "fuel_cell";
        public static String moderator = "moderator";
        // public static String port = "reactor_port";

        public class CoolerTypes {

            public static String base = cooler + "_base";

            public static String SHADOW = "shadow_" + cooler;
            public static String IRON = "iron_" + cooler;
            public static String GOLD = "gold_" + cooler;
            public static String REDSTONE = "redstone_" + cooler;
            public static String GLOWSTONE = "glowstone_" + cooler;
            public static String LAPIS = "lapis_" + cooler;
            public static String DIAMOND = "diamond_" + cooler;
            public static String EMERALD = "emerald_" + cooler;
            public static String NETHERITE = "netherite_" + cooler;
            public static String WATER = "water_" + cooler;
            public static String SCULK = "sculk_" + cooler;
            public static String QUARTZ = "quartz_" + cooler;
            public static String FROST = "frost_" + cooler;
            public static String ENDER = "ender_" + cooler;
            public static String COPPER = "copper_" + cooler;
        }

        public class ModeratorTypes {
            public static String SIMPLE = "simple_" + moderator;
            public static String ADVANCED = "advanced_" + moderator;
            public static String ELITE = "elite_" + moderator;
        }
    }

    public class PipeStuff {
        public class types {
            private static String item = "item_";
            private static String fluid = "fluid_";
            private static String energy = "energy_";
            // private static String chemical = "chemical_";

            public static String item_node = "item_" + nodes.node;
            public static String fluid_node = "fluid_" + nodes.node;
            public static String energy_node = "energy_" + nodes.node;
            // public static String chemical_node = "chemical_" + nodes.node;
        }

        public class nodes {

            private static String node = "node";
            private static String node2 = "_" + node;

            private static String transfer = "transfer";
            private static String provider = "provider";
            private static String retrieval = "retrieval";
            public static String type_transfer = transfer + node2;
            public static String type_provider = provider + node2;
            public static String type_retrieval = retrieval + node2;

            public class Transfer {
                public static String Item = types.item + transfer + node2;
                public static String Fluid = types.fluid + transfer + node2;
                public static String Energy = types.energy + transfer + node2;
                // public static String Chemical = types.chemical + transfer + node2;
            }

            public class Provider {
                public static String Item = types.item + provider + node2;
                public static String Fluid = types.fluid + provider + node2;
                public static String Energy = types.energy + provider + node2;
                // public static String Chemical = types.chemical + provider + node2;
            }

            public class Retrieval {
                public static String Item = types.item + retrieval + node2;
                public static String Fluid = types.fluid + retrieval + node2;
                public static String Energy = types.energy + retrieval + node2;
                // public static String Chemical = types.chemical + retrieval + node2;
            }

        }

        public static String pipe = "pipe";

    }

    public class Plants {

        private static String MUSH = "_mushroom";

        public static final String RICE = "rice";
        public static final String CAVE_WHEAT = "cave_wheat";
        public static final String COTTON = "cotton";

        public static final String TYPE_BLUE_CUP = "blue_cup";
        public static final String TYPE_VIOLET_WEBCAP = "violet_webcap";

        public static final String BLUE_CUP_MUSHROOM = TYPE_BLUE_CUP + MUSH;
        public static final String VIOLET_WEBCAP_MUSHROOM = TYPE_VIOLET_WEBCAP + MUSH;

    }

    public class Wild {

        public static String WILD = "wild_";

        public static final String RICE = WILD + Plants.RICE;
        public static final String CAVE_WHEAT = WILD + Plants.CAVE_WHEAT;
        public static final String COTTON = WILD + Plants.COTTON;

    }

    public class Seeds {

        private static String SEED = "_seed";
        private static String SPORE = "_pod";

        public static final String RICE_SEED = Plants.RICE + SEED;
        public static final String CAVE_WHEAT_SEED = Plants.CAVE_WHEAT + SEED;
        public static final String COTTON_SEED = Plants.COTTON + SEED;

        public static final String BLUE_CUP_SPORE = Plants.TYPE_BLUE_CUP + SPORE;
        public static final String VIOLET_WEBCAP_SPORE = Plants.TYPE_VIOLET_WEBCAP + SPORE;

    }

    public class Fluids {
        public static String AFFIX_DROPLETT = "droplet_";
        public static String AFFIX_MOLTEN = "molten_";

        public static String oil = "oil";
        public static String glue = "glue";
        public static String sap = "sap";
        public static String rubber = "rubber";

        public static String ironberry_juice = "ironberry_juice";
        public static String liquid_glass = "liquid_glass";
        public static String honey = "honey";

        public static String iron = AFFIX_MOLTEN + "iron";
        public static String copper = AFFIX_MOLTEN + "copper";
        public static String gold = AFFIX_MOLTEN + "gold";
        public static String steel = AFFIX_MOLTEN + "steel";
        public static String uranium = AFFIX_MOLTEN + "uranium";
        public static String nickel = AFFIX_MOLTEN + "nickel";
        public static String silver = AFFIX_MOLTEN + "silver";
        public static String tin = AFFIX_MOLTEN + "tin";
        public static String aluminum = AFFIX_MOLTEN + "aluminum";
        public static String iridium = AFFIX_MOLTEN + "iridium";
        public static String platinum = AFFIX_MOLTEN + "platinum";
        public static String osmium = AFFIX_MOLTEN + "osmium";
        public static String lead = AFFIX_MOLTEN + "lead";
        public static String blaze = AFFIX_MOLTEN + "blaze";

    }

    public class Mods {
        public static String GuideMe = "guideme";
        public static String Mekanism = "mekanism";
        public static String FarmersDelight = "farmersdelight";
        public static String ImmersiveEngineering = "immersiveengineering";
        public static String Patchouli = "patchouli";
        public static String AllTheOres = "alltheores";
        public static String FTBMaterials = "ftbmaterials";
        public static String Evilcraft = "evilcraft";
        public static String AE2 = "ae2";
        public static String DraconicEvolution = "draconicevolution";
        public static String EnderIO = "enderio";
        public static String SilentGear = "silentgear";
        public static String Create = "create";
        public static String JEI = "jei";
    }

    public class tips {
        public static String MIXTURE_TIP = "mixture_tip";
        public static String SHIFT = "shift";
        public static String SAFE_BUILD = "safe_building";
        public static String INGREDIENT = "crafting_ingredient";
    }

    public class Config {
        public static String FE_CAPACITY = "FE Capacity";
        public static String FE_COST = "FE consumed every tick";
        public static String FE_GEN = "FE production every tick";

    }

    public static final List<DeferredHolder<Block, Block>> ALL_DRYING_RACKS = List.of(
            zBlocks.ACACIA_DRYING_RACK,
            zBlocks.BAMBOO_DRYING_RACK,
            zBlocks.BIRCH_DRYING_RACK,
            zBlocks.CHERRY_DRYING_RACK,
            zBlocks.CRIMSON_DRYING_RACK,
            zBlocks.DARK_OAK_DRYING_RACK,
            zBlocks.JUNGLE_DRYING_RACK,
            zBlocks.MANGROVE_DRYING_RACK,
            zBlocks.OAK_DRYING_RACK,
            zBlocks.SPRUCE_DRYING_RACK,
            zBlocks.WARPED_DRYING_RACK);

    public class AdditionalModel {

        public static final String QUERN = "block/quern/moving";
        public static final String VOID_BOX = "block/tiny_block/void_box/animation";
        public static final String LOGIC_BOX_OFF = "block/tiny_block/logic_box/animation/red";
        public static final String LOGIC_BOX_ON = "block/tiny_block/logic_box/animation/green";

    }

}
