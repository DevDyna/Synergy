package com.devdyna.synergy.init.builder;

import com.mojang.serialization.MapCodec;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.material.MapColor;

public class MachineFrame extends DirectionalBlock {

    public MachineFrame(Properties p) {
        super(p.mapColor((s) -> {
            return switch (s.getValue(DirectionalBlock.FACING)) {
                case Direction.DOWN -> MapColor.TERRACOTTA_LIGHT_GRAY;
                case Direction.UP -> MapColor.COLOR_GRAY;
                case Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST -> MapColor.TERRACOTTA_GRAY;
                default -> null;
            };
        }));
    }

    @Override
    protected MapCodec<? extends DirectionalBlock> codec() {
        return simpleCodec((p) -> new MachineFrame(p));
    }

}
