package com.devdyna.synergy.init.builder.laser.laser_rotor;

import java.util.HashMap;
import java.util.Map;

import com.devdyna.synergy.api.basebe.be.TickingBE;
import com.devdyna.synergy.api.beLogic.EnergyProvider;
import com.devdyna.synergy.init.types.zBlockEntities;
import com.devdyna.synergy.init.types.zHandlers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.neoforged.neoforge.capabilities.BlockCapabilityCache;
import net.neoforged.neoforge.transfer.energy.EnergyHandler;

@SuppressWarnings("null")
public class LaserRotorBE extends TickingBE implements EnergyProvider {

    private final Map<Direction, BlockCapabilityCache<EnergyHandler, Direction>> cache = new HashMap<>();

    private int[] blockpos;

    private boolean north;
    private boolean south;
    private boolean east;
    private boolean west;

    private boolean sended = false;

    private BlockPos newpPos;

    private Direction newdir;


    public LaserRotorBE(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
        blockpos = null;
        north = false;
        south = false;
        east = false;
        west = false;
    }

    public LaserRotorBE(BlockPos pos, BlockState state) {
        this(zBlockEntities.LASER_ROTOR.get(), pos, state);
    }

    @Override
    public ContainerData getContainerData() {
        return new SimpleContainerData(getMaxFE());
    }

    @Override
    public EnergyHandler getCapEnergy() {
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
                    blockpos = pos(newpPos);
            } else {
                resetStat();
                blockpos = pos(newpPos);
            }

            sended = false;
        }

        if (north && south && east && west) {
            if (canReceive())
                increaseFE(getFERate());

            resetStat();
        }

        if (canExtract())
            providePowerAdjacent(level, getBlockPos(), cache, getStoredFE());

    }

    private int[] pos(BlockPos pos) {
        return new int[] { pos.getX(), pos.getY(), pos.getZ() };
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

    protected static final String POS_LASER = "blockpos-laser";
    protected static final String DIR_NORTH = "dir-north";
    protected static final String DIR_SOUTH = "dir-south";
    protected static final String DIR_EAST = "dir-east";
    protected static final String DIR_WEST = "dir-west";

    @Override
    protected void saveAdditional(ValueOutput output) {
        output.putBoolean(DIR_NORTH, north);
        output.putBoolean(DIR_SOUTH, south);
        output.putBoolean(DIR_EAST, east);
        output.putBoolean(DIR_WEST, west);
        output.putIntArray(POS_LASER, blockpos);
        super.saveAdditional(output);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        north = input.getBooleanOr(DIR_NORTH, false);
        south = input.getBooleanOr(DIR_SOUTH, false);
        east = input.getBooleanOr(DIR_EAST, false);
        west = input.getBooleanOr(DIR_WEST, false);

        if (input.getIntArray(POS_LASER).isPresent()) {
            var pos = input.getIntArray(POS_LASER).get();
            blockpos = pos;
        }

        super.loadAdditional(input);
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

    @Override
    public int getFERate() {
        return 10_000;
    }

}
