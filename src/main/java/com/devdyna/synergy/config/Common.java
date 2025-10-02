package com.devdyna.synergy.config;


import net.neoforged.fml.ModContainer;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.ModConfigSpec.*;

public class Common {

    //TODO NYC

    private static final ModConfigSpec.Builder qCOMMON = new ModConfigSpec.Builder();

    public static BooleanValue DISABLE_ITEM_USE_RECIPE;

    public static BooleanValue TOGGLE_CROOK_EVENT;

    public static void register(ModContainer c) {
        reg();
        c.registerConfig(ModConfig.Type.COMMON, qCOMMON.build());
    }

    private static void reg() {
        events();
    }

    private static void events() {
        qCOMMON.comment("Events").push("1-events");

        DISABLE_ITEM_USE_RECIPE = qCOMMON
                .comment("Enable/Disable Toggleable Item-Use recipes")
                .define("item_use_recipe_toggleable", false);

        TOGGLE_CROOK_EVENT = qCOMMON
                .comment("Enable/Disable Crook behavior on breaking leaves")
                .define("crook_event_status", false);

        qCOMMON.pop();
    }

}
