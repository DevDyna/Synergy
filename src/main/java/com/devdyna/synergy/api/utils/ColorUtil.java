package com.devdyna.synergy.api.utils;

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

    public static Color color(int red, int green, int blue) {
        return new Color(red, green, blue);
    }

    public static int pulseColor(Level level, int delay, int startColor, int endColor) {

        float t = (float) ((Math.sin((level.getGameTime() % delay) * (2 * Math.PI / delay)) + 1) / 2);

        int r1 = (startColor >> 16) & 0xFF;
        int g1 = (startColor >> 8) & 0xFF;
        int b1 = startColor & 0xFF;

        int r2 = (endColor >> 16) & 0xFF;
        int g2 = (endColor >> 8) & 0xFF;
        int b2 = endColor & 0xFF;

        int r = (int) (r1 + (r2 - r1) * t);
        int g = (int) (g1 + (g2 - g1) * t);
        int b = (int) (b1 + (b2 - b1) * t);

        return (r << 16) | (g << 8) | b;
    }

    public static int pulseColor(Level level, int startColor, int endColor){
        return pulseColor(level, 160, startColor, endColor);
    }

}
