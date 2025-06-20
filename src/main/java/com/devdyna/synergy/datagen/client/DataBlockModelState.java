package com.devdyna.synergy.datagen.client;

import static com.devdyna.synergy.Main.ID;

import com.devdyna.synergy.init.builder._core.crops.BaseShortCropBlock;
import com.devdyna.synergy.init.builder._core.pipes.nodeType;
import com.devdyna.synergy.init.builder._core.pipes.pipeType;
import com.devdyna.synergy.init.types.zBlocks;
import com.devdyna.synergy.utils.DataGenUtil;

import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class DataBlockModelState extends BlockStateProvider {

        public DataBlockModelState(PackOutput o, ExistingFileHelper f) {
                super(o, ID, f);
        }

        @Override
        protected void registerStatesAndModels() {
                sprinkler(zBlocks.SPRINKLER.get());
                node(zBlocks.ITEM_TRANSFER.get());
                node(zBlocks.ITEM_GEN.get());
                pipe(zBlocks.PIPE.get());
                // deposits();

                crop(zBlocks.RICE.get(), 7, CropBlock.AGE);
                crop(zBlocks.CAVE_WHEAT.get(), 5, BaseShortCropBlock.AGE);
                growPlant(zBlocks.VIOLET_WEBCAP_MUSHROOM.get(), 5, BaseShortCropBlock.AGE);
                crop(zBlocks.COTTON.get(), 5, BaseShortCropBlock.AGE);
                growPlantWithVariants(zBlocks.BLUE_CUP_MUSHROOM.get(), 5, BaseShortCropBlock.AGE);

                crossBlock(zBlocks.WILD_CAVE_WHEAT.get(), "block/crops/cave_wheat/5");
                                crossBlock(zBlocks.WILD_COTTON.get(), "block/crops/cotton/5");
                                crossBlock(zBlocks.WILD_RICE.get(), "block/crops/rice/5");

        }

        private void pipe(Block b) {
                var model = getMultipartBuilder(b);
                var core = models().getExistingFile(modLoc("block/pipe/basic/core"));
                var pipe = models().getExistingFile(modLoc("block/pipe/basic/pipe"));
                pipeType.getPipeMultiPart(b, model, core, pipe);
        }

        private void node(Block b) {
                var model = getMultipartBuilder(b);
                var core = models().getExistingFile(modLoc("block/pipe/basic/core"));
                var pipe = models().getExistingFile(modLoc("block/pipe/basic/pipe"));
                var node = models().getExistingFile(modLoc("block/node/basic/plate"));
                nodeType.getNodeMultiPart(b, model, core, pipe, node);
        }

        // private void deposits(){
        // //
        // zBlocks.deposits.forEach(c->DataGenUtil.BlockwithParent(zBlocks.AZALEA.get(),
        // this, ID + ":block/pebbles/_base").texture("block", ""));
        // }

        private void crop(Block b, int max, IntegerProperty property) {
                var name = b.getDescriptionId().replace("block." + ID + ".", "");

                var model = getVariantBuilder(b).partialState().with(property, 0).modelForState()
                                .modelFile(models().crop("crops/" + name + "/0", modLoc("block/crops/" + name + "/0"))
                                                .renderType("minecraft:cutout"))
                                .addModel();

                for (int index = 1; index <= max; index++)
                        model.partialState().with(property, index).modelForState().modelFile(models()
                                        .crop("crops/" + name + "/" + index,
                                                        modLoc("block/crops/" + name + "/" + index))
                                        .renderType("minecraft:cutout"))
                                        .addModel();
        }

        private void growPlant(Block b, int max, IntegerProperty property) {
                var name = b.getDescriptionId().replace("block." + ID + ".", "");

                var model = getVariantBuilder(b).partialState().with(property, 0).modelForState()
                                .modelFile(models().cross("crops/" + name + "/0", modLoc("block/crops/" + name + "/0"))
                                                .renderType("minecraft:cutout"))
                                .addModel();

                for (int index = 1; index <= max; index++)
                        model.partialState().with(property, index).modelForState().modelFile(models()
                                        .cross("crops/" + name + "/" + index,
                                                        modLoc("block/crops/" + name + "/" + index))
                                        .renderType("minecraft:cutout"))
                                        .addModel();
        }

        // TODO make it dynamic
        private void growPlantWithVariants(Block b, int max, IntegerProperty property) {
                var name = b.getDescriptionId().replace("block." + ID + ".", "");

                var model = getVariantBuilder(b);

                model.partialState().with(property, 0)
                                .addModels(ConfiguredModel.builder()
                                                .modelFile(models()
                                                                .cross("crops/" + name + "/0/0",
                                                                                modLoc("block/crops/" + name + "/0/0"))
                                                                .renderType("minecraft:cutout"))
                                                .nextModel()
                                                .modelFile(models()
                                                                .cross("crops/" + name + "/1/0",
                                                                                modLoc("block/crops/" + name + "/1/0"))
                                                                .renderType("minecraft:cutout"))
                                                .build());

                for (int index = 1; index <= max; index++)
                        model.partialState().with(property, index)
                                        .addModels(ConfiguredModel.builder()
                                                        .modelFile(models()
                                                                        .cross("crops/" + name + "/0/" + index,
                                                                                        modLoc("block/crops/" + name
                                                                                                        + "/0/"
                                                                                                        + index))
                                                                        .renderType("minecraft:cutout"))
                                                        .nextModel()
                                                        .modelFile(models()
                                                                        .cross("crops/" + name + "/1/" + index,
                                                                                        modLoc("block/crops/" + name
                                                                                                        + "/1/"
                                                                                                        + index))
                                                                        .renderType("minecraft:cutout"))
                                                        .build());
        }

        private void sprinkler(Block b) {
                var model = getVariantBuilder(b);
                model.partialState().addModels(ConfiguredModel.builder()
                                .modelFile(models().getExistingFile(DataGenUtil.getResource(b))).nextModel()
                                .modelFile(models().getExistingFile(DataGenUtil.getResource(b))).rotationY(90)
                                .nextModel()
                                .modelFile(models().getExistingFile(DataGenUtil.getResource(b))).rotationY(180)
                                .nextModel()
                                .modelFile(models().getExistingFile(DataGenUtil.getResource(b))).rotationY(270)

                                .build());
        }

        private void crossBlock(Block b, String texturePath) {
                var fileModelLocation = DataGenUtil.getPath(b);
                var model = getVariantBuilder(b);
                model.partialState().addModels(ConfiguredModel.builder()
                                .modelFile(models().cross(fileModelLocation, modLoc(texturePath))
                                                .renderType("minecraft:cutout"))
                                .build());
        }

        @SuppressWarnings("unused")
        private void demosimpleBlock(Block b) {
                simpleBlock(b,
                                models().getExistingFile(modLoc("block/dynamo/off")));
        }

        @SuppressWarnings("unused")
        private void demoBiState(Block b, BooleanProperty p) {
                DataGenUtil.BiStateBlock(this, b, p, models()
                                .getExistingFile(modLoc("block/dynamo/on")),
                                models().getExistingFile(modLoc("block/dynamo/off")));
        }

}
