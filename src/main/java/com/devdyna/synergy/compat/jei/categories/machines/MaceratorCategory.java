package com.devdyna.synergy.compat.jei.categories.machines;

import com.devdyna.synergy.api.MachineType;
import com.devdyna.synergy.api.utils.Size;
import com.devdyna.synergy.api.utils.x;
import com.devdyna.synergy.compat.jei.categories.core.BaseMachineRecipeCategory;
import com.devdyna.synergy.init.builder.industrial_machines.macerator.recipe.MaceratorRecipeType;
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
public class MaceratorCategory extends BaseMachineRecipeCategory<MaceratorRecipeType> {

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

        builder.addInputSlot(2, 14).addIngredients(recipe.getInputItem());
        builder.addOutputSlot(74, 6).addItemStack(recipe.getOutputItem());
        if (recipe.hasSecondaryOutput()) {
            builder.addOutputSlot(74, 31).addItemStack(recipe.getSecondaryItem());
        }

    }

    @Override
    public void draw(MaceratorRecipeType recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics,
            double mouseX,
            double mouseY) {
        super.draw(recipe, recipeSlotsView, guiGraphics, mouseX, mouseY);

        arrow.draw(guiGraphics, 29, 15);

        guiGraphics.drawString(font,
                Component.literal(
                        recipe.getTime() + " ticks"),
                22, 2,
                defaultToolTipColor.getRGB(), false);

        if (recipe.hasSecondaryOutput())
            if (recipe.getSecondaryItemChance() > 0f && !recipe.getSecondaryItem().isEmpty())
                guiGraphics.drawString(font,
                        Component.literal(
                                ((int) (recipe.getSecondaryItemChance() * 100)) + "%"),
                        50, 36,
                        defaultToolTipColor.getRGB(), false);

    }

    @Override
    public Size setXY() {
        return Size.of(96, 49);
    }

    @Override
    public MachineType<? extends Block, ? extends BlockEntity, ? extends AbstractContainerMenu, ? extends Recipe<?>> getMachine() {
        return zMachines.MACERATOR;
    }

}
