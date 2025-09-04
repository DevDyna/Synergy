package com.devdyna.synergy.init.types;

import com.devdyna.synergy.zStatic;
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

        public static final TagKey<Block> CAN_SUSTAIN_RICE = Material.tagBlock("can_sustain/" + zStatic.Plants.RICE);
        public static final TagKey<Block> CAN_SUSTAIN_COTTON = Material
                        .tagBlock("can_sustain/" + zStatic.Plants.COTTON);
        public static final TagKey<Block> CAN_SUSTAIN_CAVE_WHEAT = Material
                        .tagBlock("can_sustain/" + zStatic.Plants.CAVE_WHEAT);
        public static final TagKey<Block> CAN_SUSTAIN_BLUE_CUP = Material
                        .tagBlock("can_sustain/" + zStatic.Plants.BLUE_CUP_MUSHROOM);
        public static final TagKey<Block> CAN_SUSTAIN_VIOLET_WEBCAP = Material
                        .tagBlock("can_sustain/" + zStatic.Plants.VIOLET_WEBCAP_MUSHROOM);

        public static final TagKey<Block> INFESTED_BLOCKS = Material
                        .tagBlock("infested_blocks", "c");

        public static final TagKey<Block> HARVESTER_TREE_BREAK = Material
                        .tagBlock("harvester_can_break");

        public static final TagKey<Block> FERMERS_DELIGHT_COMPOSTING = Material
                        .tagBlock("compost_activators", "farmersdelight");

}