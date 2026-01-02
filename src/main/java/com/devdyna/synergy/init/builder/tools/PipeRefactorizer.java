package com.devdyna.synergy.init.builder.tools;

import com.devdyna.synergy.init.builder.ItemToolTipped;
import com.devdyna.synergy.init.types.zBlockTag;
import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.api.node.nodeType;
import com.devdyna.synergy.api.pipe.pipeType;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.context.UseOnContext;

@SuppressWarnings("null")
public class PipeRefactorizer extends ItemToolTipped {

    public PipeRefactorizer() {
        super(new Properties().stacksTo(1),zStatic.Items.refactorizer);
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


}
