package com.devdyna.synergy.compat.jei.api;

import java.util.List;
import java.util.function.BiFunction;

import com.devdyna.synergy.api.utils.x;

import mezz.jei.api.gui.builder.IRecipeSlotBuilder;
import mezz.jei.api.neoforge.NeoForgeTypes;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.crafting.SizedFluidIngredient;

public class JEIFluidTankHelper {

    private int x0;
    private int y0;
    private List<FluidStack> fluids;

    public JEIFluidTankHelper() {
    }

    public JEIFluidTankHelper offset(int x, int y) {
        this.x0 = x;
        this.y0 = y;
        return this;
    }

    public JEIFluidTankHelper fluid(SizedFluidIngredient f) {
        this.fluids = x.getFluids(f);
        return this;
    }
    public JEIFluidTankHelper fluid(FluidStack f) {
         this.fluids = List.of(f);
        return this;
    }

    public static JEIFluidTankHelper of() {
        return new JEIFluidTankHelper();
    }

    public void build(BiFunction<Integer, Integer, IRecipeSlotBuilder> builder) {
        var height = Math.min(16, Math.max((int) ((fluids.getFirst().getAmount() + 256) * 0.016), 1));
        builder.apply(x0, y0 - height)
                .addIngredients(NeoForgeTypes.FLUID_STACK, fluids)
                .setFluidRenderer(fluids.getFirst().getAmount(), false, 16, height);
    }

}
