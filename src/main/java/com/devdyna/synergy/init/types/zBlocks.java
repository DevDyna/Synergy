package com.devdyna.synergy.init.types;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.api.blockfamilies.BrickFamily;
import com.devdyna.synergy.Main;
import com.devdyna.synergy.init.Material;
import com.devdyna.synergy.init.builder.redstone.InvertedRepeater;
import com.devdyna.synergy.init.builder.redstone.PulseRepeater;
import com.devdyna.synergy.init.builder.redstone.RecursiveRepeater;
import com.devdyna.synergy.init.builder.survival.crushing_tub.CrushingTubBlock;
import com.devdyna.synergy.init.builder.survival.drying_rack.DryingRackBlock;
import com.devdyna.synergy.init.builder.survival.evaporation_basin.EvaporationBasinBlock;
import com.devdyna.synergy.init.builder.survival.placeable_bricks.PlaceableBrickBlock;
import com.devdyna.synergy.init.builder.*;
import com.devdyna.synergy.init.builder.automation.harvester.HarvesterBLK;
import com.devdyna.synergy.init.builder.automation.resource_gen.cobble.advanced.AdvancedCobbleGenBlock;
import com.devdyna.synergy.init.builder.automation.resource_gen.cobble.elite.EliteCobbleGenBlock;
import com.devdyna.synergy.init.builder.automation.resource_gen.cobble.simple.SimpleCobbleGenBlock;
import com.devdyna.synergy.init.builder.automation.resource_gen.water.advanced.AdvancedWaterGenBlock;
import com.devdyna.synergy.init.builder.automation.resource_gen.water.elite.EliteWaterGenBlock;
import com.devdyna.synergy.init.builder.automation.resource_gen.water.simple.SimpleWaterGenBlock;
import com.devdyna.synergy.init.builder.automation.solar_panel.SolarPanelBLK;
import com.devdyna.synergy.init.builder.automation.sprinkler.SprinklerBLK;
import com.devdyna.synergy.init.builder.automation.tank.FluidTankBlock;
import com.devdyna.synergy.init.builder.combat.Healer;
import com.devdyna.synergy.init.builder.decorative.MachineFrame;
import com.devdyna.synergy.init.builder.laser.*;
import com.devdyna.synergy.init.builder.laser.laser_rotor.LaserRotorBlock;
import com.devdyna.synergy.init.builder.laser.machine_gun.LaserMachineBlock;
import com.devdyna.synergy.init.builder.laser.sensor.LaserSensorBlock;
import com.devdyna.synergy.init.builder.magic.quern.QuernBlock;
import com.devdyna.synergy.init.builder.magic.tiny_chests.ornated.OrnatedTinyChestBlock;
import com.devdyna.synergy.init.builder.magic.tiny_chests.stone.StoneTinyChestBlock;
import com.devdyna.synergy.init.builder.magic.tiny_chests.wooden.WoodenTinyChestBlock;
import com.devdyna.synergy.init.builder.magic.urn.UrnBlock;
import com.devdyna.synergy.init.builder.magic.void_box.VoidBoxBlock;
import com.devdyna.synergy.init.builder.nuclear_reactor.controller.ReactorControllerBlock;
import com.devdyna.synergy.init.builder.nuclear_reactor.cooler.*;
import com.devdyna.synergy.init.builder.nuclear_reactor.fuel_cell.FuelCellBlock;
import com.devdyna.synergy.init.builder.nuclear_reactor.moderator.*;
import com.devdyna.synergy.init.builder.pipe_blocks.pipeBlock;
import com.devdyna.synergy.init.builder.pipe_blocks.nodes.blocks.*;
import com.devdyna.synergy.init.builder.plants.cultivated.*;
import com.devdyna.synergy.init.builder.plants.mushroom.*;
import com.devdyna.synergy.init.builder.plants.wild.*;

public class zBlocks {
        public static void register(IEventBus bus) {
                zBlock.register(bus);
                zOnlyBlock.register(bus);
                zHiddenBlock.register(bus);
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
                zMachineFrame.register(bus);
                zColumn.register(bus);
                zResourceGenerators.register(bus);
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
         * blocks with hidden item
         */
        public static final DeferredRegister.Blocks zHiddenBlock = DeferredRegister.createBlocks(Main.ID);
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
        public static final DeferredRegister.Blocks zColumn = DeferredRegister.createBlocks(Main.ID);
        public static final DeferredRegister.Blocks zBlockSlab = DeferredRegister.createBlocks(Main.ID);
        public static final DeferredRegister.Blocks zBlockStair = DeferredRegister.createBlocks(Main.ID);
        public static final DeferredRegister.Blocks zMachineFrame = DeferredRegister.createBlocks(Main.ID);

        /**
         * reactor stuff
         */
        public static final DeferredRegister.Blocks zModerators = DeferredRegister.createBlocks(Main.ID);
        public static final DeferredRegister.Blocks zCoolers = DeferredRegister.createBlocks(Main.ID);
        /**
         * resource generators
         */
        public static final DeferredRegister.Blocks zResourceGenerators = DeferredRegister.createBlocks(Main.ID);
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
                        zStatic.PipeStuff.nodes.Transfer.Item,
                        () -> new ItemTransfer());

