package com.devdyna.synergy;

public class Database {
    public static String CreativeTab = "creative_tab";

    public class Items {

        public class Azalea {
            public static String seeds = Blocks.azalea+"_seeds";
            public static String leaf = "small_"+Blocks.azalea+"_leaf";
            public static String roots = "small_"+Blocks.azalea+"_roots";
        }

        public static String wooden_crook = "wooden_crook";
    }

    public class Blocks {
        public static String azalea = "azalea";
        public static String sprinkler = "sprinkler";
        public static String pipe = "pipe";
        public static String node = "transfer_node";
    }
}
