package com.devdyna.synergy.datagen.server;

import static com.devdyna.synergy.Main.ID;
import static net.minecraft.data.recipes.RecipeCategory.*;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.api.utils.x;
import com.devdyna.synergy.common.recipes.builders.*;
import com.devdyna.synergy.common.recipes.builders.node_provider.FluidProviderBuilder;
import com.devdyna.synergy.common.recipes.builders.node_provider.ItemProviderBuilder;
import com.devdyna.synergy.datagen.api.ExtraRecipeProvider;
import com.devdyna.synergy.init.builder.industrial_machines.alloy_smelter.recipe.AlloySmelterRecipeBuilder;
import com.devdyna.synergy.init.builder.industrial_machines.caster.recipe.CasterRecipeBuilder;
import com.devdyna.synergy.init.builder.industrial_machines.compressor.recipe.CompressorRecipeBuilder;
import com.devdyna.synergy.init.builder.industrial_machines.extractor.recipe.ExtractorRecipeBuilder;
import com.devdyna.synergy.init.builder.industrial_machines.furnace.recipe.ElectricFurnaceRecipeBuilder;
import com.devdyna.synergy.init.builder.industrial_machines.macerator.recipe.MaceratorRecipeBuilder;
import com.devdyna.synergy.init.builder.industrial_machines.melter.recipe.MelterRecipeBuilder;
import com.devdyna.synergy.init.builder.industrial_machines.rock_crusher.recipe.RockCrusherRecipeBuilder;
import com.devdyna.synergy.init.builder.magic.quern.recipe.QuernMillingBuilder;
import com.devdyna.synergy.init.builder.magic.urn.recipe.UrnRitualBuilder;
import com.devdyna.synergy.init.builder.magic.void_box.recipe.VoidBoxInfusionBuilder;
import com.devdyna.synergy.init.builder.nuclear_reactor.fuel_cell.recipe.ReactorCellBuilder;
import com.devdyna.synergy.init.builder.plants.cultivated.azalea;
import com.devdyna.synergy.init.builder.survival.casting_table.recipe.CastingTableBuilder;
import com.devdyna.synergy.init.builder.survival.crushing_tub.recipe.CrushingTubBuilder;
import com.devdyna.synergy.init.builder.survival.drying_rack.recipe.DryingRackBuilder;
import com.devdyna.synergy.init.builder.survival.evaporation_basin.recipe.EvaporatingBasinBuilder;
import com.devdyna.synergy.init.builder.survival.foundry.recipe.FoundryBuilder;
import com.devdyna.synergy.init.types.*;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.registries.DeferredHolder;

@SuppressWarnings("null")
public class DataRecipe extends ExtraRecipeProvider {

        public DataRecipe(PackOutput o, CompletableFuture<HolderLookup.Provider> c) {
                super(o, c);
        }

