package com.devdyna.synergy.init.builder.survival.placeable_bricks.recipe;

import com.devdyna.synergy.api.codec.BetterThanBlockStates;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.block.state.BlockState;

public class DryableBricksRecipeSerializer implements RecipeSerializer<DryableBricksRecipe> {

    public static final MapCodec<DryableBricksRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
            Ingredient.CODEC.fieldOf("input").forGetter(DryableBricksRecipe::getInput),
            BlockState.CODEC.fieldOf("block").forGetter(DryableBricksRecipe::getBlock),
            ItemStack.CODEC.fieldOf("output").forGetter(DryableBricksRecipe::getOutput))
            .apply(inst, DryableBricksRecipe::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, DryableBricksRecipe> STREAM_CODEC = StreamCodec.composite(
            Ingredient.CONTENTS_STREAM_CODEC, DryableBricksRecipe::getInput,
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
