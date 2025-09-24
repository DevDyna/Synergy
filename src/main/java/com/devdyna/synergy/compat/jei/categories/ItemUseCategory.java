package com.devdyna.synergy.compat.jei.categories;

import org.jetbrains.annotations.Nullable;

import com.devdyna.synergy.Main;
import com.devdyna.synergy.client.gui.screenLocations;
import com.devdyna.synergy.compat.jei.drawable.SimpleIcon;
import com.devdyna.synergy.init.recipeTypes.type.ItemUseRecipe;
import com.devdyna.synergy.init.types.zRecipeTypes;
import com.devdyna.synergy.utils.x;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.LiquidBlock;

@SuppressWarnings("null")
public class ItemUseCategory implements IRecipeCategory<ItemUseRecipe> {

    private IGuiHelper helper;

    public static final RecipeType<ItemUseRecipe> TYPE = new RecipeType<>(
            x.rl(zRecipeTypes.ITEM_USE.getId()),
            ItemUseRecipe.class);

    public ItemUseCategory(IGuiHelper helper) {
        this.helper = helper;
    }

    @Override
    public RecipeType<ItemUseRecipe> getRecipeType() {
        return TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable(Main.ID + ".jei.itemuse");
    }

    @Override
    public @Nullable IDrawable getIcon() {
        return SimpleIcon.of(helper, Items.WOODEN_PICKAXE);
    }

    @Override
    public @Nullable IDrawable getBackground() {
        return helper.createDrawable(screenLocations.ITEM_USE, 0, 0, 103, 70);
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, ItemUseRecipe recipe, IFocusGroup focuses) {

        try {
            builder.addInputSlot(5, 4).addIngredients(recipe.getInputItem());
        } catch (Exception e) {
        }

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

}
