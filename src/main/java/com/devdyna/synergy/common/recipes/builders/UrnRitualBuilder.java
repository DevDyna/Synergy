package com.devdyna.synergy.common.recipes.builders;

import static com.devdyna.synergy.Main.ID;

import java.util.*;
import javax.annotation.Nullable;

import com.devdyna.synergy.api.recipes.builders.BaseRecipeBuilder;
import com.devdyna.synergy.api.recipes.builders.InputIngredientItem;
import com.devdyna.synergy.api.recipes.builders.SimpleOutputItem;
import com.devdyna.synergy.api.utils.IngredientUtils;
import com.devdyna.synergy.api.utils.x;
import com.devdyna.synergy.common.recipes.type.UrnRitualRecipe;

import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.RegistryLookup;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;

@SuppressWarnings({ "null" })
public class UrnRitualBuilder extends BaseRecipeBuilder
        implements SimpleOutputItem<UrnRitualBuilder>, InputIngredientItem<UrnRitualBuilder> {

    private List<Ingredient> inputList = new ArrayList<>();
    private ItemStack output;

    private UrnRitualBuilder(RegistryLookup<Item> p) {
        super(p);
        this.criteria = new LinkedHashMap<String, Criterion<?>>();
    }

    public static UrnRitualBuilder of(RegistryLookup<Item> p) {
        return new UrnRitualBuilder(p);
    }

    public UrnRitualBuilder unlockedBy() {
        return unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                .hasItems(IngredientUtils.getItemLikes(inputList)));
    }

    public UrnRitualBuilder unlockedBy(String name, Criterion<?> criterion) {
        this.criteria.put(name, criterion);
        return this;
    }

    public Item getResult() {
        return this.output.getItem();
    }

    @Override
    public Recipe<?> createRecipe() {
        return new UrnRitualRecipe(inputList, this.output);
    }

    @Override
    public UrnRitualBuilder getBuilder() {
        return this;
    }

    @Override
    public UrnRitualBuilder group(@Nullable String groupName) {
        return this;
    }

    @Override
    public UrnRitualBuilder add(Ingredient input) {
        inputList.add(input);
        return this;
    }

    @Override
    public UrnRitualBuilder output(ItemStack output) {
        this.output = output;
        return this;
    }

    @Override
        public String getSuffix(String extra) {
       return "urn_ritual/" + x.path(this.output.getItem())
                + extra;
        }

}
