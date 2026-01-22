package com.devdyna.synergy.api.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.devdyna.synergy.api.MachineType;
import com.devdyna.synergy.api.zFluid;
import com.devdyna.synergy.init.types.zBlocks;
import com.devdyna.synergy.init.types.zFluids;
import com.devdyna.synergy.init.types.zItems;
import com.devdyna.synergy.init.types.zMachines;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class ClazzUtil {

    /**
     * Dont use on LootTableProviders
     */
    public static List<DeferredHolder<Item, ?>> getAllzItems() {
        List<DeferredHolder<Item, ?>> items = new ArrayList<>();

        for (Field field : zItems.class.getDeclaredFields()) {
            try {
                if (DeferredHolder.class.isAssignableFrom(field.getType())) {
                    Object value = field.get(null);
                    if (value instanceof DeferredHolder<?, ?> holder) {
                        if (holder.value() instanceof Item) {
                            DeferredHolder<Item, ?> itemHolder = (DeferredHolder<Item, ?>) holder;
                            items.add(itemHolder);
                        }
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return items;
    }

    /**
     * Dont use on LootTableProviders
     */
    public static List<DeferredHolder<Block, ?>> getAllzBlocks() {
        List<DeferredHolder<Block, ?>> blocks = new ArrayList<>();

        for (Field field : zBlocks.class.getDeclaredFields()) {
            try {
                if (DeferredHolder.class.isAssignableFrom(field.getType())) {
                    Object value = field.get(null);
                    if (value instanceof DeferredHolder<?, ?> holder
                            && holder.value() instanceof Block) {

                        blocks.add((DeferredHolder<Block, ?>) holder);
                    }
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return blocks;
    }

    public static List<zFluid> getAllzFluids(String filter_whitelist) {
        return getAllzFluids().stream().filter(f -> f.getId().contains(filter_whitelist)).toList();
    }

    /**
     * Dont use on LootTableProviders
     */
    public static List<zFluid> getAllzFluids() {
        List<zFluid> l = new ArrayList<>();

        for (Field field : zFluids.class.getDeclaredFields()) {
            try {
                if (zFluid.class.isAssignableFrom(field.getType())) {
                    Object value = field.get(null);
                    if (value instanceof zFluid f) {
                        l.add(f);
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return l;
    }

    public static String[] getAllStrings(Class<?> clazz) {
        try {
            Field[] fields = clazz.getDeclaredFields();
            return java.util.Arrays.stream(fields)
                    .filter(f -> f.getType() == String.class) // only String fields
                    .map(f -> {
                        try {
                            f.setAccessible(true);
                            return (String) f.get(null); // works for static fields
                        } catch (Exception e) {
                            return null;
                        }
                    })
                    .filter(s -> s != null) // ignore null values
                    .toArray(String[]::new);
        } catch (Exception e) {
            e.printStackTrace();
            return new String[0];
        }
    }

    // public static List<DeferredHolder<Item, ?>>
    // getAllzItemsFiltered(DeferredRegister.Blocks... blacklist) {
    // List<DeferredHolder<Item, ?>> allItems = ClazzUtil.getAllzItems();

    // Set<Block> filteredBlocks = Arrays.stream(blacklist)
    // .flatMap(reg -> reg.getEntries().stream())
    // .map(holder -> holder.get())
    // .collect(Collectors.toSet());

    // return allItems.stream()
    // .filter(itemHolder -> {
    // Item item = itemHolder.get();
    // return !(item instanceof BlockItem bi) ||
    // !filteredBlocks.contains(bi.getBlock());
    // })
    // .collect(Collectors.toList());
    // }

    // public static Item[] getAllzBLocksFiltered(DeferredRegister.Blocks...
    // whitelist) {
    // List<DeferredHolder<Block, ?>> blocks = ClazzUtil.getAllzBlocks();

    // Set<Block> filteredBlocks = Arrays.stream(whitelist)
    // .flatMap(reg -> reg.getEntries().stream())
    // .map(DeferredHolder::get)
    // .collect(Collectors.toSet());

    // return blocks.stream()
    // .map(DeferredHolder::get)
    // .filter(filteredBlocks::contains)
    // .map(Block::asItem)
    // .toArray(Item[]::new);
    // }

    // /**
    // * @param inverse <br/>
    // * <br/>
    // * True -> blacklist <br/>
    // * <br/>
    // * False -> whitelist
    // */
    // public static List<DeferredHolder<Item, ?>> getAllzItemsFiltered(boolean
    // inverse,
    // DeferredRegister.Blocks... blocks) {
    // List<DeferredHolder<Item, ?>> allItems = ClazzUtil.getAllzItems();

    // Set<Block> blockSet = Arrays.stream(blocks)
    // .flatMap(reg -> reg.getEntries().stream())
    // .map(holder -> holder.get())
    // .collect(Collectors.toSet());

    // return allItems.stream()
    // .filter(itemHolder -> {
    // Item item = itemHolder.get();

    // if (!(item instanceof BlockItem bi))
    // return true;

    // boolean contains = blockSet.contains(bi.getBlock());
    // return inverse ? !contains : contains;
    // })
    // .collect(Collectors.toList());
    // }

    public static List<DeferredHolder<Item, ?>> getAllzItems(
            DeferredRegister.Items... whitelist) {
        List<DeferredHolder<Item, ?>> allItems = ClazzUtil.getAllzItems();

        Set<Item> whitelistedItems = Arrays.stream(whitelist)
                .flatMap(reg -> reg.getEntries().stream())
                .map(DeferredHolder::get)
                .collect(Collectors.toSet());

        return allItems.stream()
                .filter(h -> whitelistedItems.contains(h.get()))
                .toList();
    }

    public static List<DeferredHolder<Block, ?>> getAllzBlocks(
            DeferredRegister.Blocks... whitelist) {

        List<DeferredHolder<Block, ?>> allBlocks = ClazzUtil.getAllzBlocks();

        Set<ResourceLocation> whitelistIds = Arrays.stream(whitelist)
                .flatMap(reg -> reg.getEntries().stream())
                .map(DeferredHolder::getId)
                .collect(Collectors.toSet());

        return allBlocks.stream()
                .filter(h -> whitelistIds.contains(h.getId()))
                .toList();
    }

    public static List<MachineType> getAllMachineTypes() {

        List<MachineType> types = new ArrayList<>();
        Field[] fields = zMachines.class.getDeclaredFields();
        for (Field field : fields) {
            if (field.getType() == MachineType.class) {
                try {
                    types.add((MachineType) field.get(null));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return types;
    }

    public static List<Item> include(List<DeferredHolder<Item, ?>> list, DeferredHolder<Block, Block>... blocks) {
        // DeferredHolder<Item, ?> -> Item
        List<Item> items = list.stream()
                .map(DeferredHolder::get)
                .collect(Collectors.toList());

        // DeferredHolder<Block, Block> -> list
        List<Item> blockItems = Stream.of(blocks)
                .map(DeferredHolder::get)
                .map(block -> block.asItem())
                .collect(Collectors.toList());

        items.addAll(blockItems);
        return items;
    }

}
