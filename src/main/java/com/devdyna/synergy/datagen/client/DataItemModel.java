package com.devdyna.synergy.datagen.client;

import static com.devdyna.synergy.Main.ID;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Stream;

import com.devdyna.synergy.init.types.zBlocks;
import com.devdyna.synergy.init.types.zItems;
import com.devdyna.synergy.utils.DataGenUtil;

import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredHolder;

public class DataItemModel extends ItemModelProvider {

    public DataItemModel(PackOutput o, ExistingFileHelper f) {
        super(o, ID, f);
    }

    @Override
    protected void registerModels() {

        zItems.zTool.getEntries().forEach(item -> DataGenUtil.itemTool(item.get(), this));

        zItems.zItem.getEntries().forEach(item -> DataGenUtil.itemModel(item.get(), this));

        zBlocks.zBlockItem.getEntries()
                .forEach(e -> DataGenUtil.itemBlockwithParent(e.get(), this, ID + ":block/dynamo/off"));

    }

}
