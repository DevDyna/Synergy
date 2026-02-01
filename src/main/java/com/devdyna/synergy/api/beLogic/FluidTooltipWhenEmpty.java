package com.devdyna.synergy.api.beLogic;

import static com.devdyna.synergy.Main.ID;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.neoforge.fluids.capability.templates.FluidTank;

public interface FluidTooltipWhenEmpty {

    abstract FluidTank getFluidTank(BlockEntity be, BlockState state, Level level, BlockPos pos,
            Player player, InteractionHand hand, BlockHitResult hitResult);

    default boolean showWhen(BlockEntity be) {
        return true;
    }

    default ItemInteractionResult sendFluidTooltip(ItemStack stack, BlockState state, Level level, BlockPos pos,
            Player player, InteractionHand hand, BlockHitResult hitResult) {

        if (level.isClientSide)
            return ItemInteractionResult.SUCCESS;

        var tank = getFluidTank(level.getBlockEntity(pos), state, level, pos, player, hand, hitResult);
        if (showWhen(level.getBlockEntity(pos)) && tank != null) {
            var fluid = tank.getFluid();
            if (fluid.isEmpty())
                player.displayClientMessage(Component.translatable(ID + ".tank_interact.empty"), true);
            else
                player.displayClientMessage(
                        Component.literal(
                                fluid.getFluidType().getDescription().getString() + " : " + fluid.getAmount() + "mb"),
                        true);

            return ItemInteractionResult.SUCCESS;
        }
        return onTooltipFail(stack, state, level, pos, player, hand, hitResult);
    }

    default ItemInteractionResult onTooltipFail(ItemStack stack, BlockState state, Level level, BlockPos pos,
            Player player, InteractionHand hand, BlockHitResult hitResult) {
        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }
}
