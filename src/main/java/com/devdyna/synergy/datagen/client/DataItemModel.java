package com.devdyna.synergy.datagen.client;

import static com.devdyna.synergy.Main.ID;

import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.api.utils.DataGenUtil;
import com.devdyna.synergy.api.utils.x;
import com.devdyna.synergy.datagen.api.ExtraItemModelProvider;
import com.devdyna.synergy.init.types.zBlocks;
import com.devdyna.synergy.init.types.zItems;

import net.minecraft.data.PackOutput;
import net.minecraft.world.item.BucketItem;
import net.neoforged.neoforge.client.model.generators.loaders.DynamicFluidContainerModelBuilder;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.internal.versions.neoforge.NeoForgeVersion;

public class DataItemModel extends ExtraItemModelProvider {

        public DataItemModel(PackOutput o, ExistingFileHelper f) {
                super(o, ID, f);
        }

        @Override
        protected void registerModels() {

                withExistingParent(x.path(zBlocks.AZALEA.get().asItem()), x.rl("block/azalea/item"));

                decorative();
                resources();
                machines();

                zItems.zBucketItems.getEntries().forEach(b -> withExistingParent(
                                x.path(b.get()),
                                x.rl(NeoForgeVersion.MOD_ID, "item/bucket"))
                                .customLoader(DynamicFluidContainerModelBuilder::begin)
                                .fluid(((BucketItem) b.get()).content));

                DataGenUtil.itemBlockwithParent(zBlocks.SPRINKLER.get(), this,
                                ID + ":block/" + x.path(zBlocks.SPRINKLER.get()));

                withExistingParent(zBlocks.PIPE.getRegisteredName(), modLoc("block/pipe/basic/item_model"));

                withExistingParent(zBlocks.SOLAR_PANEL.getRegisteredName(), modLoc("block/solar_panel/item_model"));

                node(zBlocks.ITEM_PROVIDER, "green");
                node(zBlocks.ITEM_RETRIEVAL, "aqua");
                node(zBlocks.ITEM_TRANSFER, "red");

                node(zBlocks.FLUID_PROVIDER, "green");
                node(zBlocks.FLUID_RETRIEVAL, "aqua");
                node(zBlocks.FLUID_TRANSFER, "red");

                // node(zBlocks.ENERGY_PROVIDER, "green");
                node(zBlocks.ENERGY_RETRIEVAL, "aqua");
                node(zBlocks.ENERGY_TRANSFER, "red");

                orientableWithBottom(zBlocks.HARVESTER.getRegisteredName(),
                                modLoc("block/machine/frame/basic/side"),
                                modLoc("block/machine/farming/harvester"),
                                modLoc("block/machine/frame/basic/bottom"),
                                modLoc("block/machine/frame/basic/top"));

                orientableWithBottom(zBlocks.REACTOR_CONTROLLER.getRegisteredName(),
                                modLoc("block/machine/frame/advanced/side"),
                                modLoc("block/machine/nuclear/controller/front_off"),
                                modLoc("block/machine/frame/advanced/bottom"),
                                modLoc("block/machine/frame/advanced/top"));

                cubeBottomTop(zBlocks.ADVANCED_MACHINE_FRAME.getRegisteredName(),
                                modLoc("block/machine/frame/advanced/side"),
                                modLoc("block/machine/frame/advanced/bottom"),
                                modLoc("block/machine/frame/advanced/top"));

                cubeBottomTop(zBlocks.BASIC_MACHINE_FRAME.getRegisteredName(),
                                modLoc("block/machine/frame/basic/side"),
                                modLoc("block/machine/frame/basic/bottom"),
                                modLoc("block/machine/frame/basic/top"));

                simpleFullBlock(zBlocks.HEALER, "");
                simpleFlexibleBlock(zBlocks.REACTOR_FUEL_CELL, "machine/nuclear/fuel_cell");
                simpleFlexibleBlock(zBlocks.COOLER_BASE, "machine/nuclear/cooler/base");

                CoolerBlock(zBlocks.COPPER_COOLER, mcLoc("block/copper_block"));
                CoolerBlock(zBlocks.GOLD_COOLER, mcLoc("block/gold_block"));
                CoolerBlock(zBlocks.IRON_COOLER, mcLoc("block/iron_block"));
                CoolerBlock(zBlocks.ENDER_COOLER, mcLoc("block/purpur_block"));
                CoolerBlock(zBlocks.FROST_COOLER, mcLoc("block/blue_ice"));
                CoolerBlock(zBlocks.LAPIS_COOLER, mcLoc("block/lapis_block"));
                CoolerBlock(zBlocks.SCULK_COOLER, mcLoc("block/sculk"));
                CoolerBlock(zBlocks.WATER_COOLER, mcLoc("block/ice"));
                CoolerBlock(zBlocks.QUARTZ_COOLER, mcLoc("block/quartz_block_top"));
                CoolerBlock(zBlocks.SHADOW_COOLER, modLoc("block/machine/nuclear/cooler/shadow"));
                CoolerBlock(zBlocks.DIAMOND_COOLER, mcLoc("block/diamond_block"));
                CoolerBlock(zBlocks.EMERALD_COOLER, mcLoc("block/emerald_block"));
                CoolerBlock(zBlocks.REDSTONE_COOLER, mcLoc("block/redstone_block"));
                CoolerBlock(zBlocks.GLOWSTONE_COOLER, mcLoc("block/glowstone"));
                CoolerBlock(zBlocks.NETHERITE_COOLER, mcLoc("block/netherite_block"));

                moderatorBlock(zBlocks.SIMPLE_MODERATOR, modLoc("block/decorative/wrought_iron_block"));
                moderatorBlock(zBlocks.ADVANCED_MODERATOR, modLoc("block/machine/nuclear/moderator/advanced_frame"));
                moderatorBlock(zBlocks.ELITE_MODERATOR, modLoc("block/machine/nuclear/moderator/elite_frame"));

                tinyChestAll(zBlocks.WOODEN_TINY_CHEST, "block/tiny_chest/wooden");
                tinyChestAll(zBlocks.STONE_TINY_CHEST, "block/tiny_chest/stone");
                tinyChestAll(zBlocks.ORNATE_TINY_CHEST, "block/tiny_chest/ornate");
                tinyChestAll(zBlocks.VOID_BOX, "block/void_box/chest");
                tinyChestAll(zBlocks.LOGIC_BOX, "block/logic_box/item");

                withExistingParent(zBlocks.URN.getRegisteredName(),
                                modLoc("block/urn"));

                withExistingParent(zBlocks.QUERN.getRegisteredName(),
                                modLoc("block/quern/item"));

                withExistingParent(zBlocks.CLAY_BRICK.getRegisteredName(),
                                mcLoc("item/clay_ball"));

                withExistingParent(zBlocks.PACKED_MUD_BRICK.getRegisteredName(),
                                mcLoc("minecraft:item/generated"))
                                .texture("layer0",
                                                modLoc("item/resources/packed_mud_ball"));

                withExistingParent(zBlocks.FIRECLAY_BRICK.getRegisteredName(),
                                mcLoc("minecraft:item/generated"))
                                .texture("layer0",
                                                modLoc("item/resources/fireclay_ball"));

                withExistingParent(zBlocks.BLAST_BRICK.getRegisteredName(),
                                mcLoc("minecraft:item/generated"))
                                .texture("layer0",
                                                modLoc("item/resources/clay_mixture_ball"));

                withExistingParent(zBlocks.LASER_MACHINE.getRegisteredName(), modLoc("block/laser_machine_gun/off"));
                withExistingParent(zBlocks.LASER_ROTOR.getRegisteredName(), modLoc("block/laser_rotor/off"));

                withExistingParent(zBlocks.LASER_LENS.getRegisteredName(), modLoc("block/laser_lens"));
                withExistingParent(zBlocks.LASER_MIRROR.getRegisteredName(), modLoc("block/laser_mirror"));
                withExistingParent(zBlocks.LASER_SENSOR.getRegisteredName(), modLoc("block/laser_sensor/off"));

                withExistingParent(x.path(zBlocks.PULSE_REPEATER.get()), "minecraft:item/generated")
                                .texture("layer0", modLoc("item/redstone/pulse_repeater"));
                withExistingParent(x.path(zBlocks.RECURSIVE_REPEATER.get()), "minecraft:item/generated")
                                .texture("layer0", modLoc("item/redstone/recursive_repeater"));
                withExistingParent(x.path(zBlocks.INVERTED_REPEATER.get()), "minecraft:item/generated")
                                .texture("layer0", modLoc("item/redstone/inverted_repeater"));

                withExistingParent(zBlocks.SIMPLE_WATER_GEN.getRegisteredName(), modLoc("block/_template/triple_layer"))
                                .texture("top", "block/resource_gen/water")
                                .texture("middle", "block/resource_gen/overlay/simple")
                                .texture("below", "block/decorative/wrought_iron_block")
                                .renderType(DataGenUtil.CUTOUT);

                withExistingParent(zBlocks.ADVANCED_WATER_GEN.getRegisteredName(),
                                modLoc("block/_template/triple_layer"))
                                .texture("top", "block/resource_gen/water")
                                .texture("middle", "block/resource_gen/overlay/advanced")
                                .texture("below", "block/machine/frame/basic/bottom")
                                .renderType(DataGenUtil.CUTOUT);

                withExistingParent(zBlocks.ELITE_WATER_GEN.getRegisteredName(), modLoc("block/_template/triple_layer"))
                                .texture("top", "block/resource_gen/water")
                                .texture("middle", "block/resource_gen/overlay/elite")
                                .texture("below", "block/machine/frame/advanced/top")
                                .renderType(DataGenUtil.CUTOUT);

                withExistingParent(zBlocks.SIMPLE_COBBLE_GEN.getRegisteredName(),
                                modLoc("block/_template/triple_layer"))
                                .texture("top", "block/resource_gen/cobblestone")
                                .texture("middle", "block/resource_gen/overlay/simple")
                                .texture("below", "block/decorative/wrought_iron_block")
                                .renderType(DataGenUtil.CUTOUT);

                withExistingParent(zBlocks.ADVANCED_COBBLE_GEN.getRegisteredName(),
                                modLoc("block/_template/triple_layer"))
                                .texture("top", "block/resource_gen/cobblestone")
                                .texture("middle", "block/resource_gen/overlay/advanced")
                                .texture("below", "block/machine/frame/basic/bottom")
                                .renderType(DataGenUtil.CUTOUT);

                withExistingParent(zBlocks.ELITE_COBBLE_GEN.getRegisteredName(), modLoc("block/_template/triple_layer"))
                                .texture("top", "block/resource_gen/cobblestone")
                                .texture("middle", "block/resource_gen/overlay/elite")
                                .texture("below", "block/machine/frame/advanced/top")
                                .renderType(DataGenUtil.CUTOUT);

                withExistingParent(zBlocks.CRUSHING_TUB.getRegisteredName(), modLoc("block/crushing_tub"));
                withExistingParent(zBlocks.EVAPORATION_BASIN.getRegisteredName(), modLoc("block/evaporation_basin"));

                withExistingParent(zBlocks.FOUNDRY.getRegisteredName(),
                                modLoc("block/foundry"))
                                .texture("front", "synergy:block/foundry/front/off");

                withExistingParent(zBlocks.CASTING_TABLE.getRegisteredName(),
                                modLoc("block/casting_table"));
                withExistingParent(zBlocks.FAUCET.getRegisteredName(),
                                modLoc("block/faucet"));

                zStatic.ALL_DRYING_RACKS.forEach(t -> {

                        var log = "_log";
                        var toplog = "_log_top";

                        var id = t.getRegisteredName();
                        var text = id.replace(ID + ":", "").replace("_" + zStatic.Blocks.drying_rack, "");

                        if (text.contains("bamboo")) {
                                log = log.replace("log", "block");
                                toplog = toplog.replace("log", "block");
                        }

                        if (text.contains("crimson") || text.contains("warped")) {
                                log = log.replace("log", "stem");
                                toplog = toplog.replace("log", "stem");
                        }

                        withExistingParent(id,
                                        modLoc("block/drying_rack"))
                                        .texture("side", mcLoc("block/" + text + log))
                                        .texture("top", mcLoc("block/" + text + toplog));

                });

                withExistingParent(zBlocks.ROUTER.getRegisteredName(), x.rl("block/router"));

                cubeBottomTop(zBlocks.CHOPPER.getRegisteredName(),
                                modLoc("block/machine/farming/chopper/side"),
                                modLoc("block/decorative/wrought_iron_block"),
                                modLoc("block/machine/farming/chopper/top"));

                leaves(zBlocks.IRON_WOOD.getLeaves().getRegisteredName(), modLoc("block/decorative/ironwood_leaves"));

                withExistingParent(zBlocks.IRON_WOOD.getSapling().getRegisteredName(), DataGenUtil.ITEM)
                                .texture("layer0", x.rl("block/decorative/ironwood_sapling"));

                withExistingParent(zBlocks.AQUAMARINE_CLUSTER.getRegisteredName(), DataGenUtil.ITEM)
                                .texture("layer0", x.rl("block/decorative/aquamarine"));

                withExistingParent(zBlocks.ENTITY_WATCHER.getRegisteredName(),
                                modLoc("block/_render/entity_watcher"));

                withExistingParent(zBlocks.SIMPLE_TANK.getRegisteredName(),
                                x.rl("block/_template/tank"))
                                .texture("side", "synergy:block/tank/simple")
                                .texture("top", "synergy:block/tank/simple")
                                .renderType(DataGenUtil.CUTOUT);

                withExistingParent(zBlocks.FUEL_TANK.getRegisteredName(),
                                x.rl("block/_template/tank"))
                                .texture("side", "synergy:block/fuel_tank/side")
                                .texture("top", "synergy:block/fuel_tank/top")
                                .renderType(DataGenUtil.CUTOUT);

                withExistingParent(zBlocks.MIXING_CHAMBER.getRegisteredName(),
                                x.rl("block/_template/tank"))
                                .texture("side", "synergy:block/mixing_chamber")
                                .texture("top", "synergy:block/fuel_tank/top")
                                .renderType(DataGenUtil.CUTOUT);

                withExistingParent(zBlocks.BRICKED_HEATER.getRegisteredName(), modLoc("block/heater/bricked/close"));
                withExistingParent(zBlocks.METALLIC_HEATER.getRegisteredName(), modLoc("block/heater/metallic/close"));

        }

}
