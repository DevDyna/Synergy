package com.devdyna.synergy.utils;

import net.minecraft.core.DefaultedRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredHolder;

import static com.devdyna.synergy.Main.ID;

public class x {

    public static ResourceLocation rl(String modid, String s) {
        return ResourceLocation.fromNamespaceAndPath(modid, s);
    }

    public static ResourceLocation rl(String s) {
        return rl(ID, s);
    }

    /**
     * @param d <code>BuiltInRegistries.BLOCK</code>
     * @param i <code>Blocks.STONE</code>
     */
    public static <T> ResourceLocation rl(DefaultedRegistry<T> d, T i) {
        return rl(d.getKey(i).getPath());
    }

    /**
     * @param d <code>BuiltInRegistries.BLOCK</code>
     * @param i <code>Blocks.STONE</code>
     */
    public static <T> ResourceLocation rl(DefaultedRegistry<T> d, T i, String modid) {
        return rl(modid, d.getKey(i).getPath());
    }

    public static ResourceLocation rl(Item i) {
        return rl(BuiltInRegistries.ITEM, i);
    }

    public static ResourceLocation rl(Block i) {
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

    public static String path(Block i) {
        return path(BuiltInRegistries.BLOCK, i);
    }

    /**
     * @param <T>
     * @param d   <code>BuiltInRegistries.BLOCK</code>
     * @param i   <code>"stone"</code>
     */
    public static <T> T get(DefaultedRegistry<T> d, String i) {
        return d.get(rl(i));
    }

    /**
     * @param <T>
     * @param d   <code>BuiltInRegistries.BLOCK</code>
     * @param i   <code>"stone"</code>
     */
    public static <T> T get(DefaultedRegistry<T> d, String modid, String i) {
        return d.get(rl(modid, i));
    }

    public static ItemStack item(Item i, int c) {
        return new ItemStack(i, c);
    }

    public static ItemStack item(DeferredHolder<Item, Item> i, int c) {
        return item(i.get(), c);
    }

    public static ItemStack item(Item i) {
        return new ItemStack(i);
    }

    public static ItemStack item(DeferredHolder<? extends ItemLike, ?> holder) {
        return x.item(holder.get().asItem());
    }

    public static Ingredient ingredient(Item i) {
        return Ingredient.of(i);
    }

    public static Ingredient ingredient(DeferredHolder<Item, ?> i) {
        return ingredient(i.get());
    }

    public static Ingredient ingredient(TagKey<Item> i) {
        return Ingredient.of(i);
    }

}
