package com.devdyna.synergy.init.types;

import static com.devdyna.synergy.Main.ID;

import java.awt.Color;

import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.api.zFluid;
import com.devdyna.synergy.api.utils.x;

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

    public static final zFluid IRONBERRY_JUICE = zFluid.create(zStatic.Fluids.ironberry_juice, Color.LIGHT_GRAY.brighter().brighter()).pushEntity();

    public static final zFluid LIQUID_GLASS = zFluid.create(zStatic.Fluids.liquid_glass, Color.WHITE.brighter()).pushEntity();

    public static final zFluid HONEY = zFluid.create(zStatic.Fluids.honey, Color.YELLOW).pushEntity();

  // ---------------------------------------------------------------------------------------//
    public static final zFluid MOLTEN_IRON = zFluid.create(zStatic.Fluids.iron, Color.LIGHT_GRAY.brighter()).pushEntity().setTextures(x.rl( "block/fluid/still"),x.rl( "block/fluid/flow"));

    public static final zFluid MOLTEN_COPPER = zFluid.create(zStatic.Fluids.copper, Color.ORANGE.darker().darker()).pushEntity().setTextures(x.rl( "block/fluid/still"),x.rl( "block/fluid/flow"));                                                                                       
    
    public static final zFluid MOLTEN_GOLD = zFluid.create(zStatic.Fluids.gold, Color.YELLOW.brighter()).pushEntity().setTextures(x.rl( "block/fluid/still"),x.rl( "block/fluid/flow")); 
    
    public static final zFluid MOLTEN_STEEL = zFluid.create(zStatic.Fluids.steel, Color.GRAY.darker()).pushEntity().setTextures(x.rl( "block/fluid/still"),x.rl( "block/fluid/flow")); 
                                                                                                            
    public static final zFluid MOLTEN_URANIUM = zFluid.create(zStatic.Fluids.uranium, Color.GREEN.brighter().brighter()).pushEntity().setTextures(x.rl( "block/fluid/still"),x.rl( "block/fluid/flow")); 
                                                                                                            
    public static final zFluid MOLTEN_NICKEL = zFluid.create(zStatic.Fluids.nickel, Color.YELLOW.darker()).pushEntity().setTextures(x.rl( "block/fluid/still"),x.rl( "block/fluid/flow")); 
                                                                                                              
    public static final zFluid MOLTEN_SILVER = zFluid.create(zStatic.Fluids.silver, Color.CYAN.brighter()).pushEntity().setTextures(x.rl( "block/fluid/still"),x.rl( "block/fluid/flow")); 
                                                                                                              
    public static final zFluid MOLTEN_TIN = zFluid.create(zStatic.Fluids.tin, Color.LIGHT_GRAY.darker()).pushEntity().setTextures(x.rl( "block/fluid/still"),x.rl( "block/fluid/flow")); 
    
    public static final zFluid MOLTEN_ALUMINUM = zFluid.create(zStatic.Fluids.aluminum, Color.WHITE.darker()).pushEntity().setTextures(x.rl( "block/fluid/still"),x.rl( "block/fluid/flow")); 
                                                                                                                 
    public static final zFluid MOLTEN_IRIDIUM = zFluid.create(zStatic.Fluids.iridium, Color.MAGENTA.darker()).pushEntity().setTextures(x.rl( "block/fluid/still"),x.rl( "block/fluid/flow")); 
                                                                                                                
    public static final zFluid MOLTEN_PLATINUM = zFluid.create(zStatic.Fluids.platinum, Color.CYAN.darker()).pushEntity().setTextures(x.rl( "block/fluid/still"),x.rl( "block/fluid/flow")); 
                                                                                                                 
    public static final zFluid MOLTEN_OSMIUM = zFluid.create(zStatic.Fluids.osmium, Color.CYAN).pushEntity().setTextures(x.rl( "block/fluid/still"),x.rl( "block/fluid/flow")); 
                                                                                                              
    public static final zFluid MOLTEN_LEAD = zFluid.create(zStatic.Fluids.lead, Color.PINK.darker().darker()).pushEntity().setTextures(x.rl( "block/fluid/still"),x.rl( "block/fluid/flow")); 
                                                                                                                                                                                                                  

}
