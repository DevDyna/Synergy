package com.devdyna.synergy.api.basebe.block;

import static com.devdyna.synergy.Main.ID;

import com.devdyna.synergy.api.basebe.be.BETank;
import com.devdyna.synergy.api.beLogic.BucketInteraction;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

@SuppressWarnings("null")
public abstract class BlockTank extends Block implements BucketInteraction, EntityBlock {

    public BlockTank(Properties properties) {
        super(properties.noOcclusion());
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos,
            Player player, InteractionHand hand, BlockHitResult hitResult) {
        return bucketAction(stack, state, level, pos, player, hand, hitResult);
    }

    @Override
    public ItemInteractionResult executeWhenEmpty(ItemStack stack, BlockState state, Level level, BlockPos pos,
            Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (level.getBlockEntity(pos) instanceof BETank tank) {
            var fluid = tank.getFluidStorage().getFluid();
            if (fluid.isEmpty())
                player.displayClientMessage(Component.translatable(ID + ".tank_interact.empty"), true);
            else
                player.displayClientMessage(
                        Component.literal(
                                fluid.getFluidType().getDescription().getString() + " : " + fluid.getAmount() + "mb"),
                        true);

            return ItemInteractionResult.sidedSuccess(level.isClientSide);
        }
        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

}
