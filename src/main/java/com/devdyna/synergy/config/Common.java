package com.devdyna.synergy.config;

import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.api.machine.BaseMachineBE;
import com.devdyna.synergy.api.utils.ModAddonUtil;
import com.devdyna.synergy.api.utils.StringUtil;

import net.neoforged.fml.ModContainer;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.ModConfigSpec.*;

public class Common {

        private static final ModConfigSpec.Builder qCOMMON = new ModConfigSpec.Builder();

        // grr grr
        public static BooleanValue DISABLE_ITEM_USE_RECIPE;// false
        public static BooleanValue DISABLE_HARVESTABLE_ACTION;// false

        // autotune
        public static IntValue HARVESTER_TICK_DELAY;// 5
        public static IntValue HARVESTER_FE_COST;// 25
        public static IntValue HARVESTER_MAX_FE;// 10k
        public static IntValue HARVESTER_TREE_CUTTING_LIMIT;// 2048
        public static BooleanValue HARVESTER_DISABLE_CHECK_REPLANT;// false
        public static BooleanValue HARVESTER_DISABLE_CHECK_NOREPLANT;// false
        public static BooleanValue HARVESTER_DISABLE_CHECK_TREE;// false
        public static BooleanValue HARVESTER_DISABLE_CHECK_BIGPLANT;// false
        public static BooleanValue HARVESTER_DISABLE_CHECK_API;// false
        public static BooleanValue HARVESTER_DISABLE_DROP_WHEN_FULL;// false

        public static IntValue SOLAR_PANEL_FE_GEN;// 16
        public static IntValue SOLAR_PANEL_MAX_FE;// 10k
        public static BooleanValue SOLAR_PANEL_DISABLE_CHECK_SEE_SKY;// false
        public static BooleanValue SOLAR_PANEL_DISABLE_DAYTIME;// false

        public static IntValue SPRINKLER_FE_COST;// 25
        public static IntValue SPRINKLER_MAX_FE;// 10k

        public static IntValue SIMPLE_COBBLE_GEN_TICK_RATE;// 20
        public static IntValue SIMPLE_COBBLE_GEN_ITEM_COUNT;// 4

        public static IntValue SIMPLE_WATER_GEN_TICK_RATE;// 20
        public static IntValue SIMPLE_WATER_GEN_FLUID_AMOUNT;// 200

        public static IntValue ADVANCED_COBBLE_GEN_TICK_RATE;// 5
        public static IntValue ADVANCED_COBBLE_GEN_ITEM_COUNT;// 16

        public static IntValue ADVANCED_WATER_GEN_TICK_RATE;// 5
        public static IntValue ADVANCED_WATER_GEN_FLUID_AMOUNT;// 800

        public static IntValue ELITE_COBBLE_GEN_TICK_RATE;// 1
        public static IntValue ELITE_COBBLE_GEN_ITEM_COUNT;// 64

        public static IntValue ELITE_WATER_GEN_TICK_RATE;// 1
        public static IntValue ELITE_WATER_GEN_FLUID_AMOUNT;// 6400

        public static IntValue SIMPLE_WATER_GEN_CAPACITY;// 4000
        public static IntValue ADVANCED_WATER_GEN_CAPACITY;// 16000
        public static IntValue ELITE_WATER_GEN_CAPACITY;// 64000

        // ic4
        public static IntValue MACHINE_MAX_FE;// 10k

        public static IntValue MACHINE_MAX_SPEED_UPGRADES_TYPE;// 4
        public static IntValue MACHINE_MAX_ENERGY_UPGRADES_TYPE;// max
        public static IntValue MACHINE_MAX_LUCK_UPGRADES_TYPE;// max
        public static IntValue MACHINE_MAX_FLUID_UPGRADES_TYPE;// max

        public static IntValue MACHINE_MINIMAL_TICK_DELAY;// 1
        public static IntValue MACHINE_MINIMAL_FE_COST;// 0
        public static IntValue MACHINE_MINIMAL_FLUID_COST;// 0
        public static IntValue MACHINE_MAXIMAL_LUCK;// 100

        public static BooleanValue DISABLE_MACHINE_DROP_WHEN_CORRUPTED;

