package com.devdyna.synergy.init.types;

import com.devdyna.synergy.init.Material;

import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;

public class zBlockTag {
    public static void register(IEventBus bus) {
    }

    public static final TagKey<Block> LEAVES = Material.tagBlock("crook_leaves");
    public static final TagKey<Block> CROPS = Material.tagBlock("sprinkler_crops");

    public static final TagKey<Block> PIPE_CONNECTORS = Material.tagBlock("pipe_connector");

    public static final TagKey<Block> EXTRACTORS = Material.tagBlock("extractors");


    // public static final TagKey<Block> WILD_CROP_BLOCKS = Material.tagBlock("wildcrop_blocks");

}