        @Override
        protected void buildRecipes(RecipeOutput c) {

                packUnpack(c, zItems.SILICON_SHARD.get(), zItems.RAW_SILICON.get(), false);

                UrnRitualBuilder.of()
                                .add(Items.REDSTONE)
                                .add(Items.GLOWSTONE_DUST)
                                .output(zItems.ENERGIZED_REDSTONE, 2)
                                .unlockedBy()
                                .save(c);

                AlloySmelterRecipeBuilder.of()
                                .inputs(zItemTag.DUST_COAL, Tags.Items.INGOTS_IRON)
                                .delay(80)
                                .output(zItems.STEEL_INGOT)
                                .unlockedBy()
                                .save(c);

                AlloySmelterRecipeBuilder.of()
                                .inputs(Tags.Items.INGOTS_GOLD, Items.NETHERITE_SCRAP)
                                .delay(240)
                                .output(Items.NETHERITE_INGOT)
                                .unlockedBy()
                                .save(c);

                AlloySmelterRecipeBuilder.of()
                                .inputs(Items.GLOWSTONE_DUST, Items.REDSTONE)
                                .delay(240)
                                .output(zItems.ENERGIZED_REDSTONE, 2)
                                .unlockedBy()
                                .save(c);

                UrnRitualBuilder.of()
                                .add(zItemTag.DUST_AMETHYST)
                                .add(zItemTag.DUST_LAPIS)
                                .output(zItems.MAGIC_DUST, 2)
                                .unlockedBy().save(c);

                VoidBoxInfusionBuilder.of()
                                .input(Items.DIAMOND)
                                .output(zItems.VOID_CRYSTAL)
                                .unlockedBy().save(c);

                QuernMillingBuilder.of().input(Items.SUGAR_CANE)
                                .output(Items.SUGAR, 2)
                                .unlockedBy().save(c);

                MaceratorRecipeBuilder.of().input(Items.SUGAR_CANE)
                                .output(Items.SUGAR, 2)
                                .secondary(Items.SUGAR)
                                .chance(0.75f)
                                .unlockedBy().save(c);

                MaceratorRecipeBuilder.of().input(Items.SAND)
                                .output(zItems.SILICON_SHARD, 1)
                                .unlockedBy().save(c);

                QuernMillingBuilder.of().input(Items.BONE)
                                .output(Items.BONE_MEAL, 4)
                                .unlockedBy().save(c);

                MaceratorRecipeBuilder.of().input(Items.BONE)
                                .output(Items.BONE_MEAL, 5)
                                .secondary(Items.BONE_MEAL, 2)
                                .chance(0.75f)
                                .unlockedBy().save(c);

                QuernMillingBuilder.of().input(ItemTags.WOOL)
                                .output(Items.STRING, 4)
                                .unlockedBy().save(c);

                MaceratorRecipeBuilder.of().input(ItemTags.WOOL)
                                .output(Items.STRING, 4)
                                .unlockedBy().save(c, "_from_wool");

                MaceratorRecipeBuilder.of().input(zItems.COTTON.get())
                                .output(Items.STRING, 5)
                                .secondary(Items.STRING, 2)
                                .chance(0.75f)
                                .unlockedBy().save(c, "_from_cotton");

                MaceratorRecipeBuilder.of().input(Tags.Items.SANDSTONE_UNCOLORED_BLOCKS)
                                .output(Items.SAND, 4)
                                .secondary(zItems.SILICON_SHARD)
                                .chance(0.25f)
                                .unlockedBy().save(c, "_from_sandstone");

                MaceratorRecipeBuilder.of().input(Tags.Items.SANDSTONE_RED_BLOCKS)
                                .output(Items.RED_SAND, 4)
                                .secondary(zItems.SILICON_SHARD)
                                .chance(0.25f)
                                .unlockedBy().save(c);

                MaceratorRecipeBuilder.of().input(Items.BREEZE_ROD)
                                .output(Items.WIND_CHARGE, 6)
                                .unlockedBy().save(c);

                MaceratorRecipeBuilder.of().input(Items.IRON_HORSE_ARMOR)
                                .output(zItems.IRON_DUST, 5)
                                .unlockedBy().save(c, "_from_horse_armor");

                MaceratorRecipeBuilder.of().input(Items.GOLDEN_HORSE_ARMOR)
                                .output(zItems.GOLD_DUST, 5)
                                .unlockedBy().save(c, "_from_horse_armor");

                MaceratorRecipeBuilder.of().input(Items.DIAMOND_HORSE_ARMOR)
                                .output(Items.DIAMOND, 5)
                                .unlockedBy().save(c, "_from_horse_armor");

                twoByTwoPacker(c, zItems.BLANK_MOLD.get(), zItemTag.PLATE_STEEL);

                zItems.zMolds.getEntries().stream().map(DeferredHolder::get)
                                .forEach(i -> {
                                        stonecutter(c, i, zItems.BLANK_MOLD.get());
                                        stonecutter(c, i, zItemTag.MOLDS);
                                });

                ExtractorRecipeBuilder.of()
                                .input(Items.SLIME_BALL)
                                .fluid(zFluids.GLUE, 250)
                                .unlockedBy()
                                .save(c);

                ExtractorRecipeBuilder.of()
                                .input(zItems.OIL)
                                .fluid(zFluids.RUBBER, 25)
                                .secondary(zItems.CARBON_DUST, 0.15f)
                                .unlockedBy()
                                .save(c);

                ExtractorRecipeBuilder.of()
                                .input(zItems.VIOLET_WEBCAP_MUSHROOM.get())
                                .fluid(zFluids.OIL, 250)
                                .unlockedBy()
                                .save(c);

                MaceratorRecipeBuilder.of()
                                .input(Items.TORCHFLOWER)
                                .output(Items.BLAZE_POWDER)
                                .secondary(Items.BLAZE_POWDER)
                                .chance(0.05f)
                                .delay(120)
                                .unlockedBy()
                                .save(c, "_from_torchflower");

                ExtractorRecipeBuilder.of()
                                .input(zItems.BLUE_CUP_MUSHROOM.get())
                                .secondary(Items.LAPIS_LAZULI, 0.25f)
                                .fluid(Fluids.WATER, 250)
                                .unlockedBy()
                                .save(c);

                ExtractorRecipeBuilder.of()
                                .input(Items.MAGMA_BLOCK)
                                .secondary(Items.NETHERRACK, 0.75f)
                                .fluid(Fluids.LAVA, 250)
                                .unlockedBy()
                                .save(c, "_from_magmablock");

                ExtractorRecipeBuilder.of()
                                .input(Items.HONEYCOMB)
                                .secondary(zItems.BEEWAX, 0.5f)
                                .fluid(zFluids.HONEY, 250)
                                .unlockedBy()
                                .save(c, "_from_honeycomb");

                ExtractorRecipeBuilder.of()
                                .input(zItems.BEEWAX)
                                .fluid(zFluids.HONEY, 150)
                                .unlockedBy()
                                .save(c, "_from_beewax");

                ExtractorRecipeBuilder.of()
                                .input(Items.HONEY_BOTTLE)
                                .secondary(Items.SUGAR, 6)
                                .unlockedBy()
                                .save(c);

                ExtractorRecipeBuilder.of()
                                .input(Items.OBSIDIAN)
                                .secondary(Items.COBBLESTONE, 0.25f)
                                .fluid(Fluids.LAVA, 1000)
                                .unlockedBy()
                                .save(c, "_from_obsidian");

                ExtractorRecipeBuilder.of()
                                .input(Items.WET_SPONGE)
                                .secondary(Items.SPONGE)
                                .fluid(Fluids.WATER, 1000)
                                .unlockedBy()
                                .save(c, "_from_sponges");

                CasterRecipeBuilder.of()
                                .fluid(zFluids.LIQUID_GLASS, 1000)
                                .input(zItems.MOLD_BLOCK)
                                .output(Items.GLASS)
                                .unlockedBy()
                                .save(c);

                CasterRecipeBuilder.of()
                                .fluid(Tags.Fluids.WATER, 250)
                                .input(Items.COBBLESTONE)
                                .consumeItemInput()
                                .output(Items.MOSSY_COBBLESTONE)
                                .unlockedBy()
                                .save(c);

                CasterRecipeBuilder.of()
                                .fluid(zFluids.HONEY, 1000)
                                .input(zItems.MOLD_BLOCK)
                                .output(Items.HONEY_BLOCK)
                                .unlockedBy()
                                .save(c);

                MelterRecipeBuilder.of()
                                .input(Tags.Items.COBBLESTONES)
                                .fluid(Fluids.LAVA, 250)
                                .unlockedBy()
                                .save(c);

                MelterRecipeBuilder.of()
                                .input(Tags.Items.GLASS_BLOCKS_COLORLESS)
                                .fluid(zFluids.LIQUID_GLASS, 1000)
                                .unlockedBy()
                                .save(c);

                FoundryBuilder.of()
                                .input(Tags.Items.GLASS_BLOCKS_COLORLESS)
                                .fluid(zFluids.LIQUID_GLASS, 1000)
                                .unlockedBy()
                                .save(c);

                CasterRecipeBuilder.of()
                                .input(zItems.CHIP)
                                .fluid(zFluids.LIQUID_GLASS, 250)
                                .consumeItemInput()
                                .output(zItems.ELECTRON_TUBE_BASE)
                                .unlockedBy()
                                .save(c);

                CastingTableBuilder.of()
                                .fluid(zFluids.LIQUID_GLASS, 250)
                                .input(zItems.CHIP)
                                .output(zItems.ELECTRON_TUBE_BASE)
                                .consumeItemInput()
                                .unlockedBy()
                                .save(c);

                ShapelessRecipeBuilder.shapeless(MISC, zItems.WROUGHT_IRON_INGOT.get(), 1)
                                .requires(zItemTag.DUST_COAL)
                                .requires(zItemTag.DUST_COAL)
                                .requires(Tags.Items.INGOTS_IRON)
                                .unlockedBy(ID, has(Items.IRON_INGOT))
                                .save(c);

                SimpleCookingRecipeBuilder.blasting(Ingredient.of(zItems.WROUGHT_IRON_INGOT.get()), MISC,
                                zItems.STEEL_INGOT.get(), 0.01f, 100)
                                .unlockedBy(ID, has(zItems.WROUGHT_IRON_INGOT.get()))
                                .save(c);

                ElectricFurnaceRecipeBuilder.of()
                                .input(zItems.WROUGHT_IRON_INGOT)
                                .output(zItems.STEEL_INGOT)
                                .unlockedBy()
                                .save(c);

                ExtractorRecipeBuilder.of()
                                .input(zItems.IRONBERRIES)
                                .secondary(zItems.TINY_IRON_DUST, 0.25f)
                                .fluid(zFluids.IRONBERRY_JUICE, 500)
                                .unlockedBy()
                                .save(c);

                CasterRecipeBuilder.of()
                                .fluid(zFluids.IRONBERRY_JUICE, 125)
                                .output(zItems.TINY_IRON_DUST)
                                .unlockedBy()
                                .save(c);

                CrushingTubBuilder.of()
                                .input(zItems.IRONBERRIES)
                                .fluid(zFluids.IRONBERRY_JUICE, 250)
                                .output(zItems.TINY_IRON_DUST)
                                .unlockedBy()
                                .save(c);

                EvaporatingBasinBuilder.of()
                                .fluid(zFluids.IRONBERRY_JUICE, 125)
                                .output(zItems.TINY_IRON_DUST)
                                .unlockedBy()
                                .save(c);

                EvaporatingBasinBuilder.of()
                                .fluid(Fluids.WATER, 125)
                                .output(zItems.SALT)
                                .unlockedBy()
                                .save(c);

                SimpleCookingRecipeBuilder
                                .smelting(Ingredient.of(zItems.RAW_SILICON.get()),
                                                RecipeCategory.MISC,
                                                zItems.SILICON.get(), 0.1F, 200)
                                .unlockedBy(getHasName(zItems.SILICON.get()),
                                                has(zItems.SILICON.get()))
                                .save(c, ID + ":"
                                                + getConversionRecipeName(
                                                                zItems.RAW_SILICON.get(),
                                                                zItems.SILICON.get()));

                SimpleCookingRecipeBuilder
                                .smelting(Ingredient.of(Items.IRON_INGOT),
                                                RecipeCategory.BUILDING_BLOCKS,
                                                zItems.CAST_IRON_INGOT.get(), 0.1F, 200)
                                .unlockedBy(getHasName(Items.IRON_INGOT),
                                                has(Items.IRON_INGOT))
                                .save(c, ID + ":"
                                                + getConversionRecipeName(
                                                                zItems.CAST_IRON_INGOT.get(),
                                                                Items.IRON_INGOT));

                SimpleCookingRecipeBuilder
                                .blasting(Ingredient.of(Items.IRON_INGOT),
                                                RecipeCategory.BUILDING_BLOCKS,
                                                zItems.CAST_IRON_INGOT.get(), 0.1F, 100)
                                .unlockedBy(getHasName(Items.IRON_INGOT),
                                                has(Items.IRON_INGOT))
                                .save(c, ID + ":" + getConversionRecipeName(
                                                zItems.CAST_IRON_INGOT.get(),
                                                Items.IRON_INGOT) + "_from_blasting");

                stonecutter(c, zBlocks.CAST_IRON_BLOCK.get(), zItems.CAST_IRON_INGOT.get(), 2);
                stonecutter(c, zBlocks.CAST_IRON_TILES.get(), zItems.CAST_IRON_INGOT.get(), 2);

                stonecutter(c, zBlocks.CAST_IRON_BLOCK.get(), zItemTag.CAST_IRON_BLOCKS);
                stonecutter(c, zBlocks.CAST_IRON_TILES.get(), zItemTag.CAST_IRON_BLOCKS);

                stonecutter(c, zBlocks.CALCITE_BRICKS.get(), Items.CALCITE);

                ShapedRecipeBuilder.shaped(MISC, zBlocks.CALCITE_BRICKS.get().asItem())
                                .pattern("##")
                                .pattern("##")
                                .define('#', Items.CALCITE)
                                .unlockedBy(ID, has(Items.CALCITE))
                                .save(c);

                FoundryFuelBuilder.of()
                                .fluid(Fluids.LAVA)
                                .speed(1.0f)
                                .usage(1.0f)
                                .unlockedBy()
                                .save(c);

                FoundryFuelBuilder.of()
                                .fluid(zFluids.MOLTEN_BLAZE)
                                .speed(2.0f)
                                .usage(0.5f)
                                .unlockedBy()
                                .save(c);

                FoundryBuilder.of()
                                .input(Items.BLAZE_ROD)
                                .fluid(zFluids.MOLTEN_BLAZE, 500)
                                .unlockedBy()
                                .save(c, "_from_blaze_rod");

                FoundryBuilder.of()
                                .input(Items.BLAZE_POWDER)
                                .fluid(zFluids.MOLTEN_BLAZE, 125)
                                .unlockedBy()
                                .save(c, "_from_blaze_powder");

                CompressorRecipeBuilder.of()
                                .input(zItems.RUBBER)
                                .catalyst(zItems.MOLD_PLATE.get())
                                .output(zItems.PLASTIC, 2)
                                .unlockedBy()
                                .save(c);

                EvaporatingBasinBuilder.of()
                                .fluid(zFluids.RUBBER, 250)
                                .output(zItems.RUBBER)
                                .unlockedBy()
                                .save(c);

                CasterRecipeBuilder.of()
                                .fluid(zFluids.RUBBER, 250)
                                .output(zItems.RUBBER)
                                .unlockedBy()
                                .save(c);

                MaceratorRecipeBuilder.of().input(Items.CLAY)
                                .output(Items.CLAY_BALL, 4)
                                .unlockedBy().save(c);

                CasterRecipeBuilder.of()
                                .fluid(Fluids.WATER, 125)
                                .input(zItemTag.DIRTS)
                                .consumeItemInput()
                                .output(Items.MUD)
                                .unlockedBy()
                                .save(c);

                CasterRecipeBuilder.of()
                                .fluid(Fluids.LAVA, 7500)
                                .delay(320)
                                .energy(1000)
                                .input(zItems.STONE_PEBBLE)
                                .consumeItemInput()
                                .output(Items.TUFF)
                                .unlockedBy()
                                .save(c);

                CasterRecipeBuilder.of()
                                .fluid(Fluids.WATER, 7500)
                                .delay(320)
                                .energy(1000)
                                .input(Items.DIORITE)
                                .consumeItemInput()
                                .output(Items.CALCITE)
                                .unlockedBy()
                                .save(c);

                CasterRecipeBuilder.of()
                                .fluid(Fluids.WATER, 7500)
                                .delay(320)
                                .energy(1000)
                                .input(Items.CALCITE)
                                .consumeItemInput()
                                .output(Items.DRIPSTONE_BLOCK)
                                .unlockedBy()
                                .save(c);

                CasterRecipeBuilder.of()
                                .fluid(Fluids.LAVA, 7500)
                                .energy(1000)
                                .delay(320)
                                .input(Items.TUFF)
                                .consumeItemInput()
                                .output(Items.DEEPSLATE)
                                .unlockedBy()
                                .save(c);

                CasterRecipeBuilder.of()
                                .fluid(Fluids.LAVA, 1000)
                                .input(Items.NETHERRACK)
                                .consumeItemInput()
                                .output(Items.MAGMA_BLOCK)
                                .unlockedBy()
                                .save(c);

                CasterRecipeBuilder.of()
                                .fluid(Fluids.LAVA, 1000)
                                .input(zItems.MOLD_BLOCK)
                                .output(Items.OBSIDIAN)
                                .unlockedBy()
                                .save(c);

                ElectricFurnaceRecipeBuilder.of()
                                .input(Items.MUD)
                                .output(Items.CLAY)
                                .unlockedBy().save(c);

                Map.of(
                                Items.ANDESITE, zItems.ANDESITE_CHUNK,
                                Items.DIORITE, zItems.DIORITE_CHUNK,
                                Items.GRANITE, zItems.GRANITE_CHUNK,
                                Items.TUFF, zItems.TUFF_CHUNK,
                                Items.COBBLED_DEEPSLATE, zItems.DEEPSLATE_CHUNK,
                                Items.DRIPSTONE_BLOCK, zItems.DRIPSTONE_CHUNK,
                                Items.BLACKSTONE, zItems.BLACKSTONE_CHUNK,
                                Items.END_STONE, zItems.ENDSTONE_CHUNK)
                                .forEach((bk, ch) -> {

                                        if (bk != Items.END_STONE)
                                                simplePacked(c, ch.get(), bk, true);

                                        MaceratorRecipeBuilder.of()
                                                        .input(bk)
                                                        .output(ch, 2)
                                                        .secondary(ch)
                                                        .chance(0.75f)
                                                        .unlockedBy()
                                                        .save(c);

                                });

                MaceratorRecipeBuilder.of()
                                .input(zItems.ENDSTONE_CHUNK)
                                .output(zItems.ENDSTONE_PEBBLE, 2)
                                .secondary(zItems.ENDSTONE_PEBBLE)
                                .chance(0.25f)
                                .unlockedBy()
                                .save(c);

                simplePacked(c, zItems.ENDSTONE_PEBBLE.get(), Items.END_STONE, false);

                // ----------------------------------------------------------------------------//

                // RockCrusherRecipeBuilder.of()
                // .fluid(zFluidTags.SULFURIC_ACID, 25)
                // .input(Items.STONE)
                // // .addResult(zItems.STONE_PEBBLE, 0.95f)
                // // .addResult(zItems.CASSITERITE, 0.35f)
                // // .addResult(zItems.HEMATITE, 0.25f)

                // // .addResult(zItems.CYLINDRITE, 0.05f)
                // .unlockedBy()
                // .save(c);

                RockCrusherRecipeBuilder.of()
                                .fluid(zFluidTags.SULFURIC_ACID, 25)
                                .input(zItems.ANDESITE_CHUNK)
                                .addResult(zItems.STONE_PEBBLE, 0.65f)
                                .addResult(zItems.QUARTZITE, 0.25f)
                                .addResult(zItems.CHALCOPYRITE, 0.15f)
                                .addResult(zItems.CYLINDRITE, 0.15f)
                                .unlockedBy()
                                .save(c);

                RockCrusherRecipeBuilder.of()
                                .fluid(zFluidTags.SULFURIC_ACID, 25)
                                .input(zItems.DIORITE_CHUNK)
                                .addResult(zItems.STONE_PEBBLE, 0.65f)
                                .addResult(zItems.QUARTZITE, 0.25f)
                                .addResult(zItems.CASSITERITE, 0.15f)
                                .addResult(zItems.ARGENTITE, 0.15f)
                                .unlockedBy()
                                .save(c);

                RockCrusherRecipeBuilder.of()
                                .fluid(zFluidTags.SULFURIC_ACID, 25)
                                .input(zItems.GRANITE_CHUNK)
                                .addResult(zItems.STONE_PEBBLE, 0.65f)
                                .addResult(zItems.QUARTZITE, 0.35f)
                                .addResult(zItems.BAUXITE, 0.25f)
                                .addResult(zItems.HEMATITE, 0.15f)
                                .addResult(zItems.LIGNITE, 0.05f)
                                .unlockedBy()
                                .save(c);

                RockCrusherRecipeBuilder.of()
                                .fluid(zFluidTags.SULFURIC_ACID, 25)
                                .input(zItems.DRIPSTONE_CHUNK)
                                // .addResult(Items.CLAY_BALL, 0.85f)
                                .addResult(zItems.KAOLIN, 0.55f)
                                .addResult(zItems.HEMATITE, 0.35f)
                                .addResult(zItems.BAUXITE, 0.2f)
                                .addResult(zItems.MALACHITE, 0.15f)
                                .addResult(zItems.LIGNITE, 0.05f)
                                .unlockedBy()
                                .save(c);

                // RockCrusherRecipeBuilder.of()
                // .fluid(zFluidTags.SULFURIC_ACID, 25)
                // .input(Items.MUD)
                // // .addResult(zItems.MUD_BALL, 0.85f)
                // // .addResult(Items.FLINT, 0.45f)
                // .addResult(zItems.KAOLIN, 0.25f)
                // .addResult(zItems.LIGNITE, 0.05f)
                // .unlockedBy()
                // .save(c);

                RockCrusherRecipeBuilder.of()
                                .fluid(zFluidTags.SULFURIC_ACID, 25)
                                .input(zItems.TUFF_CHUNK)
                                .addResult(zItems.STONE_PEBBLE, 0.65f)
                                .addResult(zItems.HEMATITE, 0.45f)
                                .addResult(zItems.GALENA, 0.35f)
                                .addResult(zItems.BAUXITE, 0.35f)
                                .addResult(zItems.OSMIRIDIUM, 0.25f)
                                .addResult(zItems.PENTLANDITE, 0.15f)
                                .addResult(zItems.CASSITERITE, 0.15f)
                                .unlockedBy()
                                .save(c);

                RockCrusherRecipeBuilder.of()
                                .fluid(zFluidTags.SULFURIC_ACID, 25)
                                .input(zItems.DEEPSLATE_CHUNK)
                                .addResult(zItems.STONE_PEBBLE, 0.65f)
                                .addResult(zItems.HEMATITE, 0.55f)
                                .addResult(zItems.PENTLANDITE, 0.45f)
                                .addResult(zItems.GALENA, 0.35f)
                                .addResult(zItems.ARGENTITE, 0.25f)
                                .addResult(zItems.AURICUPRIDE, 0.15f)
                                .unlockedBy()
                                .save(c);

                // RockCrusherRecipeBuilder.of()
                // .fluid(zFluidTags.SULFURIC_ACID, 25)
                // .input(Items.NETHERRACK)
                // // .addResult(zItems.NETHERRACK_PEBBLE, 0.85f)
                // .addResult(zItems.PYROLITE, 0.35f)
                // .addResult(zItems.QUARTZITE, 0.15f)
                // .unlockedBy()
                // .save(c);

                RockCrusherRecipeBuilder.of()
                                .fluid(zFluidTags.SULFURIC_ACID, 25)
                                .input(zItems.BLACKSTONE_CHUNK)
                                .addResult(zItems.STONE_PEBBLE, 0.65f)
                                .addResult(zItems.NETHERRACK_PEBBLE, 0.65f)
                                .addResult(zItems.LIGNITE, 0.35f)
                                .addResult(zItems.HEMATITE, 0.25f)
                                .addResult(zItems.PYROLITE, 0.15f)
                                .addResult(zItems.XENOTHITE, 0.15f)
                                .unlockedBy()
                                .save(c);

                RockCrusherRecipeBuilder.of()
                                .fluid(zFluidTags.SULFURIC_ACID, 25)
                                .input(zItems.ENDSTONE_PEBBLE)
                                .addResult(zItems.URANINITE, 0.15f)
                                .unlockedBy()
                                .save(c);

                // -----------------------------------------------------------------------------//

                RockCrusherRecipeBuilder.of()
                                .fluid(zFluidTags.SULFURIC_ACID, 25)
                                .input(zItems.BAUXITE)
                                .addResult(zItems.ALUMINUM_DUST, 0.75f)
                                .addResult(zItems.ALUMINUM_DUST, 0.5f)
                                .addResult(zItems.IRON_DUST, 0.5f)
                                .addResult(zItems.IRON_DUST, 0.25f)
                                .unlockedBy()
                                .save(c);

                RockCrusherRecipeBuilder.of()
                                .fluid(zFluidTags.SULFURIC_ACID, 25)
                                .input(zItems.CYLINDRITE)
                                .addResult(zItems.TIN_DUST, 0.75f)
                                .addResult(zItems.TIN_DUST, 0.5f)
                                .addResult(zItems.IRON_DUST, 0.25f)
                                .unlockedBy()
                                .save(c);

                RockCrusherRecipeBuilder.of()
                                .fluid(zFluidTags.SULFURIC_ACID, 25)
                                .input(zItems.AURICUPRIDE)
                                .addResult(zItems.GOLD_DUST, 0.75f)
                                .addResult(zItems.GOLD_DUST, 0.5f)
                                .addResult(zItems.COPPER_DUST, 0.5f)
                                .addResult(zItems.COPPER_DUST, 0.25f)
                                .addResult(Items.GLOWSTONE_DUST, 0.05f)
                                .unlockedBy()
                                .save(c);

                RockCrusherRecipeBuilder.of()
                                .fluid(zFluidTags.SULFURIC_ACID, 25)
                                .input(zItems.PENTLANDITE)
                                .addResult(zItems.NICKEL_DUST, 0.75f)
                                .addResult(zItems.NICKEL_DUST, 0.5f)
                                .addResult(zItems.IRON_DUST, 0.25f)
                                .addResult(zItems.COPPER_DUST, 0.25f)
                                .unlockedBy()
                                .save(c);

                RockCrusherRecipeBuilder.of()
                                .fluid(zFluidTags.SULFURIC_ACID, 25)
                                .input(zItems.GALENA)
                                .addResult(zItems.LEAD_DUST, 0.75f)
                                .addResult(zItems.LEAD_DUST, 0.65f)
                                .addResult(zItems.SILVER_DUST, 0.5f)
                                .addResult(zItems.SILVER_DUST, 0.15f)
                                .unlockedBy()
                                .save(c);

                RockCrusherRecipeBuilder.of()
                                .fluid(zFluidTags.SULFURIC_ACID, 25)
                                .input(zItems.OSMIRIDIUM)
                                .addResult(zItems.OSMIUM_DUST, 0.75f)
                                .addResult(zItems.OSMIUM_DUST, 0.45f)
                                .addResult(zItems.PLATINUM_DUST, 0.35f)
                                .addResult(zItems.IRIDIUM_DUST, 0.15f)
                                .unlockedBy()
                                .save(c);

                RockCrusherRecipeBuilder.of()
                                .fluid(zFluidTags.SULFURIC_ACID, 25)
                                .input(zItems.ARGENTITE)
                                .addResult(zItems.SILVER_DUST, 0.75f)
                                .addResult(zItems.SILVER_DUST, 0.45f)
                                .addResult(zItems.LEAD_DUST, 0.35f)
                                .addResult(zItems.IRON_DUST, 0.15f)
                                .addResult(Items.GLOWSTONE_DUST, 0.05f)
                                .unlockedBy()
                                .save(c);

                RockCrusherRecipeBuilder.of()
                                .fluid(zFluidTags.SULFURIC_ACID, 25)
                                .input(zItems.CASSITERITE)
                                .addResult(zItems.TIN_DUST, 0.75f)
                                .addResult(zItems.TIN_DUST, 0.5f)
                                .addResult(zItems.IRON_DUST, 0.45f)
                                .addResult(zItems.IRON_DUST, 0.15f)
                                .unlockedBy()
                                .save(c);

                RockCrusherRecipeBuilder.of()
                                .fluid(zFluidTags.SULFURIC_ACID, 25)
                                .input(zItems.MALACHITE)
                                .addResult(zItems.COPPER_DUST, 0.75f)
                                .addResult(zItems.COPPER_DUST, 0.5f)
                                .addResult(zItems.COPPER_DUST, 0.25f)
                                .addResult(zItems.GOLD_DUST, 0.15f)
                                .unlockedBy()
                                .save(c);

                RockCrusherRecipeBuilder.of()
                                .fluid(zFluidTags.SULFURIC_ACID, 25)
                                .input(zItems.URANINITE)
                                .addResult(zItems.URANIUM_DUST, 0.75f)
                                .addResult(zItems.PLATINUM_DUST, 0.15f)
                                .unlockedBy()
                                .save(c);

                RockCrusherRecipeBuilder.of()
                                .fluid(zFluidTags.SULFURIC_ACID, 25)
                                .input(zItems.KAOLIN)
                                .addResult(Items.CLAY_BALL, 0.75f)
                                .addResult(Items.CLAY_BALL, 0.5f)
                                .addResult(zItems.FOSSIL, 0.15f)
                                .unlockedBy()
                                .save(c);

                RockCrusherRecipeBuilder.of()
                                .fluid(zFluidTags.SULFURIC_ACID, 25)
                                .input(zItems.XENOTHITE)
                                .addResult(zItems.ANCIENT_DEBRIS_DUST, 0.65f)
                                .addResult(zItems.LEAD_DUST, 0.35f)
                                .addResult(zItems.IRON_DUST, 0.15f)
                                .unlockedBy()
                                .save(c);

                RockCrusherRecipeBuilder.of()
                                .fluid(zFluidTags.SULFURIC_ACID, 25)
                                .input(zItems.QUARTZITE)
                                .addResult(zItems.QUARTZ_DUST, 0.75f)
                                .addResult(zItems.QUARTZ_DUST, 0.5f)
                                .addResult(Items.QUARTZ, 0.15f)
                                .unlockedBy()
                                .save(c);

                RockCrusherRecipeBuilder.of()
                                .fluid(zFluidTags.SULFURIC_ACID, 25)
                                .input(zItems.CHALCOPYRITE)
                                .addResult(zItems.COPPER_DUST, 0.75f)
                                .addResult(zItems.COPPER_DUST, 0.5f)
                                .addResult(zItems.IRON_DUST, 0.25f)
                                .unlockedBy()
                                .save(c);

                RockCrusherRecipeBuilder.of()
                                .fluid(zFluidTags.SULFURIC_ACID, 25)
                                .input(zItems.PYROLITE)
                                .addResult(zItems.NETHERRACK_PEBBLE, 0.75f)
                                .addResult(zItems.NETHERRACK_PEBBLE, 0.5f)
                                .addResult(zItems.GOLD_DUST, 0.45f)
                                .addResult(zItems.SULFUR_DUST, 0.25f)
                                .addResult(Items.GLOWSTONE_DUST, 0.15f)
                                .unlockedBy()
                                .save(c);

                RockCrusherRecipeBuilder.of()
                                .fluid(zFluidTags.SULFURIC_ACID, 25)
                                .input(zItems.LIGNITE)
                                .addResult(zItems.CARBON_DUST, 0.5f)
                                .addResult(zItems.CARBON_DUST, 0.25f)
                                .addResult(zItems.SULFUR_DUST, 0.25f)
                                .addResult(zItems.SULFUR_DUST, 0.15f)
                                .unlockedBy()
                                .save(c);

                RockCrusherRecipeBuilder.of()
                                .fluid(zFluidTags.SULFURIC_ACID, 25)
                                .input(zItems.HEMATITE)
                                .addResult(zItems.IRON_DUST, 0.75f)
                                .addResult(zItems.IRON_DUST, 0.5f)
                                .addResult(zItems.TIN_DUST, 0.15f)
                                .unlockedBy()
                                .save(c);

                // minecraft ores
                oreProcessing(c, zFluids.MOLTEN_COPPER, x.rl("c", "raw_materials/copper"), zItems.COPPER_DUST.get(),
                                Items.COPPER_INGOT, x.rl("c", "ingots/copper"), zItems.GOLD_DUST.get(), 0.25f);
                oreProcessing(c, zFluids.MOLTEN_GOLD, x.rl("c", "raw_materials/gold"), zItems.GOLD_DUST.get(),
                                Items.GOLD_INGOT, x.rl("c", "ingots/gold"), zItems.SILVER_DUST.get(), 0.25f);
                oreProcessing(c, zFluids.MOLTEN_IRON, x.rl("c", "raw_materials/iron"), zItems.IRON_DUST.get(),
                                Items.IRON_INGOT, x.rl("c", "ingots/iron"), zItems.NICKEL_DUST.get(), 0.25f);
                // compat ores
                oreProcessing(c, zFluids.MOLTEN_TIN, x.rl("c", "raw_materials/tin"), zItems.TIN_DUST.get(),
                                zItems.TIN_INGOT.get(), x.rl("c", "ingots/tin"), zItems.IRON_DUST.get(), 0.25f);
                oreProcessing(c, zFluids.MOLTEN_SILVER, x.rl("c", "raw_materials/silver"), zItems.SILVER_DUST.get(),
                                zItems.SILVER_INGOT.get(), x.rl("c", "ingots/silver"), zItems.TIN_DUST.get(), 0.25f);
                oreProcessing(c, zFluids.MOLTEN_NICKEL, x.rl("c", "raw_materials/nickel"), zItems.NICKEL_DUST.get(),
                                zItems.NICKEL_INGOT.get(), x.rl("c", "ingots/nickel"), zItems.SILVER_DUST.get(), 0.25f);
                oreProcessing(c, zFluids.MOLTEN_IRIDIUM, x.rl("c", "raw_materials/iridium"), zItems.IRIDIUM_DUST.get(),
                                zItems.IRIDIUM_INGOT.get(), x.rl("c", "ingots/iridium"), zItems.IRON_DUST.get(), 0.25f);
                oreProcessing(c, zFluids.MOLTEN_URANIUM, x.rl("c", "raw_materials/uranium"), zItems.URANIUM_DUST.get(),
                                zItems.URANIUM_INGOT.get(), x.rl("c", "ingots/uranium"), zItems.LEAD_DUST.get(), 0.25f);
                oreProcessing(c, zFluids.MOLTEN_PLATINUM, x.rl("c", "raw_materials/platinum"),
                                zItems.PLATINUM_DUST.get(),
                                zItems.PLATINUM_INGOT.get(), x.rl("c", "ingots/platinum"), zItems.IRIDIUM_DUST.get(),
                                0.25f);
                oreProcessing(c, zFluids.MOLTEN_OSMIUM, x.rl("c", "raw_materials/osmium"), zItems.OSMIUM_DUST.get(),
                                zItems.OSMIUM_INGOT.get(), x.rl("c", "ingots/osmium"), zItems.IRON_DUST.get(), 0.25f);
                oreProcessing(c, zFluids.MOLTEN_ALUMINUM, x.rl("c", "raw_materials/aluminum"),
                                zItems.ALUMINUM_DUST.get(),
                                zItems.ALUMINUM_INGOT.get(), x.rl("c", "ingots/aluminum"), zItems.IRON_DUST.get(),
                                0.25f);
                oreProcessing(c, zFluids.MOLTEN_LEAD, x.rl("c", "raw_materials/lead"), zItems.LEAD_DUST.get(),
                                zItems.LEAD_INGOT.get(), x.rl("c", "ingots/lead"), zItems.SILVER_DUST.get(), 0.25f);

                moltenIngots(c, zItems.STEEL_INGOT.get(), zItemTag.INGOT_STEEL, zFluids.MOLTEN_STEEL);

                gear(c, zItems.WOODEN_GEAR, Tags.Items.RODS_WOODEN, ItemTags.PLANKS);

                gear(c, zItems.TIN_GEAR, zItemTag.INGOT_TIN, zFluids.MOLTEN_TIN);
                gear(c, zItems.GOLD_GEAR, Tags.Items.INGOTS_GOLD, zFluids.MOLTEN_GOLD);
                gear(c, zItems.IRON_GEAR, Tags.Items.INGOTS_IRON, zFluids.MOLTEN_IRON);
                gear(c, zItems.LEAD_GEAR, zItemTag.INGOT_LEAD, zFluids.MOLTEN_LEAD);
                gear(c, zItems.STEEL_GEAR, zItemTag.INGOT_STEEL, zFluids.MOLTEN_STEEL);
                gear(c, zItems.COPPER_GEAR, Tags.Items.INGOTS_COPPER, zFluids.MOLTEN_COPPER);
                gear(c, zItems.NICKEL_GEAR, zItemTag.INGOT_NICKEL, zFluids.MOLTEN_NICKEL);

                plate(Items.IRON_INGOT, zItems.IRON_PLATE.get(), c);
                plate(Items.GOLD_INGOT, zItems.GOLD_PLATE.get(), c);
                plate(Items.COPPER_INGOT, zItems.COPPER_PLATE.get(), c);
                plate(zItemTag.INGOT_SILVER, zItems.SILVER_PLATE.get(), c);
                plate(zItemTag.INGOT_STEEL, zItems.STEEL_PLATE.get(), c);
                plate(zItemTag.INGOT_ADVANCEDALLOY, zItems.ADVANCED_ALLOY_PLATE.get(), c);
                plate(zItemTag.GEMS_AQUAMARINE, zItems.AQUAMARINE_PLATE.get(), c);
                plate(zItemTag.INGOT_WROUGHT_IRON, zItems.WROUGHT_IRON_PLATE.get(), c);

                moltenPlates(c, zItems.IRON_PLATE.get(), zItemTag.PLATE_IRON, zFluids.MOLTEN_IRON);
                moltenPlates(c, zItems.GOLD_PLATE.get(), zItemTag.PLATE_GOLD, zFluids.MOLTEN_GOLD);
                moltenPlates(c, zItems.COPPER_PLATE.get(), zItemTag.PLATE_COPPER, zFluids.MOLTEN_COPPER);
                moltenPlates(c, zItems.SILVER_PLATE.get(), zItemTag.PLATE_SILVER, zFluids.MOLTEN_SILVER);
                moltenPlates(c, zItems.STEEL_PLATE.get(), zItemTag.PLATE_STEEL, zFluids.MOLTEN_STEEL);

                foil(c, zItemTag.PLATE_GOLD, zItems.GOLD_FOIL.get());
                foil(c, zItemTag.PLATE_COPPER, zItems.COPPER_FOIL.get());
                foil(c, zItemTag.PLATE_SILVER, zItems.SILVER_FOIL.get());
                foil(c, zItemTag.PLATE_IRON, zItems.IRON_FOIL.get());

                coil(c, zItemTag.FOIL_COPPER, zItems.COPPER_COIL.get());
                coil(c, zItemTag.FOIL_GOLD, zItems.GOLD_COIL.get());
                coil(c, zItemTag.FOIL_IRON, zItems.IRON_COIL.get());
                coil(c, zItemTag.FOIL_SILVER, zItems.SILVER_COIL.get());

                electron_tube(c, zItems.TIN_ELECTRON_TUBE, zFluids.MOLTEN_TIN);
                electron_tube(c, zItems.GOLD_ELECTRON_TUBE, zFluids.MOLTEN_GOLD);
                electron_tube(c, zItems.IRON_ELECTRON_TUBE, zFluids.MOLTEN_IRON);
                electron_tube(c, zItems.LEAD_ELECTRON_TUBE, zFluids.MOLTEN_LEAD);
                electron_tube(c, zItems.STEEL_ELECTRON_TUBE, zFluids.MOLTEN_STEEL);
                electron_tube(c, zItems.COPPER_ELECTRON_TUBE, zFluids.MOLTEN_COPPER);
                electron_tube(c, zItems.NICKEL_ELECTRON_TUBE, zFluids.MOLTEN_NICKEL);
                electron_tube(c, zItems.OSMIUM_ELECTRON_TUBE, zFluids.MOLTEN_OSMIUM);
                electron_tube(c, zItems.SILVER_ELECTRON_TUBE, zFluids.MOLTEN_SILVER);
                electron_tube(c, zItems.IRIDIUM_ELECTRON_TUBE, zFluids.MOLTEN_IRIDIUM);
                electron_tube(c, zItems.URANIUM_ELECTRON_TUBE, zFluids.MOLTEN_URANIUM);
                electron_tube(c, zItems.ALUMINUM_ELECTRON_TUBE, zFluids.MOLTEN_ALUMINUM);
                electron_tube(c, zItems.PLATINUM_ELECTRON_TUBE, zFluids.MOLTEN_PLATINUM);

                crushing(c, Items.ANCIENT_DEBRIS, zItems.ANCIENT_DEBRIS_DUST.get(), 2, 1,
                                zItems.ANCIENT_DEBRIS_DUST.get(), 0.5f);
                crushing(c, ItemTags.LOGS, zItems.SAWDUST.get(), 3, 2, zItems.SAWDUST.get(), 0.5f);

                gemDustProcess(c, Items.EMERALD, zItems.EMERALD_DUST.get());
                gemDustProcess(c, Items.AMETHYST_SHARD, zItems.AMETHYST_DUST.get());
                gemDustProcess(c, Items.DIAMOND, zItems.DIAMOND_DUST.get());
                gemDustProcess(c, Items.LAPIS_LAZULI, zItems.LAPIS_DUST.get());
                gemDustProcess(c, Items.QUARTZ, zItems.QUARTZ_DUST.get());
                crushing(c, Items.PRISMARINE_SHARD, Items.PRISMARINE_CRYSTALS);

                crushing(c, ItemTags.COALS, zItems.CARBON_DUST.get(), 2, 1, zItems.SULFUR_DUST.get(), 0.25f);
                doubleSmelt(c, zItems.ANCIENT_DEBRIS_DUST.get(), Items.NETHERITE_SCRAP);

                QuernMillingBuilder.of()
                                .input(Items.TORCHFLOWER)
                                .output(Items.BLAZE_POWDER)
                                .delay(120)
                                .unlockedBy()
                                .save(c);

                MaceratorRecipeBuilder.of()
                                .input(Items.BLAZE_ROD)
                                .output(Items.BLAZE_POWDER, 2)
                                .secondary(Items.BLAZE_POWDER, 2)
                                .chance(0.75f)
                                .delay(120)
                                .unlockedBy()
                                .save(c, "_from_blaze_rod");

                MaceratorRecipeBuilder.of()
                                .input(Tags.Items.NETHERRACKS)
                                .delay(40)
                                .unlockedBy()
                                .output(zItems.SULFUR_DUST, 2)
                                .secondary(zItems.SULFUR_DUST)
                                .chance(0.75f)
                                .save(c);

                MaceratorRecipeBuilder.of()
                                .input(Items.WHEAT)
                                .delay(40)
                                .unlockedBy()
                                .output(zItems.FLOUR, 2)
                                .save(c);

                QuernMillingBuilder.of()
                                .input(Items.WHEAT)
                                .delay(80)
                                .unlockedBy()
                                .output(zItems.FLOUR)
                                .save(c);

                SimpleCookingRecipeBuilder
                                .smelting(Ingredient.of(zItems.FLOUR.get()),
                                                RecipeCategory.BUILDING_BLOCKS,
                                                Items.BREAD, 0.1F, 200)
                                .unlockedBy(getHasName(zItems.FLOUR.get()),
                                                has(zItems.FLOUR.get()))
                                .save(c, ID + ":"
                                                + getConversionRecipeName(
                                                                Items.BREAD,
                                                                zItems.FLOUR.get()));

                // stoneworking

                crushing(c, Items.DEEPSLATE, Items.COBBLED_DEEPSLATE);

                crushing(c, Items.STONE, Items.COBBLESTONE);

                crushing(c, Items.COBBLESTONE, Items.GRAVEL);
                crushing(c, Items.GRAVEL, Items.SAND);

                // nugget ingots blocks

                nuggetIngotBlock(c, zItems.STEEL_NUGGET.get(), zItems.STEEL_INGOT.get(),
                                zBlocks.STEEL_BLOCK.get().asItem());
                nuggetIngotBlock(c, zItems.ADVANCED_ALLOY_NUGGET.get(), zItems.ADVANCED_ALLOY_INGOT.get(),
                                zBlocks.ADVANCED_ALLOY_BLOCK.get().asItem());

                nuggetIngotBlock(c, zItems.WROUGHT_IRON_NUGGET.get(), zItems.WROUGHT_IRON_INGOT.get(),
                                zBlocks.WROUGHT_IRON_BLOCK.get().asItem());

                // advanced alloy
                ShapedRecipeBuilder.shaped(MISC, zItems.MIXED_INGOT.get(), 3)
                                .define('T', zItemTag.PLATE_COPPER)
                                .define('C', zItemTag.PLATE_GOLD)
                                .define('B', zItemTag.PLATE_IRON)
                                .pattern("T")
                                .pattern("C")
                                .pattern("B")
                                .unlockedBy(ID, has(Items.IRON_INGOT))
                                .save(c);

                doubleSmelt(c, zItems.MIXED_INGOT.get(), zItems.ADVANCED_ALLOY_INGOT.get());

                // pebbles

                packUnpack(c, zItems.STONE_PEBBLE.get(), Items.COBBLESTONE, true);
                packUnpack(c, zItems.NETHERRACK_PEBBLE.get(), Items.NETHERRACK, true);

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

                seeds.forEach(s -> ResourceInfoBuilder
                                .of().input(s).output(result.get(seeds.indexOf(s))
                                                .stream().map(i -> x.item(i)).toList())
                                .unlockedBy().save(c));

                ResourceInfoBuilder
                                .of()
                                .input(zBlocks.IRON_WOOD.getSapling().get().asItem())
                                .output(
                                                zBlocks.IRON_WOOD.getLog().get().asItem(),
                                                zBlocks.IRON_WOOD.getLeaves().get().asItem(),
                                                zItems.IRONBERRIES.get())
                                .unlockedBy()
                                .save(c);

                ResourceInfoBuilder
                                .of()
                                .input(Items.OAK_SAPLING)
                                .output(
                                                Items.OAK_LOG,
                                                Items.OAK_LEAVES,
                                                Items.APPLE)
                                .unlockedBy()
                                .save(c);

                ResourceInfoBuilder
                                .of()
                                .input(Items.DARK_OAK_SAPLING)
                                .output(
                                                Items.DARK_OAK_LOG,
                                                Items.DARK_OAK_LEAVES,
                                                Items.APPLE)
                                .unlockedBy()
                                .save(c);

                ResourceInfoBuilder
                                .of()
                                .input(Items.SPRUCE_SAPLING)
                                .output(
                                                Items.SPRUCE_LOG,
                                                Items.SPRUCE_LEAVES)
                                .unlockedBy()
                                .save(c);

                ResourceInfoBuilder
                                .of()
                                .input(Items.BIRCH_SAPLING)
                                .output(
                                                Items.BIRCH_LOG,
                                                Items.BIRCH_LEAVES)
                                .unlockedBy()
                                .save(c);

                ResourceInfoBuilder
                                .of()
                                .input(Items.JUNGLE_SAPLING)
                                .output(
                                                Items.JUNGLE_LOG,
                                                Items.JUNGLE_LEAVES,
                                                Items.VINE)
                                .unlockedBy()
                                .save(c);

                ResourceInfoBuilder
                                .of()
                                .input(Items.ACACIA_SAPLING)
                                .output(
                                                Items.ACACIA_LOG,
                                                Items.ACACIA_LEAVES)
                                .unlockedBy()
                                .save(c);

                ResourceInfoBuilder
                                .of()
                                .input(zItemTag.AZALEA_BUSHES)
                                .output(
                                                Items.OAK_LOG,
                                                Items.AZALEA_LEAVES,
                                                Items.FLOWERING_AZALEA_LEAVES)
                                .unlockedBy()
                                .save(c);

                ResourceInfoBuilder
                                .of()
                                .input(Items.MANGROVE_PROPAGULE)
                                .output(
                                                Items.MANGROVE_LOG,
                                                Items.MANGROVE_LEAVES,
                                                Items.VINE,
                                                Items.MANGROVE_ROOTS,
                                                Items.MOSS_CARPET,
                                                Items.BEE_NEST)
                                .unlockedBy()
                                .save(c);

                ResourceInfoBuilder
                                .of()
                                .input(Items.CHERRY_SAPLING)
                                .output(
                                                Items.CHERRY_LOG,
                                                Items.CHERRY_LEAVES,
                                                Items.BEE_NEST)
                                .unlockedBy()
                                .save(c);

                ResourceInfoBuilder
                                .of()
                                .input(zBlocks.AQUAMARINE_CLUSTER.get().asItem())
                                .output(
                                                zItems.AQUAMARINE.get())
                                .unlockedBy()
                                .save(c);

                List<Item> clearNBT = List.of(
                                zItems.RED_BATTERY.get(),
                                zItems.BLUE_BATTERY.get(),
                                zItems.GREEN_BATTERY.get(),
                                zBlocks.SIMPLE_TANK.get().asItem(),
                                zBlocks.FUEL_TANK.get().asItem());

                clearNBT.forEach(i -> {
                        ShapelessRecipeBuilder.shapeless(MISC, i)
                                        .requires(i)
                                        .unlockedBy(ID, has(i))
                                        .save(c, asID(i, "_clear_nbt"));
                });

                zStatic.MachineUpgrades.TemplateUpgrades.ALL.forEach(i -> {
                        ShapelessRecipeBuilder.shapeless(MISC, i)
                                        .requires(i.getItem())
                                        .unlockedBy(ID, has(i.getItem()))
                                        .save(c, asID(i.getItem(), "_clear_nbt"));
                });

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

                ShapedRecipeBuilder.shaped(MISC, zItems.RED_BATTERY.get())
                                .pattern("PSP")
                                .pattern("RHR")
                                .pattern("PSP")
                                .define('R', zItems.ENERGIZED_REDSTONE.get())
                                .define('P', zItems.PLASTIC.get())
                                .define('H', zItems.CONDENSER.get())
                                .define('S', zItems.SILICON.get())
                                .unlockedBy(ID,
                                                has(zItems.PLASTIC.get()))
                                .save(c);

                ShapedRecipeBuilder.shaped(MISC, zItems.BLUE_BATTERY.get())
                                .pattern(" S ")
                                .pattern("RCR")
                                .pattern(" R ")
                                .define('S', Items.REDSTONE)
                                .define('R', zItems.RESISTOR.get())
                                .define('C', zItems.CONDENSER.get())
                                .unlockedBy(ID,
                                                has(zItems.RESISTOR.get()))
                                .save(c);

                ShapedRecipeBuilder.shaped(MISC, zItems.GREEN_BATTERY.get())
                                .pattern(" S ")
                                .pattern("RHR")
                                .pattern(" R ")
                                .define('S', Items.REDSTONE)
                                .define('R', Items.SLIME_BALL)
                                .define('H', zItems.CONDENSER.get())
                                .unlockedBy(ID, has(zItems.CONDENSER.get()))
                                .save(c);

                ReactorCellBuilder.of()
                                .input(zItems.URANIUM_PELLET)
                                .output(zItems.DEPLETED_URANIUM_PELLET)
                                .duration(10_000)
                                .energy(160)
                                .heat(50)
                                .unlockedBy().save(c);

                ReactorCellBuilder.of()
                                .input(zItems.THORIUM_PELLET)
                                .output(zItems.DEPLETED_THORIUM_PELLET)
                                .duration(25_000)
                                .energy(480)
                                .heat(90)
                                .unlockedBy().save(c);

                ReactorCellBuilder.of()
                                .input(zItems.PLUTONIUM_PELLET)
                                .output(zItems.DEPLETED_PLUTONIUM_PELLET)
                                .duration(50_000)
                                .energy(1_440)
                                .heat(180)
                                .unlockedBy().save(c);

                ReactorCellBuilder.of()
                                .input(zItems.NEPTUNIUM_PELLET)
                                .output(zItems.DEPLETED_NEPTUNIUM_PELLET)
                                .duration(75_000)
                                .energy(4_320)
                                .heat(350)
                                .unlockedBy().save(c);

                ReactorCellBuilder.of()
                                .input(zItems.AMERICIUM_PELLET)
                                .output(zItems.DEPLETED_AMERICIUM_PELLET)
                                .duration(150_000)
                                .energy(12_960)
                                .heat(1040)
                                .unlockedBy().save(c);

                ReactorCellBuilder.of()
                                .input(zItems.BERKELIUM_PELLET)
                                .output(zItems.DEPLETED_BERKELIUM_PELLET)
                                .duration(300_000)
                                .energy(38_880)
                                .heat(1700)
                                .unlockedBy().save(c);

                ReactorCellBuilder.of()
                                .input(zItems.CALIFORNIUM_PELLET)
                                .output(zItems.DEPLETED_CALIFORNIUM_PELLET)
                                .duration(750_000)
                                .energy(116_640)
                                .heat(3500)
                                .unlockedBy().save(c);

                ReactorCellBuilder.of()
                                .input(zItems.CURIUM_PELLET)
                                .output(zItems.DEPLETED_CURIUM_PELLET)
                                .duration(1_250_000)
                                .energy(349_920)
                                .heat(7500)
                                .unlockedBy().save(c);

                MelterRecipeBuilder.of()
                                .input(zItemTag.DUST_SULFUR)
                                .fluid(zFluids.SULFURIC_ACID, 250)
                                .unlockedBy()
                                .save(c);

                RockCrusherRecipeBuilder.of()
                                .fluid(Fluids.WATER, 250)
                                .input(zItemTag.DUST_URANIUM)
                                .addResult(zItems.URANIUM_FRAGMENT, 0.75f)
                                .addResult(zItems.URANIUM_FRAGMENT, 0.5f)
                                .addResult(zItems.URANIUM_FRAGMENT, 0.35f)
                                .unlockedBy()
                                .save(c);

                cross(c, zItems.URANIUM_PELLET.get(), zItems.URANIUM_FRAGMENT.get(), zItemTag.DUST_SULFUR);
                cross(c, zItems.THORIUM_PELLET.get(), zItems.THORIUM_FRAGMENT.get(), zItemTag.DUST_SULFUR);
                cross(c, zItems.PLUTONIUM_PELLET.get(), zItems.PLUTONIUM_FRAGMENT.get(), zItemTag.DUST_SULFUR);
                cross(c, zItems.NEPTUNIUM_PELLET.get(), zItems.NEPTUNIUM_FRAGMENT.get(), zItemTag.DUST_SULFUR);
                cross(c, zItems.AMERICIUM_PELLET.get(), zItems.AMERICIUM_FRAGMENT.get(), zItemTag.DUST_SULFUR);
                cross(c, zItems.BERKELIUM_PELLET.get(), zItems.BERKELIUM_FRAGMENT.get(), zItemTag.DUST_SULFUR);
                cross(c, zItems.CURIUM_PELLET.get(), zItems.CURIUM_FRAGMENT.get(), zItemTag.DUST_SULFUR);

                RockCrusherRecipeBuilder.of()
                                .fluid(zFluidTags.SULFURIC_ACID, 25)
                                .input(zItems.DEPLETED_URANIUM_PELLET)
                                .addResult(zItems.URANIUM_FRAGMENT, 0.75f)
                                .addResult(zItems.URANIUM_FRAGMENT, 0.5f)
                                .addResult(zItems.THORIUM_FRAGMENT, 0.25f)
                                .unlockedBy()
                                .save(c);

                RockCrusherRecipeBuilder.of()
                                .fluid(zFluidTags.SULFURIC_ACID, 25)
                                .input(zItems.DEPLETED_THORIUM_PELLET)
                                .addResult(zItems.THORIUM_FRAGMENT, 0.75f)
                                .addResult(zItems.THORIUM_FRAGMENT, 0.5f)
                                .addResult(zItems.PLUTONIUM_FRAGMENT, 0.25f)
                                .addResult(zItems.URANIUM_FRAGMENT, 0.05f)
                                .unlockedBy()
                                .save(c);

                RockCrusherRecipeBuilder.of()
                                .fluid(zFluidTags.SULFURIC_ACID, 25)
                                .input(zItems.DEPLETED_PLUTONIUM_PELLET)
                                .addResult(zItems.PLUTONIUM_FRAGMENT, 0.75f)
                                .addResult(zItems.PLUTONIUM_FRAGMENT, 0.5f)
                                .addResult(zItems.NEPTUNIUM_FRAGMENT, 0.25f)
                                .addResult(zItems.THORIUM_FRAGMENT, 0.05f)
                                .unlockedBy()
                                .save(c);

                RockCrusherRecipeBuilder.of()
                                .fluid(zFluidTags.SULFURIC_ACID, 25)
                                .input(zItems.DEPLETED_NEPTUNIUM_PELLET)
                                .addResult(zItems.NEPTUNIUM_FRAGMENT, 0.75f)
                                .addResult(zItems.NEPTUNIUM_FRAGMENT, 0.5f)
                                .addResult(zItems.AMERICIUM_FRAGMENT, 0.25f)
                                .addResult(zItems.PLUTONIUM_FRAGMENT, 0.05f)
                                .unlockedBy()
                                .save(c);

                RockCrusherRecipeBuilder.of()
                                .fluid(zFluidTags.SULFURIC_ACID, 25)
                                .input(zItems.DEPLETED_AMERICIUM_PELLET)
                                .addResult(zItems.AMERICIUM_FRAGMENT, 0.75f)
                                .addResult(zItems.AMERICIUM_FRAGMENT, 0.5f)
                                .addResult(zItems.BERKELIUM_FRAGMENT, 0.25f)
                                .addResult(zItems.NEPTUNIUM_FRAGMENT, 0.05f)
                                .unlockedBy()
                                .save(c);

                RockCrusherRecipeBuilder.of()
                                .fluid(zFluidTags.SULFURIC_ACID, 25)
                                .input(zItems.DEPLETED_BERKELIUM_PELLET)
                                .addResult(zItems.BERKELIUM_FRAGMENT, 0.75f)
                                .addResult(zItems.BERKELIUM_FRAGMENT, 0.5f)
                                .addResult(zItems.CURIUM_FRAGMENT, 0.25f)
                                .addResult(zItems.AMERICIUM_FRAGMENT, 0.05f)
                                .unlockedBy()
                                .save(c);

                RockCrusherRecipeBuilder.of()
                                .fluid(zFluidTags.SULFURIC_ACID, 25)
                                .input(zItems.DEPLETED_CURIUM_PELLET)
                                .addResult(zItems.CURIUM_FRAGMENT, 0.75f)
                                .addResult(zItems.CURIUM_FRAGMENT, 0.5f)
                                .addResult(zItems.BERKELIUM_FRAGMENT, 0.05f)
                                .unlockedBy()
                                .save(c);

                ShapelessRecipeBuilder.shapeless(MISC, zItems.BONE_MEAL_MIXTURE.get(), 2)
                                .requires(Tags.Items.SLIME_BALLS)
                                .requires(Items.BONE_MEAL)
                                .requires(Tags.Items.DUSTS_REDSTONE)
                                .requires(zItemTag.MIXTURE_ALTERNATIVE)
                                .unlockedBy(ID,
                                                has(zItemTag.MIXTURE_ALTERNATIVE))
                                .group(zStatic.tips.MIXTURE_TIP).save(c);

                ShapelessRecipeBuilder.shapeless(MISC, zItems.GLOWSTONE_MIXTURE.get(), 3)
                                .requires(zItems.SALT.get())
                                .requires(Items.BONE_MEAL)
                                .requires(zItemTag.DUST_LAPIS)
                                .requires(Tags.Items.DUSTS_GLOWSTONE)
                                .unlockedBy(ID,
                                                has(Items.BONE_MEAL))
                                .group(zStatic.tips.MIXTURE_TIP).save(c);

                ShapelessRecipeBuilder.shapeless(MISC, zItems.AMETHYST_MIXTURE.get(), 4)
                                .requires(zItems.STONE_PEBBLE.get())
                                .requires(Items.BONE_MEAL)
                                .requires(zItems.SALT.get())
                                .requires(zItemTag.DUST_AMETHYST)
                                .unlockedBy(ID,
                                                has(Items.BONE_MEAL))
                                .group(zStatic.tips.MIXTURE_TIP).save(c);

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

                ItemUseBuilder.of()
                                .inputItem(zItems.ENERGIZED_REDSTONE)
                                .inputBlock(zBlocks.WOODEN_TINY_CHEST)
                                .outputBlock(zBlocks.LOGIC_BOX)
                                .unlockedBy().save(c);

                ItemUseBuilder.of()
                                .inputItem(zItems.VOID_CRYSTAL)
                                .inputBlock(zBlocks.WOODEN_TINY_CHEST)
                                .outputBlock(zBlocks.VOID_BOX)
                                .unlockedBy().save(c);

                ShapedRecipeBuilder.shaped(MISC, zBlocks.BASIC_MACHINE_FRAME.get())
                                .pattern(" C ")
                                .pattern("FRF")
                                .pattern(" C ")
                                .define('C', zItems.CHIP.get())
                                .define('F', Items.IRON_NUGGET)
                                .define('R', zBlocks.ADOBE.get())
                                .unlockedBy(ID, has(zBlocks.ADOBE.get()))
                                .save(c);

                ShapedRecipeBuilder.shaped(MISC, zBlocks.BASIC_MACHINE_FRAME.get(), 2)
                                .pattern(" P ")
                                .pattern("PWP")
                                .pattern(" S ")
                                .define('S', zItems.SILICON.get())
                                .define('W', zBlocks.WROUGHT_IRON_BLOCK.get())
                                .define('P', zItems.PLASTIC.get())
                                .unlockedBy(ID, has(zItems.PLASTIC.get()))
                                .save(c, asID(zBlocks.BASIC_MACHINE_FRAME.get().asItem()));

                ShapedRecipeBuilder.shaped(MISC, zBlocks.ADVANCED_MACHINE_FRAME.get())
                                .pattern("PCP")
                                .pattern("FRF")
                                .pattern("PCP")
                                .define('P', zItemTag.PLATE_ADVANCED_ALLOY)
                                .define('C', zItems.CHIP.get())
                                .define('F', zItemTag.PLATE_COAL)
                                .define('R', zBlocks.BASIC_MACHINE_FRAME.get())
                                .unlockedBy(ID, has(zBlocks.BASIC_MACHINE_FRAME.get()))
                                .save(c);

                ShapedRecipeBuilder.shaped(MISC, zBlocks.SIMPLE_MODERATOR.get())
                                .pattern("CSC")
                                .pattern("SFS")
                                .pattern("CSC")
                                .define('F', zBlocks.WROUGHT_IRON_BLOCK.get())
                                .define('C', Items.COAL)
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

                var droplets = List.of(
                                zItems.SAP,
                                zItems.OIL,
                                zItems.GLUE);

                var fluids = List.of(
                                zFluids.SAP,
                                zFluids.OIL,
                                zFluids.GLUE);

                droplets.forEach(d -> {
                        var fluid = fluids.get(droplets.indexOf(d));
                        var bucket = fluid.getItemBucket().get();
                        ShapelessRecipeBuilder.shapeless(MISC, d.get(), 4)
                                        .requires(bucket)
                                        .unlockedBy(ID, has(bucket))
                                        .save(c);

                        MelterRecipeBuilder.of()
                                        .energy(100)
                                        .delay(40)
                                        .input(d)
                                        .fluid(fluid, 250)
                                        .unlockedBy()
                                        .save(c);

                        CasterRecipeBuilder.of()
                                        .fluid(fluid, 250)
                                        .output(d)
                                        .unlockedBy()
                                        .save(c);
                });

                ShapedRecipeBuilder.shaped(MISC, zItems.RESISTOR.get(), 4)
                                .pattern(" MN")
                                .pattern("MRM")
                                .pattern("NM ")
                                .define('N', zItems.WROUGHT_IRON_NUGGET.get())
                                .define('M', Items.LAPIS_LAZULI)
                                .define('R', Items.REDSTONE)
                                .unlockedBy(ID, has(zItems.WROUGHT_IRON_NUGGET.get()))
                                .save(c);

                ShapedRecipeBuilder.shaped(MISC, zItems.RESISTOR.get(), 8)
                                .pattern(" MN")
                                .pattern("MRM")
                                .pattern("NM ")
                                .define('N', zItems.ADVANCED_ALLOY_NUGGET.get())
                                .define('M', Items.LAPIS_LAZULI)
                                .define('R', zItems.PLASTIC.get())
                                .unlockedBy(ID, has(zItems.PLASTIC.get()))
                                .save(c, asID(zItems.RESISTOR.get()));

                ShapedRecipeBuilder.shaped(MISC, zItems.CHIP.get(), 4)
                                .pattern(" N ")
                                .pattern(" GN")
                                .pattern("Q  ")
                                .define('G', zItems.STEEL_PLATE.get())
                                .define('N', Items.IRON_NUGGET)
                                .define('Q', Items.QUARTZ)
                                .unlockedBy(ID, has(zItems.STEEL_PLATE.get()))
                                .save(c);

                ShapedRecipeBuilder.shaped(MISC, zItems.CHIP.get(), 8)
                                .pattern(" N ")
                                .pattern(" GN")
                                .pattern("Q  ")
                                .define('G', zItems.SILICON.get())
                                .define('N', zItems.ADVANCED_ALLOY_NUGGET.get())
                                .define('Q', zItems.PLASTIC.get())
                                .unlockedBy(ID, has(zItems.PLASTIC.get()))
                                .save(c, asID(zItems.CHIP.get()));

                ShapedRecipeBuilder.shaped(MISC, zItems.CONDENSER.get(), 4)
                                .pattern("N N")
                                .pattern("GMG")
                                .pattern(" I ")
                                .define('N', Items.IRON_NUGGET)
                                .define('G', zItems.STEEL_PLATE.get())
                                .define('M', zItems.CARBON_FIBER.get())
                                .define('I', Items.IRON_INGOT)
                                .unlockedBy(ID, has(zItems.STEEL_PLATE.get()))
                                .save(c);

                ShapedRecipeBuilder.shaped(MISC, zItems.CONDENSER.get(), 8)
                                .pattern("N N")
                                .pattern("GMG")
                                .pattern(" I ")
                                .define('N', zItems.ADVANCED_ALLOY_NUGGET.get())
                                .define('G', zItems.PLASTIC.get())
                                .define('M', zItems.SILICON.get())
                                .define('I', zItems.ADVANCED_ALLOY_INGOT.get())
                                .unlockedBy(ID, has(zItems.STEEL_PLATE.get()))
                                .save(c, asID(zItems.CONDENSER.get()));

                ShapedRecipeBuilder.shaped(MISC, zItems.WIRED_STONE_PLATE.get())
                                .pattern("CPC")
                                .pattern(" C ")
                                .define('C', Tags.Items.DUSTS_REDSTONE)
                                .define('P', zItems.STONE_PLATE.get())
                                .unlockedBy(ID, has(zItems.STONE_PLATE.get()))
                                .save(c);

                ShapedRecipeBuilder.shaped(MISC, zItems.STONE_CIRCUIT.get())
                                .pattern("PB")
                                .pattern("R ")
                                .define('B', zItems.METAL_BOLTS.get())
                                .define('R', zItemTag.REPEATERS)
                                .define('P', zItems.WIRED_STONE_PLATE.get())
                                .unlockedBy(ID, has(zItems.WIRED_STONE_PLATE.get()))
                                .save(c);

                ShapedRecipeBuilder.shaped(MISC, zItems.MAGNETIC_STONE_CIRCUIT.get())
                                .pattern("PB")
                                .pattern("R ")
                                .define('B', zItemTag.NUGGET_ADVANCEDALLOY)
                                .define('R', zItems.COPPER_COIL.get())
                                .define('P', zItems.STONE_CIRCUIT.get())
                                .unlockedBy(ID, has(zItems.STONE_CIRCUIT.get()))
                                .save(c);

                ShapedRecipeBuilder.shaped(MISC, zItems.RESISTIVE_STONE_CIRCUIT.get())
                                .pattern("PB")
                                .pattern("R ")
                                .define('B', zItemTag.NUGGET_ADVANCEDALLOY)
                                .define('R', zItems.SUPERCONDUCTOR.get())
                                .define('P', zItems.STONE_CIRCUIT.get())
                                .unlockedBy(ID, has(zItems.STONE_CIRCUIT.get()))
                                .save(c);

                ShapedRecipeBuilder.shaped(MISC, zItems.SUPERCONDUCTOR.get())
                                .pattern("GEG")
                                .define('G', zItemTag.FOIL_GOLD)
                                .define('E', zItems.ENERGIZED_REDSTONE.get())
                                .unlockedBy(ID, has(zItems.STONE_CIRCUIT.get()))
                                .save(c);

                ShapedRecipeBuilder.shaped(MISC, zItems.WIRED_NETHER_PLATE.get())
                                .pattern("CPC")
                                .pattern(" G ")
                                .define('G', Tags.Items.NUGGETS_GOLD)
                                .define('C', zItemTag.NUGGET_STEEL)
                                .define('P', zItems.NETHERRACK_PLATE.get())
                                .unlockedBy(ID, has(zItems.NETHERRACK_PLATE.get()))
                                .save(c);

                ShapedRecipeBuilder.shaped(MISC, zItems.NETHER_CIRCUIT.get())
                                .pattern("CPC")
                                .pattern(" G ")
                                .define('G', Tags.Items.NUGGETS_GOLD)
                                .define('C', zItemTag.NUGGET_ADVANCEDALLOY)
                                .define('P', zItems.WIRED_NETHER_PLATE.get())
                                .unlockedBy(ID, has(zItems.WIRED_NETHER_PLATE.get()))
                                .save(c);

                twoByTwoPacker(c, zItems.METAL_BOLTS.get(), zItemTag.METAL_NUGGETS);

                ShapedRecipeBuilder.shaped(MISC, zItems.LIGHT_BULB.get())
                                .define('G', Tags.Items.GLASS_BLOCKS_COLORLESS)
                                .define('N', Tags.Items.NUGGETS_IRON)
                                .define('D', Tags.Items.DUSTS_GLOWSTONE)
                                .define('S', zItems.RESISTOR.get())
                                .define('C', zItems.CONDENSER.get())
                                .define('R', Tags.Items.DUSTS_REDSTONE)
                                .pattern("GN ")
                                .pattern("NDS")
                                .pattern(" CR")
                                .unlockedBy(ID, has(Items.IRON_NUGGET))
                                .save(c);

                ShapedRecipeBuilder.shaped(MISC, zItems.NETHERRACK_PLATE.get(), 2)
                                .pattern(" T ")
                                .pattern("TPT")
                                .pattern(" T ")
                                .define('T', zItems.NETHERRACK_PEBBLE.get())
                                .define('P', zItemTag.PLATE_COAL)
                                .unlockedBy(ID, has(zItemTag.PLATE_COAL))
                                .save(c);

                ShapedRecipeBuilder.shaped(MISC, zItems.STONE_PLATE.get(), 2)
                                .pattern(" T ")
                                .pattern("TPT")
                                .pattern(" T ")
                                .define('T', zItems.STONE_PEBBLE.get())
                                .define('P', zItemTag.PLATE_COAL)
                                .unlockedBy(ID, has(zItemTag.PLATE_COAL))
                                .save(c);

                ItemUseBuilder.of()
                                .inputItem(Items.POTION)
                                .inputBlock(Blocks.DIRT)
                                .outputBlock(Blocks.MUD)
                                .outputItem(Items.GLASS_BOTTLE)
                                .isRenderOnly()
                                .unlockedBy().save(c);

                twoByTwoPacker(c, zItems.CARBON_FIBER.get(), zItemTag.DUST_COAL);
                twoByTwoPacker(c, Items.RAW_IRON, zItems.TINY_IRON_DUST.get(), ID + ":raw_iron_from_tiny_iron_dust");
                twoByTwoPacker(c, MISC, zItems.CARBON_PLATE.get(), zItems.CARBON_FIBER.get());

                CompressorRecipeBuilder.of()
                                .input(zItemTag.DUST_COAL)
                                .delay(80)
                                .output(zItems.CARBON_FIBER)
                                .unlockedBy()
                                .save(c);

                CompressorRecipeBuilder.of()
                                .input(zItems.CARBON_FIBER)
                                .delay(80)
                                .catalyst(zItemTag.PLATE_STEEL)
                                .consumeCatalyst()
                                .output(zItems.CARBON_PLATE, 2)
                                .unlockedBy()
                                .save(c);

                nodeRecipe(c, zBlocks.ITEM_TRANSFER.get(), Blocks.CHEST);
                nodeRecipe(c, zBlocks.ITEM_PROVIDER.get(), Items.IRON_PICKAXE);
                nodeRecipe(c, zBlocks.ITEM_RETRIEVAL.get(), Blocks.HOPPER);

                stonecutter(c, zBlocks.ITEM_RETRIEVAL.get(), zItemTag.NODES_RETRIEVAL);
                stonecutter(c, zBlocks.FLUID_RETRIEVAL.get(), zItemTag.NODES_RETRIEVAL);
                stonecutter(c, zBlocks.ENERGY_RETRIEVAL.get(), zItemTag.NODES_RETRIEVAL);

                stonecutter(c, zBlocks.ITEM_TRANSFER.get(), zItemTag.NODES_TRANSFER);
                stonecutter(c, zBlocks.FLUID_TRANSFER.get(), zItemTag.NODES_TRANSFER);
                stonecutter(c, zBlocks.ENERGY_TRANSFER.get(), zItemTag.NODES_TRANSFER);

                stonecutter(c, zBlocks.ITEM_PROVIDER.get(), zItemTag.NODES_PROVIDER);
                stonecutter(c, zBlocks.FLUID_PROVIDER.get(), zItemTag.NODES_PROVIDER);

                ShapedRecipeBuilder.shaped(MISC, zBlocks.PIPE.get().asItem(), 16)
                                .pattern("SSS")
                                .pattern("GRG")
                                .pattern("SSS")
                                .define('R', Tags.Items.DUSTS_REDSTONE)
                                .define('G', Tags.Items.GLASS_BLOCKS_COLORLESS)
                                .define('S', zItemTag.STONE_SLABS)
                                .unlockedBy(ID, has(Items.REDSTONE))
                                .group(zStatic.PipeStuff.pipe).save(c);

                ItemProviderBuilder.of()
                                .core(Blocks.COBBLESTONE)
                                .left(Blocks.LAVA)
                                .right(Blocks.WATER)
                                .output(Items.COBBLESTONE)
                                .unlockedBy().save(c);

                ItemProviderBuilder.of()
                                .core(Blocks.BASALT)
                                .left(Blocks.LAVA)
                                .right(Blocks.BLUE_ICE)
                                .below(Blocks.SOUL_SOIL)
                                .output(Items.BASALT)
                                .unlockedBy().save(c);

                FluidProviderBuilder.of()
                                .core(Blocks.COBBLESTONE)
                                .left(Blocks.WATER)
                                .right(Blocks.WATER)
                                .output(Fluids.WATER.getSource())
                                .unlockedBy().save(c);

                FluidProviderBuilder.of()
                                .core(Blocks.OBSIDIAN)
                                .left(Blocks.LAVA)
                                .right(Blocks.LAVA)
                                .output(Fluids.LAVA.getSource(), 1)
                                .unlockedBy().save(c);

                ShapelessRecipeBuilder.shapeless(MISC, zItems.SALTY_ROTTEN_FLESH.get(), 2)
                                .requires(zItems.SALT.get())
                                .requires(Items.ROTTEN_FLESH)
                                .requires(Items.ROTTEN_FLESH)
                                .unlockedBy(ID, has(Items.ROTTEN_FLESH))
                                .save(c);

                DryingRackBuilder.of()
                                .input(zItems.SALTY_ROTTEN_FLESH)
                                .output(Items.LEATHER)
                                .unlockedBy()
                                .save(c);

                brickDryingRecipe(c, Items.CLAY_BALL, zBlocks.CLAY_BRICK.get(),
                                Items.BRICK);

                brickDryingRecipe(c, zItems.PACKED_MUD_BALL.get(), zBlocks.PACKED_MUD_BRICK.get(),
                                zItems.PACKED_MUD_BRICK.get());

                brickDryingRecipe(c, zItems.FIRECLAY_BALL.get(), zBlocks.FIRECLAY_BRICK.get(),
                                zItems.FIRECLAY_BRICK.get());

                brickDryingRecipe(c, zItems.CLAY_MIXTURE_BALL.get(), zBlocks.BLAST_BRICK.get(),
                                zItems.BLAST_BRICK.get());

                ShapelessRecipeBuilder.shapeless(MISC, zItems.ADOBE_BALL.get(), 3)
                                .requires(zItems.PACKED_MUD_BALL.get(), 2)
                                .requires(Items.CLAY_BALL)
                                .unlockedBy(ID, has(Items.CLAY_BALL))
                                .save(c);

                ShapedRecipeBuilder.shaped(MISC, Items.MUD_BRICKS, 1).define('#', zItems.PACKED_MUD_BRICK.get())
                                .pattern("##").pattern("##").unlockedBy(getHasName(zItems.PACKED_MUD_BRICK.get()),
                                                has(zItems.PACKED_MUD_BRICK.get()))
                                .save(c, ID + ":" + getConversionRecipeName(Items.MUD_BRICKS,
                                                zItems.PACKED_MUD_BRICK.get()));

                packUnpack(c, zItems.MUD_BALL.get(), Items.MUD, true);
                packUnpack(c, zItems.PACKED_MUD_BALL.get(), Items.PACKED_MUD, true);

                ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, zItems.PACKED_MUD_BALL.get(), 4)
                                .requires(zItems.MUD_BALL.get(), 4).requires(Items.WHEAT)
                                .unlockedBy(ID, has(zItems.MUD_BALL.get()))
                                .save(c, ID + ":" + getConversionRecipeName(zItems.PACKED_MUD_BALL.get(),
                                                zItems.MUD_BALL.get()));

