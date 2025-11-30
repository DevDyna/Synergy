package com.devdyna.synergy.init.builder.redstone;

import java.util.List;

import com.devdyna.synergy.Main;
import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.utils.LevelUtil;
import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item.TooltipContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DiodeBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.ticks.TickPriority;

@SuppressWarnings("null")
public class RecursiveRepeater extends DiodeBlock {

    public static final IntegerProperty DELAY = BlockStateProperties.DELAY;

    public RecursiveRepeater(Properties properties) {
        super(properties.instabreak().sound(SoundType.STONE).pushReaction(PushReaction.DESTROY));
        this.registerDefaultState(
                this.stateDefinition
                        .any()
                        .setValue(FACING, Direction.NORTH)
                        .setValue(DELAY, 1)
                        .setValue(POWERED, false));
    }

    public RecursiveRepeater() {
        this(Properties.of());
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player,
            BlockHitResult hitResult) {
        if (!player.getAbilities().mayBuild) {
            return InteractionResult.PASS;
        } else {
            level.setBlock(pos, state.cycle(DELAY), 3);
            return InteractionResult.sidedSuccess(level.isClientSide);
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, POWERED, DELAY);
    }

    @Override
    protected MapCodec<? extends DiodeBlock> codec() {
        return simpleCodec(RecursiveRepeater::new);
    }

    @Override
    public boolean canConnectRedstone(BlockState state, BlockGetter world, BlockPos pos, Direction side) {
        return state.getValue(FACING).equals(side) ||
                state.getValue(FACING).getOpposite().equals(side);
    }

    @Override
    protected int getDelay(BlockState state) {
        return state.getValue(DELAY) * 4;
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        if (state.getValue(POWERED))
            LevelUtil.addRepeaterRedstoneParticles(level, pos, state.getValue(FACING), state.getValue(DELAY));
    }

    @Override
    protected boolean shouldTurnOn(Level level, BlockPos pos, BlockState state) {
        Direction input = state.getValue(FACING);
        return level.getSignal(pos.relative(input), input) > 0;
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        Direction input = state.getValue(FACING);
        boolean powered = level.getSignal(pos.relative(input), input) > 0;

        if (!powered) {
            if (state.getValue(POWERED)) 
                level.setBlock(pos, state.setValue(POWERED, false), 2);
            
            return;
        }

        boolean flag = state.getValue(POWERED);
        level.setBlock(pos, state.setValue(POWERED, !flag), 2);

        level.scheduleTick(pos, this, this.getDelay(state), TickPriority.VERY_HIGH);
    }

    @Override
    public void appendHoverText(ItemStack i, TooltipContext c, List<Component> t,
            TooltipFlag f) {
        t.add(Component.translatable(Main.ID + "." + zStatic.Blocks.recursive_repeater));
    }
}
