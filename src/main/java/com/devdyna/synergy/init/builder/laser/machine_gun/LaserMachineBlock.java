package com.devdyna.synergy.init.builder.laser.machine_gun;

import java.util.List;

import javax.annotation.Nullable;

import com.devdyna.synergy.Main;
import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.api.BlockAbilities.tooltips.simple.Rotable;
import com.devdyna.synergy.api.basebe.block.TickingBlock;
import com.devdyna.synergy.init.builder.laser.IBlockLaser;
import com.devdyna.synergy.init.types.zItemTag;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.Item.TooltipContext;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
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
public class LaserMachineBlock extends TickingBlock implements IBlockLaser, Rotable {

    public LaserMachineBlock() {
        super(getProperties);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext c) {
        return this.defaultBlockState()
                .setValue(BlockStateProperties.ENABLED, false)
                .setValue(BlockStateProperties.HORIZONTAL_FACING, c.getHorizontalDirection());
    }

    @Override
    protected VoxelShape getShape(BlockState s, BlockGetter l, BlockPos p, CollisionContext c) {
        return getShape();
    }

    @Override
    protected boolean isPathfindable(BlockState state, PathComputationType pathComputationType) {
        return isPathfindable();
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> b) {
        b.add(BlockStateProperties.HORIZONTAL_FACING, BlockStateProperties.ENABLED);
    }

    @Override
    protected InteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos,
            Player player, InteractionHand hand, BlockHitResult hitResult) {

        if (stack.is(zItemTag.COLOR_APPLICABLE)) {

            if (level.getBlockEntity(pos) != null && level.getBlockEntity(pos) instanceof LaserMachineBE laser) {

                var inverse = player.getItemInHand(InteractionHand.OFF_HAND).is(zItemTag.DYE_RESET);

                if (stack.is(zItemTag.DYE_RESET)) {
                    laser.decRed();
                    laser.decGreen();
                    laser.decBlue();
                }

                if (stack.is(zItemTag.DYE_MAX)) {
                    laser.incRed();
                    laser.incGreen();
                    laser.incBlue();
                }

                if (stack.is(zItemTag.DYE_RED))
                    laser.tweakRed(!inverse);

                if (stack.is(zItemTag.DYE_GREEN))
                    laser.tweakGreen(!inverse);

                if (stack.is(zItemTag.DYE_BLUE))
                    laser.tweakBlue(!inverse);

                level.playSound(player, pos, SoundEvents.SHULKER_AMBIENT, SoundSource.PLAYERS, 0.15F, 1.75F);

            }

            return InteractionResult.SUCCESS;
        }

        return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
    }

    @Override
    protected InteractionResult useWithoutItem(
            BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {

        if (!level.isClientSide())
            level.setBlockAndUpdate(pos,
                    state.setValue(BlockStateProperties.HORIZONTAL_FACING,
                            (player.isShiftKeyDown()
                                    ? state.getValue(BlockStateProperties.HORIZONTAL_FACING)
                                            .getCounterClockWise(Direction.Axis.Y)
                                    : state.getValue(BlockStateProperties.HORIZONTAL_FACING)
                                            .getClockWise(Direction.Axis.Y))));

        return InteractionResult.SUCCESS_SERVER;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos p, BlockState s) {
        return new LaserMachineBE(p, s);
    }

    // @Override
    // public void appendHoverText(ItemStack i, TooltipContext c, List<Component> t,
    // TooltipFlag f) {
    // t.add(Component.translatable(Main.ID + "." + zStatic.Lazers.machine_gun));
    // t.add(Component.translatable(Main.ID + ".laser.rotate_by_click"));

    // }

}
