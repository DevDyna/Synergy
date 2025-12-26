package com.devdyna.synergy.init.builder;

import java.util.List;

import com.devdyna.synergy.Main;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item.TooltipContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.Block;

@SuppressWarnings("null")
@Deprecated
public class BlockToolTipped extends Block {

    // private String traslationkey;

    @Deprecated
    public BlockToolTipped(Properties properties, String traslationkey) {
        super(properties);
        // this.traslationkey = traslationkey;
    }

    @Deprecated
    public BlockToolTipped(String traslationkey) {
        this(Properties.of(), traslationkey);
    }

    // @Override
    // public void appendHoverText(ItemStack i, TooltipContext c, List<Component> t,
    // TooltipFlag f) {
    // t.add(Component.translatable(Main.ID + "." + traslationkey));
    // }

}
