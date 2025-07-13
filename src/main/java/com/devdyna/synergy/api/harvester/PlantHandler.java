package com.devdyna.synergy.api.harvester;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import com.devdyna.synergy.utils.LogUtil;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

/**
 * Functional Interface to allow compatibility with (Synergy) Harvester to
 * harvest stuff safetly
 * 
 * Credits : @DevDyna
 */
public interface PlantHandler {

    int treeHarvestingBlockLimit = 16384;

    /**
     * the result item after be broken a single block
     * <br/>
     * <br/>
     * when BIG_PLANT it will return it foreach times was broken
     * <br/>
     * <br/>
     * when TREE it will be ignored
     */
    List<ItemStack> itemResult(Level level, BlockPos pos);

    /**
     * block to replant
     * <br/>
     * <br/>
     * require to use setblock!
     * <br/>
     * <br/>
     * ONLY WORK WITH BLOCK_REPLANT
     */
    void blockReplanted(Level level, BlockPos pos);

    /**
     * all blocks related to the tree
     * <br/>
     * <br/>
     * can be null if mode wasn't TREE
     */
    ArrayList<Block> blockTree();

    /**
     * property of crop age
     */
    IntegerProperty getProperty();

    /**
     * Type of mode to harvest it
     */
    HarvestModes getMode();

    /**
     * Condition of when a block can be harvested
     * <br/>
     * <br/>
     * Heavly suggested
     * <br/>
     * <br/>
     * use .isMaxAge(BlockState) when the block is a crop
     * <br/>
     * <br/>
     * or check block above on BIG_PLANTS
     */
    boolean whenCanBeHarvested(Level level, BlockPos pos);

    /**
     * Try to harvest the block
     * <br/>
     * <br/>
     * Useful on custom Harvesting
     * 
     * @return item harvesteds
     */
    default List<ItemStack> execute(Level level, BlockPos pos) {

        var state = level.getBlockState(pos);
        var block = state.getBlock();

        switch (getMode()) {

            case HarvestModes.BLOCK_NO_REPLANT:
                if (whenCanBeHarvested(level, pos)) {
                    level.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
                    return itemResult(level, pos);
                }
                break;

            case HarvestModes.BLOCK_REPLANT:
                if (whenCanBeHarvested(level, pos)) {
                    blockReplanted(level, pos);
                    return itemResult(level, pos);
                }
                break;

            case HarvestModes.TREE:
                if (whenCanBeHarvested(level, pos)) {
                    return checkTree(level, pos);
                }
                break;

            case HarvestModes.BIG_PLANT:
                if (whenCanBeHarvested(level, pos)) {

                    ArrayList<ItemStack> list = new ArrayList<>();
                    while (level.getBlockState(pos.above()).is(block)) {
                        pos = pos.above();
                        level.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
                        itemResult(level, pos).forEach(t -> list.add(t));
                    }
                    return list;
                } else
                    break;

            default:
                LogUtil.error("UNKNOWN HARVEST MODE");
                break;
        }
        return null;
    }

    private List<ItemStack> checkTree(Level level, BlockPos pos) {

        if (blockTree() == null || blockTree().isEmpty()) {
            LogUtil.error("UNKNOWN TREE BLOCKS");
            return null;
        }

        var state = level.getBlockState(pos);
        var block = state.getBlock();

        boolean canProcede = false;

        if (blockTree().contains(block))
            for (Direction dir : Direction.values()) {
                if (blockTree().contains(level.getBlockState(pos.relative(dir)).getBlock())) {
                    canProcede = true;
                    break;
                }
            }

        if (canProcede) {

            ArrayList<ItemStack> itemList = new ArrayList<>();

            Queue<BlockPos> queue = new LinkedList<>();
            Set<BlockPos> visited = new HashSet<>();

            queue.add(pos);
            visited.add(pos);

            int checkBlocks = 0;

            while (!queue.isEmpty()) {
                BlockPos currentPos = queue.poll();

                for (List<Integer> off : VanillaPlants.getTreeDirections()) {
                    BlockPos adjacentPos = currentPos.offset(off.get(0), off.get(1), off.get(2));
                    BlockState adjacentState = level.getBlockState(adjacentPos);

                    if (blockTree().contains(adjacentState.getBlock()) && !visited.contains(adjacentPos)) {
                        queue.add(adjacentPos);
                        visited.add(adjacentPos);
                        level.setBlockAndUpdate(adjacentPos, Blocks.AIR.defaultBlockState());
                        Block.getDrops(adjacentState, (ServerLevel) level, adjacentPos, null)
                                .forEach(t -> itemList.add(t));
                    }
                }
                checkBlocks++;
                if(checkBlocks >= treeHarvestingBlockLimit)break;
            }
            level.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
            Block.getDrops(state, (ServerLevel) level, pos, null).forEach(t -> itemList.add(t));

            return itemList;
        }

        return null;
    }

}
