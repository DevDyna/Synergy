package com.devdyna.synergy;

import com.devdyna.synergy.api.machine.core.MaceratorMenu;
import com.devdyna.synergy.api.machine.core.MaceratorScreen;
import com.devdyna.synergy.client.aoe.*;
import com.devdyna.synergy.client.gui.fuel_cell.FuelCellScreen;
import com.devdyna.synergy.client.gui.tiny_chest.TinyChestScreen;
import com.devdyna.synergy.client.quern.QuernRendering;
import com.devdyna.synergy.init.types.zBlockEntities;
import com.devdyna.synergy.init.types.zContainer;
import com.devdyna.synergy.init.types.zItems;
import com.devdyna.synergy.init.types.zMachines;

import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.BucketItem;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;

@SuppressWarnings({ "removal", "deprecation","null" })
@EventBusSubscriber(modid = Main.ID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public class Client {

    @SubscribeEvent
    public static void render(EntityRenderersEvent.RegisterRenderers event) {

        event.registerBlockEntityRenderer(zBlockEntities.HARVESTER.get(), HarvesterAOE::new);
        event.registerBlockEntityRenderer(zBlockEntities.SPRINKLER.get(), SprinklerAOE::new);
        event.registerBlockEntityRenderer(zBlockEntities.REACTOR_CONTROLLER.get(), ReactorAOE::new);
        event.registerBlockEntityRenderer(zBlockEntities.QUERN.get(), QuernRendering::new);
    }

    @SubscribeEvent
    public static void registerScreens(RegisterMenuScreensEvent event) {
        event.register(zContainer.FUEL_CELL_MENU.get(), FuelCellScreen::new);
        event.register(zContainer.CHEST_MENU.get(), TinyChestScreen::new);
        event.register((MenuType<MaceratorMenu>) zMachines.MACERATOR.menu().get(), MaceratorScreen::new);
    }

    @SubscribeEvent
    public static void registerItemColor(RegisterColorHandlersEvent.Item event) {
        // TODO idk if it work but i will keep it
        for (var bucket : zItems.zBucketItems.getEntries()) {
            event.getItemColors().register((s, i) -> (i == 1 && s.getItem() instanceof BucketItem)
                    ? IClientFluidTypeExtensions.of(((BucketItem) s.getItem()).content).getTintColor()
                    : 0xFFFFFFFF, bucket.get());
        }

    }

}
