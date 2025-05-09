package com.devdyna.synergy.datagen.client;

import static com.devdyna.synergy.Main.MODID;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class DataBlockModelState extends BlockStateProvider {

    public DataBlockModelState(PackOutput o, ExistingFileHelper f) {
        super(o, MODID, f);
    }

    @Override
    protected void registerStatesAndModels() {

        // simpleBlock(Blocks.BK.get(),
        //         models().getExistingFile(DataGenUtil.getResource("block/stone")));
    }

}
