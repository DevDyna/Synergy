package com.devdyna.synergy.client.gui;

import com.devdyna.synergy.api.beLogic.ItemStorageBlock;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
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
public abstract class BaseMenu extends AbstractContainerMenu {

    public int MACHINE_SLOT;

    protected BaseMenu(MenuType<?> menuType, int containerId, BlockEntity blockEntity) {
        super(menuType, containerId);
        if (blockEntity instanceof ItemStorageBlock be)
            this.MACHINE_SLOT = be.MachineSlots();
        else
            this.MACHINE_SLOT = 0;
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

    //TODO DONT WORK PROPRERLY
    @Override
    public ItemStack quickMoveStack(Player playerIn, int index) {
      ItemStack itemstack = ItemStack.EMPTY;
      Slot slot = (Slot)this.slots.get(index);
      if (slot != null && slot.hasItem()) {
         ItemStack itemstack1 = slot.getItem();
         itemstack = itemstack1.copy();
         if (index < this.MACHINE_SLOT) {
            if (!this.moveItemStackTo(itemstack1, this.MACHINE_SLOT, this.slots.size(), true)) {
               return ItemStack.EMPTY;
            }
         } else if (!this.moveItemStackTo(itemstack1, 0, this.MACHINE_SLOT, false)) {
            return ItemStack.EMPTY;
         }

         if (itemstack1.isEmpty()) {
            slot.setByPlayer(ItemStack.EMPTY);
         } else {
            slot.setChanged();
         }
      }

      return itemstack;
    }

    @Override
    public boolean stillValid(Player player) {
        return ContainerLevelAccess.create(getLevel(), getBlockEntity().getBlockPos())
                .evaluate((lvl, pos) -> {
                    for (Block b : getValidBlock()) {
                        if (lvl.getBlockState(pos).is(b)) {
                            return player.canInteractWithBlock(pos, 4.0);
                        }
                    }
                    return false;
                }, true);
    }

    public abstract Block[] getValidBlock();

    public abstract BlockEntity getBlockEntity();

    public abstract Level getLevel();

}
