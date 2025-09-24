package com.devdyna.synergy.datagen.server;

import static com.devdyna.synergy.Main.ID;
import static net.minecraft.data.recipes.RecipeCategory.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.init.builder.crops.cultivated.azalea;
import com.devdyna.synergy.init.recipeTypes.builders.*;
import com.devdyna.synergy.init.types.*;
import com.devdyna.synergy.utils.x;

import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.registries.DeferredHolder;

@SuppressWarnings("null")
public class DataRecipe extends RecipeProvider {

        public DataRecipe(PackOutput o, CompletableFuture<HolderLookup.Provider> c) {
                super(o, c);
        }

        public static final List<DeferredHolder<Item, Item>> clearNBT = List.of(
                        zItems.RED_BATTERY,
                        zItems.BLUE_BATTERY,
                        zItems.GREEN_BATTERY);

        @Override
        protected void buildRecipes(RecipeOutput c) {

                cropResultRecipes(c);

                clearNBT.forEach(i -> {
                        ShapelessRecipeBuilder.shapeless(MISC, i.get())
                                        .requires(i.get())
                                        .unlockedBy(ID, InventoryChangeTrigger.TriggerInstance.hasItems(i.get()))
                                        .group(ID)
                                        .save(c, i.getRegisteredName() + "_clear_nbt");
                });

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

                ShapelessRecipeBuilder.shapeless(MISC, zItems.BONE_MEAL_MIXTURE.get(), 3)
                                .requires(Tags.Items.SLIME_BALLS)
                                .requires(Items.BONE_MEAL)
                                .requires(Tags.Items.DUSTS_REDSTONE)
                                .requires(Items.BLAZE_POWDER)
                                .unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                                                .hasItems(Items.BONE_MEAL, Items.BLAZE_POWDER, Items.REDSTONE,
                                                                Items.SLIME_BALL))
                                .group(zStatic.tips.MIXTURE_TIP).save(c);

