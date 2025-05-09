package com.devdyna.modname_id.datagen.client;

import static com.devdyna.modname_id.Main.MODID;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.neoforged.neoforge.registries.DeferredHolder;

public class DataLang extends LanguageProvider {

    public DataLang(PackOutput o) {
        super(o, MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        // RegistryToLang("block",Blocks.BK, "hi");
        // RegistryToLang("item",Items.IT, "hi");
    }

    @SuppressWarnings("rawtypes")
    private void RegistryToLang(String type, DeferredHolder d,String text){
            add(type+"."+d.getRegisteredName().replace(":", "."),text);
    }

}
