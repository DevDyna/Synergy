package com.devdyna.synergy.init.types;

import com.devdyna.synergy.zStatic;

import com.devdyna.synergy.Main;
import com.devdyna.synergy.utils.*;

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

        

        public static final DeferredHolder<CreativeModeTab, CreativeModeTab> TAB = zCreative
                        .register(Main.ID, () -> CreativeModeTab.builder()
                                        .title(Component.translatable(Main.ID + "." + zStatic.CreativeTab))
                                        .withTabsBefore(CreativeModeTabs.COMBAT)
                                        .icon(() -> zItems.CONFIGURATOR.get().getDefaultInstance())
                                        .displayItems((parameters, output) -> {

                                                zItems.zBlockItem.getEntries().forEach(e -> {
                                                        output.accept((Item) e.get());
                                                });

                                                ClazzUtil.getAllzItems().forEach(e -> {
                                                        output.accept((Item) e.get());
                                                });

                                        }).build());

}
