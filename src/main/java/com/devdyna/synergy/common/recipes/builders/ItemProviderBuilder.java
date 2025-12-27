package com.devdyna.synergy.common.recipes.builders;

import static com.devdyna.synergy.Main.ID;

import java.util.LinkedHashMap;

import javax.annotation.Nullable;

import com.devdyna.synergy.api.recipes.builders.BaseRecipeBuilder;
import com.devdyna.synergy.api.utils.x;
import com.devdyna.synergy.common.recipes.type.node_providers.ItemProviderRecipe;

import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

@SuppressWarnings({ "null" })
public class ItemProviderBuilder extends BaseRecipeBuilder {

    private BlockState core;
    private BlockState below = Blocks.AIR.defaultBlockState();
    private BlockState left = Blocks.AIR.defaultBlockState();
    private BlockState right = Blocks.AIR.defaultBlockState();
    private ItemStack output;

    public ItemProviderBuilder() {
        this.criteria = new LinkedHashMap<String, Criterion<?>>();
    }

    public static ItemProviderBuilder of() {
        return new ItemProviderBuilder();
    }

    public ItemProviderBuilder core(BlockState b) {
        this.core = b;
        return this;
    }

    public ItemProviderBuilder below(@Nullable BlockState b) {
        this.below = b;
        return this;
    }

    public ItemProviderBuilder left(@Nullable BlockState b) {
        this.left = b;
        return this;
    }

    public ItemProviderBuilder right(@Nullable BlockState b) {
        this.right = b;
        return this;
    }

    public ItemProviderBuilder core(Block b) {
        return core(b.defaultBlockState());
    }

    public ItemProviderBuilder below(@Nullable Block b) {
        return below(b.defaultBlockState());
    }

    public ItemProviderBuilder left(@Nullable Block b) {
        return left(b.defaultBlockState());
    }

    public ItemProviderBuilder right(@Nullable Block b) {
        return right(b.defaultBlockState());
    }

    public ItemProviderBuilder output(ItemStack b) {
        this.output = b;
        return this;
    }

    public ItemProviderBuilder output(Item b) {
        return output(x.item(b, 1));
    }

    public ItemProviderBuilder unlockedBy() {
        return unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                .hasItems(x.item(core).getItem()));
    }

    public ItemProviderBuilder unlockedBy(String name, Criterion<?> criterion) {
        this.criteria.put(name, criterion);
        return this;
    }

    public ItemProviderBuilder group(@Nullable String groupName) {
        return this;
    }

    public Item getResult() {
        return x.item(this.core).getItem();
    }

    @Override
    public ResourceLocation getSuffix(String extra) {
        return x.rl("provider/item/" +
                (x.path(output) == x.path(core)
                        ? x.path(core)
                        : x.path(output) + "_from_" + x.path(core))
                + extra);
    }

    @Override
    public Recipe<?> createRecipe() {
        return new ItemProviderRecipe<>(core, below, left, right, output);
    }
}
