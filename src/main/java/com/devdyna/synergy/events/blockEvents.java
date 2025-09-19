package com.devdyna.synergy.events;

import com.devdyna.synergy.init.builder.crops.cultivated.azalea;
import com.devdyna.synergy.init.types.zBlocks;
import com.devdyna.synergy.init.types.zItems;

import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

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



    
}
