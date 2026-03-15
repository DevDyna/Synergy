package com.devdyna.synergy.api.blockfactories.watchers;

import static com.devdyna.synergy.Main.ID;

import java.util.List;

import javax.annotation.Nullable;

import com.devdyna.synergy.api.basebe.block.TickingBlock;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.Item.TooltipContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

@SuppressWarnings("null")
public abstract class BaseWatcherBlock extends TickingBlock {

    public BaseWatcherBlock(Properties properties) {
        super(properties);
    }

    public BaseWatcherBlock() {
        this(Properties.of()
                .mapColor(MapColor.COLOR_GREEN)
                .sound(SoundType.AMETHYST)
                .noOcclusion()
                .strength(5.0F)
                .lightLevel((s) -> {
                    return 10;
                }).pushReaction(PushReaction.BLOCK));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> b) {
        b.add(BlockStateProperties.POWER);
    }

    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext c) {
        return defaultBlockState()
                .setValue(BlockStateProperties.POWER, 0);
    }

    @Override
    protected VoxelShape getShape(BlockState s, BlockGetter l, BlockPos p, CollisionContext c) {
        return Block.box(4, 4, 4, 12, 12, 12);
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.INVISIBLE;
    }

    @Override
    public boolean canConnectRedstone(BlockState s, BlockGetter l, BlockPos p, @Nullable Direction d) {
        return true;
    }

    @Override
    protected int getSignal(BlockState s, BlockGetter l, BlockPos p, Direction d) {
        return s.getValue(BlockStateProperties.POWER);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context,
            List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.translatable(ID + ".watcher"));
    }

}
