package com.devdyna.synergy.init.types;

import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import com.devdyna.synergy.zStatic;

import com.devdyna.synergy.Main;
import com.devdyna.synergy.init.Material;
import com.devdyna.synergy.init.builder.PottedAzalea;
import com.devdyna.synergy.init.builder.Sprinkler.SprinklerBLK;
import com.devdyna.synergy.init.builder.crops.*;
import com.devdyna.synergy.init.builder.pipeBlocks.pipeBlock;
import com.devdyna.synergy.init.builder.pipeBlocks.nodes.NodeBlock;

public class zBlocks {
        public static void register(IEventBus bus) {
                zBlock.register(bus);
                zBlockItem.register(bus);
                zDepositBlocks.register(bus);
                zDepositOres.register(bus);
                zCrop.register(bus);
                registerLists();
        }

        // ---------------------------------------------------------------------------------------//
        public static final DeferredRegister.Blocks zBlock = DeferredRegister.createBlocks(Main.ID);
        public static final DeferredRegister.Blocks zBlockItem = DeferredRegister.createBlocks(Main.ID);
        public static final DeferredRegister.Blocks zDepositBlocks = DeferredRegister.createBlocks(Main.ID);
        public static final DeferredRegister.Blocks zDepositOres = DeferredRegister.createBlocks(Main.ID);
        public static final DeferredRegister.Blocks zCrop = DeferredRegister.createBlocks(Main.ID);

        // ---------------------------------------------------------------------------------------//

        public static final DeferredHolder<Block, PottedAzalea> AZALEA = zBlock.register(zStatic.Blocks.azalea,
                        () -> new PottedAzalea());

        public static final DeferredHolder<Block, ?> SPRINKLER = Material.registerItemBlock(zStatic.Blocks.sprinkler,
                        () -> new SprinklerBLK());

        public static final DeferredHolder<Block, ?> PIPE = Material.registerItemBlock(zStatic.Blocks.pipe,
                        () -> new pipeBlock());

        public static final DeferredHolder<Block, ?> NODE = Material.registerItemBlock(zStatic.Blocks.node,
                        () -> new NodeBlock());

        public static final DeferredHolder<Block, ?> RICE = zCrop.register(zStatic.Plants.RICE,
                        () -> new rice());

        public static final DeferredHolder<Block, ?> CAVE_WHEAT =
        zBlocks.zCrop.register(zStatic.Plants.CAVE_WHEAT,
        () -> new cave_wheat());
        public static final DeferredHolder<Block, ?> COTTON =
        zBlocks.zCrop.register(zStatic.Plants.COTTON,
        () -> new cotton());
        public static final DeferredHolder<Block, ?> ELF_CUP_MUSHROOM =
        zBlocks.zCrop.register(
        zStatic.Plants.ELF_CUP_MUSHROOM,
        () -> new elf_cup());
        public static final DeferredHolder<Block, ?> CORTINARIUS_MUSHROOM =
        zBlocks.zCrop.register(
        zStatic.Plants.CORTINARIUS_MUSHROOM,
        () -> new cortinarius());

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
