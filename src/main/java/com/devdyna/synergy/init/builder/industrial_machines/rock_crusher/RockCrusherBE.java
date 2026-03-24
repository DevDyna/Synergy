package com.devdyna.synergy.init.builder.industrial_machines.rock_crusher;

import java.util.List;
import javax.annotation.Nullable;

import com.devdyna.synergy.api.FluidStorageTank;
import com.devdyna.synergy.api.blockfactories.machine.BaseMachineBE;
import com.devdyna.synergy.api.blockfactories.machine.ExtraMachineSlots;
import com.devdyna.synergy.api.blockfactories.machine.FluidTankStorage;
import com.devdyna.synergy.api.codec.ChanceOutputItem;
import com.devdyna.synergy.api.recipes.inputs.ItemFluidInput;
import com.devdyna.synergy.api.utils.ArrayUtils;
import com.devdyna.synergy.api.utils.RecipeUtils;
import com.devdyna.synergy.init.types.zMachines;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.fluids.capability.IFluidHandler.FluidAction;

@SuppressWarnings("null")
public class RockCrusherBE extends BaseMachineBE implements FluidTankStorage, ExtraMachineSlots {

    public static final int OUTPUT_EXTRA_1 = 6;
    public static final int OUTPUT_EXTRA_2 = 7;
    public static final int OUTPUT_EXTRA_3 = 8;
    public static final int OUTPUT_EXTRA_4 = 9;
    public static final int OUTPUT_EXTRA_5 = 10;
    public static final int OUTPUT_EXTRA_6 = 11;
    public static final int OUTPUT_EXTRA_7 = 12;
    public static final int OUTPUT_EXTRA_8 = 13;

    public static final List<Integer> EXTRA_OUTPUT_SLOTS = List.of(OUTPUT_EXTRA_1, OUTPUT_EXTRA_2, OUTPUT_EXTRA_3,
            OUTPUT_EXTRA_4, OUTPUT_EXTRA_5, OUTPUT_EXTRA_6, OUTPUT_EXTRA_7, OUTPUT_EXTRA_8);

    public static final List<Integer> OUTPUT_SLOTS = ArrayUtils.concat(EXTRA_OUTPUT_SLOTS,OUTPUT_SLOT);

    public RockCrusherBE(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }

    @Override
    public int getMachineSlots() {
        return 14;
    }

    public RockCrusherBE(BlockPos pos, BlockState blockState) {
        this(zMachines.ROCK_CRUSHER.blockentity().get(), pos, blockState);
    }

    @Override
    @Nullable
    public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new RockCrusherMenu(i, inventory, this, this.networkData);
    }

    @Override
    public boolean initProgress() {

        if (getFluidStorage().isEmpty())
            return cancel();

        progress_cancel = false;

        var r = RecipeUtils.getRecipes(level, zMachines.ROCK_CRUSHER,
                new ItemFluidInput(getFluidStorage().getFluid(), getInput()));

        // no recipe
        if (r.isEmpty())
            return cancel();

        var recipe = r.get().value();

        if (getFluidStorage().getFluidAmount() < recipe.getFluidInput().amount()) {
            return cancel();
        }

        if (!recipe.getResult().stream()
                .map(a -> recipe.getResult().indexOf(a))
                .allMatch(index -> checkSlot(
                        getStorage().getStackInSlot(index + 5),
                        recipe.getResult().get(index).item().copy())))
            return cancel();

        if (!calculateAndConsumeFE(recipe.getEnergy()))
            return cancel();

        update(true);

        this.maxProgress = calculateMaxProgress(recipe.getTime());

        return true;

    }

    @Override
    public void endProgress() {

        var recipe = RecipeUtils.getUnsafeRecipes(level, zMachines.ROCK_CRUSHER,
                new ItemFluidInput(getFluidStorage().getFluid(), getInput()));

        for (ChanceOutputItem result : recipe.getResult())
            if (!result.item().copy().isEmpty() && calculateSecondarySuccess(result.chance()))
                updateOutputSlot(getStorage().getStackInSlot(recipe.getResult().indexOf(result) + 5),
                        result.item().copy(), recipe.getResult().indexOf(result) + 5);

        getFluidStorage().drain(calculateMBUsage(recipe.getFluidInput().amount()), FluidAction.EXECUTE);

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
        return FluidTankType.INPUT;
    }

    @Override
    public List<Integer> getOutputSlotIndex() {
        return OUTPUT_SLOTS;
    }

    @Override
    public SlotBuilder getSlotTypes() {
        return SlotBuilder.of(8).setAll(SlotType.OUTPUT, EXTRA_OUTPUT_SLOTS.toArray(Integer[]::new));
    }
}
