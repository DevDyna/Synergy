package com.devdyna.synergy.datagen.api;

import static com.devdyna.synergy.Main.ID;
import static net.minecraft.data.recipes.RecipeCategory.*;

import java.util.concurrent.CompletableFuture;

import org.cyclops.evilcraft.RegistryEntries;

import com.brandon3055.draconicevolution.init.DEContent;
import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.api.registers.FluidRegister;
import com.devdyna.synergy.api.utils.ModAddonUtil;
import com.devdyna.synergy.api.utils.x;
import com.devdyna.synergy.common.recipes.node_provider.fluid.FluidProviderBuilder;
import com.devdyna.synergy.common.recipes.node_provider.item.ItemProviderBuilder;
import com.devdyna.synergy.init.builder.industrial_machines.alloy_smelter.recipe.AlloySmelterRecipeBuilder;
import com.devdyna.synergy.init.builder.industrial_machines.caster.recipe.CasterRecipeBuilder;
import com.devdyna.synergy.init.builder.industrial_machines.compressor.recipe.CompressorRecipeBuilder;
import com.devdyna.synergy.init.builder.industrial_machines.macerator.recipe.MaceratorRecipeBuilder;
import com.devdyna.synergy.init.builder.industrial_machines.melter.recipe.MelterRecipeBuilder;
import com.devdyna.synergy.init.builder.magic.quern.recipe.QuernMillingBuilder;
import com.devdyna.synergy.init.builder.survival.casting_table.recipe.CastingTableBuilder;
import com.devdyna.synergy.init.builder.survival.foundry.recipe.FoundryBuilder;
import com.devdyna.synergy.init.builder.survival.placeable_bricks.recipe.DryableBricksBuilder;
import com.devdyna.synergy.init.types.zBlocks;
import com.devdyna.synergy.init.types.zItems;
import com.enderio.enderio.init.EIOBlocks;
import com.enderio.enderio.init.EIOItems;
import com.simibubi.create.AllItems;

import appeng.core.definitions.AEBlocks;
import appeng.core.definitions.AEItems;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.data.recipes.SingleItemRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.conditions.ICondition;
import net.neoforged.neoforge.common.conditions.NotCondition;
import net.neoforged.neoforge.common.conditions.TagEmptyCondition;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.silentchaos512.gear.item.CraftingItems;

public class ExtraRecipeProvider extends RecipeProvider {

        public ExtraRecipeProvider(PackOutput c, CompletableFuture<Provider> r) {
                super(c, r);
        }

        protected String asID(Item i, String suffix) {
                return ID + ":" + x.path(i) + suffix;
        }

        protected String asID(Item i) {
                return asID(i, "_alt");
        }

