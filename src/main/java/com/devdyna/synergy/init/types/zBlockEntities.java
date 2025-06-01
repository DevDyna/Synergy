package com.devdyna.synergy.init.types;

import com.devdyna.synergy.Database;
import com.devdyna.synergy.Main;
import com.devdyna.synergy.init.builder.Sprinkler.SprinklerBE;
import com.devdyna.synergy.init.builder.pipeBlocks.nodes.NodeBE;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
@SuppressWarnings("null")
public class zBlockEntities {
    public static void register(IEventBus bus) {
        zBE.register(bus);
    }
    // ---------------------------------------------------------------------------------------//

    public static final DeferredRegister<BlockEntityType<?>> zBE = DeferredRegister
            .create(BuiltInRegistries.BLOCK_ENTITY_TYPE, Main.ID);
    // ---------------------------------------------------------------------------------------//
    

    
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<SprinklerBE>> SPRINKLER = zBE
            .register(
                    Database.Blocks.sprinkler,
                    () -> BlockEntityType.Builder.of(SprinklerBE::new,
                            zBlocks.SPRINKLER.get()).build(null));
        
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<NodeBE>> PIPE_NODE = zBE
            .register(
                    Database.Blocks.node,
                    () -> BlockEntityType.Builder.of(NodeBE::new,
                            zBlocks.NODE.get()).build(null));


}
