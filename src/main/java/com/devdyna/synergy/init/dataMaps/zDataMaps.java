package com.devdyna.synergy.init.dataMaps;

import static com.devdyna.synergy.Main.ID;

import com.devdyna.synergy.init.dataMaps.RecordMap.ProviderRecipes;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.registries.datamaps.DataMapType;
import net.neoforged.neoforge.registries.datamaps.RegisterDataMapTypesEvent;

public class zDataMaps {
            @SubscribeEvent
        public static void register(RegisterDataMapTypesEvent event) {
                event.register(PROVIDER_RECIPES);
        }

        public static final DataMapType<Item, ProviderRecipes> PROVIDER_RECIPES = DataMapType.builder(
                        ResourceLocation.fromNamespaceAndPath(ID, "provider/recipes"),
                        Registries.ITEM,
                        ProviderRecipes.CODEC).synced(ProviderRecipes.CODEC, false).build();
}
