package com.devdyna.synergy.api.machine.recipe;

import java.util.*;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.fluids.FluidStack;

public class IOPacked {
        public class input {

        }

        public class output {

                public record ItemOptional(Optional<ItemStack> item, float chance) {

                        public static final MapCodec<ItemOptional> CODEC = RecordCodecBuilder
                                        .mapCodec(inst -> inst.group(

                                                        ItemStack.CODEC
                                                                        .optionalFieldOf("item")
                                                                        .forGetter(ItemOptional::item),

                                                        Codec.floatRange(0.0F, 1.0F)
                                                                        .fieldOf("chance")
                                                                        .forGetter(ItemOptional::chance)

                                        ).apply(inst, ItemOptional::new));

                        public static final StreamCodec<RegistryFriendlyByteBuf, ItemOptional> STREAM_CODEC = StreamCodec
                                        .composite(
                                                        ByteBufCodecs.optional(ItemStack.STREAM_CODEC),
                                                        ItemOptional::item,
                                                        ByteBufCodecs.FLOAT, ItemOptional::chance,
                                                        ItemOptional::new);

                }

                public record FluidItem(Optional<ItemStack> item,
                                Optional<FluidStack> fluid) {

                        public static FluidItem of(Optional<ItemStack> item,
                                        Optional<FluidStack> fluid) {
                                return new FluidItem(item, fluid);
                        }

                        public static final MapCodec<FluidItem> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(

                                        ItemStack.CODEC
                                                        .optionalFieldOf("item")
                                                        .forGetter(FluidItem::item),

                                        FluidStack.CODEC
                                                        .optionalFieldOf("fluid")
                                                        .forGetter(FluidItem::fluid)

                        ).apply(inst, FluidItem::new));

                        public static final StreamCodec<RegistryFriendlyByteBuf, FluidItem> STREAM_CODEC = StreamCodec
                                        .composite(
                                                        ByteBufCodecs.optional(ItemStack.STREAM_CODEC), FluidItem::item,
                                                        ByteBufCodecs.optional(FluidStack.STREAM_CODEC),
                                                        FluidItem::fluid,
                                                        FluidItem::new);

                }
        }
}
