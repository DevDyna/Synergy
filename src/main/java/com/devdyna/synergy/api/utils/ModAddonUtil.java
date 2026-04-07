package com.devdyna.synergy.api.utils;

import net.neoforged.fml.ModList;
import net.neoforged.neoforge.common.conditions.ICondition;
import net.neoforged.neoforge.common.conditions.ModLoadedCondition;

public class ModAddonUtil {
    public static boolean checkMod(String s) {
        return ModList.get().isLoaded(s);
    }

    public static ICondition hasMod(String s) {
        return new ModLoadedCondition(s);
    }
}
