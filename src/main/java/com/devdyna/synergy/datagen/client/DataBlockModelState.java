package com.devdyna.synergy.datagen.client;

import static com.devdyna.synergy.Main.ID;

import com.devdyna.synergy.api.node.nodeType;
import com.devdyna.synergy.api.pipe.pipeType;
import com.devdyna.synergy.api.plants.builder.BaseShortCropBlock;
import com.devdyna.synergy.api.reactor.ControllerProperties;
import com.devdyna.synergy.init.builder.reactor.controller.ReactorControllerBlock;
import com.devdyna.synergy.init.types.zBlocks;
import com.devdyna.synergy.utils.DataGenUtil;
import com.devdyna.synergy.utils.x;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredHolder;

public class DataBlockModelState extends BlockStateProvider {

        public DataBlockModelState(PackOutput o, ExistingFileHelper f) {
                super(o, ID, f);
        }

        @Override
        protected void registerStatesAndModels() {

                rotableBlock(zBlocks.SPRINKLER.get());

                tinyChestAll(zBlocks.WOODEN_TINY_CHEST, "block/tiny_block/chest/wooden");
                tinyChestAll(zBlocks.STONE_TINY_CHEST, "block/tiny_block/chest/stone");
                tinyChestAll(zBlocks.ORNATE_TINY_CHEST, "block/tiny_block/chest/ornate");
                tinyUrn(zBlocks.URN);

                node(zBlocks.ITEM_TRANSFER.get(), "red");
                node(zBlocks.ITEM_PROVIDER.get(), "green");
                node(zBlocks.ITEM_RETRIEVAL.get(), "aqua");
                pipe(zBlocks.PIPE.get());

                simpleBlockDecorative(zBlocks.ADVANCED_ALLOY_BLOCK);
                simpleBlockDecorative(zBlocks.STEEL_BLOCK);
                simpleBlockDecorative(zBlocks.COOLER_BASE);
                simpleBlockDecorative(zBlocks.ADOBE);
                simpleBlockDecorative(zBlocks.RUSTIC_METAL);
                simpleBlockDecorative(zBlocks.WAXED_PLANKS);

                simpleFullBlock(zBlocks.HEALER, "");
                simpleFullBlock(zBlocks.REACTOR_FUEL_CELL, "reactor/");

                // simpleFlexibleBlock(zBlocks.COOLER_BASE, "reactor/cooler/base");

                CoolerBlock(zBlocks.COPPER_COOLER, mcLoc("block/copper_block"));
                CoolerBlock(zBlocks.GOLD_COOLER, mcLoc("block/gold_block"));
                CoolerBlock(zBlocks.IRON_COOLER, mcLoc("block/iron_block"));
                CoolerBlock(zBlocks.ENDER_COOLER, mcLoc("block/purpur_block"));
                CoolerBlock(zBlocks.FROST_COOLER, mcLoc("block/blue_ice"));
                CoolerBlock(zBlocks.LAPIS_COOLER, mcLoc("block/lapis_block"));
                CoolerBlock(zBlocks.SCULK_COOLER, mcLoc("block/sculk"));
                CoolerBlock(zBlocks.WATER_COOLER, mcLoc("block/ice"));
                CoolerBlock(zBlocks.QUARTZ_COOLER, mcLoc("block/quartz_block_top"));
                CoolerBlock(zBlocks.BIOMASS_COOLER, mcLoc("block/soul_sand"));
                CoolerBlock(zBlocks.DIAMOND_COOLER, mcLoc("block/diamond_block"));
                CoolerBlock(zBlocks.EMERALD_COOLER, mcLoc("block/emerald_block"));
                CoolerBlock(zBlocks.REDSTONE_COOLER, mcLoc("block/redstone_block"));
                CoolerBlock(zBlocks.GLOWSTONE_COOLER, mcLoc("block/glowstone"));
                CoolerBlock(zBlocks.NETHERITE_COOLER, mcLoc("block/netherite_block"));

                simpleBiState(zBlocks.SIMPLE_MODERATOR, "reactor/moderator/simple/");
                simpleBiState(zBlocks.IMPROVED_MODERATOR, "reactor/moderator/improved/");
                simpleBiState(zBlocks.ADVANCED_MODERATOR, "reactor/moderator/advanced/");
                simpleBiState(zBlocks.ELITE_MODERATOR, "reactor/moderator/elite/");

                crop(zBlocks.RICE.get(), 7, true, CropBlock.AGE);
                crop(zBlocks.CAVE_WHEAT.get(), 5, true, BaseShortCropBlock.AGE);
                crop(zBlocks.VIOLET_WEBCAP_MUSHROOM.get(), 5, false, BaseShortCropBlock.AGE);
                crop(zBlocks.COTTON.get(), 5, false, BaseShortCropBlock.AGE);
                growPlantWithVariants(zBlocks.BLUE_CUP_MUSHROOM.get(), 5, false, BaseShortCropBlock.AGE);

                crossORcropStatic(zBlocks.WILD_CAVE_WHEAT.get(), true, "block/crops/cave_wheat/5");
                crossORcropStatic(zBlocks.WILD_COTTON.get(), false, "block/crops/cotton/5");
                crossORcropStatic(zBlocks.WILD_RICE.get(), true, "block/crops/rice/7");

                // fan(zBlocks.FAN.get());

                horizontalBlock(zBlocks.HARVESTER.get(), models()
                                .orientableWithBottom(
                                                zBlocks.HARVESTER.getRegisteredName(),
                                                modLoc("block/harvester/side"), modLoc("block/harvester/front"),
                                                modLoc("block/harvester/bottom"), modLoc("block/harvester/top")));

                reactorController(zBlocks.REACTOR_CONTROLLER);

                zBlocks.zBlockSlab.getEntries().forEach(b -> slabBlock((SlabBlock) b.get(), modLoc("block/"
                                + x.path(b.get()).replace(ID + ":block/",
                                                "").replace("_slab",
                                                                "")),
                                modLoc("block/decorative/"
                                                + x.path(b.get()).replace(ID + ":block/",
                                                                "").replace("_slab",
                                                                                ""))));
                zBlocks.zBlockStair.getEntries()
                                .forEach(b -> stairsBlock((StairBlock) b.get(), modLoc("block/decorative/"
                                                + x.path(b.get()).replace(ID + ":block/",
                                                                "").replace("_stair",
                                                                                ""))));

                directionalBlock(zBlocks.MACHINE_FRAME.get(),
                                models().cubeBottomTop(zBlocks.MACHINE_FRAME.getRegisteredName(),
                                                modLoc("block/harvester/side"), modLoc("block/harvester/bottom"),
                                                modLoc("block/harvester/top")));

                directionalBlock(zBlocks.ADVANCED_MACHINE_FRAME.get(),
                                models().cubeBottomTop(zBlocks.ADVANCED_MACHINE_FRAME.getRegisteredName(),
                                                modLoc("block/reactor/controller/side"),
                                                modLoc("block/reactor/controller/bottom"),
                                                modLoc("block/reactor/controller/top")));

        }

