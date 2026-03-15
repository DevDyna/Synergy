package com.devdyna.synergy.init.builder.magic.entity_watcher;

import static com.devdyna.synergy.Main.ID;

import java.util.List;

import javax.annotation.Nullable;

import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.api.basebe.block.TickingBlock;
import com.devdyna.synergy.init.types.zItemTag;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item.TooltipContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

@SuppressWarnings("null")
public class EntityWatcherBlock extends TickingBlock {

    public static final BooleanProperty PLAYER_FILTER = BooleanProperty.create("filter_player");

    public EntityWatcherBlock(Properties properties) {
        super(properties);
    }

    public EntityWatcherBlock() {
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
        b.add(BlockStateProperties.POWER, PLAYER_FILTER);
    }

    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext c) {
        return defaultBlockState()
                .setValue(PLAYER_FILTER, false)
                .setValue(BlockStateProperties.POWER, 0);
    }

    @Override
    protected VoxelShape getShape(BlockState s, BlockGetter l, BlockPos p, CollisionContext c) {
        return Block.box(4, 4, 4, 12, 12, 12);
    }

    @Override
    @Nullable
    public BlockEntity newBlockEntity(BlockPos p, BlockState s) {
        return new EntityWatcherBE(p, s);
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos,
            Player player, InteractionHand hand, BlockHitResult hitResult) {

        if (stack.is(zItemTag.ENTITY_WATCHER_TWEAK)) {
            level.setBlockAndUpdate(pos,
                    state.setValue(PLAYER_FILTER, !state.getValue(PLAYER_FILTER)));
            return ItemInteractionResult.SUCCESS;
        }

        return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
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
        tooltip.add(Component.translatable(ID + "." + zStatic.Blocks.entity_watcher));
    }

}
