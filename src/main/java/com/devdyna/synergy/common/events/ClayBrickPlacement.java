package com.devdyna.synergy.common.events;

import java.util.Optional;

import com.devdyna.synergy.common.recipes.input.MonoItemInput;
import com.devdyna.synergy.common.recipes.type.DryableBricksRecipe;
import com.devdyna.synergy.init.builder.DryableBricks;
import com.devdyna.synergy.init.types.zRecipeTypes;

import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

public class ClayBrickPlacement {

    @SubscribeEvent
    public static void claybrickplacement(PlayerInteractEvent.RightClickBlock event) {
        var level = event.getLevel();
        var pos = event.getPos();
        var player = event.getEntity();
        var item = player.getMainHandItem();
        var dir = event.getFace();
        var block = level.getBlockState(pos.relative(dir));
        var hand = event.getHand();

        Optional<RecipeHolder<DryableBricksRecipe>> recipe = level.getServer().getRecipeManager()
                .getRecipeFor(zRecipeTypes.DRYABLE_BRICKS.getType(),
                        new MonoItemInput(item), level);

        if (recipe.isEmpty())
            return;

        var result = recipe.get().value().getBlock();

        if (!block.isEmpty() && !block.isAir())
            return;

        if (!Block.canSupportCenter(level, pos.relative(dir).below(), Direction.UP))
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
                        .setValue(DryableBricks.DRIED, false)
                        .setValue(DryableBricks.DRY_STAGE, 0)
                        .setValue(DryableBricks.WET, DryableBricks.getConditions(level, pos))
                        .setValue(DryableBricks.FACING, player.getDirection()));

    }

}
