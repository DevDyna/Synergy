package com.devdyna.synergy.init.builder.industrial_machines.alloy_smelter;

import java.util.List;
import java.util.Optional;

import javax.annotation.Nullable;

import com.devdyna.synergy.api.machine.BaseMachineBE;
import com.devdyna.synergy.api.machine.BaseMachineBlock;
import com.devdyna.synergy.api.machine.ExtraMachineSlots;
import com.devdyna.synergy.common.recipes.input.BiItemInput;
import com.devdyna.synergy.init.builder.industrial_machines.alloy_smelter.recipe.AlloySmelterRecipeType;
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
public class AlloySmelterBE extends BaseMachineBE implements ExtraMachineSlots {

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
        return List.of(INPUT_SLOT, EXTRA_SLOT_1);
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
    public void tickServer() {
        super.tickServer();

        // empty
        if (getInput().isEmpty()) {
            resetProgress();
            return;
        } else
            progress_cancel = false;

        Optional<RecipeHolder<AlloySmelterRecipeType>> r = level.getRecipeManager()
                .getRecipeFor(zMachines.ALLOY_SMELTER.recipe().getType(),
                        new BiItemInput(getInput(), getExtraSlot1()), level);

        Optional<RecipeHolder<AlloySmelterRecipeType>> r2 = level.getRecipeManager()
                .getRecipeFor(zMachines.ALLOY_SMELTER.recipe().getType(),
                        new BiItemInput(getExtraSlot1(), getInput()), level);

        AlloySmelterRecipeType recipe;

        boolean inverse = r.isEmpty();

        // no recipe
        if (inverse && r2.isEmpty()) {
            resetProgress();
            return;
        }

        recipe = inverse ? r2.get().value() : r.get().value();

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
        getExtraSlot1().shrink(1);

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

   @Override
    public SlotBuilder getSlotTypes() {
        return SlotBuilder.of().set(EXTRA_SLOT_1, SlotType.INPUT);
    }

}
