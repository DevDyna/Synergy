package com.devdyna.synergy.common.recipes.type;

import java.util.List;

import com.devdyna.synergy.api.recipes.types.BaseRecipeType;
import com.devdyna.synergy.api.registers.RecipeRegister;
import com.devdyna.synergy.api.utils.x;
import com.devdyna.synergy.common.recipes.input.ItemListInput;
import com.devdyna.synergy.init.types.zBlocks;
import com.devdyna.synergy.init.types.zRecipeTypes;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;

@SuppressWarnings("null")
public class CropResultRecipe extends BaseRecipeType<ItemListInput> {

    public static final int INPUT_COUNT = 6;
    public final Ingredient input;
    public final List<ItemStack> outputs;

    public CropResultRecipe(Ingredient input,
            List<ItemStack> outputs) {
        this.input = input;
        this.outputs = outputs;
    }

    public boolean matches(ItemListInput r, Level l) {
        return true;
    }

    public ItemStack assemble(ItemListInput i, HolderLookup.Provider r) {
        return this.outputs.get(0);
    }

    @Override
    public Item getToastIcon() {
        return x.item(zBlocks.AZALEA).getItem();
    }

    public NonNullList<Ingredient> getIngredients() {
        return NonNullList.copyOf(List.of(input));
    }
    
    @Override
    public ItemStack getResultItem(HolderLookup.Provider registryAccess) {
        return this.outputs.get(0);
    }

    public Ingredient getInput() {
        return input;
    }

    public List<ItemStack> getOutputs() {
        return outputs;
    }

    @Override
    public RecipeRegister<CropResultRecipe> getRecipe() {
        return zRecipeTypes.CROP_RESULT;
    }

    public static class Serializer implements RecipeSerializer<CropResultRecipe> {

    public static final MapCodec<CropResultRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
            Ingredient.CODEC.fieldOf("crop").forGetter(CropResultRecipe::getInput),
            ItemStack.CODEC.listOf().fieldOf("result").forGetter(CropResultRecipe::getOutputs))
            .apply(inst, CropResultRecipe::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, CropResultRecipe> STREAM_CODEC = StreamCodec.composite(
            Ingredient.CONTENTS_STREAM_CODEC, CropResultRecipe::getInput,
            ItemStack.LIST_STREAM_CODEC, CropResultRecipe::getOutputs,
            CropResultRecipe::new);

    @Override
    public MapCodec<CropResultRecipe> codec() {
        return CODEC;
    }

    @Override
    public StreamCodec<RegistryFriendlyByteBuf, CropResultRecipe> streamCodec() {
        return STREAM_CODEC;
    }

}
}
