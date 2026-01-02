package com.devdyna.synergy.api.utils;

import net.minecraft.core.DefaultedRegistry;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.RegistryLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.registries.DeferredHolder;

import static com.devdyna.synergy.Main.ID;

import java.util.stream.Stream;

@SuppressWarnings("unchecked")
public class x {

    public static Identifier rl(String modid, String s) {
        return Identifier.fromNamespaceAndPath(modid, s);
    }

    public static Identifier parse(String s) {
        return Identifier.parse(s);
    }

    public static Identifier rl(String s) {
        return rl(ID, s);
    }

    public static Identifier mcLoc(String s) {
        return rl("minecraft", s);
    }

    /**
     * @param d <code>BuiltInRegistries.BLOCK</code>
     * @param i <code>Blocks.STONE</code>
     */
    public static <T> Identifier rl(DefaultedRegistry<T> d, T i) {
        return rl(d.getKey(i).getPath());
    }

    /**
     * @param d <code>BuiltInRegistries.BLOCK</code>
     * @param i <code>Blocks.STONE</code>
     */
    public static <T> Identifier rl(DefaultedRegistry<T> d, T i, String modid) {
        return rl(modid, d.getKey(i).getPath());
    }

    public static Identifier rl(Item i) {
        return rl(BuiltInRegistries.ITEM, i);
    }

    public static Identifier rl(Block i) {
        return rl(BuiltInRegistries.BLOCK, i);
    }

    /**
     * @param d <code>BuiltInRegistries.BLOCK</code>
     * @param i <code>Blocks.STONE</code>
     */
    public static <T> String path(DefaultedRegistry<T> d, T i) {
        return d.getKey(i).getPath();
    }

    public static String path(Item i) {
        return path(BuiltInRegistries.ITEM, i);
    }

    public static String path(Fluid i) {
        return path(BuiltInRegistries.FLUID, i);
    }

    public static String path(ItemStack i) {
        return path(i.getItem());
    }

    public static String path(Block i) {
        return path(BuiltInRegistries.BLOCK, i);
    }

    public static String path(BlockState i) {
        return path(i.getBlock());
    }

    /**
     * @param <T>
     * @param d   <code>BuiltInRegistries.BLOCK</code>
     * @param i   <code>"stone"</code>
     */

    public static <T> T get(DefaultedRegistry<T> d, String i) {
        return (T) d.get(rl(i));
    }

    /**
     * @param <T>
     * @param d   <code>BuiltInRegistries.BLOCK</code>
     * @param i   <code>"stone"</code>
     */
    public static <T> T get(DefaultedRegistry<T> d, String modid, String i) {
        return (T) d.get(rl(modid, i));
    }

    public static ItemStack item(Item i, int c) {
        return new ItemStack(i, c);
    }

    public static ItemStack item(DeferredHolder<Item, Item> i, int c) {
        return item(i.get(), c);
    }

    public static ItemStack item(ItemLike i) {
        return new ItemStack(i);
    }

    public static ItemStack item(BlockState i) {
        return item(i.getBlock());
    }

    // public static ItemStack item(DeferredHolder<? extends ItemLike, ?> holder) {
    // return x.item(holder.get().asItem());
    // }

    /**
     * Use <code>ingredient(ItemLike i)</code>
     */
    @Deprecated
    public static Ingredient ingredient(ItemStack i) {
        return Ingredient.of(i.getItem());
    }

    public static Ingredient ingredient(ItemStack... i) {
        return Ingredient.of(Stream.of(i).map(ItemStack::getItem));
    }

    public static Ingredient ingredient(ItemLike... i) {
        return Ingredient.of(i);
    }

    public static Ingredient ingredient(ItemLike i) {
        return Ingredient.of(i);
    }

    public static Ingredient ingredient(Item i) {
        return ingredient(x.item(i));
    }

    public static Ingredient ingredient(DeferredHolder<Item, ?> i) {
        return ingredient(i.get());
    }

    // public static Ingredient ingredient(TagKey<Item> i) {
    // return Ingredient.of(i);
    // }

    public static Ingredient ingredient(RegistryLookup<Item> p, Identifier tag) {
        return x.ingredient(p, tag(tag));
    }

    public static TagKey<Item> tag(Identifier tag) {
        return TagKey.create(Registries.ITEM, tag);
    }

    public static Block block(DeferredHolder<Block, ?> b) {
        return b.get();
    }

    public static BlockState state(DeferredHolder<Block, ?> b) {
        return block(b).defaultBlockState();
    }

    public static Block block(BlockState b) {
        return b.getBlock();
    }

    public static Identifier id(Item item) {
        return BuiltInRegistries.ITEM.getKey(item);
    }

    public static FluidStack fluid(Fluid f) {
        return fluid(f, 1000);
    }

    public static FluidStack fluid(Fluid f, int amount) {
        return new FluidStack(f, amount);
    }

    public static <T> ItemStack item(DeferredHolder<T, ?> holder) {
        T obj = holder.get();
        if (obj instanceof Item item) {
            return item.getDefaultInstance();
        } else if (obj instanceof Block block) {
            return new ItemStack(block);
        } else {
            throw new IllegalArgumentException("Unsupported type: " + obj.getClass());
        }
    }

    public static Ingredient ingredient(HolderLookup.Provider p, TagKey<Item> tag) {
        return ingredient(p.lookupOrThrow(Registries.ITEM), tag);
    }

    public static Ingredient ingredient(RegistryLookup<Item> p, TagKey<Item> tag) {
        return Ingredient.of(p.getOrThrow(tag));
    }

    public static ResourceKey<Recipe<?>> recipeID(Item i, String suffix) {
        return recipeID(BuiltInRegistries.ITEM.getKey(i).withSuffix(suffix));
    }

    public static ResourceKey<Recipe<?>> recipeID(Identifier rl) {
        return ResourceKey.create(Registries.RECIPE, rl);
    }

    @SuppressWarnings("deprecation")
    public static Item ingredientToItem(Ingredient i){
        return i.items().findFirst().get().value();
    }

}
