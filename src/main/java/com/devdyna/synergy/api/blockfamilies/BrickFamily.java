package com.devdyna.synergy.api.blockfamilies;

import java.util.function.Supplier;

import com.devdyna.synergy.init.Material;
import com.devdyna.synergy.init.builder.decorative.DecorativeBlock;
import com.devdyna.synergy.init.builder.decorative.PillarDecorativeBlock;
import com.devdyna.synergy.init.types.zBlocks;

import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredHolder;

public class BrickFamily {

        private String id;

        private BlockBehaviour.Properties properties;

        private DeferredHolder<Block, Block> bricks;
        private DeferredHolder<Block, Block> column;
        private DeferredHolder<Block, Block> tiles;

        private DeferredHolder<Block, Block> stair_bricks;
        private DeferredHolder<Block, Block> stair_tiles;

        private DeferredHolder<Block, Block> slab_bricks;
        private DeferredHolder<Block, Block> slab_tiles;

        private TagKey<Block> tagkey;

        public BrickFamily(String id) {
                this.id = id;
        }

        public BrickFamily(String id, Properties p) {
                this.id = id;
                this.properties = p;
        }

        public static BrickFamily of(String id) {
                return new BrickFamily(id);
        }

        public static BrickFamily of(String id, Properties p) {
                return new BrickFamily(id, p);
        }

        public BrickFamily bricks(Properties p) {
                this.bricks = Material.registerItemBlock(id + "_bricks", () -> new DecorativeBlock(p),
                                zBlocks.zDecorative);
                return this;
        }

        public BrickFamily tiles(Properties p) {
                this.tiles = Material.registerItemBlock(id + "_tiles", () -> new DecorativeBlock(p),
                                zBlocks.zDecorative);
                return this;
        }

        public BrickFamily pillar(Properties p) {
                this.column = Material.registerItemBlock(id + "_column", () -> new PillarDecorativeBlock(p),
                                zBlocks.zColumn);
                return this;
        }

        public BrickFamily bricks() {
                checkProp();
                return bricks(this.properties);
        }

        public BrickFamily tiles() {
                checkProp();
                return tiles(this.properties);
        }

        public BrickFamily pillar() {
                checkProp();
                return pillar(this.properties);
        }

        public BrickFamily brick_stair() {
                this.stair_bricks = Material.stair(bricks);
                return this;
        }

        public BrickFamily brick_slab() {
                this.slab_bricks = Material.slab(bricks);
                return this;
        }

        public BrickFamily tile_stair() {
                this.stair_tiles = Material.stair(tiles);
                return this;
        }

        public BrickFamily tile_slab() {
                this.slab_tiles = Material.slab(tiles);
                return this;
        }

        public String getId() {
                return id;
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

        private void checkProp() {
                if (this.properties == null)
                        throw new NullPointerException("BrickFamily " + this.id + " don't contain a valid Properties!");
        }

        public void buildCreativeTab(Supplier<BuildCreativeModeTabContentsEvent> s) {
                var b = s.get();
                b.accept(this.bricks.get());
                b.accept(this.tiles.get());
                b.accept(this.column.get());
                b.accept(this.slab_bricks.get());
                b.accept(this.slab_tiles.get());
                b.accept(this.stair_bricks.get());
                b.accept(this.stair_tiles.get());
        }

        public Block[] getAll() {
                return new Block[]{
                                this.bricks.get(),
                                this.tiles.get(),
                                this.column.get(),
                                this.slab_bricks.get(),
                                this.slab_tiles.get(),
                                this.stair_bricks.get(),
                                this.stair_tiles.get()};
        }

        public BrickFamily createTag(String id) {
                this.tagkey = Material.tagBlock(id);
                return this;
        }

        public BrickFamily createTag() {
                return createTag(id + "_any");
        }

        public TagKey<Block> getTagkey() {
            return tagkey;
        }

}
