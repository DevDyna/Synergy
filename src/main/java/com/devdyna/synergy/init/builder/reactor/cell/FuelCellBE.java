package com.devdyna.synergy.init.builder.reactor.cell;

import java.util.Optional;

import javax.annotation.Nullable;

import com.devdyna.synergy.api.menu.BEMenu;
import com.devdyna.synergy.client.gui.fuel_cell.FuelCellMenu;
import com.devdyna.synergy.init.recipeTypes.input.MonoItemInput;
import com.devdyna.synergy.init.recipeTypes.type.FuelCellRecipe;
import com.devdyna.synergy.init.types.zBlockEntities;
import com.devdyna.synergy.init.types.zRecipeTypes;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

@SuppressWarnings("null")
public class FuelCellBE extends BEMenu {

    private static final int INPUT_SLOT = 0;
    private static final int OUTPUT_SLOT = 1;
    public static final String PROGRESS = "progress";
    // public static final String MAX_PROGRESS = "max_progress";
    private int progress = 0;
    private int maxProgress;
    private final ContainerData data;

    public FuelCellBE(BlockPos pos, BlockState blockState) {
        super(zBlockEntities.FUEL_CELL.get(), pos, blockState);
        data = new ContainerData() {

            @Override
            public int get(int i) {
                return switch (i) {
                    case 0 -> getProgress();
                    case 1 -> getMaxProgress();
                    default -> 0;
                };
            }

            @Override
            public void set(int i, int value) {
                switch (i) {
                    case 0:
                        setProgress(value);
                    case 1:
                        setMaxProgress(value);
                }
            }

            @Override
            public int getCount() {
                return MachineSlots();
            }
        };
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new FuelCellMenu(i, inventory, this, this.data);
    }

    public void tickServer() {
        if (level == null)
            return;

        if (isReadyToWork()) {

            maxProgress = getRecipe().get().value().getDuration();
            progress++;
            setChanged(level, getBlockPos(), getBlockState());
            if (progress >= maxProgress) {
                craftItem();
                resetProgress();
            }
        } else {
            resetProgress();
        }
    }

    public boolean isReadyToWork(){
        return hasRecipe() && getBlockState().getValue(BlockStateProperties.ENABLED);
    }

    public boolean isWorking() {
        return isReadyToWork() && progress < maxProgress && progress > 0;
    }

    private void craftItem() {
        //TODO implement item caching to prevent fe extraction loop
        ItemStack output = getRecipe().get().value().getOutput();
        getStorage().extractItem(INPUT_SLOT, 1, false);
        getStorage().setStackInSlot(OUTPUT_SLOT, new ItemStack(output.getItem(),
                getStorage().getStackInSlot(OUTPUT_SLOT).getCount() + output.getCount()));
    }

    private void resetProgress() {
        progress = 0;
    }

    @Override
    public int MachineSlots() {
        return 2;
    }

    public int getProgress() {
        return progress;
    }

    public int getMaxProgress() {
        return maxProgress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public void setMaxProgress(int maxProgress) {
        this.maxProgress = maxProgress;
    }

    public Optional<RecipeHolder<FuelCellRecipe>> getRecipe() {
        if (level == null)
            return null;
        return level.getRecipeManager()
                .getRecipeFor(zRecipeTypes.FUEL_CELL_RECIPE.getType(),
                        new MonoItemInput(getStorage().getStackInSlot(INPUT_SLOT)), level);
    }

    public boolean hasRecipe() {
        return !getRecipe().isEmpty() && canInsert(OUTPUT_SLOT, getRecipe().get().value().getOutput());
    }

    private boolean canInsert(int slotID, ItemStack output) {
        var slot = getStorage().getStackInSlot(slotID);
        return (slot.isEmpty() ||
                slot.getItem() == output.getItem()) &&
                (slot.isEmpty() ? 64
                        : slot.getMaxStackSize()) >= slot.getCount() + output.getCount();
    }

    @Override
    protected void saveAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        pTag.put("inventory", getStorage().serializeNBT(pRegistries));
        pTag.putInt(PROGRESS, progress);
        super.saveAdditional(pTag, pRegistries);
    }

    @Override
    protected void loadAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        super.loadAdditional(pTag, pRegistries);
        getStorage().deserializeNBT(pRegistries, pTag.getCompound("inventory"));
        progress = pTag.getInt(PROGRESS);
    }
}
