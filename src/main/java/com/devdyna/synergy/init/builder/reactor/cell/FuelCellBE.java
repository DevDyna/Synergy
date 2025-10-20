package com.devdyna.synergy.init.builder.reactor.cell;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import javax.annotation.Nullable;

import com.devdyna.synergy.api.coreBE.be.BEMachineIO;
import com.devdyna.synergy.client.gui.fuel_cell.FuelCellMenu;
import com.devdyna.synergy.init.recipeTypes.input.MonoItemInput;
import com.devdyna.synergy.init.recipeTypes.type.FuelCellRecipe;
import com.devdyna.synergy.init.types.zBlockEntities;
import com.devdyna.synergy.init.types.zHandlers;
import com.devdyna.synergy.init.types.zRecipeTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.items.ItemStackHandler;

@SuppressWarnings("null")
public class FuelCellBE extends BaseContainerBlockEntity implements WorldlyContainer {

    private int INPUT = 0;
    private int OUTPUT = 1;

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
                return getContainerSize();
            }
        };
    }

    // @Nullable
    // @Override
    // public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
    //     return new FuelCellMenu(i, inventory, this, this.data);
    // }

    // require both due client-gui extraction
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
        var item = items.get(INPUT).copy();
        var nbt = saveWithFullMetadata(level.registryAccess());
        if (!item.isEmpty() && !nbt.contains(RECIPE_INPUT)) {
            var recipe = level.getRecipeManager()
                    .getRecipeFor(zRecipeTypes.FUEL_CELL_RECIPE.getType(),
                            new MonoItemInput(item), level);

            if (!recipe.isEmpty() && canInsert(OUTPUT, recipe.get().value().getOutput())) {
                item.setCount(1);
                nbt.put(RECIPE_INPUT, item.save(level.registryAccess()));
                loadWithComponents(nbt, level.registryAccess());
                setChanged(level, getBlockPos(), getBlockState());
                items.get(INPUT).shrink(1);
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
        items.get(OUTPUT).setCount(items.get(OUTPUT).getCount() + output.getCount());
        var nbt = saveWithFullMetadata(level.registryAccess());
        nbt.remove(RECIPE_INPUT);
        inputStack = ItemStack.EMPTY;
        loadWithComponents(nbt, level.registryAccess());
        setChanged(level, getBlockPos(), getBlockState());
    }

    private void resetProgress() {
        progress = 0;
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
        return !getRecipe().isEmpty() && canInsert(OUTPUT, getRecipe().get().value().getOutput());
    }

    private boolean canInsert(int slotID, ItemStack item) {
        var slot = items.get(INPUT);
        return (slot.isEmpty() ||
                slot.getItem() == item.getItem()) &&
                (slot.isEmpty() ? 64
                        : slot.getMaxStackSize()) >= slot.getCount() + item.getCount();
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

    public static final int SIZE = 2;
    // Our item stack list. This is not final due to #setItems existing.
    private NonNullList<ItemStack> items = NonNullList.withSize(SIZE, ItemStack.EMPTY);

    // The container size, like before.
    @Override
    public int getContainerSize() {
        return SIZE;
    }

    // The getter for our item stack list.
    @Override
    protected NonNullList<ItemStack> getItems() {
        return items;
    }

    public NonNullList<ItemStack> getStorage() {
        return items;
    }

    // The setter for our item stack list.
    @Override
    protected void setItems(NonNullList<ItemStack> items) {
        this.items = items;
    }

    @Override
    protected AbstractContainerMenu createMenu(int arg0, Inventory arg1) {
        return new FuelCellMenu(arg0, arg1, this, this.data);
    }

    @Override
    protected Component getDefaultName() {
        return Component.translatable(this.getBlockState().getBlock().getDescriptionId());
    }

    @Override
    public boolean canPlaceItemThroughFace(int slot, ItemStack itemStack,
            @Nullable Direction direction) {
        return INPUT == slot;
    }

    @Override
    public boolean canTakeItemThroughFace(int slot, ItemStack itemStack,
            Direction direction) {
        return OUTPUT == slot;
    }

    @Override
    public int[] getSlotsForFace(Direction direction) {
        return new int[] { INPUT, OUTPUT };
    }

}
