package com.devdyna.synergy.init.builder.survival.evaporation_basin.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.neoforged.neoforge.fluids.crafting.SizedFluidIngredient;

public class EvaporationBasinRecipeSerializer implements RecipeSerializer<EvaporationBasinRecipe> {

        public static final MapCodec<EvaporationBasinRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                        SizedFluidIngredient.FLAT_CODEC.fieldOf("fluid").forGetter(EvaporationBasinRecipe::getFluid),
                        Codec.intRange(1,Integer.MAX_VALUE).fieldOf("ticks").forGetter(EvaporationBasinRecipe::getTicks),
                        ItemStack.CODEC.fieldOf("output").forGetter(EvaporationBasinRecipe::getOutput)
                        ).apply(inst, EvaporationBasinRecipe::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, EvaporationBasinRecipe> STREAM_CODEC = StreamCodec
                        .composite(
                                        SizedFluidIngredient.STREAM_CODEC,EvaporationBasinRecipe::getFluid,
                                        ByteBufCodecs.INT,EvaporationBasinRecipe::getTicks,
                                        ItemStack.STREAM_CODEC,EvaporationBasinRecipe::getOutput,
                                        EvaporationBasinRecipe::new);

        @Override
        public MapCodec<EvaporationBasinRecipe> codec() {
                return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, EvaporationBasinRecipe> streamCodec() {
                return STREAM_CODEC;
        }
}
