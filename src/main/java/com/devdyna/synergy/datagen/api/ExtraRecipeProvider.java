package com.devdyna.synergy.datagen.api;

import static com.devdyna.synergy.Main.ID;
import static net.minecraft.data.recipes.RecipeCategory.BUILDING_BLOCKS;
import static net.minecraft.data.recipes.RecipeCategory.MISC;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.api.utils.x;
import com.devdyna.synergy.common.recipes.builders.CropResultBuilder;
import com.devdyna.synergy.common.recipes.builders.DryableBricksBuilder;
import com.devdyna.synergy.common.recipes.builders.QuernMillingBuilder;
import com.devdyna.synergy.common.recipes.builders.ReactorCellBuilder;
import com.devdyna.synergy.common.recipes.builders.UrnRitualBuilder;
import com.devdyna.synergy.init.builder.industrial_machines.compressor.recipe.CompressorRecipeBuilder;
import com.devdyna.synergy.init.builder.industrial_machines.macerator.recipe.MaceratorRecipeBuilder;
import com.devdyna.synergy.init.types.*;

import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.conditions.ICondition;
import net.neoforged.neoforge.common.conditions.TagEmptyCondition;
import net.neoforged.neoforge.common.conditions.NotCondition;
import net.neoforged.neoforge.registries.DeferredHolder;

public abstract class ExtraRecipeProvider extends RecipeProvider {

        public ExtraRecipeProvider(PackOutput output, CompletableFuture<Provider> registries) {
                super(output, registries);
        }

        protected void compatIngotsAndDusts(RecipeOutput c) {
                raw_dust_smelt(c, x.rl("c", "raw_materials/tin"), zItems.TIN_DUST.get(),
                                zItems.TIN_INGOT.get(), x.rl("c", "ingots/tin"), zItems.IRON_DUST.get(), 0.25f);
                raw_dust_smelt(c, x.rl("c", "raw_materials/silver"), zItems.SILVER_DUST.get(),
                                zItems.SILVER_INGOT.get(), x.rl("c", "ingots/silver"), zItems.TIN_DUST.get(), 0.25f);
                raw_dust_smelt(c, x.rl("c", "raw_materials/nickel"), zItems.NICKEL_DUST.get(),
                                zItems.NICKEL_INGOT.get(), x.rl("c", "ingots/nickel"), zItems.SILVER_DUST.get(), 0.25f);
                raw_dust_smelt(c, x.rl("c", "raw_materials/iridium"), zItems.IRIDIUM_DUST.get(),
                                zItems.IRIDIUM_INGOT.get(), x.rl("c", "ingots/iridium"), zItems.IRON_DUST.get(), 0.25f);
                raw_dust_smelt(c, x.rl("c", "raw_materials/uranium"), zItems.URANIUM_DUST.get(),
                                zItems.URANIUM_INGOT.get(), x.rl("c", "ingots/uranium"), zItems.LEAD_DUST.get(), 0.25f);
                raw_dust_smelt(c, x.rl("c", "raw_materials/platinum"), zItems.PLATINUM_DUST.get(),
                                zItems.PLATINUM_INGOT.get(), x.rl("c", "ingots/platinum"), zItems.IRIDIUM_DUST.get(),
                                0.25f);
                raw_dust_smelt(c, x.rl("c", "raw_materials/osmium"), zItems.OSMIUM_DUST.get(),
                                zItems.OSMIUM_INGOT.get(), x.rl("c", "ingots/osmium"), zItems.IRON_DUST.get(), 0.25f);
                raw_dust_smelt(c, x.rl("c", "raw_materials/aluminum"), zItems.ALUMINUM_DUST.get(),
                                zItems.ALUMINUM_INGOT.get(), x.rl("c", "ingots/aluminum"), zItems.IRON_DUST.get(),
                                0.25f);
                raw_dust_smelt(c, x.rl("c", "raw_materials/lead"), zItems.LEAD_DUST.get(),
                                zItems.LEAD_INGOT.get(), x.rl("c", "ingots/lead"), zItems.SILVER_DUST.get(), 0.25f);
        }

        protected void nuggetIngotBlock(RecipeOutput c, ItemLike nugget, ItemLike ingot, ItemLike block) {
                packUnpack(c, nugget, ingot, false);
                packUnpack(c, ingot, block, false);
        }

        protected void gear(RecipeOutput c, DeferredHolder<Item, Item> gear, TagKey<Item> input) {
                ShapedRecipeBuilder.shaped(MISC, gear.get())
                                .pattern(" # ")
                                .pattern("# #")
                                .pattern(" # ")
                                .define('#', input)
                                .unlockedBy(ID, has(input))
                                .save(c);

        }

