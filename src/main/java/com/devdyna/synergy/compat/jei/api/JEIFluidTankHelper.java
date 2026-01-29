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
    private float h = 1f;
    private float w = 1f;


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

    public JEIFluidTankHelper scale(float height,float width) {
         this.w = width;
         this.h = height;  
        return this;
    }

    public static JEIFluidTankHelper of() {
        return new JEIFluidTankHelper();
    }

    public void build(BiFunction<Integer, Integer, IRecipeSlotBuilder> builder) {
        var height =(int) (Math.min(16, Math.max((int) ((fluids.getFirst().getAmount() + 256) * 0.016), 1))*h);
        builder.apply((int) (x0* w), y0 - height)
                .addIngredients(NeoForgeTypes.FLUID_STACK, fluids)
                .setFluidRenderer(fluids.getFirst().getAmount(), false, (int) (w*16), height);
    }

}
