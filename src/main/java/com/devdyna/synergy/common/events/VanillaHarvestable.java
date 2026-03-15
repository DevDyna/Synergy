package com.devdyna.synergy.common.events;

import java.util.*;

import com.devdyna.synergy.api.blockfactories.plants.Harvestable;
import com.devdyna.synergy.api.harvester.VanillaPlants;

import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.items.ItemHandlerHelper;

public class VanillaHarvestable {
    @SubscribeEvent
    public static void harvestVanillaCrops(PlayerInteractEvent.RightClickBlock event) {
        var pos = event.getPos();
        var level = event.getLevel();
        var player = event.getEntity();
        var state = level.getBlockState(pos);

        if (state.getBlock() instanceof Harvestable)
            return; // skip harvestable plants

        List<ItemStack> check = VanillaPlants.checkReplant(level, pos);

        if (check == null)
            return; // no valid plants found

        check.forEach(i -> ItemHandlerHelper.giveItemToPlayer(player, i));

    }
}
