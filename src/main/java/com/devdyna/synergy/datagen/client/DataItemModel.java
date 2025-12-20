package com.devdyna.synergy.datagen.client;

import static com.devdyna.synergy.Main.ID;

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

@SuppressWarnings("null")
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

                moderatorBlock(zBlocks.SIMPLE_MODERATOR, mcLoc("block/coal_block"));
                moderatorBlock(zBlocks.ADVANCED_MODERATOR, modLoc("block/machine/nuclear/moderator/advanced_frame"));
                moderatorBlock(zBlocks.ELITE_MODERATOR, modLoc("block/machine/nuclear/moderator/elite_frame"));

                tinyChestAll(zBlocks.WOODEN_TINY_CHEST, "block/tiny_block/chest/wooden");
                tinyChestAll(zBlocks.STONE_TINY_CHEST, "block/tiny_block/chest/stone");
                tinyChestAll(zBlocks.ORNATE_TINY_CHEST, "block/tiny_block/chest/ornate");

                withExistingParent(zBlocks.URN.getRegisteredName(),
                                modLoc("block/tiny_block/urn"));

                withExistingParent(zBlocks.QUERN.getRegisteredName(),
                                modLoc("block/quern/item"));

                withExistingParent(zBlocks.CLAY_BRICK.getRegisteredName(),
                                mcLoc("item/clay_ball"));

                withExistingParent(zBlocks.PACKED_MUD_BRICK.getRegisteredName(),
                                mcLoc("minecraft:item/generated"))
                                .texture("layer0",
                                                modLoc("item/resources/packed_mud_ball"));

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

        }

}
