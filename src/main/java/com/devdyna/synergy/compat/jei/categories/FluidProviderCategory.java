package com.devdyna.synergy.compat.jei.categories;

import java.util.List;

import com.devdyna.synergy.api.node.BaseProviderRecipe;
import com.devdyna.synergy.compat.jei.categories.core.BaseProviderCategory;
import com.devdyna.synergy.init.recipeTypes.type.FluidProviderRecipe;
import com.devdyna.synergy.init.types.zBlocks;
import com.devdyna.synergy.init.types.zRecipeTypes;
import com.devdyna.synergy.utils.x;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.neoforge.NeoForgeTypes;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeType;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.fluids.FluidStack;

@SuppressWarnings({ "unchecked", "null" })
public class FluidProviderCategory extends BaseProviderCategory<FluidProviderRecipe<FluidStack>, FluidStack> {

    public static final RecipeType<BaseProviderRecipe<FluidStack>> TYPE = new RecipeType<>(
            x.rl(zRecipeTypes.FLUID_PROVIDER.getId()),
            (Class<FluidProviderRecipe<FluidStack>>) (Class<?>) FluidProviderRecipe.class);

    public FluidProviderCategory(IGuiHelper helper) {
        super(helper);
    }

    @Override
    public RecipeType<BaseProviderRecipe<FluidStack>> getRecipeType() {
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
        builder.addOutputSlot(33, 26 - Math.max((int) (recipe.getOutput().getAmount() * 0.016), 1))
                .addIngredients(NeoForgeTypes.FLUID_STACK, List.of(recipe.getOutput()))
                .setFluidRenderer(recipe.getOutput().getAmount(), false, 16,
                        Math.max((int) (recipe.getOutput().getAmount() * 0.016), 1));
    }


  
}