        public static BooleanValue DISABLE_MACHINE_FURNACE_PROCESS_VANILLA;// false
        public static IntValue MACHINE_FURNACE_PROCESS_VANILLA_FE_COST;// DEFAULT
        public static BooleanValue DISABLE_MACHINE_FURNACE_VANILLA_TICK_REDUCER;// false
        public static IntValue MACHINE_FURNACE_PROCESS_VANILLA_MIN_TICK_DELAY;// 20
        public static IntValue MACHINE_FURNACE_PROCESS_VANILLA_PERCENTUAGE_TICK_DELAY;// 50%

        // lazer

        public static IntValue LASER_ROTOR_FE_GEN;// 10k
        public static IntValue LASER_ROTOR_MAX_FE;// 1M

        public static IntValue LASER_MACHINE_GUN_FE_COST;// 10
        public static IntValue LASER_MACHINE_GUN_MAX_FE;// 10k
        public static IntValue LASER_MACHINE_GUN_COLOR_RED;// 255
        public static IntValue LASER_MACHINE_GUN_COLOR_GREEN;// 0
        public static IntValue LASER_MACHINE_GUN_COLOR_BLUE;// 0
        public static IntValue LASER_MACHINE_GUN_MAX_LASER_LENGHT;// 8

        public static IntValue LASER_SENSOR_TICK_DELAY;// 5
        // magik

        // nuke

        public static IntValue REACTOR_CONTROLLER_MAX_FE;// 1M

        public static IntValue COPPER_COOLER_BASE_COOLING;// 0
        public static IntValue DIAMOND_COOLER_BASE_COOLING;// 0
        public static IntValue EMERALD_COOLER_BASE_COOLING;// 0
        public static IntValue ENDER_COOLER_BASE_COOLING;// 0
        public static IntValue FROST_COOLER_BASE_COOLING;// 0
        public static IntValue GLOWSTONE_COOLER_BASE_COOLING;// 0
        public static IntValue GOLD_COOLER_BASE_COOLING;// 0
        public static IntValue IRON_COOLER_BASE_COOLING;// 0
        public static IntValue LAPIS_COOLER_BASE_COOLING;// 0
        public static IntValue NETHERITE_COOLER_BASE_COOLING;// 0
        public static IntValue QUARTZ_COOLER_BASE_COOLING;// 0
        public static IntValue REDSTONE_COOLER_BASE_COOLING;// 0
        public static IntValue SCULK_COOLER_BASE_COOLING;// 0
        public static IntValue SHADOW_COOLER_BASE_COOLING;// 0
        public static IntValue WATER_COOLER_BASE_COOLING;// 0

        public static IntValue COPPER_COOLER_ACTIVE_COOLING;// -80
        public static IntValue DIAMOND_COOLER_ACTIVE_COOLING;// -150
        public static IntValue EMERALD_COOLER_ACTIVE_COOLING;// -160
        public static IntValue ENDER_COOLER_ACTIVE_COOLING;// -120
        public static IntValue FROST_COOLER_ACTIVE_COOLING;// -60
        public static IntValue GLOWSTONE_COOLER_ACTIVE_COOLING;// -130
        public static IntValue GOLD_COOLER_ACTIVE_COOLING;// -120
        public static IntValue IRON_COOLER_ACTIVE_COOLING;// -80
        public static IntValue LAPIS_COOLER_ACTIVE_COOLING;// -120
        public static IntValue NETHERITE_COOLER_ACTIVE_COOLING;// -140
        public static IntValue QUARTZ_COOLER_ACTIVE_COOLING;// -90
        public static IntValue REDSTONE_COOLER_ACTIVE_COOLING;// -90
        public static IntValue SCULK_COOLER_ACTIVE_COOLING;// -120
        public static IntValue SHADOW_COOLER_ACTIVE_COOLING;// -320
        public static IntValue WATER_COOLER_ACTIVE_COOLING;// -60

        public static DoubleValue SIMPLE_MODERATOR_FE_REDUCER;// 1.1
        public static DoubleValue ADVANCED_MODERATOR_FE_REDUCER;// 1.65
        public static DoubleValue ELITE_MODERATOR_FE_REDUCER;// 3

