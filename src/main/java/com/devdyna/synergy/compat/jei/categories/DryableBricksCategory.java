package com.devdyna.synergy.compat.jei.categories;

import com.devdyna.synergy.Main;
import com.devdyna.synergy.api.utils.Pos;
import com.devdyna.synergy.api.utils.Size;
import com.devdyna.synergy.api.utils.x;
import com.devdyna.synergy.common.recipes.type.DryableBricksRecipe;
import com.devdyna.synergy.compat.jei.categories.core.BaseRecipeCategory;
import com.devdyna.synergy.init.types.zRecipeTypes;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.builder.ITooltipBuilder;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeType;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;

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
    public void setRecipe(IRecipeLayoutBuilder builder, DryableBricksRecipe recipe, IFocusGroup focuses) {

        builder.addInputSlot(1, 1).addIngredients(recipe.getInput());
        builder.addOutputSlot(60, 1).addItemStack(recipe.getOutput());

    }

    @Override
    public void getTooltip(ITooltipBuilder tooltip, DryableBricksRecipe recipe, IRecipeSlotsView recipeSlotsView,
            double mouseX, double mouseY) {
        if (Pos.of(28, 1).setSize(16, 16).test(mouseX, mouseY))
            tooltip.add(Component.translatable(Main.ID + ".jei.dryable.tip"));

    }


    @Override
    public String getTitleKey() {
        return "drying_bricks";
    }

    @Override
    public ItemLike getIconItem() {
        return Items.BRICK;
    }

    @Override
    public Size setXY() {
        return Size.of(77, 18);
    }

    @Override
    public String setBackGround() {
        return "textures/gui/delay.png";
    }

}
