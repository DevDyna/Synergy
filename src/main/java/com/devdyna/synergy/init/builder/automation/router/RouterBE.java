package com.devdyna.synergy.init.builder.automation.router;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import javax.annotation.Nullable;

import org.checkerframework.checker.nullness.qual.NonNull;

import com.devdyna.synergy.api.basebe.be.BEStorage;
import com.devdyna.synergy.api.beLogic.DirectionBasedItemHandler;
import com.devdyna.synergy.init.types.zBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.IItemHandler;

@SuppressWarnings("null")
public class RouterBE extends BEStorage implements DirectionBasedItemHandler {

    public static final int LOGIC_SLOT = 0;

    public static final int FILTER_NORTH_1 = 1;
    public static final int FILTER_NORTH_2 = 2;
    public static final int FILTER_NORTH_3 = 3;

    public static final int FILTER_SOUTH_1 = 4;
    public static final int FILTER_SOUTH_2 = 5;
    public static final int FILTER_SOUTH_3 = 6;

    public static final int FILTER_EAST_1 = 7;
    public static final int FILTER_EAST_2 = 8;
    public static final int FILTER_EAST_3 = 9;

    public static final int FILTER_WEST_1 = 10;
    public static final int FILTER_WEST_2 = 11;
    public static final int FILTER_WEST_3 = 12;

    public static final int FILTER_UP_1 = 13;
    public static final int FILTER_UP_2 = 14;
    public static final int FILTER_UP_3 = 15;

    public static final int FILTER_DOWN_1 = 16;
    public static final int FILTER_DOWN_2 = 17;
    public static final int FILTER_DOWN_3 = 18;

    public static final List<List<Integer>> DIRECTION_BASED_SLOTS = List.of(
            List.of(
                    FILTER_DOWN_1,
                    FILTER_DOWN_2,
                    FILTER_DOWN_3),

            List.of(
                    FILTER_UP_1,
                    FILTER_UP_2,
                    FILTER_UP_3),

            List.of(FILTER_NORTH_1,
                    FILTER_NORTH_2,
                    FILTER_NORTH_3),

            List.of(
                    FILTER_SOUTH_1,
                    FILTER_SOUTH_2,
                    FILTER_SOUTH_3),

            List.of(
                    FILTER_WEST_1,
                    FILTER_WEST_2,
                    FILTER_WEST_3),

            List.of(
                    FILTER_EAST_1,
                    FILTER_EAST_2,
                    FILTER_EAST_3)

    );

    public static final List<Integer> ALL_FILTER_SLOTS = List.of(
            FILTER_NORTH_1,
            FILTER_NORTH_2,
            FILTER_NORTH_3,

            FILTER_SOUTH_1,
            FILTER_SOUTH_2,
            FILTER_SOUTH_3,

            FILTER_EAST_1,
            FILTER_EAST_2,
            FILTER_EAST_3,

            FILTER_WEST_1,
            FILTER_WEST_2,
            FILTER_WEST_3,

            FILTER_UP_1,
            FILTER_UP_2,
            FILTER_UP_3,

            FILTER_DOWN_1,
            FILTER_DOWN_2,
            FILTER_DOWN_3);

    public List<Integer> getFiltersBySide(Direction dir) {
        var index = Arrays.asList(Direction.values()).indexOf(dir);
        return index == -1 ? List.of() : DIRECTION_BASED_SLOTS.get(index);
    }

    public List<ItemStack> getItemFiltersBySide(@NonNull Direction dir) {
        return getFiltersBySide(dir).stream().map(s -> getStorage().getStackInSlot(s)).toList();
    }

    public RouterBE(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }

    public RouterBE(BlockPos pos, BlockState blockState) {
        this(zBlockEntities.ROUTER.get(), pos, blockState);
    }

    @Override
    @Nullable
    public AbstractContainerMenu createMenu(int arg0, Inventory arg1, Player arg2) {
        return new RouterMenu(arg0, arg1, this);
    }

    @Override
    public int MachineSlots() {
        return 19;
    }

    @Override
    public IItemHandler getStorageRestricted(Direction dir) {
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
            public ItemStack insertItem(int slot, ItemStack stack, boolean sim) {

                if (slot == LOGIC_SLOT)
                    return ALL_FILTER_SLOTS.stream()
                            .map(i -> getStorage().getStackInSlot(i))
                            .filter(
                                    Predicate.not(
                                            ItemStack::isEmpty))
                            .anyMatch(s -> ItemStack.isSameItemSameComponents(stack, s))
                                    ? getStorage().insertItem(slot, stack, sim)
                                    : stack;

                return stack;

            }

            @Override
            public ItemStack extractItem(int slot, int amount, boolean simulate) {
                var indexs = getFiltersBySide(dir);

                if (!indexs.isEmpty())
                    if (slot == LOGIC_SLOT)
                        if (!getStorage().getStackInSlot(LOGIC_SLOT).isEmpty()) 
                            for (Integer s : indexs) {

                                if (getStorage().getStackInSlot(s).isEmpty())
                                    continue;

                                if (ItemStack.isSameItemSameComponents(getStorage().getStackInSlot(s),
                                        getStorage().getStackInSlot(LOGIC_SLOT)))
                                    return getStorage().extractItem(slot, amount, simulate);
                            }
                        

                return ItemStack.EMPTY;

            }

            @Override
            public int getSlotLimit(int slot) {
                return getStorage().getSlotLimit(slot);
            }

            @Override
            public boolean isItemValid(int slot, ItemStack stack) {
                return slot == LOGIC_SLOT ? getStorage().isItemValid(slot, stack) : false;
            }

        };

    }

    public ItemStack getInternal() {
        return getStorage().getStackInSlot(LOGIC_SLOT);
    }

    @Override
    public List<Integer> getValidSlots() {
        return List.of(LOGIC_SLOT);
    }
}
