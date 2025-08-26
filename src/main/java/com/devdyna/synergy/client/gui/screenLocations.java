package com.devdyna.synergy.client.gui;

import static com.devdyna.synergy.Main.ID;

import net.minecraft.resources.ResourceLocation;

public interface screenLocations {
    public static final ResourceLocation PLAYER_INVENTORY = ResourceLocation.fromNamespaceAndPath(ID,
            "textures/gui/player.png");

    public static final ResourceLocation MACHINE_SINGLE_SLOT = ResourceLocation.fromNamespaceAndPath(ID,
            "textures/gui/single.png");

}
