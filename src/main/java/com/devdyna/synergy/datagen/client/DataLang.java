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
                // zItems.zItem.getEntries().forEach(b -> addItem(b, named(b)));
                zItems.zTool.getEntries().forEach(b -> addItem(b, named(b)));
                zItems.zCropExtra.getEntries().forEach(b -> addItem(b, named(b)));
                zItems.zFoods.getEntries().forEach(b -> addItem(b, named(b)));
                zItems.zSeeds.getEntries().forEach(b -> addItem(b, named(b)));
                zBlocks.zWildCrop.getEntries().forEach(b -> addBlock(b, named(b)));
                zBlocks.zDecorative.getEntries().forEach(b -> addBlock(b, named(b)));
                zBlocks.zBlockSlab.getEntries().forEach(b -> addBlock(b, named(b)));
                zBlocks.zBlockStair.getEntries().forEach(b -> addBlock(b, named(b)));
                zItems.zCraftingComponents.getEntries().forEach(b -> addItem(b, named(b)));

                // tools
                add(Main.ID + "." + zStatic.Items.configurator + ".tip",
                                TIP_COLOR + "Show the debug vision");

                add(Main.ID + "." + zStatic.Items.configurator + ".modetype",
                                TIP_COLOR + "Mode : ");

                add(Main.ID + "." + zStatic.Items.configurator + ".mode.show_area",
                                "§aMachine AOE");

                add(Main.ID + "." + zStatic.Items.configurator + ".mode.show_track",
                                "§aNode Track");

                add(Main.ID + "." + zStatic.Items.configurator + ".blockpos",
                                TIP_COLOR + "BlockPos : ");

                add(Main.ID + "." + zStatic.Items.refactorizer,
                                TIP_COLOR + "Force update blockstates of any #synergy:can_connect");
                add(Main.ID + "." + zStatic.Items.smasher, TIP_COLOR + "Quick break any #synergy:mashable");
                add(Main.ID + "." + zStatic.Items.wooden_crook, TIP_COLOR + "Increase the chance to obtain saplings");

                add(Main.ID + "." + zStatic.Items.bone_meal_mixture,
                                TIP_COLOR + "Can be used to speed the grow of any plant");

                // pipe stuff
                add(Main.ID + "." + zStatic.PipeStuff.pipe + ".desc",
                                TIP_COLOR + "Can connected at any #synergy:can_connect");

                add(Main.ID + "." + zStatic.PipeStuff.pipe + ".extend",
                                TIP_COLOR + "Can be extended using pipes");

                add(Main.ID + "." + zStatic.PipeStuff.nodes.type_transfer,
                                TIP_COLOR + "Export and deposit stuff from containers");

                add(Main.ID + "." + zStatic.PipeStuff.nodes.type_retrieval,
                                TIP_COLOR + "Import and deposit stuff from containers");

                add(Main.ID + "." + zStatic.PipeStuff.nodes.type_provider,
                                TIP_COLOR + "Generate resources and deposit it");

                // wild crops warning
                add(Main.ID + "." + zStatic.Wild.WILD + ".tip",
                                TIP_COLOR + "Item-Form unobtainable");

                add(Main.ID + "." + zStatic.Items.Batteries.TYPE_BATTERY + ".tip",
                                TIP_COLOR + "Can be used to hand-transfer FE");

                add(Main.ID + "." + zStatic.Items.Batteries.TYPE_BATTERY + ".energy",
                                TIP_COLOR + "Energy Stored: ");

                add(Main.ID + "." + zStatic.Blocks.harvester, TIP_COLOR + "Harvest crops and tree");

                add(Main.ID + "." + zStatic.Blocks.solar_panel,
                                TIP_COLOR + "Produce FE when daytime and can see the sky");

                add(Main.ID + "." + zStatic.tips.SAFE_BUILD, TIP_COLOR + "Safe for decoration");

                add(Main.ID + "." + zStatic.Blocks.sprinkler, TIP_COLOR + "Randomly speed the grow of crops around");

                add(Main.ID + "." + zStatic.tips.INGREDIENT, TIP_COLOR + "Crafting components");

                add(Main.ID + "." + zStatic.Blocks.healer,
                                TIP_COLOR + "Heal and remove fire effect at all entities above it");

                add(Main.ID + "." + zStatic.tips.SHIFT,
                                "§8Hold [§7Shift§8] to see more details");

                add(Main.ID + "." + zStatic.ReactorStuff.cooler+".desc",
                                TIP_COLOR + "DESC");

                add(Main.ID + "." + zStatic.ReactorStuff.cooler + ".off",
                                TIP_COLOR + "Deactive ");

                add(Main.ID + "." + zStatic.ReactorStuff.cooler + ".on",
                                TIP_COLOR + "Active ");

                                add(Main.ID + "." + zStatic.ReactorStuff.moderator+".multiplier" ,
                                TIP_COLOR + "Multiplier");//TODO

                                add(Main.ID + "." + zStatic.ReactorStuff.fuel_cell ,
                                TIP_COLOR + "DESC");//TODO

                                add(Main.ID + "." + zStatic.ReactorStuff.controller ,
                                TIP_COLOR + "DESC");//TODO

                                // add(Main.ID + "." + zStatic.ReactorStuff.port ,
                                // TIP_COLOR + "DESC");//TODO

                                add(Main.ID + "." + zStatic.ReactorStuff.moderator ,
                                TIP_COLOR + "DESC");//TODO

                // add(Main.ID + "." + zStatic.Blocks.fan, TIP_COLOR + "When powered it can
                // generate wind");

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
