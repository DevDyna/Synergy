package com.devdyna.synergy.init.types;

import com.devdyna.synergy.init.Material;

import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.neoforged.bus.api.IEventBus;

public class zBiomeTags {
    public static void register(IEventBus bus) {
    }

    public static final TagKey<Biome> BLUE_CUP_SPAWN = Material.tagBiome("blue_cup_spawn");
    public static final TagKey<Biome> VIOLET_WEBCAP_SPAWN = Material.tagBiome("violet_webcap_spawn");
    public static final TagKey<Biome> WILD_CAVE_WHEAT_SPAWN = Material.tagBiome("wild_cave_wheat_spawn");
    public static final TagKey<Biome> WILD_COTTON_SPAWN = Material.tagBiome("wild_cotton_spawn");
    public static final TagKey<Biome> WILD_RICE_SPAWN = Material.tagBiome("wild_rice_spawn");

}