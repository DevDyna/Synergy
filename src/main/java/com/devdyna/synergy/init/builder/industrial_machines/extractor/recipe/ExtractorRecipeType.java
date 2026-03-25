package com.devdyna.synergy.init.builder.industrial_machines.extractor.recipe;

import java.util.Optional;

import com.devdyna.synergy.api.blockfactories.machine.BaseMachineBE;
import com.devdyna.synergy.api.blockfactories.machine.BaseMachineBlock;
import com.devdyna.synergy.api.blockfactories.machine.BaseMachineMenu;
import com.devdyna.synergy.api.blockfactories.machine.recipe.BaseMachineRecipeType;
import com.devdyna.synergy.api.codec.ChanceOutputItem;
import com.devdyna.synergy.api.recipes.inputs.MonoItemInput;
import com.devdyna.synergy.api.registers.MachineType;
import com.devdyna.synergy.api.utils.RecipeUtils;
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
public class ExtractorRecipeType extends BaseMachineRecipeType<MonoItemInput> {

    public ExtractorRecipeType(int ticks, int energy, SizedIngredient input,
            ChanceOutputItem secondary, FluidStack fluid) {
        this.input = input;
        this.ticks = ticks;
        this.optional_output_item = secondary;
        this.energy = energy;
        this.fluid_output = fluid;
    }

    public static ExtractorRecipeType of(int ticks, int energy, SizedIngredient input,
            ChanceOutputItem secondary, FluidStack fluid) {
        return new ExtractorRecipeType(ticks, energy, input, secondary, fluid);
    }

    // @Override
    // public boolean hasSecondaryOutput() {
    //     return true;
    // }

    @Override
    public MachineType<? extends BaseMachineBlock, ? extends BaseMachineBE, ? extends BaseMachineMenu, ? extends BaseMachineRecipeType<MonoItemInput>> getMachine() {
        return zMachines.EXTRACTOR;
    }

    @Override
    public ItemStack getRecipeInput(MonoItemInput recipe) {
        return recipe.input();
    }

    public static class Serializer implements RecipeSerializer<ExtractorRecipeType> {

        public static final MapCodec<ExtractorRecipeType> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                Codec.intRange(1, Integer.MAX_VALUE).fieldOf("ticks").forGetter(ExtractorRecipeType::getTime),
                Codec.intRange(1, Integer.MAX_VALUE).fieldOf("energy").forGetter(ExtractorRecipeType::getEnergy),

                SizedIngredient.FLAT_CODEC.fieldOf("input").forGetter(ExtractorRecipeType::getInputItem),

                ChanceOutputItem.CODEC.optionalFieldOf("secondary_item")
                        .forGetter(r -> ChanceOutputItem.optional(r.getSecondaryOutputItem())),
                FluidStack.CODEC.optionalFieldOf("optional_fluid", FluidStack.EMPTY)
                        .forGetter(r -> RecipeUtils.optionalCodec(r.getFluidOutput())))
                .apply(inst, (ticks, energy, input, secondary,fluid) -> new ExtractorRecipeType(
                        ticks,
                        energy,
                        input,
                        secondary.orElse(null),
                        fluid)));

        public static final StreamCodec<RegistryFriendlyByteBuf, ExtractorRecipeType> STREAM_CODEC = StreamCodec
                .composite(

                        ByteBufCodecs.INT, ExtractorRecipeType::getTime,
                        ByteBufCodecs.INT, ExtractorRecipeType::getEnergy,
                        SizedIngredient.STREAM_CODEC, ExtractorRecipeType::getInputItem,

                        ByteBufCodecs.optional(ChanceOutputItem.STREAM_CODEC),
                        r -> ChanceOutputItem.optional(r.getSecondaryOutputItem()),
                        ByteBufCodecs.optional(FluidStack.STREAM_CODEC),
                        r -> (r.getFluidOutput() == null || r.getFluidOutput().isEmpty())
                                ? Optional.empty()
                                : Optional.of(r.getFluidOutput()),
                        (ticks, energy, input, secondary,fluid) -> new ExtractorRecipeType(
                        ticks,
                        energy,
                        input,
                        secondary.orElse(null),
                        fluid.orElse(null)));

        @Override
        public MapCodec<ExtractorRecipeType> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, ExtractorRecipeType> streamCodec() {
            return STREAM_CODEC;
        }

    }

}
