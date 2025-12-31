package com.devdyna.synergy.compat.jei.categories.machines;

import static com.devdyna.synergy.Main.ID;

import com.devdyna.synergy.api.MachineType;
import com.devdyna.synergy.api.utils.Size;
import com.devdyna.synergy.api.utils.x;
import com.devdyna.synergy.compat.jei.categories.core.BaseMachineRecipeCategory;
import com.devdyna.synergy.init.builder.industrial_machines.compressor.recipe.CompressorRecipeType;
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
public class CompressorCategory extends BaseMachineRecipeCategory<CompressorRecipeType> {

    public CompressorCategory(IGuiHelper h) {
        super(h);
        this.arrow = helper
                .drawableBuilder(x.rl("minecraft", "textures/gui/sprites/container/furnace/burn_progress.png"),
                        0, 0, 24, 16)
                .setTextureSize(24, 16).buildAnimated(60,
                        IDrawableAnimated.StartDirection.LEFT, false);
    }

    public static final RecipeType<CompressorRecipeType> TYPE = new RecipeType<>(
            x.rl(zMachines.COMPRESSOR.recipe().getId()),
            CompressorRecipeType.class);

    @Override
    public RecipeType<CompressorRecipeType> getRecipeType() {
        return TYPE;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, CompressorRecipeType recipe, IFocusGroup focuses) {

        builder.addInputSlot(2, 2).addIngredients(recipe.getInputItem());
        builder.addInputSlot(2, 38).addIngredients(recipe.getCatalystItem())
                .addRichTooltipCallback(
                        (v, t) -> t.add(Component.translatable(ID + ".jei.tip.dont_consume")));

        builder.addOutputSlot(74, 21).addItemStack(recipe.getOutputItem());

    }

    @Override
    public void draw(CompressorRecipeType recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics,
            double mouseX,
            double mouseY) {
        super.draw(recipe, recipeSlotsView, guiGraphics, mouseX, mouseY);

        arrow.draw(guiGraphics, 29, 21);

        guiGraphics.drawString(font,
                Component.literal(
                        recipe.getTime() + " ticks"),
                25, 2,
                defaultToolTipColor.getRGB(), false);

    }

    @Override
    public Size setXY() {
        return Size.of(96, 56);
    }

    @Override
    public MachineType<? extends Block, ? extends BlockEntity, ? extends AbstractContainerMenu, ? extends Recipe<?>> getMachine() {
        return zMachines.COMPRESSOR;
    }

}
