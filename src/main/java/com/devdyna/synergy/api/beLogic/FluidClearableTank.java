package com.devdyna.synergy.api.beLogic;

import com.devdyna.synergy.api.utils.LogUtil;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.fluids.capability.IFluidHandler.FluidAction;

public interface FluidClearableTank {

    default InteractionResult useItemToClear(BlockState blockState, Level level,
            BlockPos blockPos, Player player, BlockHitResult blockHitResult) {

        if (!player.isCrouching())
            return InteractionResult.FAIL;

        if (level.isClientSide)
            return InteractionResult.SUCCESS;

        IFluidHandler cap = level.getCapability(Capabilities.FluidHandler.BLOCK, blockPos,
                blockHitResult.getDirection());

        if (cap == null)
            return InteractionResult.FAIL;

        cap.drain(cap.getFluidInTank(0), FluidAction.EXECUTE);
                LogUtil.info("d");
        return InteractionResult.SUCCESS;

    }
}
