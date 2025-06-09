package com.devdyna.synergy.utils;

import java.util.List;

import com.devdyna.synergy.init.Material;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootParams.Builder;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

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

    public static Item ItemByTag(TagKey<Item> tag, int index) {
        return ItemByTag(tag).get(index).value();
    }

    public static int getSizeItemTag(TagKey<Item> tag) {
        return ItemByTag(tag).size() - 1;
    }

    public static int getSizeBlockTag(TagKey<Block> tag) {
        return BlockByTag(tag).size() - 1;
    }

    public static void popItemFromPos(Level level, double x, double y, double z, ItemStack itemStack) {
        ItemEntity itementity = new ItemEntity(level, x, y, z, itemStack);
        level.addFreshEntity(itementity);
    }

    public static void popItemFromPos(Level level, int x, int y, int z, ItemStack itemStack) {
        popItemFromPos(level, (double) x, (double) y, (double) z, itemStack);
    }

    public static void popItemFromPos(LevelAccessor level, double x, double y, double z, ItemStack itemStack) {
        popItemFromPos((Level) level, x, y, z, itemStack);
    }

    public static void popItemFromPos(Level level, BlockPos pos, ItemStack itemStack) {
        popItemFromPos(level, pos.getX(), pos.getY(), pos.getZ(), itemStack);
    }

    @SuppressWarnings("null")
    public static List<ItemStack> getItemStackFromLootTable(LevelAccessor level, String raw_ore_name, float luck) {

        Builder builder = new LootParams.Builder((ServerLevel) level);
        LootParams params = builder.create(LootContextParamSets.EMPTY);
        builder.withLuck(luck);

        LootTable lootTable = level.getServer().reloadableRegistries()
                .getLootTable(ResourceKey
                        .create(Registries.LOOT_TABLE, ResourceLocation.fromNamespaceAndPath(
                                IDUtil.getModName(raw_ore_name), "blocks/"
                                        + raw_ore_name.substring(raw_ore_name.lastIndexOf('.') + 1))));
        return lootTable.getRandomItems(params);

    }

    public static List<ItemStack> getItemStackFromLootTable(LevelAccessor level, String raw_ore_name) {
        return getItemStackFromLootTable(level, raw_ore_name, 1);
    }

    public static List<ItemStack> getItemStackFromLootTable(LevelAccessor level, String raw_ore_name, Player player) {
        return getItemStackFromLootTable(level, raw_ore_name, player.getLuck());
    }

    public static List<ItemStack> getItemStackFromLootTable(LevelAccessor level, BlockState state) {
        return getItemStackFromLootTable(level, state.getBlock().getDescriptionId(), 1);
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

}
