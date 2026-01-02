package com.devdyna.synergy.api.beLogic;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public interface NoGuiStorage extends ItemStorageBlock {

    default InteractionResult itemUseOn(Player player, Level level, BlockPos pos, InteractionHand hand) {

        var stack = player.getItemInHand(hand);

        if (hand.equals(InteractionHand.MAIN_HAND) && level != null
                && !player.isShiftKeyDown()) {
            var be = level.getBlockEntity(pos);

            if (be instanceof NoGuiStorage storage) {

                player.swing(hand);

                if (level.isClientSide())
                    return InteractionResult.FAIL;

                // If holding item -> try insert
                if (!stack.isEmpty()) {

                    if (canInsert(0, stack)) {
                        var insered = storage.insert(0, stack);
                        stack.shrink(insered);

                    } else
                        swap(0, stack);

                    player.setItemInHand(hand, stack);

                    return InteractionResult.SUCCESS_SERVER;
                } else {
                    // If empty hand -> extract one item
                    ItemStack extracted = storage.getStackInSlot(0);
                    if (!extracted.isEmpty()) {
                        player.setItemInHand(hand, stack);
                        return InteractionResult.CONSUME;
                    }
                }

                // storage.setChanged();
            }
        }
        return InteractionResult.FAIL;
    }

    // abstract ItemStack extractItem();

    // abstract ItemStack insertItem(ItemStack stack);

}
