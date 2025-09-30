package com.devdyna.synergy.compat;

import static com.devdyna.synergy.Main.ID;

import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.utils.LogUtil;
import com.devdyna.synergy.utils.ModAddonUtil;
import com.devdyna.synergy.utils.StringUtil;

import guideme.Guide;
import net.minecraft.resources.ResourceLocation;

public class core {

    public static void registerCompat() {
        LogUtil.decor(20);
        LogUtil.info(StringUtil.nameCapitalized(ID) + " Compatibility Checker started");
        LogUtil.decor(20);

        LogUtil.info("GuideMe"
                + (!ModAddonUtil.checkMod(zStatic.Mods.GuideMe) ? " " : " not") + " found");
        if (!ModAddonUtil.checkMod(zStatic.Mods.GuideMe))
            Guide.builder(ResourceLocation.parse(ID + ":guide")).build();

        LogUtil.info("FarmersDelight"
                + (!ModAddonUtil.checkMod(zStatic.Mods.FarmersDelight) ? " " : " not") + " found");

        LogUtil.info("Mekanism"
                + (!ModAddonUtil.checkMod(zStatic.Mods.Mekanism) ? " " : " not") + " found");

        LogUtil.decor(20);
    }
}