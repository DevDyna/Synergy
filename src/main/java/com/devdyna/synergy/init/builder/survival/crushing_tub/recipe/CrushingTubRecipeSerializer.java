package com.devdyna.synergy.init.builder.survival.crushing_tub.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.neoforged.neoforge.fluids.FluidStack;
import com.devdyna.synergy.api.utils.RecipeUtils;

public class CrushingTubRecipeSerializer implements RecipeSerializer<CrushingTubRecipe> {

        public static final MapCodec<CrushingTubRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                        Ingredient.CODEC.fieldOf("input").forGetter(CrushingTubRecipe::getInput),
                        ItemStack.CODEC.optionalFieldOf("output",ItemStack.EMPTY)
                        .forGetter(r -> RecipeUtils.optionalCodec(r.getOutput())),
                        FluidStack.CODEC.optionalFieldOf("fluid",FluidStack.EMPTY)
                        .forGetter(r -> RecipeUtils.optionalCodec(r.getFluid())))
                        .apply(inst, CrushingTubRecipe::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, CrushingTubRecipe> STREAM_CODEC = StreamCodec
                        .composite(
                                        Ingredient.CONTENTS_STREAM_CODEC, CrushingTubRecipe::getInput,
                                        ItemStack.OPTIONAL_STREAM_CODEC, CrushingTubRecipe::getOutput,
                                        FluidStack.OPTIONAL_STREAM_CODEC,CrushingTubRecipe::getFluid,
                                        CrushingTubRecipe::new);

        @Override
        public MapCodec<CrushingTubRecipe> codec() {
                return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, CrushingTubRecipe> streamCodec() {
                return STREAM_CODEC;
        }
}
