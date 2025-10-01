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

import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.Tags;

@SuppressWarnings("null")
public class DataRecipe extends ExtraRecipeProvider {

        public DataRecipe(PackOutput o, CompletableFuture<HolderLookup.Provider> c) {
                super(o, c);
        }

        @Override
        protected void buildRecipes(RecipeOutput c) {

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

                foil(c, zItemTag.PLATE_GOLD, zItems.GOLD_FOIL.get());
                foil(c, zItemTag.PLATE_COPPER, zItems.COPPER_FOIL.get());

                coil(c, zItemTag.FOIL_COPPER, zItems.COPPER_COIL.get());

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
                                .group(zStatic.Plants.BLUE_CUP_MUSHROOM).save(c, x.rl(
                                                Items.BLUE_DYE.getDescriptionId()
                                                                .replace("item.minecraft.", "")
                                                                + "_from_mushrooms"));

                ShapelessRecipeBuilder.shapeless(MISC, Items.BROWN_DYE, 4)
                                .requires(zItems.VIOLET_WEBCAP_MUSHROOM.get())
                                .unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                                                .hasItems(zItems.VIOLET_WEBCAP_MUSHROOM.get()))
                                .group(zStatic.Plants.VIOLET_WEBCAP_MUSHROOM).save(c, x.rl(
                                                Items.BROWN_DYE.getDescriptionId()
                                                                .replace("item.minecraft.", "")
                                                                + "_from_mushrooms"));

