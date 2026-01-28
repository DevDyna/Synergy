package com.devdyna.synergy.api.recipes.builders;

import com.devdyna.synergy.api.zFluid;
import com.devdyna.synergy.api.utils.x;

import net.minecraft.tags.TagKey;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.crafting.SizedFluidIngredient;

public interface InputFluidAttach<BUILDER extends BaseRecipeBuilder> {

    abstract BUILDER fluid(SizedFluidIngredient fluid);

    default BUILDER fluid(TagKey<Fluid> fluid, int amount) {
        return fluid(x.fluidSized(fluid, amount));
    }

    default BUILDER fluid(TagKey<Fluid> fluid) {
        return fluid(x.fluidSized(fluid, 1000));
    }

    default BUILDER fluid(FluidStack fluid) {
        return fluid(x.fluidSized(fluid));
    }

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
