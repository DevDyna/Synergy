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

@SuppressWarnings({ "unchecked" })
public class DataLang extends LanguageProvider {

        public DataLang(PackOutput o) {
                super(o, ID, "en_us");
        }

        public static final String TIP_COLOR = "§7";

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
                                .filter(d -> !d.is(zItems.CAKE_STICK.getId())
                                                && !zItems.zMolds.getEntries().contains(d))
                                .forEach(c -> addItem(c, named(c)));

                zItems.zBucketItems.getEntries().forEach(b -> addItem(b, named(b)));

                zBlocks.zWildCrop.getEntries().forEach(b -> addBlock(b, named(b)));
                zBlocks.zDecorative.getEntries().forEach(b -> addBlock(b, named(b)));
                zBlocks.zBlockSlab.getEntries().forEach(b -> addBlock(b, named(b)));
                zBlocks.zBlockStair.getEntries().forEach(b -> addBlock(b, named(b)));
                zBlocks.zModerators.getEntries().forEach(b -> addBlock(b, named(b)));
                zBlocks.zCoolers.getEntries().forEach(b -> addBlock(b, named(b)));
                zBlocks.zColumn.getEntries().forEach(b -> addBlock(b, named(b)));

                zBlocks.zResourceGenerators.getEntries().forEach(b -> {

                        for (String tier : zStatic.Tiers.ALL) {
                                if (b.getRegisteredName().contains(tier)) {
                                        var affix = StringUtil.nameCapitalized(tier).replace("_", "");
                                        addBlock(b, named(b).replace(affix + " ", "") + " [" + affix + "]");
                                        continue;
                                }
                        }

                });

                zBlocks.zHiddenBlock.getEntries().forEach(b -> addBlock(b, named(b).replace(" Block", "")));

                zBlocks.zOnlyBlock.getEntries().forEach(b -> addBlock(b, "§cUnobtainable Block"));

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
                add(Main.ID + ".jei." + "copper_oxidation",
                                "Copper Oxidation Info");

                add(Main.ID + ".jei." + zStatic.Blocks.foundry,
                                "Foundry Recipes");

                add(Main.ID + ".jei." + zStatic.Blocks.urn,
                                "Urn Rituals");

                add(Main.ID + ".jei." + zStatic.Blocks.quern,
                                "Quern Milling");

                add(Main.ID + ".jei.resource_info",
                                "Resource Info");

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

                add(Main.ID + ".jei." + zStatic.Blocks.crushing_tub,
                                "Crushing Tub Recipes");
                add(Main.ID + ".jei." + zStatic.Blocks.evaporation_basin,
                                "Evaporation Basin Recipes");
                add(Main.ID + ".jei." + zStatic.Blocks.drying_rack,
                                "Drying Rack Recipes");
                add(Main.ID + ".jei." + zStatic.Blocks.foundry + "_fuels",
                                "Foundry Fuels Info");
                add(Main.ID + ".jei." + zStatic.Blocks.casting_table,
                                "Casting Table recipes");

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
                add(Main.ID + "." + zStatic.Lazers.sensor,
                                TIP_COLOR + "Emit a redstone signal when a laser line pass through");

                add(Main.ID + "." + zStatic.Items.cake_stick, TIP_COLOR + "Place cake slices");

                addItem(zItems.CAKE_STICK, "The Cake Stick");

                add(Main.ID + "." + zStatic.Blocks.inverted_repeater, TIP_COLOR + "Configurable hexadecimal NOT gate");
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
                add("config.jade.plugin_" + ID + "." + zStatic.DryableBricks.TYPE,
                                "Dryable Bricks Info");
                add("config.jade.plugin_" + ID + ".timered_recipes",
                                "Recipe Timered Info");
                add("config.jade.plugin_" + ID + ".environment_modifier",
                                "Environment Modifier Info");
                add("config.jade.plugin_" + ID + ".foundry_fuel",
                                "Foundry Fuel Provider Modifier Info");
                add("config.jade.plugin_" + ID + ".simple_timer",
                                "Simple Delay Info");
                add("config.jade.plugin_" + ID + "." + zStatic.Blocks.logic_box,
                                "Logic Box Info");
                add("config.jade.plugin_" + ID + "." + zStatic.Blocks.router,
                                "Router Info");
                add("config.jade.plugin_" + ID + "." + zStatic.Blocks.entity_watcher,
                                "Entity Watcher Info");

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

