package com.devdyna.synergy.init.machine.furnace;

import static com.devdyna.synergy.api.machine.BaseMachineBE.*;

import com.devdyna.synergy.api.MachineType;
import com.devdyna.synergy.api.machine.BaseMachineBE;
import com.devdyna.synergy.api.machine.BaseMachineBlock;
import com.devdyna.synergy.api.machine.BaseMachineMenu;
import com.devdyna.synergy.api.machine.recipe.BaseMachineRecipeType;
import com.devdyna.synergy.init.types.zItemTag;
import com.devdyna.synergy.init.types.zMachines;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

@SuppressWarnings("null")
public class ElectricFurnaceMenu extends BaseMachineMenu {

    public final ElectricFurnaceBE blockEntity;
    private final Level level;
    private final ContainerData data;

    public ElectricFurnaceMenu(int c, Inventory i, FriendlyByteBuf d) {
        this(c, i, i.player.level().getBlockEntity(d.readBlockPos()), new SimpleContainerData(4));
    }

    public ElectricFurnaceMenu(int i, Inventory inv, BlockEntity be, ContainerData data) {
        super(zMachines.ELECTRIC_FURNACE.menu().get(), i, be);
        this.blockEntity = ((ElectricFurnaceBE) be);
        this.level = inv.player.level();
        this.data = data;
        addPlayerSlots(inv);
        addMachineInputSlot(blockEntity.getStorage(), 0, 47, 33);
        addMachineOutputSlot(blockEntity.getStorage(), 1, 119, 34);

        addSingleMachineSlot((u1) -> u1.is(zItemTag.UPGRADES), blockEntity.getStorage(), 2, 180, 8);
        addSingleMachineSlot((u2) -> u2.is(zItemTag.UPGRADES), blockEntity.getStorage(), 3, 180, 26);
        addSingleMachineSlot((u3) -> u3.is(zItemTag.UPGRADES), blockEntity.getStorage(), 4, 180, 44);
        addSingleMachineSlot((u4) -> u4.is(zItemTag.UPGRADES), blockEntity.getStorage(), 5, 180, 62);
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
        return zMachines.ELECTRIC_FURNACE;
    }

}
