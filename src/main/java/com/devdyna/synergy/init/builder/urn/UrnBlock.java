package com.devdyna.synergy.init.builder.urn;

import javax.annotation.Nullable;

import com.devdyna.synergy.api.coreBE.BaseBlockBE;
import com.devdyna.synergy.utils.LevelUtil;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.items.ItemHandlerHelper;
import net.neoforged.neoforge.items.ItemStackHandler;

@SuppressWarnings("null")
public class UrnBlock extends BaseBlockBE {

    public UrnBlock() {
        super(Properties.of().strength(0.4f).destroyTime(0.4f).sound(SoundType.DECORATED_POT)
                .mapColor(MapColor.TERRACOTTA_BROWN));
    }

    @Override
    @Nullable
    public BlockEntity newBlockEntity(BlockPos arg0, BlockState arg1) {
        return new UrnBE(arg0, arg1);
    }

    @Override
    protected VoxelShape getShape(BlockState s, BlockGetter l, BlockPos p, CollisionContext c) {
        return Block.box(5, 0, 5, 11, 6, 11);
    }

    @Override
    protected void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean movedByPiston) {

        if (state.getBlock() != newState.getBlock())
            if (level.getBlockEntity(pos) instanceof UrnBE be) {

                be.drops();

                level.updateNeighbourForOutputSignal(pos, this);
            }

        super.onRemove(state, level, pos, newState, movedByPiston);
    }

    // @Override
    // protected InteractionResult useWithoutItem(BlockState state, Level level,
    // BlockPos pos, Player player,
    // BlockHitResult hitResult) {
    // LogUtil.info("without fired");
    // return super.useWithoutItem(state, level, pos, player, hitResult);
    // }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos,
            Player player, InteractionHand hand, BlockHitResult hitResult) {

        if (hand.equals(InteractionHand.MAIN_HAND) && level != null)

        {
            var be = level.getBlockEntity(pos);

            if (be instanceof UrnBE urn) {

                ItemStackHandler storage = urn.getStorage();

                var items = storage.getStackInSlot(0);

                if (items.isEmpty()) { // storage items are empty

                    if (stack.isEmpty()) {
                        level.playSound(null, pos,
                                SoundEvents.DECORATED_POT_INSERT_FAIL,
                                SoundSource.BLOCKS, 1F * (LevelUtil.chance(50, level) ? 1f : 0.75f), 1);

                    } else {
                        // LogUtil.info("storage items are empty");
                        storage.insertItem(0, stack.copy(), false);
                        stack.shrink(stack.getCount());
                        level.playSound(null, pos,
                                SoundEvents.DECORATED_POT_INSERT,
                                SoundSource.BLOCKS, 1F * (LevelUtil.chance(50, level) ? 1f : 0.75f), 1);

                    }

                } else {

                    if (stack.isEmpty()) { // hand item are empty
                        // LogUtil.info("hand item are empty");
                        ItemHandlerHelper.giveItemToPlayer(player, storage.extractItem(0, items.getCount(), false));
                        level.playSound(null, pos,
                                SoundEvents.DECORATED_POT_INSERT,
                                SoundSource.BLOCKS, 1F * (LevelUtil.chance(50, level) ? 1f : 0.75f), 1);

                    } else

                    if (items.is(stack.getItem())) { // item match
                        // LogUtil.info("item match");
                        if (items.getCount() + stack.getCount() <= items.getMaxStackSize()) { // less / perfect items
                            // LogUtil.info("less");
                            items.setCount(items.getCount() + stack.getCount());
                            stack.shrink(stack.getCount());
                            level.playSound(null, pos,
                                    SoundEvents.DECORATED_POT_INSERT,
                                    SoundSource.BLOCKS, 1F * (LevelUtil.chance(50, level) ? 1f : 0.75f), 1);

                        } else { // more items
                            // LogUtil.info("more");
                            stack.setCount(stack.getCount() - (items.getMaxStackSize() - items.getCount()));
                            items.setCount(items.getMaxStackSize());
                            level.playSound(null, pos,
                                    SoundEvents.DECORATED_POT_INSERT,
                                    SoundSource.BLOCKS, 1F * (LevelUtil.chance(50, level) ? 1f : 0.75f), 1);

                        }
                    } else // dont match
                    {
                        // LogUtil.info("dont match");
                        ItemHandlerHelper.giveItemToPlayer(player, items.copy());
                        items.setCount(0);
                        level.playSound(null, pos,
                                SoundEvents.DECORATED_POT_INSERT,
                                SoundSource.BLOCKS, 1F * (LevelUtil.chance(50, level) ? 1f : 0.75f), 1);
                    }
                }
                urn.setChanged();
                return ItemInteractionResult.SUCCESS;
            }
        }
        return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
    }

}
