package com.devdyna.synergy.common.recipeTypes.serializer;

import com.devdyna.synergy.common.recipeTypes.type.CropResultRecipe;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class CropResultRecipeSerializer implements RecipeSerializer<CropResultRecipe> {

    public static final MapCodec<CropResultRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
            Ingredient.CODEC.fieldOf("crop").forGetter(CropResultRecipe::getInput),
            ItemStack.CODEC.listOf().fieldOf("result").forGetter(CropResultRecipe::getOutputs))
            .apply(inst, CropResultRecipe::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, CropResultRecipe> STREAM_CODEC = StreamCodec.composite(
            Ingredient.CONTENTS_STREAM_CODEC, CropResultRecipe::getInput,
            ItemStack.LIST_STREAM_CODEC, CropResultRecipe::getOutputs,
            CropResultRecipe::new);

    @Override
    public MapCodec<CropResultRecipe> codec() {
        return CODEC;
    }

    @Override
    public StreamCodec<RegistryFriendlyByteBuf, CropResultRecipe> streamCodec() {
        return STREAM_CODEC;
    }

}
