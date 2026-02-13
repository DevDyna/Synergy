package com.devdyna.synergy.api.beLogic;

import java.util.*;
import com.devdyna.synergy.api.utils.Range;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DirectionProperty;

public interface AreaOfEffect {

    public enum AreaType {
        CUSTOM(),
        MIDDLE(),
        SIDE();

        public boolean isCustom() {
            return this.equals(CUSTOM);
        }

        public boolean is(AreaType type) {
            return this.equals(type);
        }

    }

    // BE default
    BlockPos getBlockPos();

    // BE default
    BlockState getBlockState();

    AreaType getAreaType();

    int getHeight();

    int getWidth();

    List<BlockPos> getArea();

    Level getLevel();

    default boolean editalbe() {
        return false;
    }

    default Range getHeigthLimits() {
        return Range.of(1, getHeight());
    }

    default Range getWidthLimits() {
        return Range.of(1, getWidth());
    }

    default boolean isAreaNull() {
        return getArea() == null;
    }

    default List<BlockPos> getBasicArea(BlockPos start, BlockPos end) {
        return BlockPos
                .betweenClosedStream(start, end)
                .map(BlockPos::immutable)
                .toList();

    }

    default int getCenterXZ() {
        return getWidth() / 2;
    }

    default int getCenterY() {
        return getHeight() / 2;
    }

    /**
     * return an AOE based on a controller block on middle of it
     */
    default List<BlockPos> getCentredPosArea() {
        return getCentredPosArea(getBlockPos());
    }

    /**
     * return an AOE based on a controller block on middle of it
     */
    default List<BlockPos> getCentredPosArea(BlockPos centre) {

        var start = centre.offset(-getCenterXZ(), 0, -getCenterXZ());
        var end = centre.offset(getCenterXZ(), getHeight() - 1, getCenterXZ());

        return getBasicArea(start, end);
    }

    /**
     * return an AOE based on a controller block and directional face
     */
    default List<BlockPos> getDirectionalArea(DirectionProperty p) {
        return getDirectionalArea(getBlockPos(), getBlockState().getValue(p));
    }

    /**
     * return an AOE based on a controller block and directional face
     */
    default List<BlockPos> getDirectionalArea(Direction face) {
        return getDirectionalArea(getBlockPos(), face);
    }

    /**
     * return an AOE based on a controller block and directional face
     */
    default List<BlockPos> getDirectionalArea(BlockPos origin, Direction face) {

        BlockPos start = origin.relative(face);
        BlockPos end = origin.relative(face);

        switch (face) {

            case NORTH -> {
                start = start.offset(-getCenterXZ(), 0, -(getWidth() - 1));
                end = end.offset(getCenterXZ(), getHeight() - 1, 0);
            }

            case SOUTH -> {
                start = start.offset(-getCenterXZ(), 0, 0);
                end = end.offset(getCenterXZ(), getHeight() - 1, getWidth() - 1);
            }

            case WEST -> {
                start = start.offset(-(getWidth() - 1), 0, -getCenterXZ());
                end = end.offset(0, getHeight() - 1, getCenterXZ());
            }

            case EAST -> {
                start = start.offset(0, 0, -getCenterXZ());
                end = end.offset(getWidth() - 1, getHeight() - 1, getCenterXZ());
            }

            // case UP -> {
            // start = start.offset(-getCenterXZ(), 0, -getCenterXZ());
            // corner2 = origin.offset(getCenterXZ(), getHeight() - 1, getCenterXZ());
            // }

            // case DOWN -> {
            // start = start.offset(-getCenterXZ(), -(getHeight() - 1), -getCenterXZ());
            // corner2 = origin.offset(getCenterXZ(), 0, getCenterXZ());
            // }

            default -> throw new IllegalStateException("Unexpected value: " + face);
        }

        return getBasicArea(start, end);
    }

    default BlockPos getRandomPos(List<BlockPos> area) {
        return area.get(getLevel().random.nextInt(area.size()));
    }

}
