package com.devdyna.synergy.init.builder.reactor.cell;

import java.util.List;

import javax.annotation.Nullable;

import com.devdyna.synergy.Main;
import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.utils.LevelUtil;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item.TooltipContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.MapColor;

@SuppressWarnings("null")
public class FuelCellBlock extends Block {

    // public final static IntegerProperty MODERATORS =
    // IntegerProperty.create("moderators", 0, 6);
    public final static IntegerProperty CELLS = IntegerProperty.create("cells", 0, 6);

    public FuelCellBlock() {
        super(Properties.of().strength(1.0f).destroyTime(1.0f).sound(SoundType.CHAIN).mapColor(MapColor.METAL));
    }

    @Override
    protected void createBlockStateDefinition(Builder<Block, BlockState> b) {
        b.add(CELLS);
    }

    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext c) {
        return defaultBlockState()
                // .setValue(MODERATORS, getModerators(c.getLevel(), c.getClickedPos()))
                .setValue(CELLS, getCells(c.getLevel(), c.getClickedPos()));
    }

    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block neighborBlock,
            BlockPos neighborPos, boolean movedByPiston) {

        level.setBlockAndUpdate(pos,
                state
                        // .setValue(MODERATORS, getModerators(level, pos))
                        .setValue(CELLS, getCells(level, pos)));
    }

    public int getCells(Level level, BlockPos pos) {
        return LevelUtil.predicateNeighborMatch(level, pos, b -> b instanceof FuelCellBlock);
    }

    // public int getModerators(Level level, BlockPos pos) {
    // return LevelUtil.predicateNeighborMatch(level, pos, b -> b instanceof
    // ModeratorBase);
    // }

    public float cellsFEMultiplier() {
        return 0.25F;
    }

    public float cellsHeatMultiplier() {
        return 0.25F;
    }

    @Override
    public void appendHoverText(ItemStack i, TooltipContext c, List<Component> t,
            TooltipFlag f) {

        t.add(Component.translatable(Main.ID + "." +
                zStatic.ReactorStuff.fuel_cell));
    }

}