        protected void modCompats(RecipeOutput c) {

                CompressorRecipeBuilder.of()
                                .input(AEItems.SILICON)
                                .catalyst(AEItems.SILICON_PRESS)
                                .output(AEItems.SILICON_PRINT)
                                .unlockedBy()
                                .save(c.withConditions(ModAddonUtil.hasMod(zStatic.Mods.AE2)));

                CompressorRecipeBuilder.of()
                                .input(Items.DIAMOND)
                                .catalyst(AEItems.ENGINEERING_PROCESSOR_PRESS)
                                .output(AEItems.ENGINEERING_PROCESSOR_PRINT)
                                .unlockedBy()
                                .save(c.withConditions(ModAddonUtil.hasMod(zStatic.Mods.AE2)));

                CompressorRecipeBuilder.of()
                                .input(AEItems.CERTUS_QUARTZ_CRYSTAL)
                                .catalyst(AEItems.CALCULATION_PROCESSOR_PRESS)
                                .output(AEItems.CALCULATION_PROCESSOR_PRINT)
                                .unlockedBy()
                                .save(c.withConditions(ModAddonUtil.hasMod(zStatic.Mods.AE2)));

                CompressorRecipeBuilder.of()
                                .input(Items.GOLD_INGOT)
                                .catalyst(AEItems.LOGIC_PROCESSOR_PRESS)
                                .output(AEItems.LOGIC_PROCESSOR_PRINT)
                                .unlockedBy()
                                .save(c.withConditions(ModAddonUtil.hasMod(zStatic.Mods.AE2)));

                crushing(c.withConditions(ModAddonUtil.hasMod(zStatic.Mods.AE2)),
                                AEItems.CERTUS_QUARTZ_CRYSTAL,
                                AEItems.CERTUS_QUARTZ_DUST);

                crushing(c.withConditions(ModAddonUtil.hasMod(zStatic.Mods.AE2)),
                                AEBlocks.SKY_STONE_BLOCK,
                                AEItems.SKY_DUST);

                crushing(c.withConditions(ModAddonUtil.hasMod(zStatic.Mods.AE2)),
                                AEItems.FLUIX_CRYSTAL,
                                AEItems.FLUIX_DUST);

                crushing(c.withConditions(ModAddonUtil.hasMod(zStatic.Mods.AE2)),
                                Items.ENDER_PEARL,
                                AEItems.ENDER_DUST);

                ItemProviderBuilder.of()
                                .pattern(
                                                AEBlocks.NOT_SO_MYSTERIOUS_CUBE.block(),
                                                AEBlocks.SKY_STONE_BLOCK.block(),
                                                AEBlocks.SKY_STONE_BLOCK.block(),
                                                AEBlocks.SKY_STONE_BLOCK.block())
                                .output(AEItems.SKY_DUST.asItem())
                                .unlockedBy()
                                .save(c.withConditions(ModAddonUtil.hasMod(zStatic.Mods.AE2)));

                ItemProviderBuilder.of()
                                .pattern(AEBlocks.FLAWLESS_BUDDING_QUARTZ.block())
                                .output(AEItems.CERTUS_QUARTZ_DUST.asItem())
                                .unlockedBy()
                                .save(c.withConditions(ModAddonUtil.hasMod(zStatic.Mods.AE2)));

                crushing(c.withConditions(ModAddonUtil.hasMod(zStatic.Mods.Create)),
                                AllItems.ZINC_INGOT.get(),
                                AllItems.ZINC_NUGGET.get(),
                                9, 9);

                crushing(c.withConditions(ModAddonUtil.hasMod(zStatic.Mods.Create)),
                                AllItems.RAW_ZINC.get(),
                                AllItems.ZINC_NUGGET.get(),
                                18, 18);

                AlloySmelterRecipeBuilder.of()
                                .inputs(AllItems.ZINC_INGOT, Items.COPPER_INGOT)
                                .output(AllItems.BRASS_INGOT, 2)
                                .unlockedBy()
                                .save(c.withConditions(ModAddonUtil.hasMod(zStatic.Mods.Create)));

                AlloySmelterRecipeBuilder.of()
                                .inputs(x.ingredient(AllItems.ZINC_NUGGET, Items.IRON_NUGGET),
                                                x.ingredient(Items.ANDESITE))
                                .output(AllItems.ANDESITE_ALLOY.asItem())
                                .unlockedBy()
                                .save(c.withConditions(ModAddonUtil.hasMod(zStatic.Mods.Create)));

                crushing(c.withConditions(ModAddonUtil.hasMod(zStatic.Mods.DraconicEvolution)),
                                DEContent.INGOT_DRACONIUM.get(),
                                DEContent.DUST_DRACONIUM.get());

                crushing(c.withConditions(ModAddonUtil.hasMod(zStatic.Mods.DraconicEvolution)),
                                DEContent.INGOT_DRACONIUM_AWAKENED.get(),
                                DEContent.DUST_DRACONIUM_AWAKENED.get());

                crushing(c.withConditions(ModAddonUtil.hasMod(zStatic.Mods.SilentGear)),
                                CraftingItems.AZURE_SILVER_INGOT,
                                CraftingItems.AZURE_SILVER_DUST);

                crushing(c.withConditions(ModAddonUtil.hasMod(zStatic.Mods.SilentGear)),
                                CraftingItems.RAW_AZURE_SILVER.asItem(),
                                CraftingItems.AZURE_SILVER_DUST.asItem(), 2, 2);

                crushing(c.withConditions(ModAddonUtil.hasMod(zStatic.Mods.SilentGear)),
                                CraftingItems.CRIMSON_IRON_INGOT,
                                CraftingItems.CRIMSON_IRON_DUST);

                crushing(c.withConditions(ModAddonUtil.hasMod(zStatic.Mods.SilentGear)),
                                CraftingItems.RAW_CRIMSON_IRON.asItem(),
                                CraftingItems.CRIMSON_IRON_DUST.asItem(), 2, 2);

                crushing(c.withConditions(ModAddonUtil.hasMod(zStatic.Mods.SilentGear)),
                                CraftingItems.BLAZE_GOLD_INGOT.asItem(),
                                CraftingItems.BLAZE_GOLD_DUST.asItem());

                crushing(c.withConditions(ModAddonUtil.hasMod(zStatic.Mods.SilentGear)),
                                CraftingItems.TYRIAN_STEEL_INGOT.asItem(),
                                CraftingItems.TYRIAN_STEEL_DUST.asItem());

                crushing(c.withConditions(ModAddonUtil.hasMod(zStatic.Mods.SilentGear)),
                                CraftingItems.CRIMSON_STEEL_INGOT.asItem(),
                                CraftingItems.CRIMSON_STEEL_DUST.asItem());

                crushing(c.withConditions(ModAddonUtil.hasMod(zStatic.Mods.SilentGear)),
                                CraftingItems.AZURE_ELECTRUM_INGOT.asItem(),
                                CraftingItems.AZURE_ELECTRUM_DUST.asItem());

                crushing(c.withConditions(ModAddonUtil.hasMod(zStatic.Mods.SilentGear)),
                                Items.SHULKER_SHELL,
                                CraftingItems.CRUSHED_SHULKER_SHELL.asItem());

                crushing(c.withConditions(ModAddonUtil.hasMod(zStatic.Mods.Evilcraft)),
                                RegistryEntries.ITEM_DARK_GEM.get(),
                                RegistryEntries.ITEM_DARK_GEM_CRUSHED.get());

                FluidProviderBuilder.of()
                                .pattern(
                                                RegistryEntries.BLOCK_BLOODY_COBBLESTONE.get(),
                                                RegistryEntries.BLOCK_HARDENED_BLOOD.get(),
                                                RegistryEntries.BLOCK_HARDENED_BLOOD.get())
                                .output(RegistryEntries.FLUID_BLOOD.get())
                                .unlockedBy()
                                .save(c.withConditions(ModAddonUtil.hasMod(zStatic.Mods.Evilcraft)));

                ItemProviderBuilder.of()
                                .pattern(
                                                EIOBlocks.VOID_CHASSIS.get(),
                                                EIOBlocks.REINFORCED_OBSIDIAN.get(),
                                                EIOBlocks.REINFORCED_OBSIDIAN.get(),
                                                Blocks.BEDROCK)
                                .output(EIOItems.GRAINS_OF_INFINITY.get())
                                .unlockedBy()
                                .save(c.withConditions(ModAddonUtil.hasMod(zStatic.Mods.EnderIO)));

                AlloySmelterRecipeBuilder.of()
                                .inputs(
                                                Tags.Items.INGOTS_IRON,
                                                Tags.Items.INGOTS_COPPER)
                                .output(EIOItems.CONDUCTIVE_ALLOY_INGOT.get())
                                .unlockedBy()
                                .save(c.withConditions(ModAddonUtil.hasMod(zStatic.Mods.EnderIO)));

                AlloySmelterRecipeBuilder.of()
                                .inputs(
                                                Tags.Items.INGOTS_IRON,
                                                Tags.Items.ENDER_PEARLS)
                                .output(EIOItems.PULSATING_ALLOY_INGOT.get(), 2)
                                .unlockedBy()
                                .save(c.withConditions(ModAddonUtil.hasMod(zStatic.Mods.EnderIO)));

                AlloySmelterRecipeBuilder.of()
                                .inputs(
                                                x.ingredient(Items.SOUL_SAND, Items.SOUL_SOIL),
                                                x.ingredient(Tags.Items.INGOTS_GOLD))
                                .output(EIOItems.SOULARIUM_INGOT.get())
                                .unlockedBy()
                                .save(c.withConditions(ModAddonUtil.hasMod(zStatic.Mods.EnderIO)));

                AlloySmelterRecipeBuilder.of()
                                .inputs(
                                                x.ingredient(Items.OBSIDIAN, Items.CRYING_OBSIDIAN),
                                                x.ingredient(zItems.WROUGHT_IRON_INGOT))
                                .output(EIOItems.DARK_STEEL_INGOT.get())
                                .unlockedBy()
                                .save(c.withConditions(ModAddonUtil.hasMod(zStatic.Mods.EnderIO)));

        }

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

