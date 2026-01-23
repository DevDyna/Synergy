package com.devdyna.synergy.compat.jei.categories.machines;

import static com.devdyna.synergy.Main.ID;

import com.devdyna.synergy.api.MachineType;
import com.devdyna.synergy.api.utils.Size;
import com.devdyna.synergy.api.utils.x;
import com.devdyna.synergy.compat.jei.api.JEIFluidTankHelper;
import com.devdyna.synergy.compat.jei.categories.core.BaseMachineRecipeCategory;
import com.devdyna.synergy.init.builder.industrial_machines.caster.recipe.CasterRecipeType;
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
public class CasterCategory extends BaseMachineRecipeCategory<CasterRecipeType> {

        public CasterCategory(IGuiHelper h) {
                super(h);
                this.arrow = helper
                                .drawableBuilder(x.rl("minecraft",
                                                "textures/gui/sprites/container/furnace/burn_progress.png"),
                                                0, 0, 24, 16)
                                .setTextureSize(24, 16).buildAnimated(60,
                                                IDrawableAnimated.StartDirection.LEFT, false);
        }

        public static final RecipeType<RecipeHolder<CasterRecipeType>> TYPE = RecipeType
                        .createFromVanilla(zMachines.CASTING_FACTORY.recipe().getType());

        @Override
        public RecipeType<RecipeHolder<CasterRecipeType>> getRecipeType() {
                return TYPE;
        }

        @Override
        public void setRecipe(IRecipeLayoutBuilder builder, CasterRecipeType recipe, IFocusGroup focuses) {

                if (!recipe.getInputItem().isEmpty()) {
                        var item = builder.addInputSlot(2 + 21, 5).addIngredients(recipe.getInputItem());

                        if (!recipe.consumeCatalyst())
                                item.addRichTooltipCallback(
                                                (v, t) -> t.add(Component.translatable(ID + ".jei.tip.dont_consume")));
                }

                builder.addOutputSlot(81 + 21, 5).addItemStack(recipe.getOutputItem());

                JEIFluidTankHelper.of()
                                .fluid(recipe.getFluidInput())
                                .offset(3, 21)
                                .build((x, y) -> builder.addInputSlot(x, y));

        }

        @Override
        public void draw(CasterRecipeType recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics,
                        double mouseX,
                        double mouseY) {
                super.draw(recipe, recipeSlotsView, guiGraphics, mouseX, mouseY);

                arrow.draw(guiGraphics, 29 + 21, 6);

                guiGraphics.drawString(font,
                                Component.literal(
                                                recipe.getTime() + " ticks"),
                                24 + 21, -2,
                                defaultToolTipColor.getRGB(), false);

        }

        @Override
        public Size setXY() {
                return Size.of(124, 28);
        }

        @Override
        public MachineType<? extends Block, ? extends BlockEntity, ? extends AbstractContainerMenu, ? extends Recipe<?>> getMachine() {
                return zMachines.CASTING_FACTORY;
        }

}
