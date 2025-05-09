package com.devdyna.synergy;

import com.devdyna.synergy.compat.core;
import com.devdyna.synergy.init.Material;
import com.devdyna.synergy.utils.LogUtil;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

@Mod(Main.MODID)
public class Main {

    public static final String MODID = "synergy";

    public Main(IEventBus bus, ModContainer mc) {

        new LogUtil();

        Material.register(bus);
        core.registerCompat();

        // NeoForge.EVENT_BUS.register(new event());

    }
}
