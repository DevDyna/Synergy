package com.devdyna.synergy.common.recipes.type;

import java.util.List;

import com.devdyna.synergy.api.recipes.types.BaseRecipeType;
import com.devdyna.synergy.api.registers.RecipeRegister;
import com.devdyna.synergy.common.recipes.input.MonoItemInput;
import com.devdyna.synergy.init.types.zRecipeTypes;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

@SuppressWarnings("null")
public class DryableBricksRecipe extends BaseRecipeType<MonoItemInput> {

    private final Ingredient input;
    private final BlockState block;
    private final ItemStack output;

    public DryableBricksRecipe(Ingredient input, BlockState block, ItemStack output) {
        this.input = input;
        this.block = block;
        this.output = output;
    }

    public boolean matches(MonoItemInput r, Level l) {
        return input.test(r.input());
    }

    public ItemStack assemble(MonoItemInput i, HolderLookup.Provider r) {
        return output;
    }

    @Override
    public RecipeRegister<DryableBricksRecipe> getRecipe() {
        return zRecipeTypes.DRYABLE_BRICKS;
    }

    @Override
    public Item getToastIcon() {
        return Items.BRICK;
    }

    public NonNullList<Ingredient> getIngredients() {
        return NonNullList.copyOf(List.of(input));
    }

    @Override
    @Deprecated
    public ItemStack getResultItem(HolderLookup.Provider registryAccess) {
        return output;
    }

    public Ingredient getInput() {
        return input;
    }

    public ItemStack getOutput() {
        return output;
    }

    public BlockState getBlock() {
        return block;
    }
}
