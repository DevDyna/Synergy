package com.devdyna.synergy.events;

import com.devdyna.synergy.init.builder.quern.QuernBE;
import com.devdyna.synergy.init.builder.urn.UrnBE;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.items.ItemHandlerHelper;

public class BlockInjection {

    // TODO unify events

    @SubscribeEvent
    public static void urnInjection(PlayerInteractEvent.RightClickBlock event) {
        var level = event.getLevel();
        var player = event.getEntity();
        var pos = event.getPos();
        var hand = event.getHand();
        var stack = event.getItemStack();

        if (hand.equals(InteractionHand.MAIN_HAND) && level != null && !level.isClientSide
                && !player.isShiftKeyDown()) {
            var be = level.getBlockEntity(pos);

            if (be instanceof UrnBE urn) {

                // If holding item -> try insert
                if (!stack.isEmpty()) {
                    ItemStack remaining = urn.insertItem(stack);
                    player.setItemInHand(hand, remaining);
                    event.isCanceled();
                    event.setCanceled(true);
                } else {
                    // If empty hand -> extract one item
                    ItemStack extracted = urn.extractItem();
                    if (!extracted.isEmpty()) {
                        ItemHandlerHelper.giveItemToPlayer(player, extracted);
                    }
                }
                player.swing(hand);

                urn.setChanged();
            }
        }
    }

    @SubscribeEvent
    public static void quernInjection(PlayerInteractEvent.RightClickBlock event) {
        var level = event.getLevel();
        var player = event.getEntity();
        var pos = event.getPos();
        var hand = event.getHand();
        var stack = event.getItemStack();

        if (hand.equals(InteractionHand.MAIN_HAND) && level != null && !level.isClientSide
                && !player.isShiftKeyDown()) {
            var be = level.getBlockEntity(pos);

            if (be instanceof QuernBE quern) {

                // If holding item -> try insert
                if (!stack.isEmpty()) {
                    ItemStack remaining = quern.insertItem(stack);
                    player.setItemInHand(hand, remaining);
                    event.isCanceled();
                    event.setCanceled(true);
                } else {
                    // If empty hand -> extract one item
                    ItemStack extracted = quern.extractItem();
                    if (!extracted.isEmpty()) {
                        ItemHandlerHelper.giveItemToPlayer(player, extracted);
                    }
                }
                player.swing(hand);

                quern.setChanged();
            }
        }
    }
}
