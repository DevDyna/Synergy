package com.devdyna.synergy.init.types;

import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.Main;
import com.devdyna.synergy.init.builder.CroockItem;
import com.devdyna.synergy.init.builder._core.seeds.BaseSeedItem;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class zItems {
        public static void register(IEventBus bus) {
                zItem.register(bus);
                zTool.register(bus);
                zBlockItem.register(bus);
                zSeeds.register(bus);
                zCropExtra.register(bus);
                zFoods.register(bus);
                // zSpores.register(bus);
        }

        // ---------------------------------------------------------------------------------------//
        // DONT USE IT , ONLY FUNCTIONAL
        public static final DeferredRegister.Items zBlockItem = DeferredRegister.createItems(Main.ID);
        public static final DeferredRegister.Items zItem = DeferredRegister.createItems(Main.ID);
        public static final DeferredRegister.Items zTool = DeferredRegister.createItems(Main.ID);

        //
        public static final DeferredRegister.Items zFoods = DeferredRegister.createItems(Main.ID);
        public static final DeferredRegister.Items zCropExtra = DeferredRegister.createItems(Main.ID);
        public static final DeferredRegister.Items zSeeds = DeferredRegister.createItems(Main.ID);
        // public static final DeferredRegister.Items zSpores =
        // DeferredRegister.createItems(Main.ID);

        // ---------------------------------------------------------------------------------------//

        public static final DeferredHolder<Item, Item> AZALEA_SEEDS = zItem
                        .registerSimpleItem(zStatic.Items.Azalea.seeds);

        public static final DeferredHolder<Item, Item> SMALL_AZALEA_LEAF = zItem
                        .registerSimpleItem(zStatic.Items.Azalea.leaf);

        public static final DeferredHolder<Item, Item> SMALL_AZALEA_ROOTS = zItem
                        .registerSimpleItem(zStatic.Items.Azalea.roots);

        public static final DeferredHolder<Item, CroockItem> WOODEN_CROOK = zTool.register(zStatic.Items.wooden_crook,
                        () -> new CroockItem(BlockTags.LEAVES, Tiers.NETHERITE));

        // public static final DeferredHolder<Item, Item> RICE;
        public static final DeferredHolder<Item, ?> CAVE_WHEAT_SEEDS = zSeeds.register(zStatic.Seeds.CAVE_WHEAT_SEED,
                        () -> new BaseSeedItem(zBlocks.CAVE_WHEAT.get()));
        public static final DeferredHolder<Item, ?> RICE_SEED = zSeeds.register(zStatic.Seeds.RICE_SEED,
                        () -> new BaseSeedItem(zBlocks.RICE.get()));
        public static final DeferredHolder<Item, ?> COTTON_SEEDS = zSeeds.register(zStatic.Seeds.COTTON_SEED,
                        () -> new BaseSeedItem(zBlocks.COTTON.get()));
        public static final DeferredHolder<Item, ?> BLUE_CUP_SPORE = zSeeds.register(
                        zStatic.Seeds.BLUE_CUP_SPORE,
                        () -> new BaseSeedItem(zBlocks.BLUE_CUP_MUSHROOM.get()));
        public static final DeferredHolder<Item, ?> VIOLET_WEBCAP_SPORE = zSeeds.register(
                        zStatic.Seeds.VIOLET_WEBCAP_SPORE,
                        () -> new BaseSeedItem(zBlocks.VIOLET_WEBCAP_MUSHROOM.get()));

        // public static final DeferredHolder<Item, ?> CAVE_WHEAT = zCropExtra
        //                 .registerSimpleItem(zStatic.Plants.CAVE_WHEAT);
        public static final DeferredHolder<Item, ?> COTTON = zCropExtra.registerSimpleItem(zStatic.Plants.COTTON);
        public static final DeferredHolder<Item, ?> BLUE_CUP_MUSHROOM = zCropExtra
                        .registerSimpleItem(zStatic.Plants.BLUE_CUP_MUSHROOM);
        public static final DeferredHolder<Item, ?> VIOLET_WEBCAP_MUSHROOM = zCropExtra
                        .registerSimpleItem(zStatic.Plants.VIOLET_WEBCAP_MUSHROOM);

        /*
         * TODO
         * flax|
         * corn|
         * pigtail | (texture not completed)
         * mullberries | (bush)
         * grapple wild | (bush)
         * grapple cultivated | (multiblock)
         * quarrybush | (idk)
         * giant plants | (no texture)
         * blight spores | (todo model and logic) (vanilla?)
         * 
         * strawberries wild | (bush) (no texture)
         * strawberries cultivated | (pole-plant) (no texture)
         * blackberries wild | (bush) (no texture)
         * blackberries cultivated | (pole-plant) (no texture)
         * Nerium oleander (no texture)
         * Macrolepiota procera mushroom (double) (no texture)
         */

}