        protected void oreProcessing(RecipeOutput c, FluidRegister fluid, ResourceLocation raw, ItemLike dust,
                        ItemLike ingot,
                        ResourceLocation ingotTag, Item secondary, float chance) {

                QuernMillingBuilder.of().input(x.ingredient(raw))
                                .output(x.item(dust.asItem(), 2))
                                .unlockedBy().save(c.withConditions(
                                                new ICondition[] {
                                                                new NotCondition(
                                                                                new TagEmptyCondition(raw)) }),
                                                "_from_raw");

                MaceratorRecipeBuilder.of().input(x.itemSized(raw))
                                .output(x.item(dust.asItem(), 3))
                                .secondary(secondary, chance)
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

                MaceratorRecipeBuilder.of().input(x.itemSized(ingotTag))
                                .output(x.item(dust.asItem()))
                                .unlockedBy().save(c.withConditions(
                                                new ICondition[] {
                                                                new NotCondition(
                                                                                new TagEmptyCondition(ingotTag)) }),
                                                "_from_ingot");

                doubleSmelt(c, dust, ingot);

                MelterRecipeBuilder.of()
                                .input(x.itemSized(ingotTag))
                                .fluid(fluid, 90)
                                .unlockedBy()
                                .save(c.withConditions(
                                                new ICondition[] {
                                                                new NotCondition(
                                                                                new TagEmptyCondition(ingotTag)) }),
                                                "_from_ingot");

                FoundryBuilder.of()
                                .input(x.ingredient(ingotTag))
                                .fluid(fluid, 90)
                                .unlockedBy()
                                .save(c.withConditions(
                                                new ICondition[] {
                                                                new NotCondition(
                                                                                new TagEmptyCondition(ingotTag)) }),
                                                "_from_ingot");

                MelterRecipeBuilder.of()
                                .input(x.itemSized(raw))
                                .fluid(fluid, 180)
                                .unlockedBy()
                                .save(c.withConditions(
                                                new ICondition[] {
                                                                new NotCondition(
                                                                                new TagEmptyCondition(raw)) }),
                                                "_from_raw");

                FoundryBuilder.of()
                                .input(x.ingredient(raw))
                                .fluid(fluid, 180)
                                .unlockedBy()
                                .save(c.withConditions(
                                                new ICondition[] {
                                                                new NotCondition(
                                                                                new TagEmptyCondition(raw)) }),
                                                "_from_raw");

                MelterRecipeBuilder.of()
                                .input(x.itemSized(dust))
                                .fluid(fluid, 90)
                                .unlockedBy()
                                .save(c, "_from_dust");

                FoundryBuilder.of()
                                .input(x.ingredient(dust))
                                .fluid(fluid, 90)
                                .unlockedBy()
                                .save(c, "_from_dust");

                CasterRecipeBuilder.of()
                                .fluid(fluid, 90)
                                .input(zItems.MOLD_INGOT)
                                .output(ingot)
                                .unlockedBy()
                                .save(c);

                CastingTableBuilder.of()
                                .fluid(fluid, 90)
                                .input(zItems.MOLD_INGOT)
                                .output(ingot)
                                .unlockedBy()
                                .save(c);

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

        protected void unpacker(RecipeOutput c, ItemLike input, ItemLike output, int count) {
                ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, output, count).requires(input)
                                .unlockedBy(getHasName(input), has(input))
                                .save(c, getConversionRecipeName(output, input));
        }

