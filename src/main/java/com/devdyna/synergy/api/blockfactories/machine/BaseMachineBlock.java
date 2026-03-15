package com.devdyna.synergy.api.blockfactories.machine;

import javax.annotation.Nullable;

import com.devdyna.synergy.api.basebe.block.BlockMenu;
import com.devdyna.synergy.config.Common;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.MapColor;

@SuppressWarnings("null")
public abstract class BaseMachineBlock extends BlockMenu {

    public static DirectionProperty FACING = BlockStateProperties.FACING;
    public static BooleanProperty ENABLED = BlockStateProperties.ENABLED;

    public BaseMachineBlock(Properties p) {
        super(p
                .strength(1.0f)
                .destroyTime(1.0f)
                .sound(SoundType.METAL)
                .mapColor(MapColor.METAL));
    }

    @Override
    protected void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean movedByPiston) {

        if (state.getBlock() != newState.getBlock())
            if (level.getBlockEntity(pos) instanceof BaseMachineBE be) {

                be.dropItems();

                level.updateNeighbourForOutputSignal(pos, this);
            }

        super.onRemove(state, level, pos, newState, movedByPiston);
    }

    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext c) {
        return defaultBlockState().setValue(FACING,
                Common.ENABLE_SHIFT_INVERT_MACHINE_PLACEMENT
                        .get() ? (c.getPlayer().isCrouching() ? c.getNearestLookingDirection()
                                : c.getNearestLookingDirection().getOpposite())
                                : c.getNearestLookingDirection().getOpposite()
        )
                .setValue(ENABLED, false);
    }

    @Override
    protected void createBlockStateDefinition(Builder<Block, BlockState> b) {
        b.add(FACING, ENABLED);
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        if (state.getValue(ENABLED)) {
            Direction dir = state.getValue(FACING);
            Axis axis = dir.getAxis();

            double x = pos.getX() + 0.5;
            double y = pos.getY() + random.nextDouble() * 6.0 / 16.0;
            double z = pos.getZ() + 0.5;

            double spread = random.nextDouble() * 0.6 - 0.3;
            double offX = axis == Direction.Axis.X ? dir.getStepX() * 0.52 : spread;
            double offZ = axis == Direction.Axis.Z ? dir.getStepZ() * 0.52 : spread;

            level.addParticle(ParticleTypes.SMOKE, x + offX, y, z + offZ, 0, 0, 0);

        }
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level l, BlockState s,
            BlockEntityType<T> ty) {
        return (lvl, pos, b, t) -> {

            if (l == null)
                return;

            if (t instanceof BaseMachineBE be) {
                be.tick();
            }
        };
    }

}
