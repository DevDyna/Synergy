package com.devdyna.synergy.api.utils;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public class Range {

    private int min;
    private int max;
    private BiBool included;

    /**
     * @param min
     * @param max
     * @param included <code>[minIncluded,maxIncluded]<code/>
     */
    public Range(int min, int max, BiBool included) {
        this.min = min;
        this.max = max;
        this.included = included;
    }

    /**
     * min & max excluded
     */
    public Range(int min, int max) {
        this(min, max, BiBool.of());
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public BiBool getIncluded() {
        return included;
    }

    public boolean isBelow(int value) {
        return included.get1() ? value < min : value <= min;
    }

    public boolean isAbove(int value) {
        return included.get2() ? value > max : value >= max;
    }

    public boolean test(int value) {
        return !isBelow(value) && !isAbove(value);
    }

    public static Range of(int min, int max, BiBool included) {
        return new Range(min, max, included);
    }

    /**
     * min & max excluded
     */
    public static Range of(int min, int max) {
        return new Range(min, max);
    }

    public static final Codec<Range> CODEC = RecordCodecBuilder.create(inst -> inst.group(
            Codec.INT.fieldOf("min").forGetter(Range::getMin),
            Codec.INT.fieldOf("max").forGetter(Range::getMax),
            BiBool.CODEC.fieldOf("inclusion").forGetter(Range::getIncluded))
            .apply(inst, Range::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, Range> STREAM_CODEC = StreamCodec
            .composite(
                    ByteBufCodecs.INT, Range::getMin,
                    ByteBufCodecs.INT, Range::getMax,
                    BiBool.STREAM_CODEC, Range::getIncluded,
                    Range::new);

}
