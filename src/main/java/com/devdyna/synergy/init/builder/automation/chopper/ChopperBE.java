package com.devdyna.synergy.init.builder.automation.chopper;

import java.util.*;

import javax.annotation.Nullable;

import com.devdyna.synergy.api.beLogic.AreaOfEffect;
import com.devdyna.synergy.api.beLogic.EnergyBlock;
import com.devdyna.synergy.api.beLogic.RestrictedItemHandler;
import com.devdyna.synergy.api.harvester.VanillaPlants;
import com.devdyna.synergy.api.basebe.be.MachineBE;
import com.devdyna.synergy.api.utils.ColorUtil;
import com.devdyna.synergy.api.utils.LevelUtil;
import com.devdyna.synergy.config.Common;
import com.devdyna.synergy.init.types.zBlockEntities;
import com.devdyna.synergy.init.types.zHandlers;
import com.devdyna.synergy.init.types.zItemTag;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.energy.EnergyStorage;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemStackHandler;

@SuppressWarnings("null")
public class ChopperBE extends MachineBE implements RestrictedItemHandler, AreaOfEffect, EnergyBlock {

    public static final int AXE_SLOT = 0;
    public static final int SAPLING_SLOT = 1;
    public static final int FUEL_SLOT = 2;
    public static final int UPGRADE_SLOT = 3;

    public static final int OUTPUT_SLOT_0 = 4;
    public static final int OUTPUT_SLOT_1 = 5;
    public static final int OUTPUT_SLOT_2 = 6;
    public static final int OUTPUT_SLOT_3 = 7;
    public static final int OUTPUT_SLOT_4 = 8;
    public static final int OUTPUT_SLOT_5 = 9;
    public static final int OUTPUT_SLOT_6 = 10;
    public static final int OUTPUT_SLOT_7 = 11;
    public static final int OUTPUT_SLOT_8 = 12;

    public static final List<Integer> OUTPUT_SLOTS = List.of(
            OUTPUT_SLOT_0,
            OUTPUT_SLOT_1,
            OUTPUT_SLOT_2,
            OUTPUT_SLOT_3,
            OUTPUT_SLOT_4,
            OUTPUT_SLOT_5,
            OUTPUT_SLOT_6,
            OUTPUT_SLOT_7,
            OUTPUT_SLOT_8);

    private final ContainerData data;

    private int progress;
    private int maxProgress = 0;

    public ChopperBE(BlockPos pos, BlockState state) {
        super(zBlockEntities.CHOPPER.get(), pos, state);
        var random = new Random();
        var color = ColorUtil.colorfulColorList.get(random.nextInt(ColorUtil.colorfulColorList.size()));
        rgbColor = List.of(color.getRed(), color.getGreen(), color.getBlue());
        data = new ContainerData() {

            @Override
            public int get(int i) {
                return switch (i) {
                    case 0 -> progress;
                    case 1 -> maxProgress;
                    case 2 -> getCapEnergy() == null ? 0 : getCapEnergy().getEnergyStored();
                    case 3 -> getCapEnergy() == null ? 0 : getCapEnergy().getMaxEnergyStored();
                    case 4 -> handleEnergy() ? 1 : 0;
                    case 5 -> getWidth();
                    default -> 0;
                };
            }

            @Override
            public void set(int i, int value) {
                switch (i) {
                    case 0:
                        progress = value;
                    case 1:
                        maxProgress = value;
                    case 2:
                    default:

                }
            }

            @Override
            public int getCount() {
                return 6;
            }

        };
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new ChopperMenu(i, inventory, this, this.data);
    }

    int i = 0;
    List<Integer> rgbColor;

    int delay = 40;

    int energy_usage = 25; // TODO config

