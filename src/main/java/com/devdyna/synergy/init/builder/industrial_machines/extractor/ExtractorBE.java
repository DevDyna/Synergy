package com.devdyna.synergy.init.builder.industrial_machines.extractor;

import javax.annotation.Nullable;

import com.devdyna.synergy.api.FluidStorageTank;
import com.devdyna.synergy.api.blockfactories.machine.BaseMachineBE;
import com.devdyna.synergy.api.blockfactories.machine.FluidTankStorage;
import com.devdyna.synergy.api.utils.RecipeUtils;
import com.devdyna.synergy.common.recipes.input.MonoItemInput;
import com.devdyna.synergy.init.types.zMachines;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.IFluidHandler.FluidAction;

@SuppressWarnings("null")
public class ExtractorBE extends BaseMachineBE implements FluidTankStorage {

    public ExtractorBE(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
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
    public boolean initProgress() {

        if (getInput().isEmpty())
            return cancel();

        progress_cancel = false;

        var r = RecipeUtils.getRecipes(level, zMachines.EXTRACTOR, new MonoItemInput(getInput()));

        // no recipe
        if (r.isEmpty())
            return cancel();

        var recipe = r.get().value();

        if (!(checkSlot(getOutput(), recipe.getSecondaryItem().copy())
                && checkTank(getFluidStorage().getFluid(), recipe.getFluidOutput().copy(), getFluidCapacity())))
            return cancel();

        if (!calculateAndConsumeFE(recipe.getEnergy()))
            return cancel();

        update(true);

        this.maxProgress = calculateMaxProgress(recipe.getTime());

        return true;

    }

    @Override
    public void endProgress() {

        var recipe = RecipeUtils.getUnsafeRecipes(level, zMachines.EXTRACTOR, new MonoItemInput(getInput()));

        if (!recipe.getSecondaryItem().isEmpty() && calculateSecondarySuccess(recipe.getSecondaryItemChance()))
            updateOutputSlot(getOutput(), recipe.getSecondaryItem().copy(), OUTPUT_SLOT);

        if (!recipe.getFluidOutput().isEmpty()) {
            if (getFluidStorage().isEmpty())
                getFluidStorage().setFluid(recipe.getFluidOutput().copy());
            else if (FluidStack.isSameFluidSameComponents(recipe.getFluidOutput().copy(), getFluidStorage().getFluid()))
                getFluidStorage().fill(recipe.getFluidOutput().copy(), FluidAction.EXECUTE);
        }

        getInput().shrink(recipe.getInputItem().count());

    }

    @Override
    public ContainerData getContainerData() {
        return networkData;
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
