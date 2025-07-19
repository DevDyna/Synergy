package com.devdyna.synergy.datagen.client;

import static com.devdyna.synergy.Main.ID;

import com.devdyna.synergy.api.node.nodeType;
import com.devdyna.synergy.api.pipe.pipeType;
import com.devdyna.synergy.api.plants.builder.BaseShortCropBlock;
import com.devdyna.synergy.init.types.zBlocks;
import com.devdyna.synergy.utils.DataGenUtil;

import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class DataBlockModelState extends BlockStateProvider {

        public DataBlockModelState(PackOutput o, ExistingFileHelper f) {
                super(o, ID, f);
        }

        @Override
        protected void registerStatesAndModels() {

                rotableBlock(zBlocks.SPRINKLER.get());
                node(zBlocks.ITEM_TRANSFER.get(), "red");
                node(zBlocks.ITEM_PROVIDER.get(), "green");
                node(zBlocks.ITEM_RETRIEVAL.get(), "aqua");
                pipe(zBlocks.PIPE.get());

                simpleBlockDecorative(zBlocks.ADOBE.get());
                simpleBlockDecorative(zBlocks.RUSTIC_METAL.get());
                simpleBlockDecorative(zBlocks.WAXED_PLANKS.get());

                simpleFullBlock(zBlocks.HEALER.get(), "");

                crop(zBlocks.RICE.get(), 7, true, CropBlock.AGE);
                crop(zBlocks.CAVE_WHEAT.get(), 5, true, BaseShortCropBlock.AGE);
                crop(zBlocks.VIOLET_WEBCAP_MUSHROOM.get(), 5, false, BaseShortCropBlock.AGE);
                crop(zBlocks.COTTON.get(), 5, false, BaseShortCropBlock.AGE);
                growPlantWithVariants(zBlocks.BLUE_CUP_MUSHROOM.get(), 5, false, BaseShortCropBlock.AGE);

                crossORcropStatic(zBlocks.WILD_CAVE_WHEAT.get(), true, "block/crops/cave_wheat/5");
                crossORcropStatic(zBlocks.WILD_COTTON.get(), false, "block/crops/cotton/5");
                crossORcropStatic(zBlocks.WILD_RICE.get(), true, "block/crops/rice/7");

                fan(zBlocks.FAN.get());

                horizontalBlock(zBlocks.HARVESTER.get(), models()
                                .orientableWithBottom(
                                                zBlocks.HARVESTER.get().getDescriptionId().replace("block." + ID + ".",
                                                                ""),
                                                modLoc("block/harvester/side"), modLoc("block/harvester/front"),
                                                modLoc("block/harvester/bottom"), modLoc("block/harvester/top")));

                zBlocks.zBlockSlab.getEntries().forEach(b -> slabBlock((SlabBlock) b.get(), modLoc("block/"
                                + DataGenUtil.getPath(b.get()).replace(ID + ":block/",
                                                "").replace("_slab",
                                                                "")),
                                modLoc("block/decorative/"
                                                + DataGenUtil.getPath(b.get()).replace(ID + ":block/",
                                                                "").replace("_slab",
                                                                                ""))));
                zBlocks.zBlockStair.getEntries()
                                .forEach(b -> stairsBlock((StairBlock) b.get(), modLoc("block/decorative/"
                                                + DataGenUtil.getPath(b.get()).replace(ID + ":block/",
                                                                "").replace("_stair",
                                                                                ""))));

                directionalBlock(zBlocks.MACHINE_FRAME.get(),
                                models().cubeBottomTop(zBlocks.MACHINE_FRAME.getRegisteredName(),
                                                modLoc("block/harvester/side"), modLoc("block/harvester/bottom"),
                                                modLoc("block/harvester/top")));

        }

        private void simpleBlockDecorative(Block b) {
                simpleFullBlock(b, "decorative/");
        }

        private void simpleFullBlock(Block b, String prefix) {
                simpleBlock(b, models().cubeAll(b.getDescriptionId().replace("block." + ID + ".", ""),
                                modLoc("block/" + prefix
                                                + b.getDescriptionId().replace("block." + ID + ".", ""))));
        }

        private void fan(Block b) {
                this.getVariantBuilder(b).forAllStates((state) -> {
                        Direction dir = state.getValue(BlockStateProperties.FACING);
                        boolean isEnable = state.getValue(BlockStateProperties.ENABLED);
                        return ConfiguredModel.builder().modelFile(models().cubeBottomTop(
                                        zBlocks.FAN.get().getDescriptionId().replace("block." + ID + ".", "")
                                                        +"_"+ (isEnable ? "on" : "off"),
                                        modLoc("block/fan/side"), modLoc("block/fan/back"),
                                        modLoc("block/fan/" + (isEnable ? "on" : "off"))))
                                        .rotationX(dir == Direction.DOWN ? 180
                                                        : (dir.getAxis().isHorizontal() ? 90 : 0))
                                        .rotationY(dir.getAxis().isVertical() ? 0
                                                        : ((int) dir.toYRot() + 180) % 360)
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

        private void crossORcropStatic(Block b, boolean isCrop, String texturePath) {
                var model = getVariantBuilder(b);
                model.partialState().addModels(ConfiguredModel.builder()
                                .modelFile(DataGenUtil.crossORcrop(this, isCrop, DataGenUtil.getPath(b),
                                                modLoc(texturePath)))
                                .build());
        }

}
