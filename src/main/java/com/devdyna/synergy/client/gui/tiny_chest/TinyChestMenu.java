package com.devdyna.synergy.client.gui.tiny_chest;

import com.devdyna.synergy.api.beLogic.ItemStorageBlock;
import com.devdyna.synergy.client.gui.*;
import com.devdyna.synergy.init.types.zBlocks;
import com.devdyna.synergy.init.types.zContainer;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;

public class TinyChestMenu extends BaseMenu {
    public final ItemStorageBlock blockEntity;
    private final Level level;

    public TinyChestMenu(int containerId, Inventory inv, FriendlyByteBuf extraData) {
        this(containerId, inv, inv.player.level().getBlockEntity(extraData.readBlockPos()));
    }

    public TinyChestMenu(int containerId, Inventory inv, BlockEntity be) {
        super(zContainer.CHEST_MENU.get(), containerId, be);
        this.blockEntity = ((ItemStorageBlock) be);
        inv.player.getInventory();
        this.level = inv.player.level();
        addPlayerSlots(inv);
        addMachineSlot(this.blockEntity.getStorage(), 0, 80, 35);
    }

    @Override
    public Block[] getValidBlock() {
        return new Block[] { zBlocks.WOODEN_TINY_CHEST.get(), zBlocks.STONE_TINY_CHEST.get(),
                zBlocks.ORNATE_TINY_CHEST.get() };
    }

    @Override
    public BlockEntity getBlockEntity() {
        return (BlockEntity) this.blockEntity;
    }

    @Override
    public Level getLevel() {
        return level;
    }

}
