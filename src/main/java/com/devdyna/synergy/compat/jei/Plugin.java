package com.devdyna.synergy.compat.jei;

import static com.devdyna.synergy.Main.ID;

import java.util.ArrayList;
import java.util.List;

import com.devdyna.synergy.api.utils.ClazzUtil;
import com.devdyna.synergy.api.utils.RecipeUtils;
import com.devdyna.synergy.api.utils.x;
import com.devdyna.synergy.common.recipes.type.node_providers.FluidProviderRecipe;
import com.devdyna.synergy.common.recipes.type.node_providers.ItemProviderRecipe;
import com.devdyna.synergy.compat.jei.categories.*;
import com.devdyna.synergy.compat.jei.categories.machines.AlloySmelterCategory;
import com.devdyna.synergy.compat.jei.categories.machines.CasterCategory;
import com.devdyna.synergy.compat.jei.categories.machines.CompressorCategory;
import com.devdyna.synergy.compat.jei.categories.machines.ElectricFurnaceCategory;
import com.devdyna.synergy.compat.jei.categories.machines.ExtractorCategory;
import com.devdyna.synergy.compat.jei.categories.machines.MaceratorCategory;
import com.devdyna.synergy.config.Common;
import com.devdyna.synergy.datagen.api.ExtraRecipeProvider;
import com.devdyna.synergy.init.builder.industrial_machines.alloy_smelter.AlloySmelterScreen;
import com.devdyna.synergy.init.builder.industrial_machines.caster.CasterScreen;
import com.devdyna.synergy.init.builder.industrial_machines.compressor.CompressorScreen;
import com.devdyna.synergy.init.builder.industrial_machines.extractor.ExtractorScreen;
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
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.server.ServerLifecycleHooks;

@SuppressWarnings({ "unchecked", "null" })
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
                                r.addRecipeCatalyst(x.item(zBlocks.VOID_BOX), VoidBoxInfusionCategory.TYPE);



                r.addRecipeCatalyst(x.item((Item) zMachines.MACERATOR.item().get()), MaceratorCategory.TYPE);
                r.addRecipeCatalyst(x.item((Item) zMachines.COMPRESSOR.item().get()), CompressorCategory.TYPE);
                r.addRecipeCatalyst(x.item((Item) zMachines.ALLOY_SMELTER.item().get()), AlloySmelterCategory.TYPE);
                r.addRecipeCatalyst(x.item((Item) zMachines.ELECTRIC_FURNACE.item().get()),
                                ElectricFurnaceCategory.TYPE);
                r.addRecipeCatalyst(x.item((Item) zMachines.EXTRACTOR.item().get()), ExtractorCategory.TYPE);
                r.addRecipeCatalyst(x.item((Item) zMachines.CASTING_FACTORY.item().get()), CasterCategory.TYPE);


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
                r.addRecipeCategories(new VoidBoxInfusionCategory(helper));

                r.addRecipeCategories(new MaceratorCategory(helper));
                r.addRecipeCategories(new CompressorCategory(helper));
                r.addRecipeCategories(new AlloySmelterCategory(helper));
                r.addRecipeCategories(new ElectricFurnaceCategory(helper));
                r.addRecipeCategories(new ExtractorCategory(helper));
                r.addRecipeCategories(new CasterCategory(helper));

                

        }

        @Override
        public void registerRecipes(IRecipeRegistration r) {

                RecipeManager recipes = Minecraft.getInstance().level.getRecipeManager();

                r.addRecipes(ReactorCellCategory.TYPE,
                                RecipeUtils.getRecipes(zRecipeTypes.FUEL_CELL_RECIPE));

                r.addRecipes(UrnCategory.TYPE,
                                RecipeUtils.getRecipes(zRecipeTypes.URN_RITUAL_RECIPE));

                r.addRecipes(CropResultCategory.TYPE,
                                RecipeUtils.getRecipes(zRecipeTypes.CROP_RESULT));

                r.addRecipes(ItemUseCategory.TYPE, RecipeUtils.getRecipes(zRecipeTypes.ITEM_USE));

                r.addRecipes(QuernCategory.TYPE, RecipeUtils.getRecipes(zRecipeTypes.QUERN_MILLING));

                r.addRecipes(ItemProviderCategory.TYPE,
                                (List<ItemProviderRecipe<ItemStack>>) (List<?>) recipes
                                                .getAllRecipesFor(zRecipeTypes.ITEM_PROVIDER.getType()).stream()
                                                .map(RecipeHolder::value).toList());

                r.addRecipes(FluidProviderCategory.TYPE,
                                (List<FluidProviderRecipe<FluidStack>>) (List<?>) recipes
                                                .getAllRecipesFor(zRecipeTypes.FLUID_PROVIDER.getType()).stream()
                                                .map(RecipeHolder::value).toList());

                r.addRecipes(DryableBricksCategory.TYPE,
                                RecipeUtils.getRecipes(zRecipeTypes.DRYABLE_BRICKS));

                r.addRecipes(MaceratorCategory.TYPE,
                                RecipeUtils.getRecipes(zMachines.MACERATOR));

                r.addRecipes(CompressorCategory.TYPE,
                                RecipeUtils.getRecipes(zMachines.COMPRESSOR));

                r.addRecipes(AlloySmelterCategory.TYPE,
                                RecipeUtils.getRecipes(zMachines.ALLOY_SMELTER));

                r.addRecipes(ElectricFurnaceCategory.TYPE,
                                RecipeUtils.getRecipes(zMachines.ELECTRIC_FURNACE));

                r.addRecipes(ExtractorCategory.TYPE,
                                RecipeUtils.getRecipes(zMachines.EXTRACTOR));

                r.addRecipes(CasterCategory.TYPE,
                                RecipeUtils.getRecipes(zMachines.CASTING_FACTORY));

                if (!Common.DISABLE_MACHINE_FURNACE_PROCESS_VANILLA.get())
                        r.addRecipes(ElectricFurnaceCategory.TYPE,
                                        recipes.getAllRecipesFor(RecipeType.SMELTING).stream()
                                                        .map(s ->
                                                        new RecipeHolder<>(x.rl(zMachines.ELECTRIC_FURNACE.id()+"_generated_" + s.id().getPath().replace("/", "")),
                                                                        (ElectricFurnaceRecipeType) ElectricFurnaceRecipeBuilder
                                                                                        .of()
                                                                                        .delay(60)
                                                                                        .energy(10)
                                                                                        .input(s.value().getIngredients()
                                                                                                        .getFirst())
                                                                                        .output(s.value().getResultItem(
                                                                                                        ServerLifecycleHooks
                                                                                                                        .getCurrentServer()
                                                                                                                        .registryAccess()))
                                                                                        .createRecipe())

                                                        )

                                                        .toList());

                r.addRecipes(VoidBoxInfusionCategory.TYPE,
                                RecipeUtils.getRecipes(zRecipeTypes.VOID_BOX_INFUSION));

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

                r.addRecipeClickArea(ExtractorScreen.class, 75, 35, 22, 15,
                                ExtractorCategory.TYPE);
                r.addRecipeClickArea(CasterScreen.class, 75, 35, 22, 15,
                                CasterCategory.TYPE);
        }

}