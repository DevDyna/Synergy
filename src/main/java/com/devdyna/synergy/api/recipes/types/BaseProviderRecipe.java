package com.devdyna.synergy.api.recipes.types;

import java.util.List;

import com.devdyna.synergy.api.codec.recipe.NodePattern;
import com.devdyna.synergy.api.utils.x;
import com.devdyna.synergy.common.recipes.input.ProviderInput;

import net.minecraft.core.NonNullList;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;

@SuppressWarnings("null")
public abstract class BaseProviderRecipe<T> extends BaseRecipeType<ProviderInput> {

    protected final NodePattern pattern;
    protected T output;

    public BaseProviderRecipe(NodePattern pattern, T output) {
        this.pattern = pattern;
        this.output = output;
    }

    public boolean matches(ProviderInput r, Level l) {
        return this.pattern.core().is(x.block(r.core()));
    }

    public NonNullList<Ingredient> getIngredients() {
        return NonNullList.copyOf(List.of(x.ingredient(x.item(this.pattern.core()))));
    }

    public NodePattern getPattern() {
        return this.pattern;
    }

    // public BlockState getCore() {
    // return core;
    // }

    // @Nullable
    // public BlockState getBelow() {
    // return below;
    // }

    // @Nullable
    // public BlockState getLeft() {
    // return left;
    // }

    // @Nullable
    // public BlockState getRight() {
    // return right;
    // }

    public T getOutput() {
        return output;
    }

}
