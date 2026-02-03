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

public class FoundryRecipeSerializer implements RecipeSerializer<FoundryRecipe> {

        public static final MapCodec<FoundryRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                        Ingredient.CODEC.fieldOf("input").forGetter(FoundryRecipe::getInput),
                        Codec.intRange(1, Integer.MAX_VALUE).fieldOf("ticks").forGetter(FoundryRecipe::getTicks),
                        FluidStack.CODEC.fieldOf("fluid").forGetter(FoundryRecipe::getFluid))
                        .apply(inst, FoundryRecipe::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, FoundryRecipe> STREAM_CODEC = StreamCodec
                        .composite(
                                        Ingredient.CONTENTS_STREAM_CODEC, FoundryRecipe::getInput,
                                        ByteBufCodecs.INT, FoundryRecipe::getTicks,
                                        FluidStack.STREAM_CODEC, FoundryRecipe::getFluid,
                                        FoundryRecipe::new);

        @Override
        public MapCodec<FoundryRecipe> codec() {
                return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, FoundryRecipe> streamCodec() {
                return STREAM_CODEC;
        }
}
