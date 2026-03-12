package com.devdyna.synergy.compat.jei.categories.machines;

import com.devdyna.synergy.api.codec.recipe.ChanceOutputItem;
import com.devdyna.synergy.api.registers.MachineType;
import com.devdyna.synergy.api.utils.Size;
import com.devdyna.synergy.api.utils.x;
import com.devdyna.synergy.compat.jei.api.JEIFluidTankHelper;
import com.devdyna.synergy.compat.jei.categories.core.BaseMachineRecipeCategory;
import com.devdyna.synergy.init.builder.industrial_machines.rock_crusher.recipe.RockCrusherRecipeType;
import com.devdyna.synergy.init.types.zMachines;
import com.mojang.blaze3d.vertex.PoseStack;

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
public class RockCrusherCategory extends BaseMachineRecipeCategory<RockCrusherRecipeType> {

        public RockCrusherCategory(IGuiHelper h) {
                super(h);
                this.arrow = helper
                                .drawableBuilder(x.rl("minecraft",
                                                "textures/gui/sprites/container/furnace/burn_progress.png"),
                                                0, 0, 24, 16)
                                .setTextureSize(24, 16).buildAnimated(60,
                                                IDrawableAnimated.StartDirection.LEFT, false);
        }

        public static final RecipeType<RecipeHolder<RockCrusherRecipeType>> TYPE = RecipeType
                        .createFromVanilla(zMachines.ROCK_CRUSHER.recipe().getType());

        @Override
        public RecipeType<RecipeHolder<RockCrusherRecipeType>> getRecipeType() {
                return TYPE;
        }

        @Override
        public void setRecipe(IRecipeLayoutBuilder builder, RockCrusherRecipeType recipe, IFocusGroup focuses) {

                if (recipe.getInputItem() != null && !x.getItems(recipe.getInputItem()).isEmpty())
                        builder.addInputSlot(2 + 21, 5 + 28).addItemStacks(x.getItems(recipe.getInputItem()));

                for (ChanceOutputItem output : recipe.getResult())
                        builder.addOutputSlot(68 - 1 + (recipe.getResult().indexOf(output) % 3 * (18 + 1)),
                                        9 + (recipe.getResult().indexOf(output) / 3 * 24)).addItemStack(output.item());

                JEIFluidTankHelper.of()
                                .fluid(recipe.getFluidInput())
                                .offset(3, 21 + 28)
                                .build((x, y) -> builder.addInputSlot(x, y));

        }

        @Override
        public void draw(RockCrusherRecipeType recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics,
                        double mouseX,
                        double mouseY) {
                super.draw(recipe, recipeSlotsView, guiGraphics, mouseX, mouseY);

                arrow.draw(guiGraphics, 43 - 1, 35);

                guiGraphics.drawString(font,
                                Component.literal(
                                                recipe.getTime() + " ticks"),
                                24 - 10 - 10, 15,
                                defaultToolTipColor.getRGB(), false);

                PoseStack stack = guiGraphics.pose();
                stack.pushPose();
                stack.scale(0.6F, 0.6F, 1F);
                for (ChanceOutputItem output : recipe.getResult())
                        guiGraphics.drawCenteredString(font, ((int) (output.chance() * 100)) + "%",
                                        68 + 10 + 10 + 10 + 5 + 5 + 5 + 2 + 10
                                                        + (recipe.getResult().indexOf(output) % 3 * (20 + 12 + 1)),
                                        26 + 10 + 10 - 5 + 2 + 1
                                                        + (recipe.getResult().indexOf(output) / 3
                                                                        * (24 + 10 + 10 - 2 - 2)),
                                        0xFFFFFF);
                stack.popPose();

        }

        @Override
        public Size setXY() {
                return Size.of(124, 84);
        }

        @Override
        public MachineType<? extends Block, ? extends BlockEntity, ? extends AbstractContainerMenu, ? extends Recipe<?>> getMachine() {
                return zMachines.ROCK_CRUSHER;
        }

}
