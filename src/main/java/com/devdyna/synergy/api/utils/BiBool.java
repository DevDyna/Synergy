package com.devdyna.synergy.api.utils;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public class BiBool {
    private Boolean value1;
    private Boolean value2;

    public BiBool(Boolean value1, Boolean value2) {
        this.value1 = value1;
        this.value2 = value2;
    }

    public Boolean get1() {
        return value1;
    }

    public Boolean get2() {
        return value2;
    }

    public Boolean match() {
        return value1 && value2;
    }

    public static BiBool of(Boolean value1, Boolean value2) {
        return new BiBool(value1, value2);
    }

    public static BiBool of() {
        return new BiBool(false, false);
    }

    public static final Codec<BiBool> CODEC = RecordCodecBuilder.create(inst -> inst.group(
            Codec.BOOL.fieldOf("value_a").forGetter(BiBool::get1),
            Codec.BOOL.fieldOf("value_b").forGetter(BiBool::get2))
            .apply(inst, BiBool::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, BiBool> STREAM_CODEC = StreamCodec
            .composite(
                    ByteBufCodecs.BOOL, BiBool::get1,
                    ByteBufCodecs.BOOL, BiBool::get2,
                    BiBool::new);

}