        protected void nuggetIngotBlock(RecipeOutput c, ItemLike nugget, ItemLike ingot, ItemLike block) {
                packUnpack(c, nugget, ingot, false);
                packUnpack(c, ingot, block, false);
        }

        protected void gear(RecipeOutput c, DeferredHolder<Item, Item> gear, TagKey<Item> input,
                        TagKey<Item> inputCompress) {
                ShapedRecipeBuilder.shaped(MISC, gear.get())
                                .pattern(" # ")
                                .pattern("# #")
                                .pattern(" # ")
                                .define('#', input)
                                .unlockedBy(ID, has(input))
                                .save(c);

                CompressorRecipeBuilder.of()
                                .input(inputCompress)
                                .delay(80)
                                .catalyst(zItems.MOLD_GEAR.get())
                                .output(gear)
                                .unlockedBy()
                                .save(c);
        }

        protected void gear(RecipeOutput c, DeferredHolder<Item, Item> gear, TagKey<Item> input, FluidRegister fluid) {
                ShapedRecipeBuilder.shaped(MISC, gear.get())
                                .pattern(" # ")
                                .pattern("# #")
                                .pattern(" # ")
                                .define('#', input)
                                .unlockedBy(ID, has(input))
                                .save(c);

                MelterRecipeBuilder.of()
                                .input(gear)
                                .fluid(fluid, 180)
                                .unlockedBy()
                                .save(c, "_from_gear");

                castingRecipe(c, "", fluid, 180, zItems.MOLD_GEAR.get(), gear.get(), false);

        }

