package com.devdyna.synergy.init.builder.industrial_machines.compressor;

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
public class CompressorMenu extends BaseMachineMenu {

    public CompressorMenu(int c, Inventory i, FriendlyByteBuf d) {
        this(c, i, i.player.level().getBlockEntity(d.readBlockPos()), MACHINE_ITEM_DATA);
    }

    public CompressorMenu(int i, Inventory inv, BlockEntity be, ContainerData data) {
        super(zMachines.COMPRESSOR.menu().get(), i, be, inv, data);
        addMachineInputSlot(blockEntity.getStorage(), CompressorBE.INPUT_SLOT, 47, 15);
        addMachineOutputSlot(blockEntity.getStorage(), CompressorBE.OUTPUT_SLOT, 119, 34);
        addMachineInputSlot(blockEntity.getStorage(), CompressorBE.PLATE_SLOT, 47, 51);
    }

    @Override
    public MachineType<? extends BaseMachineBlock, ? extends BaseMachineBE, ? extends BaseMachineMenu, ? extends BaseMachineRecipeType<?>> getMachine() {
        return zMachines.COMPRESSOR;
    }

}
