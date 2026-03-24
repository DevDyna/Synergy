package com.devdyna.synergy.init.types;

import static com.devdyna.synergy.Main.ID;

import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.api.registers.RecipeRegister;
import com.devdyna.synergy.common.recipes.foundry_fuel.FoundryFuelEfficiencyRecipe;
import com.devdyna.synergy.common.recipes.item_use.ItemUseRecipe;
import com.devdyna.synergy.common.recipes.node_provider.fluid.FluidProviderRecipe;
import com.devdyna.synergy.common.recipes.node_provider.item.ItemProviderRecipe;
import com.devdyna.synergy.common.recipes.resource_info.ResourceInfoRecipe;
import com.devdyna.synergy.init.builder.magic.quern.recipe.QuernMillingRecipe;
import com.devdyna.synergy.init.builder.magic.quern.recipe.QuernMillingRecipeSerializer;
import com.devdyna.synergy.init.builder.magic.urn.recipe.UrnRitualRecipe;
import com.devdyna.synergy.init.builder.magic.urn.recipe.UrnRitualRecipeSerializer;
import com.devdyna.synergy.init.builder.magic.void_box.recipe.VoidBoxInfusionRecipe;
import com.devdyna.synergy.init.builder.magic.void_box.recipe.VoidBoxInfusionRecipeSerializer;
import com.devdyna.synergy.init.builder.nuclear_reactor.fuel_cell.recipe.FuelCellRecipe;
import com.devdyna.synergy.init.builder.nuclear_reactor.fuel_cell.recipe.FuelCellRecipeSerializer;
import com.devdyna.synergy.init.builder.survival.casting_table.recipe.CastingTableRecipe;
import com.devdyna.synergy.init.builder.survival.crushing_tub.recipe.CrushingTubRecipe;
import com.devdyna.synergy.init.builder.survival.crushing_tub.recipe.CrushingTubRecipeSerializer;
import com.devdyna.synergy.init.builder.survival.drying_rack.recipe.DryingRackRecipe;
import com.devdyna.synergy.init.builder.survival.drying_rack.recipe.DryingRackRecipeSerializer;
import com.devdyna.synergy.init.builder.survival.evaporation_basin.recipe.EvaporationBasinRecipe;
import com.devdyna.synergy.init.builder.survival.evaporation_basin.recipe.EvaporationBasinRecipeSerializer;
import com.devdyna.synergy.init.builder.survival.foundry.recipe.FoundryRecipe;
import com.devdyna.synergy.init.builder.survival.foundry.recipe.FoundryRecipeSerializer;
import com.devdyna.synergy.init.builder.survival.placeable_bricks.recipe.DryableBricksRecipe;
import com.devdyna.synergy.init.builder.survival.placeable_bricks.recipe.DryableBricksRecipeSerializer;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.registries.DeferredRegister;

public class zRecipeTypes {
        // ------------------------------------------------------------------------------------------------------------------------------------//
        public static void register(IEventBus bus) {
                SERIALIZERS.register(bus);
                TYPES.register(bus);
        }

        // ------------------------------------------------------------------------------------------------------------------------------------//
        public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS = DeferredRegister
                        .create(Registries.RECIPE_SERIALIZER, ID);
        public static final DeferredRegister<RecipeType<?>> TYPES = DeferredRegister.create(Registries.RECIPE_TYPE, ID);
        // ------------------------------------------------------------------------------------------------------------------------------------//
        public static final RecipeRegister<FuelCellRecipe> FUEL_CELL_RECIPE = RecipeRegister.of(zStatic.ReactorStuff.fuel_cell,
                        FuelCellRecipeSerializer::new);

        public static final RecipeRegister<UrnRitualRecipe> URN_RITUAL_RECIPE = RecipeRegister.of(zStatic.Blocks.urn,
                        UrnRitualRecipeSerializer::new);

        public static final RecipeRegister<QuernMillingRecipe> QUERN_MILLING = RecipeRegister.of(zStatic.Blocks.quern,
                        QuernMillingRecipeSerializer::new);

        public static final RecipeRegister<ResourceInfoRecipe> RESOURCE_INFO = RecipeRegister.of("resource_info",
                        ResourceInfoRecipe.Serializer::new);

        public static final RecipeRegister<ItemUseRecipe> ITEM_USE = RecipeRegister.of("item_use", ItemUseRecipe.Serializer::new);

        public static final RecipeRegister<ItemProviderRecipe<ItemStack>> ITEM_PROVIDER = RecipeRegister.of("provider/item",
                        ItemProviderRecipe.Serializer::new);

        public static final RecipeRegister<FluidProviderRecipe<FluidStack>> FLUID_PROVIDER = RecipeRegister.of("provider/fluid",
                        FluidProviderRecipe.Serializer::new);

        public static final RecipeRegister<DryableBricksRecipe> DRYABLE_BRICKS = RecipeRegister.of(zStatic.DryableBricks.TYPE,
                        DryableBricksRecipeSerializer::new);

        public static final RecipeRegister<VoidBoxInfusionRecipe> VOID_BOX_INFUSION = RecipeRegister.of(zStatic.Blocks.void_box+"_conversion",
                        VoidBoxInfusionRecipeSerializer::new);

        public static final RecipeRegister<DryingRackRecipe> DRYING_RACK = RecipeRegister.of(zStatic.Blocks.drying_rack,
                        DryingRackRecipeSerializer::new);

        public static final RecipeRegister<CrushingTubRecipe> CRUSHING_TUB = RecipeRegister.of(zStatic.Blocks.crushing_tub,
                        CrushingTubRecipeSerializer::new);

        public static final RecipeRegister<EvaporationBasinRecipe> EVAPORATING_BASIN = RecipeRegister.of(zStatic.Blocks.evaporation_basin,
                        EvaporationBasinRecipeSerializer::new);

        public static final RecipeRegister<FoundryRecipe> FOUNDRY = RecipeRegister.of(zStatic.Blocks.foundry,
                        FoundryRecipeSerializer::new);

        public static final RecipeRegister<FoundryFuelEfficiencyRecipe> FOUNDRY_FUELS = RecipeRegister.of(zStatic.Blocks.foundry+"_fuels",
                        FoundryFuelEfficiencyRecipe.Serializer::new);

        public static final RecipeRegister<CastingTableRecipe> CASTING_TABLE = RecipeRegister.of(zStatic.Blocks.casting_table,
                        CastingTableRecipe.Serializer::new);


        // ------------------------------------------------------------------------------------------------------------------------------------//
}
