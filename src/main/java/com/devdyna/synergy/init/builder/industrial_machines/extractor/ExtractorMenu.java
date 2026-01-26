package com.devdyna.synergy.init.builder.industrial_machines.extractor;

import static com.devdyna.synergy.api.machine.BaseMachineBE.*;

import com.devdyna.synergy.api.MachineType;
import com.devdyna.synergy.api.machine.BaseMachineBE;
import com.devdyna.synergy.api.machine.BaseMachineBlock;
import com.devdyna.synergy.api.machine.BaseMachineMenu;
import com.devdyna.synergy.api.machine.recipe.BaseMachineRecipeType;
import com.devdyna.synergy.init.types.zMachines;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.material.Fluid;

@SuppressWarnings("null")
public class ExtractorMenu extends BaseMachineMenu {

    public final ExtractorBE blockEntity;
    private final Level level;
    private final ContainerData data;

    public ExtractorMenu(int c, Inventory i, FriendlyByteBuf d) {
        this(c, i, i.player.level().getBlockEntity(d.readBlockPos()), new SimpleContainerData(6));
    }

    public ExtractorMenu(int i, Inventory inv, BlockEntity be, ContainerData data) {
        super(zMachines.EXTRACTOR.menu().get(), i, be);
        this.blockEntity = ((ExtractorBE) be);
        this.level = inv.player.level();
        this.data = data;
        addPlayerSlots(inv);
        addMachineInputSlot(blockEntity.getStorage(), ExtractorBE.INPUT_SLOT, 47, 33);
        addMachineOutputSlot(blockEntity.getStorage(), ExtractorBE.OUTPUT_SLOT, 119, 34);
        addDataSlots(data);
    }

    public boolean isCrafting() {
        return data.get(PROGRESS_INDEX) > 0;
    }

    public int getScaledArrowProgress() {
        int progress = data.get(PROGRESS_INDEX);
        int maxProgress = data.get(MAX_PROGRESS_INDEX);
        int sizeArrow = 24;
        return maxProgress != 0
                &&
                progress != 0 ? progress * sizeArrow / maxProgress : 0;
    }

    @Override
    public ExtractorBE getBlockEntity() {
        return blockEntity;
    }

    @Override
    public Level getLevel() {
        return level;
    }

    @Override
    protected int getEnergyStored() {
        blockEntity.setChanged();
        return data.get(ENERGY_INDEX);
    }

    @Override
    protected int getMaxEnergy() {
        return data.get(MAX_ENERGY_INDEX);
    }

    public int getFluidAmount() {
        return data.get(FLUID_INDEX);
    }

    public int getMaxFluidAmount() {
        return data.get(MAX_FLUID_INDEX);
    }

    public Fluid getFluid() {
        return getBlockEntity().getFluidStorage().getFluid().getFluid();
    }

    @Override
    protected int getRemainProgress() {
        return isCrafting() ? data.get(MAX_PROGRESS_INDEX) - data.get(PROGRESS_INDEX) : 0;
    }

    @Override
    public MachineType<? extends BaseMachineBlock, ? extends BaseMachineBE, ? extends BaseMachineMenu, ? extends BaseMachineRecipeType<?>> getMachine() {
        return zMachines.EXTRACTOR;
    }

}
