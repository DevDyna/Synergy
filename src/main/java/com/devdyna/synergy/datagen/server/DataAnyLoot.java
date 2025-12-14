package com.devdyna.synergy.datagen.server;

import static com.devdyna.synergy.Main.ID;

import java.util.List;
import java.util.function.BiConsumer;

import com.devdyna.synergy.api.utils.DataGenUtil;
import com.devdyna.synergy.api.utils.x;
import com.devdyna.synergy.init.types.zItems;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.EnchantedCountIncreaseFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.neoforged.neoforge.registries.DeferredHolder;

@SuppressWarnings("null")
public class DataAnyLoot implements LootTableSubProvider {

        // public final static String CHEST_DROPS = "chests/";
        public final static String PREFIX_DROPS = "entities/extra_mob_drops/";
        public final static String MUSHROOMS = "chests/mushrooms";

        private HolderLookup.Provider p;

        public DataAnyLoot(HolderLookup.Provider p) {
                this.p = p;
        }

        public final static List<DeferredHolder<Item, Item>> MOB_DROPS = List.of(
                        zItems.CREEPER_GALL,
                        zItems.ENDERMAN_HEART,
                        zItems.SLIME_BOLUS,
                        zItems.GUARDIAN_SCALE,
                        zItems.WITHERFLESH,
                        zItems.VENOM_SAC,
                        zItems.SILVERFISH_DUST,
                        zItems.GHAST_BLADDER,
                        zItems.ZOMBIE_LIVER);

        @Override
        public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> c) {

                for (DeferredHolder<Item, Item> items : MOB_DROPS) {

                        DataGenUtil.registerTable(c,
                                        x.rl(items.getRegisteredName().replace(ID + ":", PREFIX_DROPS)),

                                        DataGenUtil.createTable(DataGenUtil.createPool()
                                                        .setRolls(UniformGenerator.between(0.0f, 1.0f))
                                                        .add(LootItem.lootTableItem(items.get()))
                                                        .when(LootItemRandomChanceCondition.randomChance(0.25f))
                                                        .apply(SetItemCountFunction.setCount(
                                                                        UniformGenerator.between(1.0f, 3.0f)))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(p,
                                                                        UniformGenerator.between(1, 3)))));

                }

                var mushLoot = DataGenUtil.createPool().setRolls(UniformGenerator.between(1.0f,
                                3.0f));

                List.of(zItems.BLUE_CUP_SPORE,
                                zItems.BLUE_CUP_MUSHROOM,
                                zItems.VIOLET_WEBCAP_SPORE,
                                zItems.VIOLET_WEBCAP_MUSHROOM)
                                .forEach(i -> mushLoot.add(LootItem.lootTableItem(i.get())));

                var mushtable = DataGenUtil.createTable(mushLoot);

                DataGenUtil.registerTable(c, x.rl(MUSHROOMS), mushtable);

        }

}
