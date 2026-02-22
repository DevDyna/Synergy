package com.devdyna.synergy.compat.jei.categories;

import static com.devdyna.synergy.Main.ID;

import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.api.utils.Pos;
import com.devdyna.synergy.api.utils.Size;
import com.devdyna.synergy.compat.jei.categories.core.BaseRecipeCategory;
import com.devdyna.synergy.config.Common;
import com.devdyna.synergy.init.builder.survival.drying_rack.recipe.DryingRackRecipe;
import com.devdyna.synergy.init.types.zBlocks;
import com.devdyna.synergy.init.types.zRecipeTypes;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.builder.ITooltipBuilder;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
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
        return "textures/gui/jei/simple.png";
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, DryingRackRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 2, 2).addIngredients(recipe.getInput());
        builder.addSlot(RecipeIngredientRole.OUTPUT, 57, 2).addItemStack(recipe.getOutput());
    }

    @Override
    public void getTooltip(ITooltipBuilder tooltip, DryingRackRecipe recipe, IRecipeSlotsView recipeSlotsView,
            double mouseX, double mouseY) {
        if (Pos.of(28, 1).setSize(16, 16).test(mouseX, mouseY) && !Common.DISABLE_DRYING_RACK_STACK_NERFER.get())
            tooltip.add(Component.translatable(ID + ".jei.dryable_rack.tip"));

    }

    @Override
    public boolean enableTimerRender() {
        return true;
    }

    @Override
    public int tickValue(DryingRackRecipe recipe) {
        return recipe.getTicks();
    }

}
