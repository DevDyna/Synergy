package com.devdyna.synergy.init.builder.industrial_machines.melter.recipe;

import com.devdyna.synergy.api.MachineType;
import com.devdyna.synergy.api.machine.BaseMachineBE;
import com.devdyna.synergy.api.machine.BaseMachineBlock;
import com.devdyna.synergy.api.machine.BaseMachineMenu;
import com.devdyna.synergy.api.machine.recipe.BaseMachineRecipeType;
import com.devdyna.synergy.common.recipes.input.MonoItemInput;
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
import net.neoforged.neoforge.fluids.FluidStack;

@SuppressWarnings("null")
public class MelterRecipeType extends BaseMachineRecipeType<MonoItemInput> {

    public MelterRecipeType(int ticks, int energy, SizedIngredient input, FluidStack fluid) {
        this.input = input;
        this.ticks = ticks;
        this.energy = energy;
        this.fluid_output = fluid;
    }

    public static MelterRecipeType of(int ticks, int energy, SizedIngredient input,
            FluidStack fluid) {
        return new MelterRecipeType(ticks, energy, input, fluid);
    }

    @Override
    public boolean hasSecondaryOutput() {
        return false;
    }

    @Override
    public MachineType<? extends BaseMachineBlock, ? extends BaseMachineBE, ? extends BaseMachineMenu, ? extends BaseMachineRecipeType<MonoItemInput>> getMachine() {
        return zMachines.MELTER;
    }

    @Override
    public ItemStack getRecipeInput(MonoItemInput recipe) {
        return recipe.input();
    }

    public static class Serializer implements RecipeSerializer<MelterRecipeType> {

        public static final MapCodec<MelterRecipeType> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                Codec.intRange(1, Integer.MAX_VALUE).fieldOf("ticks").forGetter(MelterRecipeType::getTime),
                Codec.intRange(1, Integer.MAX_VALUE).fieldOf("energy").forGetter(MelterRecipeType::getEnergy),

                SizedIngredient.FLAT_CODEC.fieldOf("input").forGetter(MelterRecipeType::getInputItem),

                FluidStack.CODEC.fieldOf("fluid").forGetter(MelterRecipeType::getFluidOutput))
                .apply(inst, MelterRecipeType::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, MelterRecipeType> STREAM_CODEC = StreamCodec
                .composite(

                        ByteBufCodecs.INT, MelterRecipeType::getTime,
                        ByteBufCodecs.INT, MelterRecipeType::getEnergy,
                        SizedIngredient.STREAM_CODEC, MelterRecipeType::getInputItem,
                        FluidStack.STREAM_CODEC, MelterRecipeType::getFluidOutput,
                        (ticks, energy, input, c) -> new MelterRecipeType(
                                ticks,
                                energy,
                                input,
                                c));

        @Override
        public MapCodec<MelterRecipeType> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, MelterRecipeType> streamCodec() {
            return STREAM_CODEC;
        }

    }

}
