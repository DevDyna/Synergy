package com.devdyna.synergy.init.recipeTypes.serializer;

import com.devdyna.synergy.api.codec.BetterThanBlockStates;
import com.devdyna.synergy.init.recipeTypes.type.ItemProviderRecipe;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.block.state.BlockState;

public class ItemProviderRecipeSerializer implements RecipeSerializer<ItemProviderRecipe<ItemStack>> {

    public static final MapCodec<ItemProviderRecipe<ItemStack>> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
            BlockState.CODEC.fieldOf("core").forGetter(ItemProviderRecipe::getCore),
            BlockState.CODEC.fieldOf("below").forGetter(ItemProviderRecipe::getBelow),
            BlockState.CODEC.fieldOf("left").forGetter(ItemProviderRecipe::getLeft),
            BlockState.CODEC.fieldOf("right").forGetter(ItemProviderRecipe::getRight),
            ItemStack.CODEC.fieldOf("result").forGetter(ItemProviderRecipe::getOutput))
            .apply(inst, ItemProviderRecipe::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, ItemProviderRecipe<ItemStack>> STREAM_CODEC = StreamCodec
            .composite(
                    BetterThanBlockStates.STREAM_CODEC, ItemProviderRecipe::getCore,
                    BetterThanBlockStates.STREAM_CODEC, ItemProviderRecipe::getBelow,
                    BetterThanBlockStates.STREAM_CODEC, ItemProviderRecipe::getLeft,
                    BetterThanBlockStates.STREAM_CODEC, ItemProviderRecipe::getRight,
                    ItemStack.STREAM_CODEC, ItemProviderRecipe::getOutput,
                    ItemProviderRecipe::new);

    @Override
    public MapCodec<ItemProviderRecipe<ItemStack>> codec() {
        return CODEC;
    }

    @Override
    public StreamCodec<RegistryFriendlyByteBuf, ItemProviderRecipe<ItemStack>> streamCodec() {
        return STREAM_CODEC;
    }

}
