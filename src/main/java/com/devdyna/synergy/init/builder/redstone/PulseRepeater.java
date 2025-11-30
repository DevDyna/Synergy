package com.devdyna.synergy.init.builder.redstone;

import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

import com.devdyna.synergy.Main;
import com.devdyna.synergy.zStatic;
import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item.TooltipContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DiodeBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;

@SuppressWarnings("null")
public class PulseRepeater extends DiodeBlock {

    private static final Map<BlockPos, Boolean> poweredMap = new WeakHashMap<>();
    public static final IntegerProperty DELAY = BlockStateProperties.DELAY;

    public PulseRepeater(Properties properties) {
        super(properties);
        this.registerDefaultState(
                this.stateDefinition
                        .any()
                        .setValue(FACING, Direction.NORTH)
                        .setValue(DELAY, 1)
                        .setValue(POWERED, false));
    }

    public PulseRepeater() {
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
        return simpleCodec(PulseRepeater::new);
    }

    @Override
    protected boolean sideInputDiodesOnly() {
        return true;
    }

    @Override
    protected int getDelay(BlockState state) {
        return state.getValue(DELAY) * 2;
    }

    @Override
    public boolean canConnectRedstone(BlockState state, BlockGetter world, BlockPos pos, Direction side) {
        return state.getValue(FACING).equals(side) ||
                state.getValue(FACING).getOpposite().equals(side);
    }

    @Override
    protected boolean shouldTurnOn(Level level, BlockPos pos, BlockState state) {
        boolean powered = getInputSignal(level, pos, state) > 0;
        boolean wasPowered = poweredMap.getOrDefault(pos, false);

        if (powered && !wasPowered) {
            poweredMap.put(pos, powered);
            return true;
        }

        poweredMap.put(pos, powered);
        return false;
    }

    @Override
    public void appendHoverText(ItemStack i, TooltipContext c, List<Component> t,
            TooltipFlag f) {
        t.add(Component.translatable(Main.ID + "." + zStatic.Blocks.pulse_repeater));
    }

}
