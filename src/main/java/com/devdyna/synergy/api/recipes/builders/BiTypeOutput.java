package com.devdyna.synergy.api.recipes.builders;

import com.devdyna.synergy.api.zFluid;
import com.devdyna.synergy.api.utils.x;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.fluids.FluidStack;

public interface BiTypeOutput<BUILDER extends BaseRecipeBuilder> extends BuilderAttach<BUILDER> {
    abstract BUILDER output(ItemStack item);

    abstract BUILDER output(FluidStack fluid);

    default BUILDER output(Fluid fluid, int amount) {
        return output(x.fluid(fluid, amount));
    }

    default BUILDER output(Fluid fluid) {
        return output(fluid, 1000);
    }

    default BUILDER output(zFluid fluid, int amount) {
        return output(x.fluid(fluid), amount);
    }

    default BUILDER output(zFluid fluid) {
        return output(x.fluid(fluid), 1000);
    }

}
