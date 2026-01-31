package com.devdyna.synergy.compat.guideme;

import com.devdyna.synergy.api.utils.x;
import com.devdyna.synergy.common.recipes.type.ItemUseRecipe;
import com.devdyna.synergy.init.builder.survival.placeable_bricks.recipe.DryableBricksRecipe;
import com.devdyna.synergy.init.types.zRecipeTypes;

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
                .input(r.value().getInput())
                .output(r.value().getOutput())
                .build(r);
    }

    private static LytStandardRecipeBox<ItemUseRecipe> itemUse(RecipeHolder<ItemUseRecipe> r) {
        return LytStandardRecipeBox.builder()
                .icon(Items.GLASS_BOTTLE)
                .title("Item Use")
                .input(r.value().getInputItem())
                .output(x.item(r.value().getOutputState()))
                .build(r);
    }

}
