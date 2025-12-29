package com.devdyna.synergy.init.builder.tools;

import java.util.function.Consumer;

import com.devdyna.synergy.Main;
import com.devdyna.synergy.zStatic;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.neoforged.neoforge.common.Tags;

@SuppressWarnings("null")
public class Croock extends HoeItem {

    public Croock(ToolMaterial tier) {
        super(tier, 0.1f, 4f, new Item.Properties());
    }

    public boolean isValidRepairItem(ItemStack toRepair, ItemStack repair) {
        return repair.is(Tags.Items.RODS_WOODEN);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay tooltipDisplay,
            Consumer<Component> tooltipAdder, TooltipFlag flag) {
        tooltipAdder.accept(Component.translatable(Main.ID + "." + zStatic.Items.wooden_crook));
    }

}
