package com.devdyna.synergy.datagen.server;

import static com.devdyna.synergy.Main.ID;

import java.util.concurrent.CompletableFuture;


import com.devdyna.synergy.init.types.zEntityTag;

import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;

public class DataEntityTag extends EntityTypeTagsProvider {

        public DataEntityTag(PackOutput output, CompletableFuture<Provider> provider) {
                super(output, provider, ID);
        }

        @Override
        protected void addTags(Provider provider) {
                tag(zEntityTag.DONT_LIKE_JAY_Z);
        }

}