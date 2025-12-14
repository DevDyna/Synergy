package com.devdyna.synergy.init.builder.chests.wooden;

import com.devdyna.synergy.api.gui.BaseTinyChestScreen;
import com.devdyna.synergy.api.utils.x;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

@SuppressWarnings("null")
public class WoodenTinyChestScreen extends BaseTinyChestScreen<WoodenTinyChestMenu> {

    public WoodenTinyChestScreen(WoodenTinyChestMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
    }

    @Override
    protected ResourceLocation background() {
        return x.rl( "textures/gui/container/tiny_chest.png");
    }

}
