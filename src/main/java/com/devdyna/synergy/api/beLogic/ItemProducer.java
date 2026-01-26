package com.devdyna.synergy.api.beLogic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.devdyna.synergy.api.utils.LevelUtil;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.capabilities.BlockCapabilityCache;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemHandlerHelper;

public interface ItemProducer {

    /**
     * when true it will drop all overflow items
     * when false it will void all overflow items
     */
    default boolean dropWhenFail() {
        return false;
    }

    /**
     * unify all dropped items
     */
    default ArrayList<ItemStack> unifyDrops(List<ItemStack> items) {
        ArrayList<ItemStack> newItems = new ArrayList<>();

        for (int i = 0; i < items.size(); i++) {

            var check = false;
            int index = -1;
            for (ItemStack itemStack : newItems) {
                if (itemStack.getItem() == items.get(i).getItem()) {
                    if (itemStack.getCount() >= 64)
                        continue;
                    index = newItems.indexOf(itemStack);
                    check = true;
                    break;
                }
            }

            if (check) {

                newItems.set(index,
                        new ItemStack(newItems.get(index).getItem(),
                                newItems.get(index).getCount() + 1));

            } else {

                newItems.add(items.get(i));

            }

        }
        return newItems;
    }

    /**
     * export items to the nearest storage or will drop/void all overflow items
     */
    default boolean exportItems(ItemStack item, List<Direction> blacklistedDirs, Level level, BlockPos pos,
            Map<Direction, BlockCapabilityCache<IItemHandler, Direction>> cache) {
        var totalDir = Direction.values().length;
        for (Direction dir : Direction.values()) {
            if (blacklistedDirs.contains(dir)) {
                totalDir--;
                continue;
            }
            var cachedData = cache.get(dir);
            if (cachedData == null)
                cachedData = BlockCapabilityCache.create(
                        Capabilities.ItemHandler.BLOCK,
                        (ServerLevel) level,
                        pos.relative(dir),
                        dir.getOpposite());
            cache.put(dir, cachedData);

            IItemHandler cap = cachedData.getCapability();

            if (cap == null || !(cap instanceof IItemHandler)) {
                totalDir--;
                continue;
            }

            var items = ItemHandlerHelper.insertItemStacked(cap, item, false);

            if (item.is(items.getItem()) && item.getCount() == items.getCount()
                    && items != new ItemStack(Items.AIR) && dropWhenFail()) {

                LevelUtil.popItemFromPos(level, pos.above(), item);
                if (applySoundWhenFail())
                    level.playSound(null, pos, SoundEvents.ITEM_FRAME_REMOVE_ITEM, SoundSource.BLOCKS, 1F, 0.75F);

            }

            if (!dropWhenFail() && !items.isEmpty()) {
                totalDir--;
                continue;
            }

            break;

        }

        if (totalDir <= 0 && dropWhenFail()) {

            LevelUtil.popItemFromPos(level, pos.above(), item);
        }

        return totalDir <= 0;
    }

    default boolean applySoundWhenFail() {
        return false;
    }
}
