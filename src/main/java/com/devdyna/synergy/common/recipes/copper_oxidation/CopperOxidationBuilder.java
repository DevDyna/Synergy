package com.devdyna.synergy.common.recipes.copper_oxidation;

import static com.devdyna.synergy.Main.ID;

import java.util.LinkedHashMap;
import com.devdyna.synergy.api.recipes.builders.api.BaseRecipeBuilder;
import com.devdyna.synergy.api.utils.x;

import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;

@SuppressWarnings("null")
public class CopperOxidationBuilder extends BaseRecipeBuilder {

    private OxidationStatus type;
    private Ingredient catalyst;

    public CopperOxidationBuilder() {
        this.criteria = new LinkedHashMap<String, Criterion<?>>();
    }

    public static CopperOxidationBuilder of() {
        return new CopperOxidationBuilder();

    }

    public CopperOxidationBuilder unlockedBy() {
        return unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                .hasItems(Items.COPPER_INGOT));
    }

    public CopperOxidationBuilder unlockedBy(String name, Criterion<?> criterion) {
        this.criteria.put(name, criterion);
        return this;
    }

    public CopperOxidationBuilder type(OxidationStatus type) {
        this.type = type;
        return this;
    }

    public CopperOxidationBuilder catalyst(Ingredient catalyst) {
        this.catalyst = catalyst;
        return this;
    }
    public CopperOxidationBuilder catalyst(TagKey<Item> catalyst) {
        return catalyst(x.ingredient(catalyst));
    }

    @Override
    public ResourceLocation getSuffix(String extra) {
        return x.rl("copper_oxidation/" + type.name().toLowerCase() + extra);
    }

    @Override
    public Recipe<?> createRecipe() {
        return new CopperOxidationRecipe(type, catalyst);
    }

}
