package com.devdyna.synergy;

import com.devdyna.synergy.common.events.*;
import com.devdyna.synergy.init.builder.survival.PlaceableBrickEvent;

import net.neoforged.neoforge.common.NeoForge;

public class GameEvents {

    public static void register() {
        NeoForge.EVENT_BUS.register(CrookBreakBlocks.class);
        NeoForge.EVENT_BUS.register(ItemUseRecipeEvent.class);
        NeoForge.EVENT_BUS.register(PlaceableBrickEvent.class);
        NeoForge.EVENT_BUS.register(ItemTooltipEvents.class);
        NeoForge.EVENT_BUS.register(EntityInteractionEvent.class);
        NeoForge.EVENT_BUS.register(VanillaHarvestable.class);
        NeoForge.EVENT_BUS.register(EnderEyeReturn.class);
    }

}
