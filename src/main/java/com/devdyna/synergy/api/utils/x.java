package com.devdyna.synergy.api.utils;

import net.minecraft.core.DefaultedRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.registries.DeferredHolder;

import static com.devdyna.synergy.Main.ID;

import com.devdyna.synergy.api.zFluid;

public class x {

    public static ResourceLocation rl(String modid, String s) {
        return ResourceLocation.fromNamespaceAndPath(modid, s);
    }

    public static ResourceLocation parse(String s) {
        return ResourceLocation.parse(s);
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

    public static ItemStack item(ItemLike i) {
        return new ItemStack(i);
    }

    public static ItemStack item(BlockState i) {
        return item(i.getBlock());
    }

    // public static ItemStack item(DeferredHolder<? extends ItemLike, ?> holder) {
    // return x.item(holder.get().asItem());
    // }

    public static Ingredient ingredient(ItemStack i) {
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

    public static Ingredient ingredient(TagKey<Item> i) {
        return Ingredient.of(i);
    }

    public static Ingredient ingredient(ResourceLocation tag) {
        return Ingredient.of(TagKey.create(Registries.ITEM, tag));
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

    public static ResourceLocation id(Item item) {
        return BuiltInRegistries.ITEM.getKey(item);
    }

    public static FluidStack fluid(Fluid f) {
        return fluid(f, 1000);
    }

    public static Fluid fluid(zFluid f) {
        return f.getFluid();
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


}
