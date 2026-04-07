package com.devdyna.synergy.init.builder.survival.mixing_chamber.recipe;

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
public class MixingChamberRecipe extends BaseRecipeType<BiFluidInput> {

    private int ticks;
    private SizedFluidIngredient first;
    private SizedFluidIngredient second;
    private FluidStack output;

    public MixingChamberRecipe(SizedFluidIngredient first, SizedFluidIngredient second, int ticks, FluidStack output) {
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
                .copyOf(List.of(x.ingredient(x.getFluids(first).getFirst().getFluid().getBucket()),
                        x.ingredient(x.getFluids(second).getFirst().getFluid().getBucket())));
    }

    @Override
    @Deprecated
    public ItemStack getResultItem(HolderLookup.Provider registryAccess) {
        return x.item(output.getFluid().getBucket());
    }

    @Override
    public RecipeRegister<? extends BaseRecipeType<BiFluidInput>> getRecipe() {
        return zRecipeTypes.MIXING_CHAMBER;
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

    public static class Serializer implements RecipeSerializer<MixingChamberRecipe> {

        public static final MapCodec<MixingChamberRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst
                .group(
                        SizedFluidIngredient.FLAT_CODEC.fieldOf("left").forGetter(MixingChamberRecipe::getFirst),
                        SizedFluidIngredient.FLAT_CODEC.fieldOf("right").forGetter(MixingChamberRecipe::getSecond),
                        Codec.intRange(1, Integer.MAX_VALUE).fieldOf("ticks").forGetter(MixingChamberRecipe::getTicks),
                        FluidStack.CODEC.fieldOf("output")
                                .forGetter(MixingChamberRecipe::getOutput))
                .apply(inst, MixingChamberRecipe::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, MixingChamberRecipe> STREAM_CODEC = StreamCodec
                .composite(
                        SizedFluidIngredient.STREAM_CODEC, MixingChamberRecipe::getFirst,
                        SizedFluidIngredient.STREAM_CODEC, MixingChamberRecipe::getSecond,
                        ByteBufCodecs.INT, MixingChamberRecipe::getTicks,
                        FluidStack.STREAM_CODEC, MixingChamberRecipe::getOutput,
                        MixingChamberRecipe::new);

        @Override
        public MapCodec<MixingChamberRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, MixingChamberRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }

}
