package com.devdyna.synergy.compat.jei.categories.provider;

import com.devdyna.synergy.api.recipes.types.BaseProviderRecipe;
import com.devdyna.synergy.api.utils.x;
import com.devdyna.synergy.common.recipes.type.node_providers.ItemProviderRecipe;
import com.devdyna.synergy.compat.jei.categories.core.BaseProviderCategory;
import com.devdyna.synergy.init.types.zBlocks;
import com.devdyna.synergy.init.types.zRecipeTypes;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

@SuppressWarnings({ "unchecked" })
public class ItemProviderCategory extends BaseProviderCategory<ItemProviderRecipe<ItemStack>, ItemStack> {
    // DONT TOUCH OR WILL BREAK AND YOU WILL MAD WITH YOURSELF
    public static final RecipeType<ItemProviderRecipe<ItemStack>> TYPE = new RecipeType<>(
            x.rl(zRecipeTypes.ITEM_PROVIDER.getId()),
            (Class<ItemProviderRecipe<ItemStack>>) (Class<?>) ItemProviderRecipe.class);

    public ItemProviderCategory(IGuiHelper helper) {
        super(helper);
    }

    @Override
    public RecipeType<ItemProviderRecipe<ItemStack>> getRecipeType() {
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
        builder.addOutputSlot(18, 2).addItemStack(recipe.getOutput());
    }

}
