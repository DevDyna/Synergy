package com.devdyna.synergy.init.builder.magic.tiny_chests.wooden;

import com.devdyna.synergy.api.gui.BaseTinyChestMenu;
import com.devdyna.synergy.init.types.zBlocks;
import com.devdyna.synergy.init.types.zContainer;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;

public class WoodenTinyChestMenu extends BaseTinyChestMenu {

    public WoodenTinyChestMenu(int containerId, Inventory inv, FriendlyByteBuf extraData) {
        this(containerId, inv, inv.player.level().getBlockEntity(extraData.readBlockPos()));
    }

    public WoodenTinyChestMenu(int containerId, Inventory inv, BlockEntity be) {
        super(zContainer.WOODEN_TINY_CHEST_MENU.get(), containerId, be);
        this.level = inv.player.level();
        addPlayerSlots(inv);
        addMachineSlot(this.blockEntity.getStorage(), 0, 78, 31);
    }

    @Override
    public Block getChestType() {
        return zBlocks.WOODEN_TINY_CHEST.get();
    }

    @Override
    public Level getLevel() {
        return level;
    }

}
