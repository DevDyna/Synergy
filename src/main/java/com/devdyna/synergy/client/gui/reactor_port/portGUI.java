package com.devdyna.synergy.client.gui.reactor_port;

import com.devdyna.synergy.api.beLogic.ItemStorageBlock;
import com.devdyna.synergy.client.gui.baseGui;
import com.devdyna.synergy.init.types.zBlocks;
import com.devdyna.synergy.init.types.zContainer;
import com.devdyna.synergy.utils.LogUtil;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

@SuppressWarnings("null")
public class portGUI extends baseGui {
    public final ItemStorageBlock blockEntity;
    private final Level level;

    public portGUI(int containerId, Inventory inv, FriendlyByteBuf extraData) {
        this(containerId, inv, inv.player.level().getBlockEntity(extraData.readBlockPos()));
    }

    public portGUI(int containerId, Inventory inv, BlockEntity blockEntity) {
        super(zContainer.REACTORPORT_GUI.get(), containerId);
        this.blockEntity = ((ItemStorageBlock) blockEntity);
        inv.player.getInventory();
        this.level = inv.player.level();
        addPlayerSlots(inv);
        addMachineSlot(this.blockEntity.getStorage(), 0, 80, 35);
    }

    @Override
    public ItemStack quickMoveStack(Player playerIn, int pIndex) {

        Slot sourceSlot = slots.get(pIndex);
        if (sourceSlot == null || !sourceSlot.hasItem())
            return ItemStack.EMPTY;
        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copyOfSourceStack = sourceStack.copy();
        // Check if the slot clicked is one of the vanilla container slots
        if (pIndex < Inventory.INVENTORY_SIZE)
            // This is a vanilla container slot so merge the stack into the tile inventory
            if (!moveItemStackTo(sourceStack, Inventory.INVENTORY_SIZE, Inventory.INVENTORY_SIZE
                    + this.blockEntity.MachineSlots(), false))
                return ItemStack.EMPTY;

            else if (pIndex < Inventory.INVENTORY_SIZE + this.blockEntity.MachineSlots())
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
    public boolean stillValid(Player player) {
        return stillValid(ContainerLevelAccess.create(level, ((BlockEntity) blockEntity).getBlockPos()),
                player, zBlocks.REACTOR_PORT.get());
    }

}
