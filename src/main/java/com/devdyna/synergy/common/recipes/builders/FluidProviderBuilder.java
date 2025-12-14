package com.devdyna.synergy.common.recipes.builders;

import static com.devdyna.synergy.Main.ID;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.Nullable;

import com.devdyna.synergy.api.recipebuilders.BaseRecipeBuilder;
import com.devdyna.synergy.api.utils.x;
import com.devdyna.synergy.common.recipes.type.FluidProviderRecipe;

import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.fluids.FluidStack;

@SuppressWarnings({ "null" })
public class FluidProviderBuilder extends BaseRecipeBuilder {

    private BlockState core;
    private BlockState below = Blocks.AIR.defaultBlockState();
    private BlockState left = Blocks.AIR.defaultBlockState();
    private BlockState right = Blocks.AIR.defaultBlockState();
    private FluidStack output;

    private final Map<String, Criterion<?>> criteria;

    public FluidProviderBuilder() {
        this.criteria = new LinkedHashMap<String, Criterion<?>>();
    }

    public static FluidProviderBuilder of() {
        return new FluidProviderBuilder();
    }

    public FluidProviderBuilder core(BlockState b) {
        this.core = b;
        return this;
    }

    public FluidProviderBuilder below(@Nullable BlockState b) {
        this.below = b;
        return this;
    }

    public FluidProviderBuilder left(@Nullable BlockState b) {
        this.left = b;
        return this;
    }

    public FluidProviderBuilder right(@Nullable BlockState b) {
        this.right = b;
        return this;
    }

    public FluidProviderBuilder core(Block b) {
        return core(b.defaultBlockState());
    }

    public FluidProviderBuilder below(@Nullable Block b) {
        return below(b.defaultBlockState());
    }

    public FluidProviderBuilder left(@Nullable Block b) {
        return left(b.defaultBlockState());
    }

    public FluidProviderBuilder right(@Nullable Block b) {
        return right(b.defaultBlockState());
    }

    public FluidProviderBuilder output(FluidStack b) {
        this.output = b;
        return this;
    }

    public FluidProviderBuilder output(Fluid b) {
        return output(x.fluid(b));
    }

    public FluidProviderBuilder output(Fluid b, int c) {
        return output(x.fluid(b, c));
    }

    public FluidProviderBuilder unlockedBy() {
        return unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                .hasItems(x.item(core).getItem()));
    }

    public FluidProviderBuilder unlockedBy(String name, Criterion<?> criterion) {
        this.criteria.put(name, criterion);
        return this;
    }

    public FluidProviderBuilder group(@Nullable String groupName) {
        return this;
    }

    public Item getResult() {
        return x.item(this.core).getItem();
    }

    @Override
    public ResourceLocation getSuffix(String extra) {
        return x.rl("provider/fluid/" +
                x.path(output.getFluid()) + "_from_" + x.path(core)
                + extra);
    }

    @Override
    public Recipe<?> createRecipe() {
        return new FluidProviderRecipe<>(core, below, left, right, output);
    }
}
