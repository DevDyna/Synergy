package com.devdyna.synergy.api.beLogic;

import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import com.devdyna.synergy.api.utils.Range;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;

public interface AreaOfEffect extends SimpleAOE {

    abstract void onLoad();

    /**
     * set true to exclude <code>height();</code> and use only
     * <code>radius();</code> to define all coords
     */
    boolean hasSizeEqual();

    /*
     * height of AOE around the controller blocks
     */
    int height();

    /**
     * Limits of <code>int radius();</code>
     */
    Range radiusLimit();

    /**
     * Limits of <code>int height();</code>
     */
    Range heightLimit();

    /**
     * @return map<Start,End>
     */
    default Entry<BlockPos, BlockPos> getPoints(Level level, BlockPos baseBlock, Direction dir, boolean isUp) {
        BlockPos relPos = baseBlock.relative(dir);
        Direction upDown = isUp ? Direction.UP : Direction.DOWN;

        ArrayList<Direction> validDirs = getHorizontalDirections(dir);

        return Map.entry(relPos.relative(validDirs.get(0), radius()),
                relPos.relative(validDirs.get(1), radius()).relative(dir, (radius()) * 2).relative(upDown,
                        height() - 1));
    }

    /**
     * @return map<Start,End>
     */
    default Entry<BlockPos, BlockPos> getPoints(Level level, BlockPos baseBlock, Direction dir, boolean isUp,
            int height, int radius) {
        BlockPos relPos = baseBlock.relative(dir);
        Direction upDown = isUp ? Direction.UP : Direction.DOWN;

        ArrayList<Direction> validDirs = getHorizontalDirections(dir);

        return Map.entry(relPos.relative(validDirs.get(0), radius),
                relPos.relative(validDirs.get(1), radius).relative(dir, (radius) * 2).relative(upDown,
                        height - 1));
    }

    /**
     * check all directions and remove all blacklisted
     * NORTH -> EAST | WEST | UP | DOWN
     * <br/>
     * <br/>
     * Edit. DONT WORK AS INTENDED BUT WORK SO THIS IS FINE!
     */
    default ArrayList<Direction> getDirections(ArrayList<Direction> blacklist) {

        ArrayList<Direction> directions = new ArrayList<>(
                Arrays.stream(Direction.values()).distinct().collect(Collectors.toList()));

        directions = directions.stream().filter(d -> !blacklist.contains(d))
                .collect(Collectors.toCollection(ArrayList::new));

        return directions;
    }

    /**
     * return the values removing UP and DOWN
     * NORTH -> EAST | WEST
     */
    default ArrayList<Direction> getHorizontalDirections(Direction exclude) {
        return getDirections(new ArrayList<>(List.of(Direction.UP, Direction.DOWN, exclude, exclude.getOpposite())));
    }

    /**
     * get start blockpos of AOE
     */
    default BlockPos getStartPoint(Entry<BlockPos, BlockPos> map) {
        return map.getKey();
    }

    /**
     * get end blockpos of AOE
     */
    default BlockPos getEndPoint(Entry<BlockPos, BlockPos> map) {
        return map.getValue();
    }

    /**
     * get center blockpos of AOE
     */
    default BlockPos getCenter(Direction dir, BlockPos pos) {
        return pos.relative(dir).relative(dir, radius());
    }

    /**
     * return a list of blockpos inside the AOE
     */
    default List<BlockPos> getAreaSelection(BlockPos start, BlockPos end) {
        List<BlockPos> slots = new ArrayList<>();

        for (int y = Math.min(start.getY(), end.getY()); y <= Math.max(start.getY(), end.getY()); y++)
            for (int x = Math.min(start.getX(), end.getX()); x <= Math.max(start.getX(), end.getX()); x++)
                for (int z = Math.min(start.getZ(), end.getZ()); z <= Math.max(start.getZ(), end.getZ()); z++)
                    slots.add(new BlockPos(x, y, z));

        return slots;
    }

    /**
     * probably not necessary atm
     * return a list of blockpos inside the AOE
     */
    // default List<BlockPos> getHorizontalAreaSelection(Level level, Direction dir,
    // BlockPos baseBlock) {
    // var points = getHorizontalPoints(level, baseBlock,dir);
    // return getAreaSelection(getStartPoint(points), getEndPoint(points));
    // }

    /**
     * return a list of blockpos inside the AOE
     */
    default List<BlockPos> getAreaSelection(Level level, Direction dir, BlockPos baseBlock) {
        var points = getPoints(level, baseBlock, dir, true);
        return getAreaSelection(getStartPoint(points), getEndPoint(points));
    }

}
