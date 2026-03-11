package com.devdyna.synergy.api.beLogic;

import java.util.ArrayList;

import com.devdyna.synergy.api.utils.ArrayUtils;
import com.devdyna.synergy.api.utils.DirectionUtil;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.items.ItemHandlerHelper;

public interface NoGuiStorage {

    default ItemInteractionResult itemUseOn(Player player, Level level, BlockPos pos, InteractionHand hand) {

        var stack = player.getItemInHand(hand);

        if (hand.equals(InteractionHand.MAIN_HAND) && level != null
                && !player.isShiftKeyDown()) {
            var be = level.getBlockEntity(pos);

            if (be instanceof NoGuiStorage storage) {

                player.swing(hand);

                // If holding item -> try insert
                if (!stack.isEmpty() && !extractOnly()) {
                    if (!level.isClientSide) {
                        ItemStack remaining = storage.insertItem(stack);
                        player.setItemInHand(hand, remaining);
                    }
                    setChanged();
                    return ItemInteractionResult.sidedSuccess(level.isClientSide);
                }

                if (stack.isEmpty() && !insertOnly()) {
                    // If empty hand -> extract one item
                    ItemStack extracted = storage.extractItem();
                    if (!extracted.isEmpty() && !level.isClientSide) {
                        ItemHandlerHelper.giveItemToPlayer(player, extracted);
                        setChanged();
                        return ItemInteractionResult.CONSUME;
                    }
                }
            }
        }
        return ItemInteractionResult.FAIL;
    }

    abstract ItemStack extractItem();

    abstract ItemStack insertItem(ItemStack stack);

    default boolean extractOnly() {
        return false;
    }

    default boolean insertOnly() {
        return false;
    }

    abstract void setChanged();

    default void dropInWorldResult(ItemStack output, Level level, BlockPos pos) {

        var validDir = new ArrayList<Direction>();

        for (Direction dir : ArrayUtils.concat(DirectionUtil.HORIZONTAL, new Direction[] { Direction.UP })) {

            var relate = pos.relative(dir);

            if (!level.getBlockState(relate).isSolidRender(level, relate)) {
                validDir.add(dir);
            }
        }

        spawnItemEntity(level,
                !validDir.isEmpty()
                        ? pos.relative(
                                validDir.get(
                                        level.random.nextInt(validDir.size())))
                        : pos,
                output);

    }

    private void spawnItemEntity(Level l, BlockPos p, ItemStack s) {
        l.addFreshEntity(new ItemEntity(
                l,
                p.getX() + 0.5,
                p.getY() + 0.5,
                p.getZ() + 0.5,
                s.copy()));
    }
}
