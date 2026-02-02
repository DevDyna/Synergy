package com.devdyna.synergy.compat.jei.categories;

import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.api.utils.Size;
import com.devdyna.synergy.api.utils.x;
import com.devdyna.synergy.compat.jei.categories.core.BaseRecipeCategory;
import com.devdyna.synergy.init.builder.nuclear_reactor.fuel_cell.FuelCellBE;
import com.devdyna.synergy.init.builder.nuclear_reactor.fuel_cell.recipe.FuelCellRecipe;
import com.devdyna.synergy.init.types.zItems;
import com.devdyna.synergy.init.types.zRecipeTypes;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.ItemLike;

@SuppressWarnings("null")
public class ReactorCellCategory extends BaseRecipeCategory<FuelCellRecipe> {

    private IDrawableAnimated arrow;

    public ReactorCellCategory(IGuiHelper helper) {
        super(helper);
        // change timespan based on duration
        this.arrow = helper.drawableBuilder(x.rl(
                "textures/gui/sprite/green_progress_arrow.png"), 0, 0, 24, 16)
                .setTextureSize(24, 16).buildAnimated(200,
                        IDrawableAnimated.StartDirection.LEFT, false);
    }

    public static final RecipeType<RecipeHolder<FuelCellRecipe>> TYPE = RecipeType
            .createFromVanilla(zRecipeTypes.FUEL_CELL_RECIPE.getType());

    @Override
    public RecipeType<RecipeHolder<FuelCellRecipe>> getRecipeType() {
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
        return "textures/gui/jei/dark_slots.png";
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, FuelCellRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 16, 16).addIngredients(recipe.getInput());
        builder.addSlot(RecipeIngredientRole.OUTPUT, 64, 16).addItemStack(recipe.getOutput());
    }

    @Override
    public void draw(FuelCellRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX,
            double mouseY) {
        super.draw(recipe, recipeSlotsView, guiGraphics, mouseX, mouseY);
        guiGraphics.drawString(font,
                FuelCellBE.getTimeValue(recipe.getDuration()),
                27, 4, 0xA0A0A0);

        guiGraphics.drawString(font, "Heat " + (recipe.getHeat() >= 0 ? "+" : "") + recipe.getHeat() + "°/t",
                7, 39, 0xA0A0A0);

        guiGraphics.drawString(font, "Gen  " + (recipe.getFe() >= 0 ? "+" : "") + recipe.getFe() + "fe/t",
                7, 49, 0xA0A0A0);

        arrow.draw(guiGraphics, 35, 16);

    }

}
