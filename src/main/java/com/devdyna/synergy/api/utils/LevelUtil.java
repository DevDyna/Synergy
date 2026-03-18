package com.devdyna.synergy.api.utils;

import java.util.List;
import java.util.Random;
import java.util.function.Predicate;

import org.joml.Vector3f;

import com.devdyna.synergy.init.Material;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.phys.Vec3;

@SuppressWarnings("null")
public class LevelUtil {

    public static boolean isDimension(Level level, ResourceKey<Level> dim) {
        return level.dimension().equals(dim);
    }

    public static void SimplePlaceBlock(Level level, BlockPos pos, Block block) {
        level.setBlock(pos, block.defaultBlockState(), 32);
    }

    public static int ValidFaces(BlockPos pos, Level level, TagKey<Block> tag) {
        BlockPos[] dir = { pos.above(), pos.below(), pos.north(), pos.south(), pos.east(), pos.west() };
        int value = 0;
        for (BlockPos face : dir) {
            value += level.getBlockState(face).is(tag) ? 1 : 0;
        }
        return value;
    }

    public static List<Holder<Block>> BlockByTag(TagKey<Block> tag) {
        return BuiltInRegistries.BLOCK.getOrCreateTag(tag).stream().toList();
    }

    public static List<Holder<Block>> BlockByTagName(String tag) {
        return BuiltInRegistries.BLOCK.getOrCreateTag(Material.tagBlock(tag)).stream().toList();
    }

    public static List<Holder<Item>> ItemByTagName(String tag) {
        return BuiltInRegistries.ITEM.getOrCreateTag(Material.tagItem(tag)).stream().toList();
    }

    public static Block BlockByTag(TagKey<Block> tag, int index) {
        return BlockByTag(tag).get(index).value();
    }

    public static List<Holder<Item>> ItemByTag(TagKey<Item> tag) {
        return BuiltInRegistries.ITEM.getOrCreateTag(tag).stream().toList();
    }

    public static List<Item> getItemByTag(TagKey<Item> tag) {
        return BuiltInRegistries.ITEM.getOrCreateTag(tag).stream().map(Holder::value).toList();
    }

    public static Item ItemByTag(TagKey<Item> tag, int index) {
        return ItemByTag(tag).get(index).value();
    }

    public static int getSizeItemTag(TagKey<Item> tag) {
        return ItemByTag(tag).size() - 1;
    }

    public static int getSizeBlockTag(TagKey<Block> tag) {
        return BlockByTag(tag).size() - 1;
    }

    // @Deprecated
    // public static void popItemFromPos(Level level, BlockPos pos, ItemStack
    // itemStack) {
    // Block.popResource(level, pos, itemStack);
    // }

    // @Deprecated
    // public static void popItemFromPos(Level level, int x, int y, int z, ItemStack
    // itemStack) {
    // popItemFromPos(level, new BlockPos(x, y, z), itemStack);
    // }

    // /**
    // * @deprecated use Block.getDrops()
    // */
    // @Deprecated
    // public static List<ItemStack> getItemStackFromLootTable(LevelAccessor level,
    // String raw_ore_name, float luck) {

    // Builder builder = new LootParams.Builder((ServerLevel) level);
    // LootParams params = builder.create(LootContextParamSets.EMPTY);
    // builder.withLuck(luck);

    // LootTable lootTable = level.getServer().reloadableRegistries()
    // .getLootTable(ResourceKey
    // .create(Registries.LOOT_TABLE, ResourceLocation.fromNamespaceAndPath(
    // StringUtil.getModName(raw_ore_name), "blocks/"
    // + raw_ore_name.substring(raw_ore_name.lastIndexOf('.') + 1))));
    // return lootTable.getRandomItems(params);

    // }

    // /**
    // * @deprecated use Block.getDrops()
    // */
    // @Deprecated
    // public static List<ItemStack> getItemStackFromLootTable(LevelAccessor level,
    // String raw_ore_name) {
    // return getItemStackFromLootTable(level, raw_ore_name, 1);
    // }

    // /**
    // * @deprecated use Block.getDrops()
    // */
    // @Deprecated
    // public static List<ItemStack> getItemStackFromLootTable(LevelAccessor level,
    // String raw_ore_name, Player player) {
    // return getItemStackFromLootTable(level, raw_ore_name, player.getLuck());
    // }

    // /**
    // * @deprecated use Block.getDrops()
    // */
    // @Deprecated
    // public static List<ItemStack> getItemStackFromLootTable(LevelAccessor level,
    // BlockState state) {
    // return getItemStackFromLootTable(level, state.getBlock().getDescriptionId(),
    // 1);
    // }

    public static LootTable getLootTable(Level level, ResourceLocation rl) {
        return level.getServer().reloadableRegistries()
                .getLootTable(ResourceKey
                        .create(Registries.LOOT_TABLE, rl));
    }

