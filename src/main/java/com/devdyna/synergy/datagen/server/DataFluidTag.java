package com.devdyna.synergy.datagen.server;

import java.util.concurrent.CompletableFuture;

import com.devdyna.synergy.Main;
import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.api.zFluid;
import com.devdyna.synergy.api.utils.ClazzUtil;
import com.devdyna.synergy.init.types.zFluidTags;
import com.devdyna.synergy.init.types.zFluids;

import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.FluidTagsProvider;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

@SuppressWarnings({ "unchecked", "null" })

public class DataFluidTag extends FluidTagsProvider {

        public DataFluidTag(PackOutput o, CompletableFuture<Provider> l, ExistingFileHelper f) {
                super(o, l, Main.ID, f);
        }

        @Override
        protected void addTags(Provider p) {

                tag(zFluidTags.MOLTEN_FLUIDS)
                                .add(ClazzUtil.getAllzFluids(zStatic.Fluids.AFFIX_MOLTEN).stream()
                                                .map(zFluid::getFluid)
                                                .toArray(Fluid[]::new));

                tag(zFluidTags.ALUMINUM_MOLTEN).add(zFluids.MOLTEN_ALUMINUM.getFluid());
                tag(zFluidTags.COPPER_MOLTEN).add(zFluids.MOLTEN_COPPER.getFluid());
                tag(zFluidTags.GOLD_MOLTEN).add(zFluids.MOLTEN_GOLD.getFluid());
                tag(zFluidTags.IRIDIUM_MOLTEN).add(zFluids.MOLTEN_IRIDIUM.getFluid());
                tag(zFluidTags.IRON_MOLTEN).add(zFluids.MOLTEN_IRON.getFluid());
                tag(zFluidTags.LEAD_MOLTEN).add(zFluids.MOLTEN_LEAD.getFluid());
                tag(zFluidTags.NICKEL_MOLTEN).add(zFluids.MOLTEN_NICKEL.getFluid());
                tag(zFluidTags.OSMIUM_MOLTEN).add(zFluids.MOLTEN_OSMIUM.getFluid());
                tag(zFluidTags.PLATINUM_MOLTEN).add(zFluids.MOLTEN_PLATINUM.getFluid());
                tag(zFluidTags.SILVER_MOLTEN).add(zFluids.MOLTEN_SILVER.getFluid());
                tag(zFluidTags.STEEL_MOLTEN).add(zFluids.MOLTEN_STEEL.getFluid());
                tag(zFluidTags.TIN_MOLTEN).add(zFluids.MOLTEN_TIN.getFluid());
                tag(zFluidTags.URANIUM_MOLTEN).add(zFluids.MOLTEN_URANIUM.getFluid());

        }

}