        protected void raw_dust_smelt(RecipeOutput c, ItemLike raw, ItemLike dust, ItemLike ingot, Item secondary,
                        float chance) {

                QuernMillingBuilder.of().input(x.ingredient(raw.asItem()))
                                .output(x.item(dust.asItem(), 2))
                                .unlockedBy().save(c, "_from_raw");

                QuernMillingBuilder.of().input(x.ingredient(ingot.asItem()))
                                .output(x.item(dust.asItem()))
                                .unlockedBy().save(c, "_from_ingot");

                MaceratorRecipeBuilder.of().input(x.ingredient(raw.asItem()))
                                .output(x.item(dust.asItem(), 3))
                                .secondary(secondary)
                                .chance(chance)
                                .unlockedBy().save(c, "_from_raw");

                MaceratorRecipeBuilder.of().input(x.ingredient(ingot.asItem()))
                                .output(x.item(dust.asItem()))
                                .unlockedBy().save(c, "_from_ingot");

                doubleSmelt(c, dust, ingot);
        }

        protected void raw_dust_smelt(RecipeOutput c, ResourceLocation raw, ItemLike dust, ItemLike ingot,
                        Item secondary, float chance) {

                QuernMillingBuilder.of().input(x.ingredient(raw))
                                .output(x.item(dust.asItem(), 2))
                                .unlockedBy().save(c.withConditions(
                                                new ICondition[] {
                                                                new NotCondition(
                                                                                new TagEmptyCondition(raw)) }),
                                                "_from_raw");

                QuernMillingBuilder.of().input(x.ingredient(ingot.asItem()))
                                .output(x.item(dust.asItem()))
                                .unlockedBy().save(c, "_from_ingot");

                MaceratorRecipeBuilder.of().input(x.ingredient(raw))
                                .output(x.item(dust.asItem(), 3))
                                .secondary(secondary)
                                .chance(chance)
                                .unlockedBy().save(c.withConditions(
                                                new ICondition[] {
                                                                new NotCondition(
                                                                                new TagEmptyCondition(raw)) }),
                                                "_from_raw");

                MaceratorRecipeBuilder.of().input(x.ingredient(ingot.asItem()))
                                .output(x.item(dust.asItem()))
                                .unlockedBy().save(c, "_from_ingot");

                doubleSmelt(c, dust, ingot);
        }

        protected void raw_dust_smelt(RecipeOutput c, ResourceLocation raw, ItemLike dust, ItemLike ingot,
                        ResourceLocation ingotTag, Item secondary, float chance) {

                QuernMillingBuilder.of().input(x.ingredient(raw))
                                .output(x.item(dust.asItem(), 2))
                                .unlockedBy().save(c.withConditions(
                                                new ICondition[] {
                                                                new NotCondition(
                                                                                new TagEmptyCondition(raw)) }),
                                                "_from_raw");

                MaceratorRecipeBuilder.of().input(x.ingredient(raw))
                                .output(x.item(dust.asItem(), 3))
                                .secondary(secondary)
                                .chance(chance)
                                .unlockedBy().save(c.withConditions(
                                                new ICondition[] {
                                                                new NotCondition(
                                                                                new TagEmptyCondition(raw)) }),
                                                "_from_raw");

                QuernMillingBuilder.of().input(x.ingredient(ingotTag))
                                .output(x.item(dust.asItem()))
                                .unlockedBy().save(c.withConditions(
                                                new ICondition[] {
                                                                new NotCondition(
                                                                                new TagEmptyCondition(ingotTag)) }),
                                                "_from_ingot");

                MaceratorRecipeBuilder.of().input(x.ingredient(ingotTag))
                                .output(x.item(dust.asItem()))
                                .unlockedBy().save(c.withConditions(
                                                new ICondition[] {
                                                                new NotCondition(
                                                                                new TagEmptyCondition(ingotTag)) }),
                                                "_from_ingot");

                doubleSmelt(c, dust, ingot);
        }

        protected void raw_dust_smelt(RecipeOutput c, ItemLike raw, ItemLike dust, ItemLike ingot) {

                QuernMillingBuilder.of().input(x.ingredient(raw.asItem()))
                                .output(x.item(dust.asItem(), 2))
                                .unlockedBy().save(c, "_from_raw");

                QuernMillingBuilder.of().input(x.ingredient(ingot.asItem()))
                                .output(x.item(dust.asItem()))
                                .unlockedBy().save(c, "_from_ingot");

                doubleSmelt(c, dust, ingot);
        }

        protected void raw_dust_smelt(RecipeOutput c, ResourceLocation raw, ItemLike dust, ItemLike ingot) {

                QuernMillingBuilder.of().input(x.ingredient(raw))
                                .output(x.item(dust.asItem(), 2))
                                .unlockedBy().save(c.withConditions(
                                                new ICondition[] {
                                                                new NotCondition(
                                                                                new TagEmptyCondition(raw)) }),
                                                "_from_raw");

                QuernMillingBuilder.of().input(x.ingredient(ingot.asItem()))
                                .output(x.item(dust.asItem()))
                                .unlockedBy().save(c, "_from_ingot");

                doubleSmelt(c, dust, ingot);
        }

