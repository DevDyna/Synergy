package com.devdyna.synergy.init.builder.tinychests;

import java.util.function.Function;

import javax.annotation.Nullable;

import com.devdyna.synergy.api.menu.BlockMenu;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

@SuppressWarnings("null")
public class TinyChestBK extends BlockMenu {

    public TinyChestBK() {
        super(Properties.of().strength(1.0f).destroyTime(1.0f).sound(SoundType.WOOD).mapColor(MapColor.WOOD));
    }

    public TinyChestBK(Properties p) {
        this();
    }

    @Override
    protected void createBlockStateDefinition(Builder<Block, BlockState> b) {
        b.add(BlockStateProperties.HORIZONTAL_FACING);
    }

    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext c) {
        return defaultBlockState()
                .setValue(BlockStateProperties.HORIZONTAL_FACING, c.getHorizontalDirection().getOpposite());
    }

    @Override
    protected VoxelShape getShape(BlockState s, BlockGetter l, BlockPos p, CollisionContext c) {
        return Block.box(5, 0, 5, 11, 6, 11);
    }

    @Override
    @Nullable
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new TinyChestBE(pos, state);
    }

    @Override
    protected Function<Properties, Block> getFactory() {
        return TinyChestBK::new;
    }

    @Override
    protected void onClickAction(BlockState state, Level level, BlockPos pos, Player player) {
        level.playSound(player, pos, SoundEvents.CHEST_OPEN, SoundSource.BLOCKS, 1, 1.75f);
    }

}
