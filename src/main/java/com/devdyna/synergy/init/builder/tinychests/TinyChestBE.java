package com.devdyna.synergy.init.builder.tinychests;

import javax.annotation.Nullable;

import com.devdyna.synergy.api.menu.BEMenu;
import com.devdyna.synergy.client.gui.tiny_chest.chestGUI;
import com.devdyna.synergy.init.types.zBlockEntities;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;

@SuppressWarnings("null")
public class TinyChestBE extends BEMenu {

    public TinyChestBE(BlockPos pos, BlockState blockState) {
        super(zBlockEntities.WOODEN_TINY_CHEST.get(), pos, blockState);
    }

    @Override
    @Nullable
    public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new chestGUI(i, inventory, this);
    }

    @Override
    public int MachineSlots() {
        return 1;
    }

}
