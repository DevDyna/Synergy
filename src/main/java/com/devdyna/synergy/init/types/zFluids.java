package com.devdyna.synergy.init.types;

import static com.devdyna.synergy.Main.ID;

import java.awt.Color;

import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.api.registers.FluidRegister;
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

        public static final FluidRegister OIL = FluidRegister.create(zStatic.Fluids.oil, 0xFF202020).pushEntity();

        public static final FluidRegister SAP = FluidRegister.create(zStatic.Fluids.sap, 0xFFF2A619).pushEntity();

        public static final FluidRegister GLUE = FluidRegister.create(zStatic.Fluids.glue, 0xDAF3EFE6).pushEntity();

        public static final FluidRegister IRONBERRY_JUICE = FluidRegister
                        .create(zStatic.Fluids.ironberry_juice, Color.LIGHT_GRAY.brighter().brighter()).pushEntity();

        public static final FluidRegister LIQUID_GLASS = FluidRegister
                        .create(zStatic.Fluids.liquid_glass, Color.WHITE.brighter())
                        .pushEntity();

        public static final FluidRegister HONEY = FluidRegister.create(zStatic.Fluids.honey, Color.YELLOW).pushEntity();

        // ---------------------------------------------------------------------------------------//
        public static final FluidRegister MOLTEN_IRON = FluidRegister
                        .create(zStatic.Fluids.iron, Color.LIGHT_GRAY.brighter())
                        .pushEntity().setTextures(x.rl("block/fluid/base/still"), x.rl("block/fluid/base/flow"));

        public static final FluidRegister MOLTEN_COPPER = FluidRegister
                        .create(zStatic.Fluids.copper, Color.ORANGE.darker())
                        .pushEntity().setTextures(x.rl("block/fluid/base/still"), x.rl("block/fluid/base/flow"));

        public static final FluidRegister MOLTEN_GOLD = FluidRegister
                        .create(zStatic.Fluids.gold, Color.YELLOW.brighter()).pushEntity()
                        .setTextures(x.rl("block/fluid/base/still"), x.rl("block/fluid/base/flow"));

        public static final FluidRegister MOLTEN_STEEL = FluidRegister.create(zStatic.Fluids.steel, Color.GRAY.darker())
                        .pushEntity()
                        .setTextures(x.rl("block/fluid/base/still"), x.rl("block/fluid/base/flow"));

        public static final FluidRegister MOLTEN_URANIUM = FluidRegister
                        .create(zStatic.Fluids.uranium, Color.GREEN.brighter().brighter())
                        .pushEntity().setTextures(x.rl("block/fluid/base/still"), x.rl("block/fluid/base/flow"));

        public static final FluidRegister MOLTEN_NICKEL = FluidRegister
                        .create(zStatic.Fluids.nickel, Color.YELLOW.darker()).pushEntity()
                        .setTextures(x.rl("block/fluid/base/still"), x.rl("block/fluid/base/flow"));

        public static final FluidRegister MOLTEN_SILVER = FluidRegister
                        .create(zStatic.Fluids.silver, Color.CYAN.brighter()).pushEntity()
                        .setTextures(x.rl("block/fluid/base/still"), x.rl("block/fluid/base/flow"));

        public static final FluidRegister MOLTEN_TIN = FluidRegister
                        .create(zStatic.Fluids.tin, Color.LIGHT_GRAY.darker()).pushEntity()
                        .setTextures(x.rl("block/fluid/base/still"), x.rl("block/fluid/base/flow"));

        public static final FluidRegister MOLTEN_ALUMINUM = FluidRegister
                        .create(zStatic.Fluids.aluminum, Color.WHITE.darker())
                        .pushEntity().setTextures(x.rl("block/fluid/base/still"), x.rl("block/fluid/base/flow"));

        public static final FluidRegister MOLTEN_IRIDIUM = FluidRegister
                        .create(zStatic.Fluids.iridium, Color.MAGENTA.darker())
                        .pushEntity().setTextures(x.rl("block/fluid/base/still"), x.rl("block/fluid/base/flow"));

        public static final FluidRegister MOLTEN_PLATINUM = FluidRegister
                        .create(zStatic.Fluids.platinum, Color.CYAN.darker())
                        .pushEntity().setTextures(x.rl("block/fluid/base/still"), x.rl("block/fluid/base/flow"));

        public static final FluidRegister MOLTEN_OSMIUM = FluidRegister.create(zStatic.Fluids.osmium, Color.CYAN)
                        .pushEntity()
                        .setTextures(x.rl("block/fluid/base/still"), x.rl("block/fluid/base/flow"));

        public static final FluidRegister MOLTEN_LEAD = FluidRegister
                        .create(zStatic.Fluids.lead, Color.PINK.darker().darker())
                        .pushEntity().setTextures(x.rl("block/fluid/base/still"), x.rl("block/fluid/base/flow"));

        public static final FluidRegister MOLTEN_ANCIENT_DEBRIS = FluidRegister
                        .create(zStatic.Fluids.ancient_debris, Color.PINK.darker().darker())
                        .pushEntity().setTextures(x.rl("block/fluid/base/still"), x.rl("block/fluid/base/flow"));

        public static final FluidRegister MOLTEN_BLAZE = FluidRegister
                        .create(zStatic.Fluids.blaze, Color.YELLOW.brighter().brighter())
                        .pushEntity().setTextures(x.rl("block/fluid/custom/still"), x.rl("block/fluid/custom/flow"));

        public static final FluidRegister RUBBER = FluidRegister
                        .create(zStatic.Fluids.rubber, 0xDAF3EFE6)
                        .pushEntity().setTextures(x.rl("block/fluid/custom/still"), x.rl("block/fluid/custom/flow"));

        public static final FluidRegister SULFURIC_ACID = FluidRegister
                        .create(zStatic.Fluids.sulfuric_acid, Color.YELLOW).pushEntity().drown();

        public static final FluidRegister MOLTEN_BRONZE = FluidRegister
                        .create(zStatic.Fluids.bronze, Color.ORANGE.darker())
                        .pushEntity().setTextures(x.rl("block/fluid/base/still"), x.rl("block/fluid/base/flow"));

}
