package com.devdyna.synergy.init.builder.laser;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.shapes.VoxelShape;

public interface IBlockLaser {

    default boolean isPathfindable() {
        return false;
    }

    default VoxelShape getShape() {
        return Block.box(5, 0, 5, 11, 6, 11);
    }

   static Properties getProperties = Properties.of().noOcclusion()
                .destroyTime(1.0f)
                .sound(SoundType.METAL)
                .mapColor(MapColor.METAL);
}
