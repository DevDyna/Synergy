package com.devdyna.synergy.init.types;

import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.api.plants.builder.BaseSeedItem;
import com.devdyna.synergy.Main;
import com.devdyna.synergy.init.Material;
import com.devdyna.synergy.init.builder.tools.Battery;
import com.devdyna.synergy.init.builder.tools.PlantMixture;
import com.devdyna.synergy.init.builder.tools.Configurator;
import com.devdyna.synergy.init.builder.tools.Croock;
import com.devdyna.synergy.init.builder.tools.PipeRefactorizer;
import com.devdyna.synergy.init.builder.tools.Smasher;
import com.devdyna.synergy.init.builder.tools.SolderingGun;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class zItems {
        public static void register(IEventBus bus) {

                zBlockItem.register(bus);
                zTool.register(bus);
                zCraftingComponents.register(bus);
                zNodeModules.register(bus);

                zFoods.register(bus);
                zCropExtra.register(bus);
                zSeeds.register(bus);

                zBucketItems.register(bus);

                zDropLets.register(bus);

                zResources.register(bus);

                zDusts.register(bus);
                zFoils.register(bus);
                zGems.register(bus);
                zIngots.register(bus);
                zNuggets.register(bus);
                zPlates.register(bus);
                zRawOres.register(bus);
                zShards.register(bus);
                zMobDrop.register(bus);
                zPellets.register(bus);
        }

        // ---------------------------------------------------------------------------------------//
        /**
         * Block-Items
         * <br/>
         * <br/>
         * DONT USE THIS TO REGISTER STUFF
         */
        public static final DeferredRegister.Items zBlockItem = DeferredRegister.createItems(Main.ID);
        /**
         * Item with abilities
         */
        public static final DeferredRegister.Items zTool = DeferredRegister.createItems(Main.ID);
        /**
         * Item only for recipes
         */
        public static final DeferredRegister.Items zCraftingComponents = DeferredRegister.createItems(Main.ID);
        /**
         * Nodes upgrades
         */
        public static final DeferredRegister.Items zNodeModules = DeferredRegister.createItems(Main.ID);
        /**
         * Edible items
         */
        public static final DeferredRegister.Items zFoods = DeferredRegister.createItems(Main.ID);
        /**
         * Crop result items
         */
        public static final DeferredRegister.Items zCropExtra = DeferredRegister.createItems(Main.ID);
        /**
         * Crop seed items
         */
        public static final DeferredRegister.Items zSeeds = DeferredRegister.createItems(Main.ID);
        /**
         * BucketItems
         */
        public static final DeferredRegister.Items zBucketItems = DeferredRegister.createItems(Main.ID);

        /**
         * Fluid Resource items
         */
        public static final DeferredRegister.Items zDropLets = DeferredRegister.createItems(Main.ID);

        /**
         * Generic resource type
         */
        public static final DeferredRegister.Items zResources = DeferredRegister.createItems(Main.ID);
        // Dedicated resource types
        public static final DeferredRegister.Items zFoils = DeferredRegister.createItems(Main.ID);
        public static final DeferredRegister.Items zGems = DeferredRegister.createItems(Main.ID);
        public static final DeferredRegister.Items zIngots = DeferredRegister.createItems(Main.ID);
        public static final DeferredRegister.Items zNuggets = DeferredRegister.createItems(Main.ID);
        public static final DeferredRegister.Items zPlates = DeferredRegister.createItems(Main.ID);
        public static final DeferredRegister.Items zRawOres = DeferredRegister.createItems(Main.ID);
        public static final DeferredRegister.Items zShards = DeferredRegister.createItems(Main.ID);
        public static final DeferredRegister.Items zDusts = DeferredRegister.createItems(Main.ID);
        public static final DeferredRegister.Items zMobDrop = DeferredRegister.createItems(Main.ID);
        public static final DeferredRegister.Items zPellets = DeferredRegister.createItems(Main.ID);
        // ---------------------------------------------------------------------------------------//
        public static final DeferredHolder<Item, Item> AZALEA_SEEDS = zSeeds
                        .registerSimpleItem(zStatic.Items.Azalea.seeds);
        // ---------------------------------------------------------------------------------------//
        public static final DeferredHolder<Item, Item> SMALL_AZALEA_LEAF = zCropExtra
                        .registerSimpleItem(zStatic.Items.Azalea.leaf);

        public static final DeferredHolder<Item, Item> SMALL_AZALEA_ROOTS = zCropExtra
                        .registerSimpleItem(zStatic.Items.Azalea.roots);

        // ---------------------------------------------------------------------------------------//
        public static final DeferredHolder<Item, ?> CAVE_WHEAT_SEEDS = zSeeds.register(zStatic.Seeds.CAVE_WHEAT_SEED,
                        () -> new BaseSeedItem(zBlocks.CAVE_WHEAT.get()));

        public static final DeferredHolder<Item, ?> RICE_SEED = zSeeds.register(zStatic.Seeds.RICE_SEED,
                        () -> new BaseSeedItem(zBlocks.RICE.get(),
                                        new Item.Properties().food(new FoodProperties.Builder().alwaysEdible().fast()
                                                        .nutrition(1).saturationModifier(1).build())));

        public static final DeferredHolder<Item, ?> COTTON_SEEDS = zSeeds.register(zStatic.Seeds.COTTON_SEED,
                        () -> new BaseSeedItem(zBlocks.COTTON.get()));

        public static final DeferredHolder<Item, ?> BLUE_CUP_SPORE = zSeeds.register(
                        zStatic.Seeds.BLUE_CUP_SPORE,
                        () -> new BaseSeedItem(zBlocks.BLUE_CUP_MUSHROOM.get()));

        public static final DeferredHolder<Item, ?> VIOLET_WEBCAP_SPORE = zSeeds.register(
                        zStatic.Seeds.VIOLET_WEBCAP_SPORE,
                        () -> new BaseSeedItem(zBlocks.VIOLET_WEBCAP_MUSHROOM.get()));
        // ---------------------------------------------------------------------------------------//
        public static final DeferredHolder<Item, ?> COTTON = zCropExtra.registerSimpleItem(zStatic.Plants.COTTON);

        public static final DeferredHolder<Item, ?> BLUE_CUP_MUSHROOM = zCropExtra
                        .registerSimpleItem(zStatic.Plants.BLUE_CUP_MUSHROOM,
                                        new Item.Properties().food(new FoodProperties.Builder()
                                                        .effect(() -> new MobEffectInstance(MobEffects.BLINDNESS, 160,
                                                                        0),
                                                                        25)
                                                        .build()));

        public static final DeferredHolder<Item, ?> VIOLET_WEBCAP_MUSHROOM = zCropExtra
                        .registerSimpleItem(zStatic.Plants.VIOLET_WEBCAP_MUSHROOM,
                                        new Item.Properties().food(new FoodProperties.Builder()
                                                        .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 160,
                                                                        0),
                                                                        25)
                                                        .build()));
        // ---------------------------------------------------------------------------------------//
        public static final DeferredHolder<Item, Croock> WOODEN_CROOK = zTool.register(zStatic.Items.wooden_crook,
                        () -> new Croock(BlockTags.LEAVES, Tiers.NETHERITE));

        public static final DeferredHolder<Item, Item> PIPE_REFARCTORIZER = zTool
                        .register(zStatic.Items.refactorizer, () -> new PipeRefactorizer());

        public static final DeferredHolder<Item, Item> SMASHER = zTool
                        .register(zStatic.Items.smasher, () -> new Smasher());

        public static final DeferredHolder<Item, Item> SOLDERING_GUN = zTool
                        .register(zStatic.Items.soldering_gun, () -> new SolderingGun());

        public static final DeferredHolder<Item, Item> CONFIGURATOR = zTool
                        .register(zStatic.Items.configurator, () -> new Configurator());

        public static final DeferredHolder<Item, Item> GREEN_BATTERY = zTool
                        .register(zStatic.Items.Batteries.green, () -> new Battery(1000));

        public static final DeferredHolder<Item, Item> BLUE_BATTERY = zTool
                        .register(zStatic.Items.Batteries.blue, () -> new Battery(10000));

        public static final DeferredHolder<Item, Item> RED_BATTERY = zTool
                        .register(zStatic.Items.Batteries.red, () -> new Battery(100000));

        // ---------------------------------------------------------------------------------------//
        public static final DeferredHolder<Item, Item> CHIP = Material
                        .craftingItem(zStatic.Items.CraftingComponents.chip);

        public static final DeferredHolder<Item, Item> RESISTOR = Material
                        .craftingItem(zStatic.Items.CraftingComponents.resistor);

        public static final DeferredHolder<Item, Item> CONDENSER = Material
                        .craftingItem(zStatic.Items.CraftingComponents.condenser);
        // ---------------------------------------------------------------------------------------//
        public static final DeferredHolder<Item, Item> BONE_MEAL_MIXTURE = zTool
                        .register(zStatic.Items.bone_meal_mixture, () -> new PlantMixture());

        public static final DeferredHolder<Item, Item> GLOWSTONE_MIXTURE = zTool
                        .register(zStatic.Items.glowstone_mixture, () -> new PlantMixture());

        public static final DeferredHolder<Item, Item> AMETHYST_MIXTURE = zTool
                        .register(zStatic.Items.amethyst_mixture, () -> new PlantMixture());
        // ---------------------------------------------------------------------------------------//
        public static final DeferredHolder<Item, Item> RESISTIVE_STONE_CIRCUIT = Material
                        .craftingItem(zStatic.Items.CraftingComponents.resistive_stone_circuit);

        public static final DeferredHolder<Item, Item> COPPER_COIL = Material
                        .craftingItem(zStatic.Items.CraftingComponents.copper_coil);

        public static final DeferredHolder<Item, Item> LIGHT_BULB = Material
                        .craftingItem(zStatic.Items.CraftingComponents.light_bulb);

        public static final DeferredHolder<Item, Item> MAGNETIC_STONE_CIRCUIT = Material
                        .craftingItem(zStatic.Items.CraftingComponents.magnetic_stone_circuit);

        public static final DeferredHolder<Item, Item> NETHER_CIRCUIT = Material
                        .craftingItem(zStatic.Items.CraftingComponents.nether_circuit);

        public static final DeferredHolder<Item, Item> METAL_BOLTS = Material
                        .craftingItem(zStatic.Items.CraftingComponents.metal_bolts);

        public static final DeferredHolder<Item, Item> STONE_CIRCUIT = Material
                        .craftingItem(zStatic.Items.CraftingComponents.stone_circuit);

        public static final DeferredHolder<Item, Item> SUPERCONDUCTOR = Material
                        .craftingItem(zStatic.Items.CraftingComponents.superconductor);

        public static final DeferredHolder<Item, Item> WIRED_NETHER_PLATE = Material
                        .craftingItem(zStatic.Items.CraftingComponents.wired_nether_plate);

        public static final DeferredHolder<Item, Item> WIRED_STONE_PLATE = Material
                        .craftingItem(zStatic.Items.CraftingComponents.wired_stone_plate);
        // ---------------------------------------------------------------------------------------//
        public static final DeferredHolder<Item, Item> CARBON_DUST = Material
                        .resourceDust(zStatic.ResourceMaterial.carbon);
        // ---------------------------------------------------------------------------------------//
        public static final DeferredHolder<Item, Item> COPPER_FOIL = Material
                        .resourceFoil(zStatic.ResourceMaterial.copper);

        public static final DeferredHolder<Item, Item> GOLD_FOIL = Material.resourceFoil(zStatic.ResourceMaterial.gold);
        // ---------------------------------------------------------------------------------------//
        public static final DeferredHolder<Item, Item> AQUAMARINE = Material
                        .resourceGem(zStatic.ResourceMaterial.aquamarine);

        // public static final DeferredHolder<Item, Item> ONYX =
        // Material.resourceGem(zStatic.ResourceMaterial.onyx);

        public static final DeferredHolder<Item, Item> SILICON = Material.resourceGem(zStatic.ResourceMaterial.silicon);
        // ---------------------------------------------------------------------------------------//
        public static final DeferredHolder<Item, Item> ADVANCED_ALLOY_INGOT = Material
                        .resourceIngot(zStatic.ResourceMaterial.advanced_alloy);

        public static final DeferredHolder<Item, Item> STEEL_INGOT = Material
                        .resourceIngot(zStatic.ResourceMaterial.steel);
        // ---------------------------------------------------------------------------------------//
        public static final DeferredHolder<Item, Item> ADVANCED_ALLOY_NUGGET = Material
                        .resourceNugget(zStatic.ResourceMaterial.advanced_alloy);

        public static final DeferredHolder<Item, Item> STEEL_NUGGET = Material
                        .resourceNugget(zStatic.ResourceMaterial.steel);
        // ---------------------------------------------------------------------------------------//
        public static final DeferredHolder<Item, Item> ADVANCED_ALLOY_PLATE = Material
                        .resourcePlate(zStatic.ResourceMaterial.advanced_alloy);

        public static final DeferredHolder<Item, Item> CARBON_PLATE = Material
                        .resourcePlate(zStatic.ResourceMaterial.carbon);

        public static final DeferredHolder<Item, Item> COPPER_PLATE = Material
                        .resourcePlate(zStatic.ResourceMaterial.copper);

        public static final DeferredHolder<Item, Item> GOLD_PLATE = Material
                        .resourcePlate(zStatic.ResourceMaterial.gold);

        public static final DeferredHolder<Item, Item> IRON_PLATE = Material
                        .resourcePlate(zStatic.ResourceMaterial.iron);

        public static final DeferredHolder<Item, Item> MATRIX_PLATE = Material
                        .resourcePlate(zStatic.ResourceMaterial.matrix);

        public static final DeferredHolder<Item, Item> MIXED_PLATE = Material
                        .resourcePlate(zStatic.ResourceMaterial.mixed);

        public static final DeferredHolder<Item, Item> NETHERRACK_PLATE = Material
                        .resourcePlate(zStatic.ResourceMaterial.netherrack);

        public static final DeferredHolder<Item, Item> STEEL_PLATE = Material
                        .resourcePlate(zStatic.ResourceMaterial.steel);

        public static final DeferredHolder<Item, Item> STONE_PLATE = Material
                        .resourcePlate(zStatic.ResourceMaterial.stone);
        // ---------------------------------------------------------------------------------------//
        public static final DeferredHolder<Item, Item> RAW_SILICON = Material
                        .resourceRaw(zStatic.ResourceMaterial.silicon);
        // ---------------------------------------------------------------------------------------//
        public static final DeferredHolder<Item, Item> SILICON_SHARD = Material
                        .resourceShard(zStatic.ResourceMaterial.silicon);
        // ---------------------------------------------------------------------------------------//
        public static final DeferredHolder<Item, Item> AMERICIUM = Material
                        .resourcePellet(zStatic.ResourceMaterial.americium);
        public static final DeferredHolder<Item, Item> BERKELIUM = Material
                        .resourcePellet(zStatic.ResourceMaterial.berkelium);
        public static final DeferredHolder<Item, Item> CALIFORNIUM = Material
                        .resourcePellet(zStatic.ResourceMaterial.californium);
        public static final DeferredHolder<Item, Item> CURIUM = Material
                        .resourcePellet(zStatic.ResourceMaterial.curium);
        public static final DeferredHolder<Item, Item> NEPTUNIUM = Material
                        .resourcePellet(zStatic.ResourceMaterial.neptunium);
        public static final DeferredHolder<Item, Item> PLUTONIUM = Material
                        .resourcePellet(zStatic.ResourceMaterial.plutonium);
        public static final DeferredHolder<Item, Item> THORIUM = Material
                        .resourcePellet(zStatic.ResourceMaterial.thorium);
        public static final DeferredHolder<Item, Item> URANIUM = Material
                        .resourcePellet(zStatic.ResourceMaterial.uranium);
        // ---------------------------------------------------------------------------------------//
        public static final DeferredHolder<Item, Item> CREEPER_GALL = Material
                        .tooltippedItem(zStatic.MobDrop.creeper_gall, zMobDrop);
        public static final DeferredHolder<Item, Item> ENDERMAN_HEART = Material
                        .tooltippedItem(zStatic.MobDrop.enderman_heart, zMobDrop);
        public static final DeferredHolder<Item, Item> GHAST_BLADDER = Material
                        .tooltippedItem(zStatic.MobDrop.ghast_bladder, zMobDrop);
        public static final DeferredHolder<Item, Item> GUARDIAN_SCALE = Material
                        .tooltippedItem(zStatic.MobDrop.guardian_scale, zMobDrop);
        public static final DeferredHolder<Item, Item> SILVERFISH_DUST = Material
                        .tooltippedItem(zStatic.MobDrop.silverfish_dust, zMobDrop);
        public static final DeferredHolder<Item, Item> SLIME_BOLUS = Material
                        .tooltippedItem(zStatic.MobDrop.slime_bolus, zMobDrop);
        public static final DeferredHolder<Item, Item> VENOM_SAC = Material.tooltippedItem(zStatic.MobDrop.venom_sac,
                        zMobDrop);
        public static final DeferredHolder<Item, Item> WITHERFLESH = Material
                        .tooltippedItem(zStatic.MobDrop.witherflesh, zMobDrop);
        public static final DeferredHolder<Item, Item> ZOMBIE_LIVER = Material
                        .tooltippedItem(zStatic.MobDrop.zombie_liver, zMobDrop);
        // ---------------------------------------------------------------------------------------//
        public static final DeferredHolder<Item, Item> CARBON_FIBER = Material
                        .resourceItem("carbon_fiber");

        public static final DeferredHolder<Item, Item> INFERNAL_EMBER = Material
                        .resourceItem("infernal_ember");

        public static final DeferredHolder<Item, Item> GLUE = Material.droplet(zStatic.Fluids.GLUE);

        public static final DeferredHolder<Item, Item> OIL = Material.droplet(zStatic.Fluids.OIL);

        public static final DeferredHolder<Item, Item> SAP = Material.droplet(zStatic.Fluids.SAP);

        public static final DeferredHolder<Item, Item> WOODEN_GEAR = Material
                        .resourceItem("wooden_gear");
        public static final DeferredHolder<Item, Item> WASTE_FRAGMENT = Material
                        .resourceItem("waste_fragment");
        public static final DeferredHolder<Item, Item> WASTE = Material
                        .resourceItem("waste");
        public static final DeferredHolder<Item, Item> GHOUL_HEART = Material
                        .resourceItem("ghoul_heart");
        public static final DeferredHolder<Item, Item> STONE_PEBBLE = Material
                        .resourceItem("stone_pebble");
        // ---------------------------------------------------------------------------------------//

}