        private void tinyChestAll(DeferredHolder<Block, Block> b, String texture) {
                tinyChest(b, texture, texture, texture, texture, texture, texture, texture);
        }

        private void tinyChest(DeferredHolder<Block, Block> b, String particles, String north, String south,
                        String east, String west, String up, String down) {
                horizontalBlock(b.get(),
                                models().withExistingParent(b.getRegisteredName(),
                                                modLoc("block/tiny_block/chest"))
                                                .texture("particle", particles)
                                                .texture("north", north)
                                                .texture("south", south)
                                                .texture("east", east)
                                                .texture("west", west)
                                                .texture("up", up)
                                                .texture("down", down));
        }

        private void tinyUrn(DeferredHolder<Block, Block> b) {
                simpleBlock(b.get(),
                                models().withExistingParent(b.getRegisteredName(),
                                                modLoc("block/tiny_block/urn")));
        }

        private void simpleBlockDecorative(DeferredHolder<Block, Block> b) {
                simpleFullBlock(b, "decorative/");
        }

        private void simpleFullBlock(DeferredHolder<Block, Block> b, String prefix) {
                simpleFlexibleBlock(b, b.getRegisteredName().replace(ID + ":", prefix));
        }

        private void simpleFlexibleBlock(DeferredHolder<Block, Block> b, String loc) {
                simpleBlock(b.get(), models().cubeAll(b.getRegisteredName(),
                                modLoc("block/" + loc)));
        }

        private void CoolerBlock(DeferredHolder<Block, Block> b, ResourceLocation below) {
                simpleBlock(b.get(), models().withExistingParent(b.getRegisteredName(), modLoc("block/double_layer"))
                                .texture("top", "block/reactor/cooler/casing")
                                .texture("below", below));
        }

        private void simpleBiState(DeferredHolder<Block, Block> b, String location) {

                getVariantBuilder(b.get()).forAllStates((state) -> {
                        String front = state.getValue(BlockStateProperties.ENABLED) ? "on" : "off";
                        return ConfiguredModel.builder().modelFile(
                                        models().cubeAll(b.getRegisteredName() + front, modLoc("block/"+location + front)))
                                        .build();
                });
        }

