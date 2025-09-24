package com.devdyna.synergy.datagen.server;

import java.util.concurrent.CompletableFuture;

import com.devdyna.synergy.init.types.zBlocks;
import com.devdyna.synergy.init.types.zItemTag;
import com.devdyna.synergy.init.types.zItems;
import com.devdyna.synergy.init.types.zMultiTags;

import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;

@SuppressWarnings("null")
public class DataItemTag extends ItemTagsProvider {

        public DataItemTag(PackOutput o, CompletableFuture<Provider> p, CompletableFuture<TagLookup<Block>> b) {
                super(o, p, b);
        }

        @Override
        protected void addTags(Provider p) {
                tag(zItemTag.STONE_SLABS).add(Items.STONE_SLAB, Items.SMOOTH_STONE_SLAB);

                tag(zItemTag.AZALEA_BUSHES).add(Items.FLOWERING_AZALEA, Items.AZALEA);

                tag(Tags.Items.SEEDS)
                                .add(zItems.RICE_SEED.get(), zItems.AZALEA_SEEDS.get(), zItems.COTTON_SEEDS.get(),
                                                zItems.CAVE_WHEAT_SEEDS.get());

                tag(ItemTags.CHICKEN_FOOD).add(zItems.RICE_SEED.get(), zItems.AZALEA_SEEDS.get(),
                                zItems.COTTON_SEEDS.get(),
                                zItems.CAVE_WHEAT_SEEDS.get());

                tag(ItemTags.PARROT_FOOD).add(zItems.RICE_SEED.get(), zItems.AZALEA_SEEDS.get(),
                                zItems.COTTON_SEEDS.get(),
                                zItems.CAVE_WHEAT_SEEDS.get());

                tag(ItemTags.VILLAGER_PLANTABLE_SEEDS).add(zItems.RICE_SEED.get(), zItems.AZALEA_SEEDS.get(),
                                zItems.COTTON_SEEDS.get(),
                                zItems.CAVE_WHEAT_SEEDS.get());

                tag(Tags.Items.CROPS).add(zItems.RICE_SEED.get(),
                                zItems.COTTON.get(), zItems.BLUE_CUP_MUSHROOM.get(),
                                zItems.VIOLET_WEBCAP_MUSHROOM.get());

                tag(Tags.Items.CROPS_WHEAT).add(zItems.CAVE_WHEAT_SEEDS.get());
                tag(zItemTag.CROP_AZALEA).add(zItems.SMALL_AZALEA_ROOTS.get(), zItems.SMALL_AZALEA_LEAF.get());

                tag(Tags.Items.MUSHROOMS)
                                .add(zItems.BLUE_CUP_MUSHROOM.get(), zItems.VIOLET_WEBCAP_MUSHROOM.get());

                tag(zItemTag.CROP_BLUE_CUP).add(zItems.BLUE_CUP_MUSHROOM.get());
                tag(zItemTag.CROP_COTTON).add(zItems.COTTON.get());
                tag(zItemTag.CROP_RICE).add(zItems.RICE_SEED.get());
                tag(zItemTag.CROP_VIOLET_WEBCAP).add(zItems.VIOLET_WEBCAP_MUSHROOM.get());
                tag(zItemTag.CROP_CAVE_WHEAT).add(zItems.CAVE_WHEAT_SEEDS.get());

                tag(Tags.Items.FERTILIZERS).add(zItems.AMETHYST_MIXTURE.get(), zItems.BONE_MEAL_MIXTURE.get(),
                                zItems.GLOWSTONE_MIXTURE.get());

                tag(zItemTag.TOOLS_INTERACTIVE)
                                .add(zItems.CONFIGURATOR.get(), zItems.SOLDERING_GUN.get());

                tag(zItemTag.AMERICIUM).add(zItems.AMERICIUM.get());
                tag(zItemTag.BERKELIUM).add(zItems.BERKELIUM.get());
                tag(zItemTag.CALIFORNIUM).add(zItems.CALIFORNIUM.get());
                tag(zItemTag.CURIUM).add(zItems.CURIUM.get());
                tag(zItemTag.NEPTUNIUM).add(zItems.NEPTUNIUM.get());
                tag(zItemTag.PLUTONIUM).add(zItems.PLUTONIUM.get());
                tag(zItemTag.THORIUM).add(zItems.THORIUM.get());
                tag(zItemTag.URANIUM).add(zItems.URANIUM.get());

                tag(zItemTag.COAL_LIKE).add(Items.COAL, Items.CHARCOAL);

                tag(zItemTag.MOB_DROP)
                                .add(zItems.zMobDrop.getEntries().stream().map(i -> i.get()).toArray(Item[]::new));

                tag(zMultiTags.COOLERS.item())
                                .add(zBlocks.zCoolers.getEntries().stream().map(i -> i.get().asItem())
                                                .toArray(Item[]::new));

                tag(zMultiTags.MODERATORS.item())
                                .add(zBlocks.zModerators.getEntries().stream().map(i -> i.get().asItem())
                                                .toArray(Item[]::new));

                tag(Tags.Items.DUSTS).add(
                                zItems.GOLD_DUST.get(),
                                zItems.IRON_DUST.get(),
                                zItems.LAPIS_DUST.get(),
                                zItems.CARBON_DUST.get(),
                                zItems.COPPER_DUST.get(),
                                zItems.QUARTZ_DUST.get(),
                                zItems.DIAMOND_DUST.get(),
                                zItems.EMERALD_DUST.get(),
                                zItems.AMETHYST_DUST.get(),
                                zItems.ANCIENT_DEBRIS_DUST.get(),
                                zItems.SAWDUST.get());

                tag(zItemTag.DUST_AMETHYST).add(zItems.AMETHYST_DUST.get());
                tag(zItemTag.DUST_ANCIENT_DEBRIS).add(zItems.ANCIENT_DEBRIS_DUST.get());
                tag(zItemTag.DUST_COAL).add(zItems.CARBON_DUST.get());
                tag(zItemTag.DUST_COPPER).add(zItems.COPPER_DUST.get());
                tag(zItemTag.DUST_DIAMOND).add(zItems.DIAMOND_DUST.get());
                tag(zItemTag.DUST_EMERALD).add(zItems.EMERALD_DUST.get());
                tag(zItemTag.DUST_GOLD).add(zItems.GOLD_DUST.get());
                tag(zItemTag.DUST_IRON).add(zItems.IRON_DUST.get());
                tag(zItemTag.DUST_LAPIS).add(zItems.LAPIS_DUST.get());
                tag(zItemTag.DUST_QUARTZ).add(zItems.QUARTZ_DUST.get());
                tag(zItemTag.SAWDUST).add(zItems.SAWDUST.get());

                tag(ItemTags.PIGLIN_LOVED).add(zItems.GOLD_DUST.get(), zItems.GOLD_FOIL.get(), zItems.GOLD_PLATE.get());

                tag(Tags.Items.INGOTS)
                .add(zItems.STEEL_INGOT.get(),zItems.ADVANCED_ALLOY_INGOT.get());

                tag(Tags.Items.NUGGETS)
                .add(zItems.STEEL_NUGGET.get(),zItems.ADVANCED_ALLOY_NUGGET.get());

                tag(zItemTag.INGOT_STEEL).add(zItems.STEEL_INGOT.get());
                tag(zItemTag.INGOT_ADVANCEDALLOY).add(zItems.ADVANCED_ALLOY_INGOT.get());
                tag(zItemTag.NUGGET_STEEL).add(zItems.STEEL_NUGGET.get());
                tag(zItemTag.NUGGET_ADVANCEDALLOY).add(zItems.ADVANCED_ALLOY_NUGGET.get());


        }

}