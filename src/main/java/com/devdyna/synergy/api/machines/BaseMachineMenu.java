package com.devdyna.synergy.api.machines;

import com.devdyna.synergy.api.MachineType;
import com.devdyna.synergy.api.gui.BaseMenu;
import com.devdyna.synergy.api.machines.recipe.BaseMachineRecipeType;

import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;

public abstract class BaseMachineMenu extends BaseMenu {

    protected BaseMachineMenu(MenuType<?> menuType, int containerId, BlockEntity blockEntity) {
        super(menuType, containerId, blockEntity);
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
