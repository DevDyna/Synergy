package com.devdyna.synergy.init.builder.magic.chests.stone;

import com.devdyna.synergy.api.gui.BaseTinyChestScreen;
import com.devdyna.synergy.api.utils.x;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;

@SuppressWarnings("null")
public class StoneTinyChestScreen extends BaseTinyChestScreen<StoneTinyChestMenu> {

    public StoneTinyChestScreen(StoneTinyChestMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
    }

    @Override
    protected Identifier background() {
        return x.rl("minecraft", "textures/gui/container/shulker_box.png");
    }

    

}
