package com.devdyna.synergy.init.builder.tools;

import java.util.List;

import com.devdyna.synergy.Main;
import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.api.components.ModeTypes;
import com.devdyna.synergy.init.types.zBlockTag;
import com.devdyna.synergy.init.types.zComponents;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

@SuppressWarnings("null")
public class Configurator extends Item {

        boolean toggle = false;

        public Configurator() {
                super(new Properties().stacksTo(1)
                                .component(zComponents.MODE, ModeTypes.SHOW_AOE)
                                .component(zComponents.BLOCKPOS, null));
        }

        @Override
        public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
                var item = player.getItemInHand(usedHand);

                if (player.getUsedItemHand() == InteractionHand.MAIN_HAND)

                        if (player.isCrouching()) {
                                item.set(zComponents.BLOCKPOS, null);
                                item.set(zComponents.MODE,
                                                (item.get(zComponents.MODE) == ModeTypes.SHOW_AOE ? ModeTypes.SHOW_TRACK
                                                                : ModeTypes.SHOW_AOE));
                                player.displayClientMessage(
                                                Component.translatable(Main.ID + "." + zStatic.Items.configurator
                                                                + ".modetype")
                                                                .append(Component.translatable(Main.ID + "."
                                                                                + zStatic.Items.configurator + ".mode."
                                                                                + (item.get(zComponents.MODE) != ModeTypes.SHOW_AOE
                                                                                                ? ModeTypes.SHOW_TRACK
                                                                                                : ModeTypes.SHOW_AOE))),
                                                true);

                                player.playSound((toggle ? SoundEvents.STONE_BUTTON_CLICK_OFF
                                                : SoundEvents.STONE_BUTTON_CLICK_ON), 1F, 0.1F);
                                toggle = !toggle;
                                return InteractionResultHolder.success(item);
                        }

                return super.use(level, player, usedHand);
        }

        @Override
        public InteractionResult useOn(UseOnContext c) {
                var level = c.getLevel();
                var pos = c.getClickedPos();
                var block = level.getBlockState(c.getClickedPos());
                var item = c.getItemInHand();
                var player = c.getPlayer();
                var hand = c.getHand();
                if (hand == InteractionHand.MAIN_HAND) {
                        if (block.is(zBlockTag.AOE_RENDER)) {
                                player.swing(hand);
                                item.set(zComponents.MODE, ModeTypes.SHOW_AOE);
                                item.set(zComponents.BLOCKPOS, pos);
                                player.displayClientMessage(
                                                Component.translatable(Main.ID + "." + zStatic.Items.configurator
                                                                + ".modetype")
                                                                .append(Component.translatable(
                                                                                Main.ID + "." + zStatic.Items.configurator
                                                                                                + ".mode."
                                                                                                + ModeTypes.SHOW_AOE)),
                                                true);
                                player.playSound(SoundEvents.AMETHYST_BLOCK_RESONATE);
                                return InteractionResult.SUCCESS;
                        }

                        if (block.is(zBlockTag.NODE_RENDER)) {
                                player.swing(hand);
                                item.set(zComponents.MODE, ModeTypes.SHOW_TRACK);
                                item.set(zComponents.BLOCKPOS, pos);
                                player.displayClientMessage(
                                                Component.translatable(Main.ID + "." + zStatic.Items.configurator
                                                                + ".modetype")
                                                                .append(Component
                                                                                .translatable(Main.ID + "."
                                                                                                + zStatic.Items.configurator
                                                                                                + ".mode."
                                                                                                + ModeTypes.SHOW_TRACK)),
                                                true);
                                player.playSound(SoundEvents.AMETHYST_BLOCK_RESONATE);
                                return InteractionResult.SUCCESS;
                        }
                }

                return super.useOn(c);
        }

        @Override
        public void appendHoverText(ItemStack i, TooltipContext c, List<Component> t,
                        TooltipFlag f) {
                var pos = i.get(zComponents.BLOCKPOS);
                var mode = i.get(zComponents.MODE);

                t.add(Component.translatable(Main.ID + "." + zStatic.Items.configurator + ".tip"));

                t.add(Component.translatable(Main.ID + "." + zStatic.Items.configurator + ".modetype")
                                .append(Component.translatable(
                                                Main.ID + "." + zStatic.Items.configurator + ".mode." + mode)));

                t.add(Component.translatable(Main.ID + "." + zStatic.Items.configurator + ".blockpos")
                                .append((pos != null ? Component.literal("" + pos.getX()).withStyle(ChatFormatting.AQUA)
                                                .append(Component.literal(" " + pos.getY())
                                                                .withStyle(ChatFormatting.AQUA)
                                                                .append(Component.literal(" " + pos.getZ())
                                                                                .withStyle(ChatFormatting.AQUA)))
                                                : Component.literal("null").withStyle(ChatFormatting.RED))));
        }
}
