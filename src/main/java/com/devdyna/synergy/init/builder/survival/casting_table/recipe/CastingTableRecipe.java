package com.devdyna.synergy.init.builder.survival.casting_table.recipe;

import java.util.List;

import com.devdyna.synergy.api.recipes.inputs.ItemFluidInput;
import com.devdyna.synergy.api.recipes.types.BaseRecipeType;
import com.devdyna.synergy.api.registers.RecipeRegister;
import com.devdyna.synergy.api.utils.x;
import com.devdyna.synergy.init.types.zBlocks;
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
import net.neoforged.neoforge.fluids.crafting.SizedFluidIngredient;

@SuppressWarnings("null")
public class CastingTableRecipe extends BaseRecipeType<ItemFluidInput> {

    private final SizedFluidIngredient fluid;
    private final Ingredient input;
    private final boolean consume;
    private final int ticks;
    private final ItemStack output;

    public CastingTableRecipe(SizedFluidIngredient fluid, Ingredient input,boolean consume, int ticks, ItemStack output) {
        this.input = input;
        this.fluid = fluid;
        this.consume = consume;
        this.ticks = ticks;
        this.output = output;
    }

    public static CastingTableRecipe of(SizedFluidIngredient fluid, Ingredient input,boolean consume, int ticks, ItemStack output) {
        return new CastingTableRecipe(fluid, input,consume, ticks, output);
    }

    public boolean matches(ItemFluidInput r, Level l) {
        return this.fluid.test(r.input()) && this.input.test(r.item());
    }

    public ItemStack assemble(ItemFluidInput i, HolderLookup.Provider r) {
        return this.output.copy();
    }

    public NonNullList<Ingredient> getIngredients() {
        return NonNullList
                .copyOf(List.of(x.ingredient(x.getFluids(this.fluid).getFirst().getFluid().getBucket()), input));
    }

    public SizedFluidIngredient getFluid() {
        return fluid;
    }

    public ItemStack getOutput() {
        return output;
    }

    public int getTicks() {
        return ticks;
    }

    public Ingredient getInput() {
        return input;
    }

    public boolean consumeInput(){
        return consume;
    }

    @Override
    @Deprecated
    public ItemStack getResultItem(HolderLookup.Provider registryAccess) {
        return this.output;
    }

    @Override
    public RecipeRegister<? extends BaseRecipeType<ItemFluidInput>> getRecipe() {
        return zRecipeTypes.CASTING_TABLE;
    }

    @Override
    public Item getToastIcon() {
        return zBlocks.CASTING_TABLE.get().asItem();
    }

    public static class Serializer implements RecipeSerializer<CastingTableRecipe> {

        public static final MapCodec<CastingTableRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                SizedFluidIngredient.FLAT_CODEC.fieldOf("fluid").forGetter(CastingTableRecipe::getFluid),
                Ingredient.CODEC.fieldOf("input").forGetter(CastingTableRecipe::getInput),
                Codec.BOOL.fieldOf("consume_input").forGetter(CastingTableRecipe::consumeInput),
                Codec.intRange(1, Integer.MAX_VALUE).fieldOf("ticks").forGetter(CastingTableRecipe::getTicks),
                ItemStack.CODEC.fieldOf("output").forGetter(CastingTableRecipe::getOutput))
                .apply(inst, CastingTableRecipe::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, CastingTableRecipe> STREAM_CODEC = StreamCodec
                .composite(
                        SizedFluidIngredient.STREAM_CODEC, CastingTableRecipe::getFluid,
                        Ingredient.CONTENTS_STREAM_CODEC, CastingTableRecipe::getInput,
                        ByteBufCodecs.BOOL,CastingTableRecipe::consumeInput,
                        ByteBufCodecs.INT, CastingTableRecipe::getTicks,
                        ItemStack.STREAM_CODEC, CastingTableRecipe::getOutput,
                        CastingTableRecipe::new);

        @Override
        public MapCodec<CastingTableRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, CastingTableRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }

}
