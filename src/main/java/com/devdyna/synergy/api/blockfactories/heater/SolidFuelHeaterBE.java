package com.devdyna.synergy.api.blockfactories.heater;

import javax.annotation.Nullable;

import com.devdyna.synergy.api.basebe.be.TickingBE;
import com.devdyna.synergy.api.beLogic.ItemStorageBlock;
import com.devdyna.synergy.api.beLogic.NoGuiStorage;
import com.devdyna.synergy.api.beLogic.SimpleTickerDelay;
import com.devdyna.synergy.api.utils.Ticker;
import com.devdyna.synergy.init.types.zHandlers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Containers;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.capabilities.BlockCapabilityCache;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemStackHandler;

@SuppressWarnings("null")
public abstract class SolidFuelHeaterBE extends TickingBE
        implements NoGuiStorage, ItemStorageBlock, SimpleTickerDelay {

    private BlockCapabilityCache<IItemHandler, Direction> cache;

    public SolidFuelHeaterBE(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }

    @Override
    public ItemStackHandler getStorage() {
        return getData(zHandlers.ITEM_STORAGE);
    }

    public void drops() {
        SimpleContainer inv = new SimpleContainer(getStorage().getSlots());
        inv.setItem(0, getStorage().getStackInSlot(0));
        Containers.dropContents(this.level, this.worldPosition, inv);
    }

    @Override
    public int MachineSlots() {
        return 1;
    }

    @Override
    public void onLoad() {
        super.onLoad();
        if (this.level instanceof ServerLevel serverLevel) {
            this.cache = BlockCapabilityCache.create(
                    Capabilities.ItemHandler.BLOCK,
                    serverLevel,
                    getBlockPos(),
                    null);
        }
    }

    public ItemStack insertItem(ItemStack stack) {
        update();
        return getStorage().insertItem(0, stack, false);
    }

    public ItemStack extractItem() {
        update();
        ItemStack extracted = getStorage().extractItem(0, getStorage().getStackInSlot(0).getCount(), false);
        if (!extracted.isEmpty())
            return extracted;
        return ItemStack.EMPTY;
    }

    protected Ticker ticker = null;

    public static final int BASE_HEAT = 20;

    protected int heat = BASE_HEAT;

    protected boolean is_decay = false;

    @Override
    public void tickServer() {

        if (is_decay)
            if (heat > BASE_HEAT) {
                heat--;
                return;
            } else
                is_decay = false;

        if (cache == null) {
            fail();
            return;
        }

        var slot = this.cache.getCapability();

        if (slot == null) {
            fail();
            return;
        }

        var item = slot.getStackInSlot(0);

        update();

        if (ticker == null)
            if (item.isEmpty() || !AbstractFurnaceBlockEntity.isFuel(item)) {
                fail();
                return;
            }

        var burntime = item.getBurnTime(RecipeType.SMELTING);

        if (ticker == null)
            if (burntime <= 0) {
                fail();
                return;
            }

        if (getBlockState().getValue(BlockStateProperties.ENABLED))
            if (ticker == null && !is_decay) {

                ticker = Ticker.of(burntime);

                if (item.hasCraftingRemainingItem()) {
                    var copy = item.copy();
                    getStorage().extractItem(0, 1, false);
                    copy.setCount(1);

                    var remain = getStorage().insertItem(0, copy.getCraftingRemainingItem(), false);

                    if (!remain.isEmpty())
                        Block.popResourceFromFace(level, getBlockPos(),
                                getBlockState().getValue(BlockStateProperties.HORIZONTAL_FACING), remain);

                } else
                    getStorage().extractItem(0, 1, false);

            }

        if (ticker != null) {

            if (ticker.every(10))
                if (heat < getHeatCap())
                    heat++;

            if (ticker.commit())
                ticker = null;

        }

        update();

    }

    public abstract int getHeatCap();

    public void fail() {
        if (ticker != null || heat > BASE_HEAT)
            is_decay = true;
        update();
        if (getBlockState().getValue(BlockStateProperties.ENABLED))
            updateState(false);
    }

    public void updateState(boolean v) {
        level.setBlockAndUpdate(getBlockPos(), getBlockState().setValue(BlockStateProperties.ENABLED, v));
    }

    @Override
    public void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        tag.put("inventory", getStorage().serializeNBT(registries));
        tag.putInt("heat", heat);
        if (ticker != null)
            tag.putInt("burntime", ticker.get());
        super.saveAdditional(tag, registries);
    }

    @Override
    public void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        getStorage().deserializeNBT(registries, tag.getCompound("inventory"));
        if (tag.contains("heat"))
            heat = tag.getInt("heat");
        if (ticker != null)
            if (tag.contains("burntime"))
                ticker.set(tag.getInt("burntime"));
        super.loadAdditional(tag, registries);
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider pRegistries) {
        return saveWithoutMetadata(pRegistries);
    }

    @Override
    public boolean insertFilter(ItemStack i) {
        return AbstractFurnaceBlockEntity.isFuel(i) && getBlockState().getValue(BlockStateProperties.OPEN);
    }

    @Override
    public Ticker getTicker() {
        return ticker;
    }

    public int getHeat() {
        return heat;
    }

    public boolean isDecay() {
        return is_decay;
    }

}
