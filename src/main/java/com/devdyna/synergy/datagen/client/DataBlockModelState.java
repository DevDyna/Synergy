package com.devdyna.synergy.datagen.client;

import static com.devdyna.synergy.Main.ID;

import com.devdyna.synergy.api.datagen.ExtraBlockStateProvider;
import com.devdyna.synergy.api.plants.builder.BaseShortCropBlock;
import com.devdyna.synergy.init.types.zBlocks;
import com.devdyna.synergy.utils.ClazzUtil;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

@SuppressWarnings("null")
public class DataBlockModelState extends ExtraBlockStateProvider {

        public DataBlockModelState(PackOutput o, ExistingFileHelper f) {
                super(o, ID, f);
        }

        @Override
        protected void registerStatesAndModels() {

                ClazzUtil.getAllzFluids().forEach(f -> fluid(f));

                rotableBlock(zBlocks.SPRINKLER.get());

                tinyChestAll(zBlocks.WOODEN_TINY_CHEST, "block/tiny_block/chest/wooden");
                tinyChestAll(zBlocks.STONE_TINY_CHEST, "block/tiny_block/chest/stone");
                tinyChestAll(zBlocks.ORNATE_TINY_CHEST, "block/tiny_block/chest/ornate");

                block(zBlocks.URN, "block/tiny_block/urn");

                block(zBlocks.QUERN, "block/quern/base");
                block(zBlocks.QUERN_MOVING, "block/quern/moving");

                node(zBlocks.ITEM_PROVIDER.get(), "green");
                node(zBlocks.ITEM_RETRIEVAL.get(), "aqua");
                node(zBlocks.ITEM_TRANSFER.get(), "red");

                node(zBlocks.FLUID_PROVIDER.get(), "green");
                node(zBlocks.FLUID_RETRIEVAL.get(), "aqua");
                node(zBlocks.FLUID_TRANSFER.get(), "red");

                // node(zBlocks.ENERGY_PROVIDER.get(), "green");
                node(zBlocks.ENERGY_RETRIEVAL.get(), "aqua");
                node(zBlocks.ENERGY_TRANSFER.get(), "red");

                pipe(zBlocks.PIPE.get());

                simpleBlockDecorative(zBlocks.ADVANCED_ALLOY_BLOCK);
                simpleBlockDecorative(zBlocks.STEEL_BLOCK);
                simpleBlockDecorative(zBlocks.COOLER_BASE);
                simpleBlockDecorative(zBlocks.ADOBE);
                simpleBlockDecorative(zBlocks.RUSTIC_METAL);
                simpleBlockDecorative(zBlocks.WAXED_PLANKS);

                decorativeBlocks();

                simpleFullBlock(zBlocks.HEALER, "");
                simpleFullBlock(zBlocks.REACTOR_FUEL_CELL, "reactor/");

                CoolerBlock(zBlocks.COPPER_COOLER, mcLoc("block/copper_block"));
                CoolerBlock(zBlocks.GOLD_COOLER, mcLoc("block/gold_block"));
                CoolerBlock(zBlocks.IRON_COOLER, mcLoc("block/iron_block"));
                CoolerBlock(zBlocks.ENDER_COOLER, mcLoc("block/purpur_block"));
                CoolerBlock(zBlocks.FROST_COOLER, mcLoc("block/blue_ice"));
                CoolerBlock(zBlocks.LAPIS_COOLER, mcLoc("block/lapis_block"));
                CoolerBlock(zBlocks.SCULK_COOLER, mcLoc("block/sculk"));
                CoolerBlock(zBlocks.WATER_COOLER, mcLoc("block/ice"));
                CoolerBlock(zBlocks.QUARTZ_COOLER, mcLoc("block/quartz_block_top"));
                CoolerBlock(zBlocks.SHADOW_COOLER, modLoc("block/reactor/cooler/shadow"));
                CoolerBlock(zBlocks.DIAMOND_COOLER, mcLoc("block/diamond_block"));
                CoolerBlock(zBlocks.EMERALD_COOLER, mcLoc("block/emerald_block"));
                CoolerBlock(zBlocks.REDSTONE_COOLER, mcLoc("block/redstone_block"));
                CoolerBlock(zBlocks.GLOWSTONE_COOLER, mcLoc("block/glowstone"));
                CoolerBlock(zBlocks.NETHERITE_COOLER, mcLoc("block/netherite_block"));

                moderatorBlock(zBlocks.SIMPLE_MODERATOR, mcLoc("block/coal_block"));
                moderatorBlock(zBlocks.ADVANCED_MODERATOR, modLoc("block/reactor/moderator/advanced_frame"));
                moderatorBlock(zBlocks.ELITE_MODERATOR, modLoc("block/reactor/moderator/elite_frame"));

                crop(zBlocks.RICE.get(), 7, true, CropBlock.AGE);
                crop(zBlocks.CAVE_WHEAT.get(), 5, true, BaseShortCropBlock.AGE);
                crop(zBlocks.VIOLET_WEBCAP_MUSHROOM.get(), 5, false, BaseShortCropBlock.AGE);
                crop(zBlocks.COTTON.get(), 5, false, BaseShortCropBlock.AGE);
                growPlantWithVariants(zBlocks.BLUE_CUP_MUSHROOM.get(), 5, false, BaseShortCropBlock.AGE);

                crossORcropStatic(zBlocks.WILD_CAVE_WHEAT.get(), true, "block/crops/cave_wheat/5");
                crossORcropStatic(zBlocks.WILD_COTTON.get(), false, "block/crops/cotton/5");
                crossORcropStatic(zBlocks.WILD_RICE.get(), true, "block/crops/rice/7");

                horizontalBlock(zBlocks.HARVESTER.get(), models()
                                .orientableWithBottom(
                                                zBlocks.HARVESTER.getRegisteredName(),
                                                modLoc("block/harvester/side"),
                                                modLoc("block/harvester/front"),
                                                modLoc("block/harvester/bottom"),
                                                modLoc("block/harvester/top")));

                reactorController(zBlocks.REACTOR_CONTROLLER);

                directionalBlock(zBlocks.BASIC_MACHINE_FRAME.get(),
                                models().cubeBottomTop(zBlocks.BASIC_MACHINE_FRAME.getRegisteredName(),
                                                modLoc("block/harvester/side"),
                                                modLoc("block/harvester/bottom"),
                                                modLoc("block/harvester/top")));

                directionalBlock(zBlocks.ADVANCED_MACHINE_FRAME.get(),
                                models().cubeBottomTop(zBlocks.ADVANCED_MACHINE_FRAME.getRegisteredName(),
                                                modLoc("block/reactor/controller/side"),
                                                modLoc("block/reactor/controller/bottom"),
                                                modLoc("block/reactor/controller/top")));

                brick(zBlocks.CLAY_BRICK, mcLoc("block/clay"), mcLoc("block/terracotta"));
                brick(zBlocks.PACKED_MUD_BRICK, mcLoc("block/packed_mud"), mcLoc("block/mud_bricks"));

                horizontalBlock(zBlocks.LASER_MACHINE.get(), models()
                                .getExistingFile(modLoc("block/laser_machine")));

                blockMirror();

                simpleBlock(zBlocks.LASER_LENS.get(), models().getExistingFile(modLoc("block/laser_lens")));

                ClazzUtil.getAllMachineTypes()
                                .forEach(m -> {
                                        horizontalBlock((Block) m.block().get(), models()
                                                        .orientableWithBottom(
                                                                        m.id(),
                                                                        modLoc("block/harvester/side"), // TODO rework
                                                                        modLoc("block/machines/" + m.id()),
                                                                        modLoc("block/harvester/bottom"),
                                                                        modLoc("block/harvester/top")));
                                });

        }

}
