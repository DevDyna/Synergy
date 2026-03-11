package com.devdyna.synergy.init.builder.magic.urn.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.neoforged.neoforge.common.crafting.SizedIngredient;

public class UrnRitualRecipeSerializer implements RecipeSerializer<UrnRitualRecipe> {

    public static final MapCodec<UrnRitualRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
            SizedIngredient.FLAT_CODEC.listOf().fieldOf("ingredients").forGetter(UrnRitualRecipe::getInputs),
            ItemStack.CODEC.fieldOf("result").forGetter(UrnRitualRecipe::getOutput)
            ).apply(inst, UrnRitualRecipe::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, UrnRitualRecipe> STREAM_CODEC =
            StreamCodec.composite(
                    SizedIngredient.STREAM_CODEC.apply(ByteBufCodecs.list()), UrnRitualRecipe::getInputs,
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
