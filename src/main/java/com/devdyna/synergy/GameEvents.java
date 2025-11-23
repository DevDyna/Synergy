package com.devdyna.synergy;

import com.devdyna.synergy.events.*;

import net.neoforged.neoforge.common.NeoForge;

public class GameEvents {

    public static void register() {

        NeoForge.EVENT_BUS.register(CrookBreakBlocks.class);
        NeoForge.EVENT_BUS.register(ItemUseRecipeEvent.class);
        NeoForge.EVENT_BUS.register(ClayBrickPlacement.class);
        NeoForge.EVENT_BUS.register(ItemTooltipEvents.class);
    }

}
