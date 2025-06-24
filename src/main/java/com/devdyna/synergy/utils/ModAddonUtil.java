package com.devdyna.synergy.utils;

import net.neoforged.fml.ModList;

public class ModAddonUtil {
    public static boolean checkMod(String s) {
        return ModList.get().isLoaded(s);
    }
}
