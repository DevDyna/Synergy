package com.devdyna.synergy.common.recipes.builders;

import static com.devdyna.synergy.Main.ID;

import java.util.LinkedHashMap;
import java.util.List;
import javax.annotation.Nullable;

import com.devdyna.synergy.api.recipes.builders.BaseRecipeBuilder;
import com.devdyna.synergy.api.recipes.builders.ListedOutputItemStack;
import com.devdyna.synergy.api.recipes.builders.SimpleInputItem;
import com.devdyna.synergy.api.utils.x;
import com.devdyna.synergy.common.recipes.type.CropResultRecipe;

import net.minecraft.advancements.Criterion;
import net.minecraft.core.HolderLookup.RegistryLookup;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;

@SuppressWarnings("null")
public class CropResultBuilder extends BaseRecipeBuilder
        implements SimpleInputItem<CropResultBuilder>, ListedOutputItemStack<CropResultBuilder> {

    private Ingredient input;
    private List<ItemStack> output;

    public CropResultBuilder(RegistryLookup<Item> p) {
        super(p);
        this.criteria = new LinkedHashMap<String, Criterion<?>>();
    }

    public static CropResultBuilder of(RegistryLookup<Item> p) {
        return new CropResultBuilder(p);
    }

    public CropResultBuilder input(Ingredient input) {
        this.input = input;
        return this;
    }

    public CropResultBuilder output(List<ItemStack> output) {
        this.output = output;
        return this;
    }

    public CropResultBuilder unlockedBy() {
        return unlockedBy(ID, unlock(input));
    }

    public CropResultBuilder unlockedBy(String name, Criterion<?> criterion) {
        this.criteria.put(name, criterion);
        return this;
    }

    public CropResultBuilder group(@Nullable String groupName) {
        return this;
    }

    public Item getResult() {
        return this.output.get(0).getItem();
    }

    @Override
    public String getSuffix(String extra) {
        return "jei/crop_result/" + x.path(output.getFirst())
                + extra;
    }

    @Override
    public Recipe<?> createRecipe() {
        return new CropResultRecipe(input, output);
    }

    @Override
    public CropResultBuilder getBuilder() {
        return this;
    }

}
