package com.devdyna.modname_id.init;

import java.util.function.Supplier;

import com.devdyna.modname_id.Main;
import com.devdyna.modname_id.init.types.zBlockEntities;
import com.devdyna.modname_id.init.types.zBlockTag;
import com.devdyna.modname_id.init.types.zBlocks;
import com.devdyna.modname_id.init.types.zContainer;
import com.devdyna.modname_id.init.types.zCreativeTab;
import com.devdyna.modname_id.init.types.zFluidTags;
import com.devdyna.modname_id.init.types.zFluidTypes;
import com.devdyna.modname_id.init.types.zFluids;
import com.devdyna.modname_id.init.types.zHandlers;
import com.devdyna.modname_id.init.types.zItemTag;
import com.devdyna.modname_id.init.types.zItems;
import com.devdyna.modname_id.init.types.zProperties;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
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
                                ResourceLocation.fromNamespaceAndPath(Main.MODID, name));
        }

        /**
         * create an blocktag
         */
        public static TagKey<Block> tagBlock(String name) {
                return TagKey.create(BuiltInRegistries.BLOCK.key(),
                                ResourceLocation.fromNamespaceAndPath(Main.MODID, name));
        }

        /**
         * create an fluidtag
         */
        public static TagKey<Fluid> tagFluid(String name) {
                return TagKey.create(BuiltInRegistries.FLUID.key(),
                                ResourceLocation.fromNamespaceAndPath(Main.MODID, name));
        }

}
