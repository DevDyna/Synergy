package com.devdyna.synergy.api.machine.recipe;

import static com.devdyna.synergy.Main.ID;

import java.util.Arrays;
import java.util.Map;

import javax.annotation.Nullable;

import com.devdyna.synergy.api.MachineType;
import com.devdyna.synergy.api.machine.BaseMachineBE;
import com.devdyna.synergy.api.machine.BaseMachineBlock;
import com.devdyna.synergy.api.machine.BaseMachineMenu;
import com.devdyna.synergy.api.recipes.builders.BaseRecipeBuilder;
import com.devdyna.synergy.api.recipes.builders.BiTypeOutput;
import com.devdyna.synergy.api.recipes.builders.SecondaryOutputItem;
import com.devdyna.synergy.api.recipes.builders.SimpleInputItem;
import com.devdyna.synergy.api.recipes.builders.SimpleOutputItem;
import com.devdyna.synergy.api.utils.x;
import com.devdyna.synergy.init.builder.industrial_machines.macerator.recipe.MaceratorRecipeBuilder;

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
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.registries.DeferredHolder;

@SuppressWarnings({ "unused", "null" })
public abstract class BaseMachineRecipeBuilder<T extends BaseMachineRecipeBuilder<T>> extends BaseRecipeBuilder
        implements SimpleInputItem<T>, SimpleOutputItem<T> {

    public abstract MachineType<? extends BaseMachineBlock, ? extends BaseMachineBE, ? extends BaseMachineMenu, ? extends BaseMachineRecipeType<? extends RecipeInput>> getMachine();

    protected int ticks = BaseMachineBE.DEFAULT_TICK_DURATION;
    protected int energy = BaseMachineBE.DEFAULT_FE_COST;
    protected Ingredient input;
    protected ItemStack output;
    protected ItemStack secondary = ItemStack.EMPTY;
    protected Ingredient catalyst = Ingredient.EMPTY;
    protected float chance;
    protected boolean consumeCatalyst = false;
    protected FluidStack fluid_output = FluidStack.EMPTY;

    public T input(Ingredient input) {
        this.input = input;
        return getBuilder();
    }

    public T output(ItemStack output) {
        this.output = output;
        return getBuilder();
    }

    /**
     * default value -> 60t
     */
    public T delay(int ticks) {
        this.ticks = ticks;
        return getBuilder();
    }

    /**
     * default value -> 1kfe | 1000fe
     */
    public T energy(int energy) {
        this.energy = energy;
        return getBuilder();
    }

    /**
     * secondary recipe output chance to success
     */
    public T chance(float chance) {
        this.chance = chance;
        return getBuilder();
    }

    public T unlockedBy() {
        return unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                .hasItems(Arrays.stream(this.input.getItems())
                        .map(ItemStack::getItem)
                        .toArray(Item[]::new)));
    }

    public T unlockedBy(String name, Criterion<?> criterion) {
        this.criteria.put(name, criterion);
        return getBuilder();
    }

    public T group(@Nullable String groupName) {
        return getBuilder();
    }

    public Item getResult() {
        return this.output.getItem();
    }

    @Override
    public ResourceLocation getSuffix(String extra) {

        if (this instanceof BiTypeOutput)
            if (output == null)
                return x.rl(getMachine().id() + "/" + x.path(fluid_output.getFluid())
                        + extra);

        return x.rl(getMachine().id() + "/" + x.path(output.getItem())
                + extra);
    }

    public abstract T getBuilder();

}
