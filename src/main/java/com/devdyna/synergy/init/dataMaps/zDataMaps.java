package com.devdyna.synergy.init.dataMaps;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.registries.datamaps.RegisterDataMapTypesEvent;

public class zDataMaps {
        @SubscribeEvent
        public static void register(RegisterDataMapTypesEvent event) {
                // event.register(CROP_RESULT);
        }

        // public static final DataMapType<Item, CropResultMap> CROP_RESULT =
        // DataMapType.builder(
        // x.rl(ID, "crop_result"),
        // Integer.TYPE,
        // CropResultMap.CODEC).synced(CropResultMap.CODEC, false).build();

}
