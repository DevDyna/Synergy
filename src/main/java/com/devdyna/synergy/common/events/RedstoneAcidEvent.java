package com.devdyna.synergy.common.events;

import com.devdyna.synergy.Common;
import com.devdyna.synergy.api.utils.LevelUtil;
import com.devdyna.synergy.api.utils.x;
import com.devdyna.synergy.init.types.zItemTag;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.common.DataMapHooks;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.items.ItemHandlerHelper;

@SuppressWarnings("null")
public class RedstoneAcidEvent {

    @SubscribeEvent
    public static void redstoneAcidOxidition(PlayerInteractEvent.RightClickBlock event) {
        var pos = event.getPos();
        var level = event.getLevel();
        var player = event.getEntity();
        var item = event.getItemStack();
        var hand = event.getHand();
        var state = level.getBlockState(pos);

        if (!item.is(zItemTag.OXIDIZER))
            return;

        var oxidation = DataMapHooks.getNextOxidizedStage(state.getBlock());

        if (oxidation == null || state.is(oxidation))
            return;

        if (Common.DISABLE_REDSTONE_ACID_EVENT.get())
            return;

        player.swing(hand);

        level.setBlockAndUpdate(pos, oxidation.withPropertiesOf(state));

        if (!player.isCreative()) {
            item.shrink(1);
            ItemHandlerHelper.giveItemToPlayer(player, x.item(Items.GLASS_BOTTLE));
        }
        
        if (level.isClientSide())
        LevelUtil.addCopperWaxingParticle(level, pos, ParticleTypes.COMPOSTER);
        level.playLocalSound(pos, SoundEvents.AXE_SCRAPE, SoundSource.BLOCKS, 1.0F, 1.0F, false);

    }
}
