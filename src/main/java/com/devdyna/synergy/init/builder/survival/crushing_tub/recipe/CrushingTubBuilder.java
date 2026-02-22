package com.devdyna.synergy.init.builder.survival.crushing_tub.recipe;

import static com.devdyna.synergy.Main.ID;

import java.util.*;
import javax.annotation.Nullable;

import com.devdyna.synergy.api.recipes.builders.api.BaseRecipeBuilder;
import com.devdyna.synergy.zStatic;
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

@SuppressWarnings({ "null" })
public class CrushingTubBuilder extends BaseRecipeBuilder
        implements ItemAttach.Output.SimpleOutputItem<CrushingTubBuilder>, ItemAttach.Input.NoItemCount<CrushingTubBuilder>,
        FluidAttach.Any.SimpleFluidAttach<CrushingTubBuilder> {

    private Ingredient input;
    private FluidStack fluid;
    private ItemStack output;

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
        return this.output.getItem();
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
    public CrushingTubBuilder group(@Nullable String groupName) {
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

    @Override
    public CrushingTubBuilder output(ItemStack output) {
        this.output = output;
        return this;
    }

    @Override
    public ResourceLocation getSuffix(String extra) {
        return x.rl(zStatic.Blocks.crushing_tub+"/" + x.path(this.output.getItem())
                + extra);
    }

}
