package com.devdyna.synergy.init.builder.survival.casting_table.recipe;

import static com.devdyna.synergy.Main.ID;

import java.util.*;
import javax.annotation.Nullable;

import com.devdyna.synergy.api.recipes.builders.*;
import com.devdyna.synergy.api.recipes.builders.api.BaseRecipeBuilder;
import com.devdyna.synergy.api.utils.x;

import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.neoforged.neoforge.fluids.crafting.SizedFluidIngredient;

@SuppressWarnings({ "null" })
public class CastingTableBuilder extends BaseRecipeBuilder
        implements ItemAttach.Output.SimpleOutputItem<CastingTableBuilder>,
        FluidAttach.Input.SizedFluid<CastingTableBuilder>, ItemAttach.Input.NoItemCount<CastingTableBuilder> , ItemAttach.Input.OptionalConsume<CastingTableBuilder> {

    private SizedFluidIngredient fluid;
    private Ingredient input = Ingredient.EMPTY;
    private int ticks = 40;
    private ItemStack output;
    private boolean consume = false;

    private CastingTableBuilder() {
        this.criteria = new LinkedHashMap<String, Criterion<?>>();
    }

    public static CastingTableBuilder of() {
        return new CastingTableBuilder();
    }

    public CastingTableBuilder unlockedBy() {
        return unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                .hasItems(x.getFluids(this.fluid).getFirst().getFluid().getBucket()));
    }

    public CastingTableBuilder unlockedBy(String name, Criterion<?> criterion) {
        this.criteria.put(name, criterion);
        return this;
    }

    public Item getResult() {
        return this.output.getItem();
    }

    @Override
    public Recipe<?> createRecipe() {
        return new CastingTableRecipe(fluid, input,consume, ticks, output);
    }

    @Override
    public CastingTableBuilder getBuilder() {
        return this;
    }

    @Override
    public CastingTableBuilder group(@Nullable String groupName) {
        return this;
    }

    @Override
    public CastingTableBuilder fluid(SizedFluidIngredient fluid) {
        this.fluid = fluid;
        return this;
    }

    public CastingTableBuilder delay(int ticks) {
        this.ticks = ticks;
        return this;
    }

    @Override
    public CastingTableBuilder output(ItemStack output) {
        this.output = output;
        return this;
    }

    @Override
    public CastingTableBuilder input(Ingredient input) {
        this.input = input;
        return getBuilder();
    }

    @Override
    public ResourceLocation getSuffix(String extra) {
        return x.rl("casting/" + x.path(this.output.getItem()) + extra);
    }

    @Override
    public CastingTableBuilder consumeItemInput() {
       this.consume = true;
        return this;
    }

}
