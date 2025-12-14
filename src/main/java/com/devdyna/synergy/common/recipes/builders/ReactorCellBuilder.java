package com.devdyna.synergy.common.recipes.builders;

import static com.devdyna.synergy.Main.ID;

import java.util.LinkedHashMap;
import javax.annotation.Nullable;

import com.devdyna.synergy.api.recipebuilders.BaseRecipeBuilder;
import com.devdyna.synergy.api.recipebuilders.SimpleInputItem;
import com.devdyna.synergy.api.recipebuilders.SimpleOutputItem;
import com.devdyna.synergy.api.utils.IngredientUtils;
import com.devdyna.synergy.common.recipes.type.FuelCellRecipe;

import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;

@SuppressWarnings({ "null" })
public class ReactorCellBuilder extends BaseRecipeBuilder
        implements SimpleOutputItem<ReactorCellBuilder> , SimpleInputItem<ReactorCellBuilder> {

    private Ingredient input;
    private ItemStack output;
    private int duration;
    private double heat;
    private int fe;

    private ReactorCellBuilder() {
        this.criteria = new LinkedHashMap<String, Criterion<?>>();
    }

    public static ReactorCellBuilder of() {
        return new ReactorCellBuilder();
    }

    public ReactorCellBuilder input(Ingredient input) {
        this.input = input;
        return this;
    }

    public ReactorCellBuilder output(ItemStack output) {
        this.output = output;
        return this;
    }

    public ReactorCellBuilder energy(int fe) {
        this.fe = fe;
        return this;
    }

    public ReactorCellBuilder duration(int ticks) {
        this.duration = ticks;
        return this;
    }

    public ReactorCellBuilder heat(double heat) {
        this.heat = heat;
        return this;
    }

    public ReactorCellBuilder unlockedBy() {
        return unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                .hasItems(IngredientUtils.getItemLike(input)));
    }

    public ReactorCellBuilder unlockedBy(String name, Criterion<?> criterion) {
        this.criteria.put(name, criterion);
        return this;
    }

    public ReactorCellBuilder group(@Nullable String groupName) {
        return this;
    }

    public Item getResult() {
        return this.output.getItem();
    }

    @Override
    public ReactorCellBuilder getBuilder() {
        return this;
    }

    @Override
    public Recipe<?> createRecipe() {
        return new FuelCellRecipe(input, output, duration, fe, heat);
    }
}
