package com.devdyna.modname_id.init.types;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import com.devdyna.modname_id.Main;

public class zBlocks {
        public static void register(IEventBus bus) {
                registerLists();
                zBlock.register(bus);
                zBlockItem.register(bus);
        }

        // ---------------------------------------------------------------------------------------//
        public static final DeferredRegister.Blocks zBlock = DeferredRegister.createBlocks(Main.MODID);
        public static final DeferredRegister.Blocks zBlockItem = DeferredRegister.createBlocks(Main.MODID);
        // ---------------------------------------------------------------------------------------//

        // public static final DeferredHolder<Block, ?> BK = zBlock.register("bk",() ->
        // new BK());

        // ---------------------------------------------------------------------------------------//

        public static void registerLists() {

                // List.of(...).forEach(p -> ??);

        }

}
