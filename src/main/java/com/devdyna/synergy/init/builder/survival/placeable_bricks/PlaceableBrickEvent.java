package com.devdyna.synergy.init.builder.survival.placeable_bricks;

import java.util.Optional;

import com.devdyna.synergy.api.recipes.inputs.MonoItemInput;
import com.devdyna.synergy.init.builder.survival.placeable_bricks.recipe.DryableBricksRecipe;
import com.devdyna.synergy.init.types.zRecipeTypes;

import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

public class PlaceableBrickEvent {

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void claybrickplacement(PlayerInteractEvent.RightClickBlock event) {
        var level = event.getLevel();
        var pos = event.getPos();
        var player = event.getEntity();
        var item = player.getMainHandItem();
        var dir = event.getFace();
        var block = level.getBlockState(pos.relative(dir));
        var hand = event.getHand();

        Optional<RecipeHolder<DryableBricksRecipe>> recipe = level.getRecipeManager()
                .getRecipeFor(zRecipeTypes.DRYABLE_BRICKS.getType(),
                        new MonoItemInput(item), level);

        if (recipe.isEmpty())
            return;

        var result = recipe.get().value().getBlock();

        if (!block.isEmpty() && !block.isAir())
            return;

        if (!Block.canSupportCenter(level, pos.relative(dir).below(), Direction.UP))
            return;

        if (!dir.equals(Direction.UP))
            return;

        if (!player.isCreative())
            item.shrink(1);

        player.swing(hand);

        var soundType = result.getSoundType(level, pos, player);
        level.playSound(
                null,
                pos,
                soundType.getPlaceSound(),
                SoundSource.BLOCKS,
                (soundType.getVolume() + 1.0F) / 2.0F,
                soundType.getPitch() * 0.8F);

        level.setBlockAndUpdate(pos.relative(dir),
                result
                        .setValue(PlaceableBrickBlock.DRIED, false)
                        .setValue(PlaceableBrickBlock.DRY_STAGE, 0)
                        .setValue(PlaceableBrickBlock.WET, PlaceableBrickBlock.getConditions(level, pos))
                        .setValue(PlaceableBrickBlock.FACING, player.getDirection()));

    }

}
