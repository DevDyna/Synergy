package com.devdyna.synergy.init.types;

import com.devdyna.synergy.init.Material;

import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;

public class zBlockTag {
        public static void register(IEventBus bus) {
        }

        public static final TagKey<Block> LEAVES = Material.tagBlock("crook_leaves");
        public static final TagKey<Block> CROPS = Material.tagBlock("sprinkler_crops");

        public static final TagKey<Block> CAN_CONNECT = Material.tagBlock("can_connect");

        public static final TagKey<Block> AOE_RENDER = Material.tagBlock("prop/aoe");
        public static final TagKey<Block> NODE_RENDER = Material.tagBlock("prop/track");

        public static final TagKey<Block> PIPE = Material.tagBlock("can_connect/pipe");

        public static final TagKey<Block> NODE = Material.tagBlock("can_connect/node");

        public static final TagKey<Block> NODE_PROVIDER = Material.tagBlock("can_connect/node/provider");

        public static final TagKey<Block> NODE_TRANSFER = Material.tagBlock("can_connect/node/transfer");

        public static final TagKey<Block> NODE_RETRIEVAL = Material.tagBlock("can_connect/node/retrieval");

        public static final TagKey<Block> EXTRACTORS = Material.tagBlock("extractors");

        public static final TagKey<Block> MASHABLE = Material.tagBlock("mashable");

        public static final TagKey<Block> MUSHROOMS = Material.tagBlock("mushrooms", "c");

        public static final TagKey<Block> HARVESTER_TREE_BREAK = Material
                        .tagBlock("harvester_can_break");

        public static final TagKey<Block> FERMERS_DELIGHT_COMPOSTING = Material
                        .tagBlock("compost_activators", "farmersdelight");


        public static final TagKey<Block> BLOCK_STEEL = Material
                        .tagBlock("storage_blocks/steel", "c");

        public static final TagKey<Block> BLOCK_ADVANCED_ALLOY = Material
                        .tagBlock("storage_blocks/advanced_alloy", "c");

}