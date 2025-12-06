package com.devdyna.synergy.datagen.server;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.devdyna.synergy.api.plants.builder.BaseCropBlock;
import com.devdyna.synergy.api.plants.builder.BaseShortCropBlock;
import com.devdyna.synergy.init.builder.DryableBricks;
import com.devdyna.synergy.init.types.zBlocks;
import com.devdyna.synergy.init.types.zItems;
import com.devdyna.synergy.utils.DataGenUtil;
import com.devdyna.synergy.utils.EnchantUtil;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition.Builder;
import net.minecraft.world.level.storage.loot.predicates.BonusLevelTableCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

@SuppressWarnings("null")
public class DataLootBlock extends BlockLootSubProvider {

        public DataLootBlock(HolderLookup.Provider l) {
                super(Set.of(), FeatureFlags.DEFAULT_FLAGS, l);
        }

        List<DeferredRegister.Blocks> blocktypes = List.of(
                        zBlocks.zBlock,
                        zBlocks.zBlockItem,
                        zBlocks.zCrop,
                        zBlocks.zHiddenBlock,
                        zBlocks.zWildCrop,
                        zBlocks.zDecorative,
                        zBlocks.zBlockStair,
                        zBlocks.zBlockSlab,
                        zBlocks.zCoolers,
                        zBlocks.zModerators,
                        // wip
                        zBlocks.zDepositBlocks,
                        zBlocks.zDepositOres);

        @Override
        protected Iterable<Block> getKnownBlocks() {
                List<Block> blocks = new ArrayList<>();
                blocktypes.forEach(t -> blocks.addAll(t.getEntries().stream().map(DeferredHolder::get).toList()));
                return blocks;
        }

        @Override
        protected void generate() {

                Set.of(
                                zBlocks.BASIC_MACHINE_FRAME,
                                zBlocks.PIPE,

                                zBlocks.ITEM_PROVIDER,
                                zBlocks.ITEM_RETRIEVAL,
                                zBlocks.ITEM_TRANSFER,

                                // zBlocks.ENERGY_PROVIDER,
                                zBlocks.ENERGY_RETRIEVAL,
                                zBlocks.ENERGY_TRANSFER,

                                zBlocks.FLUID_PROVIDER,
                                zBlocks.FLUID_RETRIEVAL,
                                zBlocks.FLUID_TRANSFER,

                                zBlocks.SOLAR_PANEL,
                                zBlocks.HARVESTER,
                                zBlocks.SPRINKLER,
                                zBlocks.HEALER,

                                // zBlocks.REACTOR_PORT,
                                zBlocks.REACTOR_CONTROLLER,
                                zBlocks.REACTOR_FUEL_CELL,
                                zBlocks.COOLER_BASE,

                                zBlocks.WOODEN_TINY_CHEST,
                                zBlocks.ORNATE_TINY_CHEST,
                                zBlocks.STONE_TINY_CHEST,

                                zBlocks.ADVANCED_MACHINE_FRAME,
                                zBlocks.URN,
                                zBlocks.QUERN,
                                zBlocks.LASER_MACHINE,
                                zBlocks.LASER_LENS,
                                zBlocks.LASER_MIRROR,
                                zBlocks.LASER_SENSOR,



                                zBlocks.RECURSIVE_REPEATER,
                                zBlocks.PULSE_REPEATER,
                                zBlocks.INVERTED_REPEATER,
                                zBlocks.ELECTROMAGNETIC_ROTOR

                ).forEach(b -> dropSelf(b.get()));

                var types = List.of(
                                zBlocks.zModerators, zBlocks.zCoolers, zBlocks.zBlockSlab, zBlocks.zBlockStair,
                                zBlocks.zDecorative

                );

                types.forEach(t -> t.getEntries().forEach(b -> dropSelf(b.get())));

                // zBlocks.zOnlyBlock.getEntries().forEach(b -> dropOther(b.get(), Items.AIR));

                azalea();

                cropDrop7(zBlocks.RICE.get(), zItems.RICE_SEED.get(),
                                zItems.RICE_SEED.get());

                cropDrop5(zBlocks.BLUE_CUP_MUSHROOM.get(),
                                zItems.BLUE_CUP_SPORE.get(),
                                zItems.BLUE_CUP_MUSHROOM.get());

                cropDrop5(zBlocks.VIOLET_WEBCAP_MUSHROOM.get(),
                                zItems.VIOLET_WEBCAP_SPORE.get(),
                                zItems.VIOLET_WEBCAP_MUSHROOM.get());

                cropDrop5(zBlocks.CAVE_WHEAT.get(),
                                zItems.CAVE_WHEAT_SEEDS.get(),
                                Items.WHEAT);

                cropDrop5(zBlocks.COTTON.get(),
                                zItems.COTTON_SEEDS.get(),
                                zItems.COTTON.get());

                cropDropNoConditions(zBlocks.WILD_RICE.get(),
                                zItems.RICE_SEED.get(),
                                zItems.RICE_SEED.get());

                cropDropNoConditions(zBlocks.WILD_CAVE_WHEAT.get(),
                                zItems.CAVE_WHEAT_SEEDS.get(),
                                Items.WHEAT);

                cropDropNoConditions(zBlocks.WILD_COTTON.get(),
                                zItems.COTTON_SEEDS.get(),
                                zItems.COTTON.get());

                // dropSelf(zBlocks.EXTRACTOR.get());
                // zBlocks.deposits.forEach(e -> dropSelf(e.get()));
                // zBlocks.generators.forEach(e->dropSelf(e.get()));

                brick(zBlocks.CLAY_BRICK, Items.CLAY_BALL, Items.BRICK);
                brick(zBlocks.PACKED_MUD_BRICK, zItems.PACKED_MUD_BALL.get(), zItems.PACKED_MUD_BRICK.get());

        }

