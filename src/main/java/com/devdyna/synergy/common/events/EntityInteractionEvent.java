package com.devdyna.synergy.common.events;

import com.devdyna.synergy.api.utils.LevelUtil;
import com.devdyna.synergy.config.Common;
import com.devdyna.synergy.init.types.zEntityTag;
import com.devdyna.synergy.init.types.zItemTag;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.AgeableMob;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

public class EntityInteractionEvent {

    @SubscribeEvent
    public static void removeBabyGrow(PlayerInteractEvent.EntityInteract event) {
        var player = event.getEntity();
        var item = event.getItemStack();
        var hand = event.getHand();
        var target = event.getTarget();
        var level = event.getLevel();
        var pos = event.getPos();

        if(Common.DISABLE_REMOVE_BABY_GROW_EVENT.get()) return;

        if (item.is(zItemTag.REMOVE_ENTITY_GROWING) && target instanceof AgeableMob mob && mob.isBaby()
                && !target.getType().is(zEntityTag.DONT_LIKE_JAY_Z)) {
            if (!player.isCreative())
                item.shrink(1);
            level.playSound(player, target.blockPosition(), SoundEvents.HONEYCOMB_WAX_ON, SoundSource.AMBIENT, 1f, 1f);
            player.swing(hand);
            mob.setAge(Integer.MIN_VALUE);

            if (!level.isClientSide())
                LevelUtil.addDustParticle(255, 0, 0, (ServerLevel) level, pos, true, 16);
        }

    }

    @SubscribeEvent
    public static void readdBabyGrow(PlayerInteractEvent.EntityInteract event) {
        var player = event.getEntity();
        var item = event.getItemStack();
        var hand = event.getHand();
        var target = event.getTarget();
        var level = event.getLevel();
        var pos = event.getPos();

        if(Common.DISABLE_READD_BABY_GROW_EVENT.get()) return;

        if (item.is(zItemTag.ADD_ENTITY_GROWING) && target instanceof AgeableMob mob && mob.isBaby()
                && mob.getAge() < -100_000) {
            if (!player.isCreative())
                item.shrink(1);

            level.playSound(player, target.blockPosition(), SoundEvents.AXE_WAX_OFF, SoundSource.AMBIENT, 1f, 1f);
            player.swing(hand);

            mob.setAge(-24_000);

            if (!level.isClientSide())
                LevelUtil.addDustParticle(255, 255, 0, (ServerLevel) level, pos, true, 16);

        }

    }

}
