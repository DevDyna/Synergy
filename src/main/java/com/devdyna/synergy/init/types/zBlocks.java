package com.devdyna.synergy.init.types;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import com.devdyna.synergy.zStatic;

import com.devdyna.synergy.Main;
import com.devdyna.synergy.init.Material;
import com.devdyna.synergy.init.builder.pipeBlocks.pipeBlock;
import com.devdyna.synergy.init.builder.pipeBlocks.nodes.blocks.*;
import com.devdyna.synergy.init.builder.quern.QuernBlock;
import com.devdyna.synergy.init.builder.reactor.cell.FuelCellBlock;
import com.devdyna.synergy.init.builder.reactor.controller.ReactorControllerBlock;
import com.devdyna.synergy.init.builder.reactor.cooler.*;
import com.devdyna.synergy.init.builder.reactor.moderator.*;
import com.devdyna.synergy.init.builder.solar_panel.SolarPanelBLK;
import com.devdyna.synergy.init.builder.sprinkler.SprinklerBLK;
import com.devdyna.synergy.init.builder.tinychests.TinyChestBK;
import com.devdyna.synergy.init.builder.urn.UrnBlock;
import com.devdyna.synergy.init.builder.*;
import com.devdyna.synergy.init.builder.crops.cultivated.*;
import com.devdyna.synergy.init.builder.crops.mushroom.*;
import com.devdyna.synergy.init.builder.crops.wild.*;
import com.devdyna.synergy.init.builder.harvester.HarvesterBLK;

public class zBlocks {
        public static void register(IEventBus bus) {
                zBlock.register(bus);
                zOnlyBlock.register(bus);
                zBlockFluids.register(bus);
                zBlockItem.register(bus);
                zDepositBlocks.register(bus);
                zDepositOres.register(bus);
                zCrop.register(bus);
                zWildCrop.register(bus);
                zDecorative.register(bus);
                zBlockSlab.register(bus);
                zBlockStair.register(bus);
                zCoolers.register(bus);
                zModerators.register(bus);
                registerLists();
        }

        // ---------------------------------------------------------------------------------------//
        /**
         * generic blocks
         */
        public static final DeferredRegister.Blocks zBlock = DeferredRegister.createBlocks(Main.ID);
        /**
         * blocks not items
         */
        public static final DeferredRegister.Blocks zOnlyBlock = DeferredRegister.createBlocks(Main.ID);
        /**
         * generic fluid blocks
         */
        public static final DeferredRegister.Blocks zBlockFluids = DeferredRegister.createBlocks(Main.ID);
        /**
         * generic item blocks
         */
        public static final DeferredRegister.Blocks zBlockItem = DeferredRegister.createBlocks(Main.ID);
        /**
         * wip
         */
        public static final DeferredRegister.Blocks zDepositBlocks = DeferredRegister.createBlocks(Main.ID);
        public static final DeferredRegister.Blocks zDepositOres = DeferredRegister.createBlocks(Main.ID);
        /**
         * crop stuff
         */
        public static final DeferredRegister.Blocks zCrop = DeferredRegister.createBlocks(Main.ID);
        public static final DeferredRegister.Blocks zWildCrop = DeferredRegister.createBlocks(Main.ID);
        /**
         * decorative stuff
         */
        public static final DeferredRegister.Blocks zDecorative = DeferredRegister.createBlocks(Main.ID);
        public static final DeferredRegister.Blocks zBlockSlab = DeferredRegister.createBlocks(Main.ID);
        public static final DeferredRegister.Blocks zBlockStair = DeferredRegister.createBlocks(Main.ID);

        /**
         * reactor stuff
         */
        public static final DeferredRegister.Blocks zModerators = DeferredRegister.createBlocks(Main.ID);
        public static final DeferredRegister.Blocks zCoolers = DeferredRegister.createBlocks(Main.ID);
        // ---------------------------------------------------------------------------------------//

