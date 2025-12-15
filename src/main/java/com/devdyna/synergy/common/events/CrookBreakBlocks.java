package com.devdyna.synergy.common.events;

import com.devdyna.synergy.config.Common;
import com.devdyna.synergy.init.types.*;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.level.BlockEvent;

public class CrookBreakBlocks {
    @SubscribeEvent
    public static void crookBreakLeaves(BlockEvent.BreakEvent event) {
        var player = event.getPlayer();
        var item = player.getMainHandItem();
        var block = event.getState();
        var level = event.getLevel();
        var pos = event.getPos();

        if (block.is(zBlockTag.LEAVES) && item.is(zItems.WOODEN_CROOK) && !player.isCreative() && !Common.DISABLE_CROOK_EVENT.get())
            for (int i = 0; i < 10; i++)
                Block.getDrops(block, (ServerLevel) level, pos, null)
                        .forEach(s -> Block.popResource((Level) level, pos, s));

    }
}
