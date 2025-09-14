package com.devdyna.synergy.compat.jei;

import static com.devdyna.synergy.Main.ID;

import java.util.ArrayList;
import java.util.List;

import com.devdyna.synergy.client.gui.fuel_cell.FuelCellScreen;
import com.devdyna.synergy.compat.jei.recipes.ReactorCellCategory;
import com.devdyna.synergy.datagen.server.DataRecipe;
import com.devdyna.synergy.init.recipeTypes.type.FuelCellRecipe;
import com.devdyna.synergy.init.types.zBlocks;
import com.devdyna.synergy.init.types.zRecipeTypes;
import com.devdyna.synergy.utils.ClazzUtil;
import com.devdyna.synergy.utils.x;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.runtime.IJeiRuntime;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.RecipeHolder;

@SuppressWarnings({ "unchecked", "unlikely-arg-type", "null" })
@JeiPlugin
public class Plugin implements IModPlugin {

    @Override
    public ResourceLocation getPluginUid() {
        return x.rl(ID, "jei_plugin");
    }

    @Override
    public void onRuntimeAvailable(IJeiRuntime jeiRuntime) {

        List<RecipeHolder<CraftingRecipe>> toHide = new ArrayList<>();

        ClazzUtil.getAllzItems().forEach(b -> {
            if (!DataRecipe.clearNBT.contains(b.get())) {
                Minecraft.getInstance().level.getRecipeManager()
                        .byKey(ResourceLocation.parse(b.getId() + "_clear_nbt"))
                        .ifPresent(r -> toHide.add((RecipeHolder<CraftingRecipe>) r));
            }
        });

        jeiRuntime.getRecipeManager().hideRecipes(RecipeTypes.CRAFTING, toHide);
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration r) {
        r.addRecipeCatalyst(x.item(zBlocks.REACTOR_CONTROLLER.get().asItem(), 1), ReactorCellCategory.TYPE);
        r.addRecipeCatalyst(x.item(zBlocks.REACTOR_FUEL_CELL.get().asItem(), 1), ReactorCellCategory.TYPE);
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration r) {
        r.addRecipeCategories(new ReactorCellCategory(
                r.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration r) {
        List<FuelCellRecipe> recipes = Minecraft.getInstance().level.getRecipeManager()
                .getAllRecipesFor(zRecipeTypes.FUEL_CELL_RECIPE.getType()).stream().map(RecipeHolder::value).toList();

        r.addRecipes(ReactorCellCategory.TYPE, recipes);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration r) {
        r.addRecipeClickArea(FuelCellScreen.class, 74, 30, 22, 20,
                ReactorCellCategory.TYPE);
    }

}