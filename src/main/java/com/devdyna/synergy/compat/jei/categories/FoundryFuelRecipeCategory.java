package com.devdyna.synergy.compat.jei.categories;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.builder.ITooltipBuilder;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeType;

import static com.devdyna.synergy.Main.ID;

import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.api.utils.Pos;
import com.devdyna.synergy.api.utils.Size;
import com.devdyna.synergy.common.recipes.type.FoundryFuelEfficiencyRecipe;
import com.devdyna.synergy.compat.jei.api.JEIFluidTankHelper;
import com.devdyna.synergy.compat.jei.categories.core.BaseRecipeCategory;
import com.devdyna.synergy.init.builder.survival.fuel_tank.FuelTankBE;
import com.devdyna.synergy.init.types.zRecipeTypes;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.ItemLike;

@SuppressWarnings("null")
public class FoundryFuelRecipeCategory extends BaseRecipeCategory<FoundryFuelEfficiencyRecipe> {

    public FoundryFuelRecipeCategory(IGuiHelper helper) {
        super(helper);
    }

    public static final RecipeType<RecipeHolder<FoundryFuelEfficiencyRecipe>> TYPE = RecipeType
            .createFromVanilla(zRecipeTypes.FOUNDRY_FUELS.getType());

    @Override
    public RecipeType<RecipeHolder<FoundryFuelEfficiencyRecipe>> getRecipeType() {
        return TYPE;
    }

    @Override
    public String getTitleKey() {
        return zStatic.Blocks.foundry + "_fuels";
    }

    @Override
    public ItemLike getIconItem() {
        return Items.LAVA_BUCKET;
    }

    @Override
    public Size setXY() {
        return Size.of(96, 22);
    }

    @Override
    public String setBackGround() {
        return "textures/gui/jei/foundry_fuel.png";
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, FoundryFuelEfficiencyRecipe recipe, IFocusGroup focuses) {

        JEIFluidTankHelper.of()
                .fluid(recipe.getFluid())
                .offset(1, 19)
                .scale(1.0f, 1.0f)
                .build((x, y) -> builder.addInputSlot(x, y));
    }

    @Override
    public void draw(FoundryFuelEfficiencyRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics,
            double mouseX,
            double mouseY) {
        super.draw(recipe, recipeSlotsView, guiGraphics, mouseX, mouseY);

        PoseStack stack = guiGraphics.pose();
        stack.pushPose();
        stack.scale(0.75F, 0.75F, 8000F);
        guiGraphics.drawString(font, recipe.getSpeedModifier() + "x", 46, 4, 0xFFFFFF);
        guiGraphics.drawString(font, (int) (FuelTankBE.FLUID_BURN_RATE * recipe.getUsageModifier()) + "mb/wk", 46, 18, 0xFFFFFF);
        stack.popPose();

    }

    @Override
    public void getTooltip(ITooltipBuilder tooltip, FoundryFuelEfficiencyRecipe recipe,
            IRecipeSlotsView recipeSlotsView,
            double mouseX, double mouseY) {

        if (Pos.of(21, 0).setSize(10, 10).test(mouseX, mouseY))
            tooltip.add(Component.translatable(ID + ".jei.fuel.speed"));

        if (Pos.of(21, 11).setSize(10, 10).test(mouseX, mouseY))
            tooltip.add(Component.translatable(ID + ".jei.fuel.usage"));

    }

}