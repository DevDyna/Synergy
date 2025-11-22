package com.devdyna.synergy.api.machine.macerator;

import javax.annotation.Nullable;

import com.devdyna.synergy.api.machine.core.BaseMachineBE;
import com.devdyna.synergy.init.types.zMachines;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

@SuppressWarnings("null")
public class MaceratorBE extends BaseMachineBE {

    public MaceratorBE(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
        this.storage = new MachineItemHandler(getMachineSlots());
        networkData = new ContainerData() {

            @Override
            public int get(int i) {
                return switch (i) {
                    case 0 -> progress;
                    case 1 -> maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int i, int value) {
                switch (i) {
                    case 0 -> progress = value;
                    case 1 -> maxProgress = progress;
                }
            }

            @Override
            public int getCount() {
                return getMachineSlots();
            }
        };
    }

    public MaceratorBE(BlockPos pos, BlockState blockState) {
        this(zMachines.MACERATOR.blockentity().get(), pos, blockState);
    }

    @Override
    @Nullable
    public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new MaceratorMenu(i, inventory, this, this.networkData);
    }

}