        public static DoubleValue SIMPLE_MODERATOR_HEAT_REDUCER;// 1.3
        public static DoubleValue ADVANCED_MODERATOR_HEAT_REDUCER;// 2
        public static DoubleValue ELITE_MODERATOR_HEAT_REDUCER;// 4.5

        // pimpa

        // redrock

        // toolz

        public static BooleanValue DISABLE_PLANT_MIXTURE_ON_NETHER_WART;// false
        public static BooleanValue DISABLE_PLANT_MIXTURE_ON_SUGAR_CANES;// false
        public static BooleanValue DISABLE_PLANT_MIXTURE_ON_CACTUS;// false
        public static BooleanValue DISABLE_PLANT_MIXTURE_ON_VINES;// false
        public static BooleanValue DISABLE_PLANT_MIXTURE_ON_STEMS;// false
        public static BooleanValue DISABLE_PLANT_MIXTURE_FLOWER_SPREADING;// false
        public static BooleanValue DISABLE_PLANT_MIXTURE_VANILLA_FALLBACK;// false (super.useOn)

        // survival

        public static BooleanValue DISABLE_DRYABLE_BRICKS_DECREASE_STAGE_WHEN_WET;// false

        public static BooleanValue DISABLE_DRYING_RACK_STACK_NERFER;// false
        public static DoubleValue DRYING_RACK_STACK_NERFER_RATE;// 100%

        public static BooleanValue DISABLE_FOUNDRY_SPEED_BOOSTER;// false
        public static DoubleValue FOUNDRY_SPEED_BOOSTER_MULTIPLIER;// 1x

        // rpg

        public static BooleanValue DISABLE_HEALER_HEAL_HP;// false
        public static BooleanValue DISABLE_HEALER_REMOVE_FIRE;// false

        // ex alchemia

        public static BooleanValue DISABLE_CROOK_EVENT;// false

        // ...

        public static BooleanValue DISABLE_ENDER_EYE_RETURN_EVENT;// false
        public static BooleanValue DISABLE_REMOVE_BABY_GROW_EVENT;// false
        public static BooleanValue DISABLE_READD_BABY_GROW_EVENT;// false

        public static void register(ModContainer c) {
                agriculture();
                automation();
                industrial_machines();
                laser_stuff();
                magic();
                nuclear_stuff();
                pipe_blocks();
                redstone();
                tools();
                survival();
                rpg();
                skyblock();
                other();

                c.registerConfig(ModConfig.Type.COMMON, qCOMMON.build());
        }

        private static void agriculture() {
                qCOMMON.comment("Agriculture").push("1-agriculture");

                DISABLE_ITEM_USE_RECIPE = bool("Disable Toggleable Item-Use recipes",
                                "optional_item_use_recipe");
                DISABLE_HARVESTABLE_ACTION = bool(
                                "Disable player right-click on crops to collect" +
                                                "\nIf detected " + zStatic.Mods.FarmersDelight
                                                + " it will turn off by default",
                                "harvestable_action",
                                ModAddonUtil.checkMod(zStatic.Mods.FarmersDelight));

                qCOMMON.pop();
        }

