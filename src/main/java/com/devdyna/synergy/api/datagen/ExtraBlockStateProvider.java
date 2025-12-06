package com.devdyna.synergy.api.datagen;

import static com.devdyna.synergy.Main.ID;

import java.util.List;
import java.util.function.BiFunction;
import com.devdyna.synergy.api.zFluid;
import com.devdyna.synergy.api.node.nodeType;
import com.devdyna.synergy.api.pipe.pipeType;
import com.devdyna.synergy.api.reactor.ControllerProperties;
import com.devdyna.synergy.init.builder.DryableBricks;
import com.devdyna.synergy.init.builder.reactor.controller.ReactorControllerBlock;
import com.devdyna.synergy.init.types.zBlocks;
import com.devdyna.synergy.utils.DataGenUtil;
import com.devdyna.synergy.utils.x;

import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.data.models.blockstates.PropertyDispatch.TriFunction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredHolder;

public abstract class ExtraBlockStateProvider extends BlockStateProvider {

        public ExtraBlockStateProvider(PackOutput output, String modid, ExistingFileHelper exFileHelper) {
                super(output, modid, exFileHelper);
        }

        protected void brick(DeferredHolder<Block, Block> b, ResourceLocation working, ResourceLocation dried) {
                getVariantBuilder(b.get()).forAllStates((state) -> {
                        return ConfiguredModel.builder().modelFile(
                                        models().withExistingParent(
                                                        b.getRegisteredName() + (state.getValue(DryableBricks.DRIED)
                                                                        ? "_dried"
                                                                        : "_not_dried"),
                                                        modLoc("block/base_brick"))
                                                        .texture("texture",
                                                                        (state.getValue(DryableBricks.DRIED) ? dried
                                                                                        : working)))
                                        .rotationY(((int) (state
                                                        .getValue(BlockStateProperties.HORIZONTAL_FACING)).toYRot()
                                                        + 180) % 360)
                                        .build();
                });
        }

        protected void tinyChestAll(DeferredHolder<Block, Block> b, String texture) {
                tinyChest(b, texture, texture, texture, texture, texture, texture, texture);
        }

