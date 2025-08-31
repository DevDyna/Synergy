package com.devdyna.synergy.init.types;

import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.Main;
import com.devdyna.synergy.init.builder.chests.wchestTinyBE;
import com.devdyna.synergy.init.builder.harvester.HarvesterBE;
import com.devdyna.synergy.init.builder.pipeBlocks.nodes.blockentities.*;
import com.devdyna.synergy.init.builder.reactor.controller.ReactorControllerBE;
import com.devdyna.synergy.init.builder.reactor.controller.ReactorControllerBlock;
import com.devdyna.synergy.init.builder.reactor.port.ReactorPortBE;
import com.devdyna.synergy.init.builder.solar_panel.SolarPanelBE;
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

        public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<HarvesterBE>> HARVESTER = zBE
                        .register(
                                        zStatic.Blocks.harvester,
                                        () -> BlockEntityType.Builder.of(HarvesterBE::new,
                                                        zBlocks.HARVESTER.get()).build(null));

        public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<SolarPanelBE>> SOLAR_PANEL = zBE
                        .register(
                                        zStatic.Blocks.solar_panel,
                                        () -> BlockEntityType.Builder.of(SolarPanelBE::new,
                                                        zBlocks.SOLAR_PANEL.get()).build(null));

        public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ItemTransferBE>> ITEM_TRANSFER = zBE
                        .register(
                                        zStatic.PipeStuff.nodes.ItemTransfer,
                                        () -> BlockEntityType.Builder.of(ItemTransferBE::new,
                                                        zBlocks.ITEM_TRANSFER.get()).build(null));

        public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ItemProviderBE>> ITEM_PROVIDER = zBE
                        .register(
                                        zStatic.PipeStuff.nodes.ItemProvider,
                                        () -> BlockEntityType.Builder.of(ItemProviderBE::new,
                                                        zBlocks.ITEM_PROVIDER.get()).build(null));

        public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ItemRetrievalBE>> ITEM_RETRIEVAL = zBE
                        .register(
                                        zStatic.PipeStuff.nodes.ItemRetrieval,
                                        () -> BlockEntityType.Builder.of(ItemRetrievalBE::new,
                                                        zBlocks.ITEM_RETRIEVAL.get()).build(null));

        public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ReactorPortBE>> REACTOR_PORT = zBE
                        .register(
                                        zStatic.ReactorStuff.port,
                                        () -> BlockEntityType.Builder.of(ReactorPortBE::new,
                                                        zBlocks.REACTOR_PORT.get()).build(null));

        public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ReactorControllerBE>> REACTOR_CONTROLLER = zBE
                        .register(
                                        zStatic.ReactorStuff.controller,
                                        () -> BlockEntityType.Builder.of(ReactorControllerBE::new,
                                                        zBlocks.REACTOR_CONTROLLER.get()).build(null));

        public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<wchestTinyBE>> WOODEN_TINY_CHEST = zBE
                        .register(
                                        zStatic.Blocks.tiny_wooden_chest,
                                        () -> BlockEntityType.Builder.of(wchestTinyBE::new,
                                                        zBlocks.WOODEN_TINY_CHEST.get()).build(null));

}
