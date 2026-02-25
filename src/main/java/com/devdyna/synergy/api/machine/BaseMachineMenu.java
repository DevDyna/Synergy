package com.devdyna.synergy.api.machine;

import com.devdyna.synergy.api.gui.BaseMenu;
import com.devdyna.synergy.api.machine.recipe.BaseMachineRecipeType;
import com.devdyna.synergy.api.registers.MachineType;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.material.Fluid;

import static com.devdyna.synergy.api.machine.BaseMachineBE.*;

public abstract class BaseMachineMenu extends BaseMenu {

    protected final ContainerData data;
    protected final Level level;
    public final BaseMachineBE blockEntity;
    public static final int PROGRESS_DATA = 2;
    public static final int ENERGY_DATA = 3;
    public static final int FLUID_DATA = 2;

    /**
     * A simple container data used on machines that DONT USE FLUIDS
     */
    public static final SimpleContainerData MACHINE_ITEM_DATA = new SimpleContainerData(
            PROGRESS_DATA + ENERGY_DATA);
    /**
     * A simple container data used on machines that USE FLUIDS
     */
    public static final SimpleContainerData MACHINE_FLUID_DATA = new SimpleContainerData(
            PROGRESS_DATA + ENERGY_DATA + FLUID_DATA);

    protected BaseMachineMenu(MenuType<?> menuType, int containerId, BlockEntity be, Inventory inv,
            ContainerData data) {
        super(menuType, containerId, be);
        this.blockEntity = (BaseMachineBE) be;
        this.level = inv.player.level();
        this.data = data;

        addMachineUpgradeSlot(blockEntity.getStorage(), BaseMachineBE.SLOT_UPGRADE_1, 180, 8);
        addMachineUpgradeSlot(blockEntity.getStorage(), BaseMachineBE.SLOT_UPGRADE_2, 180, 26);
        addMachineUpgradeSlot(blockEntity.getStorage(), BaseMachineBE.SLOT_UPGRADE_3, 180, 44);
        addMachineUpgradeSlot(blockEntity.getStorage(), BaseMachineBE.SLOT_UPGRADE_4, 180, 62);

        addDataSlots(data);
        addPlayerSlots(inv);
    }

    @Override
    public BlockEntity getBlockEntity() {
        return blockEntity;
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
    public Block[] getValidBlock() {
        return new Block[] { getMachine().block().get() };
    }

    public int getEnergyStored() {
        blockEntity.setChanged();
        return data.get(ENERGY_INDEX);
    }

    public int getEnergyUsage() {
        return data.get(ENERGY_USAGE);
    }

    public int getMaxEnergy() {
        return data.get(MAX_ENERGY_INDEX);
    }

    public int getFluidAmount() {
        return (getBlockEntity() instanceof FluidTankStorage tank)
                ? tank.getFluidStorage().getFluidAmount()
                : 0;
    }

    public int getMaxFluidAmount() {
        return (getBlockEntity() instanceof FluidTankStorage tank)
                ? tank.getFluidStorage().getCapacity()
                : 0;
    }

    public Fluid getFluid() {
        return (getBlockEntity() instanceof FluidTankStorage tank)
                ? tank.getFluidStorage().getFluid().getFluid()
                : null;
    }

    public Level getLevel() {
        return level;
    }

    public int getRemainProgress() {
        return isCrafting()
                ? data.get(MAX_PROGRESS_INDEX) - data.get(PROGRESS_INDEX)
                : 0;
    }

    public abstract MachineType<? extends BaseMachineBlock, ? extends BaseMachineBE, ? extends BaseMachineMenu, ? extends BaseMachineRecipeType<?>> getMachine();

}
