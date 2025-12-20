package com.devdyna.synergy.init.machine.core;

import java.util.List;

import com.devdyna.synergy.init.types.zItemTag;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.ItemStackHandler;

public interface UpgradeSlots {

    public static final int SLOT_UPGRADE_1 = 3;
    public static final int SLOT_UPGRADE_2 = 4;
    public static final int SLOT_UPGRADE_3 = 5;
    public static final int SLOT_UPGRADE_4 = 6;

    abstract ItemStackHandler getStorage();

    public default List<Integer> getUpgradeIndexs() {
    int slots = getStorage().getSlots();
    return List.of(SLOT_UPGRADE_1, SLOT_UPGRADE_2, SLOT_UPGRADE_3, SLOT_UPGRADE_4).stream()
        .filter(i -> i < slots)
        .toList();
}


    public default List<ItemStack> getUpgradeInstalled() {
    ItemStackHandler handler = getStorage();
    return getUpgradeIndexs().stream()
        .filter(i -> i >= 0 && i < handler.getSlots())
        .map(handler::getStackInSlot)
        .toList();
}


    public default int getUpgradeInstalled(TagKey<Item> filter) {
        return (int) getUpgradeInstalled().stream().filter(i -> i.is(filter)).count();
    }

    // TODO config min-max limit
    public default int calculateFEUsage(int base) {
        var energy = getUpgradeInstalled(zItemTag.UPGRADE_ENERGY);
        var speed = getUpgradeInstalled(zItemTag.UPGRADE_SPEED);

        return (base - ((int) (base * (energy * 0.15)))) // energy -> -15% | speed -> +15%
                + ((int) (base * (speed * 0.15)));
    }

    // TODO config min-max limit
    public default int calculateMaxProgress(int base) {
        var upgrades = getUpgradeInstalled(zItemTag.UPGRADE_SPEED);
        return (base - ((int) (base * (upgrades * 0.2))));// speed -> -20%
    }

}
