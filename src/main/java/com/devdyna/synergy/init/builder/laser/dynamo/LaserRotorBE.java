package com.devdyna.synergy.init.builder.laser.dynamo;

import java.util.HashMap;
import java.util.Map;

import com.devdyna.synergy.api.beLogic.EnergyProvider;
import com.devdyna.synergy.api.coreBE.be.TickingBE;
import com.devdyna.synergy.init.types.zBlockEntities;
import com.devdyna.synergy.init.types.zHandlers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.capabilities.BlockCapabilityCache;
import net.neoforged.neoforge.energy.EnergyStorage;
import net.neoforged.neoforge.energy.IEnergyStorage;

@SuppressWarnings("null")
public class LaserRotorBE extends TickingBE implements EnergyProvider {

    private final Map<Direction, BlockCapabilityCache<IEnergyStorage, Direction>> cache = new HashMap<>();

    private int[] blockpos;

    private boolean north;
    private boolean south;
    private boolean east;
    private boolean west;

    private boolean sended = false;

    private BlockPos newpPos;

    private Direction newdir;

    private CompoundTag laserData;

    public LaserRotorBE(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
        laserData = new CompoundTag();
        blockpos = null;
        north = false;
        south = false;
        east = false;
        west = false;
    }

    public LaserRotorBE(BlockPos pos, BlockState state) {
        this(zBlockEntities.ELECTROMAGNETIC_ROTOR.get(), pos, state);
    }

    @Override
    public ContainerData getContainerData() {
        return new SimpleContainerData(getMaxFE());
    }

    @Override
    public EnergyStorage getCapEnergy() {
        return getData(zHandlers.ENERGY_STORAGE);
    }

    @Override
    public int MaxFE() {
        return 1_000_000;
    }

    @Override
    public void tickServer() {

        if (sended) {

            if (blockpos != null
                    ? (newpPos.getX() == blockpos[0] && newpPos.getY() == blockpos[1] && newpPos.getZ() == blockpos[2])
                    : true) {

                if (newdir == Direction.NORTH && !north)
                    north = true;
                if (newdir == Direction.SOUTH && !south)
                    south = true;
                if (newdir == Direction.EAST && !east)
                    east = true;
                if (newdir == Direction.WEST && !west)
                    west = true;

                if (blockpos == null)
                    blockpos = new int[] { newpPos.getX(), newpPos.getY(), newpPos.getZ() };
            } else {
                resetStat();
                blockpos = new int[] { newpPos.getX(), newpPos.getY(), newpPos.getZ() };
            }

            sended = false;
        }

        if (north && south && east && west) {
            if (canReceive())
                increaseFE(10000, false);

            resetStat();
        }

        if (canExtract())
            providePowerAdjacent(level, getBlockPos(), cache, getStoredFE());

    }

    public void sendRotation(BlockPos pos, Direction dir) {
        LaserRotorBlock.rotate(getBlockPos(), getBlockState(), false, level);
        sended = true;
        newpPos = pos;
        newdir = dir;
    }

    private void resetStat() {
        north = false;
        south = false;
        east = false;
        west = false;
    }

    @Override
    protected void saveAdditional(CompoundTag tag, Provider registries) {
        var laserData = new CompoundTag();
        var directions = new CompoundTag();

        directions.putBoolean("north", north);
        directions.putBoolean("south", south);
        directions.putBoolean("east", east);
        directions.putBoolean("west", west);

        if (blockpos != null)
            laserData.putIntArray("blockpos", blockpos);
        laserData.put("dir", directions);
        tag.put("laserData", laserData);
        super.saveAdditional(tag, registries);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, Provider registries) {

        if (tag.contains("laserData")) {
            laserData = tag.getCompound("laserData");
            if (laserData.contains("dir")) {
                var dir = laserData.getCompound("dir");

                if (dir.contains("north"))
                    north = dir.getBoolean("north");
                if (dir.contains("south"))
                    south = dir.getBoolean("south");
                if (dir.contains("east"))
                    east = dir.getBoolean("east");
                if (dir.contains("west"))
                    west = dir.getBoolean("west");

            }

            if (laserData.contains("blockpos")) {

                var pos = laserData.getIntArray("blockpos");

                blockpos = pos;
            }

        }

        super.loadAdditional(tag, registries);
    }

    public CompoundTag getData() {
        var tag = new CompoundTag();

        CompoundTag dirs = new CompoundTag();
        dirs.putBoolean("north", north);
        dirs.putBoolean("south", south);
        dirs.putBoolean("east", east);
        dirs.putBoolean("west", west);
        tag.put("dir", dirs);

        if (blockpos != null) {
            tag.putIntArray("blockpos", blockpos);
        }

        return tag;
    }

}
