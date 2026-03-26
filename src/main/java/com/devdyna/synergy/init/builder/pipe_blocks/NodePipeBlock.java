package com.devdyna.synergy.init.builder.pipe_blocks;

import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

import com.devdyna.synergy.Main;
import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.api.beLogic.Connectable;
import com.devdyna.synergy.init.types.zBlockTag;
import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item.TooltipContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.PipeBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.MapColor;

@SuppressWarnings("null")
public class NodePipeBlock extends PipeBlock implements Connectable {

    public NodePipeBlock() {
        this(BlockBehaviour.Properties.of());
    }

    public NodePipeBlock(Properties p) {
        super(0.125f, p
                .destroyTime(0.125f)
                .forceSolidOn()
                .sound(SoundType.WOOL)
                .mapColor(MapColor.TERRACOTTA_GRAY));
    }

    @Override
    protected void createBlockStateDefinition(Builder<Block, BlockState> b) {
        PropByDir().values().forEach(b::add);
    }

    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext c) {
        return defaultBlockState()
                .setValue(BlockStateProperties.NORTH, false)
                .setValue(BlockStateProperties.SOUTH, false)
                .setValue(BlockStateProperties.EAST, false)
                .setValue(BlockStateProperties.WEST, false)
                .setValue(BlockStateProperties.UP, false)
                .setValue(BlockStateProperties.DOWN, false);
    }

    @Override
    public Map<Direction, BooleanProperty> PropByDir() {
        return PROPERTY_BY_DIRECTION;
    }

    @Override
    public Boolean whenConnect(@Nullable Level level, BlockPos basePos, BlockPos neighborPos, BlockState baseState,
            BlockState neighborState) {
        return (this.updateWhen(level, basePos, neighborPos, baseState, neighborState)
                || (level != null && level.getBlockEntity(neighborPos) != null)) && !neighborState.isAir();
    }

    @Override
    public Boolean updateWhen(@Nullable Level level, BlockPos basePos, BlockPos neighborPos, BlockState baseState,
            BlockState neighborState) {
        return neighborState.is(zBlockTag.CAN_CONNECT) || neighborState.getBlock() instanceof NodePipeBlock;
    }

    @Override
    protected void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston) {
        updateOnPlace(state, level, pos, oldState, movedByPiston);
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
    protected MapCodec<? extends PipeBlock> codec() {
        return simpleCodec(NodePipeBlock::new);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents,
            TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable(Main.ID + "." + zStatic.Blocks.pipe + ".desc"));
        tooltipComponents.add(Component.translatable(Main.ID + ".safe_building"));
    }

}
