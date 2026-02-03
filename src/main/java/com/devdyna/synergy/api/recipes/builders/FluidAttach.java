package com.devdyna.synergy.api.recipes.builders;

import com.devdyna.synergy.api.recipes.builders.api.BaseRecipeBuilder;
import com.devdyna.synergy.api.recipes.builders.api.BuilderAttach;
import com.devdyna.synergy.api.registers.FluidRegister;
import com.devdyna.synergy.api.utils.x;

import net.minecraft.tags.TagKey;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.crafting.FluidIngredient;
import net.neoforged.neoforge.fluids.crafting.SizedFluidIngredient;

public class FluidAttach {
    public class Input {
        public static interface SizedFluid<BUILDER extends BaseRecipeBuilder> extends Any.SimpleFluidAttach<BUILDER> {

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


        }
        public static interface NoFluidCount<BUILDER extends BaseRecipeBuilder> extends Any.SimpleFluidAttach<BUILDER> {

            abstract BUILDER fluid(FluidIngredient fluid);

            default BUILDER fluid(TagKey<Fluid> fluid) {
                return fluid(x.ingredientFluid(fluid));
            }

            default BUILDER fluid(FluidStack fluid) {
                return fluid(x.ingredientFluid(fluid));
            }

        }
    }

    public class Any {
        public static interface SimpleFluidAttach<BUILDER extends BaseRecipeBuilder> extends BuilderAttach<BUILDER> {

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
    }

}
