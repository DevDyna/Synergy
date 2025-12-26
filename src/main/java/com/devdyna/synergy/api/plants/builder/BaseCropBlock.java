package com.devdyna.synergy.api.plants.builder;

import java.util.ArrayList;
import java.util.List;

import com.devdyna.synergy.api.harvester.HarvestModes;
import com.devdyna.synergy.api.harvester.PlantHandler;
import com.devdyna.synergy.api.plants.Harvestable;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.InteractionResult;

@SuppressWarnings("null")
public class BaseCropBlock extends CropBlock implements Harvestable, PlantHandler {

    public BaseCropBlock(Properties properties) {
        super(properties.mapColor(MapColor.PLANT)
                .noCollision()
                .randomTicks()
                .instabreak()
                .sound(SoundType.CROP)
                .pushReaction(PushReaction.DESTROY));
    }

    @Override
    protected InteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos,
            Player player, InteractionHand hand, BlockHitResult hitResult) {

        if (harvestCrop(level, state, pos, player, stack))
            return InteractionResult.SUCCESS;
        else

            return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
    }

    @Override
    public List<ItemStack> getItemResult(Level level, BlockState state, BlockPos pos, Player player, ItemStack tool) {
        return Block.getDrops(state, (ServerLevel) level, pos, null, player, tool);
    }

    @Override
    public int maxAge() {
        return getMaxAge();
    }

    @Override
    public boolean canBeHarvested(BlockState state) {
        return isMaxAge(state);
    }

    @Override
    public IntegerProperty getPublicAgeProperty() {
        return getAgeProperty();
    }

    @Override
    public List<ItemStack> itemResult(Level level, BlockPos pos) {
        return Block.getDrops(level.getBlockState(pos), (ServerLevel) level, pos, null);
    }

    @Override
    public void blockReplanted(Level level, BlockPos pos) {
        level.setBlockAndUpdate(pos, level.getBlockState(pos).setValue(getAgeProperty(), 0));
    }

    @Override
    public ArrayList<Block> blockTree() {
        return null;
    }

    @Override
    public HarvestModes getMode() {
        return HarvestModes.BLOCK_REPLANT;
    }

    @Override
    public boolean whenCanBeHarvested(Level level, BlockPos pos) {
        return isMaxAge(level.getBlockState(pos));
    }

    @Override
    public IntegerProperty getProperty() {
        return getAgeProperty();
    }

}
