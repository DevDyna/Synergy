package com.devdyna.synergy;

import com.devdyna.synergy.api.utils.ClazzUtil;
import com.devdyna.synergy.api.utils.x;
import com.devdyna.synergy.init.builder.automation.chopper.ChopperAOE;
import com.devdyna.synergy.init.builder.automation.chopper.ChopperScreen;
import com.devdyna.synergy.init.builder.automation.harvester.HarvesterAOE;
import com.devdyna.synergy.init.builder.automation.router.RouterScreen;
import com.devdyna.synergy.init.builder.automation.sprinkler.SprinklerAOE;
import com.devdyna.synergy.init.builder.automation.tank.FluidTankFluidRender;
import com.devdyna.synergy.init.builder.industrial_machines.alloy_smelter.AlloySmelterScreen;
import com.devdyna.synergy.init.builder.industrial_machines.caster.CasterScreen;
import com.devdyna.synergy.init.builder.industrial_machines.compressor.CompressorScreen;
import com.devdyna.synergy.init.builder.industrial_machines.extractor.ExtractorScreen;
import com.devdyna.synergy.init.builder.industrial_machines.furnace.ElectricFurnaceScreen;
import com.devdyna.synergy.init.builder.industrial_machines.macerator.MaceratorScreen;
import com.devdyna.synergy.init.builder.industrial_machines.melter.MelterScreen;
import com.devdyna.synergy.init.builder.industrial_machines.rock_crusher.RockCrusherScreen;
import com.devdyna.synergy.init.builder.magic.entity_watcher.EntityWatcherRender;
import com.devdyna.synergy.init.builder.magic.logic_box.LogicBoxRender;
import com.devdyna.synergy.init.builder.magic.quern.QuernRendering;
import com.devdyna.synergy.init.builder.magic.tiny_chests.ornated.OrnatedTinyChestScreen;
import com.devdyna.synergy.init.builder.magic.tiny_chests.stone.StoneTinyChestScreen;
import com.devdyna.synergy.init.builder.magic.tiny_chests.wooden.WoodenTinyChestScreen;
import com.devdyna.synergy.init.builder.magic.void_box.VoidBoxRender;
import com.devdyna.synergy.init.builder.nuclear_reactor.controller.ReactorAOE;
import com.devdyna.synergy.init.builder.nuclear_reactor.fuel_cell.FuelCellScreen;
import com.devdyna.synergy.init.builder.survival.casting_table.CastingTableRender;
import com.devdyna.synergy.init.builder.survival.crushing_tub.CrushingTubRender;
import com.devdyna.synergy.init.builder.survival.drying_rack.DryingRackRender;
import com.devdyna.synergy.init.builder.survival.evaporation_basin.EvaporationBasinRender;
import com.devdyna.synergy.init.builder.survival.faucet.FaucetRender;
import com.devdyna.synergy.init.builder.survival.foundry.FoundryRender;
import com.devdyna.synergy.init.types.zBlockEntities;
import com.devdyna.synergy.init.types.zBlocks;
import com.devdyna.synergy.init.types.zContainer;
import com.devdyna.synergy.init.types.zItems;
import com.devdyna.synergy.init.types.zMachines;

import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.level.FoliageColor;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.ModelEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;

@EventBusSubscriber(value = Dist.CLIENT)
@Mod(value = Main.ID, dist = Dist.CLIENT)
public class Client {

