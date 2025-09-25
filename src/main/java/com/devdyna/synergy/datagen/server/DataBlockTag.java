package com.devdyna.synergy.datagen.server;

import java.util.concurrent.CompletableFuture;

import com.devdyna.synergy.Main;
import com.devdyna.synergy.init.types.zBlockTag;
import com.devdyna.synergy.init.types.zBlocks;
import com.devdyna.synergy.init.types.zMultiTags;

import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

@SuppressWarnings({ "unchecked", "null" })

public class DataBlockTag extends BlockTagsProvider {

        public DataBlockTag(PackOutput o, CompletableFuture<Provider> l, ExistingFileHelper f) {
                super(o, l, Main.ID, f);
        }

        @Override
        protected void addTags(Provider p) {

                tag(zBlockTag.LEAVES).addTag(BlockTags.LEAVES);

                tag(zBlockTag.CROPS).addTag(BlockTags.CROPS);

                tag(BlockTags.MINEABLE_WITH_PICKAXE)
                                .addTag(zMultiTags.COOLERS.block())
                                .addTag(zMultiTags.MODERATORS.block())
                                .add(
                                                zBlocks.SPRINKLER.get(),
                                                zBlocks.HARVESTER.get(),
                                                zBlocks.SOLAR_PANEL.get(),
                                                zBlocks.ADOBE.get(),
                                                zBlocks.RUSTIC_METAL.get(),
                                                zBlocks.MACHINE_FRAME.get(),
                                                zBlocks.URN.get(),
                                                zBlocks.ADVANCED_MACHINE_FRAME.get(),
                                                zBlocks.ADVANCED_ALLOY_BLOCK.get(),
                                                zBlocks.STEEL_BLOCK.get(),
                                                zBlocks.HEALER.get(),
                                                zBlocks.COOLER_BASE.get(),
                                                zBlocks.REACTOR_FUEL_CELL.get(),
                                                zBlocks.REACTOR_CONTROLLER.get());

                tag(BlockTags.MINEABLE_WITH_AXE)
                                .add(
                                                zBlocks.WAXED_PLANKS.get(),
                                                zBlocks.WAXED_PLANKS_SLAB.get(),
                                                zBlocks.WAXED_PLANKS_STAIR.get());

                tag(zBlockTag.MUSHROOMS)
                                .add(zBlocks.BLUE_CUP_MUSHROOM.get(), zBlocks.VIOLET_WEBCAP_MUSHROOM.get());

                tag(zBlockTag.PIPE).add(zBlocks.PIPE.get());

                tag(zBlockTag.NODE_RETRIEVAL)
                                .add(zBlocks.ITEM_RETRIEVAL.get(), zBlocks.ENERGY_RETRIEVAL.get());

                tag(zBlockTag.NODE_TRANSFER)
                                .add(zBlocks.ITEM_TRANSFER.get(), zBlocks.ENERGY_TRANSFER.get());

                tag(zBlockTag.NODE_PROVIDER)
                                .add(zBlocks.ITEM_PROVIDER.get());

                tag(zBlockTag.NODE)
                                .addTag(zBlockTag.NODE_TRANSFER)
                                .addTag(zBlockTag.NODE_PROVIDER)
                                .addTag(zBlockTag.NODE_RETRIEVAL);

                tag(zBlockTag.CAN_CONNECT)
                                .addTag(zBlockTag.PIPE)
                                .addTag(zBlockTag.NODE);

                tag(zBlockTag.INFESTED_BLOCKS)
                                .add(Blocks.INFESTED_CHISELED_STONE_BRICKS,
                                                Blocks.INFESTED_COBBLESTONE,
                                                Blocks.INFESTED_CRACKED_STONE_BRICKS,
                                                Blocks.INFESTED_DEEPSLATE,
                                                Blocks.INFESTED_MOSSY_STONE_BRICKS,
                                                Blocks.INFESTED_STONE,
                                                Blocks.INFESTED_STONE_BRICKS);

                tag(zBlockTag.CAN_SUSTAIN_BLUE_CUP)
                                .add(Blocks.DRIPSTONE_BLOCK, Blocks.CLAY, Blocks.CALCITE)
                                .addTags(BlockTags.DIRT, Tags.Blocks.STONES, Tags.Blocks.ORES,
                                                zBlockTag.INFESTED_BLOCKS, Tags.Blocks.GRAVELS);

                tag(zBlockTag.CAN_SUSTAIN_VIOLET_WEBCAP)
                                .add(Blocks.DRIPSTONE_BLOCK, Blocks.CLAY, Blocks.CALCITE)
                                .addTags(BlockTags.DIRT, Tags.Blocks.STONES, Tags.Blocks.ORES,
                                                zBlockTag.INFESTED_BLOCKS, Tags.Blocks.GRAVELS);

                tag(zBlockTag.CAN_SUSTAIN_CAVE_WHEAT)
                                .add(Blocks.DRIPSTONE_BLOCK, Blocks.CLAY, Blocks.CALCITE)
                                .addTags(BlockTags.DIRT, Tags.Blocks.STONES, Tags.Blocks.ORES,
                                                zBlockTag.INFESTED_BLOCKS, Tags.Blocks.GRAVELS);

                tag(zBlockTag.CAN_SUSTAIN_COTTON)
                                .add(Blocks.FARMLAND)
                                .addTag(BlockTags.DIRT);

                tag(zBlockTag.CAN_SUSTAIN_RICE)
                                .add(Blocks.CLAY, Blocks.FARMLAND)
                                .addTags(BlockTags.DIRT, Tags.Blocks.SANDS, Tags.Blocks.GRAVELS,
                                                Tags.Blocks.SANDSTONE_BLOCKS);

                tag(zBlockTag.AOE_RENDER).add(zBlocks.HARVESTER.get(), zBlocks.SPRINKLER.get(),
                                zBlocks.REACTOR_CONTROLLER.get());

                tag(zBlockTag.NODE_RENDER).addTag(zBlockTag.NODE);

                tag(zBlockTag.HARVESTER_TREE_BREAK)
                                .add(Blocks.SHROOMLIGHT)
                                .addTags(BlockTags.COMPLETES_FIND_TREE_TUTORIAL,
                                                BlockTags.MANGROVE_LOGS_CAN_GROW_THROUGH,
                                                BlockTags.MANGROVE_ROOTS_CAN_GROW_THROUGH);

                tag(BlockTags.SWORD_EFFICIENT)
                                .add(zBlocks.AZALEA.get(), zBlocks.CAVE_WHEAT.get(), zBlocks.COTTON.get(),
                                                zBlocks.RICE.get(), zBlocks.BLUE_CUP_MUSHROOM.get(),
                                                zBlocks.VIOLET_WEBCAP_MUSHROOM.get());
                tag(BlockTags.BEE_GROWABLES)
                                .add(zBlocks.AZALEA.get(), zBlocks.CAVE_WHEAT.get(), zBlocks.COTTON.get(),
                                                zBlocks.RICE.get(), zBlocks.BLUE_CUP_MUSHROOM.get(),
                                                zBlocks.VIOLET_WEBCAP_MUSHROOM.get());
                tag(BlockTags.MAINTAINS_FARMLAND)
                                .add(zBlocks.AZALEA.get(), zBlocks.CAVE_WHEAT.get(), zBlocks.COTTON.get(),
                                                zBlocks.RICE.get(), zBlocks.BLUE_CUP_MUSHROOM.get(),
                                                zBlocks.VIOLET_WEBCAP_MUSHROOM.get());
                tag(BlockTags.MINEABLE_WITH_AXE)
                                .add(zBlocks.AZALEA.get(), zBlocks.CAVE_WHEAT.get(), zBlocks.COTTON.get(),
                                                zBlocks.RICE.get(), zBlocks.BLUE_CUP_MUSHROOM.get(),
                                                zBlocks.VIOLET_WEBCAP_MUSHROOM.get());

                tag(zBlockTag.FERMERS_DELIGHT_COMPOSTING).add(zBlocks.BLUE_CUP_MUSHROOM.get(),
                                zBlocks.VIOLET_WEBCAP_MUSHROOM.get());

                tag(zMultiTags.COOLERS.block())
                                .add(zBlocks.zCoolers.getEntries().stream().map(i -> i.get()).toArray(Block[]::new));

                tag(zMultiTags.MODERATORS.block())
                                .add(zBlocks.zModerators.getEntries().stream().map(i -> i.get()).toArray(Block[]::new));

                // solar panels atm will cause rendering issues
                tag(zBlockTag.MASHABLE)
                                .addTag(zBlockTag.CAN_CONNECT)
                                .addTag(zMultiTags.COOLERS.block())
                                .addTag(zMultiTags.MODERATORS.block())
                                .add(
                                                zBlocks.SPRINKLER.get(),
                                                zBlocks.HARVESTER.get(),
                                                zBlocks.URN.get(),
                                                zBlocks.REACTOR_CONTROLLER.get(),
                                                zBlocks.REACTOR_FUEL_CELL.get());

                tag(Tags.Blocks.STORAGE_BLOCKS)
                                .add(zBlocks.ADVANCED_ALLOY_BLOCK.get(), zBlocks.STEEL_BLOCK.get());

                tag(zBlockTag.BLOCK_ADVANCED_ALLOY)
                                .add(zBlocks.ADVANCED_ALLOY_BLOCK.get());

                tag(zBlockTag.BLOCK_STEEL)
                                .add(zBlocks.STEEL_BLOCK.get());

        }

}