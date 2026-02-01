package com.devdyna.synergy.init.types;

import com.devdyna.synergy.init.Material;

import net.minecraft.tags.TagKey;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.bus.api.IEventBus;

public class zFluidTags {
    public static void register(IEventBus bus) {

    }

    public static final TagKey<Fluid> MOLTEN_FLUIDS = Material.tagFluid("molten_fluids");

    public static final TagKey<Fluid> IRON_MOLTEN = Material.tagFluid("molten_fluids/iron");
    public static final TagKey<Fluid> GOLD_MOLTEN = Material.tagFluid("molten_fluids/gold");
    public static final TagKey<Fluid> COPPER_MOLTEN = Material.tagFluid("molten_fluids/copper");
    public static final TagKey<Fluid> ALUMINUM_MOLTEN = Material.tagFluid("molten_fluids/aluminum");
    public static final TagKey<Fluid> IRIDIUM_MOLTEN = Material.tagFluid("molten_fluids/iridium");
    public static final TagKey<Fluid> LEAD_MOLTEN = Material.tagFluid("molten_fluids/lead");
    public static final TagKey<Fluid> NICKEL_MOLTEN = Material.tagFluid("molten_fluids/nickel");
    public static final TagKey<Fluid> OSMIUM_MOLTEN = Material.tagFluid("molten_fluids/osmium");
    public static final TagKey<Fluid> PLATINUM_MOLTEN = Material.tagFluid("molten_fluids/platinum");
    public static final TagKey<Fluid> SILVER_MOLTEN = Material.tagFluid("molten_fluids/silver");
    public static final TagKey<Fluid> STEEL_MOLTEN = Material.tagFluid("molten_fluids/steel");
    public static final TagKey<Fluid> TIN_MOLTEN = Material.tagFluid("molten_fluids/tin");
    public static final TagKey<Fluid> URANIUM_MOLTEN = Material.tagFluid("molten_fluids/uranium");
    
    public static final TagKey<Fluid> MELTER_FUELS = Material.tagFluid("melter/fuels");

}