    @Override
    public void tickServer() {

        level.setBlockAndUpdate(getBlockPos(),
                getBlockState().setValue(BlockStateProperties.ENABLED,

                        area != null
                                // && getStorage().getStackInSlot(AXE_SLOT).is(ItemTags.AXES)
                                && (AbstractFurnaceBlockEntity.isFuel(getStorage().getStackInSlot(FUEL_SLOT))
                                        || (handleEnergy()
                                                && canExtract() && hasEnergy(energy_usage))
                                        || maxProgress != 0)));

        if (area == null) {
            area = getArea();
        }

        if (maxProgress != 0)
            if (progress <= 1) {
                maxProgress = 0;
                progress = 0;
            } else
                progress--;

        processFuel();

        if (getBlockState().getValue(BlockStateProperties.ENABLED)) {
            checkBlocks(level);
        }

    }

    private void processFuel() {

        if (maxProgress == 0) {

            var fuel = getStorage().getStackInSlot(FUEL_SLOT);

            if (AbstractFurnaceBlockEntity.isFuel(fuel)) {
                maxProgress = fuel.getBurnTime(RecipeType.SMELTING);
                fuel.shrink(1);
            } else if (handleEnergy() && canExtract() && hasEnergy(energy_usage)) {
                maxProgress = 5;//TODO config
                extractFE(energy_usage, false);
            }

            progress = maxProgress;
        }

    }

    private void checkBlocks(Level level) {
        int size = area.size();

        if (i < size && level.getGameTime() % delay == 0) {

            var pos = area.get(i);

            LevelUtil.addDustParticle(rgbColor.get(0), rgbColor.get(1), rgbColor.get(2),
                    (ServerLevel) level, pos, false, 4);

            List<ItemStack> items = !getStorage().getStackInSlot(AXE_SLOT).is(ItemTags.AXES)
                    ? null
                    : VanillaPlants.checkTree(level, pos,
                            Common.HARVESTER_DISABLE_CHECK_TREE.get(), // TODO
                            (l_state, l_pos) -> {

                                var result = Block.getDrops(l_state, (ServerLevel) level, l_pos, null);
                                if (result == null || result.isEmpty())
                                    return false;

                                if (result.stream().filter(i -> i.is(ItemTags.LOGS)).count() <= 0)
                                    return false;

                                var axe = getStorage().getStackInSlot(AXE_SLOT);
                                if (axe.isDamageableItem()) {
                                    axe.setDamageValue(axe.getDamageValue() + 1);
                                    if (axe.getDamageValue() >= axe.getMaxDamage())
                                        axe.shrink(1);
                                }

                                return !getStorage().getStackInSlot(AXE_SLOT).is(ItemTags.AXES);
                            }

                    );

            if (items != null) {

                // consume durab of first block
                var axe = getStorage().getStackInSlot(AXE_SLOT);
                if (axe.isDamageableItem()) {
                    axe.setDamageValue(axe.getDamageValue() + 1);
                    if (axe.getDamageValue() >= axe.getMaxDamage())
                        axe.shrink(1);
                }

                // void overflow
                VanillaPlants.unifyDrops(items)
                        .forEach(s -> {
                            for (i = OUTPUT_SLOT_0; i < OUTPUT_SLOTS.size(); ++i) {
                                ItemStack slot = getStorage().getStackInSlot(i);
                                if (ItemStack.isSameItemSameComponents(slot, s)) {
                                    s = getStorage().insertItem(i, s, false);
                                    if (s.isEmpty()) {
                                        break;
                                    }
                                }
                            }

                            if (!s.isEmpty()) {
                                for (i = OUTPUT_SLOT_0; i < OUTPUT_SLOTS.size(); ++i) {
                                    if (getStorage().getStackInSlot(i).isEmpty()) {
                                        s = getStorage().insertItem(i, s, false);
                                        if (s.isEmpty()) {
                                            break;
                                        }
                                    }
                                }
                            }
                        });
            } else// one action every fuel burn
            if (getStorage().getStackInSlot(SAPLING_SLOT).getItem() instanceof BlockItem bi) {
                if (bi.getBlock() instanceof SaplingBlock sapling) {
                    if (sapling.defaultBlockState().canSurvive(level, pos)
                            && level.getBlockState(pos).canBeReplaced()) {
                        level.setBlockAndUpdate(pos, sapling.defaultBlockState());
                        level.playSound(null, pos,
                                sapling.defaultBlockState().getSoundType(level, pos, null).getPlaceSound(),
                                SoundSource.BLOCKS);
                        getStorage().getStackInSlot(SAPLING_SLOT).shrink(1);
                    }
                }

            }

            i++;
        }

        if (i >= size)
            i = 0;

    }