        protected void moltenIngots(RecipeOutput c, Item ingot, TagKey<Item> ingot_tag, FluidRegister fluid) {
                moltenRecipes(c, "_from_ingot", zItems.MOLD_INGOT.get(), ingot, ingot_tag, fluid, 90);
        }

        protected void moltenPlates(RecipeOutput c, Item plate, TagKey<Item> plate_tag, FluidRegister fluid) {
                moltenRecipes(c, "_from_plate", zItems.MOLD_PLATE.get(), plate, plate_tag, fluid, 45);
        }

        protected void meltRecipes(RecipeOutput c, String suffix, TagKey<Item> input, FluidRegister fluid, int amount) {
                MelterRecipeBuilder.of()
                                .input(input)
                                .fluid(fluid, amount)
                                .unlockedBy()
                                .save(c, suffix);

                FoundryBuilder.of()
                                .input(input)
                                .fluid(fluid, amount)
                                .unlockedBy()
                                .save(c, suffix);
        }

        protected void meltRecipes(RecipeOutput c, String suffix, ItemLike input, FluidRegister fluid, int amount) {
                MelterRecipeBuilder.of()
                                .input(input)
                                .fluid(fluid, amount)
                                .unlockedBy()
                                .save(c, suffix);

                FoundryBuilder.of()
                                .input(input)
                                .fluid(fluid, amount)
                                .unlockedBy()
                                .save(c, suffix);
        }

        protected void castingRecipe(RecipeOutput c, String suffix, FluidRegister fluid, int amount, Item mold,
                        Item result, boolean consume) {
                var cast = CasterRecipeBuilder.of()
                                .fluid(fluid, amount)
                                .input(mold)
                                .output(result)
                                .unlockedBy();
                if (consume)
                        cast.consumeItemInput();

                cast.save(c);

                var table = CastingTableBuilder.of()
                                .fluid(fluid, amount)
                                .input(mold)
                                .output(result)
                                .unlockedBy();

                if (consume)
                        table.consumeItemInput();

                table.save(c);
        }

