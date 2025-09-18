package com.devdyna.synergy.datagen.client;

import static com.devdyna.synergy.Main.ID;

import java.util.List;

import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.init.types.zBlocks;
import com.devdyna.synergy.init.types.zItems;
import com.devdyna.synergy.utils.DataGenUtil;
import com.devdyna.synergy.utils.x;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.loaders.DynamicFluidContainerModelBuilder;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.internal.versions.neoforge.NeoForgeVersion;
import net.neoforged.neoforge.registries.DeferredHolder;

public class DataItemModel extends ItemModelProvider {

        public DataItemModel(PackOutput o, ExistingFileHelper f) {
                super(o, ID, f);
        }

        List<Item> wild_plants = List.of(zBlocks.WILD_CAVE_WHEAT.get().asItem(),
                        zBlocks.WILD_COTTON.get().asItem(),
                        zBlocks.WILD_RICE.get().asItem());

        @Override
        protected void registerModels() {
                // -----------------------//

                zItems.zBucketItems.getEntries().forEach(b -> withExistingParent(
                                x.path(b.get()),
                                x.rl(NeoForgeVersion.MOD_ID, "item/bucket"))
                                .customLoader(DynamicFluidContainerModelBuilder::begin)
                                .fluid(((BucketItem) b.get()).content));

                //
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

                // -----------------------//
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

                // -----------------------//

                DataGenUtil.itemBlockwithParent(zBlocks.SPRINKLER.get(), this,
                                ID + ":block/" + x.path(zBlocks.SPRINKLER.get()));

                withExistingParent(zBlocks.PIPE.getRegisteredName(), modLoc("block/pipe/basic/item_model"));

                withExistingParent(zBlocks.SOLAR_PANEL.getRegisteredName(), modLoc("block/solar_panel/item_model"));

                node(zBlocks.ITEM_PROVIDER, "green");
                node(zBlocks.ITEM_RETRIEVAL, "aqua");
                node(zBlocks.ITEM_TRANSFER, "red");

                node(zBlocks.ENERGY_PROVIDER, "green");
                node(zBlocks.ENERGY_RETRIEVAL, "aqua");
                node(zBlocks.ENERGY_TRANSFER, "red");

                wild_plants.forEach(w -> withExistingParent(x.path(w), "minecraft:item/generated")
                                .texture("layer0", x.rl(
                                                "item/plants/bush/" + x.path(w).replace("wild_", ""))));

                orientableWithBottom(zBlocks.HARVESTER.getRegisteredName(),
                                modLoc("block/harvester/side"), modLoc("block/harvester/front"),
                                modLoc("block/harvester/bottom"), modLoc("block/harvester/top"));

                orientableWithBottom(zBlocks.REACTOR_CONTROLLER.getRegisteredName(),
                                modLoc("block/reactor/controller/side"), modLoc("block/reactor/controller/front_off"),
                                modLoc("block/reactor/controller/bottom"), modLoc("block/reactor/controller/top"));

                cubeBottomTop(zBlocks.ADVANCED_MACHINE_FRAME.getRegisteredName(),
                                modLoc("block/reactor/controller/side"), modLoc("block/reactor/controller/bottom"),
                                modLoc("block/reactor/controller/top"));

                cubeBottomTop(zBlocks.MACHINE_FRAME.getRegisteredName(),
                                modLoc("block/harvester/side"), modLoc("block/harvester/bottom"),
                                modLoc("block/harvester/top"));

                simpleFullBlock(zBlocks.HEALER, "");
                simpleFullBlock(zBlocks.REACTOR_FUEL_CELL, "reactor/");
                // simpleFullBlock(zBlocks.REACTOR_PORT, "reactor/");

                // simpleFlexibleBlock(zBlocks.COOLER_BASE, "reactor/cooler/base");

                CoolerBlock(zBlocks.COPPER_COOLER, mcLoc("block/copper_block"));
                CoolerBlock(zBlocks.GOLD_COOLER, mcLoc("block/gold_block"));
                CoolerBlock(zBlocks.IRON_COOLER, mcLoc("block/iron_block"));
                CoolerBlock(zBlocks.ENDER_COOLER, mcLoc("block/purpur_block"));
                CoolerBlock(zBlocks.FROST_COOLER, mcLoc("block/blue_ice"));
                CoolerBlock(zBlocks.LAPIS_COOLER, mcLoc("block/lapis_block"));
                CoolerBlock(zBlocks.SCULK_COOLER, mcLoc("block/sculk"));
                CoolerBlock(zBlocks.WATER_COOLER, mcLoc("block/ice"));
                CoolerBlock(zBlocks.QUARTZ_COOLER, mcLoc("block/quartz_block_top"));
                CoolerBlock(zBlocks.BIOMASS_COOLER, mcLoc("block/soul_sand"));
                CoolerBlock(zBlocks.DIAMOND_COOLER, mcLoc("block/diamond_block"));
                CoolerBlock(zBlocks.EMERALD_COOLER, mcLoc("block/emerald_block"));
                CoolerBlock(zBlocks.REDSTONE_COOLER, mcLoc("block/redstone_block"));
                CoolerBlock(zBlocks.GLOWSTONE_COOLER, mcLoc("block/glowstone"));
                CoolerBlock(zBlocks.NETHERITE_COOLER, mcLoc("block/netherite_block"));

                simpleFlexibleBlock(zBlocks.SIMPLE_MODERATOR, "reactor/moderator/simple/off");
                simpleFlexibleBlock(zBlocks.IMPROVED_MODERATOR, "reactor/moderator/improved/off");
                simpleFlexibleBlock(zBlocks.ADVANCED_MODERATOR, "reactor/moderator/advanced/off");
                simpleFlexibleBlock(zBlocks.ELITE_MODERATOR, "reactor/moderator/elite/off");

                tinyChestAll(zBlocks.WOODEN_TINY_CHEST, "block/tiny_block/chest/wooden");
                tinyChestAll(zBlocks.STONE_TINY_CHEST, "block/tiny_block/chest/stone");
                tinyChestAll(zBlocks.ORNATE_TINY_CHEST, "block/tiny_block/chest/ornate");
                withExistingParent(zBlocks.URN.getRegisteredName(),
                                modLoc("block/tiny_block/urn"));
        }

