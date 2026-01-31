package com.devdyna.synergy.init.builder.survival.drying_rack.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class DryingRackRecipeSerializer implements RecipeSerializer<DryingRackRecipe> {

        public static final MapCodec<DryingRackRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                        Ingredient.CODEC.fieldOf("input").forGetter(DryingRackRecipe::getInput),
                        Codec.intRange(1, Integer.MAX_VALUE).fieldOf("ticks").forGetter(DryingRackRecipe::getTicks),
                        ItemStack.CODEC.fieldOf("output").forGetter(DryingRackRecipe::getOutput))
                        .apply(inst, DryingRackRecipe::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, DryingRackRecipe> STREAM_CODEC = StreamCodec
                        .composite(
                                        Ingredient.CONTENTS_STREAM_CODEC, DryingRackRecipe::getInput,
                                        ByteBufCodecs.INT, DryingRackRecipe::getTicks,
                                        ItemStack.STREAM_CODEC, DryingRackRecipe::getOutput,
                                        DryingRackRecipe::new);

        @Override
        public MapCodec<DryingRackRecipe> codec() {
                return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, DryingRackRecipe> streamCodec() {
                return STREAM_CODEC;
        }
}
