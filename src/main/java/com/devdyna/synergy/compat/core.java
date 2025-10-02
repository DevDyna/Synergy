package com.devdyna.synergy.compat;

import static com.devdyna.synergy.Main.ID;

import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.utils.*;

import guideme.Guide;
import net.minecraft.resources.ResourceLocation;

public class core {

    public static void registerCompat() {
        LogUtil.decor(20);
        LogUtil.info(StringUtil.nameCapitalized(ID) + " Compatibility Checker started");
        LogUtil.decor(20);

        if (ModAddonUtil.checkMod(zStatic.Mods.GuideMe))
            Guide.builder(ResourceLocation.parse(ID + ":guide")).build();

        compatInfo(zStatic.Mods.GuideMe, "GuideMe");
        compatInfo(zStatic.Mods.Patchouli, "Patchouli");
        compatInfo(zStatic.Mods.FarmersDelight, "FarmersDelight");
        compatInfo(zStatic.Mods.Mekanism, "Mekanism");
        compatInfo(zStatic.Mods.ImmersiveEngineering, "ImmersiveEngineering");

        LogUtil.decor(20);
    }

    private static final void compatInfo(String id, String displayName) {
        LogUtil.info(displayName
                + (ModAddonUtil.checkMod(id) ? " " : " not") + " found");
    }
}