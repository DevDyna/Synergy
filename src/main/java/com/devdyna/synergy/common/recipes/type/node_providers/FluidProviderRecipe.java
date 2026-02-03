package com.devdyna.synergy.common.recipes.type.node_providers;

import javax.annotation.Nullable;

import com.devdyna.synergy.api.codec.BetterThanBlockStates;
import com.devdyna.synergy.api.recipes.types.BaseProviderRecipe;
import com.devdyna.synergy.api.registers.RecipeRegister;
import com.devdyna.synergy.api.utils.x;
import com.devdyna.synergy.common.recipes.input.ProviderInput;
import com.devdyna.synergy.init.types.zBlocks;
import com.devdyna.synergy.init.types.zRecipeTypes;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.HolderLookup;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.fluids.FluidStack;

@SuppressWarnings({ "null" })
public class FluidProviderRecipe<T> extends BaseProviderRecipe<FluidStack> {

    private final FluidStack output;

    public FluidProviderRecipe(BlockState core, @Nullable BlockState below, @Nullable BlockState left,
            @Nullable BlockState right, FluidStack output) {
        super(core, below, left, right, output);
        this.output = output;

    }

    public ItemStack assemble(ProviderInput i, HolderLookup.Provider r) {
        return x.item(this.output.getFluid().getBucket());
    }

    @Override
    public RecipeRegister<FluidProviderRecipe<FluidStack>> getRecipe() {
        return zRecipeTypes.FLUID_PROVIDER;
    }

    @Override
    @Deprecated
    public ItemStack getResultItem(HolderLookup.Provider registryAccess) {
        return x.item(this.output.getFluid().getBucket());
    }

    @Override
    public Item getToastIcon() {
        return zBlocks.FLUID_PROVIDER.get().asItem();
    }

    public static class Serializer implements RecipeSerializer<FluidProviderRecipe<FluidStack>> {

    public static final MapCodec<FluidProviderRecipe<FluidStack>> CODEC = RecordCodecBuilder.mapCodec(inst -> inst
            .group(
                    BlockState.CODEC.fieldOf("core").forGetter(FluidProviderRecipe::getCore),
                    BlockState.CODEC.fieldOf("below").forGetter(FluidProviderRecipe::getBelow),
                    BlockState.CODEC.fieldOf("left").forGetter(FluidProviderRecipe::getLeft),
                    BlockState.CODEC.fieldOf("right").forGetter(FluidProviderRecipe::getRight),
                    FluidStack.CODEC.fieldOf("result").forGetter(FluidProviderRecipe::getOutput))
            .apply(inst, FluidProviderRecipe::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, FluidProviderRecipe<FluidStack>> STREAM_CODEC = StreamCodec
            .composite(
                    BetterThanBlockStates.STREAM_CODEC, FluidProviderRecipe::getCore,
                    BetterThanBlockStates.STREAM_CODEC, FluidProviderRecipe::getBelow,
                    BetterThanBlockStates.STREAM_CODEC, FluidProviderRecipe::getLeft,
                    BetterThanBlockStates.STREAM_CODEC, FluidProviderRecipe::getRight,
                    FluidStack.STREAM_CODEC, FluidProviderRecipe::getOutput,
                    FluidProviderRecipe::new);

    @Override
    public MapCodec<FluidProviderRecipe<FluidStack>> codec() {
        return CODEC;
    }

    @Override
    public StreamCodec<RegistryFriendlyByteBuf, FluidProviderRecipe<FluidStack>> streamCodec() {
        return STREAM_CODEC;
    }

}

}
