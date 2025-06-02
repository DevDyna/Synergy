package com.devdyna.synergy.datagen.client;

import static com.devdyna.synergy.Main.ID;

import com.devdyna.synergy.init.builder._core.nodeType;
import com.devdyna.synergy.init.builder._core.pipeType;
import com.devdyna.synergy.init.types.zBlocks;
import com.devdyna.synergy.utils.DataGenUtil;

import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
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
