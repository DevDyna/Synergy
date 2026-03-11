package com.devdyna.synergy.compat.jei.categories;

import java.util.function.BiConsumer;

import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.api.utils.Image;
import com.devdyna.synergy.api.utils.Pos;
import com.devdyna.synergy.api.utils.Size;
import com.devdyna.synergy.api.utils.x;
import com.devdyna.synergy.compat.jei.categories.core.BaseRecipeCategory;
import com.devdyna.synergy.init.builder.magic.urn.recipe.UrnRitualRecipe;
import com.devdyna.synergy.init.types.zBlocks;
import com.devdyna.synergy.init.types.zRecipeTypes;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.ItemLike;

@SuppressWarnings("null")
public class UrnCategory extends BaseRecipeCategory<UrnRitualRecipe> {

    public UrnCategory(IGuiHelper helper) {
        super(helper);
    }

    public static final RecipeType<RecipeHolder<UrnRitualRecipe>> TYPE = RecipeType
            .createFromVanilla(zRecipeTypes.URN_RITUAL_RECIPE.getType());

    @Override
    public RecipeType<RecipeHolder<UrnRitualRecipe>> getRecipeType() {
        return TYPE;
    }

    @Override
    public String getTitleKey() {
        return zStatic.Blocks.urn;
    }

    @Override
    public ItemLike getIconItem() {
        return zBlocks.URN.get();
    }

    @Override
    public Size setXY() {
        return Size.of(103, 54);
    }

    @Override
    public String setBackGround() {
        return "textures/gui/jei/urn_window.png";
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, UrnRitualRecipe recipe, IFocusGroup focuses) {

        checkedSort(recipe.getInputs().size(),
                (i, p) -> builder.addSlot(RecipeIngredientRole.INPUT, p.getX0(), p.getY0())
                        .addItemStacks(x.getItems(recipe.getInputs().get(i))));

        builder.addSlot(RecipeIngredientRole.OUTPUT, 86, 19)
                .addItemStack(recipe.getOutput());
    }

    @Override
    public void draw(UrnRitualRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX,
            double mouseY) {
        super.draw(recipe, recipeSlotsView, guiGraphics, mouseX, mouseY);

        checkedSort(recipe.getInputs().size(), (i, p) -> Image.of()
                .rl(x.rl("textures/gui/sprite/button.png"))
                .size(16, 16)
                .offset(p.getX0(), p.getY0())
                .render(helper, guiGraphics));

    }

    private void checkedSort(int size, BiConsumer<Integer, Pos> s) {

        int rows = (size == 4) ? 2 : (int) Math.ceil(size / (double) 3);

        int placed = 0;

        for (int row = 0; row < rows; row++) {

            int itemsInRow = size - placed;

            if (size == 4)
                itemsInRow = 2;
            else if (row < rows - 1)
                itemsInRow = 3;

            for (int col = 0; col < itemsInRow; col++) {

                s.accept(placed, Pos.of(
                        1 + ((3 * 18 - itemsInRow * 18) / 2) + col * 18,
                        1 + ((3 * 18 - rows * 18) / 2) + row * 18));

                placed++;
            }
        }
    }

}
