package com.devdyna.synergy.compat.jei.categories;

import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.api.Size;
import com.devdyna.synergy.compat.jei.categories.core.BaseRecipeCategory;
import com.devdyna.synergy.init.recipeTypes.type.FuelCellRecipe;
import com.devdyna.synergy.init.types.zItems;
import com.devdyna.synergy.init.types.zRecipeTypes;
import com.devdyna.synergy.utils.x;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.level.ItemLike;

@SuppressWarnings("null")
public class ReactorCellCategory extends BaseRecipeCategory<FuelCellRecipe> {

    private IDrawableAnimated arrow;
    public static final RecipeType<FuelCellRecipe> TYPE = new RecipeType<>(
            x.rl(zRecipeTypes.FUEL_CELL_RECIPE.getId()),
            FuelCellRecipe.class);

    public ReactorCellCategory(IGuiHelper helper) {
        super(helper);
        // TODO change timespan based on duration
        this.arrow = helper.drawableBuilder(x.rl(
                        "textures/gui/green_progress_arrow.png"), 0, 0, 24, 16)
                .setTextureSize(24, 16).buildAnimated(200,
                        IDrawableAnimated.StartDirection.LEFT, false);
    }

    @Override
    public RecipeType<FuelCellRecipe> getRecipeType() {
        return TYPE;
    }

    @Override
    public String getTitleKey() {
        return zStatic.ReactorStuff.fuel_cell;
    }

    @Override
    public ItemLike getIconItem() {
        return zItems.URANIUM.get();
    }

    @Override
    public Size setXY() {
        return Size.of(96, 60);
    }

    @Override
    public String setBackGround() {
        return "textures/gui/dark_slots.png";
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, FuelCellRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 34-32+14, 17-15+14).addIngredients(recipe.getInput());
        builder.addSlot(RecipeIngredientRole.OUTPUT, 84-32-2+14, 17-15+14).addItemStack(recipe.getOutput());
    }

    @Override
    public void draw(FuelCellRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX,
            double mouseY) {
                super.draw(recipe, recipeSlotsView, guiGraphics, mouseX, mouseY);
        guiGraphics.drawString(font,
                (recipe.getDuration() == 1 ? "no tick delay"
                        : (recipe.getDuration() >= 20 ? (recipe.getDuration() >= 1200 ? (recipe.getDuration() >= 72000
                                ? recipe.getDuration() / 72000 + " hour" + (recipe.getDuration() > 72000 ? "s" : "")
                                : recipe.getDuration() / 1200 + " minute" + (recipe.getDuration() > 1200 ? "s" : "")

                        )
                                : recipe.getDuration() / 20 + " second" + (recipe.getDuration() > 20 ? "s" : ""))
                                : recipe.getDuration() + " tick" + (recipe.getDuration() > 1 ? "s" : ""))),
                45-32+14, 5-15+14, 0xA0A0A0);

        guiGraphics.drawString(font, "Heat " + (recipe.getHeat() >= 0 ? "+" : "") + recipe.getHeat() + "°/t",
                25-32+14, 40-15+14, 0xA0A0A0);

        guiGraphics.drawString(font, "Gen  " + (recipe.getFe() >= 0 ? "+" : "") + recipe.getFe() + "fe/t",
                25-32+14, 50-15+14, 0xA0A0A0);

        arrow.draw(guiGraphics, 53-32+14, 17-15+14);

    }

}
