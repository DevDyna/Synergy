package com.devdyna.synergy.init.recipeTypes.serializer;

import com.devdyna.synergy.init.recipeTypes.type.CropResultRecipe;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class CropResultRecipeSerializer implements RecipeSerializer<CropResultRecipe> {

    public static final MapCodec<CropResultRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
            ItemStack.CODEC.fieldOf("crop").forGetter(CropResultRecipe::getInput),
            Ingredient.LIST_CODEC_NONEMPTY.fieldOf("result").forGetter(CropResultRecipe::getOutputs))
            .apply(inst, CropResultRecipe::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, CropResultRecipe> STREAM_CODEC = StreamCodec.composite(
            ItemStack.STREAM_CODEC, CropResultRecipe::getInput,
            Ingredient.CONTENTS_STREAM_CODEC.apply(ByteBufCodecs.list()), CropResultRecipe::getOutputs,
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
