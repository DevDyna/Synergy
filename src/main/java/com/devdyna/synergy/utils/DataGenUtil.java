package com.devdyna.synergy.utils;

import static com.devdyna.synergy.Main.ID;

import com.devdyna.synergy.datagen.client.DataBlockModelState;

import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.neoforged.neoforge.client.model.generators.BlockModelBuilder;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;

public class DataGenUtil {

    public static final ResourceLocation CUTOUT = ResourceLocation.withDefaultNamespace("cutout");

    private static String mc = "minecraft:";
    public static String TOOL = mc + "item/handheld";
    public static String ITEM = mc + "item/generated";
    private static String mod = ID + ":";

    public static Block getBlock(String id) {
        return BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(ID, id));
    }

    public static Block getBlock(String id, String modid) {
        return BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(modid, id));
    }

    public static String getPath(Block b) {
        return BuiltInRegistries.BLOCK.getKey(b).getPath();
    }

    public static String getPath(Item i) {
        return BuiltInRegistries.ITEM.getKey(i).getPath();
    }

    public static ResourceLocation getResource(String s) {
        return ResourceLocation.fromNamespaceAndPath(ID, s);
    }

    public static ResourceLocation getResource(Block b) {
        return ResourceLocation.fromNamespaceAndPath(ID, getPath(b));
    }

    public static ResourceLocation getResource(Item i) {
        return ResourceLocation.fromNamespaceAndPath(ID, getPath(i));
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
        return b.withExistingParent(getPath(block), mod + "block/" + getPath(block));
    }

    /**
     * @param block
     * @param b      this
     * @param parent Main.ID + ":block/..."
     */
    public static BlockModelBuilder BlockwithParent(Block block, BlockStateProvider b,
            String parent) {
        return b.models().withExistingParent(getPath(block), parent);
    }

    public static void BiStateBlock(DataBlockModelState t, Block b, BooleanProperty p, ModelFile on,
            ModelFile off) {
        t.getVariantBuilder(b).partialState().with(p, true).modelForState()
                .modelFile(on)
                .addModel().partialState().with(p, false).modelForState()
                .modelFile(off)
                .addModel();
    }

    public static ItemModelBuilder itemBlockwithParent(Block block, ItemModelProvider b, String parent) {
        return b.withExistingParent(getPath(block), parent);
    }

    public static LootItemBlockStatePropertyCondition.Builder lootTableCondition(Block block, IntegerProperty prop,
            int age_limit) {
        return LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(prop, age_limit));
    }

}