        public static final DeferredHolder<Block, Block> ITEM_PROVIDER = Material.registerItemBlock(
                        zStatic.PipeStuff.nodes.Provider.Item,
                        () -> new ItemProvider());

        public static final DeferredHolder<Block, Block> ITEM_RETRIEVAL = Material.registerItemBlock(
                        zStatic.PipeStuff.nodes.Retrieval.Item,
                        () -> new ItemRetrieval());

        public static final DeferredHolder<Block, Block> ENERGY_TRANSFER = Material.registerItemBlock(
                        zStatic.PipeStuff.nodes.Transfer.Energy,
                        () -> new EnergyTransfer());

        public static final DeferredHolder<Block, Block> ENERGY_RETRIEVAL = Material.registerItemBlock(
                        zStatic.PipeStuff.nodes.Retrieval.Energy,
                        () -> new EnergyRetrieval());

        public static final DeferredHolder<Block, Block> FLUID_TRANSFER = Material.registerItemBlock(
                        zStatic.PipeStuff.nodes.Transfer.Fluid,
                        () -> new FluidTransfer());

        public static final DeferredHolder<Block, Block> FLUID_PROVIDER = Material.registerItemBlock(
                        zStatic.PipeStuff.nodes.Provider.Fluid,
                        () -> new FluidProvider());

        public static final DeferredHolder<Block, Block> FLUID_RETRIEVAL = Material.registerItemBlock(
                        zStatic.PipeStuff.nodes.Retrieval.Fluid,
                        () -> new FluidRetrieval());

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

        public static final DeferredHolder<Block, Block> WROUGHT_IRON_BLOCK = Material
                        .DecoBlock(zStatic.ResourceMaterial.wrought_iron + zStatic.ResourceType.block,
                                        BlockBehaviour.Properties.of().sound(SoundType.METAL)
                                                        .strength(2.5f).mapColor(MapColor.RAW_IRON),
                                        zDecorative);

        public static final DeferredHolder<Block, Block> CAST_IRON_BLOCK = Material
                        .DecoBlock(zStatic.ResourceMaterial.cast_iron + zStatic.ResourceType.block,
                                        BlockBehaviour.Properties.of().sound(SoundType.METAL)
                                                        .strength(2.5f).mapColor(MapColor.COLOR_BLACK),
                                        zDecorative);

        public static final DeferredHolder<Block, Block> CAST_IRON_TILES = Material
                        .DecoBlock(zStatic.ResourceMaterial.cast_iron + zStatic.ResourceType.tiles,
                                        BlockBehaviour.Properties.of().sound(SoundType.METAL)
                                                        .strength(2.5f).mapColor(MapColor.COLOR_BLACK),
                                        zDecorative);

        public static final DeferredHolder<Block, Block> CALCITE_BRICKS = Material
                        .DecoBlock(zStatic.ResourceMaterial.calcite + zStatic.ResourceType.bricks,
                                        BlockBehaviour.Properties.of().sound(SoundType.STONE)
                                                        .strength(1.5f).mapColor(MapColor.WOOL),
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

        public static final DeferredHolder<Block, Block> SMOOTH_ADOBE = Material
                        .DecoBlock(zStatic.DecorativeBlocks.smooth_adobe,
                                        BlockBehaviour.Properties.of().sound(SoundType.STONE)
                                                        .strength(1.0f).mapColor(MapColor.SAND),
                                        zDecorative);

        public static final DeferredHolder<Block, Block> WAXED_PLANKS_SLAB = Material.slab(WAXED_PLANKS);
        public static final DeferredHolder<Block, Block> WAXED_PLANKS_STAIR = Material.stair(WAXED_PLANKS);

        public static final DeferredHolder<Block, Block> BASIC_MACHINE_FRAME = Material.registerItemBlock(
                        zStatic.DecorativeBlocks.MachineFrame.basic,
                        () -> new MachineFrame(BlockBehaviour.Properties.of().strength(1.0f).sound(SoundType.METAL)),
                        zMachineFrame);

        public static final DeferredHolder<Block, Block> ADVANCED_MACHINE_FRAME = Material.registerItemBlock(
                        zStatic.DecorativeBlocks.MachineFrame.advanced,
                        () -> new MachineFrame(BlockBehaviour.Properties.of().strength(1.0f).sound(SoundType.METAL)),
                        zMachineFrame);

        public static final DeferredHolder<Block, Block> HEALER = Material.registerItemBlock(
                        zStatic.Blocks.healer,
                        () -> new Healer(BlockBehaviour.Properties.of().strength(1.0f).sound(SoundType.WOOL)));

        public static final DeferredHolder<Block, Block> SIMPLE_MODERATOR = Material.registerItemBlock(
                        zStatic.ReactorStuff.ModeratorTypes.SIMPLE,
                        () -> new SimpleModerator(), zModerators);

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
                        zStatic.Chests.WOODEN,
                        () -> new WoodenTinyChestBlock());

