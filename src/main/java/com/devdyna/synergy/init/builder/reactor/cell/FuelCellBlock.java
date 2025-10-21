package com.devdyna.synergy.init.builder.reactor.cell;

import java.util.List;
import java.util.function.Function;

import javax.annotation.Nullable;

import com.devdyna.synergy.Main;
import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.api.coreBE.block.BlockStorage;
import com.devdyna.synergy.utils.LevelUtil;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item.TooltipContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.MapColor;

@SuppressWarnings("null")
public class FuelCellBlock extends BlockStorage {

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
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block neighborBlock,
            BlockPos neighborPos, boolean movedByPiston) {

        level.setBlockAndUpdate(pos,
                state.setValue(CELLS, getCells(level, pos)));
    }

    public int getCells(Level level, BlockPos pos) {
        return LevelUtil.predicateNeighborMatch(level, pos, b -> b instanceof FuelCellBlock);
    }

    @Override
    public void appendHoverText(ItemStack i, TooltipContext c, List<Component> t,
            TooltipFlag f) {
        t.add(Component.translatable(Main.ID + "." +
                zStatic.ReactorStuff.fuel_cell));
    }

    @Override
    @Nullable
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new FuelCellBE(pos, state);
    }

    @Override
    protected Function<Properties, Block> getFactory() {
        return FuelCellBlock::new;
    }

    @Override
    protected void onClickAction(BlockState state, Level level, BlockPos pos, Player player) {
    }

}