        private static void automation() {
                qCOMMON.comment("Automation").push("2-automation");

                decor.complex(zStatic.Blocks.harvester);

                HARVESTER_TICK_DELAY = number("Base tick rate of Harvester to check AOE blocks when actived",
                                "harvester_tickrate", 5);
                HARVESTER_FE_COST = number(zStatic.Config.FE_COST,
                                "harvester_fe_rate", 25);
                HARVESTER_MAX_FE = number(zStatic.Config.FE_CAPACITY,
                                "harvester_fe_max", 10_000);
                HARVESTER_TREE_CUTTING_LIMIT = number(
                                "Max number of harvestable blocks harvestable foreach interaction",
                                "harvester_limit", 2048);
                HARVESTER_DISABLE_CHECK_REPLANT = bool("Disable any crop-plant replantable harvest",
                                "harvester_disable_replant");
                HARVESTER_DISABLE_CHECK_NOREPLANT = bool("Disable any plant like melons and pumpkins harvest",
                                "harvester_disable_noreplant");
                HARVESTER_DISABLE_CHECK_TREE = bool("Disable any tree harvest",
                                "harvester_disable_tree");
                HARVESTER_DISABLE_CHECK_BIGPLANT = bool("Disable any cactus/bamboo/sugar canes like harvest",
                                "harvester_disable_bigplant");
                HARVESTER_DISABLE_CHECK_API = bool("Disable any HarvestableAPI plant harvest",
                                "harvester_disable_api");
                HARVESTER_DISABLE_DROP_WHEN_FULL = bool("Disable drop items when output full",
                                "harvester_disable_drop_when_full");

                decor.complex(zStatic.Blocks.solar_panel);

                SOLAR_PANEL_FE_GEN = number(zStatic.Config.FE_GEN,
                                "solar_panel_fe_gen", 16);
                SOLAR_PANEL_MAX_FE = number(zStatic.Config.FE_CAPACITY,
                                "solar_panel_fe_max", 10_000);
                SOLAR_PANEL_DISABLE_CHECK_SEE_SKY = bool("Disable condition to see the sky",
                                "solar_panel_disable_seesky");
                SOLAR_PANEL_DISABLE_DAYTIME = bool("Disable condition to be daytime",
                                "solar_panel_disable_daytime");

                decor.complex(zStatic.Blocks.sprinkler);

                SPRINKLER_FE_COST = number(zStatic.Config.FE_COST,
                                "sprinkler_fe_cost", 25);
                SPRINKLER_MAX_FE = number(zStatic.Config.FE_CAPACITY,
                                "sprinkler_fe_max", 10_000);

                decor.complex(zStatic.ResourceGenerators.CobbleStone.TYPE);
                SIMPLE_COBBLE_GEN_TICK_RATE = number("Ticks delay to produce a resource",
                                zStatic.Tiers.SIMPLE + "cobblegen_tick_rate", 20);
                SIMPLE_COBBLE_GEN_ITEM_COUNT = number("Item count every time executed",
                                zStatic.Tiers.SIMPLE + "cobblegen_item_count", 4);

                ADVANCED_COBBLE_GEN_TICK_RATE = number("Ticks delay to produce a resource",
                                zStatic.Tiers.ADVANCED + "cobblegen_tick_rate", 5);
                ADVANCED_COBBLE_GEN_ITEM_COUNT = number("Item count every time executed",
                                zStatic.Tiers.ADVANCED + "cobblegen_item_count", 16);

                ELITE_COBBLE_GEN_TICK_RATE = number("Ticks delay to produce a resource",
                                zStatic.Tiers.ELITE + "cobblegen_tick_rate", 1);
                ELITE_COBBLE_GEN_ITEM_COUNT = number("Item count every time executed",
                                zStatic.Tiers.ELITE + "cobblegen_item_count", 64);

                decor.complex(zStatic.ResourceGenerators.Water.TYPE);

                SIMPLE_WATER_GEN_TICK_RATE = number("Ticks delay to produce a resource",
                                zStatic.Tiers.SIMPLE + "watergen_tick_rate", 20);
                SIMPLE_WATER_GEN_FLUID_AMOUNT = number("Fluid amount every time executed",
                                zStatic.Tiers.SIMPLE + "watergen_fluid_amount", 200);

                ADVANCED_WATER_GEN_TICK_RATE = number("Ticks delay to produce a resource",
                                zStatic.Tiers.ADVANCED + "watergen_tick_rate", 5);
                ADVANCED_WATER_GEN_FLUID_AMOUNT = number("Fluid amount every time executed",
                                zStatic.Tiers.ADVANCED + "watergen_fluid_amount", 800);

                ELITE_WATER_GEN_TICK_RATE = number("Ticks delay to produce a resource",
                                zStatic.Tiers.ELITE + "watergen_tick_rate", 1);
                ELITE_WATER_GEN_FLUID_AMOUNT = number("Fluid amount every time executed",
                                zStatic.Tiers.ELITE + "watergen_fluid_amount", 3200);

                SIMPLE_WATER_GEN_CAPACITY = number("Fluid capacity",
                                zStatic.Tiers.SIMPLE + "watergen_capacity", 4000);
                ADVANCED_WATER_GEN_CAPACITY = number("Fluid capacity",
                                zStatic.Tiers.ADVANCED + "watergen_capacity", 16000);
                ELITE_WATER_GEN_CAPACITY = number("Fluid capacity",
                                zStatic.Tiers.ELITE + "watergen_capacity", 64000);
                qCOMMON.pop();
        }

