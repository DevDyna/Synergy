package com.devdyna.synergy.init.builder.survival.simple_melter.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.neoforged.neoforge.fluids.FluidStack;

public class SimpleMelterRecipeSerializer implements RecipeSerializer<SimpleMelterRecipe> {

        public static final MapCodec<SimpleMelterRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                        Ingredient.CODEC.fieldOf("input").forGetter(SimpleMelterRecipe::getInput),
                        Codec.intRange(1, Integer.MAX_VALUE).fieldOf("ticks").forGetter(SimpleMelterRecipe::getTicks),
                        FluidStack.CODEC.fieldOf("fluid").forGetter(SimpleMelterRecipe::getFluid))
                        .apply(inst, SimpleMelterRecipe::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, SimpleMelterRecipe> STREAM_CODEC = StreamCodec
                        .composite(
                                        Ingredient.CONTENTS_STREAM_CODEC, SimpleMelterRecipe::getInput,
                                        ByteBufCodecs.INT, SimpleMelterRecipe::getTicks,
                                        FluidStack.STREAM_CODEC, SimpleMelterRecipe::getFluid,
                                        SimpleMelterRecipe::new);

        @Override
        public MapCodec<SimpleMelterRecipe> codec() {
                return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, SimpleMelterRecipe> streamCodec() {
                return STREAM_CODEC;
        }
}
