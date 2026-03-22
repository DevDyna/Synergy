package com.devdyna.synergy.api.codec.recipe;

import java.util.Optional;

import com.devdyna.synergy.api.codec.BetterThanBlockStates;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.level.block.state.BlockState;

public record NodePattern(
        BlockState core,
        BlockState left,
        BlockState right,
        BlockState below) {

    public static final Codec<NodePattern> CODEC = RecordCodecBuilder.create(inst -> inst.group(
            BlockState.CODEC.fieldOf("core").forGetter(NodePattern::core),
            BlockState.CODEC.optionalFieldOf("left").forGetter(np -> Optional.ofNullable(np.left())),
            BlockState.CODEC.optionalFieldOf("right").forGetter(np -> Optional.ofNullable(np.right())),
            BlockState.CODEC.optionalFieldOf("below").forGetter(np -> Optional.ofNullable(np.below())))
            .apply(inst, (c, l, r, b) -> new NodePattern(c, l.orElse(null), r.orElse(null), b.orElse(null))));

    public static final StreamCodec<RegistryFriendlyByteBuf, NodePattern> STREAM_CODEC = StreamCodec.composite(
            BetterThanBlockStates.STREAM_CODEC, NodePattern::core,
            BetterThanBlockStates.OPTIONAL_STREAM_CODEC, np -> Optional.ofNullable(np.left()),
            BetterThanBlockStates.OPTIONAL_STREAM_CODEC, np -> Optional.ofNullable(np.right()),
            BetterThanBlockStates.OPTIONAL_STREAM_CODEC, np -> Optional.ofNullable(np.below()),
            (c, l, r, b) -> new NodePattern(c, l.orElse(null), r.orElse(null), b.orElse(null)));

    public static final NodePattern of(
            BlockState core,
            BlockState left,
            BlockState right,
            BlockState below) {
        return new NodePattern(core, left, right, below);
    }
}