                ShapelessRecipeBuilder.shapeless(MISC, zItems.FIRECLAY_BALL.get(), 2)
                                .requires(zItems.PACKED_MUD_BALL.get())
                                .requires(zItemTag.DUST_QUARTZ)
                                .unlockedBy(ID, has(zItemTag.DUST_QUARTZ))
                                .save(c);

                ShapelessRecipeBuilder.shapeless(MISC, zItems.CLAY_MIXTURE_BALL.get(), 3)
                                .requires(Items.CLAY_BALL, 2)
                                .requires(zItems.FIRECLAY_BALL.get())
                                .unlockedBy(ID, has(Items.CLAY_BALL))
                                .save(c);

                ShapedRecipeBuilder.shaped(MISC, zBlocks.BLAST_BRICKS.get(), 4)
                                .define('#', zItems.BLAST_BRICK.get())
                                .pattern("##")
                                .pattern("##")
                                .unlockedBy(getHasName(zItems.BLAST_BRICK.get()),
                                                has(zItems.BLAST_BRICK.get()))
                                .save(c);

                ShapedRecipeBuilder.shaped(MISC, zBlocks.FIRECLAY_BRICKS.getBricks().get(), 4)
                                .define('#', zItems.FIRECLAY_BRICK.get())
                                .pattern("##")
                                .pattern("##")
                                .unlockedBy(getHasName(zItems.FIRECLAY_BRICK.get()),
                                                has(zItems.FIRECLAY_BRICK.get()))
                                .save(c);

