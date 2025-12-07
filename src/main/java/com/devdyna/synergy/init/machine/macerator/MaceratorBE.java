package com.devdyna.synergy.init.machine.macerator;

import java.util.List;
import java.util.Optional;

import javax.annotation.Nullable;

import com.devdyna.synergy.init.machine.core.BaseMachineBE;
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

@SuppressWarnings("null")
public class MaceratorBE extends BaseMachineBE {

    public MaceratorBE(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
        this.storage = new MachineItemHandler(getMachineSlots());
        networkData = new ContainerData() {

            @Override
            public int get(int i) {
                return switch (i) {
                    case 0 -> progress;
                    case 1 -> maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int i, int value) {
                switch (i) {
                    case 0 -> progress = value;
                    case 1 -> maxProgress = progress;
                }
            }

            @Override
            public int getCount() {
                return 3;
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
        if (input.isEmpty()) {
            resetProgress();
            return;
        }

        Optional<RecipeHolder<MaceratorRecipeType>> recipeOpt = level.getRecipeManager()
                .getRecipeFor(zMachines.MACERATOR.recipe().getType(),
                        new MonoItemInput(input), level);

        if (recipeOpt.isEmpty()) {
            resetProgress();
            return;
        }

        MaceratorRecipeType recipe = recipeOpt.get().value();

        this.maxProgress = recipe.getTime();

        this.progress++;
        if (this.progress < this.maxProgress) {
            setChanged();
            return;
        }

        ItemStack outputSlot = storage.getStackInSlot(1);
        ItemStack secondarySlot = storage.getStackInSlot(2);

        ItemStack output = recipe.getOutputItem().copy();
        ItemStack secondary = recipe.getSecondaryOutputItem().copy();

        if (outputSlot.isEmpty())
            storage.setStackInSlot(1, output);
        else if (ItemStack.isSameItemSameComponents(outputSlot, output))
            outputSlot.grow(output.getCount());

        if (!secondary.isEmpty()) {
            if (secondarySlot.isEmpty())
                storage.setStackInSlot(2, secondary);
            else if (ItemStack.isSameItemSameComponents(secondarySlot, secondary))
                secondarySlot.grow(secondary.getCount());
        }

        input.shrink(1);

        resetProgress();
        setChanged();
    }

    private void resetProgress() {
        this.progress = 0;
    }

}
