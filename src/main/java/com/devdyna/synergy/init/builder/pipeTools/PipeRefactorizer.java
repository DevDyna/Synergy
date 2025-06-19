package com.devdyna.synergy.init.builder.pipeTools;

import java.util.List;

import com.devdyna.synergy.init.Material;
import com.devdyna.synergy.init.builder._core.pipes.pipeType;
import com.devdyna.synergy.init.types.zBlockTag;
import com.devdyna.synergy.Main;
import com.devdyna.synergy.zStatic;

import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;

@SuppressWarnings("null")
public class PipeRefactorizer extends Item {

    public PipeRefactorizer() {
        super(Material.iProp);
    }

    @Override
    public InteractionResult useOn(UseOnContext c) {
        var state = c.getLevel().getBlockState(c.getClickedPos());
        if (state.is(zBlockTag.PIPE_CONNECTORS)) {

            c.getPlayer().playSound(SoundEvents.UI_BUTTON_CLICK.value());

            c.getPlayer().swing(c.getPlayer().getUsedItemHand());
            state = pipeType.updatePipeOnPlace(state, c.getLevel(), c.getClickedPos());
            return InteractionResult.SUCCESS_NO_ITEM_USED;
        }
        return super.useOn(c);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents,
            TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable(Main.ID + "." + zStatic.PipeStuff.tools.refactorizer));
    }
}