                ShapelessRecipeBuilder.shapeless(MISC, zItems.GLOWSTONE_MIXTURE.get(), 6)
                                .requires(Tags.Items.SLIME_BALLS)
                                .requires(zItems.ENERGIZED_REDSTONE.get())
                                .requires(zItems.LAPIS_DUST.get())
                                .requires(Items.PRISMARINE_CRYSTALS)
                                .unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                                                .hasItems(Items.PRISMARINE_CRYSTALS, zItems.ENERGIZED_REDSTONE.get(),
                                                                zItems.LAPIS_DUST.get(),
                                                                Items.SLIME_BALL))
                                .group(zStatic.tips.MIXTURE_TIP).save(c);

                ShapelessRecipeBuilder.shapeless(MISC, zItems.AMETHYST_MIXTURE.get(), 4)
                                .requires(Items.FERMENTED_SPIDER_EYE)
                                .requires(zItems.QUARTZ_DUST.get())
                                .requires(zItems.AMETHYST_DUST.get())
                                .requires(Items.HONEYCOMB)
                                .unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                                                .hasItems(zItems.QUARTZ_DUST.get(), Items.FERMENTED_SPIDER_EYE,
                                                                zItems.AMETHYST_DUST.get(),
                                                                Items.HONEYCOMB))
                                .group(zStatic.tips.MIXTURE_TIP).save(c);

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

                // TODO probably it will change on later
                ShapedRecipeBuilder.shaped(MISC, zItems.RED_BATTERY.get(), 1)
                                .pattern(" R ")
                                .pattern("RHR")
                                .pattern(" R ")
                                .define('R', zItems.BONE_MEAL_MIXTURE.get())
                                .define('H', zItems.BLUE_BATTERY.get())
                                .unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                                                .hasItems(zItems.BLUE_BATTERY.get(), zItems.BONE_MEAL_MIXTURE.get()))
                                .group(ID).save(c);

                ShapedRecipeBuilder.shaped(MISC, zItems.BLUE_BATTERY.get(), 1)
                                .pattern(" R ")
                                .pattern("RHR")
                                .pattern(" R ")
                                .define('R', zItems.BLUE_CUP_MUSHROOM.get())
                                .define('H', zItems.GREEN_BATTERY.get())
                                .unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                                                .hasItems(zItems.GREEN_BATTERY.get(), zItems.BLUE_CUP_MUSHROOM.get()))
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

                ShapedRecipeBuilder.shaped(MISC, zBlocks.MACHINE_FRAME.get(), 1)
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
                                .define('M', zBlocks.MACHINE_FRAME.get())
                                .define('H', Items.IRON_HOE)
                                .define('A', Items.IRON_AXE)
                                .define('R', zItems.RESISTOR.get())
                                .unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                                                .hasItems(Items.IRON_HOE, zItems.RESISTOR.get(),
                                                                zBlocks.MACHINE_FRAME.get(), zItems.BLUE_BATTERY.get(),
                                                                Items.IRON_AXE))
                                .group(ID).save(c);

                ShapedRecipeBuilder.shaped(MISC, zItems.RESISTOR.get(), 4)
                                .pattern(" MN")
                                .pattern("MGM")
                                .pattern("NM ")
                                .define('N', Items.IRON_NUGGET)
                                .define('M', zItems.BLUE_CUP_MUSHROOM.get())
                                .define('G', zItems.BONE_MEAL_MIXTURE.get())
                                .unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                                                .hasItems(Items.IRON_NUGGET, zItems.BLUE_CUP_MUSHROOM.get(),
                                                                zItems.BONE_MEAL_MIXTURE.get()))
                                .group(ID).save(c);

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

                ReactorCellBuilder.of()
                                .input(zItems.RAW_SILICON)
                                .output(zItems.SILICON)
                                .duration(1_000)
                                .energy(5)
                                .heat(10)
                                .group(ID).unlockedBy().save(c);

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

                UrnRitualBuilder.of()
                                .add(Items.BLAZE_POWDER)
                                .add(zItemTag.COAL_LIKE)
                                .output(zItems.INFERNAL_EMBER, 2)
                                .group(ID).unlockedBy().save(c);

                UrnRitualBuilder.of()
                                .add(zItems.SILVERFISH_DUST)
                                .output(zItems.SILICON_SHARD, 4)
                                .group(ID).unlockedBy().save(c);

                // UrnRitualBuilder.of()
                // .add(zItems.CREEPER_GALL)
                // .output(zItems.WASTE_FRAGMENT, 2)
                // .group(ID).unlockedBy().save(c);

                UrnRitualBuilder.of()
                                .add(zItems.ENDERMAN_HEART)
                                .output(zItems.GHOUL_HEART, 1)
                                .group(ID).unlockedBy().save(c);

                UrnRitualBuilder.of()
                                .add(zItems.WASTE)
                                .output(zItems.URANIUM, 1)
                                .group(ID).unlockedBy().save(c);

                plate(Items.IRON_INGOT, zItems.IRON_PLATE.get(), c);
                plate(Items.GOLD_INGOT, zItems.GOLD_PLATE.get(), c);
                plate(Items.COPPER_INGOT, zItems.COPPER_PLATE.get(), c);
                plate(zItems.STEEL_INGOT.get(), zItems.STEEL_PLATE.get(), c);

                nineBlockStorageRecipes(c, MISC, zItems.WASTE_FRAGMENT.get(), MISC, zItems.WASTE.get());
                nineBlockStorageRecipes(c, MISC, zItems.SILICON_SHARD.get(), MISC, zItems.SILICON.get());

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
                                // .consumeItemOnUse()
                                .inputBlock(Blocks.FLOWER_POT)
                                .outputBlock(zBlocks.AZALEA.get().defaultBlockState().setValue(azalea.AGE, 0))
                                .unlockedBy().save(c);

                ItemUseBuilder.of()
                                .inputItem(Items.DIAMOND)
                                // .consumeItemOnUse()
                                // .requireShift()
                                .inputBlock(zBlocks.COOLER_BASE)
                                .outputBlock(zBlocks.DIAMOND_COOLER)
                                .unlockedBy().save(c);

                ItemUseBuilder.of()
                                .inputItem(Items.STONE)
                                // .consumeItemOnUse()
                                // .requireShift()
                                .inputBlock(zBlocks.COOLER_BASE)
                                .outputBlock(zBlocks.ENDER_COOLER)
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

                twoByTwoPacker(c, MISC, zItems.CARBON_FIBER.get(), zItems.CARBON_DUST.get());

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

        }

        private void nuggetIngotBlock(RecipeOutput c, ItemLike nugget, ItemLike ingot, ItemLike block) {
                nineBlockStorageRecipesWithCustomPacking(
                                c, RecipeCategory.MISC, nugget, RecipeCategory.MISC, ingot,
                                x.path(nugget.asItem()) + "_from_" + x.path(ingot.asItem()), x.path(ingot.asItem()));

                nineBlockStorageRecipesWithCustomPacking(
                                c, RecipeCategory.MISC, ingot, RecipeCategory.MISC, block,
                                x.path(ingot.asItem()) + "_from_" + x.path(block.asItem()), x.path(ingot.asItem()));

        }

        private void raw_dust_smelt(RecipeOutput c, ItemLike raw, ItemLike dust, ItemLike ingot) {

                QuernMillingBuilder.of().input(x.ingredient(raw.asItem()))
                                .output(x.item(dust.asItem(), 3))
                                .unlockedBy().save(c, "_from_raw");

                QuernMillingBuilder.of().input(x.ingredient(ingot.asItem()))
                                .output(x.item(dust.asItem(), 1))
                                .unlockedBy().save(c, "_from_ingot");

                doubleSmelt(c, dust, ingot);
        }

        private void doubleSmelt(RecipeOutput c, ItemLike input, ItemLike output) {
                SimpleCookingRecipeBuilder.blasting(x.ingredient(input.asItem()), MISC, output.asItem(), 0.1F, 100)
                                .unlockedBy(getHasName(input), has(output))
                                .save(c, x.path(output.asItem()) + "_from_" + x.path(input.asItem()) + "_blasting");
                SimpleCookingRecipeBuilder.smelting(x.ingredient(input.asItem()), MISC, output.asItem(), 0.1F, 200)
                                .unlockedBy(getHasName(input), has(output))
                                .save(c, x.path(output.asItem()) + "_from_" + x.path(input.asItem()) + "_smelting");
        }

        private void plate(Item input, Item output, RecipeOutput c) {
                ShapedRecipeBuilder.shaped(MISC, output, 3)
                                .pattern("III")
                                .define('I', input)
                                .unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                                                .hasItems(input))
                                .group(ID).save(c);
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
                                .save(c, x.rl(
                                                b.getDescriptionId()
                                                                .replace("block." + ID + ".", "")
                                                                + "_alt"));

        }

        private void cropResultRecipes(RecipeOutput c) {

                List<DeferredHolder<Item, ? extends Item>> seeds = List.of(
                                zItems.RICE_SEED,
                                zItems.AZALEA_SEEDS,
                                zItems.COTTON_SEEDS,
                                zItems.CAVE_WHEAT_SEEDS,
                                zItems.BLUE_CUP_SPORE,
                                zItems.VIOLET_WEBCAP_SPORE);

                List<List<Item>> result = List.of(
                                List.of(zItems.RICE_SEED.get()),
                                List.of(zItems.AZALEA_SEEDS.get(), zItems.SMALL_AZALEA_LEAF.get(),
                                                zItems.SMALL_AZALEA_ROOTS.get()),
                                List.of(zItems.COTTON_SEEDS.get(), zItems.COTTON.get()),
                                List.of(zItems.CAVE_WHEAT_SEEDS.get(), Items.WHEAT),
                                List.of(zItems.BLUE_CUP_SPORE.get(), zItems.BLUE_CUP_MUSHROOM.get()),
                                List.of(zItems.VIOLET_WEBCAP_SPORE.get(), zItems.VIOLET_WEBCAP_MUSHROOM.get()));

                seeds.forEach(s -> CropResultBuilder
                                .of().input(s.get()).output(result.get(seeds.indexOf(s)).stream()
                                                .map(i -> x.ingredient(i)).toList())
                                .group(ID).unlockedBy().save(c));

        }
}