package com.devdyna.synergy.init.machine.alloy_smelter;

import com.devdyna.synergy.init.machine.core.BaseMachineMenu;
import com.devdyna.synergy.init.machine.core.recipe.BaseMachineRecipeType;

import static com.devdyna.synergy.init.machine.core.BaseMachineBE.*;

import com.devdyna.synergy.api.MachineType;
import com.devdyna.synergy.init.machine.core.BaseMachineBE;
import com.devdyna.synergy.init.machine.core.BaseMachineBlock;
import com.devdyna.synergy.init.types.zMachines;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

@SuppressWarnings("null")
public class AlloySmelterMenu extends BaseMachineMenu {

    public final AlloySmelterBE blockEntity;
    private final Level level;
    private final ContainerData data;

    public AlloySmelterMenu(int c, Inventory i, FriendlyByteBuf d) {
        this(c, i, i.player.level().getBlockEntity(d.readBlockPos()), new SimpleContainerData(4));
    }

    public AlloySmelterMenu(int i, Inventory inv, BlockEntity be, ContainerData data) {
        super(zMachines.ALLOY_SMELTER.menu().get(), i, be);
        this.blockEntity = ((AlloySmelterBE) be);
        this.level = inv.player.level();
        this.data = data;
        addPlayerSlots(inv);
        addMachineInputSlot(blockEntity.getStorage(), 0, 34, 33);
        addMachineOutputSlot(blockEntity.getStorage(), 1, 119, 34);
        addMachineInputSlot(blockEntity.getStorage(), 2, 54, 33);
        
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
    public BlockEntity getBlockEntity() {
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

    @Override
    protected int getRemainProgress() {
        return isCrafting() ? data.get(MAX_PROGRESS_INDEX) - data.get(PROGRESS_INDEX) : 0;
    }

    @Override
    public MachineType<? extends BaseMachineBlock, ? extends BaseMachineBE, ? extends BaseMachineMenu, ? extends BaseMachineRecipeType<?>> getMachine() {
        return zMachines.ALLOY_SMELTER;
    }

}
