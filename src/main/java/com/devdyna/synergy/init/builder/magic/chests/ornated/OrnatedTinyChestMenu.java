package com.devdyna.synergy.init.builder.magic.chests.ornated;

import com.devdyna.synergy.api.gui.BaseTinyChestMenu;
import com.devdyna.synergy.init.types.zBlocks;
import com.devdyna.synergy.init.types.zContainer;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;

public class OrnatedTinyChestMenu extends BaseTinyChestMenu {

    public OrnatedTinyChestMenu(int containerId, Inventory inv, FriendlyByteBuf extraData) {
        this(containerId, inv, inv.player.level().getBlockEntity(extraData.readBlockPos()));
    }

    public OrnatedTinyChestMenu(int containerId, Inventory inv, BlockEntity be) {
        super(zContainer.ORNATED_TINY_CHEST_MENU.get(), containerId, be);
        this.level = inv.player.level();
        addPlayerSlots(inv,0,56);
        addMachineSlots(this.blockEntity.getStorage(),
                0, 8, 18, blockEntity.MachineSlots(), i -> (i % 9) * 18, i -> (i / 9) * 18);

    }

    

    @Override
    public Block getChestType() {
        return zBlocks.ORNATE_TINY_CHEST.get();
    }

    @Override
    public Level getLevel() {
        return level;
    }

}
