package com.devdyna.synergy.init.builder;

import java.util.function.Consumer;

import com.devdyna.synergy.Main;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;

@SuppressWarnings("null")

public class ItemToolTipped extends Item {

    private String traslationkey;

    public ItemToolTipped(Properties properties, String traslationkey) {
        super(properties);
        this.traslationkey = traslationkey;
    }

    public ItemToolTipped(String traslationkey) {
        this(new Item.Properties(), traslationkey);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay tooltipDisplay,
            Consumer<Component> tooltipAdder, TooltipFlag flag) {
        tooltipAdder.accept(Component.translatable(Main.ID + "." + traslationkey));
    }

}
