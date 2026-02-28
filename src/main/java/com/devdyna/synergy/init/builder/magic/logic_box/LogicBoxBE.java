package com.devdyna.synergy.init.builder.magic.logic_box;

import com.devdyna.synergy.api.basebe.be.AnimatedChestBE;
import com.devdyna.synergy.api.beLogic.NoGuiStorage;
import com.devdyna.synergy.api.beLogic.RestrictedItemHandler;
import com.devdyna.synergy.api.utils.x;
import com.devdyna.synergy.init.types.zBlockEntities;
import com.devdyna.synergy.init.types.zHandlers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Containers;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemStackHandler;

@SuppressWarnings("null")
public class LogicBoxBE extends AnimatedChestBE implements NoGuiStorage, RestrictedItemHandler {

    public static final int FILTER_SLOT = 0;
    public static final int FUNCTIONAL_SLOT = 1;

    public LogicBoxBE(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }

    public LogicBoxBE(BlockPos pos, BlockState blockState) {
        this(zBlockEntities.LOGIC_BOX.get(), pos, blockState);
    }

    @Override
    public ItemStackHandler getStorage() {
        return getData(zHandlers.ITEM_STORAGE);
    }

    public void drops() {
        SimpleContainer inv = new SimpleContainer(getStorage().getSlots());
        inv.setItem(0, getStorage().getStackInSlot(0));
        inv.setItem(1, getStorage().getStackInSlot(1));
        Containers.dropContents(this.level, this.worldPosition, inv);
    }

    @Override
    public int MachineSlots() {
        return 2;
    }

    public ItemStack insertItem(ItemStack stack) {
        if (getStorage().getStackInSlot(FILTER_SLOT).isEmpty()) {
            level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
            getStorage().insertItem(FILTER_SLOT, x.item(stack.copy().getItem(), 1), false);
            stack.shrink(1);
        }
        return stack;
    }

    public ItemStack extractItem() {
        level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
        return getStorage().extractItem(FILTER_SLOT, 1, false);
    }

    private boolean toggle = false;

    @Override
    public boolean defineOpen() {
        return getStorage().getStackInSlot(FILTER_SLOT).isEmpty();
    }

    @Override
    public void soundOpening() {
        level.playSound(null, getBlockPos(), SoundEvents.BARREL_OPEN,
                SoundSource.BLOCKS, 1f, 1.75f);
    }

    @Override
    public void soundClosing() {
        level.playSound(null, getBlockPos(), SoundEvents.BARREL_CLOSE,
                SoundSource.BLOCKS, 1f, 1.75f);
    }

    @Override
    public void tickServer() {
        if (level.hasNeighborSignal(getBlockPos()) != toggle) {
            toggle = !toggle;
            level.setBlockAndUpdate(getBlockPos(), getBlockState().setValue(BlockStateProperties.INVERTED, toggle));
        }
    }

    public boolean isInverted() {
        return getBlockState().getValue(BlockStateProperties.INVERTED);
    }

    @Override
    public IItemHandler getStorageRestricted() {
        return new IItemHandler() {

            @Override
            public int getSlots() {
                return 1;
            }

            @Override
            public ItemStack getStackInSlot(int slot) {
                return getStorage().getStackInSlot(slot);
            }

            @Override
            public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
                return getFilterSlot().isEmpty()
                        ? getStorage().insertItem(slot, stack, simulate)
                        : (ItemStack.isSameItemSameComponents(stack,
                                getFilterSlot()) == !isInverted()
                                        ? getStorage().insertItem(slot, stack, simulate)
                                        : stack);
            }

            @Override
            public ItemStack extractItem(int slot, int amount, boolean simulate) {
                return getStorage().extractItem(slot, amount, simulate);
            }

            @Override
            public int getSlotLimit(int slot) {
                return getStorage().getSlotLimit(slot);
            }

            @Override
            public boolean isItemValid(int slot, ItemStack stack) {
                return getFilterSlot().isEmpty()
                        ? getStorage().isItemValid(slot, stack)
                        : (ItemStack.isSameItemSameComponents(stack,
                                getFilterSlot()) == !isInverted()
                                        ? getStorage().isItemValid(slot, stack)
                                        : false);
            }

        };

    }

    public ItemStack getFilterSlot() {
        return getStorage().getStackInSlot(FILTER_SLOT);
    }

    public ItemStack getStorageSlot() {
        return getStorage().getStackInSlot(FUNCTIONAL_SLOT);
    }

    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider pRegistries) {
        return saveWithoutMetadata(pRegistries);
    }

}
