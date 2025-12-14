package com.devdyna.synergy.common.recipes.builders;

import static com.devdyna.synergy.Main.ID;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.Nullable;

import com.devdyna.synergy.api.recipebuilders.BaseRecipeBuilder;
import com.devdyna.synergy.api.recipebuilders.SimpleInputItem;
import com.devdyna.synergy.api.recipebuilders.SimpleOutputItem;
import com.devdyna.synergy.api.utils.IngredientUtils;
import com.devdyna.synergy.api.utils.x;
import com.devdyna.synergy.common.recipes.type.DryableBricksRecipe;

import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.resources.ResourceLocation;
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
    private final Map<String, Criterion<?>> criteria;

    public DryableBricksBuilder() {
        this.criteria = new LinkedHashMap<String, Criterion<?>>();
    }

    public static DryableBricksBuilder of() {
        return new DryableBricksBuilder();
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
    public ResourceLocation getSuffix(String extra) {
        return x.rl("dryable_bricks/" + x.path(output)
                + extra);
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
