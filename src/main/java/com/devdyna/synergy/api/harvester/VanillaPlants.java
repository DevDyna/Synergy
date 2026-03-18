package com.devdyna.synergy.api.harvester;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.function.BiFunction;

import com.devdyna.synergy.Common;
import com.devdyna.synergy.api.utils.LevelUtil;
import com.devdyna.synergy.api.utils.LogUtil;
import com.devdyna.synergy.init.types.zBlockTag;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BambooStalkBlock;
import net.minecraft.world.level.block.BeetrootBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CactusBlock;
import net.minecraft.world.level.block.CocoaBlock;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.NetherWartBlock;
import net.minecraft.world.level.block.PumpkinBlock;
import net.minecraft.world.level.block.SugarCaneBlock;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

/**
 * Utility class to harvest most of plants safetly
 * 
 * Credits : @DevDyna
 */
@SuppressWarnings("null")
public class VanillaPlants {

    static int treeHarvestingBlockLimit = Common.TREE_CUTTING_LIMIT.get();

    public static List<List<Integer>> getTreeDirections() {
        ArrayList<List<Integer>> coordinates = new ArrayList<>();
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                for (int z = -1; z <= 1; z++) {
                    if (x == 0 && y == 0 && z == 0)
                        continue;
                    coordinates.add(List.of(x, y, z));
                }
            }
        }
        return coordinates;

    }

    public static List<IntegerProperty> allCropProperties = List.of(
            BlockStateProperties.AGE_1,
            BlockStateProperties.AGE_15,
            BlockStateProperties.AGE_2,
            BlockStateProperties.AGE_25,
            BlockStateProperties.AGE_3,
            BlockStateProperties.AGE_4,
            BlockStateProperties.AGE_5,
            BlockStateProperties.AGE_7);

    public static List<ItemStack> checkReplant(Level level, BlockPos pos) {

        if (level.isClientSide())
            return null;

        if (Common.HARVESTER_DISABLE_CHECK_REPLANT.get())
            return null;

        var state = level.getBlockState(pos);
        var block = state.getBlock();

        if (block instanceof CropBlock crop) {

            if (crop.isMaxAge(state)) {
                // synergy custom crops
                if (crop instanceof PlantHandler handler) {
                    level.setBlockAndUpdate(pos, state.setValue(handler.getProperty(), 0));
                    return Block.getDrops(state, (ServerLevel) level, pos, null);

                } else {

                    try {
                        // vanilla crops
                        if (block instanceof BeetrootBlock)
                            level.setBlockAndUpdate(pos, state.setValue(BeetrootBlock.AGE, 0));
                        else
                            level.setBlockAndUpdate(pos, state.setValue(CropBlock.AGE, 0));

                        return Block.getDrops(state, (ServerLevel) level, pos, null);

                    } catch (Exception e1) {
                        // crops with different properties
                        for (IntegerProperty p : allCropProperties) {

                            if (state.hasProperty(p)) {
                                try {
                                    level.setBlockAndUpdate(pos, state.setValue(p, 0));
                                    return Block.getDrops(state, (ServerLevel) level, pos, null);
                                } catch (Exception e2) {
                                    // setblock cause issue
                                }
                            }
                        }

                        // no valid properties
                        // custom crops with special props(?)
                        LogUtil.error(
                                "Unsupported crop at : " + pos.toString() + " id: "
                                        + block.getDescriptionId() + "\n Report it to Synergy Issue tracker!");
                        LevelUtil.addParticle(ParticleTypes.ANGRY_VILLAGER, (ServerLevel) level,
                                pos.above(),
                                false);

                    }
                }
            }
        }

        if (block instanceof NetherWartBlock) {
            if (state.getValue(BlockStateProperties.AGE_3).intValue() == 3) {
                level.setBlockAndUpdate(pos, state.setValue(BlockStateProperties.AGE_3, 0));
                return Block.getDrops(state, (ServerLevel) level, pos, null);
            }
        }

        if (block instanceof SweetBerryBushBlock) {
            if (state.getValue(BlockStateProperties.AGE_3).intValue() > 1) {
                level.setBlockAndUpdate(pos, state.setValue(BlockStateProperties.AGE_3, 1));
                return Block.getDrops(state, (ServerLevel) level, pos, null);
            }
        }

        if (block instanceof CocoaBlock) {
            if (state.getValue(BlockStateProperties.AGE_2).intValue() == 2) {
                level.setBlockAndUpdate(pos, state.setValue(BlockStateProperties.AGE_2, 0));
                return Block.getDrops(state, (ServerLevel) level, pos, null);
            }
        }

        return null;

    }

    public static List<ItemStack> checkNoReplant(Level level, BlockPos pos) {
        var state = level.getBlockState(pos);
        var block = state.getBlock();

        if (Common.HARVESTER_DISABLE_CHECK_NOREPLANT.get())
            return null;

        if (block instanceof PumpkinBlock || state.is(Blocks.MELON)) {
            level.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
            return Block.getDrops(state, (ServerLevel) level, pos, null);
        }
        return null;
    }

    public static List<ItemStack> checkTree(Level level, BlockPos pos, boolean configFlag) {
        return checkTree(level, pos, configFlag, (i, p) -> false);
    }

    public static List<ItemStack> checkTree(Level level, BlockPos pos, boolean configFlag,
            BiFunction<BlockState, BlockPos, Boolean> tool) {

        var state = level.getBlockState(pos);

        if (configFlag)
            return null;

        boolean canProcede = false;

        if (state.is(zBlockTag.HARVESTER_TREE_BREAK))
            for (Direction dir : Direction.values()) {
                if (level.getBlockState(pos.relative(dir)).is(zBlockTag.HARVESTER_TREE_BREAK)) {
                    canProcede = true;
                    break;
                }
            }

        if (canProcede) {

            ArrayList<ItemStack> itemList = new ArrayList<>();

            ArrayList<SoundEvent> souldList = new ArrayList<>();

            souldList.add(state.getSoundType(level, pos, null).getBreakSound());

            Queue<BlockPos> queue = new LinkedList<>();
            Set<BlockPos> visited = new HashSet<>();

            queue.add(pos);
            visited.add(pos);

            int checkBlocks = 0;
            boolean toolFlag = false;

            while (!queue.isEmpty()) {
                BlockPos currentPos = queue.poll();

                for (List<Integer> off : getTreeDirections()) {
                    BlockPos adjacentPos = currentPos.offset(off.get(0), off.get(1), off.get(2));
                    BlockState adjacentState = level.getBlockState(adjacentPos);

                    if (adjacentState.is(zBlockTag.HARVESTER_TREE_BREAK) && !visited.contains(adjacentPos)) {
                        queue.add(adjacentPos);
                        visited.add(adjacentPos);
                        level.setBlockAndUpdate(adjacentPos, Blocks.AIR.defaultBlockState());
                        Block.getDrops(adjacentState, (ServerLevel) level, adjacentPos, null)
                                .forEach(t -> itemList.add(t));

                        toolFlag = tool.apply(adjacentState, adjacentPos);

                        if (!souldList.contains(adjacentState.getSoundType(level, adjacentPos, null).getBreakSound())) {
                            souldList.add(adjacentState.getSoundType(level, adjacentPos, null).getBreakSound());
                        }
                    }
                }
                checkBlocks++;

                if (checkBlocks >= treeHarvestingBlockLimit)
                    break;

                if (toolFlag)
                    break;
            }

            level.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
            Block.getDrops(state, (ServerLevel) level, pos, null).forEach(t -> itemList.add(t));

            souldList.forEach(s -> level.playSound(null, pos, s, SoundSource.BLOCKS));

            return itemList;
        }

        return null;
    }

    public static List<ItemStack> checkBigPlant(Level level, BlockPos pos) {

        if (Common.HARVESTER_DISABLE_CHECK_BIGPLANT.get())
            return null;

        var state = level.getBlockState(pos);
        var block = state.getBlock();

        if (block instanceof CactusBlock || block instanceof SugarCaneBlock || block instanceof BambooStalkBlock) {
            var tempPos = pos;
            ArrayList<ItemStack> list = new ArrayList<>();
            while (level.getBlockState(tempPos.above()).is(block)) {
                tempPos = tempPos.above();
                level.setBlockAndUpdate(tempPos, Blocks.AIR.defaultBlockState());
                Block.getDrops(state, (ServerLevel) level, pos, null).forEach(t -> list.add(t));
            }
            return list;

        }
        return null;
    }

    
}