        public static final DeferredHolder<Block, Block> AZALEA = Material.registerItemBlock(zStatic.Blocks.azalea,
                        () -> new azalea(), zBlock);

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

        public static final DeferredHolder<Block, Block> ENERGY_TRANSFER = Material.registerItemBlock(
                        zStatic.PipeStuff.nodes.EnergyTransfer,
                        () -> new EnergyTranfer());

        public static final DeferredHolder<Block, Block> ENERGY_PROVIDER = Material.registerItemBlock(
                        zStatic.PipeStuff.nodes.EnergyProvider,
                        () -> new EnergyProvider());

        public static final DeferredHolder<Block, Block> ENERGY_RETRIEVAL = Material.registerItemBlock(
                        zStatic.PipeStuff.nodes.EnergyRetrieval,
                        () -> new EnergyRetrieval());

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
                        .DecoBlock(zStatic.ResourceMaterial.advanced_alloy + zStatic.ResourceType.block,
                                        BlockBehaviour.Properties.of().sound(SoundType.METAL)
                                                        .strength(2f).mapColor(MapColor.METAL),
                                        zDecorative);

        public static final DeferredHolder<Block, Block> STEEL_BLOCK = Material
                        .DecoBlock(zStatic.ResourceMaterial.steel + zStatic.ResourceType.block,
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

        public static final DeferredHolder<Block, Block> SIMPLE_MODERATOR = Material.registerItemBlock(
                        zStatic.ReactorStuff.ModeratorTypes.SIMPLE,
                        () -> new SimpleModerator(), zModerators);
        public static final DeferredHolder<Block, Block> IMPROVED_MODERATOR = Material.registerItemBlock(
                        zStatic.ReactorStuff.ModeratorTypes.IMPROVED,
                        () -> new ImprovedModerator(), zModerators);
        public static final DeferredHolder<Block, Block> ADVANCED_MODERATOR = Material.registerItemBlock(
                        zStatic.ReactorStuff.ModeratorTypes.ADVANCED,
                        () -> new AdvancedModerator(), zModerators);
        public static final DeferredHolder<Block, Block> ELITE_MODERATOR = Material.registerItemBlock(
                        zStatic.ReactorStuff.ModeratorTypes.ELITE,
                        () -> new EliteModerator(), zModerators);

        public static final DeferredHolder<Block, Block> REACTOR_CONTROLLER = Material.registerItemBlock(
                        zStatic.ReactorStuff.controller,
                        () -> new ReactorControllerBlock());

        public static final DeferredHolder<Block, Block> REACTOR_FUEL_CELL = Material.registerItemBlock(
                        zStatic.ReactorStuff.fuel_cell,
                        () -> new FuelCellBlock());

        public static final DeferredHolder<Block, Block> SHADOW_COOLER = Material.registerItemBlock(
                        zStatic.ReactorStuff.CoolerTypes.SHADOW,
                        () -> new ShadowCooler(), zCoolers);
        public static final DeferredHolder<Block, Block> COPPER_COOLER = Material.registerItemBlock(
                        zStatic.ReactorStuff.CoolerTypes.COPPER,
                        () -> new CopperCooler(), zCoolers);
        public static final DeferredHolder<Block, Block> DIAMOND_COOLER = Material.registerItemBlock(
                        zStatic.ReactorStuff.CoolerTypes.DIAMOND,
                        () -> new DiamondCooler(), zCoolers);
        public static final DeferredHolder<Block, Block> EMERALD_COOLER = Material.registerItemBlock(
                        zStatic.ReactorStuff.CoolerTypes.EMERALD,
                        () -> new EmeraldCooler(), zCoolers);
        public static final DeferredHolder<Block, Block> ENDER_COOLER = Material.registerItemBlock(
                        zStatic.ReactorStuff.CoolerTypes.ENDER,
                        () -> new EnderCooler(), zCoolers);
        public static final DeferredHolder<Block, Block> FROST_COOLER = Material.registerItemBlock(
                        zStatic.ReactorStuff.CoolerTypes.FROST,
                        () -> new FrostCooler(), zCoolers);
        public static final DeferredHolder<Block, Block> GLOWSTONE_COOLER = Material.registerItemBlock(
                        zStatic.ReactorStuff.CoolerTypes.GLOWSTONE,
                        () -> new GlowstoneCooler(), zCoolers);
        public static final DeferredHolder<Block, Block> GOLD_COOLER = Material.registerItemBlock(
                        zStatic.ReactorStuff.CoolerTypes.GOLD,
                        () -> new GoldCooler(), zCoolers);
        public static final DeferredHolder<Block, Block> IRON_COOLER = Material.registerItemBlock(
                        zStatic.ReactorStuff.CoolerTypes.IRON,
                        () -> new IronCooler(), zCoolers);
        public static final DeferredHolder<Block, Block> LAPIS_COOLER = Material.registerItemBlock(
                        zStatic.ReactorStuff.CoolerTypes.LAPIS,
                        () -> new LapisCooler(), zCoolers);
        public static final DeferredHolder<Block, Block> NETHERITE_COOLER = Material.registerItemBlock(
                        zStatic.ReactorStuff.CoolerTypes.NETHERITE,
                        () -> new NetheriteCooler(), zCoolers);
        public static final DeferredHolder<Block, Block> QUARTZ_COOLER = Material.registerItemBlock(
                        zStatic.ReactorStuff.CoolerTypes.QUARTZ,
                        () -> new QuartzCooler(), zCoolers);
        public static final DeferredHolder<Block, Block> REDSTONE_COOLER = Material.registerItemBlock(
                        zStatic.ReactorStuff.CoolerTypes.REDSTONE,
                        () -> new RedstoneCooler(), zCoolers);
        public static final DeferredHolder<Block, Block> SCULK_COOLER = Material.registerItemBlock(
                        zStatic.ReactorStuff.CoolerTypes.SCULK,
                        () -> new SculkCooler(), zCoolers);
        public static final DeferredHolder<Block, Block> WATER_COOLER = Material.registerItemBlock(
                        zStatic.ReactorStuff.CoolerTypes.WATER,
                        () -> new WaterCooler(), zCoolers);

        public static final DeferredHolder<Block, Block> COOLER_BASE = Material.DecoBlock(
                        zStatic.ReactorStuff.CoolerTypes.base,
                        Properties.of().strength(1.0f).destroyTime(1.0f).sound(SoundType.CHAIN)
                                        .mapColor(MapColor.METAL),
                        zDecorative);

        public static final DeferredHolder<Block, Block> WOODEN_TINY_CHEST = Material.registerItemBlock(
                        zStatic.Blocks.tiny_wooden_chest,
                        () -> new TinyChestBK());

        public static final DeferredHolder<Block, Block> ORNATE_TINY_CHEST = Material.registerItemBlock(
                        zStatic.Blocks.tiny_ornated_chest,
                        () -> new TinyChestBK());

        public static final DeferredHolder<Block, Block> STONE_TINY_CHEST = Material.registerItemBlock(
                        zStatic.Blocks.tiny_stone_chest,
                        () -> new TinyChestBK());

        public static final DeferredHolder<Block, Block> URN = Material.registerItemBlock(
                        zStatic.Blocks.urn,
                        () -> new UrnBlock());

        public static final DeferredHolder<Block, Block> QUERN = Material.registerItemBlock(
                        zStatic.Blocks.quern,
                        () -> new QuernBlock());

        public static final DeferredHolder<Block, Block> QUERN_MOVING = zOnlyBlock.registerSimpleBlock(
                        zStatic.Blocks.quern + "_moving");

        // ---------------------------------------------------------------------------------------//

        public static void registerLists() {

        }

}
