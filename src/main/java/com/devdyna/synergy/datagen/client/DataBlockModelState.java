package com.devdyna.synergy.datagen.client;

import static com.devdyna.synergy.Main.ID;

import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.api.blockfactories.plants.builder.BaseShortCropBlock;
import com.devdyna.synergy.api.utils.ClazzUtil;
import com.devdyna.synergy.api.utils.DataGenUtil;
import com.devdyna.synergy.api.utils.x;
import com.devdyna.synergy.datagen.api.ExtraBlockStateProvider;
import com.devdyna.synergy.init.types.zBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class DataBlockModelState extends ExtraBlockStateProvider {

        public DataBlockModelState(PackOutput o, ExistingFileHelper f) {
                super(o, ID, f);
        }

        @Override
        protected void registerStatesAndModels() {

                variantRotatedBlock(zBlocks.SPRINKLER.get());

                tinyChestAll(zBlocks.WOODEN_TINY_CHEST, "block/tiny_block/chest/wooden");
                tinyChestAll(zBlocks.STONE_TINY_CHEST, "block/tiny_block/chest/stone");
                tinyChestAll(zBlocks.ORNATE_TINY_CHEST, "block/tiny_block/chest/ornate");

                block(zBlocks.URN, "block/tiny_block/urn");

                block(zBlocks.QUERN, "block/quern/base");

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
                simpleBlockDecorative(zBlocks.ADOBE);
                simpleBlockDecorative(zBlocks.WROUGHT_IRON_BLOCK);
                simpleBlockDecorative(zBlocks.WAXED_PLANKS);

                simpleBlockDecorative(zBlocks.SMOOTH_ADOBE);
                simpleBlockDecorative(zBlocks.BLAST_BRICKS);

                simpleFullBlock(zBlocks.FIRECLAY_BRICKS.getBricks(), "decorative/");
                simpleFullBlock(zBlocks.FIRECLAY_BRICKS.getTiles(), "decorative/");
                simpleFullBlock(zBlocks.FIRECLAY_BRICK_CRACKED.getBricks(), "decorative/");
                simpleFullBlock(zBlocks.FIRECLAY_BRICK_CRACKED.getTiles(), "decorative/");
                simpleFullBlock(zBlocks.FIRECLAY_BRICK_MOSSY.getBricks(), "decorative/");
                simpleFullBlock(zBlocks.FIRECLAY_BRICK_MOSSY.getTiles(), "decorative/");

                simpleFullBlock(zBlocks.IRON_WOOD.getPlanks(), "decorative/");
                leaveBlock(zBlocks.IRON_WOOD.getLeaves(), "block/decorative/");
                pottedPlant(zBlocks.IRON_WOOD.getFlowerPot(), "block/decorative/ironwood_sapling");
                crossBlock(zBlocks.IRON_WOOD.getSapling(), "block/decorative/ironwood_sapling");

                simpleFlexibleBlock(zBlocks.COOLER_BASE, "machine/nuclear/cooler/base");

                simpleBlockDecorative(zBlocks.CAST_IRON_BLOCK);
                simpleBlockDecorative(zBlocks.CAST_IRON_TILES);
                simpleBlockDecorative(zBlocks.CALCITE_BRICKS);

                simpleFullBlock(zBlocks.HEALER, "");
                simpleFlexibleBlock(zBlocks.REACTOR_FUEL_CELL, "machine/nuclear/fuel_cell");

                CoolerBlock(zBlocks.COPPER_COOLER, mcLoc("block/copper_block"));
                CoolerBlock(zBlocks.GOLD_COOLER, mcLoc("block/gold_block"));
                CoolerBlock(zBlocks.IRON_COOLER, mcLoc("block/iron_block"));
                CoolerBlock(zBlocks.ENDER_COOLER, mcLoc("block/purpur_block"));
                CoolerBlock(zBlocks.FROST_COOLER, mcLoc("block/blue_ice"));
                CoolerBlock(zBlocks.LAPIS_COOLER, mcLoc("block/lapis_block"));
                CoolerBlock(zBlocks.SCULK_COOLER, mcLoc("block/sculk"));
                CoolerBlock(zBlocks.WATER_COOLER, mcLoc("block/ice"));
                CoolerBlock(zBlocks.QUARTZ_COOLER, mcLoc("block/quartz_block_top"));
                CoolerBlock(zBlocks.SHADOW_COOLER, modLoc("block/machine/nuclear/cooler/shadow"));
                CoolerBlock(zBlocks.DIAMOND_COOLER, mcLoc("block/diamond_block"));
                CoolerBlock(zBlocks.EMERALD_COOLER, mcLoc("block/emerald_block"));
                CoolerBlock(zBlocks.REDSTONE_COOLER, mcLoc("block/redstone_block"));
                CoolerBlock(zBlocks.GLOWSTONE_COOLER, mcLoc("block/glowstone"));
                CoolerBlock(zBlocks.NETHERITE_COOLER, mcLoc("block/netherite_block"));

                moderatorBlock(zBlocks.SIMPLE_MODERATOR, mcLoc("block/coal_block"));
                moderatorBlock(zBlocks.ADVANCED_MODERATOR, modLoc("block/machine/nuclear/moderator/advanced_frame"));
                moderatorBlock(zBlocks.ELITE_MODERATOR, modLoc("block/machine/nuclear/moderator/elite_frame"));

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
                                                modLoc("block/machine/frame/basic/side"),
                                                modLoc("block/machine/farming/harvester"),
                                                modLoc("block/machine/frame/basic/bottom"),
                                                modLoc("block/machine/frame/basic/top")));

                horizontalBlock(zBlocks.CASTING_TABLE.get(), models()
                                .getExistingFile(modLoc("block/casting_table")));

                horizontalBlock(zBlocks.FAUCET.get(), models()
                                .getExistingFile(modLoc("block/faucet")));

                directionalBlock(zBlocks.BASIC_MACHINE_FRAME.get(),
                                models().cubeBottomTop(zBlocks.BASIC_MACHINE_FRAME.getRegisteredName(),
                                                modLoc("block/machine/frame/basic/side"),
                                                modLoc("block/machine/frame/basic/bottom"),
                                                modLoc("block/machine/frame/basic/top")));

                directionalBlock(zBlocks.ADVANCED_MACHINE_FRAME.get(),
                                models().cubeBottomTop(zBlocks.ADVANCED_MACHINE_FRAME.getRegisteredName(),
                                                modLoc("block/machine/frame/advanced/side"),
                                                modLoc("block/machine/frame/advanced/bottom"),
                                                modLoc("block/machine/frame/advanced/top")));

                brick(zBlocks.CLAY_BRICK, mcLoc("block/clay"), mcLoc("block/terracotta"));
                brick(zBlocks.PACKED_MUD_BRICK, mcLoc("block/packed_mud"), mcLoc("block/mud_bricks"));
                brick(zBlocks.FIRECLAY_BRICK, modLoc("block/decorative/fireclay"),
                                modLoc("block/decorative/fireclay_bricks"));
                brick(zBlocks.BLAST_BRICK, modLoc("block/decorative/clay_mixture"),
                                modLoc("block/decorative/blast_bricks"));

                repeater(zBlocks.PULSE_REPEATER.get());
                repeater(zBlocks.RECURSIVE_REPEATER.get());
                repeater(zBlocks.INVERTED_REPEATER.get(), (a, b) -> models().withExistingParent("plate_inverted_" +
                                (a ? "on" : "off"),
                                modLoc("block/redstone/plate_"
                                                + (a ? "off" : "on")))
                                .texture("top", mcLoc("block/repeater" + (a ? "_on" : "")))

                                , (a, b) -> models().getExistingFile(modLoc(
                                                "block/redstone/output/1_"
                                                                + (a ? "on" : "off"))),
                                (a, b, c) -> models().getExistingFile(modLoc(
                                                "block/redstone/input/" + c + "_"
                                                                + (a ? "off" : "on"))));

                horizontalBlockBiPhace(zBlocks.FOUNDRY.get(), BlockStateProperties.ENABLED,
                                models().withExistingParent(
                                                zBlocks.FOUNDRY.getRegisteredName() + "_off",
                                                modLoc("block/foundry"))
                                                .texture("front", "synergy:block/foundry/front/off")
                                                .renderType(DataGenUtil.CUTOUT),
                                models().withExistingParent(
                                                zBlocks.FOUNDRY.getRegisteredName() + "_on",
                                                modLoc("block/foundry"))
                                                .texture("front", "synergy:block/foundry/front/on")
                                                .renderType(DataGenUtil.CUTOUT));

                // this require to stay at the end of all !
                decorativeBlocks();
                reactorController();
                laserBlocks();
                machines();
                ClazzUtil.getAllzFluids().forEach(f -> fluid(f));

                horizontalBlock(zBlocks.VOID_BOX.get(),
                                models().withExistingParent(zBlocks.VOID_BOX.getRegisteredName(),
                                                modLoc("block/tiny_block/void_box/block")));

                horizontalBlockBiPhace(zBlocks.LOGIC_BOX.get(), BlockStateProperties.INVERTED,
                                models().withExistingParent(
                                                zBlocks.LOGIC_BOX.getRegisteredName() + "_off",
                                                modLoc("block/tiny_block/base/animated/block"))
                                                .texture("inside", modLoc("block/tiny_block/logic_box/green/inside"))
                                                .texture("all", modLoc("block/tiny_block/logic_box/green/chest"))
                                                .renderType(DataGenUtil.CUTOUT),
                                models().withExistingParent(
                                                zBlocks.LOGIC_BOX.getRegisteredName() + "_on",
                                                modLoc("block/tiny_block/base/animated/block"))
                                                .texture("inside", modLoc("block/tiny_block/logic_box/red/inside"))
                                                .texture("all", modLoc("block/tiny_block/logic_box/red/chest"))
                                                .renderType(DataGenUtil.CUTOUT));

                simpleBlock(zBlocks.SIMPLE_TANK.get(), models().cubeAll(zBlocks.SIMPLE_TANK.getRegisteredName(),
                                modLoc("block/tank/simple")).renderType(DataGenUtil.CUTOUT));

                // simpleBlock(zBlocks.FUEL_TANK.get(), models().cubeAll(zBlocks.FUEL_TANK.getRegisteredName(),
                //                 modLoc("block/tank/fuel")).renderType(DataGenUtil.CUTOUT));

                simpleBlock(zBlocks.ROUTER.get(), models().getExistingFile(x.rl("block/router")));

                simpleBlock(zBlocks.SIMPLE_WATER_GEN.get(),
                                models().withExistingParent(zBlocks.SIMPLE_WATER_GEN.getRegisteredName(),
                                                modLoc("block/triple_layer"))
                                                .texture("top", "block/resource_gen/water")
                                                .texture("middle", "block/resource_gen/overlay/simple")
                                                .texture("below", "block/decorative/wrought_iron_block")
                                                .renderType(DataGenUtil.CUTOUT));

                simpleBlock(zBlocks.ADVANCED_WATER_GEN.get(),
                                models().withExistingParent(zBlocks.ADVANCED_WATER_GEN.getRegisteredName(),
                                                modLoc("block/triple_layer"))
                                                .texture("top", "block/resource_gen/water")
                                                .texture("middle", "block/resource_gen/overlay/advanced")
                                                .texture("below", "block/machine/frame/basic/bottom")
                                                .renderType(DataGenUtil.CUTOUT));

                simpleBlock(zBlocks.ELITE_WATER_GEN.get(),
                                models().withExistingParent(zBlocks.ELITE_WATER_GEN.getRegisteredName(),
                                                modLoc("block/triple_layer"))
                                                .texture("top", "block/resource_gen/water")
                                                .texture("middle", "block/resource_gen/overlay/elite")
                                                .texture("below", "block/machine/frame/advanced/top")
                                                .renderType(DataGenUtil.CUTOUT));

                simpleBlock(zBlocks.SIMPLE_COBBLE_GEN.get(),
                                models().withExistingParent(zBlocks.SIMPLE_COBBLE_GEN.getRegisteredName(),
                                                modLoc("block/triple_layer"))
                                                .texture("top", "block/resource_gen/cobblestone")
                                                .texture("middle", "block/resource_gen/overlay/simple")
                                                .texture("below", "block/decorative/wrought_iron_block")
                                                .renderType(DataGenUtil.CUTOUT));

                simpleBlock(zBlocks.ADVANCED_COBBLE_GEN.get(),
                                models().withExistingParent(zBlocks.ADVANCED_COBBLE_GEN.getRegisteredName(),
                                                modLoc("block/triple_layer"))
                                                .texture("top", "block/resource_gen/cobblestone")
                                                .texture("middle", "block/resource_gen/overlay/advanced")
                                                .texture("below", "block/machine/frame/basic/bottom")
                                                .renderType(DataGenUtil.CUTOUT));

                simpleBlock(zBlocks.ELITE_COBBLE_GEN.get(),
                                models().withExistingParent(zBlocks.ELITE_COBBLE_GEN.getRegisteredName(),
                                                modLoc("block/triple_layer"))
                                                .texture("top", "block/resource_gen/cobblestone")
                                                .texture("middle", "block/resource_gen/overlay/elite")
                                                .texture("below", "block/machine/frame/advanced/top")
                                                .renderType(DataGenUtil.CUTOUT));

                simpleBlock(zBlocks.CRUSHING_TUB.get(), models().getExistingFile(modLoc("block/crushing_tub")));
                simpleBlock(zBlocks.EVAPORATION_BASIN.get(),
                                models().getExistingFile(modLoc("block/evaporation_basin")));

                zStatic.ALL_DRYING_RACKS.forEach(t -> {

                        var log = "_log";
                        var toplog = "_log_top";

                        var id = t.getRegisteredName();
                        var text = id.replace(ID + ":", "").replace("_" + zStatic.Blocks.drying_rack, "");

                        if (text.contains("bamboo")) {
                                log = log.replace("log", "block");
                                toplog = toplog.replace("log", "block");
                        }

                        if (text.contains("crimson") || text.contains("warped")) {
                                log = log.replace("log", "stem");
                                toplog = toplog.replace("log", "stem");
                        }

                        horizontalBlock(t.get(),
                                        models().withExistingParent(id,
                                                        modLoc("block/drying_rack"))
                                                        .texture("side", mcLoc("block/" + text + log))
                                                        .texture("top", mcLoc("block/" + text + toplog))
                                                        .renderType(DataGenUtil.CUTOUT));
                });

                simpleBlock(zBlocks.CHOPPER.get(),
                                models().cubeBottomTop(zBlocks.CHOPPER.getRegisteredName(),
                                                modLoc("block/machine/farming/chopper/side"),
                                                modLoc("block/decorative/wrought_iron_block"),
                                                modLoc("block/machine/farming/chopper/top")));

                directionalBlock(zBlocks.AQUAMARINE_CLUSTER.get(),
                                models().cross(zBlocks.AQUAMARINE_CLUSTER.getRegisteredName(),
                                                modLoc("block/decorative/aquamarine"))
                                                .renderType(DataGenUtil.CUTOUT));

                noModel(zBlocks.ENTITY_WATCHER, x.rl("block/tiny_block/entity_watcher"));

                simpleBlock(zBlocks.FUEL_TANK.get(),models().cubeColumn(zBlocks.FUEL_TANK.getRegisteredName(), modLoc("block/fuel_tank/side"),
                                modLoc("block/fuel_tank/top")).renderType(DataGenUtil.CUTOUT));

                

        }

}
