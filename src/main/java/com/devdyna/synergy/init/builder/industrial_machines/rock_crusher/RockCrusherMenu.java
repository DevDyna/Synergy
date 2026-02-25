package com.devdyna.synergy.init.builder.industrial_machines.rock_crusher;

import static com.devdyna.synergy.api.machine.BaseMachineBE.*;

import com.devdyna.synergy.api.machine.BaseMachineBE;
import com.devdyna.synergy.api.machine.BaseMachineBlock;
import com.devdyna.synergy.api.machine.BaseMachineMenu;
import com.devdyna.synergy.api.machine.recipe.BaseMachineRecipeType;
import com.devdyna.synergy.api.registers.MachineType;
import com.devdyna.synergy.init.types.zMachines;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.material.Fluid;

@SuppressWarnings("null")
public class RockCrusherMenu extends BaseMachineMenu {

    public final RockCrusherBE blockEntity;
    private final Level level;
    private final ContainerData data;

    public RockCrusherMenu(int c, Inventory i, FriendlyByteBuf d) {
        this(c, i, i.player.level().getBlockEntity(d.readBlockPos()), new SimpleContainerData(6));
    }

    public RockCrusherMenu(int i, Inventory inv, BlockEntity be, ContainerData data) {
        super(zMachines.ROCK_CRUSHER.menu().get(), i, be);
        this.blockEntity = ((RockCrusherBE) be);
        this.level = inv.player.level();
        this.data = data;
        addPlayerSlots(inv);
        addMachineInputSlot(blockEntity.getStorage(), RockCrusherBE.INPUT_SLOT, 47, 33);

        for (Integer slot : RockCrusherBE.OUTPUT_SLOTS)
            addMachineOutputSlot(blockEntity.getStorage(),
                    slot,
                    108 + (RockCrusherBE.OUTPUT_SLOTS.indexOf(slot) % 3 * 19),
                    15 + (RockCrusherBE.OUTPUT_SLOTS.indexOf(slot) / 3 * 19));

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
    public RockCrusherBE getBlockEntity() {
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
        return zMachines.ROCK_CRUSHER;
    }

}
