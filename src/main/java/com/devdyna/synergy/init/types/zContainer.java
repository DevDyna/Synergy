package com.devdyna.synergy.init.types;

import static com.devdyna.synergy.Main.ID;

import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.init.builder.automation.chopper.ChopperMenu;
import com.devdyna.synergy.init.builder.magic.tiny_chests.ornated.OrnatedTinyChestMenu;
import com.devdyna.synergy.init.builder.magic.tiny_chests.stone.StoneTinyChestMenu;
import com.devdyna.synergy.init.builder.magic.tiny_chests.wooden.WoodenTinyChestMenu;
import com.devdyna.synergy.init.builder.nuclear_reactor.fuel_cell.FuelCellMenu;

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

        public static final DeferredHolder<MenuType<?>, MenuType<WoodenTinyChestMenu>> WOODEN_TINY_CHEST_MENU = zCTNR
                        .register(zStatic.Chests.WOODEN,
                                        () -> IMenuTypeExtension.create(WoodenTinyChestMenu::new));

        public static final DeferredHolder<MenuType<?>, MenuType<StoneTinyChestMenu>> STONE_TINY_CHEST_MENU = zCTNR
                        .register(zStatic.Chests.STONE,
                                        () -> IMenuTypeExtension.create(StoneTinyChestMenu::new));

        public static final DeferredHolder<MenuType<?>, MenuType<OrnatedTinyChestMenu>> ORNATED_TINY_CHEST_MENU = zCTNR
                        .register(zStatic.Chests.ORNATE,
                                        () -> IMenuTypeExtension.create(OrnatedTinyChestMenu::new));

        public static final DeferredHolder<MenuType<?>, MenuType<FuelCellMenu>> FUEL_CELL_MENU = zCTNR
                        .register(zStatic.ReactorStuff.fuel_cell,
                                        () -> IMenuTypeExtension.create(FuelCellMenu::new));

        public static final DeferredHolder<MenuType<?>, MenuType<ChopperMenu>> CHOPPER = zCTNR
                        .register(zStatic.Blocks.chopper,
                                        () -> IMenuTypeExtension.create(ChopperMenu::new));

}
