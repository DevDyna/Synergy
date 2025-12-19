package com.devdyna.synergy.compat.jei.categories.machines;

import com.devdyna.synergy.api.MachineType;
import com.devdyna.synergy.api.utils.Size;
import com.devdyna.synergy.api.utils.x;
import com.devdyna.synergy.compat.jei.categories.core.BaseMachineRecipeCategory;
import com.devdyna.synergy.init.machine.alloy_smelter.recipe.AlloySmelterRecipeType;
import com.devdyna.synergy.init.types.zMachines;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeType;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;

@SuppressWarnings("null")
public class AlloySmelterCategory extends BaseMachineRecipeCategory<AlloySmelterRecipeType> {

    public AlloySmelterCategory(IGuiHelper h) {
        super(h);
        this.arrow = helper
                .drawableBuilder(x.rl("minecraft", "textures/gui/sprites/container/furnace/burn_progress.png"),
                        0, 0, 24, 16)
                .setTextureSize(24, 16).buildAnimated(60,
                        IDrawableAnimated.StartDirection.LEFT, false);
    }

    public static final RecipeType<AlloySmelterRecipeType> TYPE = new RecipeType<>(
            x.rl(zMachines.ALLOY_SMELTER.recipe().getId()),
            AlloySmelterRecipeType.class);

    @Override
    public RecipeType<AlloySmelterRecipeType> getRecipeType() {
        return TYPE;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, AlloySmelterRecipeType recipe, IFocusGroup focuses) {

        builder.addInputSlot(2, 5).addIngredients(recipe.getInputItem());
        builder.addInputSlot(22, 5).addIngredients(recipe.getCatalystItem());
        builder.addOutputSlot(87, 6).addItemStack(recipe.getOutputItem());

    }

    @Override
    public void draw(AlloySmelterRecipeType recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics,
            double mouseX,
            double mouseY) {
        super.draw(recipe, recipeSlotsView, guiGraphics, mouseX, mouseY);

        arrow.draw(guiGraphics, 43, 6);

        guiGraphics.drawString(font,
                Component.literal(
                        recipe.getTime() + " ticks"),
                40, -2,
                defaultToolTipColor.getRGB(), false);

    }

    @Override
    public Size setXY() {
        return Size.of(109, 28);
    }

    @Override
    public MachineType<? extends Block, ? extends BlockEntity, ? extends AbstractContainerMenu, ? extends Recipe<?>> getMachine() {
        return zMachines.ALLOY_SMELTER;
    }

}
