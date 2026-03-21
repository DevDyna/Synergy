package com.devdyna.synergy.init.builder.industrial_machines.compressor;

import javax.annotation.Nullable;

import com.devdyna.synergy.api.blockfactories.machine.BaseMachineBE;
import com.devdyna.synergy.api.blockfactories.machine.ExtraMachineSlots;
import com.devdyna.synergy.api.utils.RecipeUtils;
import com.devdyna.synergy.common.recipes.input.BiItemInput;
import com.devdyna.synergy.init.types.zMachines;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

@SuppressWarnings("null")
public class CompressorBE extends BaseMachineBE implements ExtraMachineSlots {

    public static final int PLATE_SLOT = 6;

    public CompressorBE(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }

    @Override
    public int getMachineSlots() {
        return 7;
    }

    public CompressorBE(BlockPos pos, BlockState blockState) {
        this(zMachines.COMPRESSOR.blockentity().get(), pos, blockState);
    }

    @Override
    @Nullable
    public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new CompressorMenu(i, inventory, this, this.networkData);
    }

    @Override
    public boolean initProgress() {

        if (getInput().isEmpty())
            return cancel();

        progress_cancel = false;

        var r = RecipeUtils.getRecipes(level, zMachines.COMPRESSOR, new BiItemInput(getInput(), getPlateSlot()));

        // no recipe
        if (r.isEmpty())
            return cancel();

        var recipe = r.get().value();

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

        var recipe = RecipeUtils.getUnsafeRecipes(level, zMachines.COMPRESSOR,
                new BiItemInput(getInput(), getPlateSlot()));

        updateOutputSlot(getOutput(), recipe.getOutputItem().copy(), OUTPUT_SLOT);

        if (recipe.consumeCatalyst())
            getPlateSlot().shrink(recipe.getCatalystItem().count());

        getInput().shrink(recipe.getInputItem().count());
    }

    @Override
    public ContainerData getContainerData() {
        return networkData;
    }

    @Override
    public SlotBuilder getSlotTypes() {
        return SlotBuilder.of(1).set(PLATE_SLOT, SlotType.INPUT);
    }

    public ItemStack getPlateSlot() {
        return getStorage().getStackInSlot(PLATE_SLOT);
    }

}
