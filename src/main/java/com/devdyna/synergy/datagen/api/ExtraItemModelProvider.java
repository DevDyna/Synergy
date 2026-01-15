package com.devdyna.synergy.datagen.api;

import static com.devdyna.synergy.Main.ID;

import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.api.utils.ClazzUtil;
import com.devdyna.synergy.api.utils.DataGenUtil;
import com.devdyna.synergy.api.utils.x;
import com.devdyna.synergy.init.types.*;

import java.util.*;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredHolder;

public abstract class ExtraItemModelProvider extends ItemModelProvider {

        public ExtraItemModelProvider(PackOutput output, String modid, ExistingFileHelper existingFileHelper) {
                super(output, modid, existingFileHelper);
        }

        private List<DeferredHolder<Block, Block>> notDecorativeBlocks = List.of(
                        zBlocks.COOLER_BASE

        );

        protected void decorative() {

                zBlocks.zColumn.getEntries().stream()
                                .forEach(bk -> cubeColumn(bk.getRegisteredName().replace(ID + ":block/", ""),
                                                modLoc("block/decorative/column/side/"
                                                                + x.path(bk.get())
                                                                                .replace("_column", "")
                                                                                .replace(ID + ":block/", "")),
                                                modLoc("block/decorative/column/end/"
                                                                + x.path(bk.get())
                                                                                .replace("_column", "")
                                                                                .replace(ID + ":block/", ""))));

                zBlocks.zDecorative.getEntries().stream().filter(i -> !notDecorativeBlocks.contains(i))
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
        }

        protected void machines() {
                ClazzUtil.getAllMachineTypes().forEach(m -> orientableWithBottom(
                                m.id(),
                                modLoc("block/machine/frame/basic/side"),
                                modLoc("block/machine/processing/" + m.id() + "/off"),
                                modLoc("block/machine/frame/basic/bottom"),
                                modLoc("block/machine/frame/basic/top")));
        }

        protected void resources() {
                // suffix resource types
                Map.of(
                                zItems.zDusts, zStatic.ResourceType.dust,
                                zItems.zFoils, zStatic.ResourceType.foil,
                                zItems.zCoils, zStatic.ResourceType.coil,
                                zItems.zGems, zStatic.ResourceType.gem,
                                zItems.zIngots, zStatic.ResourceType.ingot,
                                zItems.zNuggets, zStatic.ResourceType.nugget,
                                zItems.zPlates, zStatic.ResourceType.plate,
                                zItems.zShards, zStatic.ResourceType.shard,
                                zItems.zGears, zStatic.ResourceType.gear,
                                zItems.zPellets, zStatic.ResourceType.pellet)
                                .forEach((entries, data) -> entries.getEntries()
                                                .forEach(item -> DataGenUtil.itemModel(
                                                                item.get(),
                                                                this,
                                                                "resources" + data.replace("_", "/") + "/",
                                                                x.path(item.get()).replace(data, ""))));

                // prefix resource types

                Map.of(
                                zItems.zRawOres, zStatic.ResourceType.raw)
                                .forEach((entries, data) -> entries.getEntries()
                                                .forEach(item -> DataGenUtil.itemModel(
                                                                item.get(),
                                                                this,
                                                                "resources/" + data.replace("_", "/"),
                                                                x.path(item.get()).replace(data, ""))));

                // special resource types
                Map.of(
                                zItems.zResources, "resources/",
                                zItems.zMobDrop, "resources/mob_drop/",
                                zItems.zDropLets, "resources/droplet/",
                                zItems.zCraftingComponents, "components/",
                                zItems.zCropExtra, "plants/results/",
                                zItems.zFoods, "foods/",
                                zItems.zTool, "tools/").forEach(
                                                (entries, folder) -> entries.getEntries()
                                                                .forEach(item -> DataGenUtil.itemModel(
                                                                                item.get(),
                                                                                this,
                                                                                folder,
                                                                                x.path(item.get()))));

                List<Item> plants = List.of(
                                zBlocks.WILD_CAVE_WHEAT,
                                zBlocks.WILD_COTTON,
                                zBlocks.WILD_RICE)
                                .stream()
                                .map(DeferredHolder<Block, Block>::get)
                                .map(Block::asItem)
                                .toList();

                // extra resource types (plants)
                zItems.zSeeds.getEntries().stream()
                                .filter(f -> !plants

                                                .contains(f.get()))
                                .forEach(item -> DataGenUtil.itemModel(
                                                item.get(),
                                                this,
                                                "plants/seeds/",
                                                x.path(item.get())));

                zItems.zMachineUpgrades.getEntries()
                                .forEach(item -> DataGenUtil.itemModel(
                                                item.get(),
                                                this,
                                                "upgrades/",
                                                x.path(item.get()).replace("_" + zStatic.MachineUpgrades.TYPE, "")));

                plants.forEach(w -> withExistingParent(
                                x.path(w), "minecraft:item/generated")
                                .texture("layer0", x.rl("item/plants/bush/" + x.path(w).replace("wild_", ""))));

                zItems.zMolds.getEntries().stream().map(DeferredHolder::get)
                                .forEach(d -> withExistingParent(x.path(d), "minecraft:item/generated")
                                                .texture("layer0", x.rl("item/resources/blank_mold"))
                                                .texture("layer1", x.rl("item/resources/mold/"
                                                                + x.path(d).replace(zStatic.ResourceType.mold, ""))));

        }

        protected void CoolerBlock(DeferredHolder<Block, Block> b, ResourceLocation below) {
                withExistingParent(b.getRegisteredName(), modLoc("block/double_layer"))
                                .texture("top", "block/machine/nuclear/cooler/casing")
                                .texture("below", below);
        }

        protected void moderatorBlock(DeferredHolder<Block, Block> b, ResourceLocation below) {
                withExistingParent(b.getRegisteredName(), modLoc("block/double_layer"))
                                .texture("top", "block/machine/nuclear/moderator/base_off")
                                .texture("below", below);
        }

        protected void tinyChestAll(DeferredHolder<Block, Block> b, String texture) {
                tinyChest(b, texture, texture, texture, texture, texture, texture, texture);
        }

        protected void tinyChest(DeferredHolder<Block, Block> b, String particles, String north, String south,
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

        protected ItemModelBuilder simpleFlexibleBlock(DeferredHolder<Block, Block> b, String loc) {
               return cubeAll(b.getRegisteredName(),
                                modLoc("block/" + loc));
        }

        protected ItemModelBuilder  simpleFullBlock(DeferredHolder<Block, Block> b, String prefix) {
               return cubeAll(b.getRegisteredName(),
                                modLoc("block/" + b.getRegisteredName().replace(ID + ":", prefix)));
        }

        protected void node(DeferredHolder<Block, ?> b, String color) {
                withExistingParent(b.getRegisteredName(), modLoc("block/node/_template/item"))
                                .texture("pipe", ID + ":block/pipe/black")
                                .texture("node", ID + ":block/node/" + color)
                                .texture("back", ID + ":block/node/back");
        }
}
