package com.devdyna.synergy.init.builder;

import com.devdyna.synergy.api.utils.LevelUtil;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

@SuppressWarnings("null")
public class Healer extends Block {

    public Healer(Properties properties) {
        super(properties);
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {

        if (entity instanceof LivingEntity livingEntity) {
            if (livingEntity.getMaxHealth() > livingEntity.getHealth() && entity.isAlive()) {
                livingEntity.heal(1);
                if (!level.isClientSide())
                    LevelUtil.addParticle(ParticleTypes.HEART, (ServerLevel) level, pos.above(), true, 3);
                if (entity.isOnFire())
                    entity.clearFire();
            }
        }

    }

    // @Override
    // public void appendHoverText(ItemStack i, TooltipContext c, List<Component> t,
    //         TooltipFlag f) {
    //     t.add(Component.translatable(Main.ID + "." + zStatic.Blocks.healer));
    // }

}
