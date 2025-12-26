package com.devdyna.synergy.api.beLogic;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.items.ItemHandlerHelper;

public interface NoGuiStorage {

    default InteractionResult itemUseOn(Player player, Level level, BlockPos pos, InteractionHand hand) {

        var stack = player.getItemInHand(hand);

        if (hand.equals(InteractionHand.MAIN_HAND) && level != null
                && !player.isShiftKeyDown()) {
            var be = level.getBlockEntity(pos);

            if (be instanceof NoGuiStorage storage) {

                player.swing(hand);

                // If holding item -> try insert
                if (!stack.isEmpty()) {
                    if (!level.isClientSide()) {
                        ItemStack remaining = storage.insertItem(stack);
                        player.setItemInHand(hand, remaining);
                    }

                    return InteractionResult.SUCCESS_SERVER;
                } else {
                    // If empty hand -> extract one item
                    ItemStack extracted = storage.extractItem();
                    if (!extracted.isEmpty() && !level.isClientSide()) {
                        ItemHandlerHelper.giveItemToPlayer(player, extracted);
                        return InteractionResult.CONSUME;
                    }
                }
                

                // storage.setChanged();
            }
        }
        return InteractionResult.FAIL;
    }

    abstract ItemStack extractItem();

    abstract ItemStack insertItem(ItemStack stack);

}
