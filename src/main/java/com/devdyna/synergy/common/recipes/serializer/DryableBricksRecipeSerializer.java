package com.devdyna.synergy.common.recipes.serializer;

import com.devdyna.synergy.api.codec.BetterThanBlockStates;
import com.devdyna.synergy.common.recipes.type.DryableBricksRecipe;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.block.state.BlockState;

public class DryableBricksRecipeSerializer implements RecipeSerializer<DryableBricksRecipe> {

    public static final MapCodec<DryableBricksRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
            ItemStack.CODEC.fieldOf("input").forGetter(DryableBricksRecipe::getInput),
            BlockState.CODEC.fieldOf("block").forGetter(DryableBricksRecipe::getBlock),
            ItemStack.CODEC.fieldOf("output").forGetter(DryableBricksRecipe::getOutput))
            .apply(inst, DryableBricksRecipe::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, DryableBricksRecipe> STREAM_CODEC = StreamCodec.composite(
            ItemStack.STREAM_CODEC, DryableBricksRecipe::getInput,
            BetterThanBlockStates.STREAM_CODEC, DryableBricksRecipe::getBlock,
            ItemStack.STREAM_CODEC, DryableBricksRecipe::getOutput,
            DryableBricksRecipe::new);

    @Override
    public MapCodec<DryableBricksRecipe> codec() {
        return CODEC;
    }

    @Override
    public StreamCodec<RegistryFriendlyByteBuf, DryableBricksRecipe> streamCodec() {
        return STREAM_CODEC;
    }

}
