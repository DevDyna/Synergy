package com.devdyna.synergy.compat.jei.categories;

import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.api.utils.Size;
import com.devdyna.synergy.compat.jei.categories.core.BaseRecipeCategory;
import com.devdyna.synergy.init.builder.magic.urn.recipe.UrnRitualRecipe;
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
public class UrnCategory extends BaseRecipeCategory<UrnRitualRecipe> {

    public UrnCategory(IGuiHelper helper) {
        super(helper);
    }

    public static final RecipeType<RecipeHolder<UrnRitualRecipe>> TYPE = RecipeType
            .createFromVanilla(zRecipeTypes.URN_RITUAL_RECIPE.getType());

    @Override
    public RecipeType<RecipeHolder<UrnRitualRecipe>> getRecipeType() {
        return TYPE;
    }

    @Override
    public String getTitleKey() {
        return zStatic.Blocks.urn;
    }

    @Override
    public ItemLike getIconItem() {
        return zBlocks.URN.get();
    }

    @Override
    public Size setXY() {
        return Size.of(156, 64);
    }

    @Override
    public String setBackGround() {
        return "textures/gui/urn_window.png";
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, UrnRitualRecipe recipe, IFocusGroup focuses) {
        recipe.getIngredients()
                .forEach(i -> builder
                        .addSlot(RecipeIngredientRole.INPUT, 2 + (recipe.getIngredients().indexOf(i) * 17), 2)
                        .addIngredients(i));

        builder.addSlot(RecipeIngredientRole.OUTPUT, 70, 46).addItemStack(recipe.getResultItem());
    }

}
