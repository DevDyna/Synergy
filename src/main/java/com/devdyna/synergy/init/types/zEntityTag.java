package com.devdyna.synergy.init.types;

import com.devdyna.synergy.init.Material;

import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.neoforged.bus.api.IEventBus;

public class zEntityTag {
        public static void register(IEventBus bus) {
        }

        public static final TagKey<EntityType<?>> DONT_LIKE_JAY_Z = Material.tagEntity("no_forever_young");
        public static final TagKey<EntityType<?>> CRUSHING_TUB_ALLOW = Material.tagEntity("crushing_tub_allow");
        
        public static final TagKey<EntityType<?>> ENTITY_WATCHER_IGNORE = Material.tagEntity("entity_watcher_ignore");


}