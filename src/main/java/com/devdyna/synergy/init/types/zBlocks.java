package com.devdyna.synergy.init.types;

import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import com.devdyna.synergy.zStatic;

import java.util.ArrayList;
import java.util.List;

import com.devdyna.synergy.Main;
import com.devdyna.synergy.init.Material;
import com.devdyna.synergy.init.builder.Deposit;
import com.devdyna.synergy.init.builder.Extractor;
import com.devdyna.synergy.init.builder.Generator;
import com.devdyna.synergy.init.builder.PottedAzalea;
import com.devdyna.synergy.init.builder.Sprinkler.SprinklerBLK;
import com.devdyna.synergy.init.builder.pipeBlocks.pipeBlock;
import com.devdyna.synergy.init.builder.pipeBlocks.nodes.NodeBlock;

public class zBlocks {
        public static void register(IEventBus bus) {
                zBlock.register(bus);
                zBlockItem.register(bus);
                registerLists();
        }

        // ---------------------------------------------------------------------------------------//
        public static final DeferredRegister.Blocks zBlock = DeferredRegister.createBlocks(Main.ID);
        public static final DeferredRegister.Blocks zBlockItem = DeferredRegister.createBlocks(Main.ID);
        // ---------------------------------------------------------------------------------------//

        public static final DeferredHolder<Block, PottedAzalea> AZALEA = zBlock.register(zStatic.Blocks.azalea,
                        () -> new PottedAzalea());

        public static final DeferredHolder<Block, ?> SPRINKLER = Material.registerItemBlock(zStatic.Blocks.sprinkler,
                        () -> new SprinklerBLK());

        public static final DeferredHolder<Block, ?> PIPE = Material.registerItemBlock(zStatic.Blocks.pipe,
                        () -> new pipeBlock());

        public static final DeferredHolder<Block, ?> NODE = Material.registerItemBlock(zStatic.Blocks.node,
                        () -> new NodeBlock());

        public static final DeferredHolder<Block, ?> EXTRACTOR = Material.registerItemBlock(zStatic.Blocks.extractor,
                        () -> new Extractor());

        // ---------------------------------------------------------------------------------------//

        public static List<DeferredHolder<Block, ?>> deposits = new ArrayList<>(List.of());
        public static List<DeferredHolder<Block, ?>> generators = new ArrayList<>(List.of());

        public static void registerLists() {
                zMultiTags.ALL_DEPOSITS.forEach(de -> {
                        generators.add(Material.registerItemBlock(
                                        zStatic.Blocks.generator + "_" + de.suffix(),
                                        () -> new Generator(4, de.block(), de.item())));

                        deposits.add(Material.registerItemBlock(
                                        zStatic.Blocks.deposit + "_" + de.suffix(),
                                        () -> new Deposit()));
                });
        }

}
