package com.devdyna.synergy.events;

import com.devdyna.synergy.init.builder.PottedAzalea;
import com.devdyna.synergy.init.types.zBlocks;
import com.devdyna.synergy.init.types.zItems;

import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

public class blockEvents {
    @SubscribeEvent
    public static void potAction(PlayerInteractEvent.RightClickBlock event) {

        if (event.getLevel().getBlockState(event.getPos()).is(Blocks.FLOWER_POT)
                && event.getItemStack().is(zItems.AZALEA_SEEDS.get())) {
            event.getLevel().setBlockAndUpdate(event.getPos(),
                    zBlocks.AZALEA.get().defaultBlockState().setValue(PottedAzalea.AGE, 0));
            event.getEntity().swing(event.getHand());
            if (!event.getEntity().isCreative())
                event.getItemStack().shrink(1);
        }
    }
}
