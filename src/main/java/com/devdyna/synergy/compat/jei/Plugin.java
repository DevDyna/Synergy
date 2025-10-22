package com.devdyna.synergy.compat.jei;

import static com.devdyna.synergy.Main.ID;

import java.util.ArrayList;
import java.util.List;

import com.devdyna.synergy.api.datagen.ExtraRecipeProvider;
import com.devdyna.synergy.client.gui.fuel_cell.FuelCellScreen;
import com.devdyna.synergy.compat.jei.categories.*;
import com.devdyna.synergy.init.types.*;
import com.devdyna.synergy.utils.ClazzUtil;
import com.devdyna.synergy.utils.x;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.registration.*;
import mezz.jei.api.runtime.IJeiRuntime;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;

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
            if (!ExtraRecipeProvider.clearNBT.contains(b.get())) {
                Minecraft.getInstance().level.getRecipeManager()
                        .byKey(ResourceLocation.parse(b.getId() + "_clear_nbt"))
                        .ifPresent(r -> toHide.add((RecipeHolder<CraftingRecipe>) r));
            }
        });

        jeiRuntime.getRecipeManager().hideRecipes(RecipeTypes.CRAFTING, toHide);
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration r) {
        r.addRecipeCatalyst(x.item(zBlocks.REACTOR_CONTROLLER), ReactorCellCategory.TYPE);
        r.addRecipeCatalyst(x.item(zBlocks.REACTOR_FUEL_CELL), ReactorCellCategory.TYPE);
        r.addRecipeCatalyst(x.item(zBlocks.URN), UrnCategory.TYPE);
        r.addRecipeCatalyst(x.item(zBlocks.QUERN), QuernCategory.TYPE);
        r.addRecipeCatalyst(x.item(zBlocks.ITEM_PROVIDER), ItemProviderCategory.TYPE);
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration r) {
        var helper = r.getJeiHelpers().getGuiHelper();

        r.addRecipeCategories(new ReactorCellCategory(helper));
        r.addRecipeCategories(new UrnCategory(helper));
        r.addRecipeCategories(new CropResultCategory(helper));
        r.addRecipeCategories(new ItemUseCategory(helper));
        r.addRecipeCategories(new QuernCategory(helper));

        r.addRecipeCategories(new ItemProviderCategory(helper));
        r.addRecipeCategories(new DryableBricksCategory(helper));

    }

    @Override
    public void registerRecipes(IRecipeRegistration r) {

        RecipeManager recipes = Minecraft.getInstance().level.getRecipeManager();

        r.addRecipes(ReactorCellCategory.TYPE, recipes.getAllRecipesFor(zRecipeTypes.FUEL_CELL_RECIPE.getType())
                .stream().map(RecipeHolder::value).toList());

        r.addRecipes(UrnCategory.TYPE, recipes.getAllRecipesFor(zRecipeTypes.URN_RITUAL_RECIPE.getType()).stream()
                .map(RecipeHolder::value).toList());

        r.addRecipes(CropResultCategory.TYPE, recipes.getAllRecipesFor(zRecipeTypes.CROP_RESULT.getType()).stream()
                .map(RecipeHolder::value).toList());

        r.addRecipes(ItemUseCategory.TYPE, recipes.getAllRecipesFor(zRecipeTypes.ITEM_USE.getType()).stream()
                .map(RecipeHolder::value).toList());

        r.addRecipes(QuernCategory.TYPE, recipes.getAllRecipesFor(zRecipeTypes.QUERN_MILLING.getType()).stream()
                .map(RecipeHolder::value).toList());

        r.addRecipes(ItemProviderCategory.TYPE, recipes.getAllRecipesFor(zRecipeTypes.ITEM_PROVIDER.getType()).stream()
                .map(RecipeHolder::value).toList());

        r.addRecipes(DryableBricksCategory.TYPE,
                recipes.getAllRecipesFor(zRecipeTypes.DRYABLE_BRICKS.getType()).stream()
                        .map(RecipeHolder::value).toList());
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration r) {
        r.addRecipeClickArea(FuelCellScreen.class, 74, 30, 22, 20,
                ReactorCellCategory.TYPE);
    }

}