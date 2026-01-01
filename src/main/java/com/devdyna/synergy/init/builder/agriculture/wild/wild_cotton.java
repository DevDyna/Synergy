package com.devdyna.synergy.init.builder.agriculture.wild;

import com.devdyna.synergy.api.plants.CropEntityInteraction;
import com.devdyna.synergy.api.plants.builder.BaseWildCropBlock;
import com.devdyna.synergy.init.types.zBlockTag;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.Vec3;

@SuppressWarnings("null")
public class wild_cotton extends BaseWildCropBlock implements CropEntityInteraction {

    public wild_cotton() {
        super(Properties.of().mapColor(MapColor.WOOL));
    }

    @Override
    public TagKey<Block> getSpawnFilter() {
        return zBlockTag.CAN_SUSTAIN_COTTON;
    }

    protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        getEntityInside(state, level, pos, entity);
    }

    @Override
    public Vec3 speedFactor() {
        return new Vec3(0.9, 0.8, 0.9);
    }

    @Override
    public boolean HurtWhenInside() {
        return false;
    }

    @Override
    public boolean HurtWhenStep() {
        return false;
    }

    @Override
    public boolean StuckWhenInside() {
        return true;
    }

}
