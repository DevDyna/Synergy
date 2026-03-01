package com.devdyna.synergy;

import com.devdyna.synergy.api.utils.LogUtil;
import com.devdyna.synergy.compat.core;
import com.devdyna.synergy.init.Material;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

@Mod(Main.ID)
public class Main {

    public static final String ID = "synergy";

    public Main(IEventBus bus, ModContainer mc) {

        new LogUtil();

        Material.register(bus);
        core.registerCompat();

        GameEvents.register(bus);

        Config.register(mc);

    }

}
