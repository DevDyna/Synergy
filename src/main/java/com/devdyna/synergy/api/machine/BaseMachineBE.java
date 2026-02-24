package com.devdyna.synergy.api.machine;

import java.util.ArrayList;
import java.util.List;
import com.devdyna.synergy.api.FluidStorageTank;
import com.devdyna.synergy.api.basebe.be.BEMenu;
import com.devdyna.synergy.api.beLogic.EnergyBlock;
import com.devdyna.synergy.api.beLogic.MachineItemAutomation;
import com.devdyna.synergy.api.utils.LogUtil;
import com.devdyna.synergy.config.Common;
import com.devdyna.synergy.init.builder.industrial_machines.IndustrialUpgrade;
import com.devdyna.synergy.init.builder.industrial_machines.IndustrialUpgrade.UpgradeComponents;
import com.devdyna.synergy.init.builder.industrial_machines.IndustrialUpgrade.UpgradeComponents.TYPE;
import com.devdyna.synergy.init.types.zComponents;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.Containers;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.energy.EnergyStorage;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemStackHandler;

/**
 * <b>INDUSTRIAL MACHINE BASE BE</b>
 * <br/>
 * <br/>
 * Base BE of all Industrial Machine
 * <br/>
 * <br/>
 * It can handle by default Items and Energy Capability
 * <br/>
 * <br/>
 * Also it contain multiple ready-to-use methods useful on recipe handling
 * <br/>
 * <br/>
 * |-----------------------------------------------------------------|<br/>
 * <br/>
 * The Machine much be registred using
 * <code>com.devdyna.synergy.api.registers.MachineType</code>
 * <br/>
 * <br/>
 * credit: @DevDyna
 */
@SuppressWarnings("null")
public abstract class BaseMachineBE extends BEMenu implements MachineItemAutomation, EnergyBlock {

    public static final int DEFAULT_FE_COST = 500;
    public static final int DEFAULT_TICK_DURATION = 100;

    protected int progress = 0;
    protected int maxProgress;
    protected int energy = 0;
    protected int maxEnergy = 0;
    protected int fluid_amount = 0;
    protected int maxFluid = 0;

    public static final int PROGRESS_INDEX = 0;
    public static final int MAX_PROGRESS_INDEX = 1;
    public static final int ENERGY_INDEX = 2;
    public static final int MAX_ENERGY_INDEX = 3;
    public static final int FLUID_INDEX = 4;
    public static final int MAX_FLUID_INDEX = 5;

    public static final int SLOT_UPGRADE_1 = 0;
    public static final int SLOT_UPGRADE_2 = 1;
    public static final int SLOT_UPGRADE_3 = 2;
    public static final int SLOT_UPGRADE_4 = 3;

    public static final int MAX_UPGRADE_SLOTS = 4;

    /**
     * To add new slots you should use ExtraMachineSlots interface!
     * <br/>
     * <br/>
     * The first slot Index must be 6 -> ?
     */
    public static final int INPUT_SLOT = 4;
    /**
     * To add new slots you should use ExtraMachineSlots interface!
     * <br/>
     * <br/>
     * The first slot Index must be 6 -> ?
     */
    public static final int OUTPUT_SLOT = 5;

    @Deprecated
    public static final int BASE_SLOT_IO = 2;

    @Deprecated
    public static final int TOTAL_BASE_SLOT_IO = MAX_UPGRADE_SLOTS + BASE_SLOT_IO;

    protected boolean progress_cancel;

    protected MachineItemHandler storage;

    protected FluidStorageTank fluid_tank;
    /**
     * Server side data sent to client side render
     */
    public ContainerData networkData;

    protected EnergyStorage energyStorage;

    public static int check(Level l, int t, int f) {
        return (l != null && !l.isClientSide()) ? t : f;
    }

    public BaseMachineBE(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }

    public void dropItems() {
        SimpleContainer inv = new SimpleContainer(getStorage().getSlots());
        for (int i = 0; i < getStorage().getSlots(); i++)
            inv.setItem(i, getStorage().getStackInSlot(i));
        Containers.dropContents(this.level, this.worldPosition, inv);
    }

    @Override
    public List<Integer> getInputSlotIndex() {
        return List.of(INPUT_SLOT);
    }

    @Override
    public List<Integer> getOutputSlotIndex() {
        return List.of(OUTPUT_SLOT);
    }

    @Override
    public int getSlotLimit(int slotindex) {
        return getStorage().getSlotLimit(slotindex);
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        return getStorage().getStackInSlot(index);
    }

    @Override
    public boolean isItemValid(int slot, ItemStack itemStack) {
        return getInputSlotIndex().contains(slot);
    }

    @Override
    public ItemStackHandler getStorage() {
        return storage;
    }

    @Override
    public void setStackInSlot(int slot, ItemStack itemStack) {
        getStorage().setStackInSlot(slot, itemStack);
    }

