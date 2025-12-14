package com.devdyna.synergy.compat.jei.categories;

import com.devdyna.synergy.api.node.BaseProviderRecipe;
import com.devdyna.synergy.api.utils.x;
import com.devdyna.synergy.common.recipes.type.ItemProviderRecipe;
import com.devdyna.synergy.compat.jei.categories.core.BaseProviderCategory;
import com.devdyna.synergy.init.types.zBlocks;
import com.devdyna.synergy.init.types.zRecipeTypes;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

@SuppressWarnings({ "unchecked", "null" })
public class ItemProviderCategory extends BaseProviderCategory<ItemProviderRecipe<ItemStack>, ItemStack> {

    public static final RecipeType<BaseProviderRecipe<ItemStack>> TYPE = new RecipeType<>(
            x.rl(zRecipeTypes.ITEM_PROVIDER.getId()),
            (Class<ItemProviderRecipe<ItemStack>>) (Class<?>) ItemProviderRecipe.class);

    public ItemProviderCategory(IGuiHelper helper) {
        super(helper);
    }

    @Override
    public RecipeType<BaseProviderRecipe<ItemStack>> getRecipeType() {
        return TYPE;
    }

    @Override
    protected String getProviderType() {
        return "item";
    }

    @Override
    public Item getIconItem() {
        return zBlocks.ITEM_PROVIDER.get().asItem();
    }

    @Override
    protected void defineOutput(IRecipeLayoutBuilder builder, BaseProviderRecipe<ItemStack> recipe,
            IFocusGroup focuses) {
        builder.addOutputSlot(33-15, 10-8).addItemStack(recipe.getOutput());
    }


}
