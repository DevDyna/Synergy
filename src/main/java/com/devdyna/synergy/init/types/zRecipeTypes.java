package com.devdyna.synergy.init.types;

import static com.devdyna.synergy.Main.ID;

import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.api.zRecipe;
import com.devdyna.synergy.common.recipes.serializer.*;
import com.devdyna.synergy.common.recipes.type.*;
import com.devdyna.synergy.common.recipes.type.node_providers.FluidProviderRecipe;
import com.devdyna.synergy.common.recipes.type.node_providers.ItemProviderRecipe;

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
        public static final zRecipe<FuelCellRecipe> FUEL_CELL_RECIPE = zRecipe.of(zStatic.ReactorStuff.fuel_cell,
                        FuelCellRecipeSerializer::new);

        public static final zRecipe<UrnRitualRecipe> URN_RITUAL_RECIPE = zRecipe.of(zStatic.Blocks.urn,
                        UrnRitualRecipeSerializer::new);

        public static final zRecipe<QuernMillingRecipe> QUERN_MILLING = zRecipe.of(zStatic.Blocks.quern,
                        QuernMillingRecipeSerializer::new);

        public static final zRecipe<CropResultRecipe> CROP_RESULT = zRecipe.of("crop_result",
                        CropResultRecipeSerializer::new);

        public static final zRecipe<ItemUseRecipe> ITEM_USE = zRecipe.of("item_use", ItemUseRecipeSerializer::new);

        public static final zRecipe<ItemProviderRecipe<ItemStack>> ITEM_PROVIDER = zRecipe.of("provider/item",
                        ItemProviderRecipeSerializer::new);

        public static final zRecipe<FluidProviderRecipe<FluidStack>> FLUID_PROVIDER = zRecipe.of("provider/fluid",
                        FluidProviderRecipeSerializer::new);

        public static final zRecipe<DryableBricksRecipe> DRYABLE_BRICKS = zRecipe.of("dryable_bricks",
                        DryableBricksRecipeSerializer::new);

        public static final zRecipe<VoidBoxInfusionRecipe> VOID_BOX_INFUSION = zRecipe.of("void_box_infusion",
                        VoidBoxInfusionRecipeSerializer::new);

        public static final zRecipe<DryingRackRecipe> DRYING_RACK = zRecipe.of("drying_rack",
                        DryingRackRecipeSerializer::new);

        public static final zRecipe<CrushingTubRecipe> CRUSHING_TUB = zRecipe.of("crushing_tub",
                        CrushingTubRecipeSerializer::new);

        public static final zRecipe<EvaporationBasinRecipe> EVAPORATING_BASIN = zRecipe.of("evaporating_tub",
                        EvaporationBasinRecipeSerializer::new);


        // ------------------------------------------------------------------------------------------------------------------------------------//
}
