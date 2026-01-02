package com.devdyna.synergy.init.builder.nuclear_reactor.fuel_cell;

import java.util.function.Function;

import javax.annotation.Nullable;

import com.devdyna.synergy.api.basebe.block.MachineBlock;
import com.devdyna.synergy.api.utils.LevelUtil;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.MapColor;

@SuppressWarnings("null")
public class FuelCellBlock extends MachineBlock {

    public final static IntegerProperty CELLS = IntegerProperty.create("cells", 0, 6);

    public FuelCellBlock() {
        super(Properties.of().strength(1.0f).destroyTime(1.0f).sound(SoundType.CHAIN).mapColor(MapColor.METAL));
    }

    public FuelCellBlock(Properties p) {
        this();
    }

    @Override
    protected void createBlockStateDefinition(Builder<Block, BlockState> b) {
        b.add(CELLS, BlockStateProperties.ENABLED);
    }

    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext c) {
        return defaultBlockState()
                .setValue(CELLS, getCells(c.getLevel(), c.getClickedPos()))
                .setValue(BlockStateProperties.ENABLED, false);
    }

    @Override
    public void onNeighborChange(BlockState state, LevelReader levelrReader, BlockPos pos, BlockPos neighbor) {
        if (levelrReader instanceof Level level)
            level.setBlockAndUpdate(pos,
                    state.setValue(CELLS, getCells(level, pos)));
    }

    public int getCells(Level level, BlockPos pos) {
        return LevelUtil.predicateNeighborMatch(level, pos, b -> b instanceof FuelCellBlock);
    }

    // @Override
    // public void appendHoverText(ItemStack i, TooltipContext c, List<Component> t,
    // TooltipFlag f) {
    // t.add(Component.translatable(Main.ID + "." +
    // zStatic.ReactorStuff.fuel_cell));
    // }

    @Override
    @Nullable
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new FuelCellBE(pos, state);
    }

    @Override
    protected Function<Properties, Block> getFactory() {
        return FuelCellBlock::new;
    }

}
