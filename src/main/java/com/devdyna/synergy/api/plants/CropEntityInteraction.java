package com.devdyna.synergy.api.plants;

import java.util.List;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public interface CropEntityInteraction {

   static List<EntityType<?>> EntityTypeSafe = List.of(EntityType.FOX, EntityType.BEE);

   /**
    * By default it dont stuck entity inside
    * <br>
    * <br>
    * To make that you NEED to concat canStuckEntityWhen()
    */
   boolean HurtWhenInside();

   boolean HurtWhenStep();

   boolean StuckWhenInside();

   default boolean stuckCondition(Entity entity) {
      return entity instanceof LivingEntity && !EntityTypeSafe.contains(entity.getType());
   }

   default boolean hurtCondition(Entity entity, Level level) {
      return !level.isClientSide()
            && (entity.xOld != entity.getX() || entity.zOld != entity.getZ())
            && Math.abs(entity.getX() - entity.xOld) >= hurt
            || Math.abs(entity.getZ() - entity.zOld) >= hurt;
   }

   default Vec3 speedFactor() {
      return new Vec3(stuck, 0.75, stuck);
   }

   static double stuck = 0.800000011920929;
   static double hurt = 0.003000000026077032;

   default void getEntityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
      if (StuckWhenInside())
         entity.makeStuckInBlock(state, speedFactor());
      // see net.minecraft.world.level.block.SweetBerryBushBlock
      if (HurtWhenInside())
         entity.hurtServer((ServerLevel) level, level.damageSources().sweetBerryBush(), 1.0F);
   }

   default void getStepEntityOn(Level level, BlockPos pos, BlockState state, Entity entity) {
      if (HurtWhenStep())
         entity.hurtServer((ServerLevel) level, level.damageSources().sweetBerryBush(), 0.1F);
   }

}
