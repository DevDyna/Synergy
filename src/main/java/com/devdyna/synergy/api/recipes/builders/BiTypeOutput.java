package com.devdyna.synergy.api.recipes.builders;

import com.devdyna.synergy.api.zFluid;
import com.devdyna.synergy.api.utils.x;

import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.fluids.FluidStack;

public interface BiTypeOutput<BUILDER extends BaseRecipeBuilder> extends SecondaryOutputItem<BUILDER> {

    abstract BUILDER fluid(FluidStack fluid);

    default BUILDER fluid(Fluid fluid, int amount) {
        return fluid(x.fluid(fluid, amount));
    }

    default BUILDER fluid(Fluid fluid) {
        return fluid(fluid, 1000);
    }

    default BUILDER fluid(zFluid fluid, int amount) {
        return fluid(x.fluid(fluid), amount);
    }

    default BUILDER fluid(zFluid fluid) {
        return fluid(x.fluid(fluid), 1000);
    }

}
