package com.devdyna.synergy.client.gui.reactor_port;

import com.devdyna.synergy.api.beLogic.ItemStorageBlock;
import com.devdyna.synergy.client.gui.baseGui;
import com.devdyna.synergy.init.types.zBlocks;
import com.devdyna.synergy.init.types.zContainer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
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
    public Block getValidBlock() {
        return zBlocks.REACTOR_PORT.get();
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
