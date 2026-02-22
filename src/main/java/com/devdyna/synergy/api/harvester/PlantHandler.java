package com.devdyna.synergy.api.harvester;

import java.util.ArrayList;
import java.util.List;
import com.devdyna.synergy.api.utils.LogUtil;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

/**
 * Functional Interface to allow compatibility with (Synergy) Harvester to
 * harvest stuff safetly
 * 
 * Credits : @DevDyna
 */
public interface PlantHandler {

    /**
     * the result item after be broken a single block
     * <br/>
     * <br/>
     * when BIG_PLANT it will return it foreach times was broken
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

}