    public IItemHandler getAutomationHandler() {
        return new IItemHandler() {

            @Override
            public int getSlots() {
                return storage.getSlots();
            }

            @Override
            public ItemStack getStackInSlot(int slot) {
                return storage.getStackInSlot(slot);
            }

            @Override
            public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
                if (getInputSlotIndex().contains(slot))
                    return storage.insertItem(slot, stack, simulate);
                return stack;
            }

            @Override
            public ItemStack extractItem(int slot, int amount, boolean simulate) {
                if (getOutputSlotIndex().contains(slot))
                    return storage.extractItem(slot, amount, simulate);
                return ItemStack.EMPTY;
            }

            @Override
            public int getSlotLimit(int slot) {
                return storage.getSlotLimit(slot);
            }

            @Override
            public boolean isItemValid(int slot, ItemStack stack) {
                return getInputSlotIndex().contains(slot) && storage.isItemValid(slot, stack);
            }
        };
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        tag.put("inventory", getStorage().serializeNBT(registries));
        tag.putInt("progress", progress);
        tag.putInt("energy", energyStorage.getEnergyStored());
        super.saveAdditional(tag, registries);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        getStorage().deserializeNBT(registries, tag.getCompound("inventory"));
        if (tag.contains("progress"))
            progress = tag.getInt("progress");

        if (tag.contains("energy"))
            energyStorage.receiveEnergy(Math.min(tag.getInt("energy"), energyStorage.getMaxEnergyStored()), false);

