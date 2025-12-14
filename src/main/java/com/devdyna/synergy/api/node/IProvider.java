package com.devdyna.synergy.api.node;

import java.util.Arrays;
import java.util.Optional;

import com.devdyna.synergy.api.node.builder.NodeBaseBE;
import com.devdyna.synergy.api.recipes.types.BaseProviderRecipe;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

@SuppressWarnings({ "unchecked", "null" })
public interface IProvider<I extends RecipeInput, T extends Recipe<I>, L> {

    abstract Optional<RecipeHolder<T>> getRecipe(BlockPos pos);

    abstract BlockPos getInput();

    default boolean isValidSet(BlockState state, Direction dir, BlockPos pos, Level level) {

        if (level == null)
            return false;

        Optional<RecipeHolder<T>> r = getRecipe(pos);

        if (r == null || r.isEmpty())
            return false;

        var recipe = (BaseProviderRecipe<L>) r.get().value();

        var requireBelow = !(recipe.getBelow() == null || recipe.getBelow().isAir());
        var requireLeft = !(recipe.getLeft() == null || recipe.getLeft().isAir());
        var requireRight = !(recipe.getRight() == null || recipe.getRight().isAir());

        if (requireBelow)
            if (!NodeBaseBE.check(level, getInput().relative(dir), recipe.getBelow()))
                return false;

        var dirs = Arrays.asList(Direction.values()).stream()
                .filter(d -> !d.equals(dir) && !d.equals(dir.getOpposite())).toList();

        BlockPos rightPos = null;
        BlockPos leftPos = null;

        if (requireLeft || requireRight)
            for (Direction direction : dirs) {
                if (requireRight)
                    if (NodeBaseBE.check(level, pos.relative(direction), recipe.getRight())
                            && !pos.relative(direction).equals(leftPos) && rightPos == null) {
                        rightPos = pos.relative(direction);
                        continue;
                    }

                if (requireLeft)
                    if (NodeBaseBE.check(level, pos.relative(direction), recipe.getLeft())
                            && !pos.relative(direction).equals(rightPos) && leftPos == null) {
                        leftPos = pos.relative(direction);
                        continue;
                    }
            }

        if (requireLeft && leftPos == null)
            return false;
        if (requireRight && rightPos == null)
            return false;

        return true;
    }

}
