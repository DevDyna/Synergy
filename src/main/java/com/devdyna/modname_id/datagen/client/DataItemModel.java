package com.devdyna.modname_id.datagen.client;

import static com.devdyna.modname_id.Main.MODID;

import com.devdyna.modname_id.init.types.zBlocks;
import com.devdyna.modname_id.init.types.zItems;
import com.devdyna.modname_id.utils.DataGenUtil;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class DataItemModel extends ItemModelProvider {

    public DataItemModel(PackOutput o, ExistingFileHelper f) {
        super(o, MODID, f);
    }

    @Override
    protected void registerModels() {

        zItems.zTool.getEntries().forEach(item -> {
            try {
                DataGenUtil.itemTool(item.get(), this);
            } catch (Exception e) {
                System.out.println(e);
            }
        });

        zItems.zItem.getEntries().forEach(item -> {

            try {
                DataGenUtil.itemModel(item.get(), this);
            } catch (Exception e) {
                System.out.println(e);
            }

        });

        zBlocks.zBlockItem.getEntries().forEach(block -> {
            try {
                DataGenUtil.itemBlock(block.get(), this);
            } catch (Exception e) {
                System.out.println(e);
            }
        });

    }

}
