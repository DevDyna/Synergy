package com.devdyna.synergy.init.builder.automation.chopper;

import com.devdyna.synergy.api.gui.BaseMenu;
import com.devdyna.synergy.init.types.zBlocks;
import com.devdyna.synergy.init.types.zContainer;
import com.devdyna.synergy.init.types.zItemTag;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;

public class ChopperMenu extends BaseMenu {

    public final ChopperBE blockEntity;
    private final Level level;
    private final ContainerData data;

    public ChopperMenu(int pContainerId, Inventory inv, FriendlyByteBuf extraData) {
        this(pContainerId, inv, inv.player.level().getBlockEntity(extraData.readBlockPos()),
                new SimpleContainerData(6));
    }

    public ChopperMenu(int i, Inventory inv, BlockEntity be, ContainerData data) {
        super(zContainer.CHOPPER.get(), i, be);
        this.blockEntity = ((ChopperBE) be);
        this.level = inv.player.level();
        this.data = data;
        addPlayerSlots(inv);

        addMachineSlot(a -> a.is(ItemTags.AXES),
                blockEntity.getStorage(), ChopperBE.AXE_SLOT, 62, 56 + 6);

        addMachineSlot(a -> AbstractFurnaceBlockEntity.isFuel(a) || a.is(zItemTag.CHOPPER_ENERGY_UPGRADE),
                blockEntity.getStorage(), ChopperBE.FUEL_SLOT, 13, 56 - 3);

        addMachineSlot(a -> a.is(ItemTags.SAPLINGS),
                blockEntity.getStorage(), ChopperBE.SAPLING_SLOT, 13, 20 - 3);

        addMachineSlot(a -> a.is(zItemTag.CHOPPER_AREA_INCREASE), 15,
                blockEntity.getStorage(), ChopperBE.UPGRADE_SLOT, 80, 62);

        for (Integer slot : ChopperBE.OUTPUT_SLOTS) {
            var index = ChopperBE.OUTPUT_SLOTS.indexOf(slot);
            addMachineOutputSlot(blockEntity.getStorage(), slot, 115 + 1 + (index % 3 * 18),
                    10 + 10 + (index / 3 * 18));
        }

        addDataSlots(data);
    }

    public boolean hasAxe() {
        return !getAxeSlot().isEmpty();
    }

    public ItemStack getFuelSlot() {
        return blockEntity.getStorage().getStackInSlot(ChopperBE.FUEL_SLOT);
    }

    public ItemStack getAxeSlot() {
        return blockEntity.getStorage().getStackInSlot(ChopperBE.AXE_SLOT);
    }

    public ItemStack getUpgradeSlot() {
        return blockEntity.getStorage().getStackInSlot(ChopperBE.UPGRADE_SLOT);
    }

    public ItemStack getSaplingSlot() {
        return blockEntity.getStorage().getStackInSlot(ChopperBE.SAPLING_SLOT);
    }

    public boolean handleEnergy() {
        return data.get(4) == 1;
    }

    public int getRange() {
        return (int) (data.get(5) / 2);
    }

    public int getProgress() {
        return data.get(0);
    }

    public int getEnergy() {
        return data.get(2);
    }

    public int getMaxEnergy() {
        return data.get(3);
    }

    public boolean isCrafting() {
        return data.get(0) > 0;
    }

    public int getScaledArrowProgress() {
        int progress = data.get(0);
        int maxProgress = data.get(1);
        int sizeArrow = 14;
        return maxProgress != 0
                &&
                progress != 0 ? progress * sizeArrow / maxProgress : 0;
    }

    @Override
    public Block[] getValidBlock() {
        return new Block[] { zBlocks.CHOPPER.get() };
    }

    @Override
    public BlockEntity getBlockEntity() {
        return blockEntity;
    }

    @Override
    public Level getLevel() {
        return level;
    }

}
