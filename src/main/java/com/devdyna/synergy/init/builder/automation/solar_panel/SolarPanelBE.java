package com.devdyna.synergy.init.builder.automation.solar_panel;

import java.util.HashMap;
import java.util.Map;

import com.devdyna.synergy.api.basebe.be.TickingBE;
import com.devdyna.synergy.api.beLogic.EnergyProvider;
import com.devdyna.synergy.init.types.zBlockEntities;
import com.devdyna.synergy.init.types.zHandlers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.capabilities.BlockCapabilityCache;
import net.neoforged.neoforge.energy.EnergyStorage;
import net.neoforged.neoforge.energy.IEnergyStorage;

@SuppressWarnings("null")
public class SolarPanelBE extends TickingBE implements EnergyProvider {

    private final Map<Direction, BlockCapabilityCache<IEnergyStorage, Direction>> cache = new HashMap<>();

    public SolarPanelBE(BlockPos pos, BlockState state) {
        super(zBlockEntities.SOLAR_PANEL.get(), pos, state);
    }

    @Override
    public void tickServer() {

        level.setBlockAndUpdate(getBlockPos(), getBlockState().setValue(BlockStateProperties.ENABLED,
                canReceive() && level.isDay() && checkSky() && !level.hasNeighborSignal(getBlockPos())));

        if (getBlockState().getValue(BlockStateProperties.ENABLED)) {
            increaseFE(getFERate(), false);
        }

        if (canExtract()) {
            providePowerAdjacent(level, getBlockPos(), cache, 64);
        }
    }

    public boolean checkSky() {
        return level.canSeeSkyFromBelowWater(getBlockPos().above())
                && level.getBlockState(getBlockPos().above()).isAir();
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
        return 10000;
    }

    @Override
    public int getFERate() {
        return 16;
    }

}