        protected void moltenRecipes(RecipeOutput c, String suffix, Item mold, Item result, TagKey<Item> input,
                        FluidRegister fluid, int amount) {
                meltRecipes(c, suffix, input, fluid, amount);
                castingRecipe(c, suffix, fluid, amount, mold, result, false);
        }

        protected void moltenRecipes(RecipeOutput c, String suffix, Item mold, Item result, ItemLike input,
                        FluidRegister fluid, int amount) {
                meltRecipes(c, suffix, input, fluid, amount);
                castingRecipe(c, suffix, fluid, amount, mold, result, false);
        }

        protected void gemDustProcess(RecipeOutput c, Item gem, Item dust) {

                crushing(c, gem, dust);

                CompressorRecipeBuilder.of()
                                .input(dust)
                                .delay(80)
                                .output(gem)
                                .unlockedBy()
                                .save(c);
        }

        protected void crushing(RecipeOutput c, ItemLike gem, ItemLike dust) {
                crushing(c, gem.asItem(), dust.asItem(), 1);
        }

        protected void crushing(RecipeOutput c, Item gem, Item dust) {
                crushing(c, gem, dust, 1);
        }

        protected void crushing(RecipeOutput c, Item gem, Item dust, int mace_count) {
                crushing(c, gem, dust, mace_count, 1);
        }

        protected void crushing(RecipeOutput c, Item gem, Item dust, int mace_count, int quern_count) {
                crushing(c, gem, dust, mace_count, quern_count, ItemStack.EMPTY, 0);
        }

        protected void crushing(RecipeOutput c, Item gem, Item dust, int mace_count, int quern_count,
                        Item mace_secondary, float chance) {
                crushing(c, gem, dust, mace_count, quern_count, x.item(mace_secondary), chance);
        }

        protected void crushing(RecipeOutput c, Item gem, Item dust, int mace_count, int quern_count,
                        ItemStack mace_secondary, float chance) {

                QuernMillingBuilder.of().input(gem)
                                .output(dust, quern_count)
                                .unlockedBy().save(c, "_from_" + x.path(gem));

                var macerator = MaceratorRecipeBuilder.of()
                                .input(gem)
                                .delay(80)
                                .output(dust, mace_count);

                if (mace_secondary != null && !mace_secondary.isEmpty() && chance > 0)
                        macerator.secondary(mace_secondary, chance);

                macerator.unlockedBy().save(c, "_from_" + x.path(gem));
        }

        protected void crushing(RecipeOutput c, TagKey<Item> gem, Item dust) {
                crushing(c, gem, dust, 1);
        }

        protected void crushing(RecipeOutput c, TagKey<Item> gem, Item dust, int mace_count) {
                crushing(c, gem, dust, mace_count, 1);
        }

        protected void crushing(RecipeOutput c, TagKey<Item> gem, Item dust, int mace_count, int quern_count) {
                crushing(c, gem, dust, mace_count, quern_count, ItemStack.EMPTY, 0);
        }

        protected void crushing(RecipeOutput c, TagKey<Item> gem, Item dust, int mace_count, int quern_count,
                        Item mace_secondary, float chance) {
                crushing(c, gem, dust, mace_count, quern_count, x.item(mace_secondary), chance);
        }

        protected void crushing(RecipeOutput c, TagKey<Item> gem, Item dust, int mace_count, int quern_count,
                        ItemStack mace_secondary, float chance) {

                QuernMillingBuilder.of().input(gem)
                                .output(dust, quern_count)
                                .unlockedBy().save(c);

                var macerator = MaceratorRecipeBuilder.of()
                                .input(gem)
                                .delay(80)
                                .output(dust, mace_count);

                if (mace_secondary != null && !mace_secondary.isEmpty() && chance > 0)
                        macerator.secondary(mace_secondary, chance);

                macerator.unlockedBy().save(c);
        }

