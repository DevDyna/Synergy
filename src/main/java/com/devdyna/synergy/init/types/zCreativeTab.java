package com.devdyna.synergy.init.types;

import com.devdyna.synergy.zStatic;

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
import net.neoforged.neoforge.registries.DeferredRegister.Items;

public class zCreativeTab {
        public static void register(IEventBus bus) {
                zCreative.register(bus);
        }

        // ---------------------------------------------------------------------------------------//

        public static final DeferredRegister<CreativeModeTab> zCreative = DeferredRegister
                        .create(Registries.CREATIVE_MODE_TAB, Main.ID);
        // ---------------------------------------------------------------------------------------//

        private static List<Items> types = List.of(

                        zItems.zBlockItem,
                        zItems.zTool,

                        zItems.zFoods,
                        zItems.zCropExtra,
                        zItems.zSeeds,

                        zItems.zCraftingComponents,

                        zItems.zResources,

                        zItems.zDusts,
                        zItems.zFoils,
                        zItems.zGems,
                        zItems.zIngots,
                        zItems.zNuggets,
                        zItems.zPlates,
                        zItems.zRawOres,
                        zItems.zShards

        );

        public static final DeferredHolder<CreativeModeTab, CreativeModeTab> TAB = zCreative
                        .register(Main.ID, () -> CreativeModeTab.builder()
                                        .title(Component.translatable(Main.ID + "." + zStatic.CreativeTab))
                                        .withTabsBefore(CreativeModeTabs.COMBAT)
                                        .icon(() -> zItems.CONFIGURATOR.get().getDefaultInstance())
                                        .displayItems((parameters, output) -> {

                                                types.forEach(t -> t.getEntries().forEach(e -> {
                                                        output.accept((Item) e.get());
                                                }));

                                        }).build());

}
