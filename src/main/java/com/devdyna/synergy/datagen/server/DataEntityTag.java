package com.devdyna.synergy.datagen.server;

import static com.devdyna.synergy.Main.ID;

import java.util.concurrent.CompletableFuture;

import org.jetbrains.annotations.Nullable;

import com.devdyna.synergy.init.types.zEntityTag;

import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

@SuppressWarnings("null")
public class DataEntityTag extends EntityTypeTagsProvider {

        public DataEntityTag(PackOutput output, CompletableFuture<Provider> provider,
                        @Nullable ExistingFileHelper existingFileHelper) {
                super(output, provider, ID, existingFileHelper);
        }

        @Override
        protected void addTags(Provider provider) {
        }

}