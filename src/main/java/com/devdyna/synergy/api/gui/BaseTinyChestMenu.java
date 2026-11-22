package com.devdyna.synergy.api.gui;

import com.devdyna.synergy.api.beLogic.ItemStorageBlock;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;

public abstract class BaseTinyChestMenu extends BaseMenu {

    public ItemStorageBlock blockEntity;
    public Level level;

    protected BaseTinyChestMenu(MenuType<?> menuType, int containerId, BlockEntity be) {
        super(menuType, containerId, be);
        this.blockEntity = (ItemStorageBlock) be;
    }

    @Override
    public Block[] getValidBlock() {
        return new Block[] { getChestType() };
    }

    @Override
    public BlockEntity getBlockEntity() {
        return (BlockEntity) blockEntity;
    }

    public abstract Block getChestType();

    @Override
    public ItemStack quickMoveStack(Player player, int index) {

        if (index < 0 || index >= this.slots.size())
            return ItemStack.EMPTY;

        var slot = this.slots.get(index);

        if (!slot.hasItem())
            return ItemStack.EMPTY;

        var item = slot.getItem();

        // player inv -> <?>
        if (index >= 0 && index <= 35) {
            var copy = item.copy();

            // player -> machine
            for (var i = 0; i < getSlots(); i++)
                if (moveItemStackTo(item, 36 + i, 36 + 1 + i, true)) {
                    slot.onTake(player, item);
                    return copy;
                }

            // inv -> hotbar
            if (index >= 0 && index <= 26) {
                if (moveItemStackTo(item, 27, 35 + 1, true)) {
                    slot.onTake(player, item);
                    return copy;
                }
            }

            // hotbar -> inv
            if (index >= 27 && index <= 35) {
                if (moveItemStackTo(item, 0, 26 + 1, true)) {
                    slot.onTake(player, item);
                    return copy;
                }
            }
        }

        // machine -> <?>
        if (index >= 36)
            return moveToInventory(item, slot, player, 0);

        return ItemStack.EMPTY;

    }

    public abstract int getSlots();

}
