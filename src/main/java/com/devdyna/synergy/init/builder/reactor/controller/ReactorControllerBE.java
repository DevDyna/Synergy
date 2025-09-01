package com.devdyna.synergy.init.builder.reactor.controller;

import java.util.*;
import com.devdyna.synergy.api.beLogic.EnergyProvider;
import com.devdyna.synergy.api.beLogic.AreaOfEffect;
import com.devdyna.synergy.api.coreBE.BaseBE;
import com.devdyna.synergy.api.reactor.ControllerProperties;
import com.devdyna.synergy.init.builder.reactor.cell.FuelCellBlock;
import com.devdyna.synergy.init.builder.reactor.cooler.CoolerBlockBase;
import com.devdyna.synergy.init.builder.reactor.moderator.ModeratorBase;
import com.devdyna.synergy.init.types.zBlockEntities;
import com.devdyna.synergy.init.types.zHandlers;
import com.devdyna.synergy.utils.ColorUtil;
import com.devdyna.synergy.utils.LevelUtil;
import com.devdyna.synergy.utils.PlayerUtil;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.capabilities.BlockCapabilityCache;
import net.neoforged.neoforge.energy.EnergyStorage;
import net.neoforged.neoforge.energy.IEnergyStorage;

@SuppressWarnings("null")
public class ReactorControllerBE extends BaseBE implements EnergyProvider, AreaOfEffect {

    private final Map<Direction, BlockCapabilityCache<IEnergyStorage, Direction>> cache = new HashMap<>();

    public ReactorControllerBE(BlockPos pos, BlockState state) {
        super(zBlockEntities.REACTOR_CONTROLLER.get(), pos, state);
        var random = new Random();
        var color = ColorUtil.colorfulColorList.get(random.nextInt(ColorUtil.colorfulColorList.size()));
        rgbColor = List.of(color.getRed(), color.getGreen(), color.getBlue());
    }

    int i = 0;
    List<BlockPos> area = null;
    List<Integer> rgbColor;

    int areaTemp = 95; // approx 35°C as °F

    // TODO change to depend on fuel insered
    int fe = 20;
    long heat = areaTemp;

    boolean foundCell;
    boolean forceOverHeat;

    @Override
    public void tickServer() {

        level.setBlockAndUpdate(getBlockPos(),
                getBlockState()
                        .setValue(BlockStateProperties.ENABLED,
                                canReceive() && area != null && level.hasNeighborSignal(getBlockPos()))
                        .setValue(ReactorControllerBlock.STATUS,
                                enable() ? ((heat > areaTemp || forceOverHeat) ? ControllerProperties.OVERHEATED
                                        : (foundCell ? ControllerProperties.PRODUCTION : ControllerProperties.NOCELLS))
                                        : ControllerProperties.WAITING));

        if (area == null)
            area = getAreaSelection(level, getBlockState().getValue(BlockStateProperties.HORIZONTAL_FACING),
                    getBlockPos());

        if (enable()) {
            resetStats();
            checkBlocks(level);
        }

        if (is(ControllerProperties.PRODUCTION)) {
            increaseFE(fe, false);
            if (level.getGameTime() % 10 == 0)
                level.playSound(null, getBlockPos(),
                        SoundEvents.CAMPFIRE_CRACKLE,
                        SoundSource.BLOCKS, 1F * (LevelUtil.chance(50, level) ? 1f : 0.75f), 1);

        }

        if (canExtract()) {
            providePowerAdjacent(level, getBlockPos(), cache, getStoredFE());
        }

        // TODO REMOVE THIS AS FAST AS POSSIBLE
        // AND REPLACE WITH A ITEM LINKER
        level.players().forEach(p -> PlayerUtil.messageActionBar("Heat: " + heat, p));
    }

    private void checkBlocks(Level level) {

        for (BlockPos pos : area) {
            if (LevelUtil.chance(1, level))
                LevelUtil.addDustParticle(rgbColor.get(0), rgbColor.get(1), rgbColor.get(2),
                        (ServerLevel) level, pos, false, 1);

            var state = level.getBlockState(pos);
            var block = state.getBlock();

            if (block instanceof ReactorControllerBlock) {
                forceOverHeat = true;
                return;
            }

            if (block instanceof CoolerBlockBase cooler) {
                heat -= level.getBlockState(pos).getValue(BlockStateProperties.ENABLED)
                        ? cooler.getActiveCooling()
                        : cooler.getBaseCooling();
            }

            if (block instanceof FuelCellBlock fuelcell) {
                foundCell = true;
                var cells = level.getBlockState(pos).getValue(FuelCellBlock.CELLS);

                cells = cells == 0 ? 1 : cells;

                fe *= (1 + (cells * fuelcell.cellsFEMultiplier()));
                heat *= (1 + (cells * fuelcell.cellsHeatMultiplier()));
            }

            if (block instanceof ModeratorBase moderator) {
                fe /= (1 + (moderator.getMultiplier() * moderator.getBaseFEReducer()));
                heat /= (1 + (moderator.getMultiplier() * moderator.getBaseHeatReducer()));
            }

        }

        // heat efficiency calc
        fe *= 1.0 - (heat - areaTemp) * 0.001F;

    }

    private void resetStats() {
        forceOverHeat = false;
        foundCell = false;
        fe = 20;
        heat = areaTemp;
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
    public EnergyStorage getCapEnergy() {
        return getData(zHandlers.ENERGY_STORAGE);
    }

    @Override
    public int radius() {
        return 4;
    }

    @Override
    public int height() {
        return 4;
    }

    @Override
    public int MaxFE() {
        return 1_000_000;
    }

}
