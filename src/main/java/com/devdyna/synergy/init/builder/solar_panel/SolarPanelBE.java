package com.devdyna.synergy.init.builder.solar_panel;

import java.util.HashMap;
import java.util.Map;

import com.devdyna.synergy.api.capabilities.EnergyBlock;
import com.devdyna.synergy.api.coreBE.BaseBE;
import com.devdyna.synergy.init.types.zBlockEntities;
import com.devdyna.synergy.init.types.zHandlers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.capabilities.BlockCapabilityCache;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.energy.EnergyStorage;
import net.neoforged.neoforge.energy.IEnergyStorage;

@SuppressWarnings("null")
public class SolarPanelBE extends BaseBE implements EnergyBlock {

    private final Map<Direction, BlockCapabilityCache<IEnergyStorage, Direction>> cache = new HashMap<>();

    public SolarPanelBE(BlockPos pos, BlockState state) {
        super(zBlockEntities.SOLAR_PANEL.get(), pos, state);
    }

    @Override
    public void tickServer() {
        if (canReceive() && level.isDay() && checkSky()) {
            level.setBlockAndUpdate(getBlockPos(), getBlockState().setValue(BlockStateProperties.ENABLED, true));
            increaseFE(64, false);
        } else {
            level.setBlockAndUpdate(getBlockPos(), getBlockState().setValue(BlockStateProperties.ENABLED, false));
        }
        if (canExtract()) {
            providePowerAdjacent(level, getBlockPos(), cache, 64);
        }
    }

    @Override
    public ContainerData getContainerData() {
        return new SimpleContainerData(getMaxFE());
    }

    @Override
    public EnergyStorage getCapEnergy() {
        return getData(zHandlers.ENERGY_STORAGE);
    }

    private void providePowerAdjacent(Level level, BlockPos pos,
            Map<Direction, BlockCapabilityCache<IEnergyStorage, Direction>> cache, int feRate) {

        var be = level.getBlockEntity(pos);

        if (be == null)
            return;

        if (((EnergyBlock) be).canExtract())
            for (Direction dir : Direction.values()) {
                var cachedData = cache.get(dir);
                if (cachedData == null)
                    cachedData = BlockCapabilityCache.create(
                            Capabilities.EnergyStorage.BLOCK,
                            (ServerLevel) level,
                            pos.relative(dir),
                            dir.getOpposite());
                cache.put(dir, cachedData);

                IEnergyStorage cap = cachedData.getCapability();
                if (cap == null || level.getBlockState(pos.relative(dir)).is(getBlockState().getBlock()))
                    continue;
                int simOn = cap.receiveEnergy(feRate * 10, true);
                if (simOn <= 0)
                    continue;
                cap.receiveEnergy(((EnergyBlock) be).extractFE(simOn, false), false);
            }
    }

    public boolean checkSky() {
        return level.canSeeSkyFromBelowWater(getBlockPos().above())
                && level.getBlockState(getBlockPos().above()).isAir();
    }

}
