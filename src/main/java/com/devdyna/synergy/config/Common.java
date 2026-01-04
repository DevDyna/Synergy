package com.devdyna.synergy.config;

import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.api.machine.UpgradeSlots;
import com.devdyna.synergy.api.utils.ModAddonUtil;

import net.neoforged.fml.ModContainer;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.ModConfigSpec.*;

public class Common {

    private static final ModConfigSpec.Builder qCOMMON = new ModConfigSpec.Builder();

    // grr grr
    public static BooleanValue DISABLE_ITEM_USE_RECIPE;
    public static BooleanValue DISABLE_CROOK_EVENT;
    public static BooleanValue DISABLE_HARVESTABLE_ACTION;
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
    public static BooleanValue SOLAR_PANEL_CHECK_SEE_SKY;// false
    public static BooleanValue SOLAR_PANEL_DAYTIME;// false

    public static IntValue SPRINKLER_FE_COST;// 25
    public static IntValue SPRINKLER_MAX_FE;// 10k

    // ic4
    public static IntValue MACHINE_MAX_FE;

    public static IntValue MACHINE_MAX_ENERGY_UPGRADES;
    public static IntValue MACHINE_MAX_SPEED_UPGRADES;

    public static IntValue MACHINE_FURNACE_PROCESS_VANILLA_FE_COST;// DEFAULT
    public static BooleanValue DISABLE_MACHINE_FURNACE_PROCESS_VANILLA;// false
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
    public static DoubleValue SIMPLE_MODERATOR_HEAT_REDUCER;// 1.3
    public static DoubleValue ADVANCED_MODERATOR_FE_REDUCER;// 1.65
    public static DoubleValue ADVANCED_MODERATOR_HEAT_REDUCER;// 2
    public static DoubleValue ELITE_MODERATOR_FE_REDUCER;// 3
    public static DoubleValue ELITE_MODERATOR_HEAT_REDUCER;// 4.5

    // pimpa

    public static IntValue ENERGY_NODE_MAX_FE_RATE;// 256

    public static IntValue FLUID_PROVIDER_BASE_SPEED;// 20 (min)
    public static IntValue ITEM_PROVIDER_BASE_SPEED;// 20 (min)

    // redrock

    // toolz

    public static IntValue GREEN_BATTERY_CAPACITY;// 1k
    public static IntValue BLUE_BATTERY_CAPACITY;// 10k
    public static IntValue RED_BATTERY_CAPACITY;// 100k

    public static BooleanValue DISABLE_PLANT_MIXTURE_ON_NETHE_RWART;// false
    public static BooleanValue DISABLE_PLANT_MIXTURE_ON_SUGAR_CANES;// false
    public static BooleanValue DISABLE_PLANT_MIXTURE_ON_CACTUS;// false
    public static BooleanValue DISABLE_PLANT_MIXTURE_ON_VINES;// false
    public static BooleanValue DISABLE_PLANT_MIXTURE_ON_STEMS;// false
    public static BooleanValue DISABLE_PLANT_MIXTURE_FLOWER_SPREADING;// false
    public static BooleanValue DISABLE_PLANT_MIXTURE_VANILLA_FALLBACK;// false (super.useOn)

    // survival

    public static BooleanValue DISABLE_DRYABLE_BRICKS_DECREASE_STAGE_WHEN_WET;// false

    // rpg

    public static BooleanValue DISABLE_HEALER_HEAL_HP;// false
    public static BooleanValue DISABLE_HEALER_REMOVE_FIRE;// false

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
        other();

        c.registerConfig(ModConfig.Type.COMMON, qCOMMON.build());
    }

    private static void agriculture() {
        qCOMMON.comment("Automation").push("1-automation");

        DISABLE_ITEM_USE_RECIPE = qCOMMON
                .comment("Disable Toggleable Item-Use recipes")
                .define("optional_item_use_recipe", false);

        DISABLE_CROOK_EVENT = qCOMMON
                .comment("Disable Crook behavior on breaking leaves")
                .define("crook_event", false);

        DISABLE_HARVESTABLE_ACTION = qCOMMON
                .comment("Disable player right-click on crops to collect")
                .define("harvestable_action", ModAddonUtil.checkMod(zStatic.Mods.FarmersDelight));

        qCOMMON.pop();
    }

    private static void automation() {
        qCOMMON.comment("Automation").push("2-automation");

        qCOMMON.pop();
    }

    private static void industrial_machines() {
        qCOMMON.comment("Industrial-Machines").push("3-industrialmachines");

        MACHINE_MAX_FE = qCOMMON
                .comment("Base max energy stored")
                .defineInRange("base_machine_max_fe", 10_000, 256, Integer.MAX_VALUE);

        MACHINE_MAX_ENERGY_UPGRADES = qCOMMON
                .comment("Max Energy Upgrades usable foreach machine")
                .defineInRange("max_energy_upgrades", 4, 0, UpgradeSlots.MAX_UPGRADE_SLOTS);

        MACHINE_MAX_SPEED_UPGRADES = qCOMMON
                .comment("Max Speed Upgrades usable foreach machine")
                .defineInRange("max_speed_upgrades", 2, 0, UpgradeSlots.MAX_UPGRADE_SLOTS);

        qCOMMON.pop();
    }

    private static void laser_stuff() {
        qCOMMON.comment("Laser-Stuff").push("4-laser");

        qCOMMON.pop();
    }

    private static void magic() {
        qCOMMON.comment("Magic-Blocks").push("5-magic");

        qCOMMON.pop();
    }

    private static void nuclear_stuff() {
        qCOMMON.comment("NuclearReactor").push("6-nuclear");

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

        qCOMMON.pop();
    }

    private static void survival() {
        qCOMMON.comment("Survival-Expansion").push("10-survival");

        qCOMMON.pop();
    }

    private static void rpg() {
        qCOMMON.comment("RolePlay-Stuff").push("11-rpg");

        qCOMMON.pop();
    }

    private static void other() {
        qCOMMON.comment("Other").push("12-other");

        qCOMMON.pop();
    }

}
