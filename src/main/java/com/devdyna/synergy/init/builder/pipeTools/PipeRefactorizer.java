package com.devdyna.synergy.init.builder.pipeTools;

import java.util.List;

import com.devdyna.synergy.init.builder._core.pipes.nodeType;
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
        super(new Properties().stacksTo(1));
    }

    @Override
    public InteractionResult useOn(UseOnContext c) {
        var level = c.getLevel();
        var pos = c.getClickedPos();
        var state = level.getBlockState(pos);
        var player = c.getPlayer();
        if (state.is(zBlockTag.CAN_CONNECT)) {

            player.playSound(SoundEvents.CHERRY_WOOD_BUTTON_CLICK_ON);

            player.swing(player.getUsedItemHand());

            if (state.is(zBlockTag.PIPE))
                state = pipeType.updatePipeOnPlace(state, level, pos);

            if (state.is(zBlockTag.NODE))
                state = nodeType.updateNodeOnPlace(state, level, pos, state.getValue(nodeType.FACING).getOpposite());

            level.setBlockAndUpdate(pos, state);

            return InteractionResult.SUCCESS;
        }
        return super.useOn(c);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents,
            TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable(Main.ID + "." + zStatic.PipeStuff.tools.refactorizer));
    }
}
