package com.devdyna.modname_id.datagen.server;

import static net.minecraft.data.recipes.RecipeCategory.MISC;
import static net.minecraft.world.item.Items.*;
import static com.devdyna.modname_id.Main.MODID;

import java.util.concurrent.CompletableFuture;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;

@SuppressWarnings("null")
public class DataRecipe extends RecipeProvider {

        public DataRecipe(PackOutput output, CompletableFuture<HolderLookup.Provider> completableFuture) {
                super(output, completableFuture);
        }

        @Override
        protected void buildRecipes(RecipeOutput c) {

        }

        /*
         * //crafting
         * ShapedRecipeBuilder.shaped(MISC, null, 0)
         * ShapelessRecipeBuilder.shapeless(MISC, null, 0)
         * //cooking
         * SimpleCookingRecipeBuilder.smelting(null, null, null, 0, 0)
         * SimpleCookingRecipeBuilder.campfireCooking(null, null, null, 0, 0)
         * SimpleCookingRecipeBuilder.smoking(null, null, null, 0, 0)
         * SimpleCookingRecipeBuilder.blasting(null, null, null, 0, 0)
         * //stonecutting
         * SingleItemRecipeBuilder.stonecutting(null, null, null);
         * //smithing
         * SmithingTransformRecipeBuilder.smithing(null, null, null, null, null)
         */
}