package com.devdyna.synergy.init.types;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import com.devdyna.synergy.zStatic;

import com.devdyna.synergy.Main;
import com.devdyna.synergy.init.Material;
import com.devdyna.synergy.init.builder.pipeBlocks.pipeBlock;
import com.devdyna.synergy.init.builder.pipeBlocks.nodes.blocks.*;
import com.devdyna.synergy.init.builder.sprinkler.SprinklerBLK;
import com.devdyna.synergy.init.builder.crops.cultivated.*;
import com.devdyna.synergy.init.builder.crops.mushroom.*;
import com.devdyna.synergy.init.builder.crops.wild.*;

public class zBlocks {
        public static void register(IEventBus bus) {
                zBlock.register(bus);
                zBlockItem.register(bus);
                zDepositBlocks.register(bus);
                zDepositOres.register(bus);
                zCrop.register(bus);
                zWildCrop.register(bus);
                zDecorative.register(bus);
                registerLists();
        }

        // ---------------------------------------------------------------------------------------//
        public static final DeferredRegister.Blocks zBlock = DeferredRegister.createBlocks(Main.ID);
        public static final DeferredRegister.Blocks zBlockItem = DeferredRegister.createBlocks(Main.ID);
        public static final DeferredRegister.Blocks zDepositBlocks = DeferredRegister.createBlocks(Main.ID);
        public static final DeferredRegister.Blocks zDepositOres = DeferredRegister.createBlocks(Main.ID);
        public static final DeferredRegister.Blocks zCrop = DeferredRegister.createBlocks(Main.ID);
        public static final DeferredRegister.Blocks zWildCrop = DeferredRegister.createBlocks(Main.ID);
        public static final DeferredRegister.Blocks zDecorative = DeferredRegister.createBlocks(Main.ID);

        // ---------------------------------------------------------------------------------------//

        public static final DeferredHolder<Block, azalea> AZALEA = zBlock.register(zStatic.Blocks.azalea,
                        () -> new azalea());

        public static final DeferredHolder<Block, ?> SPRINKLER = Material.registerItemBlock(zStatic.Blocks.sprinkler,
                        () -> new SprinklerBLK());

        public static final DeferredHolder<Block, ?> PIPE = Material.registerItemBlock(zStatic.PipeStuff.pipe,
                        () -> new pipeBlock());

        public static final DeferredHolder<Block, ?> ITEM_TRANSFER = Material.registerItemBlock(
                        zStatic.PipeStuff.nodes.ItemTransfer,
                        () -> new ItemTranfer());

        public static final DeferredHolder<Block, ?> ITEM_PROVIDER = Material.registerItemBlock(
                        zStatic.PipeStuff.nodes.ItemProvider,
                        () -> new ItemProvider());

        public static final DeferredHolder<Block, ?> ITEM_RETRIEVAL = Material.registerItemBlock(
                        zStatic.PipeStuff.nodes.ItemRetrieval,
                        () -> new ItemRetrieval());

        public static final DeferredHolder<Block, ?> RICE = zCrop.register(zStatic.Plants.RICE,
                        () -> new rice());

        public static final DeferredHolder<Block, ?> CAVE_WHEAT = zCrop.register(zStatic.Plants.CAVE_WHEAT,
                        () -> new cave_wheat());

        public static final DeferredHolder<Block, ?> COTTON = zCrop.register(zStatic.Plants.COTTON,
                        () -> new cotton());

        public static final DeferredHolder<Block, ?> BLUE_CUP_MUSHROOM = zCrop.register(
                        zStatic.Plants.BLUE_CUP_MUSHROOM,
                        () -> new blue_cup());

        public static final DeferredHolder<Block, ?> VIOLET_WEBCAP_MUSHROOM = zCrop.register(
                        zStatic.Plants.VIOLET_WEBCAP_MUSHROOM,
                        () -> new violet_webcap());

        public static final DeferredHolder<Block, ?> WILD_RICE = Material.registerItemBlock(zStatic.Wild.RICE,
                        () -> new wild_rice(), zWildCrop);

        public static final DeferredHolder<Block, ?> WILD_CAVE_WHEAT = Material.registerItemBlock(
                        zStatic.Wild.CAVE_WHEAT,
                        () -> new wild_cave_wheat(), zWildCrop);

        public static final DeferredHolder<Block, ?> WILD_COTTON = Material.registerItemBlock(zStatic.Wild.COTTON,
                        () -> new wild_cotton(), zWildCrop);

        public static final DeferredHolder<Block, ?> INDUSTRIAL_METAL = Material
                        .registerItemBlock(zStatic.DecorativeBlocks.industrial_metal,
                                        () -> new Block(BlockBehaviour.Properties.of().sound(SoundType.METAL)
                                                        .strength(2.5f)),
                                        zDecorative);

        public static final DeferredHolder<Block, ?> WAXED_PLANKS = Material
                        .registerItemBlock(zStatic.DecorativeBlocks.waxed_planks,
                                        () -> new Block(BlockBehaviour.Properties.of().sound(SoundType.WOOD)
                                                        .strength(1.5f)),
                                        zDecorative);

        public static final DeferredHolder<Block, ?> ADOBE = Material
                        .registerItemBlock(zStatic.DecorativeBlocks.adobe,
                                        () -> new Block(BlockBehaviour.Properties.of().sound(SoundType.STONE)
                                                        .strength(1.0f)),
                                        zDecorative);

        // public static final DeferredHolder<Block, ?> EXTRACTOR =
        // Material.registerItemBlock(zStatic.Blocks.extractor,
        // () -> new Extractor());

        // ---------------------------------------------------------------------------------------//

        // public static List<DeferredHolder<Block, ?>> deposit_block = new
        // ArrayList<>(List.of());
        // public static List<DeferredHolder<Block, ?>> deposit_ores = new
        // ArrayList<>(List.of());

        // public static List<DeferredHolder<Block, ?>> generators = new
        // ArrayList<>(List.of());

        public static void registerLists() {

                // TODO NYI
                // zMultiTags.BLOCKS.forEach(de -> Material.registerItemBlock(
                // zStatic.Blocks.deposit + "_" + de.suffix(),
                // () -> new Deposit(), zDepositBlocks));

                // zMultiTags.ORES.forEach(de -> Material.registerItemBlock(
                // zStatic.Blocks.deposit + "_" + de.suffix(),
                // () -> new Deposit(), zDepositOres));
        }

}
