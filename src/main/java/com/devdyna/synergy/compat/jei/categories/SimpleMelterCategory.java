package com.devdyna.synergy.compat.jei.categories;

import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.api.utils.Size;
import com.devdyna.synergy.compat.jei.api.JEIFluidTankHelper;
import com.devdyna.synergy.compat.jei.categories.core.BaseRecipeCategory;
import com.devdyna.synergy.init.builder.survival.simple_melter.recipe.SimpleMelterRecipe;
import com.devdyna.synergy.init.types.zBlocks;
import com.devdyna.synergy.init.types.zRecipeTypes;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeType;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.ItemLike;

@SuppressWarnings("null")
public class SimpleMelterCategory extends BaseRecipeCategory<SimpleMelterRecipe> {

    public SimpleMelterCategory(IGuiHelper helper) {
        super(helper);
    }

    public static final RecipeType<RecipeHolder<SimpleMelterRecipe>> TYPE = RecipeType
            .createFromVanilla(zRecipeTypes.SIMPLE_MELTER.getType());

    @Override
    public RecipeType<RecipeHolder<SimpleMelterRecipe>> getRecipeType() {
        return TYPE;
    }

    @Override
    public String getTitleKey() {
        return zStatic.Blocks.evaporation_basin;
    }

    @Override
    public ItemLike getIconItem() {
        return zBlocks.EVAPORATION_BASIN.get();
    }

    @Override
    public Size setXY() {
        return Size.of(67, 36);
    }

    @Override
    public String setBackGround() {
        return "textures/gui/jei/simple_melter.png";
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, SimpleMelterRecipe recipe, IFocusGroup focuses) {

        JEIFluidTankHelper.of()
                .fluid(recipe.getFluid())
                .offset(50,34)
                .scale(2.0f, 1.0f)
                .build((x, y) -> builder.addOutputSlot(x, y));

        builder.addInputSlot( 1, 12).addIngredients(recipe.getInput());
    }

    @Override
    public boolean enableTimerRender() {
        return true;
    }

    @Override
    public int tickValue(SimpleMelterRecipe recipe) {
        return recipe.getTicks();
    }

}
