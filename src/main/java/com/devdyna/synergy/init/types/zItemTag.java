package com.devdyna.synergy.init.types;

import com.devdyna.synergy.init.Material;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;

public class zItemTag {
    public static void register(IEventBus bus) {

    }

    public static final TagKey<Item> STONE_SLABS = Material.tagItem("stone_slabs");

    // public static final TagKey<Item> SMASHER = Material.tagItem("smasher");

    public static final TagKey<Item> AZALEA_BUSHES = Material.tagItem("azalea");

}