                SimpleCookingRecipeBuilder
                                .smelting(Ingredient.of(zBlocks.FIRECLAY_BRICKS.getBricks().get().asItem()),
                                                RecipeCategory.BUILDING_BLOCKS,
                                                zBlocks.FIRECLAY_BRICK_CRACKED.getBricks().get().asItem(), 0.1F, 200)
                                .unlockedBy(getHasName(zBlocks.FIRECLAY_BRICKS.getBricks().get().asItem()),
                                                has(zBlocks.FIRECLAY_BRICKS.getBricks().get().asItem()))
                                .save(c, ID + ":"
                                                + getConversionRecipeName(
                                                                zBlocks.FIRECLAY_BRICK_CRACKED.getBricks().get()
                                                                                .asItem(),
                                                                zBlocks.FIRECLAY_BRICKS.getBricks().get().asItem()));

                ShapelessRecipeBuilder.shapeless(MISC, zBlocks.FIRECLAY_BRICK_MOSSY.getBricks().get().asItem())
                                .requires(zBlocks.FIRECLAY_BRICKS.getBricks().get().asItem())
                                .requires(Items.MOSS_BLOCK)
                                .unlockedBy(ID, has(Items.MOSS_BLOCK))
                                .save(c, ID + ":"
                                                + getConversionRecipeName(
                                                                zBlocks.FIRECLAY_BRICK_MOSSY.getBricks().get(),
                                                                zBlocks.FIRECLAY_BRICKS.getBricks().get().asItem())
                                                + "_from_mossblock");

