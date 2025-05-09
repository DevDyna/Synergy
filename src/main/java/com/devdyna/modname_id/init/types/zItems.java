package com.devdyna.modname_id.init.types;

import com.devdyna.modname_id.Main;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class zItems {
    public static void register(IEventBus bus) {
        zItem.register(bus);
        zTool.register(bus);
        zBlockItem.register(bus);
    }
    // ---------------------------------------------------------------------------------------//
    public static final DeferredRegister.Items zTool = DeferredRegister.createItems(Main.MODID);
    //DONT USE IT , ONLY FUNCTIONAL
    public static final DeferredRegister.Items zBlockItem = DeferredRegister.createItems(Main.MODID);
    public static final DeferredRegister.Items zItem = DeferredRegister.createItems(Main.MODID);
    // ---------------------------------------------------------------------------------------//

    // public static final DeferredHolder<Item, Item> IT = zItem.registerSimpleItem("it");


}
