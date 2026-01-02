package com.devdyna.synergy.init.builder.tools;

import java.util.function.Consumer;

import com.devdyna.synergy.Main;
import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.api.beLogic.SimpleAOE;
import com.devdyna.synergy.init.types.zComponents;

import net.minecraft.ChatFormatting;
import net.minecraft.core.GlobalPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.context.UseOnContext;

@SuppressWarnings("null")
public class Configurator extends Item {

        public Configurator() {
                super(new Properties().stacksTo(1)
                                .component(zComponents.GLOBAL_POS, null)

                );
        }

        @Override
        public InteractionResult useOn(UseOnContext c) {
                var level = c.getLevel();
                var pos = c.getClickedPos();
                var block = level.getBlockState(c.getClickedPos());
                var item = c.getItemInHand();
                var player = c.getPlayer();
                var hand = c.getHand();
                var be = level.getBlockEntity(pos);

                if (hand == InteractionHand.MAIN_HAND)
                        if (be != null)
                                if (be instanceof SimpleAOE) {

                                        if (level.isClientSide())
                                                player.playSound(SoundEvents.AMETHYST_BLOCK_RESONATE);

                                        else {
                                                item.set(zComponents.GLOBAL_POS, new GlobalPos(level.dimension(), pos));

                                                player.swing(hand);

                                                player.displayClientMessage(
                                                                Component.translatable(
                                                                                Main.ID + "." + zStatic.Items.configurator
                                                                                                + ".link")
                                                                                .append(Component.literal(block
                                                                                                .getBlock()
                                                                                                .getName()
                                                                                                .getString())),
                                                                true);
                                        }

                                        return InteractionResult.SUCCESS;
                                }

                return super.useOn(c);
        }

@Override
public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay tooltipDisplay,
    Consumer<Component> tooltipAdder, TooltipFlag flag) {

                var nbt = stack.get(zComponents.GLOBAL_POS);

                tooltipAdder.accept(Component.translatable(Main.ID + "." + zStatic.Items.configurator + ".tip"));

                if (nbt != null) {

                        var pos = nbt.pos();
                        var dim = nbt.dimension();

                        tooltipAdder.accept(Component.translatable(Main.ID + "." + zStatic.Items.configurator + ".dim")
                                        .append(Component
                                                        .literal(dim.identifier().toString())
                                                        .withStyle(ChatFormatting.GREEN)));
                        tooltipAdder.accept(Component.translatable(Main.ID + "." + zStatic.Items.configurator + ".blockpos")

                                        .append(Component
                                                        .literal("" + pos.getX())
                                                        .withStyle(ChatFormatting.AQUA))

                                        .append(Component
                                                        .literal(" " + pos.getY())
                                                        .withStyle(ChatFormatting.AQUA))

                                        .append(Component
                                                        .literal(" " + pos.getZ())
                                                        .withStyle(ChatFormatting.AQUA)));
                }
        }
}