                ShapelessRecipeBuilder.shapeless(MISC, zBlocks.FIRECLAY_BRICK_MOSSY.getBricks().get().asItem())
                                .requires(zBlocks.FIRECLAY_BRICKS.getBricks().get().asItem())
                                .requires(Items.VINE)
                                .unlockedBy(ID, has(Items.VINE))
                                .save(c, ID + ":"
                                                + getConversionRecipeName(
                                                                zBlocks.FIRECLAY_BRICK_MOSSY.getBricks().get(),
                                                                zBlocks.FIRECLAY_BRICKS.getBricks().get().asItem())
                                                + "_from_vines");

                List.of(zBlocks.FIRECLAY_BRICKS, zBlocks.FIRECLAY_BRICK_CRACKED, zBlocks.FIRECLAY_BRICK_MOSSY)
                                .forEach(s -> {

                                        pillar(c, s.getColumn().get(), s.getBricks().get());

                                        tiles(c, s.getTiles().get(), s.getBricks().get());

                                        stair(s.getStairBricks().get(), s.getBricks().get(), c);
                                        stair(s.getStairTiles().get(), s.getTiles().get(), c);

                                        slab(s.getSlabBricks().get(), s.getBricks().get(), c);

                                        slab(s.getSlabTiles().get(), s.getTiles().get(), c);

                                        stonecutter(c, s.getSlabBricks().get(), s.getBricks().get(), 2);
                                        stonecutter(c, s.getStairBricks().get(), s.getBricks().get());
                                        stonecutter(c, s.getSlabTiles().get(), s.getTiles().get(), 2);
                                        stonecutter(c, s.getStairTiles().get(), s.getTiles().get());

                                        stonecutter(c, s.getColumn().get(), s.getBricks().get());
                                        stonecutter(c, s.getTiles().get(), s.getBricks().get());

                                });

