package com.devdyna.synergy.api.node;

import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.FluidUtil;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.fluids.capability.IFluidHandler.FluidAction;

public interface FluidNodeType {
    abstract FluidStack getFluidStack();

    default void moveFluids(IFluidHandler input, IFluidHandler output, int rate) {
        if (input == null || output == null)
            return;

        FluidUtil.tryFluidTransfer(output, input, rate, true);
    }

    default FluidStack getFirstFluid(IFluidHandler handler) {
        if (handler != null)
            for (int i = 0; i < handler.getTanks(); i++) {
                if (!handler.getFluidInTank(i).isEmpty())
                    return handler.getFluidInTank(i).copy();
            }
        return FluidStack.EMPTY;
    }

    default FluidStack insertFluidStacked(IFluidHandler handler, FluidStack stack, Boolean simOn) {

        if (handler != null)
            for (int i = 0; i < handler.getTanks(); i++) {

                var tank = handler.getFluidInTank(i);
                var diff = tank.getAmount();
                var max = handler.getTankCapacity(i);

                if (FluidStack.isSameFluidSameComponents(stack, tank)
                        && max > diff) {

                    tank.setAmount(Math.min(max, diff + stack.getAmount()));

                    stack.setAmount(Math.min(max - diff, stack.getAmount()));

                    return stack;
                }

                if (tank.isEmpty()) {
                    stack.setAmount(handler.fill(stack, simOn ? FluidAction.SIMULATE : FluidAction.EXECUTE));
                    return FluidStack.EMPTY;
                }

            }

        return stack;
    }
}
