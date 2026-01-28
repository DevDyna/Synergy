package com.devdyna.synergy.compat.jei.categories.machines;

import com.devdyna.synergy.api.MachineType;
import com.devdyna.synergy.api.utils.Size;
import com.devdyna.synergy.api.utils.x;
import com.devdyna.synergy.compat.jei.api.JEIFluidTankHelper;
import com.devdyna.synergy.compat.jei.categories.core.BaseMachineRecipeCategory;
import com.devdyna.synergy.init.builder.industrial_machines.extractor.recipe.ExtractorRecipeType;
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
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;

@SuppressWarnings("null")
public class ExtractorCategory extends BaseMachineRecipeCategory<ExtractorRecipeType> {

        public ExtractorCategory(IGuiHelper h) {
                super(h);
                this.arrow = helper
                                .drawableBuilder(x.rl("minecraft",
                                                "textures/gui/sprites/container/furnace/burn_progress.png"),
                                                0, 0, 24, 16)
                                .setTextureSize(24, 16).buildAnimated(60,
                                                IDrawableAnimated.StartDirection.LEFT, false);
        }

        public static final RecipeType<RecipeHolder<ExtractorRecipeType>> TYPE = RecipeType
                        .createFromVanilla(zMachines.EXTRACTOR.recipe().getType());

        @Override
        public RecipeType<RecipeHolder<ExtractorRecipeType>> getRecipeType() {
                return TYPE;
        }

        @Override
        public void setRecipe(IRecipeLayoutBuilder builder, ExtractorRecipeType recipe, IFocusGroup focuses) {

                builder.addInputSlot(2, 5).addItemStacks(x.getItems(recipe.getInputItem()));
                if (recipe.hasSecondaryOutput())
                        builder.addOutputSlot(81, 5).addItemStack(recipe.getSecondaryItem());

                if (!recipe.getFluidOutput().isEmpty())
                        JEIFluidTankHelper.of()
                                        .fluid(recipe.getFluidOutput())
                                        .offset(102, 21)
                                        .build((x, y) -> builder.addOutputSlot(x, y));

        }

        @Override
        public void draw(ExtractorRecipeType recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics,
                        double mouseX,
                        double mouseY) {
                super.draw(recipe, recipeSlotsView, guiGraphics, mouseX, mouseY);

                arrow.draw(guiGraphics, 29, 6);

                guiGraphics.drawString(font,
                                Component.literal(
                                                recipe.getTime() + " ticks"),
                                24, -2,
                                defaultToolTipColor.getRGB(), false);

                if (recipe.hasSecondaryOutput() && recipe.getSecondaryItem() != null)
                        if (recipe.getSecondaryItemChance() > 0f && recipe.getSecondaryItemChance() < 1f
                                        && !recipe.getSecondaryItem().isEmpty())
                                guiGraphics.drawString(font,
                                                Component.literal(
                                                                ((int) (recipe.getSecondaryItemChance() * 100)) + "%"),
                                                60, 10,
                                                defaultToolTipColor.getRGB(), false);

        }

        @Override
        public Size setXY() {
                return Size.of(124, 28);
        }

        @Override
        public MachineType<? extends Block, ? extends BlockEntity, ? extends AbstractContainerMenu, ? extends Recipe<?>> getMachine() {
                return zMachines.EXTRACTOR;
        }

}
