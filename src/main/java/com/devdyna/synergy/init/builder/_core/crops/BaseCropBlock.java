package com.devdyna.synergy.init.builder._core.crops;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

@SuppressWarnings("null")
public class BaseCropBlock extends CropBlock implements CropHarvestable {

    public BaseCropBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack i, BlockState s, Level l, BlockPos pos,
            Player p, InteractionHand h, BlockHitResult r) {
        return getHarvestResult(s, l, p);
    }

}
