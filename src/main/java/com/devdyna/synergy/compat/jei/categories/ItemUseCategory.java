package com.devdyna.synergy.compat.jei.categories;

import com.devdyna.synergy.Main;
import com.devdyna.synergy.api.utils.Image;
import com.devdyna.synergy.api.utils.Pos;
import com.devdyna.synergy.api.utils.Size;
import com.devdyna.synergy.api.utils.TimeUtil;
import com.devdyna.synergy.api.utils.x;
import com.devdyna.synergy.common.recipes.type.ItemUseRecipe;
import com.devdyna.synergy.compat.jei.categories.core.BaseRecipeCategory;
import com.devdyna.synergy.init.types.zRecipeTypes;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.builder.ITooltipBuilder;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeType;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.LiquidBlock;

@SuppressWarnings("null")
public class ItemUseCategory extends BaseRecipeCategory<ItemUseRecipe> {

    public ItemUseCategory(IGuiHelper helper) {
        super(helper);
    }

    public static final RecipeType<RecipeHolder<ItemUseRecipe>> TYPE = RecipeType
            .createFromVanilla(zRecipeTypes.ITEM_USE.getType());

    @Override
    public RecipeType<RecipeHolder<ItemUseRecipe>> getRecipeType() {
        return TYPE;
    }

    @Override
    public String getTitleKey() {
        return "item_use";
    }

    @Override
    public ItemLike getIconItem() {
        return Items.GLASS_BOTTLE;
    }

    @Override
    public Size setXY() {
        return Size.of(91, 41);
    }

    @Override
    public String setBackGround() {
        return "textures/gui/item_use.png";
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, ItemUseRecipe recipe, IFocusGroup focuses) {

        if (recipe.getInputItem().test(x.item(Items.POTION)))// hardcoded jei fix , i need to investigate on future to
                                                             // fix
            builder.addInputSlot(1, 1).addItemStack(PotionContents.createItemStack(Items.POTION, Potions.WATER));
        else
            builder.addInputSlot(1, 1).addIngredients(recipe.getInputItem());

        try {
            var in = recipe.getInputState();
            if (in.getBlock() instanceof LiquidBlock fluid)
                builder.addInputSlot(29, 24).addFluidStack(fluid.fluid);
            else
                builder.addInputSlot(29, 24).addItemStack(x.item(in));
        } catch (Exception e) {
        }

        try {

            var out = recipe.getOutputState();
            if (out.getBlock() instanceof LiquidBlock fluid)
                builder.addOutputSlot(74, 24).addFluidStack(fluid.fluid);
            else
                builder.addOutputSlot(74, 24).addItemStack(x.item(out));

        } catch (Exception e) {
        }

        if (recipe.getOutputitem() != null && !recipe.getOutputitem().isEmpty())
            builder.addOutputSlot(55, 2).addItemStack(recipe.getOutputitem());

    }

    @Override
    public void draw(ItemUseRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX,
            double mouseY) {
        super.draw(recipe, recipeSlotsView, guiGraphics, mouseX, mouseY);
        if (recipe.isRenderOnly())
            helper.drawableBuilder(x.rl("minecraft",
                    "textures/gui/sprites/icon/unseen_notification.png"), 0, 0, 10, 10).setTextureSize(10, 10).build()
                    .draw(guiGraphics, 77, 3);

        Image.of().rl("minecraft",
                "textures/gui/sprites/toast/" +
                        (TimeUtil.fireAt(600) ? "right_click" : "mouse") + ".png")
                .size(20, 20)
                .offset(1, 20).render(helper, guiGraphics);

        if (recipe.getOutputitem() != null && !recipe.getOutputitem().isEmpty())
            Image.of().rl(
                    "textures/gui/item_use_output.png")
                    .size(32, 19)
                    .offset(40, 0).render(helper, guiGraphics);

    }

    @Override
    public void getTooltip(ITooltipBuilder tooltip, ItemUseRecipe recipe, IRecipeSlotsView recipeSlotsView,
            double mouseX, double mouseY) {

        if (recipe.isRenderOnly() && Pos.of(77, 3).setSize(10, 10).test(mouseX, mouseY))
            tooltip.add(Component.translatable(Main.ID + ".jei.warning.render_only"));

    }

}
