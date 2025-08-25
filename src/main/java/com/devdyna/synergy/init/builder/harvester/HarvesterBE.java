package com.devdyna.synergy.init.builder.harvester;

import java.util.*;
import com.devdyna.synergy.api.beLogic.EnergyBlock;
import com.devdyna.synergy.api.beLogic.ItemProducer;
import com.devdyna.synergy.api.beLogic.AreaOfEffect;
import com.devdyna.synergy.api.coreBE.BaseBE;
import com.devdyna.synergy.api.harvester.PlantHandler;
import com.devdyna.synergy.api.harvester.VanillaPlants;
import com.devdyna.synergy.init.types.zBlockEntities;
import com.devdyna.synergy.init.types.zHandlers;
import com.devdyna.synergy.utils.ColorUtil;
import com.devdyna.synergy.utils.LevelUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.capabilities.BlockCapabilityCache;
import net.neoforged.neoforge.energy.EnergyStorage;
import net.neoforged.neoforge.items.IItemHandler;

@SuppressWarnings("null")
public class HarvesterBE extends BaseBE implements EnergyBlock, AreaOfEffect, ItemProducer {

    private final Map<Direction, BlockCapabilityCache<IItemHandler, Direction>> cache = new HashMap<>();

    public HarvesterBE(BlockPos pos, BlockState state) {
        super(zBlockEntities.HARVESTER.get(), pos, state);
        var random = new Random();

        var color = ColorUtil.colorfulColorList.get(random.nextInt(ColorUtil.colorfulColorList.size()));

        rgbColor = List.of(color.getRed(), color.getGreen(), color.getBlue());
    }

    int i = 0;
    List<BlockPos> area = null;
    boolean soundToggle = false;
    List<Integer> rgbColor;

    int delay = 5;

    @Override
    public void tickServer() {

        level.setBlockAndUpdate(getBlockPos(),
                getBlockState().setValue(BlockStateProperties.ENABLED,
                        area != null && canExtract() && !level.hasNeighborSignal(getBlockPos())));

        if (area == null) {
            area = getAreaSelection(level, getBlockState().getValue(BlockStateProperties.HORIZONTAL_FACING),
                    getBlockPos());
        }

        if (getBlockState().getValue(BlockStateProperties.ENABLED)) {
            checkBlocks(level);
            extractFE(25, false);

        }

    }

    public List<ItemStack> collectItemDrops(Level level, BlockPos pos) {
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

    private void checkBlocks(Level level) {
        int size = area.size();

        if (i < size && level.getGameTime() % delay == 0) {

            LevelUtil.addDustParticle(rgbColor.get(0), rgbColor.get(1), rgbColor.get(2),
                    (ServerLevel) level, area.get(i), false, 4);

            List<ItemStack> items = collectItemDrops(level, area.get(i));

            if (items != null) {

                for (ItemStack itemStack : unifyDrops(items))
                    exportItems(itemStack, List.of(getBlockState().getValue(BlockStateProperties.HORIZONTAL_FACING)),
                            level, getBlockPos(), cache);
            }

            level.playSound(null, getBlockPos(),
                    (soundToggle ? SoundEvents.COPPER_BULB_TURN_ON : SoundEvents.COPPER_BULB_TURN_OFF),
                    SoundSource.BLOCKS);
            soundToggle = !soundToggle;
            i++;
        }

        if (i >= size) {
            i = 0;
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

    @Override
    public int radius() {
        return 4;
    }

    @Override
    public int height() {
        return 1;
    }

    @Override
    public boolean dropWhenFail() {
        return true;
    }

}
