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
import com.devdyna.synergy.init.builder.industrial_machines.alloy_smelter.AlloySmelterScreen;
import com.devdyna.synergy.init.builder.industrial_machines.compressor.CompressorScreen;
import com.devdyna.synergy.init.builder.industrial_machines.furnace.ElectricFurnaceScreen;
import com.devdyna.synergy.init.builder.industrial_machines.furnace.recipe.ElectricFurnaceRecipeBuilder;
import com.devdyna.synergy.init.builder.industrial_machines.furnace.recipe.ElectricFurnaceRecipeType;
import com.devdyna.synergy.init.builder.industrial_machines.macerator.MaceratorScreen;
import com.devdyna.synergy.init.builder.nuclear_reactor.fuel_cell.FuelCellScreen;
import com.devdyna.synergy.init.types.*;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.registration.*;
import mezz.jei.api.runtime.IJeiRuntime;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.server.ServerLifecycleHooks;

@SuppressWarnings({ "unchecked", "unlikely-arg-type" ,"deprecation"})
@JeiPlugin
public class Plugin implements IModPlugin {

        @Override
        public Identifier getPluginUid() {
                return x.rl(ID, "jei_plugin");
        }

        @Override
        public void onRuntimeAvailable(IJeiRuntime jeiRuntime) {

                List<RecipeHolder<CraftingRecipe>> toHide = new ArrayList<>();

                ClazzUtil.getAllzItems().forEach(b -> {
                        if (!ExtraRecipeProvider.clearNBT.contains(b.get())) {
                                Minecraft.getInstance().level.getServer().getRecipeManager()
                                                .byKey(x.recipeID(x.rl(b.getId() + "_clear_nbt")))
                                                .ifPresent(r -> toHide.add((RecipeHolder<CraftingRecipe>) r));
                        }
                });

                jeiRuntime.getRecipeManager().hideRecipes(RecipeTypes.CRAFTING, toHide);

        }

        @Override
        public void registerRecipeCatalysts(IRecipeCatalystRegistration r) {

                r.addCraftingStation(ReactorCellCategory.TYPE, zBlocks.REACTOR_CONTROLLER.get());
                r.addCraftingStation(ReactorCellCategory.TYPE, zBlocks.REACTOR_FUEL_CELL.get());
                r.addCraftingStation(UrnCategory.TYPE, zBlocks.URN.get());
                r.addCraftingStation(QuernCategory.TYPE, zBlocks.QUERN.get());
                r.addCraftingStation(ItemProviderCategory.TYPE, zBlocks.ITEM_PROVIDER.get());
                r.addCraftingStation(FluidProviderCategory.TYPE, zBlocks.FLUID_PROVIDER.get());
                r.addCraftingStation(MaceratorCategory.TYPE, zMachines.MACERATOR.item().get());
                r.addCraftingStation(CompressorCategory.TYPE, zMachines.COMPRESSOR.item().get());
                r.addCraftingStation(AlloySmelterCategory.TYPE, zMachines.ALLOY_SMELTER.item().get());
                r.addCraftingStation(ElectricFurnaceCategory.TYPE, zMachines.ELECTRIC_FURNACE.item().get());
                r.addCraftingStation(VoidBoxInfusionCategory.TYPE, zBlocks.VOID_BOX.get());

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

                r.addRecipeCategories(new VoidBoxInfusionCategory(helper));

        }

        
        @Override
        public void registerRecipes(IRecipeRegistration r) {

                RecipeManager recipes = Minecraft.getInstance().level.getServer().getRecipeManager();

                r.addRecipes(ReactorCellCategory.TYPE,
                                recipes.recipeMap().byType(zRecipeTypes.FUEL_CELL_RECIPE.getType())
                                                .stream().map(RecipeHolder::value).toList());

                r.addRecipes(UrnCategory.TYPE,
                                recipes.recipeMap().byType(zRecipeTypes.URN_RITUAL_RECIPE.getType()).stream()
                                                .map(RecipeHolder::value).toList());

                r.addRecipes(CropResultCategory.TYPE,
                                recipes.recipeMap().byType(zRecipeTypes.CROP_RESULT.getType()).stream()
                                                .map(RecipeHolder::value).toList());

                r.addRecipes(ItemUseCategory.TYPE, recipes.recipeMap().byType(zRecipeTypes.ITEM_USE.getType()).stream()
                                .map(RecipeHolder::value).toList());

                r.addRecipes(QuernCategory.TYPE,
                                recipes.recipeMap().byType(zRecipeTypes.QUERN_MILLING.getType()).stream()
                                                .map(RecipeHolder::value).toList());

                r.addRecipes(ItemProviderCategory.TYPE,
                                (List<BaseProviderRecipe<ItemStack>>) (List<?>) recipes.recipeMap()
                                                .byType(zRecipeTypes.ITEM_PROVIDER.getType()).stream()
                                                .map(RecipeHolder::value).toList());

                r.addRecipes(FluidProviderCategory.TYPE,
                                (List<BaseProviderRecipe<FluidStack>>) (List<?>) recipes.recipeMap()
                                                .byType(zRecipeTypes.FLUID_PROVIDER.getType()).stream()
                                                .map(RecipeHolder::value).toList());

                r.addRecipes(DryableBricksCategory.TYPE,
                                recipes.recipeMap().byType(zRecipeTypes.DRYABLE_BRICKS.getType()).stream()
                                                .map(RecipeHolder::value).toList());

                r.addRecipes(MaceratorCategory.TYPE,
                                recipes.recipeMap().byType(zMachines.MACERATOR.recipe().getType()).stream()
                                                .map(RecipeHolder::value).toList());

                r.addRecipes(CompressorCategory.TYPE,
                                recipes.recipeMap().byType(zMachines.COMPRESSOR.recipe().getType()).stream()
                                                .map(RecipeHolder::value).toList());

                r.addRecipes(AlloySmelterCategory.TYPE,
                                recipes.recipeMap().byType(zMachines.ALLOY_SMELTER.recipe().getType()).stream()
                                                .map(RecipeHolder::value).toList());

                r.addRecipes(ElectricFurnaceCategory.TYPE,
                                recipes.recipeMap().byType(zMachines.ELECTRIC_FURNACE.recipe().getType()).stream()
                                                .map(RecipeHolder::value).toList());

                r.addRecipes(ElectricFurnaceCategory.TYPE,
                                recipes.recipeMap().byType(RecipeType.SMELTING).stream()
                                                .map(RecipeHolder::value)
                                                .map(s -> (ElectricFurnaceRecipeType) ElectricFurnaceRecipeBuilder
                                                                .of()
                                                                .delay(60)
                                                                .energy(10)
                                                                .input(s.input())
                                                                .output(s.assemble(
                                                                                new SingleRecipeInput(x.item(s.input()
                                                                                                .items().findFirst()
                                                                                                .get().value())),
                                                                                ServerLifecycleHooks.getCurrentServer()
                                                                                                .registryAccess()))
                                                                .createRecipe())
                                                .toList());

                r.addRecipes(VoidBoxInfusionCategory.TYPE,
                                recipes.recipeMap().byType(zRecipeTypes.VOID_BOX_INFUSION.getType()).stream()
                                                .map(RecipeHolder::value).toList());

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