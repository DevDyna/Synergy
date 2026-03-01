package com.devdyna.synergy.api.registers.blockfamilies;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.devdyna.synergy.api.registers.blockfamilies.api.BaseBlockFamily;
import com.devdyna.synergy.init.Material;
import com.devdyna.synergy.init.types.zBlocks;

import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.registries.DeferredHolder;

@SuppressWarnings("null")
public class WoodFamily extends BaseBlockFamily<WoodFamily> {

        protected List<DeferredHolder<Block, Block>> wooden = new ArrayList<>();
        protected List<DeferredHolder<Block, Block>> derivates = new ArrayList<>();

        public WoodFamily(String id, Properties log_prop, MapColor log_map,
                        MapColor plank_map) {
                super(id, log_prop);
                this.log_prop = log_prop;
                this.plank_map = plank_map;
                this.log_map = log_map;
        }

        public static WoodFamily of(String id, Properties log_prop, MapColor log_map,
                        MapColor plank_map) {
                return new WoodFamily(id, log_prop, log_map, plank_map);
        }

        private BlockBehaviour.Properties log_prop;

        private MapColor log_map;
        private MapColor plank_map;

        private DeferredHolder<Block, Block> log;
        private DeferredHolder<Block, Block> sapling;
        private DeferredHolder<Block, Block> leaves;

        private DeferredHolder<Block, Block> stripped_log;

        private DeferredHolder<Block, Block> wood;
        private DeferredHolder<Block, Block> stripped_wood;

        private DeferredHolder<Block, Block> planks;

        private DeferredHolder<Block, Block> stair;
        private DeferredHolder<Block, Block> slab;

        private DeferredHolder<Block, Block> flower_pot;

        @Override
        protected WoodFamily getBuilder() {
                return this;
        }

        public WoodFamily planks() {
                this.planks = Material.registerItemBlock(id + "_planks", () -> new Block(log_prop.mapColor(plank_map)),
                                zBlocks.zDecorative);
                allBlocks.add(planks);
                return this;
        }

        public WoodFamily log() {
                this.log = Material.log(id + "_log", log_prop.mapColor(
                                (s) -> s.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? plank_map : log_map),
                                () -> stripped_log);
                allBlocks.add(log);
                wooden.add(log);
                return this;
        }

        public WoodFamily stripped_log() {
                this.stripped_log = Material.log("stripped_" + id + "_log", log_prop.mapColor(plank_map));
                allBlocks.add(stripped_log);
                wooden.add(stripped_log);
                return this;
        }

        public WoodFamily wood() {
                this.wood = Material.log(id + "_wood", log_prop.mapColor(log_map), () -> stripped_wood);
                allBlocks.add(wood);
                wooden.add(wood);
                return this;
        }

        public WoodFamily stripped_wood() {
                this.stripped_wood = Material.log("stripped_" + id + "_wood", log_prop.mapColor(plank_map));
                allBlocks.add(stripped_wood);
                wooden.add(stripped_wood);
                return this;
        }

        public WoodFamily slab() {
                this.slab = Material.slab(planks);
                allBlocks.add(slab);
                derivates.add(slab);
                return this;
        }

        public WoodFamily stair() {
                this.stair = Material.stair(planks);
                allBlocks.add(stair);
                derivates.add(stair);
                return this;
        }

        public WoodFamily flower_pot() {
                this.flower_pot = Material.flower_pot("potted_" + id + "_sapling",
                                Properties.of().pushReaction(PushReaction.DESTROY).instabreak().noOcclusion(),
                                () -> this.sapling);
                allBlocks.add(flower_pot);
                return this;
        }

        public WoodFamily sapling(ResourceKey<ConfiguredFeature<?, ?>> key, Properties p) {
                this.sapling = Material.sapling(id + "_sapling", p,
                                new TreeGrower("ironwood", Optional.empty(), Optional.of(key), Optional.empty()));
                allBlocks.add(sapling);
                return this;
        }

        public WoodFamily leaves(Properties p) {
                this.leaves = Material.leaves(id + "_leaves", p);
                allBlocks.add(leaves);
                return this;
        }

        public DeferredHolder<Block, Block> getFlowerPot() {
                return flower_pot;
        }

        public DeferredHolder<Block, Block> getLeaves() {
                return leaves;
        }

        public DeferredHolder<Block, Block> getLog() {
                return log;
        }

        public DeferredHolder<Block, Block> getPlanks() {
                return planks;
        }

        public DeferredHolder<Block, Block> getSapling() {
                return sapling;
        }

        public DeferredHolder<Block, Block> getSlab() {
                return slab;
        }

        public DeferredHolder<Block, Block> getStair() {
                return stair;
        }

        public DeferredHolder<Block, Block> getStrippedLog() {
                return stripped_log;
        }

        public DeferredHolder<Block, Block> getStrippedWood() {
                return stripped_wood;
        }

        public DeferredHolder<Block, Block> getWood() {
                return wood;
        }

        public Block[] getLogs() {
                return wooden.stream().map(DeferredHolder::get)
                                .toArray(Block[]::new);
        }

        public Block[] getDerivates() {
                return derivates.stream().map(DeferredHolder::get)
                                .toArray(Block[]::new);
        }

}
