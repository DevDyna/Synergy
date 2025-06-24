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

        private String TIP_COLOR = "§7";

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
                zBlocks.zWildCrop.getEntries().forEach(b -> addBlock(b, named(b)));
                zBlocks.zDecorative.getEntries().forEach(b -> addBlock(b, named(b)));

                // tools
                add(Main.ID + "." + zStatic.PipeStuff.tools.visualizer,
                                TIP_COLOR + "Show the debug vision of I/O foreach node");
                add(Main.ID + "." + zStatic.PipeStuff.tools.refactorizer,
                                TIP_COLOR + "Force update blockstates of any #synergy:can_connect");
                add(Main.ID + "." + zStatic.Items.smasher, TIP_COLOR + "Quick break any #synergy:mashable");
                add(Main.ID + "." + zStatic.Items.wooden_crook, TIP_COLOR + "Increase the chance to obtain saplings");

                // pipe stuff
                add(Main.ID + "." + zStatic.PipeStuff.pipe + ".desc",
                                TIP_COLOR + "Can connected at any #synergy:can_connect");
                add(Main.ID + "." + zStatic.PipeStuff.pipe + ".safe",
                                TIP_COLOR + "Safe for decoration");
                add(Main.ID + "." + zStatic.PipeStuff.pipe + ".extend",
                                TIP_COLOR + "Can be extended using pipes");

                add(Main.ID + "." + zStatic.PipeStuff.nodes.type_transfer,
                                TIP_COLOR + "Extract and deposit stuff from containers");

                add(Main.ID + "." + zStatic.PipeStuff.nodes.type_provider,
                                TIP_COLOR + "Generate resources and deposit it");

                //wild crops warning
                add(Main.ID + "." + zStatic.Wild.WILD + ".tip",
                                TIP_COLOR + "Item-Form unobtainable");


                                //TODO sprinkler tip and guideme

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
