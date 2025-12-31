package com.devdyna.synergy.init.machine.macerator;

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
public class MaceratorMenu extends BaseMachineMenu {

    public final MaceratorBE blockEntity;
    private final Level level;
    private final ContainerData data;

    public MaceratorMenu(int c, Inventory i, FriendlyByteBuf d) {
        this(c, i, i.player.level().getBlockEntity(d.readBlockPos()), new SimpleContainerData(4));
    }

    public MaceratorMenu(int i, Inventory inv, BlockEntity be, ContainerData data) {
        super(zMachines.MACERATOR.menu().get(), i, be);
        this.blockEntity = ((MaceratorBE) be);
        this.level = inv.player.level();
        this.data = data;
        addPlayerSlots(inv);
        addMachineInputSlot(blockEntity.getStorage(), MaceratorBE.INPUT_SLOT, 47, 33);
        addMachineOutputSlot(blockEntity.getStorage(), MaceratorBE.OUTPUT_SLOT, 119, 25);
        addMachineOutputSlot(blockEntity.getStorage(), MaceratorBE.EXTRA_SLOT, 119, 50);

        addSingleMachineSlot((u1)->u1.is(zItemTag.UPGRADES),blockEntity.getStorage(), MaceratorBE.SLOT_UPGRADE_1, 180, 8);
        addSingleMachineSlot((u2)->u2.is(zItemTag.UPGRADES),blockEntity.getStorage(), MaceratorBE.SLOT_UPGRADE_2, 180, 26);
        addSingleMachineSlot((u3)->u3.is(zItemTag.UPGRADES),blockEntity.getStorage(), MaceratorBE.SLOT_UPGRADE_3, 180, 44);
        addSingleMachineSlot((u4)->u4.is(zItemTag.UPGRADES),blockEntity.getStorage(), MaceratorBE.SLOT_UPGRADE_4, 180, 62);
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
        return zMachines.MACERATOR;
    }

}
