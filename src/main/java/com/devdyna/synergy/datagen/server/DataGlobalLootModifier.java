package com.devdyna.synergy.datagen.server;

import static com.devdyna.synergy.Main.ID;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.devdyna.synergy.api.utils.DataGenUtil;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootTable;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;

@SuppressWarnings("unchecked")
public class DataGlobalLootModifier extends GlobalLootModifierProvider {

        public DataGlobalLootModifier(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
                super(output, registries, ID);
        }

        @Override
        protected void start() {

                DataGenUtil.modifyLootTables(this, DataAnyLoot.MUSHROOMS,
                                BuiltInLootTables.ABANDONED_MINESHAFT,
                                BuiltInLootTables.SIMPLE_DUNGEON,
                                BuiltInLootTables.VILLAGE_PLAINS_HOUSE,
                                BuiltInLootTables.VILLAGE_SAVANNA_HOUSE,
                                BuiltInLootTables.VILLAGE_SNOWY_HOUSE,
                                BuiltInLootTables.VILLAGE_TAIGA_HOUSE,
                                BuiltInLootTables.VILLAGE_TANNERY,
                                BuiltInLootTables.VILLAGE_TEMPLE,
                                BuiltInLootTables.VILLAGE_MASON,
                                BuiltInLootTables.IGLOO_CHEST,
                                BuiltInLootTables.WOODLAND_MANSION,
                                BuiltInLootTables.SHIPWRECK_SUPPLY);

                List<List<EntityType<?>>> MOB_TYPES = List.of(
                                List.of(EntityType.CREEPER),
                                List.of(EntityType.ENDERMAN),
                                List.of(EntityType.SLIME),
                                List.of(EntityType.GUARDIAN, EntityType.ELDER_GUARDIAN),
                                List.of(EntityType.WITHER_SKELETON),
                                List.of(EntityType.CAVE_SPIDER, EntityType.BOGGED, EntityType.BAT),
                                List.of(EntityType.SILVERFISH),
                                List.of(EntityType.GHAST),
                                List.of(EntityType.ZOMBIE, EntityType.ZOGLIN, EntityType.HUSK, EntityType.ZOMBIE_HORSE,
                                                EntityType.ZOMBIE_VILLAGER, EntityType.ZOMBIFIED_PIGLIN));

                List<List<ResourceKey<LootTable>>> CHEST_TYPES = List.of(
                                List.of(BuiltInLootTables.SIMPLE_DUNGEON, BuiltInLootTables.ABANDONED_MINESHAFT,
                                                BuiltInLootTables.VILLAGE_WEAPONSMITH),
                                List.of(BuiltInLootTables.ABANDONED_MINESHAFT, BuiltInLootTables.SIMPLE_DUNGEON,
                                                BuiltInLootTables.ANCIENT_CITY, BuiltInLootTables.END_CITY_TREASURE),
                                List.of(BuiltInLootTables.ABANDONED_MINESHAFT, BuiltInLootTables.SIMPLE_DUNGEON,
                                                BuiltInLootTables.JUNGLE_TEMPLE, BuiltInLootTables.STRONGHOLD_CORRIDOR,
                                                BuiltInLootTables.SHIPWRECK_SUPPLY,
                                                BuiltInLootTables.TRIAL_CHAMBERS_SUPPLY),
                                List.of(BuiltInLootTables.WOODLAND_MANSION, BuiltInLootTables.BURIED_TREASURE,
                                                BuiltInLootTables.SHIPWRECK_TREASURE),
                                List.of(BuiltInLootTables.NETHER_BRIDGE, BuiltInLootTables.BASTION_BRIDGE,
                                                BuiltInLootTables.BASTION_OTHER, BuiltInLootTables.BASTION_TREASURE,
                                                BuiltInLootTables.BASTION_HOGLIN_STABLE),
                                List.of(BuiltInLootTables.SIMPLE_DUNGEON, BuiltInLootTables.TRIAL_CHAMBERS_SUPPLY,
                                                BuiltInLootTables.JUNGLE_TEMPLE, BuiltInLootTables.DESERT_PYRAMID),
                                List.of(BuiltInLootTables.ABANDONED_MINESHAFT, BuiltInLootTables.SIMPLE_DUNGEON,
                                                BuiltInLootTables.CAT_MORNING_GIFT,
                                                BuiltInLootTables.STRONGHOLD_CORRIDOR,
                                                BuiltInLootTables.STRONGHOLD_CROSSING,
                                                BuiltInLootTables.VILLAGE_WEAPONSMITH),
                                List.of(BuiltInLootTables.NETHER_BRIDGE, BuiltInLootTables.BASTION_BRIDGE,
                                                BuiltInLootTables.BASTION_OTHER, BuiltInLootTables.BASTION_TREASURE,
                                                BuiltInLootTables.BASTION_HOGLIN_STABLE),
                                List.of(BuiltInLootTables.ABANDONED_MINESHAFT, BuiltInLootTables.SIMPLE_DUNGEON,
                                                BuiltInLootTables.VILLAGE_WEAPONSMITH,
                                                BuiltInLootTables.JUNGLE_TEMPLE));

                DataAnyLoot.MOB_DROPS.forEach(item -> {

                        var index = DataAnyLoot.MOB_DROPS.indexOf(item);

                        DataGenUtil.modifyLootTables(this,
                                        item.getRegisteredName().replace(ID + ":", DataAnyLoot.PREFIX_DROPS),
                                        CHEST_TYPES.get(index).toArray(ResourceKey[]::new),
                                        MOB_TYPES.get(index).toArray(EntityType<?>[]::new));

                        // DataGenUtil.modifyLootTables(this,
                        // item.getRegisteredName().replace(ID + ":", DataAnyLoot.PREFIX_DROPS),
                        // );

                });

        }

}
