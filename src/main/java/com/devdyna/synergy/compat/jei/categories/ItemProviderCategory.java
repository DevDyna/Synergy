package com.devdyna.synergy.compat.jei.categories;

import org.jetbrains.annotations.Nullable;

import com.devdyna.synergy.Main;
import com.devdyna.synergy.client.gui.screenLocations;
import com.devdyna.synergy.compat.jei.drawable.SimpleIcon;
import com.devdyna.synergy.init.recipeTypes.type.ItemProviderRecipe;
import com.devdyna.synergy.init.types.zBlocks;
import com.devdyna.synergy.init.types.zRecipeTypes;
import com.devdyna.synergy.utils.x;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.LiquidBlock;

@SuppressWarnings("null")
public class ItemProviderCategory implements IRecipeCategory<ItemProviderRecipe> {

    private IGuiHelper helper;

    public static final RecipeType<ItemProviderRecipe> TYPE = new RecipeType<>(
            x.rl(zRecipeTypes.ITEM_PROVIDER.getId()),
            ItemProviderRecipe.class);

    public ItemProviderCategory(IGuiHelper helper) {
        this.helper = helper;
    }

    @Override
    public RecipeType<ItemProviderRecipe> getRecipeType() {
        return TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable(Main.ID + ".jei.provider.item");
    }

    @Override
    public @Nullable IDrawable getIcon() {
        return SimpleIcon.of(helper, x.item(zBlocks.ITEM_PROVIDER).getItem());
    }

    @Override
    public @Nullable IDrawable getBackground() {//TODO change
        return helper.createDrawable(screenLocations.ITEM_USE, 0, 0, 103, 70);
    }

    @Override//TODO move to align with background
    public void setRecipe(IRecipeLayoutBuilder builder, ItemProviderRecipe recipe, IFocusGroup focuses) {



        builder.addOutputSlot(5, 4).addItemStack(recipe.getOutput());

        try {
            var core = recipe.getCore();
            if (core.getBlock() instanceof LiquidBlock fluid)
                builder.addInputSlot(20, 4).addFluidStack(fluid.fluid);
            else
                builder.addInputSlot(20, 4).addItemStack(x.item(core));
        } catch (Exception e) {
        }

        try {
            var below = recipe.getBelow();
            if (!below.isAir()) {
                if (below.getBlock() instanceof LiquidBlock fluid)
                    builder.addOutputSlot(5, 20).addFluidStack(fluid.fluid);
                else
                    builder.addOutputSlot(5, 20).addItemStack(x.item(below));
            }
        } catch (Exception e) {
        }

        try {
            var right = recipe.getRight();
            if (!right.isAir()) {
                if (right.getBlock() instanceof LiquidBlock fluid)
                    builder.addOutputSlot(60, 20).addFluidStack(fluid.fluid);
                else
                    builder.addOutputSlot(60, 20).addItemStack(x.item(right));
            }
        } catch (Exception e) {
        }

        try {
            var left = recipe.getLeft();
            if (!left.isAir()) {
                if (left.getBlock() instanceof LiquidBlock fluid)
                    builder.addOutputSlot(20, 60).addFluidStack(fluid.fluid);
                else
                    builder.addOutputSlot(20, 60).addItemStack(x.item(left));
            }
        } catch (Exception e) {
        }

    }

}
