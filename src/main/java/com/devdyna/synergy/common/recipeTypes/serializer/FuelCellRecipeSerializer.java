package com.devdyna.synergy.common.recipeTypes.serializer;

import com.devdyna.synergy.common.recipeTypes.type.FuelCellRecipe;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class FuelCellRecipeSerializer implements RecipeSerializer<FuelCellRecipe> {

    public static final MapCodec<FuelCellRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
            Ingredient.CODEC_NONEMPTY.fieldOf("ingredient").forGetter(FuelCellRecipe::getInput),
            ItemStack.CODEC.fieldOf("result").forGetter(FuelCellRecipe::getOutput),
            Codec.INT.fieldOf("ticks").forGetter(FuelCellRecipe::getDuration),
            Codec.INT.fieldOf("fe").forGetter(FuelCellRecipe::getFe),
            Codec.DOUBLE.fieldOf("heat").forGetter(FuelCellRecipe::getHeat)
            ).apply(inst, FuelCellRecipe::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, FuelCellRecipe> STREAM_CODEC =
            StreamCodec.composite(
                    Ingredient.CONTENTS_STREAM_CODEC, FuelCellRecipe::getInput,
                    ItemStack.STREAM_CODEC, FuelCellRecipe::getOutput,
                    ByteBufCodecs.INT,FuelCellRecipe::getDuration,
                    ByteBufCodecs.INT,FuelCellRecipe::getFe,
                    ByteBufCodecs.DOUBLE,FuelCellRecipe::getHeat,
                    FuelCellRecipe::new);

    @Override
    public MapCodec<FuelCellRecipe> codec() {
        return CODEC;
    }

    @Override
    public StreamCodec<RegistryFriendlyByteBuf, FuelCellRecipe> streamCodec() {
        return STREAM_CODEC;
    }

}
