package com.devdyna.synergy.init.builder;

import java.util.List;
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
    public ItemToolTipped(Properties properties,String traslationkey) {
        super(properties);
        this.traslationkey = traslationkey;
    }

    public ItemToolTipped(String traslationkey){
        this(new Item.Properties(), traslationkey);
    }

//TODO move to separate event
    // @Override
    // public void appendHoverText(ItemStack i, TooltipContext c, List<Component> t,
    //         TooltipFlag f) {
    //     t.add(Component.translatable(Main.ID +"."+ traslationkey));
    // }

}
