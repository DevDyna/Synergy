package com.devdyna.synergy.datagen.server;

import java.util.List;
import java.util.function.BiConsumer;

import com.devdyna.synergy.init.types.zItems;
import com.devdyna.synergy.utils.DataGenUtil;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

@SuppressWarnings("null")
public class DataAnyLoot implements LootTableSubProvider {

    public final static String ENTITY_DROP = "entities/mob_drop";

    public DataAnyLoot(HolderLookup.Provider p) {
    }

    @Override
    public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> c) {

        var pool = DataGenUtil.createPool().setRolls(UniformGenerator.between(1.0f, 3.0f));

        List.of(zItems.BLUE_CUP_SPORE, zItems.VIOLET_WEBCAP_SPORE)
                .forEach(i -> pool.add(LootItem.lootTableItem(i.get())));

        pool.add(LootItem.lootTableItem(Items.ENDER_PEARL)
                .when(LootItemRandomChanceCondition.randomChance(0.3f))
                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0f))));

        var table = DataGenUtil.createTable(pool);

        DataGenUtil.regTable(c, DataGenUtil.modLoc(ENTITY_DROP), table);

    }

}
