package com.devdyna.synergy.common.recipes.type;

import java.util.List;

import com.devdyna.synergy.api.zRecipe;
import com.devdyna.synergy.api.recipes.types.BaseRecipeType;
import com.devdyna.synergy.common.recipes.input.MonoItemInput;
import com.devdyna.synergy.init.types.zBlocks;
import com.devdyna.synergy.init.types.zRecipeTypes;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;

@SuppressWarnings("null")
public class VoidBoxInfusionRecipe extends BaseRecipeType<MonoItemInput> {

    private final Ingredient input;
    private final ItemStack output;

    public VoidBoxInfusionRecipe(Ingredient input, ItemStack output) {
        this.input = input;
        this.output = output;
    }

    public boolean matches(MonoItemInput r, Level l) {
        return input.test(r.input());
    }

    public ItemStack assemble(MonoItemInput i, HolderLookup.Provider r) {
        return this.output;
    }

    public NonNullList<Ingredient> getIngredients() {
        return NonNullList.copyOf(List.of(input));
    }

    public Ingredient getInput() {
        return input;
    }

    public ItemStack getOutput() {
        return output;
    }

    @Override
    public zRecipe<? extends BaseRecipeType<MonoItemInput>> getRecipe() {
        return zRecipeTypes.VOID_BOX_INFUSION;
    }

    @Override
    public Item getToastIcon() {
        return zBlocks.VOID_BOX.get().asItem();
    }

}
