package com.devdyna.synergy.init.builder.laser.laser_rotor;

import java.util.List;

import javax.annotation.Nullable;

import com.devdyna.synergy.Main;
import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.api.basebe.block.TickingBlock;
import com.devdyna.synergy.init.builder.laser.IBlockLaser;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item.TooltipContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

@SuppressWarnings("null")
public class LaserRotorBlock extends TickingBlock implements IBlockLaser {

    public LaserRotorBlock(Properties properties) {
        super(properties);
    }

    public LaserRotorBlock() {
        this(getProperties);
    }

    @Override
    @Nullable
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new LaserRotorBE(pos, state);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext c) {
        return this.defaultBlockState()
                .setValue(BlockStateProperties.ENABLED, false)
                .setValue(BlockStateProperties.HORIZONTAL_FACING, c.getHorizontalDirection().getOpposite());
    }

    @Override
    protected InteractionResult useWithoutItem(
            BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {

        if (!level.isClientSide)
            rotate(pos, state, player.isShiftKeyDown(), level);

        return InteractionResult.sidedSuccess(level.isClientSide);
    }

    public static void rotate(BlockPos pos, BlockState state, boolean inverse, Level level) {
        level.setBlockAndUpdate(pos,
                state.setValue(BlockStateProperties.HORIZONTAL_FACING,
                        (inverse
                                ? state.getValue(BlockStateProperties.HORIZONTAL_FACING)
                                        .getCounterClockWise(Direction.Axis.Y)
                                : state.getValue(BlockStateProperties.HORIZONTAL_FACING)
                                        .getClockWise(Direction.Axis.Y))));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> b) {
        b.add(BlockStateProperties.HORIZONTAL_FACING, BlockStateProperties.ENABLED);
    }

    @Override
    protected boolean isPathfindable(BlockState state, PathComputationType pathComputationType) {
        return isPathfindable();
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return getShape();
    }

    @Override
    public void appendHoverText(ItemStack i, TooltipContext c, List<Component> t,
            TooltipFlag f) {
        t.add(Component.translatable(Main.ID + "." + zStatic.Lazers.rotor));
        t.add(Component.translatable(Main.ID + ".laser.rotate_by_click"));

    }

}
