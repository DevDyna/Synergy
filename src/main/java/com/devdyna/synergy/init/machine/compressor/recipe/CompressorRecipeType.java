package com.devdyna.synergy.init.machine.compressor.recipe;

import java.util.Optional;

import com.devdyna.synergy.api.MachineType;
import com.devdyna.synergy.api.machine.BaseMachineBE;
import com.devdyna.synergy.api.machine.BaseMachineBlock;
import com.devdyna.synergy.api.machine.BaseMachineMenu;
import com.devdyna.synergy.api.machine.recipe.BaseMachineRecipeType;
import com.devdyna.synergy.common.recipes.input.BiItemInput;
import com.devdyna.synergy.init.types.zMachines;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;

@SuppressWarnings("null")
public class CompressorRecipeType extends BaseMachineRecipeType<BiItemInput> {

    public CompressorRecipeType(int ticks, int energy, Ingredient input,Ingredient plate,
            ItemStack output ) {
        this.input = input;
        this.catalyst = plate;
        this.ticks = ticks;
        this.output = output;
        this.energy = energy;
  
    }

    public static CompressorRecipeType of(int ticks, int energy, Ingredient input,
            Ingredient plate, ItemStack output) {
        return new CompressorRecipeType(ticks, energy, input, plate, output);
    }

    @Override
    public boolean hasCatalyst() {
        return true;
    }

    @Override
    public MachineType<? extends BaseMachineBlock, ? extends BaseMachineBE, ? extends BaseMachineMenu, ? extends BaseMachineRecipeType<BiItemInput>> getMachine() {
        return zMachines.COMPRESSOR;
    }

    @Override
    public ItemStack getRecipeInput(BiItemInput recipe) {
        return recipe.first();
    }

    @Override
    public ItemStack getRecipeInput2(BiItemInput recipe) {
        return recipe.second();
    }

    public static class Serializer implements RecipeSerializer<CompressorRecipeType> {

        public static final MapCodec<CompressorRecipeType> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                Codec.intRange(1, Integer.MAX_VALUE).fieldOf("ticks").forGetter(CompressorRecipeType::getTime),
                Codec.intRange(1, Integer.MAX_VALUE).fieldOf("energy").forGetter(CompressorRecipeType::getEnergy),

                Ingredient.CODEC.fieldOf("input").forGetter(CompressorRecipeType::getInputItem),
                Ingredient.CODEC.optionalFieldOf("plate", Ingredient.EMPTY)
                        .forGetter(r -> (r.getCatalystItem() == null || r.getCatalystItem().isEmpty())
                                ? Ingredient.EMPTY
                                : r.getCatalystItem()),
                ItemStack.CODEC.fieldOf("output").forGetter(CompressorRecipeType::getOutputItem))
                .apply(inst, CompressorRecipeType::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, CompressorRecipeType> STREAM_CODEC = StreamCodec
                .composite(
                        ByteBufCodecs.INT, CompressorRecipeType::getTime,
                        ByteBufCodecs.INT, CompressorRecipeType::getEnergy,
                        Ingredient.CONTENTS_STREAM_CODEC, CompressorRecipeType::getInputItem,
                        ByteBufCodecs.optional(Ingredient.CONTENTS_STREAM_CODEC),
                        r -> (r.getCatalystItem() == null || r.getCatalystItem().isEmpty())
                                ? Optional.empty()
                                : Optional.of(r.getCatalystItem()),
                        ItemStack.STREAM_CODEC, CompressorRecipeType::getOutputItem,
                        
                        (t, e, i, o, s) -> new CompressorRecipeType(t, e, i, o.orElse(Ingredient.EMPTY), s));

        @Override
        public MapCodec<CompressorRecipeType> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, CompressorRecipeType> streamCodec() {
            return STREAM_CODEC;
        }

    }

}
