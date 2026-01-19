package com.devdyna.synergy.compat.jei.categories;

import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.api.utils.Size;
import com.devdyna.synergy.common.recipes.type.QuernMillingRecipe;
import com.devdyna.synergy.compat.jei.categories.core.BaseRecipeCategory;
import com.devdyna.synergy.init.types.zBlocks;
import com.devdyna.synergy.init.types.zRecipeTypes;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.ItemLike;

@SuppressWarnings("null")
public class QuernCategory extends BaseRecipeCategory<QuernMillingRecipe> {

    public QuernCategory(IGuiHelper helper) {
        super(helper);
    }

    public static final RecipeType<RecipeHolder<QuernMillingRecipe>> TYPE = RecipeType
            .createFromVanilla(zRecipeTypes.QUERN_MILLING.getType());

    @Override
    public RecipeType<RecipeHolder<QuernMillingRecipe>> getRecipeType() {
        return TYPE;
    }

    @Override
    public String getTitleKey() {
        return zStatic.Blocks.quern;
    }

    @Override
    public ItemLike getIconItem() {
        return zBlocks.QUERN.get();
    }

    @Override
    public Size setXY() {
        return Size.of(75, 20);
    }

    @Override
    public String setBackGround() {
        return "textures/gui/simple.png";
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, QuernMillingRecipe recipe, IFocusGroup focuses) {

        builder.addSlot(RecipeIngredientRole.INPUT, 2, 2).addIngredients(recipe.getInput());

        builder.addSlot(RecipeIngredientRole.OUTPUT, 57, 2).addItemStack(recipe.getOutput());
    }

    @Override
    public void draw(QuernMillingRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics,
            double mouseX, double mouseY) {
        super.draw(recipe, recipeSlotsView, guiGraphics, mouseX, mouseY);
        guiGraphics.drawString(font,
                Component.literal(recipe.getTime() == 1 ? "no tick delay"
                        : (recipe.getTime() >= 20 ? (recipe.getTime() >= 1200 ? (recipe.getTime() >= 72000
                                ? recipe.getTime() / 72000 + " hour" + (recipe.getTime() > 72000 ? "s" : "")
                                : recipe.getTime() / 1200 + " minute" + (recipe.getTime() > 1200 ? "s" : "")

                        )
                                : recipe.getTime() / 20 + " sec" + (recipe.getTime() > 20 ? "s" : ""))
                                : recipe.getTime() + " tick" + (recipe.getTime() > 1 ? "s" : ""))),
                21, 14, 0xA0A0A0);

    }

}
