package com.devdyna.synergy.init.builder.pipeTools;

import java.util.List;

import com.devdyna.synergy.init.builder._core.pipes.pipeProperties;
import com.devdyna.synergy.init.builder._core.pipes.pipeType;
import com.devdyna.synergy.init.types.zBlockTag;
import com.devdyna.synergy.Main;
import com.devdyna.synergy.zStatic;

import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.neoforged.neoforge.capabilities.Capabilities;

@SuppressWarnings("null")
public class PipeRefactorizer extends Item {

    public PipeRefactorizer() {
        super(new Properties().stacksTo(1));
    }

    // TODO dont work
    @Override
    public InteractionResult useOn(UseOnContext c) {
        var level = c.getLevel();
        var pos = c.getClickedPos();
        var state = level.getBlockState(pos);
        var player = c.getPlayer();
        if (state.is(zBlockTag.PIPE_CONNECTORS)) {

            player.playSound(SoundEvents.CHERRY_WOOD_BUTTON_CLICK_ON);

            player.swing(player.getUsedItemHand());

            for (Direction face : pipeType.DIRECTIONS) {

                var offset = level.getBlockState(pos.relative(face));
                if (offset.is(zBlockTag.PIPE_CONNECTORS)) {
                    // TIP. default is true

                    // connect to another pipe connector
                    if (offset.getValue(pipeType.PROPRTIES
                            .get(pipeType.DIRECTIONS.indexOf(face.getOpposite()))) != pipeProperties.NODE)
                        level.setBlockAndUpdate(pos.relative(face),
                                offset = offset.setValue(
                                        pipeType.PROPRTIES.get(pipeType.DIRECTIONS.indexOf(face.getOpposite())),
                                        pipeProperties.TRUE));
                } else {

                    // connect to BE itemhandler
                    if (level.getBlockEntity(pos.relative(face)) != null
                            && Capabilities.ItemHandler.BLOCK.getCapability(level, pos.relative(face),
                                    level.getBlockState(pos.relative(face)),
                                    level.getBlockEntity(pos.relative(face)), face.getOpposite()) != null) {
                        state = state.setValue(pipeType.PROPRTIES.get(pipeType.DIRECTIONS.indexOf(face)),
                                pipeProperties.OUTPUT);
                    } else {
                        // remove connection

                        state = state.setValue(pipeType.PROPRTIES.get(pipeType.DIRECTIONS.indexOf(face)),
                                pipeProperties.FALSE);
                    }
                }
            }

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
