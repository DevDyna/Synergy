package com.devdyna.synergy.compat.jei.categories;

import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.api.utils.Size;
import com.devdyna.synergy.compat.jei.categories.core.BaseRecipeCategory;
import com.devdyna.synergy.init.builder.survival.drying_rack.recipe.DryingRackRecipe;
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
public class DryingRackCategory extends BaseRecipeCategory<DryingRackRecipe> {

    public DryingRackCategory(IGuiHelper helper) {
        super(helper);
    }

    public static final RecipeType<RecipeHolder<DryingRackRecipe>> TYPE = RecipeType
            .createFromVanilla(zRecipeTypes.DRYING_RACK.getType());

    @Override
    public RecipeType<RecipeHolder<DryingRackRecipe>> getRecipeType() {
        return TYPE;
    }

    @Override
    public String getTitleKey() {
        return zStatic.Blocks.drying_rack;
    }

    @Override
    public ItemLike getIconItem() {
        return zBlocks.OAK_DRYING_RACK.get();
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
    public void setRecipe(IRecipeLayoutBuilder builder, DryingRackRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 2, 2).addIngredients(recipe.getInput());
        builder.addSlot(RecipeIngredientRole.OUTPUT, 57, 2).addItemStack(recipe.getOutput());
    }

    @Override
    public void draw(DryingRackRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics,
            double mouseX, double mouseY) {
        super.draw(recipe, recipeSlotsView, guiGraphics, mouseX, mouseY);
        guiGraphics.drawString(font,
                Component.literal(recipe.getTicks() == 1 ? "no tick delay"
                        : (recipe.getTicks() >= 20 ? (recipe.getTicks() >= 1200 ? (recipe.getTicks() >= 72000
                                ? recipe.getTicks() / 72000 + " hour" + (recipe.getTicks() > 72000 ? "s" : "")
                                : recipe.getTicks() / 1200 + " minute" + (recipe.getTicks() > 1200 ? "s" : "")

                        )
                                : recipe.getTicks() / 20 + " sec" + (recipe.getTicks() > 20 ? "s" : ""))
                                : recipe.getTicks() + " tick" + (recipe.getTicks() > 1 ? "s" : ""))),
                21, 14, 0xA0A0A0);

    }

}
