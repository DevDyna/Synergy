package com.devdyna.synergy.init.builder.magic.tiny_chests.ornated;

import javax.annotation.Nullable;

import com.devdyna.synergy.api.basebe.be.BEStorage;
import com.devdyna.synergy.init.types.zBlockEntities;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;

@SuppressWarnings("null")
public class OrnatedTinyChestBE extends BEStorage {

    public OrnatedTinyChestBE(BlockPos pos, BlockState blockState) {
        super(zBlockEntities.ORNATED_TINY_CHESTS.get(), pos, blockState);
    }

    @Override
    public int MachineSlots() {
        return 54;
    }

    @Override
    @Nullable
    public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new OrnatedTinyChestMenu(i, inventory, this);
    }

}
