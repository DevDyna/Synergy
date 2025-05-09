package com.devdyna.modname_id;

import com.devdyna.modname_id.compat.core;
import com.devdyna.modname_id.init.Material;
import com.devdyna.modname_id.utils.LogUtil;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

@Mod(Main.MODID)
public class Main {

    public static final String MODID = "modname_id";

    public Main(IEventBus bus, ModContainer mc) {

        new LogUtil();

        Material.register(bus);
        core.registerCompat();

        // NeoForge.EVENT_BUS.register(new event());

    }
}
