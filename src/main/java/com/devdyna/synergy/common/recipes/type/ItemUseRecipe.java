package com.devdyna.synergy.common.recipes.type;

import java.util.List;

import com.devdyna.synergy.api.zRecipe;
import com.devdyna.synergy.api.recipes.types.BaseRecipeType;
import com.devdyna.synergy.api.utils.x;
import com.devdyna.synergy.common.recipes.input.UseItemInput;
import com.devdyna.synergy.init.types.zRecipeTypes;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

@SuppressWarnings("null")
public class ItemUseRecipe extends BaseRecipeType<UseItemInput> {

    private final Ingredient inputItem;
    private final ItemStack outputitem;
    private final BlockState inputState;
    private final BlockState outputState;
    private final boolean canBeDisabled;
    private final boolean renderOnly;

    public ItemUseRecipe(Ingredient inputItem,
            BlockState inputState, BlockState outputState, boolean canBeDisabled, ItemStack outputitem,
            boolean renderOnly) {
        this.inputItem = inputItem;
        this.inputState = inputState;
        this.outputState = outputState;
        this.canBeDisabled = canBeDisabled;
        this.outputitem = outputitem;
        this.renderOnly = renderOnly;
    }

    public boolean matches(UseItemInput r, Level l) {
        return inputItem.test(r.input()) && inputState.is(r.block().getBlock());
    }

    public ItemStack assemble(UseItemInput i, HolderLookup.Provider r) {
        return x.item(this.outputState.getBlock());
    }

    public NonNullList<Ingredient> getIngredients() {
        return NonNullList.copyOf(List.of(x.ingredient(this.inputState.getBlock().asItem()), this.inputItem));
    }

    @Override
    @Deprecated
    public ItemStack getResultItem(HolderLookup.Provider registryAccess) {
        return x.item(this.outputState.getBlock());
    }

    public Ingredient getInputItem() {
        return inputItem;
    }

    public BlockState getInputState() {
        return inputState;
    }

    public BlockState getOutputState() {
        return outputState;
    }

    public boolean canBeDisabled() {
        return canBeDisabled;
    }

    public ItemStack getOutputitem() {
        return outputitem;
    }

    public boolean isRenderOnly() {
        return renderOnly;
    }

    @Override
    public zRecipe<? extends BaseRecipeType<UseItemInput>> getRecipe() {
        return zRecipeTypes.ITEM_USE;
    }

    @Override
    public Item getToastIcon() {
        return Items.WOODEN_PICKAXE;
    }

}