                smeltingResultFromBase(c, zBlocks.SMOOTH_ADOBE.get(), zBlocks.ADOBE.get());

                stairBuilder(zBlocks.IRON_WOOD.getStair().get().asItem(),
                                Ingredient.of(zBlocks.IRON_WOOD.getPlanks().get().asItem()))
                                .unlockedBy(ID, has(zBlocks.IRON_WOOD.getPlanks().get().asItem()))
                                .group(zStatic.DecorativeBlocks.ironwood).save(c);

                slabBuilder(BUILDING_BLOCKS, zBlocks.IRON_WOOD.getSlab().get().asItem(),
                                Ingredient.of(zBlocks.IRON_WOOD.getPlanks().get().asItem()))
                                .unlockedBy(ID, has(zBlocks.IRON_WOOD.getPlanks().get().asItem()))
                                .group(zStatic.DecorativeBlocks.ironwood).save(c);

                planksFromLog(c, zBlocks.IRON_WOOD.getPlanks().get().asItem(), zItemTag.IRONWOOD_LOGS, 4);

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
                                .unlockedBy(ID, has(Items.HONEYCOMB))
                                .group(zStatic.DecorativeBlocks.waxed_planks).save(c);

                ShapedRecipeBuilder.shaped(MISC, zBlocks.ADOBE.get(), 2)
                                .pattern("AAA")
                                .pattern("ABA")
                                .pattern("AAA")
                                .define('A', zItems.ADOBE_BALL.get())
                                .define('B', Items.DIORITE)
                                .unlockedBy(ID, has(zItems.ADOBE_BALL.get()))
                                .group(zStatic.DecorativeBlocks.adobe).save(c);

                stairBuilder(zBlocks.WAXED_PLANKS_STAIR.get(), Ingredient.of(zBlocks.WAXED_PLANKS.get()))
                                .unlockedBy(ID, has(zBlocks.WAXED_PLANKS.get()))
                                .group(zStatic.DecorativeBlocks.waxed_planks).save(c);

