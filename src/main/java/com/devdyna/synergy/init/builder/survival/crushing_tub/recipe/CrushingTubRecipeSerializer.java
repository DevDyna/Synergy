package com.devdyna.synergy.init.builder.survival.crushing_tub.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.neoforged.neoforge.fluids.FluidStack;

import com.devdyna.synergy.api.codec.ChanceOutputItem;
import com.devdyna.synergy.api.utils.RecipeUtils;

public class CrushingTubRecipeSerializer implements RecipeSerializer<CrushingTubRecipe> {

        public static final MapCodec<CrushingTubRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                        Ingredient.CODEC.fieldOf("input").forGetter(CrushingTubRecipe::getInput),
                        ChanceOutputItem.CODEC.optionalFieldOf("output")
                        .forGetter(r -> ChanceOutputItem.optional(r.getOutput())),
                        FluidStack.CODEC.optionalFieldOf("fluid",FluidStack.EMPTY)
                        .forGetter(r -> RecipeUtils.optionalCodec(r.getFluid())))
                        .apply(inst, (i,o,f)-> new CrushingTubRecipe(i,o.orElse(null),f)));

        public static final StreamCodec<RegistryFriendlyByteBuf, CrushingTubRecipe> STREAM_CODEC = StreamCodec
                        .composite(
                                        Ingredient.CONTENTS_STREAM_CODEC, CrushingTubRecipe::getInput,
                                        ByteBufCodecs.optional(ChanceOutputItem.STREAM_CODEC), r -> ChanceOutputItem.optional(r.getOutput()),
                                        FluidStack.OPTIONAL_STREAM_CODEC,CrushingTubRecipe::getFluid,
                                        (i,o,f)-> new CrushingTubRecipe(i,o.orElse(null),f));

        @Override
        public MapCodec<CrushingTubRecipe> codec() {
                return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, CrushingTubRecipe> streamCodec() {
                return STREAM_CODEC;
        }
}
