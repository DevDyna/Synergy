package com.devdyna.synergy.compat.jei.recipes;

import static com.devdyna.synergy.Main.ID;

import org.jetbrains.annotations.Nullable;

import com.devdyna.synergy.Main;
import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.client.gui.screenLocations;
import com.devdyna.synergy.init.recipeTypes.type.FuelCellRecipe;
import com.devdyna.synergy.init.types.zItems;
import com.devdyna.synergy.init.types.zRecipeTypes;
import com.devdyna.synergy.utils.x;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

@SuppressWarnings("null")
public class ReactorCellCategory implements IRecipeCategory<FuelCellRecipe> {

    private final IGuiHelper helper;
    public static final RecipeType<FuelCellRecipe> TYPE = new RecipeType<>(x.rl(ID, zRecipeTypes.FUEL_CELL_RECIPE.getId()),
            FuelCellRecipe.class);

    public ReactorCellCategory(IGuiHelper helper) {
        this.helper = helper;
        //TODO arrow animation return
    }

    @Override
    public RecipeType<FuelCellRecipe> getRecipeType() {
        return TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable(Main.ID + ".jei." + zStatic.ReactorStuff.fuel_cell);
    }

    @Override
    public @Nullable IDrawable getIcon() {
        return helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(zItems.URANIUM));
    }

    @Override
    public @Nullable IDrawable getBackground() {
        return helper.createDrawable(screenLocations.JEI_DARK_SLOTS, 0, 0, 128, 64);
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, FuelCellRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 34, 17).addItemStack(recipe.getInput());
        builder.addSlot(RecipeIngredientRole.OUTPUT, 84, 17).addItemStack(recipe.getOutput());
    }

    @Override
    public void draw(FuelCellRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX,
            double mouseY) {

        var font = Minecraft.getInstance().font;

        guiGraphics.drawString(font,
                (recipe.getDuration() == 1 ? "no tick delay"
                        : (recipe.getDuration() >= 20 ? (recipe.getDuration() >= 1200 ? (recipe.getDuration() >= 72000
                                ? recipe.getDuration() / 72000 + " hour" + (recipe.getDuration() > 72000 ? "s" : "")
                                : recipe.getDuration() / 1200 + " minute" + (recipe.getDuration() > 1200 ? "s" : "")

                        )
                                : recipe.getDuration() / 20 + " second" + (recipe.getDuration() > 20 ? "s" : ""))
                                : recipe.getDuration() + " tick" + (recipe.getDuration() > 1 ? "s" : ""))),
                45, 5, 0xA0A0A0);

        guiGraphics.drawString(font, (recipe.getHeat() >= 0 ? "+" : "") + recipe.getHeat() + "°",
                5, 40, 0xA0A0A0);

        guiGraphics.drawString(font, (recipe.getFe() >= 0 ? "+" : "") + recipe.getFe() + " fe",
                5, 50, 0xA0A0A0);

    }

}
