package com.devdyna.synergy.common.recipes.builders;

import static com.devdyna.synergy.Main.ID;

import java.util.LinkedHashMap;
import javax.annotation.Nullable;

import com.devdyna.synergy.api.recipes.builders.BaseRecipeBuilder;
import com.devdyna.synergy.api.utils.IngredientUtils;
import com.devdyna.synergy.api.utils.x;
import com.devdyna.synergy.common.recipes.type.ItemUseRecipe;

import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup.RegistryLookup;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.registries.DeferredHolder;

@SuppressWarnings("null")
public class ItemUseBuilder extends BaseRecipeBuilder {

    private Ingredient inputItem;
    private BlockState inputState;
    private BlockState outputState;
    private boolean canBeDisabled;
    private ItemStack outputItem;
    private boolean renderOnly;

    public ItemUseBuilder(RegistryLookup<Item> p) {
        super(p);
        this.outputItem = null;
        this.canBeDisabled = false;
        this.renderOnly = false;
        this.criteria = new LinkedHashMap<String, Criterion<?>>();
    }

    public static ItemUseBuilder of(RegistryLookup<Item> p) {
        return new ItemUseBuilder(p);
    }

    public ItemUseBuilder inputItem(ItemStack i) {
        this.inputItem = x.ingredient(i.getItem());
        return this;
    }

    public ItemUseBuilder inputItem(Item input) {
        return inputItem(x.item(input));
    }

    public ItemUseBuilder inputItem(DeferredHolder<Item, Item> input) {
        return inputItem(input.get());
    }

    public ItemUseBuilder inputBlock(BlockState b) {
        this.inputState = b;
        return this;
    }

    public ItemUseBuilder canBeDisabled() {
        this.canBeDisabled = true;
        return this;
    }

    public ItemUseBuilder isRenderOnly() {
        this.renderOnly = true;
        return this;
    }

    public ItemUseBuilder outputBlock(BlockState b) {
        this.outputState = b;
        return this;
    }

    public ItemUseBuilder inputBlock(Block b) {
        return inputBlock(b.defaultBlockState());
    }

    public ItemUseBuilder outputBlock(Block b) {
        return outputBlock(b.defaultBlockState());
    }

    /**
     * Optional
     */
    public ItemUseBuilder outputItem(ItemStack i) {
        this.outputItem = i;
        return this;
    }

    /**
     * Optional
     */
    public ItemUseBuilder outputItem(Item i) {
        this.outputItem = x.item(i);
        return this;
    }

    public ItemUseBuilder inputBlock(DeferredHolder<Block, ?> b) {
        return inputBlock(b.get());
    }

    public ItemUseBuilder outputBlock(DeferredHolder<Block, ?> b) {
        return outputBlock(b.get());
    }

    public ItemUseBuilder unlockedBy() {
        return unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                .hasItems(IngredientUtils.getItemLike(inputItem)));
    }

    public ItemUseBuilder unlockedBy(String name, Criterion<?> criterion) {
        this.criteria.put(name, criterion);
        return this;
    }

    public ItemUseBuilder group(@Nullable String groupName) {
        return this;
    }

    public Item getResult() {
        return x.item(this.outputState).getItem();
    }

    @Override
    public String getSuffix(String extra) {
        return "item_use/" +
                        x.path(outputState.getBlock())
                        + "_from_" +
                        x.path(inputState.getBlock())
                        + extra;
    }

    @Override
    public Recipe<?> createRecipe() {
        return new ItemUseRecipe(inputItem, inputState, outputState, canBeDisabled, outputItem, renderOnly);
    }
}
