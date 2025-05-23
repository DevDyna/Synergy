package com.devdyna.synergy.datagen.client;

import static com.devdyna.synergy.Main.ID;

import com.devdyna.synergy.Database;
import com.devdyna.synergy.Main;
import com.devdyna.synergy.init.types.zBlocks;
import com.devdyna.synergy.init.types.zItems;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class DataLang extends LanguageProvider {

    public DataLang(PackOutput o) {
        super(o, ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        add(Main.ID + "."+Database.CreativeTab,"Synergy Stuff");
        zBlocks.zBlockItem.getEntries().forEach(b -> addBlock(b, named(b.getRegisteredName())));
        zBlocks.zBlock.getEntries().forEach(b -> addBlock(b, named(b.getRegisteredName())));
        zItems.zItem.getEntries().forEach(b -> addItem(b, named(b.getRegisteredName())));
        zItems.zTool.getEntries().forEach(b -> addItem(b, named(b.getRegisteredName())));

    }

    private String named(String text) {

        StringBuilder result = new StringBuilder();
        for (String word : text.replace(ID + ":", "").replaceAll("_", " ").split(" ")) {
            if (!word.isEmpty()) {
                result.append(Character.toUpperCase(word.charAt(0)))
                        .append(word.substring(1))
                        .append(" ");
            }
        }
        return result.toString().trim();
    }

}
