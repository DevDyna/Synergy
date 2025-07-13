package com.devdyna.synergy.config;

import net.neoforged.fml.ModContainer;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.ModConfigSpec;

public class Startup {
    private static final ModConfigSpec.Builder qINIT = new ModConfigSpec.Builder();

    // TODO NYC

    public static void register(ModContainer c) {
        c.registerConfig(ModConfig.Type.STARTUP, qINIT.build());
    }
}
