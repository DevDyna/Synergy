package com.devdyna.synergy.datagen.server;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.devdyna.synergy.init.types.zBlocks;
import com.devdyna.synergy.init.types.zItems;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class DataLoot extends BlockLootSubProvider {

        public DataLoot(HolderLookup.Provider l) {
                super(Set.of(), FeatureFlags.DEFAULT_FLAGS, l);
        }

        @Override
        protected Iterable<Block> getKnownBlocks() {
                List<Block> blocks = new ArrayList<>();
                blocks.addAll(getList(zBlocks.zBlock));
                blocks.addAll(getList(zBlocks.zBlockItem));
                return blocks;
        }

        @SuppressWarnings("unchecked")
        private List<Block> getList(DeferredRegister.Blocks c) {
                return (List<Block>) c.getEntries().stream().map(DeferredHolder::get).toList();
        }

        @Override
        protected void generate() {

                dropSelf(zBlocks.PIPE.get());
                dropSelf(zBlocks.NODE.get());

                dropSelf(zBlocks.SPRINKLER.get());

                add(zBlocks.AZALEA.get(),
                                LootTable.lootTable()
                                                .withPool(applyExplosionCondition(Items.FLOWER_POT,
                                                                LootPool.lootPool()
                                                                                .setRolls(ConstantValue.exactly(1.0F))
                                                                                .add(LootItem.lootTableItem(
                                                                                                Items.FLOWER_POT))))
                                                .withPool(applyExplosionCondition(zItems.AZALEA_SEEDS.get(),
                                                                LootPool.lootPool()
                                                                                .setRolls(UniformGenerator.between(0,
                                                                                                1))
                                                                                .add(LootItem.lootTableItem(
                                                                                                zItems.AZALEA_SEEDS
                                                                                                                .get()))))
                                                .withPool(applyExplosionCondition(zItems.SMALL_AZALEA_LEAF.get(),
                                                                LootPool.lootPool()
                                                                                .setRolls(UniformGenerator.between(0,
                                                                                                2))
                                                                                .add(LootItem.lootTableItem(
                                                                                                zItems.SMALL_AZALEA_LEAF
                                                                                                                .get()))))
                                                .withPool(applyExplosionCondition(zItems.SMALL_AZALEA_ROOTS.get(),
                                                                LootPool.lootPool()
                                                                                .setRolls(UniformGenerator.between(0,
                                                                                                2))
                                                                                .add(LootItem.lootTableItem(
                                                                                                zItems.SMALL_AZALEA_ROOTS
                                                                                                                .get())))));

                dropSelf(zBlocks.EXTRACTOR.get());
                zBlocks.deposits.forEach(e -> dropSelf(e.get()));
                zBlocks.generators.forEach(e->dropSelf(e.get()));

        }

}
