package com.devdyna.synergy.init.builder.laser;

import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

import com.devdyna.synergy.Main;
import com.devdyna.synergy.zStatic;
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
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

@SuppressWarnings("null")
public class LaserMirrorBlock extends Block implements IBlockLaser {

    public LaserMirrorBlock() {
        super(getProperties);
    }

    @Override
    public boolean canConnectRedstone(BlockState state, BlockGetter world, BlockPos pos, Direction side) {
        return side.getAxis().isHorizontal();
    }

    @Override
    public boolean isPathfindable() {
        return isPathfindable();
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return getShape();
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext c) {
        return this.defaultBlockState()
                .setValue(BlockStateProperties.INVERTED, false);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> b) {
        b.add(BlockStateProperties.INVERTED);
    }

    private static final Map<BlockPos, Boolean> poweredMap = new WeakHashMap<>();

    @Override
    public void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos fromPos,
            boolean isMoving) {
        boolean powered = level.hasNeighborSignal(pos);
        boolean wasPowered = poweredMap.getOrDefault(pos, false);

        if (powered && !wasPowered) {
            level.setBlockAndUpdate(pos, state.cycle(BlockStateProperties.INVERTED));
        }

        poweredMap.put(pos, powered);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player,
            BlockHitResult hitResult) {
        level.setBlockAndUpdate(pos, level.getBlockState(pos).cycle(BlockStateProperties.INVERTED));
        return InteractionResult.SUCCESS;
    }

    public Direction getMirrorDir(Direction dir, BlockState state) {
        return switch (state.getValue(BlockStateProperties.INVERTED) ? dir
                : dir.getOpposite()) {
            case Direction.NORTH -> Direction.EAST;
            case Direction.SOUTH -> Direction.WEST;
            case Direction.EAST -> Direction.NORTH;
            case Direction.WEST -> Direction.SOUTH;
            default -> null;
        };
    }

    @Override
    public void appendHoverText(ItemStack i, TooltipContext c, List<Component> t,
            TooltipFlag f) {
        t.add(Component.translatable(Main.ID + "." + zStatic.Lazers.mirror));
        t.add(Component.translatable(Main.ID + ".laser.rotate_by_click"));
    }

}
