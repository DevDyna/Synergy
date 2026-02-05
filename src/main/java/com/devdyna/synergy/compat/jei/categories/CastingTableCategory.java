package com.devdyna.synergy.compat.jei.categories;

import static com.devdyna.synergy.Main.ID;

import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.api.utils.Size;
import com.devdyna.synergy.compat.jei.api.JEIFluidTankHelper;
import com.devdyna.synergy.compat.jei.categories.core.BaseRecipeCategory;
import com.devdyna.synergy.init.builder.survival.casting_table.recipe.CastingTableRecipe;
import com.devdyna.synergy.init.types.zBlocks;
import com.devdyna.synergy.init.types.zRecipeTypes;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeType;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.ItemLike;

@SuppressWarnings("null")
public class CastingTableCategory extends BaseRecipeCategory<CastingTableRecipe> {

    public CastingTableCategory(IGuiHelper helper) {
        super(helper);
    }

    public static final RecipeType<RecipeHolder<CastingTableRecipe>> TYPE = RecipeType
            .createFromVanilla(zRecipeTypes.CASTING_TABLE.getType());

    @Override
    public RecipeType<RecipeHolder<CastingTableRecipe>> getRecipeType() {
        return TYPE;
    }

    @Override
    public String getTitleKey() {
        return zStatic.Blocks.casting_table;
    }

    @Override
    public ItemLike getIconItem() {
        return zBlocks.CASTING_TABLE.get();
    }

    @Override
    public Size setXY() {
        return Size.of(75, 40);
    }

    @Override
    public String setBackGround() {
        return "textures/gui/jei/casting_table.png";
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, CastingTableRecipe recipe, IFocusGroup focuses) {

        JEIFluidTankHelper.of()
                .fluid(recipe.getFluid())
                .offset(2, 2 + 16)
                .scale(1.0f, 1.0f)
                .build((x, y) -> builder.addInputSlot(x, y));

        var item = builder.addInputSlot(2, 22).addIngredients(recipe.getInput());

        if (!recipe.consumeInput())
            item.addRichTooltipCallback(
                    (v, t) -> t.add(Component.translatable(ID + ".jei.tip.dont_consume")));

        builder.addOutputSlot(52, 11).addItemStack(recipe.getOutput());

    }

    @Override
    public boolean enableTimerRender() {
        return true;
    }

    @Override
    public int tickValue(CastingTableRecipe recipe) {
        return recipe.getTicks();
    }

    @Override
    public Size tickPos() {
        return Size.of(21, 32);
    }

}
