package com.devdyna.synergy.datagen.server;

import static com.devdyna.synergy.Main.ID;
import static net.minecraft.data.recipes.RecipeCategory.MISC;
import static net.minecraft.world.item.Items.*;
import java.util.concurrent.CompletableFuture;

import com.devdyna.synergy.init.types.zBlocks;
import com.devdyna.synergy.init.types.zItemTag;
import com.devdyna.synergy.utils.DataGenUtil;

import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.Tags;

@SuppressWarnings({ "null", "unused" })
public class DataRecipe extends RecipeProvider {

        public DataRecipe(PackOutput output, CompletableFuture<HolderLookup.Provider> completableFuture) {
                super(output, completableFuture);
        }

        @Override
        protected void buildRecipes(RecipeOutput c) {

                ShapedRecipeBuilder.shaped(MISC, zBlocks.ITEM_TRANSFER.get().asItem(), 1)
                                .pattern(" P ")
                                .pattern("RBR")
                                .pattern("SCS")
                                .define('P', zBlocks.PIPE.get().asItem())
                                .define('R', Tags.Items.DUSTS_REDSTONE)
                                .define('C', Items.CHEST)
                                .define('B', Tags.Items.STORAGE_BLOCKS_REDSTONE)
                                .define('S', Tags.Items.STONES)
                                .unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                                                .hasItems(Items.REDSTONE, Items.CHEST, Items.REDSTONE_BLOCK,
                                                                zBlocks.PIPE.get()))
                                .group(ID).save(c);

                ShapedRecipeBuilder.shaped(MISC, zBlocks.ITEM_TRANSFER.get().asItem(), 4)
                                .pattern(" P ")
                                .pattern("RBR")
                                .pattern("SCS")
                                .define('P', zBlocks.PIPE.get().asItem())
                                .define('R', Tags.Items.DUSTS_REDSTONE)
                                .define('C', Items.ENDER_PEARL)
                                .define('B', Tags.Items.STORAGE_BLOCKS_REDSTONE)
                                .define('S', Tags.Items.STONES)
                                .unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                                                .hasItems(Items.REDSTONE, Items.ENDER_PEARL, Items.REDSTONE_BLOCK,
                                                                zBlocks.PIPE.get()))
                                .group(ID)
                                .save(c, DataGenUtil.getResource(
                                                zBlocks.ITEM_TRANSFER.get().getDescriptionId()
                                                                .replace("block." + ID + ".", "")
                                                                + "_alt"));

                ShapedRecipeBuilder.shaped(MISC, zBlocks.PIPE.get().asItem(), 16)
                                .pattern("SSS")
                                .pattern("GRG")
                                .pattern("SSS")
                                .define('R', Tags.Items.DUSTS_REDSTONE)
                                .define('G', Tags.Items.GLASS_BLOCKS_COLORLESS)
                                .define('S', zItemTag.STONE_SLABS)
                                .unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                                                .hasItems(Items.REDSTONE, Items.GLASS, Items.STONE_SLAB))
                                .group(ID).save(c);

                ShapelessRecipeBuilder.shapeless(MISC, zBlocks.ITEM_PROVIDER.get().asItem())
                                .requires(zBlocks.ITEM_TRANSFER.get())
                                .unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                                                .hasItems(zBlocks.ITEM_TRANSFER.get()))
                                .group(ID).save(c);

        }

}