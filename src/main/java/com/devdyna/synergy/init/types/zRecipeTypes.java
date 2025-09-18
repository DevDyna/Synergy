package com.devdyna.synergy.init.types;

import static com.devdyna.synergy.Main.ID;

import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.api.zRecipe;
import com.devdyna.synergy.init.recipeTypes.serializer.*;
import com.devdyna.synergy.init.recipeTypes.type.*;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.bus.api.IEventBus;
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
        public static final zRecipe<FuelCellRecipe> FUEL_CELL_RECIPE = new zRecipe<FuelCellRecipe>(
                        zStatic.ReactorStuff.fuel_cell, FuelCellRecipeSerializer::new,
                        () -> new RecipeType<FuelCellRecipe>() {
                                @Override
                                public String toString() {
                                        return FUEL_CELL_RECIPE.getId();
                                }
                        });

        public static final zRecipe<UrnRitualRecipe> URN_RITUAL_RECIPE = new zRecipe<UrnRitualRecipe>(
                        zStatic.Blocks.urn, UrnRitualRecipeSerializer::new,
                        () -> new RecipeType<UrnRitualRecipe>() {
                                @Override
                                public String toString() {
                                        return URN_RITUAL_RECIPE.getId();
                                }
                        });

        public static final zRecipe<CropResultRecipe> CROP_RESULT = new zRecipe<CropResultRecipe>(
                        "crop_result", CropResultRecipeSerializer::new,
                        () -> new RecipeType<CropResultRecipe>() {
                                @Override
                                public String toString() {
                                        return CROP_RESULT.getId();
                                }
                        });
        // ------------------------------------------------------------------------------------------------------------------------------------//
}
