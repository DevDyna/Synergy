package com.devdyna.synergy.init.builder.survival.evaporation_basin;

import java.util.List;
import javax.annotation.Nullable;

import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.api.basebe.block.TickingBlock;
import com.devdyna.synergy.api.beLogic.BucketInteraction;
import com.devdyna.synergy.api.beLogic.FluidClearableTank;
import com.devdyna.synergy.api.beLogic.FluidTooltipWhenEmpty;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item.TooltipContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.fluids.capability.templates.FluidTank;

import static com.devdyna.synergy.Main.ID;

@SuppressWarnings("null")
public class EvaporationBasinBlock extends TickingBlock
        implements BucketInteraction, FluidClearableTank, FluidTooltipWhenEmpty {

    public EvaporationBasinBlock(Properties properties) {
        super(properties);
    }

    public EvaporationBasinBlock() {
        this(Properties.of().mapColor(MapColor.COLOR_ORANGE).instrument(NoteBlockInstrument.BASEDRUM).strength(1F,
                2.25F));
    }

    @Override
    @Nullable
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new EvaporationBasinBE(pos, state);
    }

    @Override
    protected VoxelShape getShape(BlockState s, BlockGetter l, BlockPos p, CollisionContext c) {
        return Block.box(1, 0, 1, 15, 5, 15);
    }

    @Override
    protected void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean movedByPiston) {
        if (state.getBlock() != newState.getBlock())
            if (level.getBlockEntity(pos) instanceof EvaporationBasinBE be) {
                be.drops();
                level.updateNeighbourForOutputSignal(pos, this);
            }
        super.onRemove(state, level, pos, newState, movedByPiston);
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos,
            Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (level.getBlockEntity(pos) instanceof EvaporationBasinBE)
            return bucketAction(stack, state, level, pos, player, hand, hitResult);
        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    @Override
    public ItemInteractionResult executeWhenEmpty(ItemStack stack, BlockState state, Level level, BlockPos pos,
            Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (player.isCrouching())
            return useItemToClear(state, level, pos, player, hitResult);
        else
            return sendFluidTooltip(stack, state, level, pos, player, hand, hitResult);
    }

    @Override
    public FluidTank getFluidTank(BlockEntity be, BlockState state, Level level, BlockPos pos, Player player,
            InteractionHand hand, BlockHitResult hitResult) {
        return (be instanceof EvaporationBasinBE tank) ? tank.getFluidStorage() : null;
    }

    @Override
    public boolean showWhen(BlockEntity be) {
        return (be instanceof EvaporationBasinBE tank) ? tank.getStorage().getStackInSlot(0).isEmpty() : true;
    }

    @Override
    public ItemInteractionResult onTooltipFail(ItemStack stack, BlockState state, Level level, BlockPos pos,
            Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (level.getBlockEntity(pos) instanceof EvaporationBasinBE be)
            return be.itemUseOn(player, level, pos, hand);
        return ItemInteractionResult.FAIL;
    }

    @Override
    public ItemInteractionResult executeWhenNotBucket(ItemStack stack, BlockState state, Level level, BlockPos pos,
            Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (level.getBlockEntity(pos) instanceof EvaporationBasinBE be)
            return be.itemUseOn(player, level, pos, hand);
        return ItemInteractionResult.FAIL;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context,
            List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.translatable(ID + "." + zStatic.Blocks.evaporation_basin));
    }

}
