package com.devdyna.synergy.api.codec;

import java.util.Optional;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

@SuppressWarnings("null")
public class BetterThanBlockStates {
    public static final StreamCodec<FriendlyByteBuf, BlockState> STREAM_CODEC = new StreamCodec<>() {
        @Override
        public BlockState decode(FriendlyByteBuf buf) {
            return Block.stateById(buf.readInt());
        }

        @Override
        public void encode(FriendlyByteBuf buf, BlockState state) {
            buf.writeInt(Block.getId(state));
        }
    };

    public static final StreamCodec<FriendlyByteBuf,Optional<BlockState>> OPTIONAL_STREAM_CODEC = ByteBufCodecs.optional(STREAM_CODEC);
}
