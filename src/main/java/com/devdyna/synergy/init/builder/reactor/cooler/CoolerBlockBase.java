package com.devdyna.synergy.init.builder.reactor.cooler;

import java.util.List;

import javax.annotation.Nullable;

import com.devdyna.synergy.Main;
import com.devdyna.synergy.zStatic;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item.TooltipContext;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.MapColor;

@SuppressWarnings("null")
public abstract class CoolerBlockBase extends Block {

    public CoolerBlockBase() {
        super(Properties.of().strength(1.0f).destroyTime(1.0f).sound(SoundType.CHAIN).mapColor(MapColor.METAL));
    }

    @Override
    protected void createBlockStateDefinition(Builder<Block, BlockState> b) {
        b.add(BlockStateProperties.ENABLED);
    }

    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext c) {
        return defaultBlockState().setValue(BlockStateProperties.ENABLED, activeWhen(defaultBlockState(), c.getLevel(),
                c.getClickedPos()));
    }

    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block neighborBlock,
            BlockPos neighborPos, boolean movedByPiston) {
        level.setBlockAndUpdate(pos, state.setValue(BlockStateProperties.ENABLED, activeWhen(state, level, pos)));
    }

    /**
     * Override to set OFF-ON cooling condition
     */
    public abstract boolean activeWhen(BlockState state, Level level, BlockPos pos);

    /**
     * Override to set ON cooling <br/><br/>
     * <strong>NOTE:</strong> This should return a negative value <i>else will increase heat value</i>!
     */
    public abstract int getActiveCooling();

    // /**
    // * Override to set OFF cooling
    // */
    public int getBaseCooling() {
        return 0;
    }

    public abstract Component conditions();

    public boolean isActive(Level level,BlockPos pos){
        return level.getBlockState(pos).getValue(BlockStateProperties.ENABLED);
    }

    @Override
    public void appendHoverText(ItemStack i, TooltipContext c, List<Component> t,
            TooltipFlag f) {

        t.add(Component.translatable(Main.ID + "." +
                zStatic.ReactorStuff.cooler+".desc"));

        if (f.hasShiftDown()) {
            t.add(conditions());
            t.add(Component.translatable(Main.ID + "." + zStatic.ReactorStuff.cooler + ".off")
                    .append("" + getBaseCooling()));
            t.add(Component.translatable(Main.ID + "." + zStatic.ReactorStuff.cooler + ".on")
                    .append("" + getActiveCooling()));
        } else {
            t.add(Component.translatable(Main.ID + "." + zStatic.tips.SHIFT));
        }
    }

}
