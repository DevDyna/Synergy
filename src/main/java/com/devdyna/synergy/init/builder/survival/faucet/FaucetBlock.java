package com.devdyna.synergy.init.builder.survival.faucet;

import java.util.List;
import javax.annotation.Nullable;

import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.api.basebe.block.TickingBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.network.chat.Component;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item.TooltipContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import static com.devdyna.synergy.Main.ID;

@SuppressWarnings("null")
public class FaucetBlock extends TickingBlock {

    public FaucetBlock(Properties properties) {
        super(properties);
    }

    public FaucetBlock() {
        this(Properties.of().sound(SoundType.METAL).mapColor(MapColor.METAL).instrument(NoteBlockInstrument.BASEDRUM).strength(1F,
                2.25F));
    }

    @Override
    protected void createBlockStateDefinition(Builder<Block, BlockState> b) {
        b.add(BlockStateProperties.HORIZONTAL_FACING, BlockStateProperties.ENABLED);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return checkDir(state).optimize();
    }

    public static VoxelShape checkDir(BlockState s) {
        return switch (s.getValue(BlockStateProperties.HORIZONTAL_FACING)) {
            case NORTH -> north();
            case SOUTH -> south();
            case EAST -> east();
            case WEST -> west();
            default -> Shapes.empty();
        };
    }

    public static VoxelShape north() {
        VoxelShape shape = Shapes.empty();
        shape = Shapes.join(shape, Shapes.box(0.375, 0.375, 0, 0.625, 0.4375, 0.25), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.375, 0.4375, 0, 0.4375, 0.5625, 0.25), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.5625, 0.4375, 0, 0.625, 0.5625, 0.25), BooleanOp.OR);
        return shape;
    }

    public static VoxelShape east() {
        VoxelShape shape = Shapes.empty();
        shape = Shapes.join(shape, Shapes.box(0.75, 0.375, 0.375, 1, 0.4375, 0.625), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.75, 0.4375, 0.375, 1, 0.5625, 0.4375), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.75, 0.4375, 0.5625, 1, 0.5625, 0.625), BooleanOp.OR);
        return shape;
    }

    public static VoxelShape west() {
        VoxelShape shape = Shapes.empty();
        shape = Shapes.join(shape, Shapes.box(0, 0.375, 0.375, 0.25, 0.4375, 0.625), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0, 0.4375, 0.5625, 0.25, 0.5625, 0.625), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0, 0.4375, 0.375, 0.25, 0.5625, 0.4375), BooleanOp.OR);
        return shape;
    }

    public static VoxelShape south() {
        VoxelShape shape = Shapes.empty();
        shape = Shapes.join(shape, Shapes.box(0.375, 0.375, 0.75, 0.625, 0.4375, 1), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.5625, 0.4375, 0.75, 0.625, 0.5625, 1), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.375, 0.4375, 0.75, 0.4375, 0.5625, 1), BooleanOp.OR);
        return shape;
    }

    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext c) {
        return defaultBlockState()
                .setValue(BlockStateProperties.ENABLED, false)
                .setValue(BlockStateProperties.HORIZONTAL_FACING,
                        (c.getClickedFace().getOpposite() == Direction.DOWN
                                || c.getClickedFace().getOpposite() == Direction.UP ? c.getHorizontalDirection()
                                        : c.getClickedFace().getOpposite()));
    }

    @Override
    @Nullable
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new FaucetBE(pos, state);
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level,
            BlockPos pos, Player player,
            InteractionHand hand, BlockHitResult hit) {
        if (level.getBlockEntity(pos) instanceof FaucetBE be) 
            return be.onClick();
        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        if ((Boolean) state.getValue(BlockStateProperties.ENABLED) && random.nextFloat() < 0.25F) {
            makeParticle(state, level, pos, 0.5F);
        }
    }

    private static void makeParticle(BlockState state, LevelAccessor level, BlockPos pos, float alpha) {
        Direction direction = state.getValue(BlockStateProperties.HORIZONTAL_FACING).getOpposite();
        Direction direction1 = direction.getOpposite();
        double d0 = pos.getX() + 0.5 + 0.1 * direction.getStepX() + 0.2 * direction1.getStepX();
        double d1 = pos.getY() + 0.5 + 0.1 * direction.getStepY() + 0.2 * direction1.getStepY();
        double d2 = pos.getZ() + 0.5 + 0.1 * direction.getStepZ() + 0.2 * direction1.getStepZ();
        level.addParticle(new DustParticleOptions(DustParticleOptions.REDSTONE_PARTICLE_COLOR, alpha), d0, d1, d2, 0.0,
                0.0, 0.0);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context,
            List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.translatable(ID + "." + zStatic.Blocks.faucet));
    }

}
