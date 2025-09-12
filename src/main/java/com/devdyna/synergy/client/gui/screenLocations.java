package com.devdyna.synergy.client.gui;

import static com.devdyna.synergy.Main.ID;

import com.devdyna.synergy.utils.x;

import net.minecraft.resources.ResourceLocation;

public interface screenLocations {
        public static final ResourceLocation PLAYER_INVENTORY = x.rl(ID,
                        "textures/gui/player.png");

        public static final ResourceLocation GUI_MONO_TINY = x.rl(ID,
                        "textures/gui/mono/tiny.png");

        public static final ResourceLocation MACHINE_LABEL = x.rl(ID,
                        "textures/gui/label.png");

        public static final ResourceLocation GUI_DOUBLE_WITH_SMART_ARROW = x.rl(ID,
                        "textures/gui/dual/with_smart_arrow.png");

        public static final ResourceLocation PROGRESS_ARROW = x.rl(ID,
                        "textures/gui/progress_arrow.png");

}
