package com.devdyna.synergy.init.machine.core.recipe;

import static com.devdyna.synergy.Main.ID;

import java.util.Arrays;
import java.util.Map;

import javax.annotation.Nullable;

import com.devdyna.synergy.api.MachineType;
import com.devdyna.synergy.api.recipes.builders.BaseRecipeBuilder;
import com.devdyna.synergy.api.recipes.builders.SecondaryOutputItem;
import com.devdyna.synergy.api.recipes.builders.SimpleInputItem;
import com.devdyna.synergy.api.recipes.builders.SimpleOutputItem;
import com.devdyna.synergy.api.utils.x;
import com.devdyna.synergy.init.machine.core.BaseMachineBE;
import com.devdyna.synergy.init.machine.core.BaseMachineBlock;
import com.devdyna.synergy.init.machine.core.BaseMachineMenu;
import com.devdyna.synergy.init.machine.macerator.recipe.MaceratorRecipeBuilder;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeInput;
import net.neoforged.neoforge.registries.DeferredHolder;

@SuppressWarnings({ "unused", "null" })
public abstract class BaseMachineRecipeBuilder extends BaseRecipeBuilder
        implements SimpleInputItem<BaseMachineRecipeBuilder>, SimpleOutputItem<BaseMachineRecipeBuilder>,
        SecondaryOutputItem<BaseMachineRecipeBuilder> {

    public abstract MachineType<? extends BaseMachineBlock, ? extends BaseMachineBE, ? extends BaseMachineMenu, ? extends BaseMachineRecipeType<? extends RecipeInput>> getMachine();

    protected int ticks = 60;
    protected int energy = 10;
    protected Ingredient input;
    protected ItemStack output;
    protected ItemStack secondary = ItemStack.EMPTY;
    protected float chance;

    public BaseMachineRecipeBuilder input(Ingredient input) {
        this.input = input;
        return this;
    }

    public BaseMachineRecipeBuilder output(ItemStack output) {
        this.output = output;
        return this;
    }

    public BaseMachineRecipeBuilder secondary(ItemStack secondary, float chance) {
        this.secondary = secondary;
        this.chance = chance;
        return this;
    }

    /**
     * default value -> 60t
     */
    public BaseMachineRecipeBuilder delay(int ticks) {
        this.ticks = ticks;
        return this;
    }

    /**
     * default value -> 1kfe | 1000fe
     */
    public BaseMachineRecipeBuilder energy(int energy) {
        this.energy = energy;
        return this;
    }

    /**
     * secondary recipe output chance to success
     */
    public BaseMachineRecipeBuilder chance(float chance) {
        this.chance = chance;
        return this;
    }

    public BaseMachineRecipeBuilder unlockedBy() {
        return unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                .hasItems(Arrays.stream(this.input.getItems())
                        .map(ItemStack::getItem)
                        .toArray(Item[]::new)));
    }

    public BaseMachineRecipeBuilder unlockedBy(String name, Criterion<?> criterion) {
        this.criteria.put(name, criterion);
        return this;
    }

    public BaseMachineRecipeBuilder group(@Nullable String groupName) {
        return this;
    }

    public Item getResult() {
        return this.output.getItem();
    }

    @Override
    public BaseMachineRecipeBuilder getBuilder() {
        return this;
    }

    @Override
    public ResourceLocation getSuffix(String extra) {
        return x.rl(getMachine().id() + "/" + x.path(output.getItem())
                + extra);
    }

}
