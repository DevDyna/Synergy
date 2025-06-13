package com.devdyna.synergy.init;

import java.util.function.Supplier;

import com.devdyna.synergy.Main;
import com.devdyna.synergy.init.types.*;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class Material {
        public static void register(IEventBus bus) {
                zBlockEntities.register(bus);
                zBlocks.register(bus);
                zBlockTag.register(bus);
                zContainer.register(bus);
                zCreativeTab.register(bus);
                zFluidTags.register(bus);
                zFluidTypes.register(bus);
                zFluids.register(bus);
                zHandlers.register(bus);
                zItems.register(bus);
                zItemTag.register(bus);
                zProperties.register(bus);
                // zMixed.register(bus);
        }

        public static final BlockBehaviour.Properties bProp = BlockBehaviour.Properties.of();
        public static final Properties iProp = new Item.Properties();

        /**
         * register an block + item
         * 
         * @param sup () -> new Builder
         */
        public static DeferredHolder<Block, ?> registerItemBlock(String blockname, Supplier<? extends Block> sup) {
                return registerItemBlock(blockname, sup, zBlocks.zBlockItem);
        }

        /**
         * register an block + item
         * 
         * @param sup () -> new Builder
         * @param b   Blocks.zBlock
         */
        public static DeferredHolder<Block, ?> registerItemBlock(String blockname, Supplier<? extends Block> sup,
                        DeferredRegister.Blocks b) {
                DeferredHolder<Block, ?> block = b.register(blockname, sup);
                zItems.zBlockItem.registerSimpleBlockItem(block);
                return block;
        }

        /**
         * create an itemtag
         */
        public static TagKey<Item> tagItem(String name) {
                return TagKey.create(BuiltInRegistries.ITEM.key(),
                                ResourceLocation.fromNamespaceAndPath(Main.ID, name));
        }

        /**
         * create an blocktag
         */
        public static TagKey<Block> tagBlock(String name) {
                return TagKey.create(BuiltInRegistries.BLOCK.key(),
                                ResourceLocation.fromNamespaceAndPath(Main.ID, name));
        }

        /**
         * create an itemtag
         */
        public static TagKey<Item> tagItem(String name, String modname) {
                return TagKey.create(BuiltInRegistries.ITEM.key(),
                                ResourceLocation.fromNamespaceAndPath(modname, name));
        }

        /**
         * create an blocktag
         */
        public static TagKey<Block> tagBlock(String name, String modname) {
                return TagKey.create(BuiltInRegistries.BLOCK.key(),
                                ResourceLocation.fromNamespaceAndPath(modname, name));
        }

        /**
         * create an fluidtag
         */
        public static TagKey<Fluid> tagFluid(String name) {
                return TagKey.create(BuiltInRegistries.FLUID.key(),
                                ResourceLocation.fromNamespaceAndPath(Main.ID, name));
        }

        public static DeferredItem<ItemNameBlockItem> seedFoodItem(String name, Block b, int nutrition,
                        float saturationModifier, boolean fastToEat, boolean isAlwaysEdible) {
                var food = (new FoodProperties.Builder())
                                .nutrition(nutrition)
                                .saturationModifier(saturationModifier);

                if (fastToEat)
                        food.fast();

                if (isAlwaysEdible)
                        food.alwaysEdible();

                return zItems.zFoods.register(name,
                                () -> new ItemNameBlockItem(b, Material.iProp.food(food.build())));
        }

        public static DeferredItem<ItemNameBlockItem> seedItem(String name, Block b) {
                return zItems.zSeeds.register(name,
                                () -> new ItemNameBlockItem(b, Material.iProp));
        }

        // public static DeferredBlock<BaseCropBlock> seedBlock(String name, Item
        // seedItem) {
        // return zBlocks.zCrop.register(name,
        // () -> new BaseCropBlock(seedItem));
        // }

}
