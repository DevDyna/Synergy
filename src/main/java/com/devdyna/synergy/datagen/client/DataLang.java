package com.devdyna.synergy.datagen.client;

import static com.devdyna.synergy.Main.ID;

import java.util.Arrays;
import java.util.List;

import com.devdyna.synergy.Main;
import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.api.utils.ClazzUtil;
import com.devdyna.synergy.api.utils.StringUtil;
import com.devdyna.synergy.init.types.*;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.neoforged.neoforge.registries.DeferredHolder;

public class DataLang extends LanguageProvider {

        public DataLang(PackOutput o) {
                super(o, ID, "en_us");
        }

        public static final String TIP_COLOR = "§7";

        @SuppressWarnings({ "deprecation", "unchecked" })
        @Override
        protected void addTranslations() {

                Arrays.asList(ClazzUtil.getAllStrings(zStatic.CreativeTab.class))
                                .stream().filter(s -> !s.equals(zStatic.CreativeTab.TYPE))
                                .forEach(s -> add(Main.ID + "." + zStatic.CreativeTab.TYPE + "." + s,
                                                "Synergy : " + StringUtil.nameCapitalized(s)));

                zFluids.zFluidTypes.getEntries()
                                .forEach(f -> add(f.get().getDescriptionId(), named(f).replace(" Type", "")));

                zBlocks.zBlockItem.getEntries().forEach(b -> addBlock(b, named(b)));
                zBlocks.zBlockFluids.getEntries().forEach(b -> addBlock(b, named(b)));
                zBlocks.zBlock.getEntries().forEach(b -> addBlock(b, named(b)));
                zBlocks.zCrop.getEntries().forEach(b -> addBlock(b, named(b)));
                zBlocks.zMachineFrame.getEntries().forEach(b -> addBlock(b, named(b)));

                ClazzUtil.getAllzItems().stream()
                                .filter(d -> !d.is(zItems.CAKE_STICK) && !zItems.zMolds.getEntries().contains(d))
                                .forEach(c -> addItem(c, named(c)));

                zItems.zBucketItems.getEntries().forEach(b -> addItem(b, named(b)));

                zBlocks.zWildCrop.getEntries().forEach(b -> addBlock(b, named(b)));
                zBlocks.zDecorative.getEntries().forEach(b -> addBlock(b, named(b)));
                zBlocks.zBlockSlab.getEntries().forEach(b -> addBlock(b, named(b)));
                zBlocks.zBlockStair.getEntries().forEach(b -> addBlock(b, named(b)));
                zBlocks.zModerators.getEntries().forEach(b -> addBlock(b, named(b)));
                zBlocks.zCoolers.getEntries().forEach(b -> addBlock(b, named(b)));
                zBlocks.zColumn.getEntries().forEach(b -> addBlock(b, named(b)));

                zBlocks.zHiddenBlock.getEntries().forEach(b -> addBlock(b, named(b).replace(" Block", "")));

                zBlocks.zOnlyBlock.getEntries().forEach(b -> addBlock(b, "Unobtainable block"));

                zItems.zMolds.getEntries()
                                .forEach(i -> addItem(i, "Mold: " + named(i).replace(" Mold", "")));

                // tools
                add(Main.ID + "." + zStatic.Items.configurator + ".tip",
                                TIP_COLOR + "Show the debug vision");

                add(Main.ID + "." + zStatic.Items.configurator + ".link",
                                "§aBound to ");

                add(Main.ID + "." + zStatic.Items.configurator + ".blockpos",
                                TIP_COLOR + "BlockPos : ");
                add(Main.ID + "." + zStatic.Items.configurator + ".dim",
                                TIP_COLOR + "Dimension : ");

                // add(Main.ID + "." + zStatic.Items.refactorizer,
                // TIP_COLOR + "Force update blockstates of any #synergy:can_connect");
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
                add(Main.ID + ".disabled",
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

                add(Main.ID + "." + zStatic.Chests.WOODEN,
                                TIP_COLOR + "Small chests useful to store a single stack");
                add(Main.ID + "." + zStatic.Chests.STONE,
                                TIP_COLOR + "Like wooden but can store an entire chest");
                add(Main.ID + "." + zStatic.Chests.ORNATE,
                                TIP_COLOR + "Like stone but can store an entire double chest!");

                add(Main.ID + "." + zStatic.tips.SHIFT,
                                "§8Hold [§7Shift§8] to see more details");

                add(Main.ID + "." + zStatic.ReactorStuff.cooler + ".desc",
                                TIP_COLOR + "Reduce Heat based on some conditions");

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

                add(Main.ID + ".jei.item_use",
                                "Item Use Recipes");

                add(Main.ID + ".jei.provider.item",
                                "Item Provider Pattern");

                add(Main.ID + ".jei.provider.fluid",
                                "Fluid Provider Pattern");

                add(Main.ID + ".jei.drying_bricks",
                                "Dryable Brick Recipes");

                add(Main.ID + ".jei." + zStatic.Blocks.void_box,
                                "Void Box Infusions");

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

                add(Main.ID + "." + zStatic.Items.soldering_gun,
                                TIP_COLOR + "Change the size of AOE on specific blocks");

                add(Main.ID + ".jei.warning.render_only",
                                "In-World interaction not editable");

                add(Main.ID + ".placed",
                                TIP_COLOR + "Can be placed");

                add(Main.ID + ".jei.dryable.tip", "Require to be in the sun and placed in a dry biome");

                add(Main.ID + ".laser_use.reset", TIP_COLOR + "Can decrease all colors");
                add(Main.ID + ".laser_use.max", TIP_COLOR + "Can increase all colors");

                add(Main.ID + ".laser_use.red", TIP_COLOR + "Can increase red color on laser beam");
                add(Main.ID + ".laser_use.green", TIP_COLOR + "Can increase green color on laser beam");
                add(Main.ID + ".laser_use.blue", TIP_COLOR + "Can increase blue color on laser beam");

                add(Main.ID + ".laser.rotate_by_click",
                                TIP_COLOR + "Can be rotated with right-click");

                add(Main.ID + ".remove_entity_growing",
                                TIP_COLOR + "Can be used to remove the growing-event of baby entities");
                add(Main.ID + ".add_entity_growing",
                                TIP_COLOR + "Can be used to re-add the growing-event of baby entities");

                add(Main.ID + "." + zStatic.Lazers.machine_gun,
                                TIP_COLOR + "Generate a laser line when has ForgeEnergy and a redstone signal");

                add(Main.ID + "." + zStatic.Lazers.lens,
                                TIP_COLOR + "Repeat the signal of any laser line when pass through");
                add(Main.ID + "." + zStatic.Lazers.mirror, TIP_COLOR + "Rotate of 90° any laser line");
                add(Main.ID + "." + zStatic.Lazers.sensor, TIP_COLOR
                                + "Emit a redstone signal and generate a small amount of ForgeEnergy when a laser line pass through");

                add(Main.ID + "." + zStatic.Items.cake_stick, TIP_COLOR + "Place cake slices");

                addItem(zItems.CAKE_STICK, "The Cake Stick");

                add(Main.ID + "." + zStatic.Blocks.inverted_repeater, TIP_COLOR + "Configurable NOT gate");
                add(Main.ID + "." + zStatic.Blocks.recursive_repeater, TIP_COLOR + "Configurable timer");
                add(Main.ID + "." + zStatic.Blocks.pulse_repeater, TIP_COLOR + "Configurable pulse converter");

                add("config.jade.plugin_" + ID + "." + zStatic.Lazers.machine_gun, "Laser Machine Gun Color");
                add("config.jade.plugin_" + ID + "." + zStatic.Lazers.rotor, "Laser Rotor Info");
                add("config.jade.plugin_" + ID + "." + zStatic.Machines.TYPE, "Industrial Machines Info");
                add("config.jade.plugin_" + ID + "." + zStatic.PipeStuff.nodes.type_provider,
                                "Node Provider Recipe Output Info");
                add("config.jade.plugin_" + ID + "." + zStatic.ReactorStuff.controller,
                                "Quantum Reactor Controller Stats");
                add("config.jade.plugin_" + ID + ".aoe", "AreaOfEffect Machines Info");
                add("config.jade.plugin_" + ID + ".fegen", "Energy Provider Info");
                add("config.jade.plugin_" + ID + "." + zStatic.ReactorStuff.moderator, "Moderator Provider Info");
                add("config.jade.plugin_" + ID + "." + zStatic.ReactorStuff.cooler, "Cooler Provider Info");
                add("config.jade.plugin_" + ID + "." + zStatic.ReactorStuff.fuel_cell,
                                "Fuel Cell Recipe Provider Info");

                add(Main.ID + ".color", "Color: %d");

                add(Main.ID + ".blockpos", TIP_COLOR + "BlockPos : ");
                add(Main.ID + ".dirs", TIP_COLOR + "Dirs : ");

                add(Main.ID + "." + zStatic.Lazers.rotor, TIP_COLOR
                                + "Generate FE when powered from the same laser machine at all of the sides\nIf used multiple laser machine it will reset!");

                ClazzUtil.getAllMachineTypes()
                                .forEach(m -> {
                                        addBlock(m.block(), named(m.block()));
                                        add(ID + ".jei.machine." + m.id(), named(m.item()) + " Recipes");
                                }

                                );

                add(ID + ".nofuel", "§eNo Fuel Cell available");
                add(ID + ".waiting", "§eRequire a redstone signal to stay active");
                add(ID + ".overheated", "§cOverheated");
                add(ID + ".production", "§aProcessing");

                add(ID + ".fe", "FE: ");
                add(ID + ".heat", "Heat: ");
                add(ID + ".aoe", "AreaOfEffect: ");
                add(ID + ".aoe.small", "§cArea too small");
                add(ID + ".aoe.big", "§cArea too big");

                add(ID + ".jade.tip.daytime", "when daytime");
                add(ID + ".jade.tip.cycle", "every cycle");

                add(ID + ".jade.warn.moderator", "§cModerator not satisfied");

                add(ID + ".jade.info.cooler_status.false", "Status: §cInactive");
                add(ID + ".jade.info.cooler_status.true", "Status: §aActive");

                add(ID + ".jei.tip.dont_consume", "§cNot consume");

                // i will move to item components intend of static values and tags
                add(ID + ".upgrade.speed.speed", TIP_COLOR + "Machine Speed : §a+35%§f");
                add(ID + ".upgrade.speed.energy", TIP_COLOR + "Machine Energy : §c-100%");
                add(ID + ".upgrade.energy", TIP_COLOR + "Machine Energy : §a+75%§f");

                add(ID + "." + zStatic.Blocks.void_box, TIP_COLOR + "A strange box to allow to NOT store items");

                add(ID + ".block.blast_proof", TIP_COLOR + "Blast resistance");

                add(ID + ".tank_interact.empty", "Empty");

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
