package com.devdyna.synergy.init.types;

import static com.devdyna.synergy.Main.ID;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

@SuppressWarnings("unused")
public class zFluids {
        public static void register(IEventBus bus) {
        zFluids.register(bus);
    }

    // ---------------------------------------------------------------------------------------//
    public static final DeferredRegister<Fluid> zFluids = DeferredRegister.create(BuiltInRegistries.FLUID, ID);

    // ---------------------------------------------------------------------------------------//

//     public static final DeferredHolder<Fluid, ?> CRYSTALLINE_SOURCE = zFluids.register("source",
//             () -> new BaseFlowingFluid.Source(zProperties.FProp));
//     public static final DeferredHolder<Fluid, FlowingFluid> CRYSTALLINE_FLOWING = zFluids.register("flowing",
//             () -> new BaseFlowingFluid.Flowing(zProperties.FProp));
}
