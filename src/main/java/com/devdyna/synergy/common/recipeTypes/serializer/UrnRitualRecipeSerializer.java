package com.devdyna.synergy.common.recipeTypes.serializer;

import com.devdyna.synergy.common.recipeTypes.type.UrnRitualRecipe;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class UrnRitualRecipeSerializer implements RecipeSerializer<UrnRitualRecipe> {

    public static final MapCodec<UrnRitualRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
            Ingredient.LIST_CODEC_NONEMPTY.fieldOf("ingredient").forGetter(UrnRitualRecipe::getIngredients),
            ItemStack.CODEC.fieldOf("result").forGetter(UrnRitualRecipe::getResultItem)
            ).apply(inst, UrnRitualRecipe::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, UrnRitualRecipe> STREAM_CODEC =
            StreamCodec.composite(
                    Ingredient.CONTENTS_STREAM_CODEC.apply(ByteBufCodecs.list()), UrnRitualRecipe::getIngredients,
                    ItemStack.STREAM_CODEC, UrnRitualRecipe::getResultItem,
                    UrnRitualRecipe::new);

    @Override
    public MapCodec<UrnRitualRecipe> codec() {
        return CODEC;
    }

    @Override
    public StreamCodec<RegistryFriendlyByteBuf, UrnRitualRecipe> streamCodec() {
        return STREAM_CODEC;
    }

}
