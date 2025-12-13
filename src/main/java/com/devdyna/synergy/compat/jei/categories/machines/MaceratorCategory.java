package com.devdyna.synergy.compat.jei.categories.machines;

import com.devdyna.synergy.api.MachineType;
import com.devdyna.synergy.api.Size;
import com.devdyna.synergy.compat.jei.categories.core.BaseMachineRecipeCategory;
import com.devdyna.synergy.init.machine.macerator.recipe.MaceratorRecipeType;
import com.devdyna.synergy.init.types.zMachines;
import com.devdyna.synergy.utils.x;

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
public class MaceratorCategory extends BaseMachineRecipeCategory<MaceratorRecipeType> {

    private IDrawableAnimated arrow;

    public MaceratorCategory(IGuiHelper h) {
        super(h);
        this.arrow = helper
                .drawableBuilder(x.rl("minecraft", "textures/gui/sprites/container/furnace/burn_progress.png"),
                        0, 0, 24, 16)
                .setTextureSize(24, 16).buildAnimated(60,
                        IDrawableAnimated.StartDirection.LEFT, false);
    }

    public static final RecipeType<MaceratorRecipeType> TYPE = new RecipeType<>(
            x.rl(zMachines.MACERATOR.recipe().getId()),
            MaceratorRecipeType.class);

    @Override
    public RecipeType<MaceratorRecipeType> getRecipeType() {
        return TYPE;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, MaceratorRecipeType recipe, IFocusGroup focuses) {

        builder.addInputSlot(3, 15).addIngredients(recipe.getInputItem());
        builder.addOutputSlot(75, 7).addItemStack(recipe.getOutputItem());
        if (recipe.hasSecondaryItem()) {
            builder.addOutputSlot(75, 32).addItemStack(recipe.getSecondaryOutputItem());
        }

    }

    @Override
    public void draw(MaceratorRecipeType recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics,
            double mouseX,
            double mouseY) {
        super.draw(recipe, recipeSlotsView, guiGraphics, mouseX, mouseY);

        arrow.draw(guiGraphics, 30, 16);

        guiGraphics.drawString(font,
                Component.literal(
                        recipe.getTime() + " ticks"),
                25, 2,
                defaultToolTipColor.getRGB(), false);

        if (recipe.hasSecondaryItem())
            guiGraphics.drawString(font,
                    Component.literal(
                            ((int) (recipe.getSecondaryItemChance() * 100)) + "%"),
                    50, 36,
                    defaultToolTipColor.getRGB(), false);

    }

    @Override
    public Size setXY() {
        return Size.of(97, 50);
    }

    @Override
    public MachineType<? extends Block, ? extends BlockEntity, ? extends AbstractContainerMenu, ? extends Recipe<?>> getMachine() {
        return zMachines.MACERATOR;
    }

}
