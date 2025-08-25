package com.devdyna.synergy.client.gui;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.neoforged.neoforge.items.SlotItemHandler;

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

}
