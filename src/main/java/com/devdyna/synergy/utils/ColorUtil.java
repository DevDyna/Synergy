package com.devdyna.synergy.utils;

import java.util.List;
import java.util.stream.Collectors;
import java.awt.Color;

import net.minecraft.world.level.Level;

public class ColorUtil {

    public static List<Color> colorList = List.of(
            Color.WHITE,
            Color.LIGHT_GRAY,
            Color.GRAY,
            Color.DARK_GRAY,
            Color.BLACK,
            Color.RED,
            Color.PINK,
            Color.ORANGE,
            Color.YELLOW,
            Color.GREEN,
            Color.MAGENTA,
            Color.CYAN,
            Color.BLUE);

    public static List<Color> colorBlackWhiteList = List.of(
            Color.WHITE,
            Color.LIGHT_GRAY,
            Color.GRAY,
            Color.DARK_GRAY,
            Color.BLACK);

    public static List<Color> colorfulColorList = colorList.stream().filter(i -> !colorBlackWhiteList.contains(i))
            .collect(Collectors.toList());

    public static int rgbColor(Level level, int delay) {
        return java.awt.Color.HSBtoRGB((level.getGameTime() % delay) / (delay * 1f), 1.0f, 1.0f)
                & 0xFFFFFF;
    }

    public static int rgbColor(Level level) {
        return rgbColor(level, 160);
    }

    public static Color color(int red,int green, int blue){
        return new Color(red, green, blue);
    }

}
