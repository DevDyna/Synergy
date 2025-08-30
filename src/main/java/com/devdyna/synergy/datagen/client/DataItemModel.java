package com.devdyna.synergy.datagen.client;

import static com.devdyna.synergy.Main.ID;

import java.util.List;

import com.devdyna.synergy.init.types.zBlocks;
import com.devdyna.synergy.init.types.zItems;
import com.devdyna.synergy.utils.DataGenUtil;

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

        @Override
        protected void registerModels() {

                List<Item> wild_plants = List.of(zBlocks.WILD_CAVE_WHEAT.get().asItem(),
                                zBlocks.WILD_COTTON.get().asItem(),
                                zBlocks.WILD_RICE.get().asItem());

                // zItems.zItem.getEntries().forEach(item -> DataGenUtil.itemModel(item.get(),
                // this));
                zItems.zCropExtra.getEntries()
                                .forEach(item -> DataGenUtil.itemModel(item.get(), this, "plants/results/"));
                zItems.zFoods.getEntries().forEach(item -> DataGenUtil.itemModel(item.get(), this, "foods/"));

                zItems.zSeeds.getEntries().stream().filter(f -> !wild_plants.contains(f.get()))
                                .forEach(item -> DataGenUtil.itemModel(item.get(), this, "plants/seeds/"));

                DataGenUtil.itemBlockwithParent(zBlocks.SPRINKLER.get(), this,
                                ID + ":block/" + DataGenUtil.getPath(zBlocks.SPRINKLER.get()));

                withExistingParent(zBlocks.PIPE.getRegisteredName(), modLoc("block/pipe/basic/item_model"));

                withExistingParent(zBlocks.SOLAR_PANEL.getRegisteredName(), modLoc("block/solar_panel/item_model"));

                node(zBlocks.ITEM_TRANSFER, "red");
                node(zBlocks.ITEM_PROVIDER, "green");
                node(zBlocks.ITEM_RETRIEVAL, "aqua");

                zItems.zTool.getEntries().forEach(item -> DataGenUtil.itemModel(item.get(), this, "tools/"));

                wild_plants.forEach(w -> withExistingParent(DataGenUtil.getPath(w), "minecraft:item/generated")
                                .texture("layer0", DataGenUtil.getResource(
                                                "item/plants/bush/" + DataGenUtil.getPath(w).replace("wild_", ""))));

                zBlocks.zDecorative.getEntries()
                                .forEach(bk -> cubeAll(bk.getRegisteredName().replace(ID + ":block/", ""),
                                                modLoc("block/decorative/"
                                                                + DataGenUtil.getPath(bk.get()).replace(ID + ":block/",
                                                                                ""))));

                zItems.zCraftingComponents.getEntries()
                                .forEach(item -> DataGenUtil.itemModel(item.get(), this, "components/"));

                orientableWithBottom(zBlocks.HARVESTER.getRegisteredName(),
                                modLoc("block/harvester/side"), modLoc("block/harvester/front"),
                                modLoc("block/harvester/bottom"), modLoc("block/harvester/top"));

                // cubeBottomTop(zBlocks.FAN.get().getDescriptionId().replace("block." + ID +
                // ".", ""),
                // modLoc("block/fan/side"), modLoc("block/fan/off"),
                // modLoc("block/fan/back"));

                zBlocks.zBlockSlab.getEntries().forEach(bk -> slab(
                                bk.getRegisteredName().replace(ID + ":block/", ""), modLoc("block/decorative/"
                                                + DataGenUtil.getPath(bk.get()).replace(ID + ":block/",
                                                                "").replace("_slab",
                                                                                "")),
                                modLoc("block/decorative/"
                                                + DataGenUtil.getPath(bk.get()).replace(ID + ":block/",
                                                                "").replace("_slab",
                                                                                "")),
                                modLoc("block/decorative/"
                                                + DataGenUtil.getPath(bk.get()).replace(ID + ":block/",
                                                                "").replace("_slab",
                                                                                ""))));

                zBlocks.zBlockStair.getEntries().forEach(bk -> stairs(
                                bk.getRegisteredName().replace(ID + ":block/", ""), modLoc("block/decorative/"
                                                + DataGenUtil.getPath(bk.get()).replace(ID + ":block/",
                                                                "").replace("_stair",
                                                                                "")),
                                modLoc("block/decorative/"
                                                + DataGenUtil.getPath(bk.get()).replace(ID + ":block/",
                                                                "").replace("_stair",
                                                                                "")),
                                modLoc("block/decorative/"
                                                + DataGenUtil.getPath(bk.get()).replace(ID + ":block/",
                                                                "").replace("_stair",
                                                                                ""))));

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
                simpleFlexibleBlock(zBlocks.IRON_COOLER, "reactor/cooler/on");
                simpleFlexibleBlock(zBlocks.GRAPHITE_MODERATOR, "reactor/moderator/casing");

                withExistingParent(zBlocks.WOODEN_TINY_CHEST.getRegisteredName(),
                                modLoc("block/tiny_chest/wooden"));

        }

        private void simpleFlexibleBlock(DeferredHolder<Block, Block> b, String loc) {
                cubeAll(b.getRegisteredName(),
                                modLoc("block/"+loc));
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
