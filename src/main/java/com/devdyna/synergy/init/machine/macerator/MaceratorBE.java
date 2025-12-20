package com.devdyna.synergy.init.machine.macerator;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import javax.annotation.Nullable;

import com.devdyna.synergy.common.recipes.input.MonoItemInput;
import com.devdyna.synergy.init.machine.core.BaseMachineBE;
import com.devdyna.synergy.init.machine.core.BaseMachineBlock;
import com.devdyna.synergy.init.machine.core.ExtraMachineSlot;
import com.devdyna.synergy.init.machine.core.UpgradeSlots;
import com.devdyna.synergy.init.machine.macerator.recipe.MaceratorRecipeType;
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

@SuppressWarnings("null")
public class MaceratorBE extends BaseMachineBE implements ExtraMachineSlot , UpgradeSlots {

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
    public List<Integer> getInputSlotIndex() {
        return Stream.concat(getUpgradeIndexs().stream(), Stream.of(INPUT_SLOT)).toList();
    }

    @Override
    public List<Integer> getOutputSlotIndex() {
        return List.of(OUTPUT_SLOT, EXTRA_SLOT);
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
        super.tickServer();

        if (getInput().isEmpty()) {
            resetProgress();
            return;
        } else 
            progress_cancel = false;
        

        Optional<RecipeHolder<MaceratorRecipeType>> r = level.getRecipeManager()
                .getRecipeFor(zMachines.MACERATOR.recipe().getType(),
                        new MonoItemInput(getInput()), level);

        // no recipe
        if (r.isEmpty()) {
            resetProgress();
            return;
        }

        MaceratorRecipeType recipe = r.get().value();

        ItemStack output = recipe.getOutputItem().copy();
        ItemStack secondary = recipe.getSecondaryItem().copy();

        this.maxProgress = recipe.getTime();

        boolean success = level.random.nextFloat() < recipe.getSecondaryItemChance();

        if (!(checkSlot(getOutput(), output) && checkSlot(getExtraSlot(), secondary))) {
            resetProgress();
            return;
        }

        if (progress_cancel)
            return;
        else
            this.progress++;

        if (checkAndConsumeFE(recipe.getEnergy())) {
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

        if (!secondary.isEmpty() && success)
            updateOutputSlot(getExtraSlot(), secondary, EXTRA_SLOT);

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

            setChanged();
    }

    @Override
    public ContainerData getContainerData() {
        return networkData;
    }

  @Override
  public TYPE getSlotType() {
      return TYPE.OUTPUT;
  }


  @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        tag.put("inventory", getStorage().serializeNBT(registries));
        tag.putInt("progress", progress);
        tag.putInt("energy", energyStorage.getEnergyStored());
        super.saveAdditional(tag, registries);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        getStorage().deserializeNBT(registries, tag.getCompound("inventory"));
        if (tag.contains("progress"))
            progress = tag.getInt("progress");

        if (tag.contains("energy"))
            energyStorage.receiveEnergy(Math.min(tag.getInt("energy"), energyStorage.getMaxEnergyStored()), false);

        super.loadAdditional(tag, registries);
    }
}
