package com.devdyna.synergy.init.builder.reactor.moderator;

import java.util.List;

import com.devdyna.synergy.Main;
import com.devdyna.synergy.zStatic;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item.TooltipContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.MapColor;

@SuppressWarnings("null")
public abstract class ModeratorBase extends Block {

    public ModeratorBase() {
        super(Properties.of().strength(1.0f).destroyTime(1.0f).sound(SoundType.CHAIN).mapColor(MapColor.METAL));
    }

    public abstract int getMultiplier();

    @Override
    public void appendHoverText(ItemStack i, TooltipContext c, List<Component> t,
            TooltipFlag f) {

        t.add(Component.translatable(Main.ID + "." +
        zStatic.ReactorStuff.moderator));

        if (f.hasShiftDown()) {
            t.add(Component.translatable(Main.ID + "." + zStatic.ReactorStuff.moderator + ".multiplier")
                    .append(" " + getMultiplier()));
        } else {
            t.add(Component.translatable(Main.ID + "." + zStatic.tips.SHIFT));
        }
    }

}
