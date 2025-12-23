package com.devdyna.synergy.common.recipes.type;

import java.util.List;

import com.devdyna.synergy.api.utils.x;
import com.devdyna.synergy.common.recipes.input.MonoItemInput;
import com.devdyna.synergy.init.types.zBlocks;
import com.devdyna.synergy.init.types.zRecipeTypes;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

@SuppressWarnings("null")
public class VoidBoxInfusionRecipe implements Recipe<MonoItemInput> {


    private final Ingredient input;
    private final ItemStack output;

    public VoidBoxInfusionRecipe(Ingredient input,ItemStack output) {
        this.input = input;
        this.output = output;
    }

    public boolean matches(MonoItemInput r, Level l) {
        return input.test(r.input()) ;
    }

    public ItemStack assemble(MonoItemInput i, HolderLookup.Provider r) {
        return this.output;
    }

    public boolean canCraftInDimensions(int xz, int y) {
        return false;
    }

    public RecipeType<?> getType() {
        return zRecipeTypes.VOID_BOX_INFUSION.getType();
    }

    public ItemStack getToastSymbol() {
        return x.item(zBlocks.VOID_BOX);
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return zRecipeTypes.VOID_BOX_INFUSION.getSerializer();
    }

    public NonNullList<Ingredient> getIngredients() {
        return NonNullList.copyOf(List.of(input));
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider registryAccess) {
        return output;
    }

  public Ingredient getInput() {
      return input;
  }

  public ItemStack getOutput() {
      return output;
  }

}