        private void CoolerBlock(DeferredHolder<Block, Block> b, ResourceLocation below) {
                withExistingParent(b.getRegisteredName(), modLoc("block/double_layer"))
                                .texture("top", "block/reactor/cooler/casing")
                                .texture("below", below);
        }

        private void tinyChestAll(DeferredHolder<Block, Block> b, String texture) {
                tinyChest(b, texture, texture, texture, texture, texture, texture, texture);
        }

        private void tinyChest(DeferredHolder<Block, Block> b, String particles, String north, String south,
                        String east, String west, String up, String down) {
                withExistingParent(b.getRegisteredName(),
                                modLoc("block/tiny_block/chest"))
                                .texture("particle", particles)
                                .texture("north", north)
                                .texture("south", south)
                                .texture("east", east)
                                .texture("west", west)
                                .texture("up", up)
                                .texture("down", down);
        }

        private void simpleFlexibleBlock(DeferredHolder<Block, Block> b, String loc) {
                cubeAll(b.getRegisteredName(),
                                modLoc("block/" + loc));
        }

        private void simpleFullBlock(DeferredHolder<Block, Block> b, String prefix) {
                cubeAll(b.getRegisteredName(),
                                modLoc("block/" + b.getRegisteredName().replace(ID + ":", prefix)));
        }

        private void node(DeferredHolder<Block, ?> b, String color) {
                withExistingParent(b.getRegisteredName(), modLoc("block/node/_template/item"))
                                .texture("pipe", ID + ":block/pipe/black")
                                .texture("node", ID + ":block/node/" + color)
                                .texture("back", ID + ":block/node/back");
        }

}
