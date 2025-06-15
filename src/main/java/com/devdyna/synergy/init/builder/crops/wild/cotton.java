package com.devdyna.synergy.init.builder.crops.wild;

import com.devdyna.synergy.init.Material;
import com.devdyna.synergy.init.builder._core.crops.BaseWildCropBlock;
import com.devdyna.synergy.init.builder._core.crops.CropEntityInteraction;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

@SuppressWarnings("null")
public class cotton extends BaseWildCropBlock implements CropEntityInteraction {

    public cotton() {
        super(Material.cropProp);
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
