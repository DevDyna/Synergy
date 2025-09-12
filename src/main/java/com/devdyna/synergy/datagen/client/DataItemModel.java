package com.devdyna.synergy.datagen.client;

import static com.devdyna.synergy.Main.ID;

import java.util.List;

import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.init.types.zBlocks;
import com.devdyna.synergy.init.types.zItems;
import com.devdyna.synergy.utils.DataGenUtil;
import com.devdyna.synergy.utils.x;

import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
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

                node(zBlocks.ITEM_TRANSFER, "red");
                node(zBlocks.ITEM_PROVIDER, "green");
                node(zBlocks.ITEM_RETRIEVAL, "aqua");

                wild_plants.forEach(w -> withExistingParent(x.path(w), "minecraft:item/generated")
                                .texture("layer0", x.rl(
                                                "item/plants/bush/" + x.path(w).replace("wild_", ""))));

                orientableWithBottom(zBlocks.HARVESTER.getRegisteredName(),
                                modLoc("block/harvester/side"), modLoc("block/harvester/front"),
                                modLoc("block/harvester/bottom"), modLoc("block/harvester/top"));

                // cubeBottomTop(zBlocks.FAN.get().getDescriptionId().replace("block." + ID +
                // ".", ""),
                // modLoc("block/fan/side"), modLoc("block/fan/off"),
                // modLoc("block/fan/back"));

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
                simpleFlexibleBlock(zBlocks.IRON_COOLER, "reactor/cooler/on");
                simpleFlexibleBlock(zBlocks.GRAPHITE_MODERATOR, "reactor/moderator/casing");

                tinyChestAll(zBlocks.WOODEN_TINY_CHEST, "block/tiny_block/chest/wooden");
                tinyChestAll(zBlocks.STONE_TINY_CHEST, "block/tiny_block/chest/stone");
                tinyChestAll(zBlocks.ORNATE_TINY_CHEST, "block/tiny_block/chest/ornate");
                withExistingParent(zBlocks.URN.getRegisteredName(),
                                modLoc("block/tiny_block/urn"));
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
