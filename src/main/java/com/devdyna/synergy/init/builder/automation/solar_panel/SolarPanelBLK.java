package com.devdyna.synergy.init.builder.automation.solar_panel;

import java.util.*;

import javax.annotation.Nullable;

import com.devdyna.synergy.Main;
import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.api.basebe.block.TickingBlock;
import com.devdyna.synergy.api.beLogic.Connectable;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

import net.minecraft.Util;
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
public class SolarPanelBLK extends TickingBlock implements Connectable {

    public SolarPanelBLK() {
        super(Properties.of()
                .strength(1.0f)
                .destroyTime(1.0f)
                .sound(SoundType.METAL)
                .mapColor(MapColor.METAL));
    }

    @Override
    protected VoxelShape getShape(BlockState s, BlockGetter l, BlockPos p, CollisionContext c) {
        return Block.box(0, 0, 0, 16, 4, 16);
    }

    @Override
    protected void createBlockStateDefinition(Builder<Block, BlockState> b) {
        b.add(BlockStateProperties.ENABLED);
        PropByDir().values().forEach(b::add);
    }

    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return defaultBlockState()
                .setValue(BlockStateProperties.ENABLED, false)
                .setValue(BlockStateProperties.NORTH, false)
                .setValue(BlockStateProperties.SOUTH, false)
                .setValue(BlockStateProperties.EAST, false)
                .setValue(BlockStateProperties.WEST, false);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos p, BlockState s) {
        return new SolarPanelBE(p, s);
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
        updateOnPlace(state, level, pos, oldState, isMoving);
    }

    @Override
    public void destroy(LevelAccessor l, BlockPos p, BlockState s) {
        updateOnDestroy(l, p, s);
    }

    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block neighborBlock,
            BlockPos neighborPos, boolean movedByPiston) {

        updateOnNeighborChanged(state, level, pos, neighborBlock, neighborPos, movedByPiston);
        super.neighborChanged(state, level, pos, neighborBlock, neighborPos, movedByPiston);
    }

    @Override
    public Map<Direction, BooleanProperty> PropByDir() {
        return  ImmutableMap.copyOf(Util.make(Maps.newEnumMap(Direction.class), (e) -> {
         e.put(Direction.NORTH, BlockStateProperties.NORTH);
         e.put(Direction.EAST, BlockStateProperties.EAST);
         e.put(Direction.SOUTH, BlockStateProperties.SOUTH);
         e.put(Direction.WEST, BlockStateProperties.WEST);
      }));
    }



    @Override
    public Boolean whenConnect(Level level, BlockPos basePos, BlockPos neighborPos, BlockState baseState,
            BlockState neighborState) {
        return neighborState.is(this);
    }

    @Override
    public void appendHoverText(ItemStack i, TooltipContext c, List<Component> t,
            TooltipFlag f) {
        t.add(Component.translatable(Main.ID + "." + zStatic.Blocks.solar_panel));
    }

}
