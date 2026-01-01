package com.devdyna.synergy.common.recipes.builders;

import static com.devdyna.synergy.Main.ID;

import java.util.LinkedHashMap;
import javax.annotation.Nullable;

import com.devdyna.synergy.api.recipes.builders.BaseRecipeBuilder;
import com.devdyna.synergy.api.recipes.builders.SimpleInputItem;
import com.devdyna.synergy.api.recipes.builders.SimpleOutputItem;
import com.devdyna.synergy.api.utils.IngredientUtils;
import com.devdyna.synergy.api.utils.x;
import com.devdyna.synergy.common.recipes.type.QuernMillingRecipe;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup.RegistryLookup;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;

@SuppressWarnings({ "null" })
public class QuernMillingBuilder extends BaseRecipeBuilder
        implements SimpleInputItem<QuernMillingBuilder>, SimpleOutputItem<QuernMillingBuilder> {

    private Ingredient input;
    private int tick;
    private ItemStack output;

    private QuernMillingBuilder(RegistryLookup<Item> p) {
        super(p);
        this.tick = 60;
        this.criteria = new LinkedHashMap<String, Criterion<?>>();
    }

    public static QuernMillingBuilder of(RegistryLookup<Item> p) {
        return new QuernMillingBuilder(p);
    }

    public QuernMillingBuilder input(Ingredient input) {
        this.input = input;
        return this;
    }

    public QuernMillingBuilder output(ItemStack output) {
        this.output = output;
        return this;
    }

    public QuernMillingBuilder delay(int tick) {
        this.tick = tick;
        return this;
    }

    public QuernMillingBuilder unlockedBy() {
        return unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                .hasItems(IngredientUtils.getItemLike(input)));
    }

    public QuernMillingBuilder unlockedBy(String name, Criterion<?> criterion) {
        this.criteria.put(name, criterion);
        return this;
    }

    public QuernMillingBuilder group(@Nullable String groupName) {
        return this;
    }

    public Item getResult() {
        return this.output.getItem();
    }

    @Override
    public Recipe<?> createRecipe() {
        return new QuernMillingRecipe(input, output, tick);
    }

    @Override
    public QuernMillingBuilder getBuilder() {
        return this;
    }

    @Override
    public String getSuffix(String extra) {
        return "quern/" + x.path(output.getItem())
                + extra;
    }
}
