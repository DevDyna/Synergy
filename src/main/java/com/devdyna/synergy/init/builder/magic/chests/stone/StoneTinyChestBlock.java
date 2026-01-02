package com.devdyna.synergy.init.builder.magic.chests.stone;

import java.util.function.Function;

import javax.annotation.Nullable;

import com.devdyna.synergy.init.builder.magic.chests.wooden.WoodenTinyChestBlock;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;

@SuppressWarnings("null")
public class StoneTinyChestBlock extends WoodenTinyChestBlock {

    public StoneTinyChestBlock(Properties p) {
        super(
                Properties.of()
                        .strength(1.5F, 6.0F)
                        .sound(SoundType.STONE)
                        .mapColor(MapColor.STONE)
                        .requiresCorrectToolForDrops());

    }

    public StoneTinyChestBlock() {
        this(Properties.of());
    }

    @Override
    @Nullable
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new StoneTinyChestBE(pos, state);
    }

    @Override
    protected Function<Properties, Block> getFactory() {
        return StoneTinyChestBlock::new;
    }

}