        private void reactorController(DeferredHolder<Block, Block> b) {

                getVariantBuilder(b.get()).forAllStates((state) -> {

                        String front = switch (state.getValue(ReactorControllerBlock.STATUS)) {
                                case ControllerProperties.WAITING -> "front_off";
                                case ControllerProperties.NOFUEL -> "front_nocell";
                                case ControllerProperties.OVERHEATED -> "front_overheated";
                                case ControllerProperties.PRODUCTION -> "front_on";
                                default -> "";
                        };

                        return ConfiguredModel.builder().modelFile(models()
                                        .orientableWithBottom(b.getRegisteredName() + front,
                                                        modLoc("block/reactor/controller/side"),
                                                        modLoc("block/reactor/controller/" + front),
                                                        modLoc("block/reactor/controller/bottom"),
                                                        modLoc("block/reactor/controller/top")))
                                        .rotationY(
                                                        ((int) (state.getValue(BlockStateProperties.HORIZONTAL_FACING))
                                                                        .toYRot() + 0) % 360)
                                        .build();
                });

        }

        private void pipe(Block b) {
                var model = getMultipartBuilder(b);
                var core = models().getExistingFile(modLoc("block/pipe/basic/core"));
                var pipe = models().getExistingFile(modLoc("block/pipe/basic/pipe"));
                pipeType.getPipeMultiPart(b, model, core, pipe);
        }

        private void node(Block b, String color) {
                var model = getMultipartBuilder(b);
                var core = models().getExistingFile(modLoc("block/pipe/basic/core"));
                var pipe = models().getExistingFile(modLoc("block/pipe/basic/pipe"));
                var node = models()
                                .withExistingParent(b.getDescriptionId().replace("block." + ID + ".", ""),
                                                modLoc("block/node/_template/block"))
                                .texture("pipe", ID + ":block/pipe/black")
                                .texture("node", ID + ":block/node/" + color)
                                .texture("back", ID + ":block/node/back");

                nodeType.getNodeMultiPart(b, model, core, pipe, node);
        }

        // private void deposits(){
        // //
        // zBlocks.deposits.forEach(c->DataGenUtil.BlockwithParent(zBlocks.AZALEA.get(),
        // this, ID + ":block/pebbles/_base").texture("block", ""));
        // }

        private void crop(Block b, int max, boolean isCrop, IntegerProperty property) {
                var name = b.getDescriptionId().replace("block." + ID + ".", "");

                var model = getVariantBuilder(b).partialState().with(property, 0).modelForState()
                                .modelFile(DataGenUtil.crossORcrop(this, isCrop, "crops/" + name + "/0",
                                                modLoc("block/crops/" + name + "/0")))
                                .addModel();

                for (int index = 1; index <= max; index++)
                        model.partialState().with(property, index).modelForState()
                                        .modelFile(DataGenUtil.crossORcrop(this, isCrop, "crops/" + name + "/" + index,
                                                        modLoc("block/crops/" + name + "/" + index)))
                                        .addModel();
        }

        // TODO optimize and make it dynamic
        private void growPlantWithVariants(Block b, int max, boolean isCrop, IntegerProperty property) {
                var name = b.getDescriptionId().replace("block." + ID + ".", "");

                var model = getVariantBuilder(b);

                model.partialState().with(property, 0)
                                .addModels(ConfiguredModel.builder()
                                                .modelFile(DataGenUtil.crossORcrop(this, isCrop,
                                                                "crops/" + name + "/0/0",
                                                                modLoc("block/crops/" + name + "/0/0")))
                                                .nextModel()
                                                .modelFile(DataGenUtil.crossORcrop(this, isCrop,
                                                                "crops/" + name + "/1/0",
                                                                modLoc("block/crops/" + name + "/1/0")))
                                                .build());

                for (int index = 1; index <= max; index++)
                        model.partialState().with(property, index)
                                        .addModels(ConfiguredModel.builder()
                                                        .modelFile(DataGenUtil.crossORcrop(this, isCrop,
                                                                        "crops/" + name + "/0/" + index,
                                                                        modLoc("block/crops/" + name
                                                                                        + "/0/"
                                                                                        + index)))
                                                        .nextModel()
                                                        .modelFile(DataGenUtil.crossORcrop(this, isCrop,
                                                                        "crops/" + name + "/1/" + index,
                                                                        modLoc("block/crops/" + name
                                                                                        + "/1/"
                                                                                        + index)))
                                                        .build());
        }

        private void rotableBlock(Block b) {
                rotableBlock(b, x.rl(b));
        }

        private void rotableBlock(Block b, ResourceLocation path) {
                var model = getVariantBuilder(b);
                model.partialState().addModels(ConfiguredModel.builder()
                                .modelFile(models().getExistingFile(path)).nextModel()
                                .modelFile(models().getExistingFile(path)).rotationY(90)
                                .nextModel()
                                .modelFile(models().getExistingFile(path)).rotationY(180)
                                .nextModel()
                                .modelFile(models().getExistingFile(path)).rotationY(270)
                                .build());
        }

        private void crossORcropStatic(Block b, boolean isCrop, String texturePath) {
                var model = getVariantBuilder(b);
                model.partialState().addModels(ConfiguredModel.builder()
                                .modelFile(DataGenUtil.crossORcrop(this, isCrop, x.path(b),
                                                modLoc(texturePath)))
                                .build());
        }

}
