package com.devdyna.synergy.init.types;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import com.devdyna.synergy.zStatic;

import com.devdyna.synergy.Main;
import com.devdyna.synergy.init.Material;
import com.devdyna.synergy.init.builder.pipeBlocks.pipeBlock;
import com.devdyna.synergy.init.builder.pipeBlocks.nodes.blocks.*;
import com.devdyna.synergy.init.builder.reactor.cell.FuelCellBlock;
import com.devdyna.synergy.init.builder.reactor.controller.ReactorControllerBlock;
import com.devdyna.synergy.init.builder.reactor.cooler.IronCooler;
import com.devdyna.synergy.init.builder.reactor.moderator.GraphiteModerator;
import com.devdyna.synergy.init.builder.reactor.port.ReactorPortBlock;
import com.devdyna.synergy.init.builder.solar_panel.SolarPanelBLK;
import com.devdyna.synergy.init.builder.sprinkler.SprinklerBLK;
import com.devdyna.synergy.init.builder.*;
import com.devdyna.synergy.init.builder.chests.TinyChestBK;
import com.devdyna.synergy.init.builder.crops.cultivated.*;
import com.devdyna.synergy.init.builder.crops.mushroom.*;
import com.devdyna.synergy.init.builder.crops.wild.*;
import com.devdyna.synergy.init.builder.harvester.HarvesterBLK;

