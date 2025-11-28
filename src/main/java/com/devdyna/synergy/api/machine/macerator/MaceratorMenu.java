package com.devdyna.synergy.api.machine.macerator;

import com.devdyna.synergy.client.gui.api.BaseMenu;
import com.devdyna.synergy.init.types.zMachines;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;

@SuppressWarnings("null")
public class MaceratorMenu extends BaseMenu {

    public final MaceratorBE blockEntity;
    private final Level level;
    private final ContainerData data;

    public MaceratorMenu(int c, Inventory i, FriendlyByteBuf d) {
        this(c, i, i.player.level().getBlockEntity(d.readBlockPos()),
                new SimpleContainerData(2));
    }

    public MaceratorMenu(int i, Inventory inv, BlockEntity be, ContainerData data) {
        super(zMachines.MACERATOR.menu().get(), i, be);
        this.blockEntity = ((MaceratorBE) be);
        this.level = inv.player.level();
        this.data = data;
        addPlayerSlots(inv);
        addMachineSlot(blockEntity.getStorage(), 0, 54, 34);
        addMachineOutputSlot(blockEntity.getStorage(), 1, 104, 34);
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
        return new Block[] { zMachines.MACERATOR.block().get() };
    }

    @Override
    public BlockEntity getBlockEntity() {
        return blockEntity;
    }

    @Override
    public Level getLevel() {
        return level;
    }

}
