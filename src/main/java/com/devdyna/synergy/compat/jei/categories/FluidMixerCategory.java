package com.devdyna.synergy.compat.jei.categories;

import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.api.utils.Size;
import com.devdyna.synergy.compat.jei.api.JEIFluidTankHelper;
import com.devdyna.synergy.compat.jei.categories.core.BaseRecipeCategory;
import com.devdyna.synergy.init.builder.survival.fluid_mixer.recipe.FluidMixingRecipe;
import com.devdyna.synergy.init.types.zBlocks;
import com.devdyna.synergy.init.types.zRecipeTypes;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeType;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.ItemLike;

@SuppressWarnings("null")
public class FluidMixerCategory extends BaseRecipeCategory<FluidMixingRecipe> {

    public FluidMixerCategory(IGuiHelper helper) {
        super(helper);
    }

    public static final RecipeType<RecipeHolder<FluidMixingRecipe>> TYPE = RecipeType
            .createFromVanilla(zRecipeTypes.FLUID_MIXING.getType());

    @Override
    public RecipeType<RecipeHolder<FluidMixingRecipe>> getRecipeType() {
        return TYPE;
    }

    @Override
    public String getTitleKey() {
        return zStatic.Blocks.fluid_mixer;
    }

    @Override
    public ItemLike getIconItem() {
        return zBlocks.FLUID_MIXER.get();
    }

    @Override
    public Size setXY() {
        return Size.of(104, 43);
    }

    @Override
    public String setBackGround() {
        return "textures/gui/jei/fluid_mixing.png";
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, FluidMixingRecipe recipe, IFocusGroup focuses) {

        JEIFluidTankHelper.of()
                .fluid(recipe.getFirst())
                .offset(1, 35 )
                .scale(2.0f, 1.0f)
                .build((x, y) -> builder.addInputSlot(x, y));

        JEIFluidTankHelper.of()
                .fluid(recipe.getSecond())
                .offset(38, 35 )
                .scale(2.0f, 1.0f)
                .build((x, y) -> builder.addInputSlot(x, y));

        JEIFluidTankHelper.of()
                .fluid(recipe.getOutput())
                .offset(87, 35)
                .scale(2.0f, 1.0f)
                .build((x, y) -> builder.addOutputSlot(x, y));

    }

    @Override
    public boolean enableTimerRender() {
        return true;
    }

    @Override
    public int tickValue(FluidMixingRecipe recipe) {
        return recipe.getTicks();
    }

    @Override
    public Size tickPos() {
        return Size.of(26, 38);
    }

}
