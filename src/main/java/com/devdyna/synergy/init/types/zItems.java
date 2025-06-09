package com.devdyna.synergy.init.types;

import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.Main;
import com.devdyna.synergy.init.builder.CroockItem;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class zItems {
    public static void register(IEventBus bus) {
        zItem.register(bus);
        zTool.register(bus);
        zBlockItem.register(bus);
    }

    // ---------------------------------------------------------------------------------------//
    public static final DeferredRegister.Items zTool = DeferredRegister.createItems(Main.ID);
    // DONT USE IT , ONLY FUNCTIONAL
    public static final DeferredRegister.Items zBlockItem = DeferredRegister.createItems(Main.ID);
    public static final DeferredRegister.Items zItem = DeferredRegister.createItems(Main.ID);
    // ---------------------------------------------------------------------------------------//

    public static final DeferredHolder<Item, Item> AZALEA_SEEDS = zItem.registerSimpleItem(zStatic.Items.Azalea.seeds);

    public static final DeferredHolder<Item, Item> SMALL_AZALEA_LEAF = zItem
            .registerSimpleItem(zStatic.Items.Azalea.leaf);

    public static final DeferredHolder<Item, Item> SMALL_AZALEA_ROOTS = zItem
            .registerSimpleItem(zStatic.Items.Azalea.roots);

    public static final DeferredHolder<Item, CroockItem> WOODEN_CROOK = zTool.register(zStatic.Items.wooden_crook,
            () -> new CroockItem(BlockTags.LEAVES, Tiers.NETHERITE));

}
