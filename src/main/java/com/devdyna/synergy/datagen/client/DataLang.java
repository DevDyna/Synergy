package com.devdyna.synergy.datagen.client;

import static com.devdyna.synergy.Main.ID;

import com.devdyna.synergy.Main;
import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.init.types.zBlocks;
import com.devdyna.synergy.init.types.zItems;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.neoforged.neoforge.registries.DeferredHolder;

public class DataLang extends LanguageProvider {

    public DataLang(PackOutput o) {
        super(o, ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        add(Main.ID + "." + zStatic.CreativeTab, "Synergy Stuff");
        zBlocks.zBlockItem.getEntries().forEach(b -> addBlock(b, named(b)));
        zBlocks.zBlock.getEntries().forEach(b -> addBlock(b, named(b)));
        zBlocks.zCrop.getEntries().forEach(b -> addBlock(b, named(b)));
        zItems.zItem.getEntries().forEach(b -> addItem(b, named(b)));
        zItems.zTool.getEntries().forEach(b -> addItem(b, named(b)));
        zItems.zCropExtra.getEntries().forEach(b -> addItem(b, named(b)));
        zItems.zFoods.getEntries().forEach(b -> addItem(b, named(b)));
        zItems.zSeeds.getEntries().forEach(b -> addItem(b, named(b)));

    }

    private String named(DeferredHolder<?, ?> b) {

        StringBuilder result = new StringBuilder();
        for (String word : b.getRegisteredName().replace(ID + ":", "").replaceAll("_", " ").split(" ")) {
            if (!word.isEmpty()) {
                result.append(Character.toUpperCase(word.charAt(0)))
                        .append(word.substring(1))
                        .append(" ");
            }
        }
        return result.toString().trim();
    }

}
