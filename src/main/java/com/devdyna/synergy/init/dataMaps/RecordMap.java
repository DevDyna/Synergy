package com.devdyna.synergy.init.dataMaps;

import java.util.Arrays;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.world.level.block.state.BlockState;

public class RecordMap {
    public record ProviderRecipes(BlockState belowBlock, BlockState... blocksToCheck) {

        public static final Codec<ProviderRecipes> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                Codec.list(BlockState.CODEC).fieldOf("blocksToCheck").forGetter(r -> Arrays.asList(r.blocksToCheck)),
                BlockState.CODEC.fieldOf("belowBlock").forGetter(ProviderRecipes::belowBlock))
                .apply(instance, (blocksList, belowBlock) -> new ProviderRecipes(belowBlock,
                        blocksList.toArray(new BlockState[0]))));
    }

}
