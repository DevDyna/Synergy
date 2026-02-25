package com.devdyna.synergy.api.codec.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;

public record ChanceOutputItem(ItemStack item, float chance) {

        public static final Codec<ChanceOutputItem> CODEC = RecordCodecBuilder.create(inst -> inst.group(
                        ItemStack.CODEC.fieldOf("item").forGetter(ChanceOutputItem::item),
                        Codec.floatRange(0, 1).fieldOf("chance").forGetter(ChanceOutputItem::chance))
                        .apply(inst, ChanceOutputItem::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, ChanceOutputItem> STREAM_CODEC = StreamCodec
                        .composite(
                                        ItemStack.STREAM_CODEC, ChanceOutputItem::item,
                                        ByteBufCodecs.FLOAT, ChanceOutputItem::chance,
                                        ChanceOutputItem::new);

        public static final ChanceOutputItem of(ItemStack stack, float chance) {
                return new ChanceOutputItem(stack, chance);
        }
}