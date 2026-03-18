package com.devdyna.synergy.init.builder.survival.drying_rack.recipe;

import static com.devdyna.synergy.Main.ID;

import java.util.*;

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

@SuppressWarnings({ "null" })
public class DryingRackBuilder extends BaseRecipeBuilder
        implements ItemAttach.Input.NoItemCount<DryingRackBuilder>,
        ItemAttach.Output.SimpleOutputItem<DryingRackBuilder> {

    private Ingredient input;
    private int ticks = 100;
    private ItemStack output;

    private DryingRackBuilder() {
        this.criteria = new LinkedHashMap<String, Criterion<?>>();
    }

    public static DryingRackBuilder of() {
        return new DryingRackBuilder();
    }

    public DryingRackBuilder unlockedBy() {
        return unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                .hasItems(IngredientUtils.getItemLike(input)));
    }

    public DryingRackBuilder unlockedBy(String name, Criterion<?> criterion) {
        this.criteria.put(name, criterion);
        return this;
    }

    public Item getResult() {
        return this.output.getItem();
    }

    @Override
    public Recipe<?> createRecipe() {
        return new DryingRackRecipe(input, ticks, output);
    }

    @Override
    public DryingRackBuilder getBuilder() {
        return this;
    }

    @Override
    public DryingRackBuilder input(Ingredient input) {
        this.input = input;
        return this;
    }

    public DryingRackBuilder delay(int ticks) {
        this.ticks = ticks;
        return this;
    }

    @Override
    public DryingRackBuilder output(ItemStack output) {
        this.output = output;
        return this;
    }

    @Override
    public ResourceLocation getSuffix(String extra) {
        return x.rl(zStatic.Blocks.drying_rack + "/" + x.path(this.output.getItem())
                + extra);
    }

}
