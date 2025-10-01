package com.devdyna.synergy.api.datagen;

import static com.devdyna.synergy.Main.ID;
import static net.minecraft.data.recipes.RecipeCategory.MISC;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.init.recipeTypes.builders.CropResultBuilder;
import com.devdyna.synergy.init.recipeTypes.builders.QuernMillingBuilder;
import com.devdyna.synergy.init.recipeTypes.builders.ReactorCellBuilder;
import com.devdyna.synergy.init.recipeTypes.builders.UrnRitualBuilder;
import com.devdyna.synergy.init.types.*;
import com.devdyna.synergy.utils.x;

import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.registries.DeferredHolder;

public abstract class ExtraRecipeProvider extends RecipeProvider {

        public ExtraRecipeProvider(PackOutput output, CompletableFuture<Provider> registries) {
                super(output, registries);
        }

        protected void nuggetIngotBlock(RecipeOutput c, ItemLike nugget, ItemLike ingot, ItemLike block) {
                nineBlockStorageRecipesWithCustomPacking(
                                c, RecipeCategory.MISC, nugget, RecipeCategory.MISC, ingot,
                                x.path(nugget.asItem()) + "_from_" + x.path(ingot.asItem()), x.path(ingot.asItem()));

                nineBlockStorageRecipesWithCustomPacking(
                                c, RecipeCategory.MISC, ingot, RecipeCategory.MISC, block,
                                x.path(ingot.asItem()) + "_from_" + x.path(block.asItem()), x.path(ingot.asItem()));

        }

        protected void raw_dust_smelt(RecipeOutput c, ItemLike raw, ItemLike dust, ItemLike ingot) {

                QuernMillingBuilder.of().input(x.ingredient(raw.asItem()))
                                .output(x.item(dust.asItem(), 3))
                                .unlockedBy().save(c, "_from_raw");

                QuernMillingBuilder.of().input(x.ingredient(ingot.asItem()))
                                .output(x.item(dust.asItem(), 1))
                                .unlockedBy().save(c, "_from_ingot");

                doubleSmelt(c, dust, ingot);
        }

        protected void doubleSmelt(RecipeOutput c, ItemLike input, ItemLike output) {
                SimpleCookingRecipeBuilder.blasting(x.ingredient(input.asItem()), MISC, output.asItem(), 0.1F, 100)
                                .unlockedBy(getHasName(input), has(output))
                                .save(c, x.path(output.asItem()) + "_from_" + x.path(input.asItem()) + "_blasting");
                SimpleCookingRecipeBuilder.smelting(x.ingredient(input.asItem()), MISC, output.asItem(), 0.1F, 200)
                                .unlockedBy(getHasName(input), has(output))
                                .save(c, x.path(output.asItem()) + "_from_" + x.path(input.asItem()) + "_smelting");
        }

        protected static void twoByTwoPacker(RecipeOutput c, ItemLike output, TagKey<Item> tag) {
                ShapedRecipeBuilder.shaped(MISC, output, 1)
                                .define('#', tag)
                                .pattern("##")
                                .pattern("##")
                                .unlockedBy(ID, has(tag))
                                .save(c);
        }