    @Override
    public AreaType getAreaType() {
        return AreaType.MIDDLE;
    }

    @Override
    public int getHeight() {
        return 1;
    }

    @Override
    public int getWidth() {
        return 3 + (2 * getStorage().getStackInSlot(UPGRADE_SLOT).getCount());
    }

    @Override
    public List<BlockPos> getArea() {
        return getCentredPosArea();
    }

    @Override
    public ItemStackHandler getStorage() {
        return getData(zHandlers.ITEM_STORAGE);
    }

    @Override
    public int MachineSlots() {
        return 13;
    }

    @Override
    public IItemHandler getStorageRestricted() {
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
                setChanged();
                if (slot == UPGRADE_SLOT)
                    return stack;

                if ((slot == AXE_SLOT && stack.is(ItemTags.AXES)) ||
                        (slot == SAPLING_SLOT && stack.is(ItemTags.SAPLINGS)) ||
                        (slot == FUEL_SLOT && (AbstractFurnaceBlockEntity.isFuel(stack)
                                || stack.is(zItemTag.CHOPPER_ENERGY_UPGRADE))))
                    return getStorage().insertItem(slot, stack, simulate);
                return stack;
            }

            @Override
            public ItemStack extractItem(int slot, int amount, boolean simulate) {
                if (OUTPUT_SLOTS.contains(slot))
                    return getStorage().extractItem(slot, amount, simulate);
                return ItemStack.EMPTY;
            }

            @Override
            public int getSlotLimit(int slot) {
                if (slot == UPGRADE_SLOT)
                    return 15;
                return getStorage().getSlotLimit(slot);
            }

            @Override
            public boolean isItemValid(int slot, ItemStack stack) {
                return (slot == AXE_SLOT && stack.is(ItemTags.AXES)) ||
                        (slot == UPGRADE_SLOT && stack.is(zItemTag.CHOPPER_AREA_INCREASE)) ||
                        (slot == SAPLING_SLOT && stack.is(ItemTags.SAPLINGS)) ||
                        (slot == FUEL_SLOT && (AbstractFurnaceBlockEntity.isFuel(stack)
                                || stack.is(zItemTag.CHOPPER_ENERGY_UPGRADE)));
            }

        };

    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        // tag.put("inventory", getStorage().serializeNBT(registries));
        tag.putInt("progress", progress);
        // tag.putInt("energy", energyStorage.getEnergyStored());
        super.saveAdditional(tag, registries);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        // getStorage().deserializeNBT(registries, tag.getCompound("inventory"));
        if (tag.contains("progress"))
            progress = tag.getInt("progress");
        // if (tag.contains("energy"))
        // energyStorage.receiveEnergy(Math.min(tag.getInt("energy"),
        // energyStorage.getMaxEnergyStored()), false);

        super.loadAdditional(tag, registries);
    }

    @Override
    public ContainerData getContainerData() {
        return this.data;
    }

    @Override
    public EnergyStorage getCapEnergy() {
        return handleEnergy()
                ? getData(zHandlers.ENERGY_STORAGE)
                : null;
    }

    @Override
    public int MaxFE() {
        return handleEnergy() ? 1_000 : 0;
    }

    public boolean handleEnergy() {
        return getStorage().getStackInSlot(FUEL_SLOT).is(zItemTag.CHOPPER_ENERGY_UPGRADE);
    }

}
