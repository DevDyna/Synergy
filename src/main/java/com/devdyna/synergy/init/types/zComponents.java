package com.devdyna.synergy.init.types;

import static com.devdyna.synergy.Main.ID;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.GlobalPos;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

@SuppressWarnings("removal")
public class zComponents {
        public static void register(IEventBus bus) {
                zComponents.register(bus);

        }
        // ---------------------------------------------------------------------------------------//

        public static final DeferredRegister<DataComponentType<?>> zComponents = DeferredRegister
                        .createDataComponents(Registries.DATA_COMPONENT_TYPE,ID);
        // ---------------------------------------------------------------------------------------//

        public static final DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> FE_STORED = zComponents
                        .register(
                                        "fe_stored",
                                        () -> DataComponentType.<Integer>builder()
                                                        .persistent(Codec.INT.orElse(0))
                                                        .networkSynchronized(ByteBufCodecs.VAR_INT)
                                                        .build());

        public static final DeferredHolder<DataComponentType<?>, DataComponentType<GlobalPos>> GLOBAL_POS = zComponents
                        .register("global_pos",
                                        () -> DataComponentType.<GlobalPos>builder()
                                                        .persistent(GlobalPos.CODEC)
                                                        .networkSynchronized(GlobalPos.STREAM_CODEC)
                                                        .build());

        public static final DeferredHolder<DataComponentType<?>, DataComponentType<BlockPos>> BLOCKPOS = zComponents
                        .register("blockpos",
                                        () -> DataComponentType.<BlockPos>builder()
                                                        .persistent(BlockPos.CODEC)
                                                        .networkSynchronized(BlockPos.STREAM_CODEC)
                                                        .build());

}