                add(ID + ".jade.info.whitelist", "Whitelist:");
                add(ID + ".jade.info.blacklist", "Blacklist:");

                add(ID + ".jei.tip.dont_consume", "§cNot consume");

                add(ID + "." + zStatic.Blocks.void_box, TIP_COLOR + "A strange box to allow to NOT store items");

                add(ID + ".block.blast_proof", TIP_COLOR + "Blast resistance");

                add(ID + ".tank_interact.empty", "Empty");

                add(ID + "." + zStatic.Items.chisel, TIP_COLOR
                                + "Craft in-world Stonecutter recipes when bound to the result block");

                add(Main.ID + "." + zStatic.Items.chisel + ".registry",
                                TIP_COLOR + "Block : ");

                add(ID + ".resourcegen.tip", "Generate %s %s every %d ticks");
                add(ID + ".resourcegen.tip.mono", "Generate %s %s every tick");

                add(ID + ".upgrades.title", TIP_COLOR + "Upgrade Modifiers");

                add(ID + ".upgrades.modifier.energy", TIP_COLOR + "Energy Usage: %s");
                add(ID + ".upgrades.modifier.speed", TIP_COLOR + "Recipe Speed: %s");
                add(ID + ".upgrades.modifier.luck", TIP_COLOR + "Secondary Output: %s");
                add(ID + ".upgrades.modifier.fluid", TIP_COLOR + "Fluid Usage: %s");

                add(ID + ".screen.upgrades", "Supported Upgrades:");

                add(ID + ".screen.modifier.energy", TIP_COLOR + "Energy Modifier §7[§f§a%s§7]");
                // add(ID + ".screen.modifier.energy_capacity", TIP_COLOR + "Energy Capacity
                // Modifier §7[§f§a%s§7]");
                add(ID + ".screen.modifier.speed", TIP_COLOR + "Speed Modifier §7[§f§a%s§7]");
                add(ID + ".screen.modifier.luck", TIP_COLOR + "Luck Modifier §7[§f§a%s§7]");
                add(ID + ".screen.modifier.fluid", TIP_COLOR + "Fluid Modifier §7[§f§a%s§7]");

                add(ID + "." + zStatic.Blocks.crushing_tub,
                                TIP_COLOR + "Crush items into items and fluids when jump on it");
                add(ID + "." + zStatic.Blocks.evaporation_basin, TIP_COLOR
                                + "Dry fluids into items after some time\nA MagmaBlock placed below can speed up the process");

                add(ID + "." + zStatic.Blocks.drying_rack, TIP_COLOR
                                + "Dry items into other items\nA campfire placed below can speed up the process");
                add(ID + "." + zStatic.Blocks.foundry, TIP_COLOR
                                + "Melt items into fluids\nRequire a fuel tank with a liquid fuel to process\nIf already contain a fluid , the process time will reduced");

                add(ID + ".jade.bricks.dried", "Drying stage: §aMature");
                add(ID + ".jade.bricks.wet", "§cDrying conditions don't match!");
                add(ID + ".jade.bricks.stage", "Drying stage: %s");
                add(ID + ".jade.environment_modifier.tip", "Base Speed: %s");
                add(ID + ".jade.foundry_fuel.tip", "Heat Speed Modifier: %s");

                add(ID + ".jei.fuel.usage", "Fluid amount used every recipe");
                add(ID + ".jei.fuel.speed", "Base recipe speed");

                add(ID + "." + zStatic.Blocks.casting_table, TIP_COLOR + "Use molds to solidify liquids");
                add(ID + "." + zStatic.Blocks.faucet, TIP_COLOR + "Move fluids when active");

                add(ID + ".boost.evaporation_basin",
                                TIP_COLOR + "When placed below a Evaporation Basin it will speed up the process");
                add(ID + ".boost.drying_rack",
                                TIP_COLOR + "When placed below a Drying Rack it will speed up the process");

                add(ID + "." + zStatic.Blocks.chopper, TIP_COLOR + "Harvest trees using axes and a solid fuel");
                add(ID + "." + zStatic.Blocks.chopper + ".aoe", TIP_COLOR + "Increase the area of Chopper");
                add(ID + "." + zStatic.Blocks.chopper + ".energy",
                                TIP_COLOR + "Allow to use FE intend of solid fuel to power the Chopper");

                add(ID + ".jei.dryable_rack.tip", "Tick delay can change based on stack size");

