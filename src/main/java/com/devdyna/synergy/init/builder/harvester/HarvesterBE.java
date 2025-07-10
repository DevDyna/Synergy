package com.devdyna.synergy.init.builder.harvester;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import com.devdyna.synergy.api.capabilities.EnergyBlock;
import com.devdyna.synergy.api.coreBE.BaseBE;
import com.devdyna.synergy.api.harvester.PlantHandler;
import com.devdyna.synergy.api.harvester.VanillaPlants;
import com.devdyna.synergy.init.types.zBlockEntities;
import com.devdyna.synergy.init.types.zHandlers;
import com.devdyna.synergy.utils.LevelUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.capabilities.BlockCapabilityCache;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.energy.EnergyStorage;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemHandlerHelper;

public class HarvesterBE extends BaseBE implements EnergyBlock {

    private final Map<Direction, BlockCapabilityCache<IItemHandler, Direction>> cache = new HashMap<>();

    public int radius = 5;

    public HarvesterBE(BlockPos pos, BlockState state) {
        super(zBlockEntities.HARVESTER.get(), pos, state);
    }

    int i;
    List<BlockPos> area = null;
    boolean areaFound = false;
    boolean toggle = false;

    int delay = 5;

    @Override
    public void tickServer() {

        if (canExtract()) {
            extractFE(25, false);

            if (areaFound) {
                checkBlocks(level);
            } else {
                calculateArea(level, getBlockState(), getBlockPos());
            }
        }
    }

    public List<ItemStack> tryHarvestAndGetDrops(Level level, BlockPos pos) {
        ArrayList<List<ItemStack>> list = new ArrayList<>(Arrays.asList(
                VanillaPlants.checkReplant(level, pos),
                VanillaPlants.checkNoReplant(level, pos),
                VanillaPlants.checkTree(level, pos),
                VanillaPlants.checkBigPlant(level, pos),
                getAPICrops(level, pos)));

        for (List<ItemStack> check : list) {
            if (check != null) {
                return check;
            }
        }

        return null;
    }

    public List<ItemStack> getAPICrops(Level level, BlockPos pos) {
        var state = level.getBlockState(pos);
        var block = state.getBlock();
        if (block instanceof PlantHandler plant) {
            return plant.execute(level, pos);
        }
        return null;
    }

    public void tryExportOrDrop(ItemStack item, Level level, BlockPos pos,
            Map<Direction, BlockCapabilityCache<IItemHandler, Direction>> cache) {
        var dirToExclude = getBlockState().getValue(BlockStateProperties.HORIZONTAL_FACING);
        var totalDir = Direction.values().length;
        for (Direction dir : Direction.values()) {
            if (dir == dirToExclude) {
                totalDir--;
                continue;
            }
            var cachedData = cache.get(dir);
            if (cachedData == null)
                cachedData = BlockCapabilityCache.create(
                        Capabilities.ItemHandler.BLOCK,
                        (ServerLevel) level,
                        pos.relative(dir),
                        dir.getOpposite());
            cache.put(dir, cachedData);

            IItemHandler cap = cachedData.getCapability();

            if (cap == null || !(cap instanceof IItemHandler)) {
                totalDir--;
                continue;
            } else {

                var items = ItemHandlerHelper.insertItemStacked(cap, item, false);

                if (item.is(items.getItem()) && item.getCount() == items.getCount()
                        && items != new ItemStack(Items.AIR)) {
                    LevelUtil.popItemFromPos(level, pos.above(), item);
                    level.playSound(null, pos, SoundEvents.ITEM_FRAME_REMOVE_ITEM, SoundSource.BLOCKS, 1F, 0.75F);
                }

                break;
            }

        }

        if (totalDir <= 0) {
            LevelUtil.popItemFromPos(level, pos.above(), item);
        }

    }

    public Entry<BlockPos, BlockPos> getPoints(Level level, BlockState state, BlockPos baseBlock) {
        Direction dir = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
        BlockPos relPos = baseBlock.relative(dir);

        ArrayList<Direction> horizontalDirs = getDirPoints(dir);

        return Map.entry(move(relPos, horizontalDirs.get(0), radius - 1),
                move(move(relPos, horizontalDirs.get(1), radius - 1), dir, (radius - 1) * 2));
    }

    public ArrayList<Direction> getDirPoints(Direction dir) {
        ArrayList<Direction> horizontalDirs = new ArrayList<>(
                Arrays.asList(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST));

        for (Direction axis : Direction.Plane.HORIZONTAL)
            if (axis == dir || axis == dir.getOpposite())
                horizontalDirs.remove(axis);
        return horizontalDirs;
    }

    public BlockPos getStartPoit(Entry<BlockPos, BlockPos> map) {
        return map.getKey();
    }

    public BlockPos getEndPoit(Entry<BlockPos, BlockPos> map) {
        return map.getValue();
    }

    public BlockPos getCenter(BlockState state, BlockPos baseBlock) {
        Direction dir = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
        BlockPos relPos = baseBlock.relative(dir);
        return move(relPos, dir, radius - 1);
    }

    public void calculateArea(Level level, BlockState state, BlockPos baseBlock) {
        var points = getPoints(level, state, baseBlock);
        area = getAreaSelection(getStartPoit(points), getEndPoit(points));
        i = 0;
        areaFound = true;
    }

    private void checkBlocks(Level level) {
        int size = area.size();

        if (i < size && level.getGameTime() % delay == 0) {
            LevelUtil.addParticle((ServerLevel) level, area.get(i), ParticleTypes.END_ROD, false);

            var items = tryHarvestAndGetDrops(level, area.get(i));

            if (items != null)
                for (ItemStack itemStack : items)
                    tryExportOrDrop(itemStack, level, getBlockPos(), cache);

            level.playSound(null, getBlockPos(),
                    (toggle ? SoundEvents.COPPER_BULB_TURN_ON : SoundEvents.COPPER_BULB_TURN_OFF),
                    SoundSource.BLOCKS);
            toggle = !toggle;
            i++;
        }

        if (i >= size) {
            i = 0;
        }
    }

    @Deprecated
    public static BlockPos move(BlockPos actualPos, Direction dir, int times) {
        return actualPos.relative(dir, times);
    }

    public List<BlockPos> getAreaSelection(BlockPos start, BlockPos end) {
        List<BlockPos> slots = new ArrayList<>();

        for (int x = Math.min(start.getX(), end.getX()); x <= Math.max(start.getX(), end.getX()); x++)
            for (int z = Math.min(start.getZ(), end.getZ()); z <= Math.max(start.getZ(), end.getZ()); z++)
                slots.add(new BlockPos(x, start.getY(), z));

        return slots;
    }

    @Override
    public ContainerData getContainerData() {
        return new SimpleContainerData(getMaxFE());
    }

    @Override
    public EnergyStorage getCapEnergy() {
        return getData(zHandlers.ENERGY_STORAGE);
    }

}
