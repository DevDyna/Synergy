package com.devdyna.synergy.common.events;

import com.devdyna.synergy.api.utils.x;
import com.devdyna.synergy.config.Common;

import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.*;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.items.ItemHandlerHelper;

public class EnderEyeReturn {
    @SubscribeEvent
    public static void endFrameEncaseEvent(PlayerInteractEvent.RightClickBlock event) {
        var pos = event.getPos();
        var level = event.getLevel();
        var player = event.getEntity();
        var state = level.getBlockState(pos);

        if (state.is(Blocks.END_PORTAL_FRAME) && state.getValue(EndPortalFrameBlock.HAS_EYE)
                && !Common.DISABLE_ENDER_EYE_RETURN_EVENT.get()) {
            ItemHandlerHelper.giveItemToPlayer(player, x.item(Items.ENDER_EYE));
            level.setBlockAndUpdate(pos, state.setValue(EndPortalFrameBlock.HAS_EYE, false));
        }

    }
}
