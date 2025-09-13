package com.devdyna.synergy.datagen.server;

import java.util.concurrent.CompletableFuture;

import com.devdyna.synergy.init.types.zItemTag;
import com.devdyna.synergy.init.types.zItems;

import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
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

        }

}