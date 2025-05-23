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

}