package com.devdyna.synergy.init.recipeTypes.serializer;

import com.devdyna.synergy.api.codec.BetterThanBlockStates;
import com.devdyna.synergy.init.recipeTypes.type.ItemUseRecipe;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.block.state.BlockState;

public class ItemUseRecipeSerializer implements RecipeSerializer<ItemUseRecipe> {

    public static final MapCodec<ItemUseRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
            Ingredient.CODEC.fieldOf("item").forGetter(ItemUseRecipe::getInputItem),
            BlockState.CODEC.fieldOf("base").forGetter(ItemUseRecipe::getInputState),
            BlockState.CODEC.fieldOf("result").forGetter(ItemUseRecipe::getOutputState))
            .apply(inst, ItemUseRecipe::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, ItemUseRecipe> STREAM_CODEC = StreamCodec.composite(
            Ingredient.CONTENTS_STREAM_CODEC, ItemUseRecipe::getInputItem,
            BetterThanBlockStates.STREAM_CODEC, ItemUseRecipe::getInputState,
            BetterThanBlockStates.STREAM_CODEC, ItemUseRecipe::getOutputState,
            ItemUseRecipe::new);

    @Override
    public MapCodec<ItemUseRecipe> codec() {
        return CODEC;
    }

    @Override
    public StreamCodec<RegistryFriendlyByteBuf, ItemUseRecipe> streamCodec() {
        return STREAM_CODEC;
    }

}
