package com.devdyna.synergy.compat.jei.api;

import java.util.List;
import java.util.function.BiFunction;
import mezz.jei.api.gui.builder.IRecipeSlotBuilder;
import mezz.jei.api.neoforge.NeoForgeTypes;
import net.neoforged.neoforge.fluids.FluidStack;

public class JEIFluidTankHelper {

    private int x;
    private int y;
    private FluidStack fluid = FluidStack.EMPTY;

    public JEIFluidTankHelper() {
    }

    public JEIFluidTankHelper offset(int x, int y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public JEIFluidTankHelper fluid(FluidStack f) {
        this.fluid = f;
        return this;
    }

    public static JEIFluidTankHelper of() {
        return new JEIFluidTankHelper();
    }

    public void build(BiFunction<Integer, Integer, IRecipeSlotBuilder> builder) {
        var height = Math.min(16, Math.max((int) ((fluid.getAmount() + 256) * 0.016), 1));
        builder.apply(x, y - height)
                .addIngredients(NeoForgeTypes.FLUID_STACK, List.of(fluid))
                .setFluidRenderer(fluid.getAmount(), false, 16, height);
    }

}
