package com.devdyna.synergy.client.gui;

import com.devdyna.synergy.api.beLogic.ItemStorageBlock;
import com.devdyna.synergy.utils.LogUtil;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.neoforged.neoforge.items.SlotItemHandler;

@SuppressWarnings("null")
public abstract class baseGui extends AbstractContainerMenu {

    protected baseGui(MenuType<?> menuType, int containerId) {
        super(menuType, containerId);
    }

    protected void addPlayerSlots(Inventory inventory) {
        addInventory(inventory);
        addHotbar(inventory);

    }

    protected void addInventory(Inventory inventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                addSlot(new Slot(inventory, l + i * 9 + 9, 8 + l * 18, 84 + i * 18));
            }
        }
    }

    protected void addHotbar(Inventory inventory) {
        for (int i = 0; i < 9; ++i) {
            addSlot(new Slot(inventory, i, 8 + i * 18, 142));
        }
    }

    protected void addMachineSlot(ItemStackHandler beSlot, int id, int x, int y) {
        addSlot(new SlotItemHandler(beSlot, id, x, y));
    }

    // TODO need to be verified
    protected void addMachineSlots(ItemStackHandler beSlot, int baseId,
            int x, int y, int count, int xOf, int yOf) {
        for (int i = 0; i < count; ++i)
            addSlot(new SlotItemHandler(beSlot, baseId + i, x + xOf * i, y + yOf * i));
    }

    // TODO need to be verified
    protected void addFilteredSlot(ItemStackHandler beSlot, int id, int x, int y, ItemStack filter) {
        var a = new SlotItemHandler(beSlot, id, x, y);
        a.mayPlace(filter);
        addSlot(a);
    }

    protected ItemStack shiftMoveStack(Player playerIn, int pIndex, int machineSlots) {

        Slot sourceSlot = slots.get(pIndex);
        if (sourceSlot == null || !sourceSlot.hasItem())
            return ItemStack.EMPTY;
        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copyOfSourceStack = sourceStack.copy();
        // Check if the slot clicked is one of the vanilla container slots
        if (pIndex < Inventory.INVENTORY_SIZE)
            // This is a vanilla container slot so merge the stack into the tile inventory
            if (!moveItemStackTo(sourceStack, Inventory.INVENTORY_SIZE, Inventory.INVENTORY_SIZE
                    + machineSlots, false))
                return ItemStack.EMPTY;

            else if (pIndex < Inventory.INVENTORY_SIZE + machineSlots)
                // This is a TE slot so merge the stack into the players inventory
                if (!moveItemStackTo(sourceStack, 0, Inventory.INVENTORY_SIZE,
                        false))
                    return ItemStack.EMPTY;

                else {
                    LogUtil.error("Invalid index:" + pIndex);
                    return ItemStack.EMPTY;
                }
        // If stack size == 0 (the entire stack was moved) set slot contents to null
        if (sourceStack.getCount() == 0)
            sourceSlot.set(ItemStack.EMPTY);
        else
            sourceSlot.setChanged();

        sourceSlot.onTake(playerIn, sourceStack);
        return copyOfSourceStack;
    }

    @Override
    public ItemStack quickMoveStack(Player playerIn, int pIndex) {
        return shiftMoveStack(playerIn, pIndex, ((ItemStorageBlock) getBlockEntity()).MachineSlots());
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(ContainerLevelAccess.create(getLevel(), getBlockEntity().getBlockPos()),
                player, getValidBlock());
    }

    public abstract Block getValidBlock();
    public abstract BlockEntity getBlockEntity();
    public abstract Level getLevel();

}
