package com.devdyna.synergy.compat.jei.categories;

import org.jetbrains.annotations.Nullable;

import com.devdyna.synergy.Main;
import com.devdyna.synergy.client.gui.screenLocations;
import com.devdyna.synergy.compat.jei.categories.core.BaseRecipeCategory;
import com.devdyna.synergy.compat.jei.drawable.SimpleIcon;
import com.devdyna.synergy.init.recipeTypes.type.DryableBricksRecipe;
import com.devdyna.synergy.init.types.zRecipeTypes;
import com.devdyna.synergy.utils.x;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeType;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Items;

@SuppressWarnings("null")
public class DryableBricksCategory extends BaseRecipeCategory<DryableBricksRecipe> {

    public static final RecipeType<DryableBricksRecipe> TYPE = new RecipeType<>(
            x.rl(zRecipeTypes.DRYABLE_BRICKS.getId()),
            DryableBricksRecipe.class);

    public DryableBricksCategory(IGuiHelper helper) {
        super(helper);
    }

    @Override
    public RecipeType<DryableBricksRecipe> getRecipeType() {
        return TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable(Main.ID + ".jei.drying_bricks");
    }

    @Override
    public @Nullable IDrawable getIcon() {
        return SimpleIcon.of(helper, Items.BRICK);
    }

    @Override
    public @Nullable IDrawable getBackground() {
        return helper.createDrawable(x.rl(screenLocations.CUSTOM_JEI_GUI + "delay.png"), 0, 0, 93, 33);
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, DryableBricksRecipe recipe, IFocusGroup focuses) {

        builder.addInputSlot(8, 9).addItemStack(recipe.getInput());
        builder.addOutputSlot(67, 9).addItemStack(recipe.getOutput());

    }

}