        protected static void brickDryingRecipe(RecipeOutput c, Item ball, Block block,
                        Item brick) {
                DryableBricksBuilder.of()
                                .input(ball)
                                .block(block)
                                .output(brick)
                                .unlockedBy()
                                .save(c);

                CompressorRecipeBuilder.of()
                                .input(ball)
                                .delay(20)
                                .energy(100)
                                .catalyst(zItems.MOLD_INGOT.get())
                                .output(brick)
                                .unlockedBy()
                                .save(c);

                SimpleCookingRecipeBuilder.smelting(x.ingredient(ball), MISC, brick, 0.1F, 200)
                                .unlockedBy(getHasName(ball), has(brick))
                                .save(c, ID + ":" + x.path(brick) + "_from_" + x.path(ball)
                                                + "_smelting");
        }

        protected static void twoByTwoPacker(RecipeOutput c, ItemLike output, TagKey<Item> tag) {
                ShapedRecipeBuilder.shaped(MISC, output)
                                .define('#', tag)
                                .pattern("##")
                                .pattern("##")
                                .unlockedBy(ID, has(tag))
                                .save(c);
        }

        protected static void twoByTwoPacker(RecipeOutput c, ItemLike i, ItemLike o, String e) {
                ShapedRecipeBuilder.shaped(MISC, i, 1).define('#', o).pattern("##").pattern("##")
                                .unlockedBy(getHasName(o), has(o)).save(c, e);
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

        protected static void packUnpack(RecipeOutput c, ItemLike unpacked, ItemLike packed, boolean isSmall) {
                ShapelessRecipeBuilder.shapeless(MISC, unpacked, isSmall ? 4 : 9)
                                .requires(packed)

                                .unlockedBy(getHasName(packed), has(packed))
                                .save(c, ID + ":" + x.path((Item) unpacked) + "_unpack" + (isSmall ? "_4" : "_9"));

                simplePacked(c, unpacked.asItem(), packed.asItem(), isSmall);
        }

        @Deprecated
        protected static void simplePacked(RecipeOutput c, Item input, Item output) {
                simplePacked(c, input, output, true);
        }

        protected static void simplePacked(RecipeOutput c, Item input, Item output, boolean isSmall) {
                var temp = ShapedRecipeBuilder.shaped(MISC, output)
                                .define('#', input)
                                .pattern("##" + (!isSmall ? "#" : "")).pattern("##" + (!isSmall ? "#" : ""));

                if (!isSmall)
                        temp = temp.pattern("###");

                temp.unlockedBy(getHasName(input), has(input))
                                .save(c, ID + ":" + getConversionRecipeName(output,
                                                input));

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
                                .unlockedBy("tag_" + getItemName(result), has(material))
                                .save(c, extra);
        }

        protected static void stonecutter(RecipeOutput c, ItemLike result, TagKey<Item> material,
                        String extra) {
                stonecutter(c, result, material, 1, extra);
        }

        protected static void stonecutter(RecipeOutput c, ItemLike result, TagKey<Item> material) {
                stonecutter(c, result, material, 1, ID + ":" + getItemName(result) + "_from_stonecutting");
        }

        protected static void stonecutter(RecipeOutput c, ItemLike result, ItemLike material, String extra) {
                stonecutter(c, result, material, 1, extra);
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

        protected static void cross(RecipeOutput c, ItemLike result, ItemLike material, ItemLike middle) {
                ShapedRecipeBuilder.shaped(BUILDING_BLOCKS, result, 4)
                                .define('#', material)
                                .define('A', middle)
                                .pattern(" # ")
                                .pattern("#A#")
                                .pattern(" # ")
                                .unlockedBy(getHasName(material), has(material))
                                .save(c);
        }

        protected static void cross(RecipeOutput c, ItemLike result, ItemLike material, TagKey<Item> middle) {
                ShapedRecipeBuilder.shaped(BUILDING_BLOCKS, result, 4)
                                .define('#', material)
                                .define('A', middle)
                                .pattern(" # ")
                                .pattern("#A#")
                                .pattern(" # ")
                                .unlockedBy(getHasName(material), has(material))
                                .save(c);
        }

}
