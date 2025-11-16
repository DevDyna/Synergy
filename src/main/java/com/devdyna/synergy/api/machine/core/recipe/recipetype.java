package com.devdyna.synergy.api.machine.core.recipe;

import java.util.List;
import com.devdyna.synergy.init.recipeTypes.input.MonoItemInput;
import com.devdyna.synergy.init.types.zBlocks;
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
public class recipetype implements Recipe<MonoItemInput> {

    private final Ingredient input;
    private final int ticks;
    private final int energy;
    private final ItemStack output;

    public recipetype(Ingredient input,
            ItemStack output, int ticks, int energy) {
        this.input = input;
        this.ticks = ticks;
        this.output = output;
        this.energy = energy;
    }

    public static recipetype of(Ingredient input, ItemStack output, int ticks, int energy) {
        return new recipetype(input, output, ticks, energy);
    }

    public recipetype of() {
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
        return new RecipeType<recipetype>() {
            @Override
            public String toString() {
                return "id";// TODO
            }
        };
    }

    public ItemStack getToastSymbol() {
        return new ItemStack(zBlocks.QUERN.get());
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return new serializer();
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
    public ItemStack getResultItem(HolderLookup.Provider registryAccess) {
        return this.output;
    }

    public static class serializer implements RecipeSerializer<recipetype> {

        public static final MapCodec<recipetype> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                Ingredient.CODEC.fieldOf("ingredient").forGetter(recipetype::getInput),
                ItemStack.CODEC.fieldOf("result").forGetter(recipetype::getOutput),
                Codec.intRange(1, Integer.MAX_VALUE).fieldOf("ticks").forGetter(recipetype::getTicks),
                Codec.intRange(1, Integer.MAX_VALUE).fieldOf("energy").forGetter(recipetype::getEnergy))
                .apply(inst, recipetype::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, recipetype> STREAM_CODEC = StreamCodec.composite(
                Ingredient.CONTENTS_STREAM_CODEC, recipetype::getInput,
                ItemStack.STREAM_CODEC, recipetype::getOutput,
                ByteBufCodecs.INT, recipetype::getTicks,
                ByteBufCodecs.INT, recipetype::getEnergy,
                recipetype::new);

        @Override
        public MapCodec<recipetype> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, recipetype> streamCodec() {
            return STREAM_CODEC;
        }

    }

}
