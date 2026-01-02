package com.devdyna.synergy.api.recipes.types;

import java.util.List;

import javax.annotation.Nullable;

import com.devdyna.synergy.api.utils.x;
import com.devdyna.synergy.common.recipes.input.ProviderInput;

import net.minecraft.core.NonNullList;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

@SuppressWarnings("null")
public abstract class BaseProviderRecipe<T> extends BaseRecipeType<ProviderInput> {

    protected final BlockState core;
    protected final BlockState below;
    protected final BlockState left;
    protected final BlockState right;
    protected final T output;

    public BaseProviderRecipe(BlockState core, @Nullable BlockState below, @Nullable BlockState left,
            @Nullable BlockState right, T output) {
        this.core = core;
        this.below = below;
        this.left = left;
        this.right = right;
        this.output = output;
    }

    public boolean matches(ProviderInput r, Level l) {
        return this.core.is(x.block(r.core()));
    }

    public NonNullList<Ingredient> getIngredients() {
        return NonNullList.copyOf(List.of(x.ingredient(core.getBlock())));
    }

    public BlockState getCore() {
        return core;
    }

    @Nullable
    public BlockState getBelow() {
        return below;
    }

    @Nullable
    public BlockState getLeft() {
        return left;
    }

    @Nullable
    public BlockState getRight() {
        return right;
    }

    public T getOutput() {
        return output;
    }

}
