package com.devdyna.synergy;

import com.devdyna.synergy.client.PipeDebugRender;
import com.devdyna.synergy.init.types.zBlockEntities;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(modid = Main.ID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public class Client {

    @SubscribeEvent
    public static void render(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(zBlockEntities.ITEM_TRANSFER.get(), PipeDebugRender::new);
    }

       

        event.registerBlockEntityRenderer(zBlockEntities.ITEM_TRANSFER.get(), ItemTransferDebugRender::new)
        event.registerBlockEntityRenderer(zBlockEntities.ITEM_PROVIDER.get(),ItemProviderDebugRender::new)
    }

}
