package com.devdyna.synergy.init.types;

import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.api.plants.builder.BaseSeedItem;
import com.devdyna.synergy.Main;
import com.devdyna.synergy.init.builder.tools.Croock;
import com.devdyna.synergy.init.builder.tools.PipeRefactorizer;
import com.devdyna.synergy.init.builder.tools.PipeVisualizer;
import com.devdyna.synergy.init.builder.tools.Smasher;

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
                zItem.register(bus);
                zTool.register(bus);
                zBlockItem.register(bus);
                zSeeds.register(bus);
                zCropExtra.register(bus);
                zFoods.register(bus);
        }

        // ---------------------------------------------------------------------------------------//
        // DONT USE IT , ONLY FUNCTIONAL
        public static final DeferredRegister.Items zBlockItem = DeferredRegister.createItems(Main.ID);
        public static final DeferredRegister.Items zItem = DeferredRegister.createItems(Main.ID);
        public static final DeferredRegister.Items zTool = DeferredRegister.createItems(Main.ID);

        //
        public static final DeferredRegister.Items zDecorative = DeferredRegister.createItems(Main.ID);

        public static final DeferredRegister.Items zFoods = DeferredRegister.createItems(Main.ID);
        public static final DeferredRegister.Items zCropExtra = DeferredRegister.createItems(Main.ID);
        public static final DeferredRegister.Items zSeeds = DeferredRegister.createItems(Main.ID);
        // public static final DeferredRegister.Items zSpores =
        // DeferredRegister.createItems(Main.ID);

        // ---------------------------------------------------------------------------------------//

        public static final DeferredHolder<Item, Item> AZALEA_SEEDS = zSeeds
                        .registerSimpleItem(zStatic.Items.Azalea.seeds);

        public static final DeferredHolder<Item, Item> SMALL_AZALEA_LEAF = zCropExtra
                        .registerSimpleItem(zStatic.Items.Azalea.leaf);

        public static final DeferredHolder<Item, Item> SMALL_AZALEA_ROOTS = zCropExtra
                        .registerSimpleItem(zStatic.Items.Azalea.roots);

        public static final DeferredHolder<Item, Croock> WOODEN_CROOK = zTool.register(zStatic.Items.wooden_crook,
                        () -> new Croock(BlockTags.LEAVES, Tiers.NETHERITE));

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

        public static final DeferredHolder<Item, Item> PIPE_REFARCTORIZER = zTool
                        .register(zStatic.PipeStuff.tools.refactorizer, () -> new PipeRefactorizer());

        public static final DeferredHolder<Item, Item> SMASHER = zTool
                        .register(zStatic.Items.smasher, () -> new Smasher());

        public static final DeferredHolder<Item, Item> PIPE_VISUALIZER = zTool
                        .register(zStatic.PipeStuff.tools.visualizer, () -> new PipeVisualizer());

}
