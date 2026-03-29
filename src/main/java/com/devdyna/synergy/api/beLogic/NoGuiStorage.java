package com.devdyna.synergy.api.beLogic;

import java.util.ArrayList;

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

                // If holding item -> try insert
                if (!stack.isEmpty() && !extractOnly() && insertFilter(stack) && requiredToInsert(stack)) {

                    player.swing(hand);
                    if (!level.isClientSide()) {
                        ItemStack remaining = storage.insertItem(stack);
                        player.setItemInHand(hand, remaining);
                    }
                    setChanged();
                    return ItemInteractionResult.sidedSuccess(level.isClientSide());
                }

                if (stack.isEmpty() && !insertOnly()) {

                    player.swing(hand);
                    // If empty hand -> extract one item
                    ItemStack extracted = storage.extractItem();
                    if (!extracted.isEmpty() && !level.isClientSide()) {
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

    /**
     * insert only specific itemstacks
     */
    default boolean insertFilter(ItemStack i) {
        return true;
    }

    /**
     * insert only when holding a specific itemstack<br/>
     * <br/>
     * NOTE : it will consume the item used!
     */
    default boolean requiredToInsert(ItemStack i) {
        return true;
    }

    default boolean insertOnly() {
        return false;
    }

    abstract void setChanged();

    default void dropInWorldResult(ItemStack output, Level level, BlockPos pos) {

        var validDir = new ArrayList<Direction>();

        for (Direction dir : DirectionUtil.HORIZONTAL)
            if (!canPlaceItem(level, pos, dir))
                validDir.add(dir);

        var outputPos = pos;

        if (!validDir.isEmpty())
            outputPos = pos.relative(validDir.get(level.random.nextInt(validDir.size())));
        else if (canPlaceItem(level, pos, Direction.UP))
            outputPos = pos.relative(Direction.UP);

        spawnItemEntity(level, outputPos, output);

    }

    private boolean canPlaceItem(Level l, BlockPos p, Direction d) {
        return !l.getBlockState(p.relative(d)).isSolidRender(l, p.relative(d));
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
