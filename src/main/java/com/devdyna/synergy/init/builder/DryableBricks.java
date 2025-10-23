package com.devdyna.synergy.init.builder;

import java.util.List;

import com.devdyna.synergy.Main;
import com.devdyna.synergy.utils.LevelUtil;
import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Item.TooltipContext;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.items.ItemHandlerHelper;

@SuppressWarnings("null")
public class DryableBricks extends HorizontalDirectionalBlock {

    public final static IntegerProperty DRY_STAGE = IntegerProperty.create("stage", 0, 5);
    public final static BooleanProperty DRIED = BooleanProperty.create("dried");
    public final static BooleanProperty WET = BooleanProperty.create("wet");

    public DryableBricks(Properties p) {
        super(p.randomTicks().instabreak().pushReaction(PushReaction.DESTROY));
        this.registerDefaultState(
                defaultBlockState()
                        .setValue(FACING, Direction.NORTH)
                        .setValue(DRIED, false)
                        .setValue(WET, false)
                        .setValue(DRY_STAGE, 0)
        );
    }

    @Override
    protected VoxelShape getShape(BlockState s, BlockGetter l, BlockPos p, CollisionContext c) {
        return (s.getValue(FACING).getAxis() == Axis.X) ? Block.box(3.5, 0, 5.5, 12.5, 2, 10.5)
                : Block.box(5.5, 0, 3.5, 10.5, 2, 12.5);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext c) {
        return defaultBlockState()
                .setValue(FACING, c.getHorizontalDirection())
                .setValue(DRIED, false)
                .setValue(WET, getConditions(c.getLevel(), c.getClickedPos().above()))
                .setValue(DRY_STAGE, 0);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> b) {
        b.add(FACING, DRIED, DRY_STAGE, WET);
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {

        level.setBlockAndUpdate(pos,
                state.setValue(WET,
                        !state.getValue(DRIED)
                                && getConditions(level, pos)));

        if (state.getValue(WET)) {
            if (state.getValue(DRY_STAGE) != 0)
                level.setBlockAndUpdate(pos, state.setValue(DRY_STAGE, state.getValue(DRY_STAGE) - 1));
        } else {
            if (LevelUtil.chance(25, level) && state.getValue(DRY_STAGE) != 5)
                level.setBlockAndUpdate(pos, state.setValue(DRY_STAGE, state.getValue(DRY_STAGE) + 1));
        }

        if (state.getValue(DRY_STAGE) == 5)
            level.setBlockAndUpdate(pos, state.setValue(DRIED, true));
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        if (LevelUtil.chance(15, level) && isRandomlyTicking(state))
            level.addParticle(ParticleTypes.CLOUD, pos.getX() + 0.5, pos.getY() + 0.25, pos.getZ() + 0.5,
                    0, 0, 0);
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        return Block.canSupportCenter(level, pos.below(), Direction.UP);
    }

    @Override
    protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
        return simpleCodec((p) -> this);
    }

    @Override
    protected boolean isRandomlyTicking(BlockState state) {
        return !state.getValue(DRIED);
    }

    public static boolean getConditions(Level level, BlockPos pos) {
        return (!level.getBiome(pos).is(Tags.Biomes.IS_DRY) || level.isRaining() || !level.isDay()
                || !level.canSeeSkyFromBelowWater(pos.above()));
    }

    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block neighborBlock,
            BlockPos neighborPos, boolean movedByPiston) {
        if (level.isClientSide)
            return;

        if (!Block.canSupportCenter(level, pos.below(), Direction.UP)) {
            dropResources(state, level, pos);
            level.removeBlock(pos, false);
        }
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player,
            BlockHitResult hitResult) {

        if (!level.isClientSide && state.getValue(DRIED)) {
            level.removeBlock(pos, false);
            Block.getDrops(state, (ServerLevel) level, pos, null)
                    .forEach(i -> ItemHandlerHelper.giveItemToPlayer(player, i));
            return InteractionResult.SUCCESS;
        } else
            return super.useWithoutItem(state, level, pos, player, hitResult);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents,
            TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable(Main.ID + ".disabled"));
    }

}
