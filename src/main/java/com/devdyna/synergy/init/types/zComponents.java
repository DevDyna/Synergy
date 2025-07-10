package com.devdyna.synergy.init.types;

import static com.devdyna.synergy.Main.ID;

import com.mojang.serialization.Codec;

import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.codec.ByteBufCodecs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

@SuppressWarnings("removal")
public class zComponents {
        public static void register(IEventBus bus) {
                zComponents.register(bus);

        }
        // ---------------------------------------------------------------------------------------//

        public static final DeferredRegister<DataComponentType<?>> zComponents = DeferredRegister
                        .createDataComponents(ID);
        // ---------------------------------------------------------------------------------------//

        public static final DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> FE_STORED = zComponents
                        .register(
                                        "fe_stored",
                                        () -> DataComponentType.<Integer>builder().persistent(Codec.INT.orElse(0))
                                                        .networkSynchronized(ByteBufCodecs.VAR_INT)
                                                        .build());

        public static final DeferredHolder<DataComponentType<?>, DataComponentType<String>> MODE = zComponents
                        .register(
                                        "mode",
                                        () -> DataComponentType.<String>builder().persistent(Codec.STRING)
                                                        .networkSynchronized(ByteBufCodecs.STRING_UTF8)
                                                        .build());
                                                        
        public static final DeferredHolder<DataComponentType<?>, DataComponentType<BlockPos>> BLOCKPOS = zComponents
                        .register(
                                        "blockpos",
                                        () -> DataComponentType.<BlockPos>builder().persistent(BlockPos.CODEC)
                                                        .networkSynchronized(BlockPos.STREAM_CODEC)
                                                        .build());

}