        public static final DeferredHolder<Block, Block> ORNATE_TINY_CHEST = Material.registerItemBlock(
                        zStatic.Chests.ORNATE,
                        () -> new OrnatedTinyChestBlock());

        public static final DeferredHolder<Block, Block> STONE_TINY_CHEST = Material.registerItemBlock(
                        zStatic.Chests.STONE,
                        () -> new StoneTinyChestBlock());

        public static final DeferredHolder<Block, Block> URN = Material.registerItemBlock(
                        zStatic.Blocks.urn,
                        () -> new UrnBlock());

        public static final DeferredHolder<Block, Block> QUERN = Material.registerItemBlock(
                        zStatic.Blocks.quern,
                        () -> new QuernBlock());

        // public static final DeferredHolder<Block, Block> QUERN_MOVING = zOnlyBlock.registerSimpleBlock(
        //                 zStatic.Blocks.quern + "_moving");

        public static final DeferredHolder<Block, Block> CLAY_BRICK = Material.registerItemBlock(
                        zStatic.DryableBricks.brick.clay + "_block",
                        () -> new PlaceableBrickBlock(BlockBehaviour.Properties.of()
                                        .sound(SoundType.GRAVEL).mapColor(MapColor.CLAY)),
                        zHiddenBlock);

        public static final DeferredHolder<Block, Block> PACKED_MUD_BRICK = Material.registerItemBlock(
                        zStatic.DryableBricks.brick.packed_mud + "_block",
                        () -> new PlaceableBrickBlock(BlockBehaviour.Properties.of()
                                        .sound(SoundType.GRAVEL).mapColor(MapColor.TERRACOTTA_ORANGE)),
                        zHiddenBlock);

        public static final DeferredHolder<Block, Block> FIRECLAY_BRICK = Material.registerItemBlock(
                        zStatic.DryableBricks.brick.fireclay + "_block",
                        () -> new PlaceableBrickBlock(BlockBehaviour.Properties.of()
                                        .sound(SoundType.GRAVEL).mapColor(MapColor.TERRACOTTA_ORANGE)),
                        zHiddenBlock);

        public static final DeferredHolder<Block, Block> BLAST_BRICK = Material.registerItemBlock(
                        zStatic.DryableBricks.brick.blast + "_block",
                        () -> new PlaceableBrickBlock(BlockBehaviour.Properties.of()
                                        .sound(SoundType.GRAVEL).mapColor(MapColor.TERRACOTTA_ORANGE)),
                        zHiddenBlock);

        public static final DeferredHolder<Block, Block> LASER_MACHINE = Material.registerItemBlock(
                        zStatic.Lazers.machine_gun,
                        () -> new LaserMachineBlock());

        public static final DeferredHolder<Block, Block> LASER_LENS = Material.registerItemBlock(
                        zStatic.Lazers.lens,
                        () -> new LaserLensBlock());

        public static final DeferredHolder<Block, Block> LASER_MIRROR = Material.registerItemBlock(
                        zStatic.Lazers.mirror,
                        () -> new LaserMirrorBlock());

        public static final DeferredHolder<Block, Block> LASER_SENSOR = Material.registerItemBlock(
                        zStatic.Lazers.sensor,
                        () -> new LaserSensorBlock());

        public static final DeferredHolder<Block, Block> PULSE_REPEATER = Material.registerItemBlock(
                        zStatic.Blocks.pulse_repeater,
                        () -> new PulseRepeater());

        public static final DeferredHolder<Block, Block> RECURSIVE_REPEATER = Material.registerItemBlock(
                        zStatic.Blocks.recursive_repeater,
                        () -> new RecursiveRepeater());