        protected void raw_dust_smelt(RecipeOutput c, ResourceLocation raw, ItemLike dust, ItemLike ingot,
                        ResourceLocation ingotTag) {

                QuernMillingBuilder.of().input(x.ingredient(raw))
                                .output(x.item(dust.asItem(), 2))
                                .unlockedBy().save(c.withConditions(
                                                new ICondition[] {
                                                                new NotCondition(
                                                                                new TagEmptyCondition(raw)) }),
                                                "_from_raw");

                QuernMillingBuilder.of().input(x.ingredient(ingotTag))
                                .output(x.item(dust.asItem()))
                                .unlockedBy().save(c.withConditions(
                                                new ICondition[] {
                                                                new NotCondition(
                                                                                new TagEmptyCondition(ingotTag)) }),
                                                "_from_ingot");

                doubleSmelt(c, dust, ingot);
        }

        protected void doubleSmelt(RecipeOutput c, ItemLike input, ItemLike output) {
                SimpleCookingRecipeBuilder.blasting(x.ingredient(input.asItem()), MISC, output.asItem(), 0.1F, 100)
                                .unlockedBy(getHasName(input), has(output))
                                .save(c, ID + ":" + x.path(output.asItem()) + "_from_" + x.path(input.asItem())
                                                + "_blasting");
                SimpleCookingRecipeBuilder.smelting(x.ingredient(input.asItem()), MISC, output.asItem(), 0.1F, 200)
                                .unlockedBy(getHasName(input), has(output))
                                .save(c, ID + ":" + x.path(output.asItem()) + "_from_" + x.path(input.asItem())
                                                + "_smelting");
        }

