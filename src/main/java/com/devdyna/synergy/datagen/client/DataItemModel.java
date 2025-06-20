package com.devdyna.synergy.datagen.client;

import static com.devdyna.synergy.Main.ID;

import java.util.List;

import com.devdyna.synergy.init.types.zBlocks;
import com.devdyna.synergy.init.types.zItems;
import com.devdyna.synergy.utils.DataGenUtil;

import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class DataItemModel extends ItemModelProvider {

    public DataItemModel(PackOutput o, ExistingFileHelper f) {
        super(o, ID, f);
    }

    List<Block> demo = List.of();

    @Override
    protected void registerModels() {

        zItems.zItem.getEntries().forEach(item -> DataGenUtil.itemModel(item.get(), this));
        zItems.zCropExtra.getEntries().forEach(item -> DataGenUtil.itemModel(item.get(), this, "plants/results/"));
        zItems.zFoods.getEntries().forEach(item -> DataGenUtil.itemModel(item.get(), this, "foods/"));
        zItems.zSeeds.getEntries().forEach(item -> DataGenUtil.itemModel(item.get(), this, "plants/seeds/"));

        demo.forEach(e -> DataGenUtil.itemBlockwithParent(e, this, ID + ":block/dynamo/off"));


        DataGenUtil.itemBlockwithParent(zBlocks.SPRINKLER.get(), this, ID + ":block/"+DataGenUtil.getPath(zBlocks.SPRINKLER.get()));

        withExistingParent(zBlocks.PIPE.getRegisteredName(), modLoc("block/pipe/basic/item_model"));
        withExistingParent(zBlocks.ITEM_TRANSFER.getRegisteredName(), modLoc("block/node/basic/item_model"));
        withExistingParent(zBlocks.ITEM_GEN.getRegisteredName(), modLoc("block/node/basic/item_model"));

        zItems.zTool.getEntries().forEach(item -> DataGenUtil.itemModel(item.get(), this, "tools/"));
    
    
    
    }

}
