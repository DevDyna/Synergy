package com.devdyna.synergy.init.builder.industrial_machines.extractor;

import java.util.Optional;

import javax.annotation.Nullable;

import com.devdyna.synergy.api.FluidStorageTank;
import com.devdyna.synergy.api.machine.BaseMachineBE;
import com.devdyna.synergy.api.machine.BaseMachineBlock;
import com.devdyna.synergy.api.machine.FluidTankStorage;
import com.devdyna.synergy.common.recipes.input.MonoItemInput;
import com.devdyna.synergy.init.builder.industrial_machines.extractor.recipe.ExtractorRecipeType;
import com.devdyna.synergy.init.types.zMachines;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.energy.EnergyStorage;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.IFluidHandler.FluidAction;

@SuppressWarnings("null")
public class ExtractorBE extends BaseMachineBE implements FluidTankStorage {

    public ExtractorBE(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
        this.storage = new MachineItemHandler(6);
        this.fluid_tank = new FluidStorageTank(this, this.getFluidCapacity());
        this.energyStorage = new EnergyStorage(MaxFE());
        networkData = new ContainerData() {
            @Override
            public int get(int i) {
                return switch (i) {
                    case PROGRESS_INDEX -> progress;
                    case MAX_PROGRESS_INDEX -> maxProgress;
                    case ENERGY_INDEX -> check(level, getStoredFE(), energy);
                    case MAX_ENERGY_INDEX -> check(level, getMaxFE(), maxEnergy);
                    case FLUID_INDEX -> check(level, getFluidStorage().getFluidAmount(), fluid_amount);
                    case MAX_FLUID_INDEX -> check(level, getFluidCapacity(), maxFluid);
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
                    case FLUID_INDEX -> fluid_amount = value;
                    case MAX_FLUID_INDEX -> maxFluid = value;
                }
            }

            @Override
            public int getCount() {
                return 6;
            }
        };

    }

    @Override
    public int getMachineSlots() {
        return 6;
    }

    public ExtractorBE(BlockPos pos, BlockState blockState) {
        this(zMachines.EXTRACTOR.blockentity().get(), pos, blockState);
    }

    @Override
    @Nullable
    public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new ExtractorMenu(i, inventory, this, this.networkData);
    }

    @Override
    public void tickServer() {
        super.tickServer();

        if (getInput().isEmpty()) {
            resetProgress();
            return;
        } else
            progress_cancel = false;

        Optional<RecipeHolder<ExtractorRecipeType>> r = level.getRecipeManager()
                .getRecipeFor(zMachines.EXTRACTOR.recipe().getType(),
                        new MonoItemInput(getInput()), level);

        // no recipe
        if (r.isEmpty()) {
            resetProgress();
            return;
        }

        ExtractorRecipeType recipe = r.get().value();

        ItemStack item_out = recipe.getSecondaryItem().copy();
        FluidStack fluid_out = recipe.getFluidOutput().copy();

        this.maxProgress = calculateMaxProgress(recipe.getTime());

        boolean success = calculateSecondarySuccess(recipe.getSecondaryItemChance());

        if (!(checkSlot(getOutput(), item_out)
                && checkSlot(getFluidStorage().getFluid(), fluid_out, getFluidCapacity()))) {
            resetProgress();
            return;
        }

        if (progress_cancel)
            return;
        else
            this.progress++;

        if (calculateAndConsumeFE(recipe.getEnergy())) {
            if (!getBlockState().getValue(BaseMachineBlock.ENABLED))
                update(true);
        } else {
            resetProgress();
            return;
        }

        if (this.progress < this.maxProgress) {
            setChanged();
            return;
        }

        if (!item_out.isEmpty() && success)
            updateOutputSlot(getOutput(), item_out, OUTPUT_SLOT);

        if (!fluid_out.isEmpty()) {
            if (getFluidStorage().isEmpty())
                getFluidStorage().setFluid(fluid_out);
            else if (FluidStack.isSameFluidSameComponents(fluid_out, getFluidStorage().getFluid()))
                getFluidStorage().fill(fluid_out, FluidAction.EXECUTE);
        }

        getInput().shrink(recipe.getInputItem().count());

        progress = 0;
        setChanged();
    }

    private void update(boolean v) {
        level.setBlockAndUpdate(getBlockPos(),
                getBlockState().setValue(BaseMachineBlock.ENABLED, v));
    }

    private void resetProgress() {

        progress_cancel = true;
        if (progress > 0)
            progress--;
        if (progress == 0)
            progress_cancel = false;

        if (getBlockState().getValue(BaseMachineBlock.ENABLED))
            update(false);

        setChanged();
    }

    @Override
    public ContainerData getContainerData() {
        return networkData;
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        tag.put("inventory", getStorage().serializeNBT(registries));
        tag.put("tank", getFluidStorage().serializeNBT(registries));
        tag.putInt("progress", progress);
        tag.putInt("energy", energyStorage.getEnergyStored());
        super.saveAdditional(tag, registries);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        getStorage().deserializeNBT(registries, tag.getCompound("inventory"));
        getFluidStorage().deserializeNBT(registries, tag.getCompound("tank"));
        if (tag.contains("progress"))
            progress = tag.getInt("progress");

        if (tag.contains("energy"))
            energyStorage.receiveEnergy(Math.min(tag.getInt("energy"), energyStorage.getMaxEnergyStored()), false);

        super.loadAdditional(tag, registries);
    }

    @Override
    public FluidStorageTank getFluidStorage() {
        return fluid_tank;
    }

    @Override
    public int getFluidCapacity() {
        return 10_000;
    }

    @Override
    public FluidTankType getTankIOType() {
        return FluidTankType.OUTPUT;
    }
}
