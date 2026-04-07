package com.devdyna.synergy.compat.jade.provider;

import static com.devdyna.synergy.Main.ID;

import com.devdyna.synergy.api.beLogic.HeatConsumer;
import com.devdyna.synergy.api.utils.Range;
import com.devdyna.synergy.api.utils.x;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import snownee.jade.api.*;
import snownee.jade.api.config.IPluginConfig;

@SuppressWarnings("null")
public enum HeatInfoConsumer
        implements IBlockComponentProvider, StreamServerDataProvider<BlockAccessor, HeatInfoConsumer.Data> {
    INSTANCE;

    @Override
    public void appendTooltip(ITooltip t, BlockAccessor accessor, IPluginConfig config) {

        Data data = decodeFromData(accessor).orElse(null);
        if (data == null)
            return;

        if (data.heat < 0) {
            t.add(Component.translatable(ID + ".jade.heat_consumer.status.missing_heat"));
            return;
        }

        t.add(
                Component.translatable(ID + ".jade.heat_consumer.temperature",
                        data.heat,
                        data.range.getMin(),
                        data.range.getMax())
        // .append(Component.literal(" ["))
        // .append(Component.translatable(
        // ID + ".jade.tip." + (data.range.getIncluded().get1() ? "include" :
        // "exclude")))
        // .append(Component.literal("|"))
        // .append(Component.translatable(
        // ID + ".jade.tip." + (data.range.getIncluded().get2() ? "include" :
        // "exclude")))
        // .append(Component.literal("]"))
        );

        if (data.range.isAbove(data.heat)) {
            t.add(Component.translatable(ID + ".jade.heat_consumer.status.warn.above"));
            return;
        }

        if (data.range.isBelow(data.heat)) {
            t.add(Component.translatable(ID + ".jade.heat_consumer.status.warn.below"));
            return;
        }

        t.add(Component.translatable(ID + ".jade.heat_consumer.status.safe"));

    }

    @Override
    public Data streamData(BlockAccessor accessor) {
        if (accessor.getBlockEntity() instanceof HeatConsumer h) {
            if (h.getProvider() == null)
                return new Data(-1, h.getHeatLimit());

            return new Data(h.getProvider().getHeat(), h.getHeatLimit());
        }
        return null;
    }

    @Override
    public StreamCodec<RegistryFriendlyByteBuf, Data> streamCodec() {
        return Data.STREAM_CODEC;
    }

    public record Data(int heat, Range range) {
        public static final StreamCodec<RegistryFriendlyByteBuf, Data> STREAM_CODEC = StreamCodec.composite(
                ByteBufCodecs.INT, Data::heat,
                Range.STREAM_CODEC, Data::range,
                Data::new);
    }

    @Override
    public ResourceLocation getUid() {
        return x.rl("heat_consumer");
    }

}