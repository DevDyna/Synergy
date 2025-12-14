package com.devdyna.synergy.init.builder.chests.wooden;

import javax.annotation.Nullable;

import com.devdyna.synergy.api.coreBE.be.BEStorage;
import com.devdyna.synergy.init.types.zBlockEntities;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;

@SuppressWarnings("null")
public class WoodenTinyChestBE extends BEStorage {

    public WoodenTinyChestBE(BlockPos pos, BlockState blockState) {
        super(zBlockEntities.WOODEN_TINY_CHEST.get(), pos, blockState);
    }

    @Override
    @Nullable
    public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new WoodenTinyChestMenu(i, inventory, this);
    }

}
