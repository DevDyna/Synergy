package com.devdyna.synergy.init.builder.survival.steam_boiler.recipe;

import java.util.List;

import com.devdyna.synergy.api.recipes.inputs.FluidInput;
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
public class SteamBoilerRecipe extends BaseRecipeType<FluidInput> {

    private int ticks;
    private SizedFluidIngredient input;
    private FluidStack output;

    public SteamBoilerRecipe(SizedFluidIngredient input, int ticks, FluidStack output) {
        this.ticks = ticks;
        this.input = input;
        this.output = output;
    }

    public boolean matches(FluidInput r, Level l) {
        return input.test(r.input()) ;
    }

    public ItemStack assemble(FluidInput i, HolderLookup.Provider r) {
        return x.item(output.getFluid().getBucket());
    }

    public NonNullList<Ingredient> getIngredients() {
        return NonNullList
                .copyOf(List.of(x.ingredient(x.getFluids(input).getFirst().getFluid().getBucket())));
    }

    @Override
    @Deprecated
    public ItemStack getResultItem(HolderLookup.Provider registryAccess) {
        return x.item(output.getFluid().getBucket());
    }

    @Override
    public RecipeRegister<? extends BaseRecipeType<FluidInput>> getRecipe() {
        return zRecipeTypes.STEAM_BOILER;
    }

    public int getTicks() {
        return ticks;
    }

    public SizedFluidIngredient getInput() {
        return input;
    }

    public FluidStack getOutput() {
        return output;
    }

    @Override
    public Item getToastIcon() {
        return output.getFluid().getBucket();
    }

    public static class Serializer implements RecipeSerializer<SteamBoilerRecipe> {

        public static final MapCodec<SteamBoilerRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst
                .group(
                        SizedFluidIngredient.FLAT_CODEC.fieldOf("input").forGetter(SteamBoilerRecipe::getInput),
                        Codec.intRange(1, Integer.MAX_VALUE).fieldOf("ticks").forGetter(SteamBoilerRecipe::getTicks),
                        FluidStack.CODEC.fieldOf("output")
                                .forGetter(SteamBoilerRecipe::getOutput))
                .apply(inst, SteamBoilerRecipe::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, SteamBoilerRecipe> STREAM_CODEC = StreamCodec
                .composite(
                        SizedFluidIngredient.STREAM_CODEC, SteamBoilerRecipe::getInput,
                        ByteBufCodecs.INT, SteamBoilerRecipe::getTicks,
                        FluidStack.STREAM_CODEC, SteamBoilerRecipe::getOutput,
                        SteamBoilerRecipe::new);

        @Override
        public MapCodec<SteamBoilerRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, SteamBoilerRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }

}
