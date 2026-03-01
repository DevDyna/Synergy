package com.devdyna.synergy.api.registers.blockfamilies;

import com.devdyna.synergy.api.registers.blockfamilies.api.BaseBlockFamily;
import com.devdyna.synergy.init.Material;
import com.devdyna.synergy.init.types.zBlocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.neoforged.neoforge.registries.DeferredHolder;

public class BrickFamily extends BaseBlockFamily<BrickFamily> {

        public BrickFamily(String id, Properties p) {
                super(id, p);
        }

        public static BrickFamily of(String id, Properties p) {
                return new BrickFamily(id, p);
        }

        private DeferredHolder<Block, Block> bricks;
        private DeferredHolder<Block, Block> column;
        private DeferredHolder<Block, Block> tiles;

        private DeferredHolder<Block, Block> stair_bricks;
        private DeferredHolder<Block, Block> stair_tiles;

        private DeferredHolder<Block, Block> slab_bricks;
        private DeferredHolder<Block, Block> slab_tiles;

        public BrickFamily bricks() {
                this.bricks = Material.registerItemBlock(id + "_bricks", () -> new Block(this.properties),
                                zBlocks.zDecorative);
                allBlocks.add(bricks);
                return this;
        }

        public BrickFamily tiles() {
                this.tiles = Material.registerItemBlock(id + "_tiles", () -> new Block(this.properties),
                                zBlocks.zDecorative);
                allBlocks.add(tiles);
                return this;
        }

        public BrickFamily pillar() {
                this.column = Material.registerItemBlock(id + "_column",
                                () -> new RotatedPillarBlock(this.properties),
                                zBlocks.zColumn);
                allBlocks.add(column);
                return this;
        }

        public BrickFamily brick_stair() {
                this.stair_bricks = Material.stair(bricks);
                allBlocks.add(stair_bricks);
                return this;
        }

        public BrickFamily brick_slab() {
                this.slab_bricks = Material.slab(bricks);
                allBlocks.add(slab_bricks);
                return this;
        }

        public BrickFamily tile_stair() {
                this.stair_tiles = Material.stair(tiles);
                allBlocks.add(stair_tiles);
                return this;
        }

        public BrickFamily tile_slab() {
                this.slab_tiles = Material.slab(tiles);
                allBlocks.add(slab_tiles);
                return this;
        }

        public DeferredHolder<Block, Block> getBricks() {
                return bricks;
        }

        public DeferredHolder<Block, Block> getColumn() {
                return column;
        }

        public DeferredHolder<Block, Block> getTiles() {
                return tiles;
        }

        public DeferredHolder<Block, Block> getSlabBricks() {
                return slab_bricks;
        }

        public DeferredHolder<Block, Block> getSlabTiles() {
                return slab_tiles;
        }

        public DeferredHolder<Block, Block> getStairBricks() {
                return stair_bricks;
        }

        public DeferredHolder<Block, Block> getStairTiles() {
                return stair_tiles;
        }

        @Override
        protected BrickFamily getBuilder() {
                return this;
        }

}
