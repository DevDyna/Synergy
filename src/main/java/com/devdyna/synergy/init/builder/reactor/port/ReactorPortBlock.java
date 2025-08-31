package com.devdyna.synergy.init.builder.reactor.port;

import java.util.List;
import java.util.function.Function;

import javax.annotation.Nullable;

import com.devdyna.synergy.Main;
import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.api.menu.BlockMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item.TooltipContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;

@SuppressWarnings("null")
public class ReactorPortBlock extends BlockMenu {

    public ReactorPortBlock() {
        super(Properties.of().strength(1.0f).destroyTime(1.0f).sound(SoundType.CHAIN).mapColor(MapColor.METAL));
    }

    public ReactorPortBlock(Properties p) {
        this();
    }

    @Override
    protected Function<Properties, Block> getFactory() {
        return ReactorPortBlock::new;
    }

    @Override
    @Nullable
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new ReactorPortBE(pos, state);
    }

    @Override
    public void appendHoverText(ItemStack i, TooltipContext c, List<Component> t,
            TooltipFlag f) {
        t.add(Component.translatable(Main.ID + "." + zStatic.ReactorStuff.port));
    }

    @Override
    protected void onClickAction(BlockState state, Level level, BlockPos pos, Player player) {
    }

}
