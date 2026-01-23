package com.devdyna.synergy.compat.jei.categories;

import com.devdyna.synergy.api.recipes.types.BaseProviderRecipe;
import com.devdyna.synergy.api.utils.x;
import com.devdyna.synergy.common.recipes.type.node_providers.FluidProviderRecipe;
import com.devdyna.synergy.compat.jei.api.JEIFluidTankHelper;
import com.devdyna.synergy.compat.jei.categories.core.BaseProviderCategory;
import com.devdyna.synergy.init.types.zBlocks;
import com.devdyna.synergy.init.types.zRecipeTypes;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeType;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.fluids.FluidStack;

@SuppressWarnings({ "unchecked" })
public class FluidProviderCategory extends BaseProviderCategory<FluidProviderRecipe<FluidStack>, FluidStack> {
    // DONT TOUCH OR WILL BREAK AND YOU WILL MAD WITH YOURSELF
    public static final RecipeType<FluidProviderRecipe<FluidStack>> TYPE = new RecipeType<>(
            x.rl(zRecipeTypes.FLUID_PROVIDER.getId()),
            (Class<FluidProviderRecipe<FluidStack>>) (Class<?>) FluidProviderRecipe.class);

    public FluidProviderCategory(IGuiHelper helper) {
        super(helper);
    }

    @Override
    public RecipeType<FluidProviderRecipe<FluidStack>> getRecipeType() {
        return TYPE;
    }

    @Override
    protected String getProviderType() {
        return "fluid";
    }

    @Override
    public Item getIconItem() {
        return zBlocks.FLUID_PROVIDER.get().asItem();
    }

    @Override
    protected void defineOutput(IRecipeLayoutBuilder builder, BaseProviderRecipe<FluidStack> recipe,
            IFocusGroup focuses) {
        // FluidStack size start on top of slot!

        JEIFluidTankHelper.of()
                .fluid(recipe.getOutput())
                .offset(18, 18)
                .build((x, y) -> builder.addOutputSlot(x, y));

    }

}
