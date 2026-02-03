package com.devdyna.synergy.api.recipes.builders;

import java.util.Arrays;
import java.util.List;

import com.devdyna.synergy.api.recipes.builders.api.BaseRecipeBuilder;
import com.devdyna.synergy.api.recipes.builders.api.BuilderAttach;
import com.devdyna.synergy.api.utils.x;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.crafting.SizedIngredient;
import net.neoforged.neoforge.registries.DeferredHolder;

@SuppressWarnings("unchecked")
public class ItemAttach {
    public class Input {

        public static interface OptionalConsume<BUILDER extends BaseRecipeBuilder> extends BuilderAttach<BUILDER> {

            abstract BUILDER consumeItemInput();

        }

        public static interface CatalystItem<BUILDER extends BaseRecipeBuilder> extends BuilderAttach<BUILDER> {

            abstract BUILDER consumeCatalyst();

            abstract BUILDER catalyst(SizedIngredient catalyst);

            default BUILDER catalyst(Ingredient catalyst) {
                return catalyst(x.itemSized(catalyst));
            }

            default BUILDER catalyst(ItemStack... catalyst) {
                return catalyst(Ingredient.of(catalyst));
            }

            default BUILDER catalyst(DeferredHolder<Item, Item>... catalyst) {
                return catalyst((Item[]) Arrays.asList(catalyst).stream().map(DeferredHolder::get).toArray());
            }

            default BUILDER catalyst(Item... catalyst) {
                return catalyst(Ingredient.of(catalyst));
            }

            default BUILDER catalyst(ItemLike... catalyst) {
                return catalyst(Ingredient.of(catalyst));
            }

            default BUILDER catalyst(TagKey<Item> catalyst) {
                return catalyst(Ingredient.of(catalyst));
            }

        }

        public static interface NoItemCount<BUILDER extends BaseRecipeBuilder> extends BuilderAttach<BUILDER> {

            abstract BUILDER input(Ingredient input);

            default BUILDER input(Item input) {
                return input(x.ingredient(input));
            }

            default BUILDER input(DeferredHolder<Item, Item> input) {
                return input(input.get());
            }

            default BUILDER input(TagKey<Item> input) {
                return input(x.ingredient(input));
            }

        }

        public static interface ListedNoItemCount<BUILDER extends BaseRecipeBuilder> extends BuilderAttach<BUILDER> {

            abstract BUILDER add(Ingredient input);

            default BUILDER add(Item input) {
                return add(x.ingredient(input));
            }

            default BUILDER add(DeferredHolder<Item, Item> input) {
                return add(input.get());
            }

            default BUILDER add(TagKey<Item> input) {
                return add(x.ingredient(input));
            }

            default BUILDER add(ItemLike input) {
                return add(x.ingredient(input));
            }
        }

        public interface ItemCounted<BUILDER extends BaseRecipeBuilder> extends NoItemCount<BUILDER> {

            abstract BUILDER input(SizedIngredient input);

            default BUILDER input(Item input) {
                return input(x.itemSized(input));
            }

            default BUILDER input(Ingredient input) {
                return input(x.itemSized(input));
            }

            default BUILDER input(DeferredHolder<Item, Item> input) {
                return input(input.get());
            }

            default BUILDER input(TagKey<Item> input) {
                return input(x.itemSized(input));
            }

            default BUILDER input(Item input, int c) {
                return input(x.itemSized(input, c));
            }

            default BUILDER input(DeferredHolder<Item, Item> input, int c) {
                return input(input.get(), c);
            }

            default BUILDER input(TagKey<Item> input, int c) {
                return input(x.itemSized(input, c));
            }

        }

