package com.devdyna.synergy.init.recipeTypes.serializer;

import com.devdyna.synergy.api.codec.BetterThanBlockStates;
import com.devdyna.synergy.init.recipeTypes.type.FluidProviderRecipe;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.fluids.FluidStack;

public class FluidProviderRecipeSerializer implements RecipeSerializer<FluidProviderRecipe> {

    public static final MapCodec<FluidProviderRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
            BlockState.CODEC.fieldOf("core").forGetter(FluidProviderRecipe::getCore),
            BlockState.CODEC.fieldOf("below").forGetter(FluidProviderRecipe::getBelow),
            BlockState.CODEC.fieldOf("left").forGetter(FluidProviderRecipe::getLeft),
            BlockState.CODEC.fieldOf("right").forGetter(FluidProviderRecipe::getRight),
            FluidStack.CODEC.fieldOf("result").forGetter(FluidProviderRecipe::getOutput))
            .apply(inst, FluidProviderRecipe::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, FluidProviderRecipe> STREAM_CODEC = StreamCodec.composite(
            BetterThanBlockStates.STREAM_CODEC, FluidProviderRecipe::getCore,
            BetterThanBlockStates.STREAM_CODEC, FluidProviderRecipe::getBelow,
            BetterThanBlockStates.STREAM_CODEC, FluidProviderRecipe::getLeft,
            BetterThanBlockStates.STREAM_CODEC, FluidProviderRecipe::getRight,
            FluidStack.STREAM_CODEC, FluidProviderRecipe::getOutput,
            FluidProviderRecipe::new);

    @Override
    public MapCodec<FluidProviderRecipe> codec() {
        return CODEC;
    }

    @Override
    public StreamCodec<RegistryFriendlyByteBuf, FluidProviderRecipe> streamCodec() {
        return STREAM_CODEC;
    }

}
