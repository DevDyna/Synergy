package com.devdyna.synergy.init.builder.survival.crushing_tub.recipe;

import static com.devdyna.synergy.Main.ID;

import java.util.*;

import com.devdyna.synergy.api.recipes.builders.api.BaseRecipeBuilder;
import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.api.codec.ChanceOutputItem;
import com.devdyna.synergy.api.recipes.builders.*;
import com.devdyna.synergy.api.utils.IngredientUtils;
import com.devdyna.synergy.api.utils.x;

import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.registries.DeferredHolder;

@SuppressWarnings({ "null" })
public class CrushingTubBuilder extends BaseRecipeBuilder
        implements
        ItemAttach.Input.NoItemCount<CrushingTubBuilder>,
        FluidAttach.Any.SimpleFluidAttach<CrushingTubBuilder> {

    private Ingredient input;
    private FluidStack fluid;
    private ChanceOutputItem output;

    private CrushingTubBuilder() {
        this.criteria = new LinkedHashMap<String, Criterion<?>>();
    }

    public static CrushingTubBuilder of() {
        return new CrushingTubBuilder();
    }

    public CrushingTubBuilder unlockedBy() {
        return unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                .hasItems(IngredientUtils.getItemLike(input)));
    }

    public CrushingTubBuilder unlockedBy(String name, Criterion<?> criterion) {
        this.criteria.put(name, criterion);
        return this;
    }

    public Item getResult() {
        return this.output != null
                ? this.output.item().getItem()
                : this.fluid.getFluid().getBucket();
    }

    @Override
    public Recipe<?> createRecipe() {
        return new CrushingTubRecipe(input, output, fluid);
    }

    @Override
    public CrushingTubBuilder getBuilder() {
        return this;
    }

    @Override
    public CrushingTubBuilder fluid(FluidStack fluid) {
        this.fluid = fluid;
        return this;
    }

    @Override
    public CrushingTubBuilder input(Ingredient input) {
        this.input = input;
        return this;
    }

    public CrushingTubBuilder output(ChanceOutputItem output) {
        this.output = output;
        return this;
    }

    public CrushingTubBuilder output(ItemStack output, float chance) {
        return output(ChanceOutputItem.of(output, chance));
    }

    public CrushingTubBuilder output(Item output, float chance) {
        return output(x.item(output), chance);
    }

    public CrushingTubBuilder output(DeferredHolder<Item, Item> output, float chance) {
        return output(x.item(output), chance);
    }

    @Override
    public ResourceLocation getSuffix(String extra) {
        return x.rl(zStatic.Blocks.crushing_tub + "/"
                + (this.output != null ? x.path(this.output.item().getItem())
                        : x.path(this.fluid.getFluid()))
                + extra);
    }

}
