package com.devdyna.synergy.datagen.server;

import static com.devdyna.synergy.Main.ID;

import java.util.concurrent.CompletableFuture;

import org.jetbrains.annotations.Nullable;

import com.devdyna.synergy.init.types.zBiomeTags;

import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.world.level.biome.Biomes;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

@SuppressWarnings("null")
public class DataBiomeTag extends BiomeTagsProvider {

    public DataBiomeTag(PackOutput o, CompletableFuture<Provider> p,
            @Nullable ExistingFileHelper e) {
        super(o, p, ID, e);
    }

    @Override
    protected void addTags(Provider p) {
        tag(zBiomeTags.BLUE_CUP_SPAWN)
                .addTag(Tags.Biomes.IS_OVERWORLD);

        tag(zBiomeTags.VIOLET_WEBCAP_SPAWN)
                .addTag(Tags.Biomes.IS_OVERWORLD);

        tag(zBiomeTags.WILD_CAVE_WHEAT_SPAWN)
                .addTag(Tags.Biomes.IS_OVERWORLD);

        tag(zBiomeTags.WILD_COTTON_SPAWN)
                .addTag(Tags.Biomes.IS_PLAINS)
                .addTag(Tags.Biomes.IS_FOREST)
                .addTag(Tags.Biomes.IS_SAVANNA)
                .addTag(Tags.Biomes.IS_TAIGA);

        tag(zBiomeTags.WILD_RICE_SPAWN)
                .addTag(Tags.Biomes.IS_JUNGLE)
                .addTag(Tags.Biomes.IS_RIVER)
                .addTag(Tags.Biomes.IS_SWAMP)
                .addTag(Tags.Biomes.IS_BEACH);

        tag(zBiomeTags.IRONWOOD_TREE_SPAWN)
                .addTag(Tags.Biomes.IS_FOREST)
                .addTag(Tags.Biomes.IS_TAIGA)
                .add(Biomes.MEADOW)
                .addTag(Tags.Biomes.IS_WINDSWEPT)
                .addTag(Tags.Biomes.IS_BEACH);
    }

}