        public static final DeferredHolder<Block, Block> INVERTED_REPEATER = Material.registerItemBlock(
                        zStatic.Blocks.inverted_repeater,
                        () -> new InvertedRepeater());

        public static final DeferredHolder<Block, Block> LASER_ROTOR = Material.registerItemBlock(
                        zStatic.Lazers.rotor,
                        () -> new LaserRotorBlock());

        public static final DeferredHolder<Block, Block> FLUID_TANK = Material.registerItemBlock(
                        zStatic.Blocks.fluid_tank,
                        () -> new FluidTankBlock());

        public static final DeferredHolder<Block, Block> VOID_BOX = Material.registerItemBlock(
                        zStatic.Blocks.void_box,
                        () -> new VoidBoxBlock());

        // public static final DeferredHolder<Block, Block> VOID_BOX_LID = zOnlyBlock.registerSimpleBlock(
        //                 zStatic.Blocks.void_box + "_lid");

        @SuppressWarnings("deprecation")
        public static final DeferredHolder<Block, Block> BLAST_BRICKS = Material
                        .registerItemBlock(zStatic.DecorativeBlocks.blast_bricks,
                                        () -> new BlockToolTipped(
                                                        BlockBehaviour.Properties.of().sound(SoundType.NETHER_BRICKS)
                                                                        .strength(2.0F, 1200.0F)
                                                                        .mapColor(MapColor.TERRACOTTA_ORANGE),
                                                        "block.blast_proof"),
                                        zDecorative);

        public static final BrickFamily FIRECLAY_BRICKS = BrickFamily
                        .of(zStatic.DecorativeBlocks.FireClay.TYPE,
                                        BlockBehaviour.Properties.of().sound(SoundType.STONE)
                                                        .strength(1.0f).mapColor(MapColor.TERRACOTTA_ORANGE))
                        .bricks()
                        .pillar()
                        .tiles()
                        .brick_stair()
                        .brick_slab()
                        .tile_stair()
                        .tile_slab()
                        .createTag();

        public static final BrickFamily FIRECLAY_BRICK_MOSSY = BrickFamily
                        .of(zStatic.DecorativeBlocks.FireClay.mossy,
                                        BlockBehaviour.Properties.of().sound(SoundType.STONE)
                                                        .strength(1.0f).mapColor(MapColor.TERRACOTTA_ORANGE))
                        .bricks()
                        .pillar()
                        .tiles()
                        .brick_stair()
                        .brick_slab()
                        .tile_stair()
                        .tile_slab()
                        .createTag();

        public static final BrickFamily FIRECLAY_BRICK_CRACKED = BrickFamily
                        .of(zStatic.DecorativeBlocks.FireClay.cracked,
                                        BlockBehaviour.Properties.of().sound(SoundType.STONE)
                                                        .strength(1.0f).mapColor(MapColor.TERRACOTTA_ORANGE))
                        .bricks()
                        .pillar()
                        .tiles()
                        .brick_stair()
                        .brick_slab()
                        .tile_stair()
                        .tile_slab()
                        .createTag();

        public static final DeferredHolder<Block, Block> SIMPLE_WATER_GEN = Material.registerItemBlock(
                        zStatic.ResourceGenerators.Water.simple,
                        () -> new SimpleWaterGenBlock(), zResourceGenerators);
        public static final DeferredHolder<Block, Block> ADVANCED_WATER_GEN = Material.registerItemBlock(
                        zStatic.ResourceGenerators.Water.advanced,
                        () -> new AdvancedWaterGenBlock(), zResourceGenerators);
        public static final DeferredHolder<Block, Block> ELITE_WATER_GEN = Material.registerItemBlock(
                        zStatic.ResourceGenerators.Water.elite,
                        () -> new EliteWaterGenBlock(), zResourceGenerators);

        public static final DeferredHolder<Block, Block> SIMPLE_COBBLE_GEN = Material.registerItemBlock(
                        zStatic.ResourceGenerators.CobbleStone.simple,
                        () -> new SimpleCobbleGenBlock(), zResourceGenerators);
        public static final DeferredHolder<Block, Block> ADVANCED_COBBLE_GEN = Material.registerItemBlock(
                        zStatic.ResourceGenerators.CobbleStone.advanced,
                        () -> new AdvancedCobbleGenBlock(), zResourceGenerators);
        public static final DeferredHolder<Block, Block> ELITE_COBBLE_GEN = Material.registerItemBlock(
                        zStatic.ResourceGenerators.CobbleStone.elite,
                        () -> new EliteCobbleGenBlock(), zResourceGenerators);

