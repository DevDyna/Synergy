package com.devdyna.synergy.api.node_pipe.builder;

import java.util.List;

import javax.annotation.Nullable;

import com.devdyna.synergy.Main;
import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.api.basebe.be.TickingBE;
import com.devdyna.synergy.init.builder.pipe_blocks.NodePipeBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Item.TooltipContext;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

@SuppressWarnings("null")
public abstract class NodeBaseBlock extends NodePipeBlock implements EntityBlock {

    public static final DirectionProperty FACING = BlockStateProperties.FACING;

    public static final VoxelShape DOWN_NODE_SHAPE = Shapes.box(0.0625, 0, 0.0625, 0.9375, 0.1875, 0.9375);
    public static final VoxelShape UP_NODE_SHAPE = Shapes.box(0.0625, 0.8125, 0.0625, 0.9375, 1, 0.9375);
    public static final VoxelShape NORTH_NODE_SHAPE = Shapes.box(0.0625, 0.0625, 0, 0.9375, 0.9375, 0.1875);
    public static final VoxelShape SOUTH_NODE_SHAPE = Shapes.box(0.0625, 0.0625, 0.8125, 0.9375, 0.9375, 1);
    public static final VoxelShape WEST_NODE_SHAPE = Shapes.box(0, 0.0625, 0.0625, 0.1875, 0.9375, 0.9375);
    public static final VoxelShape EAST_NODE_SHAPE = Shapes.box(0.8125, 0.0625, 0.0625, 1, 0.9375, 0.9375);

    public NodeBaseBlock() {
        super();
    }

    @Override
    protected void createBlockStateDefinition(Builder<Block, BlockState> b) {
        b.add(FACING);
        super.createBlockStateDefinition(b);
    }

    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext c) {
        return defaultBlockState()
                .setValue(BlockStateProperties.FACING, c.getClickedFace().getOpposite())
                .setValue(BlockStateProperties.NORTH, false)
                .setValue(BlockStateProperties.SOUTH, false)
                .setValue(BlockStateProperties.EAST, false)
                .setValue(BlockStateProperties.WEST, false)
                .setValue(BlockStateProperties.UP, false)
                .setValue(BlockStateProperties.DOWN, false);
    }

    @Override
    protected VoxelShape getShape(BlockState s, BlockGetter l, BlockPos p, CollisionContext c) {
        VoxelShape model = super.getShape(s, l, p, c);

        model = switch (s.getValue(FACING)) {
            case Direction.DOWN -> Shapes.or(model, DOWN_NODE_SHAPE);
            case Direction.UP -> Shapes.or(model, UP_NODE_SHAPE);
            case Direction.NORTH -> Shapes.or(model, NORTH_NODE_SHAPE);
            case Direction.SOUTH -> Shapes.or(model, SOUTH_NODE_SHAPE);
            case Direction.WEST -> Shapes.or(model, WEST_NODE_SHAPE);
            case Direction.EAST -> Shapes.or(model, EAST_NODE_SHAPE);
        };

        return model.optimize();
    }

    @Override
    protected void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean movedByPiston) {

        if (state.getBlock() != newState.getBlock())
            if (level.getBlockEntity(pos) instanceof NodeBaseBE be) {

                be.drops();

                level.updateNeighbourForOutputSignal(pos, this);
            }

        super.onRemove(state, level, pos, newState, movedByPiston);
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos,
            Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (level.getBlockEntity(pos) instanceof NodeBaseBE be)
            return be.itemUseOn(player, level, pos, hand);
        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    // @Override
    // protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state,
    // Level level, BlockPos pos,
    // Player player, InteractionHand hand, BlockHitResult hitResult) {

    // var be = (NodeBaseBE) level.getBlockEntity(pos);

    // if (stack.is(zItems.NODE_SPEED_UPGRADE) && be.getSpeedUpgrades() <
    // Common.MAX_NODE_SPEED_UPGRADES.get()) {

    // level.playSound(player, pos, SoundEvents.AXE_SCRAPE, SoundSource.BLOCKS, 1f,
    // 0.1f);

    // if (level.isClientSide())
    // return ItemInteractionResult.FAIL;

    // stack.shrink(1);
    // be.addSpeedUpgrade();
    // player.swing(hand);
    // return ItemInteractionResult.SUCCESS;
    // }

    // if (stack.is(zItems.NODE_STACK_UPGRADE) && be.getStackUpgrades() <
    // Common.MAX_NODE_STACK_UPGRADES.get()) {

    // level.playSound(player, pos, SoundEvents.AXE_SCRAPE, SoundSource.BLOCKS, 1f,
    // 0.1f);

    // if (level.isClientSide())
    // return ItemInteractionResult.FAIL;

    // stack.shrink(1);
    // be.addStackUpgrade();
    // player.swing(hand);
    // return ItemInteractionResult.SUCCESS;
    // }

    // return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
    // }

    // @Override
    // protected void onRemove(BlockState state, Level level, BlockPos pos,
    // BlockState newState, boolean movedByPiston) {
    // var be = (NodeBaseBE) level.getBlockEntity(pos);
    // if (be.getSpeedUpgrades() > 0 || be.getStackUpgrades() > 0)
    // if (state.getBlock() != newState.getBlock()) {
    // var inv = new SimpleContainer(2);
    // if (be.getSpeedUpgrades() > 0)
    // inv.addItem(x.item(zItems.NODE_SPEED_UPGRADE, be.getSpeedUpgrades()));
    // if (be.getStackUpgrades() > 0)
    // inv.addItem(x.item(zItems.NODE_STACK_UPGRADE, be.getStackUpgrades()));
    // Containers.dropContents(level, pos, inv);
    // level.updateNeighbourForOutputSignal(pos, this);
    // }

    // super.onRemove(state, level, pos, newState, movedByPiston);
    // }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level l, BlockState s,
            BlockEntityType<T> ty) {
        return (lvl, pos, b, t) -> {
            if (t instanceof TickingBE be) {

                if (l == null)
                    return;

                be.tickBoth();
                if (l.isClientSide())
                    be.tickClient();
                else
                    be.tickServer();
            }
        };
    }

    @Override
    public void appendHoverText(ItemStack i, TooltipContext c, List<Component> t,
            TooltipFlag f) {
        t.add(Component.translatable(Main.ID + "." + zStatic.Blocks.pipe + ".extend"));
    }
}