        private static void industrial_machines() {
                qCOMMON.comment("Industrial-Machines").push("3-industrialmachines");

                decor.simple("Base Machine and Upgrades");

                MACHINE_MAX_FE = number("Base max energy stored",
                                "base_machine_max_fe", 10_000);

                MACHINE_MAX_SPEED_UPGRADES_TYPE = number("Max Speed Increaser Upgrade Types usable foreach machine",
                                "max_speed_upgrades", 4,0,16);
                MACHINE_MAX_ENERGY_UPGRADES_TYPE = number("Max Energy Efficiency Upgrade Types usable foreach machine",
                                "max_energy_upgrades", 16,0,16);
                MACHINE_MAX_LUCK_UPGRADES_TYPE = number(
                                "Max Secondary Output Increaser Upgrade Types usable foreach machine",
                                "max_luck_upgrades", 16,0,16);
                MACHINE_MAX_FLUID_UPGRADES_TYPE = number("Max Fluid Efficiency Upgrade Types usable foreach machine",
                                "max_fluid_upgrades", 16,0,16);

                MACHINE_MINIMAL_TICK_DELAY = number("Minimal tick delay based on upgrade installed",
                                "min_tick_rate", 1);
                MACHINE_MINIMAL_FE_COST = number("Minimal Energy cost based on upgrade installed",
                                "min_fe_cost", 5);
                MACHINE_MINIMAL_FLUID_COST = number("Minimal Fluid cost based on upgrade installed",
                                "min_mb_cost", 0);
                MACHINE_MAXIMAL_LUCK = number("Maximal Secondary Chance based on upgrade installed",
                                "max_luck", 100);

                DISABLE_MACHINE_DROP_WHEN_CORRUPTED = bool(
                                "Disable crash safer when an Industrial Machine is corrupted", "disable_ms_firewall");

                decor.complex(zStatic.Machines.electric_furnace);

                DISABLE_MACHINE_FURNACE_PROCESS_VANILLA = bool("Disable Vanilla Recipes",
                                "machine_furnace_disable_vanilla");

                MACHINE_FURNACE_PROCESS_VANILLA_FE_COST = number("Vanilla Recipe Base FE consumed every tick",
                                "machine_furnace_vanilla_fe_cost", BaseMachineBE.DEFAULT_FE_COST);

                DISABLE_MACHINE_FURNACE_VANILLA_TICK_REDUCER = bool("Disable Vanilla Recipe Tick Reducer",
                                "machine_furnace_vanilla_disable_tick_reducer");

                MACHINE_FURNACE_PROCESS_VANILLA_MIN_TICK_DELAY = number("Vanilla Recipe Mininal Tick Delay",
                                "machine_furnace_vanilla_min_tick_delay", BaseMachineBE.DEFAULT_TICK_DURATION);
                MACHINE_FURNACE_PROCESS_VANILLA_PERCENTUAGE_TICK_DELAY = number(
                                "Vanilla Recipe Tick Delay reduction of total Tick Delay",
                                "machine_furnace_vanilla_percentuage_tick_delay", 50, 0, 100);

                qCOMMON.pop();
        }

