package com.devdyna.synergy.init.builder.industrial_machines.caster;

import com.devdyna.synergy.api.blockfactories.machine.BaseMachineBE;
import com.devdyna.synergy.api.blockfactories.machine.BaseMachineBlock;
import com.devdyna.synergy.api.blockfactories.machine.BaseMachineMenu;
import com.devdyna.synergy.api.blockfactories.machine.recipe.BaseMachineRecipeType;
import com.devdyna.synergy.api.registers.MachineType;
import com.devdyna.synergy.init.types.zMachines;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.*;
import net.minecraft.world.level.block.entity.BlockEntity;

@SuppressWarnings("null")
public class CasterMenu extends BaseMachineMenu {

    public CasterMenu(int c, Inventory i, FriendlyByteBuf d) {
        this(c, i, i.player.level().getBlockEntity(d.readBlockPos()), MACHINE_FLUID_DATA);
    }

    public CasterMenu(int i, Inventory inv, BlockEntity be, ContainerData data) {
        super(zMachines.CASTING_FACTORY.menu().get(), i, be, inv, data);
        addMachineInputSlot(blockEntity.getStorage(), CasterBE.INPUT_SLOT, 47, 33);
        addMachineOutputSlot(blockEntity.getStorage(), CasterBE.OUTPUT_SLOT, 119, 34);
    }

    @Override
    public MachineType<? extends BaseMachineBlock, ? extends BaseMachineBE, ? extends BaseMachineMenu, ? extends BaseMachineRecipeType<?>> getMachine() {
        return zMachines.CASTING_FACTORY;
    }

}
