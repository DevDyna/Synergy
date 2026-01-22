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

    public static final zFluid OIL = zFluid.create(zStatic.Fluids.oil, 0xFF202020).pushEntity();

    public static final zFluid SAP = zFluid.create(zStatic.Fluids.sap, 0xFFF2A619).pushEntity();

    public static final zFluid GLUE = zFluid.create(zStatic.Fluids.glue, 0xDAF3EFE6).pushEntity();

    public static final zFluid IRONBERRY_JUICE = zFluid.create(zStatic.Fluids.ironberry_juice, 0xD1D1D1E3).pushEntity();

    public static final zFluid LIQUID_GLASS = zFluid.create(zStatic.Fluids.liquid_glass, 0xFFFFFFDD).pushEntity();

    public static final zFluid HONEY = zFluid.create(zStatic.Fluids.honey, 0xF69707DD).pushEntity();

  // ---------------------------------------------------------------------------------------//
    public static final zFluid MOLTEN_IRON = zFluid.create(zStatic.Fluids.iron, 0xF6D14A2C).pushEntity();

    public static final zFluid MOLTEN_COPPER = zFluid.create(zStatic.Fluids.copper, 0xF6E0702A).pushEntity();                                                                                        
    
    public static final zFluid MOLTEN_GOLD = zFluid.create(zStatic.Fluids.gold, 0xF6FFD34A).pushEntity(); 
    
    public static final zFluid MOLTEN_STEEL = zFluid.create(zStatic.Fluids.steel, 0xF6B0B0B0).pushEntity(); 
                                                                                                            
    public static final zFluid MOLTEN_URANIUM = zFluid.create(zStatic.Fluids.uranium, 0xF65CFF3A).pushEntity(); 
                                                                                                            
    public static final zFluid MOLTEN_NICKEL = zFluid.create(zStatic.Fluids.nickel, 0xF6CFCFCF).pushEntity(); 
                                                                                                              
    public static final zFluid MOLTEN_SILVER = zFluid.create(zStatic.Fluids.silver, 0xF6E6E6E6).pushEntity(); 
                                                                                                              
    public static final zFluid MOLTEN_TIN = zFluid.create(zStatic.Fluids.tin, 0xF6D6D6D6).pushEntity(); 
    
    public static final zFluid MOLTEN_ALUMINUM = zFluid.create(zStatic.Fluids.aluminum, 0xF6E0E0E0).pushEntity(); 
                                                                                                                 
    public static final zFluid MOLTEN_IRIDIUM = zFluid.create(zStatic.Fluids.iridium, 0xF64B5B7A).pushEntity(); 
                                                                                                                
    public static final zFluid MOLTEN_PLATINUM = zFluid.create(zStatic.Fluids.platinum, 0xF6DADFE3).pushEntity(); 
                                                                                                                 
    public static final zFluid MOLTEN_OSMIUM = zFluid.create(zStatic.Fluids.osmium, 0xF63A3F4B).pushEntity(); 
                                                                                                              
    public static final zFluid MOLTEN_LEAD = zFluid.create(zStatic.Fluids.lead, 0xF6555560).pushEntity(); 
                                                                                                          

}
