package com.devdyna.synergy.common.events;

import com.devdyna.synergy.Common;
import com.devdyna.synergy.api.utils.IOUtils;
import com.devdyna.synergy.api.utils.LevelUtil;
import com.devdyna.synergy.datagen.server.DataAnyLoot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.WeatheringCopper.WeatherState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.event.level.BlockEvent;

public class PatinaDropEvent {

    @SubscribeEvent
    public static void patinaDropEvent(BlockEvent.BlockToolModificationEvent event) {

        if (Common.DISABLE_PATINA_DROP_EVENT.get())
            return;

        if (event.getItemAbility() != ItemAbilities.AXE_SCRAPE)
            return;

        if (!(event.getLevel() instanceof Level level))
            return;

        if (level.isClientSide())
            return;

        if (!(event.getState().getBlock() instanceof WeatheringCopper oxidize))
            return;

        if (oxidize.getAge() == WeatherState.UNAFFECTED)
            return;

        // useful when changed the loot table to custom
        for (ItemStack item : IOUtils.unifyDrops(LevelUtil.getLootTableItems(level, DataAnyLoot.PATINA,
                event.getPlayer() != null ? event.getPlayer().getLuck() : 0)))
            Block.popResource(level, event.getPos().relative(event.getContext().getClickedFace()), item);

    }
}
