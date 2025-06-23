package com.devdyna.synergy.init.types;

import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.Main;
import com.devdyna.synergy.init.builder.pipeBlocks.nodes.blockentities.*;
import com.devdyna.synergy.init.builder.sprinkler.SprinklerBE;

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
                                        zStatic.Blocks.sprinkler,
                                        () -> BlockEntityType.Builder.of(SprinklerBE::new,
                                                        zBlocks.SPRINKLER.get()).build(null));

        public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ItemTransferBE>> ITEM_TRANSFER = zBE
                        .register(
                                        zStatic.PipeStuff.nodes.ITransfer,
                                        () -> BlockEntityType.Builder.of(ItemTransferBE::new,
                                                        zBlocks.ITEM_TRANSFER.get()).build(null));

        public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ItemProviderBE>> ITEM_PROVIDER = zBE
                        .register(
                                        zStatic.PipeStuff.nodes.IGen,
                                        () -> BlockEntityType.Builder.of(ItemProviderBE::new,
                                                        zBlocks.ITEM_PROVIDER.get()).build(null));

}
