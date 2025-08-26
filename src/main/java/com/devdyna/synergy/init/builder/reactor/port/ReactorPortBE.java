package com.devdyna.synergy.init.builder.reactor.port;

import javax.annotation.Nullable;

import com.devdyna.synergy.api.menu.BEMenu;
import com.devdyna.synergy.client.gui.reactor_port.portGUI;
import com.devdyna.synergy.init.types.zBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;

@SuppressWarnings("null")
public class ReactorPortBE extends BEMenu {

    public ReactorPortBE(BlockPos pos, BlockState blockState) {
        super(zBlockEntities.REACTOR_PORT.get(), pos, blockState);
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new portGUI(i, inventory, this);
    }

}
