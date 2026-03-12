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
import net.neoforged.neoforge.common.crafting.SizedIngredient;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.crafting.FluidIngredient;
import net.neoforged.neoforge.fluids.crafting.SizedFluidIngredient;
import net.neoforged.neoforge.registries.DeferredHolder;

import static com.devdyna.synergy.Main.ID;

import java.util.Arrays;
import java.util.List;

import com.devdyna.synergy.api.registers.FluidRegister;
import com.devdyna.synergy.api.registers.MachineType;

@SuppressWarnings("unchecked")
public class x {

    public static ResourceLocation rl(String modid, String s) {
        return ResourceLocation.fromNamespaceAndPath(modid, s);
    }

    public static ResourceLocation mcLoc(String s) {
        return rl("minecraft", s);
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

    public static ItemStack item(MachineType<?, ?, ?, ?> i) {
        return item(i.item());
    }

    public static ItemStack item(BlockState i) {
        return item(i.getBlock());
    }

    public static FluidIngredient ingredientFluid(FluidStack i) {
        return FluidIngredient.of(i);
    }

    public static FluidIngredient ingredientFluid(Fluid i) {
        return FluidIngredient.of(i);
    }

    public static FluidIngredient ingredientFluid(FluidRegister i) {
        return ingredientFluid(i.getFluid());
    }

    public static FluidIngredient ingredientFluid(TagKey<Fluid> i) {
        return FluidIngredient.tag(i);
    }

    public static FluidIngredient ingredientFluid(ResourceLocation tag) {
        return FluidIngredient.tag(TagKey.create(Registries.FLUID, tag));
    }

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

    public static SizedIngredient itemSized(ResourceLocation tag) {
        return itemSized(ingredient(tag));
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

    public static Item get(ResourceLocation rl) {
        return BuiltInRegistries.ITEM.get(rl);
    }

    public static FluidStack fluid(Fluid f) {
        return fluid(f, 1000);
    }

    public static Fluid fluid(FluidRegister f) {
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

    public static SizedFluidIngredient fluidSized(TagKey<Fluid> tag, int amount) {
        return SizedFluidIngredient.of(tag, amount);
    }

    public static SizedFluidIngredient fluidSized(Fluid fluid, int amount) {
        return SizedFluidIngredient.of(fluid, amount);
    }

    public static SizedFluidIngredient fluidSized(TagKey<Fluid> tag) {
        return fluidSized(tag, 1000);
    }

    public static SizedFluidIngredient fluidSized(Fluid fluid) {
        return fluidSized(fluid, 1000);
    }

    public static SizedFluidIngredient fluidSized(FluidStack stack) {
        return SizedFluidIngredient.of(stack);
    }

    public static SizedIngredient itemSized(ItemLike stack) {
        return itemSized(stack, 1);
    }

    public static SizedIngredient itemSized(TagKey<Item> stack) {
        return itemSized(stack, 1);
    }

    public static SizedIngredient itemSized(ItemLike stack, int c) {
        return SizedIngredient.of(stack, c);
    }

    public static SizedIngredient itemSized(TagKey<Item> stack, int c) {
        return SizedIngredient.of(stack, c);
    }

    public static SizedIngredient itemSized(ItemStack stack) {
        return itemSized(stack.getItem(), stack.getCount());
    }

    public static SizedIngredient itemSized() {
        return itemSized(Ingredient.EMPTY, 1);
    }

    public static SizedIngredient itemSized(Ingredient i, int c) {
        return new SizedIngredient(i, c);
    }

    public static SizedIngredient itemSized(Ingredient i) {
        return itemSized(i, 1);
    }

    public static List<FluidStack> getFluids(SizedFluidIngredient i) {
        return Arrays.asList(i.getFluids());
    }

    public static List<FluidStack> getFluids(FluidIngredient i) {
        return Arrays.asList(i.getStacks());
    }

    public static SizedFluidIngredient fluidSized() {
        return new SizedFluidIngredient(FluidIngredient.empty(), 1);
    }

    public static boolean matchAny(SizedFluidIngredient s, SizedFluidIngredient f) {
        return getFluids(s).stream().anyMatch(i -> f.test(i));
    }

    public static boolean matchAny(SizedIngredient s, SizedIngredient f) {
        return getItems(s).stream().anyMatch(i -> f.test(i));
    }

    public static List<ItemStack> getItems(SizedIngredient i) {
        return Arrays.asList(i.getItems());
    }

    public static List<ItemStack> getItems(Ingredient i) {
        return Arrays.asList(i.getItems());
    }

    public static String id(Ingredient i) {

        var v = i.getValues();

        if (v.length == 0)
            throw new NullPointerException("Ingredient don't match requisitions!");

        if (v[0] instanceof Ingredient.ItemValue itemValue)
            return id(itemValue.item().getItem()).getPath();

        if (v[0] instanceof Ingredient.TagValue tagValue)
            return tagValue.tag().location().getPath();

        throw new NullPointerException("Ingredient broken!");
    }

    public static Block[] toBlocks(DeferredHolder<Block, ?>... blocks) {
        return Arrays.asList(blocks).stream().map(DeferredHolder::get).toArray(Block[]::new);
    }

    public static DeferredHolder<Block, ?>[] toDefHolders(MachineType<?, ?, ?, ?>... machines) {
        return Arrays.asList(machines).stream().map(MachineType::block).toArray(DeferredHolder[]::new);
    }

    public static Block[] toBlocks(MachineType<?, ?, ?, ?>... machines) {
        return toBlocks(toDefHolders(machines));
    }

    public static Item[] toItems(DeferredHolder<Block, ?>... blocks) {
        return toItems(toBlocks(blocks));
    }

    public static Item[] toItems(Block... blocks) {
        return Arrays.asList(blocks).stream().map(Block::asItem).toArray(Item[]::new);
    }

    public static DeferredHolder<Item, ?>[] toItems(MachineType<?, ?, ?, ?>... machines) {
        return Arrays.asList(machines).stream().map(MachineType::item).toArray(DeferredHolder[]::new);
    }

}
