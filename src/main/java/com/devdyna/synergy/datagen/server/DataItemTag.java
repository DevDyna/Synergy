package com.devdyna.synergy.datagen.server;

import java.util.concurrent.CompletableFuture;

import com.devdyna.synergy.Main;
import com.devdyna.synergy.api.utils.ClazzUtil;
import com.devdyna.synergy.api.utils.DataGenUtil;
import com.devdyna.synergy.api.utils.x;
import com.devdyna.synergy.init.types.*;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredHolder;

@SuppressWarnings("null")
public class DataItemTag extends ItemTagsProvider {

        public DataItemTag(PackOutput o, CompletableFuture<HolderLookup.Provider> p,
                        CompletableFuture<TagLookup<Block>> b,
                        ExistingFileHelper h) {
                super(o, p, b, Main.ID, h);
        }

        @Override
        protected void addTags(Provider p) {

                copy(zBlockTag.MACHINES, zItemTag.MACHINES);

                copy(zBlockTag.CAN_SUSTAIN_BLUE_CUP, zItemTag.CAN_SUSTAIN_BLUE_CUP);
                copy(zBlockTag.CAN_SUSTAIN_CAVE_WHEAT, zItemTag.CAN_SUSTAIN_CAVE_WHEAT);
                copy(zBlockTag.CAN_SUSTAIN_COTTON, zItemTag.CAN_SUSTAIN_COTTON);
                copy(zBlockTag.CAN_SUSTAIN_RICE, zItemTag.CAN_SUSTAIN_RICE);
                copy(zBlockTag.CAN_SUSTAIN_VIOLET_WEBCAP,
                                zItemTag.CAN_SUSTAIN_VIOLET_WEBCAP);
                copy(zBlockTag.INFESTED_BLOCKS, zItemTag.INFESTED_BLOCKS);

                tag(zItemTag.STONE_SLABS).add(Items.STONE_SLAB, Items.SMOOTH_STONE_SLAB);

                tag(zItemTag.AZALEA_BUSHES).add(Items.FLOWERING_AZALEA, Items.AZALEA);

                tag(Tags.Items.SEEDS)
                                .add(zItems.RICE_SEED.get(), zItems.AZALEA_SEEDS.get(), zItems.COTTON_SEEDS.get(),
                                                zItems.CAVE_WHEAT_SEEDS.get());

                tag(ItemTags.CHICKEN_FOOD).add(zItems.RICE_SEED.get(), zItems.AZALEA_SEEDS.get(),
                                zItems.COTTON_SEEDS.get(),
                                zItems.CAVE_WHEAT_SEEDS.get());

                tag(ItemTags.PARROT_FOOD).add(zItems.RICE_SEED.get(), zItems.AZALEA_SEEDS.get(),
                                zItems.COTTON_SEEDS.get(),
                                zItems.CAVE_WHEAT_SEEDS.get());

                tag(ItemTags.VILLAGER_PLANTABLE_SEEDS).add(zItems.RICE_SEED.get(), zItems.AZALEA_SEEDS.get(),
                                zItems.COTTON_SEEDS.get(),
                                zItems.CAVE_WHEAT_SEEDS.get());

                tag(Tags.Items.CROPS).add(zItems.RICE_SEED.get(),
                                zItems.COTTON.get(), zItems.BLUE_CUP_MUSHROOM.get(),
                                zItems.VIOLET_WEBCAP_MUSHROOM.get());

                tag(Tags.Items.CROPS_WHEAT).add(zItems.CAVE_WHEAT_SEEDS.get());
                tag(zItemTag.CROP_AZALEA).add(zItems.SMALL_AZALEA_ROOTS.get(), zItems.SMALL_AZALEA_LEAF.get());

                tag(Tags.Items.MUSHROOMS)
                                .add(zItems.BLUE_CUP_MUSHROOM.get(), zItems.VIOLET_WEBCAP_MUSHROOM.get());

                tag(zItemTag.CROP_BLUE_CUP).add(zItems.BLUE_CUP_MUSHROOM.get());
                tag(zItemTag.CROP_COTTON).add(zItems.COTTON.get());
                tag(zItemTag.CROP_RICE).add(zItems.RICE_SEED.get());
                tag(zItemTag.CROP_VIOLET_WEBCAP).add(zItems.VIOLET_WEBCAP_MUSHROOM.get());
                tag(zItemTag.CROP_CAVE_WHEAT).add(zItems.CAVE_WHEAT_SEEDS.get());

                tag(Tags.Items.FERTILIZERS).add(zItems.AMETHYST_MIXTURE.get(), zItems.BONE_MEAL_MIXTURE.get(),
                                zItems.GLOWSTONE_MIXTURE.get());

                tag(zItemTag.TOOLS_INTERACTIVE)
                                .add(zItems.CONFIGURATOR.get(), zItems.SOLDERING_GUN.get());

                tag(zItemTag.AMERICIUM).add(zItems.AMERICIUM.get());
                tag(zItemTag.BERKELIUM).add(zItems.BERKELIUM.get());
                tag(zItemTag.CALIFORNIUM).add(zItems.CALIFORNIUM.get());
                tag(zItemTag.CURIUM).add(zItems.CURIUM.get());
                tag(zItemTag.NEPTUNIUM).add(zItems.NEPTUNIUM.get());
                tag(zItemTag.PLUTONIUM).add(zItems.PLUTONIUM.get());
                tag(zItemTag.THORIUM).add(zItems.THORIUM.get());
                tag(zItemTag.URANIUM).add(zItems.URANIUM.get());

                tag(zItemTag.COAL_LIKE).add(Items.COAL, Items.CHARCOAL);

                tag(zItemTag.MOB_DROP)
                                .add(DataGenUtil.getItems(zItems.zMobDrop));

                copy(zBlockTag.COOLERS, zItemTag.COOLERS);
                copy(zBlockTag.MODERATORS, zItemTag.MODERATORS);

                tag(Tags.Items.DUSTS).add(DataGenUtil.getItems(zItems.zDusts));

                tag(Tags.Items.DUSTS).add(zItems.SAWDUST.get());

                tag(zItemTag.DUST_AMETHYST).add(zItems.AMETHYST_DUST.get());
                tag(zItemTag.DUST_ANCIENT_DEBRIS).add(zItems.ANCIENT_DEBRIS_DUST.get());
                tag(zItemTag.DUST_COAL).add(zItems.CARBON_DUST.get());
                tag(zItemTag.DUST_COPPER).add(zItems.COPPER_DUST.get());
                tag(zItemTag.DUST_DIAMOND).add(zItems.DIAMOND_DUST.get());
                tag(zItemTag.DUST_EMERALD).add(zItems.EMERALD_DUST.get());
                tag(zItemTag.DUST_GOLD).add(zItems.GOLD_DUST.get());
                tag(zItemTag.DUST_IRON).add(zItems.IRON_DUST.get());
                tag(zItemTag.DUST_LAPIS).add(zItems.LAPIS_DUST.get());
                tag(zItemTag.DUST_QUARTZ).add(zItems.QUARTZ_DUST.get());
                tag(zItemTag.SAWDUST).add(zItems.SAWDUST.get()).addOptionalTag(zItemTag.SAWDUST2);

                tag(ItemTags.PIGLIN_LOVED).add(zItems.GOLD_DUST.get(), zItems.GOLD_FOIL.get(), zItems.GOLD_PLATE.get());

                tag(Tags.Items.INGOTS)
                                .add(DataGenUtil.getItems(zItems.zIngots));

                tag(Tags.Items.NUGGETS)
                                .add(DataGenUtil.getItems(zItems.zNuggets));

                tag(zItemTag.INGOT_WROUGHT_IRON).add(zItems.WROUGHT_IRON_INGOT.get());
                tag(zItemTag.INGOT_STEEL).add(zItems.STEEL_INGOT.get());
                tag(zItemTag.INGOT_ADVANCEDALLOY).add(zItems.ADVANCED_ALLOY_INGOT.get());
                tag(zItemTag.NUGGET_STEEL).add(zItems.STEEL_NUGGET.get());
                tag(zItemTag.NUGGET_WROUGHT_IRON).add(zItems.WROUGHT_IRON_NUGGET.get());
                tag(zItemTag.NUGGET_ADVANCEDALLOY).add(zItems.ADVANCED_ALLOY_NUGGET.get());
                tag(zItemTag.NUGGET_COPPER).add(zItems.COPPER_NUGGET.get());

                tag(zItemTag.FOILS)
                                .add(DataGenUtil.getItems(zItems.zFoils));

                tag(zItemTag.FOIL_COPPER).add(zItems.COPPER_FOIL.get());
                tag(zItemTag.FOIL_GOLD).add(zItems.GOLD_FOIL.get());
                tag(zItemTag.FOIL_SILVER).add(zItems.SILVER_FOIL.get());
                tag(zItemTag.FOIL_IRON).add(zItems.IRON_FOIL.get());

                tag(zItemTag.PLATES)
                                .add(DataGenUtil.getItems(zItems.zPlates));

                tag(zItemTag.PLATE_WROUGHT_IRON).add(zItems.WROUGHT_IRON_PLATE.get());
                tag(zItemTag.PLATE_COAL).add(zItems.CARBON_PLATE.get());
                tag(zItemTag.PLATE_COPPER).add(zItems.COPPER_PLATE.get());
                tag(zItemTag.PLATE_SILVER).add(zItems.SILVER_PLATE.get());
                tag(zItemTag.PLATE_GOLD).add(zItems.GOLD_PLATE.get());
                tag(zItemTag.PLATE_IRON).add(zItems.IRON_PLATE.get());
                tag(zItemTag.PLATE_STEEL).add(zItems.STEEL_PLATE.get());
                tag(zItemTag.PLATE_ADVANCED_ALLOY).add(zItems.ADVANCED_ALLOY_PLATE.get());
                tag(zItemTag.PLATE_AQUAMARINE).add(zItems.AQUAMARINE_PLATE.get());

                tag(zItemTag.CAVE_WHEAT_PLANT)
                                .add(
                                                zItems.CAVE_WHEAT_SEEDS.get(),
                                                zBlocks.WILD_CAVE_WHEAT.get().asItem());
                tag(zItemTag.RICE_PLANT)
                                .add(
                                                zItems.RICE_SEED.get(),
                                                zBlocks.WILD_RICE.get().asItem());
                tag(zItemTag.COTTON_PLANT)
                                .add(
                                                zItems.COTTON_SEEDS.get(),
                                                zBlocks.WILD_COTTON.get().asItem());

                tag(zItemTag.URN_MIXTURES).add(zItems.AMETHYST_MIXTURE.get(),
                                zItems.GLOWSTONE_MIXTURE.get());

                tag(Tags.Items.GEMS).add(DataGenUtil.getItems(zItems.zGems));

                tag(zItemTag.GEMS_AQUAMARINE).add(zItems.AQUAMARINE.get());
                tag(zItemTag.GEMS_SILICON).add(zItems.SILICON.get());

                tag(zItemTag.METAL_NUGGETS)
                                .addTag(Tags.Items.NUGGETS)
                                .remove(x.rl("create", "experience_nugget"));

                tag(zItemTag.MUSHROOM_RESULT).add(zItems.VIOLET_WEBCAP_MUSHROOM.get(), zItems.BLUE_CUP_MUSHROOM.get());
                tag(zItemTag.MUSHROOM_SEED).add(zItems.VIOLET_WEBCAP_SPORE.get(), zItems.BLUE_CUP_SPORE.get());

                tag(zItemTag.PLANT_SEED).add(zItems.RICE_SEED.get(), zItems.AZALEA_SEEDS.get(),
                                zItems.COTTON_SEEDS.get(), zItems.CAVE_WHEAT_SEEDS.get());

                tag(zItemTag.SILICON).add(zItems.SILICON.get());

                tag(zItemTag.INGOT_NICKEL).add(zItems.NICKEL_INGOT.get());
                tag(zItemTag.INGOT_SILVER).add(zItems.SILVER_INGOT.get());
                tag(zItemTag.INGOT_IRIDIUM).add(zItems.IRIDIUM_INGOT.get());
                tag(zItemTag.INGOT_PLATINUM).add(zItems.PLATINUM_INGOT.get());
                tag(zItemTag.INGOT_OSMIUM).add(zItems.OSMIUM_INGOT.get());
                tag(zItemTag.INGOT_TIN).add(zItems.TIN_INGOT.get());
                tag(zItemTag.INGOT_LEAD).add(zItems.LEAD_INGOT.get());
                tag(zItemTag.INGOT_URANIUM).add(zItems.URANIUM_INGOT.get());
                tag(zItemTag.INGOT_ALUMINUM).add(zItems.ALUMINUM_INGOT.get());

                tag(zItemTag.DUST_NICKEL).add(zItems.NICKEL_DUST.get());
                tag(zItemTag.DUST_SILVER).add(zItems.SILVER_DUST.get());
                tag(zItemTag.DUST_IRIDIUM).add(zItems.IRIDIUM_DUST.get());
                tag(zItemTag.DUST_PLATINUM).add(zItems.PLATINUM_DUST.get());
                tag(zItemTag.DUST_OSMIUM).add(zItems.OSMIUM_DUST.get());
                tag(zItemTag.DUST_TIN).add(zItems.TIN_DUST.get());
                tag(zItemTag.DUST_LEAD).add(zItems.LEAD_DUST.get());
                tag(zItemTag.DUST_URANIUM).add(zItems.URANIUM_DUST.get());
                tag(zItemTag.DUST_ALUMINUM).add(zItems.ALUMINUM_DUST.get());
                tag(zItemTag.DUST_SULFUR).add(zItems.SULFUR_DUST.get());

                tag(zItemTag.NODES_RETRIEVAL)
                                .add(zBlocks.ITEM_RETRIEVAL.get().asItem(), zBlocks.ENERGY_RETRIEVAL.get().asItem(),
                                                zBlocks.FLUID_RETRIEVAL.get().asItem());

                tag(zItemTag.NODES_TRANSFER)
                                .add(zBlocks.ITEM_TRANSFER.get().asItem(), zBlocks.ENERGY_TRANSFER.get().asItem(),
                                                zBlocks.FLUID_TRANSFER.get().asItem());

                tag(zItemTag.NODES_PROVIDER)
                                .add(zBlocks.ITEM_PROVIDER.get().asItem(), zBlocks.FLUID_PROVIDER.get().asItem());


                tag(zItemTag.NODE_ENERGY).add(zBlocks.ENERGY_RETRIEVAL.get().asItem(),zBlocks.ENERGY_TRANSFER.get().asItem());
                tag(zItemTag.NODE_FLUID).add(zBlocks.FLUID_PROVIDER.get().asItem(),zBlocks.FLUID_RETRIEVAL.get().asItem(),zBlocks.FLUID_TRANSFER.get().asItem());
                tag(zItemTag.NODE_ITEM).add(zBlocks.ITEM_PROVIDER.get().asItem(),zBlocks.ITEM_RETRIEVAL.get().asItem(),zBlocks.ITEM_TRANSFER.get().asItem());

                tag(zItemTag.NODES)
                                .addTag(zItemTag.NODES_TRANSFER)
                                .addTag(zItemTag.NODES_PROVIDER)
                                .addTag(zItemTag.NODES_RETRIEVAL);

                tag(zItemTag.PIPE).add(zBlocks.PIPE.get().asItem());

                tag(Tags.Items.BRICKS).add(
                                zItems.PACKED_MUD_BRICK.get(),
                                zItems.BLAST_BRICK.get(),
                                zItems.FIRECLAY_BRICK.get()

                );

                tag(zItemTag.SUPPLEMENTARIES_BRICKS)
                                .add(

                                                zItems.PACKED_MUD_BRICK.get(),
                                                zItems.FIRECLAY_BRICK.get(),
                                                zItems.BLAST_BRICK.get()

                                );

                tag(zItemTag.PLACEABLE).add(
                                Items.CLAY_BALL,
                                zItems.PACKED_MUD_BALL.get(),
                                zItems.FIRECLAY_BALL.get(),
                                zItems.CLAY_MIXTURE_BALL.get()

                );

                tag(zItemTag.GEARS).add(DataGenUtil.getItems(zItems.zGears));
                tag(zItemTag.GEAR_WOODEN).add(zItems.WOODEN_GEAR.get());
                tag(zItemTag.GEAR_COPPER).add(zItems.COPPER_GEAR.get());
                tag(zItemTag.GEAR_GOLD).add(zItems.GOLD_GEAR.get());
                tag(zItemTag.GEAR_IRON).add(zItems.IRON_GEAR.get());
                tag(zItemTag.GEAR_LEAD).add(zItems.LEAD_GEAR.get());
                tag(zItemTag.GEAR_NICKEL).add(zItems.NICKEL_GEAR.get());
                tag(zItemTag.GEAR_STEEL).add(zItems.STEEL_GEAR.get());
                tag(zItemTag.GEAR_TIN).add(zItems.TIN_GEAR.get());

                tag(zItemTag.DYE_RESET).addTag(Tags.Items.DYES_BLACK);
                tag(zItemTag.DYE_MAX).addTag(Tags.Items.DYES_WHITE);
                tag(zItemTag.DYE_RED).addTag(Tags.Items.DYES_RED);
                tag(zItemTag.DYE_GREEN).addTag(Tags.Items.DYES_GREEN);
                tag(zItemTag.DYE_BLUE).addTag(Tags.Items.DYES_BLUE);

                tag(zItemTag.COLOR_APPLICABLE)
                                .addTag(zItemTag.DYE_RESET)
                                .addTag(zItemTag.DYE_MAX)
                                .addTag(zItemTag.DYE_RED)
                                .addTag(zItemTag.DYE_GREEN)
                                .addTag(zItemTag.DYE_BLUE);

                tag(zItemTag.REMOVE_ENTITY_GROWING)
                                .add(zItems.VENOM_SAC.get());

                tag(zItemTag.ADD_ENTITY_GROWING).add(Items.GLISTERING_MELON_SLICE);

                tag(zItemTag.REPEATERS).add(zBlocks.INVERTED_REPEATER.get().asItem(), Items.REPEATER);

                tag(zItemTag.COILS)
                                .add(DataGenUtil.getItems(zItems.zCoils));

                tag(zItemTag.COIL_COPPER).add(zItems.COPPER_COIL.get());
                tag(zItemTag.COIL_GOLD).add(zItems.GOLD_COIL.get());
                tag(zItemTag.COIL_IRON).add(zItems.IRON_COIL.get());
                tag(zItemTag.COIL_SILVER).add(zItems.SILVER_COIL.get());

                tag(zItemTag.UPGRADES)
                                .add(
                                                ClazzUtil.getAllzItems(zItems.zMachineUpgrades)
                                                                .stream()
                                                                .map(DeferredHolder::get)
                                                                .toArray(Item[]::new));

                tag(zItemTag.UPGRADE_ENERGY).add(zItems.UPGRADE_ENERGY.get());
                tag(zItemTag.UPGRADE_SPEED).add(zItems.UPGRADE_SPEED.get());
                tag(zItemTag.UPGRADE_LUCK).add(zItems.UPGRADE_LUCK.get());
                tag(zItemTag.UPGRADE_FLUID).add(zItems.UPGRADE_FLUID.get());

                tag(zItemTag.VOID_BOX_DENY)
                                .add(zBlocks.VOID_BOX.get().asItem());

                tag(zItemTag.MOLDS)
                                .add(ClazzUtil.getAllzItems(zItems.zMolds).stream()
                                                .map(DeferredHolder::get)
                                                .toArray(Item[]::new));

                tag(Tags.Items.STORAGE_BLOCKS)
                                .add(zBlocks.ADVANCED_ALLOY_BLOCK.get().asItem(), zBlocks.STEEL_BLOCK.get().asItem(),
                                                zBlocks.WROUGHT_IRON_BLOCK.get().asItem());

                tag(zItemTag.BLOCK_ADVANCED_ALLOY)
                                .add(zBlocks.ADVANCED_ALLOY_BLOCK.get().asItem());

                tag(zItemTag.BLOCK_STEEL)
                                .add(zBlocks.STEEL_BLOCK.get().asItem());

                tag(zItemTag.BLOCK_WROUGHT_IRON)
                                .add(zBlocks.WROUGHT_IRON_BLOCK.get().asItem());

                tag(zItemTag.CAST_IRON_BLOCKS)
                                .add(zBlocks.CAST_IRON_BLOCK.get().asItem(), zBlocks.CAST_IRON_TILES.get().asItem());

                tag(zItemTag.CHOPPER_ENERGY_UPGRADE)
                                .add(zItems.RED_BATTERY.get());

                tag(zItemTag.CHOPPER_AREA_INCREASE)
                                .add(zItems.FLOPPY_DISK.get());

                tag(zItemTag.MIXTURE_ALTERNATIVE)
                                .addTag(zItemTag.DUST_SULFUR)
                                .add(Items.BLAZE_POWDER);

                tag(zItemTag.RESISTOR_INGREDIENT)
                                .addTag(zItemTag.GEMS_AQUAMARINE)
                                .add(zItems.GUARDIAN_SCALE.get());

                tag(zItemTag.DEEPSLATE_STONES)
                                .add(Items.DEEPSLATE, Items.COBBLED_DEEPSLATE);

                tag(zItemTag.ORE_DEPOSITS)
                                .add(ClazzUtil.getAllzItems(zItems.zDepositOres).stream()
                                                .map(DeferredHolder::get)
                                                .toArray(Item[]::new));

                tag(zItemTag.DIRTS)
                .add(Items.DIRT,Items.ROOTED_DIRT,Items.COARSE_DIRT);

        }

}