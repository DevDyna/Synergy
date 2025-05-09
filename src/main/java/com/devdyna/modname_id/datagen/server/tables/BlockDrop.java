package com.devdyna.modname_id.datagen.server.tables;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.devdyna.modname_id.init.types.zBlocks;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class BlockDrop extends BlockLootSubProvider {

        public BlockDrop(HolderLookup.Provider l) {
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
                // dropSelf(Blocks.BK.get());

                // Blocks.zBlock.getEntries().forEach(b -> dropSelf(b.get()));

        }

}