        super.loadAdditional(tag, registries);
    }

    @Override
    public EnergyStorage getCapEnergy() {
        return energyStorage;
    }

    @Override
    public int MaxFE() {
        return Common.MACHINE_MAX_FE.get();
    }

    @Override
    public ContainerData getContainerData() {
        return networkData;
    }

    protected void resetProgress() {
        progress_cancel = true;
        if (progress > 0)
            progress--;
        if (progress == 0)
            progress_cancel = false;

        if (getBlockState().getValue(BaseMachineBlock.ENABLED))
            update(false);

        setChanged();
    }

    protected void update(boolean v) {
        level.setBlockAndUpdate(getBlockPos(),
                getBlockState().setValue(BaseMachineBlock.ENABLED, v));
    }

    protected class MachineItemHandler extends ItemStackHandler {

        public MachineItemHandler(int machineSlots) {
            super(machineSlots);
        }

        @Override
        public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
            if (getInputSlotIndex().contains(slot))
                return super.insertItem(slot, stack, simulate);
            return stack;
        }

        @Override
        public ItemStack extractItem(int slot, int amount, boolean simulate) {
            return super.extractItem(slot, amount, simulate);
        }

        @Override
        public CompoundTag serializeNBT(Provider provider) {
            return super.serializeNBT(provider);
        }

        @Override
        public void deserializeNBT(HolderLookup.Provider provider, CompoundTag nbt) {
            super.deserializeNBT(provider, nbt);
        }

    }

    protected void tick() {
        if (Common.DISABLE_MACHINE_DROP_WHEN_CORRUPTED.get()) {
            tickBoth();
            if (level.isClientSide())
                tickClient();
            else
                tickServer();
        } else {
            try {
                tickBoth();
                if (level.isClientSide())
                    tickClient();
                else
                    tickServer();

            } catch (RuntimeException e) {
                // catch potential crashes
                if (level.getBlockEntity(getBlockPos()) instanceof BaseMachineBE) {
                    LogUtil.error(
                            "BlockEntity at " + getBlockPos() + " has invalid data -> Broken to prevent crash");
                    LogUtil.error("Contact Mod Author and report this as BUG");
                    LogUtil.error(e.getMessage());
                    level.removeBlockEntity(getBlockPos());
                    level.destroyBlock(getBlockPos(), true);
                }
            }
        }

    }

    public void tickBoth() {
    }

    public void tickClient() {
    }

    public void tickServer() {

    }

    public boolean isCrafting() {
        return progress > 0 && !progress_cancel;
    }

    public int getProgress() {
        return progress;
    }

    public int getMaxProgress() {
        return maxProgress;
    }

    public ItemStack getInput() {
        return getStorage().getStackInSlot(INPUT_SLOT);
    }

    public ItemStack getOutput() {
        return getStorage().getStackInSlot(OUTPUT_SLOT);
    }

    /**
     * Return <code>true</code> when success
     */
    public boolean checkSlot(ItemStack slot, ItemStack recipeSlot) {
        if (!slot.isEmpty()) {
            // same item
            if (ItemStack.isSameItemSameComponents(slot, recipeSlot)) {
                // count valid
                if (slot.getMaxStackSize() < slot.getCount() + recipeSlot.getCount()) {
                    return false;
                }

            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * Return <code>true</code> when success
     */
    public boolean checkSlot(FluidStack slot, FluidStack recipeSlot, int max_fluid_tank) {
        if (!slot.isEmpty()) {
            // same item
            if (FluidStack.isSameFluidSameComponents(slot, recipeSlot)) {
                // count valid
                if (max_fluid_tank < slot.getAmount() + recipeSlot.getAmount()) {
                    return false;
                }

            } else {
                return false;
            }
        }
        return true;
    }

    public void updateOutputSlot(ItemStack stack, ItemStack slotStack, int slotIndex) {
        if (stack.isEmpty())
            storage.setStackInSlot(slotIndex, slotStack);
        else if (ItemStack.isSameItemSameComponents(stack, slotStack))
            stack.grow(slotStack.getCount());
    }

    public List<Integer> getUpgradeIndexs() {
        return List.of(SLOT_UPGRADE_1, SLOT_UPGRADE_2, SLOT_UPGRADE_3, SLOT_UPGRADE_4).stream()
                .filter(i -> i < getStorage().getSlots())
                .toList();
    }

    public List<ItemStack> getUpgradeInstalled() {
        return getUpgradeIndexs().stream()
                .map(getStorage()::getStackInSlot)
                .filter(i -> i.getItem() instanceof IndustrialUpgrade)
                .filter(i -> i.get(zComponents.UPGRADE_COMPONENTS) != null)
                .toList();
    }

    public List<Integer> getValues(UpgradeComponents.TYPE type) {
        List<ItemStack> upgrades = getUpgradeInstalled().stream()
                .filter(i -> UpgradeComponents.has(i, type))
                .toList();

        List<Integer> validSlots = new ArrayList<>();
        int maxRoll = getTypeLimiter(type);

        for (int i = 0; i < upgrades.size() && validSlots.size() < maxRoll; i++)
            for (int j = 0; j < upgrades.get(i).getCount() && validSlots.size() < maxRoll; j++)
                validSlots.add(UpgradeComponents.get(upgrades.get(i), type));

        return validSlots;
    }

    public int getTypeLimiter(UpgradeComponents.TYPE type) {
        if (type.equals(TYPE.SPEED))
            return Common.MACHINE_MAX_SPEED_UPGRADES_TYPE.get();
        if (type.equals(TYPE.ENERGY))
            return Common.MACHINE_MAX_ENERGY_UPGRADES_TYPE.get();
        if (type.equals(TYPE.LUCK))
            return Common.MACHINE_MAX_LUCK_UPGRADES_TYPE.get();
        if (type.equals(TYPE.FLUID))
            return Common.MACHINE_MAX_FLUID_UPGRADES_TYPE.get();
        return Integer.MAX_VALUE;
    }

    public int calculateMaxProgress(int base) {
        var upgrades = getValues(TYPE.SPEED);
        var sum = upgrades == null ? 0 : upgrades.stream().mapToInt(Integer::intValue).sum();
        return Common.MACHINE_MAX_SPEED_UPGRADES_TYPE.get() == 0 ? base
                : Math.max(Common.MACHINE_MINIMAL_TICK_DELAY.get(), (int) (base - (base * (((float) sum) / 100))));
    }

    private int calculateFEUsage(int base) {
        var upgrades = getValues(TYPE.ENERGY);
        var sum = upgrades == null ? 0 : upgrades.stream().mapToInt(Integer::intValue).sum();
        return Common.MACHINE_MAX_ENERGY_UPGRADES_TYPE.get() == 0 ? base
                : Math.max(Common.MACHINE_MINIMAL_FE_COST.get(), (int) (base + (base * (((float) sum) / 100))));
    }

    public int calculateMBUsage(int base) {
        var upgrades = getValues(TYPE.FLUID);
        var sum = upgrades == null ? 0 : upgrades.stream().mapToInt(Integer::intValue).sum();
        return Common.MACHINE_MAX_FLUID_UPGRADES_TYPE.get() == 0 ? base
                : Math.max(Common.MACHINE_MINIMAL_FLUID_COST.get(), (int) (base - (base * (((float) sum) / 100))));
    }

    public boolean calculateSecondarySuccess(float base) {
        var upgrades = getValues(TYPE.LUCK);
        var sum = upgrades == null ? 0 : upgrades.stream().mapToInt(Integer::intValue).sum();
        return Common.MACHINE_MAX_LUCK_UPGRADES_TYPE.get() == 0 ? false
                : level.random.nextFloat() < Math.min(Common.MACHINE_MAXIMAL_LUCK.get(),
                        (base + (((float) sum) / 100)));
    }

    /**
     * Return <code>true</code> when success
     */
    public boolean calculateAndConsumeFE(int min) {
        var base = calculateFEUsage(min);
        if (energyStorage.getEnergyStored() >= base && !progress_cancel) {
            energyStorage.extractEnergy(base, false);
            return true;
        } else
            return false;
    }

    public boolean tryAddUpgrade(ItemStack item) {
        var upgrade = item.copy();
        upgrade.setCount(1);

        if (!upgrade.has(zComponents.UPGRADE_COMPONENTS))
            return false;

        for (int index = 0; index < MAX_UPGRADE_SLOTS; index++) {
            var slot = getStackInSlot(index);

            if (slot.isEmpty()) {
                setStackInSlot(index, upgrade);
                return true;
            }

            if (ItemStack.isSameItemSameComponents(upgrade, slot) && slot.getCount() < 4) {
                slot.grow(1);
                return true;
            }
        }

        return false;
    }
}
