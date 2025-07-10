package com.devdyna.synergy.datagen.server;

import static com.devdyna.synergy.Main.ID;
import static net.minecraft.data.recipes.RecipeCategory.*;
import java.util.concurrent.CompletableFuture;

import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.init.types.*;
import com.devdyna.synergy.utils.DataGenUtil;

import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.Tags;

@SuppressWarnings({ "null" })
public class DataRecipe extends RecipeProvider {

        public DataRecipe(PackOutput o, CompletableFuture<HolderLookup.Provider> c) {
                super(o, c);
        }

        @Override
        protected void buildRecipes(RecipeOutput c) {

                nodeRecipe(zBlocks.ITEM_TRANSFER.get(), Blocks.CHEST, c);
                nodeRecipe(zBlocks.ITEM_PROVIDER.get(), Items.IRON_PICKAXE, c);
                nodeRecipe(zBlocks.ITEM_RETRIEVAL.get(), Blocks.HOPPER, c);

                ShapedRecipeBuilder.shaped(MISC, zBlocks.PIPE.get().asItem(), 16)
                                .pattern("SSS")
                                .pattern("GRG")
                                .pattern("SSS")
                                .define('R', Tags.Items.DUSTS_REDSTONE)
                                .define('G', Tags.Items.GLASS_BLOCKS_COLORLESS)
                                .define('S', zItemTag.STONE_SLABS)
                                .unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                                                .hasItems(Items.REDSTONE, Items.GLASS, Items.STONE_SLAB))
                                .group(zStatic.PipeStuff.pipe).save(c);