                slabBuilder(BUILDING_BLOCKS, zBlocks.WAXED_PLANKS_SLAB.get(),
                                Ingredient.of(zBlocks.WAXED_PLANKS.get()))
                                .unlockedBy(ID, has(zBlocks.WAXED_PLANKS.get()))
                                .group(zStatic.DecorativeBlocks.waxed_planks).save(c);

                ItemUseBuilder.of()
                                .inputItem(zItems.AZALEA_SEEDS)
                                .inputBlock(Blocks.FLOWER_POT)
                                .outputBlock(zBlocks.AZALEA.get().defaultBlockState().setValue(azalea.AGE, 0))
                                .canBeDisabled()
                                .unlockedBy().save(c);

                ShapedRecipeBuilder.shaped(MISC, Items.SLIME_BALL, 4)
                                .pattern(" R ")
                                .pattern("RHR")
                                .pattern(" R ")
                                .define('R', zItems.RICE_SEED.get())
                                .define('H', Items.HONEYCOMB)
                                .unlockedBy(ID, has(zItems.RICE_SEED.get()))
                                .save(c, x.rl(
                                                Items.SLIME_BALL.getDescriptionId()
                                                                .replace("item.minecraft.", "")
                                                                + "_from_rice"));

                ShapelessRecipeBuilder.shapeless(MISC, Items.BLUE_DYE, 4)
                                .requires(zItems.BLUE_CUP_MUSHROOM.get())
                                .unlockedBy(ID, has(zItems.BLUE_CUP_MUSHROOM.get()))
                                .group(zStatic.Plants.BLUE_CUP_MUSHROOM).save(c, x.rl(
                                                Items.BLUE_DYE.getDescriptionId()
                                                                .replace("item.minecraft.", "")
                                                                + "_from_mushrooms"));

                ShapelessRecipeBuilder.shapeless(MISC, Items.BROWN_DYE, 4)
                                .requires(zItems.VIOLET_WEBCAP_MUSHROOM.get())
                                .unlockedBy(ID, has(zItems.VIOLET_WEBCAP_MUSHROOM.get()))
                                .group(zStatic.Plants.VIOLET_WEBCAP_MUSHROOM).save(c, x.rl(
                                                Items.BROWN_DYE.getDescriptionId()
                                                                .replace("item.minecraft.", "")
                                                                + "_from_mushrooms"));

                ShapelessRecipeBuilder.shapeless(MISC, zItems.AZALEA_SEEDS.get(), 3)
                                .requires(zItemTag.AZALEA_BUSHES)
                                .unlockedBy(ID, has(Items.FLOWERING_AZALEA))
                                .group(zStatic.Items.Azalea.seeds).save(c);

                ShapelessRecipeBuilder.shapeless(MISC, Items.STRING, 3)
                                .requires(zItems.COTTON.get())
                                .unlockedBy(ID, has(zItems.COTTON.get()))
                                .group(zStatic.Plants.COTTON).save(c, x.rl(
                                                Items.STRING.getDescriptionId()
                                                                .replace("item.minecraft.", "")
                                                                + "_from_cotton"));

                ShapedRecipeBuilder.shaped(MISC, Items.HANGING_ROOTS)
                                .pattern("RR")
                                .define('R', zItems.SMALL_AZALEA_ROOTS.get())
                                .unlockedBy(ID, has(zItems.SMALL_AZALEA_ROOTS.get()))
                                .group(zStatic.Items.Azalea.roots).save(c, x.rl(
                                                Items.HANGING_ROOTS.getDescriptionId()
                                                                .replace("block.minecraft.", "")
                                                                + "_from_small_azalea"));

                ShapedRecipeBuilder.shaped(MISC, Items.FLOWERING_AZALEA_LEAVES)
                                .pattern("LL")
                                .pattern("LL")
                                .define('L', zItems.SMALL_AZALEA_LEAF.get())
                                .unlockedBy(ID, has(zItems.SMALL_AZALEA_LEAF.get()))
                                .group(zStatic.Items.Azalea.leaf).save(c, x.rl(
                                                Items.FLOWERING_AZALEA_LEAVES.getDescriptionId()
                                                                .replace("block.minecraft.", "")
                                                                + "_from_small_azalea"));

                ShapelessRecipeBuilder.shapeless(MISC, Items.FLOWERING_AZALEA)
                                .requires(zItems.SMALL_AZALEA_LEAF.get())
                                .requires(Items.AZALEA)
                                .unlockedBy(ID, has(Items.AZALEA))
                                .group(zStatic.Items.Azalea.leaf).save(c, x.rl(
                                                Items.FLOWERING_AZALEA.getDescriptionId()
                                                                .replace("block.minecraft.", "")
                                                                + "_from_small_azalea"));

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

                ShapedRecipeBuilder.shaped(MISC, zBlocks.SIMPLE_TANK.get())
                                .define('#', Tags.Items.NUGGETS_IRON)
                                .define('G', Tags.Items.GLASS_BLOCKS_COLORLESS)
                                .pattern(" # ")
                                .pattern("#G#")
                                .pattern(" # ")
                                .unlockedBy(getHasName(Items.IRON_NUGGET),
                                                has(Items.IRON_NUGGET))
                                .save(c);

                ShapedRecipeBuilder.shaped(MISC, zBlocks.CHOPPER.get())
                                .pattern(" H ")
                                .pattern("AFA")
                                .pattern("WWW")
                                .define('W', zItems.WROUGHT_IRON_INGOT.get())
                                .define('H', Items.HOPPER)
                                .define('F', zBlocks.FIRECLAY_BRICKS.getBricks().get())
                                .define('A', Items.IRON_AXE)
                                .unlockedBy(ID, has(zItems.PLASTIC.get()))
                                .save(c);

                ShapedRecipeBuilder.shaped(MISC, zBlocks.SPRINKLER.get())
                                .pattern("WWW")
                                .pattern("RBR")
                                .pattern("AIA")
                                .define('A', zBlocks.ADOBE.get())
                                .define('W', zBlocks.WAXED_PLANKS.get())
                                .define('R', Items.REDSTONE)
                                .define('I', Items.IRON_INGOT)
                                .define('B', zItems.BLUE_BATTERY.get())
                                .unlockedBy(ID, has(
                                                zItems.BLUE_BATTERY.get()))
                                .group(zStatic.Blocks.sprinkler).save(c);

                ShapedRecipeBuilder.shaped(MISC, zBlocks.SOLAR_PANEL.get())
                                .pattern("LLL")
                                .pattern("FRF")
                                .define('L', Items.LAPIS_LAZULI)
                                .define('F', zItems.RESISTOR.get())
                                .define('R', zItems.BLUE_BATTERY.get())
                                .unlockedBy(ID, has(zItems.BLUE_BATTERY.get()))
                                .save(c);

                ShapedRecipeBuilder.shaped(MISC, zBlocks.HARVESTER.get())
                                .pattern(" R ")
                                .pattern("AMH")
                                .pattern(" C ")
                                .define('C', zItems.BLUE_BATTERY.get())
                                .define('M', zBlocks.BASIC_MACHINE_FRAME.get())
                                .define('H', Items.IRON_HOE)
                                .define('A', Items.IRON_AXE)
                                .define('R', zItems.RESISTOR.get())
                                .unlockedBy(ID, has(
                                                zBlocks.BASIC_MACHINE_FRAME.get()))
                                .save(c);

                ShapedRecipeBuilder.shaped(MISC, zBlocks.SIMPLE_COBBLE_GEN.get().asItem())
                                .pattern("PPP")
                                .pattern("LNW")
                                .pattern("PPP")
                                .define('P', zItemTag.PLATE_WROUGHT_IRON)
                                .define('L', Items.LAVA_BUCKET)
                                .define('W', Items.WATER_BUCKET)
                                .define('N', zBlocks.ITEM_PROVIDER.get())
                                .unlockedBy(ID, has(zBlocks.ITEM_PROVIDER.get()))
                                .save(c);

                ShapedRecipeBuilder.shaped(MISC, zBlocks.SIMPLE_WATER_GEN.get().asItem())
                                .pattern("PPP")
                                .pattern("WNW")
                                .pattern("PPP")
                                .define('P', zItemTag.PLATE_WROUGHT_IRON)
                                .define('W', Items.WATER_BUCKET)
                                .define('N', zBlocks.FLUID_PROVIDER.get())
                                .unlockedBy(ID, has(zBlocks.FLUID_PROVIDER.get()))
                                .save(c);

                ShapedRecipeBuilder.shaped(MISC, zBlocks.ADVANCED_COBBLE_GEN.get().asItem())
                                .pattern("AAA")
                                .pattern("ABA")
                                .pattern("AAA")
                                .define('A', zBlocks.SIMPLE_COBBLE_GEN.get())
                                .define('B', zBlocks.BASIC_MACHINE_FRAME.get())
                                .unlockedBy(ID, has(zBlocks.SIMPLE_COBBLE_GEN.get()))
                                .save(c);

                ShapedRecipeBuilder.shaped(MISC, zBlocks.ADVANCED_WATER_GEN.get().asItem())
                                .pattern("AAA")
                                .pattern("ABA")
                                .pattern("AAA")
                                .define('A', zBlocks.SIMPLE_WATER_GEN.get())
                                .define('B', zBlocks.BASIC_MACHINE_FRAME.get())
                                .unlockedBy(ID, has(zBlocks.SIMPLE_WATER_GEN.get()))
                                .save(c);

                ShapedRecipeBuilder.shaped(MISC, zBlocks.ELITE_COBBLE_GEN.get().asItem())
                                .pattern("AAA")
                                .pattern("ABA")
                                .pattern("AAA")
                                .define('A', zBlocks.ADVANCED_COBBLE_GEN.get())
                                .define('B', zBlocks.ADVANCED_MACHINE_FRAME.get())
                                .unlockedBy(ID, has(zBlocks.ADVANCED_COBBLE_GEN.get()))
                                .save(c);

                ShapedRecipeBuilder.shaped(MISC, zBlocks.ELITE_WATER_GEN.get().asItem())
                                .pattern("AAA")
                                .pattern("ABA")
                                .pattern("AAA")
                                .define('A', zBlocks.ADVANCED_WATER_GEN.get())
                                .define('B', zBlocks.ADVANCED_MACHINE_FRAME.get())
                                .unlockedBy(ID, has(zBlocks.ADVANCED_WATER_GEN.get()))
                                .save(c);

                ShapedRecipeBuilder.shaped(MISC, zBlocks.COOLER_BASE.get(), 4)
                                .pattern("IPI")
                                .pattern("P P")
                                .pattern("IPI")
                                .define('P', zItems.IRON_PLATE.get())
                                .define('I', Items.IRON_INGOT)
                                .unlockedBy(ID, has(zItems.IRON_PLATE.get()))
                                .save(c);
                ShapedRecipeBuilder.shaped(MISC, zBlocks.REACTOR_CONTROLLER.get().asItem())
                                .pattern("PTP")
                                .pattern("ACA")
                                .pattern("PSP")
                                .define('C', zBlocks.ADVANCED_MACHINE_FRAME.get())
                                .define('P', zItemTag.PLATE_COAL)
                                .define('A', zItemTag.PLATE_AQUAMARINE)
                                .define('T', zItems.MAGNETIC_STONE_CIRCUIT.get())
                                .define('S', zItems.NETHER_CIRCUIT.get())
                                .unlockedBy(ID, has(zBlocks.ADVANCED_MACHINE_FRAME.get()))
                                .group(zStatic.ReactorStuff.controller).save(c);

                ShapedRecipeBuilder.shaped(MISC, zBlocks.REACTOR_FUEL_CELL.get().asItem(), 2)
                                .pattern(" C ")
                                .pattern("RPR")
                                .pattern(" A ")
                                .define('C', zItems.MAGNETIC_STONE_CIRCUIT.get())
                                .define('R', zItems.RESISTIVE_STONE_CIRCUIT.get())
                                .define('P', zBlocks.ADVANCED_MACHINE_FRAME.get())
                                .define('A', zItemTag.PLATE_AQUAMARINE)
                                .unlockedBy(ID, has(zBlocks.ADVANCED_MACHINE_FRAME.get()))
                                .group(zStatic.ReactorStuff.fuel_cell).save(c);

                ShapedRecipeBuilder.shaped(MISC, zMachines.CASTING_FACTORY.block().get())
                                .pattern("CRC")
                                .pattern("GMG")
                                .pattern(" B ")
                                .define('R', zItems.RESISTOR.get())
                                .define('G', zItems.STEEL_GEAR.get())
                                .define('C', zItems.CHIP.get())
                                .define('B', zItems.BLUE_BATTERY.get())
                                .define('M', zBlocks.BASIC_MACHINE_FRAME.get())
                                .unlockedBy(ID, has(zBlocks.BASIC_MACHINE_FRAME.get()))
                                .save(c);

                ShapedRecipeBuilder.shaped(MISC, zMachines.ROCK_CRUSHER.block().get())
                                .pattern("RLR")
                                .pattern("MFM")
                                .pattern(" B ")
                                .define('M', zMachines.MACERATOR.item().get())
                                .define('F', zBlocks.BASIC_MACHINE_FRAME.get())
                                .define('L', zItems.LIGHT_BULB.get())
                                .define('R', zItems.RESISTOR.get())
                                .define('B', zItems.BLUE_BATTERY.get())
                                .unlockedBy(ID, has(zMachines.MACERATOR.item().get()))
                                .save(c);

                ShapedRecipeBuilder.shaped(MISC, zMachines.EXTRACTOR.block().get())
                                .pattern("RFR")
                                .pattern("EME")
                                .pattern(" B ")
                                .define('E', zItems.STEEL_ELECTRON_TUBE.get())
                                .define('R', zItems.RESISTOR.get())
                                .define('F', zItems.MAGNETIC_STONE_CIRCUIT.get())
                                .define('B', zItems.BLUE_BATTERY.get())
                                .define('M', zBlocks.BASIC_MACHINE_FRAME.get())
                                .unlockedBy(ID, has(zBlocks.BASIC_MACHINE_FRAME.get()))
                                .save(c);

                ShapedRecipeBuilder.shaped(MISC, zMachines.MELTER.block().get())
                                .pattern("CRC")
                                .pattern("DMD")
                                .pattern(" B ")
                                .define('R', zItems.RESISTIVE_STONE_CIRCUIT.get())
                                .define('C', zItems.CHIP.get())
                                .define('D', zItems.CONDENSER.get())
                                .define('B', zItems.BLUE_BATTERY.get())
                                .define('M', zBlocks.BASIC_MACHINE_FRAME.get())
                                .unlockedBy(ID, has(zBlocks.BASIC_MACHINE_FRAME.get()))
                                .save(c);

