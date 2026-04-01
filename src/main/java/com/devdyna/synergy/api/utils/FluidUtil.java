package com.devdyna.synergy.api.utils;

import java.util.ArrayList;
import java.util.List;

import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;

public class FluidUtil {

    public static boolean hasFluid(IFluidHandler tank) {

        if (tank == null)
            return false;

        if (tank.getTanks() < 0)
            return false;

        for (int i = 0; i < tank.getTanks(); i++)
            if (!tank.getFluidInTank(i).isEmpty())
                return true;

        return false;
    }

    /**
     * Can return List.EMPTY when has no elements!
     */
    public static List<FluidStack> getFluids(IFluidHandler tank) {

        if (tank == null)
            return List.of();

        if (tank.getTanks() < 0)
            return List.of();

        List<FluidStack> list = new ArrayList<>();

        for (int i = 0; i < tank.getTanks(); i++)
            if (!tank.getFluidInTank(i).isEmpty())
                list.add(tank.getFluidInTank(i));

        return list;
    }

}
