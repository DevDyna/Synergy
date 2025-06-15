package com.devdyna.synergy.init.builder._core.crops;

import com.devdyna.synergy.utils.LevelUtil;

import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;

public interface CropHarvestable {

    default ItemInteractionResult getHarvestResult(BlockState state, Level level, Player player) {

        if (!((CropBlock) state.getBlock()).isMaxAge(state))
            return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;

        LevelUtil.getItemStackFromLootTable(level, state)
                .forEach((item) -> player.addItem(item));

        return ItemInteractionResult.SUCCESS;
    }

}
