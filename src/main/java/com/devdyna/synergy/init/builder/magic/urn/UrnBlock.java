package com.devdyna.synergy.init.builder.magic.urn;

import javax.annotation.Nullable;

import com.devdyna.synergy.api.basebe.block.TickingBlock;
import com.devdyna.synergy.api.beLogic.DropOnBreak;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

@SuppressWarnings("null")
public class UrnBlock extends TickingBlock {

    public UrnBlock() {
        super(Properties.of().strength(0.4f).destroyTime(0.4f).sound(SoundType.DECORATED_POT)
                .mapColor(MapColor.TERRACOTTA_BROWN));
    }

    @Override
    @Nullable
    public BlockEntity newBlockEntity(BlockPos arg0, BlockState arg1) {
        return new UrnBE(arg0, arg1);
    }

    @Override
    protected VoxelShape getShape(BlockState s, BlockGetter l, BlockPos p, CollisionContext c) {
        return Block.box(5, 0, 5, 11, 6, 11);
    }

    @Override
    public void destroy(LevelAccessor level, BlockPos pos, BlockState state) {
        if (level.getBlockEntity(pos) instanceof DropOnBreak be)
            be.drops();
        super.destroy(level, pos, state);
    }

    @Override
    protected InteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos,
            Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (level.getBlockEntity(pos) instanceof UrnBE be)
            return be.itemUseOn(player, level, pos, hand);
        return InteractionResult.FAIL;
    }

}
