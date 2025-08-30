package com.devdyna.synergy.init.types;

import static com.devdyna.synergy.Main.ID;

import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.client.gui.tiny_chest.chestGUI;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class zContainer {
    public static void register(IEventBus bus) {
        zCTNR.register(bus);
    }

    public static final DeferredRegister<MenuType<?>> zCTNR = DeferredRegister.create(Registries.MENU, ID);

    public static final DeferredHolder<MenuType<?>, MenuType<chestGUI>> CHEST_GUI = zCTNR
            .register(zStatic.Blocks.tiny_wooden_chest,
                    () -> IMenuTypeExtension.create(chestGUI::new));
}
