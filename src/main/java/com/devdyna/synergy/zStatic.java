package com.devdyna.synergy;

import com.devdyna.synergy.utils.ModAddonUtil;

public class zStatic {
    public static String CreativeTab = "creative_tab";

    public class Items {

        public class Azalea {
            public static String seeds = Blocks.azalea + "_seeds";
            public static String leaf = "small_" + Blocks.azalea + "_leaf";
            public static String roots = "small_" + Blocks.azalea + "_roots";
        }

        public static String wooden_crook = "wooden_crook";
        public static String area_configurator = "area_configurator";
        public static String smasher = "smasher";
    }

    public class DecorativeBlocks {
        public static String industrial_metal = "industrial_metal";
        public static String waxed_planks = "waxed_planks";
        public static String adobe = "adobe";
    }

    public class Blocks {
        public static String azalea = "azalea";
        public static String sprinkler = "sprinkler";
        public static String harvester = "harvester";

        // TODO WIP
        public static String generator = "generator";
        public static String deposit = "deposit";
        public static String extractor = "extractor";
    }

    public class PipeStuff {
        public class types {
            private static String item = "item_";
            private static String fluid = "fluid_";
            private static String energy = "energy_";
            private static String chemical = "chemical_";
        }

        public class nodes {

            private static String node = "_node";

            private static String transfer = "transfer";
            private static String provider = "provider";
            private static String retrieval = "retrieval";

            // only used for tooltips atm
            public static String type_transfer = transfer + node;
            public static String type_provider = provider + node;
            public static String type_retrieval = retrieval + node;

            public static String ItemTransfer = types.item + transfer + node;
            public static String ItemProvider = types.item + provider + node;
            public static String ItemRetrieval = types.item + retrieval + node;

            // TODO fluid nodes
            public static String FluidTransfer = types.fluid + transfer + node;
            public static String FluidProvider = types.fluid + provider + node;
            public static String FluidRetrieval = types.fluid + retrieval + node;

            // TODO FE nodes
            public static String EnergyTransfer = types.energy + transfer + node;
            public static String EnergyProvider = types.energy + provider + node;
            public static String EnergyRetrieval = types.energy + retrieval + node;

            // TODO chem nodes (meka compat)
            public static String GasTransfer = types.chemical + transfer + node;
            public static String GasProvider = types.chemical + provider + node;
            public static String GasRetrieval = types.chemical + retrieval + node;
        }

        public class tools {
            public static String refactorizer = pipe + "_refactorizer";
            public static String visualizer = pipe + "_visualizer";
        }

        public static String pipe = "pipe";

    }

    public class Plants {

        private static String MUSH = "_mushroom";

        public static final String RICE = "rice";
        public static final String CAVE_WHEAT = "cave_wheat";
        public static final String COTTON = "cotton";

        protected static final String BLUE_CUP = "blue_cup";
        protected static final String VIOLET_WEBCAP = "violet_webcap";

        public static final String BLUE_CUP_MUSHROOM = BLUE_CUP + MUSH;
        public static final String VIOLET_WEBCAP_MUSHROOM = VIOLET_WEBCAP + MUSH;

    }

    public class Wild {

        public static String WILD = "wild_";

        public static final String RICE = WILD + Plants.RICE;
        public static final String CAVE_WHEAT = WILD + Plants.CAVE_WHEAT;
        public static final String COTTON = WILD + Plants.COTTON;

    }

    public class Seeds {

        private static String SEED = "_seed";
        private static String SPORE = "_pod";

        public static final String RICE_SEED = Plants.RICE + SEED;
        public static final String CAVE_WHEAT_SEED = Plants.CAVE_WHEAT + SEED;
        public static final String COTTON_SEED = Plants.COTTON + SEED;

        public static final String BLUE_CUP_SPORE = Plants.BLUE_CUP + SPORE;
        public static final String VIOLET_WEBCAP_SPORE = Plants.VIOLET_WEBCAP + SPORE;

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

    public class Mods {
        public static String GuideMe = "guideme";
    }

    public class checkMods {
        public static boolean GuideMe = ModAddonUtil.checkMod(Mods.GuideMe);
    }

}
