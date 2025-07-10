package com.devdyna.synergy.events;

import com.devdyna.synergy.init.builder.crops.cultivated.azalea;
import com.devdyna.synergy.init.types.zBlockTag;
import com.devdyna.synergy.init.types.zBlocks;
import com.devdyna.synergy.init.types.zItems;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.level.BlockEvent;

public class blockEvents {
    @SubscribeEvent
    public static void potAction(PlayerInteractEvent.RightClickBlock event) {
        var pos = event.getPos();
        var level = event.getLevel();
        var player = event.getEntity();
        var item = event.getItemStack();
        var hand = event.getHand();

        if (level.getBlockState(pos).is(Blocks.FLOWER_POT)
                && item.is(zItems.AZALEA_SEEDS.get())) {
            level.setBlockAndUpdate(pos,
                    zBlocks.AZALEA.get().defaultBlockState().setValue(azalea.AGE, 0));
            player.swing(hand);
            if (!player.isCreative())
                item.shrink(1);
        }
    }

    @SubscribeEvent
    public static void cookBreakLeave(BlockEvent.BreakEvent event) {
        var player = event.getPlayer();
        var item = player.getMainHandItem();
        var block = event.getState();
        var level = event.getLevel();
        var pos = event.getPos();

        if (block.is(zBlockTag.LEAVES) && item.is(zItems.WOODEN_CROOK) && !player.isCreative())
            for (int i = 0; i < 10; i++) 
                Block.getDrops(block, (ServerLevel) level, pos, null)
                        .forEach(s -> Block.popResource((Level) level, pos, s));
            

    }

}
