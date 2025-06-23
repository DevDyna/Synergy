package com.devdyna.synergy.init.builder._core.crops;

import java.util.List;

import javax.annotation.Nonnull;

import com.devdyna.synergy.utils.LevelUtil;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder.Reference;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.neoforge.capabilities.Capabilities.FluidHandler;
import net.neoforged.neoforge.capabilities.Capabilities.ItemHandler;
import net.neoforged.neoforge.fluids.capability.templates.FluidHandlerItemStack;
import net.neoforged.neoforge.items.ItemHandlerHelper;

@SuppressWarnings("null")
public class BaseCropBlock extends CropBlock {

    public BaseCropBlock(Properties properties) {
        super(properties.mapColor(MapColor.PLANT)
                .noCollission()
                .randomTicks()
                .instabreak()
                .sound(SoundType.CROP)
                .pushReaction(PushReaction.DESTROY));
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos,
            Player player, InteractionHand hand, BlockHitResult hitResult) {

        if (!level.isClientSide && isMaxAge(state)) {
            List<ItemStack> drops = Block.getDrops(state, (ServerLevel) level, pos, null, player, stack);

            drops.forEach(item -> ItemHandlerHelper.giveItemToPlayer(player, item));

            level.setBlockAndUpdate(pos,
                    this.defaultBlockState().setValue(getAgeProperty(), LevelUtil.getRandomValue(getMaxAge() - 2, level)));
        }

        return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
    }

}
