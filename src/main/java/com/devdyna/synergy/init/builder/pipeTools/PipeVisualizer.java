package com.devdyna.synergy.init.builder.pipeTools;

import java.util.List;

import com.devdyna.synergy.init.Material;
import com.devdyna.synergy.Main;
import com.devdyna.synergy.zStatic;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

@SuppressWarnings("null")
public class PipeVisualizer extends Item {

    public PipeVisualizer() {
        super(Material.iProp.stacksTo(1));
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents,
            TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable(Main.ID + "." + zStatic.PipeStuff.tools.visualizer));
    }
}