                ShapelessRecipeBuilder.shapeless(MISC, Items.BLUE_DYE, 4)
                                .requires(zItems.BLUE_CUP_MUSHROOM.get())
                                .unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                                                .hasItems(zItems.BLUE_CUP_MUSHROOM.get()))
                                .group(zStatic.Plants.BLUE_CUP_MUSHROOM).save(c);

                ShapelessRecipeBuilder.shapeless(MISC, Items.BROWN_DYE, 4)
                                .requires(zItems.VIOLET_WEBCAP_MUSHROOM.get())
                                .unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                                                .hasItems(zItems.VIOLET_WEBCAP_MUSHROOM.get()))
                                .group(zStatic.Plants.VIOLET_WEBCAP_MUSHROOM).save(c);

                ShapedRecipeBuilder.shaped(MISC, zItems.WOODEN_CROOK.get(), 1)
                                .pattern("SS")
                                .pattern(" S")
                                .pattern(" S")
                                .define('S', Items.STICK)
                                .unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                                                .hasItems(Items.STICK))
                                .group(zStatic.Items.wooden_crook).save(c);

                ShapedRecipeBuilder.shaped(MISC, zItems.WOODEN_CROOK.get(), 1)
                                .pattern("SS")
                                .pattern("S ")
                                .pattern("S ")
                                .define('S', Items.STICK)
                                .unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                                                .hasItems(Items.STICK))
                                .group(zStatic.Items.wooden_crook).save(c, DataGenUtil.getResource(
                                                zItems.WOODEN_CROOK.get().getDescriptionId()
                                                                .replace("block." + ID + ".", "")
                                                                + "_alt"));

                ShapedRecipeBuilder.shaped(MISC, zItems.SMASHER.get(), 1)
                                .pattern("NI ")
                                .pattern(" I ")
                                .pattern(" IN")
                                .define('I', Items.IRON_INGOT)
                                .define('N', Items.IRON_NUGGET)
                                .unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                                                .hasItems(Items.IRON_INGOT, Items.IRON_NUGGET))
                                .group(zStatic.Items.smasher).save(c);

                ShapedRecipeBuilder.shaped(MISC, zItems.SMASHER.get(), 1)
                                .pattern(" IN")
                                .pattern(" I ")
                                .pattern("NI ")
                                .define('I', Items.IRON_INGOT)
                                .define('N', Items.IRON_NUGGET)
                                .unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                                                .hasItems(Items.IRON_INGOT, Items.IRON_NUGGET))
                                .group(zStatic.Items.smasher).save(c, DataGenUtil.getResource(
                                                zItems.SMASHER.get().getDescriptionId()
                                                                .replace("block." + ID + ".", "")
                                                                + "_alt"));

                ShapedRecipeBuilder.shaped(MISC, zItems.PIPE_REFARCTORIZER.get(), 1)
                                .pattern(" I")
                                .pattern("S ")
                                .define('S', Items.STICK)
                                .define('I', Items.IRON_NUGGET)
                                .unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                                                .hasItems(Items.IRON_INGOT, Items.IRON_NUGGET))
                                .group(zStatic.Items.refactorizer).save(c);

                ShapedRecipeBuilder.shaped(MISC, zItems.CONFIGURATOR.get(), 1)
                                .pattern("N N")
                                .pattern("IEI")
                                .pattern("RIR")
                                .define('R', Items.REDSTONE)
                                .define('N', Items.IRON_NUGGET)
                                .define('E', Items.EMERALD)
                                .define('I', Items.IRON_INGOT)
                                .unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                                                .hasItems(Items.REDSTONE, Items.EMERALD, Items.IRON_INGOT,
                                                                Items.IRON_NUGGET))
                                .group(zStatic.Items.configurator).save(c);

                ShapelessRecipeBuilder.shapeless(MISC, zItems.AZALEA_SEEDS.get(), 3)
                                .requires(zItemTag.AZALEA_BUSHES)
                                .unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                                                .hasItems(Items.FLOWERING_AZALEA))
                                .group(zStatic.Items.Azalea.seeds).save(c);

                ShapelessRecipeBuilder.shapeless(MISC, Items.STRING, 3)
                                .requires(zItems.COTTON.get())
                                .unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                                                .hasItems(zItems.COTTON.get()))
                                .group(zStatic.Plants.COTTON).save(c, DataGenUtil.getResource(
                                                Items.STRING.getDescriptionId()
                                                                .replace("block.minecraft.", "")
                                                                + ID + "_alt"));

                ShapedRecipeBuilder.shaped(MISC, Items.HANGING_ROOTS, 1)
                                .pattern("RR")
                                .define('R', zItems.SMALL_AZALEA_ROOTS.get())
                                .unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                                                .hasItems(zItems.SMALL_AZALEA_ROOTS.get()))
                                .group(zStatic.Items.Azalea.roots).save(c, DataGenUtil.getResource(
                                                Items.HANGING_ROOTS.getDescriptionId()
                                                                .replace("block.minecraft.", "")
                                                                + ID + "_alt"));

                ShapedRecipeBuilder.shaped(MISC, Items.FLOWERING_AZALEA_LEAVES, 1)
                                .pattern("LL")
                                .pattern("LL")
                                .define('L', zItems.SMALL_AZALEA_LEAF.get())
                                .unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                                                .hasItems(zItems.SMALL_AZALEA_LEAF.get()))
                                .group(zStatic.Items.Azalea.leaf).save(c, DataGenUtil.getResource(
                                                Items.FLOWERING_AZALEA_LEAVES.getDescriptionId()
                                                                .replace("block.minecraft.", "")
                                                                + ID + "_alt"));

                ShapelessRecipeBuilder.shapeless(MISC, Items.FLOWERING_AZALEA, 1)
                                .requires(zItems.SMALL_AZALEA_LEAF.get())
                                .requires(Items.AZALEA)
                                .unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                                                .hasItems(Items.AZALEA, zItems.SMALL_AZALEA_LEAF.get()))
                                .group(zStatic.Items.Azalea.leaf).save(c, DataGenUtil.getResource(
                                                Items.FLOWERING_AZALEA.getDescriptionId()
                                                                .replace("block.minecraft.", "")
                                                                + ID + "_alt"));

                ShapedRecipeBuilder.shaped(MISC, zBlocks.SPRINKLER.get(), 1)
                                .pattern("WWW")
                                .pattern("RIR")
                                .pattern("AIA")
                                .define('A', zBlocks.ADOBE.get())
                                .define('W', zBlocks.WAXED_PLANKS.get())
                                .define('R', Items.REDSTONE)
                                .define('I', Items.IRON_INGOT)
                                .unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                                                .hasItems(zBlocks.WAXED_PLANKS.get(), zBlocks.ADOBE.get()))
                                .group(zStatic.Blocks.sprinkler).save(c);

                SimpleCookingRecipeBuilder.blasting(Ingredient.of(Items.IRON_BLOCK), MISC,
                                zBlocks.RUSTIC_METAL.get().asItem(), 0.01f, 100)
                                .unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                                                .hasItems(Items.IRON_BLOCK))
                                .group(zStatic.DecorativeBlocks.rustic_metal).save(c, DataGenUtil.getResource(
                                                zBlocks.RUSTIC_METAL.get().getDescriptionId()
                                                                .replace("block." + ID + ".", "")
                                                                + ID + "_alt"));

                ShapelessRecipeBuilder.shapeless(MISC, zBlocks.WAXED_PLANKS.get(), 8)
                                .requires(ItemTags.PLANKS)
                                .requires(ItemTags.PLANKS)
                                .requires(ItemTags.PLANKS)
                                .requires(ItemTags.PLANKS)
                                .requires(Items.HONEYCOMB)
                                .requires(ItemTags.PLANKS)
                                .requires(ItemTags.PLANKS)
                                .requires(ItemTags.PLANKS)
                                .requires(ItemTags.PLANKS)
                                .unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                                                .hasItems(Items.HONEYCOMB))
                                .group(zStatic.DecorativeBlocks.waxed_planks).save(c);

                ShapelessRecipeBuilder.shapeless(MISC, zBlocks.ADOBE.get(), 3)
                                .requires(Items.MUD)
                                .requires(Items.DIORITE)
                                .requires(Items.CLAY)
                                .unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                                                .hasItems(Items.DIORITE, Items.MUD, Items.CLAY))
                                .group(zStatic.DecorativeBlocks.adobe).save(c);

                ShapelessRecipeBuilder.shapeless(MISC, zItems.BONE_MEAL_MIXTURE.get(), 3)
                                .requires(Tags.Items.SLIME_BALLS)
                                .requires(Items.BONE_MEAL)
                                .requires(Tags.Items.DUSTS_REDSTONE)
                                .requires(Items.BLAZE_POWDER)
                                .unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                                                .hasItems(Items.BONE_MEAL, Items.BLAZE_POWDER, Items.REDSTONE,
                                                                Items.SLIME_BALL))
                                .group(zStatic.Items.bone_meal_mixture).save(c);

                stairBuilder(zBlocks.WAXED_PLANKS_STAIR.get(), Ingredient.of(zBlocks.WAXED_PLANKS.get()));
                slabBuilder(BUILDING_BLOCKS, zBlocks.WAXED_PLANKS_STAIR.get(),
                                Ingredient.of(zBlocks.WAXED_PLANKS.get()));

