package com.devdyna.synergy.init.types;

import static com.devdyna.synergy.Main.ID;

import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.api.zFluid;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries.Keys;

public class zFluids {
    public static void register(IEventBus bus) {
        zFluids.register(bus);
        zFluidTypes.register(bus);
    }

    // ---------------------------------------------------------------------------------------//
    public static final DeferredRegister<Fluid> zFluids = DeferredRegister.create(BuiltInRegistries.FLUID, ID);
    public static final DeferredRegister<FluidType> zFluidTypes = DeferredRegister.create(Keys.FLUID_TYPES, ID);

    // ---------------------------------------------------------------------------------------//

    // public static final zFluid CRYSTALLINE = zFluid.create("crystalline",
    // 0x7cefe3);
    public static final zFluid OIL = zFluid.create(zStatic.Fluids.OIL, 0x20202001).pushEntity();
    public static final zFluid SAP = zFluid.create(zStatic.Fluids.SAP, 0xe6ad3a00).pushEntity();
    public static final zFluid GLUE = zFluid.create(zStatic.Fluids.GLUE, 0xfde9a601).pushEntity();

}
