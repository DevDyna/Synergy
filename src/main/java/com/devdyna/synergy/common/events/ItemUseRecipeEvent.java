package com.devdyna.synergy.common.events;

import java.util.Optional;

import com.devdyna.synergy.common.recipes.input.UseItemInput;
import com.devdyna.synergy.common.recipes.type.ItemUseRecipe;
import com.devdyna.synergy.config.Common;
import com.devdyna.synergy.init.types.zRecipeTypes;

import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

public class ItemUseRecipeEvent {

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void potAction(PlayerInteractEvent.RightClickBlock event) {
        var pos = event.getPos();
        var level = event.getLevel();
        var player = event.getEntity();
        var item = event.getItemStack();
        var hand = event.getHand();
        var state = level.getBlockState(pos);

        Optional<RecipeHolder<ItemUseRecipe>> recipe = level.getRecipeManager()
                .getRecipeFor(zRecipeTypes.ITEM_USE.getType(),
                        new UseItemInput(state, item), level);

        if (!recipe.isEmpty()) {

            var rcp = recipe.get().value();

            if (rcp.canBeDisabled() && Common.DISABLE_ITEM_USE_RECIPE.get()) // disabled via config
                return;

            if (rcp.isRenderOnly()) // only jei , no event
                return;

            if (!player.isCreative()) {
                item.shrink(1);
            }
            var output = rcp.getOutputState();

            try {
                for (int i = 0; i < 8; i++)
                    level.addParticle(new BlockParticleOption(ParticleTypes.BLOCK, output),
                            pos.getX() + 0.25, pos.getY() + 0.25, pos.getZ() + 0.25,
                            1, 1, 1);

            } catch (Exception e) {

            }

            try {
                level.playSound(null, pos.getX(), pos.getY(), pos.getZ(),
                        output.getSoundType(level, pos, player).getBreakSound(),
                        SoundSource.BLOCKS,
                        1.0f,
                        1.0f);
            } catch (Exception e) {

            }
            player.swing(hand);
            level.setBlockAndUpdate(pos, output);
            event.isCanceled();
            event.setCanceled(true);

        }
    }

}
