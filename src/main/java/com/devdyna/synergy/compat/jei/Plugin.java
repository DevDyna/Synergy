package com.devdyna.synergy.compat.jei;

import static com.devdyna.synergy.Main.ID;

import java.util.ArrayList;
import java.util.List;

import com.devdyna.synergy.api.recipes.types.BaseProviderRecipe;
import com.devdyna.synergy.api.utils.ClazzUtil;
import com.devdyna.synergy.api.utils.x;
import com.devdyna.synergy.compat.jei.categories.*;
import com.devdyna.synergy.compat.jei.categories.machines.AlloySmelterCategory;
import com.devdyna.synergy.compat.jei.categories.machines.CompressorCategory;
import com.devdyna.synergy.compat.jei.categories.machines.ElectricFurnaceCategory;
import com.devdyna.synergy.compat.jei.categories.machines.MaceratorCategory;
import com.devdyna.synergy.datagen.api.ExtraRecipeProvider;
import com.devdyna.synergy.init.builder.nuclear_reactor.fuel_cell.FuelCellScreen;
import com.devdyna.synergy.init.machine.alloy_smelter.AlloySmelterScreen;
import com.devdyna.synergy.init.machine.compressor.CompressorScreen;
import com.devdyna.synergy.init.machine.furnace.ElectricFurnaceScreen;
import com.devdyna.synergy.init.machine.furnace.recipe.ElectricFurnaceRecipeBuilder;
import com.devdyna.synergy.init.machine.furnace.recipe.ElectricFurnaceRecipeType;
import com.devdyna.synergy.init.machine.macerator.MaceratorScreen;
import com.devdyna.synergy.init.types.*;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.registration.*;
import mezz.jei.api.runtime.IJeiRuntime;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.server.ServerLifecycleHooks;

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
                r.addRecipeCatalyst(x.item(zBlocks.FLUID_PROVIDER), FluidProviderCategory.TYPE);

                r.addRecipeCatalyst(x.item((Item) zMachines.MACERATOR.item().get()), MaceratorCategory.TYPE);
                r.addRecipeCatalyst(x.item((Item) zMachines.COMPRESSOR.item().get()), CompressorCategory.TYPE);
                r.addRecipeCatalyst(x.item((Item) zMachines.ALLOY_SMELTER.item().get()), AlloySmelterCategory.TYPE);
                r.addRecipeCatalyst(x.item((Item) zMachines.ELECTRIC_FURNACE.item().get()),
                                ElectricFurnaceCategory.TYPE);
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
                r.addRecipeCategories(new FluidProviderCategory(helper));
                r.addRecipeCategories(new DryableBricksCategory(helper));

                r.addRecipeCategories(new MaceratorCategory(helper));

                r.addRecipeCategories(new CompressorCategory(helper));
                r.addRecipeCategories(new AlloySmelterCategory(helper));
                r.addRecipeCategories(new ElectricFurnaceCategory(helper));

        }

        @Override
        public void registerRecipes(IRecipeRegistration r) {

                RecipeManager recipes = Minecraft.getInstance().level.getRecipeManager();

                r.addRecipes(ReactorCellCategory.TYPE, recipes.getAllRecipesFor(zRecipeTypes.FUEL_CELL_RECIPE.getType())
                                .stream().map(RecipeHolder::value).toList());

                r.addRecipes(UrnCategory.TYPE,
                                recipes.getAllRecipesFor(zRecipeTypes.URN_RITUAL_RECIPE.getType()).stream()
                                                .map(RecipeHolder::value).toList());

                r.addRecipes(CropResultCategory.TYPE,
                                recipes.getAllRecipesFor(zRecipeTypes.CROP_RESULT.getType()).stream()
                                                .map(RecipeHolder::value).toList());

                r.addRecipes(ItemUseCategory.TYPE, recipes.getAllRecipesFor(zRecipeTypes.ITEM_USE.getType()).stream()
                                .map(RecipeHolder::value).toList());

                r.addRecipes(QuernCategory.TYPE, recipes.getAllRecipesFor(zRecipeTypes.QUERN_MILLING.getType()).stream()
                                .map(RecipeHolder::value).toList());

                r.addRecipes(ItemProviderCategory.TYPE,
                                (List<BaseProviderRecipe<ItemStack>>) (List<?>) recipes
                                                .getAllRecipesFor(zRecipeTypes.ITEM_PROVIDER.getType()).stream()
                                                .map(RecipeHolder::value).toList());

                r.addRecipes(FluidProviderCategory.TYPE,
                                (List<BaseProviderRecipe<FluidStack>>) (List<?>) recipes
                                                .getAllRecipesFor(zRecipeTypes.FLUID_PROVIDER.getType()).stream()
                                                .map(RecipeHolder::value).toList());

                r.addRecipes(DryableBricksCategory.TYPE,
                                recipes.getAllRecipesFor(zRecipeTypes.DRYABLE_BRICKS.getType()).stream()
                                                .map(RecipeHolder::value).toList());

                r.addRecipes(MaceratorCategory.TYPE,
                                recipes.getAllRecipesFor(zMachines.MACERATOR.recipe().getType()).stream()
                                                .map(RecipeHolder::value).toList());

                r.addRecipes(CompressorCategory.TYPE,
                                recipes.getAllRecipesFor(zMachines.COMPRESSOR.recipe().getType()).stream()
                                                .map(RecipeHolder::value).toList());

                r.addRecipes(AlloySmelterCategory.TYPE,
                                recipes.getAllRecipesFor(zMachines.ALLOY_SMELTER.recipe().getType()).stream()
                                                .map(RecipeHolder::value).toList());

                r.addRecipes(ElectricFurnaceCategory.TYPE,
                                recipes.getAllRecipesFor(zMachines.ELECTRIC_FURNACE.recipe().getType()).stream()
                                                .map(RecipeHolder::value).toList());

                r.addRecipes(ElectricFurnaceCategory.TYPE,
                                recipes.getAllRecipesFor(RecipeType.SMELTING).stream()
                                                .map(RecipeHolder::value)
                                                .map(s -> (ElectricFurnaceRecipeType) ElectricFurnaceRecipeBuilder
                                                                .of()
                                                                .delay(60)
                                                                .energy(10)
                                                                .input(s.getIngredients().getFirst())
                                                                .output(s.getResultItem(ServerLifecycleHooks
                                                                                .getCurrentServer().registryAccess()))
                                                                .createRecipe())
                                                .toList());

        }

        @Override
        public void registerGuiHandlers(IGuiHandlerRegistration r) {
                r.addRecipeClickArea(FuelCellScreen.class, 74, 30, 22, 20,
                                ReactorCellCategory.TYPE);

                r.addRecipeClickArea(MaceratorScreen.class, 75, 35, 22, 15,
                                MaceratorCategory.TYPE);

                r.addRecipeClickArea(CompressorScreen.class, 75, 35, 22, 15,
                                CompressorCategory.TYPE);

                r.addRecipeClickArea(AlloySmelterScreen.class, 75, 35, 22, 15,
                                AlloySmelterCategory.TYPE);

                r.addRecipeClickArea(ElectricFurnaceScreen.class, 75, 35, 22, 15,
                                ElectricFurnaceCategory.TYPE);
        }

}