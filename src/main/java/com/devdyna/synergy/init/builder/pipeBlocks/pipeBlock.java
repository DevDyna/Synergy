package com.devdyna.synergy.init.builder.pipeBlocks;

import java.util.List;

import javax.annotation.Nullable;

import com.devdyna.synergy.Main;
import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.api.pipe.pipeType;

import net.minecraft.core.BlockPos;
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
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class pipeBlock extends Block implements pipeType {

    public pipeBlock() {
        super(BlockBehaviour.Properties.of().destroyTime(0.125f).forceSolidOn()
                .sound(SoundType.WOOL).mapColor(MapColor.TERRACOTTA_GRAY));
    }

    @Override
    protected void createBlockStateDefinition(Builder<Block, BlockState> b) {
        pipeType.PipeStateDefinition(b);
    }

    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext c) {
        return pipeType.updatePipeOnPlace(defaultBlockState(), c.getLevel(), c.getClickedPos());
    }

    @Override
    protected VoxelShape getShape(BlockState s, BlockGetter l, BlockPos p, CollisionContext c) {
        return pipeType.getPipeBaseShape(s);
    }

    @Override
    public void destroy(LevelAccessor l, BlockPos p, BlockState s) {
        pipeType.onDestroyPipe(s, (Level) l, p);
    }

    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block neighborBlock,
            BlockPos neighborPos, boolean movedByPiston) {
        level.setBlockAndUpdate(pos, pipeType.updatePipeOnPlace(state, level, pos));
        super.neighborChanged(state, level, pos, neighborBlock, neighborPos, movedByPiston);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents,
            TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable(Main.ID + "." + zStatic.PipeStuff.pipe + ".desc"));
        tooltipComponents.add(Component.translatable(Main.ID + ".safe_building"));
    }

}