        protected void tinyChest(DeferredHolder<Block, Block> b, String particles, String north, String south,
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

        protected void block(DeferredHolder<Block, Block> b, String rl) {
                simpleBlock(b.get(),
                                models().withExistingParent(b.getRegisteredName(),
                                                modLoc(rl)));
        }

        protected void simpleBlockDecorative(DeferredHolder<Block, Block> b) {
                simpleFullBlock(b, "decorative/");
        }

        protected void simpleFullBlock(DeferredHolder<Block, Block> b, String prefix) {
                simpleFlexibleBlock(b, b.getRegisteredName().replace(ID + ":", prefix));
        }

        protected void simpleFlexibleBlock(DeferredHolder<Block, Block> b, String loc) {
                simpleBlock(b.get(), models().cubeAll(b.getRegisteredName(),
                                modLoc("block/" + loc)));
        }

        protected void CoolerBlock(DeferredHolder<Block, Block> b, ResourceLocation below) {
                simpleBlock(b.get(), models().withExistingParent(b.getRegisteredName(), modLoc("block/double_layer"))
                                .texture("top", "block/reactor/cooler/casing")
                                .texture("below", below));
        }

        protected void simpleBiState(DeferredHolder<Block, Block> b, String location) {

                getVariantBuilder(b.get()).forAllStates((state) -> {
                        String front = state.getValue(BlockStateProperties.ENABLED) ? "on" : "off";
                        return ConfiguredModel.builder().modelFile(
                                        models().cubeAll(b.getRegisteredName() + "_" + front,
                                                        modLoc("block/" + location + front)))
                                        .build();
                });
        }

        protected void simpleBiState(DeferredHolder<Block, Block> b, String location, Property<Boolean> prop,
                        String statusOn, String statusOff) {

                getVariantBuilder(b.get()).forAllStates((state) -> {
                        String front = state.getValue(prop) ? statusOn : statusOff;
                        return ConfiguredModel.builder().modelFile(
                                        models().cubeAll(b.getRegisteredName() + "_" + front,
                                                        modLoc("block/" + location + front)))
                                        .build();
                });
        }

        protected void moderatorBlock(DeferredHolder<Block, Block> b, ResourceLocation below) {

                getVariantBuilder(b.get()).forAllStates((state) -> {
                        String front = state.getValue(BlockStateProperties.ENABLED) ? "on" : "off";
                        return ConfiguredModel.builder().modelFile(
                                        models().withExistingParent(b.getRegisteredName() + "_" + front,
                                                        modLoc("block/double_layer"))
                                                        .texture("top", "block/reactor/moderator/base_" + front)
                                                        .texture("below", below))
                                        .build();
                });
        }

        protected void reactorController(DeferredHolder<Block, Block> b) {

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

        protected void pipe(Block b) {
                var model = getMultipartBuilder(b);
                var core = models().getExistingFile(modLoc("block/pipe/basic/core"));
                var pipe = models().getExistingFile(modLoc("block/pipe/basic/pipe"));
                pipeType.getPipeMultiPart(b, model, core, pipe);
        }

        protected void node(Block b, String color) {
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

        // protected void deposits(){
        // //
        // zBlocks.deposits.forEach(c->DataGenUtil.BlockwithParent(zBlocks.AZALEA.get(),
        // this, ID + ":block/pebbles/_base").texture("block", ""));
        // }

        protected void crop(Block b, int max, boolean isCrop, IntegerProperty property) {
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
        protected void growPlantWithVariants(Block b, int max, boolean isCrop, IntegerProperty property) {
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

        protected void rotableBlock(Block b) {
                rotableBlock(b, x.rl(b));
        }

        protected void rotableBlock(Block b, ResourceLocation path) {
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

        protected void crossORcropStatic(Block b, boolean isCrop, String texturePath) {
                var model = getVariantBuilder(b);
                model.partialState().addModels(ConfiguredModel.builder()
                                .modelFile(DataGenUtil.crossORcrop(this, isCrop, x.path(b),
                                                modLoc(texturePath)))
                                .build());
        }

        protected void fluid(zFluid fluid) {
                getVariantBuilder(fluid.getBlock().get())
                                .partialState().modelForState()
                                .modelFile(models().getBuilder("block/" + fluid.getId())
                                                .texture("particle", fluid.getStill()))
                                .addModel();
        }

        protected void decorativeBlocks() {

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
        }

        protected void laserBlocks() {

                simpleBlock(zBlocks.LASER_LENS.get(), models().getExistingFile(modLoc("block/laser_lens")));

                getVariantBuilder(zBlocks.LASER_MIRROR.get()).forAllStates((state) -> {

                        return ConfiguredModel.builder().modelFile(
                                        models().getExistingFile(modLoc("block/laser_mirror")))
                                        .rotationY(state.getValue(BlockStateProperties.INVERTED) ? 90 : 0)
                                        .build();
                });

                getVariantBuilder(zBlocks.LASER_SENSOR.get()).forAllStates((state) -> {

                        return ConfiguredModel.builder().modelFile(
                                        models().getExistingFile(modLoc("block/laser_sensor/"
                                                        + (state.getValue(BlockStateProperties.ENABLED)
                                                                        ? "on"
                                                                        : "off"))))
                                        .rotationY(state.getValue(BlockStateProperties.INVERTED) ? 90 : 0)
                                        .build();
                });

                getVariantBuilder(zBlocks.LASER_MACHINE.get())
                                .forAllStates(state -> ConfiguredModel.builder()
                                                .modelFile(models()
                                                                .getExistingFile(modLoc("block/laser_machine_gun/"
                                                                                + (state.getValue(
                                                                                                BlockStateProperties.ENABLED)
                                                                                                                ? "on"
                                                                                                                : "off"))))
                                                .rotationY(((int) state.getValue(BlockStateProperties.HORIZONTAL_FACING)
                                                                .toYRot() + 180) % 360)
                                                .build());

                getVariantBuilder(zBlocks.ELECTROMAGNETIC_ROTOR.get())
                                .forAllStates(state -> ConfiguredModel.builder()
                                                .modelFile(models()
                                                                .getExistingFile(modLoc("block/laser_rotor/"
                                                                                + (state.getValue(
                                                                                                BlockStateProperties.ENABLED)
                                                                                                                ? "on"
                                                                                                                : "off"))))
                                                .rotationY(((int) state.getValue(BlockStateProperties.HORIZONTAL_FACING)
                                                                .toYRot() + 180) % 360)
                                                .build());

        }

        protected void repeater(Block b) {

                var type = x.id(b.asItem()).getPath().replace("_repeater", "") ;

                var index = type.equals("pulse") ? "2" : "1";

                var model = getMultipartBuilder(b);

                var delays = List.of(1, 2, 3, 4);
                var powered = List.of(true, false);

                var dirs = Direction.Plane.HORIZONTAL.stream().toList();

                for (Direction dir : dirs) {
                        for (boolean status : powered) {

                                model.part().modelFile(models()
                                                .withExistingParent("plate_" + type + "_" +
                                                                (status ? "on" : "off"),
                                                                modLoc("block/redstone/plate_"
                                                                                + (status ? "on" : "off")))
                                                .texture("top", modLoc("block/redstone/" + type + "_"
                                                                + (status ? "on" : "off"))))
                                                .rotationY(dir.get2DDataValue() * 90)

                                                .addModel()

                                                .condition(BlockStateProperties.HORIZONTAL_FACING, dir)
                                                .condition(BlockStateProperties.POWERED, status);

                                model.part().modelFile(models().getExistingFile(modLoc(
                                                "block/redstone/output/" + index + "_"
                                                                + (status ? "on" : "off"))))
                                                .rotationY(dir.get2DDataValue() * 90).addModel()
                                                .condition(BlockStateProperties.HORIZONTAL_FACING, dir)
                                                .condition(BlockStateProperties.POWERED, status);

                                for (int delay : delays) {

                                        model.part().modelFile(models().getExistingFile(
                                                        modLoc("block/redstone/input/" + delay + "_"
                                                                        + (status ? "on" : "off"))))
                                                        .rotationY(dir.get2DDataValue() * 90).addModel()
                                                        .condition(BlockStateProperties.HORIZONTAL_FACING, dir)
                                                        .condition(BlockStateProperties.POWERED, status)
                                                        .condition(BlockStateProperties.DELAY, delay);

                                }

                        }
                }

        }

        protected void repeater(Block b,
                        BiFunction<Boolean, Direction, ModelFile> plate,
                        BiFunction<Boolean, Direction, ModelFile> output,
                        TriFunction<Boolean, Direction, Integer, ModelFile> input) {

                var type = x.id(b.asItem()).getPath().replace("_repeater", "");

                if (plate == null)
                        plate = (status, dir) -> models()
                                        .withExistingParent("plate_" + type + "_" +
                                                        (status ? "on" : "off"),
                                                        modLoc("block/redstone/plate_"
                                                                        + (status ? "on" : "off")))
                                        .texture("top", modLoc("block/redstone/" + type + "_"
                                                        + (status ? "on" : "off")));

                if (output == null)
                        output = (status, dir) -> models().getExistingFile(modLoc(
                                        "block/redstone/output/" + type + "_"
                                                        + (status ? "on" : "off")));

                if (input == null)
                        input = (status, dir, delay) -> models().getExistingFile(
                                        modLoc("block/redstone/input/" + delay + "_"
                                                        + (status ? "on" : "off")));

                var model = getMultipartBuilder(b);

                var delays = List.of(1, 2, 3, 4);
                var powered = List.of(true, false);

                var dirs = Direction.Plane.HORIZONTAL.stream().toList();

                for (Direction dir : dirs) {
                        for (boolean status : powered) {

                                model.part().modelFile(plate.apply(status, dir))
                                                .rotationY(dir.get2DDataValue() * 90)

                                                .addModel()

                                                .condition(BlockStateProperties.HORIZONTAL_FACING, dir)
                                                .condition(BlockStateProperties.POWERED, status);

                                model.part().modelFile(output.apply(status, dir))
                                                .rotationY(dir.get2DDataValue() * 90).addModel()
                                                .condition(BlockStateProperties.HORIZONTAL_FACING, dir)
                                                .condition(BlockStateProperties.POWERED, status);

                                for (int delay : delays) {

                                        model.part().modelFile(input.apply(status, dir, delay))
                                                        .rotationY(dir.get2DDataValue() * 90).addModel()
                                                        .condition(BlockStateProperties.HORIZONTAL_FACING, dir)
                                                        .condition(BlockStateProperties.POWERED, status)
                                                        .condition(BlockStateProperties.DELAY, delay);

                                }

                        }
                }

        }

}
