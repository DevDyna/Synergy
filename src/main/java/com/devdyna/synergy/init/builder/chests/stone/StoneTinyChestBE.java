package com.devdyna.synergy.init.builder.chests.stone;

import javax.annotation.Nullable;

import com.devdyna.synergy.api.coreBE.be.BEStorage;
import com.devdyna.synergy.init.types.zBlockEntities;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;

@SuppressWarnings("null")
public class StoneTinyChestBE extends BEStorage {

    public StoneTinyChestBE(BlockPos pos, BlockState blockState) {
        super(zBlockEntities.STONE_TINY_CHESTS.get(), pos, blockState);
    }

    @Override
    public int MachineSlots() {
        return 27;
    }

    @Override
    @Nullable
    public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new StoneTinyChestMenu(i, inventory, this);
    }

}
