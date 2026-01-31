package com.devdyna.synergy.api.recipes.builders;

import com.devdyna.synergy.api.registers.FluidRegister;
import com.devdyna.synergy.api.utils.x;

import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.fluids.FluidStack;

public interface SimpleFluidAttach<BUILDER extends BaseRecipeBuilder> {

    abstract BUILDER fluid(FluidStack fluid);

    default BUILDER fluid(Fluid fluid, int amount) {
        return fluid(x.fluid(fluid, amount));
    }

    default BUILDER fluid(Fluid fluid) {
        return fluid(fluid, 1000);
    }

    default BUILDER fluid(FluidRegister fluid, int amount) {
        return fluid(x.fluid(fluid), amount);
    }

    default BUILDER fluid(FluidRegister fluid) {
        return fluid(x.fluid(fluid), 1000);
    }

}
