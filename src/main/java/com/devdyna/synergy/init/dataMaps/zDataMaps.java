package com.devdyna.synergy.init.dataMaps;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.registries.datamaps.RegisterDataMapTypesEvent;

public class zDataMaps {
        @SubscribeEvent
        public static void register(RegisterDataMapTypesEvent event) {
                // event.register(ITEM_TO_BRICK);
        }

        // public static final DataMapType<Item, ItemToBrickMap> ITEM_TO_BRICK = DataMapType.builder(
        //                 x.rl(ID, "item_to_block"),
        //                 Registries.ITEM,
        //                 ItemToBrickMap.CODEC)
        //                 .synced(ItemToBrickMap.CODEC, false)
        //                 .build();

}
