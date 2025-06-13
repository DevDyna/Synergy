package com.devdyna.synergy;

public class zStatic {
    public static String CreativeTab = "creative_tab";

    public class Items {

        public class Azalea {
            public static String seeds = Blocks.azalea + "_seeds";
            public static String leaf = "small_" + Blocks.azalea + "_leaf";
            public static String roots = "small_" + Blocks.azalea + "_roots";
        }

        public static String wooden_crook = "wooden_crook";
    }

    public class Blocks {
        public static String azalea = "azalea";
        public static String sprinkler = "sprinkler";
        public static String pipe = "pipe";
        public static String node = "transfer_node";

        public static String generator = "generator";
        public static String deposit = "deposit";
        public static String extractor = "extractor";
    }

    public class Plants {

        private static String SEED = "_seed";
        private static String SPORE = "_spore";
        private static String MUSH = "_mushroom";

        public static final String RICE = "rice";
        public static final String CAVE_WHEAT = "cave_wheat";
        public static final String COTTON = "cotton";

        public static final String ELF_CUP = "elf_cup";
        public static final String CORTINARIUS = "cortinarius";

        public static final String ELF_CUP_MUSHROOM = ELF_CUP + MUSH;
        public static final String CORTINARIUS_MUSHROOM = CORTINARIUS + MUSH;

        public static final String RICE_SEED = RICE + SEED;
        public static final String CAVE_WHEAT_SEED = CAVE_WHEAT + SEED;
        public static final String COTTON_SEED = COTTON + SEED;

        public static final String ELF_CUP_MUSHROOM_SPORE = ELF_CUP + SPORE;
        public static final String CORTINARIUS_MUSHROOM_SPORE = CORTINARIUS + SPORE;

    }

    // TODO WIP
    public class Resources {

        public static class Soils {
            public static final String ANDESITE = "andesite";
            public static final String BASALT = "basalt";
            public static final String BLACKSTONE = "blackstone";
            public static final String BLUE_ICE = "blue_ice";
            public static final String CALCITE = "calcite";
            public static final String CLAY = "clay";
            public static final String COBBLESTONE = "cobblestone";
            public static final String COBBLED_DEEPSLATE = "cobbled_deepslate";
            public static final String COARSE_DIRT = "coarse_dirt";
            public static final String DEEPSLATE = "deepslate";
            public static final String DIORITE = "diorite";
            public static final String DRIPSTONE_BLOCK = "dripstone_block";
            public static final String DIRT = "dirt";
            public static final String END_STONE = "end_stone";
            public static final String GRAVEL = "gravel";
            public static final String GRANITE = "granite";
            public static final String GILDED_BLACKSTONE = "gilded_blackstone";
            public static final String ICE = "ice";
            public static final String MAGMA_BLOCK = "magma_block";
            public static final String MOSS_BLOCK = "moss_block";
            public static final String MOSSY_COBBLESTONE = "mossy_cobblestone";
            public static final String MUD = "mud";
            public static final String NETHERRACK = "netherrack";
            public static final String OBSIDIAN = "obsidian";
            public static final String PACKED_ICE = "packed_ice";
            public static final String PACKED_MUD = "packed_mud";
            public static final String PRISMARINE = "prismarine";
            public static final String RED_SAND = "red_sand";
            public static final String ROOTED_DIRT = "rooted_dirt";
            public static final String SCULK = "sculk";
            public static final String SOUL_SAND = "soul_sand";
            public static final String SOUL_SOIL = "soul_soil";
            public static final String SAND = "sand";
            public static final String SANDSTONE = "sandstone";
            public static final String SNOW_BLOCK = "snow_block";
            public static final String STONE = "stone";
            public static final String TUFF = "tuff";
            public static final String CRYING_OBSIDIAN = "crying_obsidian";
        }

        public static class RawMaterials {
            public static final String COPPER = "copper";
            public static final String IRON = "iron";
            public static final String GOLD = "gold";
            public static final String ALUMINUM = "aluminum";
            public static final String TIN = "tin";
            public static final String NICKEL = "nickel";
            public static final String ZINC = "zinc";
            public static final String SILVER = "silver";
            public static final String LEAD = "lead";
            public static final String OSMIUM = "osmium";
            public static final String URANIUM = "uranium";
            public static final String IRIDIUM = "iridium";
            public static final String PLATINUM = "platinum";
        }

        public static class Gems {
            public static final String COAL = "coal";
            public static final String QUARTZ = "quartz";
            public static final String AMETHYST_SHARD = "amethyst_shard";
            public static final String LAPIS_LAZULI = "lapis_lazuli";
            public static final String DIAMOND = "diamond";
            public static final String EMERALD = "emerald";
            public static final String NETHERITE_SCRAP = "netherite_scrap";
            public static final String RUBY = "ruby";
            public static final String SAPPHIRE = "sapphire";
            public static final String PERIDOT = "peridot";
            public static final String FLUORITE = "fluorite";
            public static final String CINNABAR = "cinnabar";
        }

        public static class Dusts {
            public static final String GLOWSTONE = "glowstone";
            public static final String REDSTONE = "redstone";
            public static final String SALT = "salt";
            public static final String SULFUR = "sulfur";
        }

    }

}
