package com.devdyna.synergy.api.machine.core;

import java.util.List;
import java.util.function.Function;

import javax.annotation.Nullable;

import com.devdyna.synergy.Main;
import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.api.coreBE.block.BlockMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item.TooltipContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
@SuppressWarnings("null")
public class MaceratorBlock extends BlockMenu {

    public MaceratorBlock(Properties p) {
        super(p.strength(1.0f).destroyTime(1.0f).sound(SoundType.METAL).mapColor(MapColor.METAL));
    }

    public MaceratorBlock() {
        this(Properties.of());
    }

    @Override
    @Nullable
    public BlockEntity newBlockEntity(BlockPos arg0, BlockState arg1) {
        return new MaceratorBE(arg0, arg1);
    }

    @Override
    protected Function<Properties, Block> getFactory() {
        return MaceratorBlock::new;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents,
            TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable(Main.ID + "." + zStatic.Blocks.tiny_wooden_chest));
    }

}
