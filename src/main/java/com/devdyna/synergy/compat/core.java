package com.devdyna.synergy.compat;

import static com.devdyna.synergy.Main.ID;

import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.utils.LogUtil;
import com.devdyna.synergy.utils.StringUtil;

import guideme.Guide;
import net.minecraft.resources.ResourceLocation;

public class core {

    public static void registerCompat() {
        LogUtil.decor(20);
        LogUtil.info(StringUtil.nameCapitalized(ID)+" Compatibility Checker started");
        LogUtil.decor(20);
        core.createGuide();
        LogUtil.decor(20);
    }

    private static void createGuide() {

        LogUtil.info("GuideMe"
                + (zStatic.checkMods.GuideMe ? " found" : " not found"));
        if (zStatic.checkMods.GuideMe)
            Guide.builder(ResourceLocation.parse(ID + ":guide")).build();

    }
}