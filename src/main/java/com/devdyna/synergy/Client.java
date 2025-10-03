package com.devdyna.synergy;

import com.devdyna.synergy.client.aoe.*;
import com.devdyna.synergy.client.gui.fuel_cell.FuelCellScreen;
import com.devdyna.synergy.client.gui.tiny_chest.TinyChestScreen;
import com.devdyna.synergy.client.quern.QuernRendering;
import com.devdyna.synergy.init.types.zBlockEntities;
import com.devdyna.synergy.init.types.zContainer;
import com.devdyna.synergy.init.types.zItems;
import com.devdyna.synergy.utils.ClazzUtil;

import net.minecraft.client.Minecraft;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.material.FluidState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;

@SuppressWarnings({ "removal", "deprecation", "null" })
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
    }

    @SubscribeEvent
    public static void registerItemColor(RegisterColorHandlersEvent.Item event) {
        final ItemColors colors = event.getItemColors();

        for (var bucket : zItems.zBucketItems.getEntries()) {
            colors.register((s, i) -> (i == 1 && s.getItem() instanceof BucketItem)
                    ? IClientFluidTypeExtensions.of(((BucketItem) s.getItem()).content).getTintColor()
                    : 0xFFFFFFFF, bucket.get());
        }

    }

    @SubscribeEvent
    static void onRegisterClientExtensions(RegisterClientExtensionsEvent event) {

        ClazzUtil.getAllzFluids().forEach(f ->

        event.registerFluidType(new IClientFluidTypeExtensions() {
            @Override
            public ResourceLocation getStillTexture() {
                return f.getStillTexture();
            }

            @Override
            public ResourceLocation getFlowingTexture() {
                return f.getFlowingTexture();
            }

            @Override
            public ResourceLocation getOverlayTexture() {
                return f.getOverlayTexture();
            }

            @Override
            public ResourceLocation getRenderOverlayTexture(Minecraft mc) {
                return f.getUiInsideTexture();
            }

            @Override
            public int getTintColor() {
                return f.getColor();
            }

            @Override
            public int getTintColor(FluidState state, BlockAndTintGetter getter, BlockPos pos) {
                return f.getColor();
            }
        }, f.getType())

        );

    }

}
