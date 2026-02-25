package com.devdyna.synergy.init.builder.industrial_machines.alloy_smelter;

import java.util.List;
import javax.annotation.Nullable;

import com.devdyna.synergy.api.machine.BaseMachineBE;
import com.devdyna.synergy.api.machine.ExtraMachineSlots;
import com.devdyna.synergy.api.utils.RecipeUtils;
import com.devdyna.synergy.common.recipes.input.BiItemInput;
import com.devdyna.synergy.init.builder.industrial_machines.alloy_smelter.recipe.AlloySmelterRecipeType;
import com.devdyna.synergy.init.types.zMachines;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.energy.EnergyStorage;

@SuppressWarnings("null")
public class AlloySmelterBE extends BaseMachineBE implements ExtraMachineSlots {

    public static final int SECONDARY_INPUT = 6;

    public AlloySmelterBE(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
        this.storage = new MachineItemHandler(7);
        this.energyStorage = new EnergyStorage(MaxFE());
        networkData = new ContainerData() {
            @Override
            public int get(int i) {
                return switch (i) {
                    case PROGRESS_INDEX -> progress;
                    case MAX_PROGRESS_INDEX -> maxProgress;
                    case ENERGY_INDEX -> (level != null && !level.isClientSide()) ? getStoredFE() : energy;
                    case MAX_ENERGY_INDEX -> (level != null && !level.isClientSide()) ? getMaxFE() : maxEnergy;
                    default -> 0;
                };
            }

            @Override
            public void set(int i, int value) {
                switch (i) {
                    case PROGRESS_INDEX -> progress = value;
                    case MAX_PROGRESS_INDEX -> maxProgress = value;
                    case ENERGY_INDEX -> energy = value;
                    case MAX_ENERGY_INDEX -> maxEnergy = value;
                }
            }

            @Override
            public int getCount() {
                return 4;
            }
        };

    }

    @Override
    public int getMachineSlots() {
        return 7;
    }

    @Override
    public List<Integer> getInputSlotIndex() {
        return List.of(INPUT_SLOT, SECONDARY_INPUT);
    }

    public AlloySmelterBE(BlockPos pos, BlockState blockState) {
        this(zMachines.ALLOY_SMELTER.blockentity().get(), pos, blockState);
    }

    @Override
    @Nullable
    public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new AlloySmelterMenu(i, inventory, this, this.networkData);
    }

    @Override
    public boolean initProgress() {

        if (getInput().isEmpty())
            return cancel();

        progress_cancel = false;

        var r = RecipeUtils.getRecipes(level, zMachines.ALLOY_SMELTER,
                new BiItemInput(getSecondaryInput(), getInput()));
        var r2 = RecipeUtils.getRecipes(level, zMachines.ALLOY_SMELTER,
                new BiItemInput(getInput(), getSecondaryInput()));

        AlloySmelterRecipeType recipe;

        boolean inverse = r.isEmpty();

        // no recipe
        if (inverse && r2.isEmpty())
            return cancel();

        recipe = (inverse ? r2 : r).get().value();

        if (!checkSlot(getOutput(), recipe.getOutputItem().copy()))
            return cancel();

        if (!calculateAndConsumeFE(recipe.getEnergy()))
            return cancel();

        update(true);

        this.maxProgress = calculateMaxProgress(recipe.getTime());

        return true;

    }

    @Override
    public void endProgress() {

        var r = RecipeUtils.getRecipes(level, zMachines.ALLOY_SMELTER,
                new BiItemInput(getSecondaryInput(), getInput()));

        var r2 = RecipeUtils.getRecipes(level, zMachines.ALLOY_SMELTER,
                new BiItemInput(getInput(), getSecondaryInput()));

        AlloySmelterRecipeType recipe;

        boolean inverse = r.isEmpty();

        recipe = (inverse ? r2 : r).get().value();

        updateOutputSlot(getOutput(), recipe.getOutputItem().copy(), OUTPUT_SLOT);

        getInput().shrink(recipe.getInputItem().count());
        getSecondaryInput().shrink(recipe.getCatalystItem().count());
    }

    @Override
    public ContainerData getContainerData() {
        return networkData;
    }

    @Override
    public SlotBuilder getSlotTypes() {
        return SlotBuilder.of(1).set(SECONDARY_INPUT, SlotType.INPUT);
    }

    public ItemStack getSecondaryInput() {
        return getStorage().getStackInSlot(SECONDARY_INPUT);
    }

}
