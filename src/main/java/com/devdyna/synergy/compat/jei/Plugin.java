package com.devdyna.synergy.compat.jei;

import static com.devdyna.synergy.Main.ID;

import java.util.List;

import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.api.machine.BaseMachineScreen;
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
import com.devdyna.synergy.compat.jei.categories.machines.MelterCategory;
import com.devdyna.synergy.compat.jei.categories.machines.RockCrusherCategory;
import com.devdyna.synergy.compat.jei.categories.provider.FluidProviderCategory;
import com.devdyna.synergy.compat.jei.categories.provider.ItemProviderCategory;
import com.devdyna.synergy.config.Common;
import com.devdyna.synergy.init.builder.industrial_machines.alloy_smelter.AlloySmelterScreen;
import com.devdyna.synergy.init.builder.industrial_machines.caster.CasterScreen;
import com.devdyna.synergy.init.builder.industrial_machines.compressor.CompressorScreen;
import com.devdyna.synergy.init.builder.industrial_machines.extractor.ExtractorScreen;
import com.devdyna.synergy.init.builder.industrial_machines.furnace.ElectricFurnaceBE;
import com.devdyna.synergy.init.builder.industrial_machines.furnace.ElectricFurnaceScreen;
import com.devdyna.synergy.init.builder.industrial_machines.furnace.recipe.ElectricFurnaceRecipeBuilder;
import com.devdyna.synergy.init.builder.industrial_machines.furnace.recipe.ElectricFurnaceRecipeType;
import com.devdyna.synergy.init.builder.industrial_machines.macerator.MaceratorScreen;
import com.devdyna.synergy.init.builder.industrial_machines.melter.MelterScreen;
import com.devdyna.synergy.init.builder.industrial_machines.rock_crusher.RockCrusherScreen;
import com.devdyna.synergy.init.builder.nuclear_reactor.fuel_cell.FuelCellScreen;
import com.devdyna.synergy.init.types.*;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.gui.handlers.IGuiContainerHandler;
import mezz.jei.api.registration.*;
import mezz.jei.api.runtime.IJeiRuntime;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Rect2i;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.registries.DeferredHolder;
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

                // List<RecipeHolder<CraftingRecipe>> toHide = new ArrayList<>();

                // ClazzUtil.getAllzItems().forEach(b -> {
                // if (ExtraRecipeProvider.clearNBT.contains(b.get())) {
                // Minecraft.getInstance().level.getRecipeManager()
                // .byKey(x.parse(ID + ":" + x.path(b.get()) + "_clear_nbt"))
                // .ifPresent(r -> toHide.add((RecipeHolder<CraftingRecipe>) r));
                // }
                // });

                // jeiRuntime.getRecipeManager().hideRecipes(RecipeTypes.CRAFTING, toHide);

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

                r.addRecipeCatalyst(x.item(zMachines.MACERATOR.item().get()), MaceratorCategory.TYPE);
                r.addRecipeCatalyst(x.item(zMachines.COMPRESSOR.item().get()), CompressorCategory.TYPE);
                r.addRecipeCatalyst(x.item(zMachines.ALLOY_SMELTER.item().get()), AlloySmelterCategory.TYPE);
                r.addRecipeCatalyst(x.item(zMachines.ELECTRIC_FURNACE.item().get()),
                                ElectricFurnaceCategory.TYPE);
                r.addRecipeCatalyst(x.item(zMachines.EXTRACTOR.item().get()), ExtractorCategory.TYPE);
                r.addRecipeCatalyst(x.item(zMachines.CASTING_FACTORY.item().get()), CasterCategory.TYPE);
                r.addRecipeCatalyst(x.item(zMachines.MELTER.item().get()), MelterCategory.TYPE);
                r.addRecipeCatalyst(x.item(zMachines.ROCK_CRUSHER.item().get()), RockCrusherCategory.TYPE);

                r.addRecipeCatalyst(x.item(zItems.CHISEL), RecipeTypes.STONECUTTING);

                r.addRecipeCatalyst(x.item(zBlocks.CRUSHING_TUB), CrushingTubCategory.TYPE);
                r.addRecipeCatalyst(x.item(zBlocks.EVAPORATION_BASIN), EvaporationBasinCategory.TYPE);
                r.addRecipeCatalyst(x.item(zBlocks.FOUNDRY), FoundryCategory.TYPE);
                r.addRecipeCatalyst(x.item(zBlocks.FUEL_TANK), FoundryFuelRecipeCategory.TYPE);
                r.addRecipeCatalyst(x.item(zBlocks.CASTING_TABLE), CastingTableCategory.TYPE);

                r.addRecipeCatalysts(DryingRackCategory.TYPE, zStatic.ALL_DRYING_RACKS.stream().map(DeferredHolder::get)
                                .map(Block::asItem).toArray(Item[]::new));

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
                r.addRecipeCategories(new MelterCategory(helper));
                r.addRecipeCategories(new RockCrusherCategory(helper));

                r.addRecipeCategories(new CrushingTubCategory(helper));
                r.addRecipeCategories(new EvaporationBasinCategory(helper));

                r.addRecipeCategories(new DryingRackCategory(helper));
                r.addRecipeCategories(new FoundryCategory(helper));
                r.addRecipeCategories(new FoundryFuelRecipeCategory(helper));
                r.addRecipeCategories(new CastingTableCategory(helper));

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

                r.addRecipes(MelterCategory.TYPE,
                                RecipeUtils.getRecipes(zMachines.MELTER));

                r.addRecipes(RockCrusherCategory.TYPE,
                                RecipeUtils.getRecipes(zMachines.ROCK_CRUSHER));

                if (!Common.DISABLE_MACHINE_FURNACE_PROCESS_VANILLA.get())
                        r.addRecipes(ElectricFurnaceCategory.TYPE,
                                        recipes.getAllRecipesFor(RecipeType.SMELTING).stream()
                                                        .map(s -> new RecipeHolder<>(
                                                                        x.rl(zMachines.ELECTRIC_FURNACE.id()
                                                                                        + "_generated_"
                                                                                        + s.id().getPath().replace("/",
                                                                                                        "")),
                                                                        (ElectricFurnaceRecipeType) ElectricFurnaceRecipeBuilder
                                                                                        .of()
                                                                                        .delay(ElectricFurnaceBE
                                                                                                        .getCalculatedDelay(
                                                                                                                        s.value()))
                                                                                        .energy(Common.MACHINE_FURNACE_PROCESS_VANILLA_FE_COST
                                                                                                        .get())
                                                                                        .input(x.itemSized(s.value()
                                                                                                        .getIngredients()
                                                                                                        .getFirst()))
                                                                                        .output(s.value().getResultItem(
                                                                                                        ServerLifecycleHooks
                                                                                                                        .getCurrentServer()
                                                                                                                        .registryAccess()))
                                                                                        .createRecipe())

                                                        )

                                                        .toList());

                r.addRecipes(VoidBoxInfusionCategory.TYPE,
                                RecipeUtils.getRecipes(zRecipeTypes.VOID_BOX_INFUSION));

                r.addRecipes(CrushingTubCategory.TYPE,
                                RecipeUtils.getRecipes(zRecipeTypes.CRUSHING_TUB));

                r.addRecipes(EvaporationBasinCategory.TYPE,
                                RecipeUtils.getRecipes(zRecipeTypes.EVAPORATING_BASIN));

                r.addRecipes(DryingRackCategory.TYPE,
                                RecipeUtils.getRecipes(zRecipeTypes.DRYING_RACK));
                r.addRecipes(FoundryCategory.TYPE,
                                RecipeUtils.getRecipes(zRecipeTypes.FOUNDRY));
                r.addRecipes(FoundryFuelRecipeCategory.TYPE,
                                RecipeUtils.getRecipes(zRecipeTypes.FOUNDRY_FUELS));
                r.addRecipes(CastingTableCategory.TYPE,
                                RecipeUtils.getRecipes(zRecipeTypes.CASTING_TABLE));

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
                r.addRecipeClickArea(MelterScreen.class, 75, 35, 22, 15,
                                MelterCategory.TYPE);
                r.addRecipeClickArea(RockCrusherScreen.class, 75, 35, 22, 15,
                                RockCrusherCategory.TYPE);

                r.addGuiContainerHandler(
                                (Class<? extends BaseMachineScreen<?>>) (Class<?>) BaseMachineScreen.class,
                                new IGuiContainerHandler<BaseMachineScreen<?>>() {
                                        @Override
                                        public List<Rect2i> getGuiExtraAreas(BaseMachineScreen<?> screen) {
                                                return List.of(
                                                                new Rect2i(
                                                                                screen.getGuiLeft() + 172,
                                                                                screen.getGuiTop(),
                                                                                32,
                                                                                86));
                                        }
                                });

        }

        public void registerIngredientAliases(IIngredientAliasRegistration r) {
                List.of(zBlocks.SOLAR_PANEL, zBlocks.REACTOR_CONTROLLER, zBlocks.LASER_ROTOR).stream()
                                .map(DeferredHolder::get).map(x::item)
                                .forEach(e -> r.addAlias(e, ID + ".jei.atlas.generator"));

                List.of(zBlocks.RECURSIVE_REPEATER, zBlocks.PULSE_REPEATER, zBlocks.INVERTED_REPEATER).stream()
                                .map(DeferredHolder::get).map(x::item)
                                .forEach(e -> r.addAlias(e, ID + ".jei.atlas.redstone"));

                List.of(zBlocks.WOODEN_TINY_CHEST, zBlocks.STONE_TINY_CHEST, zBlocks.ORNATE_TINY_CHEST).stream()
                                .map(DeferredHolder::get).map(x::item)
                                .forEach(e -> r.addAlias(e, ID + ".jei.atlas.storage.item"));

                List.of(zBlocks.LOGIC_BOX).stream()
                                .map(DeferredHolder::get).map(x::item)
                                .forEach(e -> r.addAlias(e, ID + ".jei.atlas.filter.item"));

                List.of(zBlocks.VOID_BOX).stream()
                                .map(DeferredHolder::get).map(x::item)
                                .forEach(e -> r.addAlias(e, ID + ".jei.atlas.trash_can.item"));

                List.of(zBlocks.FAUCET).stream()
                                .map(DeferredHolder::get).map(x::item)
                                .forEach(e -> {
                                        r.addAlias(e, ID + ".jei.atlas.transfer.fluid");
                                        r.addAlias(e, ID + ".jei.atlas.type.transmitter");
                                });

                List.of(zBlocks.SIMPLE_TANK, zBlocks.FUEL_TANK).stream()
                                .map(DeferredHolder::get).map(x::item)
                                .forEach(e -> {
                                        r.addAlias(e, ID + ".jei.atlas.portable");
                                        r.addAlias(e, ID + ".jei.atlas.storage.fluid");
                                });

                List.of(
                                zBlocks.ITEM_PROVIDER,
                                zBlocks.ITEM_RETRIEVAL,
                                zBlocks.ITEM_TRANSFER,
                                zBlocks.FLUID_PROVIDER,
                                zBlocks.FLUID_RETRIEVAL,
                                zBlocks.FLUID_TRANSFER,
                                zBlocks.ENERGY_RETRIEVAL,
                                zBlocks.ENERGY_TRANSFER).stream()
                                .map(DeferredHolder::get).map(x::item)
                                .forEach(e -> {
                                        r.addAlias(e, ID + ".jei.atlas.conduit");
                                        r.addAlias(e, ID + ".jei.atlas.pipe");
                                        r.addAlias(e, ID + ".jei.atlas.tube");

                                        if (e.is(zItemTag.NODE_ITEM))
                                                r.addAlias(e, ID + ".jei.atlas.transfer.item");
                                        if (e.is(zItemTag.NODE_FLUID))
                                                r.addAlias(e, ID + ".jei.atlas.transfer.fluid");
                                        if (e.is(zItemTag.NODE_ENERGY))
                                                r.addAlias(e, ID + ".jei.atlas.transfer.energy");

                                        if (e.is(zItemTag.NODES_TRANSFER))
                                                r.addAlias(e, ID + ".jei.atlas.type.transmitter");

                                        if (e.is(zItemTag.NODES_PROVIDER))
                                                r.addAlias(e, ID + ".jei.atlas.type.producer");

                                        if (e.is(zItemTag.NODES_RETRIEVAL))
                                                r.addAlias(e, ID + ".jei.atlas.type.reciever");

                                });

        }

}