package com.devdyna.synergy.init.builder.industrial_machines.furnace;

import com.devdyna.synergy.api.machine.BaseMachineBE;
import com.devdyna.synergy.api.machine.BaseMachineBlock;
import com.devdyna.synergy.api.machine.BaseMachineMenu;
import com.devdyna.synergy.api.machine.recipe.BaseMachineRecipeType;
import com.devdyna.synergy.api.registers.MachineType;
import com.devdyna.synergy.init.types.zMachines;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.*;
import net.minecraft.world.level.block.entity.BlockEntity;

@SuppressWarnings("null")
public class ElectricFurnaceMenu extends BaseMachineMenu {

    public ElectricFurnaceMenu(int c, Inventory i, FriendlyByteBuf d) {
        this(c, i, i.player.level().getBlockEntity(d.readBlockPos()), MACHINE_ITEM_DATA);
    }

    public ElectricFurnaceMenu(int i, Inventory inv, BlockEntity be, ContainerData data) {
        super(zMachines.ELECTRIC_FURNACE.menu().get(), i, be, inv, data);
        addMachineInputSlot(blockEntity.getStorage(), ElectricFurnaceBE.INPUT_SLOT, 47, 33);
        addMachineOutputSlot(blockEntity.getStorage(), ElectricFurnaceBE.OUTPUT_SLOT, 119, 34);
    }

    @Override
    public MachineType<? extends BaseMachineBlock, ? extends BaseMachineBE, ? extends BaseMachineMenu, ? extends BaseMachineRecipeType<?>> getMachine() {
        return zMachines.ELECTRIC_FURNACE;
    }

}
