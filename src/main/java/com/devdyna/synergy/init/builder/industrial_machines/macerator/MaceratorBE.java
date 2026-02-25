package com.devdyna.synergy.init.builder.industrial_machines.macerator;

import java.util.List;
import javax.annotation.Nullable;

import com.devdyna.synergy.api.machine.BaseMachineBE;
import com.devdyna.synergy.api.machine.ExtraMachineSlots;
import com.devdyna.synergy.api.utils.RecipeUtils;
import com.devdyna.synergy.common.recipes.input.MonoItemInput;
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
public class MaceratorBE extends BaseMachineBE implements ExtraMachineSlots {

    public static final int SECONDARY_SLOT = 6;

    public MaceratorBE(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
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
    public List<Integer> getOutputSlotIndex() {
        return List.of(OUTPUT_SLOT, SECONDARY_SLOT);
    }

    public MaceratorBE(BlockPos pos, BlockState blockState) {
        this(zMachines.MACERATOR.blockentity().get(), pos, blockState);
    }

    @Override
    @Nullable
    public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new MaceratorMenu(i, inventory, this, this.networkData);
    }

    @Override
    public boolean initProgress() {

        if (getInput().isEmpty())
            return cancel();

        progress_cancel = false;

        var r = RecipeUtils.getRecipes(level, zMachines.MACERATOR, new MonoItemInput(getInput()));

        // no recipe
        if (r.isEmpty())
            return cancel();

        var recipe = r.get().value();

        if (!(checkSlot(getOutput(), recipe.getOutputItem().copy())
                && checkSlot(getSecondarySlot(), recipe.getSecondaryItem().copy())))
            return cancel();

        if (!calculateAndConsumeFE(recipe.getEnergy()))
            return cancel();

        update(true);

        this.maxProgress = calculateMaxProgress(recipe.getTime());

        return true;

    }

    @Override
    public void endProgress() {

        var recipe = RecipeUtils.getUnsafeRecipes(level, zMachines.MACERATOR, new MonoItemInput(getInput()));

        updateOutputSlot(getOutput(), recipe.getOutputItem().copy(), OUTPUT_SLOT);

        if (!recipe.getSecondaryItem().copy().isEmpty() && calculateSecondarySuccess(recipe.getSecondaryItemChance()))
            updateOutputSlot(getSecondarySlot(), recipe.getSecondaryItem().copy(), SECONDARY_SLOT);

        getInput().shrink(recipe.getInputItem().count());
    }

    @Override
    public ContainerData getContainerData() {
        return networkData;
    }

    @Override
    public SlotBuilder getSlotTypes() {
        return SlotBuilder.of(1).set(SECONDARY_SLOT, SlotType.OUTPUT);
    }

    public ItemStack getSecondarySlot() {
        return getStorage().getStackInSlot(SECONDARY_SLOT);
    }
}
