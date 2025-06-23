package com.devdyna.synergy.init.builder._core.crops;

import java.util.List;

import com.devdyna.synergy.utils.LevelUtil;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.neoforged.neoforge.items.ItemHandlerHelper;

public interface Harvestable {

    List<ItemStack> getItemResult(Level level, BlockState state, BlockPos pos, Player player, ItemStack tool);

    int maxAge();

    boolean canBeHarvested(BlockState state);

    IntegerProperty property();

    default boolean harvestCrop(Level level, BlockState state, BlockPos pos, Player player, ItemStack tool) {
        if (!level.isClientSide && canBeHarvested(state)) {

            getItemResult(level, state, pos, player, tool)
                    .forEach(item -> ItemHandlerHelper.giveItemToPlayer(player, item));

            level.setBlockAndUpdate(pos,
                    state.setValue(property(), LevelUtil.getRandomValue(maxAge() - 2, level)));
            return true;
        }
        return false;
    }

}
