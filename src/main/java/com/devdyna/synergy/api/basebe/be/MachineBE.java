package com.devdyna.synergy.api.basebe.be;

import java.util.List;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.IItemHandler;

/**
 * <b>STANDALONE BASE BE</b>
 * <br/>
 * <br/>
 * Base BE storage with menu and a custom handler to filter multiple slots on
 * automation handling
 * <br/>
 * <br/>
 * This Base BE is inspired from
 * <code>com.devdyna.synergy.api.machine.BaseMachineBE</code> to be used to
 * create simple-complex machines
 * <br/>
 * <br/>
 * |-----------------------------------------------------------------|<br/>
 * <br/>
 * <br/>
 * credit: @DevDyna
 */
@SuppressWarnings("null")
public abstract class MachineBE extends BEStorage {

    // public final static String RADIUS = "aoe";

    // protected int radius;

    public MachineBE(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }

    /**
     * Server only ticking
     * Useful for block events
     */
    public void tickServer() {
    }

    /**
     * Client only ticking
     * Useful for player events
     */
    public void tickClient() {
    }

    /**
     * Client and Server ticking
     * 
     * Usefull for particles
     */
    public void tickBoth() {
    }

    // @Override
    // protected void saveAdditional(CompoundTag tag, Provider registries) {
    //     // if (this instanceof AreaOfEffect)
    //         // tag.putInt(RADIUS, radius);
    //     super.saveAdditional(tag, registries);
    // }

    // @Override
    // protected void loadAdditional(CompoundTag tag, Provider registries) {

    //     if (this instanceof AreaOfEffect)
    //         if (tag.contains(RADIUS))
    //             radius = tag.getInt(RADIUS);
    //     super.loadAdditional(tag, registries);
    // }

    public abstract List<Integer> getInputSlotIndex();

    public abstract List<Integer> getOutputSlotIndex();

    public IItemHandler getAutomationItemHandler() {
        return new IItemHandler() {

            @Override
            public int getSlots() {
                return getStorage().getSlots();
            }

            @Override
            public ItemStack getStackInSlot(int slot) {
                return getStorage().getStackInSlot(slot);
            }

            @Override
            public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
                if (getInputSlotIndex().contains(slot))
                    return getStorage().insertItem(slot, stack, simulate);
                return stack;
            }

            @Override
            public ItemStack extractItem(int slot, int amount, boolean simulate) {
                if (getOutputSlotIndex().contains(slot))
                    return getStorage().extractItem(slot, amount, simulate);
                return ItemStack.EMPTY;
            }

            @Override
            public int getSlotLimit(int slot) {
                return getStorage().getSlotLimit(slot);
            }

            @Override
            public boolean isItemValid(int slot, ItemStack stack) {
                return getInputSlotIndex().contains(slot);
            }

        };
    }

}
