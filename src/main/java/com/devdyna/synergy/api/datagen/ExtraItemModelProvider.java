package com.devdyna.synergy.api.datagen;
import static com.devdyna.synergy.Main.ID;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredHolder;

public abstract class ExtraItemModelProvider extends ItemModelProvider {

    public ExtraItemModelProvider(PackOutput output, String modid, ExistingFileHelper existingFileHelper) {
        super(output, modid, existingFileHelper);
    }
    protected void CoolerBlock(DeferredHolder<Block, Block> b, ResourceLocation below) {
                withExistingParent(b.getRegisteredName(), modLoc("block/double_layer"))
                                .texture("top", "block/reactor/cooler/casing")
                                .texture("below", below);
        }

        protected void moderatorBlock(DeferredHolder<Block, Block> b, ResourceLocation below) {
                withExistingParent(b.getRegisteredName(), modLoc("block/double_layer"))
                                .texture("top", "block/reactor/moderator/base_off")
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

        
        protected void simpleFlexibleBlock(DeferredHolder<Block, Block> b, String loc) {
                cubeAll(b.getRegisteredName(),
                                modLoc("block/" + loc));
        }

        protected void simpleFullBlock(DeferredHolder<Block, Block> b, String prefix) {
                cubeAll(b.getRegisteredName(),
                                modLoc("block/" + b.getRegisteredName().replace(ID + ":", prefix)));
        }

        protected void node(DeferredHolder<Block, ?> b, String color) {
                withExistingParent(b.getRegisteredName(), modLoc("block/node/_template/item"))
                                .texture("pipe", ID + ":block/pipe/black")
                                .texture("node", ID + ":block/node/" + color)
                                .texture("back", ID + ":block/node/back");
        }
}
