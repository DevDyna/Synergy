package com.devdyna.synergy.compat.jei.categories;

import org.jetbrains.annotations.Nullable;

import com.devdyna.synergy.Main;
import com.devdyna.synergy.client.gui.screenLocations;
import com.devdyna.synergy.compat.jei.categories.core.BaseRecipeCategory;
import com.devdyna.synergy.compat.jei.drawable.SimpleIcon;
import com.devdyna.synergy.init.recipeTypes.type.ItemProviderRecipe;
import com.devdyna.synergy.init.types.zBlocks;
import com.devdyna.synergy.init.types.zRecipeTypes;
import com.devdyna.synergy.utils.x;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.LiquidBlock;

@SuppressWarnings("null")
public class ItemProviderCategory extends BaseRecipeCategory<ItemProviderRecipe> {

    public static final RecipeType<ItemProviderRecipe> TYPE = new RecipeType<>(
            x.rl(zRecipeTypes.ITEM_PROVIDER.getId()),
            ItemProviderRecipe.class);

    public ItemProviderCategory(IGuiHelper helper) {
        super(helper);
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
    public @Nullable IDrawable getBackground() {
        return helper.createDrawable(screenLocations.ITEM_PROVIDER, 0, 0, 80, 100);
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, ItemProviderRecipe recipe, IFocusGroup focuses) {

        builder.addOutputSlot(33, 10).addItemStack(recipe.getOutput());

        builder.addSlot(RecipeIngredientRole.RENDER_ONLY, 33, 45)
                .addIngredients(x.ingredient(zBlocks.ITEM_PROVIDER.get()));

        try {
            var core = recipe.getCore();
            if (core.getBlock() instanceof LiquidBlock fluid)
                builder.addInputSlot(33, 62).addFluidStack(fluid.fluid);
            else
                builder.addInputSlot(33, 62).addItemStack(x.item(core));
        } catch (Exception e) {
        }

        try {
            var below = recipe.getBelow();
            if (!below.isAir()) {
                if (below.getBlock() instanceof LiquidBlock fluid)
                    builder.addInputSlot(33, 79).addFluidStack(fluid.fluid);
                else
                    builder.addInputSlot(33, 79).addItemStack(x.item(below));
            }
        } catch (Exception e) {
        }

        try {
            var right = recipe.getRight();
            if (!right.isAir()) {
                if (right.getBlock() instanceof LiquidBlock fluid)
                    builder.addInputSlot(50, 62).addFluidStack(fluid.fluid);
                else
                    builder.addInputSlot(50, 62).addItemStack(x.item(right));
            }
        } catch (Exception e) {
        }

        try {
            var left = recipe.getLeft();
            if (!left.isAir()) {
                if (left.getBlock() instanceof LiquidBlock fluid)
                    builder.addInputSlot(16, 62).addFluidStack(fluid.fluid);
                else
                    builder.addInputSlot(16, 62).addItemStack(x.item(left));
            }
        } catch (Exception e) {
        }

    }

}