//TODO
ShapedRecipeBuilder.shaped(MISC, zBlocks.SOLAR_PANEL.get(), 1)
                                .pattern("LLL")
                                .pattern("RIR")
                                .define('A', zBlocks.ADOBE.get())
                                .define('W', zBlocks.WAXED_PLANKS.get())
                                .define('R', Items.REDSTONE)
                                .define('I', Items.IRON_INGOT)
                                .unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                                                .hasItems(zBlocks.WAXED_PLANKS.get(), zBlocks.ADOBE.get()))
                                .group(zStatic.Blocks.sprinkler).save(c);

        }

        // nodes
        private void nodeRecipe(Block b, ItemLike catalyst, RecipeOutput c) {

                ShapedRecipeBuilder.shaped(MISC, b.asItem(), 1)
                                .pattern(" P ")
                                .pattern("RBR")
                                .pattern("SCS")
                                .define('P', zBlocks.PIPE.get().asItem())
                                .define('R', Tags.Items.DUSTS_REDSTONE)
                                .define('C', catalyst)
                                .define('B', Tags.Items.STORAGE_BLOCKS_REDSTONE)
                                .define('S', Tags.Items.STONES)
                                .unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                                                .hasItems(Items.REDSTONE, catalyst, Items.REDSTONE_BLOCK,
                                                                zBlocks.PIPE.get()))
                                .group(zStatic.PipeStuff.types.item_node).save(c);

                ShapedRecipeBuilder.shaped(MISC, b.asItem(), 4)
                                .pattern(" P ")
                                .pattern("RBR")
                                .pattern("SCS")
                                .define('P', zBlocks.PIPE.get().asItem())
                                .define('R', Tags.Items.DUSTS_REDSTONE)
                                .define('C', catalyst)
                                .define('B', Items.ENDER_PEARL)
                                .define('S', Tags.Items.STONES)
                                .unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                                                .hasItems(Items.REDSTONE, Items.ENDER_PEARL, catalyst,
                                                                zBlocks.PIPE.get()))
                                .group(zStatic.PipeStuff.types.item_node)
                                .save(c, DataGenUtil.getResource(
                                                b.getDescriptionId()
                                                                .replace("block." + ID + ".", "")
                                                                + "_alt"));

        }

}