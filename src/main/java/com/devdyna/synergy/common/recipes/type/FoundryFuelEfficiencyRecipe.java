package com.devdyna.synergy.common.recipes.type;

import java.util.List;
import com.devdyna.synergy.api.recipes.types.BaseRecipeType;
import com.devdyna.synergy.api.registers.RecipeRegister;
import com.devdyna.synergy.api.utils.x;
import com.devdyna.synergy.common.recipes.input.FluidInput;
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
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.fluids.FluidStack;

@SuppressWarnings("null")
public class FoundryFuelEfficiencyRecipe extends BaseRecipeType<FluidInput> {

    private FluidStack fluid;
    private float usageModifier;
    private float speedModifier;

    public FoundryFuelEfficiencyRecipe(FluidStack fluid, float usageModifier, float speedModifier) {
        this.fluid = fluid;
        this.usageModifier = usageModifier;
        this.speedModifier = speedModifier;
    }

    public boolean matches(FluidInput r, Level l) {
        return FluidStack.isSameFluidSameComponents(r.input(), fluid);
    }

    public ItemStack assemble(FluidInput i, HolderLookup.Provider r) {
        return x.item(fluid.getFluid().getBucket());
    }

    public NonNullList<Ingredient> getIngredients() {
        return NonNullList
                .copyOf(List.of(x.ingredient(fluid.getFluid().getBucket())));
    }

    @Override
    @Deprecated
    public ItemStack getResultItem(HolderLookup.Provider registryAccess) {
        return x.item(fluid.getFluid().getBucket());
    }

    @Override
    public RecipeRegister<? extends BaseRecipeType<FluidInput>> getRecipe() {
        return zRecipeTypes.FOUNDRY_FUELS;
    }

    public FluidStack getFluid() {
        return fluid;
    }

    public float getSpeedModifier() {
        return speedModifier;
    }

    public float getUsageModifier() {
        return usageModifier;
    }

    @Override
    public Item getToastIcon() {
        return Items.LAVA_BUCKET;
    }

    public static class Serializer implements RecipeSerializer<FoundryFuelEfficiencyRecipe> {

        public static final MapCodec<FoundryFuelEfficiencyRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst
                .group(
                        FluidStack.CODEC.fieldOf("fluid").forGetter(FoundryFuelEfficiencyRecipe::getFluid),
                        Codec.floatRange(0, Float.MAX_VALUE).fieldOf("usage")
                                .forGetter(FoundryFuelEfficiencyRecipe::getUsageModifier),
                        Codec.floatRange(0, Float.MAX_VALUE).fieldOf("speed")
                                .forGetter(FoundryFuelEfficiencyRecipe::getSpeedModifier))
                .apply(inst, FoundryFuelEfficiencyRecipe::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, FoundryFuelEfficiencyRecipe> STREAM_CODEC = StreamCodec
                .composite(
                        FluidStack.STREAM_CODEC, FoundryFuelEfficiencyRecipe::getFluid,
                        ByteBufCodecs.FLOAT, FoundryFuelEfficiencyRecipe::getUsageModifier,
                        ByteBufCodecs.FLOAT, FoundryFuelEfficiencyRecipe::getSpeedModifier,
                        FoundryFuelEfficiencyRecipe::new);

        @Override
        public MapCodec<FoundryFuelEfficiencyRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, FoundryFuelEfficiencyRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }

}