        private void brick(DeferredHolder<Block, Block> b, Item fail, Item success) {
                add(b.get(),
                                LootTable.lootTable()
                                                .withPool(DataGenUtil.createPool()
                                                                .add(LootItem.lootTableItem(fail))
                                                                .when(DataGenUtil.lootTableConditionInverse(
                                                                                b.get(),
                                                                                DryableBricks.DRIED))
                                                                .apply(SetItemCountFunction
                                                                                .setCount(ConstantValue.exactly(1))))
                                                .withPool(DataGenUtil.createPool()
                                                                .add(LootItem.lootTableItem(success))
                                                                .when(DataGenUtil.lootTableCondition(
                                                                                b.get(),
                                                                                DryableBricks.DRIED))
                                                                .apply(SetItemCountFunction
                                                                                .setCount(ConstantValue.exactly(1)))));
        }

        // TODO add fortune and age condition
        // TODO convert to crop
        private void azalea() {
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

        }

        /**
         * Only BaseShortCropBlock crops support
         */
        private void cropDrop5(Block b, Item seed, Item... items) {
                cropDrop(b, DataGenUtil.lootTableCondition(b, BaseShortCropBlock.AGE, BaseShortCropBlock.MAX_AGE), seed,
                                1, 3, items);
        }

        /**
         * Only BaseCropBlock crops support
         */
        private void cropDrop7(Block b, Item seed, Item... items) {
                cropDrop(b, DataGenUtil.lootTableCondition(b, BaseCropBlock.AGE, BaseCropBlock.MAX_AGE), seed, 1, 3,
                                items);
        }

        private void cropDrop(Block b, Builder builder, Item seed, int min, int max, Item... items) {

                var table = LootTable.lootTable()
                                .withPool(LootPool.lootPool().add(LootItem.lootTableItem(seed)
                                                .when(builder)
                                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 5)))
                                                .apply(ApplyBonusCount.addUniformBonusCount(EnchantUtil
                                                                .getEnchantHolder(registries, Enchantments.FORTUNE)))));

                for (Item item : items)
                        table = table.withPool(LootPool.lootPool().add(LootItem
                                        .lootTableItem(item).when(builder)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(min, max)))
                                        .apply(ApplyBonusCount.addUniformBonusCount(
                                                        EnchantUtil.getEnchantHolder(registries, Enchantments.FORTUNE)))
                                        .otherwise(LootItem.lootTableItem(seed))));

                add(b, applyExplosionDecay(b, table));
        }

        private void cropDropNoConditions(Block b, int min, int max, Item... items) {

                var table = LootTable.lootTable();

                for (Item item : items) {
                        table = table.withPool(LootPool.lootPool().add(LootItem.lootTableItem(item)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(min, max))))
                                        .when(BonusLevelTableCondition.bonusLevelFlatChance(
                                                        EnchantUtil.getEnchantHolder(registries, Enchantments.FORTUNE),
                                                        2)));
                }

                add(b, applyExplosionDecay(b, table));
        }

        private void cropDropNoConditions(Block b, Item... items) {
                cropDropNoConditions(b, 1, 3, items);
        }

}
