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

 public static final zFluid OIL =
    zFluid.create(zStatic.Fluids.OIL, 0xFF202020).pushEntity();

public static final zFluid SAP =
    zFluid.create(zStatic.Fluids.SAP, 0xFFF2A619).pushEntity();

public static final zFluid GLUE =
    zFluid.create(zStatic.Fluids.GLUE, 0xDAF3EFE6).pushEntity();

public static final zFluid IRONBERRY_JUICE =
    zFluid.create(zStatic.Fluids.IRONBERRY_JUICE, 0xD1D1D1E3).pushEntity();

public static final zFluid LIQUID_GLASS =
    zFluid.create(zStatic.Fluids.LIQUID_GLASS, 0xFFFFFFDD).pushEntity();

public static final zFluid HONEY =
    zFluid.create(zStatic.Fluids.HONEY, 0xF69707DD).pushEntity();

}
