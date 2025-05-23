package com.devdyna.synergy.init.types;

import static com.devdyna.synergy.Main.ID;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

@SuppressWarnings("unused")
public class zContainer {
            public static void register(IEventBus bus) {
                zCTNR.register(bus);
        }

        public static final DeferredRegister<MenuType<?>> zCTNR = DeferredRegister.create(Registries.MENU, ID);

        // public static final DeferredHolder<MenuType<?>, MenuType<GUI>> GUI = zCTNR
        //                 .register("name",
        //                                 () -> IMenuTypeExtension.create(GUI::new));
}
