package com.devdyna.synergy.init.builder.industrial_machines.extractor;

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
public class ExtractorMenu extends BaseMachineMenu {

    public ExtractorMenu(int c, Inventory i, FriendlyByteBuf d) {
        this(c, i, i.player.level().getBlockEntity(d.readBlockPos()), MACHINE_FLUID_DATA);
    }

    public ExtractorMenu(int i, Inventory inv, BlockEntity be, ContainerData data) {
        super(zMachines.EXTRACTOR.menu().get(), i, be, inv, data);
        addMachineInputSlot(blockEntity.getStorage(), ExtractorBE.INPUT_SLOT, 47, 33);
        addMachineOutputSlot(blockEntity.getStorage(), ExtractorBE.OUTPUT_SLOT, 119, 34);
    }

    @Override
    public MachineType<? extends BaseMachineBlock, ? extends BaseMachineBE, ? extends BaseMachineMenu, ? extends BaseMachineRecipeType<?>> getMachine() {
        return zMachines.EXTRACTOR;
    }

}
