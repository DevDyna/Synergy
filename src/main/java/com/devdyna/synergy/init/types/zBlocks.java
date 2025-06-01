package com.devdyna.synergy.init.types;

import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import com.devdyna.synergy.Database;
import com.devdyna.synergy.Main;
import com.devdyna.synergy.init.Material;
import com.devdyna.synergy.init.builder.PottedAzalea;
import com.devdyna.synergy.init.builder.Sprinkler.SprinklerBLK;
import com.devdyna.synergy.init.builder.pipeBlocks.pipeBlock;
import com.devdyna.synergy.init.builder.pipeBlocks.nodes.NodeBlock;

public class zBlocks {
        public static void register(IEventBus bus) {
                registerLists();
                zBlock.register(bus);
                zBlockItem.register(bus);
        }

        // ---------------------------------------------------------------------------------------//
        public static final DeferredRegister.Blocks zBlock = DeferredRegister.createBlocks(Main.ID);
        public static final DeferredRegister.Blocks zBlockItem = DeferredRegister.createBlocks(Main.ID);
        // ---------------------------------------------------------------------------------------//

        public static final DeferredHolder<Block, PottedAzalea> AZALEA = zBlock.register(Database.Blocks.azalea,
                        () -> new PottedAzalea());

        public static final DeferredHolder<Block, ?> SPRINKLER = Material.registerItemBlock(Database.Blocks.sprinkler,
                        () -> new SprinklerBLK());

        public static final DeferredHolder<Block, ?> PIPE = Material.registerItemBlock(Database.Blocks.pipe,
                        () -> new pipeBlock());

        public static final DeferredHolder<Block, ?> NODE = Material.registerItemBlock(Database.Blocks.node,
                        () -> new NodeBlock());

        // ---------------------------------------------------------------------------------------//

        public static void registerLists() {

                // List.of(...).forEach(p -> ??);

        }

}
