package com.devdyna.synergy.init.machine.macerator;

import java.util.List;
import java.util.Optional;

import javax.annotation.Nullable;

import com.devdyna.synergy.init.machine.core.BaseMachineBE;
import com.devdyna.synergy.init.machine.core.BaseMachineBlock;
import com.devdyna.synergy.init.machine.core.SecondaryMachineResult;
import com.devdyna.synergy.init.machine.macerator.recipe.MaceratorRecipeType;
import com.devdyna.synergy.init.recipeTypes.input.MonoItemInput;
import com.devdyna.synergy.init.types.zMachines;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.energy.EnergyStorage;

@SuppressWarnings("null")
public class MaceratorBE extends BaseMachineBE implements SecondaryMachineResult {

    public MaceratorBE(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
        this.storage = new MachineItemHandler(getMachineSlots());
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
        return 3;
    }

    @Override
    public List<Integer> getOutputSlotIndex() {
        return List.of(OUTPUT_SLOT, OUTPUT_SECONDARY_SLOT);
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
    public void tickServer() {
        if (level == null || level.isClientSide())
            return;

        // empty
        if (getInput().isEmpty()) {
            if (getBlockState().getValue(BaseMachineBlock.ENABLED))
                update(false);
            resetProgress();
            return;
        }else{
            progress_cancel = false;
        }

        Optional<RecipeHolder<MaceratorRecipeType>> r = level.getRecipeManager()
                .getRecipeFor(zMachines.MACERATOR.recipe().getType(),
                        new MonoItemInput(getInput()), level);

        level.setBlockAndUpdate(getBlockPos(), getBlockState()
                .setValue(BaseMachineBlock.ENABLED, !r.isEmpty() && canExtract()));

        // no recipe
        if (r.isEmpty()) {
            resetProgress();
            return;
        }

        MaceratorRecipeType recipe = r.get().value();

        ItemStack output = recipe.getOutputItem().copy();
        ItemStack secondary = recipe.getSecondaryOutputItem().copy();

        this.maxProgress = recipe.getTime();

        boolean success = level.random.nextFloat() < recipe.getSecondaryItemChance();

        if (energyStorage.getEnergyStored() >= recipe.getEnergy()) {
            energyStorage.extractEnergy(recipe.getEnergy(), false);
        } else {
            resetProgress();
            return;
        }

        // not empty
        if (!getOutput().isEmpty()) {
            // same item
            if (ItemStack.isSameItemSameComponents(getOutput(), output)) {
                // count valid
                if (getOutput().getMaxStackSize() < getOutput().getCount() + output.getCount()) {
                    resetProgress();
                    return;
                }

            } else {
                resetProgress();
                return;
            }
        }

        // not empty
        if (!getSecondary().isEmpty() && !secondary.isEmpty()) {
            // same item
            if (ItemStack.isSameItemSameComponents(getSecondary(), secondary)) {
                // count valid
                if (getSecondary().getMaxStackSize() < getSecondary().getCount() + secondary.getCount()) {
                    resetProgress();
                    return;
                }

            } else {
                resetProgress();
                return;
            }
        }

        if (progress_cancel) {
            return;
        } else
            this.progress++;

        if (this.progress < this.maxProgress) {
            setChanged();
            return;
        }

        if (getOutput().isEmpty())
            storage.setStackInSlot(OUTPUT_SLOT, output);
        else if (ItemStack.isSameItemSameComponents(getOutput(), output))
            getOutput().grow(output.getCount());

        if (!secondary.isEmpty() && success) {
            if (getSecondary().isEmpty())
                storage.setStackInSlot(OUTPUT_SECONDARY_SLOT, secondary);
            else if (ItemStack.isSameItemSameComponents(getSecondary(), secondary))
                getSecondary().grow(secondary.getCount());
        }

        getInput().shrink(1);

        progress = 0;
        setChanged();
    }

    private void update(boolean v) {
        level.setBlockAndUpdate(getBlockPos(), getBlockState().setValue(BaseMachineBlock.ENABLED, v));
    }

    private void resetProgress() {
        
        progress_cancel = true;
        if (progress > 0)
            progress--;
        if (progress == 0)
            progress_cancel = false;
    }

    @Override
    public ContainerData getContainerData() {
        return networkData;
    }

}
