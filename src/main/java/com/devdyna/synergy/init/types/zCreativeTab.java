package com.devdyna.synergy.init.types;

import com.devdyna.synergy.zStatic;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.devdyna.synergy.Main;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class zCreativeTab {
        public static void register(IEventBus bus) {
                zCreative.register(bus);
        }

        // ---------------------------------------------------------------------------------------//

        public static final DeferredRegister<CreativeModeTab> zCreative = DeferredRegister
                        .create(Registries.CREATIVE_MODE_TAB, Main.ID);
        // ---------------------------------------------------------------------------------------//

        private static List<DeferredHolder<Item, ?>> getAllzItems() {
                List<DeferredHolder<Item, ?>> items = new ArrayList<>();

                for (Field field : zItems.class.getDeclaredFields()) {
                        try {
                                // Only pick DeferredHolder<Item, ?>
                                if (DeferredHolder.class.isAssignableFrom(field.getType())) {
                                        Object value = field.get(null); // static field → null instance
                                        if (value instanceof DeferredHolder<?, ?> holder) {
                                                // Ensure it's an Item DeferredHolder
                                                if (holder.value() instanceof Item) {
                                                        @SuppressWarnings("unchecked")
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

        public static final DeferredHolder<CreativeModeTab, CreativeModeTab> TAB = zCreative
                        .register(Main.ID, () -> CreativeModeTab.builder()
                                        .title(Component.translatable(Main.ID + "." + zStatic.CreativeTab))
                                        .withTabsBefore(CreativeModeTabs.COMBAT)
                                        .icon(() -> zItems.CONFIGURATOR.get().getDefaultInstance())
                                        .displayItems((parameters, output) -> {

                                                getAllzItems().forEach(e -> {
                                                        output.accept((Item) e.get());
                                                });

                                        }).build());

}
