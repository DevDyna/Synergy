package com.devdyna.synergy.init.builder.nuclear_reactor.fuel_cell;

import com.devdyna.synergy.api.gui.BaseMenu;
import com.devdyna.synergy.init.types.zBlocks;
import com.devdyna.synergy.init.types.zContainer;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;

public class FuelCellMenu extends BaseMenu {

    public final FuelCellBE blockEntity;
    private final Level level;
    private final ContainerData data;

    public FuelCellMenu(int pContainerId, Inventory inv, FriendlyByteBuf extraData) {
        this(pContainerId, inv, inv.player.level().getBlockEntity(extraData.readBlockPos()),
                new SimpleContainerData(2));
    }

    public FuelCellMenu(int i, Inventory inv, BlockEntity be, ContainerData data) {
        super(zContainer.FUEL_CELL_MENU.get(), i, be);
        this.blockEntity = ((FuelCellBE) be);
        this.level = inv.player.level();
        this.data = data;
        addPlayerSlots(inv);
        addMachineSlot(blockEntity.getStorage(), 0, 54, 34);
        addMachineOutputSlot(blockEntity.getStorage(), 1, 102, 34);
        addDataSlots(data);
    }

    public boolean isCrafting() {
        return data.get(0) > 0;
    }

    public int getScaledArrowProgress() {
        int progress = data.get(0);
        int maxProgress = data.get(1);
        int sizeArrow = 24;
        return maxProgress != 0
                &&
                progress != 0 ? progress * sizeArrow / maxProgress : 0;
    }

    @Override
    public Block[] getValidBlock() {
        return new Block[] { zBlocks.REACTOR_FUEL_CELL.get() };
    }

    @Override
    public BlockEntity getBlockEntity() {
        return blockEntity;
    }

    @Override
    public Level getLevel() {
        return level;
    }

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
            if (moveItemStackTo(item, 36, 36 + 1, true)) {
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

}
