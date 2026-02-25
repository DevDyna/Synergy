package com.devdyna.synergy.init.builder.industrial_machines.furnace;

import java.util.Optional;
import javax.annotation.Nullable;

import com.devdyna.synergy.api.machine.BaseMachineBE;
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

@SuppressWarnings("null")
public class ElectricFurnaceBE extends BaseMachineBE {

    public ElectricFurnaceBE(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
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

    private MixedRecipeHolder recipeHolder;

    @Override
    public boolean initProgress() {

        if (getInput().isEmpty())
            return cancel();

        progress_cancel = false;

        Optional<RecipeHolder<ElectricFurnaceRecipeType>> r = level.getRecipeManager()
                .getRecipeFor(zMachines.ELECTRIC_FURNACE.recipe().getType(),
                        new MonoItemInput(getInput()), level);

        Optional<RecipeHolder<SmeltingRecipe>> r2 = level.getRecipeManager()
                .getRecipeFor(RecipeType.SMELTING, new SingleRecipeInput(getInput()), level);

        ElectricFurnaceRecipeType electric = null;
        SmeltingRecipe smelting = null;

        if (r.isEmpty()) {
            if (r2.isEmpty() || Common.DISABLE_MACHINE_FURNACE_PROCESS_VANILLA.get())
                return cancel();

            smelting = r2.get().value();
        } else
            electric = r.get().value();

        recipeHolder = new MixedRecipeHolder(

                (r.isEmpty()
                        ? getCalculatedDelay(smelting)
                        : electric.getTime()),

                (r.isEmpty()
                        ? Common.MACHINE_FURNACE_PROCESS_VANILLA_FE_COST.get()
                        : electric.getEnergy()),

                (r.isEmpty()
                        ? smelting.getResultItem(level.registryAccess()).copy()
                        : electric.getOutputItem().copy()));

        if (!checkSlot(getOutput(), recipeHolder.result_item)) {
            return cancel();
        }

        if (!calculateAndConsumeFE(recipeHolder.energy_every_tick))
            return cancel();

        update(true);

        this.maxProgress = calculateMaxProgress(recipeHolder.tick_delay);

        return true;

    }

    @Override
    public void endProgress() {

        updateOutputSlot(getOutput(), recipeHolder.result_item, OUTPUT_SLOT);

        getInput().shrink(1);
    }

    @Override
    public ContainerData getContainerData() {
        return networkData;
    }

    public static int getCalculatedDelay(SmeltingRecipe recipe) {
        return Common.DISABLE_MACHINE_FURNACE_VANILLA_TICK_REDUCER.get() ? recipe.getCookingTime()
                : Math.max(Common.MACHINE_FURNACE_PROCESS_VANILLA_MIN_TICK_DELAY.get(), recipe.getCookingTime()
                        * Common.MACHINE_FURNACE_PROCESS_VANILLA_PERCENTUAGE_TICK_DELAY.get() / 100);
    }

    private record MixedRecipeHolder(int tick_delay, int energy_every_tick, ItemStack result_item) {

    }

}
