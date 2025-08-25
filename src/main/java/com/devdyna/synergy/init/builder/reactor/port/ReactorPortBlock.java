package com.devdyna.synergy.init.builder.reactor.port;

import java.util.List;

import javax.annotation.Nullable;

import com.devdyna.synergy.Main;
import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.api.coreBE.BaseBlockBE;
import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item.TooltipContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;

@SuppressWarnings("null")
public class ReactorPortBlock extends BaseBlockBE {

    public static final MapCodec<ReactorPortBlock> CODEC = simpleCodec(ReactorPortBlock::new);

    public ReactorPortBlock() {
        super(Properties.of().strength(1.0f).destroyTime(1.0f).sound(SoundType.CHAIN).mapColor(MapColor.METAL));
    }

    public ReactorPortBlock(Properties p) {
        this();
    }

    @Override
    protected MapCodec<? extends ReactorPortBlock> codec() {
        return CODEC;
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player,
            BlockHitResult hitResult) {

        if (level.getBlockEntity(pos) instanceof ReactorPortBE be) {
            player.openMenu(new SimpleMenuProvider(be, be.getContainerName()), pos);
            return InteractionResult.SUCCESS;
        }
        return super.useWithoutItem(state, level, pos, player, hitResult);
    }

    @Override
    @Nullable
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new ReactorPortBE(pos, state);
    }

    @Override
    protected void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean movedByPiston) {

        if (state.getBlock() != newState.getBlock())
            if (level.getBlockEntity(pos) instanceof ReactorPortBE be) {

                be.drops();

                level.updateNeighbourForOutputSignal(pos, this);
            }

        super.onRemove(state, level, pos, newState, movedByPiston);
    }

    @Override
    public void appendHoverText(ItemStack i, TooltipContext c, List<Component> t,
            TooltipFlag f) {
        t.add(Component.translatable(Main.ID + "." + zStatic.ReactorStuff.port));
    }

}
