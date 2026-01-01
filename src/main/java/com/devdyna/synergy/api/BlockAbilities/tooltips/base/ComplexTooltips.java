package com.devdyna.synergy.api.BlockAbilities.tooltips.base;

import java.util.*;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.TooltipFlag;

public interface ComplexTooltips {

    public static final int REGISTRY_ID = 0;
    public static final int BELOW_THE_REGISTRY_ID = 1;

    public abstract void renderTip(List<Component> t, TooltipFlag f);

}
