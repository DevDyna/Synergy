package com.devdyna.synergy.common.recipes.serializer;

import com.devdyna.synergy.common.recipes.type.QuernMillingRecipe;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class QuernMillingRecipeSerializer implements RecipeSerializer<QuernMillingRecipe> {

    public static final MapCodec<QuernMillingRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
            Ingredient.CODEC.fieldOf("ingredient").forGetter(QuernMillingRecipe::getInput),
            ItemStack.CODEC.fieldOf("result").forGetter(QuernMillingRecipe::getOutput),
                        Codec.intRange(1, Integer.MAX_VALUE).fieldOf("frequence").forGetter(QuernMillingRecipe::getTime)
            ).apply(inst, QuernMillingRecipe::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, QuernMillingRecipe> STREAM_CODEC =
            StreamCodec.composite(
                    Ingredient.CONTENTS_STREAM_CODEC, QuernMillingRecipe::getInput,
                    ItemStack.STREAM_CODEC, QuernMillingRecipe::getOutput,
                    ByteBufCodecs.INT, QuernMillingRecipe::getTime,
                    QuernMillingRecipe::new);

    @Override
    public MapCodec<QuernMillingRecipe> codec() {
        return CODEC;
    }

    @Override
    public StreamCodec<RegistryFriendlyByteBuf, QuernMillingRecipe> streamCodec() {
        return STREAM_CODEC;
    }

}