                add(ID + "." + zStatic.Blocks.logic_box, TIP_COLOR
                                + "Input filtered tiny chest");

                var deposit_suffixs = List.of(
                                "aluminum",
                                "tin",
                                "gold",
                                "nickel",
                                "lead",
                                "osmium",
                                "silver",
                                "tin",
                                "copper",
                                "uranium",
                                "organic sediments",
                                "heavy metals",
                                "quartz",
                                "copper",
                                "sulfur",
                                "coal",
                                "iron"

                );

                Arrays.asList(ClazzUtil.getAllStrings(zStatic.ResourceMaterial.deposits.class))
                                .forEach(
                                                s -> add(ID + "." + s + ".tip", TIP_COLOR + "High concentration of "
                                                                + deposit_suffixs.get(Arrays.asList(ClazzUtil
                                                                                .getAllStrings(zStatic.ResourceMaterial.deposits.class))
                                                                                .indexOf(s))));

                add(ID + ".jei.atlas.consumer.energy", "Energy Consumer");

                add(ID + ".jei.atlas.consumer.optional.energy", "Optional Energy Consumer");

                add(ID + ".jei.atlas.generator.energy", "Energy Generator");
                add(ID + ".jei.atlas.generator.cobble", "Cobblestone Generator");
                add(ID + ".jei.atlas.generator.water", "Water Generator");
                add(ID + ".jei.atlas.redstone.component", "Redstone Component");
                add(ID + ".jei.atlas.redstone.sensible", "Redstone Sensible");
                add(ID + ".jei.atlas.redstone.emitter", "Redstone Emitter");
                add(ID + ".jei.atlas.storage.item", "Item Storage");
                add(ID + ".jei.atlas.storage.fluid", "Fluid Storage");

                add(ID + ".jei.atlas.portable", "Portable Storage");
                add(ID + ".jei.atlas.filter.item", "Item Filter");
                add(ID + ".jei.atlas.trash_can.item", "Item Trash Can");
                add(ID + ".jei.atlas.conduit", "Conduit");
                add(ID + ".jei.atlas.pipe", "Pipe");
                add(ID + ".jei.atlas.tube", "Tube");

                add(ID + ".jei.atlas.transfer.item", "Item Transfer");
                add(ID + ".jei.atlas.transfer.fluid", "Fluid Transfer");
                add(ID + ".jei.atlas.transfer.energy", "Energy Transfer");

                add(ID + ".jei.atlas.type.transmitter", "Transmitter");
                add(ID + ".jei.atlas.type.reciever", "Reciever");
                add(ID + ".jei.atlas.type.producer", "Producer");

                add(ID + ".jei.atlas.seed.crop", "Plant Seed");
                add(ID + ".jei.atlas.seed.mushroom", "Mushroom Seed");

                add(ID + ".jei.atlas.foundry.melter", "Item Melter");

                add(ID + ".jei.atlas.aoe", "Area of Effect Related");

                add(ID + "." + zStatic.Blocks.router, TIP_COLOR
                                + "Route items to be extracted from differents sides based on some internal filters");

                add(ID + "." + zStatic.Blocks.chopper + ".info.status", "Status");
                add(ID + "." + zStatic.Blocks.chopper + ".info.range", "Range: %d");
                add(ID + "." + zStatic.Blocks.chopper + ".info.axe", "No valid axe");
                add(ID + "." + zStatic.Blocks.chopper + ".info.fuel", "Missing Fuel");
                add(ID + "." + zStatic.Blocks.chopper + ".info.ready", "Ready");

                zPotions.zPotion.getEntries().forEach(i -> {
                        add("item.minecraft.tipped_arrow.effect."
                                        + i.getRegisteredName().replace(ID + ":", ""),
                                        "Arrow of " + named(i));
                        add("item.minecraft.potion.effect."
                                        + i.getRegisteredName().replace(ID + ":", ""),
                                        "Potion of " + named(i));
                        add("item.minecraft.splash_potion.effect."
                                        + i.getRegisteredName().replace(ID + ":", ""),
                                        "Splash Potion of " + named(i));
                        add("item.minecraft.lingering_potion.effect."
                                        + i.getRegisteredName().replace(ID + ":", ""),
                                        "Lingering Potion of " + named(i));
                });

                add(ID + "." + zStatic.Blocks.entity_watcher,
                                TIP_COLOR + "A strange eye that want look any near entities");

