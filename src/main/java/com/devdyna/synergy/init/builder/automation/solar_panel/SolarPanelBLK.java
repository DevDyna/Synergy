package com.devdyna.synergy.init.builder.automation.solar_panel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Nullable;

import com.devdyna.synergy.Main;
import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.api.basebe.block.TickingBlock;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item.TooltipContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

@SuppressWarnings("null")
public class SolarPanelBLK extends TickingBlock {

    protected static ArrayList<BooleanProperty> PROPRTIES = new ArrayList<>(
            Arrays.asList(
                    BlockStateProperties.NORTH,
                    BlockStateProperties.SOUTH,
                    BlockStateProperties.EAST,
                    BlockStateProperties.WEST));

    protected static ArrayList<Direction> DIRECTIONS = new ArrayList<>(
            Arrays.asList(
                    Direction.NORTH,
                    Direction.SOUTH,
                    Direction.EAST,
                    Direction.WEST));

    public SolarPanelBLK() {
        super(Properties.of().strength(1.0f).destroyTime(1.0f).sound(SoundType.METAL).mapColor(MapColor.METAL));
    }

    @Override
    protected void createBlockStateDefinition(Builder<Block, BlockState> b) {
        b.add(BlockStateProperties.ENABLED);
        PROPRTIES.forEach(p -> b.add(p));
    }

    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        // Just return default state with all connections false
        return defaultBlockState()
                .setValue(BlockStateProperties.ENABLED, false)
                .setValue(BlockStateProperties.NORTH, false)
                .setValue(BlockStateProperties.SOUTH, false)
                .setValue(BlockStateProperties.EAST, false)
                .setValue(BlockStateProperties.WEST, false);
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
        if (!level.isClientSide) {
            BlockState newState = state;
            for (Direction face : DIRECTIONS) {
                BlockPos neighborPos = pos.relative(face);
                BlockState neighborState = level.getBlockState(neighborPos);

                if (neighborState.is(this)) {
                    newState = newState.setValue(PROPRTIES.get(DIRECTIONS.indexOf(face)), true);

                    level.setBlock(neighborPos,
                            neighborState.setValue(PROPRTIES.get(DIRECTIONS.indexOf(face.getOpposite())), true),
                            Block.UPDATE_ALL);
                }
            }
            level.setBlock(pos, newState, Block.UPDATE_ALL);
        }
    }

    @Override
    protected VoxelShape getShape(BlockState s, BlockGetter l, BlockPos p, CollisionContext c) {
        return Block.box(0, 0, 0, 16, 4, 16);
    }

    @Override
    public void destroy(LevelAccessor l, BlockPos p, BlockState s) {
        for (Direction face : DIRECTIONS) {
            var offset = l.getBlockState(p.relative(face));
            if (offset.is(s.getBlock())) {
                l.setBlock(p.relative(face),
                        offset.setValue(PROPRTIES.get(DIRECTIONS.indexOf(face.getOpposite())),
                                false),
                        Block.UPDATE_ALL);
            }
        }

    }

    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block neighborBlock,
            BlockPos neighborPos, boolean movedByPiston) {

        for (Direction face : DIRECTIONS) {

            var offset = level.getBlockState(pos.relative(face));
            if (offset.is(state.getBlock())) {

                level.setBlockAndUpdate(pos.relative(face),
                        offset.setValue(PROPRTIES.get(DIRECTIONS.indexOf(face.getOpposite())),
                                true));
                level.setBlockAndUpdate(pos, state.setValue(PROPRTIES.get(DIRECTIONS.indexOf(face)), true));

            } else {

                level.setBlockAndUpdate(pos, state.setValue(PROPRTIES.get(DIRECTIONS.indexOf(face)), false));

            }
        }

        super.neighborChanged(state, level, pos, neighborBlock, neighborPos, movedByPiston);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos p, BlockState s) {
        return new SolarPanelBE(p, s);
    }

    public BooleanProperty getProp(int i) {
        if (PROPRTIES.size() < i)
            return null;
        return PROPRTIES.get(i);
    }

    @Override
    public void appendHoverText(ItemStack i, TooltipContext c, List<Component> t,
            TooltipFlag f) {
        t.add(Component.translatable(Main.ID + "." + zStatic.Blocks.solar_panel));
    }

}
