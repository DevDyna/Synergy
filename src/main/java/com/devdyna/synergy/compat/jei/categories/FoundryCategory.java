package com.devdyna.synergy.compat.jei.categories;

import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.api.utils.Size;
import com.devdyna.synergy.compat.jei.api.JEIFluidTankHelper;
import com.devdyna.synergy.compat.jei.categories.core.BaseRecipeCategory;
import com.devdyna.synergy.init.builder.survival.foundry.recipe.FoundryRecipe;
import com.devdyna.synergy.init.types.zBlocks;
import com.devdyna.synergy.init.types.zRecipeTypes;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeType;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.ItemLike;

@SuppressWarnings("null")
public class FoundryCategory extends BaseRecipeCategory<FoundryRecipe> {

    public FoundryCategory(IGuiHelper helper) {
        super(helper);
    }

    public static final RecipeType<RecipeHolder<FoundryRecipe>> TYPE = RecipeType
            .createFromVanilla(zRecipeTypes.FOUNDRY.getType());

    @Override
    public RecipeType<RecipeHolder<FoundryRecipe>> getRecipeType() {
        return TYPE;
    }

    @Override
    public String getTitleKey() {
        return zStatic.Blocks.foundry;
    }

    @Override
    public ItemLike getIconItem() {
        return zBlocks.FOUNDRY.get();
    }

    @Override
    public Size setXY() {
        return Size.of(67, 36);
    }

    @Override
    public String setBackGround() {
        return "textures/gui/jei/foundry.png";
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, FoundryRecipe recipe, IFocusGroup focuses) {

        JEIFluidTankHelper.of()
                .fluid(recipe.getFluid())
                .offset(50,34+1)
                .scale(2.0f, 1.0f)
                .build((x, y) -> builder.addOutputSlot(x, y));

        builder.addInputSlot( 1, 12).addIngredients(recipe.getInput());
    }

    @Override
    public boolean enableTimerRender() {
        return true;
    }

    @Override
    public int tickValue(FoundryRecipe recipe) {
        return recipe.getTicks();
    }

        @Override
    public Size tickPos() {
        return Size.of(11, 30);
    }

}