    public static List<ItemStack> getLootTableItems(Level level, ResourceLocation rl, float luck) {
        return getLootTable(level, rl)
                .getRandomItems(new LootParams.Builder((ServerLevel) level)
                        .withLuck(luck)
                        .create(LootContextParamSets.EMPTY));
    }

    public static List<ItemStack> getLootTableItems(Level level, String rl, float luck) {
        return getLootTable(level, rl)
                .getRandomItems(new LootParams.Builder((ServerLevel) level)
                        .withLuck(luck)
                        .create(LootContextParamSets.EMPTY));
    }

    public static LootTable getLootTable(Level level, String rl) {
        return getLootTable(level, x.rl(rl));
    }

    // example
    /**
     * 
     * @param level
     * @param ModName          "minecraft"
     * @param resourcelocation "blocks/stone"
     * @return
     */
    public static List<ItemStack> getItemStackFromLootTable(ServerLevel level, String ModName,
            String resourcelocation) {
        LootTable lootTable = level.getServer().reloadableRegistries()
                .getLootTable(ResourceKey
                        .create(Registries.LOOT_TABLE, ResourceLocation.fromNamespaceAndPath(
                                ModName, resourcelocation)));
        return lootTable.getRandomItems(new LootParams.Builder(level).create(LootContextParamSets.EMPTY));
    }

    public static int getRandomValue(int max, Level l) {
        if (max <= 0)
            return 1;
        return l.random.nextInt(max) + 1;
    }

    public static boolean chance(int value, Level l) {
        if (value == 0)
            return false;

        return getRandomValue(100, l) <= value;
    }

    public static void addParticle(ParticleOptions type, ServerLevel level, BlockPos pos, boolean isRandom, int count) {
        level.sendParticles(type,
                (double) pos.getX() + 0.5,
                (double) pos.getY() + 0.5,
                (double) pos.getZ() + 0.5,
                count, (isRandom ? level.random.nextDouble() / 2.5 : 0),
                (isRandom ? level.random.nextDouble() / 2.5 : 0),
                (isRandom ? level.random.nextDouble() / 2.5 : 0), (isRandom ? level.random.nextDouble() * 0.025 : 0));
    }

    public static void addParticle(ParticleOptions type, ServerLevel level, BlockPos pos, boolean isRandom) {
        addParticle(type, level, pos, isRandom, 1);
    }

    public static void addParticle(ParticleOptions type, Level level, BlockPos pos, boolean isRandom) {
        addParticle(type, (ServerLevel) level, pos, isRandom, 1);
    }

    /**
     * @param red   0 -> 255
     * @param green 0 -> 255
     * @param blue  0 -> 255
     */
    public static void addDustParticle(int red, int green, int blue,
            ServerLevel level, BlockPos pos,
            boolean isRandom, int count) {

        Vector3f color = Vec3
                .fromRGB24((red << 16) | (green << 8) | blue)
                .toVector3f();

        double x = pos.getX() + 0.5;
        double y = pos.getY() + 0.5;
        double z = pos.getZ() + 0.5;

        double dx = isRandom ? level.random.nextDouble() / 2.5 : 0;
        double dy = isRandom ? level.random.nextDouble() / 2.5 : 0;
        double dz = isRandom ? level.random.nextDouble() / 2.5 : 0;
        double speed = isRandom ? level.random.nextDouble() * 0.025 : 0;

        level.sendParticles(
                new DustParticleOptions(color, 1.0F),
                x, y, z,
                count,
                dx, dy, dz,
                speed);
    }

    public static void addDustParticle(int red, int green, int blue, ServerLevel level, double x, double y, double z,
            boolean isRandom,
            int count) {

        level.sendParticles(
                new DustParticleOptions(Vec3.fromRGB24((red << 16) | (green << 8) | blue).toVector3f(), 1.0F),
                (double) x + 0.5,
                (double) y + 0.5,
                (double) z + 0.5,
                count, (isRandom ? level.random.nextDouble() / 2.5 : 0),
                (isRandom ? level.random.nextDouble() / 2.5 : 0),
                (isRandom ? level.random.nextDouble() / 2.5 : 0), (isRandom ? level.random.nextDouble() * 0.025 : 0));
    }

    /**
     * @param rgbColor like 16711680 [redstone | RGB(255, 0, 0)] ||
     *                 Vec3.fromRGB24(16711680).toVector3f()
     */
    public static void addDustParticle(int rgbColor, ServerLevel level, BlockPos pos, boolean isRandom,
            int count) {

        level.sendParticles(
                new DustParticleOptions(Vec3.fromRGB24(rgbColor).toVector3f(), 1.0F),
                (double) pos.getX() + 0.5,
                (double) pos.getY() + 0.5,
                (double) pos.getZ() + 0.5,
                count, (isRandom ? level.random.nextDouble() / 2.5 : 0),
                (isRandom ? level.random.nextDouble() / 2.5 : 0),
                (isRandom ? level.random.nextDouble() / 2.5 : 0), (isRandom ? level.random.nextDouble() * 0.025 : 0));
    }

