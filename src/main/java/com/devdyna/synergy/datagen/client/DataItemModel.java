package com.devdyna.synergy.datagen.client;

import static com.devdyna.synergy.Main.MODID;

import com.devdyna.synergy.init.types.zBlocks;
import com.devdyna.synergy.init.types.zItems;
import com.devdyna.synergy.utils.DataGenUtil;

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
