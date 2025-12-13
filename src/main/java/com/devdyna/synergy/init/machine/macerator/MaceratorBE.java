package com.devdyna.synergy.init.machine.macerator;

import java.util.List;
import java.util.Optional;

import javax.annotation.Nullable;

import com.devdyna.synergy.init.machine.core.BaseMachineBE;
import com.devdyna.synergy.init.machine.core.BaseMachineBlock;
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
public class MaceratorBE extends BaseMachineBE {

    protected static final int OUTPUT_SECONDARY_SLOT = 2;

    public MaceratorBE(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
        this.storage = new MachineItemHandler(getMachineSlots());
        this.energyStorage = new EnergyStorage(MaxFE());
        networkData = new ContainerData() {
            @Override
            public int get(int i) {
                return switch (i) {
                    case 0 -> progress;
                    case 1 -> maxProgress;
                    case 2 -> (level != null && !level.isClientSide()) ? getStoredFE() : energy;
                    case 3 -> (level != null && !level.isClientSide()) ? getMaxFE() : maxEnergy;
                    default -> 0;
                };
            }

            @Override
            public void set(int i, int value) {
                switch (i) {
                    case 0 -> progress = value;
                    case 1 -> maxProgress = value;
                    case 2 -> energy = value;
                    case 3 -> maxEnergy = value;
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
        return List.of(1, 2);
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

        ItemStack input = storage.getStackInSlot(0);
        ItemStack outputSlot = storage.getStackInSlot(1);
        ItemStack secondarySlot = storage.getStackInSlot(2);

        // empty
        if (input.isEmpty()) {
            if (getBlockState().getValue(BaseMachineBlock.ENABLED))
                update(false);
            resetProgress();
            return;
        }

        Optional<RecipeHolder<MaceratorRecipeType>> r = level.getRecipeManager()
                .getRecipeFor(zMachines.MACERATOR.recipe().getType(),
                        new MonoItemInput(input), level);

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
        if (!outputSlot.isEmpty()) {
            // same item
            if (ItemStack.isSameItemSameComponents(outputSlot, output)) {
                // count valid
                if (outputSlot.getMaxStackSize() < outputSlot.getCount() + output.getCount()) {
                    resetProgress();
                    return;
                }

            } else {
                resetProgress();
                return;
            }
        }

        // not empty
        if (!secondarySlot.isEmpty() && !secondary.isEmpty()) {
            // same item
            if (ItemStack.isSameItemSameComponents(secondarySlot, secondary)) {
                // count valid
                if (secondarySlot.getMaxStackSize() < secondarySlot.getCount() + secondary.getCount()) {
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

        if (outputSlot.isEmpty())
            storage.setStackInSlot(1, output);
        else if (ItemStack.isSameItemSameComponents(outputSlot, output))
            outputSlot.grow(output.getCount());

        if (!secondary.isEmpty() && success) {
            if (secondarySlot.isEmpty())
                storage.setStackInSlot(2, secondary);
            else if (ItemStack.isSameItemSameComponents(secondarySlot, secondary))
                secondarySlot.grow(secondary.getCount());
        }

        input.shrink(1);

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
