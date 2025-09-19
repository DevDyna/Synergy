package com.devdyna.synergy.init.recipeTypes.type;

import java.util.List;

import com.devdyna.synergy.init.recipeTypes.input.MonoItemInput;
import com.devdyna.synergy.init.types.zRecipeTypes;
import com.devdyna.synergy.utils.x;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

@SuppressWarnings("null")
public class ItemUseRecipe implements Recipe<MonoItemInput> {

    private final Ingredient inputItem;
    private final BlockState inputState;
    private final BlockState outputState;

    public ItemUseRecipe(Ingredient inputItem,
            BlockState inputState, BlockState outputState) {
        this.inputItem = inputItem;
        this.inputState = inputState;
        this.outputState = outputState;
    }

    public boolean matches(MonoItemInput r, Level l) {
        return true;
    }

    public ItemStack assemble(MonoItemInput i, HolderLookup.Provider r) {
        return x.item(this.outputState.getBlock().asItem());
    }

    public boolean canCraftInDimensions(int xz, int y) {
        return false;
    }

    public RecipeType<?> getType() {
        return zRecipeTypes.ITEM_USE.getType();
    }

    public ItemStack getToastSymbol() {
        return new ItemStack(Items.WOODEN_PICKAXE);
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return zRecipeTypes.ITEM_USE.getSerializer();
    }

    public NonNullList<Ingredient> getIngredients() {
        return NonNullList.copyOf(List.of(x.ingredient(this.inputState.getBlock().asItem())));
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider registryAccess) {
        return x.item(this.outputState.getBlock().asItem());
    }

    public Ingredient getInputItem() {
        return inputItem;
    }

    public BlockState getInputState() {
        return inputState;
    }

    public BlockState getOutputState() {
        return outputState;
    }
}
