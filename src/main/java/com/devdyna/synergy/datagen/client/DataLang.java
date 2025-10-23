package com.devdyna.synergy.datagen.client;

import static com.devdyna.synergy.Main.ID;

import java.util.List;

import com.devdyna.synergy.Main;
import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.init.types.*;
import com.devdyna.synergy.utils.ClazzUtil;

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

                add(Main.ID + "." + zStatic.CreativeTab, "Synergy : MagiTech Collection");

                zFluids.zFluidTypes.getEntries()
                                .forEach(f -> add(f.get().getDescriptionId(), named(f).replace(" Type", "")));

                zBlocks.zBlockItem.getEntries().forEach(b -> addBlock(b, named(b)));
                zBlocks.zBlockFluids.getEntries().forEach(b -> addBlock(b, named(b)));
                zBlocks.zBlock.getEntries().forEach(b -> addBlock(b, named(b)));
                zBlocks.zCrop.getEntries().forEach(b -> addBlock(b, named(b)));

                var denyList = List.of(
                                zItems.SAWDUST);

                ClazzUtil.getAllzItems().forEach(c -> {
                        if (!denyList.contains(c))
                                addItem(c, named(c));
                });

                addItem(zItems.SAWDUST, "Sawdust");

                zItems.zBucketItems.getEntries().forEach(b -> addItem(b, named(b)));

                zBlocks.zWildCrop.getEntries().forEach(b -> addBlock(b, named(b)));
                zBlocks.zDecorative.getEntries().forEach(b -> addBlock(b, named(b)));
                zBlocks.zBlockSlab.getEntries().forEach(b -> addBlock(b, named(b)));
                zBlocks.zBlockStair.getEntries().forEach(b -> addBlock(b, named(b)));
                zBlocks.zModerators.getEntries().forEach(b -> addBlock(b, named(b)));
                zBlocks.zCoolers.getEntries().forEach(b -> addBlock(b, named(b)));

                zBlocks.zHiddenBlock.getEntries().forEach(b -> addBlock(b, named(b).replace(" Block", "")));

                zBlocks.zOnlyBlock.getEntries().forEach(b -> addBlock(b, "Unobtainable block"));

                // tools
                add(Main.ID + "." + zStatic.Items.configurator + ".tip",
                                TIP_COLOR + "Show the debug vision");

                add(Main.ID + "." + zStatic.Items.configurator + ".link",
                                "§aBound to ");

                // add(Main.ID + "." + zStatic.Items.configurator + ".modetype",
                // TIP_COLOR + "Mode : ");

                // add(Main.ID + "." + zStatic.Items.configurator + ".mode.show_area",
                // "§aMachine AOE");

                // add(Main.ID + "." + zStatic.Items.configurator + ".mode.show_track",
                // "§aNode Track");

                add(Main.ID + "." + zStatic.Items.configurator + ".blockpos",
                                TIP_COLOR + "BlockPos : ");
                add(Main.ID + "." + zStatic.Items.configurator + ".dim",
                                TIP_COLOR + "Dimension : ");

                add(Main.ID + "." + zStatic.Items.refactorizer,
                                TIP_COLOR + "Force update blockstates of any #synergy:can_connect");
                add(Main.ID + "." + zStatic.Items.smasher, TIP_COLOR + "Quick break any #synergy:mashable");
                add(Main.ID + "." + zStatic.Items.wooden_crook, TIP_COLOR + "Increase the chance to obtain saplings");

                add(Main.ID + "." + zStatic.tips.MIXTURE_TIP,
                                TIP_COLOR + "Can be used to speed the grow of any plant");

                // pipe stuff
                add(Main.ID + "." + zStatic.PipeStuff.pipe + ".desc",
                                TIP_COLOR + "Can connected at any #synergy:can_connect");

                add(Main.ID + "." + zStatic.PipeStuff.pipe + ".extend",
                                TIP_COLOR + "Can be extended using pipes");

                add(Main.ID + "." + zStatic.PipeStuff.nodes.type_transfer,
                                TIP_COLOR + "Export and deposit from containers");

                add(Main.ID + "." + zStatic.PipeStuff.nodes.type_retrieval,
                                TIP_COLOR + "Import and deposit from containers");

                add(Main.ID + "." + zStatic.PipeStuff.nodes.type_provider,
                                TIP_COLOR + "Produce and deposit on containers");

                // wild crops warning
                add(Main.ID +".disabled",
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

                add(Main.ID + "." + zStatic.tips.INGREDIENT, TIP_COLOR + "Crafting component");

                add(Main.ID + "." + zStatic.Blocks.healer,
                                TIP_COLOR + "Heal and remove fire effect at all entities above it");

                add(Main.ID + "." + zStatic.Blocks.tiny_wooden_chest,
                                TIP_COLOR + "Small chests useful to store a single stack");


                add(Main.ID + "." + zStatic.tips.SHIFT,
                                "§8Hold [§7Shift§8] to see more details");

                add(Main.ID + "." + zStatic.ReactorStuff.cooler + ".desc",
                                TIP_COLOR + "Reduce Heat based on some conditions");

                // add(Main.ID + "." + zStatic.ReactorStuff.cooler + ".condition",
                // TIP_COLOR + "DESC");

                add(Main.ID + "." + zStatic.ReactorStuff.cooler + ".off",
                                TIP_COLOR + "Deactive: ");

                add(Main.ID + "." + zStatic.ReactorStuff.cooler + ".on",
                                TIP_COLOR + "Active: ");

                add(Main.ID + "." + zStatic.ReactorStuff.moderator + ".fe",
                                TIP_COLOR + "Fe: x");

                add(Main.ID + "." + zStatic.ReactorStuff.moderator,
                                TIP_COLOR + "Increase Heat and FE production");

                add(Main.ID + "." + zStatic.ReactorStuff.moderator + ".condition",
                                TIP_COLOR + "Active when near to a Fuel Cell");

                add(Main.ID + "." + zStatic.ReactorStuff.moderator + ".heat",
                                TIP_COLOR + "Heat: x");

                add(Main.ID + "." + zStatic.ReactorStuff.fuel_cell,
                                TIP_COLOR + "Create Heat , FE and execute recipes when heat below 0");

                add(Main.ID + "." + zStatic.ReactorStuff.controller,
                                TIP_COLOR + "Generate FE based on Fuel Cell recipes and heat when below 0");

                add(Main.ID + ".jei." + zStatic.ReactorStuff.fuel_cell,
                                "Reactor Reactions");

                add(Main.ID + ".jei." + zStatic.Blocks.urn,
                                "Urn Rituals");

                add(Main.ID + ".jei." + zStatic.Blocks.quern,
                                "Quern Milling");

                add(Main.ID + ".jei.crop",
                                "Crop Result");

                add(Main.ID + ".jei.itemuse",
                                "Item Use Recipes");

                add(Main.ID + ".jei.provider.item",
                                "Item Provider Pattern");

                add(Main.ID + ".jei.drying_bricks",
                                "Dryable Brick Recipes");

                List<String> coolerTypes = List.of(
                                zStatic.ReactorStuff.CoolerTypes.SHADOW,
                                zStatic.ReactorStuff.CoolerTypes.COPPER,
                                zStatic.ReactorStuff.CoolerTypes.DIAMOND,
                                zStatic.ReactorStuff.CoolerTypes.EMERALD,
                                zStatic.ReactorStuff.CoolerTypes.ENDER,
                                zStatic.ReactorStuff.CoolerTypes.FROST,
                                zStatic.ReactorStuff.CoolerTypes.GLOWSTONE,
                                zStatic.ReactorStuff.CoolerTypes.GOLD,
                                zStatic.ReactorStuff.CoolerTypes.IRON,
                                zStatic.ReactorStuff.CoolerTypes.LAPIS,
                                zStatic.ReactorStuff.CoolerTypes.NETHERITE,
                                zStatic.ReactorStuff.CoolerTypes.QUARTZ,
                                zStatic.ReactorStuff.CoolerTypes.REDSTONE,
                                zStatic.ReactorStuff.CoolerTypes.SCULK,
                                zStatic.ReactorStuff.CoolerTypes.WATER);

                List<String> langCooler = List.of(
                                "one Reactor Controller and one Fuel Cell",
                                "one Glowstone Cooler",
                                "one active Water Cooler and one active Quartz Cooler",
                                "one Fuel Cell and one Moderator",
                                "three Cooler but not this",
                                "two Fuel Cell",
                                "two active Moderators",
                                "one active Water Cooler and one active Redstone Cooler",
                                "one active Gold Cooler",
                                "one Fuel Cell and one Cooler",
                                "one active gold Cooler and one active Sculk Cooler",
                                "one Moderator",
                                "one Fuel Cell",
                                "two active Lapis Cooler on same axis",
                                "one Fuel Cell or one Moderator");

                coolerTypes.forEach(t -> {
                        add(
                                        Main.ID + "." + zStatic.ReactorStuff.cooler + "." + t,
                                        TIP_COLOR + "Require at least " + langCooler.get(coolerTypes.indexOf(t)));
                });

                var droptype = List.of(
                                "Creepers",
                                "Endermans",
                                "Ghasts",
                                "Guardians",
                                "Silverfishes",
                                "Slimes",
                                "Poison Creatures",
                                "Wither Skeletons",
                                "Zombie-like Creatures");

                zItems.zMobDrop.getEntries().forEach(d -> add(d.getRegisteredName().replace(":", ".") + ".tip",
                                TIP_COLOR + "Obtained from " + droptype
                                                .get(zItems.zMobDrop.getEntries().stream().toList().indexOf(d))));

                add(Main.ID + "." + zStatic.Items.soldering_gun, TIP_COLOR + "Change the size of AOE on specific blocks");

                add(Main.ID + ".jei.warning.config",
                                "Can be overriden via config");

                 add(Main.ID + ".placed",
                                TIP_COLOR + "Can be placed");

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
