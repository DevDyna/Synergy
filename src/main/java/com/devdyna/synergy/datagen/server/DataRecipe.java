package com.devdyna.synergy.datagen.server;

import static com.devdyna.synergy.Main.ID;
import static net.minecraft.data.recipes.RecipeCategory.*;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.api.utils.x;
import com.devdyna.synergy.common.recipes.builders.*;
import com.devdyna.synergy.datagen.api.ExtraRecipeProvider;
import com.devdyna.synergy.init.builder.agriculture.cultivated.azalea;
import com.devdyna.synergy.init.builder.industrial_machines.alloy_smelter.recipe.AlloySmelterRecipeBuilder;
import com.devdyna.synergy.init.builder.industrial_machines.caster.recipe.CasterRecipeBuilder;
import com.devdyna.synergy.init.builder.industrial_machines.compressor.recipe.CompressorRecipeBuilder;
import com.devdyna.synergy.init.builder.industrial_machines.extractor.recipe.ExtractorRecipeBuilder;
import com.devdyna.synergy.init.builder.industrial_machines.furnace.recipe.ElectricFurnaceRecipeBuilder;
import com.devdyna.synergy.init.builder.industrial_machines.macerator.recipe.MaceratorRecipeBuilder;
import com.devdyna.synergy.init.builder.industrial_machines.melter.recipe.MelterRecipeBuilder;
import com.devdyna.synergy.init.types.*;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
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

                compatIngotsAndDusts(c);
                cropResultRecipes(c);
                clearNBT(c);
                bacteries(c);
                legacyItemComponents(c);
                fuelCellNuclearReactions(c);
                mixtures(c);
                coolerRecipes(c);
                chests(c);
                tools(c);
                moderators(c);
                droplets(c);
                brickRecipes(c);
                lasers(c);

                foil(c, zItemTag.PLATE_GOLD, zItems.GOLD_FOIL.get());
                foil(c, zItemTag.PLATE_COPPER, zItems.COPPER_FOIL.get());
                foil(c, zItemTag.PLATE_SILVER, zItems.SILVER_FOIL.get());
                foil(c, zItemTag.PLATE_IRON, zItems.IRON_FOIL.get());

                coil(c, zItemTag.FOIL_COPPER, zItems.COPPER_COIL.get());
                coil(c, zItemTag.FOIL_GOLD, zItems.GOLD_COIL.get());
                coil(c, zItemTag.FOIL_IRON, zItems.IRON_COIL.get());
                coil(c, zItemTag.FOIL_SILVER, zItems.SILVER_COIL.get());

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

                ShapelessRecipeBuilder.shapeless(MISC, zItems.ADOBE_BALL.get(), 3)
                                .requires(zItems.PACKED_MUD_BALL.get(), 2)
                                .requires(Items.CLAY_BALL)
                                .unlockedBy(ID, has(Items.CLAY_BALL))
                                .save(c);

                stairBuilder(zBlocks.WAXED_PLANKS_STAIR.get(), Ingredient.of(zBlocks.WAXED_PLANKS.get()))
                                .unlockedBy(ID, has(zBlocks.WAXED_PLANKS.get()))
                                .group(zStatic.DecorativeBlocks.waxed_planks).save(c);

                slabBuilder(BUILDING_BLOCKS, zBlocks.WAXED_PLANKS_SLAB.get(),
                                Ingredient.of(zBlocks.WAXED_PLANKS.get()))
                                .unlockedBy(ID, has(zBlocks.WAXED_PLANKS.get()))
                                .group(zStatic.DecorativeBlocks.waxed_planks).save(c);

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

                ShapedRecipeBuilder.shaped(MISC, zBlocks.SOLAR_PANEL.get())
                                .pattern("LLL")
                                .pattern("FRF")
                                .define('L', Items.LAPIS_LAZULI)
                                .define('F', zItems.RESISTOR.get())
                                .define('R', zItems.BLUE_BATTERY.get())
                                .unlockedBy(ID, has(zItems.BLUE_BATTERY.get()))
                                .save(c);

                ShapedRecipeBuilder.shaped(MISC, zBlocks.BASIC_MACHINE_FRAME.get())
                                .pattern(" C ")
                                .pattern("FRF")
                                .pattern(" C ")
                                .define('C', zItems.CHIP.get())
                                .define('F', Items.IRON_NUGGET)
                                .define('R', zBlocks.ADOBE.get())
                                .unlockedBy(ID, has(zBlocks.ADOBE.get()))
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

                ReactorCellBuilder.of()
                                .input(zItems.RAW_SILICON)
                                .output(zItems.SILICON)
                                .duration(1_000)
                                .energy(50)
                                .heat(10)
                                .unlockedBy().save(c);

                UrnRitualBuilder.of()
                                .add(Items.BLAZE_POWDER)
                                .add(zItemTag.COAL_LIKE)
                                .output(zItems.INFERNAL_EMBER, 2)
                                .unlockedBy().save(c);

                UrnRitualBuilder.of()
                                .add(zItems.SILVERFISH_DUST)
                                .add(zItemTag.DUST_QUARTZ)
                                .output(zItems.SILICON_SHARD, 4)
                                .unlockedBy().save(c);

                UrnRitualBuilder.of()
                                .add(zItems.ENDERMAN_HEART)
                                .add(zItems.ENERGIZED_REDSTONE)
                                .add(zItemTag.DUST_LAPIS)
                                .output(zItems.GHOUL_HEART)
                                .unlockedBy().save(c);

                plate(Items.IRON_INGOT, zItems.IRON_PLATE.get(), c);
                plate(Items.GOLD_INGOT, zItems.GOLD_PLATE.get(), c);
                plate(Items.COPPER_INGOT, zItems.COPPER_PLATE.get(), c);
                plate(zItemTag.INGOT_SILVER, zItems.SILVER_PLATE.get(), c);
                plate(zItemTag.INGOT_STEEL, zItems.STEEL_PLATE.get(), c);
                plate(zItemTag.INGOT_ADVANCEDALLOY, zItems.ADVANCED_ALLOY_PLATE.get(), c);
                plate(zItemTag.GEMS_AQUAMARINE, zItems.AQUAMARINE_PLATE.get(), c);
                plate(zItemTag.INGOT_WROUGHT_IRON, zItems.WROUGHT_IRON_PLATE.get(), c);

                packUnpack(c, zItems.WASTE_FRAGMENT.get(), zItems.WASTE.get(), false);
                packUnpack(c, zItems.SILICON_SHARD.get(), zItems.RAW_SILICON.get(), false);

                ShapedRecipeBuilder.shaped(MISC, zBlocks.COOLER_BASE.get(), 4)
                                .pattern("IPI")
                                .pattern("P P")
                                .pattern("IPI")
                                .define('P', zItems.IRON_PLATE.get())
                                .define('I', Items.IRON_INGOT)
                                .unlockedBy(ID, has(zItems.IRON_PLATE.get()))
                                .save(c);

                ShapedRecipeBuilder.shaped(MISC, zBlocks.URN.get())
                                .pattern("B B")
                                .pattern("BHB")
                                .pattern("BBB")
                                .define('H', zItemTag.MOB_DROP)
                                .define('B', Tags.Items.BRICKS_NORMAL)
                                .unlockedBy(ID, has(zItems.ENDERMAN_HEART.get()))
                                .save(c);

                ItemUseBuilder.of()
                                .inputItem(zItems.AZALEA_SEEDS)
                                .inputBlock(Blocks.FLOWER_POT)
                                .outputBlock(zBlocks.AZALEA.get().defaultBlockState().setValue(azalea.AGE, 0))
                                .canBeDisabled()
                                .unlockedBy().save(c);

                ItemUseBuilder.of()
                                .inputItem(Items.POTION)
                                .inputBlock(Blocks.DIRT)
                                .outputBlock(Blocks.MUD)
                                .outputItem(Items.GLASS_BOTTLE)
                                .isRenderOnly()
                                .unlockedBy().save(c);

                QuernMillingBuilder.of().input(ItemTags.COALS)
                                .output(zItems.CARBON_DUST).unlockedBy().save(c);

                MaceratorRecipeBuilder.of().input(ItemTags.COALS)
                                .output(zItems.CARBON_DUST, 2)
                                .secondary(zItems.SULFUR_DUST)
                                .chance(0.25f)
                                .unlockedBy().save(c);

                QuernMillingBuilder.of().input(zItems.CREEPER_GALL)
                                .output(x.item(zItems.WASTE_FRAGMENT, 1))
                                .unlockedBy().save(c);

                MaceratorRecipeBuilder.of().input(zItems.CREEPER_GALL)
                                .output(x.item(zItems.WASTE_FRAGMENT, 3))
                                .secondary(zItems.WASTE_FRAGMENT)
                                .chance(0.35f)
                                .unlockedBy().save(c);

                QuernMillingBuilder.of().input(zItemTag.DUST_URANIUM)
                                .output(x.item(zItems.WASTE_FRAGMENT, 3))
                                .unlockedBy().save(c, "_alt");

                MaceratorRecipeBuilder.of().input(zItemTag.DUST_URANIUM)
                                .output(x.item(zItems.WASTE_FRAGMENT, 9))
                                .secondary(zItems.WASTE_FRAGMENT)
                                .chance(0.45f)
                                .unlockedBy().save(c, "_alt");

                QuernMillingBuilder.of().input(Items.DIAMOND)
                                .output(zItems.DIAMOND_DUST)
                                .unlockedBy().save(c);

                MaceratorRecipeBuilder.of().input(Items.DIAMOND)
                                .output(zItems.DIAMOND_DUST)
                                .unlockedBy().save(c);

                CompressorRecipeBuilder.of()
                                .input(zItemTag.DUST_DIAMOND)
                                .delay(80)
                                .output(Items.DIAMOND)
                                .unlockedBy()
                                .save(c);

                QuernMillingBuilder.of().input(Items.EMERALD)
                                .output(zItems.EMERALD_DUST)
                                .unlockedBy().save(c);

                MaceratorRecipeBuilder.of().input(Items.EMERALD)
                                .output(zItems.EMERALD_DUST)
                                .unlockedBy().save(c);

                CompressorRecipeBuilder.of()
                                .input(zItemTag.DUST_EMERALD)
                                .delay(80)
                                .output(Items.EMERALD)
                                .unlockedBy()
                                .save(c);

                QuernMillingBuilder.of().input(Items.AMETHYST_SHARD)
                                .output(zItems.AMETHYST_DUST)
                                .unlockedBy().save(c);

                MaceratorRecipeBuilder.of().input(Items.AMETHYST_SHARD)
                                .output(zItems.AMETHYST_DUST)
                                .unlockedBy().save(c);

                CompressorRecipeBuilder.of()
                                .input(zItemTag.DUST_AMETHYST)
                                .delay(80)
                                .output(Items.AMETHYST_SHARD)
                                .unlockedBy()
                                .save(c);

                QuernMillingBuilder.of().input(Items.LAPIS_LAZULI)
                                .output(zItems.LAPIS_DUST)
                                .unlockedBy().save(c);

                MaceratorRecipeBuilder.of().input(Items.LAPIS_LAZULI)
                                .output(zItems.LAPIS_DUST)
                                .unlockedBy().save(c);

                CompressorRecipeBuilder.of()
                                .input(zItemTag.DUST_LAPIS)
                                .delay(80)
                                .output(Items.LAPIS_LAZULI)
                                .unlockedBy()
                                .save(c);

                QuernMillingBuilder.of().input(Items.ANCIENT_DEBRIS)
                                .output(zItems.ANCIENT_DEBRIS_DUST)
                                .unlockedBy().save(c);

                MaceratorRecipeBuilder.of().input(Items.ANCIENT_DEBRIS)
                                .output(zItems.ANCIENT_DEBRIS_DUST, 2)
                                .secondary(zItems.ANCIENT_DEBRIS_DUST)
                                .chance(0.5f)
                                .unlockedBy().save(c);

                QuernMillingBuilder.of().input(ItemTags.LOGS)
                                .output(x.item(zItems.SAWDUST, 2))
                                .unlockedBy().save(c);

                MaceratorRecipeBuilder.of().input(ItemTags.LOGS)
                                .output(x.item(zItems.SAWDUST, 3))
                                .secondary(x.item(zItems.SAWDUST))
                                .chance(0.5f)
                                .unlockedBy().save(c);

                QuernMillingBuilder.of().input(Items.QUARTZ)
                                .output(zItems.QUARTZ_DUST)
                                .unlockedBy().save(c);

                MaceratorRecipeBuilder.of().input(Items.QUARTZ)
                                .output(zItems.QUARTZ_DUST)
                                .unlockedBy().save(c);

                CompressorRecipeBuilder.of()
                                .input(zItemTag.DUST_QUARTZ)
                                .delay(80)
                                .output(Items.QUARTZ)
                                .unlockedBy()
                                .save(c);

                QuernMillingBuilder.of().input(Items.PRISMARINE_SHARD)
                                .output(Items.PRISMARINE_CRYSTALS)
                                .delay(20)
                                .unlockedBy().save(c);

                MaceratorRecipeBuilder.of().input(Items.PRISMARINE_SHARD)
                                .output(Items.PRISMARINE_CRYSTALS)
                                .delay(20)
                                .unlockedBy().save(c);

                QuernMillingBuilder.of().input(Tags.Items.COBBLESTONES)
                                .output(Items.GRAVEL)
                                .delay(20)
                                .unlockedBy().save(c);

                MaceratorRecipeBuilder.of().input(Tags.Items.COBBLESTONES)
                                .output(Items.GRAVEL)
                                .secondary(Items.GRAVEL)
                                .chance(0.5f)
                                .delay(20)
                                .unlockedBy().save(c);

                QuernMillingBuilder.of().input(Tags.Items.GRAVELS)
                                .output(Items.SAND)
                                .delay(20)
                                .unlockedBy().save(c);

                MaceratorRecipeBuilder.of().input(Tags.Items.GRAVELS)
                                .output(Items.SAND)
                                .secondary(Items.SAND)
                                .chance(0.5f)
                                .delay(20)
                                .unlockedBy().save(c);

                UrnRitualBuilder.of().add(Tags.Items.GRAVELS)
                                .output(zItems.STONE_PEBBLE, 4)
                                .unlockedBy().save(c);

                QuernMillingBuilder.of()
                                .input(zItems.STONE_PEBBLE)
                                .output(zItems.SILVERFISH_DUST)
                                .delay(20)

                                .unlockedBy()
                                .save(c);

                MaceratorRecipeBuilder.of()
                                .input(zItems.STONE_PEBBLE)
                                .output(zItems.SILVERFISH_DUST, 2)
                                .delay(20)

                                .unlockedBy()
                                .save(c);

                UrnRitualBuilder.of()
                                .add(zItems.CARBON_DUST)
                                .add(zItems.IRON_DUST)
                                .output(zItems.STEEL_NUGGET, 6)

                                .unlockedBy()
                                .save(c);

                UrnRitualBuilder.of()
                                .add(zItems.DIAMOND_DUST)
                                .add(zItems.LAPIS_DUST)
                                .add(zItems.SILVERFISH_DUST)
                                .output(zItems.AQUAMARINE, 2)
                                .unlockedBy()
                                .save(c);

                raw_dust_smelt(c, Items.RAW_COPPER, zItems.COPPER_DUST.get(), Items.COPPER_INGOT,
                                zItems.GOLD_DUST.get(), 0.25f);
                raw_dust_smelt(c, Items.RAW_GOLD, zItems.GOLD_DUST.get(), Items.GOLD_INGOT, zItems.SILVER_DUST.get(),
                                0.25f);
                raw_dust_smelt(c, Items.RAW_IRON, zItems.IRON_DUST.get(), Items.IRON_INGOT, zItems.NICKEL_DUST.get(),
                                0.25f);

                doubleSmelt(c, zItems.ANCIENT_DEBRIS_DUST.get(), Items.NETHERITE_SCRAP);

                UrnRitualBuilder.of()
                                .add(Items.REDSTONE)
                                .add(Items.GLOWSTONE_DUST)
                                .output(zItems.ENERGIZED_REDSTONE, 2)

                                .unlockedBy()
                                .save(c);

                twoByTwoPacker(c, zItems.CARBON_FIBER.get(), zItemTag.DUST_COAL);
                twoByTwoPacker(c, Items.RAW_IRON, zItems.TINY_IRON_DUST.get(),ID+":raw_iron_from_tiny_iron_dust");
                twoByTwoPacker(c, MISC, zItems.CARBON_PLATE.get(), zItems.CARBON_FIBER.get());

                ShapedRecipeBuilder.shaped(MISC, zBlocks.QUERN.get())
                                .pattern("TS ")
                                .pattern("SWS")
                                .define('W', zItems.WOODEN_GEAR.get())
                                .define('S', Items.STONE_SLAB)
                                .define('T', Items.STICK)
                                .unlockedBy(ID, has(zItems.WOODEN_GEAR.get()))
                                .save(c);

                gear(c, zItems.WOODEN_GEAR, Tags.Items.RODS_WOODEN, ItemTags.PLANKS);
                gear(c, zItems.TIN_GEAR, zItemTag.INGOT_TIN, zFluids.MOLTEN_TIN);
                gear(c, zItems.GOLD_GEAR, Tags.Items.INGOTS_GOLD, zFluids.MOLTEN_GOLD);
                gear(c, zItems.IRON_GEAR, Tags.Items.INGOTS_IRON, zFluids.MOLTEN_IRON);
                gear(c, zItems.LEAD_GEAR, zItemTag.INGOT_LEAD, zFluids.MOLTEN_LEAD);
                gear(c, zItems.STEEL_GEAR, zItemTag.INGOT_STEEL, zFluids.MOLTEN_STEEL);
                gear(c, zItems.COPPER_GEAR, Tags.Items.INGOTS_COPPER, zFluids.MOLTEN_COPPER);
                gear(c, zItems.NICKEL_GEAR, zItemTag.INGOT_NICKEL, zFluids.MOLTEN_NICKEL);

                nuggetIngotBlock(c, zItems.STEEL_NUGGET.get(), zItems.STEEL_INGOT.get(),
                                zBlocks.STEEL_BLOCK.get().asItem());
                nuggetIngotBlock(c, zItems.ADVANCED_ALLOY_NUGGET.get(), zItems.ADVANCED_ALLOY_INGOT.get(),
                                zBlocks.ADVANCED_ALLOY_BLOCK.get().asItem());

                nuggetIngotBlock(c, zItems.WROUGHT_IRON_NUGGET.get(), zItems.WROUGHT_IRON_INGOT.get(),
                                zBlocks.WROUGHT_IRON_BLOCK.get().asItem());

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

                UrnRitualBuilder.of()
                                .add(zItems.ZOMBIE_LIVER)
                                .add(Items.GHAST_TEAR)
                                .output(zItems.GHAST_BLADDER, 2)
                                .unlockedBy().save(c);

                UrnRitualBuilder.of()
                                .add(Items.BONE)
                                .add(Items.SLIME_BALL)
                                .output(zItems.SLIME_BOLUS, 2)
                                .unlockedBy().save(c);

                UrnRitualBuilder.of()
                                .add(Items.SPIDER_EYE)
                                .add(zItems.WASTE_FRAGMENT)
                                .output(zItems.VENOM_SAC)
                                .unlockedBy().save(c);

                UrnRitualBuilder.of()
                                .add(Items.ROTTEN_FLESH)
                                .add(Items.BONE)
                                .add(zItemTag.DUST_COAL)
                                .output(zItems.WITHERFLESH)
                                .unlockedBy().save(c);

                UrnRitualBuilder.of()
                                .add(Items.ROTTEN_FLESH)
                                .add(zItemTag.NUGGET_STEEL)
                                .add(zItemTag.SAWDUST)
                                .output(zItems.ZOMBIE_LIVER)
                                .unlockedBy().save(c);

                UrnRitualBuilder.of().add(Tags.Items.NETHERRACKS)
                                .output(zItems.NETHERRACK_PEBBLE, 4)
                                .unlockedBy().save(c);

                UrnRitualBuilder.of().add(zItems.INFERNAL_EMBER)
                                .add(Items.ENDER_EYE)
                                .add(zItemTag.DUST_COAL)
                                .add(zItems.SILICON_SHARD)
                                .output(zItems.ENDERMAN_HEART)
                                .unlockedBy().save(c);

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

                twoByTwoPacker(c, zItems.METAL_BOLTS.get(), zItemTag.METAL_NUGGETS);

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

                ShapedRecipeBuilder.shaped(MISC, zBlocks.HEALER.get().asItem())
                                .pattern("RWR")
                                .pattern("WSW")
                                .pattern("RWR")
                                .define('R', Items.RED_CONCRETE)
                                .define('W', Items.WHITE_CONCRETE)
                                .define('S', Items.NETHER_STAR)
                                .unlockedBy(ID, has(Items.NETHER_STAR))
                                .group(zStatic.Blocks.healer).save(c);

                UrnRitualBuilder.of()
                                .add(zItemTag.DUST_LAPIS)
                                .add(zItems.BLUE_CUP_MUSHROOM.get())
                                .add(zItemTag.NUGGET_ADVANCEDALLOY)
                                .output(zItems.GUARDIAN_SCALE, 4)
                                .unlockedBy().save(c);

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

                QuernMillingBuilder.of()
                                .input(Items.TORCHFLOWER)
                                .output(Items.BLAZE_POWDER)
                                .delay(120)

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

                MaceratorRecipeBuilder.of()
                                .input(Items.BLAZE_ROD)
                                .output(Items.BLAZE_POWDER, 2)
                                .secondary(Items.BLAZE_POWDER, 2)
                                .chance(0.75f)
                                .delay(120)

                                .unlockedBy()
                                .save(c, "_from_blaze_rod");

                ShapedRecipeBuilder.shaped(MISC, Items.MUD_BRICKS, 1).define('#', zItems.PACKED_MUD_BRICK.get())
                                .pattern("##").pattern("##").unlockedBy(getHasName(zItems.PACKED_MUD_BRICK.get()),
                                                has(zItems.PACKED_MUD_BRICK.get()))
                                .save(c, ID + ":" + getConversionRecipeName(Items.MUD_BRICKS,
                                                zItems.PACKED_MUD_BRICK.get()));

                packUnpack(c, zItems.MUD_BALL.get(), Items.MUD, true);

                ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, zItems.PACKED_MUD_BALL.get(), 4)
                                .requires(zItems.MUD_BALL.get(), 4).requires(Items.WHEAT)
                                .unlockedBy(ID, has(zItems.MUD_BALL.get()))
                                .save(c, ID + ":" + getConversionRecipeName(zItems.PACKED_MUD_BALL.get(),
                                                zItems.MUD_BALL.get()));

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

                ShapedRecipeBuilder.shaped(MISC, zItems.CAKE_STICK.get())
                                .pattern(" C")
                                .pattern("S ")
                                .define('C', Items.CAKE)
                                .define('S', Items.STICK)
                                .unlockedBy(getHasName(Items.CAKE),
                                                has(Items.CAKE))
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

                MaceratorRecipeBuilder.of()
                                .input(Tags.Items.STONES)
                                .delay(40)
                                .unlockedBy()

                                .output(zItems.STONE_PEBBLE, 4)
                                .secondary(zItems.SILVERFISH_DUST)
                                .chance(0.45f)
                                .save(c);

                MaceratorRecipeBuilder.of()
                                .input(Tags.Items.NETHERRACKS)
                                .delay(40)
                                .unlockedBy()
                                .output(zItems.NETHERRACK_PEBBLE, 4)
                                .secondary(zItems.SULFUR_DUST)
                                .chance(0.75f)
                                .save(c);

                ShapedRecipeBuilder.shaped(MISC, zMachines.MACERATOR.block().get())
                                .pattern(" Q ")
                                .pattern("RMS")
                                .pattern(" C ")
                                .define('S', zItems.STONE_CIRCUIT.get())
                                .define('R', zItems.RESISTOR.get())
                                .define('Q', zBlocks.QUERN.get())
                                .define('C', zItems.BLUE_BATTERY.get())
                                .define('M', zBlocks.BASIC_MACHINE_FRAME.get())
                                .unlockedBy(ID, has(zBlocks.BASIC_MACHINE_FRAME.get()))
                                .save(c);

                ShapedRecipeBuilder.shaped(MISC, zMachines.COMPRESSOR.block().get())
                                .pattern(" Q ")
                                .pattern("RMS")
                                .pattern(" C ")
                                .define('S', zItems.MAGNETIC_STONE_CIRCUIT.get())
                                .define('R', zItems.RESISTOR.get())
                                .define('Q', Items.CRAFTER)
                                .define('C', zItems.BLUE_BATTERY.get())
                                .define('M', zBlocks.BASIC_MACHINE_FRAME.get())
                                .unlockedBy(ID, has(zBlocks.BASIC_MACHINE_FRAME.get()))
                                .save(c);

                ShapedRecipeBuilder.shaped(MISC, zMachines.ALLOY_SMELTER.block().get())
                                .pattern(" Q ")
                                .pattern("RMS")
                                .pattern(" C ")
                                .define('S', zItems.RESISTIVE_STONE_CIRCUIT.get())
                                .define('R', zItems.RESISTOR.get())
                                .define('Q', zBlocks.URN.get())
                                .define('C', zItems.BLUE_BATTERY.get())
                                .define('M', zBlocks.BASIC_MACHINE_FRAME.get())
                                .unlockedBy(ID, has(zBlocks.BASIC_MACHINE_FRAME.get()))
                                .save(c);

                ShapedRecipeBuilder.shaped(MISC, zMachines.ELECTRIC_FURNACE.block().get())
                                .pattern(" Q ")
                                .pattern("RMS")
                                .pattern(" C ")
                                .define('S', zItems.NETHER_CIRCUIT.get())
                                .define('R', zItems.RESISTOR.get())
                                .define('Q', Items.FURNACE)
                                .define('C', zItems.BLUE_BATTERY.get())
                                .define('M', zBlocks.BASIC_MACHINE_FRAME.get())
                                .unlockedBy(ID, has(zBlocks.BASIC_MACHINE_FRAME.get()))
                                .save(c);

                simplePacked(c, zItems.STONE_PEBBLE.get(), Items.COBBLESTONE);
                simplePacked(c, zItems.NETHERRACK_PEBBLE.get(), Items.NETHERRACK);

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

                AlloySmelterRecipeBuilder.of()
                                .inputs(zItemTag.DUST_COAL, Tags.Items.INGOTS_IRON)
                                .delay(80)
                                .output(zItems.STEEL_INGOT)
                                .unlockedBy()
                                .save(c);

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

                smeltingResultFromBase(c, zBlocks.SMOOTH_ADOBE.get(), zBlocks.ADOBE.get());

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

                UrnRitualBuilder.of()
                                .add(Tags.Items.GEMS_DIAMOND)
                                .add(zItems.MAGIC_DUST)
                                .output(zItems.VOID_CRYSTAL.get())
                                .unlockedBy().save(c);

                ItemUseBuilder.of()
                                .inputItem(zItems.VOID_CRYSTAL)
                                .inputBlock(zBlocks.WOODEN_TINY_CHEST)
                                .outputBlock(zBlocks.VOID_BOX)
                                .unlockedBy().save(c);

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
                                .unlockedBy().save(c, "_from_sandstone");

                MaceratorRecipeBuilder.of().input(Tags.Items.SANDSTONE_RED_BLOCKS)
                                .output(Items.RED_SAND, 4)
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

                ShapedRecipeBuilder.shaped(MISC, zBlocks.FLUID_TANK.get())
                                .define('#', Tags.Items.NUGGETS_IRON)
                                .define('G', Tags.Items.GLASS_BLOCKS_COLORLESS)
                                .pattern(" # ")
                                .pattern("#G#")
                                .pattern(" # ")
                                .unlockedBy(getHasName(Items.IRON_NUGGET),
                                                has(Items.IRON_NUGGET))
                                .save(c);

                ExtractorRecipeBuilder.of()
                                .input(Items.SLIME_BALL)
                                .fluid(zFluids.GLUE, 250)
                                .unlockedBy()
                                .save(c);

                ExtractorRecipeBuilder.of()
                                .input(zItems.VIOLET_WEBCAP_MUSHROOM.get())
                                .fluid(zFluids.OIL, 250)
                                .unlockedBy()
                                .save(c);

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
                                .consumeCatalyst()
                                .output(Items.MOSSY_COBBLESTONE)
                                .unlockedBy()
                                .save(c);

                CasterRecipeBuilder.of()
                                .fluid(zFluids.HONEY, 1000)
                                .input(zItems.MOLD_BLOCK)
                                .output(Items.HONEY_BLOCK)
                                .unlockedBy()
                                .save(c);

                CasterRecipeBuilder.of()
                                .fluid(zFluids.GLUE, 250)
                                .output(zItems.GLUE)
                                .unlockedBy()
                                .save(c);

                CasterRecipeBuilder.of()
                                .fluid(zFluids.OIL, 250)
                                .output(zItems.OIL)
                                .unlockedBy()
                                .save(c);

                CasterRecipeBuilder.of()
                                .fluid(zFluids.SAP, 250)
                                .output(zItems.SAP)
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

                CasterRecipeBuilder.of()
                                .input(zItems.CHIP)
                                .fluid(zFluids.LIQUID_GLASS, 250)
                                .consumeCatalyst()
                                .output(zItems.ELECTRON_TUBE_BASE)
                                .unlockedBy()
                                .save(c);

                moltenIngots(c, zItems.TIN_INGOT.get(), zItemTag.INGOT_TIN, zFluids.MOLTEN_TIN);
                moltenIngots(c, Items.GOLD_INGOT, Tags.Items.INGOTS_GOLD, zFluids.MOLTEN_GOLD);
                moltenIngots(c, Items.IRON_INGOT, Tags.Items.INGOTS_IRON, zFluids.MOLTEN_IRON);
                moltenIngots(c, zItems.LEAD_INGOT.get(), zItemTag.INGOT_LEAD, zFluids.MOLTEN_LEAD);
                moltenIngots(c, zItems.STEEL_INGOT.get(), zItemTag.INGOT_STEEL, zFluids.MOLTEN_STEEL);
                moltenIngots(c, Items.COPPER_INGOT, Tags.Items.INGOTS_COPPER, zFluids.MOLTEN_COPPER);
                moltenIngots(c, zItems.NICKEL_INGOT.get(), zItemTag.INGOT_NICKEL, zFluids.MOLTEN_NICKEL);
                moltenIngots(c, zItems.OSMIUM_INGOT.get(), zItemTag.INGOT_OSMIUM, zFluids.MOLTEN_OSMIUM);
                moltenIngots(c, zItems.SILVER_INGOT.get(), zItemTag.INGOT_SILVER, zFluids.MOLTEN_SILVER);
                moltenIngots(c, zItems.IRIDIUM_INGOT.get(), zItemTag.INGOT_IRIDIUM, zFluids.MOLTEN_IRIDIUM);
                moltenIngots(c, zItems.URANIUM_INGOT.get(), zItemTag.INGOT_URANIUM, zFluids.MOLTEN_URANIUM);
                moltenIngots(c, zItems.ALUMINUM_INGOT.get(), zItemTag.INGOT_ALUMINUM, zFluids.MOLTEN_ALUMINUM);
                moltenIngots(c, zItems.PLATINUM_INGOT.get(), zItemTag.INGOT_PLATINUM, zFluids.MOLTEN_PLATINUM);

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

                ShapedRecipeBuilder.shaped(MISC, zMachines.CASTING_FACTORY.block().get())
                                .pattern(" Q ")
                                .pattern("RMS")
                                .pattern(" C ")
                                .define('S', zItems.STEEL_GEAR.get())
                                .define('R', zItems.RESISTOR.get())
                                .define('Q', zBlocks.FLUID_TANK.get())
                                .define('C', zItems.BLUE_BATTERY.get())
                                .define('M', zBlocks.BASIC_MACHINE_FRAME.get())
                                .unlockedBy(ID, has(zBlocks.BASIC_MACHINE_FRAME.get()))
                                .save(c);

                ShapedRecipeBuilder.shaped(MISC, zMachines.EXTRACTOR.block().get())
                                .pattern(" Q ")
                                .pattern("RMS")
                                .pattern(" C ")
                                .define('S', zItems.STEEL_ELECTRON_TUBE.get())
                                .define('R', zItems.RESISTOR.get())
                                .define('Q', zItems.AQUAMARINE_PLATE.get())
                                .define('C', zItems.BLUE_BATTERY.get())
                                .define('M', zBlocks.BASIC_MACHINE_FRAME.get())
                                .unlockedBy(ID, has(zBlocks.BASIC_MACHINE_FRAME.get()))
                                .save(c);

                ShapedRecipeBuilder.shaped(MISC, zMachines.MELTER.block().get())
                                .pattern(" Q ")
                                .pattern("RMS")
                                .pattern(" C ")
                                .define('S', zItems.LIGHT_BULB.get())
                                .define('R', zItems.RESISTOR.get())
                                .define('Q', zItems.SUPERCONDUCTOR.get())
                                .define('C', zItems.BLUE_BATTERY.get())
                                .define('M', zBlocks.BASIC_MACHINE_FRAME.get())
                                .unlockedBy(ID, has(zBlocks.BASIC_MACHINE_FRAME.get()))
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

                stonecutter(c, zBlocks.CAST_IRON_BLOCK.get(), zItems.CAST_IRON_INGOT.get(),2);
                stonecutter(c, zBlocks.CAST_IRON_TILES.get(), zItems.CAST_IRON_INGOT.get(),2);

                stonecutter(c, zBlocks.CAST_IRON_BLOCK.get(), zItemTag.CAST_IRON_BLOCKS);
                stonecutter(c, zBlocks.CAST_IRON_TILES.get(), zItemTag.CAST_IRON_BLOCKS);

                stonecutter(c, zBlocks.CALCITE_BRICKS.get(), Items.CALCITE);

                ShapedRecipeBuilder.shaped(MISC, zBlocks.CALCITE_BRICKS.get().asItem())
                                .pattern("##")
                                .pattern("##")
                                .define('#', Items.CALCITE)
                                .unlockedBy(ID, has(Items.CALCITE))
                                .save(c);

        }

}