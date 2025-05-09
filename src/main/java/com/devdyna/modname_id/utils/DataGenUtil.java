package com.devdyna.modname_id.utils;

import static com.devdyna.modname_id.Main.MODID;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.BlockModelBuilder;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;

public class DataGenUtil {

    public static final ResourceLocation CUTOUT = ResourceLocation.withDefaultNamespace("cutout");

    private static String mc = "minecraft:";
    public static String TOOL = mc + "item/handheld";
    public static String ITEM = mc + "item/generated";
    private static String parent = MODID + ":";

    public static Block getBlock(String id) {
        return BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(MODID, id));
    }

    public static String getPath(Block b) {
        return BuiltInRegistries.BLOCK.getKey(b).getPath();
    }

    public static String getPath(Item i) {
        return BuiltInRegistries.ITEM.getKey(i).getPath();
    }

    public static ResourceLocation getResource(String s) {
        return ResourceLocation.fromNamespaceAndPath(MODID, s);
    }

    public static ResourceLocation getResource(Block b) {
        return ResourceLocation.fromNamespaceAndPath(MODID, getPath(b));
    }

    public static ResourceLocation getResource(Item i) {
        return ResourceLocation.fromNamespaceAndPath(MODID, getPath(i));
    }

    public static ItemModelBuilder itemTool(Item item, ItemModelProvider b) {
        return b.withExistingParent(getPath(item), TOOL).texture("layer0",
                getResource("item/" + getPath(item)));
    }

    public static ItemModelBuilder itemModel(Item item, ItemModelProvider b) {
        return b.withExistingParent(getPath(item), ITEM).texture("layer0",
                getResource("item/" + getPath(item)));
    }

    public static ItemModelBuilder itemBlock(Block block, ItemModelProvider b) {
        return b.withExistingParent(getPath(block), parent + getPath(block));
    }

    /**
     * @param block
     * @param b       this
     * @param parent  Main.ID + ":block/..."
     * @param keyname "all"
     * @param texture "minecraft:block/cobblestone"
     * @return
     */
    public static BlockModelBuilder BlockwithParent(Block block, BlockStateProvider b,
            String parent, String keyname, String texture) {
        return b.models().withExistingParent(getPath(block), parent)
                .texture(keyname, texture);
    }

}