        private static void laser_stuff() {
                qCOMMON.comment("Laser-Stuff").push("4-laser");

                decor.complex(zStatic.Lazers.rotor);

                LASER_ROTOR_FE_GEN = number(zStatic.Config.FE_GEN,
                                "laser_rotor_fe_gen", 10_000);// 10k
                LASER_ROTOR_MAX_FE = number(zStatic.Config.FE_CAPACITY,
                                "laser_rotor_fe_max", 1_000_000);// 1M

                decor.complex(zStatic.Lazers.machine_gun);

                LASER_MACHINE_GUN_FE_COST = number(zStatic.Config.FE_GEN,
                                "laser_machine_fe_cost", 10);// 10

                LASER_MACHINE_GUN_MAX_FE = number(zStatic.Config.FE_CAPACITY,
                                "laser_machine_fe_max", 10_000);// 10k

                LASER_MACHINE_GUN_COLOR_RED = number("Laser track color Red tint",
                                "laser_track_red", 255, 0, 255);// 255

                LASER_MACHINE_GUN_COLOR_GREEN = number("Laser track color Green tint",
                                "laser_track_green", 0, 0, 255);// 0

                LASER_MACHINE_GUN_COLOR_BLUE = number("Laser track color Blue tint",
                                "laser_track_blue", 0, 0, 255);// 0

                LASER_MACHINE_GUN_MAX_LASER_LENGHT = number(zStatic.Config.FE_GEN,
                                "laser_track_lenght", 8);// 8

                decor.complex(zStatic.Lazers.sensor);

                LASER_SENSOR_TICK_DELAY = number("Tick delay to check when turn off",
                                "laser_sensor_delay", 5);// 5

                qCOMMON.pop();
        }

        private static void magic() {
                qCOMMON.comment("Magic-Blocks").push("5-magic");

                qCOMMON.pop();
        }

        private static void nuclear_stuff() {
                qCOMMON.comment("NuclearReactor").push("6-nuclear");

                decor.complex(zStatic.ReactorStuff.controller);
                REACTOR_CONTROLLER_MAX_FE = number(zStatic.Config.FE_CAPACITY,
                                "nuclear_controller_fe_max", 1_000_000);// 1M

                decor.complex(zStatic.ReactorStuff.cooler + "_base_cooling");

                COPPER_COOLER_BASE_COOLING = coolers.base("copper");// 0
                DIAMOND_COOLER_BASE_COOLING = coolers.base("diamond");// 0
                EMERALD_COOLER_BASE_COOLING = coolers.base("emerald");// 0
                ENDER_COOLER_BASE_COOLING = coolers.base("ender");// 0
                FROST_COOLER_BASE_COOLING = coolers.base("frost");// 0
                GLOWSTONE_COOLER_BASE_COOLING = coolers.base("glowstone");// 0
                GOLD_COOLER_BASE_COOLING = coolers.base("gold");// 0
                IRON_COOLER_BASE_COOLING = coolers.base("iron");// 0
                LAPIS_COOLER_BASE_COOLING = coolers.base("lapis");// 0
                NETHERITE_COOLER_BASE_COOLING = coolers.base("netherite");// 0
                QUARTZ_COOLER_BASE_COOLING = coolers.base("quartz");// 0
                REDSTONE_COOLER_BASE_COOLING = coolers.base("redstone");// 0
                SCULK_COOLER_BASE_COOLING = coolers.base("sculk");// 0
                SHADOW_COOLER_BASE_COOLING = coolers.base("shadow");// 0
                WATER_COOLER_BASE_COOLING = coolers.base("water");// 0

                decor.complex(zStatic.ReactorStuff.cooler + "_active_cooling");

                COPPER_COOLER_ACTIVE_COOLING = coolers.active("copper", 80);// -80
                DIAMOND_COOLER_ACTIVE_COOLING = coolers.active("diamond", 150);// -150
                EMERALD_COOLER_ACTIVE_COOLING = coolers.active("emerald", 160);// -160
                ENDER_COOLER_ACTIVE_COOLING = coolers.active("ender", 120);// -120
                FROST_COOLER_ACTIVE_COOLING = coolers.active("frost", 60);// -60
                GLOWSTONE_COOLER_ACTIVE_COOLING = coolers.active("glowstone", 130);// -130
                GOLD_COOLER_ACTIVE_COOLING = coolers.active("gold", 120);// -120
                IRON_COOLER_ACTIVE_COOLING = coolers.active("iron", 80);// -80
                LAPIS_COOLER_ACTIVE_COOLING = coolers.active("lapis", 120);// -120
                NETHERITE_COOLER_ACTIVE_COOLING = coolers.active("netherite", 140);// -140
                QUARTZ_COOLER_ACTIVE_COOLING = coolers.active("quartz", 90);// -90
                REDSTONE_COOLER_ACTIVE_COOLING = coolers.active("redstone", 90);// -90
                SCULK_COOLER_ACTIVE_COOLING = coolers.active("sculk", 120);// -120
                SHADOW_COOLER_ACTIVE_COOLING = coolers.active("shadow", 320);// -320
                WATER_COOLER_ACTIVE_COOLING = coolers.active("water", 60);// -60

                decor.complex(zStatic.ReactorStuff.moderator);

                SIMPLE_MODERATOR_FE_REDUCER = moderators.fe("simple", 1.1);// 1.1
                ADVANCED_MODERATOR_FE_REDUCER = moderators.fe("advanced", 1.65);// 1.65
                ELITE_MODERATOR_FE_REDUCER = moderators.fe("elite", 3);// 3

                SIMPLE_MODERATOR_HEAT_REDUCER = moderators.heat("simple", 1.3);// 1.3
                ADVANCED_MODERATOR_HEAT_REDUCER = moderators.heat("advanced", 2);// 2
                ELITE_MODERATOR_HEAT_REDUCER = moderators.heat("elite", 4.5);// 4.5

                qCOMMON.pop();
        }

