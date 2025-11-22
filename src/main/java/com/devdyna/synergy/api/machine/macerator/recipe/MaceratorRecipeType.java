package com.devdyna.synergy.api.machine.macerator.recipe;

import java.util.List;
import com.devdyna.synergy.init.recipeTypes.input.MonoItemInput;
import com.devdyna.synergy.init.types.zMachines;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

@SuppressWarnings("null")
public class MaceratorRecipeType implements Recipe<MonoItemInput> {

    private final Ingredient input;
    private final int ticks;
    private final int energy;
    private final ItemStack output;

    public MaceratorRecipeType(Ingredient input,
            ItemStack output, int ticks, int energy) {
        this.input = input;
        this.ticks = ticks;
        this.output = output;
        this.energy = energy;
    }

    public static MaceratorRecipeType of(Ingredient input, ItemStack output, int ticks, int energy) {
        return new MaceratorRecipeType(input, output, ticks, energy);
    }

    public MaceratorRecipeType of() {
        return this;
    }

    public boolean matches(MonoItemInput r, Level l) {
        return this.input.test(r.input());
    }

    public ItemStack assemble(MonoItemInput i, HolderLookup.Provider r) {
        return this.output.copy();
    }

    public boolean canCraftInDimensions(int xz, int y) {
        return false;
    }

    public RecipeType<?> getType() {
        return new RecipeType<MaceratorRecipeType>() {
            @Override
            public String toString() {
                return zMachines.MACERATOR.id();
            }
        };
    }

    public ItemStack getToastSymbol() {
        return new ItemStack(zMachines.MACERATOR.block().get());
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return new Serializer();
    }

    public NonNullList<Ingredient> getIngredients() {
        return NonNullList.copyOf(List.of(this.input));
    }

    public Ingredient getInput() {
        return input;
    }

    public ItemStack getOutput() {
        return output;
    }

    public int getTicks() {
        return ticks;
    }

    public int getEnergy() {
        return energy;
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider a) {
        return this.output;
    }

    public static class Serializer implements RecipeSerializer<MaceratorRecipeType> {

        public static final MapCodec<MaceratorRecipeType> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                Ingredient.CODEC.fieldOf("ingredient").forGetter(MaceratorRecipeType::getInput),
                ItemStack.CODEC.fieldOf("result").forGetter(MaceratorRecipeType::getOutput),
                Codec.intRange(1, Integer.MAX_VALUE).fieldOf("ticks").forGetter(MaceratorRecipeType::getTicks),
                Codec.intRange(1, Integer.MAX_VALUE).fieldOf("energy").forGetter(MaceratorRecipeType::getEnergy))
                .apply(inst, MaceratorRecipeType::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, MaceratorRecipeType> STREAM_CODEC = StreamCodec.composite(
                Ingredient.CONTENTS_STREAM_CODEC, MaceratorRecipeType::getInput,
                ItemStack.STREAM_CODEC, MaceratorRecipeType::getOutput,
                ByteBufCodecs.INT, MaceratorRecipeType::getTicks,
                ByteBufCodecs.INT, MaceratorRecipeType::getEnergy,
                MaceratorRecipeType::new);

        @Override
        public MapCodec<MaceratorRecipeType> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, MaceratorRecipeType> streamCodec() {
            return STREAM_CODEC;
        }

    }

}
