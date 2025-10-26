package com.devdyna.synergy.compat.jei.categories;

import java.util.List;

import org.jetbrains.annotations.Nullable;

import com.devdyna.synergy.Main;
import com.devdyna.synergy.client.gui.screenLocations;
import com.devdyna.synergy.compat.jei.categories.core.BaseRecipeCategory;
import com.devdyna.synergy.compat.jei.drawable.SimpleIcon;
import com.devdyna.synergy.init.recipeTypes.type.FluidProviderRecipe;
import com.devdyna.synergy.init.types.zBlocks;
import com.devdyna.synergy.init.types.zRecipeTypes;
import com.devdyna.synergy.utils.x;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.neoforge.NeoForgeTypes;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.LiquidBlock;

@SuppressWarnings("null")
public class FluidProviderCategory extends BaseRecipeCategory<FluidProviderRecipe> {

    public static final RecipeType<FluidProviderRecipe> TYPE = new RecipeType<>(
            x.rl(zRecipeTypes.FLUID_PROVIDER.getId()),
            FluidProviderRecipe.class);

    public FluidProviderCategory(IGuiHelper helper) {
        super(helper);
    }

    @Override
    public RecipeType<FluidProviderRecipe> getRecipeType() {
        return TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable(Main.ID + ".jei.provider.fluid");
    }

    @Override
    public @Nullable IDrawable getIcon() {
        return SimpleIcon.of(helper, x.item(zBlocks.FLUID_PROVIDER).getItem());
    }

    @Override
    public @Nullable IDrawable getBackground() {
        return helper.createDrawable(screenLocations.ITEM_PROVIDER, 0, 0, 80, 100);
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, FluidProviderRecipe recipe, IFocusGroup focuses) {

        // FluidStack size start on top of slot!
        builder.addOutputSlot(33, 26 - Math.max((int) (recipe.getOutput().getAmount() * 0.016), 1))
                .addIngredients(NeoForgeTypes.FLUID_STACK, List.of(recipe.getOutput()))
                .setFluidRenderer(recipe.getOutput().getAmount(), false, 16,
                        Math.max((int) (recipe.getOutput().getAmount() * 0.016), 1));

        builder.addSlot(RecipeIngredientRole.RENDER_ONLY, 33, 45)
                .addIngredients(x.ingredient(zBlocks.FLUID_PROVIDER.get()));

        try {
            var core = recipe.getCore();
            if (core.getBlock() instanceof LiquidBlock fluid)
                builder.addInputSlot(33, 62).addFluidStack(fluid.fluid);
            else
                builder.addInputSlot(33, 62).addItemStack(x.item(core));
        } catch (Exception e) {
        }

        try {
            var below = recipe.getBelow();
            if (!below.isAir()) {
                if (below.getBlock() instanceof LiquidBlock fluid)
                    builder.addInputSlot(33, 79).addFluidStack(fluid.fluid);
                else
                    builder.addInputSlot(33, 79).addItemStack(x.item(below));
            }
        } catch (Exception e) {
        }

        try {
            var right = recipe.getRight();
            if (!right.isAir()) {
                if (right.getBlock() instanceof LiquidBlock fluid)
                    builder.addInputSlot(50, 62).addFluidStack(fluid.fluid);
                else
                    builder.addInputSlot(50, 62).addItemStack(x.item(right));
            }
        } catch (Exception e) {
        }

        try {
            var left = recipe.getLeft();
            if (!left.isAir()) {
                if (left.getBlock() instanceof LiquidBlock fluid)
                    builder.addInputSlot(16, 62).addFluidStack(fluid.fluid);
                else
                    builder.addInputSlot(16, 62).addItemStack(x.item(left));
            }
        } catch (Exception e) {
        }

    }

}