        private static void pipe_blocks() {
                qCOMMON.comment("PipeBlocks").push("7-pipe");

                qCOMMON.pop();
        }

        private static void redstone() {
                qCOMMON.comment("Redstone").push("8-redstone");

                qCOMMON.pop();
        }

        private static void tools() {
                qCOMMON.comment("Tools").push("9-tools");

                decor.simple("Plant Mixtures");

                DISABLE_PLANT_MIXTURE_ON_NETHER_WART = bool("Disable plant mixture effect on nether warts",
                                "plant_mixture_netherwart");
                DISABLE_PLANT_MIXTURE_ON_SUGAR_CANES = bool("Disable plant mixture effect on sugar canes",
                                "plant_mixture_sugarcanes");
                DISABLE_PLANT_MIXTURE_ON_CACTUS = bool("Disable plant mixture effect on cactus",
                                "plant_mixture_cactus");
                DISABLE_PLANT_MIXTURE_ON_VINES = bool("Disable plant mixture effect on vines",
                                "plant_mixture_vines");
                DISABLE_PLANT_MIXTURE_ON_STEMS = bool("Disable plant mixture effect on plant stems",
                                "plant_mixture_stems");
                DISABLE_PLANT_MIXTURE_FLOWER_SPREADING = bool("Disable plant mixture flower spread effect",
                                "plant_mixture_flower_spreading");
                DISABLE_PLANT_MIXTURE_VANILLA_FALLBACK = bool("Disable plant mixture vanilla effect fallback",
                                "plant_mixture_fallback");

                qCOMMON.pop();
        }

        private static void survival() {
                qCOMMON.comment("Survival-Expansion").push("10-survival");

                decor.simple("Dryable Bricks");

                DISABLE_DRYABLE_BRICKS_DECREASE_STAGE_WHEN_WET = bool("Disable DryingBricks decrease stage when wet",
                                "dryable_bricks_decrease_stage_when_wet");

                decor.simple("Drying Racks");

                DISABLE_DRYING_RACK_STACK_NERFER = bool(
                                "Disable Drying Racks reduce the efficiency to prevent to dry at same speed one entire stack as a single item",
                                "disable_drying_rack_nerfer");
                DRYING_RACK_STACK_NERFER_RATE = numberFloat(
                                "Drying Racks Speed reducer to balance Stack Nerfer\nExample : 64 items \n 1x -> 64x time required \n 4x -> 64x/4 -> 16x time required \n 0.5x -> 64x/0.5 -> 128x time required ",
                                "drying_rack_nerfer_decreaser", 1,0.001);

                decor.simple("Foundry");

                 DISABLE_FOUNDRY_SPEED_BOOSTER = bool("Disable Foundry speed boost based on fluid amount", "disable_foundry_speed_boost");
                FOUNDRY_SPEED_BOOSTER_MULTIPLIER = numberFloat(
                                "Foundry Speed increaser based on fluid amount",
                                "foundry_speed_increaser", 0.25,0.001);

                qCOMMON.pop();
        }

