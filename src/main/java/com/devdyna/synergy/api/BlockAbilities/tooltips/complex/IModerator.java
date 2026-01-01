package com.devdyna.synergy.api.BlockAbilities.tooltips.complex;

import java.util.*;

import com.devdyna.synergy.Main;
import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.api.BlockAbilities.tooltips.base.ComplexTooltips;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.TooltipFlag;

public interface IModerator extends ComplexTooltips {

    @Override
    default void renderTip(List<Component> t, TooltipFlag f) {
        if (f.hasShiftDown()) {
            t.add(1,conditions());
            t.add(2,Component.translatable(Main.ID + "." + zStatic.ReactorStuff.moderator + ".fe")
                    .append("" + FEReducer()));
            t.add(3,Component.translatable(Main.ID + "." + zStatic.ReactorStuff.moderator + ".heat")
                    .append("" + HeatReducer()));
        } else {
            t.add(1,Component.translatable(Main.ID + "." + zStatic.ReactorStuff.moderator));
            t.add(2,Component.translatable(Main.ID + "." + zStatic.tips.SHIFT));

        }
    }

    public abstract Component conditions();

    public abstract float FEReducer();

    public abstract float HeatReducer();

}
