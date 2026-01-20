package com.devdyna.synergy.init.builder.industrial_machines.caster.recipe;

import java.util.Optional;
import com.devdyna.synergy.api.MachineType;
import com.devdyna.synergy.api.machine.BaseMachineBE;
import com.devdyna.synergy.api.machine.BaseMachineBlock;
import com.devdyna.synergy.api.machine.BaseMachineMenu;
import com.devdyna.synergy.api.machine.recipe.BaseMachineRecipeType;
import com.devdyna.synergy.common.recipes.input.FluidInput;
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
import net.neoforged.neoforge.fluids.FluidStack;

@SuppressWarnings("null")
public class CasterRecipeType extends BaseMachineRecipeType<FluidInput> {

    public CasterRecipeType(int ticks, int energy, FluidStack fluid, Ingredient input,
            ItemStack output) {
        this.input = input;
        this.ticks = ticks;
        this.energy = energy;
        this.fluid_input = fluid;
        this.output = output;
    }

    public static CasterRecipeType of(int ticks, int energy, FluidStack fluid, Ingredient input,
            ItemStack output) {
        return new CasterRecipeType(ticks, energy, fluid, input, output);
    }

    @Override
    public boolean hasSecondaryOutput() {
        return false;
    }

    @Override
    public MachineType<? extends BaseMachineBlock, ? extends BaseMachineBE, ? extends BaseMachineMenu, ? extends BaseMachineRecipeType<FluidInput>> getMachine() {
        return zMachines.CASTING_FACTORY;
    }

    @Override
    public ItemStack getRecipeInput(FluidInput recipe) {
        return recipe.item();
    }

    @Override
    public FluidStack getRecipeFluidInput(FluidInput recipe) {
        return recipe.input();
    }

    public static class Serializer implements RecipeSerializer<CasterRecipeType> {

        public static final MapCodec<CasterRecipeType> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                Codec.intRange(1, Integer.MAX_VALUE).fieldOf("ticks").forGetter(CasterRecipeType::getTime),
                Codec.intRange(1, Integer.MAX_VALUE).fieldOf("energy").forGetter(CasterRecipeType::getEnergy),

                FluidStack.CODEC.fieldOf("input_fluid").forGetter(CasterRecipeType::getFluidInput),

                Ingredient.CODEC.optionalFieldOf("input_item", Ingredient.EMPTY)
                        .forGetter(r -> (r.getInputItem() == null || r.getInputItem().isEmpty())
                                ? Ingredient.EMPTY
                                : r.getInputItem()),

                ItemStack.CODEC.fieldOf("output").forGetter(CasterRecipeType::getOutputItem)

        )
                .apply(inst, CasterRecipeType::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, CasterRecipeType> STREAM_CODEC = StreamCodec
                .composite(

                        ByteBufCodecs.INT, CasterRecipeType::getTime,
                        ByteBufCodecs.INT, CasterRecipeType::getEnergy,
                        FluidStack.STREAM_CODEC, CasterRecipeType::getFluidInput,

                        ByteBufCodecs.optional(Ingredient.CONTENTS_STREAM_CODEC),
                        r -> (r.getInputItem() == null || r.getInputItem().isEmpty())
                                ? Optional.empty()
                                : Optional.of(r.getInputItem()),

                        ItemStack.STREAM_CODEC, CasterRecipeType::getOutputItem,

                        (ticks, energy, f, i, o) -> new CasterRecipeType(
                                ticks,
                                energy,
                                f,
                                i.orElse(Ingredient.EMPTY),
                                o));

        @Override
        public MapCodec<CasterRecipeType> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, CasterRecipeType> streamCodec() {
            return STREAM_CODEC;
        }

    }

}
