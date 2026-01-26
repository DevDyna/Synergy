package com.devdyna.synergy.api.resource_gen;

import com.devdyna.synergy.api.basebe.be.TickingBE;
import com.devdyna.synergy.api.beLogic.ItemStorageBlock;
import com.devdyna.synergy.api.utils.Ticker;
import com.devdyna.synergy.api.utils.x;
import com.devdyna.synergy.init.types.zHandlers;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.items.ItemStackHandler;

@SuppressWarnings("null")
public abstract class BaseCobbleRGBE extends TickingBE implements ItemStorageBlock {

    protected Ticker ticker;

    public BaseCobbleRGBE(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }

    @Override
    public void tickServer() {
        if (ticker.commit()) {
            if (!export())
                increaseStored();
        }
    }

    @Override
    public int MachineSlots() {
        return 1;
    }

    @Override
    public ItemStackHandler getStorage() {
        return getData(zHandlers.ITEM_STORAGE);
    }

    public ItemStack getItemOutput() {
        return x.item(Items.COBBLESTONE, getItemAmount());
    }

    public abstract int getItemAmount();

    public boolean export() {
        var output = getItemOutput();

        for (Direction dir : Direction.values()) {

            var cap = Capabilities.ItemHandler.BLOCK.getCapability(level, getBlockPos().relative(dir),
                    level.getBlockState(getBlockPos().relative(dir)), level.getBlockEntity(getBlockPos().relative(dir)),
                    dir);

            if (cap == null)
                continue;

            if (level.getBlockState(getBlockPos().relative(dir)).getBlock() instanceof BaseResourceGenBlock)
                continue;

            if (!getStorage().getStackInSlot(0).isEmpty())
                output = getStorage().extractItem(0, getStorage().getStackInSlot(0).getCount(), false);

            for (int i = 0; i < cap.getSlots(); i++) {
                if (cap.getStackInSlot(i).isEmpty() || cap.isItemValid(i, output)) {
                    cap.insertItem(i, output, false);
                    return true;
                }
            }

        }

        return false;
    }

    public void increaseStored() {
        if (getStorage().getStackInSlot(0).getCount() < getStorage().getStackInSlot(0).getMaxStackSize())
            getStorage().insertItem(0, getItemOutput().copy(), false);
    }

}