        public static final DeferredHolder<Block, Block> CRUSHING_TUB = Material.registerItemBlock(
                        zStatic.Blocks.crushing_tub,
                        () -> new CrushingTubBlock());
        public static final DeferredHolder<Block, Block> EVAPORATION_BASIN = Material.registerItemBlock(
                        zStatic.Blocks.evaporation_basin,
                        () -> new EvaporationBasinBlock());

        public static final DeferredHolder<Block, Block> ACACIA_DRYING_RACK = Material.registerItemBlock(
                        WoodType.ACACIA.name() + "_" + zStatic.Blocks.drying_rack,
                        () -> new DryingRackBlock(BlockBehaviour.Properties.of()
                                        .mapColor(MapColor.COLOR_ORANGE).sound(SoundType.WOOD)));

        public static final DeferredHolder<Block, Block> BAMBOO_DRYING_RACK = Material.registerItemBlock(
                        WoodType.BAMBOO.name() + "_" + zStatic.Blocks.drying_rack,
                        () -> new DryingRackBlock(BlockBehaviour.Properties.of()
                                        .mapColor(MapColor.COLOR_GREEN).sound(SoundType.BAMBOO)));

        public static final DeferredHolder<Block, Block> BIRCH_DRYING_RACK = Material.registerItemBlock(
                        WoodType.BIRCH.name() + "_" + zStatic.Blocks.drying_rack,
                        () -> new DryingRackBlock(BlockBehaviour.Properties.of()
                                        .mapColor(MapColor.WOOL).sound(SoundType.WOOD)));

        public static final DeferredHolder<Block, Block> CHERRY_DRYING_RACK = Material.registerItemBlock(
                        WoodType.CHERRY.name() + "_" + zStatic.Blocks.drying_rack,
                        () -> new DryingRackBlock(BlockBehaviour.Properties.of()
                                        .mapColor(MapColor.COLOR_PINK).sound(SoundType.CHERRY_WOOD)));

        public static final DeferredHolder<Block, Block> CRIMSON_DRYING_RACK = Material.registerItemBlock(
                        WoodType.CRIMSON.name() + "_" + zStatic.Blocks.drying_rack,
                        () -> new DryingRackBlock(BlockBehaviour.Properties.of()
                                        .mapColor(MapColor.CRIMSON_STEM).sound(SoundType.NETHER_WOOD)));

        public static final DeferredHolder<Block, Block> DARK_OAK_DRYING_RACK = Material.registerItemBlock(
                        WoodType.DARK_OAK.name() + "_" + zStatic.Blocks.drying_rack,
                        () -> new DryingRackBlock(BlockBehaviour.Properties.of()
                                        .mapColor(MapColor.COLOR_BROWN).sound(SoundType.WOOD)));

        public static final DeferredHolder<Block, Block> JUNGLE_DRYING_RACK = Material.registerItemBlock(
                        WoodType.JUNGLE.name() + "_" + zStatic.Blocks.drying_rack,
                        () -> new DryingRackBlock(BlockBehaviour.Properties.of()
                                        .mapColor(MapColor.COLOR_ORANGE).sound(SoundType.WOOD)));

        public static final DeferredHolder<Block, Block> MANGROVE_DRYING_RACK = Material.registerItemBlock(
                        WoodType.MANGROVE.name() + "_" + zStatic.Blocks.drying_rack,
                        () -> new DryingRackBlock(BlockBehaviour.Properties.of()
                                        .mapColor(MapColor.COLOR_ORANGE).sound(SoundType.WOOD)));

        public static final DeferredHolder<Block, Block> OAK_DRYING_RACK = Material.registerItemBlock(
                        WoodType.OAK.name() + "_" + zStatic.Blocks.drying_rack,
                        () -> new DryingRackBlock(BlockBehaviour.Properties.of()
                                        .mapColor(MapColor.COLOR_ORANGE).sound(SoundType.WOOD)));

        public static final DeferredHolder<Block, Block> SPRUCE_DRYING_RACK = Material.registerItemBlock(
                        WoodType.SPRUCE.name() + "_" + zStatic.Blocks.drying_rack,
                        () -> new DryingRackBlock(BlockBehaviour.Properties.of()
                                        .mapColor(MapColor.COLOR_BROWN).sound(SoundType.WOOD)));

        public static final DeferredHolder<Block, Block> WARPED_DRYING_RACK = Material.registerItemBlock(
                        WoodType.WARPED.name() + "_" + zStatic.Blocks.drying_rack,
                        () -> new DryingRackBlock(BlockBehaviour.Properties.of()
                                        .mapColor(MapColor.WARPED_STEM).sound(SoundType.NETHER_WOOD)));

        // ---------------------------------------------------------------------------------------//

}
