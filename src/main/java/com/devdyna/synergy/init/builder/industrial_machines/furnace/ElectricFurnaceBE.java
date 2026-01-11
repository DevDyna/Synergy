package com.devdyna.synergy.init.builder.industrial_machines.furnace;

import java.util.Optional;
import javax.annotation.Nullable;

import com.devdyna.synergy.api.machine.BaseMachineBE;
import com.devdyna.synergy.api.machine.BaseMachineBlock;
import com.devdyna.synergy.common.recipes.input.MonoItemInput;
import com.devdyna.synergy.config.Common;
import com.devdyna.synergy.init.builder.industrial_machines.furnace.recipe.ElectricFurnaceRecipeType;
import com.devdyna.synergy.init.types.zMachines;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.energy.EnergyStorage;

@SuppressWarnings("null")
public class ElectricFurnaceBE extends BaseMachineBE {

    public ElectricFurnaceBE(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
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
        return 6;
    }

    public ElectricFurnaceBE(BlockPos pos, BlockState blockState) {
        this(zMachines.ELECTRIC_FURNACE.blockentity().get(), pos, blockState);
    }

    @Override
    @Nullable
    public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new ElectricFurnaceMenu(i, inventory, this, this.networkData);
    }

    @Override
    public void tickServer() {
        super.tickServer();

        if (getInput().isEmpty()) {
            resetProgress();
            return;
        } else
            progress_cancel = false;

        Optional<RecipeHolder<ElectricFurnaceRecipeType>> r = level.getRecipeManager()
                .getRecipeFor(zMachines.ELECTRIC_FURNACE.recipe().getType(),
                        new MonoItemInput(getInput()), level);

        Optional<RecipeHolder<SmeltingRecipe>> vanilla = level.getRecipeManager()
                .getRecipeFor(RecipeType.SMELTING, new SingleRecipeInput(getInput()), level);

        // no recipe
        if (r.isEmpty() && !vanilla.isEmpty())
            processVanillaType(vanilla.get().value());
        else if (!r.isEmpty())
            processStandaloneType(r.get().value());
        else {
            resetProgress();
            return;
        }

    }

    private void processStandaloneType(ElectricFurnaceRecipeType recipe) {

        ItemStack output = recipe.getOutputItem().copy();

        this.maxProgress = calculateMaxProgress(recipe.getTime());

        if (!(checkSlot(getOutput(), output))) {
            resetProgress();
            return;
        }

        if (progress_cancel)
            return;
        else
            this.progress++;

        if (checkAndConsumeFE(calculateFEUsage(recipe.getEnergy()))) {
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

        updateOutputSlot(getOutput(), output, OUTPUT_SLOT);

        getInput().shrink(1);

        progress = 0;
        setChanged();
    }

    private void processVanillaType(SmeltingRecipe recipe) {

        if(Common.DISABLE_MACHINE_FURNACE_PROCESS_VANILLA.get()) return;

        ItemStack output = recipe.getResultItem(level.registryAccess()).copy();

        this.maxProgress = calculateMaxProgress(recipe.getCookingTime());

        if (!(checkSlot(getOutput(), output))) {
            resetProgress();
            return;
        }

        if (progress_cancel)
            return;
        else
            this.progress++;

        if (checkAndConsumeFE(calculateFEUsage(Common.MACHINE_FURNACE_PROCESS_VANILLA_FE_COST.get()))) {
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

        updateOutputSlot(getOutput(), output, OUTPUT_SLOT);

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

        if (getBlockState().getValue(BaseMachineBlock.ENABLED))
            update(false);
    }

    @Override
    public ContainerData getContainerData() {
        return networkData;
    }

}
