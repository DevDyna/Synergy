package com.devdyna.synergy.common.recipes.builders;

import static com.devdyna.synergy.Main.ID;

import java.util.*;
import javax.annotation.Nullable;

import com.devdyna.synergy.api.recipebuilders.*;
import com.devdyna.synergy.api.utils.IngredientUtils;
import com.devdyna.synergy.api.utils.x;
import com.devdyna.synergy.common.recipes.type.UrnRitualRecipe;

import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;

@SuppressWarnings({ "null" })
public class UrnRitualBuilder extends BaseRecipeBuilder
        implements SimpleOutputItem<UrnRitualBuilder>, ListedInputIngredient<UrnRitualBuilder> {

    private List<Ingredient> inputList = new ArrayList<>();
    private ItemStack output;

    private UrnRitualBuilder() {
        this.criteria = new LinkedHashMap<String, Criterion<?>>();
    }

    public static UrnRitualBuilder of() {
        return new UrnRitualBuilder();
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
        public ResourceLocation getSuffix(String extra) {
       return x.rl("urn_ritual/" + x.path(this.output.getItem())
                + extra);
        }

}
