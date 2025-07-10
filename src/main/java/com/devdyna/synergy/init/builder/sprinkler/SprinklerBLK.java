package com.devdyna.synergy.init.builder.sprinkler;

import java.util.List;

import javax.annotation.Nullable;

import com.devdyna.synergy.Main;
import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.api.coreBE.BaseBlockBE;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item.TooltipContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;

public class SprinklerBLK extends BaseBlockBE {

    public SprinklerBLK() {
        super(Properties.of().forceSolidOn().destroyTime(1.0f).sound(SoundType.TUFF_BRICKS)
                .mapColor(MapColor.TERRACOTTA_BROWN));
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos p, BlockState s) {
        return new SprinklerBE(p, s);
    }

    @Override
    public void appendHoverText(ItemStack i, TooltipContext c, List<Component> t,
            TooltipFlag f) {
        t.add(Component.translatable(Main.ID + "." + zStatic.Blocks.sprinkler));
    }

}
