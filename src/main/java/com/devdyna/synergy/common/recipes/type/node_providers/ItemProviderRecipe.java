package com.devdyna.synergy.common.recipes.type.node_providers;

import javax.annotation.Nullable;

import com.devdyna.synergy.api.recipes.types.BaseProviderRecipe;
import com.devdyna.synergy.api.registers.RecipeRegister;
import com.devdyna.synergy.common.recipes.input.ProviderInput;
import com.devdyna.synergy.init.types.zBlocks;
import com.devdyna.synergy.init.types.zRecipeTypes;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

@SuppressWarnings({ "null" })
public class ItemProviderRecipe<T> extends BaseProviderRecipe<ItemStack> {

    private final ItemStack output;

    public ItemProviderRecipe(BlockState core, @Nullable BlockState below, @Nullable BlockState left,
            @Nullable BlockState right, ItemStack output) {
        super(core, below, left, right, output);
        this.output = output;

    }

    public ItemStack assemble(ProviderInput i, HolderLookup.Provider r) {
        return this.output;
    }

    @Override
    public RecipeRegister<ItemProviderRecipe<ItemStack>> getRecipe() {
        return zRecipeTypes.ITEM_PROVIDER;
    }

    @Override
    @Deprecated
    public ItemStack getResultItem(HolderLookup.Provider registryAccess) {
        return this.output;
    }

    @Override
    public Item getToastIcon() {
        return zBlocks.ITEM_PROVIDER.get().asItem();
    }

}