                ShapedRecipeBuilder.shaped(MISC, zMachines.MACERATOR.block().get())
                                .pattern("FFF")
                                .pattern("RMR")
                                .pattern(" B ")
                                .define('F', Items.FLINT)
                                .define('R', zItems.RESISTOR.get())
                                .define('B', zItems.BLUE_BATTERY.get())
                                .define('M', zBlocks.BASIC_MACHINE_FRAME.get())
                                .unlockedBy(ID, has(zBlocks.BASIC_MACHINE_FRAME.get()))
                                .save(c);

                ShapedRecipeBuilder.shaped(MISC, zMachines.COMPRESSOR.block().get())
                                .pattern("CPC")
                                .pattern("RMR")
                                .pattern(" B ")
                                .define('C', zItems.CHIP.get())
                                .define('R', zItems.RESISTOR.get())
                                .define('P', Items.PISTON)
                                .define('B', zItems.BLUE_BATTERY.get())
                                .define('M', zBlocks.BASIC_MACHINE_FRAME.get())
                                .unlockedBy(ID, has(zBlocks.BASIC_MACHINE_FRAME.get()))
                                .save(c);

                ShapedRecipeBuilder.shaped(MISC, zMachines.ALLOY_SMELTER.block().get())
                                .pattern("FCF")
                                .pattern("RMR")
                                .pattern(" B ")
                                .define('F', Items.FURNACE)
                                .define('R', zItems.RESISTOR.get())
                                .define('C', zItems.CONDENSER.get())
                                .define('B', zItems.BLUE_BATTERY.get())
                                .define('M', zBlocks.BASIC_MACHINE_FRAME.get())
                                .unlockedBy(ID, has(zBlocks.BASIC_MACHINE_FRAME.get()))
                                .save(c);

                ShapedRecipeBuilder.shaped(MISC, zMachines.ELECTRIC_FURNACE.block().get())
                                .pattern("CFC")
                                .pattern("RMR")
                                .pattern(" B ")
                                .define('F', Items.FURNACE)
                                .define('R', zItems.RESISTOR.get())
                                .define('C', zItems.CHIP.get())
                                .define('B', zItems.BLUE_BATTERY.get())
                                .define('M', zBlocks.BASIC_MACHINE_FRAME.get())
                                .unlockedBy(ID, has(zBlocks.BASIC_MACHINE_FRAME.get()))
                                .save(c);

                ShapedRecipeBuilder.shaped(MISC, zBlocks.FUEL_TANK.get())
                                .define('I', zItems.WROUGHT_IRON_INGOT.get())
                                .define('#', zItems.WROUGHT_IRON_NUGGET.get())
                                .define('G', zBlocks.SIMPLE_TANK.get())
                                .pattern("I#I")
                                .pattern("#G#")
                                .pattern("I#I")
                                .unlockedBy(getHasName(zBlocks.SIMPLE_TANK.get()),
                                                has(zBlocks.SIMPLE_TANK.get()))
                                .save(c);

                ShapedRecipeBuilder.shaped(MISC, zBlocks.CASTING_TABLE.get().asItem())
                                .pattern("III")
                                .pattern("I I")
                                .pattern("I I")
                                .define('I', zItems.WROUGHT_IRON_INGOT.get())
                                .unlockedBy(ID, has(zItems.WROUGHT_IRON_INGOT.get()))
                                .save(c);

                ShapedRecipeBuilder.shaped(MISC, zBlocks.FAUCET.get().asItem(), 3)
                                .pattern("I I")
                                .pattern(" I ")
                                .define('I', zItems.WROUGHT_IRON_INGOT.get())
                                .unlockedBy(ID, has(zItems.WROUGHT_IRON_INGOT.get()))
                                .save(c);

                ShapedRecipeBuilder.shaped(MISC, zBlocks.FOUNDRY.get().asItem())
                                .pattern("III")
                                .pattern("IFI")
                                .pattern("SSS")
                                .define('F', zBlocks.FUEL_TANK.get())
                                .define('S', Items.SMOOTH_STONE)
                                .define('I', zItems.WROUGHT_IRON_INGOT.get())
                                .unlockedBy(ID, has(zBlocks.FUEL_TANK.get()))
                                .save(c);

                ShapedRecipeBuilder.shaped(MISC, zBlocks.CRUSHING_TUB.get().asItem())
                                .pattern("# #")
                                .pattern("###")
                                .define('#', ItemTags.WOODEN_SLABS)
                                .unlockedBy(ID, has(ItemTags.WOODEN_SLABS))
                                .save(c);

                ShapedRecipeBuilder.shaped(MISC, zBlocks.EVAPORATION_BASIN.get().asItem())
                                .pattern("# #")
                                .pattern("###")
                                .define('#', Items.TERRACOTTA)
                                .unlockedBy(ID, has(Items.TERRACOTTA))
                                .save(c);

                var logs = List.of(Items.ACACIA_LOG, Items.BAMBOO_BLOCK,
                                Items.BIRCH_LOG, Items.CHERRY_LOG, Items.CRIMSON_STEM,
                                Items.DARK_OAK_LOG, Items.JUNGLE_LOG, Items.MANGROVE_LOG,
                                Items.OAK_LOG, Items.SPRUCE_LOG, Items.WARPED_STEM);

                zStatic.ALL_DRYING_RACKS.forEach(a -> ShapedRecipeBuilder.shaped(MISC, a.get(), 4)
                                .pattern(" L ")
                                .pattern("C C")
                                .pattern("LLL")
                                .define('C', Items.CHAIN)
                                .define('L', logs.get(zStatic.ALL_DRYING_RACKS.indexOf(a)))
                                .unlockedBy(ID, has(Items.CHAIN))
                                .save(c));

                ShapedRecipeBuilder.shaped(MISC, zBlocks.QUERN.get())
                                .pattern("TS ")
                                .pattern("SWS")
                                .define('W', zItems.WOODEN_GEAR.get())
                                .define('S', Items.STONE_SLAB)
                                .define('T', Items.STICK)
                                .unlockedBy(ID, has(zItems.WOODEN_GEAR.get()))
                                .save(c);

                UrnRitualBuilder.of()
                                .add(Tags.Items.GEMS_DIAMOND)
                                .add(zItems.MAGIC_DUST)
                                .output(zItems.VOID_CRYSTAL.get())
                                .unlockedBy().save(c);

                ShapedRecipeBuilder.shaped(MISC, zBlocks.URN.get())
                                .pattern("B B")
                                .pattern("BHB")
                                .pattern("BBB")
                                .define('H', zItemTag.MOB_DROP)
                                .define('B', Tags.Items.BRICKS_NORMAL)
                                .unlockedBy(ID, has(Items.BRICK))
                                .save(c);

                UrnRitualBuilder.of()
                                .add(Items.BLAZE_POWDER)
                                .add(zItemTag.COAL_LIKE)
                                .output(zItems.INFERNAL_EMBER, 2)
                                .unlockedBy().save(c);

                ShapedRecipeBuilder.shaped(MISC, zItems.FLOPPY_DISK.get(), 2)
                                .pattern(" P ")
                                .pattern("CNF")
                                .pattern(" P ")
                                .define('N', zItems.ADVANCED_ALLOY_NUGGET.get())
                                .define('F', zItems.IRON_FOIL.get())
                                .define('C', zItems.COPPER_COIL.get())
                                .define('P', zItems.PLASTIC.get())
                                .unlockedBy(ID, has(zItems.PLASTIC.get()))
                                .save(c);

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

                ShapedRecipeBuilder.shaped(MISC, zItems.CHISEL.get())
                                .pattern("  N")
                                .pattern(" I ")
                                .pattern("S  ")
                                .define('N', Items.IRON_NUGGET)
                                .define('S', Items.STICK)
                                .define('I', Items.IRON_INGOT)
                                .unlockedBy(ID,
                                                has(Items.IRON_INGOT))
                                .group(zStatic.Items.chisel).save(c);

                ShapedRecipeBuilder.shaped(MISC, zItems.CAKE_STICK.get())
                                .pattern(" C")
                                .pattern("S ")
                                .define('C', Items.CAKE)
                                .define('S', Items.STICK)
                                .unlockedBy(getHasName(Items.CAKE),
                                                has(Items.CAKE))
                                .save(c);

                ShapedRecipeBuilder.shaped(MISC, zBlocks.HEALER.get().asItem())
                                .pattern("RWR")
                                .pattern("WSW")
                                .pattern("RWR")
                                .define('R', Items.RED_CONCRETE)
                                .define('W', Items.WHITE_CONCRETE)
                                .define('S', Items.NETHER_STAR)
                                .unlockedBy(ID, has(Items.NETHER_STAR))
                                .group(zStatic.Blocks.healer).save(c);

                ShapedRecipeBuilder.shaped(MISC, zStatic.MachineUpgrades.TemplateUpgrades.ENERGY)
                                .pattern(" B ")
                                .pattern("CSC")
                                .pattern(" R ")
                                .define('S', zItemTag.PLATE_STEEL)
                                .define('R', zItems.RESISTOR.get())
                                .define('B', zItems.ENERGIZED_REDSTONE.get())
                                .define('C', zItems.CHIP.get())
                                .unlockedBy(ID, has(zItemTag.PLATE_STEEL))
                                .save(c);

                ShapedRecipeBuilder.shaped(MISC, zStatic.MachineUpgrades.TemplateUpgrades.SPEED)
                                .pattern(" B ")
                                .pattern("CSC")
                                .pattern(" R ")
                                .define('S', zItemTag.PLATE_STEEL)
                                .define('R', zItems.CONDENSER.get())
                                .define('B', zItems.AQUAMARINE.get())
                                .define('C', zItems.CHIP.get())
                                .unlockedBy(ID, has(zItemTag.PLATE_STEEL))
                                .save(c);

                ShapedRecipeBuilder.shaped(MISC, zStatic.MachineUpgrades.TemplateUpgrades.LUCK)
                                .pattern(" B ")
                                .pattern("CSC")
                                .pattern(" R ")
                                .define('S', zItemTag.PLATE_STEEL)
                                .define('R', zItems.MAGNETIC_STONE_CIRCUIT.get())
                                .define('B', zItems.GLUE.get())
                                .define('C', zItems.CHIP.get())
                                .unlockedBy(ID, has(zItemTag.PLATE_STEEL))
                                .save(c);

                ShapedRecipeBuilder.shaped(MISC, zStatic.MachineUpgrades.TemplateUpgrades.FLUID)
                                .pattern(" B ")
                                .pattern("CSC")
                                .pattern(" R ")
                                .define('S', zItemTag.PLATE_STEEL)
                                .define('R', zItems.RESISTIVE_STONE_CIRCUIT.get())
                                .define('B', zItems.SAP.get())
                                .define('C', zItems.CHIP.get())
                                .unlockedBy(ID, has(zItemTag.PLATE_STEEL))
                                .save(c);

                ShapedRecipeBuilder.shaped(MISC, zBlocks.INVERTED_REPEATER.get())
                                .pattern("RTT")
                                .pattern("SSS")
                                .define('R', Items.REDSTONE)
                                .define('T', Items.REDSTONE_TORCH)
                                .define('S', Tags.Items.STONES)
                                .unlockedBy(getHasName(Items.REDSTONE),
                                                has(Items.REDSTONE))
                                .save(c);

                ShapedRecipeBuilder.shaped(MISC, zBlocks.RECURSIVE_REPEATER.get())
                                .pattern(" R ")
                                .pattern("TET")
                                .pattern("SSS")
                                .define('E', zItems.ENERGIZED_REDSTONE.get())
                                .define('R', Items.REDSTONE)
                                .define('T', Items.REDSTONE_TORCH)
                                .define('S', Tags.Items.STONES)
                                .unlockedBy(getHasName(zItems.ENERGIZED_REDSTONE.get()),
                                                has(zItems.ENERGIZED_REDSTONE.get()))
                                .save(c);

                ShapedRecipeBuilder.shaped(MISC, zBlocks.PULSE_REPEATER.get())
                                .pattern(" RT")
                                .pattern("TQT")
                                .pattern("SSS")
                                .define('Q', Items.QUARTZ)
                                .define('R', Items.REDSTONE)
                                .define('T', Items.REDSTONE_TORCH)
                                .define('S', Tags.Items.STONES)
                                .unlockedBy(getHasName(Items.QUARTZ),
                                                has(Items.QUARTZ))
                                .save(c);

                CrushingTubBuilder.of()
                                .input(ItemTags.LEAVES)
                                .fluid(Fluids.WATER, 125)
                                .unlockedBy()
                                .save(c);

                ShapedRecipeBuilder.shaped(MISC, zBlocks.ROUTER.get())
                                .pattern("WRW")
                                .pattern("RCR")
                                .pattern("WRW")
                                .define('C', Items.CHEST)
                                .define('R', zItems.STONE_CIRCUIT.get())
                                .define('W', zItems.WROUGHT_IRON_PLATE.get())
                                .unlockedBy(getHasName(zItems.WROUGHT_IRON_PLATE.get()),
                                                has(zItems.WROUGHT_IRON_PLATE.get()))
                                .save(c);

                crushing(c,
                                zItems.CREEPER_GALL.get(),
                                Items.GUNPOWDER,
                                3, 3,
                                x.item(zItems.URANIUM_FRAGMENT),
                                0.25f);

                crushing(c,
                                zItems.ENDERMAN_HEART.get(),
                                Items.ENDER_PEARL,
                                6, 6,
                                x.item(zItems.MAGIC_DUST),
                                0.25f);

                crushing(c,
                                zItems.GHAST_BLADDER.get(),
                                Items.GHAST_TEAR,
                                3, 3,
                                x.item(Items.SOUL_SAND),
                                0.25f);

                crushing(c,
                                zItems.GUARDIAN_SCALE.get(),
                                Items.PRISMARINE_CRYSTALS,
                                4, 4,
                                x.item(Items.PRISMARINE_SHARD, 2),
                                0.25f);

                crushing(c,
                                zItems.SILVERFISH_DUST.get(),
                                Items.IRON_NUGGET,
                                2, 2,
                                x.item(Items.IRON_NUGGET, 2),
                                0.25f);

                crushing(c,
                                zItems.SLIME_BOLUS.get(),
                                Items.SLIME_BALL,
                                2, 2,
                                x.item(zItems.GLUE),
                                0.25f);

                crushing(c,
                                zItems.WITHERFLESH.get(),
                                zItems.CARBON_DUST.get(),
                                2, 2,
                                x.item(Items.BONE_MEAL, 2),
                                0.75f);

                crushing(c,
                                zItems.ZOMBIE_LIVER.get(),
                                Items.ROTTEN_FLESH,
                                3, 3,
                                x.item(zItems.GHOUL_HEART),
                                0.25f);

        }

}