                add(ID + "." + zStatic.Blocks.entity_watcher + ".tweak",
                                TIP_COLOR + "Right click to a Entity Watcher to change the Entity Mode filter");

                add(ID + ".jade.watcher_mode.player_only", "Mode Filter: §aNearest player");
                add(ID + ".jade.watcher_mode.not_player", "Mode Filter: §aNearest living entity not player");

                add(ID + ".advancement.root.wrought_iron", "Simplify what you desire");
                add(ID + ".advancement.root.wrought_iron.desc", "The time isn't a reason to define complexity");
                add(ID + ".advancement.root.steel", "Simplify to another level");
                add(ID + ".advancement.root.steel.desc", "Exponential potential");
                add(ID + ".advancement.root.magic", "Thinking as a wizard");
                add(ID + ".advancement.root.magic.desc", "Not Forge Energy Based");

                advKey(zStatic.ResourceMaterial.wrought_iron, "One shade of gray",
                                "Combine some carbon dust with an iron ingot");
                advKey(zStatic.ResourceMaterial.steel, "Steel!", "Smelt on a blast furnace a wrought iron ingot");

                advKey("ironberries", "IronBerry Expert 2.0 when?", "Obtain some ironberries from an IronWood Tree");
                advKey(zStatic.Items.wooden_crook, "More from less",
                                "Craft a wooden crook to obtain more leaves drops");
                advKey("mushrooms", "Beta Infestation!",
                                "Obtain some mushroom spores , keep in mind that they can spread very quickly!");
                advKey(zStatic.Items.cake_stick, "The cake is(n't) a lie!", "The Cake stick is right!");

                advKey(zStatic.Blocks.quern, "It spin!", "Craft a quern to process resources into dusts");
                advKey(zStatic.Blocks.crushing_tub, "Crushing time",
                                "Craft a crushing tub to obtain a early way to produce iron");
                advKey(zStatic.Blocks.evaporation_basin, "Drying fluids",
                                "Craft a evaporation basin to dry fluids using the sun");
                advKey(zStatic.Blocks.drying_rack, "Drying with style", "Craft any drying rack to dry items");

                advKey(zStatic.ResourceMaterial.aquamarine, "Well yes but actually no", "Obtain some aquamarine");
                advKey(zStatic.PipeStuff.pipe, "Back to 2014", "Craft some pipes and have fun with nostalgia");
                advKey(zStatic.ResourceMaterial.sulfur, "This isn't gunpowder!", "Obtain some sulfur");

                advKey(zStatic.Items.chisel, "StoneCutter on a Stick",
                                "Craft a chisel to craft in-world stonecutting recipes");
                advKey(zStatic.Items.soldering_gun, "No limits",
                                "Craft a soldering gun to extend AOE of any compatible machine");
                advKey(zStatic.Items.configurator, "Let me see!",
                                "Craft a configurator to show AOE of any compatible machine");

                advKey(zStatic.Blocks.chopper, "Not a progressive automation",
                                "Craft a Tree chopper to fully automate wood gathering");
                advKey(zStatic.Blocks.router, "Diamonds aren't filters", "Craft a Router to filter items using pipes");
                advKey(zStatic.Blocks.foundry, "Better than Productive Metalwork",
                                "Craft a foundry to melt items into fluids");

                advKey(zStatic.ResourceGenerators.CobbleStone.simple, "Chobblesome!",
                                "Craft a cobblestone generator to collect a passive amount of cobblestone");
                advKey(zStatic.ResourceGenerators.CobbleStone.advanced, "I want more cobblestone!",
                                "Upgrade your cobblestone generator to obtain more cobblestone");
                advKey(zStatic.ResourceGenerators.CobbleStone.elite, "I want MORE cobblestone!",
                                "Upgrade your cobblestone generator to obtain MORE cobblestone");

                advKey(zStatic.ResourceGenerators.Water.simple, "Watermon",
                                "Craft a water collector to collect a passive amount of water");
                advKey(zStatic.ResourceGenerators.Water.advanced, "I want MORE water!",
                                "Upgrade your water collector to obtain more water");
                advKey(zStatic.ResourceGenerators.Water.elite, "I want MORE water!",
                                "Upgrade your water collector to obtain MORE water");

