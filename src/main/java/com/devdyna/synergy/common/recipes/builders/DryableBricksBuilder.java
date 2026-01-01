package com.devdyna.synergy.common.recipes.builders;

import static com.devdyna.synergy.Main.ID;

import java.util.LinkedHashMap;

import javax.annotation.Nullable;

import com.devdyna.synergy.api.recipes.builders.BaseRecipeBuilder;
import com.devdyna.synergy.api.recipes.builders.SimpleInputItem;
import com.devdyna.synergy.api.recipes.builders.SimpleOutputItem;
import com.devdyna.synergy.api.utils.IngredientUtils;
import com.devdyna.synergy.api.utils.x;
import com.devdyna.synergy.common.recipes.type.DryableBricksRecipe;

import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup.RegistryLookup;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

@SuppressWarnings("null")
public class DryableBricksBuilder extends BaseRecipeBuilder implements
        SimpleInputItem<DryableBricksBuilder>, SimpleOutputItem<DryableBricksBuilder> {

    private Ingredient input;
    private BlockState block;
    private ItemStack output;

    public DryableBricksBuilder(RegistryLookup<Item> p) {
        super(p);
        this.criteria = new LinkedHashMap<String, Criterion<?>>();
    }

    public static DryableBricksBuilder of(RegistryLookup<Item> p) {
        return new DryableBricksBuilder(p);
    }

    /**
     * Block reference
     */
    public DryableBricksBuilder block(BlockState block) {
        this.block = block;
        return this;
    }

    @Override
    public DryableBricksBuilder input(Ingredient input) {
        this.input = input;
        return this;
    }

    public DryableBricksBuilder block(Block block) {
        return block(block.defaultBlockState());
    }

    public DryableBricksBuilder output(ItemStack output) {
        this.output = output;
        return this;
    }

    public DryableBricksBuilder unlockedBy() {
        return unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                .hasItems(IngredientUtils.getItemLike(input)));
    }

    public DryableBricksBuilder unlockedBy(String name, Criterion<?> criterion) {
        this.criteria.put(name, criterion);
        return this;
    }

    public DryableBricksBuilder group(@Nullable String groupName) {
        return this;
    }

    public Item getResult() {
        return this.output.getItem();
    }

    @Override
    public String getSuffix(String extra) {
        return "dryable_bricks/" + x.path(output)
                + extra;
    }

    @Override
    public Recipe<?> createRecipe() {
        return new DryableBricksRecipe(input, block, output);
    }

    @Override
    public DryableBricksBuilder getBuilder() {
        return this;
    }

}