                ShapelessRecipeBuilder.shapeless(MISC, zItems.AZALEA_SEEDS.get(), 3)
                                .requires(zItemTag.AZALEA_BUSHES)
                                .unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                                                .hasItems(Items.FLOWERING_AZALEA))
                                .group(zStatic.Items.Azalea.seeds).save(c);

                ShapelessRecipeBuilder.shapeless(MISC, Items.STRING, 3)
                                .requires(zItems.COTTON.get())
                                .unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                                                .hasItems(zItems.COTTON.get()))
                                .group(zStatic.Plants.COTTON).save(c, x.rl(
                                                Items.STRING.getDescriptionId()
                                                                .replace("item.minecraft.", "")
                                                                + "_from_cotton"));

                ShapedRecipeBuilder.shaped(MISC, Items.HANGING_ROOTS, 1)
                                .pattern("RR")
                                .define('R', zItems.SMALL_AZALEA_ROOTS.get())
                                .unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                                                .hasItems(zItems.SMALL_AZALEA_ROOTS.get()))
                                .group(zStatic.Items.Azalea.roots).save(c, x.rl(
                                                Items.HANGING_ROOTS.getDescriptionId()
                                                                .replace("block.minecraft.", "")
                                                                + "_from_small_azalea"));

                ShapedRecipeBuilder.shaped(MISC, Items.FLOWERING_AZALEA_LEAVES, 1)
                                .pattern("LL")
                                .pattern("LL")
                                .define('L', zItems.SMALL_AZALEA_LEAF.get())
                                .unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                                                .hasItems(zItems.SMALL_AZALEA_LEAF.get()))
                                .group(zStatic.Items.Azalea.leaf).save(c, x.rl(
                                                Items.FLOWERING_AZALEA_LEAVES.getDescriptionId()
                                                                .replace("block.minecraft.", "")
                                                                + "_from_small_azalea"));

                ShapelessRecipeBuilder.shapeless(MISC, Items.FLOWERING_AZALEA, 1)
                                .requires(zItems.SMALL_AZALEA_LEAF.get())
                                .requires(Items.AZALEA)
                                .unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                                                .hasItems(Items.AZALEA, zItems.SMALL_AZALEA_LEAF.get()))
                                .group(zStatic.Items.Azalea.leaf).save(c, x.rl(
                                                Items.FLOWERING_AZALEA.getDescriptionId()
                                                                .replace("block.minecraft.", "")
                                                                + "_from_small_azalea"));

                ShapedRecipeBuilder.shaped(MISC, zBlocks.SPRINKLER.get(), 1)
                                .pattern("WWW")
                                .pattern("RBR")
                                .pattern("AIA")
                                .define('A', zBlocks.ADOBE.get())
                                .define('W', zBlocks.WAXED_PLANKS.get())
                                .define('R', Items.REDSTONE)
                                .define('I', Items.IRON_INGOT)
                                .define('B', zItems.BLUE_BATTERY.get())
                                .unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                                                .hasItems(zBlocks.WAXED_PLANKS.get(), zBlocks.ADOBE.get(),
                                                                zItems.BLUE_BATTERY.get()))
                                .group(zStatic.Blocks.sprinkler).save(c);

                SimpleCookingRecipeBuilder.blasting(Ingredient.of(Items.IRON_BLOCK), MISC,
                                zBlocks.RUSTIC_METAL.get().asItem(), 0.01f, 100)
                                .unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                                                .hasItems(Items.IRON_BLOCK))
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

                stairBuilder(zBlocks.WAXED_PLANKS_STAIR.get(), Ingredient.of(zBlocks.WAXED_PLANKS.get()))
                                .unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                                                .hasItems(zBlocks.WAXED_PLANKS.get()))
                                .group(zStatic.DecorativeBlocks.waxed_planks).save(c);

                slabBuilder(BUILDING_BLOCKS, zBlocks.WAXED_PLANKS_SLAB.get(),
                                Ingredient.of(zBlocks.WAXED_PLANKS.get()))
                                .unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                                                .hasItems(zBlocks.WAXED_PLANKS.get()))
                                .group(zStatic.DecorativeBlocks.waxed_planks).save(c);

                ShapedRecipeBuilder.shaped(MISC, Items.SLIME_BALL, 4)
                                .pattern(" R ")
                                .pattern("RHR")
                                .pattern(" R ")
                                .define('R', zItems.RICE_SEED.get())
                                .define('H', Items.HONEYCOMB)
                                .unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                                                .hasItems(Items.HONEYCOMB, zItems.RICE_SEED.get()))
                                .group(ID).save(c, x.rl(
                                                Items.SLIME_BALL.getDescriptionId()
                                                                .replace("item.minecraft.", "")
                                                                + "_from_rice"));

                ShapedRecipeBuilder.shaped(MISC, zBlocks.SOLAR_PANEL.get(), 1)
                                .pattern("LLL")
                                .pattern("FRF")
                                .define('L', Items.LAPIS_LAZULI)
                                .define('F', zItems.RESISTOR.get())
                                .define('R', zItems.BLUE_BATTERY.get())
                                .unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                                                .hasItems(zItems.BLUE_BATTERY.get(), zItems.RESISTOR.get(),
                                                                Items.LAPIS_LAZULI))
                                .group(ID).save(c);

                ShapedRecipeBuilder.shaped(MISC, zBlocks.BASIC_MACHINE_FRAME.get(), 1)
                                .pattern(" C ")
                                .pattern("FRF")
                                .pattern(" C ")
                                .define('C', zItems.CHIP.get())
                                .define('F', Items.IRON_NUGGET)
                                .define('R', zBlocks.ADOBE.get())
                                .unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                                                .hasItems(zBlocks.ADOBE.get(), zItems.CHIP.get(), Items.IRON_NUGGET))
                                .group(ID).save(c);

                ShapedRecipeBuilder.shaped(MISC, zBlocks.HARVESTER.get(), 1)
                                .pattern(" R ")
                                .pattern("AMH")
                                .pattern(" C ")
                                .define('C', zItems.BLUE_BATTERY.get())
                                .define('M', zBlocks.BASIC_MACHINE_FRAME.get())
                                .define('H', Items.IRON_HOE)
                                .define('A', Items.IRON_AXE)
                                .define('R', zItems.RESISTOR.get())
                                .unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                                                .hasItems(Items.IRON_HOE, zItems.RESISTOR.get(),
                                                                zBlocks.BASIC_MACHINE_FRAME.get(),
                                                                zItems.BLUE_BATTERY.get(),
                                                                Items.IRON_AXE))
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
                                .output(zItems.SILICON_SHARD, 4)
                                .group(ID).unlockedBy().save(c);

                UrnRitualBuilder.of()
                                .add(zItems.ENDERMAN_HEART)
                                .add(zItems.ENERGIZED_REDSTONE)
                                .add(zItemTag.DUST_LAPIS)
                                .output(zItems.GHOUL_HEART, 1)
                                .group(ID).unlockedBy().save(c);

                plate(Items.IRON_INGOT, zItems.IRON_PLATE.get(), c);
                plate(Items.GOLD_INGOT, zItems.GOLD_PLATE.get(), c);
                plate(Items.COPPER_INGOT, zItems.COPPER_PLATE.get(), c);
                plate(zItems.STEEL_INGOT.get(), zItems.STEEL_PLATE.get(), c);
                plate(zItems.ADVANCED_ALLOY_INGOT.get(), zItems.ADVANCED_ALLOY_PLATE.get(), c);
                plate(zItems.AQUAMARINE.get(), zItems.AQUAMARINE_PLATE.get(), c);

                nineBlockStorageRecipes(c, MISC, zItems.WASTE_FRAGMENT.get(), MISC, zItems.WASTE.get());
                nineBlockStorageRecipes(c, MISC, zItems.SILICON_SHARD.get(), MISC, zItems.RAW_SILICON.get());

                ShapedRecipeBuilder.shaped(MISC, zBlocks.COOLER_BASE.get(), 4)
                                .pattern("IPI")
                                .pattern("P P")
                                .pattern("IPI")
                                .define('P', zItems.IRON_PLATE.get())
                                .define('I', Items.IRON_INGOT)
                                .unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                                                .hasItems(zItems.IRON_PLATE.get(),
                                                                Items.IRON_INGOT))
                                .group(ID).save(c);

                ShapedRecipeBuilder.shaped(MISC, zBlocks.URN.get())
                                .pattern("B B")
                                .pattern("BHB")
                                .pattern("BBB")
                                .define('H', zItems.ENDERMAN_HEART.get())
                                .define('B', Tags.Items.BRICKS_NORMAL)
                                .unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                                                .hasItems(zItems.ENDERMAN_HEART.get(),
                                                                Items.BRICK))
                                .group(ID).save(c);

                ItemUseBuilder.of()
                                .inputItem(zItems.AZALEA_SEEDS)
                                .inputBlock(Blocks.FLOWER_POT)
                                .outputBlock(zBlocks.AZALEA.get().defaultBlockState().setValue(azalea.AGE, 0))
                                .unlockedBy().save(c);

                QuernMillingBuilder.of().input(ItemTags.COALS)
                                .output(zItems.CARBON_DUST).unlockedBy().save(c);

                QuernMillingBuilder.of().input(zItems.CREEPER_GALL)
                                .output(x.item(zItems.WASTE_FRAGMENT, 3))
                                .unlockedBy().save(c);

                QuernMillingBuilder.of().input(Items.DIAMOND)
                                .output(x.item(zItems.DIAMOND_DUST, 1))
                                .unlockedBy().save(c);

                QuernMillingBuilder.of().input(Items.EMERALD)
                                .output(x.item(zItems.EMERALD_DUST, 1))
                                .unlockedBy().save(c);

                QuernMillingBuilder.of().input(Items.AMETHYST_SHARD)
                                .output(x.item(zItems.AMETHYST_DUST, 1))
                                .unlockedBy().save(c);

                QuernMillingBuilder.of().input(Items.LAPIS_LAZULI)
                                .output(x.item(zItems.LAPIS_DUST, 1))
                                .unlockedBy().save(c);

                QuernMillingBuilder.of().input(Items.ANCIENT_DEBRIS)
                                .output(x.item(zItems.ANCIENT_DEBRIS_DUST, 1))
                                .unlockedBy().save(c);

                QuernMillingBuilder.of().input(ItemTags.LOGS)
                                .output(x.item(zItems.SAWDUST, 4))
                                .unlockedBy().save(c);

                QuernMillingBuilder.of().input(Items.QUARTZ)
                                .output(x.item(zItems.QUARTZ_DUST, 1))
                                .unlockedBy().save(c);

                QuernMillingBuilder.of().input(Items.PRISMARINE_SHARD)
                                .output(x.item(Items.PRISMARINE_CRYSTALS, 1))
                                .unlockedBy().save(c);

                QuernMillingBuilder.of().input(Tags.Items.COBBLESTONES)
                                .output(x.item(Items.GRAVEL, 1))
                                .unlockedBy().save(c);

                QuernMillingBuilder.of().input(Tags.Items.GRAVELS)
                                .output(x.item(Items.SAND, 1))
                                .unlockedBy().save(c);

                UrnRitualBuilder.of().add(Tags.Items.GRAVELS)
                                .output(x.item(zItems.STONE_PEBBLE, 4))
                                .unlockedBy().save(c);

                QuernMillingBuilder.of()
                                .input(zItems.STONE_PEBBLE)
                                .output(zItems.SILVERFISH_DUST, 1)
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

                ShapedRecipeBuilder.shaped(MISC, zBlocks.QUERN.get(), 1)
                                .pattern("TS ")
                                .pattern("SWS")
                                .define('W', zItems.WOODEN_GEAR.get())
                                .define('S', Items.STONE_SLAB)
                                .define('T', Items.STICK)
                                .unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                                                .hasItems(Items.STONE_SLAB,
                                                                Items.STICK, zItems.WOODEN_GEAR.get()))
                                .group(ID).save(c);

                ShapedRecipeBuilder.shaped(MISC, zItems.WOODEN_GEAR.get(), 1)
                                .pattern(" T ")
                                .pattern("T T")
                                .pattern(" T ")
                                .define('T', Items.STICK)
                                .unlockedBy(ID, has(Items.STICK))
                                .group(ID).save(c);

                nuggetIngotBlock(c, zItems.STEEL_NUGGET.get(), zItems.STEEL_INGOT.get(), zBlocks.STEEL_BLOCK.get());
                nuggetIngotBlock(c, zItems.ADVANCED_ALLOY_NUGGET.get(), zItems.ADVANCED_ALLOY_INGOT.get(),
                                zBlocks.ADVANCED_ALLOY_BLOCK.get());

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

                ShapedRecipeBuilder.shaped(MISC, zBlocks.ADVANCED_MACHINE_FRAME.get(), 1)
                                .pattern("PCP")
                                .pattern("FRF")
                                .pattern("PCP")
                                .define('P', zItemTag.PLATE_ADVANCED_ALLOY)
                                .define('C', zItems.CHIP.get())
                                .define('F', zItemTag.PLATE_COAL)
                                .define('R', zBlocks.BASIC_MACHINE_FRAME.get())
                                .unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                                                .hasItems(zBlocks.BASIC_MACHINE_FRAME.get(), zItems.CHIP.get(),
                                                                Items.IRON_NUGGET))
                                .group(ID).save(c);

                twoByTwoPacker(c, zItems.METAL_BOLTS.get(), zItemTag.METAL_NUGGETS);

                ShapedRecipeBuilder.shaped(MISC, zItems.MIXED_INGOT.get(), 3)
                                .define('T', zItemTag.PLATE_COPPER)
                                .define('C', zItemTag.PLATE_GOLD)
                                .define('B', zItemTag.PLATE_IRON)
                                .pattern("T")
                                .pattern("C")
                                .pattern("B")
                                .unlockedBy(ID, InventoryChangeTrigger.TriggerInstance.hasItems(Items.IRON_INGOT))
                                .save(c);

                doubleSmelt(c, zItems.MIXED_INGOT.get(), zItems.ADVANCED_ALLOY_INGOT.get());

                ShapedRecipeBuilder.shaped(MISC, zItems.LIGHT_BULB.get(), 1)
                                .define('G', Tags.Items.GLASS_BLOCKS_COLORLESS)
                                .define('N', Tags.Items.NUGGETS_IRON)
                                .define('D', Tags.Items.DUSTS_GLOWSTONE)
                                .define('S', zItems.RESISTOR.get())
                                .define('C', zItems.CONDENSER.get())
                                .define('R', Tags.Items.DUSTS_REDSTONE)
                                .pattern("GN ")
                                .pattern("NDS")
                                .pattern(" CR")
                                .unlockedBy(ID, InventoryChangeTrigger.TriggerInstance.hasItems(Items.IRON_NUGGET))
                                .save(c);

                ShapedRecipeBuilder.shaped(MISC, zBlocks.HEALER.get().asItem(), 1)
                                .pattern("RWR")
                                .pattern("WSW")
                                .pattern("RWR")
                                .define('R', Items.RED_CONCRETE)
                                .define('W', Items.WHITE_CONCRETE)
                                .define('S', Items.NETHER_STAR)
                                .unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                                                .hasItems(Items.NETHER_STAR, Items.WHITE_CONCRETE, Items.RED_CONCRETE))
                                .group(zStatic.Blocks.healer).save(c);

                UrnRitualBuilder.of()
                                .add(zItemTag.DUST_LAPIS)
                                .add(zItems.BLUE_CUP_MUSHROOM.get())
                                .add(zItemTag.NUGGET_ADVANCEDALLOY)
                                .output(zItems.GUARDIAN_SCALE, 4)
                                .group(ID).unlockedBy().save(c);

                ShapedRecipeBuilder.shaped(MISC, zBlocks.REACTOR_CONTROLLER.get().asItem(), 1)
                                .pattern("PTP")
                                .pattern("ACA")
                                .pattern("PSP")
                                .define('C', zBlocks.ADVANCED_MACHINE_FRAME.get())
                                .define('P', zItemTag.PLATE_COAL)
                                .define('A', zItemTag.PLATE_AQUAMARINE)
                                .define('T', zItems.MAGNETIC_STONE_CIRCUIT.get())
                                .define('S', zItems.NETHER_CIRCUIT.get())
                                .unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                                                .hasItems(zBlocks.ADVANCED_MACHINE_FRAME.get()))
                                .group(zStatic.ReactorStuff.controller).save(c);

                ShapedRecipeBuilder.shaped(MISC, zBlocks.REACTOR_FUEL_CELL.get().asItem(), 2)
                                .pattern(" C ")
                                .pattern("RPR")
                                .pattern(" A ")
                                .define('C', zItems.MAGNETIC_STONE_CIRCUIT.get())
                                .define('R', zItems.RESISTIVE_STONE_CIRCUIT.get())
                                .define('P', zBlocks.ADVANCED_MACHINE_FRAME.get())
                                .define('A', zItemTag.PLATE_AQUAMARINE)
                                .unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                                                .hasItems(zBlocks.ADVANCED_MACHINE_FRAME.get()))
                                .group(zStatic.ReactorStuff.fuel_cell).save(c);

                ShapedRecipeBuilder.shaped(MISC, zItems.WIRED_STONE_PLATE.get(), 1)
                                .pattern("CPC")
                                .pattern(" C ")
                                .define('C', Tags.Items.DUSTS_REDSTONE)
                                .define('P', zItems.STONE_PLATE.get())
                                .unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                                                .hasItems(zItems.STONE_PLATE.get()))
                                .group(ID).save(c);

                ShapedRecipeBuilder.shaped(MISC, zItems.STONE_CIRCUIT.get(), 1)
                                .pattern("PB")
                                .pattern("R ")
                                .define('B', zItems.METAL_BOLTS.get())
                                .define('R', Items.REPEATER)
                                .define('P', zItems.WIRED_STONE_PLATE.get())
                                .unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                                                .hasItems(zItems.WIRED_STONE_PLATE.get()))
                                .group(ID).save(c);

                ShapedRecipeBuilder.shaped(MISC, zItems.MAGNETIC_STONE_CIRCUIT.get(), 1)
                                .pattern("PB")
                                .pattern("R ")
                                .define('B', zItemTag.NUGGET_ADVANCEDALLOY)
                                .define('R', zItems.COPPER_COIL.get())
                                .define('P', zItems.STONE_CIRCUIT.get())
                                .unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                                                .hasItems(zItems.STONE_CIRCUIT.get()))
                                .group(ID).save(c);

                ShapedRecipeBuilder.shaped(MISC, zItems.RESISTIVE_STONE_CIRCUIT.get(), 1)
                                .pattern("PB")
                                .pattern("R ")
                                .define('B', zItemTag.NUGGET_ADVANCEDALLOY)
                                .define('R', zItems.SUPERCONDUCTOR.get())
                                .define('P', zItems.STONE_CIRCUIT.get())
                                .unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                                                .hasItems(zItems.STONE_CIRCUIT.get()))
                                .group(ID).save(c);

                ShapedRecipeBuilder.shaped(MISC, zItems.SUPERCONDUCTOR.get(), 1)
                                .pattern("GEG")
                                .define('G', zItemTag.FOIL_GOLD)
                                .define('E', zItems.ENERGIZED_REDSTONE.get())
                                .unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                                                .hasItems(zItems.STONE_CIRCUIT.get()))
                                .group(ID).save(c);

                ShapedRecipeBuilder.shaped(MISC, zItems.WIRED_NETHER_PLATE.get(), 1)
                                .pattern("CPC")
                                .pattern(" G ")
                                .define('G', Tags.Items.NUGGETS_GOLD)
                                .define('C', zItemTag.NUGGET_STEEL)
                                .define('P', zItems.NETHERRACK_PLATE.get())
                                .unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                                                .hasItems(zItems.NETHERRACK_PLATE.get()))
                                .group(ID).save(c);

                ShapedRecipeBuilder.shaped(MISC, zItems.NETHER_CIRCUIT.get(), 1)
                                .pattern("CPC")
                                .pattern(" G ")
                                .define('G', Tags.Items.NUGGETS_GOLD)
                                .define('C', zItemTag.NUGGET_ADVANCEDALLOY)
                                .define('P', zItems.WIRED_NETHER_PLATE.get())
                                .unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                                                .hasItems(zItems.WIRED_NETHER_PLATE.get()))
                                .group(ID).save(c);

        }

}