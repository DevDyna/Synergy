package com.devdyna.synergy.events;

import com.devdyna.synergy.init.builder.PottedAzalea;
import com.devdyna.synergy.init.builder._core.pipeType;
import com.devdyna.synergy.init.types.zBlockTag;
import com.devdyna.synergy.init.types.zBlocks;
import com.devdyna.synergy.init.types.zItems;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.level.BlockEvent;

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

    @SubscribeEvent
    public static void PipeTypeBreak(BlockEvent.BreakEvent event) {
        if (event.getState().is(zBlockTag.PIPE_CONNECTORS)) {
            for (Direction face : pipeType.DIRECTIONS) {
                var offset = event.getLevel().getBlockState(event.getPos().relative(face));
                if (offset.is(zBlockTag.PIPE_CONNECTORS)) {
                    event.getLevel().setBlock(event.getPos().relative(face),
                            offset.setValue(pipeType.PROPRTIES.get(pipeType.DIRECTIONS.indexOf(face.getOpposite())),
                                    false),
                            Block.UPDATE_ALL);
                            
                }
            }
        }
    }
}