                advKey(zStatic.Blocks.urn, "Dark brick pot with inside parts of monsters",
                                "Craft an urn to craft more magic blocks");
                advKey(zStatic.Blocks.void_box, "Hungry chest", "Craft a void box to delete anything you don't want");
                advKey(zStatic.Blocks.logic_box, "Red Green but not Blue!", "Craft a logic box to filter input items");
                advKey(zStatic.Blocks.entity_watcher, "A harmless and curious floating eye",
                                "Craft an entity watcher to detect any entity near to you like a security camera!");

                advKey("battery", "Transfer energy around the world",
                                "Craft some batteries to hand-transfer energy across blocks");

                advKey(zStatic.Blocks.solar_panel, "Sun is the source", "Craft a solar panel to produce Forge Energy");
                advKey(zStatic.Blocks.sprinkler, "Watering can mentioned!",
                                "Craft a sprinkler to speed up crop growing");
                advKey(zStatic.Blocks.harvester, "Harvest anything you want",
                                "Craft an harvester to harvest trees , crops and any other growable plant");

                advKey(zStatic.Lazers.machine_gun, "Beam!",
                                "Craft a laser machine gun to create a colourful laser track");
                advKey(zStatic.Lazers.mirror, "Mirrors and Levers", "Craft some laser mirrors to rotate a laser track");
                advKey(zStatic.Lazers.rotor, "You spin me round",
                                "Craft a laser rotor to generate huge amounts of energy every time the same laser machine gun hit all of four faces of it");

                advKey(zStatic.DecorativeBlocks.MachineFrame.basic, "Industrial machines",
                                "Craft a basic machine frame");
                advKey(zStatic.DecorativeBlocks.MachineFrame.advanced, "Nuclear machines",
                                "Craft an advanced machine frame");

                advKey(zStatic.ReactorStuff.controller, "Wireless Puzzle",
                                "Craft a Quantum Reactor Controller , the core of any quantum reactor controller multiblock structure");
                advKey(zStatic.ReactorStuff.fuel_cell, "The tricky part", "Craft a Quantum Reactor Fuel Cell");
                advKey(zStatic.ReactorStuff.moderator, "Improve your work",
                                "Craft any Moderator to improve fuel cell efficiency");
                advKey(zStatic.ReactorStuff.cooler, "Reduce what could gone wrong",
                                "Craft any Cooler to reduce the Heating generated");

                advKey(zStatic.Machines.alloy_smelter, "Mix stuff", "Craft an alloy smelter");
                advKey(zStatic.Machines.casting_factory, "Cool it down!", "Craft a casting factory");
                advKey(zStatic.Machines.compressor, "Don't put your finger here!", "Craft a compressor");
                advKey(zStatic.Machines.electric_furnace, "Not an Iron Furnace", "Craft an electric furnace");
                advKey(zStatic.Machines.extractor, "Extract the essential", "Craft an extractor");
                advKey(zStatic.Machines.macerator, "Crush into dusts", "Craft a macerator to process items into dusts");
                advKey(zStatic.Machines.melter, "High temperatures", "Craft an electric melter");
                advKey(zStatic.Machines.rock_crusher, "Rock 'n' Roll", "Craft a rock crusher");

                advKey(zStatic.ResourceMaterial.plastic, "Plastic Fantastic", "Craft some plastic");

                advKey(zStatic.ResourceMaterial.uranium, "Also called U235", "Craft an uranium pellet");
                advKey(zStatic.ResourceMaterial.thorium, "Not lightning related", "Craft a thorium pellet");
                advKey(zStatic.ResourceMaterial.plutonium, "We can't forget it", "Craft a plutonium pellet");
                advKey(zStatic.ResourceMaterial.neptunium, "Same of Atlantis", "Craft a neptunium pellet");
                advKey(zStatic.ResourceMaterial.americium, "Before Christopher Columbus", "Craft an americium pellet");
                advKey(zStatic.ResourceMaterial.berkelium, "Berkel!", "Craft a berkelium pellet");
                advKey(zStatic.ResourceMaterial.californium, "Eagles Success", "Craft a californium pellet");
                advKey(zStatic.ResourceMaterial.curium, "Curiosity", "Craft a curium pellet");

                add(Main.ID + ".jei.patina_drop", TIP_COLOR + "Drop %s"+TIP_COLOR+" items every scrape");

                //unused
                add(ID + ".jei.tip.consume_durability", "§cConsume durability");

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

        private void advKey(String k, String title, String desc) {
                add(ID + ".advancement.branch." + k, title);
                add(ID + ".advancement.branch." + k + ".desc", desc);
        }

}
