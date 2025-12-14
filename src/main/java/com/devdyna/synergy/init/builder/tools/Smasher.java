package com.devdyna.synergy.init.builder.tools;

import java.util.List;

import com.devdyna.synergy.init.types.zBlockTag;
import com.devdyna.synergy.Main;
import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.api.pipe.pipeType;
import com.devdyna.synergy.api.utils.LevelUtil;

import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;

@SuppressWarnings("null")
public class Smasher extends Item {

    public Smasher() {
        super(new Properties().stacksTo(1));
    }

    @Override
    public InteractionResult useOn(UseOnContext c) {
        var state = c.getLevel().getBlockState(c.getClickedPos());
        if (state.is(zBlockTag.MASHABLE) && c.getPlayer().isCrouching()) {

            c.getPlayer().playSound(SoundEvents.ITEM_FRAME_REMOVE_ITEM);

            c.getPlayer().swing(c.getPlayer().getUsedItemHand());
            pipeType.onDestroyPipe(state, c.getLevel(), c.getClickedPos());

            c.getLevel().removeBlock(c.getClickedPos(), false);
            LevelUtil.popItemFromPos(c.getLevel(), c.getClickedPos(), new ItemStack(state.getBlock()));

            return InteractionResult.SUCCESS_NO_ITEM_USED;
        }
        return super.useOn(c);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents,
            TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable(Main.ID + "." + zStatic.Items.smasher));
    }
}
