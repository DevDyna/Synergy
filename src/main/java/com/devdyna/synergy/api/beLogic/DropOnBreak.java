package com.devdyna.synergy.api.beLogic;

import net.minecraft.core.BlockPos;
import net.minecraft.world.Containers;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public interface DropOnBreak {

    default void drops() {
        SimpleContainer inv = new SimpleContainer(getItems());
        Containers.dropContents(getLevel(), getBlockPos(), inv);
    }

    ItemStack[] getItems();

    Level getLevel();

    BlockPos getBlockPos();
}
