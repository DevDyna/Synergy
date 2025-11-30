package com.devdyna.synergy.datagen.server;

import static com.devdyna.synergy.Main.ID;
import static net.minecraft.data.recipes.RecipeCategory.*;

import java.util.concurrent.CompletableFuture;
import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.api.datagen.ExtraRecipeProvider;
import com.devdyna.synergy.init.builder.crops.cultivated.azalea;
import com.devdyna.synergy.init.recipeTypes.builders.*;
import com.devdyna.synergy.init.types.*;
import com.devdyna.synergy.utils.x;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.common.Tags;

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

                foil(c, zItemTag.PLATE_GOLD, zItems.GOLD_FOIL.get());
                foil(c, zItemTag.PLATE_COPPER, zItems.COPPER_FOIL.get());

                coil(c, zItemTag.FOIL_COPPER, zItems.COPPER_COIL.get());

                nodeRecipe(c, zBlocks.ITEM_TRANSFER.get(), Blocks.CHEST);
                nodeRecipe(c, zBlocks.ITEM_PROVIDER.get(), Items.IRON_PICKAXE);
                nodeRecipe(c, zBlocks.ITEM_RETRIEVAL.get(), Blocks.HOPPER);
                node_alt(c, zBlocks.ITEM_RETRIEVAL.get(), zBlocks.ENERGY_RETRIEVAL.get());
                node_alt(c, zBlocks.ITEM_TRANSFER.get(), zBlocks.ENERGY_TRANSFER.get());

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

                SimpleCookingRecipeBuilder.blasting(Ingredient.of(Items.IRON_BLOCK), MISC,
                                zBlocks.RUSTIC_METAL.get().asItem(), 0.01f, 100)
                                .unlockedBy(ID, has(Items.IRON_BLOCK))
                                .group(zStatic.DecorativeBlocks.rustic_metal).save(c, x.rl(
                                                zBlocks.RUSTIC_METAL.get().getDescriptionId()
                                                                .replace("block." + ID + ".", "")
                                                                + "_blasting"));

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
                                .group(ID).save(c);

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
                                .group(ID).save(c, x.rl(
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
                                .group(ID).save(c);

                ShapedRecipeBuilder.shaped(MISC, zBlocks.BASIC_MACHINE_FRAME.get())
                                .pattern(" C ")
                                .pattern("FRF")
                                .pattern(" C ")
                                .define('C', zItems.CHIP.get())
                                .define('F', Items.IRON_NUGGET)
                                .define('R', zBlocks.ADOBE.get())
                                .unlockedBy(ID, has(zBlocks.ADOBE.get()))
                                .group(ID).save(c);

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
                                .group(ID).save(c);

                ReactorCellBuilder.of()
                                .input(zItems.RAW_SILICON)
                                .output(zItems.SILICON)
                                .duration(1_000)
                                .energy(5)
                                .heat(10)
                                .group(ID).unlockedBy().save(c);

                UrnRitualBuilder.of()
                                .add(Items.BLAZE_POWDER)
                                .add(zItemTag.COAL_LIKE)
                                .output(zItems.INFERNAL_EMBER, 2)
                                .group(ID).unlockedBy().save(c);

                UrnRitualBuilder.of()
                                .add(zItems.SILVERFISH_DUST)
                                .add(zItemTag.DUST_QUARTZ)
                                .output(zItems.SILICON_SHARD, 4)
                                .group(ID).unlockedBy().save(c);

                UrnRitualBuilder.of()
                                .add(zItems.ENDERMAN_HEART)
                                .add(zItems.ENERGIZED_REDSTONE)
                                .add(zItemTag.DUST_LAPIS)
                                .output(zItems.GHOUL_HEART)
                                .group(ID).unlockedBy().save(c);

                plate(Items.IRON_INGOT, zItems.IRON_PLATE.get(), c);
                plate(Items.GOLD_INGOT, zItems.GOLD_PLATE.get(), c);
                plate(Items.COPPER_INGOT, zItems.COPPER_PLATE.get(), c);
                plate(zItems.STEEL_INGOT.get(), zItems.STEEL_PLATE.get(), c);
                plate(zItems.ADVANCED_ALLOY_INGOT.get(), zItems.ADVANCED_ALLOY_PLATE.get(), c);
                plate(zItems.AQUAMARINE.get(), zItems.AQUAMARINE_PLATE.get(), c);

                packUnpack(c, zItems.WASTE_FRAGMENT.get(), zItems.WASTE.get(), false);
                packUnpack(c, zItems.SILICON_SHARD.get(), zItems.RAW_SILICON.get(), false);

                ShapedRecipeBuilder.shaped(MISC, zBlocks.COOLER_BASE.get(), 4)
                                .pattern("IPI")
                                .pattern("P P")
                                .pattern("IPI")
                                .define('P', zItems.IRON_PLATE.get())
                                .define('I', Items.IRON_INGOT)
                                .unlockedBy(ID, has(zItems.IRON_PLATE.get()))
                                .group(ID).save(c);

                ShapedRecipeBuilder.shaped(MISC, zBlocks.URN.get())
                                .pattern("B B")
                                .pattern("BHB")
                                .pattern("BBB")
                                .define('H', zItemTag.MOB_DROP)
                                .define('B', Tags.Items.BRICKS_NORMAL)
                                .unlockedBy(ID, has(zItems.ENDERMAN_HEART.get()))
                                .group(ID).save(c);

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

                QuernMillingBuilder.of().input(zItems.CREEPER_GALL)
                                .output(x.item(zItems.WASTE_FRAGMENT, 3))
                                .unlockedBy().save(c);

                QuernMillingBuilder.of().input(zItemTag.DUST_URANIUM)
                                .output(x.item(zItems.WASTE_FRAGMENT, 9))
                                .unlockedBy().save(c, "_alt");

                QuernMillingBuilder.of().input(Items.DIAMOND)
                                .output(zItems.DIAMOND_DUST)
                                .unlockedBy().save(c);

                QuernMillingBuilder.of().input(Items.EMERALD)
                                .output(zItems.EMERALD_DUST)
                                .unlockedBy().save(c);

                QuernMillingBuilder.of().input(Items.AMETHYST_SHARD)
                                .output(zItems.AMETHYST_DUST)
                                .unlockedBy().save(c);

                QuernMillingBuilder.of().input(Items.LAPIS_LAZULI)
                                .output(zItems.LAPIS_DUST)
                                .unlockedBy().save(c);

                QuernMillingBuilder.of().input(Items.ANCIENT_DEBRIS)
                                .output(zItems.ANCIENT_DEBRIS_DUST)
                                .unlockedBy().save(c);

                QuernMillingBuilder.of().input(ItemTags.LOGS)
                                .output(x.item(zItems.SAWDUST, 4))
                                .unlockedBy().save(c);

                QuernMillingBuilder.of().input(Items.QUARTZ)
                                .output(zItems.QUARTZ_DUST)
                                .unlockedBy().save(c);

                QuernMillingBuilder.of().input(Items.PRISMARINE_SHARD)
                                .output(Items.PRISMARINE_CRYSTALS)
                                .delay(20)
                                .unlockedBy().save(c);

                QuernMillingBuilder.of().input(Tags.Items.COBBLESTONES)
                                .output(Items.GRAVEL)
                                .delay(20)
                                .unlockedBy().save(c);

                QuernMillingBuilder.of().input(Tags.Items.GRAVELS)
                                .output(Items.SAND)
                                .delay(20)
                                .unlockedBy().save(c);

                UrnRitualBuilder.of().add(Tags.Items.GRAVELS)
                                .output(zItems.STONE_PEBBLE, 4)
                                .unlockedBy().save(c);

                QuernMillingBuilder.of()
                                .input(zItems.STONE_PEBBLE)
                                .output(zItems.SILVERFISH_DUST)
                                .delay(20)
                                .group(ID)
                                .unlockedBy()
                                .save(c);

                UrnRitualBuilder.of()
                                .add(zItems.CARBON_DUST)
                                .add(zItems.IRON_DUST)
                                .output(zItems.STEEL_NUGGET, 6)
                                .group(ID)
                                .unlockedBy()
                                .save(c);

                UrnRitualBuilder.of()
                                .add(zItems.DIAMOND_DUST)
                                .add(zItems.LAPIS_DUST)
                                .add(zItems.SILVERFISH_DUST)
                                .output(zItems.AQUAMARINE, 2)
                                .group(ID)
                                .unlockedBy()
                                .save(c);

                raw_dust_smelt(c, Items.RAW_COPPER, zItems.COPPER_DUST.get(), Items.COPPER_INGOT);
                raw_dust_smelt(c, Items.RAW_GOLD, zItems.GOLD_DUST.get(), Items.GOLD_INGOT);
                raw_dust_smelt(c, Items.RAW_IRON, zItems.IRON_DUST.get(), Items.IRON_INGOT);

                doubleSmelt(c, zItems.ANCIENT_DEBRIS_DUST.get(), Items.NETHERITE_SCRAP);

                UrnRitualBuilder.of()
                                .add(Items.REDSTONE)
                                .add(Items.GLOWSTONE_DUST)
                                .output(zItems.ENERGIZED_REDSTONE, 2)
                                .group(ID)
                                .unlockedBy()
                                .save(c);

                twoByTwoPacker(c, zItems.CARBON_FIBER.get(), zItemTag.DUST_COAL);

                twoByTwoPacker(c, MISC, zItems.CARBON_PLATE.get(), zItems.CARBON_FIBER.get());

                ShapedRecipeBuilder.shaped(MISC, zBlocks.QUERN.get())
                                .pattern("TS ")
                                .pattern("SWS")
                                .define('W', zItems.WOODEN_GEAR.get())
                                .define('S', Items.STONE_SLAB)
                                .define('T', Items.STICK)
                                .unlockedBy(ID, has(zItems.WOODEN_GEAR.get()))
                                .group(ID).save(c);

                ShapedRecipeBuilder.shaped(MISC, zItems.WOODEN_GEAR.get())
                                .pattern(" T ")
                                .pattern("T T")
                                .pattern(" T ")
                                .define('T', Items.STICK)
                                .unlockedBy(ID, has(Items.STICK))
                                .group(ID).save(c);

                nuggetIngotBlock(c, zItems.STEEL_NUGGET.get(), zItems.STEEL_INGOT.get(),
                                zBlocks.STEEL_BLOCK.get().asItem());
                nuggetIngotBlock(c, zItems.ADVANCED_ALLOY_NUGGET.get(), zItems.ADVANCED_ALLOY_INGOT.get(),
                                zBlocks.ADVANCED_ALLOY_BLOCK.get().asItem());

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
                                .group(ID).save(c);

                ShapedRecipeBuilder.shaped(MISC, zItems.STONE_PLATE.get(), 2)
                                .pattern(" T ")
                                .pattern("TPT")
                                .pattern(" T ")
                                .define('T', zItems.STONE_PEBBLE.get())
                                .define('P', zItemTag.PLATE_COAL)
                                .unlockedBy(ID, has(zItemTag.PLATE_COAL))
                                .group(ID).save(c);

                ShapedRecipeBuilder.shaped(MISC, zBlocks.ADVANCED_MACHINE_FRAME.get())
                                .pattern("PCP")
                                .pattern("FRF")
                                .pattern("PCP")
                                .define('P', zItemTag.PLATE_ADVANCED_ALLOY)
                                .define('C', zItems.CHIP.get())
                                .define('F', zItemTag.PLATE_COAL)
                                .define('R', zBlocks.BASIC_MACHINE_FRAME.get())
                                .unlockedBy(ID, has(zBlocks.BASIC_MACHINE_FRAME.get()))
                                .group(ID).save(c);

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
                                .group(ID).unlockedBy().save(c);

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
                                .group(ID).save(c);

                ShapedRecipeBuilder.shaped(MISC, zItems.STONE_CIRCUIT.get())
                                .pattern("PB")
                                .pattern("R ")
                                .define('B', zItems.METAL_BOLTS.get())
                                .define('R', zItemTag.REPEATERS)
                                .define('P', zItems.WIRED_STONE_PLATE.get())
                                .unlockedBy(ID, has(zItems.WIRED_STONE_PLATE.get()))
                                .group(ID).save(c);

                ShapedRecipeBuilder.shaped(MISC, zItems.MAGNETIC_STONE_CIRCUIT.get())
                                .pattern("PB")
                                .pattern("R ")
                                .define('B', zItemTag.NUGGET_ADVANCEDALLOY)
                                .define('R', zItems.COPPER_COIL.get())
                                .define('P', zItems.STONE_CIRCUIT.get())
                                .unlockedBy(ID, has(zItems.STONE_CIRCUIT.get()))
                                .group(ID).save(c);

                ShapedRecipeBuilder.shaped(MISC, zItems.RESISTIVE_STONE_CIRCUIT.get())
                                .pattern("PB")
                                .pattern("R ")
                                .define('B', zItemTag.NUGGET_ADVANCEDALLOY)
                                .define('R', zItems.SUPERCONDUCTOR.get())
                                .define('P', zItems.STONE_CIRCUIT.get())
                                .unlockedBy(ID, has(zItems.STONE_CIRCUIT.get()))
                                .group(ID).save(c);

                ShapedRecipeBuilder.shaped(MISC, zItems.SUPERCONDUCTOR.get())
                                .pattern("GEG")
                                .define('G', zItemTag.FOIL_GOLD)
                                .define('E', zItems.ENERGIZED_REDSTONE.get())
                                .unlockedBy(ID, has(zItems.STONE_CIRCUIT.get()))
                                .group(ID).save(c);

                ShapedRecipeBuilder.shaped(MISC, zItems.WIRED_NETHER_PLATE.get())
                                .pattern("CPC")
                                .pattern(" G ")
                                .define('G', Tags.Items.NUGGETS_GOLD)
                                .define('C', zItemTag.NUGGET_STEEL)
                                .define('P', zItems.NETHERRACK_PLATE.get())
                                .unlockedBy(ID, has(zItems.NETHERRACK_PLATE.get()))
                                .group(ID).save(c);

                ShapedRecipeBuilder.shaped(MISC, zItems.NETHER_CIRCUIT.get())
                                .pattern("CPC")
                                .pattern(" G ")
                                .define('G', Tags.Items.NUGGETS_GOLD)
                                .define('C', zItemTag.NUGGET_ADVANCEDALLOY)
                                .define('P', zItems.WIRED_NETHER_PLATE.get())
                                .unlockedBy(ID, has(zItems.WIRED_NETHER_PLATE.get()))
                                .group(ID).save(c);

                QuernMillingBuilder.of()
                                .input(Items.TORCHFLOWER)
                                .output(Items.BLAZE_POWDER)
                                .delay(120)
                                .group(ID)
                                .unlockedBy()
                                .save(c);

                ShapedRecipeBuilder.shaped(MISC, Items.MUD_BRICKS, 1).define('#', zItems.PACKED_MUD_BRICK.get())
                                .pattern("##").pattern("##").unlockedBy(getHasName(zItems.PACKED_MUD_BRICK.get()),
                                                has(zItems.PACKED_MUD_BRICK.get()))
                                .save(c, ID + ":" + getConversionRecipeName(Items.MUD_BRICKS,
                                                zItems.PACKED_MUD_BRICK.get()));

                packUnpack(c, zItems.MUD_BALL.get(), Items.MUD, true);

                ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, zItems.PACKED_MUD_BALL.get(), 4)
                                .requires(zItems.MUD_BALL.get(), 4).requires(Items.WHEAT)
                                .group(ID).unlockedBy(ID, has(zItems.MUD_BALL.get()))
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

        }

}