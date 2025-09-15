package com.devdyna.synergy.init.builder.tools;

import java.util.List;

import com.devdyna.synergy.Main;
import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.api.beLogic.AreaOfEffect;
import com.devdyna.synergy.init.types.zComponents;
import com.devdyna.synergy.init.types.zItems;

import net.minecraft.ChatFormatting;
import net.minecraft.core.GlobalPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;

@SuppressWarnings("null")
public class Configurator extends Item {

        public Configurator() {
                super(new Properties().stacksTo(1)
                .craftRemainder(zItems.CONFIGURATOR.get())
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
                                if (be instanceof AreaOfEffect) {

                                        if (level.isClientSide)
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
        public void appendHoverText(ItemStack i, TooltipContext c, List<Component> t,
                        TooltipFlag f) {
                var nbt = i.get(zComponents.GLOBAL_POS);

                t.add(Component.translatable(Main.ID + "." + zStatic.Items.configurator + ".tip"));

                if (nbt != null) {

                        var pos = nbt.pos();
                        var dim = nbt.dimension();

                        t.add(Component.translatable(Main.ID + "." + zStatic.Items.configurator + ".dim")
                                        .append(Component
                                                        .literal(dim.registry().toString())
                                                        .withStyle(ChatFormatting.GREEN)));
                        t.add(Component.translatable(Main.ID + "." + zStatic.Items.configurator + ".blockpos")

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