        protected static void unpacker(RecipeOutput c, ItemLike input, ItemLike output, int count) {
                ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, output, count).requires(input)
                                .unlockedBy(getHasName(input), has(input))
                                .save(c, getConversionRecipeName(output, input));
        }

        protected static void brickRecipes(RecipeOutput c) {
                DryableBricksBuilder.of()
                                .input(Items.CLAY_BALL)
                                .block(zBlocks.CLAY_BRICK.get())
                                .output(Items.BRICK)
                                .unlockedBy()

                                .save(c);

                DryableBricksBuilder.of()
                                .input(zItems.PACKED_MUD_BALL)
                                .block(zBlocks.PACKED_MUD_BRICK.get())
                                .output(zItems.PACKED_MUD_BRICK)
                                .unlockedBy()

                                .save(c);

                DryableBricksBuilder.of()
                                .input(zItems.FIRECLAY_BALL)
                                .block(zBlocks.FIRECLAY_BRICK.get())
                                .output(zItems.FIRECLAY_BRICK)
                                .unlockedBy()

                                .save(c);

                DryableBricksBuilder.of()
                                .input(zItems.CLAY_MIXTURE_BALL)
                                .block(zBlocks.BLAST_BRICK.get())
                                .output(zItems.BLAST_BRICK)
                                .unlockedBy()

                                .save(c);

        }

        protected static void twoByTwoPacker(RecipeOutput c, ItemLike output, TagKey<Item> tag) {
                ShapedRecipeBuilder.shaped(MISC, output)
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
                                .unlockedBy(ID,
                                                has(input))
                                .save(c);

                CompressorRecipeBuilder.of()
                                .input(input)
                                .delay(80)
                                .catalyst(zItems.MOLD_PLATE.get())
                                .output(output, 2)
                                .unlockedBy()
                                .save(c);
        }

        protected void plate(TagKey<Item> input, Item output, RecipeOutput c) {
                ShapedRecipeBuilder.shaped(MISC, output, 3)
                                .pattern("III")
                                .define('I', input)
                                .unlockedBy(ID,
                                                has(input))
                                .save(c);

                CompressorRecipeBuilder.of()
                                .input(input)
                                .delay(80)
                                .catalyst(zItems.MOLD_PLATE.get())
                                .output(output, 2)
                                .unlockedBy()
                                .save(c);
        }

        // nodes
        protected void nodeRecipe(RecipeOutput c, Block b, ItemLike catalyst) {

                ShapedRecipeBuilder.shaped(MISC, b.asItem())
                                .pattern(" P ")
                                .pattern("RBR")
                                .pattern("SCS")
                                .define('P', zBlocks.PIPE.get().asItem())
                                .define('R', Tags.Items.DUSTS_REDSTONE)
                                .define('C', catalyst)
                                .define('B', Tags.Items.STORAGE_BLOCKS_REDSTONE)
                                .define('S', Tags.Items.STONES)
                                .unlockedBy(ID,
                                                has(
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
                                .unlockedBy(ID,
                                                has(
                                                                zBlocks.PIPE.get()))
                                .group(zStatic.PipeStuff.types.item_node)
                                .save(c, BuiltInRegistries.ITEM.getKey(b.asItem())
                                                + "_alt");

        }

        protected void node_alt(RecipeOutput c, ItemLike input, ItemLike output) {
                ShapelessRecipeBuilder.shapeless(MISC, output)
                                .requires(input)
                                .unlockedBy(ID, has(input))
                                .group(zStatic.PipeStuff.types.item_node)
                                .save(c, BuiltInRegistries.ITEM.getKey(input.asItem()) + "_alt2");

                ShapelessRecipeBuilder.shapeless(MISC, input)
                                .requires(output)
                                .unlockedBy(ID, has(output))
                                .group(zStatic.PipeStuff.types.item_node)
                                .save(c, BuiltInRegistries.ITEM.getKey(output.asItem()) + "_alt2");
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
                                .unlockedBy().save(c));

        }

        public static List<DeferredHolder<Item, Item>> clearNBT = List.of(
                        zItems.RED_BATTERY,
                        zItems.BLUE_BATTERY,
                        zItems.GREEN_BATTERY);

        protected void clearNBT(RecipeOutput c) {

                clearNBT.forEach(i -> {
                        ShapelessRecipeBuilder.shapeless(MISC, i.get())
                                        .requires(i.get())
                                        .unlockedBy(ID, has(i.get()))

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
                        ShapelessRecipeBuilder.shapeless(MISC, b.get())
                                        .requires(ingredients.get(index))
                                        .requires(zBlocks.COOLER_BASE.get())
                                        .unlockedBy(ID, has(ingredients.get(index)))
                                        .save(c);
                }
        }

        protected void bacteries(RecipeOutput c) {
                ShapedRecipeBuilder.shaped(MISC, zItems.RED_BATTERY.get())
                                .pattern(" R ")
                                .pattern("RHR")
                                .pattern(" R ")
                                .define('R', zItemTag.URN_MIXTURES)
                                .define('H', zItems.BLUE_BATTERY.get())
                                .unlockedBy(ID,
                                                has(zItems.BLUE_BATTERY.get()))
                                .save(c);

                ShapedRecipeBuilder.shaped(MISC, zItems.BLUE_BATTERY.get())
                                .pattern(" R ")
                                .pattern("RHR")
                                .pattern(" R ")
                                .define('R', zItemTag.GEMS_AQUAMARINE)
                                .define('H', zItems.GREEN_BATTERY.get())
                                .unlockedBy(ID,
                                                has(zItems.AQUAMARINE.get()))
                                .save(c);

                ShapedRecipeBuilder.shaped(MISC, zItems.GREEN_BATTERY.get())
                                .pattern(" S ")
                                .pattern("RHR")
                                .pattern(" R ")
                                .define('S', Items.REDSTONE)
                                .define('R', Items.SLIME_BALL)
                                .define('H', zItems.CONDENSER.get())
                                .unlockedBy(ID,
                                                has(zItems.CONDENSER.get()))
                                .save(c);
        }

        protected void legacyItemComponents(RecipeOutput c) {

                ShapedRecipeBuilder.shaped(MISC, zItems.RESISTOR.get(), 4)
                                .pattern(" MN")
                                .pattern("MGM")
                                .pattern("NM ")
                                .define('N', Items.IRON_NUGGET)
                                .define('M', zItems.GUARDIAN_SCALE.get())
                                .define('G', zItems.BONE_MEAL_MIXTURE.get())
                                .unlockedBy(ID,
                                                has(
                                                                zItems.BONE_MEAL_MIXTURE.get()))
                                .save(c);

                ShapedRecipeBuilder.shaped(MISC, zItems.RESISTOR.get(), 8)
                                .pattern(" MN")
                                .pattern("MGM")
                                .pattern("NM ")
                                .define('N', zItemTag.NUGGET_STEEL)
                                .define('M', zItemTag.GEMS_AQUAMARINE)
                                .define('G', zItemTag.URN_MIXTURES)
                                .unlockedBy(ID,
                                                has(zItems.STEEL_NUGGET.get()))
                                .save(c, ID + ":" + x.path(zItems.RESISTOR.get()) + "_improved");

                ShapedRecipeBuilder.shaped(MISC, zItems.CHIP.get(), 4)
                                .pattern(" N ")
                                .pattern(" GN")
                                .pattern("Q  ")
                                .define('G', zItems.BONE_MEAL_MIXTURE.get())
                                .define('N', Items.IRON_NUGGET)
                                .define('Q', Items.QUARTZ)
                                .unlockedBy(ID,
                                                has(zItems.BONE_MEAL_MIXTURE.get()))
                                .save(c);

                ShapedRecipeBuilder.shaped(MISC, zItems.CHIP.get(), 8)
                                .pattern(" N ")
                                .pattern(" GN")
                                .pattern("Q  ")
                                .define('G', zItemTag.URN_MIXTURES)
                                .define('N', zItemTag.NUGGET_STEEL)
                                .define('Q', zItemTag.DUST_QUARTZ)
                                .unlockedBy(ID,
                                                has(zItems.BONE_MEAL_MIXTURE.get()))
                                .save(c, ID + ":" + x.path(zItems.CHIP.get()) + "_improved");

                ShapedRecipeBuilder.shaped(MISC, zItems.CONDENSER.get(), 4)
                                .pattern("N N")
                                .pattern("GMG")
                                .pattern(" I ")
                                .define('N', Items.IRON_NUGGET)
                                .define('G', zItems.BONE_MEAL_MIXTURE.get())
                                .define('M', zItems.VIOLET_WEBCAP_MUSHROOM.get())
                                .define('I', Items.IRON_INGOT)
                                .unlockedBy(ID,
                                                has(zItems.BONE_MEAL_MIXTURE.get()))
                                .save(c);

                ShapedRecipeBuilder.shaped(MISC, zItems.CONDENSER.get(), 8)
                                .pattern("N N")
                                .pattern("GMG")
                                .pattern(" I ")
                                .define('N', zItemTag.NUGGET_STEEL)
                                .define('G', zItemTag.URN_MIXTURES)
                                .define('M', zItemTag.GEMS_SILICON)
                                .define('I', zItemTag.INGOT_STEEL)
                                .unlockedBy(ID,
                                                has(zItems.SILICON.get()))
                                .save(c, ID + ":" + x.path(zItems.CONDENSER.get()) + "_improved");
        }

        protected void fuelCellNuclearReactions(RecipeOutput c) {

                UrnRitualBuilder.of()
                                .add(zItems.WASTE)
                                .output(zItems.URANIUM)
                                .unlockedBy().save(c);

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
                                .add(zItems.MAGIC_DUST)
                                .add(zItems.AQUAMARINE)
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
                                .unlockedBy().save(c, "_from_uranium");

                ReactorCellBuilder.of()
                                .input(zItemTag.THORIUM)
                                .output(zItems.WASTE_FRAGMENT, 6)
                                .duration(25_000)
                                .energy(80)
                                .heat(90)
                                .unlockedBy().save(c, "_from_thorium");

                ReactorCellBuilder.of()
                                .input(zItemTag.PLUTONIUM)
                                .output(zItems.WASTE_FRAGMENT, 9)
                                .duration(50_000)
                                .energy(160)
                                .heat(180)
                                .unlockedBy().save(c, "_from_plutonium");

                ReactorCellBuilder.of()
                                .input(zItemTag.NEPTUNIUM)
                                .output(zItems.WASTE_FRAGMENT, 12)
                                .duration(75_000)
                                .energy(300)
                                .heat(350)
                                .unlockedBy().save(c, "_from_neptunium");

                ReactorCellBuilder.of()
                                .input(zItemTag.AMERICIUM)
                                .output(zItems.WASTE_FRAGMENT, 15)
                                .duration(150_000)
                                .energy(750)
                                .heat(1040)
                                .unlockedBy().save(c, "_from_americium");

                ReactorCellBuilder.of()
                                .input(zItemTag.BERKELIUM)
                                .output(zItems.WASTE_FRAGMENT, 18)
                                .duration(300_000)
                                .energy(1500)
                                .heat(1700)
                                .unlockedBy().save(c, "_from_berkelium");

                ReactorCellBuilder.of()
                                .input(zItemTag.CALIFORNIUM)
                                .output(zItems.WASTE_FRAGMENT, 21)
                                .duration(750_000)
                                .energy(2700)
                                .heat(3500)
                                .unlockedBy().save(c, "_from_californium");

                ReactorCellBuilder.of()
                                .input(zItemTag.CURIUM)
                                .output(zItems.WASTE_FRAGMENT, 24)
                                .duration(1_250_000)
                                .energy(3500)
                                .heat(7500)
                                .unlockedBy().save(c, "_from_curium");
        }

        protected void mixtures(RecipeOutput c) {

                ShapelessRecipeBuilder.shapeless(MISC, zItems.BONE_MEAL_MIXTURE.get(), 2)
                                .requires(Tags.Items.SLIME_BALLS)
                                .requires(Items.BONE_MEAL)
                                .requires(Tags.Items.DUSTS_REDSTONE)
                                .requires(Items.BLAZE_POWDER)
                                .unlockedBy(ID,
                                                has(Items.BLAZE_POWDER))
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
                                .add(zItemTag.DUST_GOLD)
                                .add(zItems.ADVANCED_ALLOY_PLATE)
                                .add(Items.NETHERITE_SCRAP)
                                .output(zBlocks.ORNATE_TINY_CHEST.get())
                                .unlockedBy().save(c);

                UrnRitualBuilder.of()
                                .add(zBlocks.WOODEN_TINY_CHEST.get())
                                .add(zItems.STONE_PEBBLE)
                                .add(zItems.GHAST_BLADDER)
                                .output(zBlocks.STONE_TINY_CHEST.get())
                                .unlockedBy().save(c);

        }

        protected void tools(RecipeOutput c) {

                ShapedRecipeBuilder.shaped(MISC, zItems.WOODEN_CROOK.get())
                                .pattern("SS")
                                .pattern(" S")
                                .pattern(" S")
                                .define('S', Items.STICK)
                                .unlockedBy(ID,
                                                has(Items.STICK))
                                .group(zStatic.Items.wooden_crook).save(c);

                ShapedRecipeBuilder.shaped(MISC, zItems.WOODEN_CROOK.get())
                                .pattern("SS")
                                .pattern("S ")
                                .pattern("S ")
                                .define('S', Items.STICK)
                                .unlockedBy(ID,
                                                has(Items.STICK))
                                .group(zStatic.Items.wooden_crook).save(c, x.rl(
                                                zItems.WOODEN_CROOK.get().getDescriptionId()
                                                                .replace("item." + ID + ".", "")
                                                                + "_alt"));

                ShapedRecipeBuilder.shaped(MISC, zItems.SMASHER.get())
                                .pattern("NI ")
                                .pattern(" I ")
                                .pattern(" IN")
                                .define('I', Items.IRON_INGOT)
                                .define('N', Items.IRON_NUGGET)
                                .unlockedBy(ID,
                                                has(Items.IRON_INGOT))
                                .group(zStatic.Items.smasher).save(c);

                ShapedRecipeBuilder.shaped(MISC, zItems.SMASHER.get())
                                .pattern(" IN")
                                .pattern(" I ")
                                .pattern("NI ")
                                .define('I', Items.IRON_INGOT)
                                .define('N', Items.IRON_NUGGET)
                                .unlockedBy(ID,
                                                has(Items.IRON_INGOT))
                                .group(zStatic.Items.smasher).save(c, x.rl(
                                                zItems.SMASHER.get().getDescriptionId()
                                                                .replace("item." + ID + ".", "")
                                                                + "_alt"));

                ShapedRecipeBuilder.shaped(MISC, zItems.SOLDERING_GUN.get())
                                .pattern("  I")
                                .pattern(" G ")
                                .pattern("S  ")
                                .define('S', Items.STICK)
                                .define('G', zItemTag.FOIL_GOLD)
                                .define('I', Items.IRON_NUGGET)
                                .unlockedBy(ID,
                                                has(zItems.GOLD_FOIL.get()))
                                .group(zStatic.Items.soldering_gun).save(c);

                // ShapedRecipeBuilder.shaped(MISC, zItems.PIPE_REFARCTORIZER.get())
                //                 .pattern(" I")
                //                 .pattern("S ")
                //                 .define('S', Items.STICK)
                //                 .define('I', Items.IRON_NUGGET)
                //                 .unlockedBy(ID,
                //                                 has(Items.IRON_INGOT))
                //                 .group(zStatic.Items.refactorizer).save(c);

                ShapedRecipeBuilder.shaped(MISC, zItems.CONFIGURATOR.get())
                                .pattern("N N")
                                .pattern("IEI")
                                .pattern("RIR")
                                .define('R', Items.REDSTONE)
                                .define('N', Items.IRON_NUGGET)
                                .define('E', Items.EMERALD)
                                .define('I', Items.IRON_INGOT)
                                .unlockedBy(ID,
                                                has(Items.REDSTONE))
                                .group(zStatic.Items.configurator).save(c);
        }

        protected void foil(RecipeOutput c, TagKey<Item> input, Item output) {
                ShapedRecipeBuilder.shaped(MISC, output, 3)
                                .pattern(" IS")
                                .pattern(" I ")
                                .pattern("SI ")
                                .define('I', input)
                                .define('S', Items.STICK)
                                .unlockedBy(ID,
                                                has(Items.STICK))
                                .save(c);

                CompressorRecipeBuilder.of()
                                .input(input)
                                .delay(80)
                                .catalyst(zItems.MOLD_FOIL.get())
                                .output(output, 2)
                                .unlockedBy()
                                .save(c);
        }

        protected void coil(RecipeOutput c, TagKey<Item> input, Item output) {
                ShapedRecipeBuilder.shaped(MISC, output, 4)
                                .pattern(" I ")
                                .pattern("ISI")
                                .pattern(" I ")
                                .define('I', input)
                                .define('S', Items.STICK)
                                .unlockedBy(ID,
                                                has(Items.STICK))
                                .save(c);

                CompressorRecipeBuilder.of()
                                .input(input)
                                .delay(80)
                                .catalyst(zItems.MOLD_FOIL.get())
                                .output(output, 2)
                                .unlockedBy()
                                .save(c);
        }

        protected void moderators(RecipeOutput c) {

                ShapedRecipeBuilder.shaped(MISC, zBlocks.SIMPLE_MODERATOR.get())
                                .pattern(" S ")
                                .pattern("SFS")
                                .pattern(" S ")
                                .define('F', Tags.Items.STORAGE_BLOCKS_COAL)
                                .define('S', zItemTag.PLATE_AQUAMARINE)
                                .unlockedBy(ID,
                                                has(zBlocks.ADVANCED_MACHINE_FRAME.get()))
                                .save(c);

                ShapedRecipeBuilder.shaped(MISC, zBlocks.ADVANCED_MODERATOR.get())
                                .pattern("CSC")
                                .pattern("SFS")
                                .pattern("CSC")
                                .define('F', zBlocks.SIMPLE_MODERATOR.get())
                                .define('S', zItemTag.PLATE_STEEL)
                                .define('C', zItemTag.NUGGET_ADVANCEDALLOY)
                                .unlockedBy(ID,
                                                has(zBlocks.SIMPLE_MODERATOR.get()))
                                .save(c);

                ShapedRecipeBuilder.shaped(MISC, zBlocks.ELITE_MODERATOR.get())
                                .pattern("SPS")
                                .pattern("CFC")
                                .pattern("SPS")
                                .define('F', zBlocks.ADVANCED_MODERATOR.get())
                                .define('P', zItems.NETHER_CIRCUIT.get())
                                .define('C', zItems.CARBON_PLATE.get())
                                .define('S', zItemTag.PLATE_ADVANCED_ALLOY)
                                .unlockedBy(ID,
                                                has(zBlocks.ADVANCED_MODERATOR.get()))
                                .save(c);

        }

        protected void droplets(RecipeOutput c) {

                var droplets = List.of(
                                zItems.SAP,
                                zItems.OIL,
                                zItems.GLUE);

                var fluids = List.of(
                                zFluids.SAP,
                                zFluids.OIL,
                                zFluids.GLUE);

                droplets.forEach(d -> {
                        var bucket = fluids.get(droplets.indexOf(d)).getItemBucket().get();
                        ShapelessRecipeBuilder.shapeless(MISC, d.get(), 3)
                                        .requires(bucket)
                                        .unlockedBy(ID, has(bucket))
                                        .save(c);
                });

        }

        protected static void packUnpack(RecipeOutput c, ItemLike unpacked, ItemLike packed, boolean isSmall) {
                ShapelessRecipeBuilder.shapeless(MISC, unpacked, isSmall ? 4 : 9)
                                .requires(packed)

                                .unlockedBy(getHasName(packed), has(packed))
                                .save(c, ID + ":" + x.path((Item) unpacked) + "_unpack" + (isSmall ? "_4" : "_9"));
                var temp = ShapedRecipeBuilder.shaped(MISC, packed)
                                .define('#', unpacked)
                                .pattern("##" + (!isSmall ? "#" : "")).pattern("##" + (!isSmall ? "#" : ""));

                if (!isSmall)
                        temp = temp.pattern("###");

                temp.unlockedBy(getHasName(unpacked), has(unpacked))
                                .save(c, ID + ":" + x.path((Item) packed) + "_pack" + (isSmall ? "_x4" : "_x9"));

        }

        protected static void simplePacked(RecipeOutput c, Item input, Item output) {
                ShapedRecipeBuilder.shaped(MISC, output, 1)
                                .define('#', input)
                                .pattern("##")
                                .pattern("##")
                                .unlockedBy(getHasName(input),
                                                has(input))
                                .save(c, ID + ":" + getConversionRecipeName(output,
                                                input));
        }

        protected static void lasers(RecipeOutput c) {
                ShapedRecipeBuilder.shaped(MISC, zBlocks.LASER_MIRROR.get(), 2)
                                .pattern(" C ")
                                .pattern("CGC")
                                .pattern("SSS")
                                .define('S', zItemTag.PLATE_STEEL)
                                .define('C', Tags.Items.INGOTS_COPPER)
                                .define('G', Tags.Items.GLASS_BLOCKS_COLORLESS)
                                .unlockedBy(ID, has(zItemTag.PLATE_STEEL))

                                .save(c);

                ShapedRecipeBuilder.shaped(MISC, zBlocks.LASER_LENS.get(), 4)
                                .pattern("G G")
                                .pattern("GCG")
                                .pattern("SSS")
                                .define('S', zItemTag.PLATE_STEEL)
                                .define('C', Tags.Items.INGOTS_COPPER)
                                .define('G', Tags.Items.GLASS_BLOCKS_COLORLESS)
                                .unlockedBy(ID, has(zItemTag.PLATE_STEEL))

                                .save(c);

                ShapedRecipeBuilder.shaped(MISC, zBlocks.LASER_MACHINE.get())
                                .pattern("RC ")
                                .pattern("CDC")
                                .pattern(" CS")
                                .define('R', zItems.CARBON_FIBER.get())
                                .define('S', zItemTag.PLATE_STEEL)
                                .define('C', Tags.Items.INGOTS_COPPER)
                                .define('D', Tags.Items.DUSTS_REDSTONE)
                                .unlockedBy(ID, has(zItemTag.PLATE_STEEL))

                                .save(c);

                ShapedRecipeBuilder.shaped(MISC, zBlocks.LASER_SENSOR.get())
                                .pattern("CRC")
                                .pattern("DSD")
                                .define('R', zItems.CARBON_FIBER.get())
                                .define('S', zItemTag.PLATE_STEEL)
                                .define('C', Tags.Items.INGOTS_COPPER)
                                .define('D', Tags.Items.DUSTS_REDSTONE)
                                .unlockedBy(ID, has(zItemTag.PLATE_STEEL))

                                .save(c);

                ShapedRecipeBuilder.shaped(MISC, zBlocks.LASER_ROTOR.get())
                                .pattern("SGS")
                                .pattern("GDG")
                                .pattern("SGS")
                                .define('G', zBlocks.LASER_MACHINE.get())
                                .define('S', zItemTag.PLATE_STEEL)
                                .define('D', Tags.Items.DUSTS_REDSTONE)
                                .unlockedBy(ID, has(zItemTag.PLATE_STEEL))

                                .save(c);

        }

        protected static void slab(ItemLike slab, ItemLike material, RecipeOutput c) {
                ShapedRecipeBuilder.shaped(BUILDING_BLOCKS, slab, 6).define('#', material).pattern("###")
                                .unlockedBy(getHasName(material), has(material))
                                .save(c);
        }

        protected static void stair(ItemLike stair, ItemLike material, RecipeOutput c) {
                ShapedRecipeBuilder.shaped(BUILDING_BLOCKS, stair, 4).define('#', material)
                                .pattern("#  ")
                                .pattern("## ")
                                .pattern("###")
                                .unlockedBy(getHasName(material), has(material))
                                .save(c);
        }

        protected static void stonecutter(RecipeOutput c, ItemLike result, ItemLike material, int resultCount) {
                stonecutter(c, result, material, resultCount,
                                ID + ":" + getConversionRecipeName(result, material) + "_stonecutting");
        }

        protected static void stonecutter(RecipeOutput c, ItemLike result, ItemLike material) {
                stonecutter(c, result, material, 1,
                                ID + ":" + getConversionRecipeName(result, material) + "_stonecutting");
        }

        protected static void slab(ItemLike slab, ItemLike material, RecipeOutput c, String extra) {
                ShapedRecipeBuilder.shaped(BUILDING_BLOCKS, slab, 6).define('#', material).pattern("###")
                                .unlockedBy(getHasName(material), has(material))
                                .save(c, extra);
        }

        protected static void stair(ItemLike stair, ItemLike material, RecipeOutput c, String extra) {
                ShapedRecipeBuilder.shaped(BUILDING_BLOCKS, stair, 4).define('#', material)
                                .pattern("  #")
                                .pattern(" ##")
                                .pattern("###")
                                .unlockedBy(getHasName(material), has(material))
                                .save(c, extra);
        }

        protected static void stonecutter(RecipeOutput c, ItemLike result, ItemLike material, int resultCount,
                        String extra) {
                SingleItemRecipeBuilder.stonecutting(Ingredient.of(material), BUILDING_BLOCKS, result, resultCount)
                                .unlockedBy(getHasName(material), has(material))
                                .save(c, extra);
        }

        protected static void stonecutter(RecipeOutput c, ItemLike result, TagKey<Item> material, int resultCount,
                        String extra) {
                SingleItemRecipeBuilder.stonecutting(x.ingredient(material), BUILDING_BLOCKS, result, resultCount)
                                .unlockedBy("tag_"+getItemName(result), has(material))
                                .save(c, extra);
        }

        protected static void stonecutter(RecipeOutput c, ItemLike result, TagKey<Item> material, 
                        String extra) {
                 stonecutter(c, result, material,1,extra);
        }

        protected static void stonecutter(RecipeOutput c, ItemLike result, TagKey<Item> material) {
                 stonecutter(c, result, material,1,ID + ":" + getItemName(result) + "_from_stonecutting");
        }

        protected static void stonecutter(RecipeOutput c, ItemLike result, ItemLike material,String extra) {
                stonecutter(c, result, material,1,extra);
        }

        protected static void pillar(RecipeOutput c, ItemLike result, ItemLike material) {
                ShapedRecipeBuilder.shaped(BUILDING_BLOCKS, result, 2)
                                .define('#', material)
                                .pattern("#")
                                .pattern("#")
                                .unlockedBy(getHasName(material), has(material))
                                .save(c);
        }

        protected static void pillar(RecipeOutput c, ItemLike result, ItemLike material,
                        String extra) {
                ShapedRecipeBuilder.shaped(BUILDING_BLOCKS, result, 2)
                                .define('#', material)
                                .pattern("#")
                                .pattern("#")
                                .unlockedBy(getHasName(material), has(material))
                                .save(c, extra);
        }

        protected static void tiles(RecipeOutput c, ItemLike result, ItemLike material,
                        String extra) {
                ShapedRecipeBuilder.shaped(BUILDING_BLOCKS, result, 4)
                                .define('#', material)
                                .pattern("##")
                                .pattern("##")
                                .unlockedBy(getHasName(material), has(material))
                                .save(c, extra);
        }

        protected static void tiles(RecipeOutput c, ItemLike result, ItemLike material) {
                ShapedRecipeBuilder.shaped(BUILDING_BLOCKS, result, 4)
                                .define('#', material)
                                .pattern("##")
                                .pattern("##")
                                .unlockedBy(getHasName(material), has(material))
                                .save(c);
        }

}