public class zBlocks {
        public static void register(IEventBus bus) {
                zBlock.register(bus);
                zBlockItem.register(bus);
                zDepositBlocks.register(bus);
                zDepositOres.register(bus);
                zCrop.register(bus);
                zWildCrop.register(bus);
                zDecorative.register(bus);
                zBlockSlab.register(bus);
                zBlockStair.register(bus);
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
        public static final DeferredRegister.Blocks zBlockSlab = DeferredRegister.createBlocks(Main.ID);
        public static final DeferredRegister.Blocks zBlockStair = DeferredRegister.createBlocks(Main.ID);

        // ---------------------------------------------------------------------------------------//

        public static final DeferredHolder<Block, azalea> AZALEA = zBlock.register(zStatic.Blocks.azalea,
                        () -> new azalea());

        public static final DeferredHolder<Block, Block> SPRINKLER = Material.registerItemBlock(
                        zStatic.Blocks.sprinkler,
                        () -> new SprinklerBLK());

        public static final DeferredHolder<Block, Block> SOLAR_PANEL = Material.registerItemBlock(
                        zStatic.Blocks.solar_panel,
                        () -> new SolarPanelBLK());

        public static final DeferredHolder<Block, Block> HARVESTER = Material.registerItemBlock(
                        zStatic.Blocks.harvester,
                        () -> new HarvesterBLK());

        public static final DeferredHolder<Block, Block> PIPE = Material.registerItemBlock(zStatic.Blocks.pipe,
                        () -> new pipeBlock());

        public static final DeferredHolder<Block, Block> ITEM_TRANSFER = Material.registerItemBlock(
                        zStatic.PipeStuff.nodes.ItemTransfer,
                        () -> new ItemTranfer());

        public static final DeferredHolder<Block, Block> ITEM_PROVIDER = Material.registerItemBlock(
                        zStatic.PipeStuff.nodes.ItemProvider,
                        () -> new ItemProvider());

        public static final DeferredHolder<Block, Block> ITEM_RETRIEVAL = Material.registerItemBlock(
                        zStatic.PipeStuff.nodes.ItemRetrieval,
                        () -> new ItemRetrieval());

        public static final DeferredHolder<Block, Block> RICE = zCrop.register(zStatic.Plants.RICE,
                        () -> new rice());

        public static final DeferredHolder<Block, Block> CAVE_WHEAT = zCrop.register(zStatic.Plants.CAVE_WHEAT,
                        () -> new cave_wheat());

        public static final DeferredHolder<Block, Block> COTTON = zCrop.register(zStatic.Plants.COTTON,
                        () -> new cotton());

        public static final DeferredHolder<Block, Block> BLUE_CUP_MUSHROOM = zCrop.register(
                        zStatic.Plants.BLUE_CUP_MUSHROOM,
                        () -> new blue_cup());

        public static final DeferredHolder<Block, Block> VIOLET_WEBCAP_MUSHROOM = zCrop.register(
                        zStatic.Plants.VIOLET_WEBCAP_MUSHROOM,
                        () -> new violet_webcap());

        public static final DeferredHolder<Block, Block> WILD_RICE = Material.registerItemBlock(zStatic.Wild.RICE,
                        () -> new wild_rice(), zWildCrop);

        public static final DeferredHolder<Block, Block> WILD_CAVE_WHEAT = Material.registerItemBlock(
                        zStatic.Wild.CAVE_WHEAT,
                        () -> new wild_cave_wheat(), zWildCrop);

        public static final DeferredHolder<Block, Block> WILD_COTTON = Material.registerItemBlock(zStatic.Wild.COTTON,
                        () -> new wild_cotton(), zWildCrop);

        public static final DeferredHolder<Block, Block> RUSTIC_METAL = Material
                        .DecoBlock(zStatic.DecorativeBlocks.rustic_metal,
                                        BlockBehaviour.Properties.of().sound(SoundType.METAL)
                                                        .strength(2.5f).mapColor(MapColor.RAW_IRON),
                                        zDecorative);

        public static final DeferredHolder<Block, Block> WAXED_PLANKS = Material
                        .DecoBlock(zStatic.DecorativeBlocks.waxed_planks,
                                        BlockBehaviour.Properties.of().sound(SoundType.WOOD)
                                                        .strength(1.5f).mapColor(MapColor.COLOR_BROWN),
                                        zDecorative);

        public static final DeferredHolder<Block, Block> ADVANCED_ALLOY_BLOCK = Material
                        .DecoBlock(zStatic.ResourceMaterial.advanced_alloy + "_" + zStatic.ResourceType.block,
                                        BlockBehaviour.Properties.of().sound(SoundType.METAL)
                                                        .strength(2f).mapColor(MapColor.METAL),
                                        zDecorative);

        public static final DeferredHolder<Block, Block> STEEL_BLOCK = Material
                        .DecoBlock(zStatic.ResourceMaterial.steel + "_" + zStatic.ResourceType.block,
                                        BlockBehaviour.Properties.of().sound(SoundType.METAL)
                                                        .strength(2f).mapColor(MapColor.METAL),
                                        zDecorative);

        public static final DeferredHolder<Block, Block> ADOBE = Material
                        .DecoBlock(zStatic.DecorativeBlocks.adobe,
                                        BlockBehaviour.Properties.of().sound(SoundType.STONE)
                                                        .strength(1.0f).mapColor(MapColor.SAND),
                                        zDecorative);

        public static final DeferredHolder<Block, Block> WAXED_PLANKS_SLAB = Material.slab(WAXED_PLANKS);
        public static final DeferredHolder<Block, Block> WAXED_PLANKS_STAIR = Material.stair(WAXED_PLANKS);

        public static final DeferredHolder<Block, Block> MACHINE_FRAME = Material.registerItemBlock(
                        zStatic.DecorativeBlocks.MachineFrame.basic,
                        () -> new MachineFrame(BlockBehaviour.Properties.of().strength(1.0f).sound(SoundType.METAL)));

        public static final DeferredHolder<Block, Block> ADVANCED_MACHINE_FRAME = Material.registerItemBlock(
                        zStatic.DecorativeBlocks.MachineFrame.advanced,
                        () -> new MachineFrame(BlockBehaviour.Properties.of().strength(1.0f).sound(SoundType.METAL)));

        public static final DeferredHolder<Block, Block> HEALER = Material.registerItemBlock(
                        zStatic.Blocks.healer,
                        () -> new Healer(BlockBehaviour.Properties.of().strength(1.0f).sound(SoundType.WOOL)));

        public static final DeferredHolder<Block, Block> IRON_COOLER = Material.registerItemBlock(
                        zStatic.ReactorStuff.CoolerTypes.IRON,
                        () -> new IronCooler());

        public static final DeferredHolder<Block, Block> GRAPHITE_MODERATOR = Material.registerItemBlock(
                        zStatic.ReactorStuff.ModeratorTypes.GRAPHITE,
                        () -> new GraphiteModerator());

        public static final DeferredHolder<Block, Block> REACTOR_PORT = Material.registerItemBlock(
                        zStatic.ReactorStuff.port,
                        () -> new ReactorPortBlock());

        public static final DeferredHolder<Block, Block> REACTOR_CONTROLLER = Material.registerItemBlock(
                        zStatic.ReactorStuff.controller,
                        () -> new ReactorControllerBlock());

        public static final DeferredHolder<Block, Block> REACTOR_FUEL_CELL = Material.registerItemBlock(
                        zStatic.ReactorStuff.fuel_cell,
                        () -> new FuelCellBlock());

        public static final DeferredHolder<Block, Block> WOODEN_TINY_CHEST = Material.registerItemBlock(
                        zStatic.Blocks.tiny_wooden_chest,
                        () -> new TinyChestBK());

                public static final DeferredHolder<Block, Block> ORNATE_TINY_CHEST = Material.registerItemBlock(
                        zStatic.Blocks.tiny_ornated_chest,
                        () -> new TinyChestBK());

                                public static final DeferredHolder<Block, Block> STONE_TINY_CHEST = Material.registerItemBlock(
                        zStatic.Blocks.tiny_stone_chest,
                        () -> new TinyChestBK());


        // ---------------------------------------------------------------------------------------//

        public static void registerLists() {

        }

}
