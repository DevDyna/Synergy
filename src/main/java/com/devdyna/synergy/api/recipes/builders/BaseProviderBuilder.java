package com.devdyna.synergy.api.recipes.builders;

import static com.devdyna.synergy.Main.ID;

import javax.annotation.Nullable;

import com.devdyna.synergy.api.codec.recipe.NodePattern;
import com.devdyna.synergy.api.recipes.builders.api.BaseRecipeBuilder;
import com.devdyna.synergy.api.utils.x;

import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public abstract class BaseProviderBuilder<BUILDER extends BaseProviderBuilder<?>> extends BaseRecipeBuilder {
    protected NodePattern pattern;

    protected abstract BUILDER getBuilder();

    public BUILDER pattern(
            BlockState core,
            @Nullable BlockState left,
            @Nullable BlockState right,
            @Nullable BlockState below) {
        this.pattern = NodePattern.of(core, left, right, below);
        return getBuilder();
    }

    public BUILDER pattern(
            BlockState core,
            @Nullable BlockState left,
            @Nullable BlockState right) {
        return pattern(core, left, right, null);
    }

    public BUILDER pattern(
            BlockState core,
            @Nullable BlockState left) {
        return pattern(core, left, null, null);
    }

    public BUILDER pattern(
            BlockState core) {
        return pattern(core, null, null, null);
    }

    public BUILDER pattern(
            Block core,
            @Nullable Block left,
            @Nullable Block right,
            @Nullable Block below) {
        this.pattern = NodePattern.of(core.defaultBlockState(),
                left == null ? null : left.defaultBlockState(),
                right == null ? null : right.defaultBlockState(),
                below == null ? null : below.defaultBlockState());
        return getBuilder();
    }

    public BUILDER pattern(
            Block core,
            @Nullable Block left,
            @Nullable Block right) {
        return pattern(core, left, right, null);
    }

    public BUILDER pattern(
            Block core,
            @Nullable Block left) {
        return pattern(core, left, null, null);
    }

    public BUILDER pattern(
            Block core) {
        return pattern(core, null, null, null);
    }

    public BUILDER unlockedBy() {
        return unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                .hasItems(x.item(pattern.core()).getItem()));
    }

    public BUILDER unlockedBy(String name, Criterion<?> criterion) {
        this.criteria.put(name, criterion);
        return getBuilder();
    }

    @Override
    public ResourceLocation getSuffix(String extra) {
        return x.rl("provider/" + getFolderPath() + "/" +
                (getOutputPath() == x.path(pattern.core())
                        ? x.path(pattern.core())
                        : getOutputPath() + "_from_" + x.path(pattern.core()))
                + extra);
    }

    public abstract String getOutputPath();

    public abstract String getFolderPath();
}
