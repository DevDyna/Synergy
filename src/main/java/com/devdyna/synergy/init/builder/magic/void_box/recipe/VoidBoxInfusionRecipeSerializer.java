package com.devdyna.synergy.init.builder.magic.void_box.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class VoidBoxInfusionRecipeSerializer implements RecipeSerializer<VoidBoxInfusionRecipe> {

        public static final MapCodec<VoidBoxInfusionRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                        Ingredient.CODEC.fieldOf("input").forGetter(VoidBoxInfusionRecipe::getInput),
                        ItemStack.CODEC.fieldOf("output").forGetter(VoidBoxInfusionRecipe::getOutput))
                        .apply(inst, VoidBoxInfusionRecipe::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, VoidBoxInfusionRecipe> STREAM_CODEC = StreamCodec
                        .composite(
                                        Ingredient.CONTENTS_STREAM_CODEC, VoidBoxInfusionRecipe::getInput,
                                        ItemStack.STREAM_CODEC, VoidBoxInfusionRecipe::getOutput,
                                        VoidBoxInfusionRecipe::new);

        @Override
        public MapCodec<VoidBoxInfusionRecipe> codec() {
                return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, VoidBoxInfusionRecipe> streamCodec() {
                return STREAM_CODEC;
        }
}
