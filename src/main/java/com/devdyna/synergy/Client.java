package com.devdyna.synergy;

import com.devdyna.synergy.client.aoe.HarvesterAOE;
import com.devdyna.synergy.client.aoe.ReactorAOE;
import com.devdyna.synergy.client.aoe.SprinklerAOE;
import com.devdyna.synergy.client.gui.tiny_chest.chestScreen;
import com.devdyna.synergy.client.nodes.*;
import com.devdyna.synergy.init.types.zBlockEntities;
import com.devdyna.synergy.init.types.zContainer;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;

@EventBusSubscriber(modid = Main.ID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public class Client {

    @SubscribeEvent
    public static void render(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(zBlockEntities.ITEM_TRANSFER.get(), ItemTransferDebugRender::new);
        event.registerBlockEntityRenderer(zBlockEntities.ITEM_RETRIEVAL.get(), ItemRetrievalDebugRender::new);
        event.registerBlockEntityRenderer(zBlockEntities.ITEM_PROVIDER.get(), ItemProviderDebugRender::new);

        event.registerBlockEntityRenderer(zBlockEntities.HARVESTER.get(), HarvesterAOE::new);
        event.registerBlockEntityRenderer(zBlockEntities.SPRINKLER.get(), SprinklerAOE::new);
        event.registerBlockEntityRenderer(zBlockEntities.REACTOR_CONTROLLER.get(), ReactorAOE::new);

    }

    @SubscribeEvent
    public static void registerScreens(RegisterMenuScreensEvent event) {
        event.register(zContainer.CHEST_GUI.get(), chestScreen::new);
    }

}