        public static interface DoubleItemCounted<BUILDER extends BaseRecipeBuilder>
                extends ItemCounted<BUILDER> {

            abstract BUILDER inputs(SizedIngredient right, SizedIngredient left);

            default BUILDER inputs(Ingredient right, Ingredient left) {
                return inputs(x.itemSized(right), x.itemSized(left));
            }

            default BUILDER inputs(Item right, Item left) {
                return inputs(x.ingredient(right), x.ingredient(left));
            }

            default BUILDER inputs(DeferredHolder<Item, Item> right, DeferredHolder<Item, Item> left) {
                return inputs(right.get(), left.get());
            }

            default BUILDER inputs(TagKey<Item> right, TagKey<Item> left) {
                return inputs(x.ingredient(right), x.ingredient(left));
            }

            default BUILDER inputs(TagKey<Item> right, Item left) {
                return inputs(x.ingredient(right), x.ingredient(left));
            }

            default BUILDER inputs(Item right, TagKey<Item> left) {
                return inputs(x.ingredient(right), x.ingredient(left));
            }

        }
    }

    public class Output {

        public static interface SimpleOutputItem<BUILDER extends BaseRecipeBuilder> extends BuilderAttach<BUILDER> {

            abstract BUILDER output(ItemStack output);

            default BUILDER output(Item output) {
                return output(x.item(output));
            }

            default BUILDER output(ItemLike output) {
                return output(x.item(output));
            }

            default BUILDER output(DeferredHolder<Item, Item> output) {
                return output(output.get());
            }

            default BUILDER output(Item output, int count) {
                return output(x.item(output, count));
            }

            default BUILDER output(DeferredHolder<Item, Item> output, int count) {
                return output(output.get(), count);
            }
        }

        public static interface SecondaryOutputItem<BUILDER extends BaseRecipeBuilder> extends BuilderAttach<BUILDER> {

            abstract BUILDER secondary(ItemStack output, float chance);

            /**
             * default chance of success -> 100%
             */
            default BUILDER secondary(ItemStack secondary) {
                return secondary(secondary, 1f);
            }

            default BUILDER secondary(DeferredHolder<Item, Item> secondary) {
                return secondary(secondary.get());
            }

            default BUILDER secondary(Item secondary) {
                return secondary(x.item(secondary));
            }

            default BUILDER secondary(Item secondary, int count) {
                return secondary(x.item(secondary, count));
            }

            default BUILDER secondary(DeferredHolder<Item, Item> secondary, int count) {
                return secondary(secondary.get(), count);
            }

            default BUILDER secondary(Item secondary, float chance) {
                return secondary(x.item(secondary), chance);
            }

            default BUILDER secondary(DeferredHolder<Item, Item> secondary, float chance) {
                return secondary(secondary.get(), chance);
            }

            default BUILDER secondary(Item secondary, int count, float chance) {
                return secondary(x.item(secondary, count), chance);
            }

            default BUILDER secondary(DeferredHolder<Item, Item> secondary, int count, float chance) {
                return secondary(secondary.get(), count, chance);
            }
        }

        public static interface ListedOutputItemStack<BUILDER extends BaseRecipeBuilder>
                extends BuilderAttach<BUILDER> {

            abstract BUILDER output(List<ItemStack> output);

            default BUILDER output(ItemStack output) {
                return output(List.of(output));
            }

            default BUILDER output(ItemStack... output) {
                return output(List.of(output));
            }

            default BUILDER output(Item output) {
                return output(x.item(output));
            }

            default BUILDER output(Item... output) {
                return output(List.of(output).stream().map(i -> x.item(i)).toList());
            }

            default BUILDER output(DeferredHolder<Item, Item> output) {
                return output(output.get());
            }

            default BUILDER output(ItemLike output) {
                return output(x.item(output));
            }

            default BUILDER output(DeferredHolder<Item, Item>... output) {
                return output(List.of(output).stream().map(DeferredHolder::get).map(ItemStack::new).toList());
            }

            default BUILDER output(ItemLike... output) {
                return output(List.of(output).stream().map(ItemStack::new).toList());
            }
        }
    }

}
