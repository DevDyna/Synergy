package com.devdyna.synergy;

import com.devdyna.synergy.compat.core;
import com.devdyna.synergy.events.blockEvents;
import com.devdyna.synergy.init.Material;
import com.devdyna.synergy.init.dataMaps.zDataMaps;
import com.devdyna.synergy.utils.LogUtil;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;

@Mod(Main.ID)
public class Main {

    public static final String ID = "synergy";

    public Main(IEventBus bus, ModContainer mc) {

        new LogUtil();

        Material.register(bus);
        core.registerCompat();

        NeoForge.EVENT_BUS.register(blockEvents.class);

        bus.addListener(Capabilities::register);
        bus.addListener(zDataMaps::register);

    }

}
