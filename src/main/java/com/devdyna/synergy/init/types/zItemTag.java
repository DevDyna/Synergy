package com.devdyna.synergy.init.types;

import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.init.Material;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;

public class zItemTag {
    public static void register(IEventBus bus) {

    }

    public static final TagKey<Item> STONE_SLABS = Material.tagItem("stone_slabs");

    public static final TagKey<Item> AZALEA_BUSHES = Material.tagItem("azalea");

    public static final TagKey<Item> CROP_BLUE_CUP = Material.tagItem("crops/" + zStatic.Plants.TYPE_BLUE_CUP, "c");
    public static final TagKey<Item> CROP_VIOLET_WEBCAP = Material.tagItem("crops/" + zStatic.Plants.TYPE_VIOLET_WEBCAP,
            "c");
    public static final TagKey<Item> CROP_RICE = Material.tagItem("crops/" + zStatic.Plants.RICE, "c");
    public static final TagKey<Item> CROP_COTTON = Material.tagItem("crops/" + zStatic.Plants.COTTON, "c");
    public static final TagKey<Item> CROP_CAVE_WHEAT = Material.tagItem("crops/" + zStatic.Plants.CAVE_WHEAT, "c");
    public static final TagKey<Item> CROP_AZALEA = Material.tagItem("crops/potted_" + zStatic.Blocks.azalea, "c");

}
