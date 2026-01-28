package com.devdyna.synergy.common.recipes.builders;

import static com.devdyna.synergy.Main.ID;

import java.util.*;
import javax.annotation.Nullable;

import com.devdyna.synergy.api.recipes.builders.BaseRecipeBuilder;
import com.devdyna.synergy.api.recipes.builders.SimpleOutputItem;
import com.devdyna.synergy.api.recipes.builders.legacy.SimpleInputItem;
import com.devdyna.synergy.api.utils.IngredientUtils;
import com.devdyna.synergy.api.utils.x;
import com.devdyna.synergy.common.recipes.type.VoidBoxInfusionRecipe;

import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;

@SuppressWarnings({ "null" })
public class VoidBoxInfusionBuilder extends BaseRecipeBuilder
        implements SimpleOutputItem<VoidBoxInfusionBuilder>, SimpleInputItem<VoidBoxInfusionBuilder> {

    private Ingredient input;
    private ItemStack output;

    private VoidBoxInfusionBuilder() {
        this.criteria = new LinkedHashMap<String, Criterion<?>>();
    }

    public static VoidBoxInfusionBuilder of() {
        return new VoidBoxInfusionBuilder();
    }

    public VoidBoxInfusionBuilder unlockedBy() {
        return unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                .hasItems(IngredientUtils.getItemLike(input)));
    }

    public VoidBoxInfusionBuilder unlockedBy(String name, Criterion<?> criterion) {
        this.criteria.put(name, criterion);
        return this;
    }

    public Item getResult() {
        return this.output.getItem();
    }

    @Override
    public Recipe<?> createRecipe() {
        return new VoidBoxInfusionRecipe(input, output);
    }

    @Override
    public VoidBoxInfusionBuilder getBuilder() {
        return this;
    }

    @Override
    public VoidBoxInfusionBuilder group(@Nullable String groupName) {
        return this;
    }

      @Override
    public VoidBoxInfusionBuilder input(Ingredient input) {
        this.input = input;
        return this;
    }

    @Override
    public VoidBoxInfusionBuilder output(ItemStack output) {
        this.output = output;
        return this;
    }

    @Override
        public ResourceLocation getSuffix(String extra) {
       return x.rl("void_box_infusion/" + x.path(this.output.getItem())
                + extra);
        }

  

}
