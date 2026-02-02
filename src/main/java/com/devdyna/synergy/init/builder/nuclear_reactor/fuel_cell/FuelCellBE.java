package com.devdyna.synergy.init.builder.nuclear_reactor.fuel_cell;

import java.util.List;
import java.util.Optional;

import javax.annotation.Nullable;

import com.devdyna.synergy.api.basebe.be.MachineBE;
import com.devdyna.synergy.common.recipes.input.MonoItemInput;
import com.devdyna.synergy.init.builder.nuclear_reactor.fuel_cell.recipe.FuelCellRecipe;
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
public class FuelCellBE extends MachineBE {

    protected static final int INPUT_SLOT = 0;
    protected static final int OUTPUT_SLOT = 1;
    public static final String PROGRESS = "progress";
    public static final String RECIPE_INPUT = "recipe_input";
    private int progress = 0;
    private int maxProgress;
    private final ContainerData data;

    private ItemStack inputStack;

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
                return 2;
            }

        };
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new FuelCellMenu(i, inventory, this, this.data);
    }

    // require both due client-gui extraction
    @Override
    public void tickBoth() {
        if (level == null)
            return;

        saveItemInput();

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

    public void saveItemInput() {
        var item = getStorage().getStackInSlot(INPUT_SLOT).copy();
        var nbt = saveWithFullMetadata(level.registryAccess());
        setChanged();
        if (!item.isEmpty() && !nbt.contains(RECIPE_INPUT)) {
            var recipe = level.getRecipeManager()
                    .getRecipeFor(zRecipeTypes.FUEL_CELL_RECIPE.getType(),
                            new MonoItemInput(item), level);

            if (!recipe.isEmpty() && canInsert(OUTPUT_SLOT, recipe.get().value().getOutput())) {
                item.setCount(1);
                nbt.put(RECIPE_INPUT, item.save(level.registryAccess()));
                loadWithComponents(nbt, level.registryAccess());
                getStorage().extractItem(INPUT_SLOT, 1, false);
                setChanged();
            }
        }

    }

    public boolean isReadyToWork() {
        return hasRecipe() && getBlockState().getValue(BlockStateProperties.ENABLED);
    }

    public boolean isWorking() {
        return isReadyToWork() && progress < maxProgress && progress > 0;
    }

    private void craftItem() {
        ItemStack output = getRecipe().get().value().getOutput();
        getStorage().setStackInSlot(OUTPUT_SLOT, new ItemStack(output.getItem(),
                getStorage().getStackInSlot(OUTPUT_SLOT).getCount() + output.getCount()));
        var nbt = saveWithFullMetadata(level.registryAccess());
        nbt.remove(RECIPE_INPUT);
        inputStack = ItemStack.EMPTY;
        loadWithComponents(nbt, level.registryAccess());
        setChanged(level, getBlockPos(), getBlockState());
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

        var nbt = this.saveWithFullMetadata(level.registryAccess());
        if (nbt.contains(RECIPE_INPUT)) {
            var item = ItemStack.parseOptional(this.level.registryAccess(), nbt.getCompound(RECIPE_INPUT));

            return level.getRecipeManager()
                    .getRecipeFor(zRecipeTypes.FUEL_CELL_RECIPE.getType(),
                            new MonoItemInput(item), level);
        } else
            return Optional.empty();
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
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider pRegistries) {
        tag.putInt(PROGRESS, progress);
        if (inputStack != null && !inputStack.isEmpty())
            tag.put(RECIPE_INPUT, inputStack.save(pRegistries));
        super.saveAdditional(tag, pRegistries);

    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider pRegistries) {
        super.loadAdditional(tag, pRegistries);
        progress = tag.getInt(PROGRESS);
        inputStack = ItemStack.parseOptional(pRegistries, tag.getCompound(RECIPE_INPUT));

    }

    @Override
    public List<Integer> getInputSlotIndex() {
        return List.of(INPUT_SLOT);
    }

    @Override
    public List<Integer> getOutputSlotIndex() {
        return List.of(OUTPUT_SLOT);
    }

    public ItemStack getInputStack() {
        return inputStack;
    }

}
