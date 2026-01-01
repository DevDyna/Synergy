package com.devdyna.synergy.api.beLogic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.devdyna.synergy.api.utils.LevelUtil;
import com.devdyna.synergy.api.utils.x;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.capabilities.BlockCapabilityCache;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.transfer.ResourceHandler;
import net.neoforged.neoforge.transfer.item.ItemResource;
import net.neoforged.neoforge.transfer.transaction.Transaction;

public interface ItemProducer {

    /**
     * when true it will drop all overflow items
     * when false it will void all overflow items
     */
    boolean dropWhenFail();

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
    default void exportItems(ItemStack item, List<Direction> blacklistedDirs, Level level, BlockPos pos,
            Map<Direction, BlockCapabilityCache<ResourceHandler<ItemResource>, Direction>> cache) {
        var totalDir = Direction.values().length;
        for (Direction dir : Direction.values()) {
            if (blacklistedDirs.contains(dir)) {
                totalDir--;
                continue;
            }
            var cachedData = cache.get(dir);
            if (cachedData == null)
                cachedData = BlockCapabilityCache.create(
                        Capabilities.Item.BLOCK,
                        (ServerLevel) level,
                        pos.relative(dir),
                        dir.getOpposite());
            cache.put(dir, cachedData);

            ResourceHandler<ItemResource> cap = cachedData.getCapability();

            if (cap == null || !(cap instanceof ResourceHandler<ItemResource>)) {
                totalDir--;
                continue;
            } else {

                try (var tx = Transaction.openRoot()) {

                    var insered = cap.insert(ItemResource.of(item.copy()), item.getCount(), tx);

                    if (insered != item.getCount() && dropWhenFail()) {

                        LevelUtil.popItemFromPos(level, pos.above(), x.item(item.getItem(), item.getCount() - insered));
                        level.playSound(null, pos, SoundEvents.ITEM_FRAME_REMOVE_ITEM, SoundSource.BLOCKS, 1F, 0.75F);

                    }
                    tx.commit();

                }

                break;
            }

        }

        if (totalDir <= 0 && dropWhenFail()) {

            LevelUtil.popItemFromPos(level, pos.above(), item);
        }

    }
}
