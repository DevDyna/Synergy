package com.devdyna.modname_id.init.types;

import com.devdyna.modname_id.Main;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class zBlockEntities {
    public static void register(IEventBus bus) {
        zBE.register(bus);
    }
    // ---------------------------------------------------------------------------------------//

    public static final DeferredRegister<BlockEntityType<?>> zBE = DeferredRegister
            .create(BuiltInRegistries.BLOCK_ENTITY_TYPE, Main.MODID);
    // ---------------------------------------------------------------------------------------//
    

//     public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<?>> stone = 
//     zBE.register("stone", () -> BlockEntityType.Builder.of(BK::new, Blocks.STONE).build(null));
        

}
