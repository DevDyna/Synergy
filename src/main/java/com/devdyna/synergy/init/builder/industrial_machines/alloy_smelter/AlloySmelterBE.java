package com.devdyna.synergy.init.builder.industrial_machines.alloy_smelter;

import java.util.List;
import java.util.Optional;

import javax.annotation.Nullable;

import com.devdyna.synergy.api.blockfactories.machine.BaseMachineBE;
import com.devdyna.synergy.api.blockfactories.machine.ExtraMachineSlots;
import com.devdyna.synergy.api.recipes.inputs.BiItemInput;
import com.devdyna.synergy.api.utils.RecipeUtils;
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

@SuppressWarnings("null")
public class AlloySmelterBE extends BaseMachineBE implements ExtraMachineSlots {

    public static final int SECONDARY_INPUT = 6;

    public AlloySmelterBE(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }

    @Override
    public int getMachineSlots() {
        return 7;
    }

    @Override
    public List<Integer> getInputSlotIndex() {
        return List.of(INPUT_SLOT, SECONDARY_INPUT);
    }

    public AlloySmelterBE(BlockPos pos, BlockState blockState) {
        this(zMachines.ALLOY_SMELTER.blockentity().get(), pos, blockState);
    }

    @Override
    @Nullable
    public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new AlloySmelterMenu(i, inventory, this, this.networkData);
    }

    Optional<RecipeHolder<AlloySmelterRecipeType>> saved_recipe = null;

    @Override
    public ItemStack getInput() {
        return invertedRecipe
                ? super.getInput()
                : getStorage().getStackInSlot(SECONDARY_INPUT);
    }

    public ItemStack getSecondaryInput() {
        return invertedRecipe
                ? getStorage().getStackInSlot(SECONDARY_INPUT)
                : super.getInput();
    }

    boolean invertedRecipe = false;

    @Override
    public boolean initProgress() {

        if (getInput().isEmpty())
            return cancel();

        progress_cancel = false;

        saved_recipe = RecipeUtils.getRecipes(level, zMachines.ALLOY_SMELTER,
                new BiItemInput(getInput(), getSecondaryInput()));

        if (saved_recipe.isEmpty()) {
            invertedRecipe = !invertedRecipe;
            return cancel();
        }

        AlloySmelterRecipeType recipe;

        recipe = saved_recipe.get().value();

        if (!checkSlot(getOutput(), recipe.getOutputItem().copy()))
            return cancel();

        if (!calculateAndConsumeFE(recipe.getEnergy()))
            return cancel();

        update(true);

        this.maxProgress = calculateMaxProgress(recipe.getTime());

        return true;

    }

    @Override
    public void endProgress() {

        AlloySmelterRecipeType recipe;

        recipe = saved_recipe.get().value();

        updateOutputSlot(getOutput(), recipe.getOutputItem().copy(), OUTPUT_SLOT);

        getInput().shrink(recipe.getInputItem().count());
        getSecondaryInput().shrink(recipe.getCatalystItem().count());
    }

    @Override
    public ContainerData getContainerData() {
        return networkData;
    }

    @Override
    public SlotBuilder getSlotTypes() {
        return SlotBuilder.of(1).set(SECONDARY_INPUT, SlotType.INPUT);
    }

}
