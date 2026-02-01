package com.devdyna.synergy.api.beLogic;

import net.minecraft.core.BlockPos;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.fluids.capability.IFluidHandler.FluidAction;

public interface FluidClearableTank {

    default ItemInteractionResult useItemToClear(BlockState blockState, Level level,
            BlockPos blockPos, Player player, BlockHitResult blockHitResult) {

        // if (!player.isCrouching())
        // return InteractionResult.FAIL;

        if (level.isClientSide)
        return ItemInteractionResult.SUCCESS;

        IFluidHandler cap = level.getCapability(Capabilities.FluidHandler.BLOCK, blockPos,
                blockHitResult.getDirection());

        if (cap == null)
            return ItemInteractionResult.FAIL;

            if(cap.getFluidInTank(0).isEmpty())
            return ItemInteractionResult.FAIL;

        cap.drain(cap.getFluidInTank(0), FluidAction.EXECUTE);
        return ItemInteractionResult.SUCCESS;

    }
}
