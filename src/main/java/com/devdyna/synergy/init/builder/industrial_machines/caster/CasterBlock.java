package com.devdyna.synergy.init.builder.industrial_machines.caster;

import java.util.function.Function;

import javax.annotation.Nullable;

import com.devdyna.synergy.api.basebe.block.BlockMenu;
import com.devdyna.synergy.api.beLogic.BucketInteraction;
import com.devdyna.synergy.api.machine.BaseMachineBlock;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

@SuppressWarnings("null")
public class CasterBlock extends BaseMachineBlock implements BucketInteraction {

    public CasterBlock(Properties p) {
        super(p);
    }

    public CasterBlock() {
        this(Properties.of());
    }

    @Override
    @Nullable
    public BlockEntity newBlockEntity(BlockPos arg0, BlockState arg1) {
        return new CasterBE(arg0, arg1);
    }

    @Override
    protected Function<Properties, Block> getFactory() {
        return CasterBlock::new;
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos,
            Player player, InteractionHand hand, BlockHitResult hitResult) {
        return bucketAction(stack, state, level, pos, player, hand, hitResult);
    }

    @Override
    public ItemInteractionResult executeWhenEmpty(ItemStack stack, BlockState state, Level level, BlockPos pos,
            Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (this instanceof BlockMenu bmv)
            bmv.useWithoutItem(state, level, pos, player, hitResult);
        return ItemInteractionResult.SUCCESS;
    }

}
