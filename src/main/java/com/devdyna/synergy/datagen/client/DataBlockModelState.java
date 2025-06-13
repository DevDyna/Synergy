package com.devdyna.synergy.datagen.client;

import static com.devdyna.synergy.Main.ID;

import com.devdyna.synergy.init.builder._core.nodeType;
import com.devdyna.synergy.init.builder._core.pipeType;
import com.devdyna.synergy.init.types.zBlocks;
import com.devdyna.synergy.utils.DataGenUtil;

import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.VariantBlockStateBuilder;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class DataBlockModelState extends BlockStateProvider {

    public DataBlockModelState(PackOutput o, ExistingFileHelper f) {
        super(o, ID, f);
    }

    @Override
    protected void registerStatesAndModels() {
        demosimpleBlock(zBlocks.SPRINKLER.get());
        node(zBlocks.NODE.get());
        pipe(zBlocks.PIPE.get());
        // deposits();

        crop(zBlocks.RICE.get(), 7);
        unstableCrop(zBlocks.CAVE_WHEAT.get());
        unstableCrop(zBlocks.CORTINARIUS_MUSHROOM.get());
        unstableCrop(zBlocks.COTTON.get());
        unstableCrop(zBlocks.ELF_CUP_MUSHROOM.get());
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

    private void crop(Block b,int state) {
        var name = b.getDescriptionId().replace("block." + ID + ".", "");

        var model = getVariantBuilder(b).partialState().with(CropBlock.AGE, 0).modelForState()
                .modelFile(models().crop("crops/" + name + "/0", modLoc("block/crops/" + name + "/0"))
                        .renderType("minecraft:cutout"))
                .addModel();

        for (int index = 1; index <= state; index++)
            model.partialState().with(CropBlock.AGE, index).modelForState().modelFile(models()
                    .crop("crops/" + name + "/" + index, modLoc("block/crops/" + name + "/" + index))
                    .renderType("minecraft:cutout"))
                    .addModel();
                    
    }

    /**
     * TODO new CropBlock builder
     * @Deprecated
     */
    public void unstableCrop(Block b){
        var name = b.getDescriptionId().replace("block." + ID + ".", "");

         getVariantBuilder(b).partialState()
            .with(CropBlock.AGE, 0).modelForState().modelFile(models().crop("crops/" + name + "/0", modLoc("block/crops/" + name + "/0")).renderType("minecraft:cutout")).addModel()
            .partialState().with(CropBlock.AGE, 1).modelForState().modelFile(models().crop("crops/" + name + "/1", modLoc("block/crops/" + name + "/1")).renderType("minecraft:cutout")).addModel()
            .partialState().with(CropBlock.AGE, 2).modelForState().modelFile(models().crop("crops/" + name + "/1", modLoc("block/crops/" + name + "/1")).renderType("minecraft:cutout")).addModel()
            .partialState().with(CropBlock.AGE, 3).modelForState().modelFile(models().crop("crops/" + name + "/2", modLoc("block/crops/" + name + "/2")).renderType("minecraft:cutout")).addModel()
            .partialState().with(CropBlock.AGE, 4).modelForState().modelFile(models().crop("crops/" + name + "/3", modLoc("block/crops/" + name + "/3")).renderType("minecraft:cutout")).addModel()
            .partialState().with(CropBlock.AGE, 5).modelForState().modelFile(models().crop("crops/" + name + "/3", modLoc("block/crops/" + name + "/3")).renderType("minecraft:cutout")).addModel()
            .partialState().with(CropBlock.AGE, 6).modelForState().modelFile(models().crop("crops/" + name + "/4", modLoc("block/crops/" + name + "/4")).renderType("minecraft:cutout")).addModel()
            .partialState().with(CropBlock.AGE, 7).modelForState().modelFile(models().crop("crops/" + name + "/5", modLoc("block/crops/" + name + "/5")).renderType("minecraft:cutout")).addModel();
    }


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
