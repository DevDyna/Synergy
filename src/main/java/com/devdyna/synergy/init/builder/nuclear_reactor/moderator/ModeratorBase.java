package com.devdyna.synergy.init.builder.nuclear_reactor.moderator;

import javax.annotation.Nullable;

import com.devdyna.synergy.Main;
import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.api.BlockAbilities.tooltips.complex.IModerator;
import com.devdyna.synergy.api.utils.LevelUtil;
import com.devdyna.synergy.init.builder.nuclear_reactor.fuel_cell.FuelCellBlock;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.MapColor;

@SuppressWarnings("null")
public abstract class ModeratorBase extends Block implements IModerator {

    public ModeratorBase() {
        super(Properties.of().strength(1.0f).destroyTime(1.0f).sound(SoundType.CHAIN).mapColor(MapColor.METAL));
    }

    @Override
    protected void createBlockStateDefinition(Builder<Block, BlockState> b) {
        b.add(BlockStateProperties.ENABLED);
    }

    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext c) {
        return defaultBlockState().setValue(BlockStateProperties.ENABLED,
                checkForCells(c.getLevel(), c.getClickedPos()));
    }

    public boolean isActive(Level level, BlockPos pos) {
        return level.getBlockState(pos).getValue(BlockStateProperties.ENABLED);
    }

    @Override
    public void onNeighborChange(BlockState state, LevelReader levelrReader, BlockPos pos, BlockPos neighbor) {
        if (levelrReader instanceof Level level)
            level.setBlockAndUpdate(pos, state.setValue(BlockStateProperties.ENABLED,
                    checkForCells(level, pos)));
    }

    private boolean checkForCells(Level level, BlockPos pos) {
        return 0 < LevelUtil.predicateNeighborMatch(level, pos, b -> b instanceof FuelCellBlock);
    }

    // @Override
    // public void appendHoverText(ItemStack i, TooltipContext c, List<Component> t,
    // TooltipFlag f) {

    // if (f.hasShiftDown()) {
    // t.add(conditions());
    // t.add(Component.translatable(Main.ID + "." + zStatic.ReactorStuff.moderator +
    // ".fe")
    // .append("" + FEReducer()));
    // t.add(Component.translatable(Main.ID + "." + zStatic.ReactorStuff.moderator +
    // ".heat")
    // .append("" + HeatReducer()));
    // } else {
    // t.add(Component.translatable(Main.ID + "." +
    // zStatic.ReactorStuff.moderator));
    // t.add(Component.translatable(Main.ID + "." + zStatic.tips.SHIFT));

    // }
    // }

    public Component conditions() {
        return Component.translatable(Main.ID + "." + zStatic.ReactorStuff.moderator + ".condition");
    }

}
