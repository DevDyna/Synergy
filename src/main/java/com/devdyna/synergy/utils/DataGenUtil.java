package com.devdyna.synergy.utils;

import static com.devdyna.synergy.Main.ID;

import java.util.Arrays;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

import com.devdyna.synergy.datagen.server.DataGlobalLootModifier;

import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.predicates.AnyOfCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.client.model.generators.BlockModelBuilder;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.loot.AddTableLootModifier;
import net.neoforged.neoforge.common.loot.LootTableIdCondition;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

@SuppressWarnings("unchecked")
public class DataGenUtil {

    public static final ResourceLocation CUTOUT = ResourceLocation.withDefaultNamespace("cutout");

    private static String mc = "minecraft:";
    public static String TOOL = mc + "item/handheld";
    public static String ITEM = mc + "item/generated";
    private static String mod = ID + ":";

    /**
     * @deprecated use <code> x.get() </code>
     */
    @Deprecated
    public static Block getBlock(String id) {
        return BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(ID, id));
    }

    /**
     * @deprecated use <code> x.get() </code>
     */
    @Deprecated
    public static Block getBlock(String id, String modid) {
        return BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(modid, id));
    }

    /**
     * @deprecated use <code> x.path() </code>
     */
    @Deprecated
    public static String getPath(Block b) {
        return BuiltInRegistries.BLOCK.getKey(b).getPath();
    }

    /**
     * @deprecated use <code> x.path() </code>
     */
    @Deprecated
    public static String getPath(Item i) {
        return BuiltInRegistries.ITEM.getKey(i).getPath();
    }

    /**
     * @deprecated use <code> x.rl() </code>
     */
    @Deprecated
    public static ResourceLocation getResource(String s) {
        return ResourceLocation.fromNamespaceAndPath(ID, s);
    }

    /**
     * @deprecated use <code> x.rl() </code>
     */
    @Deprecated
    public static ResourceLocation getResource(Block b) {
        return ResourceLocation.fromNamespaceAndPath(ID, getPath(b));
    }

    /**
     * @deprecated use <code> x.rl() </code>
     */
    @Deprecated
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

    public static ItemModelBuilder itemModel(Item item, ItemModelProvider b, String pathSuffix) {
        return b.withExistingParent(getPath(item), ITEM).texture("layer0",
                getResource("item/" + pathSuffix + getPath(item)));
    }

    public static ItemModelBuilder itemModel(Item item, ItemModelProvider b, String pathSuffix, String itemPath) {
        return b.withExistingParent(getPath(item), ITEM).texture("layer0",
                getResource("item/" + pathSuffix + itemPath));
    }

    public static ItemModelBuilder itemBlock(Block block, ItemModelProvider b) {
        return b.withExistingParent(getPath(block), mod + "block/" + getPath(block));
    }

    public static BlockModelBuilder cross(BlockStateProvider t, String filePath, ResourceLocation texturePath) {
        return t.models().withExistingParent(filePath, t.mcLoc("block/cross")).texture("cross", texturePath)
                .renderType("minecraft:cutout");
    }

    public static BlockModelBuilder crop(BlockStateProvider t, String filePath, ResourceLocation texturePath) {
        return t.models().withExistingParent(filePath, t.mcLoc("block/crop")).texture("crop", texturePath)
                .renderType("minecraft:cutout");
    }

    public static BlockModelBuilder crossORcrop(BlockStateProvider t, boolean isCrop, String filePath,
            ResourceLocation texturePath) {
        return t.models().withExistingParent(filePath, t.mcLoc("block/" + (isCrop ? "crop" : "cross")))
                .texture((isCrop ? "crop" : "cross"), texturePath)
                .renderType("minecraft:cutout");
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

    public static void BiStateBlock(BlockStateProvider t, Block b, BooleanProperty p, ModelFile on,
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

    public static LootItemBlockStatePropertyCondition.Builder lootTableCondition(Block block, BooleanProperty prop) {
        return LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(prop, true));
    }
    
    public static LootItemBlockStatePropertyCondition.Builder lootTableConditionInverse(Block block, BooleanProperty prop) {
        return LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(prop, true));
    }

    /**
     * Apply a loot modifier at all of specific loot tables
     * 
     * @param lootModifier like "chests/jungle_temple"
     * @param lootTables   like "chests/jungle_temple"
     */
    public static void modifyLootTables(DataGlobalLootModifier g, String lootModifier, String... lootTables) {
        g.add(lootModifier,
                new AddTableLootModifier(
                        new LootItemCondition[] { AnyOfCondition.anyOf(Arrays.asList(lootTables).stream()
                                .map(r -> LootTableIdCondition.builder(ResourceLocation.parse(r)))
                                .toArray(LootTableIdCondition.Builder[]::new)).build() },
                        ResourceKey.create(Registries.LOOT_TABLE, modLoc(lootModifier))));

    }

    public static void modifyLootTables(DataGlobalLootModifier g, String lootModifier,
            ResourceKey<LootTable>... lootTables) {
        modifyLootTables(g, lootModifier,
                Arrays.asList(lootTables).stream().map(r -> r.location().getPath()).toArray(String[]::new));
    }

    public static void modifyLootTables(DataGlobalLootModifier g, String lootModifier,
            EntityType<?>... lootTables) {
        modifyLootTables(g, lootModifier,
                Arrays.asList(lootTables).stream().map(r -> r.getDefaultLootTable().location().getPath())
                        .toArray(String[]::new));
    }

    public static void modifyLootTables(DataGlobalLootModifier g, String lootModifier,
            ResourceKey<LootTable>[] chestTables, EntityType<?>... entityTables) {
        modifyLootTables(g, lootModifier,
                ArrayUtils.concat(
                        Arrays.asList(entityTables).stream().map(r -> r.getDefaultLootTable().location().getPath())
                                .toArray(String[]::new),
                        Arrays.asList(chestTables).stream().map(r -> r.location().getPath()).toArray(String[]::new)));

    }

    /**
     * 
     * @deprecated
     */
    @Deprecated
    public static ResourceLocation modLoc(String path) {
        return ResourceLocation.fromNamespaceAndPath(ID, path);
    }

    /**
     * Apply a rool<br/>
     * 
     * <pre>
     * .setRolls(UniformGenerator.between(0f,1f))
     * </pre>
     * 
     * <br/>
     * <br/>
     * Add lootItems<br/>
     * 
     * <pre>
     * .add(LootItem.lootTableItem(Items.STONE))
     * </pre>
     */
    public static LootPool.Builder createPool() {
        return LootPool.lootPool();
    }

    /**
     * 
     * @param pool
     * 
     *             <pre>
     *             DataGenUtil.createPool()
     *             </pre>
     */
    public static LootTable.Builder createTable(LootPool.Builder pool) {
        return LootTable
                .lootTable()
                .withPool(pool)
                .setParamSet(LootContextParamSet.builder().build());
    }

    public static void registerTable(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> c,
            ResourceLocation tableLocation,
            LootTable.Builder table) {
        c.accept(ResourceKey.create(Registries.LOOT_TABLE, tableLocation), table);
    }

    public static Item[] getItems(DeferredRegister<?> register) {
        return register.getEntries().stream()
                .map(DeferredHolder::get)
                .flatMap(i -> {
                    if (i instanceof Item item) {
                        return Stream.of(item);
                    } else if (i instanceof Block block) {
                        Item item = Item.BY_BLOCK.get(block);
                        return item != null ? Stream.of(item) : Stream.empty(); // check if block has blockitem
                    }
                    return Stream.empty();
                })
                .toArray(Item[]::new);
    }

}
