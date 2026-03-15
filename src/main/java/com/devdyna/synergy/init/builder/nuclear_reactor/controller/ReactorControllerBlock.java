package com.devdyna.synergy.init.builder.nuclear_reactor.controller;

import java.util.List;

import javax.annotation.Nullable;

import com.devdyna.synergy.Main;
import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.api.basebe.block.AreaBlock;
import com.devdyna.synergy.api.utils.PlayerUtil;
import com.devdyna.synergy.api.utils.StringUtil;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item.TooltipContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;

@SuppressWarnings("null")
public class ReactorControllerBlock extends AreaBlock {

    public final static EnumProperty<ControllerProperties> STATUS = EnumProperty.create("status",
            ControllerProperties.class);

    public ReactorControllerBlock() {
        super(Properties.of().forceSolidOn().destroyTime(1.0f).sound(SoundType.METAL).mapColor(MapColor.METAL));
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext c) {
        return this.defaultBlockState()
                .setValue(BlockStateProperties.ENABLED, false)
                .setValue(STATUS, ControllerProperties.WAITING)
                .setValue(BlockStateProperties.HORIZONTAL_FACING, c.getHorizontalDirection());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> b) {
        b.add(BlockStateProperties.HORIZONTAL_FACING, STATUS, BlockStateProperties.ENABLED);
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos,
            Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (!player.isShiftKeyDown() && !level.isClientSide && hand.equals(InteractionHand.MAIN_HAND)
                && stack.isEmpty()
                && level.getBlockEntity(pos) instanceof ReactorControllerBE be) {

            PlayerUtil.messageActionBar(
                    (be.heat > 0 ? "§c" : "§a") +
                            StringUtil.getFormat().format(be.heat)
                            + "°/t§f | §6" +
                            StringUtil.getFormat().format(be.fe) + "fe/t",
                    player);

            return ItemInteractionResult.SUCCESS;
        }
        return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
    }

    @Override
    protected void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean movedByPiston) {

        var be = (ReactorControllerBE) level.getBlockEntity(pos);

        if (!be.getArea().isEmpty()) {
            be.updateCells(false);
        }
        super.onRemove(state, level, pos, newState, movedByPiston);

    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos p, BlockState s) {
        return new ReactorControllerBE(p, s);
    }

    @Override
    public void appendHoverText(ItemStack i, TooltipContext c, List<Component> t,
            TooltipFlag f) {
        t.add(Component.translatable(Main.ID + "." + zStatic.ReactorStuff.controller));
    }

}
