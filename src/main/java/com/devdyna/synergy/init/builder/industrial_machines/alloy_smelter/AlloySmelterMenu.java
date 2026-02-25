package com.devdyna.synergy.init.builder.industrial_machines.alloy_smelter;

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
public class AlloySmelterMenu extends BaseMachineMenu {

    public AlloySmelterMenu(int c, Inventory i, FriendlyByteBuf d) {
        this(c, i, i.player.level().getBlockEntity(d.readBlockPos()), MACHINE_ITEM_DATA);
    }

    public AlloySmelterMenu(int i, Inventory inv, BlockEntity be, ContainerData data) {
        super(zMachines.ALLOY_SMELTER.menu().get(), i, be, inv, data);
        addMachineInputSlot(blockEntity.getStorage(), AlloySmelterBE.INPUT_SLOT, 34, 33);
        addMachineOutputSlot(blockEntity.getStorage(), AlloySmelterBE.OUTPUT_SLOT, 119, 34);
        addMachineInputSlot(blockEntity.getStorage(), AlloySmelterBE.SECONDARY_INPUT, 54, 33);
    }

    @Override
    public MachineType<? extends BaseMachineBlock, ? extends BaseMachineBE, ? extends BaseMachineMenu, ? extends BaseMachineRecipeType<?>> getMachine() {
        return zMachines.ALLOY_SMELTER;
    }

}
