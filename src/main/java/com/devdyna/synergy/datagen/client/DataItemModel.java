package com.devdyna.synergy.datagen.client;

import static com.devdyna.synergy.Main.ID;

import java.util.List;

import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.api.datagen.ExtraItemModelProvider;
import com.devdyna.synergy.init.types.zBlocks;
import com.devdyna.synergy.init.types.zItems;
import com.devdyna.synergy.utils.ClazzUtil;
import com.devdyna.synergy.utils.DataGenUtil;
import com.devdyna.synergy.utils.x;

import net.minecraft.data.PackOutput;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.model.generators.loaders.DynamicFluidContainerModelBuilder;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.internal.versions.neoforge.NeoForgeVersion;

@SuppressWarnings("null")
public class DataItemModel extends ExtraItemModelProvider {

        public DataItemModel(PackOutput o, ExistingFileHelper f) {
                super(o, ID, f);
        }

        List<Item> wild_plants = List.of(zBlocks.WILD_CAVE_WHEAT.get().asItem(),
                        zBlocks.WILD_COTTON.get().asItem(),
                        zBlocks.WILD_RICE.get().asItem());

        @Override
        protected void registerModels() {

                withExistingParent(x.path(zBlocks.AZALEA.get().asItem()), x.rl("block/azalea/item"));

                zItems.zBucketItems.getEntries().forEach(b -> withExistingParent(
                                x.path(b.get()),
                                x.rl(NeoForgeVersion.MOD_ID, "item/bucket"))
                                .customLoader(DynamicFluidContainerModelBuilder::begin)
                                .fluid(((BucketItem) b.get()).content));

                zItems.zCraftingComponents.getEntries()
                                .forEach(item -> DataGenUtil.itemModel(item.get(), this, "components/"));
                zItems.zCropExtra.getEntries()
                                .forEach(item -> DataGenUtil.itemModel(item.get(), this, "plants/results/"));
                zItems.zFoods.getEntries().forEach(item -> DataGenUtil.itemModel(item.get(), this, "foods/"));

                zItems.zSeeds.getEntries().stream().filter(f -> !wild_plants.contains(f.get()))
                                .forEach(item -> DataGenUtil.itemModel(item.get(), this, "plants/seeds/"));

                zItems.zTool.getEntries().forEach(item -> DataGenUtil.itemModel(item.get(), this, "tools/"));

                zItems.zResources.getEntries().forEach(item -> DataGenUtil.itemModel(item.get(), this, "resources/"));
                zItems.zDusts.getEntries().forEach(item -> DataGenUtil.itemModel(item.get(), this, "resources/dust/",
                                x.path(item.get()).replace(zStatic.ResourceType.dust, "")));
                zItems.zFoils.getEntries().forEach(item -> DataGenUtil.itemModel(item.get(), this, "resources/foil/",
                                x.path(item.get()).replace(zStatic.ResourceType.foil, "")));
                zItems.zGems.getEntries().forEach(item -> DataGenUtil.itemModel(item.get(), this, "resources/gem/",
                                x.path(item.get()).replace(zStatic.ResourceType.gem, "")));
                zItems.zIngots.getEntries().forEach(item -> DataGenUtil.itemModel(item.get(), this, "resources/ingot/",
                                x.path(item.get()).replace(zStatic.ResourceType.ingot, "")));
                zItems.zNuggets.getEntries().forEach(item -> DataGenUtil.itemModel(item.get(), this,
                                "resources/nugget/",
                                x.path(item.get()).replace(zStatic.ResourceType.nugget, "")));
                zItems.zPlates.getEntries().forEach(item -> DataGenUtil.itemModel(item.get(), this, "resources/plate/",
                                x.path(item.get()).replace(zStatic.ResourceType.plate, "")));
                zItems.zRawOres.getEntries().forEach(item -> DataGenUtil.itemModel(item.get(), this, "resources/raw/",
                                x.path(item.get()).replace(zStatic.ResourceType.raw, "")));
                zItems.zShards.getEntries().forEach(item -> DataGenUtil.itemModel(item.get(), this, "resources/shard/",
                                x.path(item.get()).replace(zStatic.ResourceType.shard, "")));
                zItems.zMobDrop.getEntries()
                                .forEach(item -> DataGenUtil.itemModel(item.get(), this, "resources/mob_drop/",
                                                x.path(item.get())));
                zItems.zPellets.getEntries()
                                .forEach(item -> DataGenUtil.itemModel(item.get(), this, "resources/pellet/",
                                                x.path(item.get()).replace(zStatic.ResourceType.pellet, "")));

                zItems.zDropLets.getEntries()
                                .forEach(item -> DataGenUtil.itemModel(item.get(), this, "resources/droplet/",
                                                x.path(item.get())));

                zBlocks.zDecorative.getEntries()
                                .forEach(bk -> cubeAll(bk.getRegisteredName().replace(ID + ":block/", ""),
                                                modLoc("block/decorative/"
                                                                + x.path(bk.get()).replace(ID + ":block/",
                                                                                ""))));

                zBlocks.zBlockSlab.getEntries().forEach(bk -> slab(
                                bk.getRegisteredName().replace(ID + ":block/", ""), modLoc("block/decorative/"
                                                + x.path(bk.get()).replace(ID + ":block/",
                                                                "").replace("_slab",
                                                                                "")),
                                modLoc("block/decorative/"
                                                + x.path(bk.get()).replace(ID + ":block/",
                                                                "").replace("_slab",
                                                                                "")),
                                modLoc("block/decorative/"
                                                + x.path(bk.get()).replace(ID + ":block/",
                                                                "").replace("_slab",
                                                                                ""))));

                zBlocks.zBlockStair.getEntries().forEach(bk -> stairs(
                                bk.getRegisteredName().replace(ID + ":block/", ""), modLoc("block/decorative/"
                                                + x.path(bk.get()).replace(ID + ":block/",
                                                                "").replace("_stair",
                                                                                "")),
                                modLoc("block/decorative/"
                                                + x.path(bk.get()).replace(ID + ":block/",
                                                                "").replace("_stair",
                                                                                "")),
                                modLoc("block/decorative/"
                                                + x.path(bk.get()).replace(ID + ":block/",
                                                                "").replace("_stair",
                                                                                ""))));

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

                wild_plants.forEach(w -> withExistingParent(x.path(w), "minecraft:item/generated")
                                .texture("layer0", x.rl(
                                                "item/plants/bush/" + x.path(w).replace("wild_", ""))));

                orientableWithBottom(zBlocks.HARVESTER.getRegisteredName(),
                                modLoc("block/harvester/side"),
                                modLoc("block/harvester/front"),
                                modLoc("block/harvester/bottom"),
                                modLoc("block/harvester/top"));

                orientableWithBottom(zBlocks.REACTOR_CONTROLLER.getRegisteredName(),
                                modLoc("block/reactor/controller/side"),
                                modLoc("block/reactor/controller/front_off"),
                                modLoc("block/reactor/controller/bottom"),
                                modLoc("block/reactor/controller/top"));

                cubeBottomTop(zBlocks.ADVANCED_MACHINE_FRAME.getRegisteredName(),
                                modLoc("block/reactor/controller/side"),
                                modLoc("block/reactor/controller/bottom"),
                                modLoc("block/reactor/controller/top"));

                cubeBottomTop(zBlocks.BASIC_MACHINE_FRAME.getRegisteredName(),
                                modLoc("block/harvester/side"),
                                modLoc("block/harvester/bottom"),
                                modLoc("block/harvester/top"));

                simpleFullBlock(zBlocks.HEALER, "");
                simpleFullBlock(zBlocks.REACTOR_FUEL_CELL, "reactor/");

                CoolerBlock(zBlocks.COPPER_COOLER, mcLoc("block/copper_block"));
                CoolerBlock(zBlocks.GOLD_COOLER, mcLoc("block/gold_block"));
                CoolerBlock(zBlocks.IRON_COOLER, mcLoc("block/iron_block"));
                CoolerBlock(zBlocks.ENDER_COOLER, mcLoc("block/purpur_block"));
                CoolerBlock(zBlocks.FROST_COOLER, mcLoc("block/blue_ice"));
                CoolerBlock(zBlocks.LAPIS_COOLER, mcLoc("block/lapis_block"));
                CoolerBlock(zBlocks.SCULK_COOLER, mcLoc("block/sculk"));
                CoolerBlock(zBlocks.WATER_COOLER, mcLoc("block/ice"));
                CoolerBlock(zBlocks.QUARTZ_COOLER, mcLoc("block/quartz_block_top"));
                CoolerBlock(zBlocks.SHADOW_COOLER, modLoc("block/reactor/cooler/shadow"));
                CoolerBlock(zBlocks.DIAMOND_COOLER, mcLoc("block/diamond_block"));
                CoolerBlock(zBlocks.EMERALD_COOLER, mcLoc("block/emerald_block"));
                CoolerBlock(zBlocks.REDSTONE_COOLER, mcLoc("block/redstone_block"));
                CoolerBlock(zBlocks.GLOWSTONE_COOLER, mcLoc("block/glowstone"));
                CoolerBlock(zBlocks.NETHERITE_COOLER, mcLoc("block/netherite_block"));

                moderatorBlock(zBlocks.SIMPLE_MODERATOR, mcLoc("block/coal_block"));
                moderatorBlock(zBlocks.ADVANCED_MODERATOR, modLoc("block/reactor/moderator/advanced_frame"));
                moderatorBlock(zBlocks.ELITE_MODERATOR, modLoc("block/reactor/moderator/elite_frame"));

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

                withExistingParent(zBlocks.LASER_MACHINE.getRegisteredName(), modLoc("block/laser_machine"));
                withExistingParent(zBlocks.LASER_LENS.getRegisteredName(), modLoc("block/laser_lens"));
                withExistingParent(zBlocks.LASER_MIRROR.getRegisteredName(), modLoc("block/laser_mirror"));

                ClazzUtil.getAllMachineTypes().forEach(m -> {
                        orientableWithBottom(m.id(),
                                        modLoc("block/harvester/side"), // TODO rework
                                        modLoc("block/machines/" + m.id()),
                                        modLoc("block/harvester/bottom"),
                                        modLoc("block/harvester/top"));
                });

        }

}
