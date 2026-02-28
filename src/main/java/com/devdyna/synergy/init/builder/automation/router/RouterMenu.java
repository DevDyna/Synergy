package com.devdyna.synergy.init.builder.automation.router;

import com.devdyna.synergy.api.gui.BaseMenu;
import com.devdyna.synergy.init.types.zBlocks;
import com.devdyna.synergy.init.types.zContainer;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;

public class RouterMenu extends BaseMenu {

    public final RouterBE blockEntity;
    private final Level level;

    public RouterMenu(int pContainerId, Inventory inv, FriendlyByteBuf extraData) {
        this(pContainerId, inv, inv.player.level().getBlockEntity(extraData.readBlockPos()));
    }

    public RouterMenu(int i, Inventory inv, BlockEntity be) {
        super(zContainer.ROUTER.get(), i, be);
        this.blockEntity = ((RouterBE) be);
        this.level = inv.player.level();
        addPlayerSlots(inv);

        addSingleMachineSlot(blockEntity.getStorage(), RouterBE.FILTER_NORTH_1, 5, 19);
        addSingleMachineSlot(blockEntity.getStorage(), RouterBE.FILTER_NORTH_2, 23, 19);
        addSingleMachineSlot(blockEntity.getStorage(), RouterBE.FILTER_NORTH_3, 41, 19);

        addSingleMachineSlot(blockEntity.getStorage(), RouterBE.FILTER_SOUTH_1, 5, 54);
        addSingleMachineSlot(blockEntity.getStorage(), RouterBE.FILTER_SOUTH_2, 23, 54);
        addSingleMachineSlot(blockEntity.getStorage(), RouterBE.FILTER_SOUTH_3, 41, 54);

        addSingleMachineSlot(blockEntity.getStorage(), RouterBE.FILTER_EAST_1, 62, 19);
        addSingleMachineSlot(blockEntity.getStorage(), RouterBE.FILTER_EAST_2, 80, 19);
        addSingleMachineSlot(blockEntity.getStorage(), RouterBE.FILTER_EAST_3, 98, 19);

        addSingleMachineSlot(blockEntity.getStorage(), RouterBE.FILTER_WEST_1, 62, 54);
        addSingleMachineSlot(blockEntity.getStorage(), RouterBE.FILTER_WEST_2, 80, 54);
        addSingleMachineSlot(blockEntity.getStorage(), RouterBE.FILTER_WEST_3, 98, 54);

        addSingleMachineSlot(blockEntity.getStorage(), RouterBE.FILTER_UP_1, 119, 19);
        addSingleMachineSlot(blockEntity.getStorage(), RouterBE.FILTER_UP_2, 137, 19);
        addSingleMachineSlot(blockEntity.getStorage(), RouterBE.FILTER_UP_3, 155, 19);

        addSingleMachineSlot(blockEntity.getStorage(), RouterBE.FILTER_DOWN_1, 119, 54);
        addSingleMachineSlot(blockEntity.getStorage(), RouterBE.FILTER_DOWN_2, 137, 54);
        addSingleMachineSlot(blockEntity.getStorage(), RouterBE.FILTER_DOWN_3, 155, 54);

    }

    @Override
    public Block[] getValidBlock() {
        return new Block[] { zBlocks.ROUTER.get() };
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
