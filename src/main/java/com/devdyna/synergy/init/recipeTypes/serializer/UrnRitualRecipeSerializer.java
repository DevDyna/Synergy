package com.devdyna.synergy.init.recipeTypes.serializer;

import com.devdyna.synergy.init.recipeTypes.type.UrnRitualRecipe;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class UrnRitualRecipeSerializer implements RecipeSerializer<UrnRitualRecipe> {

    public static final MapCodec<UrnRitualRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
            ItemStack.CODEC.fieldOf("ingredient").forGetter(UrnRitualRecipe::getInput),
            ItemStack.CODEC.fieldOf("result").forGetter(UrnRitualRecipe::getOutput)
            ).apply(inst, UrnRitualRecipe::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, UrnRitualRecipe> STREAM_CODEC =
            StreamCodec.composite(
                    ItemStack.STREAM_CODEC, UrnRitualRecipe::getInput,
                    ItemStack.STREAM_CODEC, UrnRitualRecipe::getOutput,
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
