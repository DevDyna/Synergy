package com.devdyna.synergy.api.utils;

import net.neoforged.fml.ModList;

public class ModAddonUtil {
    public static boolean checkMod(String s) {
        return ModList.get().isLoaded(s);
    }
}
