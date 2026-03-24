package com.devdyna.synergy.common.recipes.copper_oxidation;

import static com.devdyna.synergy.Main.ID;

import java.util.LinkedHashMap;
import com.devdyna.synergy.api.recipes.builders.api.BaseRecipeBuilder;
import com.devdyna.synergy.api.utils.x;

import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Recipe;

@SuppressWarnings("null")
public class CopperOxidationBuilder extends BaseRecipeBuilder {

    private OxidationStatus type;

    public CopperOxidationBuilder() {
        this.criteria = new LinkedHashMap<String, Criterion<?>>();
    }

    public static void build(RecipeOutput c) {
        for (OxidationStatus ox : OxidationStatus.values())
            new CopperOxidationBuilder()
                    .setType(ox)
                    .unlockedBy()
                    .save(c);
    }

    public CopperOxidationBuilder unlockedBy() {
        return unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                .hasItems(Items.CRAFTING_TABLE));
    }

    public CopperOxidationBuilder unlockedBy(String name, Criterion<?> criterion) {
        this.criteria.put(name, criterion);
        return this;
    }

    public CopperOxidationBuilder setType(OxidationStatus type) {
        this.type = type;
        return this;
    }

    @Override
    public ResourceLocation getSuffix(String extra) {
        return x.rl("copper_oxidation/" + type.name().toLowerCase() + extra);
    }

    @Override
    public Recipe<?> createRecipe() {
        return new CopperOxidationRecipe(type);
    }

}