        private static void rpg() {
                qCOMMON.comment("RolePlay-Stuff").push("11-rpg");

                decor.complex(zStatic.Blocks.healer);

                DISABLE_HEALER_HEAL_HP = bool("Disable healing effect",
                                "disable_healing_effect");
                DISABLE_HEALER_REMOVE_FIRE = bool("Disable fire extinguish effect",
                                "disable_fire_extinguish_effect");

                qCOMMON.pop();
        }

        private static void skyblock() {
                qCOMMON.comment("Skyblock-Utility").push("12-skyblock");

                DISABLE_CROOK_EVENT = bool("Disable Crook behavior on breaking leaves",
                                "crook_event");

                qCOMMON.pop();
        }

        private static void other() {
                qCOMMON.comment("Other").push("13-other");

                DISABLE_ENDER_EYE_RETURN_EVENT = bool("Disable End Portal Frame interaction to remove Eye of Ender",
                                "ender_eye_return");
                DISABLE_REMOVE_BABY_GROW_EVENT = bool("Disable Forever Young effect",
                                "git_commit_forever_young");
                DISABLE_READD_BABY_GROW_EVENT = bool("Disable the reverse of Forever Young effect",
                                "git_revert_forever_young");

                qCOMMON.pop();
        }

        private static BooleanValue bool(String c, String k, boolean b) {
                return qCOMMON
                                .comment(c)
                                .define(k, b);
        }

        /**
         * default = false
         */
        private static BooleanValue bool(String c, String k) {
                return bool(c, k, false);
        }

        private static IntValue number(String c, String k, int d, int mn, int mx) {
                return qCOMMON
                                .comment(c)
                                .defineInRange(k, d, mn, mx);
        }

        private static DoubleValue numberFloat(String c, String k, double d, double min, double max) {
                return qCOMMON
                                .comment(c)
                                .defineInRange(k, d, min, max);
        }

        /**
         * min = 0<br/>
         * <br/>
         * max = Double.MAX_VALUE
         */
        private static DoubleValue numberFloat(String c, String k, double d) {
                return numberFloat(c, k, d, 0, Integer.MAX_VALUE);
        }

        /**
         * max = Double.MAX_VALUE
         */
        @SuppressWarnings("unused")
        private static DoubleValue numberFloat(String c, String k, double d, double min) {
                return numberFloat(c, k, d, min, Integer.MAX_VALUE);
        }

        /**
         * min = 1<br/>
         * <br/>
         * max = Integer.MAX_VALUE
         */
        private static IntValue number(String c, String k, int d) {
                return number(c, k, d, 1, Integer.MAX_VALUE);
        }

        /**
         * max = Integer.MAX_VALUE
         */
        private static IntValue number(String c, String k, int d, int min) {
                return number(c, k, d, min, Integer.MAX_VALUE);
        }

        protected class coolers {
                protected static IntValue base(String i) {
                        return number(StringUtil.nameCapitalized(i) + " Cooler Base Cooling", i + "_base_cooling", 0,
                                        Integer.MIN_VALUE);
                }

                protected static IntValue active(String i, int v) {
                        return number(StringUtil.nameCapitalized(i) + " Cooler Active Cooling", i + "_active_cooling",
                                        -v,
                                        Integer.MIN_VALUE);
                }
        }

        protected class moderators {
                protected static DoubleValue fe(String i, double v) {
                        return numberFloat(StringUtil.nameCapitalized(i) + " Moderator FE Reducer", i + "_fe_reducer",
                                        v);
                }

                protected static DoubleValue heat(String i, double v) {
                        return numberFloat(StringUtil.nameCapitalized(i) + " Moderator Heat Reducer",
                                        i + "_heat_reducer", v);
                }
        }

        protected class decor {
                protected static void complex(String s) {
                        qCOMMON.comment(StringUtil.nameCapitalized(s));
                }

                protected static void simple(String s) {
                        qCOMMON.comment(s);
                }
        }

}
