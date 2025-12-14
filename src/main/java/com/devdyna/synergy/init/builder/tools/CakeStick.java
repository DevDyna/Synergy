package com.devdyna.synergy.init.builder.tools;

import java.awt.Color;
import java.util.List;
import com.devdyna.synergy.Main;
import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.api.utils.ColorUtil;
import com.devdyna.synergy.api.utils.LevelUtil;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CakeBlock;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

@SuppressWarnings({ "deprecation", "null" })
public class CakeStick extends Item {

    public CakeStick() {
        super(new Properties().stacksTo(1).durability(7));
    }

    @Override
    public InteractionResult useOn(UseOnContext c) {
        var pos = c.getClickedPos();
        var dir = c.getClickedFace();
        var level = c.getLevel();
        var item = c.getItemInHand();
        var state = level.getBlockState(pos);
        var offset = level.getBlockState(pos.relative(dir));
        var player = c.getPlayer();
        var hand = c.getHand();

        // found a cake
        if (state.getBlock() instanceof CakeBlock) {
            var bites = state.getValue(BlockStateProperties.BITES);
            if (bites != 0) {
                bites--;
                level.setBlockAndUpdate(pos, state.setValue(BlockStateProperties.BITES, bites));

                player.swing(hand);
                item.hurtAndBreak(1, player, Player.getSlotForHand(hand));

                if (!level.isClientSide())
                    LevelUtil.addDustParticle(255, 255, 255, (ServerLevel) level, pos, true, 16);

                return InteractionResult.SUCCESS;
            }
        }

        // valid *cake place*
        if (offset.canBeReplaced()
                && state.isSolid()) {
            level.setBlockAndUpdate(pos.relative(dir),
                    Blocks.CAKE.defaultBlockState().setValue(BlockStateProperties.BITES, 6));

            player.swing(hand);
            item.hurtAndBreak(1, player, Player.getSlotForHand(hand));

            if (!level.isClientSide())
                LevelUtil.addDustParticle(255, 255, 255, (ServerLevel) level, pos, true, 16);

            return InteractionResult.SUCCESS;
        }

        return super.useOn(c);
    }

    @Override
    public Component getName(ItemStack stack) {
        var level = Minecraft.getInstance().level;
        int color = (level == null ? Color.RED.getRGB() : ColorUtil.rgbColor(level));
        return Component.translatable(this.getDescriptionId(stack)).withColor(color);
    }

    @Override
    public void appendHoverText(ItemStack i, TooltipContext c, List<Component> t,
            TooltipFlag f) {
        t.add(Component.translatable(Main.ID + "." + zStatic.Items.cake_stick));
    }
}
