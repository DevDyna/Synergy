package com.devdyna.synergy.config;


import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.api.utils.ModAddonUtil;

import net.neoforged.fml.ModContainer;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.ModConfigSpec.*;

public class Common {

    private static final ModConfigSpec.Builder qCOMMON = new ModConfigSpec.Builder();

    public static BooleanValue DISABLE_ITEM_USE_RECIPE;

    public static BooleanValue DISABLE_CROOK_EVENT;

    public static BooleanValue DISABLE_HARVESTABLE_ACTION;

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

}
