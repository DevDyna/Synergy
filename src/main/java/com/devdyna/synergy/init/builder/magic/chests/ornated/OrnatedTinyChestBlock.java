package com.devdyna.synergy.init.builder.magic.chests.ornated;

import java.util.List;
import java.util.function.Function;

import javax.annotation.Nullable;

import com.devdyna.synergy.Main;
import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.init.builder.magic.chests.wooden.WoodenTinyChestBlock;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Item.TooltipContext;
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
