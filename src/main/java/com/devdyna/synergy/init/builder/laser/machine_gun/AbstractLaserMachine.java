package com.devdyna.synergy.init.builder.laser.machine_gun;

import javax.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

import com.devdyna.synergy.api.beLogic.EnergyBlock;
import com.devdyna.synergy.api.coreBE.be.TickingBE;
import com.devdyna.synergy.init.builder.laser.LaserMirrorBlock;
import com.devdyna.synergy.init.builder.laser.sensor.LaserSensorBE;
import com.devdyna.synergy.init.types.zHandlers;
import com.devdyna.synergy.utils.LevelUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.Level.ExplosionInteraction;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.AABB;
import net.neoforged.neoforge.energy.EnergyStorage;

@SuppressWarnings("null")
public abstract class AbstractLaserMachine extends TickingBE implements EnergyBlock {

    public boolean fused;

    public int red = 255;
    public int green = 0;
    public int blue = 0;

    /**
     * dead decay stage -1 (lasermachine)
     */
    protected final int MAX_LASER_LENGHT = getMaxLaserLenght();
    /**
     * init decay stage
     */
    protected final int RESET = 0;

    public AbstractLaserMachine(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
        fused = false;
    }

    // public BaseLaserMachine(BlockPos p, BlockState s) {
    // this(zBlockEntities.LASER_MACHINE.get(), p, s);
    // }

    /**
     * check if there is an entity at specific blockpos
     */
    public static boolean isEntity(Level level, BlockState partialState, BlockPos currentPos) {
        return isEntity(level, partialState, new AABB(currentPos));
    }

    /**
     * check if there is an entity at specific blockpos
     */
    public static boolean isEntity(Level level, BlockState partialState, AABB pos) {
        return !level.getEntities(null, pos).isEmpty();
    }

    /**
     * check if there is an opaque block at specific blockpos
     */
    public static boolean isOpaqueBlock(Level level, BlockState partialState, BlockPos currentPos) {
        return partialState.isCollisionShapeFullBlock(level, currentPos);
    }

    @Override
    public void tickServer() {
        if (!fused)
            level.setBlockAndUpdate(getBlockPos(),
                    getBlockState()
                            .setValue(BlockStateProperties.ENABLED, enableWhen(level, getBlockPos())));
    }

    @Override
    public void tickBoth() {
        var state = getBlockState();
        var pos = getBlockPos();
        var facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);

        if (state.getValue(BlockStateProperties.ENABLED) && canExtract()) {
            extractFE(10, false);

            /**
             * dynamic pos
             */
            var currentPos = pos;

            /**
             * decay
             */
            var lenght = RESET;

            boolean isStart = true;

            var currentDir = facing;

            /**
             * life time
             */
            while (lenght < MAX_LASER_LENGHT) {

                BlockState partialState = level.getBlockState(currentPos);
                @Nullable
                BlockEntity partialBE = level.getBlockEntity(currentPos);

                // extra condition -> break
                if (breakWhen(level, partialState, currentDir, currentPos, isStart, partialBE))
                    break;

                // lens -> extend
                if (extendWhen(level, partialState, currentDir, currentPos, isStart, partialBE))
                    lenght = RESET;

                // sensor -> update | break
                if (partialBE != null && partialBE instanceof LaserSensorBE ls) {
                    if (sensorActive(level, partialState, currentDir, currentPos, isStart, ls))
                        ls.setActive();
                    else
                        break;
                }

                // mirror -> rotate
                if (partialState.getBlock() instanceof LaserMirrorBlock lm
                        && rotateWhen(level, partialState, currentDir, currentPos, isStart)) {
                    var newDir = lm.getMirrorDir(currentDir, partialState);

                    if (renderParticlesWhen(level, partialState, currentDir, currentPos, isStart, partialBE, lenght))
                        LevelUtil.addDustParticleDiagonalLine(red, green, blue, (ServerLevel) level, currentPos,
                                currentDir, newDir,
                                getParticlesScale(level, partialState, currentDir, currentPos, isStart, partialBE,
                                        lenght));

                    currentDir = newDir;

                    lenght = RESET / 2;
                } else // empty -> repeat
                if (renderParticlesWhen(level, partialState, currentDir, currentPos, isStart, partialBE, lenght))
                    LevelUtil.addDustParticleLine(red, green, blue,
                            (ServerLevel) level, currentPos, currentDir,
                            getParticlesScale(level, partialState, currentDir, currentPos, isStart, partialBE, lenght));

                // laser machine -> explode
                if (partialBE != null && partialBE instanceof AbstractLaserMachine laser
                        && explodeWhen(level, partialState, currentDir, currentPos, isStart, laser)) {
                    laser.setFused();

                    level.explode(null, currentPos.getX() + 0.5, currentPos.getY() + 0.5, currentPos.getZ() + 0.5,
                            1,
                            ExplosionInteraction.BLOCK);
                    break;
                }

                currentPos = currentPos.relative(currentDir);

                // prevent to select the initial laser machine at initial execution
                if (isStart)
                    isStart = false;

                // increase decay
                lenght++;

            }

        }

    }

    protected abstract boolean renderParticlesWhen(Level level, BlockState partialState, Direction currentDir,
            BlockPos currentPos, boolean isStart, @Nullable BlockEntity partialBE, int lenght);

    protected abstract float getParticlesScale(Level level, BlockState partialState, Direction currentDir,
            BlockPos currentPos, boolean isStart, @Nullable BlockEntity partialBE, int lenght);

    protected abstract boolean sensorActive(Level level, BlockState partialState, Direction currentDir,
            BlockPos currentPos, boolean isStart, @NotNull LaserSensorBE partialBE);

    /**
     * 
     * @param partialState support ONLY LaserMirrorBlock state
     */
    protected abstract boolean rotateWhen(Level level, BlockState partialState, Direction currentDir,
            BlockPos currentPos,
            boolean isStart);

    protected abstract boolean canExplodeDestination();

    /**
     * Server Side Level Only!
     */
    protected abstract boolean enableWhen(Level level, BlockPos pos);

    protected abstract int getMaxLaserLenght();

    protected abstract boolean breakWhen(Level level, BlockState partialState, Direction currentDir,
            BlockPos currentPos, boolean isStart, @Nullable BlockEntity partialBE);

    protected abstract boolean explodeWhen(Level level, BlockState partialState, Direction currentDir,
            BlockPos currentPos, boolean isStart, @NotNull AbstractLaserMachine partialBE);

    protected abstract boolean extendWhen(Level level, BlockState partialState, Direction currentDir,
            BlockPos currentPos, boolean isStart, @Nullable BlockEntity partialBE);

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
        return 1000;
    }

    public void setFused() {
        if (canExplodeDestination())
            fused = true;
    }

    @Override
    protected void saveAdditional(CompoundTag tag, Provider registries) {
        tag.putInt("red", red);
        tag.putInt("green", green);
        tag.putInt("blue", blue);
        super.saveAdditional(tag, registries);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, Provider registries) {

        if (tag.contains("red"))
            red = tag.getInt("red");
        if (tag.contains("green"))
            green = tag.getInt("green");
        if (tag.contains("blue"))
            blue = tag.getInt("blue");
        super.loadAdditional(tag, registries);
    }

    public void setRed(int red) {
        this.red = red;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }

}
