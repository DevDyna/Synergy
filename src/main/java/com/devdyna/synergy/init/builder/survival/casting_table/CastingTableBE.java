package com.devdyna.synergy.init.builder.survival.casting_table;

import java.util.List;
import java.util.Optional;

import javax.annotation.Nullable;

import com.devdyna.synergy.api.FluidStorageTank;
import com.devdyna.synergy.api.basebe.be.TickingBE;
import com.devdyna.synergy.api.beLogic.NoGuiStorage;
import com.devdyna.synergy.api.beLogic.RestrictedFluidHandler;
import com.devdyna.synergy.api.beLogic.RestrictedItemHandler;
import com.devdyna.synergy.api.beLogic.SimpleTickerDelay;
import com.devdyna.synergy.api.recipes.inputs.ItemFluidInput;
import com.devdyna.synergy.api.utils.Ticker;
import com.devdyna.synergy.init.builder.survival.casting_table.recipe.CastingTableRecipe;
import com.devdyna.synergy.init.types.zBlockEntities;
import com.devdyna.synergy.init.types.zHandlers;
import com.devdyna.synergy.init.types.zRecipeTypes;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Containers;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.capabilities.BlockCapabilityCache;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.fluids.capability.IFluidHandler.FluidAction;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemStackHandler;

@SuppressWarnings("null")
public class CastingTableBE extends TickingBE
        implements NoGuiStorage, RestrictedFluidHandler, RestrictedItemHandler, SimpleTickerDelay {

    private BlockCapabilityCache<IItemHandler, Direction> cache;

    public static final int OUTPUT_SLOT = 0;
    public static final int MOLD_SLOT = 1;
    private static final int DEFAULT_TANK_CAPACITY = 1_000;

    public CastingTableBE(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }

    public CastingTableBE(BlockPos pos, BlockState blockState) {
        this(zBlockEntities.CASTING_TABLE.get(), pos, blockState);
    }

    // @Override
    // public ItemStackHandler getStorage() {
    // return getData(zHandlers.ITEM_STORAGE);
    // }

    public void drops() {
        SimpleContainer inv = new SimpleContainer(getStorageRestricted().getSlots());
        for (int i = 0; i < getStorageRestricted().getSlots(); i++)
            inv.setItem(i, getStorageRestricted().getStackInSlot(i));
        Containers.dropContents(this.level, this.worldPosition, inv);
    }

    // @Override
    // public int MachineSlots() {
    // return 2;
    // }

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
        if (getStorageRestricted().getStackInSlot(MOLD_SLOT).isEmpty())
            return getStorageRestricted().insertItem(MOLD_SLOT, stack, false);
        return stack;
    }

    public ItemStack extractItem() {
        update();
        ItemStack extracted;

        if (getFluidStorage().isEmpty() && getStorage().getStackInSlot(OUTPUT_SLOT).isEmpty())
            extracted = getStorage().extractItem(MOLD_SLOT, getStorage().getStackInSlot(MOLD_SLOT).getCount(), false);
        else
            extracted = getStorageRestricted().extractItem(OUTPUT_SLOT,
                    getStorageRestricted().getStackInSlot(OUTPUT_SLOT).getCount(), false);

        if (!extracted.isEmpty())
            return extracted;
        return ItemStack.EMPTY;
    }

    @Override
    public ItemStackHandler getStorage() {
        return getData(zHandlers.ITEM_STORAGE);
    }

    @Override
    public int MachineSlots() {
        return 2;
    }

    @Override
    public FluidStorageTank getFluidStorage() {
        return getData(zHandlers.FLUID_TANK);
    }

    @Override
    public int getFluidCapacity() {
        return DEFAULT_TANK_CAPACITY;
    }

    private Ticker ticker = null;

    @Override
    public void tickBoth() {

        if (level == null) {
            fail();
            return;
        }

        if (cache == null) {
            fail();
            return;
        }

        if (getFluidStorage().getFluid().isEmpty()) {
            fail();
            getFluidStorage().setCapacity(DEFAULT_TANK_CAPACITY);
            return;
        }

        update();

        if (!getStorage().getStackInSlot(OUTPUT_SLOT).isEmpty()) {
            fail();
            // getFluidStorage().setCapacity(0);
            return;
        }

        Optional<RecipeHolder<CastingTableRecipe>> r = level.getRecipeManager()
                .getRecipeFor(zRecipeTypes.CASTING_TABLE.getType(),
                        new ItemFluidInput(getFluidStorage().getFluid(),
                                getStorage().getStackInSlot(MOLD_SLOT)),
                        level);

        if (r.isEmpty()) {
            fail();
            return;
        }

        var recipe = r.get().value();

        getFluidStorage().setCapacity(recipe.getFluid().amount());

        if (getStorage().getStackInSlot(OUTPUT_SLOT).getMaxStackSize() < recipe.getOutput().getCount()
                + getStorage().getStackInSlot(OUTPUT_SLOT).getCount()) {
            fail();
            return;
        }

        if (ticker == null)
            ticker = Ticker.of(recipe.getTicks());

        if (ticker.commit()) {
            if (recipe.consumeInput())
                getStorage().extractItem(MOLD_SLOT, 1, false);
            getFluidStorage().drain(recipe.getFluid().amount(), FluidAction.EXECUTE);
            getStorage().insertItem(OUTPUT_SLOT, recipe.getOutput().copy(), false);
            level.playSound(null, getBlockPos(), SoundEvents.LAVA_EXTINGUISH, SoundSource.BLOCKS, 1f, 1f);
            ticker = null;
        }

        update();

    }

    public void fail() {
        ticker = null;
    }

    protected void update() {
        setChanged();
        level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
    }

    @Override
    public void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        tag.put("tank", getFluidStorage().serializeNBT(registries));
        tag.put("inventory", getStorage().serializeNBT(registries));
        super.saveAdditional(tag, registries);
    }

    @Override
    public void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        getFluidStorage().deserializeNBT(registries, tag.getCompound("tank"));
        getStorage().deserializeNBT(registries, tag.getCompound("inventory"));
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
    public IItemHandler getStorageRestricted() {
        return new IItemHandler() {

            @Override
            public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
                if (slot == OUTPUT_SLOT || ticker != null ||
                        !getFluidStorage().isEmpty())
                    return stack;

                if (stack.isEmpty())
                    return ItemStack.EMPTY;

                if (!this.isItemValid(slot, stack))
                    return stack;

                if (slot == MOLD_SLOT && !getStorage().getStackInSlot(MOLD_SLOT).isEmpty())
                    return stack;

                return getStorage().insertItem(slot, stack, simulate);
            }

            @Override
            public ItemStack extractItem(int slot, int amount, boolean simulate) {
                if (slot == MOLD_SLOT) {
                    if (ticker == null && getFluidStorage().isEmpty() && level.hasNeighborSignal(getBlockPos())) {
                        update();
                        if (!level.isClientSide())
                            level.playSound(null, getBlockPos(), SoundEvents.COMPARATOR_CLICK, SoundSource.BLOCKS, 1f,
                                    1.15f);

                        return getStorage().extractItem(slot, amount, simulate);
                    }
                    return ItemStack.EMPTY;
                }

                return getStorage().extractItem(slot, amount, simulate);
            }

            @Override
            public boolean isItemValid(int slot, ItemStack stack) {
                if (slot == OUTPUT_SLOT)
                    return false;
                return getStorage().isItemValid(slot, stack);
            }

            @Override
            public int getSlotLimit(int slot) {
                return 1;
            }

            @Override
            public int getSlots() {
                return 2;
            }

            @Override
            public ItemStack getStackInSlot(int slot) {
                return getStorage().getStackInSlot(slot);
            }

        };
    }

    @Override
    public IFluidHandler getFluidStorageRestricted() {
        return new IFluidHandler() {
            @Override
            public FluidStack drain(int maxDrain, FluidAction action) {
                if (ticker == null)
                    return getFluidStorage().drain(maxDrain, action);
                return FluidStack.EMPTY;
            }

            @Override
            public FluidStack drain(FluidStack resource, FluidAction action) {
                if (ticker == null)
                    return getFluidStorage().drain(resource, action);
                return FluidStack.EMPTY;
            }

            @Override
            public int fill(FluidStack resource, FluidAction action) {

                if (!getStorage().getStackInSlot(OUTPUT_SLOT).isEmpty())
                    return 0;

                Optional<RecipeHolder<CastingTableRecipe>> r = level.getRecipeManager()
                        .getRecipeFor(zRecipeTypes.CASTING_TABLE.getType(),
                                new ItemFluidInput(getFluidStorage().getFluid(),
                                        getStorageRestricted().getStackInSlot(MOLD_SLOT)),
                                level);

                if (r.isEmpty())
                    return 0;

                return r.get().value().getFluid().amount();
            }

            @Override
            public FluidStack getFluidInTank(int arg0) {
                return getFluidStorage().getFluidInTank(arg0);
            }

            @Override
            public int getTankCapacity(int arg0) {
                return getFluidCapacity();
            }

            @Override
            public int getTanks() {
                return getFluidStorage().getTanks();
            }

            @Override
            public boolean isFluidValid(int arg0, FluidStack arg1) {
                if (!getStorage().getStackInSlot(OUTPUT_SLOT).isEmpty())
                    return false;
                return getFluidStorage().isFluidValid(arg0, arg1);
            }

        };
    }

    @Override
    public Ticker getTicker() {
        return ticker;
    }

    @Override
    public List<Integer> getValidSlots() {
        return List.of(MOLD_SLOT);
    }

}
