package com.devdyna.synergy.client.gui.chests.stone;

import com.devdyna.synergy.client.gui.api.BaseTinyChestMenu;
import com.devdyna.synergy.init.types.zBlocks;
import com.devdyna.synergy.init.types.zContainer;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;

public class StoneTinyChestMenu extends BaseTinyChestMenu {

    public StoneTinyChestMenu(int containerId, Inventory inv, FriendlyByteBuf extraData) {
        this(containerId, inv, inv.player.level().getBlockEntity(extraData.readBlockPos()));
    }

    public StoneTinyChestMenu(int containerId, Inventory inv, BlockEntity be) {
        super(zContainer.STONE_TINY_CHEST_MENU.get(), containerId, be);
        this.level = inv.player.level();
        addPlayerSlots(inv);
        addMachineSlots(this.blockEntity.getStorage(),
                0, 8, 18, blockEntity.MachineSlots(), i -> (i % 9) * 18, i -> (i / 9) * 18);

    }

    @Override
    public Block getChestType() {
        return zBlocks.STONE_TINY_CHEST.get();
    }

    @Override
    public Level getLevel() {
        return level;
    }

}