    /**
     * spawn a dust particle that will change color randomly
     */
    public static void addDustParticle(ServerLevel level, BlockPos pos, boolean isRandom) {
        var random = new Random();
        addDustParticle(random.nextInt(256), random.nextInt(256), random.nextInt(256), level, pos, isRandom, 1);
    }

    /**
     * spawn a dust particle that will change color like rainbow!
     */
    public static void addRainbowDustParticle(ServerLevel level, BlockPos pos, boolean isRandom) {
        addDustParticle(ColorUtil.rgbColor(level), level, pos, isRandom, 1);
    }

    /**
     * spawn a dust particle that will change color like rainbow!
     */
    public static void addRainbowDustParticle(ServerLevel level, BlockPos pos, boolean isRandom, int count) {
        addDustParticle(ColorUtil.rgbColor(level), level, pos, isRandom, count);
    }

    /**
     * spawn a dust particle that will change color like rainbow!
     */
    public static void addRainbowDustParticle(int delay, ServerLevel level, BlockPos pos, boolean isRandom, int count) {
        addDustParticle(ColorUtil.rgbColor(level, delay), level, pos, isRandom, count);
    }

    /**
     * Check foreach side based on a predicate
     * 
     * @param predicate x -> x instanceof BlockToCheck
     */
    public static int predicateNeighborMatch(Level level, BlockPos pos, Predicate<Block> predicate) {
        int counter = 0;
        for (Direction dir : Direction.values()) {
            Block block = level.getBlockState(pos.relative(dir)).getBlock();
            if (predicate.test(block))
                counter++;
        }
        return counter;
    }

    public static void addDustParticleLine(int red, int green, int blue, ServerLevel level, BlockPos pos,
            Direction direction) {
        addDustParticleLine(red, green, blue, level, pos, direction, 0.35F);
    }

    public static void addDustParticleLine(int red, int green, int blue, ServerLevel level, BlockPos pos,
            Direction direction, float scale) {
        int maxParticles = 9;
        double step = 1.0 / (maxParticles + 1);

        double startX = pos.getX();
        double startY = pos.getY();
        double startZ = pos.getZ();

        for (int i = 1; i <= maxParticles; i++) {
            double offset = i * step;

            double x = startX + 0.5;
            double y = startY + 0.25;
            double z = startZ + 0.5;

            // Apply offset
            switch (direction) {
                case NORTH, SOUTH -> z = startZ + offset; // Z
                case EAST, WEST -> x = startX + offset; // X
                case UP, DOWN -> y = startY + offset; // Y
            }

            level.sendParticles(
                    new DustParticleOptions(
                            Vec3.fromRGB24((red << 16) | (green << 8) | blue).toVector3f(),
                            scale),
                    x, y, z,
                    1,
                    0, 0, 0, 0);
        }
    }

    public static void addDustParticleDiagonalLine(int red, int green, int blue, ServerLevel level, BlockPos pos,
            Direction input, Direction output, float scale) {
        int maxParticles = 8;

        double startX = pos.getX() + 0.5;
        double startY = pos.getY() + 0.25;
        double startZ = pos.getZ() + 0.5;

        Vec3 inputVec = Vec3.atLowerCornerOf(input.getOpposite().getNormal()).scale(0.5);
        Vec3 outputVec = Vec3.atLowerCornerOf(output.getNormal()).scale(0.5);

        for (int i = 1; i <= maxParticles; i++) {
            double t = (double) i / (maxParticles + 1);

            double x = startX + inputVec.x * (1 - t) + outputVec.x * t;
            double y = startY + inputVec.y * (1 - t) + outputVec.y * t;
            double z = startZ + inputVec.z * (1 - t) + outputVec.z * t;

            level.sendParticles(
                    new DustParticleOptions(
                            Vec3.fromRGB24((red << 16) | (green << 8) | blue).toVector3f(),
                            scale),
                    x, y, z,
                    1,
                    0, 0, 0, 0);
        }
    }

    public static void addRepeaterRedstoneParticles(Level level, BlockPos pos, Direction dir, int delay) {

        float f = -5.0F;
        if (level.random.nextBoolean()) {
            f = delay * 2 - 1;
        }

        f /= 16.0F;

        level.addParticle(DustParticleOptions.REDSTONE,
                pos.getX() + 0.5 + (level.random.nextDouble() - 0.5) * 0.2 + f * dir.getStepX(),
                pos.getY() + 0.4 + (level.random.nextDouble() - 0.5) * 0.2,
                pos.getZ() + 0.5 + (level.random.nextDouble() - 0.5) * 0.2 + f * dir.getStepZ(),
                0.0, 0.0, 0.0);
    }

    /**
     * compare All Blockstate Properties
     */
    public static boolean compareProperties(BlockState a, BlockState b) {

        for (Property<?> prop : a.getProperties())
            if (!a.getValue(prop).equals(b.getValue(prop)))
                return false;

        return true;
    }

}
