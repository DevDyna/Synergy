package com.devdyna.synergy.compat.jei.categories;

import org.jetbrains.annotations.Nullable;

import com.devdyna.synergy.Main;
import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.api.jei.BaseJEICategory;
import com.devdyna.synergy.client.gui.screenLocations;
import com.devdyna.synergy.compat.jei.drawable.SimpleIcon;
import com.devdyna.synergy.init.recipeTypes.type.QuernMillingRecipe;
import com.devdyna.synergy.init.types.zBlocks;
import com.devdyna.synergy.init.types.zRecipeTypes;
import com.devdyna.synergy.utils.x;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;

@SuppressWarnings("null")
public class QuernCategory extends BaseJEICategory implements IRecipeCategory<QuernMillingRecipe> {

    private final IGuiHelper helper;
    public static final RecipeType<QuernMillingRecipe> TYPE = new RecipeType<>(
            x.rl(zRecipeTypes.QUERN_MILLING.getId()),
            QuernMillingRecipe.class);

    public QuernCategory(IGuiHelper helper) {
        this.helper = helper;
        // TODO arrow animation return
    }

    @Override
    public RecipeType<QuernMillingRecipe> getRecipeType() {
        return TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable(Main.ID + ".jei." + zStatic.Blocks.quern);
    }

    @Override
    public @Nullable IDrawable getIcon() {
        return SimpleIcon.of(helper, zBlocks.QUERN.get().asItem());
    }

    @Override
    public @Nullable IDrawable getBackground() {
        return helper.createDrawable(screenLocations.QUERN, 0, 0, 80, 22);
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, QuernMillingRecipe recipe, IFocusGroup focuses) {

        builder.addSlot(RecipeIngredientRole.INPUT, 5, 4).addIngredients(recipe.getInput());

        builder.addSlot(RecipeIngredientRole.OUTPUT, 60, 4).addItemStack(recipe.getOutput());
    }

    @Override
    public void draw(QuernMillingRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics,
            double mouseX, double mouseY) {

        guiGraphics.drawString(font,
                Component.literal(recipe.getTime() == 1 ? "no tick delay"
                        : (recipe.getTime() >= 20 ? (recipe.getTime() >= 1200 ? (recipe.getTime() >= 72000
                                ? recipe.getTime() / 72000 + " hour" + (recipe.getTime() > 72000 ? "s" : "")
                                : recipe.getTime() / 1200 + " minute" + (recipe.getTime() > 1200 ? "s" : "")

                        )
                                : recipe.getTime() / 20 + " sec" + (recipe.getTime() > 20 ? "s" : ""))
                                : recipe.getTime() + " tick" + (recipe.getTime() > 1 ? "s" : ""))),
                24, 16, 0xA0A0A0);

    }

}
