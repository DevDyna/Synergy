package com.devdyna.synergy.compat.jei.categories;

import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.api.utils.Size;
import com.devdyna.synergy.compat.jei.api.JEIFluidTankHelper;
import com.devdyna.synergy.compat.jei.categories.core.BaseRecipeCategory;
import com.devdyna.synergy.init.builder.survival.evaporation_basin.recipe.EvaporationBasinRecipe;
import com.devdyna.synergy.init.types.zBlocks;
import com.devdyna.synergy.init.types.zRecipeTypes;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.ItemLike;

@SuppressWarnings("null")
public class EvaporationBasinCategory extends BaseRecipeCategory<EvaporationBasinRecipe> {

    public EvaporationBasinCategory(IGuiHelper helper) {
        super(helper);
    }

    public static final RecipeType<RecipeHolder<EvaporationBasinRecipe>> TYPE = RecipeType
            .createFromVanilla(zRecipeTypes.EVAPORATING_BASIN.getType());

    @Override
    public RecipeType<RecipeHolder<EvaporationBasinRecipe>> getRecipeType() {
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
        return Size.of(108, 64);
    }

    @Override
    public String setBackGround() {
        return "textures/gui/jei/evaporation_basin.png";
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, EvaporationBasinRecipe recipe, IFocusGroup focuses) {

        JEIFluidTankHelper.of()
                .fluid(recipe.getFluid())
                .offset(21, 49)
                .scale(2.0f, 1.0f)
                .build((x, y) -> builder.addInputSlot(x, y));

        builder.addSlot(RecipeIngredientRole.OUTPUT, 71+1, 28+1).addItemStack(recipe.getOutput());
    }

}
