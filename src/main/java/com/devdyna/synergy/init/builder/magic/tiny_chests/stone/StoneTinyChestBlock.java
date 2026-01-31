package com.devdyna.synergy.init.builder.magic.tiny_chests.stone;

import java.util.List;
import java.util.function.Function;

import javax.annotation.Nullable;

import com.devdyna.synergy.Main;
import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.init.builder.magic.tiny_chests.wooden.WoodenTinyChestBlock;

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
public class StoneTinyChestBlock extends WoodenTinyChestBlock {

    public StoneTinyChestBlock(Properties p) {
        super(
            Properties.of()
            .strength(1.5F, 6.0F)
            .sound(SoundType.STONE)
            .mapColor(MapColor.STONE)
            .requiresCorrectToolForDrops()
            );
            
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

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents,
            TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable(Main.ID + "." + zStatic.Chests.STONE));
    }

}
