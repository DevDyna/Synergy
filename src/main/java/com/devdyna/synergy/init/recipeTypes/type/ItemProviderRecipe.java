package com.devdyna.synergy.init.recipeTypes.type;

import java.util.List;

import com.devdyna.synergy.init.recipeTypes.input.ProviderInput;
import com.devdyna.synergy.init.types.zBlocks;
import com.devdyna.synergy.init.types.zRecipeTypes;
import com.devdyna.synergy.utils.x;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

@SuppressWarnings("null")
public class ItemProviderRecipe implements Recipe<ProviderInput> {

    private final BlockState core;
    private final BlockState below;
    private final BlockState left;
    private final BlockState right;
    private final ItemStack output;

    public ItemProviderRecipe(BlockState below, BlockState core, BlockState left, BlockState right, ItemStack output) {
        this.core = core;
        this.below = below;
        this.left = left;
        this.right = right;
        this.output = output;
    }

    public boolean matches(ProviderInput r, Level l) {
        return r.core().is(x.block(core));
    }

    public ItemStack assemble(ProviderInput i, HolderLookup.Provider r) {
        return output;
    }

    public boolean canCraftInDimensions(int xz, int y) {
        return false;
    }

    public RecipeType<?> getType() {
        return zRecipeTypes.ITEM_PROVIDER.getType();
    }

    public ItemStack getToastSymbol() {
        return new ItemStack(zBlocks.ITEM_PROVIDER.get());
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return zRecipeTypes.ITEM_PROVIDER.getSerializer();
    }

    public NonNullList<Ingredient> getIngredients() {
        return NonNullList.copyOf(List.of(x.ingredient(x.item(core))));
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider registryAccess) {
        return x.item(core);
    }

    public BlockState getCore() {
        return core;
    }

    public BlockState getBelow() {
        return below;
    }

    public BlockState getLeft() {
        return left;
    }

    public BlockState getRight() {
        return right;
    }

    public ItemStack getOutput() {
        return output;
    }

}
