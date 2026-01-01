package com.devdyna.synergy.api.BlockAbilities.tooltips.base;

import java.util.*;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.TooltipFlag;

public interface ComplexTooltips {

    public abstract void renderTip(List<Component> t, TooltipFlag f);

}
