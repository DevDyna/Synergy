package com.devdyna.synergy.init.builder.magic.chests.ornated;

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
public class OrnatedTinyChestBlock extends WoodenTinyChestBlock {

    public OrnatedTinyChestBlock(Properties p) {
        super(
            Properties.of()
            .strength(2.5F, 9.0F)
            .sound(SoundType.METAL)
            .mapColor(MapColor.METAL)
            .requiresCorrectToolForDrops()
            );
            
    }

    public OrnatedTinyChestBlock() {
        this(Properties.of());
    }

    @Override
    @Nullable
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new OrnatedTinyChestBE(pos, state);
    }

    @Override
    protected Function<Properties, Block> getFactory() {
        return OrnatedTinyChestBlock::new;
    }


}
