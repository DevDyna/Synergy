package com.devdyna.synergy.init.builder.nuclear_reactor.controller;

import java.util.*;

import com.devdyna.synergy.api.beLogic.EnergyProvider;
import com.devdyna.synergy.api.basebe.be.TickingBE;
import com.devdyna.synergy.api.beLogic.AreaOfEffect;
import com.devdyna.synergy.api.reactor.ControllerProperties;
import com.devdyna.synergy.api.utils.*;
import com.devdyna.synergy.init.builder.nuclear_reactor.cooler.CoolerBlockBase;
import com.devdyna.synergy.init.builder.nuclear_reactor.fuel_cell.FuelCellBE;
import com.devdyna.synergy.init.builder.nuclear_reactor.fuel_cell.FuelCellBlock;
import com.devdyna.synergy.init.builder.nuclear_reactor.moderator.ModeratorBase;
import com.devdyna.synergy.init.types.zBlockEntities;
import com.devdyna.synergy.init.types.zHandlers;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.storage.ValueInput;
import net.neoforged.neoforge.capabilities.BlockCapabilityCache;
import net.neoforged.neoforge.transfer.energy.EnergyHandler;

@SuppressWarnings("null")
public class ReactorControllerBE extends TickingBE implements EnergyProvider, AreaOfEffect {

    private final Map<Direction, BlockCapabilityCache<EnergyHandler, Direction>> cache = new HashMap<>();

    public ReactorControllerBE(BlockPos pos, BlockState state) {
        super(zBlockEntities.REACTOR_CONTROLLER.get(), pos, state);
        if (radius == 0)
            this.radius = 4;
        var random = new Random();
        var color = ColorUtil.colorfulColorList.get(random.nextInt(ColorUtil.colorfulColorList.size()));
        rgbColor = List.of(color.getRed(), color.getGreen(), color.getBlue());
    }

    int i = 0;

    List<Integer> rgbColor;

    public int fe = 1;
    public double heat = 1;

    boolean isOverHeated;
    private List<BlockPos> cells = null;
    public int cellsCount = cells != null ? cells.size() : 0;

    @Override
    public void tickServer() {

        level.setBlockAndUpdate(getBlockPos(),
                getBlockState()
                        .setValue(BlockStateProperties.ENABLED,
                                canReceive() && !isAreaNull() && level.hasNeighborSignal(getBlockPos()))
                        .setValue(ReactorControllerBlock.STATUS,
                                enable() ? (isOverHeated ? ControllerProperties.OVERHEATED
                                        : (cellFound() ? ControllerProperties.PRODUCTION
                                                : ControllerProperties.NOFUEL))
                                        : ControllerProperties.WAITING));

        if (isAreaNull())
            area = getAreaSelection(level, getBlockState().getValue(BlockStateProperties.HORIZONTAL_FACING),
                    getBlockPos());

        if (enable()) {
            resetStats();
            checkBlocks(level);
        }

        if (is(ControllerProperties.PRODUCTION)) {
            increaseFE(fe);
            if (level.getGameTime() % 10 == 0)
                level.playSound(null, getBlockPos(),
                        SoundEvents.BLASTFURNACE_FIRE_CRACKLE,
                        SoundSource.BLOCKS, 1F * (LevelUtil.chance(50, level) ? 1f : 0.75f), 1);

        }

        if (canExtract())
            providePowerAdjacent(level, getBlockPos(), cache, getStoredFE());

    }

    @Override
    public void updateAOE() {
        super.updateAOE();
        resetStats();
    }

    private void resetStats() {
        isOverHeated = false;
        fe = 1;
        heat = 1;
        cells = new ArrayList<>();
    }

    private void checkBlocks(Level level) {

        for (BlockPos pos : area) {
            if (LevelUtil.chance(1, level))
                LevelUtil.addDustParticle(rgbColor.get(0), rgbColor.get(1), rgbColor.get(2),
                        (ServerLevel) level, pos, false, 1);

            var block = level.getBlockState(pos).getBlock();

            if (block instanceof ReactorControllerBlock) {
                isOverHeated = true;
                return;
            }

            if (block instanceof CoolerBlockBase cooler) {
                heat += (level.getBlockState(pos).getValue(BlockStateProperties.ENABLED)
                        ? cooler.getActiveCooling()
                        : cooler.getBaseCooling());
            }

            if (level.getBlockEntity(pos) instanceof FuelCellBE fuelcell) {
                var c = level.getBlockState(pos).getValue(FuelCellBlock.CELLS);

                if (fuelcell.hasRecipe()) {
                    cells.add(pos);

                    var recipe = fuelcell.getRecipe().get().value();

                    fe += (1 + c) * recipe.getFe();
                    heat += (1 + c) * recipe.getHeat();
                }

            }

            if (block instanceof ModeratorBase moderator) {
                if (level.getBlockState(pos).getValue(BlockStateProperties.ENABLED)) {
                    fe *= moderator.FEReducer();
                    heat *= moderator.HeatReducer();
                }
            }

        }

        heat = ((int) (heat * 1000)) / 1000.0;

        // heat efficiency calc
        fe *= 1.0 - (heat) * 0.001F;

        isOverHeated = heat > 0;

        updateCells(is(ControllerProperties.PRODUCTION));

    }

    public boolean cellFound() {
        return cells != null && !cells.isEmpty();
    }

    public void updateCells(boolean state) {
        if (cellFound())
            for (BlockPos pos : cells)
                level.setBlockAndUpdate(pos, level.getBlockState(pos).setValue(BlockStateProperties.ENABLED, state));

    }

    public boolean is(ControllerProperties prop) {
        return getBlockState().getValue(ReactorControllerBlock.STATUS).equals(prop);
    }

    public boolean enable() {
        return getBlockState().getValue(BlockStateProperties.ENABLED);
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
    public int radius() {
        return radius;
    }

    @Override
    public int height() {
        return radius;
    }

    @Override
    public int MaxFE() {
        return 1_000_000;
    }

    @Override
    public Range radiusLimit() {
        return getRange();
    }

    @Override
    public Range heightLimit() {
        return getRange();
    }

    public Range getRange() {
        return Range.of(1, 8, BiBool.of(true, false));
    }

    public double getHeat() {
        return heat;
    }

    public String getStatus() {
        return getBlockState().getValue(ReactorControllerBlock.STATUS).getName();
    }

    @Override
    public CompoundTag getUpdateTag(Provider lookupProvider) {
        CompoundTag tag = super.getUpdateTag(lookupProvider);
        tag.putDouble("heat", heat);
        tag.putInt("fe", fe);
        return tag;
    }

    @Override
    public void handleUpdateTag(ValueInput input) {
        super.handleUpdateTag(input);
        heat = input.getDoubleOr("heat", heat);
        fe = input.getInt("fe").get();
        rebuildArea();
    }

    @Override
    public void onLoad() {
        super.onLoad();
        if (level.isClientSide())
            rebuildArea();
    }

    @Override
    public boolean hasSizeEqual() {
        return true;
    }

    @Override
    public int getFERate() {
        return fe;
    }

}
