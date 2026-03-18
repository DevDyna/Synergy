package com.devdyna.synergy.init.builder.magic.urn.recipe;

import static com.devdyna.synergy.Main.ID;

import java.util.*;
import com.devdyna.synergy.api.recipes.builders.*;
import com.devdyna.synergy.api.recipes.builders.api.BaseRecipeBuilder;
import com.devdyna.synergy.api.utils.IngredientUtils;
import com.devdyna.synergy.api.utils.x;

import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.neoforged.neoforge.common.crafting.SizedIngredient;

@SuppressWarnings({ "null" })
public class UrnRitualBuilder extends BaseRecipeBuilder
        implements ItemAttach.Output.SimpleOutputItem<UrnRitualBuilder>, ItemAttach.Input.ListedItemCount<UrnRitualBuilder> {

    private List<SizedIngredient> inputList = new ArrayList<>();
    private ItemStack output;

    private UrnRitualBuilder() {
        this.criteria = new LinkedHashMap<String, Criterion<?>>();
    }

    public static UrnRitualBuilder of() {
        return new UrnRitualBuilder();
    }

    public UrnRitualBuilder unlockedBy() {
        return unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                .hasItems(IngredientUtils.getItemLikesSized(inputList)));
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
        return new UrnRitualRecipe(inputList, output);
    }

    @Override
    public UrnRitualBuilder getBuilder() {
        return this;
    }

    @Override
    public UrnRitualBuilder add(SizedIngredient input) {
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
