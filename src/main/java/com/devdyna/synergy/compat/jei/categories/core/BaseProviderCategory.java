package com.devdyna.synergy.compat.jei.categories.core;

import static com.devdyna.synergy.Main.ID;

import java.awt.Color;

import org.jetbrains.annotations.Nullable;

import com.devdyna.synergy.api.recipes.types.BaseProviderRecipe;
import com.devdyna.synergy.api.utils.ColorUtil;
import com.devdyna.synergy.api.utils.Image;
import com.devdyna.synergy.api.utils.Size;
import com.devdyna.synergy.api.utils.x;
import com.devdyna.synergy.init.types.zBlocks;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.LiquidBlock;

@SuppressWarnings({ "null" })
public abstract class BaseProviderCategory<T extends BaseProviderRecipe<J>, J>
        implements IRecipeCategory<T> {

    protected IGuiHelper helper;

    public final Font font = Minecraft.getInstance().font;

    protected final Color defaultToolTipColor = ColorUtil.color(64, 64, 64);

    public abstract ItemLike getIconItem();

    public BaseProviderCategory(IGuiHelper h) {
        this.helper = h;
    }

    protected abstract String getProviderType();

    public String getTitleKey() {
        return "provider." + getProviderType();
    }

    public Size setXY() {
        return Size.of(52, 88);
    }

    public String setBackGround() {
        return "textures/gui/provider.png";
    }

    protected abstract void defineOutput(IRecipeLayoutBuilder builder, BaseProviderRecipe<J> recipe,
            IFocusGroup focuses);

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, T recipe, IFocusGroup focuses) {

        defineOutput(builder, recipe, focuses);

        builder.addSlot(RecipeIngredientRole.RENDER_ONLY, 33 - 15, 45 - 8)
                .addIngredients(x.ingredient(zBlocks.FLUID_PROVIDER.get()));

        try {
            var core = recipe.getCore();
            if (core.getBlock() instanceof LiquidBlock fluid)
                builder.addInputSlot(33 - 15, 62 - 8).addFluidStack(fluid.fluid);
            else
                builder.addInputSlot(33 - 15, 62 - 8).addItemStack(x.item(core));
        } catch (Exception e) {
        }

        try {
            var below = recipe.getBelow();
            if (!below.isAir()) {
                if (below.getBlock() instanceof LiquidBlock fluid)
                    builder.addInputSlot(33 - 15, 79 - 8).addFluidStack(fluid.fluid);
                else
                    builder.addInputSlot(33 - 15, 79 - 8).addItemStack(x.item(below));
            }
        } catch (Exception e) {
        }

        try {
            var right = recipe.getRight();
            if (!right.isAir()) {
                if (right.getBlock() instanceof LiquidBlock fluid)
                    builder.addInputSlot(50 - 15, 62 - 8).addFluidStack(fluid.fluid);
                else
                    builder.addInputSlot(50 - 15, 62 - 8).addItemStack(x.item(right));
            }
        } catch (Exception e) {
        }

        try {
            var left = recipe.getLeft();
            if (!left.isAir()) {
                if (left.getBlock() instanceof LiquidBlock fluid)
                    builder.addInputSlot(16 - 15, 62 - 8).addFluidStack(fluid.fluid);
                else
                    builder.addInputSlot(16 - 15, 62 - 8).addItemStack(x.item(left));
            }
        } catch (Exception e) {
        }

    }

    @Override
    public Component getTitle() {
        return Component.translatable(ID + ".jei." + getTitleKey());
    }

    @Override
    public @Nullable IDrawable getIcon() {
        return helper.createDrawableItemLike(getIconItem());
    }

    @Override
    public int getWidth() {
        return setXY().getX();
    }

    @Override
    public int getHeight() {
        return setXY().getY();
    }

    public void background(GuiGraphics graphics) {
        Image.of()
                .rl(this.setBackGround())
                .size(this.getWidth(), this.getHeight())
                .render(helper, graphics);
    }

    public void draw(T recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX,
            double mouseY) {
        background(guiGraphics);
    }

}
