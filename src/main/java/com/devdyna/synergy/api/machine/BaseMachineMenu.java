package com.devdyna.synergy.api.machine;

import com.devdyna.synergy.api.beLogic.MachineItemAutomation;
import com.devdyna.synergy.api.gui.BaseMenu;
import com.devdyna.synergy.api.machine.recipe.BaseMachineRecipeType;
import com.devdyna.synergy.api.registers.MachineType;

import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;

public abstract class BaseMachineMenu extends BaseMenu {

    protected BaseMachineMenu(MenuType<?> menuType, int containerId, BlockEntity blockEntity) {
        super(menuType, containerId, blockEntity);
        if (blockEntity instanceof MachineItemAutomation storage) {
            addMachineUpgradeSlot(storage.getStorage(), BaseMachineBE.SLOT_UPGRADE_1, 180, 8);
            addMachineUpgradeSlot(storage.getStorage(), BaseMachineBE.SLOT_UPGRADE_2, 180, 26);
            addMachineUpgradeSlot(storage.getStorage(), BaseMachineBE.SLOT_UPGRADE_3, 180, 44);
            addMachineUpgradeSlot(storage.getStorage(), BaseMachineBE.SLOT_UPGRADE_4, 180, 62);
        }
    }

    @Override
    public Block[] getValidBlock() {
        return new Block[] { getMachine().block().get() };
    }

    protected abstract int getEnergyStored();

    protected abstract int getMaxEnergy();

    protected abstract int getRemainProgress();

    public abstract MachineType<? extends BaseMachineBlock, ? extends BaseMachineBE, ? extends BaseMachineMenu, ? extends BaseMachineRecipeType<?>> getMachine();

}