        protected void plate(Item input, Item output, RecipeOutput c) {
                ShapedRecipeBuilder.shaped(MISC, output, 3)
                                .pattern("III")
                                .define('I', input)
                                .unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                                                .hasItems(input))
                                .group(ID).save(c);
        }

        // nodes
        protected void nodeRecipe(Block b, ItemLike catalyst, RecipeOutput c) {

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
                                .save(c, x.rl(
                                                b.getDescriptionId()
                                                                .replace("block." + ID + ".", "")
                                                                + "_alt"));

        }

        protected void cropResultRecipes(RecipeOutput c) {

                var seeds = List.of(
                                x.ingredient(zItemTag.RICE_PLANT),
                                x.ingredient(zBlocks.AZALEA.get().asItem()),
                                x.ingredient(zItemTag.COTTON_PLANT),
                                x.ingredient(zItemTag.CAVE_WHEAT_PLANT),
                                x.ingredient(zItems.BLUE_CUP_SPORE.get()),
                                x.ingredient(zItems.VIOLET_WEBCAP_SPORE.get()));

                List<List<Item>> result = List.of(
                                List.of(zItems.RICE_SEED.get()),
                                List.of(zItems.AZALEA_SEEDS.get(), zItems.SMALL_AZALEA_LEAF.get(),
                                                zItems.SMALL_AZALEA_ROOTS.get()),
                                List.of(zItems.COTTON_SEEDS.get(), zItems.COTTON.get()),
                                List.of(zItems.CAVE_WHEAT_SEEDS.get(), Items.WHEAT),
                                List.of(zItems.BLUE_CUP_SPORE.get(), zItems.BLUE_CUP_MUSHROOM.get()),
                                List.of(zItems.VIOLET_WEBCAP_SPORE.get(), zItems.VIOLET_WEBCAP_MUSHROOM.get()));

                seeds.forEach(s -> CropResultBuilder
                                .of().input(s).output(result.get(seeds.indexOf(s))
                                                .stream().map(i -> x.item(i)).toList())
                                .group(ID).unlockedBy().save(c));

        }

        public static List<DeferredHolder<Item, Item>> clearNBT = List.of(
                        zItems.RED_BATTERY,
                        zItems.BLUE_BATTERY,
                        zItems.GREEN_BATTERY);

        protected void clearNBT(RecipeOutput c) {

                clearNBT.forEach(i -> {
                        ShapelessRecipeBuilder.shapeless(MISC, i.get())
                                        .requires(i.get())
                                        .unlockedBy(ID, InventoryChangeTrigger.TriggerInstance.hasItems(i.get()))
                                        .group(ID)
                                        .save(c, i.getRegisteredName() + "_clear_nbt");
                });
        }

        protected void coolerRecipes(RecipeOutput c) {
                var coolers = List.of(
                                zBlocks.COPPER_COOLER,
                                zBlocks.GOLD_COOLER,
                                zBlocks.IRON_COOLER,
                                zBlocks.ENDER_COOLER,
                                zBlocks.FROST_COOLER,
                                zBlocks.LAPIS_COOLER,
                                zBlocks.SCULK_COOLER,
                                zBlocks.WATER_COOLER,
                                zBlocks.QUARTZ_COOLER,
                                zBlocks.SHADOW_COOLER,
                                zBlocks.DIAMOND_COOLER,
                                zBlocks.EMERALD_COOLER,
                                zBlocks.REDSTONE_COOLER,
                                zBlocks.GLOWSTONE_COOLER,
                                zBlocks.NETHERITE_COOLER);

                var ingredients = List.of(
                                Items.COPPER_INGOT,
                                Items.GOLD_INGOT,
                                Items.IRON_INGOT,
                                Items.ENDER_PEARL,
                                Items.PACKED_ICE,
                                Items.LAPIS_LAZULI,
                                Items.SCULK,
                                Items.WATER_BUCKET,
                                Items.QUARTZ,
                                zItems.GHOUL_HEART.get(),
                                Items.DIAMOND,
                                Items.EMERALD,
                                Items.REDSTONE,
                                Items.GLOWSTONE,
                                Items.NETHERITE_INGOT);

                for (DeferredHolder<Block, Block> b : coolers) {
                        var index = coolers.indexOf(b);
                        ShapelessRecipeBuilder.shapeless(MISC, b.get(), 1)
                                        .requires(ingredients.get(index))
                                        .requires(zBlocks.COOLER_BASE.get())
                                        .unlockedBy(ID, has(ingredients.get(index)))
                                        .save(c);
                }
        }

        protected void bacteries(RecipeOutput c) {
                ShapedRecipeBuilder.shaped(MISC, zItems.RED_BATTERY.get(), 1)
                                .pattern(" R ")
                                .pattern("RHR")
                                .pattern(" R ")
                                .define('R', zItemTag.URN_MIXTURES)
                                .define('H', zItems.BLUE_BATTERY.get())
                                .unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                                                .hasItems(zItems.BLUE_BATTERY.get(), zItems.BONE_MEAL_MIXTURE.get()))
                                .group(ID).save(c);

                ShapedRecipeBuilder.shaped(MISC, zItems.BLUE_BATTERY.get(), 1)
                                .pattern(" R ")
                                .pattern("RHR")
                                .pattern(" R ")
                                .define('R', zItemTag.GEMS_AQUAMARINE)
                                .define('H', zItems.GREEN_BATTERY.get())
                                .unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                                                .hasItems(zItems.GREEN_BATTERY.get(), zItems.AQUAMARINE.get()))
                                .group(ID).save(c);

                ShapedRecipeBuilder.shaped(MISC, zItems.GREEN_BATTERY.get(), 1)
                                .pattern(" S ")
                                .pattern("RHR")
                                .pattern(" R ")
                                .define('S', Items.REDSTONE)
                                .define('R', Items.SLIME_BALL)
                                .define('H', zItems.CONDENSER.get())
                                .unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                                                .hasItems(Items.SLIME_BALL, zItems.CONDENSER.get(), Items.REDSTONE))
                                .group(ID).save(c);
        }

        protected void legacyItemComponents(RecipeOutput c) {

                ShapedRecipeBuilder.shaped(MISC, zItems.RESISTOR.get(), 4)
                                .pattern(" MN")
                                .pattern("MGM")
                                .pattern("NM ")
                                .define('N', Items.IRON_NUGGET)
                                .define('M', zItems.GUARDIAN_SCALE.get())
                                .define('G', zItems.BONE_MEAL_MIXTURE.get())
                                .unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                                                .hasItems(Items.IRON_NUGGET, zItems.GUARDIAN_SCALE.get(),
                                                                zItems.BONE_MEAL_MIXTURE.get()))
                                .group(ID).save(c);

                ShapedRecipeBuilder.shaped(MISC, zItems.RESISTOR.get(), 8)
                                .pattern(" MN")
                                .pattern("MGM")
                                .pattern("NM ")
                                .define('N', zItemTag.NUGGET_STEEL)
                                .define('M', zItemTag.GEMS_AQUAMARINE)
                                .define('G', zItemTag.URN_MIXTURES)
                                .unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                                                .hasItems(zItems.STEEL_NUGGET.get(), zItems.AQUAMARINE.get(),
                                                                zItems.AMETHYST_MIXTURE.get()))
                                .group(ID).save(c, x.path(zItems.RESISTOR.get()) + "_improved");

                ShapedRecipeBuilder.shaped(MISC, zItems.CHIP.get(), 4)
                                .pattern(" N ")
                                .pattern(" GN")
                                .pattern("Q  ")
                                .define('G', zItems.BONE_MEAL_MIXTURE.get())
                                .define('N', Items.IRON_NUGGET)
                                .define('Q', Items.QUARTZ)
                                .unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                                                .hasItems(zItems.BONE_MEAL_MIXTURE.get(), Items.IRON_NUGGET,
                                                                Items.QUARTZ))
                                .group(ID).save(c);

                ShapedRecipeBuilder.shaped(MISC, zItems.CHIP.get(), 8)
                                .pattern(" N ")
                                .pattern(" GN")
                                .pattern("Q  ")
                                .define('G', zItemTag.URN_MIXTURES)
                                .define('N', zItems.STEEL_NUGGET.get())
                                .define('Q', zItemTag.DUST_QUARTZ)
                                .unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                                                .hasItems(zItems.BONE_MEAL_MIXTURE.get(), zItems.STEEL_NUGGET.get(),
                                                                zItems.QUARTZ_DUST.get()))
                                .group(ID).save(c, x.path(zItems.CHIP.get()) + "_improved");

                ShapedRecipeBuilder.shaped(MISC, zItems.CONDENSER.get(), 4)
                                .pattern("N N")
                                .pattern("GMG")
                                .pattern(" I ")
                                .define('N', Items.IRON_NUGGET)
                                .define('G', zItems.BONE_MEAL_MIXTURE.get())
                                .define('M', zItems.VIOLET_WEBCAP_MUSHROOM.get())
                                .define('I', Items.IRON_INGOT)
                                .unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                                                .hasItems(Items.IRON_NUGGET, zItems.BONE_MEAL_MIXTURE.get(),
                                                                Items.IRON_INGOT, zItems.VIOLET_WEBCAP_MUSHROOM.get()))
                                .group(ID).save(c);

                ShapedRecipeBuilder.shaped(MISC, zItems.CONDENSER.get(), 8)
                                .pattern("N N")
                                .pattern("GMG")
                                .pattern(" I ")
                                .define('N', zItems.STEEL_NUGGET.get())
                                .define('G', zItemTag.URN_MIXTURES)
                                .define('M', zItemTag.GEMS_SILICON)
                                .define('I', zItems.STEEL_INGOT.get())
                                .unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                                                .hasItems(zItems.STEEL_NUGGET.get(), zItems.BONE_MEAL_MIXTURE.get(),
                                                                zItems.STEEL_INGOT.get(), zItems.SILICON.get()))
                                .group(ID).save(c, x.path(zItems.CONDENSER.get()) + "_improved");
        }

        protected void fuelCellNuclearReactions(RecipeOutput c) {

                UrnRitualBuilder.of()
                                .add(zItems.WASTE)
                                .output(zItems.URANIUM, 1)
                                .group(ID).unlockedBy().save(c);

                UrnRitualBuilder.of()
                                .add(zItemTag.URANIUM)
                                .add(zItemTag.DUST_COAL)
                                .output(zItems.THORIUM, 2)
                                .unlockedBy().save(c);

                UrnRitualBuilder.of()
                                .add(zItemTag.THORIUM)
                                .add(zItemTag.DUST_DIAMOND)
                                .add(zItemTag.DUST_QUARTZ)
                                .output(zItems.PLUTONIUM, 2)
                                .unlockedBy().save(c);

                UrnRitualBuilder.of()
                                .add(zItemTag.PLUTONIUM)
                                .add(zItemTag.DUST_LAPIS)
                                .add(zItemTag.DUST_AMETHYST)
                                .output(zItems.NEPTUNIUM, 2)
                                .unlockedBy().save(c);

                UrnRitualBuilder.of()
                                .add(zItemTag.NEPTUNIUM)
                                .add(zItems.INFERNAL_EMBER)
                                .add(Tags.Items.DUSTS_REDSTONE)
                                .output(zItems.AMERICIUM, 2)
                                .unlockedBy().save(c);

                UrnRitualBuilder.of()
                                .add(zItemTag.AMERICIUM)
                                .add(zItemTag.DUST_COPPER)
                                .add(zItemTag.DUST_GOLD)
                                .add(zItemTag.DUST_EMERALD)
                                .output(zItems.BERKELIUM, 2)
                                .unlockedBy().save(c);

                UrnRitualBuilder.of()
                                .add(zItemTag.BERKELIUM)
                                .add(zItemTag.DUST_ANCIENT_DEBRIS)
                                .add(zItems.ENERGIZED_REDSTONE)
                                .output(zItems.CURIUM, 2)
                                .unlockedBy().save(c);

                ReactorCellBuilder.of()
                                .input(zItemTag.URANIUM)
                                .output(zItems.WASTE_FRAGMENT, 3)
                                .duration(10_000)
                                .energy(20)
                                .heat(50)
                                .group(ID).unlockedBy().save(c, "_from_uranium");

                ReactorCellBuilder.of()
                                .input(zItemTag.THORIUM)
                                .output(zItems.WASTE_FRAGMENT, 6)
                                .duration(25_000)
                                .energy(80)
                                .heat(90)
                                .group(ID).unlockedBy().save(c, "_from_thorium");

                ReactorCellBuilder.of()
                                .input(zItemTag.PLUTONIUM)
                                .output(zItems.WASTE_FRAGMENT, 9)
                                .duration(50_000)
                                .energy(160)
                                .heat(180)
                                .group(ID).unlockedBy().save(c, "_from_plutonium");

                ReactorCellBuilder.of()
                                .input(zItemTag.NEPTUNIUM)
                                .output(zItems.WASTE_FRAGMENT, 12)
                                .duration(75_000)
                                .energy(300)
                                .heat(350)
                                .group(ID).unlockedBy().save(c, "_from_neptunium");

                ReactorCellBuilder.of()
                                .input(zItemTag.AMERICIUM)
                                .output(zItems.WASTE_FRAGMENT, 15)
                                .duration(150_000)
                                .energy(750)
                                .heat(1040)
                                .group(ID).unlockedBy().save(c, "_from_americium");

                ReactorCellBuilder.of()
                                .input(zItemTag.BERKELIUM)
                                .output(zItems.WASTE_FRAGMENT, 18)
                                .duration(300_000)
                                .energy(1500)
                                .heat(1700)
                                .group(ID).unlockedBy().save(c, "_from_berkelium");

                ReactorCellBuilder.of()
                                .input(zItemTag.CALIFORNIUM)
                                .output(zItems.WASTE_FRAGMENT, 21)
                                .duration(750_000)
                                .energy(2700)
                                .heat(3500)
                                .group(ID).unlockedBy().save(c, "_from_californium");

                ReactorCellBuilder.of()
                                .input(zItemTag.CURIUM)
                                .output(zItems.WASTE_FRAGMENT, 24)
                                .duration(1_250_000)
                                .energy(3500)
                                .heat(7500)
                                .group(ID).unlockedBy().save(c, "_from_curium");
        }

        protected void mixtures(RecipeOutput c) {

                ShapelessRecipeBuilder.shapeless(MISC, zItems.BONE_MEAL_MIXTURE.get(), 2)
                                .requires(Tags.Items.SLIME_BALLS)
                                .requires(Items.BONE_MEAL)
                                .requires(Tags.Items.DUSTS_REDSTONE)
                                .requires(Items.BLAZE_POWDER)
                                .unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                                                .hasItems(Items.BONE_MEAL, Items.BLAZE_POWDER, Items.REDSTONE,
                                                                Items.SLIME_BALL))
                                .group(zStatic.tips.MIXTURE_TIP).save(c);

                UrnRitualBuilder.of()
                                .add(Tags.Items.SLIME_BALLS)
                                .add(zItems.ENERGIZED_REDSTONE.get())
                                .add(zItems.LAPIS_DUST.get())
                                .add(Items.PRISMARINE_CRYSTALS)
                                .output(zItems.GLOWSTONE_MIXTURE.get(), 5)
                                .unlockedBy().save(c);

                UrnRitualBuilder.of()
                                .add(zItemTag.GEMS_SILICON)
                                .add(zItems.QUARTZ_DUST.get())
                                .add(zItems.AMETHYST_DUST.get())
                                .add(Items.HONEYCOMB)
                                .output(zItems.AMETHYST_MIXTURE.get(), 9)
                                .unlockedBy().save(c);
        }

        protected void chests(RecipeOutput c) {
                ShapelessRecipeBuilder.shapeless(MISC, zBlocks.WOODEN_TINY_CHEST.get(), 4)
                                .requires(Items.CHEST)
                                .unlockedBy(ID, has(Items.CHEST))
                                .save(c);

                UrnRitualBuilder.of()
                                .add(zBlocks.WOODEN_TINY_CHEST.get())
                                .add(Tags.Items.NUGGETS_GOLD)
                                .output(zBlocks.ORNATE_TINY_CHEST.get())
                                .unlockedBy().save(c);

                UrnRitualBuilder.of()
                                .add(zBlocks.WOODEN_TINY_CHEST.get())
                                .add(zItems.STONE_PEBBLE)
                                .output(zBlocks.STONE_TINY_CHEST.get())
                                .unlockedBy().save(c);
        }

        protected void tools(RecipeOutput c) {

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
                                .group(zStatic.Items.wooden_crook).save(c, x.rl(
                                                zItems.WOODEN_CROOK.get().getDescriptionId()
                                                                .replace("item." + ID + ".", "")
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
                                .group(zStatic.Items.smasher).save(c, x.rl(
                                                zItems.SMASHER.get().getDescriptionId()
                                                                .replace("item." + ID + ".", "")
                                                                + "_alt"));

                ShapedRecipeBuilder.shaped(MISC, zItems.SOLDERING_GUN.get(), 1)
                                .pattern("  I")
                                .pattern(" G ")
                                .pattern("S  ")
                                .define('S', Items.STICK)
                                .define('G', zItemTag.FOIL_GOLD)
                                .define('I', Items.IRON_NUGGET)
                                .unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                                                .hasItems(Items.STICK, zItems.GOLD_FOIL.get(), Items.IRON_NUGGET))
                                .group(zStatic.Items.soldering_gun).save(c);

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
        }

        protected void foil(RecipeOutput c, TagKey<Item> input, Item output) {
                ShapedRecipeBuilder.shaped(MISC, output, 1)
                                .pattern(" IS")
                                .pattern(" I ")
                                .pattern("SI ")
                                .define('I', input)
                                .define('S', Items.STICK)
                                .unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                                                .hasItems(Items.STICK))
                                .group(ID).save(c);
        }

        protected void coil(RecipeOutput c, TagKey<Item> input, Item output) {
                ShapedRecipeBuilder.shaped(MISC, output, 4)
                                .pattern("I")
                                .pattern("I")
                                .define('I', input)
                                .unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                                                .hasItems(Items.STICK))
                                .group(ID).save(c);
        }

        protected void moderators(RecipeOutput c) {

                ShapedRecipeBuilder.shaped(MISC, zBlocks.SIMPLE_MODERATOR.get(), 1)
                                .pattern(" S ")
                                .pattern("SFS")
                                .pattern(" S ")
                                .define('F', Tags.Items.STORAGE_BLOCKS_COAL)
                                .define('S', zItemTag.PLATE_AQUAMARINE)
                                .unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                                                .hasItems(zBlocks.ADVANCED_MACHINE_FRAME.get()))
                                .group(ID).save(c);

                ShapedRecipeBuilder.shaped(MISC, zBlocks.ADVANCED_MODERATOR.get(), 1)
                                .pattern("CSC")
                                .pattern("SFS")
                                .pattern("CSC")
                                .define('F', zBlocks.SIMPLE_MODERATOR.get())
                                .define('S', zItemTag.PLATE_STEEL)
                                .define('C', zItemTag.NUGGET_ADVANCEDALLOY)
                                .unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                                                .hasItems(zBlocks.SIMPLE_MODERATOR.get()))
                                .group(ID).save(c);

                ShapedRecipeBuilder.shaped(MISC, zBlocks.ELITE_MODERATOR.get(), 1)
                                .pattern("SPS")
                                .pattern("CFC")
                                .pattern("SPS")
                                .define('F', zBlocks.ADVANCED_MODERATOR.get())
                                .define('P', zItems.NETHER_CIRCUIT.get())
                                .define('C', zItems.CARBON_PLATE.get())
                                .define('S', zItemTag.PLATE_ADVANCED_ALLOY)
                                .unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                                                .hasItems(zBlocks.ADVANCED_MODERATOR.get()))
                                .group(ID).save(c);

        }

}
