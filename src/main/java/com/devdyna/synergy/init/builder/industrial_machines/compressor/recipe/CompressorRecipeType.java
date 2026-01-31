package com.devdyna.synergy.init.builder.industrial_machines.compressor.recipe;

import java.util.Optional;

import com.devdyna.synergy.api.machine.BaseMachineBE;
import com.devdyna.synergy.api.machine.BaseMachineBlock;
import com.devdyna.synergy.api.machine.BaseMachineMenu;
import com.devdyna.synergy.api.machine.recipe.BaseMachineRecipeType;
import com.devdyna.synergy.api.registers.MachineType;
import com.devdyna.synergy.api.utils.x;
import com.devdyna.synergy.common.recipes.input.BiItemInput;
import com.devdyna.synergy.init.types.zMachines;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.neoforged.neoforge.common.crafting.SizedIngredient;

@SuppressWarnings("null")
public class CompressorRecipeType extends BaseMachineRecipeType<BiItemInput> {

    public CompressorRecipeType(int ticks, int energy, SizedIngredient input, SizedIngredient plate,
            boolean consumeCatalyst, ItemStack output) {
        this.input = input;
        this.optional_input = plate;
        this.ticks = ticks;
        this.output = output;
        this.energy = energy;
        this.consumeCatalyst = consumeCatalyst;
    }

    public static CompressorRecipeType of(int ticks, int energy, SizedIngredient input,
            SizedIngredient plate, boolean consumeCatalyst, ItemStack output) {
        return new CompressorRecipeType(ticks, energy, input, plate, consumeCatalyst, output);
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

                SizedIngredient.FLAT_CODEC.fieldOf("input").forGetter(CompressorRecipeType::getInputItem),
                SizedIngredient.FLAT_CODEC.optionalFieldOf("plate", x.itemSized())
                        .forGetter(r -> (r.getCatalystItem() == null || x.getItems(r.getCatalystItem()).isEmpty())
                                ? x.itemSized()
                                : r.getCatalystItem()),
                Codec.BOOL.fieldOf("consume_catalyst").forGetter(CompressorRecipeType::consumeCatalyst),
                ItemStack.CODEC.fieldOf("output").forGetter(CompressorRecipeType::getOutputItem))
                .apply(inst, CompressorRecipeType::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, CompressorRecipeType> STREAM_CODEC = StreamCodec
                .composite(
                        ByteBufCodecs.INT, CompressorRecipeType::getTime,
                        ByteBufCodecs.INT, CompressorRecipeType::getEnergy,
                        SizedIngredient.STREAM_CODEC, CompressorRecipeType::getInputItem,
                        ByteBufCodecs.optional(SizedIngredient.STREAM_CODEC),
                        r -> (r.getCatalystItem() == null || x.getItems(r.getCatalystItem()).isEmpty())
                                ? Optional.empty()
                                : Optional.of(r.getCatalystItem()),
                        ByteBufCodecs.BOOL, CompressorRecipeType::consumeCatalyst,
                        ItemStack.STREAM_CODEC, CompressorRecipeType::getOutputItem,

                        (t, e, i, o, s, c) -> new CompressorRecipeType(t, e, i, o.orElse(x.itemSized()), s, c));

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
