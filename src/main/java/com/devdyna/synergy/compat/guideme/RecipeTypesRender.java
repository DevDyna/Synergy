package com.devdyna.synergy.compat.guideme;

import com.devdyna.synergy.init.recipeTypes.type.DryableBricksRecipe;
import com.devdyna.synergy.init.recipeTypes.type.ItemUseRecipe;
import com.devdyna.synergy.init.types.zRecipeTypes;
import com.devdyna.synergy.utils.x;

import guideme.compiler.tags.RecipeTypeMappingSupplier;
import guideme.document.block.recipes.LytStandardRecipeBox;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeHolder;

public class RecipeTypesRender implements RecipeTypeMappingSupplier {

    @Override
    public void collect(RecipeTypeMappings mappings) {
        mappings.add(zRecipeTypes.DRYABLE_BRICKS.getType(), RecipeTypesRender::dryableBricks);
        mappings.add(zRecipeTypes.ITEM_USE.getType(), RecipeTypesRender::itemUse);
    }

    private static LytStandardRecipeBox<DryableBricksRecipe> dryableBricks(RecipeHolder<DryableBricksRecipe> r) {
        return LytStandardRecipeBox.builder()
                .icon(Items.BRICK)
                .title("Dryable Bricks")
                .input(x.ingredient(r.value().getInput()))
                .output(r.value().getOutput())
                .build(r);
    }

    private static LytStandardRecipeBox<ItemUseRecipe> itemUse(RecipeHolder<ItemUseRecipe> r) {
        return LytStandardRecipeBox.builder()
                .icon(Items.WOODEN_PICKAXE)
                .title("Item Use")
                .input(r.value().getInputItem())
                .output(x.item(r.value().getOutputState()))
                .build(r);
    }

}
