package com.devdyna.synergy.compat.jei.categories;

import static com.devdyna.synergy.Main.ID;

import org.jetbrains.annotations.Nullable;

import com.devdyna.synergy.Main;
import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.client.gui.screenLocations;
import com.devdyna.synergy.compat.jei.drawable.SimpleIcon;
import com.devdyna.synergy.init.recipeTypes.type.UrnRitualRecipe;
import com.devdyna.synergy.init.types.zBlocks;
import com.devdyna.synergy.init.types.zRecipeTypes;
import com.devdyna.synergy.utils.x;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;

@SuppressWarnings("null")
public class UrnCategory implements IRecipeCategory<UrnRitualRecipe> {

    private final IGuiHelper helper;
    public static final RecipeType<UrnRitualRecipe> TYPE = new RecipeType<>(
            x.rl(ID, zRecipeTypes.URN_RITUAL_RECIPE.getId()),
            UrnRitualRecipe.class);

    public UrnCategory(IGuiHelper helper) {
        this.helper = helper;
        // TODO arrow animation return
    }

    @Override
    public RecipeType<UrnRitualRecipe> getRecipeType() {
        return TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable(Main.ID + ".jei." + zStatic.Blocks.urn);
    }

    @Override
    public @Nullable IDrawable getIcon() {
        return SimpleIcon.of(helper, zBlocks.URN.get().asItem());
    }

    @Override
    public @Nullable IDrawable getBackground() {
        return helper.createDrawable(screenLocations.URN_WINDOW, 0, 0, 184, 82);
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, UrnRitualRecipe recipe, IFocusGroup focuses) {
        recipe.getIngredients().forEach(i -> {
            var index = recipe.getIngredients().indexOf(i);

            builder.addSlot(RecipeIngredientRole.INPUT, 14 + (index * 17), 9).addIngredients(i);

        });

        builder.addSlot(RecipeIngredientRole.OUTPUT, 82, 53).addItemStack(recipe.getResultItem());
    }

}
