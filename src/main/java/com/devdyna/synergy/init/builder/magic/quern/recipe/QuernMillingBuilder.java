package com.devdyna.synergy.init.builder.magic.quern.recipe;

import static com.devdyna.synergy.Main.ID;

import java.util.LinkedHashMap;
import javax.annotation.Nullable;

import com.devdyna.synergy.api.recipes.builders.*;
import com.devdyna.synergy.api.recipes.builders.api.BaseRecipeBuilder;
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
public class QuernMillingBuilder extends BaseRecipeBuilder
        implements ItemAttach.Input.NoItemCount<QuernMillingBuilder>, ItemAttach.Output.SimpleOutputItem<QuernMillingBuilder> {

    private Ingredient input;
    private int tick;
    private ItemStack output;

    private QuernMillingBuilder() {
        this.tick = 60;
        this.criteria = new LinkedHashMap<String, Criterion<?>>();
    }

    public static QuernMillingBuilder of() {
        return new QuernMillingBuilder();
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
    public ResourceLocation getSuffix(String extra) {
        return x.rl("quern/" + x.path(output.getItem())
                + extra);
    }
}
