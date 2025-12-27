package com.devdyna.synergy.init.builder;

import net.minecraft.world.item.Item;

@SuppressWarnings("null")
@Deprecated
public class ItemComponents extends ItemToolTipped {

    public ItemComponents(Properties properties,String traslationkey) {
        super(properties,traslationkey);
    }

    public ItemComponents() {
        this(new Item.Properties(),"crafting_ingredient");
    }

}
