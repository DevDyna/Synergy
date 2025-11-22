package com.devdyna.synergy.compat.jei.categories;

import com.devdyna.synergy.Main;
import com.devdyna.synergy.api.Pos;
import com.devdyna.synergy.api.Size;
import com.devdyna.synergy.client.gui.screenLocations;
import com.devdyna.synergy.compat.jei.categories.core.BaseRecipeCategory;
import com.devdyna.synergy.init.recipeTypes.type.ItemUseRecipe;
import com.devdyna.synergy.init.types.zRecipeTypes;
import com.devdyna.synergy.utils.x;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.builder.ITooltipBuilder;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeType;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.LiquidBlock;

@SuppressWarnings("null")
public class ItemUseCategory extends BaseRecipeCategory<ItemUseRecipe> {

    public static final RecipeType<ItemUseRecipe> TYPE = new RecipeType<>(
            x.rl(zRecipeTypes.ITEM_USE.getId()),
            ItemUseRecipe.class);

    public ItemUseCategory(IGuiHelper helper) {
        super(helper);
    }

    @Override
    public RecipeType<ItemUseRecipe> getRecipeType() {
        return TYPE;
    }

    @Override
    public String getTitleKey() {
        return "item_use";
    }

    @Override
    public ItemLike getIconItem() {
        return Items.WOODEN_PICKAXE;
    }

    @Override
    public Size setXY() {
        return Size.of(103, 70);
    }

    @Override
    public String setBackGround() {
        return "textures/gui/jei/click_event.png";
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, ItemUseRecipe recipe, IFocusGroup focuses) {

        builder.addInputSlot(5, 4).addIngredients(recipe.getInputItem());

        try {
            var in = recipe.getInputState();
            if (in.getBlock() instanceof LiquidBlock fluid)
                builder.addInputSlot(45, 27).addFluidStack(fluid.fluid);
            else
                builder.addInputSlot(45, 27).addItemStack(x.item(in));
        } catch (Exception e) {
        }

        try {

            var out = recipe.getOutputState();
            if (out.getBlock() instanceof LiquidBlock fluid)
                builder.addOutputSlot(81, 49).addFluidStack(fluid.fluid);
            else
                builder.addOutputSlot(81, 49).addItemStack(x.item(out));

        } catch (Exception e) {
        }
    }

    @Override
    public void draw(ItemUseRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX,
            double mouseY) {
        if (recipe.canBeDisabled())
            helper.drawableBuilder(screenLocations.WARNING, 0, 0, 10, 10).setTextureSize(10, 10).build()
                    .draw(guiGraphics, 81, 7);
    }

    @Override
    public void getTooltip(ITooltipBuilder tooltip, ItemUseRecipe recipe, IRecipeSlotsView recipeSlotsView,
            double mouseX, double mouseY) {
        if (Pos.of(81, 7).setSize(10, 10).test(mouseX, mouseY))
            tooltip.add(Component.translatable(Main.ID + ".jei.warning.config"));

    }

}