    @SubscribeEvent
    public static void render(EntityRenderersEvent.RegisterRenderers event) {

        event.registerBlockEntityRenderer(zBlockEntities.HARVESTER.get(), HarvesterAOE::new);
        event.registerBlockEntityRenderer(zBlockEntities.SPRINKLER.get(), SprinklerAOE::new);
        event.registerBlockEntityRenderer(zBlockEntities.REACTOR_CONTROLLER.get(), ReactorAOE::new);
        event.registerBlockEntityRenderer(zBlockEntities.QUERN.get(), QuernRendering::new);
        event.registerBlockEntityRenderer(zBlockEntities.VOID_BOX.get(), VoidBoxRender::new);
        event.registerBlockEntityRenderer(zBlockEntities.LOGIC_BOX.get(), LogicBoxRender::new);

        event.registerBlockEntityRenderer(zBlockEntities.FLUID_TANK.get(), FluidTankFluidRender::new);

        event.registerBlockEntityRenderer(zBlockEntities.CRUSHING_TUB.get(), CrushingTubRender::new);
        event.registerBlockEntityRenderer(zBlockEntities.EVAPORATION_BASIN.get(), EvaporationBasinRender::new);

        event.registerBlockEntityRenderer(zBlockEntities.DRYING_RACK.get(), DryingRackRender::new);
        event.registerBlockEntityRenderer(zBlockEntities.FOUNDRY.get(), FoundryRender::new);
        event.registerBlockEntityRenderer(zBlockEntities.CASTING_TABLE.get(), CastingTableRender::new);
        event.registerBlockEntityRenderer(zBlockEntities.FAUCET.get(), FaucetRender::new);

        event.registerBlockEntityRenderer(zBlockEntities.CHOPPER.get(), ChopperAOE::new);

        event.registerBlockEntityRenderer(zBlockEntities.ENTITY_WATCHER.get(), EntityWatcherRender::new);

    }

    @SubscribeEvent
    public static void registerScreens(RegisterMenuScreensEvent event) {
        event.register(zContainer.FUEL_CELL_MENU.get(), FuelCellScreen::new);
        event.register(zContainer.WOODEN_TINY_CHEST_MENU.get(), WoodenTinyChestScreen::new);
        event.register(zContainer.STONE_TINY_CHEST_MENU.get(), StoneTinyChestScreen::new);
        event.register(zContainer.ORNATED_TINY_CHEST_MENU.get(), OrnatedTinyChestScreen::new);

        event.register(zMachines.MACERATOR.menu().get(), MaceratorScreen::new);
        event.register(zMachines.COMPRESSOR.menu().get(), CompressorScreen::new);
        event.register(zMachines.ALLOY_SMELTER.menu().get(), AlloySmelterScreen::new);
        event.register(zMachines.ELECTRIC_FURNACE.menu().get(), ElectricFurnaceScreen::new);
        event.register(zMachines.EXTRACTOR.menu().get(), ExtractorScreen::new);
        event.register(zMachines.CASTING_FACTORY.menu().get(), CasterScreen::new);
        event.register(zMachines.MELTER.menu().get(), MelterScreen::new);
        event.register(zMachines.ROCK_CRUSHER.menu().get(), RockCrusherScreen::new);

        event.register(zContainer.CHOPPER.get(), ChopperScreen::new);
        event.register(zContainer.ROUTER.get(), RouterScreen::new);
    }

    @SuppressWarnings("deprecation")
    @SubscribeEvent
    public static void registerItemColor(RegisterColorHandlersEvent.Item event) {
        // idk if it work but i will keep it
        for (var bucket : zItems.zBucketItems.getEntries()) {
            event.register((s, i) -> (i == 1 && s.getItem() instanceof BucketItem)
                    ? IClientFluidTypeExtensions.of(((BucketItem) s.getItem()).content).getTintColor()
                    : 0xFFFFFFFF, bucket.get());
        }

        event.register((s, i) -> {
            return FoliageColor.getDefaultColor();
        }, zBlocks.IRON_WOOD.getLeaves().get());

    }

    @SubscribeEvent
    public static void registerAdditionalModels(ModelEvent.RegisterAdditional event) {
        ClazzUtil.getAllAdditionalModels()
                .stream()
                .map(x::rl)
                .map(ModelResourceLocation::standalone)
                .forEach(event::register);
    }

    @SubscribeEvent
    public static void registerBlockColors(RegisterColorHandlersEvent.Block event) {

        event.register((state, level, pos, tintIndex) -> {

            if (pos == null || level == null)
                return FoliageColor.getDefaultColor();

            return BiomeColors.getAverageFoliageColor(level, pos);

        }, zBlocks.IRON_WOOD.getLeaves().get());
    }

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        // if(!Common.DISABLE_PONDER_COMPAT.get())
        // event.enqueueWork(() ->PonderIndex.addPlugin(new Plugin()));
    }

}
