package com.devdyna.synergy.init.builder.industrial_machines.extractor.recipe;

import java.util.Optional;
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
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.neoforged.neoforge.fluids.FluidStack;

@SuppressWarnings("null")
public class ExtractorRecipeType extends BaseMachineRecipeType<MonoItemInput> {

    public ExtractorRecipeType(int ticks, int energy, Ingredient input,
            ItemStack secondary, FluidStack fluid, float chance) {
        this.input = input;
        this.ticks = ticks;
        this.secondary = secondary;
        this.energy = energy;
        this.fluid_output = fluid;
        this.chance = chance;
    }

    public static ExtractorRecipeType of(int ticks, int energy, Ingredient input,
            ItemStack secondary, FluidStack fluid, float chance) {
        return new ExtractorRecipeType(ticks, energy, input, secondary, fluid, chance);
    }

    @Override
    public boolean hasSecondaryOutput() {
        return true;
    }

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

                Ingredient.CODEC.fieldOf("input").forGetter(ExtractorRecipeType::getInputItem),

                ItemStack.CODEC.optionalFieldOf("output_item", ItemStack.EMPTY)
                        .forGetter(r -> (r.getSecondaryItem() == null || r.getSecondaryItem().isEmpty())
                                ? ItemStack.EMPTY
                                : r.getSecondaryItem()),
                FluidStack.CODEC.optionalFieldOf("output_fluid", FluidStack.EMPTY)
                        .forGetter(r -> (r.getFluidOutput() == null || r.getFluidOutput().isEmpty())
                                ? FluidStack.EMPTY
                                : r.getFluidOutput()),
                Codec.floatRange(0, 1).fieldOf("chance").forGetter(ExtractorRecipeType::getSecondaryItemChance))
                .apply(inst, ExtractorRecipeType::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, ExtractorRecipeType> STREAM_CODEC = StreamCodec
                .composite(

                        ByteBufCodecs.INT, ExtractorRecipeType::getTime,
                        ByteBufCodecs.INT, ExtractorRecipeType::getEnergy,
                        Ingredient.CONTENTS_STREAM_CODEC, ExtractorRecipeType::getInputItem,

                        ByteBufCodecs.optional(ItemStack.STREAM_CODEC),
                        r -> (r.getSecondaryItem() == null || r.getSecondaryItem().isEmpty())
                                ? Optional.empty()
                                : Optional.of(r.getSecondaryItem()),
                        ByteBufCodecs.optional(FluidStack.STREAM_CODEC),
                        r -> (r.getFluidOutput() == null || r.getFluidOutput().isEmpty())
                                ? Optional.empty()
                                : Optional.of(r.getFluidOutput()),
                        ByteBufCodecs.FLOAT, ExtractorRecipeType::getSecondaryItemChance,
                        (ticks, energy, input, a, b, c) -> new ExtractorRecipeType(
                                ticks,
                                energy,
                                input,
                                a.orElse(ItemStack.EMPTY),
                                b.orElse(FluidStack.EMPTY),
                                c));

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
