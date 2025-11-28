package com.devdyna.synergy.client.gui.chests.stone;

import com.devdyna.synergy.client.gui.api.BaseTinyChestScreen;
import com.devdyna.synergy.utils.x;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

@SuppressWarnings("null")
public class StoneTinyChestScreen extends BaseTinyChestScreen<StoneTinyChestMenu> {

    public StoneTinyChestScreen(StoneTinyChestMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
    }

    @Override
    protected ResourceLocation background() {
        return x.rl("minecraft", "textures/gui/container/shulker_box.png");
    }

    

}
