package com.devdyna.synergy.init.builder.survival.fluid_mixer.recipe;

import java.util.List;

import com.devdyna.synergy.api.recipes.inputs.BiFluidInput;
import com.devdyna.synergy.api.recipes.types.BaseRecipeType;
import com.devdyna.synergy.api.registers.RecipeRegister;
import com.devdyna.synergy.api.utils.x;
import com.devdyna.synergy.init.types.zRecipeTypes;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.crafting.SizedFluidIngredient;

@SuppressWarnings("null")
public class FluidMixingRecipe extends BaseRecipeType<BiFluidInput> {

    private int ticks;
    private SizedFluidIngredient first;
    private SizedFluidIngredient second;
    private FluidStack output;

    public FluidMixingRecipe(SizedFluidIngredient first,SizedFluidIngredient second,int ticks,FluidStack output) {
        this.ticks = ticks;
        this.first = first;
        this.second = second;
        this.output = output;
    }

    public boolean matches(BiFluidInput r, Level l) {
        return first.test(r.first()) && second.test(r.second());
    }

    public ItemStack assemble(BiFluidInput i, HolderLookup.Provider r) {
        return x.item(output.getFluid().getBucket());
    }

    public NonNullList<Ingredient> getIngredients() {
        return NonNullList
                .copyOf(List.of(x.ingredient(x.getFluids(first).getFirst().getFluid().getBucket()),x.ingredient(x.getFluids(second).getFirst().getFluid().getBucket())));
    }

    @Override
    @Deprecated
    public ItemStack getResultItem(HolderLookup.Provider registryAccess) {
        return x.item(output.getFluid().getBucket());
    }

    @Override
    public RecipeRegister<? extends BaseRecipeType<BiFluidInput>> getRecipe() {
        return zRecipeTypes.FLUID_MIXING;
    }

    public int getTicks() {
        return ticks;
    }

    public SizedFluidIngredient getFirst() {
        return first;
    }

    public SizedFluidIngredient getSecond() {
        return second;
    }

    public FluidStack getOutput() {
        return output;
    }

    @Override
    public Item getToastIcon() {
        return output.getFluid().getBucket();
    }

    public static class Serializer implements RecipeSerializer<FluidMixingRecipe> {

        public static final MapCodec<FluidMixingRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst
                .group(
                        SizedFluidIngredient.FLAT_CODEC.fieldOf("left").forGetter(FluidMixingRecipe::getFirst),
                        SizedFluidIngredient.FLAT_CODEC.fieldOf("right").forGetter(FluidMixingRecipe::getSecond),
                        Codec.intRange(1, Integer.MAX_VALUE).fieldOf("ticks").forGetter(FluidMixingRecipe::getTicks),
                        FluidStack.CODEC.fieldOf("output")
                                .forGetter(FluidMixingRecipe::getOutput))
                .apply(inst, FluidMixingRecipe::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, FluidMixingRecipe> STREAM_CODEC = StreamCodec
                .composite(
                        SizedFluidIngredient.STREAM_CODEC, FluidMixingRecipe::getFirst,
                        SizedFluidIngredient.STREAM_CODEC, FluidMixingRecipe::getSecond,
                        ByteBufCodecs.INT, FluidMixingRecipe::getTicks,
                        FluidStack.STREAM_CODEC, FluidMixingRecipe::getOutput,
                        FluidMixingRecipe::new);

        @Override
        public MapCodec<FluidMixingRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, FluidMixingRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }

}
